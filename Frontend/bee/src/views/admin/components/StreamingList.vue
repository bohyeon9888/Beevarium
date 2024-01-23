<script setup>
defineProps({
  title: String,
});
</script>

<template>
  <div class="streaming-list-box">
    <h2>{{ title }}</h2>
  </div>
</template>

<style scoped>
.streaming-list-box {
  width: 100%;
  background-color: #fff;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  margin: 10px;
}

h2 {
  font-size: 18px;
  margin-top: 0;
}
</style>
