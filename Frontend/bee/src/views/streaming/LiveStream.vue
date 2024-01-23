<script setup>
import LiveStreamChat from "./components/LiveStreamChat.vue";
import Screen from "./components/Screen.vue";
import { ref } from "vue";
import LanguageSelectorModal from "./components/LanguageSelectorModal.vue";
import DonationModal from "@/views/streaming/components/DonationModal.vue";

// 도네이션 모달 활성화
const donModActive = ref(false);
// 언어 선택 모달 활성화
const lanModActive = ref(false);
// 도네이션 모달 토글
const toggleDonMod = () => {
  donModActive.value = !donModActive.value;
};
// 언어 선택 모달 토글
const toggleLanMod = () => {
  lanModActive.value = !lanModActive.value;
};
</script>

<template>
  <div class="livestream-container">
    <div class="livestream-box">
      <div class="screen-container">
        <Screen />
      </div>
      <div class="livestream-info-container">
        <div class="livestream-info-box">
          <div class="stream-title">방송 제목</div>
          <div style="display: flex; flex-direction: row">
            <div class="streamer-logo-box">
              <img
                class="streamer-logo"
                src="../../assets/img/streamer_image_1.png"
                alt=""
              />
            </div>
            <div
              style="
                display: flex;
                flex-direction: column;
                width: calc(100% - 70px);
              "
            >
              <div class="streamer-name">스트리머 닉네임</div>
              <div class="livestream-tag">태그</div>
            </div>
          </div>
        </div>
        <div class="livestream-setting">
          <div class="follow-alarm-box">
            <div class="follow-button">팔로우</div>
            <div class="alarm-button">알림</div>
          </div>
          <div class="watcher-count-box">xxx명 시청중</div>
          <div class="translate-filter-box">자막 필터링 설정</div>
        </div>
      </div>
    </div>
    <div class="chat-container">
      <LiveStreamChat class="chat" />
    </div>
    <!-- <DonationModal :donModActive="donModActive" @close="toggleDonMod">
    </DonationModal>
    <button @click="toggleDonMod">후원 모달 팝업</button>
    <LanguageSelectorModal :lanModActive="lanModActive">
    </LanguageSelectorModal>
    <button @click="toggleLanMod">언어 선택</button> -->
  </div>
</template>

<style scoped>
.livestream-container {
  display: flex;
  width: 100%;
  height: calc(100vh - 80px);
}
.livestream-box {
  display: flex;
  flex-direction: column;
  width: 100%;
  border-right: 2px solid #3455;
}
.screen-container {
  width: 100%;
  height: 550px;
  background-color: #000000;
}
.livestream-info-container {
  display: flex;
  width: 100%;
  height: calc(100% - 550px);
  border-top: 2px solid #3455;
}
.livestream-info-box {
  width: calc(100% - 280px);
  height: 100%;
}
.stream-title {
  width: 100%;
  height: 36px;
  padding-left: 10px;
  font-size: 26px;
  line-height: 36px;
}
.streamer-logo-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 70px;
  height: 70px;
}
.streamer-logo {
  width: 60px;
  height: 60px;
}
.streamer-name {
  display: flex;
  align-items: center;
  height: 50%;
  font-size: 17px;
}
.livestream-tag {
  display: flex;
  align-items: center;
  height: 50%;
  font-size: 17px;
}
.livestream-setting {
  display: flex;
  flex-direction: column;
  width: 280px;
}
.follow-alarm-box {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 35%;
  font-size: 17px;
}
.follow-button,
.alarm-button {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #efefef;
  margin-right: 15px;
  height: 30px;
  border-radius: 15px;
}
.follow-button {
  width: 60px;
}
.alarm-button {
  width: 45px;
}
.watcher-count-box {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 30%;
  padding-right: 16px;
  font-size: 17px;
}
.translate-filter-box {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 35%;
  padding-right: 16px;
  font-size: 17px;
}
.chat-container {
  width: 295px;
  color: #000000;
}
.chat {
  width: 295px;
  height: 100%;
}
</style>
