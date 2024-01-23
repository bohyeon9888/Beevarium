<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="admin-navbar">
    <h2>관리자 페이지</h2>
    <div class="nav-links">
      <RouterLink :to="{ name: 'Monitoring' }">
        <strong> 실시간 모니터링 </strong>
      </RouterLink>

      <RouterLink :to="{ name: 'WarningUser' }">
        <strong> 경고 유저 목록 </strong>
      </RouterLink>

      <RouterLink :to="{ name: 'BlackList' }">
        <strong> 영구 정지 유저 목록 </strong>
      </RouterLink>
    </div>
  </div>
</template>

<style scoped>
.admin-navbar {
  background-color: #333;
  color: #fff;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.admin-navbar h2 {
  font-size: 20px;
  margin: 0;
}

.nav-links {
  display: flex;
  gap: 20px;
}

.nav-links a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
}

.nav-links a:hover {
  text-decoration: underline;
}
</style>
