<script setup>
import { ref } from "vue";

const searchQuery = ref("");
function searchBlacklistedUser() {
  // 검색 로직 구현
  // 예: API 호출을 통해 영구정지 유저 정보 조회
  console.log(`Searching for: ${searchQuery.value}`);
}
</script>

<template>
  <div class="blacklist-page">
    <div class="page-title">영구 정지 유저 조회</div>
    <div class="page-content">
      <form @submit.prevent="searchBlacklistedUser">
        <input
          v-model="searchQuery"
          type="text"
          placeholder="유저 이름 입력"
          class="search-input"
        />
        <button type="submit" class="search-button">검색</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.blacklist-page {
  padding: 20px 0;
  color: #fff;
}

.page-title {
  font-size: 20px;
  margin-bottom: 20px;
}

.page-content {
  background-color: #323232;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  color: #000;
}

.search-input {
  padding: 10px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-button {
  padding: 10px 20px;
  background-color: #ffec3e;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-button:hover {
  background-color: #ffec4e;
}
</style>
