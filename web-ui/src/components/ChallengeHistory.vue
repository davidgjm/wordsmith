<script setup>
import { ref,onMounted } from 'vue'
import MaterialSymbol from '../components/icons/MaterialSymbol.vue'
import api from 'axios'

const props = defineProps({
  id: Number,
  stem: String
})

const historyItems = ref([])

const loadHistoryRecords = async function (stemId) {
  const res = await api.get(`/challenges/v1/${stemId}`)
  const data = res.data.data
  historyItems.value = data
}

</script>
<template>
  <div>
    <el-button type="primary" size="large" @click="loadHistoryRecords(id)">
      <MaterialSymbol icon="history" style="margin-right: 0.25rem" /> Load History Items
    </el-button>
  </div>
  <main class="main-content">
    <el-timeline>
    <el-timeline-item
      v-for="(item, index) in historyItems"
      :key="index"
      :timestamp="new Date(item.attemptedAt).toLocaleString('zh-CN')"
    >
      {{ item.result }}
      <div v-if="item.note">
        {{ item.note }}
      </div>
    </el-timeline-item>
  </el-timeline>
  </main>
</template>
<style scoped>
.main-content {
  margin-top: 1rem;
  margin-bottom: 1rem;
}
</style>