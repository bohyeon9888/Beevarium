<script setup>
import { ref } from "vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import { RouterLink } from "vue-router";

const images = ref(["HomeCarousel1", "HomeCarousel2", "HomeCarousel3"]);
const livestreams = ref([
  "HomeCarousel1",
  "HomeCarousel2",
  "HomeCarousel3",
  "HomeCarousel1",
  "HomeCarousel2",
  "HomeCarousel3",
  "HomeCarousel1",
  "HomeCarousel2",
]);
const getImageUrl = (name) => {
  return new URL(`/src/assets/img/${name}.png`, import.meta.url).href;
};
</script>

<template>
  <div class="home-container">
    <div class="carousel-container">
      <Carousel
        class="carousel-viewport"
        :wrap-around="true"
        :transition="1000"
      >
        <Slide v-for="(image, index) in images" :key="index">
          <div class="carousel__item">
            <img class="slide-image" :src="getImageUrl(image)" />
          </div>
        </Slide>
        <template #addons>
          <Navigation />
        </template>
      </Carousel>
    </div>
    <br>
    <br>
    <br>
    <br>
    <h1 style="padding-left: 50px;">라이브 방송중</h1>
    <div class="livestream-container">
      <ul class="livestream-box">
        <li class="livestream" v-for="(livestream, index) in livestreams" :key="index">
          <router-link :to="{name: 'LiveStream'}">
            <img class="livestream-thumbnail" :src="getImageUrl(livestream)" alt="">
            <div class="livestream-title">방송제목</div>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.carousel-container {
  display: flex;
  justify-content: center;
}
.carousel-viewport {
  width: 1000px;
  height: 380px;
}
.slide-image {
  width: 800px;
  height: 380px;
}
.livestream-container {
  display: flex;
  justify-content: center;
}
.livestream-box {
  max-width: 95%;
  display: flex;
  flex-wrap: wrap;
}
.livestream {
  width: 23%;
  margin: 12px;
}
.livestream-thumbnail {  
  width: 100%;
  border-radius: 15px;
}
</style>
