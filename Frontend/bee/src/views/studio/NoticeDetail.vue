<script setup>
import { ref, onMounted } from "vue";
// import StudioInfo from "./components/StudioInfo.vue";
import { noticeDelete, noticeDetail } from "@/api/notice";
import { useRoute } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import router from "@/router";
import { useStudioStore } from "@/stores/studio";
const studioStore = useStudioStore();
const { studioInfo } = storeToRefs(studioStore);

const authStore = useAuthStore();
const { accessToken } = storeToRefs(authStore);
const route = useRoute();


const notice = ref({});

const getNoticeDetail = () => {
  noticeDetail(
    route.params.noticeNo,
    ({ data }) => {
      notice.value = data.data;
      console.log(notice.value);
    },
    () => {}
  );
};

const doNoticeDelete = () => {
  noticeDelete(
    accessToken.value,
    route.params.noticeNo,
    ({ data }) => {
      console.log(data.message);
      router.push({ path: `/studio/${route.params.streamerId}/notice` });
    },
    () => {}
  );
};

onMounted(() => {
  getNoticeDetail();
});
</script>

<template>
  <!-- <div class="notice-detail-container"> -->
  <div class="notice-detail-content-container">
    <div class="back-to-notice-button">
      <router-link
        :to="{ path: `/studio/${route.params.streamerId}/notice` }"
        style="display: flex; align-items: center; width: 96px; height: 24px"
      >
        <img
          src="../../assets/img/common/prev-button.png"
          alt=""
          class="back-to-notice"
        />
        <div
          style="width: 75px; height: 24px; font-size: 20px; font-weight: 600"
        >
          공지사항
        </div>
      </router-link>
    </div>
    <div class="notice-detail-content-box">
      <div class="notice-title">{{ notice.broadcastStationNoticeTitle }}</div>
      <div class="notice-detail-content">
        <div class="notice-username-date-views-box">
          <div class="notice-username-box">
            <div class="streamer-logo-box">
              <img
                src="../../assets/img/studio/studio-logo.png"
                alt=""
                class="streamer-logo"
              />
            </div>
            <div class="streamer-name-id-box">
              <div class="streamer-name">{{ studioInfo.userName }}</div>
              <div class="streamer-id">({{ studioInfo.userId }})</div>
            </div>
          </div>
          <div
            style="
              display: flex;
              justify-content: space-between;
              align-items: center;
              height: 19px;
            "
          >
            <div class="notice-date">{{ notice.createdDate }}</div>
            <div
              style="
                width: 1px;
                height: 14px;
                background: #636363;
                margin: 0 8px;
              "
            ></div>
          </div>
        </div>
        <div class="notice-content">
          {{ notice.broadcastStationNoticeContent }}
        </div>
      </div>
      <div class="notice-detail-manage-button-box">
        <div class="notice-detail-manage-button">
          <div class="notice-detail-edit-button">수정</div>
          <div class="notice-detail-delete-button" @click="doNoticeDelete">
            삭제
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<style scoped>
/* .notice-detail-container {
  display: flex;
  width: 1899px;
} */
.notice-detail-content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1579px;
}
.back-to-notice-button {
  width: 1579px;
  height: 64px;
  padding: 16px 0 24px 30px;
}
.back-to-notice {
  height: 18px;
  margin-right: 8px;
}
.notice-detail-content-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 1519px;
  min-height: 842px;
  background-color: #1e1e1e;
  border-radius: 16px;
}
.notice-title {
  width: 1420px;
  height: 24px;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 24px;
}
.notice-detail-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1420px;
  min-height: 693px;
  border-top: 1px solid #434343;
  border-bottom: 1px solid #434343;
}
.notice-username-date-views-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1420px;
  height: 19px;
  margin: 20px 0 50px;
}
.notice-username-box {
  display: flex;
  align-items: center;
  height: 33px;
}
.streamer-logo-box {
  width: 32px;
  height: 32px;
  margin-right: 8px;
}
.streamer-logo {
  width: 32px;
  height: 32px;
}
.streamer-name-id-box {
  display: flex;
  align-items: center;
  height: 19px;
}
.streamer-name {
  font-size: 16px;
  font-weight: 600;
}
.streamer-id {
  font-size: 14px;
  font-weight: 400;
  color: #a0a0a0;
}
.notice-date,
.notice-views {
  display: flex;
  font-size: 16px;
  font-weight: 400;
}
.notice-content {
  display: flex;
  justify-content: center;
  width: 1420px;
}
.notice-detail-manage-button-box {
  display: flex;
  justify-content: flex-end;
  width: 1420px;
  height: 33px;
  margin-top: 16px;
}
.notice-detail-manage-button {
  display: flex;
  justify-content: space-between;
  width: 122px;
  height: 33px;
}
.notice-detail-edit-button,
.notice-detail-delete-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 57px;
  height: 33px;
  border: 1px solid #e6e5ea;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}
</style>
