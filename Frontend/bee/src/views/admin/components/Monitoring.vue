<script setup>
import StreamingList from "./StreamingList.vue";
import { ref } from "vue";

const streamingData = ref([
  { name: "스트리머1", viewers: 1200 },
  { name: "스트리머2", viewers: 3000 },
  { name: "스트리머3", viewers: 3000 },
  { name: "스트리머4", viewers: 3000 },
  { name: "스트리머5", viewers: 3000 },
  { name: "스트리머6", viewers: 3000 },
  { name: "스트리머1", viewers: 1200 },
  { name: "스트리머2", viewers: 3000 },
  { name: "스트리머3", viewers: 3000 },
  { name: "스트리머4", viewers: 3000 },
  { name: "스트리머5", viewers: 3000 },
  { name: "스트리머6", viewers: 3000 },
  { name: "스트리머1", viewers: 1200 },
  { name: "스트리머2", viewers: 3000 },
  { name: "스트리머3", viewers: 3000 },
  { name: "스트리머4", viewers: 3000 },
  { name: "스트리머5", viewers: 3000 },
  { name: "스트리머6", viewers: 3000 },
]);
</script>

<template>
  <div class="monitoring-page">
    <div class="streaming-list">
      현재 스트리밍 중인 방송
      <div class="streaming-lists-info">
        <StreamingList
          v-for="stream in streamingData"
          :key="stream.name"
          :stream="stream"
        />
      </div>
    </div>
    <div class="monitoring">
      실시간 모니터링
      <div class="monitoring-screen">
        <div class="streaming-info">
          <div class="screen"></div>
          <div class="info"></div>
        </div>
        <div class="chat"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.monitoring-page {
  display: grid;
  grid-template-columns: 3fr 7fr;
  margin-top: 30px;
}

.streaming-lists-info {
  width: 100%;
  background-color: #323232;
  border-radius: 10px;
  padding: 20px;
  margin: 20px 0;
  height: 600px;
  overflow-y: scroll;
}
.streaming-list {
  margin-right: 30px;
}

.monitoring-screen {
  background-color: #323232;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  margin: 20px 0;
  width: 100%;
  height: 600px;
  display: grid;
  grid-template-columns: 7fr 3fr;
  gap: 3px;
}
.streaming-info {
  height: 100%;
  display: grid;
  grid-template-rows: 7fr 3fr;
  gap: 3px;
}
.screen {
  border: 1px solid #121212;
  height: 100%;
}
.chat {
  border: 1px solid #121212;
  height: 100%;
}
.info {
  border: 1px solid #121212;
}
</style>
