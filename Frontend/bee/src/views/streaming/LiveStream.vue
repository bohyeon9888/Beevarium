<script setup>
import LiveStreamChat from "./components/LiveStreamChat.vue";
import AIScript from "./components/AIScript.vue";
import { ref, onMounted, computed } from "vue";
import { useSidebarStore } from "@/stores/sidebar";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { useOVSStore } from "@/stores/ov_subscriber";
import { streamingEnter } from "@/api/live";
import { useRoute, useRouter } from "vue-router";
import { subscribe, unSubscribe } from "@/api/follow";

const authStore = useAuthStore();
const sidebarStore = useSidebarStore();
const { accessToken } = storeToRefs(authStore);
const ovsStore = useOVSStore();
const { subtitle } = storeToRefs(ovsStore);
const route = useRoute();
const router = useRouter();
const streamerId = ref("");

const isSubOn = ref(false);
const isFilterOn = ref(false);
const subToggle = () => {
  isSubOn.value = !isSubOn.value;
  console.log(isSubOn.value);
};
const filterToggle = () => {
  isFilterOn.value = !isFilterOn.value;
  console.log(isFilterOn.value);
};

// 도네이션 모달 활성화
const donModActive = ref(false);
// 언어 선택 모달 활성화
const lanModActive = ref(false);
// 도네이션 모달 토글
const toggleDonMod = () => {
  donModActive.value = !donModActive.value;
};
// 언어 선택 모달 토글
const toggleLanMod = () => {
  lanModActive.value = !lanModActive.value;
};

const stream = ref({
  title: "라이브 스트리밍 방송 제목 입니다",
  streamerName: "스트리머1",
  liveWatcher: "626",
  tags: ["한국어", "게임", "수다"],
});

const streamInfo = ref({});
const isFollow = computed(() => {
  return streamInfo.value.follow;
});
const doStreamingEnter = () => {
  (streamerId.value = route.params.streamerId),
    streamingEnter(
      accessToken.value,
      streamerId.value,
      ({ data }) => {
        streamInfo.value = data.data;
      },
      (error) => {}
    );
};
const doSubscribe = () => {
  subscribe(
    accessToken.value,
    streamInfo.value.stationNo,
    ({ data }) => {
      streamInfo.value.follow = true;
      console.log(data.msg);
      ovsStore.sendfollow();
    },
    (error) => {}
  );
};

const doUnSubsribe = () => {
  console.log(streamInfo.value.stationNo);
  unSubscribe(
    accessToken.value,
    streamInfo.value.stationNo,
    ({ data }) => {
      streamInfo.value.follow = false;
      console.log(data.msg);
    },
    (error) => {}
  );
};
onMounted(() => {
  doStreamingEnter();
  ovsStore.subscribeToSession();
});
</script>

<template>
  <div id="livestream-container" class="livestream-container">
    <div id="stream-container" class="stream-container">
      <div id="screen-container" class="screen-container">
        <div class="screen" id="subscriber-video">
          <div v-if="isSubOn" class="subtitle">{{ subtitle }}</div>
        </div>
      </div>
      <div id="livestream-info-container" class="livestream-info-container">
        <div id="livestream-info-box" class="livestream-info-box">
          <div id="livestream-title" class="livestream-title">
            {{ streamInfo.title }}
          </div>
          <div id="streamer-info-container" class="streamer-info-container">
            <div class="streamer-logo-container">
              <div class="streamer-logo-box">
                <img
                  :src="streamInfo.streamerProfile"
                  alt=""
                  class="streamer-logo"
                />
              </div>
            </div>
            <div id="streamer-info-box" class="streamer-info-box">
              <div id="streamer-name" class="streamer-name" @click="router.push({path: `/studio/${streamInfo.streamerId}/studio-main`})">
                {{ streamInfo.streamerName }}
              </div>
              <div class="watcher-tag-container">
                <div class="watcher">
                  <img
                    src="../../assets/img/stream/live-watcher.png"
                    alt=""
                    class="live-watcher"
                  />
                  <div style="font-size: 16px; font-weight: 500">
                    현재 {{ streamInfo.viewers }}명 시청 중
                  </div>
                </div>
                <div
                  style="
                    width: 1px;
                    height: 14px;
                    margin: 0 10px;
                    background: #636363;
                  "
                ></div>
                <div class="tag-container">
                  <ul class="tag-list">
                    <li
                      v-for="(tag, index) in streamInfo.tags"
                      :key="index"
                      class="tag"
                    >
                      {{ tag }}
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="livestream-setting-container">
          <div class="translate-filter-container">
            <div
              style="
                height: 24px;
                line-height: 24px;
                font-size: 16px;
                font-weight: 600;
                margin-right: 8px;
              "
            >
              자막
            </div>
            <label class="switch">
              <input type="checkbox" />
              <div class="slider round" @click="subToggle"></div>
            </label>
            <div
              style="
                height: 24px;
                line-height: 24px;
                font-size: 16px;
                font-weight: 600;
                margin-right: 8px;
              "
            >
              필터링
            </div>
            <label class="switch">
              <input type="checkbox" />
              <div class="slider round" @click="filterToggle"></div>
            </label>
          </div>
          <div class="follow-alarm-container">
            <div v-if="isFollow" class="follow-button" @click="doUnSubsribe">
              <img
                src="../../assets/img/stream/follow.png"
                alt=""
                class="follow-image"
              />
              <div style="font-size: 14px; font-weight: 600; color: #121212">
                팔로우
              </div>
            </div>
            <div v-if="!isFollow" class="unfollow-button" @click="doSubscribe">
              <img
                src="../../assets/img/stream/follow.png"
                alt=""
                class="follow-image"
              />
              <div style="font-size: 14px; font-weight: 600; color: #121212">
                팔로우
              </div>
            </div>
            <img
              src="../../assets/img/stream/alarm.png"
              alt=""
              class="alarm-button"
            />
          </div>
        </div>
      </div>
    </div>
    <div class="script-container">
      <AIScript />
    </div>
    <div class="chat-container">
      <LiveStreamChat :streamerId="streamerId" />
    </div>
  </div>
