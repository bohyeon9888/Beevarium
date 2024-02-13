<script setup>
import { ref, computed, onMounted } from "vue";
import { useAuthStore } from "@/stores/user";
import { useOVPStore } from "@/stores/ov_publisher";
import { storeToRefs } from "pinia";
import DashboardChat from "./components/DashboardChat.vue";
import { streamingStart, streamingEnd } from "@/api/live.js";
import { useRecordStore } from "@/stores/ov_record";

const recordStore = useRecordStore();
const ovpStore = useOVPStore();
const authStore = useAuthStore();
const { isLoggedIn, accessToken } = storeToRefs(authStore);
const streamerId = ref("김싸피");
const initialAlarm = ref(streamerId.value + "님이 생방송을 시작하였습니다.");
const tagInput = ref("");
const tagList = ref(new Set());
const audioInputDevices = ref([]);
const selectedMicrophoneId = ref("");
const receivedMessages = ref([]);
const sessionId = ref("CUSTOM_SESSION_ID3");

function handleMessages(newMessages) {
  console.log("Received messages from child component:", newMessages);
  receivedMessages.value = newMessages;
}

const loadAudioInputDevices = async () => {
  const devices = await navigator.mediaDevices.enumerateDevices();
  audioInputDevices.value = devices.filter(
    (device) => device.kind === "audioinput"
  );
};

const onTag = function (event, input) {
  if (input && input.trim() !== "") {
    tagList.value.add(input);
  }
  tagInput.value = "";
};

const deleteTag = function (input) {
  tagList.value.delete(input);
};

const newsFeed = ref([]);
const onAir = ref(false);

const addNewsFeedItem = (user, action) => {
  const item = {
    id: Date.now(),
    user: user,
    action: action,
    time: new Date().toLocaleString(),
  };
  newsFeed.value.unshift(item);
};

const streamData = ref({
  broadcastingTitle: "",
  tagList: tagList.value,
});
const doStreamingStart = () => {
  console.dir(streamData.value);
  onAir.value = true;
  streamingStart(
    accessToken.value,
    streamData.value,
    ({ data }) => {
      console.log(data.status);
    },
    (error) => {
      console.log(error.data.msg);
    }
  );
};
const doStreamingEnd = () => {
  onAir.value = false;
  streamingEnd(
    accessToken.value,
    { chatting: receivedMessages.value },
    async ({ data }) => {
      console.log(data.msg);
    },
    (error) => {
      console.log(error.data.msg);
    }
  );
};
const streamingButtonText = computed(() => {
  return onAir.value ? "On Air" : "방송 시작";
});

onMounted(() => {
  loadAudioInputDevices();
});

const openSessionWithSelectedMic = async () => {
  if (selectedMicrophoneId.value) {
    // connectSession 함수에 선택된 마이크 ID를 전달합니다.
    await ovpStore.connectSession(selectedMicrophoneId.value);
  }
};

const recordUrl = () => {
  console.log(recordStore.recordUrl);
  recordStore.sendRecord(
    accessToken.value,
    recordStore.recordUrl,
    ({ data }) => {
      console.log(data);
      console.log(data.msg);
    },
    (error) => {
      console.log(error);
    }
  );
};

const startStreaming = async () => {
  await doStreamingStart();
  await ovpStore.openSession();
};

const endStreaming = async () => {
  await doStreamingEnd();
  await recordStore.stopRecording(recordingId.value);
  await ovpStore.closeSession();
  await recordStore.retrieveRecord(recordingId.value);
  await recordUrl();
};

const recordingId = ref("");

const startRecording = async () => {
  await recordStore.startRecording(sessionId.value);
  recordingId.value = recordStore.recordingId;
  console.log("컴포넌트에서", recordingId);
};

// 테스트
addNewsFeedItem("아재개더", "3,000");
addNewsFeedItem("컬쳐쇼크러블리", "팔로우");
addNewsFeedItem("컬쳐쇼크러블리", "팔로우");
addNewsFeedItem("아재개더", "3,000");
</script>

