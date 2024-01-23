<script></script>

<template>
  <div class="chat-container">
    <div class="chat-box">채팅이 표시될 곳</div>
    <div class="chat-input-box">
      <input type="text" class="chat-input" placeholder="채팅을 입력해주세요."/>
      <div class="message-send-button-box">
        <div class="message-send-button">채팅</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  background-color: #1e1e1f;
}
.chat-box {
  width: 100%;
  height: 80%;
  border-bottom: 2px solid #3455;
  padding: 15px;
}
.chat-input-box {
  display: flex;
  flex-direction: column;
  width: 90%;
  height: 15%;
  margin: 15px;
}
.chat-input {
  width: 100%;
  height: 50px;
  outline: none;
  border: 0;
  border-radius: 8px;
  background-color: #434343;
  font-size: 16px;
  padding-left: 10px;
}
.message-send-button-box {
  display: flex;
  justify-content: flex-end;
}
.message-send-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 52px;
  height: 39px;
  background-color: #434343;
  border-radius: 8px;
  margin-top: 12px;
  cursor: pointer;
}
</style>
