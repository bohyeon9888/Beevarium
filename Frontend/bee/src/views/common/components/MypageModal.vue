<script setup>
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const authStore = useAuthStore();
const { user } = storeToRefs(authStore);

const prop = defineProps(["mypageModActive"]);

const emit = defineEmits(["logOut", "toggleMpMod"]);
const router = useRouter();

const toggleMpMod = () => {
  emit("toggleMpMod");
};
const moveToStudioMain = () => {
  router.push({ path: `/studio/studio-main/${user.value.id}` });
};
const moveToMypage = () => {
  router.push({ name: "MyPage" });
};
const logout = () => {
  emit("toggleMpMod");
  emit("logOut");
  console.log("로그아웃 호출");
};
</script>

<template>
  <div class="mypage-modal" v-show="mypageModActive">
    <ul class="mypage-modal-inner">
      <li @click="[moveToStudioMain(), toggleMpMod()]">방송국</li>
      <li @click="[moveToMypage(), toggleMpMod()]">마이페이지</li>
      <li @click="[logout()]">로그아웃</li>
    </ul>
  </div>
</template>

<style scoped>
.mypage-modal {
  position: fixed;
  display: flex;
  flex-direction: column;
  width: 120px;
  height: 150px;
  background-color: #434343;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
}

li {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  width: 120px;
  cursor: pointer;
}
.mypage-modal-inner li:hover {
  font-size: 16px;
  font-weight: 600;
  background-color: #ffcf40;
  color: #121212;
}
</style>
