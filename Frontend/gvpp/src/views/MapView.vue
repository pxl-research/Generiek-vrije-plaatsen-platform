<template>
  <div class="flex justify-center">
    <div class="fixed top-25 flex flex-col items-start z-10">
      <div class="flex items-center">
        <input
          type="text"
          v-model="cityInput"
          @input="showCityList = true"
          @keydown.enter="filterByCity"
          class="bg-white border-2 h-15 w-75 text-xl p-3 focus:outline-none"
          placeholder="Enter city name"
        />
        <svg
          class="w-10 h-10 ml-2 bg-red-300 text-red-900 rounded-lg cursor-pointer hover:bg-red-400"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg"
          @click="clearCityFilter"
        >
          <path stroke-linecap="round" stroke-linejoin="round" d="M6 6l12 12M6 18L18 6"></path>
        </svg>
      </div>
      <div v-if="showCityList && cityInput" class="z-11 w-75 border-2 border-t-0 max-h-60 overflow-y-auto">
        <div
          v-for="(city, index) in filteredCities"
          :key="index"
          @click="selectCity(city)"
          class="bg-white hover:bg-pink-200 p-2 cursor-pointer"
        >
          {{ city }}
        </div>
      </div>
    </div>

    <div ref="mapDiv" class="absolute top-0 z-1 h-screen w-full"></div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, computed } from "vue";
import { Loader } from "@googlemaps/js-api-loader";
import escapeRoomData from "@/data/escaperooms.json";

const cityInput = ref<string>("");
const showCityList = ref<boolean>(false);
const mapDiv = ref<HTMLDivElement | null>(null);
let map: google.maps.Map | null = null;
let geocoder: google.maps.Geocoder | null = null;
const markers: google.maps.Marker[] = [];

const apiKey = import.meta.env.VITE_MAPS_API_KEY;

interface EscapeRoom {
  id: string;
  name: string;
  address: string;
  city: string;
  postalcode: string;
  website: string;
  phoneNumber: string;
  position?: google.maps.LatLngLiteral;
}

// Prepare escape room data
const escapeRooms = escapeRoomData.map(room => ({
  id: room.id,
  name: room.name,
  address: `${room.address}, ${room.city}`,
  city: room.city,
  postalcode: room.postalcode,
  website: room.website,
  phoneNumber: room["phone-number"],
}));

// Get unique cities
const allCities = [...new Set(escapeRooms.map(room => room.city))];

// Filter cities based on input
const filteredCities = computed(() => {
  return allCities.filter(city =>
    city.toLowerCase().includes(cityInput.value.toLowerCase())
  );
});

// Initialize map
const initMap = async () => {
  if (!mapDiv.value) return;

  const loader = new Loader({
    apiKey,
    version: "weekly",
  });

  await loader.load();

  map = new google.maps.Map(mapDiv.value, {
    center: { lat: 52.1326, lng: 5.2913 }, // Center of Netherlands
    zoom: 7,
  });

  geocoder = new google.maps.Geocoder();
  showAllEscapeRooms();
};

// Show all escape rooms on map
const showAllEscapeRooms = async () => {
  if (!map || !geocoder) return;

  clearMarkers();

  // Geocode and show all rooms
  for (const room of escapeRooms) {
    try {
      const results = await geocodeAddress(room.address);
      if (results && results[0]) {
        const position = results[0].geometry.location;
        addMarker(room, position);
      }
    } catch (error) {
      console.error(`Geocoding failed for ${room.address}:`, error);
    }
  }
};

// Filter escape rooms by city
const filterByCity = async () => {
  if (!cityInput.value || !map || !geocoder) return;

  const selectedCity = cityInput.value;
  const roomsInCity = escapeRooms.filter(
    (room) => room.city.toLowerCase() === selectedCity.toLowerCase()
  );

  if (roomsInCity.length === 0) return;

  clearMarkers();

  // Geocode and show rooms in selected city
  for (const room of roomsInCity) {
    try {
      const results = await geocodeAddress(room.address);
      if (results && results[0]) {
        const position = results[0].geometry.location;
        addMarker(room, position);
      }
    } catch (error) {
      console.error(`Geocoding failed for ${room.address}:`, error);
    }
  }

  // Center map on first result
  if (roomsInCity.length > 0) {
    const firstRoom = roomsInCity[0];
    const results = await geocodeAddress(firstRoom.address);
    if (results && results[0]) {
      map.setCenter(results[0].geometry.location);
      map.setZoom(13);
    }
  }

  showCityList.value = false;
};

// Select city from dropdown
const selectCity = (city: string) => {
  cityInput.value = city;
  filterByCity();
};

// Clear all markers
const clearMarkers = () => {
  markers.forEach((marker) => marker.setMap(null));
  markers.length = 0;
};

// Add marker to map
const addMarker = (room: EscapeRoom, position: google.maps.LatLng) => {
  if (!map) return;

  const marker = new google.maps.Marker({
    position,
    map,
    title: room.name,
  });

  const infoWindow = new google.maps.InfoWindow({
    content: `
      <div class="p-2">
        <h3 class="font-bold">${room.name}</h3>
        <p>${room.address}</p>
        <p>${room.postalcode} ${room.city}</p>
        <a href="${room.website}" target="_blank" class="text-blue-600 underline">Website</a>
        <br>
        <a href="tel:${room.phoneNumber}" class="text-blue-600">${room.phoneNumber}</a>
      </div>
    `,
  });

  marker.addListener("click", () => {
    infoWindow.open(map, marker);
  });

  markers.push(marker);
};

// Geocode helper function
const geocodeAddress = (address: string): Promise<google.maps.GeocoderResult[]> => {
  return new Promise((resolve, reject) => {
    if (!geocoder) {
      reject("Geocoder not initialized");
      return;
    }

    geocoder.geocode({ address }, (results, status) => {
      if (status === "OK" && results) {
        resolve(results);
      } else {
        reject(status);
      }
    });
  });
};

// Clear city filter and show all
const clearCityFilter = () => {
  cityInput.value = "";
  showAllEscapeRooms();
};

onMounted(initMap);
</script>

<style scoped>
/* Add some styles if needed */
</style>
