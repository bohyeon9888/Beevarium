<script setup>
import { ref } from "vue";
import StudioInfo from "./components/StudioInfo.vue";
import { useRouter } from "vue-router";
import { noticeCreate } from "@/api/notice";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const authStore = useAuthStore();
const { accessToken } = storeToRefs(authStore);

const router = useRouter();

const noticeData = ref({
  broadcastStationNo: "5",
  broadcastStationNoticeTitle: "",
  broadcastStationNoticeContent: "",
});
const noticeWrite = () => {
  noticeCreate(
    accessToken.value,
    noticeData.value,
    ({ data }) => {
      console.log(data.message);
    },
    (error) => {
      console.log(error.data.msg);
    }
  );
};
const goBack = () => {
  router.go(-1);
};
</script>

<template>
  <div class="notice-write-container">
    <div class="studio-info">
      <StudioInfo />
    </div>
    <div class="notice-write-content-container">
      <div class="notice-write-content-box">
        <div
          style="
            width: 1420px;
            height: 30px;
            font-size: 20px;
            font-weight: 700;
            margin-bottom: 30px;
          "
        >
          공지사항
        </div>
        <input
          class="notice-title-input"
          type="text"
          placeholder="제목을 입력해 주세요"
          v-model="noticeData.broadcastStationNoticeTitle"
        />
        <div
          style="width: 1420px; height: 2px; background-color: #434343; border-radius: 8px"
        ></div>
        <textarea
          class="notice-content-input"
          v-model="noticeData.broadcastStationNoticeContent"
        ></textarea>
        <div class="notice-write-cancel-button-box">
          <div class="notice-write-button" @click="noticeWrite">등록</div>
          <div class="notice-cancel-button" @click="goBack">취소</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.notice-write-container {
  display: flex;
  width: 1899px;
}
.notice-write-content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1579px;
}
.notice-write-content-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 1519px;
  min-height: 922px;
  background-color: #1e1e1e;
  border-radius: 16px;
}
.notice-title-input {
  width: 1420px;
  height: 40px;
  font-size: 24px;
  font-weight: 700;
  outline: none;
  border: 0;
  background-color: transparent;
  margin-bottom: 20px;
}
.notice-content-input {
  width: 1420px;
  height: 600px;
  font-size: 16px;
  font-weight: 500;
  outline: none;
  resize: none;
  border: 2px solid #434343;
  border-radius: 8px;
  background-color: transparent;
  margin-top: 30px;
  padding: 20px;
}
.notice-write-cancel-button-box {
  display: flex;
  justify-content: flex-end;
  width: 1420px;
  height: 33px;
  margin-top: 20px;
}
.notice-write-button {
  display: flex;
  align-items: center;
  height: 36px;
  font-size: 18px;
  font-weight: 600;
  color: #121212;
  padding: 0 20px;
  border-radius: 8px;
  background-color: #ffcf40;
  margin-right: 8px;
  cursor: pointer;
}
.notice-cancel-button {
  display: flex;
  align-items: center;
  height: 36px;
  font-size: 18px;
  font-weight: 600;
  color: #e6e5ea;
  padding: 0 20px;
  border: 1px solid #e6e5ea;
  border-radius: 8px;
  cursor: pointer;
}
</style>
