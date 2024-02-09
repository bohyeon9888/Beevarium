<script setup>
import DonationModal from "./DonationModal.vue";
import { useOVSStore } from "@/stores/ov_subscriber";
import { ref, onMounted, onUpdated, reactive, watchEffect } from "vue";
import { useOVPStore } from "@/stores/ov_publisher";
import { useAuthStore } from "@/stores/user";

const ovsStore = useOVSStore();
const ovpStore = useOVPStore();
const authStore = useAuthStore();
const chatBoxRef = ref(null);

const isModalOpened = ref(false);
const messages = ref([]);
const newMessage = ref("");
onUpdated(() => {
  if (chatBoxRef.value) {
    chatBoxRef.value.scrollTop = chatBoxRef.value.scrollHeight;
  }
});
function hashCode(str) {
  let hash = 0;
  for (let i = 0; i < str.length; i++) {
    hash = str.charCodeAt(i) + ((hash << 5) - hash);
  }
  return hash;
}
function generateColorForUsername(username) {
  const hash = hashCode(username);
  const hue = Math.abs(hash) % 360; // 음수 방지를 위해 Math.abs를 사용합니다.
  const lightness = 50; // 채도와 명도를 고정하고 휘도만 변화시킵니다.
  const saturation = 70;
  return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
}

const openModal = () => {
  isModalOpened.value = true;
};

const closeModal = () => {
  isModalOpened.value = false;
};

const sendMessage = () => {
  const trimmedMessage = newMessage.value.trim();
  if (trimmedMessage) {
    const messageObject = {
      id: Date.now(),
      username: authStore.user.name,
      content: trimmedMessage,
      type: "normal",
    };
    // messages.value.push(messageObject);
    ovsStore.sendMessage1(newMessage.value);
    newMessage.value = "";
  }
};
const usernameColors = reactive({});

onMounted(() => {
  ovsStore.closeSession();
});

watchEffect(() => {
  messages.value.forEach((message) => {
    const username = message.name;
    if (!usernameColors[username]) {
      usernameColors[username] = generateColorForUsername(username);
    }
  });
});

watchEffect(() => {
  const newMessages = ovsStore.messagee;
  if (typeof newMessages === "string") {
    try {
      const messageObject = JSON.parse(newMessages);
      messages.value.push(messageObject);
    } catch (error) {
      console.error("Parsing error:", error);
    }
  } else {
    messages.value.push(newMessages);
  }
});
</script>

<template>
  <div class="livestream-chat-container">
    <div
      style="
        display: flex;
        justify-content: center;
        align-items: center;
        height: 50px;
        font-size: 18px;
        font-weight: 600;
        border-top: 1px solid #202020;
        border-bottom: 1px solid #202020;
      "
    >
      채팅
    </div>
    <div class="livestream-chat-box">
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
    <div
      id="livestream-chat-input-container"
      class="livestream-chat-input-container"
      :class="{ modalOpen: isModalOpened }"
    >
      <div class="livestream-chat-input-box">
        <input
          class="livestream-chat-input"
          v-model="newMessage"
          type="text"
          @keyup.enter="sendMessage"
          placeholder="채팅을 입력해주세요."
        />
        <img
          src="../../../assets/img/stream/donation.png"
          alt=""
          class="donation-button"
          @click="openModal"
        />
      </div>
      <div class="livestream-chat-button">
        <button class="chat-send" @click="sendMessage()">전송</button>
      </div>
    </div>
    <div
      id="donation-modal"
      class="donation-modal"
      :class="{ modalOpen: isModalOpened }"
    >
      <DonationModal @close="closeModal" />
    </div>
  </div>
</template>

<style scoped>
.livestream-chat-container {
  position: relative;
  width: 370px;
  height: 862px;
}
.livestream-chat-box {
  overflow-y: auto;
  padding: 10px;
  flex-grow: 1;
  width: 370px;
  height: 691px;
  max-height: 691px;
}
.livestream-chat-box::-webkit-scrollbar {
  width: 16px;
  display: none;
}
.livestream-chat-box:hover::-webkit-scrollbar {
  display: block;
}
.livestream-chat-box::-webkit-scrollbar-track {
  background-color: #1e1e1e;
}
.livestream-chat-box::-webkit-scrollbar-thumb {
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
.livestream-chat-input-container {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 370px;
  height: 121px;
}
#livestream-chat-input-container.modalOpen {
  visibility: hidden;
}
.livestream-chat-input-box {
  display: flex;
  align-items: center;
  width: 338px;
  height: 40px;
  background-color: #323232;
  border-radius: 8px;
  padding: 8px 12px;
  margin: auto;
  margin-top: 16px;
}
.livestream-chat-input {
  width: 314px;
  height: 20px;
  font-size: 16px;
  font-weight: 400;
  background-color: transparent;
  border: 0;
  outline: none;
}
.donation-button {
  width: 18px;
  height: 18px;
  cursor: pointer;
}
.livestream-chat-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 57px;
  height: 32px;
  font-size: 14px;
  font-weight: 600;
  background-color: #323232;
  border-radius: 8px;
  color: #8a8b8f;
  cursor: pointer;
  margin-right: 16px;
  margin-bottom: 16px;
}
.donation-modal {
  position: absolute;
  right: 24px;
  transform: translateY(121px);
  z-index: 2;
  transition: 0.3s;
}
#donation-modal.modalOpen {
  transform: translateY(-460px);
  z-index: 2;
  transition: 0.3s;
}
</style>
