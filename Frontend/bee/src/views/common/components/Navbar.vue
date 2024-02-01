<script setup>
import { ref, onMounted, onBeforeUnmount, computed } from "vue";
import { RouterLink, useRouter } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { useSidebarStore } from "@/stores/sidebar";
import { storeToRefs } from "pinia";
import MypageModal from "@/views/streaming/components/MypageModal.vue";

const authStore = useAuthStore();
const { isLoggedIn } = storeToRefs(authStore);
const sidebarStore = useSidebarStore();
const router = useRouter();
const searchQuery = ref("");
const mypageModActive = ref(false);

const onSearch = (event) => {
  if (event.key === "Enter") {
    router.push({ path: `/search/${searchQuery.value}` });
    searchQuery.value = "";
  }
};

const expand = () => {
  sidebarStore.expand();
};

const logout = () => {
  authStore.logout();
  mypageModActive.value = !mypageModActive.value;
  router.push("/");
};

const isScrolled = ref(false);
const checkScrolled = () => {
  isScrolled.value = window.scrollY > 0;
};
onMounted(() => {
  window.addEventListener("scroll", checkScrolled);
});
onBeforeUnmount(() => {
  window.removeEventListener("scroll", checkScrolled);
});

const toggleMpMod = () => {
  mypageModActive.value = !mypageModActive.value;
};
</script>

<template>
  <div class="navbar-container">
    <div style="display: flex">
      <div class="hamburger-menu-button" @click="expand">
        <img
          class="hamburger-menu"
          src="../../../assets/img/navbar/hamburger-menu.png"
          alt=""
        />
      </div>
      <div class="navbar-logo-box">
        <router-link :to="{ name: 'Home' }">
          <img
            class="navbar-logo"
            src="../../../assets/img/navbar/navbar-logo.png"
            alt=""
          />
        </router-link>
      </div>
    </div>
    <div class="searchbar-box">
      <input
        class="searchbar-input"
        type="text"
        v-model="searchQuery"
        @keyup="onSearch"
        placeholder="스트리머, 게임 영상 검색"
      />
      <img
        class="search-button"
        src="../../../assets/img/navbar/search.png"
        alt=""
      />
    </div>
    <div class="navbar-menu">
      <router-link
        :to="{ name: 'Dashboard' }"
        style="
          display: flex;
          justify-content: center;
          align-items: center;
          width: 97px;
          height: 33px;
          font-size: 14px;
          color: #e6e5ea;
          font-weight: 600;
          margin-right: 30px;
        "
      >
        <div v-if="isLoggedIn" class="broadcast-button">
          <img
            class="broadcast"
            src="../../../assets/img/navbar/broadcast.png"
            alt=""
          />
          방송하기
        </div>
      </router-link>
      <div v-if="isLoggedIn" style="width: 52px; margin-right: 30px;">
        <router-link
          :to="{ name: 'StudioMain' }"
          style="
            width: 52px;
            height: 17px;
            color: #e6e5ea;
            font-size: 14px;
            font-weight: 600;
          "
          >나의 채널</router-link
        >
      </div>
      <div v-if="isLoggedIn" class="profile-button" @click="toggleMpMod">
        <img class="profile" src="../../../assets/img/profile.png" alt="" />
      </div>
      <div v-if="!isLoggedIn" class="move-to-login">
        <router-link :to="{ name: 'Login' }" style="color: #e6e5ea"
          >로그인</router-link
        >
      </div>
    </div>
  </div>
</template>

<style scoped>
.navbar-container {
  position: fixed;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 60px;
  z-index: 2;
  background-color: #121212;
}
.hamburger-menu-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 18px;
  height: 14px;
  margin: 24px;
  cursor: pointer;
}
.hamburger-menu {
  width: 18px;
  height: 14px;
}
.navbar-logo-box {
  width: 162px;
  height: 38px;
  margin: 11px 0;
}
.searchbar-box {
  display: flex;
  align-items: center;
  width: 400px;
  height: 40px;
  border: 1px solid #4f4f4f;
  border-radius: 10rem;
  margin-right: 470px;
  margin-left: 532px;
}
.searchbar-input {
  width: 368px;
  height: 40px;
  outline: none;
  border: 0;
  border-radius: 10rem;
  background-color: transparent;
  padding-left: 20px;
  color: white;
  font-size: 14px;
  font-weight: 400;
}
.search-button {
  width: 16px;
  height: 16px;
  margin: 12px 16px 12px 0;
  cursor: pointer;
}
.navbar-menu {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 269px;
  height: 60px;
}
.broadcast-button {
  display: flex;
  align-items: center;
  width: 97px;
  height: 33px;
  background-color: #404040;
  border-radius: 8px;
  padding: 12px;
  cursor: pointer;
}
.broadcast {
  width: 18px;
  height: 12px;
  margin-right: 6px;
}
.profile-button {
  width: 55.5px;
  height: 36px;
  border-radius: 10rem;
  cursor: pointer;
}
.profile {
  width: 36px;
  height: 36px;
  border-radius: 10rem;
  object-fit: cover;
}
.move-to-login {
  margin: 0 30px 0 24px;
}
</style>
