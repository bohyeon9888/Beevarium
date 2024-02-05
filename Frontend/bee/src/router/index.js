import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/home/Home.vue";
import Search from "../views/search/Search.vue";
import Login from "../views/auth/Login.vue";
import Signup from "../views/auth/Signup.vue";
import Dashboard from "../views/streaming/Dashboard.vue";
import LiveStream from "../views/streaming/LiveStream.vue";
import Monitoring from "../views/admin/Monitoring.vue";
import WarningUser from "../views/admin/WarningUser.vue";
import BlackList from "../views/admin/BlackList.vue";
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
import StudioSetting from "../views/studio/StudioSetting.vue";
import AuthCallBack from "../views/auth/AuthCallBack.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home,
    },
    {
      path: "/search/:keyword",
      name: "Search",
      component: Search,
    },
    {
      path: "/user/login",
      name: "Login",
      component: Login,
    },
    {
      path: "/user/signup",
      name: "Signup",
      component: Signup,
    },
    {
      path: "/streaming/dashboard",
      name: "Dashboard",
      component: Dashboard,
    },
    {
      path: "/streaming/live-stream",
      name: "LiveStream",
      component: LiveStream,
    },
    {
      path: "/admin/monitoring",
      name: "Monitoring",
      component: Monitoring,
    },
    {
      path: "/admin/warning-user",
      name: "WarningUser",
      component: WarningUser,
    },
    {
      path: "/admin/blacklist",
      name: "BlackList",
      component: BlackList,
    },
    {
      path: "/mypage",
      name: "MyPage",
      component: MyPage,
      redirect: { name: "Profile" },
      children: [
        {
          path: "profile",
          name: "Profile",
          component: Profile,
        },
        {
          path: "privacy",
          name: "Privacy",
          component: Privacy
        }
      ]
    },
    {
      path: "/studio/clip",
      name: "Clip",
      component: Clip,
    },
    {
      path: "/studio/clip-detail",
      name: "ClipDetail",
      component: ClipDetail,
    },
    {
      path: "/studio/notice",
      name: "Notice",
      component: Notice,
    },
    {
      path: "/studio/notice-detail",
      name: "NoticeDetail",
      component: NoticeDetail,
    },
    {
      path: "/studio/notice-write",
      name: "NoticeWrite",
      component: NoticeWrite,
    },
    {
      path: "/studio/replay",
      name: "Replay",
      component: Replay,
    },
    {
      path: "/studio/replay-detail",
      name: "ReplayDetail",
      component: ReplayDetail,
    },
    {
      path: "/studio/banlist-management",
      name: "BanListManagement",
      component: BanListManagement,
    },
    {
      path: "/studio/studio-main",
      name: "StudioMain",
      component: StudioMain,
    },
    {
      path: "/studio/setting",
      name: "StudioSetting",
      component: StudioSetting,
    },
    {
      path: "/oauth/callback/google",
      name: "GoogleAuthCallback",
      component: AuthCallBack,
    },
    {
      path: "/oauth/callback/naver",
      name: "NaverAuthCallback",
      component: AuthCallBack,
    },
    {
      path: "/oauth/callback/kakao",
      name: "KakaoAuthCallback",
      component: AuthCallBack,
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
