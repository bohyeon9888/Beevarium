<script setup>
import { ref, computed, onMounted } from "vue";
// import StudioInfo from "./components/StudioInfo.vue";
import { noticeDelete, noticeList } from "@/api/notice";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const prop = defineProps(["studioInfo"]);
const authStore = useAuthStore();
const { accessToken } = storeToRefs(authStore);
// const Notices = [
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
//   {
//     title: "[안내] 2024 LCK SPRING 일정 안내",
//     date: "24.01.09",
//     views: 1353,
//     content: "[1월 17일 수요일 오후 5시]...",
//   },
// ];
const Notices = ref([]);

const currentPage = ref(1);
const noticesPerPage = 4;

const totalPages = computed(() =>
  Math.ceil(Notices.value.length / noticesPerPage)
);

const paginatedNotices = computed(() => {
  const start = (currentPage.value - 1) * noticesPerPage;
  const end = start + noticesPerPage;
  return Notices.value.slice(start, end);
});

const visiblePages = computed(() => {
  let pages = [];
  let startPage;
  let endPage;

  if (totalPages.value <= 5) {
    // 총 페이지 수가 5 이하인 경우 모든 페이지 번호를 보여준다.
    startPage = 1;
    endPage = totalPages.value;
  } else {
    // 총 페이지가 5개 이상인 경우 현재 페이지를 중심으로 계산
    if (currentPage.value <= 3) {
      // 현재 페이지가 3 이하면 처음 5개 페이지를 보여줌
      startPage = 1;
      endPage = 5;
    } else if (currentPage.value + 2 >= totalPages.value) {
      // 현재 페이지가 마지막에서 세 번째 이후면 마지막 5개 페이지를 보여줌
      startPage = totalPages.value - 4;
      endPage = totalPages.value;
    } else {
      // 그 외의 경우 현재 페이지를 중앙에 두고 양쪽으로 2개씩 보여줌
      startPage = currentPage.value - 2;
      endPage = currentPage.value + 2;
    }
  }

  for (let i = startPage; i <= endPage; i++) {
    pages.push(i);
  }

  return pages;
});

const changePage = (page) => {
  if (page < 1) {
    currentPage.value = 1;
  } else if (page > totalPages.value) {
    currentPage.value = totalPages.value;
  } else {
    currentPage.value = page;
  }
};

const doNoticeDelete = (noticeNo) => {
  noticeDelete(
    accessToken.value,
    noticeNo,
    ({ data }) => {
      console.log(data.message);
      router.go(0);
    },
    () => {}
  );
};

onMounted(() => {
  noticeList(
    accessToken.value,
    5,
    ({ data }) => {
      Notices.value = data.data;
    },
    (error) => {
      console.log("?");
    }
  );
});
</script>

