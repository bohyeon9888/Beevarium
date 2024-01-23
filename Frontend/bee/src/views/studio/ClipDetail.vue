<script setup>
import StudioInfo from "./components/StudioInfo.vue";
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="studio-main">
        <StudioInfo />
        <div class="studio-content">
          <h1>유저 클립</h1>
          <div class="content-box">
            <h3>title</h3>
            <div>
              <p>작성일시:</p>
              <p>작성자:</p>
              <p>조회수:</p>
            </div>
          </div>
          <div class="video-screen"></div>
          <div class="video-editor"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-content {
  width: 100%;
  border: 2px solid #000;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 10px;
  overflow: auto;
}
.studio-main {
  display: grid;
  grid-template-columns: 3fr 7fr;
  padding: 10px;
  width: 100%;
  height: 500px;
  gap: 20px;
}

.content-box {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.video-screen {
  width: 100%;
  height: 50%;
  border: 2px solid #000;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 10px;
  overflow: auto;
  margin-bottom: 10px;
}

.video-editor {
  width: 100%;
  border: 2px solid #000;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 10px;
  overflow: auto;
}
</style>
