<script setup>
import { ref, computed } from "vue";
// import StudioInfo from "./components/StudioInfo.vue";

const prop = defineProps(["studioInfo"]);
const currentPage = ref(1);
const clipsPerPage = 15;

// const Clips = [
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip1",
//     title: "전체보기 / 2024 시즌 오프닝",
//     date: "24.01.09",
//     username: "user1",
//   },
//   {
//     thumbnail: "clip2",
//     title: "2023 LCK AWARDS",
//     date: "24.01.09",
//     username: "user2",
//   },
//   {
//     thumbnail: "clip3",
//     title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user3",
//   },
//   {
//     thumbnail: "clip4",
//     title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
//     date: "24.01.09",
//     username: "user4",
//   },
//   {
//     thumbnail: "clip5",
//     title: "[KT vs T1] 1세트 / 2023 LCK Summer",
//     date: "24.01.09",
//     username: "user4",
//   },
// ];

const totalPages = computed(() => Math.ceil(Clips.length / clipsPerPage));

const paginatedClips = computed(() => {
  const start = (currentPage.value - 1) * clipsPerPage;
  const end = start + clipsPerPage;
  return Clips.slice(start, end);
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

const getClipUrl = (name) => {
  return new URL(`/src/assets/img/studio/${name}.png`, import.meta.url).href;
};
</script>

<template>
  <!-- <div class="clip-container"> -->
    <div class="clip-content-container">
      <div class="clip-content-box">
        <div style="height: 36px; font-size: 20px; font-weight: 600">
          유저 클립
        </div>
        <div class="clip-content">
          <ul>
            <router-link :to="{ name: 'ClipDetail' }" class="clip-list">
              <li
                v-for="(clip, index) in paginatedClips"
                :key="index"
                class="clip"
              >
                <div class="clip-thumbnail-box">
                  <img
                    :src="getClipUrl(clip.thumbnail)"
                    alt=""
                    class="clip-thumbnail"
                  />
                </div>
                <div class="clip-info-box">
                  <div class="clip-title">{{ clip.title }}</div>
                  <div class="clip-username">{{ clip.username }}</div>
                  <div class="clip-date">{{ clip.date }}</div>
                </div>
              </li>
            </router-link>
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
/* .clip-container {
  display: flex;
  width: 1899px;
} */
.clip-content-container {
  display: flex;
  justify-content: center;
  width: 1579px;
}
.clip-content-box {
  margin-top: 40px;
  width: 1540px;
}
.clip-content {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 822px;
}
.clip-list {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 1540px;
}
.clip {
  width: 294px;
  height: 242px;
  cursor: pointer;
  margin-bottom: 32px;
}
.clip-thumbnail-box {
  position: relative;
  width: 294px;
  height: 165px;
  border-radius: 16px;
  border: 1px solid #252525;
}
.clip-thumbnail {
  width: 294px;
  height: 165px;
  border-radius: 16px;
  object-fit: cover;
}
.clip-info-box {
  width: 294px;
  height: 65px;
  margin-top: 12px;
}
.clip-title {
  width: 294px;
  height: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  font-weight: 700;
}
.clip-username {
  width: 294px;
  height: 18px;
  font-size: 15px;
  font-weight: 700;
  color: #b6b6b6;
  margin: 6px 0;
}
.clip-date {
  width: 294px;
  height: 17px;
  font-size: 15px;
  font-weight: 700;
  color: #b6b6b6;
}

.pagination-container {
  position: absolute;
  bottom: -40px;
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
