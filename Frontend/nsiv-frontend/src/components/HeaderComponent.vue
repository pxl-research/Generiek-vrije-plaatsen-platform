<template>
  <header class="flex justify-between items-center p-4 fixed top-0 left-0 right-0 z-10 bg-slate-20">
    <div class="flex items-center mt-4">
      <img src="/assets/nsiv_logo_v1.png" alt="Logo" class="h-18 w-auto" />
    </div>


  </header>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const menuOpen = ref(false)
const toggleMenu = (): void => {
  menuOpen.value = !menuOpen.value
}
</script>
