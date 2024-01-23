<script setup>
import UserChatModal from './UserChatModal.vue'
import { ref } from 'vue'

const props = defineProps({
  infoModalActive: Boolean
});
const emit = defineEmits(['close','openchat'])
const close = () => {
  emit('close')
}

const chatModalActive = ref(false)

const toggleChat = () => {
  chatModalActive.value = !chatModalActive.value
  console.log(chatModalActive.value)
}

</script>

<template>
  <UserChatModal :chatModalActive="chatModalActive" @closechat="toggleChat">
  </UserChatModal>
  <div class="user-info-modal" v-show="infoModalActive">
    <div class="user-info-modal-inner"> <!--이  div 핑요한가?-->
      <h1>유저관리</h1>
      <div class="user-info">
        <div>
          <img src="@/assets/img/streamer_image_1.png" alt="">
        </div>
        <div>
          <p>유저 닉네임</p>
          <p>유저 이메일</p>
          <p>구독일</p>
        </div>
      </div>
      <div class="user-management">
        <button @click="toggleChat">채팅내역</button>
        <button>임시차단</button>
        <button id= "permanently-bann">영구차단</button>
      </div>
      <button @click="close">닫기</button>
    </div>
  </div>
</template>

<style scoped>
.user-info-modal{
  display: flex;
  flex-direction: column;
  justify-content:center;
  text-align: center;
  height: 500px;
  width: 300px;
  position: fixed;
  background-color: blue;
}
.user-info{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin: 10%;
  background-color: lightgreen;
}

.user-management{
  display: flex;
  flex-direction: column;
  margin: 10%;
  background-color: lightgreen;
}

#permanently-bann{
  margin-top: 40px;
  background-color: red;
  color: white

}
button{
  margin: 10px; 
  height: 40px;
  font-size: 20px;
}

img{
  width: 80px;
}

</style>
