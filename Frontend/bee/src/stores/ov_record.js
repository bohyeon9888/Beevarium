import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useRecordStore = defineStore(
  "RecordStore",
  () => {
    // 녹화 시작
    const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;
    const recordingId = ref("");
    const recordUrl = ref("");

    const startRecording = async (sessionId) => {
      try {
        const recordingResp = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/recordings/MyRecording`
        );
        console.log(recordingResp.data);
        console.log(recordingResp.data.id);
        recordingId.value = recordingResp.data.id;
        console.log("녹화 시작");
      } catch (error) {
        console.log("녹화 시작 실패", error);
      }
    };

    //녹화 중지
    const stopRecording = async (recordingId) => {
      console.log("stop recording js 로그", recordingId);
      try {
          await axios.delete(
          `${API_SERVER_URL}openvidu/api/recordings/${recordingId}`
        );
        console.log("녹화 중지 성공");
      } catch (error) {
        console.log(error, "녹화 중지 실패");
      }
    };

    //녹화 객체 반환
    const retrieveRecord = async (recordingId) => {
      const checkStatus = async () => {
        try {
          const recordingObj = await axios.get(
            `${API_SERVER_URL}openvidu/api/recordings/${recordingId}`
          );
          console.log(recordingObj.data)
          if (recordingObj.data.status === "ready") {
            recordUrl.value = recordingObj.data.url
          }
          else {
            console.log("녹화 파일 준비 중...상태:", recordingObj.data.status)
            setTimeout(checkStatus, 5000)
          }
        }
        catch (error) {
          console.error("녹화 객체 반환 실패")
        }
      }
      checkStatus()
    };


    const sendRecord = async (accessToken, url, success, fail) => {
      await axios
        .post(
          `${API_SERVER_URL}videos/upload/replay`,
          { filePath: url },
          {
            headers: {
              Authorization: accessToken,
            },
          }
        )
        .then(success)
        .catch(fail);
    };

    return {
      startRecording,
      stopRecording,
      retrieveRecord,
      sendRecord,
      recordingId,
      recordUrl,
    };
  },
  {
    persist: true,
  }
);
