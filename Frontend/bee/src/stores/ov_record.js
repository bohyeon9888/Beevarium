import { ref } from "vue";
import { defineStore } from "pinia";

export const useRecordStore = defineStore(
  "RecordStore",
  () => {
    // 녹화 시작
    const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;
    const recordingId = ref("");

    const startRecording = async () => {
      try {
        const recordingResp = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/recordings/MyRecording`
        );
        console.log(recordingResp.data);
        console.log(recordingResp.data.id);
        recordingId.value = ref(recordingResp.data.id);
        console.log("녹화 시작");
      } catch (error) {
        console.log("녹화 시작 실패", error);
      }
    };

    //녹화 중지
    const stopRecording = async (recordingId) => {
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
      try {
        recordingObj = await axios.get(
          `${API_SERVER_URL}openvidu/api/recordings/${recordingId}`
        );
        console.log(recordingObj.data.url);
      } catch (error) {
        console.error(error, "녹화 객체 반환 실패");
      }
    };

    return { startRecording, stopRecording, retrieveRecord, recordingId };
  },
  {
    persist: true,
  }
);
