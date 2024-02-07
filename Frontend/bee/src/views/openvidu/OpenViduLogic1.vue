<script setup>
// 페이지 구성 : 방송하기 버튼, 썸네일
// 방송하기 버튼 클릭 -> 세션 열림 -> 여기서 나온 sessionID를 대쉬보드로 넘겨줘야 함 -> 방송자로 connect
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import { useRouter } from 'vue-router'
const OV = new OpenVidu() 
//openvidu api 요청 url 
const API_SERVER_URL = import.meta.env.VITE_API_SERVER_URL 
const router = useRouter()

var session = ''
var sessionId = ''
let connectId = ''


const createSession = async () => {
  console.log("버튼 눌림")
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
        "mediaNode": "media_openvidu.beevarium.site"
       },
    })
    console.log('세션 생성됨', response.data)
    sessionId = response.data
    
    // 세션 열기 성공시, 자동으로 publisher로 연결
    await connectSession("PUBLISHER")
    // 다음 페이지에서 스트림을 publish 하기 위해 sessionId, connectId 넘겨주기 
    // router.push({ nama: 'OpenviduLogic2', query: { sessionId, connectId } })
    
  }
  catch (error) {
    console.error('Error', error)
  }
}

// 세션 연결 (connection) - 방송 만든사람
const connectSession = async (role="PUBLISHER") => {
  try {
    const response = await axios.post(`${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection`, {
      "type": "WEBRTC",
      "data": "My Server Data",
      "record": true,
      "role": "PUBLISHER",
      "kurentoOptions": {
        "videoMaxRecvBandwidth": 1000,
        "videoMinRecvBandwidth": 300,
        "videoMaxSendBandwidth": 1000,
        "videoMinSendBandwidth": 300,
        "allowedFilters": ["GStreamerFilter", "ZBarFilter"]
      }
    })

    connectId = response.data.connectionId
    console.log("세션 connection", response.data)
    const token = response.data.connectionToken
    //여기서 client 단 세션 join (with token)
    session.connect(token)
      .then(() => {
        console.log("클라이언트측 세션 연결 성공")
        router.push({ name: 'OpenviduLogic2', query: { sessionId, connectId } })
      })
      .catch(error => {
        console.error("클라이언트측 세션 연결 실패",error)
      })

  }
    catch (error) {
      console.error("Error",error)
    }
}
</script>

<template>
  <div>
    <h1>page 1 : 방송하기(대쉬보드 이동) 버튼, 썸네일</h1>
    <button @click="createSession"> 대쉬보드 이동 </button>
    
  </div>
</template>

<style scoped>
button{
  font-size: 30px;
  color: red
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
</style>
