<script setup>
import TheWelcome from '../components/TheWelcome.vue'
</script>
<template>
  <div class="about">
    <main>
    <h1>This is an about page</h1>
      <TheWelcome />
    </main>
  </div>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    /* display: flex; */
    /* align-items: center; */
  }
}
</style>
