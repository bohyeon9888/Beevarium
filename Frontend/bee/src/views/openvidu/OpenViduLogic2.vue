<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue';
import { OpenVidu } from 'openvidu-browser'
import { useRouter } from 'vue-router'
var OV = new OpenVidu() 
// var publisher=''
let publisher = ref(null)

onMounted(() => {
  //마운트 된 후 publisher 초기화
  publisher.value = OV.initPublisher('streamerscreen', {
    videoSource: "screen"
  })
  document.getElementById('streamerscreen').appendChild(publisher.value.element)
})

// 녹화 시작
// const startRecording = async () => {
//   try {
//     const response = await axios.post(`${API_SERVER_URL}openvidu/api/recordings/start`, {
//     // "id": "ses_YnDaGYNcd7",
//     "object": "recording",
//     "name": "MyRecording",
//     "outputMode": "COMPOSED",
//     "hasAudio": true,
//     "hasVideo": true,
//     "resolution": "1280x720",
//     "frameRate": 25,
//     "recordingLayout": "CUSTOM",
//     "customLayout": "",
//     "sessionId": "ses_YnDaGYNcd7",
//     "mediaNode": "media_i-po39jr3e10rkjsdfj",
//     "createdAt": 1600564785109,
//     "size": 303072692,
//     "duration": 108000.234,
//     "url": "https://my.openvidu.ip/openvidu/recordings/ses_YnDaGYNcd7/MyRecording.mp4",
//     "status": "ready"

//     })

//   }
// }


</script>


<template>
  <div>
    <h1>Logic2</h1>
    <h2>방송 대쉬보드 화면</h2>
    <div id="streamerscreen"></div>
    <button>방송 시작</button>
    <button>녹화 시작</button>
  </div>
</template>

<style scoped>

  button{
    font-size: 30px;
    color: red;
    margin : 30px;
  }

</style>