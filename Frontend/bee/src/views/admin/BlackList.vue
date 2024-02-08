<script setup>
import AdminNavbar from "./components/AdminNavbar.vue";
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="admin-page">
        <AdminNavbar />
        <h1 class="page-title">영구 정지 유저</h1>
        <div class="page-content"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.admin-page {
  font-family: Arial, sans-serif;
  background-color: #f0f0f0;
  padding: 20px;
  margin-top: 50px;
}

.page-title {
  font-size: 24px;
  margin: 10px 0;
}

.page-content {
  background-color: #fff;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
}
</style>
