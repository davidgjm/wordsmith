<script setup>
import { ref } from 'vue'
import { RouterView, RouterLink } from 'vue-router'
import MaterialSymbol from '../icons/MaterialSymbol.vue'
import IpaChart from '../IpaChart.vue'
const showIpaChart = ref(false)
</script>
<template>
  <main>
    <el-container>
      <el-header height="200">
        <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
          <el-menu-item index="0">
            <div>
              <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="60" height="60" />
            </div>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="1">
            <RouterLink to="/"><MaterialSymbol class="primary xlarge" icon="home" /></RouterLink>
          </el-menu-item>
          <el-menu-item index="2">
            <RouterLink to="/admin"
              ><MaterialSymbol class="primary xlarge" icon="edit"
            /></RouterLink>
          </el-menu-item>
          <el-menu-item index="4" @click="showIpaChart = true">
            <div>
              <MaterialSymbol icon="mic" class="primary xlarge" />
            </div>
          </el-menu-item>
          <el-menu-item>
            <RouterLink to="/about">About</RouterLink>
          </el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <RouterView />
        <el-drawer v-model="showIpaChart" title="IPA" :with-header="false">
          <IpaChart />
        </el-drawer>
      </el-main>
    </el-container>
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}
.logo {
  display: inline-block;
  margin: 0 auto 1rem;
}
nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}
</style>
