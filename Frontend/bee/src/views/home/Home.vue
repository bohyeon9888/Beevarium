<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";
import CardCarousel from "./CardCarousel.vue";

const sidebarStore = useSidebarStore();
const { isExpanded } = storeToRefs(sidebarStore);

const livestreams = ref([
  {
    streamerId: "streamer1",
    thumbnail: "hotlive1",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머1",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer2",
    thumbnail: "hotlive2",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머2",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer3",
    thumbnail: "hotlive3",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머3",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer4",
    thumbnail: "hotlive4",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머4",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer5",
    thumbnail: "hotlive5",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머5",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer6",
    thumbnail: "hotlive6",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머6",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer7",
    thumbnail: "hotlive7",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머7",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
  {
    streamerId: "streamer8",
    thumbnail: "hotlive8",
    title: "방송 제목입니다.",
    streamerLogo: "streamer_image_1",
    streamerName: "스트리머8",
    tags: ["한국어", "게임", "수다"],
    watcher: "1579",
  },
]);
const getImageUrl = (name) => {
  return new URL(`/src/assets/img/${name}.png`, import.meta.url).href;
};
const getThumbnailUrl = (name) => {
  return new URL(`/src/assets/img/home/hotlive/${name}.png`, import.meta.url).href;
};
</script>

<template>
  <div id="home-container" class="home-container" :class="{ expanded: !isExpanded }">
    <div class="carousel-container">
      <CardCarousel />
    </div>
    <div id="slogan-container" class="slogan-container" :class="{ expanded: !isExpanded }">
      <img class="slogan" src="../../assets/img/slogan.png" alt="" />
    </div>
    <div id="hotlive-container" class="hotlive-container" :class="{ expanded: !isExpanded }">
      <div style="font-size: 20px; font-weight: 600">인기 라이브</div>
      <ul id="hotlive-list" class="hotlive-list" :class="{ expanded: !isExpanded }">
        <li
          id="hotlive"
          class="hotlive"
          :class="{ expanded: !isExpanded }"
          v-for="(livestream, index) in livestreams"
          :key="index"
        >
          <div class="live-watcher-box">
            <div
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
            <div
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
              {{ livestream.watcher }}명 시청
            </div>
          </div>
          <div
            id="livestream-image-box"
            class="livestream-image-box"
            :class="{ expanded: !isExpanded }"
          >
            <router-link :to="{ path: `/streaming/live-stream/${livestream.streamerId}` }">
              <img
                id="livestream-image"
                class="livestream-image"
                :class="{ expanded: !isExpanded }"
                :src="getThumbnailUrl(livestream.thumbnail)"
                alt=""
              />
            </router-link>
          </div>
          <div
            id="livestream-info-container"
            class="livestream-info-container"
            :class="{ expanded: !isExpanded }"
          >
            <div class="streamer-logo-box">
              <img class="streamer-logo-image" :src="getImageUrl(livestream.streamerLogo)" alt="" />
            </div>
            <div
              id="livestream-info-box"
              class="livestream-info-box"
              :class="{ expanded: !isExpanded }"
            >
              <div class="livestream-title">{{ livestream.title }}</div>
              <div class="streamer-name">{{ livestream.streamerName }}</div>
              <ul class="livestream-tag-list">
                <li class="livestream-tag" v-for="(tag, index) in livestream.tags">
                  {{ tag }}
                </li>
              </ul>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  width: 1620px;
}
#home-container.expanded {
  width: 1790px;
}
.carousel-container {
  width: 1400px;
  height: 336px;
  margin-bottom: 30px;
}
.slogan-container {
  display: flex;
  justify-content: center;
  width: 1620px;
  height: 80px;
  border-radius: 16px;
  background-color: #1e1e1e;
}
#slogan-container.expanded {
  width: 1790px;
}
.slogan {
  width: 1620px;
  height: 80px;
  border-radius: 16px;
}
.hotlive-container {
  width: 1620px;
  margin: 24px 0 12px;
}
#hotlive-container.expanded {
  width: 1790px;
}
.hotlive-list {
  display: flex;
  justify-content: space-between;
  width: 1620px;
  flex-wrap: wrap;
  margin-top: 12px;
}
#hotlive-list.expanded {
  width: 1790px;
}
.hotlive {
  position: relative;
  width: 393px;
  height: 322px;
  margin-bottom: 30px;
}
.live-watcher-box {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 373px;
  height: 25px;
  display: flex;
}
#hotlive.expanded {
  width: 434px;
  height: 347px;
}
.livestream-image-box {
  width: 393px;
  height: 221px;
  border-radius: 16px;
}
#livestream-image-box.expanded {
  width: 434px;
  height: 244px;
}
.livestream-image {
  width: 393px;
  height: 221px;
  border-radius: 16px;
}
#livestream-image.expanded {
  width: 434px;
  height: 244px;
}
.livestream-info-container {
  display: flex;
  width: 393px;
  height: 89px;
  margin-top: 12px;
}
#livestream-info-container.expanded {
  width: 434px;
}
.streamer-logo-box {
  width: 40px;
  height: 40px;
  border-radius: 10rem;
}
.streamer-logo-image {
  width: 40px;
  height: 40px;
}
.livestream-info-box {
  width: 342px;
  height: 85px;
  margin: 4px 0 0 11px;
}
#livestream-info-box {
  width: 383px;
}
.livestream-title {
  font-size: 15px;
  font-weight: 700;
  margin-bottom: 6px;
}
.streamer-name {
  height: 17px;
  font-size: 14px;
  font-weight: 400;
  color: #b6b6b6;
}
.livestream-tag-list {
  display: flex;
  margin-top: 6px;
}
.livestream-tag {
  background-color: #434343;
  font-size: 12px;
  color: white;
  margin-right: 6px;
  height: 20px;
  text-align: center;
  line-height: 20px;
  border-radius: 10rem;
  padding: 0 8px;
}
</style>
