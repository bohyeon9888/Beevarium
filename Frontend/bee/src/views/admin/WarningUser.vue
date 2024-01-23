<script setup>
import AdminNavbar from "./components/AdminNavbar.vue";
import StreamingList from "./components/StreamingList.vue";
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="warning-user-page">
        <AdminNavbar />
        <h1 class="page-title">경고 유저 목록</h1>
        <div class="streaming-list">
          <StreamingList title="현재 스트리밍 중인 방송" />
          <StreamingList title="스트리밍 중이 아닌 방송" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.warning-user-page {
  font-family: Arial, sans-serif;
  background-color: #f0f0f0;
  padding: 20px;
  margin-top: 50px;
}

.page-title {
  font-size: 24px;
  margin: 10px 0;
}
.streaming-list {
  background-color: #fff;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  display: flex;
}
</style>
