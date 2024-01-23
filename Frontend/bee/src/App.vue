<script setup>
import { computed } from "vue";
import { RouterView, useRoute } from "vue-router";
import Navbar from "./views/common/components/Navbar.vue";
import SideBar from "./views/common/components/SideBar.vue";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";

const route = useRoute();
const invisiblePaths = [
  "/user/login",
  "/user/signup",
  "/streaming/dashboard",
  "/admin/monitoring",
  "/admin/warning-user",
  "/admin/blacklist",
];
const isInvisible = computed(() => invisiblePaths.includes(route.path));
const sidebarStore = useSidebarStore();
const { isExpanded } = storeToRefs(sidebarStore);
</script>

<template>
  <Navbar v-if="!isInvisible" />
  <SideBar v-if="!isInvisible" />
  <div id="router-view" :class="{ expanded: isExpanded, invisible: isInvisible}">
    <RouterView />
  </div>
</template>

<style scoped>
#router-view {
  padding-top: 80px;
  padding-left: 50px;
}
#router-view.expanded {
  padding-left: 220px;
}
#router-view.invisible {
  padding: 0;
}
</style>
