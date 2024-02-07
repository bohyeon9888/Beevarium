<script setup>
import { ref, computed } from "vue";
import StudioInfo from "./components/StudioInfo.vue";

const currentPage = ref(1);
const banlistsPerPage = 10;

const streamer = ref({
  name: "LCK_KR",
  id: "bvlol"
})

const Banlists = [
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
  {
    username: "유저4",
    userid: "user4",
    date: "24.01.29",
  },
  {
    username: "유저5",
    userid: "user5",
    date: "24.01.29",
  },
  {
    username: "유저6",
    userid: "user6",
    date: "24.01.29",
  },
  {
    username: "유저7",
    userid: "user7",
    date: "24.01.29",
  },
  {
    username: "유저8",
    userid: "user8",
    date: "24.01.29",
  },
  {
    username: "유저9",
    userid: "user9",
    date: "24.01.29",
  },
  {
    username: "유저10",
    userid: "user10",
    date: "24.01.29",
  },
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
  {
    username: "유저4",
    userid: "user4",
    date: "24.01.29",
  },
  {
    username: "유저5",
    userid: "user5",
    date: "24.01.29",
  },
  {
    username: "유저6",
    userid: "user6",
    date: "24.01.29",
  },
  {
    username: "유저7",
    userid: "user7",
    date: "24.01.29",
  },
  {
    username: "유저8",
    userid: "user8",
    date: "24.01.29",
  },
  {
    username: "유저9",
    userid: "user9",
    date: "24.01.29",
  },
  {
    username: "유저10",
    userid: "user10",
    date: "24.01.29",
  },
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
  {
    username: "유저4",
    userid: "user4",
    date: "24.01.29",
  },
  {
    username: "유저5",
    userid: "user5",
    date: "24.01.29",
  },
  {
    username: "유저6",
    userid: "user6",
    date: "24.01.29",
  },
  {
    username: "유저7",
    userid: "user7",
    date: "24.01.29",
  },
  {
    username: "유저8",
    userid: "user8",
    date: "24.01.29",
  },
  {
    username: "유저9",
    userid: "user9",
    date: "24.01.29",
  },
  {
    username: "유저10",
    userid: "user10",
    date: "24.01.29",
  },
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
  {
    username: "유저4",
    userid: "user4",
    date: "24.01.29",
  },
  {
    username: "유저5",
    userid: "user5",
    date: "24.01.29",
  },
  {
    username: "유저6",
    userid: "user6",
    date: "24.01.29",
  },
  {
    username: "유저7",
    userid: "user7",
    date: "24.01.29",
  },
  {
    username: "유저8",
    userid: "user8",
    date: "24.01.29",
  },
  {
    username: "유저9",
    userid: "user9",
    date: "24.01.29",
  },
  {
    username: "유저10",
    userid: "user10",
    date: "24.01.29",
  },
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
  {
    username: "유저4",
    userid: "user4",
    date: "24.01.29",
  },
  {
    username: "유저5",
    userid: "user5",
    date: "24.01.29",
  },
  {
    username: "유저6",
    userid: "user6",
    date: "24.01.29",
  },
  {
    username: "유저7",
    userid: "user7",
    date: "24.01.29",
  },
  {
    username: "유저8",
    userid: "user8",
    date: "24.01.29",
  },
  {
    username: "유저9",
    userid: "user9",
    date: "24.01.29",
  },
  {
    username: "유저10",
    userid: "user10",
    date: "24.01.29",
  },
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
  {
    username: "유저4",
    userid: "user4",
    date: "24.01.29",
  },
  {
    username: "유저5",
    userid: "user5",
    date: "24.01.29",
  },
  {
    username: "유저6",
    userid: "user6",
    date: "24.01.29",
  },
  {
    username: "유저7",
    userid: "user7",
    date: "24.01.29",
  },
  {
    username: "유저8",
    userid: "user8",
    date: "24.01.29",
  },
  {
    username: "유저9",
    userid: "user9",
    date: "24.01.29",
  },
  {
    username: "유저10",
    userid: "user10",
    date: "24.01.29",
  },
  {
    username: "유저1",
    userid: "user1",
    date: "24.01.29",
  },
  {
    username: "유저2",
    userid: "user2",
    date: "24.01.29",
  },
  {
    username: "유저3",
    userid: "user3",
    date: "24.01.29",
  },
];

const totalPages = computed(() => Math.ceil(Banlists.length / banlistsPerPage));

