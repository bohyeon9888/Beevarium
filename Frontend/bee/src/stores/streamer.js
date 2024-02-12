import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useStreamerStore = defineStore(
  "streamerStore",
  () => {
    const streamer = ref({
      id: "",
      name: "",
    });

    const selectStreamer = (streamerName, streamerId) => {
      streamer.value.id = streamerId;
      streamer.value.name = streamerName;
    }

    return { streamer, selectStreamer };
  },
  {
    persist: {
      storage: sessionStorage,
    },
  }
);
