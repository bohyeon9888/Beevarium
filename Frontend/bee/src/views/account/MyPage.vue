<script setup>
import { ref } from "vue";
import { useRouter, RouterView } from "vue-router";
import { useEffectStore } from "@/stores/mypage";
import { storeToRefs } from "pinia";

const effectStore = useEffectStore();
const { activeOption } = storeToRefs(effectStore);

const router = useRouter();
const moveToProfile = () => {
  router.push({ name: "Profile" });
};
const moveToPrivacy = () => {
  router.push({ name: "Privacy" });
};

const handleOptionClick = (index) => {
  activeOption.value = index;
};
</script>

<template>
  <div class="account-container">
    <div class="mypage-head">마이페이지</div>
    <ul class="profile-privacy-toggle-container">
      <li
        class="profile-toggle-button"
        @click="[handleOptionClick(1), moveToProfile()]"
        :class="{ active: activeOption === 1 }"
      >
        프로필
      </li>
      <li
        class="privacy-toggle-button"
        @click="[handleOptionClick(0), moveToPrivacy()]"
        :class="{ active: activeOption === 0 }"
      >
        개인정보
      </li>
    </ul>
    <div style="width: 1620px; height: 1px; border-radius: 8px; background-color: #323232;"></div>
    <RouterView />
  </div>
</template>

<style scoped>
.account-container {
  width: 1620px;
  min-height: 859px;
}
.mypage-head {
  width: 104px;
  height: 29px;
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 40px;
}
.profile-privacy-toggle-container {
  display: flex;
  margin-bottom: 10px;
}
.profile-toggle-button {
  width: 42px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
  cursor: pointer;
  margin-right: 35px;
}
.profile-toggle-button:after {
  display: block;
  content: "";
  height: 10px;
  border-bottom: 1px solid #ffec3e;
  transform: scaleX(0);
  transition: 0.2s;
}
.profile-toggle-button.active:after {
  transform: scaleX(1);
  transition: 0.2s;
}
.privacy-toggle-button {
  width: 56px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
  cursor: pointer;
}
.privacy-toggle-button:after {
  display: block;
  content: "";
  height: 10px;
  border-bottom: 1px solid #ffec3e;
  transform: scaleX(0);
  transition: 0.2s;
}
.privacy-toggle-button.active:after {
  transform: scaleX(1);
  transition: 0.2s;
}
</style>
