import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import { useAuthStore } from "./user";
import { storeToRefs } from "pinia";

export const useOVPStore = defineStore(
  "OVPStore",
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

    // 메시지를 추가하는 함수
    const addMessage = (message) => {
      console.log("몇번 호출?");
      messagee.value = message;
      console.log(messagee.value);
    };

    const openSession = async () => {
      try {
        // 성공적으로 통신시 클라이언트측 세션 초기화
        session = OV.initSession();
        const response = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions`,
          {
            mediaMode: "ROUTED",
            recordingMode: "MANUAL",
            customSessionId: "CUSTOM_SESSION_ID3",
            forcedVideoCodec: "VP8",
            allowTranscoding: false,
            defaultRecordingProperties: {
              name: "MyRecording",
              hasAudio: true,
              hasVideo: true,
              outputMode: "COMPOSED",
              recordingLayout: "BEST_FIT",
              resolution: "1280x720",
              frameRate: 25,
              shmSize: 536870912,
            },
          }
        );
        console.log("세션 생성됨", response.data);
        sessionId = response.data;
        // 세션 열기 성공시, 자동으로 publisher로 연결
        await connectSession("PUBLISHER");
      } catch (error) {
        console.error("Error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11", error);
      }
    };

    const connectSession = async (role = "PUBLISHER") => {
      try {
        const response = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection`,
          {
            type: "WEBRTC",
            data: "My Server Data",
            record: true,
            role: "PUBLISHER",
            kurentoOptions: {
              videoMaxRecvBandwidth: 1000,
              videoMinRecvBandwidth: 300,
              videoMaxSendBandwidth: 1000,
              videoMinSendBandwidth: 300,
              allowedFilters: ["GStreamerFilter", "ZBarFilter"],
            },
          }
        );

        connectId = response.data.connectionId;
        console.log("세션 connection", response.data);
        const token = response.data.connectionToken;

        // 클라이언트 단 세션 join (with token)
        session
          .connect(token)
          .then(() => {
            console.log("클라이언트측 세션 연결 성공");

            // 퍼블리셔의 카메라 및 화면 공유 설정
            var publisher = OV.initPublisher("my-video", {
              videoSource: "screen",
              // videoDimensions: '{"width":890, "height":493}',
              // 카메라와 화면 공유 설정
            });

            publisher.on("videoElementCreated", (event) => {
              // 비디오 엘리먼트에 접근
              var videoElement = event.element;

              // 비디오 엘리먼트의 크기를 고정된 값으로 설정
              videoElement.style.width = "890px"; // 너비를 640픽셀로 설정W
              videoElement.style.height = "493px"; // 높이를 480픽셀로 설정
            });

            mainstreamer = publisher;

            session
              .publish(publisher)
              .then(() => {
                console.log("화면 및 카메라 공유 스트림 발생 성공", sessionId);
              })
              .catch((error) => {
                console.error("화면 및 카메라 공유 스트림 발행 실패", error);
              });

            session.on("signal:my-chat", (event) => {
              console.log(event.data);
              console.log(event.name); // Message
              addMessage(event.data); // The type of message
            });
          })
          .catch((error) => {
            console.error("클라이언트측 세션 연결 실패", error);
          });
      } catch (error) {
        console.error("Error", error);
      }
    };

    // 세션 닫기
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
      openSession,
      connectSession,
      closeSession,
      sendMessage1,
      receiveMessage,
      addMessage,
      messagee,
      sessionId,
      connectId,
    };
  },
  {
    persist: true,
  }
);
