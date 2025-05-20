<template>
  <HeaderComponent/>
  <div class="bg-slate-200 min-h-screen">
    <div class="flex justify-center pt-8">
      <div class="fixed z-10 flex flex-col items-start">
        <div class="flex items-center">
          <input
            type="text"
            v-model="userInput"
            @input="showEscapeRoomList = true"
            @keydown.enter="updateLocation"
            class="bg-white border-2 h-15 w-72 text-xl p-3 focus:outline-none"
            placeholder="Enter city"
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

        <div v-if="showEscapeRoomList && filteredEscapeRooms.length" class="w-72 border-2 border-t-0 bg-white max-h-60 overflow-y-auto z-20">
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

      <div ref="mapDiv" class="absolute bottom-0 h-[700px] w-full"></div>

      <div
        v-if="selectedEscapeRoom"
        class="fixed bottom-28 bg-white w-11/12 md:w-3/4 lg:w-1/2 shadow-lg p-6 border border-black z-30 rounded-3xl max-h-[80vh] overflow-y-auto"
      >
        <button @click="selectedEscapeRoom = null" class="absolute right-4 top-2 text-gray-500">✕</button>

        <p class="text-2xl font-bold text-gray-800 mb-2">{{ selectedEscapeRoom.name }}</p>
        <p class="text-sm text-gray-600">{{ selectedEscapeRoom.address }}, {{ selectedEscapeRoom.city }}</p>
        <a :href="selectedEscapeRoom.website" target="_blank" class="text-blue-900 underline block mt-2">
          {{ selectedEscapeRoom.website }}
        </a>
        <a :href="'tel:' + selectedEscapeRoom['phone-number']" class="block mt-1">
          Call us: {{ selectedEscapeRoom['phone-number'] }}
        </a>
        <a :href="'mailto:' + selectedEscapeRoom.email" class="block mt-1">
          Email: {{ selectedEscapeRoom.email }}
        </a>

        <div class="mt-4">
          <p class="font-bold">Available Rooms:</p>
          <div
            v-for="(room, index) in selectedEscapeRoom['different-rooms']"
            :key="index"
            class="bg-gray-100 p-3 rounded-md mt-3"
          >
            <h4 class="font-bold text-lg">{{ room.name }}</h4>
            <p>Price: €{{ room.price }}</p>
            <p>Duration: {{ room.duration }}</p>
            <p>Players: {{ room.players }}</p>
            <p>Age: {{ room.age || room['age-range'] }}</p>
            <p class="mt-2 font-semibold">Time slots:</p>
            <ul class="flex flex-wrap gap-2 mt-1">
              <li
                v-for="(slot, i) in room.timeslots"
                :key="i"
                :class="slot.status === 'open' ? 'bg-green-200 text-green-900' : 'bg-red-200 text-red-900'"
                class="px-2 py-1 text-sm rounded"
              >
                {{ slot.time }} ({{ slot.status }})
              </li>
            </ul>
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


