<script setup>
import { ref, onMounted, watch } from "vue";

const props = defineProps({
  chatModalActive: Boolean,
  username: String,
  chatLog: Array,
});
const emit = defineEmits(["closechat"]);
const close = () => {
  emit("closechat");
};

const formatTimestamp = (timestamp) => {
  const date = new Date(timestamp);
  return date.toLocaleString("en-US", {
    year: "2-digit",
    month: "2-digit",
    day: "2-digit",
    hour: "numeric",
    minute: "numeric",
    hour12: true,
  });
};

onMounted(() => {
  fetchChatHistory().then((data) => {
    chatHistory.value = data;
  });
});

// 가상의 채팅 데이터를 불러오는 비동기 함수
const fetchChatHistory = async () => {
  return [
    {
      id: 1,
      sender: "user",
      text: "안녕하세요!",
      timestamp: "2024-01-31T12:00:00",
    },
    {
      id: 2,
      sender: "other",
      text: "안녕하세요! 반갑습니다. 안녕하세요! 반갑습니다. 안녕하세요! 반갑습니다.",
      timestamp: "2024-01-31T12:05:00",
    },
    {
      id: 3,
      sender: "user",
      text: "오늘 날씨 어때요?",
      timestamp: "2024-01-31T12:10:00",
    },
  ];
};
</script>

<template>
  <div class="user-chat-modal" v-show="chatModalActive">
    <img
      src="../../../assets/img/common/x.png"
      alt="x"
      class="close-modal"
      @click="close"
    />
    <div class="user-chat-modal-inner">
      <div class="modal-header">
        <img
          src="../../../assets/img/stream/modal-logo.png"
          alt="modal-logo"
          class="modal-logo"
        />
        채팅 내역
      </div>
      <div class="modal-body">
        <img
          src="../../../assets/img/stream/profile.png"
          alt="profile"
          class="profile-img"
        />
        <p class="user-nickname">{{ username }}</p>
        <div class="chat-history">
          <div
            v-for="(message, index) in props.chatLog"
            :key="index"
            class="chat-message"
          >
            <div class="message-timestamp">
              {{ formatTimestamp(message.time) }}
            </div>
            <div class="user-message">
              {{ message.message }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.user-chat-modal {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  height: 496px;
  width: 400px;
  position: fixed;
  right: 320px;
  border-radius: 12px;
  border: 1px solid #323232;
  background: #1d1d1d;
}
.close-modal {
  width: 24px;
  height: 24px;
  margin-left: auto;
  margin-right: 19px;
  margin-top: 16px;
}
.close-modal:hover {
  cursor: pointer;
}
.user-chat-modal-inner {
  display: flex;
  flex-direction: column;
  padding: 0 40px;
  height: 100%;
}
.modal-header {
  color: #fff;
  font-size: 20px;
  font-weight: 700;
  line-height: 24px;
  margin-bottom: 24px;
  display: flex;
  justify-content: start;
  height: 24px;
}
.modal-logo {
  margin-right: 8px;
}
.profile-img {
  width: 60px;
  height: 60px;
  margin-bottom: 8px;
}
.modal-body {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.user-nickname {
  text-align: center;
  font-size: 16px;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 30px;
}
.chat-history {
  width: 352px;
  height: 240px;
  max-height: 240px;
  overflow-y: auto;
  display: flex;
  border-radius: 12px;
  background: #323232;
  padding: 24px 20px;
  margin-bottom: 32px;
  flex-direction: column;
  align-items: start;
}
.chat-message {
  display: flex;
  width: 100%;
  font-size: 14px;
  text-align: start;
  margin-bottom: 10px;
}
.user-message {
  display: flex;
  width: 159px;
  margin-left: 15px;
}
.message-timestamp {
  width: 130px;
}
</style>
