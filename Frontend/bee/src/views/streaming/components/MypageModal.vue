<script setup>
import { useRouter } from 'vue-router'

const props = defineProps({
  mypageModActive : Boolean
})

const emit = defineEmits(['callLogout','hidemodal'])

const hideModal = () => 
{
  emit('hidemodal')
}
const router = useRouter()

const callLogout = () => {
  emit('callLogout')
  console.log("로그아웃 호출")
}


</script>

<template>
  <div class="mypage-modal" v-show="mypageModActive">
    <ul class="mypage-modal-inner">
      <li>
        <router-link :to="{name : 'Dashboard'}">방송하기</router-link>
      </li>
      <li>
        <router-link :to="{name: 'StudioMain'}" @click="hideModal">나의 채널</router-link>
      </li>
      <li>
        <router-link :to="{name : 'Account'}" @click="hideModal"> 마이페이지</router-link>
      </li>
      <li @click="callLogout" class="clickable">로그아웃</li>
    </ul>
  </div>
</template>

<style scoped>
 .mypage-modal{
  position: fixed;
  display: flex;
  flex-direction: column;
  width: 150px;
  height: 150px;
  background-color: #ffe3bc;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
 }

 li{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  width: 150px;
}
 .mypage-modal-inner li:hover{
  background-color: rgb(252, 144, 22);
  color: white;
 }
.clickable{
  cursor: pointer;

}
</style>