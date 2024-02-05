<script setup>
import { ref } from "vue";

const honeyPoint = ref(5000);
const getProfileImageUrl = () => {
  return new URL("/src/assets/img/mypage/profile-image.png", import.meta.url).href;
};

const getFollowLogoUrl = (name) => {
  return new URL(`/src/assets/img/mypage/${name}.png`, import.meta.url).href;
};
const previewImage = ref(getProfileImageUrl());

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

const follows = ref([
  {
    logo: "follow-channel1",
    isLive: true,
    name: "LCK_KR",
  },
  {
    logo: "follow-channel2",
    isLive: true,
    name: "고전파0",
  },
  {
    logo: "follow-channel3",
    isLive: true,
    name: "제우스",
  },
  {
    logo: "follow-channel4",
    isLive: true,
    name: "쵸비03",
  },
  {
    logo: "follow-channel5",
    isLive: true,
    name: "Cuzz",
  },
  {
    logo: "follow-channel6",
    isLive: true,
    name: "라스칼곰팡히",
  },
  {
    logo: "follow-channel7",
    isLive: false,
    name: "onerrrr",
  },
  {
    logo: "follow-channel8",
    isLive: false,
    name: "Canyon_lol",
  },
  {
    logo: "follow-channel9",
    isLive: false,
    name: "두두_kdf",
  },
  {
    logo: "follow-channel10",
    isLive: false,
    name: "스폰지3",
  },
]);
</script>

