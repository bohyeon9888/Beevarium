import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore(
  "authStore",
  () => {
    const isLoggedIn = ref(false);
    const accessToken = ref("");
    const coperation = ref("");
    const user = ref({
      name: "",
      id: "",
      logo: "",
    });

    const login = (userData, username) => {
      isLoggedIn.value = true;
      accessToken.value = userData.token;
      user.value.name = userData.nickname;
      user.value.id = username;
      user.value.logo = userData.profile_img_url;
    };

    const logout = () => {
      isLoggedIn.value = false;
      accessToken.value = null;
      user.value = null;
    };

    const chooseCoperation = (index) => {
      coperation.value = index;
    }

    return { isLoggedIn, coperation, accessToken, user, login, logout, chooseCoperation };
  },
  {
    persist: {
      storage: sessionStorage,
    },
  }
);
