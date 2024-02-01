<script setup>
import { computed } from "vue";
import { RouterView, useRoute } from "vue-router";
import Navbar from "./views/common/components/Navbar.vue";
import SideBar from "./views/common/components/SideBar.vue";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";

const route = useRoute();
const invisiblePaths_navbar = [
  "/user/login",
  "/user/signup",
  "/admin/monitoring",
  "/admin/warning-user",
  "/admin/blacklist",
];

const invisiblePaths_sidebar = [
  "/user/login",
  "/user/signup",
  "/streaming/dashboard",
  "/admin/monitoring",
  "/admin/warning-user",
  "/admin/blacklist",
];

const isInvisible_n = computed(() =>
  invisiblePaths_navbar.includes(route.path)
);
const isInvisible_s = computed(() =>
  invisiblePaths_sidebar.includes(route.path)
);

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
    :class="{ expanded: isExpanded, invisible: isInvisible_s }"
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
#router-view.invisible {
  padding: 0;
}
</style>
