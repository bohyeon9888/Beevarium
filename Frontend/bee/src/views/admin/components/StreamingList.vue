<script setup>
import { defineProps } from "vue";

defineProps({
  stream: Object,
});
</script>

<template>
  <div class="streaming-list-box">
    <div class="name">{{ stream.name }}</div>
    <div class="viewers">
      <img class="live" src="../../../assets/img/live.png" alt="" />
      {{ stream.viewers }}
    </div>
  </div>
</template>

<style scoped>
.streaming-list-box {
  display: flex;
  justify-content: space-between;
  font-size: 20px;
  font-weight: 600;
}
.name {
  color: #fff;
  margin-bottom: 20px;
}
.viewers {
  color: #fff;
  margin-bottom: 20px;
}
.live {
  padding-bottom: 2px;
}
</style>
