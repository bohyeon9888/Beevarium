import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useSidebarStore = defineStore(
  "sidebarStore",
  () => {
    const isExpanded = ref(false);

    const expand = () => {
      isExpanded.value = !isExpanded.value;
    }

    return { isExpanded, expand };
  },
  {
    persist: {
      storage: sessionStorage,
    },
  }
);
