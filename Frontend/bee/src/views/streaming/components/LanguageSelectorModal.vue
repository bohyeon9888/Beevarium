<script setup>
import { ref } from 'vue'
const props = defineProps({
  lanModActive : Boolean
})
const korSelected = ref(false)
const toggleKor = () => {
  korSelected.value = true
  engSelected.value = false
}

const engSelected = ref(false)
const toggleEng = () => {
  engSelected.value = true
  korSelected.value = false
}

const disableSub = () => {
  korSelected.value = false
  engSelected.value = false
}
</script>

<template>
  <div class="lng-select-modal" v-show="lanModActive">
    <div class="lng-select-modal-inner">
      <button :class="{selected : korSelected}" @click = toggleKor> 
        한글<div class="check-mark" v-show="korSelected">✔</div>
      </button>
      <button :class="{selected : engSelected}" @click = toggleEng> 
        영어<div class="check-mark" v-show="engSelected">✔</div></button> 
      <button @click="disableSub"> 사용 안함</button> 
    </div>
    <!-- <button>닫기</button> -->
  </div>
</template>

<style scoped>


.lng-select-modal{
  position: fixed;
  width: 200px;
  height: 150px;
  background-color: blue;
  align-items: center;
  justify-content: center;
}

.lng-select-modal-inner{
  display: flex;
  flex-direction: column;
  align-items: center;
}

button{
  width: 150px;
  height: 30px;
  margin: 5px;
  font-size:20px;
  position: relative;
}

.check-mark{
  position: absolute;
  right: 10px;
  top: 0;
}

.selected{
  color:red;
  background-color: yellow;
}


</style>
