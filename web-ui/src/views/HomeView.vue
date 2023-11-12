<template>
  <div v-if="stems.length > 0">
    <el-space :size="30" wrap>
      <WordCard v-for="(o, key) in stems" :key="key" :stem="o.term" class="word-card">
        <template #body>
          <el-menu mode="horizontal" :ellipsis="false">
            <el-menu-item index="1" @click="getWordsByStemId(o, key)">
              <MaterialSymbol class="warning" icon="dictionary" />
            </el-menu-item>
            <el-menu-item index="2">
              <MaterialSymbol icon="linked_services" />
            </el-menu-item>
            <el-menu-item index="3">
              <MaterialSymbol icon="scan" />
            </el-menu-item>
            <el-menu-item index="4" @click="showIpaChart = true">
              <MaterialSymbol icon="music_note" />
            </el-menu-item>
          </el-menu>

          <el-row>
            <el-col :span="2">
              <el-text class="mx-1" type="info">ID</el-text>
            </el-col>
            <el-col :span="2">
              <span>{{ o.id }}</span>
            </el-col>
            <el-col :span="3">
              <el-text class="mx-1" type="info">Created</el-text>
            </el-col>
            <el-col :span="7">
              <el-text size="small">{{ new Date(o.created).toLocaleString('zh-CN') }}</el-text>
            </el-col>
            <el-col :span="3">
              <el-text class="mx-1" type="info">Updated</el-text>
            </el-col>
            <el-col :span="7">
              <el-text size="small">{{ new Date(o.lastModified).toLocaleString('zh-CN') }}</el-text>
            </el-col>
          </el-row>

          <el-space direction="vertical">
            <div>
              <el-text class="mx-1" type="info">IPA</el-text>
            </div>
            <div>{{ o.ipa != null ? o.ipa : '' }}</div>
          </el-space>

          <div :style="{ display: o.showWords ? 'block' : 'none' }">
            <div v-for="(word, key) in words[key]" :key="key">
              <el-row style="margin-top: 5px">
                <el-col :span="4" />
                <el-col :span="3">
                  <el-tag class="ml-2 start" effect="dark">{{ word.partOfSpeech }}</el-tag>
                </el-col>
                <el-col :span="16">{{ word.translation }}</el-col>
              </el-row>

              <el-row>
                <el-col :span="4">
                  <el-text class="mx-1" type="info">解释</el-text>
                </el-col>
                <el-col :span="20">{{ word.explanation }}</el-col>
              </el-row>

              <el-row>
                <el-col :span="4">
                  <el-text class="mx-1" type="info">示例</el-text>
                </el-col>
                <el-col :span="20">{{ word.example }}</el-col>
              </el-row>
            </div>
          </div>
        </template>
      </WordCard>
    </el-space>
    <el-divider />
    <div>
      <el-pagination
        background
        :hide-on-single-page="true"
        :page-size="pagination.pageSize"
        :current-page="pagination.pageNumber"
        @update:current-page="requestStemsByPage"
        layout="prev, pager, next"
        :total="pagination.totalElements"
      />
    </div>
    <el-drawer v-model="showIpaChart" title="IPA" :with-header="false">
      <IpaChart />
    </el-drawer>
  </div>
  <div v-else>
    <el-empty :image-size="200" />
  </div>
</template>
<script setup>
import { onMounted } from 'vue'
import WordCard from '../components/WordCard.vue'
import { ref } from 'vue'
import IpaChart from '../components/IpaChart.vue'

const showIpaChart = ref(false)
const stems = ref([])
const words = ref([])
const pagination = ref({
  totalPages: 0,
  totalElements: 0,
  pageNumber: 1,
  pageSize: 20,
  first: false,
  last: false
})

import api from 'axios'
import MaterialSymbol from '../components/icons/MaterialSymbol.vue'

const getStems = async function () {
  const res = await api.get(
    `/stems/v1?pageNumber=${pagination.value.pageNumber}&pageSize=${pagination.value.pageSize}`
  )
  stems.value = res.data.data
  pagination.value = res.data.pagination
  stems.value.forEach((element) => {
    element.showWords = false
    element.wordsLoaded = false
  })
}

const getWordsByStemId = async function (stem, index) {
  console.log('getting words for stem %s at %s', stem.id, index)
  console.log('stem words loaded, stem.showwords', stem.wordsLoaded, stem.showWords)
  if (!stem.wordsLoaded) {
    const res = await api.get(`/stems/v1/${stem.id}/words`)
    const clone = words.value.slice()
    clone[index] = res.data.data
    words.value = clone
    stem.showWords = true
    stem.wordsLoaded = true
  } else stem.showWords = !stem.showWords
}

const requestStemsByPage = async function (pageNumber) {
  console.log('Page click detected. Current pagination info', pageNumber)
  pagination.value.pageNumber = pageNumber
  getStems()
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
