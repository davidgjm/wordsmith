<script setup>
import { inject } from 'vue'
// import QuickStartLayout from './components/layouts/QuickStartLayout.vue'
import ElementLayout from './components/layouts/ElementLayout.vue'

const axios = inject('axios')
</script>

<template>
  <ElementLayout/>
</template>

<style scoped>
</style>
