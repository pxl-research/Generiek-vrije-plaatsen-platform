<template>
  <div class="flex justify-center">
    <div class="fixed top-25 flex flex-col items-start z-10">
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
          <p>{{ escapeRoom.title }}</p>
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
import escapeRoomData from "@/data/escaperooms.json";

const userInput = ref("");
const showEscapeRoomList = ref(false);
const mapDiv = ref<HTMLDivElement | null>(null);
let map: google.maps.Map | null = null;
let geocoder: google.maps.Geocoder | null = null;

const apiKey = import.meta.env.VITE_MAPS_API_KEY;

interface EscapeRoom {
  address: string;
  title: string;
  city: string;
  website: string;
  phoneNumber: string;
}

const escapeRooms: EscapeRoom[] = escapeRoomData.map((escaperoom) => ({
  address: `${escaperoom.address}, ${escaperoom.city}, ${escaperoom.postalcode}`,
  title: escaperoom.name,
  city: escaperoom.city.toLowerCase(),
  website: escaperoom.website,
  phoneNumber: escaperoom["phone-number"],
}));

const filteredEscapeRooms = computed(() =>
  escapeRooms.filter((escaperoom) => escaperoom.city.includes(userInput.value.toLowerCase()))
);

const selectEscapeRoom = (escaperoom: EscapeRoom) => {
  console.log("Selected:", escaperoom.title);
  showEscapeRoomList.value = false;
  userInput.value = escaperoom.city;
  geocodeAddresses(escaperoom);
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
    });

    geocoder = new google.maps.Geocoder();
    geocodeAddresses();
  });
};

function geocodeAddresses(escaperoom: EscapeRoom | null = null) {
  if (!map || !geocoder) return;
  const infoWindow = new google.maps.InfoWindow();

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

          marker.addListener("click", () => {
            infoWindow.setContent(`
              <div>
                <p>${escapeRoom.title}</p>
                <p>${escapeRoom.address}</p>
                <a href="${escapeRoom.website}" target="_blank" class="text-blue-700 underline">${escapeRoom.website}</a>
                <br>
                <a href="tel:${escapeRoom.phoneNumber}">Call us</a>
              </div>
            `);
            infoWindow.open(map, marker);
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

watch(() => escapeRooms, () => geocodeAddresses(), { deep: true });

onMounted(initMap);
</script>
