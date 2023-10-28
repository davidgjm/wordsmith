<script setup>
import { nextTick, ref } from 'vue'
import { ElInput } from 'element-plus'
import MaterialSymbol from './icons/MaterialSymbol.vue'

defineProps({
  id: String,
  stem: String,
  showWord: Function,
  word: {
    id: {
      type: number
    }
  }
})
const word = ref({})

const inputValue = ref('')
const dynamicTags = ref(['Tag 1', 'Tag 2', 'Tag 3'])
const inputVisible = ref(false)
const InputRef = ref<InstanceType<typeof ElInput>>()

const handleClose = (tag: string) => {
  dynamicTags.value.splice(dynamicTags.value.indexOf(tag), 1)
}

const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value!.input!.focus()
  })
}

const handleInputConfirm = () => {
  if (inputValue.value) {
    dynamicTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}


</script>
<template>
  <el-space direction="vertical">
    <div>
      <el-text class="mx-1" type="info">IPA</el-text>
    </div>
    <div>{{ o.ipa != null ? o.ipa : '' }}</div>
  </el-space>

  <el-row>
      <el-col :span="4">
        <el-text class="mx-1" type="info">词性</el-text>
      </el-col>
      <el-col :span="20">
        <el-tag class="ml-2" effect="dark">{{ word.partOfSpeech }}</el-tag>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <el-tag
          v-for="tag in word.variants"
          :key="tag"
          class="mx-1"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)"
        >
          {{ tag }}
        </el-tag>
        <el-input
          v-if="inputVisible"
          ref="InputRef"
          v-model="inputValue"
          class="ml-1 w-20"
          size="small"
          @keyup.enter="handleInputConfirm"
          @blur="handleInputConfirm"
        />
        <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
          + New Tag
        </el-button>
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
</template>
