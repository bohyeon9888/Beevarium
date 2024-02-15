<script setup>
import { ref, onMounted } from "vue";
import { studio } from "@/api/studio";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const authStore = useAuthStore();
const { accessToken } = storeToRefs(authStore);

// const replays = ref([
//   {
//     thumbnail: "replay1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//   },
//   {
//     thumbnail: "replay2",
//     title: "2023 LCK AWARDS",
//     date: "23.12.13",
//   },
//   {
//     thumbnail: "replay3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "23.10.28",
//   },
//   {
//     thumbnail: "replay4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "23.10.28",
//   },
//   {
//     thumbnail: "replay5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "23.08.06",
//   },
// ]);

// const clips = ref([
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
// ]);

const getReplayUrl = (name) => {
  return new URL(`/src/assets/img/studio/${name}.png`, import.meta.url).href;
};

const studioInfo = ref({});

const getStudio = () => {
  studio(
    accessToken.value,
    route.params.streamerId,
    ({ data }) => {
      studioInfo.value = data.data.stationInfo;
      console.log(studioInfo.value);
    },
    (error) => {}
  );
};

onMounted(() => {
  getStudio();
});
</script>

<template>
  <!-- <div class="studiomain-container"> -->
  <div class="studiomain-content-container" v-if="studioInfo.firstNotice">
    <div class="studiomain-content-box">
      <div class="studio-banner-container">
        <img
          src="../../assets/img/studio/studio-banner-1.png"
          alt=""
          class="studio-banner"
        />
      </div>
      <div class="studio-notice-container">
        <div style="height: 36px; font-size: 20px; font-weight: 600">
          <router-link
            :to="{ path: `/studio/${route.params.streamerId}/notice` }"
            >공지사항</router-link
          >
        </div>
        <div class="notice-container">
          <router-link
            :to="{ path: `/studio/${route.params.streamerId}/notice/${studioInfo.firstNotice.broadcastStationNoticeNo}` }"
          >
            <div class="notice-content-container">
              <div class="streamer-box">
                <div class="streamer-logo-box">
                  <img :src="studioInfo.stationImg" alt="" class="streamer-logo">
                </div>
                <div class="streamer-info-box">
                  <div class="streamer">
                    <div class="streamer-name">{{ studioInfo.userName }}</div>
                    <div class="streamer-id">({{ studioInfo.userId }})</div>
                  </div>
                  <div class="notice-date">
                    {{ studioInfo.firstNotice.createdDate }}
                  </div>
                </div>
              </div>
              <div class="notice-content-box">
                <div class="notice-title">{{ studioInfo.firstNotice.broadcastStationNoticeTitle }}</div>
                <div class="notice-content">{{ studioInfo.firstNotice.broadcastStationNoticeContent }}</div>
              </div>
            </div>
          </router-link>
          <div class="notice-banner-box">
            <img
              src="../../assets/img/studio/notice-banner.png"
              alt=""
              class="notice-banner"
            />
          </div>
        </div>
      </div>
      <div class="studio-replay-container">
        <div style="height: 36px; font-size: 20px; font-weight: 600">
          <router-link
            :to="{ path: `/studio/${route.params.streamerId}/replay` }"
            >다시보기</router-link
          >
        </div>
        <div class="replay-container">
          <ul class="replay-list">
            <li
              v-for="(replay, index) in studioInfo.replayList"
              :key="index"
              class="replay"
              @click="
                router.push({
                  path: `/studio/${route.params.streamerId}/replay/${replay.id}`,
                })
              "
            >
              <div class="replay-thumbnail-box">
                <img :src="replay.imgUrl" alt="" class="replay-thumbnail" />
                <div
                  style="
                    position: absolute;
                    top: 12px;
                    left: 12px;
                    width: 69px;
                    height: 25px;
                    font-size: 14px;
                    font-weight: 700;
                    background-color: #e89900;
                    border-radius: 8px;
                    padding: 4px 10px;
                  "
                >
                  다시보기
                </div>
              </div>
              <div class="replay-info-box">
                <div class="replay-title">{{ replay.title }}</div>
                <div class="replay-date">{{ replay.date }}</div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="studio-clip-container">
        <div style="height: 36px; font-size: 20px; font-weight: 600">
          <router-link :to="{ path: `/studio/${route.params.streamerId}/clip` }"
            >유저 클립</router-link
          >
        </div>
        <div class="clip-container">
          <ul>
            <router-link
              :to="{ path: `/studio/${route.params.streamerId}/clip/1` }"
              class="clip-list"
            >
              <li
                v-for="(clip, index) in studioInfo.clipList"
                :key="index"
                class="clip"
              >
                <div class="clip-thumbnail-box">
                  <img
                    :src="getReplayUrl(clip.thumbnail)"
                    alt=""
                    class="clip-thumbnail"
                  />
                </div>
                <div class="clip-info-box">
                  <div class="clip-title">{{ clip.title }}</div>
                  <div class="clip-username">{{ clip.username }}</div>
                  <div class="clip-date">{{ clip.date }}</div>
                </div>
              </li>
            </router-link>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<style scoped>
