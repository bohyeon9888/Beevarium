<script setup>
import UserChatModal from "./UserChatModal.vue";
import { ref } from "vue";

const props = defineProps({
  infoModalActive: Boolean,
  username: String,
});
const emit = defineEmits(["close", "openchat"]);
const close = () => {
  emit("close");
};

const username = props.username;
const chatModalActive = ref(false);

const toggleChat = () => {
  chatModalActive.value = !chatModalActive.value;
};
</script>

<template>
  <div class="user-info-modal" v-show="infoModalActive">
    <img
      src="../../../assets/img/common/x.png"
      alt="x"
      class="close-modal"
      @click="close"
    />
    <div class="user-info-modal-inner">
      <div class="modal-header">
        <img
          src="../../../assets/img/stream/modal-logo.png"
          alt="modal-logo"
          class="modal-logo"
        />
        유저 관리
      </div>
      <div class="user-info">
        <img
          src="../../../assets/img/stream/profile.png"
          alt="profile"
          class="profile-img"
        />
        <div class="user-info-text">
          <p class="user-nickname">{{ props.username }}</p>
          <p class="user-email">Iamjaenom@gmail.com</p>
          <p class="user-follow">구독 시작일 : 2024. 01. 10</p>
        </div>
      </div>
      <div class="user-management"></div>
      <button class="modal-btn" @click="toggleChat">채팅 내역</button>
      <button class="modal-btn">임시 차단</button>
      <button class="modal-btn" id="permanently-bann">영구 차단</button>
    </div>
  </div>
  <UserChatModal
    :chatModalActive="chatModalActive"
    :username="username"
    @closechat="toggleChat"
  />
</template>

<style scoped>
.user-info-modal {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  height: 496px;
  width: 400px;
  position: fixed;
  border-radius: 12px;
  border: 1px solid #323232;
  background: #1d1d1d;
}
.modal-header {
  color: #fff;
  font-size: 20px;
  font-weight: 700;
  line-height: 24px;
  margin-bottom: 24px;
}
.user-info {
  display: flex;
  width: 320px;
  height: 120px;
  border-radius: 12px;
  background: #323232;
  padding: 24px 20px;
  margin-bottom: 32px;
}

#permanently-bann {
  margin-top: 40px;
  background-color: red;
  color: white;
}
.modal-btn {
  width: 320px;
  height: 48px;
  font-size: 20px;
  margin-bottom: 16px;
  border-radius: 8px;
  background-color: #ffec3e;
  color: #121212;
  border: none;
  font-size: 16px;
  font-weight: 700;
  line-height: normal;
}
.modal-btn:hover {
  cursor: pointer;
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
.user-info-modal-inner {
  display: flex;
  flex-direction: column;
  padding: 0 40px;
  height: 100%;
}
.modal-header {
  display: flex;
  justify-content: start;
  height: 24px;
  font-size: 20px;
  font-weight: 700;
  line-height: 24px;
}
.modal-logo {
  margin-right: 8px;
}
.profile-img {
  width: 80px;
  height: 80px;
}
.user-info-text {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 16px;
  text-align: start;
}
.user-nickname {
  font-size: 16px;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 6px;
}
.user-email {
  color: #d3d3d3;
  font-size: 14px;
  font-weight: 400;
  line-height: normal;
  margin-bottom: 6px;
}
.user-follow {
  color: #faf585;
  font-size: 12px;
  font-weight: 400;
  line-height: normal;
  margin-bottom: 6px;
}
</style>
