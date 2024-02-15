<script setup>
import { useOVSStore } from "@/stores/ov_subscriber";
import { ref, onMounted, onUpdated, watchEffect } from "vue";
import { storeToRefs } from "pinia";

const ovsStore = useOVSStore();
const chatBoxRef = ref(null);
const messages = ref([]);

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

onUpdated(() => {
  if (chatBoxRef.value) {
    chatBoxRef.value.scrollTop = chatBoxRef.value.scrollHeight;
  }
});

watchEffect(() => {
  const newMessages = ovsStore.ai_subtitle; // 이 부분은 ovsStore가 맞는지 확인하세요, 원래는 ovpStore일 수 있습니다.
  if (typeof newMessages === "string") {
    console.log("string");
    try {
      console.log("1", newMessages);
      const messageObject = JSON.parse(newMessages); // 여기서 messageObject는 이미 객체입니다.
      console.log("2", messageObject);
      messages.value.push(messageObject.text);
      console.log(messageObject.text);
    } catch (error) {
      console.error("Parsing error:", error);
    }
  }
});

onMounted(() => {
  messages.value = [];
  ovsStore.closeSession();
});
</script>

<template>
  <div class="ai-script-container">
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
      AI 스크립트
    </div>
    <div class="ai-script-box">
      <div
        v-for="(message, index) in messages"
        :key="index"
        class="chat-message"
      >
        <p class="chat-content">{{ formatTimestamp(message.requestTime) }}</p>
        <p style="margin-top: 2px; font-size: 18px; font-weight: 600;">{{ message.answer }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.ai-script-container {
  width: 370px;
  height: 862px;
  border-right: 1px solid #202020;
  border-left: 1px solid #202020;
}
.ai-script-box {
  overflow-y: auto;
  padding: 10px;
  flex-grow: 1;
  width: 370px;
  height: 796px;
}
.ai-script-box::-webkit-scrollbar {
  width: 16px;
  display: none;
}
.ai-script-box:hover::-webkit-scrollbar {
  display: block;
}
.ai-script-box::-webkit-scrollbar-track {
  background-color: #1e1e1e;
}
.ai-script-box::-webkit-scrollbar-thumb {
  border: 4px solid #1e1e1e;
}
.chat-message {
  width: 320px;
  min-height: 72px;
  padding: 16px;
  border-top: 0.5px solid #3455;
  border-bottom: 0.5px solid #3455;
}
</style>
