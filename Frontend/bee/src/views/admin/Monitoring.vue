<script setup>
import AdminNavbar from "./components/AdminNavbar.vue";
import StreamingList from "./components/StreamingList.vue";
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="monitoring-page">
        <AdminNavbar />
        <h1 class="page-title">실시간 모니터링</h1>
        <div class="streaming-lists">
          <StreamingList
            class="streaming-lists-info"
            title="현재 스트리밍 중인 방송"
          />
          <div class="monitoring-screen"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.monitoring-page {
  font-family: Arial, sans-serif;
  background-color: #f0f0f0;
  padding: 20px;
  margin-top: 50px;
}

.page-title {
  font-size: 24px;
  margin: 10px 0;
}

.streaming-lists {
  background-color: #fff;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  display: flex;
}

.streaming-lists-info {
  width: 40%;
}

.monitoring-screen {
  background-color: #fff;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  margin: 10px;
  width: 60%;
}
</style>
