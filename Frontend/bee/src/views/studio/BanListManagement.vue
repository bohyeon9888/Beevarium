<script setup>
import StudioInfo from "./components/StudioInfo.vue";
</script>

<template>
  <div class="container">
    <div class="container-box">
      <div class="studio-main">
        <StudioInfo />
        <div class="studio-content">
          <h1>밴 유저 관리</h1>
          <div class="content-box">
            <table>
              <thead>
                <tr>
                  <td>닉네임(아이디)</td>
                  <td>채팅내역</td>
                  <td>밴 해제</td>
                </tr>
              </thead>
              <tbody>
                <tr class="ban-user">
                  <td>차준석</td>
                  <td><button>내역보기</button></td>
                  <td><button>벤 해제</button></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content-box {
  width: 100%;
  border: 2px solid #000;
  padding: 10px;
  margin: 10px 0;
}

table {
  border: 2px solid #000;
  width: 100%;
  border: 2px solid white;
}

thead::after {
  content: "";
  display: block;
  margin-bottom: 20px;
}
tbody td {
  border: 2px solid black;
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
}
</style>
