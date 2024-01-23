import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore(
  "authStore",
  () => {
    const isLoggedIn = ref(false);

    const login = (userData) => {
      isLoggedIn.value = true;
    };

    const logout = () => {
      isLoggedIn.value = false;
    };

    return { isLoggedIn, login, logout };
  },
  {
    persist: {
      storage: sessionStorage,
    },
  }
);
