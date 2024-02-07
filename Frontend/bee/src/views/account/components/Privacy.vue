<script setup>
import { ref, computed, watch, onMounted } from "vue";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { checkNickname, changeNickname, changePassword } from "@/api/mypage";

const authStore = useAuthStore();
const { accessToken } = storeToRefs(authStore);

const account = ref({
  image: "account-image",
  platform: "네이버",
  date: "24.01.27",
});

const getAccountImageUrl = (name) => {
  return new URL(`/src/assets/img/mypage/${name}.png`, import.meta.url).href;
};

// 마이페이지 정보 prop
const prop = defineProps(["myPageData"]);
const nickname = ref(prop.myPageData.username);

// 닉네임 중복 확인
const isNicknameSame = ref("");
const doCheckNickname = () => {
  checkNickname(
    accessToken.value,
    nickname,
    ({ data }) => {
      console.log(data.data.msg);
      isNicknameSame.value = data.status;
    },
    (error) => {
      console.log(error.data.msg);
    }
  );
};

// 닉네임 변경
const doChangeNickname = () => {
  if (isNicknameSame.value == "success") {
    changeNickname(
      accessToken.value,
      {
        nickname: nickname.value,
      },
      ({ data }) => {
        console.log(data.data.msg);
      },
      (error) => {
        console.log(error.data.msg);
      }
    );
  }
};

// 비밀번호 확인
const newPassword = ref("");
const passwordCheck = ref("");
const isPasswordSame = computed(() => {
  if (
    newPassword.value != "" &&
    passwordCheck.value != "" &&
    newPassword.value === passwordCheck.value
  ) {
    return 1;
  }
  if (
    newPassword.value != "" &&
    passwordCheck.value != "" &&
    newPassword.value != passwordCheck.value
  ) {
    return 2;
  }
});
watch(
  () => newPassword.value,
  (newValue, oldValue) => {
    newPassword.value = newValue;
  }
);
watch(
  () => passwordCheck.value,
  (newValue, oldValue) => {
    passwordCheck.value = newValue;
  }
);

// 비밀번호 변경
const doChangePassword = () => {
  if (isPasswordSame == 1) {
    changePassword(
      accessToken.value,
      {
        password: newPassword.value,
      },
      ({ data }) => {
        console.log(data.data.msg);
      },
      (error) => {
        console.log(error.data.msg);
      }
    );
  } else {
    alert("비밀번호가 일치하지 않습니다.");
  }
};

</script>

<template>
  <div class="privacy-container">
    <div style="font-size: 20px; font-weight: 600">내 정보 변경</div>
    <div class="privacy-box">
      <div class="account-info-container">
        <div
          style="width: 106px; height: 21px; font-size: 18px; font-weight: 600"
        >
          가입 계정
        </div>
        <div
          style="
            display: flex;
            align-items: center;
            height: 40px;
            margin-left: 244px;
          "
        >
          <img :src="getAccountImageUrl(account.image)" class="account-image" />
          <div class="account-info-box">
            <div class="account-platform">{{ account.platform }}</div>
            <div class="account-signup-date">({{ account.date }})</div>
          </div>
        </div>
      </div>
      <div
        style="
          width: 1540px;
          height: 1px;
          border-radius: 8px;
          background-color: #434343;
        "
      ></div>
      <div
        style="
          width: 1540px;
          height: 21px;
          font-size: 18px;
          font-weight: 600;
          margin: 30px 0;
        "
      >
        계정 설정
      </div>
      <div
        style="
          width: 1540px;
          height: 1px;
          border-radius: 8px;
          background-color: #323232;
        "
      ></div>
      <div class="name-setting-container">
        <div
          style="
            width: 42px;
            height: 19px;
            font-size: 16px;
            font-weight: 700;
            margin-top: 40px;
            margin-left: 30px;
          "
        >
          닉네임
        </div>
        <div class="name-edit-container">
          <div class="name-edit-box">
            <input type="text" class="name-input" v-model="nickname" />
            <div class="duplication-check-button" @click="doCheckNickname">
              중복 확인
            </div>
            <div class="name-edit-button" @click="doChangeNickname">
              닉네임 변경
            </div>
          </div>
          <div
            style="
              width: 586px;
              height: 40px;
              font-size: 14px;
              font-weight: 400;
            "
          >
            <div>
              ㅡ 닉네임을 만드세요! 닉네임을 한국어, 영어로 설정할 수 있습니다.
            </div>
            <div>
              ㅡ BEEVARIUM 이용약관을 위반한 닉네임은 제재당할 수 있으니
              주의하세요.
            </div>
          </div>
        </div>
      </div>
      <div
        style="
          width: 1540px;
          height: 1px;
          border-radius: 8px;
          background-color: #323232;
        "
      ></div>
      <div class="password-setting-container">
        <div
          style="
            width: 98px;
            height: 19px;
            font-size: 16px;
            font-weight: 700;
            margin-top: 40px;
            margin-left: 30px;
          "
        >
          비밀번호 변경
        </div>
        <div class="password-edit-container">
          <input
            type="password"
            class="new-password"
            placeholder="새 비밀번호"
            v-model="newPassword"
          />
          <input
            type="password"
            class="new-password-check"
            placeholder="새 비밀번호 확인"
            v-model="passwordCheck"
          />
          <div
            style="
              width: 295px;
              height: 20px;
              font-size: 14px;
              font-weight: 400;
              color: #e6e5ea;
            "
          >
            영문/숫자/특수문자 조합으로 10~15자 대소문자 구분
          </div>
        </div>
      </div>
      <div class="save-button" @click="doChangePassword">저장</div>
    </div>
  </div>
