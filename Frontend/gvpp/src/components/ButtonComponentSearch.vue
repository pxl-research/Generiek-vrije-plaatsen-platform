<script setup lang="ts">
const props = defineProps<{
  text: string;
  icon: string;
}>();
</script>
<template>
  <button
    :class="[
      'group py-8 px-4 rounded-4xl text-2xl font-semibold  transition',
      'bg-sky-700 text-white  hover:bg-sky-200 hover:text-black',
      'flex gap-10 pl-4 ',
      $attrs.class
    ]"
  >
    <!-- Render the SVG icon dynamically -->
    <svg
      v-if="props.icon"
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 512 512"
      :width="30"
      class="fill-current text-white group-hover:text-black -mr-1 ml-0.5"
    >
     <path :d="props.icon" />
    </svg>

    <!-- Button text -->
    {{ props.text }}
  </button>
</template>
