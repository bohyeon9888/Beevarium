<script setup>
import { RouterLink } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const authStore = useAuthStore();
const { isLoggedIn } = storeToRefs(authStore);

const login = () => {
  authStore.login();
};
</script>

<template>
  <div class="login-container">
    <router-link class="logo-box" :to="{ name: 'Home' }">
      <img class="login-form-logo" src="../../assets/img/logo.png" />
    </router-link>
    <div class="login-box">
      <input class="id-input" type="text" placeholder="아이디 입력" />
      <input
        class="password-input"
        type="password"
        placeholder="비밀번호 입력"
      />
    </div>
    <div style="display: flex; justify-content: flex-end; margin: 10px 0 20px; font-size: 17px;">
      <router-link :to="{ name: 'Signup' }">회원가입</router-link>
      <div style="margin: 0 10px">|</div>
      <div>비밀번호 찾기</div>
    </div>
    <router-link :to="{ name: 'Home' }" @click="login" >
      <div class="login-button">로그인</div>
    </router-link>
    <div style="display: flex; justify-content: center; margin: 40px 0 0; font-size: 17px;">
      SNS 계정 간편 로그인
    </div>
    <div class="social-login">
      <a
        class="move-google-login"
        @click.prevent="
          {
            changeOper('google');
          }
        "
      >
        <img
          class="google-login-button"
          src="../../assets/img/social-login/google.png"
        />
      </a>
      <a
        class="move-kakao-login"
        href="http://codakcodak.site:8000/wannago/backapi/oauth/redirect/kakao"
      >
        <img
          class="kakao-login-button"
          @click="changeOper('kakao')"
          src="../../assets/img/social-login/kakao.png"
        />
      </a>
      <a
        class="move-naver-login"
        href="http://codakcodak.site:8000/wannago/backapi/oauth/redirect/naver"
      >
        <img
          class="naver-login-button"
          @click="changeOper('naver')"
          src="../../assets/img/social-login/naver.png"
        />
      </a>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 500px;
  padding: 40px;
  transform: translate(-50%, -50%);
}
.logo-box {
  display: flex;
  justify-content: center;
  width: 100%;
}
.login-form-logo {
  width: 40%;
}
.login-box {
  display: flex;
  flex-direction: column;
  margin-top: 30px;
}
.id-input,
.password-input {
  width: 100%;
  height: 50px;
  outline: none;
  border: 1px solid #3455;
  padding-left: 15px;
  margin: 10px 0;
  font-size: 17px;
  border-radius: 5px;
}
.login-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 50px;
  margin: 10px 0;
  background-color: #ffe3bc;
  border-radius: 10px;
  font-size: 18px;
}
.social-login {
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: yellow; */
  width: 100%;
  height: 55px;
  margin-top: 20px;
}

.google-login-button,
.kakao-login-button {
  width: 50px;
  height: 50px;
}
.naver-login-button {
  width: 55px;
  height: 55px;
}
.google-login-button,
.kakao-login-button,
.naver-login-button {
  margin: 0 20px;
  cursor: pointer;
}
</style>
