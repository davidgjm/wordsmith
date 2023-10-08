<template>
  <div>
      <h2 @click="handleClick(1)">首页</h2>
      <h2 @click="handleClick(0)">关于</h2>
  </div>
</template>
<script setup lang="ts">
  import { useRouter } from 'vue-router';
  const router = useRouter()
  const handleClick = (num: number)=>{
      if (num) {
          router.push({name: 'home'})
      }else router.push({name: 'about'})
  }
</script>
<style scoped></style>