/* .studiomain-container {
  display: flex;
  width: 1899px;
} */
.studio-info {
  width: 320px;
}
.studiomain-content-container {
  display: flex;
  justify-content: center;
  width: 1579px;
}
.studiomain-content-box {
  width: 1540px;
}
.studio-banner-container {
  width: 1540px;
  height: 200px;
  border-radius: 16px;
  margin-bottom: 32px;
}
.studio-banner {
  width: 1540px;
  height: 200px;
  border-radius: 16px;
  object-fit: cover;
}
.studio-notice-container {
  width: 1540px;
  height: 216px;
  margin-bottom: 32px;
}
.notice-container {
  display: flex;
  justify-content: space-between;
  width: 1540px;
  height: 180px;
}
.notice-content-container {
  display: flex;
  width: 916px;
  height: 180px;
  background-color: #1e1e1e;
  border-radius: 16px;
  padding: 32px;
}
.notice-content-box {
  width: 600px;
  height: 116px;
}
.notice-title {
  font-size: 20px;
  font-weight: 600;
}
.notice-content {
  font-size: 16px;
  font-weight: 400;
  margin-top: 16px;
}
.streamer-box {
  display: flex;
  width: 290px;
  height: 44px;

}
.streamer-logo-box {
  width: 44px;
  height: 44px;
}
.streamer-logo {
  width: 44px;
  height: 44px;
  border-radius: 10rem;
  object-fit: contain;
}
.streamer-info-box {
  width: 290px; 
  height: 44px;
  margin-left: 8px;
}
.streamer {
  display: flex;
  align-items: center;
  width: 248px;
  height: 19px;
}
.streamer-name {
  height: 19px;
  font-size: 16px;
  font-weight: 600;
}
.streamer-id {
  height: 16px;
  font-size: 14px;
  font-weight: 00;
  color: #a0a0a0;
}
.notice-date {
  height: 17px;
  font-size: 14px;
  font-weight: 400;
  margin-top: 8px;
}
.notice-banner-box {
  width: 607px;
  height: 180px;
  border-radius: 16px;
}
.notice-banner {
  width: 607px;
  height: 180px;
  border-radius: 16px;
  object-fit: cover;
}
.studio-replay-container {
  width: 1540px;
  height: 254px;
  margin-bottom: 32px;
}
.replay-container {
  width: 1540px;
  height: 218px;
}
.replay-list {
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
  width: 1540px;
  height: 218px;
}
.replay {
  width: 294px;
  height: 218px;
  cursor: pointer;
  margin: 0 8.5px;
}
.replay:nth-child(5n + 1) {
  margin-left: 0;
}
.replay:nth-child(5n) {
  margin-right: 0;
}
.replay:nth-last-child(-n + 5) {
  margin-bottom: 0;
}
.replay-thumbnail-box {
  position: relative;
  width: 294px;
  height: 165px;
  border-radius: 16px;
  border: 1px solid #252525;
}
.replay-thumbnail {
  width: 294px;
  height: 165px;
  border-radius: 16px;
  object-fit: cover;
}
.replay-info-box {
  width: 294px;
  height: 41px;
  margin-top: 12px;
}
.replay-title {
  width: 294px;
  height: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  font-weight: 700;
  margin-bottom: 6px;
}
.replay-date {
  width: 294px;
  height: 17px;
  font-size: 15px;
  font-weight: 700;
  color: #b6b6b6;
}
.studio-clip-container {
  width: 1540px;
  height: 310px;
}
.clip-container {
  width: 1540px;
  height: 242px;
}
.clip-list {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 1540px;
  height: 242px;
}
.clip {
  width: 294px;
  height: 242px;
  cursor: pointer;
  margin-bottom: 32px;
}
.clip-thumbnail-box {
  position: relative;
  width: 294px;
  height: 165px;
  border-radius: 16px;
  border: 1px solid #252525;
}
.clip-thumbnail {
  width: 294px;
  height: 165px;
  border-radius: 16px;
  object-fit: cover;
}
.clip-info-box {
  width: 294px;
  height: 65px;
  margin-top: 12px;
}
.clip-title {
  width: 294px;
  height: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  font-weight: 700;
}
.clip-username {
  width: 294px;
  height: 18px;
  font-size: 15px;
  font-weight: 700;
  color: #b6b6b6;
  margin: 6px 0;
}
.clip-date {
  width: 294px;
  height: 17px;
  font-size: 15px;
  font-weight: 700;
  color: #b6b6b6;
}
</style>
