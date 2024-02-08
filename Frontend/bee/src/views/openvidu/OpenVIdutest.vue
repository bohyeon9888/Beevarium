<script setup>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
var session; //전역 스코프에서 선언
var OV = new OpenVidu();
var mainstreamer;

const API_SERVER_URL = import.meta.env.VITE_API_SERVER_URL;
let sessionId = "";
let connectId = "";

const openSession = async () => {
  try {
    // 성공적으로 통신시 클라이언트측 세션 초기화
    session = OV.initSession()
    console.log(session)
    const response = await axios.post(`${API_SERVER_URL}openvidu/api/sessions`, {
      "mediaMode": "ROUTED",
      "recordingMode": "MANUAL",
      "customSessionId": "CUSTOM_SESSION_ID",
      "forcedVideoCodec": "VP8",
      "allowTranscoding": false,
      "defaultRecordingProperties": {
          "name": "MyRecording",
          "hasAudio": true,
          "hasVideo": true,
          "outputMode": "COMPOSED",
          "recordingLayout": "BEST_FIT",
          "resolution": "1280x720",
          "frameRate": 25,
        "shmSize": 536870912,
        "mediaNode": "media_media.beevarium.site"
       },
    })
    console.log('세션 생성됨', response.data)
    sessionId = response.data
  
    //세션 열기 성공시, 자동으로 publisher로 연결
    await connectSession("PUBLISHER");

    //세션 열기 성공시, 자동으로 publisher로 연결
    await connectSession("PUBLISHER");
  } catch (error) {
    console.error("Error", error);
  }
};
// 세션 닫기
const closeSession = async () => {
  try {
    if (mainstreamer) {
      //stream publish 취소하기
      session.unpublish(mainstreamer);
      // 서버에서 세션 delete 요청 보내기
      await axios.delete(`${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection/${connectId}`)
      console.log("세션 연결 끊김")
    }
  }
 
  catch (error) {
    console.error("Error", error);
  }
};

// 세션 연결 (connection) - 방송 만든사람
const connectSession = async (role = "PUBLISHER") => {
  try {
    const response = await axios.post(
      `${API_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
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
    //여기서 client 단 세션 join (with token)
    session
      .connect(token)
      .then(() => {
        console.log("클라이언트측 세션 연결 성공");
        var publisher = OV.initPublisher("my-video", {
          videoSource: "screen", //카메라 X, 화면 공유 설정
        });
        mainstreamer = publisher;
        session.publish(publisher)
          .then(() => {
            console.log("화면 공유 스트림 발생 성공");
            //여기서 전역변수에 저장
          })
          .catch((error) => {
            console.error("화면 공유 스트림 발행 실패", error);
          });
      })
      .catch((error) => {
        console.error("클라이언트측 세션 연결 실패", error);
      });
  } catch (error) {
    console.error("Error", error);
  }
};
// 세션 연결 - 방송 참여자
// ssesionID = opensession 하면 전역변수로 생김
const subscribeStream = async (role = "SUBSCRIBER") => {
  let subscriber;
  try {
    const response = await axios.post(
      `${API_SERVER_URL}openvidu/api/sessions/CUSTOM_SESSION_ID/connection`,
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

    //커넥트 아이디, 토큰 - 내 연결에서 받아와야 함. sessionID = 퍼블리셔갸 열어놓은 sessionID 글로벌 스코프로 선언되어 있음.
    connectId = response.data.connectionId;
    // console.log("세션 connection", response.data)
    const token = response.data.connectionToken;
    session.on("streamCreated", (event) => {
      subscriber = session.subscribe(event.stream, "subscriber-video");
    });
    // 세션 종료시
    session.on("sessionDisconnected", (event) => {
      console.log("세션이 종료되었습니다.");
    });
    session
      .connect(token)
      .then(() => {
        console.log("새션 연결 성공, subscriber 연결 성공");
      })
      .catch((error) => {
        console.error("subscriber 연결 실패", error);
      });
  } catch (error) {
    console.error("세션 연결 실패", error);
  }
};
//세션 연결 끊기 (시청자 )
const disconnectSession = async () => {
  try {
    session.disconnect()
  }
 
  catch (error) {
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

const retrieveAll = async () => {
  try {
    const response = await axios.get(`${API_SERVER_URL}openvidu/api/sessions`);
    console.log("모든 새션 데이터", response.data);
  } catch (error) {
    console.error("Error", error);
  }
};

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
      <!-- <button @click="connectSession('SUBSCRIBER')">구독자로 세션 연결하기</button> -->
      <button @click="disconnectSession">세션 연결(connection) 끊기</button>
      <button @click="subscribeStream">구독자로 세션 연결</button>
      <!-- 현재 열려있는 모든 세션 확인 -->
      <button @click="retrieveAll">모든 세션 확인</button>
      <!--세션에 연결된 connection 확인하기 위한 get 요청 -->
      <button @click="connectionList">연결된 커넥션 확인</button>
      <button @click="startRecording">녹화 시작</button>
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
