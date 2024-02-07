<script setup>
import { ref } from "vue";
import DonationModal from "./DonationModal.vue";

const isModalOpened = ref(false);
const openModal = () => {
  isModalOpened.value = true;
};
const closeModal = () => {
  isModalOpened.value = false;
};
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
    <div class="livestream-chat-box"></div>
    <div
      id="livestream-chat-input-container"
      class="livestream-chat-input-container"
      :class="{ modalOpen: isModalOpened }"
    >
      <div class="livestream-chat-input-box">
        <input
          class="livestream-chat-input"
          type="text"
          placeholder="채팅을 입력해주세요"
        />
        <img
          src="../../../assets/img/stream/donation.png"
          alt=""
          class="donation-button"
          @click="openModal"
        />
      </div>
      <div class="livestream-chat-button">채팅</div>
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
  width: 370px;
  height: 691px;
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