</template>

<style scoped>
.livestream-container {
  display: flex;
  width: 1840px;
  height: 862px;
  overflow-y: hidden;
}
.stream-container {
  width: 1100px;
  height: 862px;
}
.screen-container {
  display: flex;
  justify-content: center;
  width: 1100px;
  height: 619px;
  background-color: black;
}
.screen {
  position: relative;
  width: 1100px;
  height: 619px;
  display: flex;
  justify-content: center;
}
.subtitle {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  bottom: 0;
  width: 100%;
  height: 60px;
  font-size: 18px;
  font-weight: 600;
  background-color: rgba(0, 0, 0, 0.6);
}
.livestream-info-container {
  display: flex;
  width: 1100px;
  height: 142px;
}
.livestream-info-box {
  width: 820px;
  height: 142px;
}
.livestream-title {
  width: 820px;
  height: 48px;
  font-size: 24px;
  font-weight: 600;
  padding: 16px 16px 0;
}
.streamer-info-container {
  display: flex;
  width: 820px;
  height: 86px;
}
.streamer-logo-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 86px;
  height: 86px;
  padding: 10px;
}
.streamer-logo-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 68px;
  height: 68px;
  border: 2px solid #ffec3e;
  border-radius: 10rem;
}
.streamer-logo {
  width: 60px;
  height: 60px;
  border-radius: 10rem;
}
.streamer-info-box {
  width: 914px;
  height: 86px;
}
.streamer-name {
  width: 914px;
  height: 43px;
  font-size: 20px;
  font-weight: 600;
  padding: 14px 0 0 8px;
}
.watcher-tag-container {
  display: flex;
  align-items: center;
}
.watcher {
  display: flex;
  align-items: center;
  height: 43px;
  padding-left: 8px;
}
.live-watcher {
  width: 8px;
  height: 8px;
  margin-right: 4px;
}
.tag-list {
  display: flex;
}
.tag {
  height: 20px;
  background-color: #434343;
  border-radius: 10rem;
  padding: 3px 8px;
  font-size: 12px;
  font-weight: 500;
  margin-right: 6px;
}
.translate-filter-container {
  display: flex;
  align-items: center;
  width: 210px;
  height: 55px;
  margin: 16px 0 0 69px;
}
.follow-alarm-container {
  display: flex;
  height: 33px;
}
.follow-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 87px;
  height: 33px;
  background-color: #ffec3e;
  border-radius: 10rem;
  margin: 0 6px 0 139px;
  cursor: pointer;
}
.unfollow-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 87px;
  height: 33px;
  background-color: #e6e5ea;
  border-radius: 10rem;
  margin: 0 6px 0 139px;
  cursor: pointer;
}
.follow-image {
  width: 14px;
  height: 12px;
  margin-right: 4px;
}
.alarm-button {
  width: 33px;
  height: 33px;
  cursor: pointer;
}
.script-container {
  width: 370px;
  height: 862px;
}
.chat-container {
  width: 370px;
  height: 862px;
}
.switch {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 28px;
  margin-right: 8px;
}
.switch input {
  display: none;
}
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #6f6f6f;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}
.slider:before {
  position: absolute;
  content: "";
  height: 20px;
  width: 20px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}
input:checked + .slider {
  background-color: #e89900;
}
input:checked + .slider:before {
  -webkit-transform: translateX(22px);
  -ms-transform: translateX(22px);
  transform: translateX(22px);
}
.slider.round {
  border-radius: 10rem;
}
.slider.round:before {
  border-radius: 10rem;
}
</style>
