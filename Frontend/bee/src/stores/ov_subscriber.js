import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import { useAuthStore } from "./user";
import { storeToRefs } from "pinia";

export const useOVSStore = defineStore(
  "OVSStore",
  () => {
    const authStore = useAuthStore();
    const { user } = storeToRefs(authStore);
    var session;
    var OV = new OpenVidu();
    var mainstreamer;

    axios.defaults.headers.post["Content-Type"] = "application/json";

    const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;
    let sessionId = "";
    let connectId = "";

    const messagee = ref("");
    const subtitle = ref("");

    // 메시지를 추가하는 함수
    const addMessage = (message) => {
      console.log("몇번 호출?");
      messagee.value = message;
      console.log(messagee.value);
    };

    const addSubtitle = (signal) => {
      subtitle.value = signal;
      console.log(subtitle.value);
    };

    // 세션 연결 - 방송 참여자
    // ssesionID = opensession 하면 전역변수로 생김
    const subscribeToSession = async () => {
      if (session && session.connection) {
        await session.disconnect(); // 이전 세션 연결 해제
        OV = new OpenVidu(); // OpenVidu 객체 초기화
      }
      try {
        // 세션 생성
        session = OV.initSession();
        messagee.value = "";
        // 세션 이벤트 핸들러 추가
        session.on("streamCreated", (event) => {
          const subscriber = session.subscribe(
            event.stream,
            "subscriber-video"
          );
          subscriber.on("videoElementCreated", (event) => {
            // 비디오 엘리먼트에 접근
            var videoElement = event.element;

            // 비디오 엘리먼트의 크기를 고정된 값으로 설정
            videoElement.style.width = "1100px";
            videoElement.style.height = "619px";
          });
          console.log("새로운 스트림 구독 시작");
        });

        session.on("signal:my-chat", (event) => {
          console.log(event.data);
          console.log(event.name); // Message
          addMessage(event.data); // The type of message
        });

        session.on("signal:subtitles", (event) => {
          console.log("Received subtitle signal:", event.data);
          addSubtitle(event.data);
        });

        // 세션에 연결할 토큰 가져오기
        const token = await getToken();

        // 세션에 연결
        await session.connect(token);
        console.log("Subscriber connected successfully");
      } catch (error) {
        console.error("Subscriber connection failed", error);
      }
    };

    const getToken = async () => {
      try {
        const response = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions/CUSTOM_SESSION_ID3/connection`,
          {
            type: "WEBRTC",
            data: "My Server Data",
            record: true,
            role: "SUBSCRIBER",
            kurentoOptions: {
              videoMaxRecvBandwidth: 1000,
              videoMinRecvBandwidth: 300,
              videoMaxSendBandwidth: 1000,
              videoMinSendBandwidth: 300,
              allowedFilters: ["GStreamerFilter", "ZBarFilter"],
            },
          }
        );
        return response.data.connectionToken;
      } catch (error) {
        console.error("Failed to get token", error);
        throw error; // 예외를 다시 던지고 호출하는 쪽에서 처리할 수 있도록 합니다.
      }
    };
    const closeSession = async () => {
      try {
        await axios.delete(
          `${API_SERVER_URL}openvidu/api/sessions/${sessionId}`
        );
        console.log("세션 닫힘");
        //클라이언트측 세션 닫기 -> 필요없나??
      } catch (error) {
        console.error("Error", error);
      }
    };

    const sendMessage1 = async (message) => {
      try {
        const currentTime = new Date(); // 현재 시간 가져오기
        const messageData = {
          message: message,
          name: user.value.name,
          time: currentTime.toISOString(), // ISO 형식으로 시간을 문자열로 변환
        };

        // 메시지 브로드캐스트
        await session.signal({
          data: JSON.stringify(messageData),
          to: [], // 모든 연결에게 브로드캐스트
          type: "my-chat",
        });
        console.log("Message successfully sent");
      } catch (error) {
        console.error("Error sending message:", error);
      }
    };

    // 채팅 메시지 수신하기
    const receiveMessage = () => {
      console.log("11");
      session.on("signal:my-chat", (event) => {
        console.log(event);
        console.log("Received message:", event.data); // 수신된 메시지 출력
        console.log("Sender:", event.from); // 메시지 보낸 사용자 정보
        console.log("Message type:", event.type); // 메시지 타입 출력
      });
    };

    return {
      subscribeToSession,
      sendMessage1,
      receiveMessage,
      closeSession,
      addMessage,
      messagee,
      sessionId,
      connectId,
      subtitle,
    };
  },
  {
    persist: true,
  }
);
