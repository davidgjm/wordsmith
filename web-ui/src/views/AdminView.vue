<script setup>
import { ref, onMounted, nextTick } from 'vue'
import { ElInput, ElMessage, ElMessageBox } from 'element-plus'
import { Check, EditPen, Plus } from '@element-plus/icons-vue'
import MaterialSymbol from '../components/icons/MaterialSymbol.vue'
import api from 'axios'

const keyword = ref('')
const selectedStems = ref([])


const clearSelectedWords = function () {
  selectedStems.value = []
}

const toggleEditMode = function (word) {
  word.editMode = !word.editMode
}

const findWordsbyStem = async function () {
  let len = keyword.value.length
  console.log('keyword', keyword.value.length)
  if (len == 0) {
    return false
  }

  if (len < 3) {
    keyword.value = ''
    ElMessage({
      type: 'error',
      message: 'Keyword too short'
    })
    return false
  }
  const res = await api.get(`/words/v1/search/words?keyword=${keyword.value}`)
  const data = res.data.data
  selectedStems.value = data

  console.log(data)
  data.forEach((element) => {
    element.editMode=false
    element.words.forEach((word) => {
      word.editMode = false
    })
  })

}
const showEditStem = (stem) => {
  console.log('stem edit mode', stem)
  stem.editMode = !stem.editMode
}

const showAddVariantPrompt = (word) => {
  ElMessageBox.prompt('Please input variant', 'Tip', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputPattern: /[\w ]{3,}/,
    inputErrorMessage: 'Invalid variant'
  })
    .then(({ value }) => {
      if (!word.variants.includes(value)) {
        word.variants.push(value)
      }
      console.log('updated word', selectedStems.value)
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'Input canceled'
      })
    })
}

const updateStem = async (stem) => {
  console.log('stem to be updated', stem)
  try {
    await api.put(`/stems/v1/${stem.id}`, stem)
    ElMessage({
        type: 'success',
        message: `Stem ${stem.id} (${stem.term}) updated successfully!`
      })
      stem.editMode=false
  } catch (error) {
    if (error.response) {
      ElMessage({
        type: 'error',
        message: `Response error ${error.response.status},\n${error.response.data.detail}`
      })
    }
  }
}

const updateWord = async function (word) {
  try {
    await api.put(`/words/v1/${word.id}`, word)
    ElMessage({
        type: 'success',
        message: `Word ${word.id} (${word.translation}) updated successfully!`
      })
    word.editMode=false
  } catch (error) {
    if (error.response) {
      ElMessage({
        type: 'error',
        message: `Response error ${error.response.status},\n${error.response.data.detail}`
      })
    }
  }
}

