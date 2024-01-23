<script setup>
import StudioInfo from "./components/StudioInfo.vue";
import StreamThumbnail from "../common/components/StreamThumbnail.vue";
import { useMoveStore } from "@/stores/move";

const store = useMoveStore();
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="studio-main">
        <StudioInfo class="studio-sidebar" />
        <div class="studio-content">
          <div class="content-list" @click="store.goNotice">
            <div class="content-top">
              <h3>공지사항</h3>
              <button @click="store.goNotice">리스트</button>
            </div>
            <div
              v-for="notice in notice_list"
              :key="notice.broadcasting_station_notice_no"
            >
              <p>제목: {{ notice.broadcasting_station_notice_title }}</p>
              <p>
                작성 일시: {{ notice.broadcasting_station_notice_timestamp }}
              </p>
              <p>작성자: {{ notice.member_nickname }}</p>
            </div>
          </div>
          <div class="video-zone">
            <div class="content-list">
              <div class="content-top">
                <h3>다시보기</h3>
                <button @click="store.goReplay">리스트</button>
              </div>
              <div class="video-list">
                <StreamThumbnail @click="store.goReplayDetail" />
                <StreamThumbnail @click="store.goReplayDetail" />
                <StreamThumbnail @click="store.goReplayDetail" />
                <StreamThumbnail @click="store.goReplayDetail" />
                <div v-for="video in videos" :key="replay_video_no">
                  <StreamThumbnail />
                  <p>{{ video.title }}</p>
                </div>
              </div>
            </div>
            <div class="content-list">
              <div class="content-top">
                <h3>유저 클립</h3>
                <button @click="store.goClip">리스트</button>
              </div>
              <div class="video-list">
                <StreamThumbnail @click="store.goClipDetail" />
                <StreamThumbnail @click="store.goClipDetail" />
                <StreamThumbnail @click="store.goClipDetail" />
                <StreamThumbnail @click="store.goClipDetail" />
                <div v-for="video in videos" :key="replay_video_no">
                  <StreamThumbnail />
                  <p></p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-content {
  width: 100%;
  border: 2px solid #000;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 10px;
  overflow: auto;
  display: grid;
  grid-template-rows: 1fr 1fr;
}
.studio-main {
  display: grid;
  grid-template-columns: 3fr 7fr;
  padding: 10px;
  width: 100%;
  gap: 20px;
}

.content-list {
  width: 100%;
  height: 210px;
  border: 2px solid #000;
  padding: 10px;
  margin-bottom: 10px;
}

.video-zone {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
}

.video-list {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  margin-bottom: 10px;
  width: 100%;
}

.content-top {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
</style>
