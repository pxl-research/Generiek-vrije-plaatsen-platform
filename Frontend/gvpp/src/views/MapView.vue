<template>
  <HeaderComponent/>
  <div class="bg-slate-200 min-h-screen "> 
    <div class="flex top-25 justify-center">
      <div class="fixed flex flex-col items-start z-10">
        <div class="flex items-center">
          <input
            type="text"
            v-model="userInput"
            @input="showEscapeRoomList = true"
            @keydown.enter="updateLocation"
            class="bg-white border-2 h-15 w-75 text-xl p-3 focus:outline-none"
            placeholder="Enter location here"
          />
          <svg
            class="w-10 h-10 ml-2 bg-red-300 text-red-900 rounded-lg cursor-pointer hover:bg-red-400"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
            @click="removeUserInput"
          >
            <path stroke-linecap="round" stroke-linejoin="round" d="M6 6l12 12M6 18L18 6"></path>
          </svg>
        </div>
        <div v-if="showEscapeRoomList && userInput" class="z-11 w-75 border-2 border-t-0">
          <div
            v-for="(escapeRoom, index) in filteredEscapeRooms"
            :key="index"
            @click="selectEscapeRoom(escapeRoom)"
            class="bg-white hover:bg-pink-200 p-1"
          >
            <p>{{ escapeRoom.city }}</p>
          </div>
        </div>
      </div>
      

      <div ref="mapDiv" class="absolute bottom-0 h-170 w-full"></div>


      <div v-if="selectedEscapeRoom" class="fixed bottom-25 bg-white w-90 shadow-lg p-4 border border-black z-90 rounded-3xl">
        <button @click="selectedEscapeRoom = null" class="absolute right-4 top-2 text-gray-500">✕</button>
        <p class="text-lg font-bold text-gray-800">{{ selectedEscapeRoom.title }}</p>
        <p class="text-sm text-gray-600">{{ selectedEscapeRoom.address }}</p>
        <a :href="selectedEscapeRoom.website" target="_blank" class="text-blue-900 underline block mt-2">
          {{ selectedEscapeRoom.website }}
        </a>
        <a :href="'tel:' + selectedEscapeRoom.phoneNumber" class="block mt-2">
          Call us: {{ selectedEscapeRoom.phoneNumber }}
        </a>

  
        <div v-if="expanded" class="mt-4">
          <p><strong>Available Rooms:</strong></p>
          <div v-for="(room, index) in selectedEscapeRoom['different-rooms']" :key="index" class="bg-gray-100 p-3 rounded-md mt-2">
            <h4 class="font-bold text-lg">{{ room.name }}</h4>
            <p>Price: €{{ room.price }}</p>
            <p>Duration: {{ room.duration }}</p>
            <p>Players: {{ room.players }}</p>
          </div>
        </div>


        <div class="flex justify-center mt-4">
          <button
            @click="toggleExpand"
            class="bg-green-500 text-white rounded-l p-1"
          >
            {{ expanded ? "Less Info" : "More Info" }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, computed, watch } from "vue";
import { Loader } from "@googlemaps/js-api-loader";
import escapeRoomData from "@/data/escaperooms.json";
import HeaderComponent from '@/components/HeaderComponent.vue'

const userInput = ref("");
const showEscapeRoomList = ref(false);
const selectedEscapeRoom = ref(null); // Store clicked marker's data
const mapDiv = ref<HTMLDivElement | null>(null);
let map: google.maps.Map | null = null;
let geocoder: google.maps.Geocoder | null = null;
const expanded = ref(false); // State to toggle More Info view

const apiKey = import.meta.env.VITE_MAPS_API_KEY;

interface EscapeRoom {
  address: string;
  title: string;
  city: string;
  website: string;
  phoneNumber: string;
  "different-rooms": Array<{ name: string, price: string, duration: string, players: string }>;
}

const escapeRooms: EscapeRoom[] = escapeRoomData.map((escaperoom) => ({
  address: `${escaperoom.address}, ${escaperoom.city}, ${escaperoom.postalcode}`,
  title: escaperoom.name,
  city: escaperoom.city.toLowerCase(),
  website: escaperoom.website,
  phoneNumber: escaperoom["phone-number"],
  "different-rooms": escaperoom["different-rooms"]
}));

const filteredEscapeRooms = computed(() =>
  escapeRooms.filter((escaperoom) => escaperoom.city.includes(userInput.value.toLowerCase()))
);

const selectEscapeRoom = (escaperoom: EscapeRoom) => {
  console.log("Selected:", escaperoom.title);
  showEscapeRoomList.value = false;
  userInput.value = escaperoom.city;
  geocodeAddresses(escaperoom);
  selectedEscapeRoom.value = escaperoom; // Set the selected escape room
};

const initMap = async () => {
  if (!mapDiv.value) return;

  const loader = new Loader({
    apiKey,
    version: "weekly",
  });

  loader.load().then(() => {
    if (!mapDiv.value) return;

    map = new google.maps.Map(mapDiv.value, {
      center: { lat: 50.93106, lng: 5.33781 },
      zoom: 13,
      fullscreenControl: false,
      mapTypeControl: false,
      streetViewControl: false,
      disableDefaultUI: true,
    });

    geocoder = new google.maps.Geocoder();
    geocodeAddresses();
  });
};

function geocodeAddresses(escaperoom: EscapeRoom | null = null) {
  if (!map || !geocoder) return;

  if (escaperoom) {
    geocoder.geocode({ address: escaperoom.city }, (results, status) => {
      if (status === "OK" && results && results[0]) {
        const position = results[0].geometry.location;
        map.setCenter(position);
      } else {
        console.error(`Geocoding failed for ${escaperoom.city}: ${status}`);
      }
    });
  } else {
    escapeRooms.forEach((escapeRoom) => {
      geocoder.geocode({ address: escapeRoom.city }, (results, status) => {
        if (status === "OK" && results && results[0]) {
          const position = results[0].geometry.location;
          const marker = new google.maps.Marker({
            position,
            map,
            title: escapeRoom.title,
          });

          // Set selected escape room data when marker is clicked
          marker.addListener("click", () => {
            selectedEscapeRoom.value = escapeRoom;
          });
        } else {
          console.error(`Geocoding failed for ${escapeRoom.city}: ${status}`);
        }
      });
    });
  }
}

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

const removeUserInput = () => {
  userInput.value = "";
};

// Toggle the "More Info" section
const toggleExpand = () => {
  expanded.value = !expanded.value;
};

watch(() => escapeRooms, () => geocodeAddresses(), { deep: true });

onMounted(initMap);
</script>
