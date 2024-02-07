<template>
  <div class="studio-film-tab__film">
    <div class="studio__video-section" v-if="streamManager">
      <OvVideo :stream-manager="streamManager" />
      <div class="nickname-section">
        {{ clientData }}
      </div>
    </div>
  </div>
</template>

<script setup>
import OvVideo from "./OvVideo.vue";

const { streamManager } = props;
const getConnectionData = () => {
  const { connection } = streamManager.stream;
  return JSON.parse(connection.data);
};

const clientData = getConnectionData().clientData;
</script>

<style scoped>
.studio-film-tab__film {
  margin: 6px 5%;
  padding: 18px;
  border-radius: 15px;
  background-color: #fff;
  display: inline-block;
  flex-direction: row;
  position: relative;
}

.studio-film-tab__film ov-video {
  position: absolute;
  top: 0;
}

.studio__video-section {
  display: flex;
  align-content: flex-end;
  justify-content: center;
  flex-direction: column;
}

.nickname-section {
  padding-top: 10px;
  text-align: center;
  color: black;
  border-radius: 8px;
  flex-direction: column;
  align-items: center;
}
</style>
