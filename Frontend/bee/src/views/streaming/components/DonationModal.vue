<script setup>
import { ref, watch } from "vue";
// Props 및 Emits 정의
const props = defineProps({
  donModActive: Boolean,
});
const emit = defineEmits(["close"]);
const donationAmount = ref(1000);
const myHoney = ref(100000);
const donationMessage = ref("");
const isHoneyLack = ref(false);

const close = () => {
  donationAmount.value = 0;
  emit("close");
};

const addAmount = (amount) => {
  donationAmount.value += amount;
};

const honeyAlert = ref("");

watch(
  () => donationAmount.value,
  (newValue, oldValue) => {
    if (newValue <= myHoney.value) {
      isHoneyLack.value = false;
    } else {
      honeyAlert.value = "허니가 부족합니다"
      isHoneyLack.value = true;
    }
    if (newValue < 1000) {
      donationAmount.value = 1000;
    }
  }
);
</script>
<template>
  <div class="donation-modal-container">
    <img
      src="../../../assets/img/common/x.png"
      alt=""
      class="close-button"
      @click="close"
    />
    <div class="donation-modal-box">
      <div
        style="
          width: 290px;
          height: 57px;
          padding: 16px 0 20px;
          font-size: 18px;
          font-weight: 600;
        "
      >
        후원하기
      </div>
      <div
        style="
          display: flex;
          justify-content: space-between;
          align-items: center;
          width: 290px;
          height: 33px;
          display: flex;
          align-items: center;
        "
      >
        <div
          style="width: 74px; height: 19px; font-size: 16px; font-weight: 600"
        >
          후원할 허니
        </div>
        <div class="my-honey">
          <div
            style="
              width: 35px;
              height: 14px;
              font-size: 12px;
              font-weight: 600;
              margin-right: 4px;
            "
          >
            내 허니
          </div>
          <img
            src="../../../assets/img/donation/my-honey.png"
            alt=""
            class="my-honey-image"
          />
          <div
            style="
              height: 17px;
              font-size: 14px;
              font-weight: 700;
              color: #ffcf40;
              margin-right: 6px;
            "
          >
            {{ myHoney }}
          </div>
          <img
            src="../../../assets/img/donation/reset.png"
            alt=""
            class="reset-button"
          />
        </div>
      </div>
      <div class="honey-input-box">
        <img
          src="../../../assets/img/donation/honey.png"
          alt=""
          class="honey-image"
        />
        <input id="honey-input" type="number" class="honey-input" :class="{ honeyLack: isHoneyLack }" v-model="donationAmount" />
      </div>
      <div class="honey-add-box">
        <div class="honey-add-button" @click="addAmount(1000)">1,000</div>
        <div class="honey-add-button" @click="addAmount(5000)">5,000</div>
        <div class="honey-add-button" @click="addAmount(10000)">10,000</div>
        <div class="honey-add-button" @click="addAmount(50000)">50,000</div>
      </div>
      <div id="honey-lack-alert" class="honey-lack-alert" :class="{ honeyLack: !isHoneyLack}">{{ honeyAlert }}</div>
      <div class="donation-message-box">
        <textarea
          class="donation-message"
          placeholder="후원 메세지를 입력해주세요"
          v-model="donationMessage"
        ></textarea>
      </div>
      <div class="donation-button" @click="close">후원하기</div>
    </div>
  </div>

  <!-- <div v-show="donModActive" class="donation-modal">
    <div class="donation-modal-inner"> -->
  <!--모달 창 내용-->
  <!-- <h1>후원하기</h1> -->
  <!-- 현재 소지중 포인트 바인딩 필요 -->
  <!-- <p>현재 소지중 포인트 :</p> -->
  <!-- <label for="donation-amount">후원 금액</label> -->
  <!-- <input type="number" name="donation-amount" v-model="donationAmount" /> -->
  <!-- {{ donationAmount }} -->
  <!-- <button @click="addAmount(1000)">1000</button> -->
  <!-- <button @click="addAmount(5000)">5000</button> -->
  <!-- <button @click="addAmount(10000)">10,000</button> -->
  <!-- <button @click="addAmount(50000)">50,000</button> -->
  <!-- <label for="donation-message"></label> -->
  <!-- 메세지 변수 바인딩 필요 -->
  <!-- <input
        type="text"
        name="donation-message"
        placeholder="메세지를 입력해주세요."
      />
      <button @click="close">후원하기</button>
      <button @click="close">Close</button>
    </div>
  </div> -->
</template>

<style scoped>
.donation-modal-container {
  position: relative;
  display: flex;
  justify-content: center;
  width: 322px;
  height: 460px;
  border-top-right-radius: 16px;
  border-top-left-radius: 16px;
  background-color: #1d1d1d;
}
.close-button {
  position: absolute;
  top: 16px;
  right: 16px;
  width: 18px;
  height: 18px;
  cursor: pointer;
}
.donation-modal-box {
  width: 290px;
}
.my-honey {
  display: flex;
  align-items: center;
  height: 33px;
  padding: 8px 12px;
  border: 1px solid white;
  border-radius: 10rem;
}
.my-honey-image {
  width: 12px;
  height: 12px;
  margin-right: 4px;
}
.reset-button {
  cursor: pointer;
}
.honey-input-box {
  display: flex;
  align-items: center;
  width: 290px;
  height: 40px;
  padding: 10px 12px;
  background-color: #323232;
  border-radius: 8px;
  margin: 16px 0;
}
.honey-input {
  width: 242px;
  height: 26px;
  background-color: transparent;
  outline: none;
  border: 0;
  font-size: 16px;
  font-weight: 700;
  margin-left: 8px;
}
#honey-input.honeyLack {
  color:#eb3a3a;
}
.honey-add-box {
  display: flex;
  justify-content: space-between;
  width: 278px;
  height: 25px;
  margin: 0 6px 8px;
}
.honey-add-button {
  display: flex;
  align-items: center;
  height: 25px;
  padding: 4px 10px;
  border: 1px solid white;
  border-radius: 10rem;
  font-size: 14px;
  font-weight: 700;
  cursor: pointer;
}
.honey-lack-alert {
  text-align: center;
  width: 290px;
  height: 14px;
  font-size: 12px;
  font-weight: 600;
  color: #eb3a3a;
}
#honey-lack-alert.honeyLack {
  visibility: hidden;
}
.donation-message-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 290px;
  height: 120px;
  background-color: #323232;
  border-radius: 8px;
  margin: 24px 0;
}
.donation-message {
  width: 266px;
  height: 96px;
  outline: none;
  border: 0;
  resize: none;
  font-size: 16px;
  font-weight: 400;
  background-color: transparent;
}
.donation-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 290px;
  height: 48px;
  background-color: #ffec3e;
  border-radius: 8px;
  color: #121212;
  font-size: 16px;
  font-weight: 700;
  cursor: pointer;
}
</style>
