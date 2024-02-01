import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore(
  "authStore",
  () => {
    const isLoggedIn = ref(false);
    const coperation = ref("");

    const login = (userData) => {
      isLoggedIn.value = true;
    };

    const logout = () => {
      isLoggedIn.value = false;
    };

    const chooseCoperation = (index) => {
      coperation.value = index;
    }

    return { isLoggedIn, coperation, login, logout, chooseCoperation };
  },
  {
    persist: {
      storage: sessionStorage,
    },
  }
);
