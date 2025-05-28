<template>
  <div class="flex flex-col h-screen">
    <HeaderComponent/>

    <!-- Map + Input container -->
    <div class="relative flex-1 w-full">

      <!-- Map -->
      <div ref="mapDiv" class="absolute inset-0 z-0"></div>

      <!-- Floating input -->
      <div class="absolute top-12 left-1/2 transform -translate-x-1/2 z-10 bg-white rounded shadow-lg w-120">
        <div class="relative w-full">
          <input
            type="text"
            v-model="userInput"
            @input="showEscapeRoomList = true"
            @keydown.enter="updateLocation"
            class="bg-white border-2 w-full text-xl p-3 pr-10 focus:outline-none rounded"
            placeholder="Voeg stad, straat of postcode in"
          />
          <svg
            class="w-6 h-6 absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 cursor-pointer hover:text-red-600"
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

        <!-- Dropdown list -->
        <div
          v-if="showEscapeRoomList && filteredEscapeRooms.length"
          class="w-full border-2 border-t-0 bg-white max-h-60 overflow-y-auto mt-2 rounded"
        >
          <div
            v-for="(escapeRoom, index) in filteredEscapeRooms"
            :key="index"
            @click="selectEscapeRoom(escapeRoom)"
            class="hover:bg-pink-200 cursor-pointer p-2 border-b"
          >
            <p class="font-semibold">{{ escapeRoom.name }}</p>
            <p class="text-sm text-gray-600">{{ escapeRoom.city }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import escapeRoomData from '@/data/escaperooms.json'
import { Loader } from '@googlemaps/js-api-loader'
import HeaderComponent from '@/components/HeaderComponent.vue'

const userInput = ref('')
const showEscapeRoomList = ref(false)
const selectedEscapeRoom = ref<any | null>(null)
const mapDiv = ref<HTMLDivElement | null>(null)
const map = ref<google.maps.Map | null>(null)
const markers = ref<google.maps.Marker[]>([])
const apiKey = import.meta.env.VITE_MAPS_API_KEY;

const filteredEscapeRooms = computed(() =>
  escapeRoomData.filter(er =>
    er.city.toLowerCase().includes(userInput.value.toLowerCase())
  )
)

const removeUserInput = () => {
  userInput.value = ''
  showEscapeRoomList.value = false
  selectedEscapeRoom.value = null
}

const selectEscapeRoom = (room: any) => {
  selectedEscapeRoom.value = room
  showEscapeRoomList.value = false
  userInput.value = room.city

  const fullAddress = `${room.address}, ${room.postalcode} ${room.city}`
  const geocoder = new google.maps.Geocoder()

  geocoder.geocode({ address: fullAddress }, (results, status) => {
    if (status === 'OK' && results?.[0]) {
      map.value?.setCenter(results[0].geometry.location)
      map.value?.setZoom(15)
    }
  })
}

onMounted(async () => {
  const loader = new Loader({
    apiKey,
    version: 'weekly'
  })

  await loader.load()

  if (mapDiv.value) {
    map.value = new google.maps.Map(mapDiv.value, {
      center: { lat: 50.93106, lng: 5.33781 },
      zoom: 13,
      fullscreenControl: false,
      mapTypeControl: false,
      streetViewControl: false,
      disableDefaultUI: true,
    })

    const geocoder = new google.maps.Geocoder()

    escapeRoomData.forEach(room => {
      const address = `${room.address}, ${room.postalcode} ${room.city}`

      geocoder.geocode({ address }, (results, status) => {
        if (status === 'OK' && results?.[0]) {
          const marker = new google.maps.Marker({
            map: map.value!,
            position: results[0].geometry.location,
            title: room.name
          })

          marker.addListener('click', () => {
            selectedEscapeRoom.value = room
            map.value?.setCenter(results[0].geometry.location)
            map.value?.setZoom(15)
          })

          markers.value.push(marker)
        }
      })
    })
  }
})
</script>


