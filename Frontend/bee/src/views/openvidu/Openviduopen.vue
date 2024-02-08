<script setup>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import { ref } from "vue";

var session;
var OV = new OpenVidu();
var mainstreamer;

axios.defaults.headers.post["Content-Type"] = "application/json";

const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;
let sessionId = "";
let connectId = "";

const openSession = async () => {
  try {
    // 성공적으로 통신시 클라이언트측 세션 초기화
    session = OV.initSession();
    const response = await axios.post(
      `${API_SERVER_URL}openvidu/api/sessions`,
      {
        mediaMode: "ROUTED",
        recordingMode: "MANUAL",
        customSessionId: "CUSTOM_SESSION_ID2",
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
          videoSource: ["screen", "camera"], // 카메라와 화면 공유 설정
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
    await axios.delete(`${API_SERVER_URL}openvidu/api/sessions/${sessionId}`);
    console.log("세션 닫힘");
    //클라이언트측 세션 닫기 -> 필요없나??
  } catch (error) {
    console.error("Error", error);
  }
};

//세션 닫기 (방송자)
const disconnectSession = async () => {
  try {
    //화면 공유 중지
    if (mainstreamer) {
      session.unpublish(mainstreamer);
    }
    await axios.delete(
      `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection/${connectId}`
    );
    //퍼블리시 종료
    console.log("세션 연결 끊김");
  } catch (error) {
    console.error("Error", error);
  }
};
//세션에 연결된 connection 확인
const connectionList = async () => {
  try {
    const response = await axios.get(
      `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection/${connectId}`
    );
    console.log("연결된 세션 정보", response.data);
  } catch (error) {
    console.error("Error", error);
  }
};
// 세션 연결 - 방송 참여자
// ssesionID = opensession 하면 전역변수로 생김
const subscribeToSession = async () => {
  try {
    // 세션 생성
    session.value = OV.initSession();

    // 세션 이벤트 핸들러 추가
    session.value.on("streamCreated", (event) => {
      const subscriber = session.value.subscribe(
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
      }
    );
    return response.data.connectionToken;
  } catch (error) {
    console.error("Failed to get token", error);
    throw error; // 예외를 다시 던지고 호출하는 쪽에서 처리할 수 있도록 합니다.
  }
};

const retrieveAll = async () => {
  try {
    const response = await axios.get(`${API_SERVER_URL}openvidu/api/sessions`);
    console.log("모든 새션 데이터", response.data);
  } catch (error) {
    console.error("Error", error);
  }
};
// 녹화 시작
// const startRecording = async () => {
//   try {
//     const response = await axios.post(`${API_SERVER_URL}openvidu/api/recordings/start`, {

//     })

//   }
// }
const disablevideo = () => {
  const videoEnabled = !mainstreamer.stream.videoActive;
  mainstreamer.publishVideo(videoEnabled);
};
</script>

<template>
  <div class="outer">
    <!-- 스트리머 비디오 송출되는 공간 -->
    <div id="my-video">
      <h1>스트리머 측 화면</h1>
      <button @click="disablevideo">화면 공유 중지</button>
    </div>
    <div id="subscriber-video">
      <h1>방송 참여자 측 화면</h1>
    </div>
    <div class="buttons">
      <!-- 시청자로 커넥션 연습 -->
      <!-- <div class="subscribe"></div> -->
      <button @click="openSession">방송하기(스트리머)</button>
      <button @click="closeSession">세션 닫기</button>
      <button @click="disconnectSession">세션 연결(connection) 끊기</button>
      <!-- 현재 열려있는 모든 세션 확인 -->
      <button @click="retrieveAll">모든 세션 확인</button>
      <!--세션에 연결된 connection 확인하기 위한 get 요청 -->
      <button @click="connectionList">연결된 커넥션 확인</button>
      <!-- <button @click="startRecording">녹화 시작</button> -->
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
