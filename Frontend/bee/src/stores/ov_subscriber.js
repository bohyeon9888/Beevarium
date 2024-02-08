import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";

export const useOVSStore = defineStore(
  "OVSStore",
  () => {
    var session;
    var OV = new OpenVidu();
    var mainstreamer;

    axios.defaults.headers.post["Content-Type"] = "application/json";

    const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;
    let sessionId = "";
    let connectId = "";

    // 세션 연결 - 방송 참여자
    // ssesionID = opensession 하면 전역변수로 생김
    const subscribeToSession = async () => {
      try {
        // 세션 생성
        session = OV.initSession();

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
            videoElement.style.width = "1280px"; // 너비를 640픽셀로 설정
            videoElement.style.height = "720px"; // 높이를 480픽셀로 설정
          });
          console.log("새로운 스트림 구독 시작");
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
          `${API_SERVER_URL}openvidu/api/sessions/CUSTOM_SESSION_ID2/connection`,
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

    return { subscribeToSession, sessionId, connectId };
  },
  {
    persist: true,
  }
);
