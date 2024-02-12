<script setup>
import { ref, onMounted } from "vue";
import StudioInfo from "./components/StudioInfo.vue";
import { studio } from "@/api/studio";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { RouterView, useRoute } from "vue-router";

const authStore = useAuthStore();
const { accessToken } = storeToRefs(authStore);
const route = useRoute();


const getReplayUrl = (name) => {
  return new URL(`/src/assets/img/studio/${name}.png`, import.meta.url).href;
};

const studioInfo = ref({});

const getStudio = () => {
  studio(
    accessToken.value,
    route.params.streamerId,
    ({ data }) => {
      studioInfo.value = data.data.stationInfo;
      console.log(studioInfo.value);
    },
    (error) => {}
  );
};

onMounted(() => {
  getStudio();
});
</script>

<template>
  <div class="studiomain-container">
    <div class="studio-info">
      <StudioInfo :studioInfo="studioInfo" />
    </div>
    <RouterView :studioInfo="studioInfo" />
  </div>
</template>

<style scoped>
.studiomain-container {
  display: flex;
  width: 1899px;
}
.studio-info {
  width: 320px;
}
</style>