<template>
  <div class="profile-container">
    <div class="profile-image-point-container">
      <div class="profile-image-setting-container">
        <div style="font-size: 20px; font-weight: 600">프로필 사진</div>
        <div class="profile-image-setting-box">
          <div class="profile-image-box">
            <img :src="previewImage" class="profile-image" />
          </div>
          <div class="profile-image-input-box">
            <label
              for="chooseProfileImage"
              style="display: inline-block; width: 132px; height: 33px; margin-bottom: 16px"
            >
              <div class="profile-image-input-button">프로필 사진 추가</div>
              <input
                id="chooseProfileImage"
                name="chooseProfileImage"
                type="file"
                accept="image/*"
                @change="changeImage"
                multiple
              />
            </label>
            <div
              style="width: 284px; height: 17px; font-size: 14px; font-weight: 400; color: #e6e5ea"
            >
              10MB 이내의 JPEG, PNG, GIF 형식이어야 합니다.
            </div>
          </div>
        </div>
      </div>
      <div class="profile-point-container">
        <div style="font-size: 20px; font-weight: 600">나의 포인트</div>
        <div class="profile-point-box">
          <div class="point-image-box">
            <img src="../../../assets/img/mypage/point-image.png" alt="" class="point-image" />
          </div>
          <div
            style="width: 98px; height: 21px; font-size: 18px; font-weight: 700; margin-right: 12px"
          >
            보유중인 허니
          </div>
          <div class="point-amount">{{ honeyPoint.toLocaleString("ko-KR") }}개</div>
        </div>
      </div>
    </div>
    <div class="follow-channel-container">
      <div style="font-size: 20px; font-weight: 600">팔로우 채널</div>
      <div class="follow-channel-box">
        <ul class="follow-channel-list">
          <li v-for="(follow, index) in follows" :key="index" class="follow-channel">
            <div
              id="follow-channel-logo-box"
              class="follow-channel-logo-box"
              :class="{ isLive: follow.isLive }"
            >
              <img :src="getFollowLogoUrl(follow.logo)" alt="" class="follow-channel-logo" />
              <div
                v-if="follow.isLive"
                style="
                  position: absolute;
                  bottom: -10px;
                  right: auto;
                  width: 50px;
                  height: 26px;
                  padding: 5px 11px;
                  font-size: 14px;
                  font-weight: 700;
                  background-color: red;
                  border-radius: 8px;
                "
              >
                LIVE
              </div>
            </div>
            <div class="follow-channel-name">{{ follow.name }}</div>
          </li>
        </ul>
        <div class="more-button-container">
          <div
            style="width: 717px; height: 1px; borer-radius: 8px; background-color: #323232"
          ></div>
          <div class="more-button-box">
            <div
              style="
                width: 32px;
                height: 14px;
                font-size: 12px;
                font-weight: 600;
                margin-right: 6px;
                color: #dcdcdc;
              "
            >
              더보기
            </div>
            <img src="../../../assets/img/search/more-button.png" alt="" />
          </div>
          <div
            style="width: 717px; height: 1px; borer-radius: 8px; background-color: #323232"
          ></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  width: 1620px;
  min-height: 617px;
  margin-top: 30px;
}
.profile-image-point-container {
  display: flex;
  justify-content: space-between;
  width: 1620px;
  height: 240px;
}
.profile-image-setting-container {
  width: 795px;
  height: 240px;
}
.profile-image-setting-box {
  display: flex;
  width: 795px;
  height: 200px;
  background-color: #1e1e1e;
  border-radius: 16px;
  margin-top: 16px;
}
.profile-image-box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 150px;
  height: 150px;
  border-radius: 10rem;
  background-color: #434343;
  margin: 25px 40px;
}
.profile-image {
  width: 97px;
  height: 97px;
  border-radius: 10rem;
  object-fit: scale-down;
}
.profile-image-input-box {
  width: 284px;
  height: 66px;
  margin: 84px 0 50px;
}
.profile-image-input-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 132px;
  height: 33px;
  font-size: 14px;
  font-weight: 600;
  border-radius: 8px;
  background-color: #ffec3e;
  color: #121212;
  cursor: pointer;
}
#chooseProfileImage {
  display: none;
}
.profile-point-container {
  width: 795px;
  height: 240px;
}
.profile-point-box {
  display: flex;
  align-items: center;
  width: 795px;
  height: 200px;
  background-color: #1e1e1e;
  border-radius: 16px;
  margin-top: 16px;
}
.point-image-box {
  width: 60px;
  height: 60px;
  margin: 0 12px 0 40px;
}
.point-image {
  width: 60px;
  height: 60px;
  border-radius: 10rem;
}
.point-amount {
  height: 29px;
  font-size: 24px;
  font-weight: 700;
  color: #ffcf40;
}
.follow-channel-container {
  width: 1620px;
  min-height: 347px;
  margin-top: 30px;
}
.follow-channel-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1620px;
  min-height: 307px;
  background-color: #1e1e1e;
  border-radius: 16px;
  margin-top: 16px;
}
.follow-channel-list {
  display: flex;
  width: 1540px;
  min-height: 137px;
  margin-top: 50px;
}
.follow-channel {
  width: 100px;
  height: 137px;
  margin: 15px 30px;
}
.follow-channel:nth-child(10n + 1) {
  margin-left: 0;
}
.follow-channel:nth-child(10n) {
  margin-right: 0;
}
.follow-channel:nth-last-child(-n + 10) {
  margin-bottom: 0;
}
.follow-channel-logo-box {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  border: 2px solid #a0a0a0;
  border-radius: 10rem;
  margin-bottom: 16px;
}
#follow-channel-logo-box.isLive {
  border: 2px solid #ffec3e;
}
.follow-channel-logo {
  width: 90px;
  height: 90px;
}
.follow-channel-name {
  display: flex;
  justify-content: center;
  width: 100px;
  height: 21px;
  font-size: 18px;
  font-weight: 600;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.more-button-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1540px;
  height: 30px;
  margin: 30px 0;
}
.more-button-box {
  display: flex;
  align-items: center;
  height: 30px;
  padding: 8px 16px;
  color: #dcdcdc;
  border: 1px solid #a0a0a0;
  border-radius: 10rem;
  cursor: pointer;
}
</style>
