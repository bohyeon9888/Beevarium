<script setup>
import { ref, computed, watch, reactive } from "vue";
import { RouterLink } from "vue-router";
import { signUp } from "@/api/user";

const moveLogin = () => {
  router.push({ name: "Login" });
};

// 회원가입 입력 정보
const inputData = ref({
  userId: "",
  userPassword: "",
  userEmailId: "",
  userEmailDomain: "",
  userName: "",
});

// 회원가입 axios request 정보
const registerData = computed(() => ({
  userId: inputData.value.userId,
  password: inputData.value.userPassword,
  email: inputData.value.userEmailId + "@" + inputData.value.userEmailDomain,
  username: inputData.value.userName,
}));

// 비밀번호 확인
const passwordCheck = ref("");
const isPasswordSame = computed(() => {
  if (
    inputData.value.userPassword != "" &&
    passwordCheck.value != "" &&
    inputData.value.userPassword === passwordCheck.value
  ) {
    return 1;
  }
  if (
    inputData.value.userPassword != "" &&
    passwordCheck.value != "" &&
    inputData.value.userPassword != passwordCheck.value
  ) {
    return 2;
  }
});
watch(
  () => inputData.value.userPassword,
  (newValue, oldValue) => {
    inputData.value.userPassword = newValue;
  }
);
watch(
  () => passwordCheck.value,
  (newValue, oldValue) => {
    passwordCheck.value = newValue;
  }
);

// 회원가입
const doSignUp = () => {
  console.dir(registerData.value);
  signUp(
    registerData.value,
    ({ data }) => {
      if (data.status == "success") moveLogin();
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="container">
    <div class="signup-container">
      <div class="signup-box">
        <div class="signup-logo-box">
          <img
            src="../../assets/img/signup-logo.png"
            alt=""
            class="signup-logo-image"
          />
        </div>
        <div class="id-input-container">
          <div style="font-size: 14px; font-weight: 600">아이디</div>
          <input type="text" class="id-input" v-model="inputData.userId" />
        </div>
        <div class="name-input-container">
          <div style="font-size: 14px; font-weight: 600">닉네임</div>
          <input type="text" class="name-input" v-model="inputData.userName" />
        </div>
        <div class="password-input-container">
          <div style="font-size: 14px; font-weight: 600">비밀번호</div>
          <input
            type="password"
            class="password-input"
            v-model="inputData.userPassword"
          />
        </div>
        <div class="password-check-input-container">
          <div style="font-size: 14px; font-weight: 600">비밀번호 확인</div>
          <input
            type="password"
            class="password-check-input"
            v-model="passwordCheck"
          />
        </div>
        <div
          v-if="isPasswordSame === 2"
          style="
            width: 400px;
            height: 20px;
            font-size: 14px;
            font-weight: 600;
            color: #ffec3e;
          "
        >
          비밀번호가 일치하지 않습니다.
        </div>
        <div
          v-if="isPasswordSame === 1"
          style="
            width: 400px;
            height: 20px;
            font-size: 14px;
            font-weight: 600;
            color: #91ff6d;
          "
        >
          비밀번호가 일치합니다.
        </div>
        <div class="email-input-container">
          <div style="font-size: 14px; font-weight: 600">이메일</div>
          <div class="email-input-box">
            <input
              type="text"
              class="email-id-input"
              v-model="inputData.userEmailId"
            />
            <div
              style="
                width: 13px;
                height: 17px;
                font-size: 14px;
                font-weight: 600;
              "
            >
              @
            </div>
            <input
              type="text"
              class="email-domain-input"
              v-model="inputData.userEmailDomain"
            />
          </div>
        </div>
        <div class="signup-button" @click="doSignUp">회원가입</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
}
.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 680px;
  height: 842px;
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 32px;
  background-color: #1e1e1e;
}
.signup-box {
  width: 400px;
  min-height: 536px;
}
.signup-logo-box {
  display: flex;
  justify-content: center;
  width: 400px;
  height: 60px;
  margin-bottom: 40px;
}
.id-input-container {
  width: 400px;
  height: 73px;
  margin-bottom: 16px;
}
.id-input {
  width: 400px;
  height: 48px;
  outline: none;
  font-size: 14px;
  font-weight: 400;
  padding-left: 20px;
  border: 0;
  border-radius: 12px;
  background-color: #3a3a3b;
  margin-top: 8px;
}
.name-input-container {
  width: 400px;
  height: 73px;
  margin-bottom: 16px;
}
.name-input {
  width: 400px;
  height: 48px;
  outline: none;
  font-size: 14px;
  font-weight: 400;
  padding-left: 20px;
  border: 0;
  border-radius: 12px;
  background-color: #3a3a3b;
  margin-top: 8px;
}
.password-input-container {
  width: 400px;
  height: 73px;
  margin-bottom: 16px;
}
.password-input {
  width: 400px;
  height: 48px;
  outline: none;
  font-size: 14px;
  font-weight: 400;
  padding-left: 20px;
  border: 0;
  border-radius: 12px;
  background-color: #3a3a3b;
  margin-top: 8px;
}
.password-check-input-container {
  width: 400px;
  height: 73px;
  margin-bottom: 16px;
}
.password-check-input {
  width: 400px;
  height: 48px;
  outline: none;
  font-size: 14px;
  font-weight: 400;
  padding-left: 20px;
  border: 0;
  border-radius: 12px;
  background-color: #3a3a3b;
  margin-top: 8px;
}
.email-input-container {
  width: 400px;
  height: 73px;
  margin: 16px 0;
}
.email-input-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 400px;
  height: 48px;
  margin-top: 8px;
}
.email-id-input,
.email-domain-input {
  width: 188px;
  height: 48px;
  font-size: 14px;
  font-weight: 400;
  outline: none;
  padding-left: 20px;
  border: 0;
  border-radius: 12px;
  background-color: #3a3a3b;
}
.signup-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 56px;
  font-size: 16px;
  font-weight: 700;
  color: #121212;
  border-radius: 12px;
  background-color: #ffec3e;
  cursor: pointer;
  margin-top: 40px;
}
</style>
