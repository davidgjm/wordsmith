<template>
  <div v-if="stems.length > 0">
    <el-space :size="30" wrap>
      <WordCard v-for="(o, key) in stems" :key="key" :stem="o.term" class="word-card">
        <template #body>
          <el-menu mode="horizontal">
            <el-menu-item index="1" @click="getWordsByStemId(o.id, key)">
              <!-- <el-icon><View /></el-icon> -->
              <span class="material-symbols-outlined">dictionary</span>
            </el-menu-item>
            <el-menu-item index="2">
              <!-- <el-icon><Link/></el-icon> -->
              <span class="material-symbols-outlined"> linked_services </span>
            </el-menu-item>
            <el-menu-item index="3">
              <el-icon><document /></el-icon>
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

          <div v-for="(word, key) in words[key]" :key="key">
            <el-row>
              <el-col :span="4">
                <el-text class="mx-1" type="info">词性</el-text>
              </el-col>
              <el-col :span="20">
                <el-tag class="ml-2" effect="dark">{{ word.partOfSpeech }}</el-tag>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="4">
                <el-text class="mx-1" type="info">Translation</el-text>
              </el-col>
              <el-col :span="20">{{ word.translation }}</el-col>
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
  </div>
  <div v-else>
    <el-empty :image-size="200" />
  </div>
</template>
<script setup>
import {
  Link,
  Delete,
  Edit,
  Message,
  Search,
  Star,
  View,
  Discount,
  Key
} from '@element-plus/icons-vue'

import { onMounted } from 'vue'
import WordCard from '../components/WordCard.vue'
import { ref } from 'vue'

const stems = ref([])
const words = ref([])
const pagination = ref({
  totalPages: 0,
  totalElements: 0,
  pageNumber: 1,
  pageSize: 10,
  first: false,
  last: false
})

import api from 'axios'

const getStems = async function () {
  const res = await api.get(`/stems/v1?pageNumber=${pagination.value.pageNumber}`)
  stems.value = res.data.data

  pagination.value = res.data.pagination
}

const getWordsByStemId = async function (stemId, index) {
  console.log('getting words for stem %s at %s', stemId, index)
  const res = await api.get(`/stems/v1/${stemId}/words`)
  const clone = words.value.slice()
  clone[index] = res.data.data
  words.value = clone
}

const requestStemsByPage = async function (pageNumber){
  console.log("Page click detected. Current pagination info", pageNumber)
  pagination.value.pageNumber=pageNumber
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
