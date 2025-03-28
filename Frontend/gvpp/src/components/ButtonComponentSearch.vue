<script setup lang="ts">
const props = defineProps<{
  text: string;
  icon: string;
}>();
</script>
<template>
  <button
    :class="[
      'py-8 px-4 rounded-4xl text-2xl font-semibold hover:opacity-80 transition',
      'bg-[#0072BB] text-white',
      'flex gap-10 pl-4 ',
      $attrs.class
    ]"
  >
    <!-- Render the SVG icon dynamically -->
    <svg v-if="props.icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" :width="30" :class="['-mr-1', 'ml-0.5']">
      <path :d="props.icon" fill="white"/>
    </svg>
    <!-- Button text -->
    {{ props.text }}
  </button>
</template>