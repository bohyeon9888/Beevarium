<script setup>
import { ref, computed } from "vue";
import StudioInfo from "./components/StudioInfo.vue";
import { useMoveStore } from "@/stores/move";

const store = useMoveStore();
const currentPage = ref(1);
const replaysPerPage = 4;
const neighborPages = 1;

const Replays = [
  {
    title: "다시보기제목1",
    viewers: 1500,
    likes: 100,
  },
  {
    title: "다시보기 제목2",
    viewers: 2500,
    likes: 200,
  },
  {
    title: "다시보기 제목3",
    viewers: 3500,
    likes: 300,
  },
  {
    title: "다시보기 제목4",
    viewers: 4500,
    likes: 400,
  },
  {
    title: "다시보기 제목5",
    viewers: 5500,
    likes: 500,
  },
  {
    title: "다시보기 제목6",
    viewers: 6500,
    likes: 600,
  },
  {
    title: "다시보기 제목7",
    viewers: 7500,
    likes: 700,
  },
  {
    title: "다시보기 제목8",
    viewers: 8500,
    likes: 800,
  },
  {
    title: "다시보기 제목9",
    viewers: 9500,
    likes: 900,
  },
  {
    title: "다시보기 제목10",
    viewers: 10500,
    likes: 1000,
  },
  {
    title: "다시보기 제목11",
    viewers: 11500,
    likes: 1100,
  },
  {
    title: "다시보기 제목1",
    viewers: 1500,
    likes: 100,
  },
  {
    title: "다시보기 제목2",
    viewers: 2500,
    likes: 200,
  },
  {
    title: "다시보기 제목3",
    viewers: 3500,
    likes: 300,
  },
  {
    title: "다시보기 제목4",
    viewers: 4500,
    likes: 400,
  },
  {
    title: "다시보기 제목5",
    viewers: 5500,
    likes: 500,
  },
  {
    title: "다시보기 제목6",
    viewers: 6500,
    likes: 600,
  },
  {
    title: "다시보기 제목7",
    viewers: 7500,
    likes: 700,
  },
  {
    title: "다시보기 제목8",
    viewers: 8500,
    likes: 800,
  },
  {
    title: "다시보기 제목9",
    viewers: 9500,
    likes: 900,
  },
  {
    title: "다시보기 제목10",
    viewers: 10500,
    likes: 1000,
  },
  {
    title: "다시보기 제목11",
    viewers: 11500,
    likes: 1100,
  },
];

const totalPages = computed(() => Math.ceil(Replays.length / replaysPerPage));

const paginatedReplays = computed(() => {
  const start = (currentPage.value - 1) * replaysPerPage;
  const end = start + replaysPerPage;
  return Replays.slice(start, end);
});

const visiblePages = computed(() => {
  let pages = [];
  let startPage = Math.max(currentPage.value - neighborPages, 1);
  let endPage = Math.min(currentPage.value + neighborPages, totalPages.value);

  for (let i = startPage; i <= endPage; i++) {
    pages.push(i);
  }

  return pages;
});

const changePage = (page) => {
  currentPage.value = page;
};
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="studio-main">
        <StudioInfo />
        <div class="studio-content">
          <h1>다시 보기</h1>
          <div class="replay-list">
            <div
              class="replay-item"
              v-for="(replay, index) in paginatedReplays"
              :key="index"
              @click="store.goReplayDetail"
            >
              <div class="replay-thumbnail"></div>
              <div class="replay-info">
                <h3>{{ replay.title }}</h3>
                <p>조회수: {{ replay.viewers }} 회</p>
                <p>{{ replay.likes }} 좋아요</p>
              </div>
            </div>
          </div>
          <div class="pagination">
            <button v-if="currentPage > 1" @click="changePage(currentPage - 1)">
              Prev
            </button>
            <button
              v-for="page in visiblePages"
              :key="page"
              :class="{ active: page === currentPage }"
              @click="changePage(page)"
            >
              {{ page }}
            </button>
            <button
              v-if="currentPage < totalPages"
              @click="changePage(currentPage + 1)"
            >
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.replay-list {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.replay-item {
  border: 2px solid #ddd;
  padding: 15px;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.replay-thumbnail {
  width: 100%;
  height: 150px;
  background-color: #eee;
  border: 1px dashed #ccc;
  margin-bottom: 10px;
}

.replay-info h3 {
  margin-bottom: 5px;
}

.replay-info p {
  margin-bottom: 2px;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination button {
  padding: 5px 10px;
  margin: 0 5px;
  border: 1px solid #ccc;
  background-color: #f0f0f0;
  cursor: pointer;
}

.pagination button.active {
  border-color: #007bff;
  background-color: #007bff;
  color: white;
}
h1 {
  margin-bottom: 20px;
}
</style>
