<script setup>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
var session; //전역 스코프에서 선언
var OV = new OpenVidu();

const API_SERVER_URL = import.meta.env.VITE_API_SERVER_URL;
let sessionId = "";
let connectId = "";
let publisher = "";

async function getAudioInputDevices() {
  try {
    const devices = await navigator.mediaDevices.enumerateDevices();
    const audioInputDevices = devices.filter(
      (device) => device.kind === "audioinput"
    );
    console.log("Available audio input devices:", audioInputDevices);
    audioInputDevices.forEach((device, index) => {
      console.log(
        `Mic ${index}: ${device.label}, Device ID: ${device.deviceId}`
      );
    });
    // 선택 또는 자동 선택 로직을 여기에 구현할 수 있습니다.
    // 예: audioInputDevices[0].deviceId를 사용자의 audioSource로 설정할 수 있습니다.
    return audioInputDevices;
  } catch (error) {
    console.error("Error getting audio input devices:", error);
    return [];
  }
}

// 이 함수를 호출하여 오디오 입력 장치 목록을 가져오고 콘솔에 출력
getAudioInputDevices();

const openSession = async () => {
  try {
    // 성공적으로 통신시 클라이언트측 세션 초기화
    session = OV.initSession();
    console.log(session);
    const response = await axios.post(
      `${API_SERVER_URL}openvidu/api/sessions`,
      {
        // "mediaMode": "ROUTED",
        // "recordingMode": "MANUAL",
        // "customSessionId": "CUSTOM_SESSION_ID",
        // "forcedVideoCodec": "VP8",
        // "allowTranscoding": false,
        // "defaultRecordingProperties": {
        //     "name": "MyRecording",
        //     "hasAudio": true,
        //     "hasVideo": true,
        //     "outputMode": "INDIVIDUAL",
        //     "recordingLayout": "BEST_FIT",
        //     "resolution": "1280x720",
        //     "frameRate": 25,
        //     "shmSize": 536870912,
        //     "mediaNode": "media_openvidu.beevarium.site"
        //  },
        recordingMode: "ALWAYS", //녹화 시점 선택
        customSessionId: "CUSTOM_SESSION_ID_TEST",
        allowTranscoding: true,
        mediaNode: "media_media.beevarium.site",
        defaultRecordingProperties: {
          name: "MyRecording",
          hasAudio: true,
          hasVideo: true,
          outputMode: "COMPOSED", //녹화되는 화면의 형태
          recordingLayout: "BEST_FIT",
          resolution: "1280x720",
          frameRate: 25,
          shmSize: 536870912,
        },
      }
    );
    console.log("세션 생성됨", response.data);
    sessionId = response.data;

    //세션 열기 성공시, 자동으로 publisher로 연결
    await connectSession("PUBLISHER");
  } catch (error) {
    console.error("Error", error);
  }
};
// 세션 닫기 (방송자)
const closeSession = async () => {
  try {
    //클라이언트측 스트림 unpublish
    session.unpublish(publisher);
    // 서버에서 세션 delete 요청 보내기
    await axios.delete(`${API_SERVER_URL}openvidu/api/sessions/${sessionId}`);
    //stream publish 취소하기
    console.log("세션 연결 끊김");
  } catch (error) {
    console.error("Error", error);
  }
};

