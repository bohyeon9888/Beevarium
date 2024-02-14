<script setup></script>

<template>
  <div class="ai-script-container">
    <div
      style="
        display: flex;
        justify-content: center;
        align-items: center;
        height: 50px;
        font-size: 18px;
        font-weight: 600;
        border-top: 1px solid #202020;
        border-bottom: 1px solid #202020;
      "
    >
      AI 스크립트
    </div>
    <div class="ai-script-box"></div>
  </div>
</template>

<style scoped>
.ai-script-container {
  width: 370px;
  height: 862px;
  border-right: 1px solid #202020;
  border-left: 1px solid #202020;
}
.ai-script-box {
  overflow-y: auto;
  padding: 10px;
  flex-grow: 1;
  width: 370px;
  height: 812px;
  max-height: 812px;
}
.ai-script-box::-webkit-scrollbar {
  width: 16px;
  display: none;
}
.ai-script-box:hover::-webkit-scrollbar {
  display: block;
}
.ai-script-box::-webkit-scrollbar-track {
  background-color: #1e1e1e;
}
.ai-script-box::-webkit-scrollbar-thumb {
  border: 4px solid #1e1e1e;
}
</style>
