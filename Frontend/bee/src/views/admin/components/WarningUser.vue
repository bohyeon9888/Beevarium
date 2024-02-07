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

const NonestreamingData = ref([
  { name: "스트리머1" },
  { name: "스트리머2" },
  { name: "스트리머3" },
  { name: "스트리머4" },
  { name: "스트리머5" },
  { name: "스트리머6" },
  { name: "스트리머1" },
  { name: "스트리머2" },
  { name: "스트리머3" },
  { name: "스트리머4" },
  { name: "스트리머5" },
  { name: "스트리머6" },
  { name: "스트리머1" },
  { name: "스트리머2" },
  { name: "스트리머3" },
  { name: "스트리머4" },
  { name: "스트리머5" },
  { name: "스트리머6" },
]);
</script>

<template>
  <div class="warning-user-page">
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
    <div class="streaming-list">
      스트리밍 중이 아닌 방송
      <div class="streaming-lists-info">
        <StreamingList
          v-for="stream in NonestreamingData"
          :key="stream.name"
          :stream="stream"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.warning-user-page {
  display: grid;
  grid-template-columns: 1fr 1fr;
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
</style>
