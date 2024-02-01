<script setup>
import { ref } from "vue";
import { useMoveStore } from "@/stores/move";
import DonationModal from "@/views/streaming/components/DonationModal.vue";
const store = useMoveStore();
const donModActive = ref(false);
const toggleDonMod = () => {
  donModActive.value = !donModActive.value;
};
</script>

<template>
  <div class="studio-info">
    <p>StudioInfo</p>
    <div class="info-content">
      <div class="content-box" @click="store.goStudioMain">
        <p>방송국 소개</p>
      </div>
      <div class="streamer-box"></div>
      <div class="content-box user-box">
        <div class="user-tab">follow</div>
        <!-- <DonationModal
          :modalActive="modalActive"
          @close="toggleModal"
        ></DonationModal> -->
          <DonationModal :donModActive="donModActive" 
          @close="toggleDonMod">
          </DonationModal>
        <div class="user-tab" @click="toggleDonMod">dontation</div>
      </div>
      <div class="content-box" @click="store.goBanListManagement">
        <p>유저 밴리스트</p>
      </div>
      <div class="content-box" @click="store.goDashboard">
        <p>스트리머 대쉬보드</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-info {
  width: 100%;
  height: 465px;
  border: 2px solid #000;
  padding: 10px;
  margin: 0 10px;
}
.info-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 90%;
}
.content-box {
  height: 30%;
  border: 2px solid #000;
  padding: 10px;
  margin: 10px;
}

.user-box {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.user-tab {
  border: 2px solid #000;
  padding: 10px;
  margin: 10px;
}
</style>
