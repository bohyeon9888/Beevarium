<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { socialLogin } from "@/api/user";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const route = useRoute();
const router = useRouter();
const authCode = ref(null);
const authStore = useAuthStore();
const { coperation } = storeToRefs(authStore);

onMounted(async () => {
  // URL의 쿼리스트링에서 'code' 매개변수를 가져옴
  authCode.value = route.query.code;
console.log(coperation);
  await socialLogin(
    coperation.value,
    authCode.value,
    ({ data }) => {
      console.log(data);
      authStore.login(data.data);
    },
    (error) => {
      console.log(error);
    }
  );
  router.push({ name: "Home" });
});
</script>
<template>
  <div></div>
</template>

<style scoped></style>
