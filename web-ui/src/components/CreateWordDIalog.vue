<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue'
import { ElInput, ElMessage, ElMessageBox } from 'element-plus'

import api from 'axios'

defineProps({
  partOfSpeechValue: String,
  dialogFormVisible: {
    type: Boolean,
    required: true
  }
})

const partsOfSpeech = ref([])

const loadPartsOfSpeech = async function () {
  try {
    const result = await api.get('/metadata/v1/parts-of-speech')
    partsOfSpeech.value= result.data.data
  } catch (error) {
    if (error.response) {
      ElMessage({
        type: 'error',
        message: `Response error ${error.response.status},\n${error.response.data.detail}`
      })
    }
  }
}

// const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
const form = reactive({
  stem: {
    term: '',
    ipa: '',
    note: ''
  },
  partOfSpeech: '',
  ipa: '',
  variants: [],
  translation: '',
  explanation: '',
  example: ''
})

const rules = reactive({
  term: [{ required: true,trigger: blur }],
  translation: [{ required: true }],
  partOfSpeech: [{required: true}]
})

const submitNewWordForm = async function(){
  // dialogFormVisible=false
  console.log('about to submit new word form', form)
}


onMounted(() => {
  console.log(`the component is now mounted.`)
  loadPartsOfSpeech()
})
</script>
<template>
  <el-dialog v-model="dialogFormVisible" title="Shipping address">
    <el-form :model="form" label-position="left" :rules="rules">
      <el-card>
        <el-form-item label="Term" :label-width="formLabelWidth" prop="term">
          <el-input v-model="form.stem.term" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Term IPA" :label-width="formLabelWidth">
          <el-input v-model="form.stem.pa" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Term Note" :label-width="formLabelWidth">
          <el-input v-model="form.stem.note" autocomplete="off" />
        </el-form-item>
      </el-card>

      <el-card>
        <el-form-item label="Part of Speech" :label-width="formLabelWidth" prop="partOfSpeech">
          <el-select v-model="form.partOfSpeech" placeholder="Please select a part of speech">
            <el-option
              v-for="item in partsOfSpeech"
              :key="item.code"
              :label="item.description + '(' + item.code + ')'"
              :value="item.code"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="IPA" :label-width="formLabelWidth">
          <el-input v-model="form.ipa" autocomplete="off" />
        </el-form-item>
        
        <el-form-item label="Translation" :label-width="formLabelWidth" prop="translation">
          <el-input v-model="form.translation" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Explanation" :label-width="formLabelWidth">
          <el-input v-model="form.explanation" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Example" :label-width="formLabelWidth">
          <el-input v-model="form.example" type="textarea" rows="2" autocomplete="off" />
        </el-form-item>
      </el-card>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitNewWordForm"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style scoped>
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>