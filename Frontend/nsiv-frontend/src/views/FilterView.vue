<script setup lang="ts">
import { ref, computed } from "vue";
import schoolsData from "../data/schools.json";

interface School {
  id: number;
  name: string;
  address: string;
  postalCode: string;
  region: string;
  city: string;
  website: string;
  email: string;
  phoneNumber: string;
  institutionNumber: string;
  establishmentNumbers: string[];
  niveau: string;
  level: string;
  type: string;
  leerjaar: number;
}

const optionschool = ref<{ schools: School[] }>(schoolsData);
const input = ref("");
const showCityList = ref(true);
const schooljaar = ref("");
const niveau = ref("");
const schooltype = ref("");
const showResults = ref(false);


const setSchooljaar = (value: string) => {
  schooljaar.value = schooljaar.value === value ? "" : value;
};


const setNiveau = (value: string) => {
  niveau.value = niveau.value === value ? "" : value;
}


const setSchooltype = (value: string) => {
  schooltype.value = schooltype.value === value ? "" : value;
}


const toggleResults = () => {
  showResults.value = true;
}

const uniqueCities = computed(() => {
  const cities = optionschool.value.schools.map((option: School) => option.city);
  return Array.from(new Set(cities));
});


const filteredCities = computed(() => {
  return uniqueCities.value.filter((city) =>
    city.toLowerCase().includes(input.value.toLowerCase())
  );
});


const selectCity = (city: string) => {
  input.value = city;
  showCityList.value = false;
};


const removeCity = () => {
  input.value = "";
}


const filteredSchools = computed(() => {
  return optionschool.value.schools.filter(
    (school) =>
      (school.city === input.value) &&
      (school.niveau === niveau.value || niveau.value === '') &&
      (school.type === schooltype.value || schooltype.value === '')
  );
});
</script>




<template>
  <div class="min-h-screen flex flex-col px-6 pt-12 bg-slate-200 ">
    <header class="flex justify-between items-center p-4 fixed top-0 left-0 right-0 z-10 bg-slate-200">
      <div class="flex items-center mt-3">
        <router-link to="/">
          <img src="/assets/nsiv_logo_v1.png" alt="Logo" class="h-18 w-auto" />
        </router-link>
      </div>
    </header>

    <p class="text-3xl text-gray-800 text-bold ml-3 mt-20">Zoek vrije plaatsen</p>
    <p class="ml-3 text-gray-700 w-75 mt-5 mb-5">Selecteer de steden/gemeenten waar je je kinderen graag naar school
      laat gaan.</p>

    <div class="relative w-full h-90 overflow-hidden ">
      <iframe
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d80478.5673049272!2d5.231144139688511!3d50.9245452049915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c12183ded75db7%3A0xf7cb7b027e7e2181!2sHasselt!5e0!3m2!1sen!2sbe!4v1741168303625!5m2!1sen!2sbe"
        class="w-screen h-75" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
    </div>


    <div class="filters-container flex flex-col gap-5 p-5 bg-white shadow-md rounded-lg mb-10 ">
      <p class="text-lg text-gray-800 font-bold">Selecteer een gemeente</p>
      <div class="cityinput-container flex items-center">
        <input class="border p-2 flex-1 rounded-sm" type="text" v-model="input" placeholder="Search cities"
          @input="showCityList = true" />
        <p @click="removeCity"
          class="bg-slate-900 text-white text-2xl w-10 h-10 flex items-center justify-center cursor-pointer rounded-sm">
          X
        </p>
      </div>

      <div v-if="showCityList && input" class="cities-list">
        <div v-for="(city, index) in filteredCities" :key="index" class="city-item" @click="selectCity(city)">
          <p>{{ city }}</p>
        </div>
      </div>

      <p class="text-lg text-gray-800 font-bold">Selecteer een schooljaar</p>
      <div class="flex gap-3 flex-wrap">
        <button @click="setSchooljaar('2024-2025')"
          :class="schooljaar === '2024-2025' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          2024-2025
        </button>
        <button @click="setSchooljaar('2025-2026')"
          :class="schooljaar === '2025-2026' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          2025-2026
        </button>
      </div>

      <p class="text-lg text-gray-800 font-bold">Selecteer een niveau</p>
      <div class="flex gap-3 flex-wrap">
        <button @click="setNiveau('kleuter onderwijs')"
          :class="niveau === 'kleuter onderwijs' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          Kleuteronderwijs
        </button>
        <button @click="setNiveau('lager onderwijs')"
          :class="niveau === 'lager onderwijs' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          Lager onderwijs
        </button>
        <button @click="setNiveau('secundair onderwijs')"
          :class="niveau === 'secundair onderwijs' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class="py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          Middelbaar onderwijs
        </button>
      </div>

      <p class="text-lg text-gray-800 font-bold">Selecteer een type</p>
      <div class="flex gap-3 flex-wrap">
        <button @click="setSchooltype('gewoon')"
          :class="schooltype === 'gewoon' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          Gewoon
        </button>
        <button @click="setSchooltype('buitengewoon')"
          :class="schooltype === 'buitengewoon' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          Buitengewoon
        </button>
        <button @click="setSchooltype('onthaalonderwijs')"
          :class="schooltype === 'onthaalonderwijs' ? 'bg-slate-200 border-2 border-blue-500 scale-105' : 'bg-slate-300'"
          class=" py-2 px-4 rounded-lg w-full sm:w-auto transition-all duration-300">
          Onthaalonderwijs
        </button>
      </div>

      <button @click="toggleResults" class="mt-5 bg-green-700 text-white text-xl py-4 px-4 rounded-lg w-full">
        Bekijk resultaten
      </button>
    </div>



    <div v-if="showResults" class="results max-w-full bg-white shadow-lg rounded-xl p-5 overflow-auto mb-10">
      <h2 class="text-xl font-semibold text-gray-800 mb-4">Beschikbare scholen</h2>

      <div v-if="filteredSchools.length > 0">
        <div v-for="school in filteredSchools" :key="school.name"
          class="p-5 bg-slate-200 rounded-lg shadow-sm mb-4 border border-gray-200 hover:shadow-md transition-all duration-300">
          <p class="text-lg mb-2 font-semibold text-gray-900">{{ school.name }}</p>
          <p class="text-gray-600 mb-1"><strong>📍 Locatie:</strong> {{ school.address }}</p>
          <p class="text-gray-600">
            <strong>Website:</strong>
            <a :href="school.website" target="_blank"
              class="text-blue-600 font-medium underline hover:text-blue-800 transition break-words">
              {{ school.website }}
            </a>
          </p>
        </div>
      </div>

      <div v-else class="flex flex-col items-center justify-center text-center py-10">
        <p class="text-red-500 text-lg font-semibold">❌ Geen scholen gevonden.</p>
        <p class="text-gray-500 mt-2">Probeer je filters aan te passen.</p>
      </div>
    </div>

  </div>
</template>
