<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import MaterialSymbol from './icons/MaterialSymbol.vue'
defineProps({
  id: Number,
  stem: String,
  showWord: Function
})

import api from 'axios'

const postChallengeResult = async function (stemId, result, note) {
  console.log('Recording challenge record for stem with result', stemId, result)
  var record = {
    stemId: stemId,
    result: result,
    note: note
  }

  try {
    await api.post('/challenges/v1', record)
    ElMessage({
      type: 'success',
      message: `Challenge record for stem ${stemId} (${result}) updated!`
    })
  } catch (error) {
    if (error.response) {
      ElMessage({
        type: 'error',
        message: `Response error ${error.response.status},\n${error.response.data.detail}`
      })
    }
  }
}

const showPostChallengeDialogWithNote = function (stemId, result) {
  ElMessageBox.prompt('Add Note', result, {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputPattern: /[\w ]*/,
    inputErrorMessage: 'Invalid comment'
  })
    .then(({ value }) => postChallengeResult(stemId, result, value))
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'Input canceled'
      })
    })
}
</script>
<template>
  <el-card shadow="hover" class="box-card">
    <template #header>
      <div class="card-header">
        <MaterialSymbol icon="crossword" class="primary large" />
        <el-text class="mx-1 header" type="primary" tag="b">
          {{ stem }}
        </el-text>

        <div>
          <el-button-group>
            <el-tooltip content="Passed!">
              <el-button type="success"
                ><MaterialSymbol
                  icon="verified"
                  @click="showPostChallengeDialogWithNote(id, 'PASSED')"
              /></el-button>
            </el-tooltip>
            <el-tooltip content="未通过">
              <el-button type="warning"
                ><MaterialSymbol
                  icon="close"
                  @click="showPostChallengeDialogWithNote(id, 'FAILED')"
              /></el-button>
            </el-tooltip>
            <el-tooltip content="反复遗忘">
              <el-button type="danger"
                ><MaterialSymbol
                  icon="replay"
                  @click="showPostChallengeDialogWithNote(id, 'REGRESSION')"
              /></el-button>
            </el-tooltip>
          </el-button-group>
        </div>
      </div>
    </template>
    <div class="body">
      <slot name="body"> </slot>
    </div>
  </el-card>
</template>
<style scoped>
.header {
  font-weight: bold;
  font-size: x-large;
}
</style>
