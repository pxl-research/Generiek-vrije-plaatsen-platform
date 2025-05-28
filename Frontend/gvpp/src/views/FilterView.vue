<script setup lang="ts">
import HeaderComponent from '../components/HeaderComponent.vue';
import { ref, onMounted, computed } from 'vue';
import escapeRooms from '../data/escaperooms.json';

const escapeRoomsData = ref(escapeRooms);
const searchQuery = ref('');
const maxPrice = ref<number>(200); // Single price slider value
const players = ref<number | null>(null);
const filteredEscapeRooms = ref([...escapeRoomsData.value]);
const expandedCards = ref<{ [key: string]: boolean }>({});
const showSuggestions = ref(false);

const search = () => {
  filteredEscapeRooms.value = escapeRoomsData.value.filter(room => {
    const matchesCity = room.city.toLowerCase().includes(searchQuery.value.toLowerCase());
    const matchesRoom = room["different-rooms"].some(r => {
      const matchesPrice = parseInt(r.price) <= maxPrice.value;

      const matchesPlayers = players.value ? (() => {
        const [min, max] = r.players.split('-').map(p => parseInt(p));
        return players.value! >= min && players.value! <= max;
      })() : true;

      return matchesPrice && matchesPlayers;
    });
    return matchesCity && matchesRoom;
  });
};

const toggleExpand = (name: string) => {
  expandedCards.value[name] = !expandedCards.value[name];
};

const suggestedLocations = computed(() => {
  const query = searchQuery.value.toLowerCase();
  if (!query) return [];
  return [...new Set(escapeRoomsData.value.map(room => room.city))]
    .filter(city => city.toLowerCase().includes(query));
});

const selectLocation = (city: string) => {
  searchQuery.value = city;
  showSuggestions.value = false;
  search();
};

onMounted(() => {
  filteredEscapeRooms.value = escapeRoomsData.value;
});
</script>

<template>
  <HeaderComponent />

  <div class="bg-slate-200 min-h-screen flex flex-col px-6 pt-8">
    <h1 class="text-2xl font-bold w-full text-left mt-2">
      Search for an Escape Room by location.
    </h1>


    <div class="w-full grid grid-cols-1 md:grid-cols-3 lg:grid-cols-3 gap-4 mt-4">

      <div class="relative">
        <input v-model="searchQuery" @input="showSuggestions = true; search()" type="text" placeholder="City"
          class="w-full p-3.5 border border-gray-400 rounded-lg bg-slate-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:bg-slate-300 hover:bg-slate-300"/>
        <ul v-if="showSuggestions && suggestedLocations.length" class="absolute bg-white border border-gray-300 w-full mt-1 rounded-lg shadow-md z-10">
          <li v-for="(city, index) in suggestedLocations" :key="index" @click="selectLocation(city)"
            class="p-2 hover:bg-blue-100 cursor-pointer">
            {{ city }}
          </li>
        </ul>
      </div>

      <div>
        <p class="font-bold">Max Price: €{{ maxPrice }}</p>
        <input type="range" min="0" max="200" step="5" v-model="maxPrice" @input="search"
          class="w-full mt-2 cursor-pointer" />
      </div>

      <div>
        <p class="font-bold">Players</p>
        <select v-model="players" @change="search" class="w-full p-2 border border-gray-400 rounded-lg hover:bg-slate-300 cursor-pointer">
          <option :value="null" class="bg-white">Any</option>
          <option v-for="num in [2, 3, 4, 5, 6, 7, 8]" :key="num" :value="num" class="bg-white">{{ num }} Players</option>
        </select>
      </div>
    </div>

    <div class="w-full mt-10 mb-5 grid gap-6">
      <div v-for="room in filteredEscapeRooms" :key="room.id"
        class="bg-white p-5 rounded-lg shadow-md border-indigo-400 border">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="text-xl font-bold">{{ room.name }}</h2>
            <p class="pt-2">{{ room.address }}, {{ room.postalcode }} {{ room.city }}</p>
          </div>
          <a :href="room.website" target="_blank" class="text-blue-500 underline text-sm">
            Visit Website
          </a>
        </div>

        <div v-if="expandedCards[room.name]" class="mt-4">
          <div v-for="(game, index) in room['different-rooms'].filter(r => parseInt(r.price) <= maxPrice)"
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

        <button @click="toggleExpand(room.name)"
          class="bg-[#2473BA] text-white px-3 py-2 rounded-md hover:bg-blue-600 transition mt-4">
          {{ expandedCards[room.name] ? "Less Info" : "More Info" }}
        </button>
      </div>
    </div>
  </div>
</template>
