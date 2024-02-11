<script setup>
import { ref, onMounted } from "vue";

const getImageUrl = (name) => {
  return new URL(`/src/assets/img/${name}.png`, import.meta.url).href;
};

const activeSlide = ref(1);
const recommendChannels = ref([
  {
    id: "1",
    img: "https://images.unsplash.com/photo-1530651788726-1dbf58eeef1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=882&q=80",
    logo: "streamer_image_1",
    name: "스트리머1",
    tags: ["한국어", "게임", "수다", "한국어", "게임", "수다"],
    intro:
      "종합 게임 스트리머 스트리머1 채널입니다! 다양한 장르의 게임을 기발한 방식으로 클리어하는 스트리머1 채널을 시청해보세요!",
    watcher: "626",
  },
  {
    id: "2",
    img: "https://images.unsplash.com/photo-1559386484-97dfc0e15539?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80",
    logo: "streamer_image_2",
    name: "스트리머2",
    tags: ["한국어", "게임", "수다"],
    intro:
      "종합 게임 스트리머 스트리머2 채널입니다! 다양한 장르의 게임을 기발한 방식으로 클리어하는 스트리머2 채널을 시청해보세요!",
    watcher: "310",
  },
  {
    id: "3",
    img: "https://images.unsplash.com/photo-1533461502717-83546f485d24?ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=60",
    logo: "streamer_image_1",
    name: "스트리머3",
    tags: ["한국어", "게임", "수다"],
    intro:
      "종합 게임 스트리머 스트리머3 채널입니다! 다양한 장르의 게임을 기발한 방식으로 클리어하는 스트리머3 채널을 시청해보세요!",
    watcher: "1492",
  },
  {
    id: "4",
    img: "https://images.unsplash.com/photo-1530651788726-1dbf58eeef1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=882&q=80",
    logo: "streamer_image_2",
    name: "스트리머4",
    tags: ["한국어", "게임", "수다"],
    intro:
      "종합 게임 스트리머 스트리머4 채널입니다! 다양한 장르의 게임을 기발한 방식으로 클리어하는 스트리머4 채널을 시청해보세요!",
    watcher: "72",
  },
  {
    id: "5",
    img: "https://images.unsplash.com/photo-1559386484-97dfc0e15539?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1234&q=80",
    logo: "streamer_image_1",
    name: "스트리머5",
    tags: ["한국어", "게임", "수다"],
    intro:
      "종합 게임 스트리머 스트리머5 채널입니다! 다양한 장르의 게임을 기발한 방식으로 클리어하는 스트리머5 채널을 시청해보세요!",
    watcher: "221",
  },
]);
onMounted(() => {
  setInterval(() => {
    activeSlide.value =
      activeSlide.value < recommendChannels.value.length
        ? activeSlide.value + 1
        : 1;

    const elem = document.getElementById(`item-${activeSlide.value}`);
    if (elem) {
      elem.checked = true;
    } else {
      console.log("no item error");
    }
  }, 3000);
});
</script>

<template>
  <div style="width: 1400px; height: 336px">
    <input type="radio" name="slider" id="item-1" checked />
    <input type="radio" name="slider" id="item-2" />
    <input type="radio" name="slider" id="item-3" />
    <input type="radio" name="slider" id="item-4" />
    <input type="radio" name="slider" id="item-5" />
    <div class="cards">
      <label
        v-for="(recommendChannel, index) in recommendChannels"
        :key="index"
        class="card"
        :for="`item-${recommendChannel.id}`"
        :id="`slide-${recommendChannel.id}`"
      >
        <img class="slide-image" :src="recommendChannel.img" alt="slide" />
        <div class="channel-info-container">
          <div class="channel-info-box">
            <div class="streamer-info-box">
              <div class="streamer-logo-box">
                <img
                  :src="getImageUrl(recommendChannel.logo)"
                  alt=""
                  class="streamer-logo"
                />
              </div>
              <div>
                <div class="streamer-name-box">{{ recommendChannel.name }}</div>
                <div class="live-watcher-box">
                  <img
                    src="../../assets/img/live.png"
                    alt=""
                    class="live-watcher"
                  />
                  <div style="font-size: 14px; font-weight: 500">
                    현재 {{ recommendChannel.watcher }}명 시청 중
                  </div>
                </div>
              </div>
            </div>
            <div class="tag-list-box">
              <ul class="tag-list">
                <li
                  v-for="(tag, index) in recommendChannel.tags"
                  :key="index"
                  class="tag"
                >
                  {{ tag }}
                </li>
              </ul>
            </div>
            <div class="channel-intro">{{ recommendChannel.intro }}</div>
          </div>
        </div>
      </label>
    </div>
  </div>
