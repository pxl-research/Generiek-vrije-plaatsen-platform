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
  schooljaar.value = value;
};


const setNiveau = (value: string) => {
  niveau.value = value;
}


const setSchooltype = (value: string) => {
  schooltype.value = value; 
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
    <p class="text-3xl text-bold ml-5 mt-5">Zoek vrije plaatsen</p>
    <p class="ml-5 w-75 mt-5 mb-5">Selecteer de steden/gemeenten waar je je kinderen graag naar school laat gaan.</p>
    <iframe
      src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d80478.5673049272!2d5.231144139688511!3d50.9245452049915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c12183ded75db7%3A0xf7cb7b027e7e2181!2sHasselt!5e0!3m2!1sen!2sbe!4v1741168303625!5m2!1sen!2sbe"
      class="w-screen h-75" loading="lazy" referrerpolicy="no-referrer-when-downgrade"
    ></iframe>
  
    <p class="ml-10 pt-5">Selecteer een gemeente</p>
    <div class="cityinput flex gap-10">
        <input class="ml-10 border" type="text" v-model="input" placeholder="Search cities" @input="showCityList = true" />
        <p @click="removeCity" class="bg-black rounded rounded-l text-2xl w-10 pl-3 text-white flex content-center justify-self-center ">X</p>
    </div>

    <div v-if="showCityList && input" class="cities-list">
      <div
        v-for="(city, index) in filteredCities"
        :key="index"
        class="city-item"
        @click="selectCity(city)"
      >
        <p>{{ city }}</p>
      </div>
    </div>

    <p class="ml-10 pt-5">Selecteer een schooljaar</p>
    <div class="schooljaarinput flex gap-10 pl-10">
        <button @click="setSchooljaar('2024-2025')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">2024-2025</button>
        <button @click="setSchooljaar('2025 - 2026')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">2025-2026</button>
    </div>
    
    <p class="ml-10 pt-5">Selecteer een niveau</p>
    <div class="niveauinput flex flex-wrap gap-10 pl-10">
        <button @click="setNiveau('kleuter onderwijs')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">kleuteronderwijs</button>
        <button @click="setNiveau('lager onderwijs')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">lager onderwijs</button>
        <button @click="setNiveau('secundair onderwijs')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">middelbaar onderwijs</button>
    </div>

    <p class="ml-10 pt-5">Selecteer een type</p>
    <div class="niveauinput flex flex-wrap gap-10 pl-10">
        <button @click="setSchooltype('gewoon')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">gewoon</button>
        <button @click="setSchooltype('buitengewoon')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">buitengewoon</button>
        <button @click="setSchooltype('onthaalonderwijs')" class="bg-blue-800 text-white w-30 h-15 rounded rounded-l">onthaalonderwijs</button>
    </div>


    <button @click="toggleResults" class="mt-10 ml-30 mb-10 bg-green-400 text-white w-30 h-15 rounded rounded-l">Check results</button>


    <div v-if="showResults" class="results w-screen h-92 bg-gray-200 rounded-xl p-5 overflow-auto">
        <h2 class="text-lg font-bold mb-3">Available Schools</h2>
        <div v-if="filteredSchools.length > 0">
            <div v-for="school in filteredSchools" :key="school.name" class="p-3 border-b">
                <p><strong>Name:</strong> {{ school.name }}</p>
                <p><strong>Location:</strong> {{ school.address }}</p>
                <p>
                    <strong>Website:</strong>
                    <a :href="school.website" target="_blank" class="text-blue-600 underline">
                        {{ school.website }}
                    </a>
                </p>
            </div>
        </div>
        <div v-else>
            <p class="text-red-500">No schools found matching your criteria.</p>
        </div>
    </div>
</template>
