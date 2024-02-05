<script setup>
import { RouterLink } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import router from "@/router";

const authStore = useAuthStore();

const login = () => {
  authStore.login();
  router.push({ name: "Home" });
};

const changeCoper = (value) => {
  authStore.chooseCoperation(value);
  console.log(value);
};
</script>

<template>
  <div class="container">
    <div class="login-container">
      <div class="login-box">
        <div class="logo-box">
          <router-link :to="{ name: 'Home' }">
            <img class="logo-image" src="../../assets/img/logo.png" alt="" />
          </router-link>
        </div>
        <div class="id-input-box">
          <input class="id-input" type="text" placeholder="아이디" />
        </div>
        <div class="password-input-box">
          <input
            class="password-input"
            type="password"
            placeholder="비밀번호"
          />
        </div>
        <div class="login-button" @click="login">로그인</div>
        <div class="user-function-box">
          <router-link :to="{ name: 'Signup' }">회원가입</router-link>
          <div
            style="
              width: 1px;
              height: 14px;
              margin: 0 10px;
              background: #636363;
            "
          ></div>
          <router-link to="">아이디 찾기</router-link>
          <div
            style="
              width: 1px;
              height: 14px;
              margin: 0 10px;
              background: #636363;
            "
          ></div>
          <router-link to="">비밀번호 찾기</router-link>
        </div>
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 60px;
          "
        >
          <div style="width: 110px; height: 1px; background: #636363"></div>
          <div
            style="
              text-align: right;
              color: white;
              font-size: 16px;
              line-height: 20px;
            "
          >
            SNS 계정으로 로그인하기
          </div>
          <div style="width: 110px; height: 1px; background: #636363"></div>
        </div>
        <div class="social-login-box">
          <a
            class="social-login-button"
            href="http://localhost:8080/oauth/redirect/google"
          >
            <img
              class="google-login"
              src="../../assets/img/social-login/google.png"
              @click="changeCoper('google')"
            />
          </a>
          <a
            class="social-login-button"
            href="http://localhost:8080/oauth/redirect/naver"
            style="margin: 0 24px"
          >
            <img
              class="naver-login"
              src="../../assets/img/social-login/naver.png"
              @click="changeCoper('naver')"
            />
          </a>
          <a
            class="social-login-button"
            href="http://localhost:8080/oauth/redirect/kakao"
          >
            <img
              class="kakao-login"
              src="../../assets/img/social-login/kakao.png"
              @click="changeCoper('kakao')"
            />
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
}
.login-container {
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
.login-box {
  width: 400px;
  height: 590px;
}
.logo-box {
  display: flex;
  justify-content: center;
  width: 100%;
  height: 135px;
}
.id-input-box {
  width: 100%;
  height: 48px;
  border-radius: 12px;
  background-color: #3a3a3b;
  margin-top: 48px;
}
.id-input {
  width: 100%;
  height: 100%;
  border: 0;
  outline: none;
  background-color: transparent;
  color: white;
  font-size: 16px;
  padding-left: 20px;
}
.password-input-box {
  width: 100%;
  height: 48px;
  border-radius: 12px;
  background-color: #3a3a3b;
  margin-top: 12px;
}
.password-input {
  width: 100%;
  height: 100%;
  border: 0;
  outline: none;
  background-color: transparent;
  color: white;
  font-size: 16px;
  padding-left: 20px;
}
.login-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 56px;
  background-color: #ffec3e;
  margin-top: 32px;
  border-radius: 12px;
  font-weight: 600;
  font-size: 16px;
  cursor: pointer;
  color: black;
}
.user-function-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 19px;
  margin-top: 24px;
  font-size: 16px;
  color: white;
}
.social-login-box {
  display: flex;
  justify-content: center;
  margin-top: 24px;
}
.social-login-button {
  width: 64px;
  height: 64px;
  border-radius: 10rem;
  cursor: pointer;
}
.google-login,
.naver-login,
.kakao-login {
  width: 64px;
  height: 64px;
  border-radius: 10rem;
  cursor: pointer;
}
</style>
