import { useRouter } from "vue-router";
import { defineStore } from "pinia";

export const useMoveStore = defineStore(
  "moveStore",
  () => {
    const router = useRouter();
    const goHome = function () {
      router.push({
        name: "Home",
      });
    };
    const goClip = function () {
      router.push({
        name: "Clip",
      });
    };

    const goNotice = function () {
      router.push({
        name: "Notice",
      });
    };

    const goReplay = function () {
      router.push({
        name: "Replay",
      });
    };

    const goReplayDetail = function () {
      router.push({
        name: "ReplayDetail",
      });
    };

    const goClipDetail = function () {
      router.push({
        name: "ClipDetail",
      });
    };

    const goNoticeDetail = function () {
      router.push({
        name: "NoticeDetail",
      });
    };

    const goDashboard = function () {
      router.push({
        name: "Dashboard",
      });
    };

    const goStudioMain = function () {
      router.push({
        name: "StudioMain",
      });
    };

    const goBanListManagement = function () {
      router.push({
        name: "BanListManagement",
      });
    };
    return {
      goHome,
      goClip,
      goNotice,
      goReplay,
      goReplayDetail,
      goClipDetail,
      goNoticeDetail,
      goDashboard,
      goStudioMain,
      goBanListManagement,
    };
  },
  { persist: true }
);
