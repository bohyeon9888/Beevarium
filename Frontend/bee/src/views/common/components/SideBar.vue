<script setup>
import { ref, onMounted } from "vue";
import { useAuthStore } from "@/stores/user";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";
import { followChannel } from "@/api/navbar";

const authStore = useAuthStore();
const { isLoggedIn, accessToken } = storeToRefs(authStore);

const sidebarStore = useSidebarStore();
const { isExpanded } = storeToRefs(sidebarStore);

const expand = () => {
  sidebarStore.expand();
};

const follows = ref([
  {
    streamer_image: "streamer_image_1",
    streamer_name: "스트리머1",
    watcher: 634,
  },
  {
    streamer_image: "streamer_image_2",
    streamer_name: "스트리머2",
    watcher: 226,
  },
]);

const getImageUrl = (index) => {
  return new URL(`/src/assets/img/${index}.png`, import.meta.url).href;
};

const followChannels = ref([]);
const getFollowChannel = () => {
  followChannel(accessToken.value,
  ({data}) => {
    followChannels.value = data.data.subscribeList;
    console.log(followChannels.value);
  },
  (error) => {

  });
}

onMounted(() => {
  getFollowChannel();
});
</script>

<template>
  <div
    id="sidebar-container"
    class="sidebar-container"
    :class="{ expanded: isExpanded }"
  >
    <div class="expand-button" @click="expand">
      <img v-if="!isExpanded" src="../../../assets/img/common/expand-on.png" alt="">
      <img v-if="isExpanded" src="../../../assets/img/common/expand-off.png" alt="">
    </div>
    <div v-if="isLoggedIn" class="follow-channel">
      <div
        v-if="isExpanded"
        style="
          margin-top: 16px;
          margin-left: 22px;
          font-size: 14px;
          font-weight: 600;
        "
      >
        팔로우 채널
      </div>
      <div
        v-if="!isExpanded"
        style="
          margin-top: 16px;
          margin-left: 22px;
          width: 37px;
          font-size: 14px;
          font-weight: 600;
        "
      >
        팔로우
      </div>
      <ul class="follow-list">
        <li class="follow" v-for="(follow, index) in followChannels">
          <div
            style="
              width: 33px;
              height: 33px;
              border-radius: 10rem;
              border: 2px solid #ffec3e;
            "
          >
            <img
              class="streamer-image"
              :src="follow.profileUrl"
              alt=""
            />
            

          </div>
          <div v-if="isExpanded" class="streamer-name">
            {{ follow.streamerName }}
          </div>
          <div style="position: absolute; display: flex; right: 30px; width: 70px; height:">
            <div
              v-if="isExpanded"
              style="margin-right: 4px; display: flex; align-items: center"
            >
              <img src="../../../assets/img/live.png" alt="" />
            </div>
            <div v-if="isExpanded" class="watcher">{{ follow.viewer }}</div>
          </div>
        </li>
      </ul>
    </div>
    <div class="recommend-channel">
      <div
        v-if="isExpanded"
        style="
          margin-top: 16px;
          margin-left: 22px;
          font-size: 14px;
          font-weight: 600;
        "
      >
        추천 채널
      </div>
      <div
        v-if="!isExpanded"
        style="
          margin-top: 16px;
          margin-left: 27.5px;
          width: 25px;
          font-size: 14px;
          font-weight: 600;
        "
      >
        추천
      </div>
    </div>
  </div>
</template>

<style scoped>
.sidebar-container {
  position: fixed;
  top: 60px;
  width: 80px;
  height: 100%;
}
.expand-button {
  margin: 20px 31px 24px;
  cursor: pointer;
}
.recommend-channel {
  margin-top: 16px;
}
#sidebar-container.expanded {
  width: 270px;
}
.sidebar-channel-text {
  height: 18px;
  margin: 14px 16px 10px;
}
.sidebar-channel-image {
  width: 18px;
  height: 18px;
  margin: 14px 16px 10px;
}
.follow-list {
  margin-top: 16px;
  margin-left: 24px;
}
.follow {
  position: relative;
  display: flex;
  align-items: center;
  margin: 0 0 12px;
}
.streamer-image {
  width: 29px;
  height: 29px;
  border-radius: 10rem;
  object-fit: cover;
}
.streamer-name {
  width: 100px;
  margin: 0 0 0 7px;
  font-size: 14px;
  font-weight: 600;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.watcher {
  font-size: 12px;
  font-weight: 600;
}
</style>
