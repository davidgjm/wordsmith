<script setup>
import { onMounted } from 'vue'
import WordCard from '../components/WordCard.vue'
import { ref } from 'vue'


const pagination = ref(null)

</script>

<template>
  <el-space :size="30" wrap>
    <WordCard v-for="(o, key) in 10" :id="key" :key="key" :stem="o" class="word-card">
      <template #body>
        Reprehenderit dolor pariatur et Lorem exercitation ullamco quis nulla laborum. Adipisicing
        pariatur cupidatat aliquip eu Lorem. Sit ex laborum ea id fugiat consectetur. Ex est
        deserunt non amet id mollit. Commodo nostrud dolor esse laboris proident laboris.
      </template>
    </WordCard>
  </el-space>

  <el-divider />

  <div>
    <el-pagination background :hide-on-single-page="true" layout="prev, pager, next" :total="500" />
  </div>
</template>

<style scoped>
.word-card {
  width: 500px;
}
</style>
