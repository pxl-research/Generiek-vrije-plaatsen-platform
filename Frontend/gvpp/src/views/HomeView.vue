<script setup lang="ts">
import ButtonComponent from '@/components/ButtonComponentSearch.vue'
import HeaderComponent from '@/components/HeaderComponent.vue'
import { useRouter, useRoute } from 'vue-router'
import { sectorConfig } from '@/config/sectorConfig'

const router = useRouter()
const route = useRoute()

const sectorOptions = sectorConfig

const switchSector = (sectorKey: string) => {
  router.push({ query: { ...route.query, sector: sectorKey } })
}
</script>

<template>
  <div class="bg-slate-200 min-h-screen">
    <HeaderComponent />

    <!-- 🔽 Sector Dropdown -->
<div class="flex justify-center p-4">
  <select
    @change="switchSector(($event.target as HTMLSelectElement).value)"
    class="p-2 rounded border border-gray-400"
    :value="route.query.sector || ''"
  >
    <option disabled value="">Select a sector</option>
    <option
      v-for="(sector, key) in sectorOptions"
      :key="key"
      :value="key"
    >
      {{ sector.title }}
    </option>
  </select>
</div>


    <!-- 🔍 Main Title -->
    <h1 class="text-xl font-bold w-auto m-5 text-center">
      Search for an organisation by name, location or filter through the list.
    </h1>

    <!-- 🖼️ Logo -->
    <router-link to="/login">
      <img class="w-50 m-auto" src="/assets/Asset%201.svg" />
    </router-link>

    <!-- 🔘 Search Buttons -->
    <div class="buttons h-120 w-full flex flex-col justify-center items-center -mt-20 gap-12">
      <router-link to="/map">
        <ButtonComponent
          text="Search by location"
          :icon="'M215.7 499.2C267 435 384 279.4 384 192C384 86 298 0 192 0S0 86 0 192c0 87.4 117 243 168.3 307.2c12.3 15.3 35.1 15.3 47.4 0zM192 128a64 64 0 1 1 0 128 64 64 0 1 1 0-128z'"
          class="w-80"
        />
      </router-link>

      <router-link to="/filter">
        <ButtonComponent
          text="Filter through list"
          :icon="'M152.1 38.2c9.9 8.9 10.7 24 1.8 33.9l-72 80c-4.4 4.9-10.6 7.8-17.2 7.9s-12.9-2.4-17.6-7L7 113C-2.3 103.6-2.3 88.4 7 79s24.6-9.4 33.9 0l22.1 22.1 55.1-61.2c8.9-9.9 24-10.7 33.9-1.8zm0 160c9.9 8.9 10.7 24 1.8 33.9l-72 80c-4.4 4.9-10.6 7.8-17.2 7.9s-12.9-2.4-17.6-7L7 273c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l22.1 22.1 55.1-61.2c8.9-9.9 24-10.7 33.9-1.8zM224 96c0-17.7 14.3-32 32-32l224 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-224 0c-17.7 0-32-14.3-32-32zm0 160c0-17.7 14.3-32 32-32l224 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-224 0c-17.7 0-32-14.3-32-32zM160 416c0-17.7 14.3-32 32-32l288 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-288 0c-17.7 0-32-14.3-32-32zM48 368a48 48 0 1 1 0 96 48 48 0 1 1 0-96z'"
          class="w-80"
        />
      </router-link>
    </div>
  </div>
</template>