const paginatedBanlists = computed(() => {
  const start = (currentPage.value - 1) * banlistsPerPage;
  const end = start + banlistsPerPage;
  return Banlists.slice(start, end);
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
</script>

<template>
  <div class="banlist-management-container">
    <div class="studio-info">
      <StudioInfo />
    </div>
    <div class="banlist-management-content-container">
      <div class="banlist-management-content-box">
        <div class="banlist-management-content-head">밴리스트 관리</div>
        <div class="banlist-management-content">
          <div class="banlist-head">
            <div style="display: flex; width: 1420px; height: 33px">
                <div class="index-head ">No</div>
                <div class="banlist-name-id-box-head">
                  <div class="banlist-username-head">닉네임</div>
                  <div class="banlist-userid-head">(아이디)</div>
                </div>
                <div class="streamer-name-id-box-head">
                  <div class="streamer-name-head">등록자</div>
                  <div class="streamer-id-head">(아이디)</div>
                </div>
                <div class="banlist-date-head">추가일</div>
                <div class="banlist-delete-head">해제</div>
              </div>
          </div>
          <ul class="ban-list">
            <li v-for="(ban, index) in paginatedBanlists" class="ban">
              <div style="display: flex; align-items: center; width: 1420px; height: 33px">
                <div class="index">{{ index }}</div>
                <div class="banlist-name-id-box">
                  <div class="banlist-username">{{ ban.username }}</div>
                  <div class="banlist-userid">({{ ban.userid }})</div>
                </div>
                <div class="streamer-name-id-box">
                  <div class="streamer-name">{{ streamer.name }}</div>
                  <div class="streamer-id">({{ streamer.id }})</div>
                </div>
                <div class="banlist-date">{{ ban.date }}</div>
                <div class="banlist-delete-button">해제</div>
              </div>
            </li>
          </ul>
          <div class="pagination-container">
            <div class="prev-button-box" @click="changePage(currentPage - 1)">
              <img src="../../assets/img/common/prev-button.png" alt="" class="prev-button" />
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
              <img src="../../assets/img/common/next-button.png" alt="" class="next-button" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.banlist-management-container {
  display: flex;
  width: 1899px;
}
.banlist-management-content-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 1579px;
}
.banlist-management-content-box {
  width: 1519px;
  height: 890px;
}
.banlist-management-content-head {
  width: 1519px;
  height: 24px;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 24px;
}
.banlist-management-content {
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
.banlist-head {
  width: 1420px;
  height: 31px;
  border-bottom: 1px solid #434343;
}
.index-head {
  display: flex;
  justify-content: center;
  width: 59px;
  height: 19px;
  font-size: 16px;
  font-weight: 600;
}
.banlist-name-id-box-head {
  display: flex;
  width: 857px;
  height: 19px;
}
.banlist-username-head {
  font-size: 16px;
  font-weight: 600;
  color: #e6e5ea;
  margin-right: 4px;
}
.banlist-userid-head {
  font-size: 16px;
  font-weight: 400;
  color: #a0a0a0;
}
.streamer-name-id-box-head {
  display: flex;
  width: 199px;
  height: 19px;
}
.streamer-name-head {
  font-size: 16px;
  font-weight: 600;
  color: #e6e5ea;
  margin-right: 4px;
}
.streamer-id-head {
  font-size: 16px;
  font-weight: 400;
  color: #a0a0a0;
}
.banlist-date-head {
  width: 207px;
  height: 19px;
  font-size: 16px;
  font-weight: 600;
}
.banlist-delete-head {
  display: flex;
  justify-content: center;
  width: 57px;
  height: 19px;
  font-size: 16px;
  font-weight: 600;
}
.ban-list {
  width: 1420px;
  height: 660px;
}
.ban {
  display: flex;
  align-items: center;
  width: 1420px;
  height: 66px;
  border-bottom: 1px solid #434343;
}
.index {
  display: flex;
  justify-content: center;
  width: 59px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
}
.banlist-name-id-box {
  display: flex;
  width: 857px;
  height: 19px;
}
.banlist-username {
  font-size: 16px;
  font-weight: 400;
  color: #e6e5ea;
  margin-right: 4px;
}
.banlist-userid {
  font-size: 16px;
  font-weight: 400;
  color: #e6e5ea;
}
.streamer-name-id-box {
  display: flex;
  width: 199px;
  height: 19px;
}
.streamer-name {
  font-size: 16px;
  font-weight: 400;
  color: #e6e5ea;
  margin-right: 4px;
}
.streamer-id {
  font-size: 16px;
  font-weight: 400;
  color: #e6e5ea;
}
.banlist-date {
  width: 207px;
  height: 19px;
  font-size: 16px;
  font-weight: 400;
}
.banlist-delete-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 57px;
  height: 33px;
  border: 1px solid #e6e5ea;
  border-radius: 8px;
  cursor: pointer;
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
