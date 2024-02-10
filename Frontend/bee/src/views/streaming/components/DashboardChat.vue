<script setup>
import UserInfoModal from "./UserInfoModal.vue";
import { ref, onUpdated, onMounted, reactive, watchEffect } from "vue";
import { useOVPStore } from "@/stores/ov_publisher";
import { useAuthStore } from "@/stores/user";

const ovpStore = useOVPStore();
const authStore = useAuthStore();
const infoModalActive = ref(false);
const toggleInfo = () => {
  infoModalActive.value = !infoModalActive.value;
};
const chatBoxRef = ref(null);
const messages = ref([
  {
    id: 0,
    name: "MightyEamessage",
    message: "와, 정말 놀라운 플레이였어요!",
    type: "normal",
    time: "2024-01-31 05:50:21",
  },
  {
    id: 1,
    name: "BlueRabbit",
    message: "좋아요! 계속 이야기해주세요!",
    type: "greeting",
    time: "2024-01-31 05:28:53",
  },
  {
    id: 2,
    name: "QuietCat",
    message: "정말 재미있는 방송이네요!",
    type: "shoutout",
    time: "2024-01-31 05:04:29",
  },
  {
    id: 3,
    name: "RedFox",
    message: "지금 무슨 게임을 하고 계신가요?",
    type: "greeting",
    time: "2024-01-31 05:00:29",
  },
  {
    id: 4,
    name: "RedFox",
    message: "저는 이 부분에 대해 다른 생각이 있어요.",
    type: "normal",
    time: "2024-01-31 05:39:01",
  },
  {
    id: 5,
    name: "RedFox",
    message: "여기 누구 있나요?",
    type: "question",
    time: "2024-01-31 05:00:09",
  },
  {
    id: 6,
    name: "CrazyPanda",
    message: "지금 무슨 게임을 하고 계신가요?",
    type: "greeting",
    time: "2024-01-31 05:15:41",
  },
  {
    id: 7,
    name: "BlueRabbit",
    message: "오늘 방송 언제 시작해요?",
    type: "greeting",
    time: "2024-01-31 05:12:18",
  },
  {
    id: 8,
    name: "CrazyPanda",
    message: "좋아요! 계속 이야기해주세요!",
    type: "question",
    time: "2024-01-31 04:59:04",
  },
  {
    id: 9,
    name: "BlueRabbit",
    message: "좋아요! 계속 이야기해주세요!",
    type: "greeting",
    time: "2024-01-31 05:16:52",
  },
]);
const newMessage = ref("");
onUpdated(() => {
  if (chatBoxRef.value) {
    chatBoxRef.value.scrollTop = chatBoxRef.value.scrollHeight;
  }
});
const selectedUsername = ref("");
function hashCode(str) {
  let hash = 0;
  for (let i = 0; i < str.length; i++) {
    hash = str.charCodeAt(i) + ((hash << 5) - hash);
  }
  return hash;
}
function generateColorForUsername(username) {
  const hash = hashCode(username);
  const hue = Math.abs(hash) % 360;
  const lightness = 50;
  const saturation = 70;
  return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
}
const selectUser = (username) => {
  selectedUsername.value = username;
  toggleInfo();
};
const sendMessage = () => {
  const trimmedMessage = newMessage.value.trim();
  if (trimmedMessage) {
    ovpStore.sendMessage1(newMessage.value); // 서버 또는 다른 클라이언트에 메시지 전송
    newMessage.value = ""; // 입력 필드 초기화
  }
};

const usernameColors = reactive({});

watchEffect(() => {
  messages.value.forEach((message) => {
    const username = message.name;
    if (!usernameColors[username]) {
      usernameColors[username] = generateColorForUsername(username);
    }
  });
});
watchEffect(() => {
  const newMessages = ovpStore.messagee;
  if (typeof newMessages === "string") {
    try {
      const messageObject = JSON.parse(newMessages);
      // 메시지 객체가 올바른 속성을 가지고 있는지 확인
      messages.value.push(messageObject);
    } catch (error) {
      console.error("Parsing error:", error);
    }
  } else {
    // 객체가 올바른 속성을 가지고 있는지 확인
    messages.value.push(newMessages);
  }
});

onMounted(() => {
  ovpStore.closeSession();
});
</script>

<template>
  <div class="chat-container">
    <div class="chat-box">
      <div class="chat-messages" ref="chatBoxRef">
        <div v-for="message in messages" :key="message.id" class="chat-message">
          <img
            src="../../../assets/img/stream/fan.png"
            alt="fan"
            style="margin-right: 4px"
          />

          <span
            v-if="message.name"
            :style="{ color: usernameColors[message.name] }"
            class="chat-username"
            @click="selectUser(message.name)"
            >{{ message.name }}</span
          >
          <span v-if="message.message" class="chat-content">
            {{ message.message }}</span
          >
        </div>
      </div>
      <div class="chat-input-container">
        <div class="chat-input-box">
          <input
            v-model="newMessage"
            type="text"
            class="chat-input"
            @keyup.enter="sendMessage"
            placeholder="채팅을 입력해주세요."
          />
        </div>
        <div class="chat-button-box">
          <button class="chat-send" @click="sendMessage()">전송</button>
        </div>
      </div>
    </div>
  </div>
  <div v-if="infoModalActive" class="modal-overlay">
    <UserInfoModal
      :infoModalActive="infoModalActive"
      :username="selectedUsername"
      @close="toggleInfo"
    />
  </div>
</template>

<style scoped>
.chat-messages {
  overflow-y: auto;
  padding: 10px;
  flex-grow: 1;
  margin: 0 16px;
  height: 690px;
  max-height: 700px;
  /* -ms-overflow-style: none; */
  /* scrollbar-width: none; */
}
.chat-messages::-webkit-scrollbar {
  width: 16px;
  display: none;
}
.chat-messages:hover::-webkit-scrollbar {
  display: block;
}
.chat-messages::-webkit-scrollbar-track {
  background-color: #1e1e1e;
}
.chat-messages::-webkit-scrollbar-thumb {
  border: 4px solid #1e1e1e;
}
.chat-message {
  width: 292px;
  display: flex;
  align-items: flex-start;
  margin-bottom: 8px;
}

.chat-username {
  font-weight: bold;
  margin-right: 8px;
}
.chat-username:hover {
  cursor: pointer;
}

.chat-content {
  margin-left: 5px;
}

.chat-input-container {
  display: flex;
  flex-wrap: wrap;
}
.chat-input-box {
  width: 100%;
  display: flex;
  justify-content: center;
}

.chat-input {
  display: flex;
  width: 322px;
  height: 40px;
  padding: 10px 12px;
  align-items: center;
  gap: 146px;
  border-radius: 8px;
  background-color: #434343;
  border: none;
  font-size: 16px;
}

button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  background-color: #555;
  color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #666;
}

.chat-button-box {
  margin-top: 12px;
  margin-left: auto;
  margin-right: 18px;
}

.chat-send {
  width: 57px;
  height: 33px;
  border-radius: 8px;
  display: inline-flex;
  padding: 8px 16px;
  align-items: center;
  color: #8a8b8f;

  font-size: 14px;
  font-weight: 600;
  line-height: normal;
}
.chat-box {
  display: flex;
  height: 98%;
  flex-direction: column;
  justify-content: space-between;
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.2);
}
</style>
