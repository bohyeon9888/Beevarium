<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";
// import StudioInfo from "./components/StudioInfo.vue";

const prop = defineProps(["studioInfo"]);
const clip = ref({
  title: "모든 길은 결국 저를 통합니다_페이커 [T1 vs JDG]",
  thumbnail: "clip-detail1",
  username: "user1",
  date: "24.01.09",
  views: 1353,
});
</script>

<template>
  <!-- <div class="clip-detail-container"> -->
    <div class="clip-detail-content-container">
      <div class="back-to-clip-button">
        <router-link
          :to="{ name: 'Clip' }"
          style="display: flex; align-items: center; width: 96px; height: 24px;"
        >
          <img src="../../assets/img/common/prev-button.png" alt="" class="back-to-clip" />
          <div style="width: 75px; height: 24px; font-size: 20px; font-weight: 600">유저 클립</div>
        </router-link>
      </div>
      <div class="clip-detail-content-box">
        <div class="clip-title">{{ clip.title }}</div>
        <div class="clip-detail-content">
          <div class="clip-username-date-views-box">
            <div class="clip-username">
              <div style="font-size: 16px; font-weight: 400; color: #a0a0a0; margin-right: 4px;">작성자 :</div>
              {{ clip.username }}
            </div>
            <div
              style="
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 180px;
                height: 19px;
              "
            >
              <div class="clip-date">{{ clip.date }}</div>
              <div style="width: 1px; height: 14px; background: #636363"></div>
              <div class="clip-views">
                <div style="font-size: 16px; font-weight: 400; color: #a0a0a0; margin-right: 4px;">조회수</div>
                {{ clip.views }}회
              </div>
            </div>
          </div>
          <div class="clip-screen">
            <img src="../../assets/img/studio/clip-detail1.png" alt="" class="clip" />
          </div>
        </div>
      </div>
    </div>
  <!-- </div> -->
</template>

<style scoped>
/* .clip-detail-container {
  display: flex;
  width: 1899px;
} */
.clip-detail-content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1579px;
}
.back-to-clip-button {
  width: 1579px;
  height: 64px;
  padding: 16px 0 24px 30px;
}
.back-to-clip {
  height: 18px;
  margin-right: 8px;
}
.clip-detail-content-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 1519px;
  height: 842px;
  background-color: #1e1e1e;
  border-radius: 16px;
}
.clip-title {
  width: 1420px;
  height: 24px;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 24px;
}
.clip-detail-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1420px;
  height: 693px;
  border-top: 1px solid #434343;
}
.clip-username-date-views-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1420px;
  height: 19px;
  margin: 20px 0 50px;
}
.clip-username {
  display: flex;
  font-size: 16px;
  font-weight: 400;
}
.clip-date,
.clip-views {
  display: flex;
  font-size: 16px;
  font-weight: 400;
}
.clip-screen {
  width: 1000px;
  height: 554px;
  margin-bottom: 10px;
}
.clip {
  width: 1000px;
  height: 554px;
}
</style>
