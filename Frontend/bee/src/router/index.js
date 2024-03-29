import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/home/Home.vue";
import Search from "../views/search/Search.vue";
import Login from "../views/auth/Login.vue";
import Signup from "../views/auth/Signup.vue";
import Dashboard from "../views/streaming/Dashboard.vue";
import LiveStream from "../views/streaming/LiveStream.vue";
import Admin from "../views/admin/Admin.vue";
import Monitoring from "../views/admin/components/Monitoring.vue";
import WarningUser from "../views/admin/components/WarningUser.vue";
import BlackList from "../views/admin/components/BlackList.vue";
import MyPage from "../views/account/MyPage.vue";
import Profile from "../views/account/components/Profile.vue";
import Privacy from "../views/account/components/Privacy.vue";
import Clip from "../views/studio/Clip.vue";
import ClipDetail from "../views/studio/ClipDetail.vue";
import Notice from "../views/studio/Notice.vue";
import NoticeDetail from "../views/studio/NoticeDetail.vue";
import NoticeWrite from "../views/studio/NoticeWrite.vue";
import Replay from "../views/studio/Replay.vue";
import ReplayDetail from "../views/studio/ReplayDetail.vue";
import BanListManagement from "../views/studio/BanListManagement.vue";
import StudioMain from "../views/studio/StudioMain.vue";
import Studio from "../views/studio/Studio.vue";
import StudioSetting from "../views/studio/StudioSetting.vue";
import AuthCallBack from "../views/auth/AuthCallBack.vue";
import OpenVIdutest from "@/views/openvidu/OpenVIdutest.vue";
import Openviduopen from "@/views/openvidu/Openviduopen.vue";
import OpenviduView from "@/views/openvidu/OpenviduView.vue";
import OpenViduLogic1 from "@/views/openvidu/OpenViduLogic1.vue";
import Openviduchat from "@/views/openvidu/Openviduchat.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home,
      props: true,
    },
    {
      path: "/search/:keyword",
      name: "Search",
      component: Search,
      props: true,
    },
    {
      path: "/user/login",
      name: "Login",
      component: Login,
      props: true,
    },
    {
      path: "/user/signup",
      name: "Signup",
      component: Signup,
      props: true,
    },
    {
      path: "/streaming/dashboard",
      name: "Dashboard",
      component: Dashboard,
      props: true,
    },
    {
      path: "/streaming/live-stream/:streamerId",
      name: "LiveStream",
      component: LiveStream,
      props: true,
    },
    {
      path: "/admin",
      name: "Admin",
      component: Admin,
      props: true,
      redirect: { name: "Monitoring" },
      children: [
        {
          path: "monitoring",
          name: "Monitoring",
          component: Monitoring,
          props: true,
        },
        {
          path: "warning-user",
          name: "WarningUser",
          component: WarningUser,
          props: true,
        },
        {
          path: "blacklist",
          name: "BlackList",
          component: BlackList,
          props: true,
        },
      ],
    },
    {
      path: "/mypage",
      name: "MyPage",
      component: MyPage,
      props: true,
      redirect: { name: "Profile" },
      children: [
        {
          path: "profile",
          name: "Profile",
          component: Profile,
          props: true,
        },
        {
          path: "privacy",
          name: "Privacy",
          component: Privacy,
          props: true,
        },
      ],
    },
    {
      path: "/studio/:streamerId",
      name: "Studio",
      component: Studio,
      props: true,
      redirect: { name: "StudioMain" },
      children: [
        {
          path: "studio-main",
          name: "StudioMain",
          component: StudioMain,
          props: true,
        },
        {
          path: "notice",
          name: "Notice",
          component: Notice,
          props: true,
        },
        {
          path: "notice/:noticeNo",
          name: "NoticeDetail",
          component: NoticeDetail,
          props: true,
        },
        {
          path: "notice/write",
          name: "NoticeWrite",
          component: NoticeWrite,
          props: true,
        },
        {
          path: "replay",
          name: "Replay",
          component: Replay,
          props: true,
        },
        {
          path: "replay/:replayNo",
          name: "ReplayDetail",
          component: ReplayDetail,
          props: true,
        },
        {
          path: "clip",
          name: "Clip",
          component: Clip,
          props: true,
        },
        {
          path: "clip/:clipNo",
          name: "ClipDetail",
          component: ClipDetail,
          props: true,
        },
        {
          path: "banlist",
          name: "BanListManagement",
          component: BanListManagement,
          props: true,
        },
        {
          path: "setting",
          name: "StudioSetting",
          component: StudioSetting,
          props: true,
        },
      ],
    },
    {
      path: "/oauth/callback/google",
      name: "GoogleAuthCallback",
      component: AuthCallBack,
      props: true,
    },
    {
      path: "/oauth/callback/naver",
      name: "NaverAuthCallback",
      component: AuthCallBack,
      props: true,
    },
    {
      path: "/oauth/callback/kakao",
      name: "KakaoAuthCallback",
      component: AuthCallBack,
      props: true,
    },
    {
      path: "/openvidutest",
      name: "OpenviduTest",
      component: OpenVIdutest,
      props: true,
    },
    {
      path: "/openvidutestview",
      name: "OpenviduView",
      component: OpenviduView,
      props: true,
    },
    {
      path: "/openvidutestopen",
      name: "Openviduopen",
      component: Openviduopen,
      props: true,
    },
    {
      path: "/openviduchat",
      name: "Openviduchat",
      component: Openviduchat,
      props: true,
    },
    {
      path: "/logic1",
      name: "OpenviduLogic1",
      component: OpenViduLogic1,
      props: true,
    },
  ],
});

import { useAuthStore } from "@/stores/user";

router.beforeEach((to, from) => {
  const store = useAuthStore();
  if (to.name === "Dashboard" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if (to.name === "Account" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if (to.name === "LiveStream" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if (to.name === "Blacklist" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if (to.name === "Monitoring" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if (to.name === "WarningUser" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if (to.name === "Search" && !store.isLoggedIn) {
    window.alert("로그인이 필요합니다.");
    return { name: "Login" };
  }
  if ((to.name === "Signup" || to.name === "Login") && store.isLoggedIn) {
    window.alert("이미 로그인 했습니다.");
    return { name: "Home" };
  }
});

export default router;
