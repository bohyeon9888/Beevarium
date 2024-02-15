import { ref } from "vue";
import { defineStore } from "pinia";

export const useEffectStore = defineStore(
  "effectStore",
  () => {
    const activeOption = ref(1);

    return { activeOption };
  },
  {
    persist: true,
  }
);
