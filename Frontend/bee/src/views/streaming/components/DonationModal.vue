<script setup>

import { ref } from 'vue';
// Props 및 Emits 정의
const props = defineProps({
  donModActive: Boolean
});
const emit = defineEmits(['close']);
const donationAmount = ref(0);

const close = () => {
  donationAmount.value = 0;
  emit("close");
};

const addAmount = (amount) => {
  donationAmount.value += amount;
};
</script>
<template>
  <div v-show="donModActive" class="donation-modal">
    <div class="donation-modal-inner">
      <!--모달 창 내용-->
      <h1>후원하기</h1>
      <!-- 현재 소지중 포인트 바인딩 필요 -->
      <p>현재 소지중 포인트 :</p>
      <label for="donation-amount">후원 금액</label>
      <input type="number" name="donation-amount" v-model="donationAmount" />
      <!-- {{ donationAmount }} -->
      <button @click="addAmount(1000)">1000</button>
      <button @click="addAmount(5000)">5000</button>
      <button @click="addAmount(10000)">10,000</button>
      <button @click="addAmount(50000)">50,000</button>
      <label for="donation-message"></label>
      <!-- 메세지 변수 바인딩 필요 -->
      <input
        type="text"
        name="donation-message"
        placeholder="메세지를 입력해주세요."
      />
      <button @click="close">후원하기</button>
      <button @click="close">Close</button>
    </div>
  </div>
</template>


<style scoped>
.donation-modal {
  display: flex;
  justify-content: center;
  height: 400px;
  width: 300px;
  position: fixed;
  background-color: blue;
}
.donation-modal-inner {
  background-color: lightcoral;
  display: flex;
  flex-direction: column;
  text-align: center;
}

button {
  margin: 10px;
}
</style>
