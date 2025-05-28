<script setup lang="ts">
import HeaderComponent from '../components/HeaderComponent.vue';
import { ref, onMounted, } from 'vue';
import type {Filter} from "@/models/filter.ts";
import {EscapeRoom} from "@/models/escapeRoom.ts";

const escapeRoomsData = ref<EscapeRoom[]>([]);
const filters = ref<Filter[]>([]);
const maxPrice = ref<number>(200); // Single price slider value
const filteredEscapeRooms = ref([...escapeRoomsData.value]);
const expandedCards = ref<{ [key: string]: boolean }>({});

const toggleExpand = (name: string) => {
  expandedCards.value[name] = !expandedCards.value[name];
};

onMounted(async () => {
  filteredEscapeRooms.value = escapeRoomsData.value;

  try {
    const response = await fetch('http://localhost:8080/api/escaperooms');
    if (!response.ok) {
      throw new Error('Failed to fetch escaperooms');
    }
    const data = await response.json();
    escapeRoomsData.value = data.sort((a : Filter, b : Filter) => a.id - b.id);
    console.log(escapeRoomsData)
  } catch (error) {
    console.error(error)
  }

  try {
    const response = await fetch('http://localhost:8080/api/filters');
    if (!response.ok) {
      throw new Error('Failed to fetch filters');
    }
    const data = await response.json();
    filters.value = data.sort((a : Filter, b : Filter) => a.id - b.id);
    console.log(filters.value)
  } catch (error) {
    console.error(error)
  }
});
</script>

<template>
  <HeaderComponent />

  <div class="bg-slate-200 min-h-screen flex flex-col px-6 pt-8">
    <h1 class="text-2xl font-bold w-full text-left mt-2">
      Search for an Escape Room by location.
    </h1>


    <div class="w-full border border-gray-300 bg-white mt-10">
      <div class="px-4 py-2">
        <p>Filters</p>
        <div class="flex flex-wrap gap-4">
          <div v-for="filter in filters" :key="filter.id" class="flex flex-col">
            <div v-if="filter.active === true" class="py-2">
              <label class="block text-sm font-medium mb-1">{{ filter.name }}</label>
              <select v-if="filter.inputType === 'dropdown'" class="border rounded px-3 py-2">
                <option value="">Kies een optie</option>
                <!-- Placeholder options - you can customize this per filter -->
                <option>Optie 1</option>
                <option>Optie 2</option>
              </select>
              <input v-else-if="filter.inputType === 'textbox'" type="text" class="border-2"/>
              <input v-else-if="filter.inputType === 'checkbox'" type="checkbox"/>
            </div>
          </div>
        </div>
        <button>Apply filters</button>
      </div>
    </div>

    <div class="w-full mt-10 mb-5 grid gap-6">
      <div v-for="escaperoom in escapeRoomsData" :key="escaperoom.id"
        class="bg-white p-5 rounded-lg shadow-md border-indigo-400 border">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="text-xl font-bold">{{ escaperoom.name }}</h2>
            <p class="pt-2">{{ escaperoom.address }}, {{ escaperoom.postalcode }} {{ escaperoom.city }}</p>
          </div>
          <a :href="escaperoom.website" target="_blank" class="text-blue-500 underline text-sm">
            Visit Website
          </a>
        </div>

        <div v-if="expandedCards[escaperoom.name]" class="mt-4">
          <div v-for="(game, index) in escaperoom['different-rooms'].filter(r => parseInt(r.price) <= maxPrice)"
               :key="index"
               class="flex justify-between items-center p-3 bg-blue-100 border-b">
            <div>
              <h3 class="font-bold">{{ game.name }}</h3>
              <p class="text-sm text-gray-600">Players: {{ game.players }}</p>
              <p class="text-sm text-gray-600">Duration: {{ game.duration }}</p>
            </div>
            <span class="bg-green-500 text-white px-4 py-2 rounded-md">
              €{{ game.price }}
            </span>
          </div>
        </div>

        <button @click="toggleExpand(escaperoom.name)"
          class="bg-[#2473BA] text-white px-3 py-2 rounded-md hover:bg-blue-600 transition mt-4">
          {{ expandedCards[escaperoom.name] ? "Less Info" : "More Info" }}
        </button>
      </div>
    </div>
  </div>
</template>
