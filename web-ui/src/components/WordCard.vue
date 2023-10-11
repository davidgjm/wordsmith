<script setup>
import { Check, Delete, Edit, Message, Search, Star, View, Discount } from '@element-plus/icons-vue'
defineProps(['id','stem'])
</script>
<template>
  <el-card shadow="hover" class="box-card">
    <template #header>
      <div class="card-header">
        <span class="material-symbols-outlined" style="font-size: 32px; color: #409eff"
          >crossword</span
        >
        <el-text class="mx-1" type="primary" size="large" tag="b" style="font-weight: bold">
          {{ stem }}
        </el-text>

        <el-button type="warning" :icon="View" circle />
        <div>
          <el-dropdown split-button type="success">
            <span class="material-symbols-outlined">shopping_cart_checkout</span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>Action 1</el-dropdown-item>
                <el-dropdown-item>Action 2</el-dropdown-item>
                <el-dropdown-item>Action 3</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </template>
    <div class="body">
      <slot name="body"> </slot>
    </div>
  </el-card>
</template>
<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