<template>
  <div class="dashboard-container">
    <div class="dashboard-body">
      <div class="streaminfo-setting-container">
        <div class="dashboard-header">방송 정보</div>
        <div class="stream-title">
          <div>방송 제목</div>
          <textarea
            class="title-input"
            placeholder="방송 제목을 입력해주세요."
            v-model="streamData.broadcastingTitle"
          ></textarea>
        </div>
        <div class="stream-alarm">
          <div>생방송 알림 멘트</div>
          <textarea
            class="alarm-input"
            placeholder="생방송 알림멘트를 입력해주세요."
            v-model="initialAlarm"
          ></textarea>
        </div>
        <div class="stream-tag">
          <div>방송 태그</div>
          <div class="tag-box">
            <input
              class="tag-input"
              type="text"
              v-model="tagInput"
              @keyup.enter="onTag($event, tagInput)"
              placeholder="방송 태그를 입력해주세요."
            />
            <button class="tag-button" @click="onTag($event, tagInput)">
              입력
            </button>
          </div>
          <ul class="tag-list">
            <li class="tag" v-for="(tag, index) in tagList" :key="index">
              {{ tag }}
              <p class="delete-tag" @click="deleteTag(tag)">x</p>
            </li>
          </ul>
        </div>
        <div class="stream-update">
          <button class="update-button">방송 업데이트</button>
        </div>
      </div>
      <div class="stream-screen-setting">
        <div class="dashboard-header">방송 미리보기</div>
        <div class="stream-screen" id="my-video">
          <!-- <Screen /> -->
        </div>

        <div class="streaming-option">
          <select class="mic-select" v-model="selectedMicrophoneId">
            <option
              v-for="device in audioInputDevices"
              :key="device.deviceId"
              :value="device.deviceId"
            >
              {{ device.label }}
            </option>
          </select>
          <button class="mic-button" @click="openSessionWithSelectedMic">
            마이크 선택 및 세션 시작
          </button>
          <button
            class="streaming-start-btn"
            @click="startStreaming()"
            :disabled="onAir"
          >
            {{ streamingButtonText }}
          </button>
          <button
            class="streaming-end-btn"
            @click="endStreaming()"
            :disabled="!onAir"
          >
            방송 종료
          </button>
          <button class="streaming-start-btn" @click="startRecording()">
            녹화 시작
          </button>
        </div>
        <div class="stream-setting"></div>
      </div>
      <div class="newsfeed-container">
        <div class="dashboard-header">뉴스 피드</div>
        <div class="newsfeed-box">
          <div class="newsfeed-item" v-for="item in newsFeed" :key="item.id">
            <span class="user-name">{{ item.user }}</span> 님이
            <span v-if="item.action == '팔로우'" class="follow-action">
              {{ item.action }}
              <span>하셨습니다.</span>
            </span>
            <span v-if="item.action != '팔로우'" class="donation-action">
              {{ item.action }}
              <span>원을 후원하셨습니다.</span>
            </span>
            <div class="newsfeed-time">{{ item.time }}</div>
          </div>
        </div>
      </div>
      <div class="chat-container">
        <div class="dashboard-header">방송 채팅</div>
        <DashboardChat class="chat" @sendMessages="handleMessages" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.dashboard-container {
  display: flex;
  width: 1920px;
  height: 862px;
  flex-direction: column;
}
button:hover {
  cursor: pointer;
}
.streaminfo-setting-container {
  width: 320px;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #1e1e1f;
  border-right: 2px solid #3455;
  color: white;
}
.stream-title {
  margin: 20px 20px 0;
  font-size: 18px;
}
.title-input {
  width: 100%;
  height: 150px;
  outline: none;
  border: 0;
  border-radius: 8px;
  padding: 5px;
  margin: 8px 0 0;
  resize: none;
  font-size: 16px;
  background-color: #434343;
  padding: 12px;
}
.stream-alarm {
  margin: 32px 20px 0;
  font-size: 18px;
}
.alarm-input {
  width: 100%;
  height: 150px;
  outline: none;
  border: 0;
  border-radius: 8px;
  padding: 12px;
  margin: 8px 0 0;
  resize: none;
  font-size: 17px;
  background-color: #434343;
}
.stream-tag {
  margin: 32px 20px;
  font-size: 18px;
  max-height: 200px;
  min-height: 125px;
}
.stream-update {
  margin-top: auto;
  padding: 15px;
  cursor: pointer;
}
.update-button {
  display: flex;
  width: 290px;
  height: 48px;
  padding: 8px 16px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  flex-shrink: 0;
  border-radius: 8px;
  background-color: #ffec3e;
  color: #121212;
  font-size: 16px;
  font-weight: 700;
  line-height: normal;
}
.stream-screen-setting {
  width: 890px;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #1e1e1f;
  color: white;
}
.stream-screen {
  display: flex;
  justify-content: center;
  width: 890px;
  height: 493px;
  background-color: #000000;
  margin-bottom: 24px;
}
#my-video video {
  max-width: 890px !important;
  max-height: 493px !important;
  object-fit: contain;
}
.stream-setting {
  width: 100%;
  height: 30%;
  background-color: #1e1e1f;
  color: #ffffff;
}
.newsfeed-container {
  width: 19%;
  height: 100%;
  background-color: #1e1e1f;
  border-left: 2px solid #3455;
  border-right: 2px solid #3455;
  color: #ffffff;
}
.chat-container {
  width: 19%;
  height: 100%;
  background-color: #1e1e1f;
  color: #ffffff;
}
.chat {
  width: 100%;
  height: calc(100% - 60px);
}
.dashboard-body {
  display: flex;
  height: 873px;
}
.dashboard-header {
  display: flex;
  align-items: center;
  width: 100%;
  height: 50px;
  font-size: 18px;
  padding-left: 20px;
  border-bottom: 2px solid #3455;
}
.tag-box {
  display: flex;
}
.tag-input {
  width: 220px;
  height: 40px;
  outline: none;
  border: 0;
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  padding: 12px;
  margin: 8px 0 16px;
  resize: none;
  font-size: 17px;
  background-color: #434343;
}
.tag-button {
  width: 66px;
  height: 40px;
  outline: none;
  border: 0;
  border-radius: 0px 8px 8px 0px;
  background-color: #404040;
  margin: 8px 0 0;
  border-left: 2px solid #676767;
  font-size: 16px;
}
.tag-list {
  display: flex;
  flex-wrap: wrap;
}
.tag {
  display: flex;
  align-items: center;
  height: 24px;
  font-size: 14px;
  font-weight: 500;
  background-color: #434343;
  border-radius: 10rem;
  padding: 3px 8px;
  margin-right: 6px;
  margin-bottom: 6px;
}
.delete-tag {
  line-height: 20px;
  height: 20px;
  margin-left: 4px;
  font-size: 12px;
  font-weight: 500;
}
.streaming-start-btn {
  width: 91px;
  height: 35px;
  display: inline-flex;
  padding: 8px 16px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 8px;
  background: #eb3a3a;
  border: none;
  font-size: 15px;
  line-height: normal;
  margin-right: 15px;
}
.streaming-end-btn {
  width: 91px;
  height: 35px;
  display: inline-flex;
  padding: 8px 16px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 8px;
  background: #434343;
  border: none;
  font-size: 15px;
  line-height: normal;
  margin-right: 15px;
}
.streaming-end-btn[disabled] {
  background: #434343;
  color: #777777;
  cursor: not-allowed;
}

