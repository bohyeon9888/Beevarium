<script setup>
import { ref, computed, onMounted } from "vue";
import StudioInfo from "./components/StudioInfo.vue";
import { replayList } from "@/api/replay";

const currentPage = ref(1);
const ReplaysPerPage = 15;

const Replays = [
{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },{
    thumbnail: "replay1",
    title: "전체보기 / 2024 시즌 오프닝",
    date: "24.01.09",
  },
  {
    thumbnail: "replay2",
    title: "2023 LCK AWARDS",
    date: "23.12.13",
  },
  {
    thumbnail: "replay3",
    title: "[T1 vs BLG] 2세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay4",
    title: "[T1 vs BLG] 1세트 / 2023 월드 챔피언십",
    date: "23.10.28",
  },
  {
    thumbnail: "replay5",
    title: "[KT vs T1] 1세트 / 2023 LCK Summer",
    date: "23.08.06",
  },
];

const totalPages = computed(() => Math.ceil(Replays.length / ReplaysPerPage));

const paginatedReplays = computed(() => {
  const start = (currentPage.value - 1) * ReplaysPerPage;
  const end = start + ReplaysPerPage;
  return Replays.slice(start, end);
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

const getReplayUrl = (name) => {
  return new URL(`/src/assets/img/studio/${name}.png`, import.meta.url).href;
};

onMounted(() => {
  replayList(streamerId);
});
</script>

<template>
  <div class="replay-container">
    <div class="studio-info">
      <StudioInfo />
    </div>
    <div class="replay-content-container">
      <div class="replay-content-box">
        <div style="height: 36px; font-size: 20px; font-weight: 600">
          다시보기
        </div>
        <div class="replay-content">
          <ul>
            <router-link :to="{ name: 'ReplayDetail' }" class="replay-list">
              <li
                v-for="(replay, index) in paginatedReplays"
                :key="index"
                class="replay"
              >
                <div class="replay-thumbnail-box">
                  <img
                    :src="getReplayUrl(replay.thumbnail)"
                    alt=""
                    class="replay-thumbnail"
                  />
                </div>
                <div class="replay-info-box">
                  <div class="replay-title">{{ replay.title }}</div>
                  <div class="replay-date">{{ replay.date }}</div>
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
  </div>
</template>

<style scoped>
.replay-container {
  display: flex;
  width: 1899px;
}
.replay-content-container {
  display: flex;
  justify-content: center;
  width: 1579px;
}
.replay-content-box {
  margin-top: 40px;
  width: 1540px;
}
.replay-content {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 822px;
}
.replay-list {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 1540px;
}
.replay {
  width: 294px;
  height: 218px;
  cursor: pointer;
  margin-bottom: 32px;
}
.replay-thumbnail-box {
  position: relative;
  width: 294px;
  height: 165px;
  border-radius: 16px;
  border: 1px solid #252525;
}
.replay-thumbnail {
  width: 294px;
  height: 165px;
  border-radius: 16px;
  object-fit: cover;
}
.replay-info-box {
  width: 294px;
  height: 41px;
  margin-top: 12px;
}
.replay-title {
  width: 294px;
  height: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  font-weight: 700;
  margin-bottom: 6px;
}
.replay-date {
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