</template>

<style scoped>
input[type="radio"] {
  display: none;
}

.card {
  position: absolute;
  display: flex;
  width: 847px;
  height: 100%;
  left: 0;
  right: 0;
  margin: auto;
  transition: transform 0.4s ease;
  cursor: pointer;
}
.cards {
  position: relative;
  width: 100%;
  height: 100%;
  margin-bottom: 20px;
  overflow-x: hidden;
}
.slide-image {
  width: 597px;
  height: 336px;
  border-radius: 10px;
  object-fit: cover;
}
.channel-info-container {
  width: 250px;
  height: 336px;
  background-color: #1e1e1e;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  display: none;
}
.channel-info-box {
  width: 218px;
  height: 296px;
  margin: 20px 16px;
}
.streamer-info-box {
  display: flex;
  width: 218px;
  height: 60px;
}
.streamer-logo-box {
  width: 60px;
  height: 60px;
}
.streamer-logo {
  width: 60px;
  height: 60px;
}
.streamer-name-box {
  font-size: 20px;
  font-weight: 600;
  margin: 8px 10px 4px;
}
.live-watcher-box {
  display: flex;
  align-items: center;
  margin: 0 10px;
}
.live-watcher {
  width: 8px;
  height: 8px;
  margin-right: 4px;
}
.tag-list-box {
  margin: 16px 0;
}
.tag-list {
  display: flex;
  flex-wrap: wrap;
}
.tag {
  height: 20px;
  font-size: 12px;
  font-weight: 500;
  background-color: #434343;
  border-radius: 10rem;
  padding: 3px 8px;
  margin-right: 6px;
  margin-bottom: 6px;
}
.channel-intro {
  font-size: 14px;
  font-weight: 400;
}

#item-1:checked ~ .cards #slide-2,
#item-2:checked ~ .cards #slide-3,
#item-3:checked ~ .cards #slide-4,
#item-4:checked ~ .cards #slide-5,
#item-5:checked ~ .cards #slide-1 {
  transform: translateX(47%) scale(0.9);
  filter: brightness(70%);
  z-index: 1;
}
#item-1:checked ~ .cards #slide-5,
#item-2:checked ~ .cards #slide-1,
#item-3:checked ~ .cards #slide-2,
#item-4:checked ~ .cards #slide-3,
#item-5:checked ~ .cards #slide-4 {
  transform: translateX(-20%) scale(0.9);
  filter: brightness(70%);
  z-index: 1;
}
#item-1:checked ~ .cards #slide-3,
#item-2:checked ~ .cards #slide-4,
#item-3:checked ~ .cards #slide-5,
#item-4:checked ~ .cards #slide-1,
#item-5:checked ~ .cards #slide-2 {
  transform: translateX(64%) scale(0.8);
  filter: brightness(30%);
  z-index: 0;
}
#item-1:checked ~ .cards #slide-4,
#item-2:checked ~ .cards #slide-5,
#item-3:checked ~ .cards #slide-1,
#item-4:checked ~ .cards #slide-2,
#item-5:checked ~ .cards #slide-3 {
  transform: translateX(-40%) scale(0.8);
  filter: brightness(30%);
  z-index: 0;
}
#item-1:checked ~ .cards #slide-1,
#item-2:checked ~ .cards #slide-2,
#item-3:checked ~ .cards #slide-3,
#item-4:checked ~ .cards #slide-4,
#item-5:checked ~ .cards #slide-5 {
  transform: translatex(0) scale(1);
  z-index: 2;
}
#item-1:checked ~ .cards #slide-1 .channel-info-container,
#item-2:checked ~ .cards #slide-2 .channel-info-container,
#item-3:checked ~ .cards #slide-3 .channel-info-container,
#item-4:checked ~ .cards #slide-4 .channel-info-container,
#item-5:checked ~ .cards #slide-5 .channel-info-container {
  display: block;
}
#item-1:checked ~ .cards #slide-1 .slide-image,
#item-2:checked ~ .cards #slide-2 .slide-image,
#item-3:checked ~ .cards #slide-3 .slide-image,
#item-4:checked ~ .cards #slide-4 .slide-image,
#item-5:checked ~ .cards #slide-5 .slide-image {
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
</style>
