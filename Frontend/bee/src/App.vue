<script setup>
import { computed } from "vue";
import { RouterView, useRoute } from "vue-router";
import Navbar from "./views/common/components/Navbar.vue";
import SideBar from "./views/common/components/SideBar.vue";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";

const route = useRoute();

// navbar가 안보여야 하는 컴포넌트
const invisiblePaths_navbar = [
  "/user/login",
  "/user/signup",
  "/admin/monitoring",
  "/admin/warning-user",
  "/admin/blacklist",
];

// sidebar가 안보여야 하는 컴포넌트
const invisiblePaths_sidebar = [
  "/studio/banlist-management",
  "/studio/replay-detail",
  "/studio/replay",
  "/studio/studio-main",
  "/studio/clip",
  "/studio/clip-detail",
  "/studio/notice",
  "/studio/notice-detail",
  "/user/login",
  "/user/signup",
  "/streaming/dashboard",
  "/admin/monitoring",
  "/admin/warning-user",
  "/admin/blacklist",
];

// navbar, sidebar 표시 관련 함수
const isInvisible_n = computed(() => invisiblePaths_navbar.includes(route.path));
const isInvisible_s = computed(() => invisiblePaths_sidebar.includes(route.path));

// sidebar 확장 관련 함수
const sidebarStore = useSidebarStore();
const { isExpanded } = storeToRefs(sidebarStore);
</script>

<template>
  <div>
    <Navbar v-if="!isInvisible_n" />
  </div>
  <SideBar v-if="!isInvisible_s" />
  <div
    id="router-view"
    :class="{
      expanded: isExpanded,
      invisible_s: isInvisible_s,
      invisible_n: isInvisible_n,
    }"
  >
    <RouterView />
  </div>
</template>

<style scoped>
#router-view {
  display: flex;
  justify-content: center;
  padding-top: 60px;
  padding-left: 80px;
}
#router-view.expanded {
  padding-left: 270px;
}
#router-view.invisible_s {
  padding-left: 0;
}
#router-view.invisible_n {
  padding-top: 0;
}
</style>
