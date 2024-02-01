<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { socialLogin } from "@/api/user";

const router = useRouter();
const authStore = useAuthStore();
const { coperation } = storeToRefs(authStore);

onMounted(async () => {
  console.log(coperation);
  await socialLogin(
    coperation.value,
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
