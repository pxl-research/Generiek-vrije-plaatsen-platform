<template>
    <div class="flex justify-center">
        <div class="fixed top-25 flex flex-col items-start z-10">
          <div class="flex items-center">
            <input 
              type="text" v-model="userInput"
              @input="showSchoolList = true"
              @keydown.enter="updateLocation"
              class="bg-white border-2 h-15 w-75 text-xl p-3 focus:outline-none" 
              placeholder="Enter text here" 
            />
            <svg class="w-10 h-10 ml-2 bg-red-300 text-red-900 rounded-lg cursor-pointer hover:bg-red-400" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg" @click="removeUserinput">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 6l12 12M6 18L18 6"></path>
            </svg>
          </div>
          <div v-if="showSchoolList && userInput" class="z-11 w-75 border-2 border-t-0">
            <div v-for="(school, index) in filteredAddresses" :key="index" @click="selectSchool(school)"
            class="bg-white hover:bg-pink-200 p-1">
              <p>{{ school.title }}</p>
            </div>
          </div>

        </div> 
        

        <div class="flex justify-center items-center w-screen bg-[#D0E0EF] pb-10">
            <button class="bg-purple-900 text-white" @click="updateLocation">Select</button>
        </div> 

        <div ref="mapDiv" class="absolute top-0 z-1 h-screen w-full"></div>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, computed, watch } from "vue";
import { Loader } from "@googlemaps/js-api-loader";
import schoolData from "@/assets/schools.json";

const userInput = ref("");
const showSchoolList = ref(false);

const removeUserinput = () => {
  userInput.value = "";
}

interface Location {
  address: string;
  title: string;
  website: string;
  phoneNumber: string;
}

const apiKey = import.meta.env.VITE_MAPS_API_KEY;

const addresses: Location[] = schoolData.schools.map((school) => ({
  address: `${school.address}, ${school.city}, ${school.postalCode}`,
  title: school.name,
  website: school.website,
  phoneNumber: school.phoneNumber,
}));
console.log(addresses);

// Show possible schools under search bar
const filteredAddresses = computed(() => {
  return addresses.filter((school) => {
    return school.title.toLowerCase().includes(userInput.value.toLowerCase());
  });
});

// Select school from search
const selectSchool = (school: Location) => {
  console.log("Selected school:", school.title);
  showSchoolList.value = false;
  userInput.value = school.title;
  geocodeAddresses(school);
};

const mapDiv = ref<HTMLDivElement | null>(null);
let map: google.maps.Map | null = null;
let geocoder: google.maps.Geocoder | null = null;

// Initialize the map
const initMap = async () => {
  if (!mapDiv.value) return;

  const loader = new Loader({
    apiKey: apiKey,
    version: "weekly",
  });

  loader.load().then(() => {
    if (!mapDiv.value) return;

    map = new google.maps.Map(mapDiv.value, {
      center: { lat: 50.93106, lng: 5.33781 }, // Default to NYC
      zoom: 13,
    });

    geocoder = new google.maps.Geocoder();
    geocodeAddresses();
  });
};

// Function for creating markers and searching for schools
function geocodeAddresses(school?: Location) {
  if (!map || !geocoder) return;
  const infoWindow = new google.maps.InfoWindow();

  if (school) {
    // Go to adress of specific school
    geocoder.geocode({ address: school.address }, (results, status) => {
      if (status === "OK" && results[0]) {
        const position = results[0].geometry.location;
        map.setCenter(position); 
      } else {
        console.error(`Geocoding failed for ${school.address}: ${status}`);
      }
    });
  } else {
    // Create markers for all schools on the map
    addresses.forEach((location) => {
      geocoder.geocode({ address: location.address }, (results, status) => {
        if (status === "OK" && results[0]) {
          const position = results[0].geometry.location;
          const marker = new google.maps.Marker({
            position,
            map,
            title: location.title || location.address,
          });

          // Add a click event to show the InfoWindow when the marker is clicked
          marker.addListener("click", () => {
            console.log("Marker clicked", location.title);
            infoWindow.setContent(`
            <div>
              <p>${location.title}</p>
              <p>${location.address}</p>
              <a href="${location.website}" target="_blank" class="text-blue-700 underline">${location.website}</a>
              <br>
              <a href="tel:${location.phoneNumber}">Call us</a>
            </div>
            `);
            infoWindow.open(map, marker); // Open the InfoWindow on the marker
          });

        } else {
          console.error(`Geocoding failed for ${location.address}: ${status}`);
        }
      });
    });
  }
};

// Function for searching general locations instead of schools
function updateLocation() {
  if (!map || !geocoder || !userInput.value) return;

  geocoder.geocode({ address: userInput.value }, (results, status) => {
    if (status === "OK" && results[0]) {
      const position = results[0].geometry.location;

      map.setCenter(position);
    } else {
      console.error(`Geocoding failed for ${userInput.value}: ${status}`);
    }
  });
}

watch(() => addresses, () => geocodeAddresses(), { deep: true });

onMounted(initMap);
</script>