</template>

<style scoped>
.privacy-container {
  width: 1620px;
  height: 730px;
  margin-top: 30px;
}
.privacy-box {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1620px;
  height: 692px;
  background-color: #1e1e1e;
  border-radius: 16px;
  margin-top: 14px;
}
.account-info-container {
  display: flex;
  align-items: center;
  width: 1540px;
  height: 40px;
  margin-bottom: 24px;
  margin-top: 60px;
}
.account-image {
  width: 40px;
  height: 40px;
  border-radius: 10rem;
  object-fit: scale-down;
  margin-right: 12px;
}
.account-info-box {
  display: flex;
  align-items: center;
}
.account-platform {
  font-size: 16px;
  font-weight: 400;
  margin-right: 4px;
}
.account-signup-date {
  font-size: 12px;
  font-weight: 400;
  color: #a0a0a0;
}
.name-setting-container {
  display: flex;
  width: 1540px;
  height: 155px;
}
.name-edit-container {
  width: 586px;
  height: 95px;
  margin-left: 278px;
  margin-top: 30px;
}
.name-edit-box {
  display: flex;
  height: 40px;
  margin-bottom: 16px;
}
.name-input {
  width: 300px;
  height: 40px;
  font-size: 16px;
  font-weight: 400;
  outline: none;
  border: 1px solid #a0a0a0;
  border-radius: 8px;
  background-color: transparent;
  padding: 10px 16px;
  margin-right: 8px;
}
.duplication-check-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 92px;
  height: 40px;
  font-size: 14px;
  font-weight: 600;
  color: #121212;
  background-color: #ffcf40;
  border-radius: 8px;
  cursor: pointer;
  margin-right: 8px;
}
.name-edit-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 104px;
  height: 40px;
  font-size: 14px;
  font-weight: 600;
  border: 1px solid #e6e5ea;
  border-radius: 8px;
  cursor: pointer;
}
.password-setting-container {
  display: flex;
  width: 1540px;
  height: 190px;
}
.password-edit-container {
  width: 515px;
  height: 130px;
  margin: 30px 0 30px 222px;
}
.new-password,
.new-password-check {
  width: 300px;
  height: 39px;
  font-size: 16px;
  font-weight: 400;
  padding: 10px 16px;
  outline: none;
  border: 1px solid #a0a0a0;
  border-radius: 8px;
  background-color: transparent;
  margin-bottom: 16px;
}
.save-button {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  bottom: 40px;
  width: 272px;
  height: 48px;
  font-size: 16px;
  font-weight: 700;
  color: #121212;
  border-radius: 8px;
  background-color: #ffec3e;
  cursor: pointer;
}
</style>
