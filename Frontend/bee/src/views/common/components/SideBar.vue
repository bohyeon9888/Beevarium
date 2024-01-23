<script setup>
import { ref } from "vue";
import { useAuthStore } from "@/stores/user";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";

const authStore = useAuthStore();
const { isLoggedIn } = storeToRefs(authStore);

const sidebarStore = useSidebarStore();
const { isExpanded } = storeToRefs(sidebarStore);

const follows = ref([
  {
    streamer_image: "streamer_image_1",
    streamer_name: "Streamer1",
    watcher: 634,
  },
  {
    streamer_image: "streamer_image_2",
    streamer_name: "Streamer2",
    watcher: 226,
  },
]);
const recommends = ref([{}, {}]);

const getImageUrl = (index) => {
  return new URL(`/src/assets/img/${index}.png`, import.meta.url).href;
};
</script>

<template>
  <div
    id="sidebar-container"
    class="sidebar-container"
    :class="{ expanded: isExpanded }"
  >
    <div v-if="isLoggedIn" class="follow-channel">
      <img
        v-if="isExpanded"
        class="sidebar-channel-text"
        src="../../../assets/img/follow_channel_text.png"
        alt=""
      />
      <img
        v-if="!isExpanded"
        class="sidebar-channel-image"
        src="../../../assets/img/follow_channel_image.png"
        alt=""
      />
      <ul class="follow-list">
        <li class="follow" v-for="(follow, index) in follows">
          <img
            class="streamer-image"
            :src="getImageUrl(follow.streamer_image)"
            alt=""
          />
          <div v-if="isExpanded" class="streamer-name">
            {{ follow.streamer_name }}
          </div>
          <div v-if="isExpanded" class="watcher">{{ follow.watcher }}</div>
        </li>
      </ul>
    </div>
    <div class="recommend-channel">
      <img
        v-if="isExpanded"
        class="sidebar-channel-text"
        src="../../../assets/img/recommend_channel_text.png"
        alt=""
      />
      <img
        v-if="!isExpanded"
        class="sidebar-channel-image"
        src="../../../assets/img/recommend_channel_image.png"
        alt=""
      />
    </div>
  </div>
</template>

<style scoped>
.sidebar-container {
  position: fixed;
  top: 80px;
  width: 50px;
  height: 100%;
}

#sidebar-container.expanded {
  width: 220px;
}
.expand-arrow {
  width: 25px;
  cursor: pointer;
}
.follow-title {
  padding: 10px;
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
.follow {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  margin: 0 0 5px;
}
.streamer-image {
  width: 36px;
  height: 36px;
}
.recommend-title {
  padding: 10px;
}
</style>
