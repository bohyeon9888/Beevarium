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
const mypageModActive = ref(false)

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
  mypageModActive.value = !mypageModActive.value
  router.push('/')
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

const toggleMpMod = ()=>{
  mypageModActive.value = !mypageModActive.value
}


</script>

<template>
  <div
    id="navbar-container"
    class="navbar-container"
    :class="{ scrolled: isScrolled }"
  >
    <div class="hamburger-menu-box" @click="expand">
      <img
        class="hamburger-menu-image"
        src="../../../assets/img/hamburger-menu.png"
        alt=""
      />
    </div>
    <div class="logo-box">
      <router-link :to="{ name: 'Home' }">
        <img class="logo-image" src="../../../assets/img/logo.png" alt=""
      /></router-link>
    </div>
    <div class="searchbar-box">
      <div class="searchbar-wrapper">
        <input
          id="searchbar-input"
          class="searchbar-input"
          :class="{ scrolled: isScrolled }"
          type="text"
          v-model="searchQuery"
          @keyup="onSearch"
          placeholder="검색어를 입력하세요."
        />
      </div>
    </div>
    <ul class="navbar-menu">
      <!-- <li class="move-to-dashboard">
        <router-link :to="{ name: 'Dashboard' }">방송하기</router-link>
      </li>
      <li class="move-to-my-channel">
        <router-link :to="{ name: 'StudioMain' }">나의 채널</router-link>
      </li> -->
      <li v-if="isLoggedIn"> user 님 안녕하세요 </li>
      <li v-if="!isLoggedIn" class="move-to-login">
        <router-link :to="{ name: 'Login' }">로그인</router-link>
      </li>
      <!-- <li v-if="isLoggedIn">
        <router-link :to="{ name: 'Account' }">마이페이지</router-link>
      </li>
      <li v-if="isLoggedIn">
        <router-link :to="{ name: 'Home' }" @click="toggleMpMod"
          >로그아웃</router-link
        >
      </li> -->
      <li v-if="isLoggedIn">
        <img src="@/assets/img/streamer_image_1.png" 
        class="navbar-profile"
        @click="toggleMpMod">
      </li>
    </ul>

    <MypageModal  @callLogout="logout" @hidemodal="toggleMpMod" :mypageModActive="mypageModActive"
     class="mypage-modal">
    </MypageModal>

  </div>
</template>

<style scoped>
.navbar-container {
  position: fixed;
  display: flex;
  width: 100%;
  height: 80px;
  justify-content: space-evenly;
  z-index: 2;
  transition: 0.3s;
}
#navbar-container.scrolled {
  transition: 0.3s;
  background-color: #ffe3bc;
}
.hamburger-menu-box {
  display: flex;
  align-items: center;
  cursor: pointer;
}
.hamburger-menu-image {
  margin: 0 10px;
  width: 30px;
  height: 30px;
  cursor: pointer;
}
.logo-box {
  width: 100%;
  height: 80px;
  /* background-color: red; */
  align-items: center;
  display: flex;
  justify-content: flex-start;
}
.logo-image {
  display: flex;
  align-items: center;
  height: 60px;
}
.searchbar-box {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: coral; */
}
.searchbar-wrapper {
  display: flex;
  /* background-color: aquamarine; */
  width: 400px;
  height: 40px;
  border: 1px solid black;
  border-radius: 2rem;
}
.searchbar-input {
  background-color: transparent;
  display: flex;
  width: 320px;
  outline: none;
  border: 0;
  margin-left: 20px;
  font-size: 17px;
  transition: 0.3s;
}
#searchbar-input.scrolled {
  transition: 0.3s;
}
.navbar-menu {
  width: 100%;
  display: flex;
  /* background-color: orange; */
  justify-content: flex-end;
  align-items: center;
}

.navbar-menu > li {
  margin: 0 20px 0 0;
  font-size: 17px;
}

.navbar-profile{
  width: 30px;
  cursor: pointer;
  position: relative;
}

.mypage-modal{
  position: absolute;
  right: 0;
  top: 80px;

}
</style>
