<script setup>
import { ref, onMounted, onBeforeUnmount, computed } from "vue";
import { RouterLink, useRouter } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import MypageModal from "./MypageModal.vue";

const authStore = useAuthStore();
const { isLoggedIn, user } = storeToRefs(authStore);
const router = useRouter();
const searchQuery = ref("");
const mypageModActive = ref(false);

const onSearch = (event) => {
  if (event.key === "Enter") {
    router.push({ path: `/search/${searchQuery.value}` });
    searchQuery.value = "";
  }
};

const logout = () => {
  authStore.logout();
  router.push("/");
};

const modalRef = ref(null);
// 모달 열기, 닫기 토글
const toggleMpMod = () => {
  event.stopPropagation();
  mypageModActive.value = !mypageModActive.value;
};
const handleOutsideClick = (event) => {
  if (mypageModActive.value && !modalRef.value.contains(event.target)) {
    mypageModActive.value = false;
  }
};

// const getProfileImageUrl = (name) => {
//   if (name == "none") return new URL(`/src/assets/img/mypage/profile-image.png`, import.meta.url).href;
//   else return name;
//   console.log(name);
// };

onMounted(() => {
  document.addEventListener("click", handleOutsideClick);
});
onBeforeUnmount(() => {
  document.removeEventListener("click", handleOutsideClick);
});
</script>

<template>
  <div class="navbar-container">
    <div style="display: flex">
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
          margin-right: 24px;
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
      <div v-if="isLoggedIn" class="profile-button" @click="toggleMpMod($event)">
        <img class="profile" :src="user.logo" alt="" />
      </div>
      <div v-if="!isLoggedIn" class="move-to-login">
        <router-link :to="{ name: 'Login' }" style="color: #e6e5ea"
        >로그인</router-link
        >
      </div>
    </div>
    <div v-if="mypageModActive" style="position: absolute; top: 60px; right: 139.5px;" ref="modalRef">
      <MypageModal :mypage-mod-active="mypageModActive" @log-out="logout" @toggle-mp-mod="toggleMpMod"/>
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
  z-index: 100;
  background-color: #121212;
}
.navbar-logo-box {
  width: 162px;
  height: 38px;
  margin-left: 24px;
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
  width: 217px;
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
  position: relative;
  width: 36px;
  height: 36px;
  border-radius: 10rem;
  margin-right: 19.5px;
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
