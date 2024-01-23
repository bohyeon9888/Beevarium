<script setup>
import DashboardChat from "./components/DashboardChat.vue";
import Screen from "./components/Screen.vue";
import UserChatModal from "./components/UserChatModal.vue";
import UserInfoModal from "./components/UserInfoModal.vue";
import Navbar from "../common/components/Navbar.vue";
import { ref } from "vue";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const authStore = useAuthStore();
const { isLoggedIn } = storeToRefs(authStore);
const router = useRouter();

const infoModalActive = ref(false);
const toggleInfo = () => {
  infoModalActive.value = !infoModalActive.value;
};

const streamerId = ref("김싸피");
const initialAlarm = ref(streamerId.value + "님이 생방송을 시작하였습니다.");
</script>

<template>
  <div class="dashboard-container">
    <div class="streaminfo-setting-container">
      <div
        style="
          display: flex;
          align-items: center;
          width: 100%;
          height: 60px;
          font-size: 22px;
          padding-left: 20px;
          border-bottom: 2px solid #3455;
        "
      >
        방송정보
      </div>
      <div class="stream-title">
        <div>방송 제목</div>
        <textarea class="title-input" placeholder="방송 제목 입력"></textarea>
      </div>
      <div class="stream-alarm">
        <div>생방송 알림 멘트</div>
        <textarea class="alarm-input" v-model="initialAlarm"></textarea>
      </div>
      <div class="stream-tag">
        <div>방송 태그</div>
      </div>
      <div class="stream-update">
        <div class="update-button">방송 업데이트</div>
      </div>
    </div>
    <div class="stream-screen-setting">
      <div class="stream-screen">
        <Screen />
      </div>
      <div class="stream-setting"></div>
    </div>
    <div class="newsfeed-container">
      <div
        style="
          display: flex;
          align-items: center;
          width: 100%;
          height: 60px;
          font-size: 22px;
          padding-left: 20px;
          border-bottom: 2px solid #3455;
        "
      >
        뉴스 피드
      </div>
      <h2>뉴스 피드</h2>
    </div>
    <div class="chat-container">
      <div
        style="
          display: flex;
          align-items: center;
          width: 100%;
          height: 60px;
          font-size: 22px;
          padding-left: 20px;
          border-bottom: 2px solid #3455;
        "
      >
        방송 채팅
      </div>
      <DashboardChat class="chat" />
    </div>
    <!-- <UserInfoModal :infoModalActive="infoModalActive" @close="toggleInfo">
    </UserInfoModal>
    <button @click="toggleInfo">유저 정보 모달</button>
    <div></div> -->
  </div>
</template>

<style scoped>
.dashboard-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100vh;
}
.streaminfo-setting-container {
  width: 18%;
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
  margin: 20px 0 0;
  resize: none;
  font-size: 17px;
  background-color: #434343;
}
.stream-alarm {
  margin: 20px 20px 0;
  font-size: 18px;
}
.alarm-input {
  width: 100%;
  height: 150px;
  outline: none;
  border: 0;
  border-radius: 8px;
  padding: 5px;
  margin: 20px 0 0;
  resize: none;
  font-size: 17px;
  background-color: #434343;
}
.stream-tag {
  margin: 20px 20px 0;
  font-size: 18px;
  max-height: 200px;
  min-height: 125px;
}
.stream-update {
  margin: 20px;
  cursor: pointer;
}
.update-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 60px;
  border-radius: 8px;
  font-size: 20px;
  background-color: #ffe3bc;
  color: black;
}
.stream-screen-setting {
  display: flex;
  width: 44%;
  flex-direction: column;
}
.stream-screen {
  width: 100%;
  height: 70%;
  background-color: #000000;
}
.stream-setting {
  width: 100%;
  height: 30%;
  background-color: #1e1e1f;
  border-top: 2px solid #3455;
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
</style>
