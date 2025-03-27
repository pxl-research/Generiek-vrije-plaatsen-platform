<script setup lang="ts">
import HeaderComponent from '../components/HeaderComponent.vue';
import { ref } from 'vue';

const organizations = ref([ //fake data
  {
    name: 'Tech Hub',
    type: 'IT Company',
    address: '123 Tech Street',
    postalCode: '1000',
    city: 'Brussels',
    website: 'https://techhub.com',
    sections: [
      { name: "Section in organisation", status: "Available", date: "14/03/2025", color: "green" },
      { name: "Section in organisation", status: "Full", date: "14/03/2025", color: "red" },
      { name: "Section in organisation", status: "Available", date: "14/03/2025", color: "green" },
      { name: "Section in organisation", status: "Available", date: "14/03/2025", color: "green" }
    ],
  },
  {
    name: 'Eco Solutions',
    type: 'Non-Profit',
    address: '45 Green Lane',
    postalCode: '2000',
    city: 'Antwerp',
    website: 'https://ecosolutions.org',
    sections: [
      { name: "Section in organisation", status: "Available", date: "14/03/2025", color: "green" },
      { name: "Section in organisation", status: "Full", date: "14/03/2025", color: "red" },
      { name: "Section in organisation", status: "Available", date: "14/03/2025", color: "green" },
      { name: "Section in organisation", status: "Available", date: "14/03/2025", color: "green" }
    ],
  }
]);

const searchQuery = ref('');
const filteredOrganizations = ref([...organizations.value]);
const expandedCards = ref<{ [key: string]: boolean }>({});

const search = () => { //zoekfunctie
  filteredOrganizations.value = organizations.value.filter(org =>
    org.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
};

const toggleExpand = (name: string) => {
  expandedCards.value[name] = !expandedCards.value[name];
};


</script>

<template>
  <HeaderComponent />


  <div class="bg-slate-200 min-h-screen flex flex-col px-6 pt-8">
    <h1 class="text-2xl font-bold w-full text-left mt-2">
      Search for an organisation by name.
    </h1>

    <!-- zoeken -->
    <div class="w-full flex items-center  mt-4">
      <input v-model="searchQuery" type="text" placeholder="Name"
        class="w-full  p-3.5  border border-gray-400 rounded-l-lg rounded-r-none bg-slate-300 focus:outline-none focus:ring-2 focus:ring-blue-500" />
      <button @click="search"
        class="bg-[#2473BA]  text-white px-5 py-4 flex-grow max-w-[180px] rounded-r-lg rounded-l-none hover:bg-blue-600 transition whitespace-nowrap">Show
        Results
      </button>
    </div>

    <!-- resultaten cards -->
    <div class="w-full mt-10 mb-5 grid gap-6 ">
      <div v-for="org in filteredOrganizations" :key="org.name"
        class="bg-white p-5   rounded-lg shadow-md border-indigo-400 border ">
        <h2 class="text-xl font-bold">{{ org.name }}</h2>
        <p class="text-sm text-gray-500">{{ org.type }}</p>
        <p class="pt-2">{{ org.address }}, {{ org.postalCode }} {{ org.city }}</p>
        <a :href="org.website" target="_blank" class="text-blue-500 underline text-sm block mt-2 mb-8">
          {{ org.website }}
        </a>

        <div class="grid transition-all duration-500 ease-in-out"
          :class="expandedCards[org.name] ? 'auto-rows-[1fr] opacity-100' : 'auto-rows-[0fr] opacity-0'">

          <div v-if="expandedCards[org.name]" class="overflow-hidden -ml-5 -mr-5">
            <div v-for="(section, index) in org.sections" :key="index" class="flex justify-between items-center p-3 "
              :class="index % 2 === 0 ? 'bg-blue-100' : 'bg-white'">
              <span class="p-2">{{ section.name }}</span>
              <div class="flex flex-col items-end">
                <button class="text-white text-sm px-4 py-2 rounded-md"
                  :class="section.color === 'green' ? 'bg-green-500' : 'bg-red-500'">
                  {{ section.status }}
                </button>
                <p v-if="section.status === 'Full'" class="text-xs text-gray-500 mt-1">
                  at {{ section.date }}
                </p>
              </div>
            </div>
          </div>
        </div>

        <!-- Button to Expand/Collapse -->
        <div class="bg-blue-100 w-[calc(100%+2.5rem)] p-5 -mb-5 -mx-5 rounded-b-lg flex justify-center">
          <button @click="toggleExpand(org.name)"
            class="bg-[#2473BA] text-white px-3 py-2 rounded-md hover:bg-blue-600 transition">
            {{ expandedCards[org.name] ? "Less Info" : "More Info" }}
          </button>
        </div>
      </div>


    </div>
  </div>


</template>
