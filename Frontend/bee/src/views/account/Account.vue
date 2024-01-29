<script setup>
import { ref } from "vue";

const isEditMode = ref(false);

const toggleEditMode = () => {
  isEditMode.value = !isEditMode.value;
};
</script>

<template>
  <div class="my-page">
    <header class="header">
      <div class="header-content">
        <h1>마이페이지</h1>
        <button class="update-button" @click="toggleEditMode">
          {{ isEditMode ? "프로필" : "회원정보수정" }}
        </button>
      </div>
    </header>
    <main class="main-content">
      <section v-if="!isEditMode" class="profile-view">
        <h2>프로필</h2>
        <div class="content-detail">
          <div class="box-detail">
            <hr />
            <div class="profile-update">
              <p>프로필 사진 변경</p>
              <div class="picture-update">
                <div class="picture"></div>
                <div class="picture-box">
                  <button class="pofile-button">프로필 사진 선택</button>
                  <p>10MB 이내의 JPEG, PNG, GIF 형식이어야 합니다.</p>
                </div>
              </div>
            </div>
          </div>
          <div class="box-detail">
            <hr />
            <p>Points</p>
          </div>
          <div class="box-detail">
            <hr />
            <p>팔로잉 채널</p>
          </div>
        </div>
      </section>

      <section v-else class="edit-view">
        <h2>회원정보수정</h2>
        <!-- 닉네임 & 이메일 -->
        <h3>개인정보수정</h3>
        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label for="nickname">Nickname:</label>
            <input
              id="nickname"
              type="text"
              placeholder="닉네임을 입력해주세요"
            />
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input id="email" type="email" placeholder="email을 입력해주세요" />
          </div>
          <div class="button-container">
            <button class="update-button" type="submit">저장</button>
          </div>
        </form>

        <hr />

        <!-- 패스워드변경1 -->
        <h3>비밀번호변경</h3>
        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label for="nickname">비밀번호:</label>
            <input
              id="password"
              type="password"
              placeholder="패스워드를 입력해주세요"
            />
          </div>

          <div class="form-group">
            <label for="email">비밀번호확인:</label>
            <input
              id="password"
              type="password"
              placeholder="패스워드를 다시 한번 입력해주세요"
            />
          </div>
          <div class="button-container">
            <button class="update-button" type="submit">저장</button>
          </div>
        </form>
      </section>
    </main>
  </div>
</template>

<style scoped>
.my-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  min-height: 100vh;
}

.header {
  width: 100%;
}

.header-content {
  max-width: 800px;
  margin: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.main-content {
  width: 100%;
  max-width: 800px;
  margin-top: 20px;
}

.button-container {
  display: flex;
  justify-content: right;
}

.profile-view,
.edit-view {
  padding: 20px;
  background-color: #ffe3bc;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
}

.profile-view h2,
.edit-view h2 {
  margin-bottom: 16px;
}

label {
  display: block;
  margin-top: 10px;
}
.form-group {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
h3 {
  margin-bottom: 20px;
}
label {
  margin-right: 10px;
  white-space: nowrap; /* Prevents the label from wrapping */
}
input[type="text"],
input[type="email"],
input[type="password"] {
  width: 70%;
  padding: 8px;
  margin-top: 4px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.update-button {
  display: block;
  padding: 10px 20px;
  margin-top: 20px;
  border: none;
  border-radius: 4px;
  background-color: #deb887;
  color: black;
  cursor: pointer;
}

.update-button:hover {
  background-color: #deb887;
}

hr {
  border: 2px solid #deb887;
  border-radius: 20px;
  margin: 20px 0;
}

.content-detail {
  display: grid;
  grid-template-rows: 4fr 2fr 4fr;
}
.box-detail {
  width: 100%;
  box-sizing: border-box;
}

.profile-update {
  display: flex;
  justify-content: space-between;
  height: 60%;
}
.pofile-button {
  display: block;
  padding: 10px 20px;
  margin-bottom: 10px;
  border: none;
  border-radius: 4px;
  background-color: #deb887;
  color: black;
  cursor: pointer;
}

.picture-update {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 10px;
}

.picture {
  border: 1px dotted black;
  width: 100%;
  padding-bottom: 100%;
  position: relative;
}
</style>
