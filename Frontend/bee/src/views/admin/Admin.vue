<script setup>
import { ref } from 'vue';
import { useRouter, RouterView } from "vue-router";

const router = useRouter();
const moveToMonitoring = () => {
  router.push({ name: "Monitoring" });
};
const moveToWarningUser = () => {
  router.push({ name: "WarningUser" });
};
const moveToBlackList = () => {
  router.push({ name: "BlackList" });
};

const activeOption = ref(1);
const handleOptionClick = (index) => {
  activeOption.value = index;
};
</script>

<template>
  <div class="admin-container">
    <div class="admin-head">관리자 페이지</div>
    <ul class="admin-toggle-container">
      <li
        class="monitoring-toggle-button"
        @click="[handleOptionClick(1), moveToMonitoring()]"
        :class="{ active: activeOption === 1 }"
      >
        실시간 모니터링
      </li>
      <li
        class="warninguser-toggle-button"
        @click="[handleOptionClick(0), moveToWarningUser()]"
        :class="{ active: activeOption === 0 }"
      >
        경고 유저 관리
      </li>
      <li
        class="blacklist-toggle-button"
        @click="[handleOptionClick(2), moveToBlackList()]"
        :class="{ active: activeOption === 2 }"
      >
        영구정지 유저 관리
      </li>
    </ul>
    <div
      style="
        width: 1620px;
        height: 1px;
        border-radius: 8px;
        background-color: #323232;
      "
    ></div>
    <RouterView />
  </div>
</template>

<style scoped>
.admin-container {
  width: 1620px;
  min-height: 859px;
}
.admin-head {
  width: 140px;
  height: 29px;
  font-size: 24px;
  font-weight: 600;
  margin: 40px 0;
}
.admin-toggle-container {
  display: flex;
  margin-bottom: 10px;
}
.monitoring-toggle-button {
  text-align: center;
  width: 110px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
  cursor: pointer;
  margin-right: 35px;
}
.monitoring-toggle-button:after {
  display: block;
  content: "";
  height: 10px;
  border-bottom: 1px solid #ffec3e;
  transform: scaleX(0);
  transition: 0.2s;
}
.monitoring-toggle-button.active:after {
  transform: scaleX(1);
  transition: 0.2s;
}
.warninguser-toggle-button {
  text-align: center;
  width: 110px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
  cursor: pointer;
  margin-right: 35px;
}
.warninguser-toggle-button:after {
  display: block;
  content: "";
  height: 10px;
  border-bottom: 1px solid #ffec3e;
  transform: scaleX(0);
  transition: 0.2s;
}
.warninguser-toggle-button.active:after {
  transform: scaleX(1);
  transition: 0.2s;
}
.blacklist-toggle-button {
  text-align: center;
  width: 130px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
  cursor: pointer;
}
.blacklist-toggle-button:after {
  display: block;
  content: "";
  height: 10px;
  border-bottom: 1px solid #ffec3e;
  transform: scaleX(0);
  transition: 0.2s;
}
.blacklist-toggle-button.active:after {
  transform: scaleX(1);
  transition: 0.2s;
}
</style>
