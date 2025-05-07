<template>
  <header
  :class="['flex justify-between items-center p-4 top-0 left-0 right-0 z-10', (sectorStyles as any).bgColor]"
>
  <div class="flex items-center">
    <img :src="sectorStyles.logo" alt="sector logo" class="h-10 w-10 mr-2" />
    <router-link to="/">
      <h1 class="text-2xl font-bold">{{ sectorStyles.title }}</h1>
    </router-link>
  </div>
</header>
  </template>


  <script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { sectorConfig } from '@/config/sectorConfig';

const route = useRoute();

// Assume the sector is passed as a route param or query like `/spots?sector=escaperooms`
const sector = computed(() => route.query.sector || 'escaperooms');

// Fallback if sector not defined in config
const sectorStyles = computed(() => sectorConfig[sector.value as string] || sectorConfig.escaperooms);
  </script>
