
<template>
  <div>
    <span class="material-symbols-sharp" style="font-size: 96px;color: #409EFF;">face</span>
  </div>
  <div class="demo-progress">
    <el-progress type="circle" :percentage="0" />
    <el-progress type="circle" :percentage="25" />
    <el-progress type="circle" :percentage="100" status="success" />
    <el-progress type="circle" :percentage="70" status="warning" />
    <el-progress type="circle" :percentage="50" status="exception" />
  </div>
  <div class="demo-progress">
    <el-progress :text-inside="true" :stroke-width="26" :percentage="70" />
    <el-progress
      :text-inside="true"
      :stroke-width="24"
      :percentage="100"
      status="success"
    />
    <el-progress
      :text-inside="true"
      :stroke-width="22"
      :percentage="80"
      status="warning"
    />
    <el-progress
      :text-inside="true"
      :stroke-width="20"
      :percentage="50"
      status="exception"
    />
  </div>
</template>
<style scoped>
.demo-progress .el-progress--circle {
  margin-right: 15px;
}

.demo-progress .el-progress--line {
  margin-bottom: 15px;
  width: 350px;
}

</style>