<template>
  <!-- <div class="notice-container"> -->
  <div class="notice-content-container">
    <div class="notice-content-box">
      <div class="notice-content-head">공지사항</div>
      <div class="notice-content">
        <div class="notice-count-box">
          <div class="notice-count">{{ Notices.length }}</div>
          <div style="font-size: 16px; font-weight: 600; color: #e6e5ea">
            개의 글
          </div>
        </div>
        <ul class="notice-list">
          <li
            v-for="(notice, index) in paginatedNotices"
            class="notice"
            @click="
              router.push({ path: `/studio/${route.params.streamerId}/notice/${notice.broadcastStationNoticeNo}` })
            "
          >
            <div
              style="
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 1420px;
                height: 33px;
                margin-bottom: 23px;
              "
            >
              <div style="display: flex; align-items: center">
                <div class="streamer-logo-box">
                  <img
                    src="../../assets/img/studio/studio-logo.png"
                    alt=""
                    class="streamer-logo"
                  />
                </div>
                <div class="streamer-name-box">
                  <div class="streamer-name">{{ prop.studioInfo.userName }}</div>
                  <div class="streamer-id">({{ prop.studioInfo.userId }})</div>
                </div>
                <div
                  style="
                    width: 1px;
                    height: 14px;
                    margin: 0 8px;
                    background: #636363;
                  "
                ></div>
                <div class="notice-date">{{ notice.createdDate }}</div>
                <div
                  style="
                    width: 1px;
                    height: 14px;
                    margin: 0 8px;
                    background: #636363;
                  "
                ></div>
                <div class="notice-views">조회수 {{ notice.views }}회</div>
              </div>
              <div class="notice-manage-button">
                <div class="notice-edit-button">수정</div>
                <div class="notice-delete-button" @click="doNoticeDelete(notice.broadcastStationNoticeNo)">삭제</div>
              </div>
            </div>
            <div class="notice-title">
              {{ notice.broadcastStationNoticeTitle }}
            </div>
            <div class="notice-body">
              {{ notice.broadcastStationNoticeContent }}
            </div>
          </li>
        </ul>
        <div class="pagination-container">
          <div class="prev-button-box" @click="changePage(currentPage - 1)">
            <img
              src="../../assets/img/common/prev-button.png"
              alt=""
              class="prev-button"
            />
          </div>
          <div class="pagination-button-box">
            <div
              v-for="page in visiblePages"
              :key="page"
              class="pagination-button"
              :class="{ active: page === currentPage }"
              @click="changePage(page)"
            >
              {{ page }}
            </div>
          </div>
          <div class="next-button-box" @click="changePage(currentPage + 1)">
            <img
              src="../../assets/img/common/next-button.png"
              alt=""
              class="next-button"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<style scoped>
/* .notice-container {
  display: flex;
  width: 1899px;
} */
.notice-content-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 1579px;
}
.notice-content-box {
  width: 1519px;
  height: 890px;
}
.notice-content-head {
  width: 1519px;
  height: 24px;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 24px;
}
.notice-content {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 1519px;
  height: 842px;
  background-color: #1e1e1e;
  border-radius: 16px;
}
.notice-count-box {
  display: flex;
  width: 1420px;
  height: 31px;
  border-bottom: 1px solid #434343;
}
.notice-count {
  font-size: 16px;
  font-weight: 600;
  color: #ffcf40;
}
.notice-list {
  width: 1420px;
  height: 633px;
}
.notice {
  width: 1420px;
  height: 149px;
  border-bottom: 1px solid #434343;
  padding: 16px 0;
  cursor: pointer;
}
.streamer-logo-box {
  width: 33px;
  height: 33px;
  border-radius: 10rem;
  margin-right: 8px;
}
.streamer-logo {
  width: 33px;
  height: 33px;
  border-radius: 10rem;
}
.streamer-name-box {
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
.notice-date {
  font-size: 16px;
  font-weight: 600;
}
.notice-views {
  font-size: 16px;
  font-weight: 400;
}
.notice-manage-button {
  display: flex;
  justify-content: space-between;
  width: 122px;
  height: 33px;
}
.notice-edit-button,
.notice-delete-button {
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
.notice-title {
  width: 1420px;
  height: 21px;
  font-size: 18px;
  font-weight: 600;
  color: #dcdcdc;
  margin-bottom: 16px;
}
.notice-body {
  width: 1420px;
  height: 24px;
  font-size: 16px;
  font-weight: 600;
}
.pagination-container {
  position: absolute;
  bottom: 30px;
  display: flex;
  justify-content: center;
  width: 376px;
  height: 40px;
  margin-top: 20px;
}
.prev-button-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  cursor: pointer;
}
.next-button-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  cursor: pointer;
}
.pagination-button-box {
  display: flex;
  justify-content: space-between;
  width: 232px;
  height: 40px;
}
.pagination-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
}

.pagination-button.active {
  background-color: #ffcf40;
  border-radius: 8px;
  color: #121212;
}
</style>
