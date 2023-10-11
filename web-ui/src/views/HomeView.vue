<template>
  <div v-if="stems.length > 0">
    <el-space :size="30" wrap>
      <WordCard v-for="(o, key) in stems" :id="o.id" :key="key" :stem="o.term" class="word-card">
        <template #body>
          <div>{{ o.ipa != null ? o.ipa : '' }}</div>
          <div>{{ o.id }} / {{ o.created }}</div>
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
  </div>
  <div v-else>
    <el-empty :image-size="200" />
  </div>


</template>
<script setup>
import { onMounted } from 'vue'
import WordCard from '../components/WordCard.vue'
import { ref } from 'vue'

const stems = ref([])
const pagination = ref(null)

import api from 'axios'

const getStems = async function () {
  const res = await api.get('/stems/v1')
  stems.value = res.data.data
}

onMounted(() => {
  console.log(`the component is now mounted.`)
  getStems()
})
</script>

<style scoped>
.word-card {
  width: 500px;
}
</style>
