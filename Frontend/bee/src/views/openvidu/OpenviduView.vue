<script setup>
import { ref } from "vue";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";

axios.defaults.headers.post["Content-Type"] = "application/json";

const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;

const OV = new OpenVidu();
const session = ref(null);

const subscribeToSession = async () => {
  const subscriber = ref(null);
  try {
    // 세션 생성
    session.value = OV.initSession();

    // 세션 이벤트 핸들러 추가
    session.value.on("streamCreated", (event) => {
      subscriber.value = session.value.subscribe(
        event.stream,
        "subscriber-video"
      );
      console.log("새로운 스트림 구독 시작");
    });

    // 세션에 연결할 토큰 가져오기
    const token = await getToken();

    // 세션에 연결
    await session.value.connect(token);
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
        customIceServers: [
          {
            url: API_SERVER_URL,
          },
        ],
      }
    );
    return response.data.connectionToken;
  } catch (error) {
    console.error("Failed to get token", error);
    throw error; // 예외를 다시 던지고 호출하는 쪽에서 처리할 수 있도록 합니다.
  }
};
</script>

<template>
  <div class="outer">
    <div id="my-video">
      <h1>스트리머 측 화면</h1>
    </div>
    <div id="subscriber-video">
      <h1>방송 참여자 측 화면</h1>
    </div>
    <div class="buttons">
      <button @click="subscribeToSession">구독자로 세션 연결</button>
    </div>
  </div>
</template>

<style scoped>
button {
  height: 100px;
  color: black;
  margin: 20px;
  font-size: 20px;
}
.outer {
  display: flex;
  flex-direction: column;
}

.buttons {
  display: flex;
  flex-direction: row;
}
</style>
