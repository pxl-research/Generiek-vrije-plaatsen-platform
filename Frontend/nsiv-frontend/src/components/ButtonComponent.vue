<template>
  <button
    :class="[
      'py-4 px-4 rounded-lg text-xl font-semibold hover:opacity-80 transition',
      'bg-[#2473BA] text-white',
      $attrs.class
    ]"
  >
    {{ text }}
  </button>
</template>

<script setup lang="ts">
const props = defineProps<{ text: string }>()
</script>
