import { ref } from "vue";
import { defineStore } from "pinia";

export const useStudioStore = defineStore(
  "studioStore",
  () => {
    const studioInfo = ref({});

    return { studioInfo };
  },
  {
    persist: true,
  }
);
