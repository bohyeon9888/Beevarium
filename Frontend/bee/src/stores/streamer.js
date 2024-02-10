import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useStreamerStore = defineStore(
  "streamerStore",
  () => {
    const streamer = ref({
      id: "",
      name: "",
    });
    return { streamer };
  },
  {
    persist: {
      storage: sessionStorage,
    },
  }
);
