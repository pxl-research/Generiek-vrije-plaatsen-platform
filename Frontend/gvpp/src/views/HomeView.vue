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

const currentSector = route.query.sector as string
</script>

<template>
  <div class="bg-slate-200 min-h-screen">
    <HeaderComponent />

    <!-- 🏷️ Section Title -->
    <h2 class="text-2xl font-bold text-center pt-6 mb-4 text-gray-800">
      Choose your sector
    </h2>

    <!-- 🟦 Sector Cards Grid -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 p-6 max-w-5xl mx-auto">
      <div
        v-for="(sector, key) in sectorOptions"
        :key="key"
        :class="[
          'rounded-lg shadow-md p-6 flex flex-col items-center justify-between space-y-4 transition-transform hover:scale-105 cursor-pointer',
          sector.bgColor,
          currentSector === key ? 'ring-4 ring-yellow-400' : ''
        ]"
      >
        <img
          :src="sector.logo"
          alt="sector logo"
          class="w-16 h-16 rounded-full object-cover"
        />
        <h2 class="text-lg font-semibold text-white text-center">
          {{ sector.title }}
        </h2>
        <button
          @click="switchSector(key)"
          class="px-4 py-2 bg-slate-100 text-gray-800 font-semibold rounded-md hover:bg-white transition"
        >
          Select
        </button>
      </div>
    </div>

    <!-- 🔍 Main Title -->
    <h1 class="text-xl font-bold w-auto m-5 text-center">
      Search for an organisation by name, location or filter through the list.
    </h1>

    <!-- 🖼️ Logo -->
    <router-link to="/login">
      <img class="w-75 m-auto" src="../../public/assets/logo3.png" />
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
