<script setup>
import { ref, computed } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const keyword = computed(() => route.params.keyword);
const showAllChannels = ref(false);
const loadMoreChannels = () => {
  showAllChannels.value = true;
};
const showAllClips = ref(false);
const loadMoreClips = () => {
  showAllClips.value = true;
};
const channels = [
  { name: "닉네임1", followers: 12354 },
  { name: "닉네임2", followers: 12354 },
  { name: "닉네임3", followers: 12354 },
  { name: "닉네임4", followers: 12354 },
  { name: "닉네임5", followers: 12354 },
  { name: "닉네임6", followers: 12354 },
];
const streamingChannels = [
  {
    name: "스트리머1",
    category: "카테고리1",
    viewers: 1000,
    title: "방송 제목1",
  },
  {
    name: "스트리머2",
    category: "카테고리2",
    viewers: 2000,
    title: "방송 제목2",
  },
  {
    name: "스트리머3",
    category: "카테고리3",
    viewers: 3000,
    title: "방송 제목3",
  },
  {
    name: "스트리머4",
    category: "카테고리4",
    viewers: 4000,
    title: "방송 제목4",
  },
];
const Clips = [
  {
    title: "클립 제목1",
    viewers: 1500,
    likes: 100,
    name: "유저1",
  },
  {
    title: "클립 제목2",
    viewers: 2500,
    likes: 230,
    name: "유저2",
  },
  {
    title: "클립 제목3",
    viewers: 3500,
    likes: 330,
    name: "유저3",
  },
  {
    title: "클립 제목4",
    viewers: 4500,
    likes: 400,
    name: "유저4",
  },
  {
    title: "클립 제목5",
    viewers: 5500,
    likes: 520,
    name: "유저5",
  },
];
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="search-header">
        <h1>'{{ keyword }}' 의 검색 결과입니다.</h1>
      </div>
      <div class="search-content">
        <div class="search-channel">
          <h2>채널</h2>
          <div class="channel-list">
            <div class="search-channel-list">
              <div
                class="channel-detail"
                v-for="(channel, index) in showAllChannels
                  ? channels
                  : channels.slice(0, 2)"
                :key="index"
              >
                <div class="channel-img"></div>
                <div class="name-follow">
                  <p>{{ channel.name }}</p>
                  <p>팔로워: {{ channel.followers }}</p>
                </div>
                <div class="follow-button">
                  <button>팔로우</button>
                </div>
              </div>
            </div>
            <div v-if="!showAllChannels" class="more-container">
              <button class="more-button" @click="loadMoreChannels">
                더 보기
              </button>
            </div>
          </div>
        </div>
        <div class="search-streaming">
          <h2>생방송 중인 채널</h2>
          <div class="streaming-list">
            <div
              class="search-result"
              v-for="(channel, index) in streamingChannels"
              :key="index"
            >
              <div class="streaming-thumbnail"></div>
              <div class="streaming-info">
                <h3>{{ channel.name }}</h3>
                <p>{{ channel.category }}</p>
                <p>시청자: {{ channel.viewers }} 명</p>
                <p>{{ channel.title }}</p>
                <div class="tag-button">
                  <button>tag</button>
                  <button>tag</button>
                  <button>tag</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="search-clip">
          <h2>유저 클립</h2>
          <div class="clip-list">
            <div
              class="clip-item"
              v-for="(clip, index) in showAllClips ? Clips : Clips.slice(0, 2)"
              :key="index"
            >
              <div class="clip-thumbnail"></div>
              <div class="clip-info">
                <h3>{{ clip.title }}</h3>
                <p>조회수: {{ clip.viewers }} 회</p>
                <p>{{ clip.likes }} 좋아요</p>
                <p>작성자: {{ clip.name }}</p>
              </div>
            </div>
            <div v-if="!showAllClips" class="more-container">
              <button class="more-button" @click="loadMoreClips">
                더 보기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.search-header {
  margin: 20px 0;
}
.search-content {
  display: grid;
  gap: 20px;
  border: 2px solid black;
  padding: 10px;
}
.search-channel,
.search-streaming,
.search-clip {
  margin-bottom: 20px;
}
.search-channel-list,
.streaming-list,
.clip-list {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  padding: 10px;
}
.channel-list,
.streaming-list,
.clip-list {
  border: 2px solid black;
  padding: 10px;
}
.channel-detail,
.search-result,
.clip-item {
  display: grid;
  grid-template-columns: 3fr 7fr 2fr;
  gap: 10px;
  border: 2px solid black;
  padding: 10px;
  margin-bottom: 10px;
}
.channel-img,
.streaming-thumbnail,
.clip-thumbnail {
  border: 2px dotted black;
  width: 100%;
  height: 0;
  padding-bottom: 100%;
  position: relative;
}
.name-follow,
.streaming-info,
.clip-info {
  padding: 10px;
}
.follow-button {
  display: flex;
  align-items: center;
}
.more-container {
  display: flex;
  align-items: center;
  justify-content: center;
  grid-column: 1 / -1;
}
.more-container::before,
.more-container::after {
  content: "";
  flex: 1;
  height: 1px;
  background: black;
  margin: 0 20px;
}
.more-button {
  padding: 5px 10px;
  border: none;
  background-color: #f0f0f0;
  cursor: pointer;
}
h2 {
  margin-bottom: 10px;
}
</style>
