<script setup>
import { ref, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { search } from "@/api/search";

const router = useRouter();

const route = useRoute();
const keyword = computed(() => route.params.keyword);
const showAllChannels = ref(false);
const maxChannelsToShow = ref(3);
const maxClipsToShow = ref(6);

const loadMoreChannels = () => {
  maxChannelsToShow.value += 3;
};
const loadMoreClips = () => {
  maxClipsToShow.value += 6;
};

const VODs = [
  {
    thumbnail: "search-vod1",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo1",
    name: "스트리머1",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod2",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo2",
    name: "스트리머2",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod3",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo3",
    name: "스트리머3",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod4",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo1",
    name: "스트리머4",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod5",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo2",
    name: "스트리머5",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod6",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo3",
    name: "스트리머6",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod1",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo1",
    name: "스트리머1",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod2",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo2",
    name: "스트리머2",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod3",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo3",
    name: "스트리머3",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod4",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo1",
    name: "스트리머4",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod5",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo2",
    name: "스트리머5",
    tags: ["한국어", "마인크래프트", "게임"],
  },
  {
    thumbnail: "search-vod6",
    title: "사업 시작과 동시에 망합니다",
    views: 626,
    logo: "search-streamer-logo3",
    name: "스트리머6",
    tags: ["한국어", "마인크래프트", "게임"],
  },
];

const livestreams = ref([]);

const doSearch = () => {
  search(
    route.params.keyword,
    ({ data }) => {
      livestreams.value = data.data.result;
    },
    (error) => {
      console.log("검색 api 실패");
    }
  );
};

const getStreamerLogoUrl = (name) => {
  return new URL(`/src/assets/img/search/${name}.png`, import.meta.url).href;
};
const getThumbnailUrl = (name) => {
  return new URL(`/src/assets/img/search/${name}.png`, import.meta.url).href;
};

onMounted(() => {
  doSearch();
});
</script>

<template>
  <div class="search-container">
    <div class="search-keyword-box">
      <div class="search-keyword">{{ keyword }}</div>
      <div style="font-size: 20px; font-weight: 600">(으)로 검색한 생방송 채널</div>
    </div>
    <ul class="search-livestream-list">
      <li
        class="search-livestream"
        v-for="(livestream, index) in livestreams.slice(0, maxChannelsToShow)"
        :key="index"
        @click="router.push({ path: `/streaming/live-stream/${livestream.streamerId}` })"
      >
        <div class="search-livestream-thumbnail-box">
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
              {{ livestream.viewers }}명 시청
            </div>
          </div>
          <img :src="livestream.broadcastingImgUrl" alt="" class="search-livestream-thumbnail" />
        </div>
        <div class="search-livestream-info-container">
          <div class="search-livestream-info-box">
            <div class="search-livestream-streamer-box">
              <div class="streamer-logo-box">
                <img :src="livestream.profileUrl" alt="" class="streamer-logo" />
              </div>
              <div class="search-livestream-streamer-name">
                {{ livestream.streamerName }}
              </div>
            </div>
            <div class="search-livestream-watcher">시청자 {{ livestream.viewers }}명</div>
            <div class="search-livestream-title">{{ livestream.broadcastingTitle }}</div>
            <ul class="search-livestream-tags">
              <li
                v-for="(tag, index) in livestream.tags"
                :key="index"
                class="search-livestream-tag"
              >
                {{ tag }}
              </li>
            </ul>
          </div>
        </div>
      </li>
    </ul>
    <div class="more-button-container" v-if="!showAllChannels">
      <div style="width: 757px; height: 1px; borer-radius: 8px; background-color: #323232"></div>
      <div class="more-button-box" @click="loadMoreChannels">
        <div
          style="
            width: 32px;
            height: 14px;
            font-size: 12px;
            font-weight: 600;
            margin-right: 6px;
            color: #dcdcdc;
          "
        >
          더보기
        </div>
        <img src="../../assets/img/search/more-button.png" alt="" />
      </div>
      <div style="width: 757px; height: 1px; borer-radius: 8px; background-color: #323232"></div>
    </div>
    <div class="search-keyword-box">
      <div class="search-keyword">#{{ keyword }}</div>
      <div style="font-size: 20px; font-weight: 600">동영상</div>
    </div>
    <ul class="search-vod-list">
      <li
        v-for="(VOD, index) in VODs.slice(0, maxClipsToShow)"
        :key="index"
        class="search-vod"
        @click="moveToVOD"
      >
        <div class="search-vod-thumbnail-box">
          <img :src="getThumbnailUrl(VOD.thumbnail)" alt="" class="search-vod-thumbnail" />
        </div>
        <div class="search-vod-info-container">
          <div class="search-vod-info-box">
            <div class="search-vod-title">{{ VOD.title }}</div>
            <div class="search-vod-streamer-box">
              <div class="streamer-logo-box">
                <img :src="getStreamerLogoUrl(VOD.logo)" alt="" class="streamer-logo" />
              </div>
              <div class="search-vod-streamer-name">{{ VOD.name }}</div>
            </div>
            <div class="search-vod-views">조회수 {{ VOD.views }}회</div>
          </div>
        </div>
      </li>
    </ul>
    <div class="more-button-container">
      <div style="width: 757px; height: 1px; borer-radius: 8px; background-color: #323232"></div>
      <div class="more-button-box" @click="loadMoreClips">
        <div
          style="
            width: 32px;
            height: 14px;
            font-size: 12px;
            font-weight: 600;
            margin-right: 6px;
            color: #dcdcdc;
          "
        >
          더보기
        </div>
        <img src="../../assets/img/search/more-button.png" alt="" />
      </div>
      <div style="width: 757px; height: 1px; borer-radius: 8px; background-color: #323232"></div>
    </div>
  </div>
</template>

<style scoped>
.search-container {
  width: 1620px;
  min-height: 1076px;
}
.search-keyword-box {
  display: flex;
  width: 1620px;
  height: 24px;
  margin-bottom: 24px;
}
.search-keyword {
  height: 24px;
  font-size: 20px;
  font-weight: 600;
  color: #ffcf40;
  margin-right: 6px;
}
.search-livestream-list {
  display: flex;
  flex-direction: column;
  width: 526px;
  min-height: 496px;
}
.search-livestream {
  display: flex;
  width: 526px;
  height: 152px;
  margin: 10px 0;
  cursor: pointer;
}
.search-livestream:first-child {
  margin-top: 0;
}
.search-livestream:last-child {
  margin-bottom: 0;
}
.search-livestream-thumbnail-box {
  position: relative;
  width: 270px;
  height: 152px;
  margin-right: 16px;
}
.live-watcher-box {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 373px;
  height: 25px;
  display: flex;
}
.search-livestream-thumbnail {
  width: 270px;
  height: 152px;
  border-radius: 16px;
}
.search-livestream-info-container {
  width: 240px;
  height: 152px;
}
.search-livestream-info-box {
  width: 240px;
  min-height: 102px;
  margin-top: 14px;
}
.search-livestream-streamer-box {
  display: flex;
  align-items: center;
  width: 240px;
  height: 24px;
  margin-bottom: 8px;
}
.streamer-logo-box {
  width: 24px;
  height: 24px;
  margin-right: 4px;
}
.streamer-logo {
  width: 24px;
  height: 24px;
  border-radius: 10rem;
}
.search-livestream-streamer-name {
  width: 212px;
  height: 21px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 18px;
  font-weight: 600;
}
.search-livestream-watcher {
  width: 240px;
  height: 19px;
  font-size: 15px;
  font-weight: 400;
  color: #dcdcdc;
  margin-bottom: 4px;
}
.search-livestream-title {
  width: 240px;
  height: 19px;
  font-size: 15px;
  font-weight: 400;
  color: #dcdcdc;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 4px;
}
.search-livestream-tags {
  display: flex;
  width: 240px;
  min-height: 20px;
  flex-wrap: wrap;
}
.search-livestream-tag {
  height: 20px;
  font-size: 12px;
  font-weight: 500;
  padding: 3px 8px;
  border-radius: 10rem;
  background-color: #434343;
  margin-right: 6px;
  margin-bottom: 6px;
}
.more-button-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1620px;
  height: 30px;
  margin: 30px 0;
}
.more-button-box {
  display: flex;
  align-items: center;
  height: 30px;
  padding: 8px 16px;
  color: #dcdcdc;
  border: 1px solid #a0a0a0;
  border-radius: 10rem;
  cursor: pointer;
}
.search-vod-list {
  display: flex;
  flex-wrap: wrap;
  width: 1620px;
  min-height: 334px;
}
.search-vod {
  display: flex;
  width: 526px;
  height: 152px;
  margin: 0 10px 30px;
  cursor: pointer;
}
.search-vod:nth-child(3n) {
  margin-right: 0;
}
.search-vod:nth-child(3n + 1) {
  margin-left: 0;
}
.search-vod:nth-last-child(-n + 3) {
  margin-bottom: 0;
}
.search-vod-thumbnail-box {
  width: 270px;
  height: 152px;
  margin-right: 16px;
}
.search-vod-thumbnail {
  width: 270px;
  height: 152px;
  border-radius: 16px;
}
.search-vod-info-container {
  width: 240px;
  height: 152px;
}
.search-vod-info-box {
  width: 240px;
  min-height: 102px;
  margin-top: 14px;
}
.search-vod-title {
  width: 240px;
  height: 19px;
  font-size: 16px;
  font-weight: 700;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 8px;
}
.search-vod-streamer-box {
  display: flex;
  align-items: center;
  width: 240px;
  height: 24px;
  margin-bottom: 8px;
}
.search-vod-streamer-name {
  width: 212px;
  height: 17px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 14px;
  font-weight: 600;
  color: #b6b6b6;
}
.search-vod-views {
  width: 240px;
  height: 17px;
  font-size: 14px;
  font-weight: 400;
  color: #b6b6b6;
  margin-bottom: 8px;
}
.search-vod-tags {
  display: flex;
  width: 240px;
  min-height: 20px;
  flex-wrap: wrap;
}
.search-vod-tag {
  height: 20px;
  font-size: 12px;
  font-weight: 500;
  padding: 3px 8px;
  border-radius: 10rem;
  background-color: #434343;
  margin-right: 6px;
  margin-bottom: 6px;
}
</style>
