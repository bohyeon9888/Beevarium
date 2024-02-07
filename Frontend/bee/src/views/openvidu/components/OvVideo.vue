<template>
  <video ref="videoRef" autoplay><track kind="captions" /></video>
</template>

<script setup>
const { streamManager } = props;

onMounted(() => {
  streamManager.addVideoElement(videoRef.value);
});
</script>

<style scoped>
video {
  padding-bottom: 10px;
}
</style>
