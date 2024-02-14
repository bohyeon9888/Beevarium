<script setup>
import { ref, onMounted } from "vue";
import { RouterLink, useRoute } from "vue-router";
import { replayDetail } from "@/api/replay";

// const replay = ref({
//   title: "다시보기 방송 제목입니다.",
//   tags: ["한국어", "게임", "방송"],
//   views: 1353,
//   date: "24.01.09",
// });

const replay = ref({});

const route = useRoute();

onMounted(() => {
  replayDetail(
    route.params.replayNo,
    ({ data }) => {
      console.log(data);
      replay.value = data.videoList;
    },
    (error) => {
      console.log(error.data.msg);
    }
  );
});
</script>

<template>
  <!-- <div class="replay-detail-container"> -->
  <div class="replay-detail-content-container">
    <div class="back-to-replay-button">
      <router-link
        :to="{ name: 'Replay' }"
        style="display: flex; align-items: center; width: 91px; height: 24px"
      >
        <img
          src="../../assets/img/common/prev-button.png"
          alt=""
          class="back-to-replay"
        />
        <div
          style="width: 70px; height: 24px; font-size: 20px; font-weight: 600"
        >
          다시보기
        </div>
      </router-link>
    </div>
    <div class="replay-detail-content-box">
      <div class="replay-screen-box">
        <video :src="replay.replayVideoUrl" class="replay-screen"></video>
      </div>
      <div class="replay-info-container">
        <div class="replay-info-box">
          <div class="replay-title-box">
            {{ replay.title }}
          </div>
          <div class="replay-views-date-box">
            <div>조회수 {{ replay.viewers }}회</div>
            <div
              style="
                width: 1px;
                height: 14px;
                margin: 0 10px;
                background: #636363;
              "
            ></div>
            <div>{{ replay.createdDate }}</div>
          </div>
        </div>
        <div class="clip-setting-container">
          <div class="clip-checkpoint-button-box">
            <div class="clip-startpoint-button">
              <img
                src="../../assets/img/studio/clip/clip-startpoint.png"
                alt=""
                class="clip-startpoint"
              />
              <div style="font-size: 14px; font-weight: 700">클립 체크</div>
            </div>
            <div class="clip-endpoint-button">
              <div style="font-size: 14px; font-weight: 700">클립 해제</div>
              <img
                src="../../assets/img/studio/clip/clip-endpoint.png"
                alt=""
                class="clip-endpoint"
              />
            </div>
          </div>
          <div class="clip-save-button-box">
            <div class="clip-save-button">
              <div style="font-size: 14px; font-weight: 700; color: #121212">
                클립 저장
              </div>
              <img
                src="../../assets/img/studio/clip/clip-save.png"
                alt=""
                class="clip-save"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<style scoped>
/* .replay-detail-container {
  display: flex;
  width: 1899px;
} */
.replay-detail-content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1579px;
}
.back-to-replay-button {
  width: 1579px;
  height: 64px;
  padding: 16px 0 24px 30px;
}
.back-to-replay {
  height: 18px;
  margin-right: 8px;
}
.replay-detail-content-box {
  width: 1216px;
}
.replay-screen-box {
  width: 1216px;
  height: 674px;
  margin-bottom: 8px;
}
.replay-screen {
  width: 1216px;
  height: 674px;
}
.progressbar-container {
  width: 1216px;
  height: 40px;
  background-color: #434343;
  margin-bottom: 34px;
}
.replay-info-container {
  display: flex;
  width: 1216px;
  height: 92px;
}
.replay-info-box {
  width: 988px;
  height: 92px;
}
.replay-title-box {
  height: 29px;
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 12px;
}
.replay-tag-box {
  height: 20px;
  margin-bottom: 12px;
}
.tag-list {
  display: flex;
  height: 20px;
}
.tag {
  height: 20px;
  padding: 3px 8px;
  background-color: #434343;
  border-radius: 10rem;
  font-size: 12px;
  font-weight: 500;
  margin-right: 6px;
}
.replay-views-date-box {
  display: flex;
  align-items: center;
  height: 19px;
}
.clip-setting-container {
  width: 212px;
  height: 74px;
}
.clip-checkpoint-button-box {
  display: flex;
  justify-content: space-between;
  width: 212px;
  margin-bottom: 8px;
}
.clip-startpoint-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 102px;
  height: 33px;
  background-color: #404040;
  border-radius: 8px;
  cursor: pointer;
}
.clip-startpoint {
  width: 14px;
  height: 12px;
  margin-right: 4px;
}
.clip-endpoint-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 102px;
  height: 33px;
  background-color: #404040;
  border-radius: 8px;
  cursor: pointer;
}
.clip-endpoint {
  width: 14px;
  height: 12px;
  margin-left: 4px;
}
.clip-save-button-box {
  display: flex;
  width: 212px;
  justify-content: flex-end;
}
.clip-save-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 102px;
  height: 33px;
  background-color: #ffec3e;
  border-radius: 8px;
  cursor: pointer;
}
.clip-save {
  margin-left: 4px;
}
</style>
