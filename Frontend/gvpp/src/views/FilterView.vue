<script setup lang="ts">
import HeaderComponent from '../components/HeaderComponent.vue';
import { ref, onMounted, } from 'vue';
import type {Filter} from "@/models/filter.ts";
import {EscapeRoom} from "@/models/escapeRoom.ts";
import {useFilterStore} from "@/store/FilterStore.ts";
import {useEscapeRoomStore} from "@/store/EscapeRoomStore.ts";

const filterStore = useFilterStore();
const escapeRoomStore = useEscapeRoomStore();

const escapeRoomsData = ref<EscapeRoom[]>([]);
const filters = ref<Filter[]>([]);
const expandedCards = ref<{ [key: string]: boolean }>({});

const toggleExpand = (name: string) => {
  expandedCards.value[name] = !expandedCards.value[name];
};

async function getFiltersFromBackend() {
  filters.value = await filterStore.getFilters();
  filters.value = filters.value.sort((a, b) => a.id - b.id);
}

async function getEscapeRoomsFromBackend() {
  escapeRoomsData.value = await escapeRoomStore.getEscapeRooms();
  escapeRoomsData.value = escapeRoomsData.value.sort((a, b) => a.id - b.id);
}

onMounted(async () => {
  await getFiltersFromBackend();
  await getEscapeRoomsFromBackend();
});
</script>

<template>
  <HeaderComponent />

  <div class="bg-slate-200 min-h-screen flex flex-col px-6 pt-8">
    <h1 class="text-2xl font-bold w-full text-left mt-2">
      Zoek een Escape Room met locatie.
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
        <button class="mt-4 border border-gray-400 rounded px-4 py-2 bg-white hover:bg-gray-100">Pas filters toe</button>
      </div>
    </div>

    <div class="w-full mt-10 mb-5 grid gap-6">
      <div v-for="escaperoom in escapeRoomsData" :key="escaperoom.id"
        class="bg-white p-5 rounded-lg shadow-md border-indigo-400 border">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="text-xl font-bold">{{ escaperoom.name }}</h2>
            <p class="pt-2">{{ escaperoom.address }}, {{ escaperoom.postalCode }} {{ escaperoom.city }}</p>
          </div>
          <a :href="escaperoom.website" target="_blank" class="text-blue-500 underline text-sm">
            Visit Website
          </a>
        </div>

        <div v-if="expandedCards[escaperoom.name]" class="mt-4">
          <div
            v-for="room in escaperoom.rooms"
            :key="room.id"
            class="flex justify-between items-center p-3 bg-blue-100 border-b"
          >
            <div>
              <h3 class="font-bold">{{ room.name }}</h3>
              <p class="text-sm text-gray-600">Minimumleeftijd: {{ room.minimumAge }}</p>
              <p class="text-sm text-gray-600">Duratie: {{ room.duration }} uur</p>
            </div>
            <span class="bg-green-500 text-white px-4 py-2 rounded-md">
              Capaciteit: {{ room.maxCapacity - room.currentCapacity }}/{{ room.maxCapacity }}
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

