<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="signup-container">
    <router-link class="logo-box" :to="{ name: 'Home' }">
      <img class="signup-form-logo" src="../../assets/img/logo.png" />
    </router-link>
    <div class="signup-box">
      <input type="text" class="id-input" placeholder="아이디" />
      <input type="password" class="password-input" placeholder="비밀번호" />
      <input
        type="password"
        class="password-check"
        placeholder="비밀번호 확인"
      />
      <input type="text" class="nickname-input" placeholder="닉네임" />
      <div class="email-input-box">
        <input type="text" class="email-id-input" placeholder="이메일 앞자리" />
        <div style="margin: auto; font-size: 20px">@</div>
        <input
          type="text"
          class="email-domain-input"
          placeholder="이메일 뒷자리"
        />
      </div>
    </div>
    <router-link :to="{ name: 'Login' }">
      <div class="signup-button">회원가입</div>
    </router-link>
  </div>
</template>

<style scoped>
.signup-container {
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
.signup-form-logo {
  width: 40%;
}
.signup-box {
  display: flex;
  flex-direction: column;
  margin-top: 30px;
}
.id-input,
.password-input,
.password-check,
.nickname-input {
  width: 100%;
  height: 50px;
  outline: none;
  border: 1px solid #3455;
  padding-left: 15px;
  margin: 10px 0;
  font-size: 17px;
  border-radius: 5px;
}
.email-input-box {
  display: flex;
  flex-direction: row;
}
.email-id-input,
.email-domain-input {
  width: 45%;
  height: 50px;
  outline: none;
  border: 1px solid #3455;
  padding-left: 15px;
  margin: 10px 0;
  font-size: 17px;
  border-radius: 5px;
}
.signup-button {
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
</style>