// 세션 연결 (connection) - 방송 만든사람
const connectSession = async (role = "PUBLISHER") => {
  try {
    const response = await axios.post(
      `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection`,
      {
        type: "WEBRTC",
        data: "My Server Data",
        role: "PUBLISHER",
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
        publisher = OV.initPublisher("my-video", {
          videoSource: "screen", //카메라 X, 화면 공유 설정
          audioSource:
            "6b5f46efba42a0e41745111c21dfeecb1bd09f0c271f97b23caeb8ee24ac3ab6", // 마이크 오디오 사용
          publishAudio: true, // 오디오 발행 활성화
          publishVideo: true, // 비디오 발행 활성화
        });
        publisher.on("accessAllowed", () => {
          //발행한 스트림에 오디오트랙이 포함되어 있는지 확인
          const audioTracks = publisher.stream
            .getMediaStream()
            .getAudioTracks();
          console.log("오디오 트랙 정보", audioTracks);
        });

        // mainstreamer = publisher;
        session
          .publish(publisher)
          .then(() => {
            console.log("화면 공유 스트림 발생 성공");
            // 오디오 입력중인지 확인
            // publisher.on('streamAudioVolumeChange', event => {
            //   console.log("Current volume:", event.value.newValue);
            //   // 여기서 event.value.newValue는 오디오 볼륨의 변화를 나타냅니다.
            //   // 볼륨이 0보다 크면 오디오가 입력되고 있음을 의미합니다.
            // });
            // STT 구독 성공여부

            session
              .subscribeToSpeechToText(publisher.stream, "ko-KR")
              .then(() => {
                console.log("Speech-to-Text 구독 성공");
                // STT 구독이 성공적이라면
                session.on("speechToTextMessage", (event) => {
                  console.log("음성 변환 인식됨");
                  console.log(event);
                  // axios
                  //   .post(`https://2778-112-166-150-139.ngrok-free.app`, {
                  //     prompt: event.text,
                  //   })
                  //   .then((response) => {
                  //     console.log(response);
                  //   })
                  //   .catch((error) => {
                  //     console.error("변환 실패", error);
                  //   });
                });
              })
              .catch((error) => {
                console.error("Speech-to-Text 구독 실패:", error);
              });

            //  // 녹화 테스트 코드
            // const response2 = axios.post(`${API_SERVER_URL}openvidu/api/sessions/${sessionId}/recordings/MyRecording`)
            // console.log(response2.data)
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
    console.error("세션 연결 실패", error);
  }
};
// 세션 연결 - 방송 참여자
// ssesionID = opensession 하면 전역변수로 생김
const subscribeStream = async (role = "SUBSCRIBER") => {
  let subscriber;
  try {
    const response = await axios.post(
      `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection`,
      {
        type: "WEBRTC",
        data: "My Server Data",
        record: true,
        role: "SUBSCRIBER",
      }
    );
    //connectId, token = 서버 측 응답으로부터 받아옴
    connectId = response.data.connectionId;
    const token = response.data.connectionToken;
    // 세션 생성 이벤트를 감지하는 함수
    session.on("streamCreated", (event) => {
      subscriber = session.subscribe(event.stream, "subscriber-video");
    });
    // 세션 종료 이벤트를 감지하는 함수
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
    session.disconnect();
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
//연결된 모든 세션 데이터
const retrieveAll = async () => {
  try {
    const response = await axios.get(`${API_SERVER_URL}openvidu/api/sessions`);
    console.log("모든 새션 데이터", response.data);
  } catch (error) {
    console.error("Error", error);
  }
};
// 녹화 시작
const startRecording = async () => {
  try {
    const response = await axios.post(
      `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/recordings/MyRecording`,
      {
        // "id": "ses_YnDaGYNcd7",
        object: "recording",
        name: "MyRecording",
        outputMode: "INDIVIDUAL",
        hasAudio: true,
        hasVideo: true,
        resolution: "1280x720",
        frameRate: 25,
        sessionId: "CUSTOM_SESSION_ID",
        mediaNode: "media_media.beevarium.site",
        size: 303072692,
        duration: 108000.234,
        url: `${API_SERVER_URL}openvidu/recordings/CUSTOM_SESSION_ID/MyRecording.mp4`,
        status: "ready",
        recordingLayout: "BEST_FIT",
      }
    );
    console.log(response.data);
  } catch (error) {
    console.log(error);
  }
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
      <button @click="getAudioInputDevices">장치 확인</button>
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
