<script setup>
import { ref } from "vue";
import StudioInfo from "./components/StudioInfo.vue";

// 프로필 배너 사진 선택
const getProfileBannerImageUrl = () => {
  return new URL("/src/assets/img/mypage/profile-image.png", import.meta.url)
    .href;
};
const previewImage = ref(getProfileBannerImageUrl());
const changeImage = (event) => {
  const files = event.target.files;
  console.log(files);
  if (files.length > 0) {
    const file = files[0];

    if (file.type.startsWith("image/")) {
      const reader = new FileReader();
      reader.onload = (e) => {
        previewImage.value = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
};
</script>

<template>
  <div class="studio-setting-container">
    <div class="studio-info">
      <StudioInfo />
    </div>
    <div class="studio-setting-content-container">
      <div class="studio-setting-head">방송국 설정</div>
      <div class="studio-banner-setting-container">
        <div class="profile-banner-setting-container">
          <div
            style="width: 92px; height: 24px; font-size: 20px; font-weight: 600"
          >
            프로필 배너
          </div>
          <div class="profile-banner-setting-box">
            <div class="profile-banner-image-box">
              <img :src="previewImage" alt="" class="profile-banner-image" />
            </div>
            <div class="profile-banner-image-input-box">
              <div class="profile-banner-image-input">
                <label
                  for="chooseProfileBannerImage"
                  style="display: inline-block; width: 89px; height: 33px"
                >
                  <div class="profile-banner-image-input-button">업데이트</div>
                  <input
                    id="chooseProfileBannerImage"
                    name="chooseProfileBannerImage"
                    type="file"
                    accept="image/*"
                    @change="changeImage"
                    multiple
                  />
                </label>
                <div class="profile-banner-image-save-button">변경</div>
                <div class="profile-banner-image-reset-button">초기화</div>
              </div>
              <div
                style="
                  width: 284px;
                  height: 17px;
                  font-size: 14px;
                  font-weight: 400;
                  color: #e6e5ea;
                "
              >
                10MB 이내의 JPEG, PNG, GIF 형식이어야 합니다.
              </div>
            </div>
          </div>
        </div>
        <div class="ad-banner-setting-container">
          <div
            style="width: 92px; height: 24px; font-size: 20px; font-weight: 600"
          >
            광고 배너
          </div>
          <div class="ad-banner-setting-box">
            <div class="ad-banner-image-box">
              <img :src="previewImage" alt="" class="ad-banner-image" />
            </div>
            <div class="ad-banner-image-input-box">
              <div class="ad-banner-image-input">
                <label
                  for="chooseAdBannerImage"
                  style="display: inline-block; width: 89px; height: 33px"
                >
                  <div class="ad-banner-image-input-button">업데이트</div>
                  <input
                    id="chooseAdBannerImage"
                    name="chooseAdBannerImage"
                    type="file"
                    accept="image/*"
                    @change="changeImage"
                    multiple
                  />
                </label>
                <div class="ad-banner-image-save-button">변경</div>
                <div class="ad-banner-image-reset-button">초기화</div>
              </div>
              <div
                style="
                  width: 284px;
                  height: 17px;
                  font-size: 14px;
                  font-weight: 400;
                  color: #e6e5ea;
                "
              >
                10MB 이내의 JPEG, PNG, GIF 형식이어야 합니다.
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="studio-intro-setting-container">
        <div
          style="width: 109px; height: 24px; font-size: 20px; font-weight: 600"
        >
          방송국 소개글
        </div>
        <div class="studio-intro-setting-box">
          <textarea class="studio-intro-input"></textarea>
          <div class="studio-intro-update-button">업데이트</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-setting-container {
  display: flex;
  width: 1899px;
}
.studio-setting-content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1579px;
}
.studio-setting-head {
  width: 1540px;
  height: 29px;
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 40px;
}
.studio-banner-setting-container {
  display: flex;
  justify-content: space-between;
  width: 1540px;
  height: 240px;
}
.profile-banner-setting-container {
  width: 755px;
  height: 240px;
}
.profile-banner-setting-box {
  display: flex;
  width: 755px;
  height: 200px;
  border-radius: 16px;
  background-color: #1e1e1e;
  margin-top: 16px;
}
.profile-banner-image-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 320px;
  height: 140px;
  border-radius: 16px;
  margin: 30px;
}
.profile-banner-image {
  width: 320px;
  height: 140px;
  border: 1px solid #434343;
  border-radius: 16px;
  object-fit: scale-down;
}
.profile-banner-image-input-box {
  width: 300px;
  height: 66px;
  margin: 84px 0 50px;
}
.profile-banner-image-input {
  display: flex;
  height: 33px;
  margin-bottom: 16px;
}
.profile-banner-image-input-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 89px;
  height: 33px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 8px;
  background-color: #ffec3e;
  color: #121212;
  cursor: pointer;
}
.profile-banner-image-save-button {
  height: 33px;
  padding: 8px 20px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 8px;
  background-color: #ffcf40;
  color: #121212;
  cursor: pointer;
  margin: 0 8px;
}
.profile-banner-image-reset-button {
  height: 33px;
  padding: 8px 20px;
  font-size: 14px;
  font-weight: 600;
  border: 1px solid #e6e5ea;
  border-radius: 8px;
  background-color: transparent;
  color: #e6e5ea;
  cursor: pointer;
}
#chooseProfileBannerImage {
  display: none;
}
.ad-banner-setting-container {
  width: 755px;
  height: 240px;
}
.ad-banner-setting-box {
  display: flex;
  width: 755px;
  height: 200px;
  border-radius: 16px;
  background-color: #1e1e1e;
  margin-top: 16px;
}
.ad-banner-image-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 320px;
  height: 140px;
  border-radius: 16px;
  margin: 30px;
}
.ad-banner-image {
  width: 320px;
  height: 140px;
  border: 1px solid #434343;
  border-radius: 16px;
  object-fit: scale-down;
}
.ad-banner-image-input-box {
  width: 300px;
  height: 66px;
  margin: 84px 0 50px;
}
.ad-banner-image-input {
  display: flex;
  height: 33px;
  margin-bottom: 16px;
}
.ad-banner-image-input-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 89px;
  height: 33px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 8px;
  background-color: #ffec3e;
  color: #121212;
  cursor: pointer;
}
.ad-banner-image-save-button {
  height: 33px;
  padding: 8px 20px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 8px;
  background-color: #ffcf40;
  color: #121212;
  cursor: pointer;
  margin: 0 8px;
}
.ad-banner-image-reset-button {
  height: 33px;
  padding: 8px 20px;
  font-size: 14px;
  font-weight: 600;
  border: 1px solid #e6e5ea;
  border-radius: 8px;
  background-color: transparent;
  color: #e6e5ea;
  cursor: pointer;
}
#chooseAdBannerImage {
  display: none;
}
.studio-intro-setting-container {
  width: 1540px;
  height: 585px;
  margin-top: 30px;
}
.studio-intro-setting-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 1540px;
  height: 547px;
  border-radius: 16px;
  background-color: #1e1e1e;
  margin-top: 14px;
}
.studio-intro-input {
  text-align: center;
  width: 1460px;
  height: 420px;
  padding: 40px;
  resize: none;
  outline: none;
  border: 1px solid #a0a0a0;
  border-radius: 8px;
  background-color: transparent;
  margin-bottom: 24px;
}
.studio-intro-update-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 89px;
  height: 33px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 8px;
  background-color: #ffec3e;
  color: #121212;
  cursor: pointer;
}
</style>