const removeVariant = function (tag, word) {
  console.log('word to be updated', word)
  console.log('stems', selectedStems.value)

  word.variants = word.variants.filter((item) => !item.match(tag))
}
</script>
<template>
  <div style="padding-bottom: 1rem">
    <el-input
      v-model="keyword"
      ref="searchBox"
      size="large"
      min="3"
      label="asdfadsf"
      @change="findWordsbyStem"
      @clear="clearSelectedWords"
      clearable
      style="width: 400px"
      placeholder="Please input stem"
    >
      <template #prefix>
        <el-icon class="el-input__icon"><search /></el-icon>
      </template>
    </el-input>
  </div>

  <el-tabs tab-position="left" class="demo-tabs">
    <el-tab-pane v-for="stem in selectedStems" :key="stem.id" :label="stem.term">
      <el-space :size="30" direction="vertical">
        <el-space direction="vertical">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <div>
                  <MaterialSymbol class="primary large" icon="font_download" style="margin-right: 1rem;" />
                  <el-text class="mx-1" type="success" style="font-size: xx-large;">{{ stem.term }}</el-text>
                </div>
                <div>
                  <el-button type="primary" :icon="EditPen" @click="showEditStem(stem)"  />
                  <el-button type="success" :icon="Check" @click="updateStem(stem)" />
                </div>
              </div>
            </template>
            <el-row>
              <el-col :span="4">
                <el-text class="mx-1" type="info">IPA</el-text>
              </el-col>
              <el-col :span="20">
                <div v-if="stem.editMode" >
                  <el-input v-model="stem.ipa" placeholder="Please input" class="m-2">
                    <template #prepend>IPA</template>
                  </el-input>
                </div>
                <el-text v-else-if="stem.ipa" class="mx-1" type="danger" tag="i">/{{ stem.ipa }}/</el-text>
              </el-col>
            </el-row>
            <el-row style="margin-top: 0.5rem;">
              <el-col :span="4">
                <el-text class="mx-1" type="info">Note</el-text>
              </el-col>
              <el-col :span="20">
                <el-input v-if="stem.editMode" v-model="stem.note"></el-input>
                <el-text class="mx-1" type="info" v-else>{{ stem.note }}</el-text>
              </el-col>
            </el-row>
          </el-card>
          <el-card v-for="(word,key) in stem.words" :key="key" class="box-card">
            <template #header>
              <div class="card-header">
                <div>
                  <MaterialSymbol class="primary large" icon="translate" style="margin-right: 1rem;" />
                  <el-tag class="ml-2" type="info">{{ word.id }}/{{ key }}</el-tag>
                  <el-text v-if="word.ipa" class="mx-1" type="danger" tag="i" style="margin: 0 0.5rem 0 0.5rem;">/{{ word.ipa }}/</el-text>
                  <el-tag class="ml-2" effect="dark" type="warning" style="margin: 0 0.5rem 0 0.5rem;">{{ word.partOfSpeech }}</el-tag>
                  <el-text class="mx-2" type="primary" size="large" tag="i">{{ word.translation }}</el-text>
                </div>
                <div>
                  <el-button type="primary" :icon="EditPen" @click="toggleEditMode(word)" circle />
                  <el-button type="success" :icon="Check" @click="updateWord(word)" circle />
                </div>
              </div>
            </template>
            <el-row>
              <el-col :span="4">
                <el-input v-if="word.editMode" v-model="word.ipa" />
                <div v-else-if="word.ipa">
                  <el-text class="mx-1" type="danger" tag="i">/{{ word.ipa }}/</el-text>
                </div>
              </el-col>
              <el-col :span="20">
                <el-input v-if="word.editMode" v-model="word.translation" />
                <el-text v-else>{{ word.translation }}</el-text>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="4">
                <el-text class="mx-1" type="info">相关变化</el-text>
              </el-col>
              <el-col :span="20">
                <div v-if="word.variants != null && word.variants.length > 0"></div>
                <span v-else> </span>
                <el-tag
                  v-for="tag in word.variants"
                  :key="tag"
                  class="mx-1"
                  :closable="word.editMode"
                  :disable-transitions="false"
                  @close="removeVariant(tag, word)"
                >
                  {{ tag }}
                </el-tag>
                <el-button
                  v-if="word.editMode"
                  :style="{ 'margin-left': word.variants.length > 0 ? 1 : 0 + 'rem' }"
                  type="primary"
                  class="end ml-1"
                  size="small"
                  :icon="Plus"
                  @click="showAddVariantPrompt(word)"
                  circle
                />
              </el-col>
            </el-row>

            <el-row style="padding-top: 10px">
              <el-col :span="4">
                <el-text class="mx-1" type="info">解释</el-text>
              </el-col>
              <el-col :span="20">
                <el-input v-if="word.editMode" v-model="word.explanation" />
                <el-text v-else> {{ word.explanation }}</el-text>
              </el-col>
            </el-row>

            <el-row style="padding-top: 10px">
              <el-col :span="4">
                <el-text class="mx-1" type="info">示例</el-text>
              </el-col>
              <el-col :span="20">
                <el-input
                  autosize
                  type="textarea"
                  v-if="word.editMode"
                  v-model="word.example"
                />
                <el-text v-else> {{ word.example }}</el-text>
              </el-col>
            </el-row>
          </el-card>
        </el-space>
      </el-space>
    </el-tab-pane>
  </el-tabs>
</template>
<style>
.box-card {
  width: 800px;
}
</style>
