<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, onMounted } from "vue";

const prop = defineProps(["studioInfo"]);

const router = useRouter();
const route = useRoute();

const stream = ref({
  watcher: "1579",
  title: "[KT vs BRO] 2024 LCK Spring Spl Final",
});

const isLive = ref(true);

const moveToNoticeWrite = () => {
  router.push({ name: "NoticeWrite" });
};
const moveToStudioSetting = () => {
  router.push({ name: "StudioSetting" });
};

onMounted(() => {
  console.log(prop.studioInfo);
});
</script>

<template>
  <div class="studio-info-container">
    <div class="studio-info-box">
      <div class="streamer-logo-container">
        <router-link :to="{ name: 'StudioMain' }">
          <div class="streamer-logo-box">
            <img
              :src="prop.studioInfo.stationImg"
              alt=""
              class="streamer-logo"
            />
            <div
              v-if="prop.studioInfo.liveInfo.live"
              style="
                position: absolute;
                bottom: -10px;
                right: auto;
                width: 50px;
                height: 26px;
                padding: 5px 11px;
                font-size: 14px;
                font-weight: 700;
                background-color: red;
                border-radius: 8px;
              "
            >
              LIVE
            </div>
          </div>
        </router-link>
      </div>
      <div class="streamer-name-container">
        <div class="streamer-name-box">
          <div class="streamer-name">{{ prop.studioInfo.userName }}</div>
          <div class="streamer-id">({{ prop.studioInfo.userId }})</div>
        </div>
      </div>
      <div class="follower-count">
        팔로워 {{ prop.studioInfo.stationFollower }}명
      </div>
      <div
        v-if="prop.studioInfo.isMine"
        class="write-button"
        @click="moveToNoticeWrite"
      >
        글쓰기
      </div>
      <div
        v-if="prop.studioInfo.isMine"
        class="studio-setting-button"
        @click="moveToStudioSetting"
      >
        방송국 설정
      </div>
      <div v-if="prop.studioInfo.liveInfo.live" class="stream-live-thumbnail-box">
        <router-link :to="{ name: 'LiveStream' }">
          <img
            :src="prop.studioInfo.liveInfo.broadcastingThumbnail"
            alt=""
            class="stream-live-thumbnail"
          />
        </router-link>
        <div class="live-watcher-box">
          <div v-if="prop.studioInfo.liveInfo.live"
            style="
              width: 50px;
              height: 26px;
              padding: 5px 11px;
              font-size: 14px;
              font-weight: 700;
              background-color: red;
              border-radius: 8px;
            "
          >
            LIVE
          </div>
          <div v-if="prop.studioInfo.liveInfo.live"
            style="
              height: 26px;
              padding: 5px 11px;
              font-size: 14px;
              font-weight: 700;
              background-color: rgba(0, 0, 0, 0.6);
              border-radius: 8px;
              margin-left: 8px;
            "
          >
            {{ prop.studioInfo.liveInfo.viewers }}명 시청
          </div>
        </div>
        <div class="stream-title">{{ prop.studioInfo.liveInfo.broadcastingTitle }}</div>
      </div>
      <div class="studio-intro-container">
        <div class="studio-intro">{{ prop.studioInfo.stationTitle }}</div>
      </div>
      <div class="board-menu-container">
        <div style="font-size: 18px; font-weight: 700">게시판</div>
        <router-link class="move-to-notice" :to="{ name: 'Notice' }"
          >공지사항</router-link
        >
      </div>
      <div class="vod-menu-container">
        <div style="font-size: 18px; font-weight: 700">VOD</div>
        <router-link class="move-to-replay" :to="{ name: 'Replay' }"
          >다시보기</router-link
        >
        <router-link class="move-to-clip" :to="{ name: 'Clip' }"
          >유저 클립</router-link
        >
      </div>
      <div class="studio-manage-menu-container">
        <div style="font-size: 18px; font-weight: 700">방송국 관리</div>
        <router-link class="move-to-dashboard" :to="{ name: 'Dashboard' }"
          >스트리머 대시보드</router-link
        >
        <router-link
          class="move-to-banlist-management"
          :to="{ name: 'BanListManagement' }"
          >밴리스트 관리</router-link
        >
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-info-container {
  display: flex;
  justify-content: center;
  width: 320px;
}
.studio-info-box {
  width: 272px;
}
.streamer-logo-container {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  width: 272px;
  height: 150px;
}
.streamer-logo-box {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  border: 2px solid #ffec3e;
  border-radius: 10rem;
  margin-bottom: 12px;
  cursor: pointer;
}
.streamer-logo {
  width: 90px;
  height: 90px;
  border-radius: 10rem;
}
.streamer-name-container {
  display: flex;
  justify-content: center;
  width: 272px;
  height: 42px;
  margin-bottom: 12px;
}
.streamer-name-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 42px;
}
.streamer-name {
  height: 21px;
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 4px;
}
.streamer-id {
  height: 17px;
  font-size: 14px;
  font-weight: 400;
  color: #a0a0a0;
}
.follower-count {
  font-size: 14px;
  font-weight: 600;
  text-align: center;
  margin-bottom: 12px;
}
.write-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 272px;
  height: 48px;
  font-size: 16px;
  font-weight: 700;
  background-color: #ffec3e;
  border-radius: 8px;
  color: #121212;
  margin-bottom: 16px;
  cursor: pointer;
}
.studio-setting-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 272px;
  height: 48px;
  font-size: 16px;
  font-weight: 700;
  background-color: #ffcf40;
  border-radius: 8px;
  color: #121212;
  margin-bottom: 16px;
  cursor: pointer;
}
.stream-live-thumbnail-box {
  position: relative;
  width: 272px;
  height: 153px;
  border-radius: 16px;
  margin-bottom: 24px;
}
.stream-live-thumbnail {
  width: 272px;
  height: 153px;
  object-fit: cover;
  border-radius: 16px;
}
.live-watcher-box {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 373px;
  height: 25px;
  display: flex;
}
.stream-title {
  position: absolute;
  bottom: 12px;
  right: 12px;
  width: 248px;
  height: 18px;
  font-size: 15px;
  font-weight: 700;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.studio-intro-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 272px;
  border: 1px solid #ffec3e;
  border-radius: 8px;
  margin-bottom: 24px;
}
.studio-intro {
  justify-content: flex-start;
  width: 240px;
  font-size: 16px;
  font-weight: 700;
  margin: 16px;
}
.board-menu-container {
  display: flex;
  flex-direction: column;
  width: 272px;
  height: 104px;
  padding: 24px 0;
  border-top: 1px solid #323232;
}
.move-to-notice {
  font-size: 16px;
  font-weight: 400;
  margin: 16px 0 16px 8px;
}
.vod-menu-container {
  display: flex;
  flex-direction: column;
  width: 272px;
  height: 140px;
  padding: 24px 0;
  border-top: 1px solid #323232;
}
.move-to-replay {
  font-size: 16px;
  font-weight: 400;
  margin: 16px 0 16px 8px;
}
.move-to-clip {
  font-size: 16px;
  font-weight: 400;
  margin-left: 8px;
}
.studio-manage-menu-container {
  display: flex;
  flex-direction: column;
  width: 272px;
  height: 140px;
  padding: 24px 0;
  border-top: 1px solid #323232;
}
.move-to-dashboard {
  font-size: 16px;
  font-weight: 40;
  margin: 16px 0 16px 8px;
}
.move-to-banlist-management {
  font-size: 16px;
  font-weight: 400;
  margin-left: 8px;
}
</style>
