<script setup>
const props = defineProps({
    chatModalActive : Boolean
})
const emit = defineEmits(['closechat'])
const close = () => {
  emit('closechat')
}
</script>

<template>
  <div class="user-chat-modal" v-show="chatModalActive">
    <div class="user-chat-modal-inner" >
      <h1>채팅 내역</h1> 
      <div class="profile-img">
        <img src="@/assets/img/streamer_image_1.png" alt="">
      </div>
      <div class ="chat-history">

      </div>
      <button @click="close">닫기</button> 
    </div>
  </div>
</template>

<style scoped>
.user-chat-modal{
  display: flex;
  flex-direction: column;
  justify-content:center;
  text-align: center;
  height: 500px;
  width: 300px;
  position: fixed;
  left:300px;
  background-color: blue;
  border: 2px solid red;
}

.user-chat-modal-inner{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.chat-history{
  background-color: lightgreen;
  width: 240px;
  height: 300px;
  margin: 5%;
  /* 스크롤 가능 */
  overflow-y: auto;
}

img{
  width: 50px;
}

</style>