.streaming-end-btn[disabled]:hover {
  background: #434343;
  color: #777777;
}
.streaming-option {
  display: flex;
  justify-content: end;
}
.newsfeed-box {
  padding: 16px;
  height: 300px;
  overflow-y: auto;
}
.newsfeed-item {
  border-bottom: 1px solid #2a2a2a;
  padding-bottom: 8px;
  margin-bottom: 8px;
}

.newsfeed-content {
  font-size: 14px;
  margin: 8px 0;
}

.newsfeed-time {
  font-size: 12px;
  color: #777777;
}
.user-name {
  color: #e0e0e0;
  font-weight: bold;
}

.follow-action {
  color: #ffcf40;
}
.donation-action {
  color: #3effe8;
}
.streaming-start-btn[disabled] {
  background: #eb3a3a;
  color: #777777;
  cursor: not-allowed;
}

.streaming-start-btn[disabled]:hover {
  background: #434343;
  color: #777777;
}
.mic-select {
  display: inline-flex;
  padding: 8px 16px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 8px;
  background: #434343;
  font-size: 15px;
  line-height: normal;
  margin-right: 15px;
}
.mic-button {
  display: inline-flex;
  padding: 8px 16px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 8px;
  background: #434343;
  border: none;
  font-size: 15px;
  line-height: normal;
  margin-right: 15px;
}
</style>
