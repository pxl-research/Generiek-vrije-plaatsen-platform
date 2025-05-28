<script setup lang="ts">
import { ref, onMounted } from 'vue';
import HeaderComponent from "@/components/HeaderComponent.vue";
import type { Filter } from "@/models/filter.ts";
import { EscapeRoomRequest } from "@/models/escapeRoomRequest.ts";
import type { EscapeRoom } from "@/models/escapeRoom.ts";
import { useEscapeRoomStore } from "@/store/EscapeRoomStore.ts";
const selectedRoomId = ref<number | null>(null);
const escapeRooms = ref<EscapeRoom[]>([]);
const localCapacities = ref<Record<number, number>>({});
const isEditing = ref<Record<number, boolean>>({});
const showAddModal = ref(false);
const filters = ref<Filter[]>([]);
const showFiltersModal = ref(false);

function openFiltersModal() {
  showFiltersModal.value = true;
}

function closeFiltersModal() {
  showFiltersModal.value = false;
}


const escapeRoomStore = useEscapeRoomStore();

const newEscapeRoom = ref<EscapeRoom>();

newEscapeRoom.value = {
  name: '',
  description: '',
  address: "",
  city: "",
  currentCapacity: 0,
  email: "",
  id: 0,
  organizationId: 0,
  phoneNumber: "",
  postalCode: 0,
  website: "",
  maxCapacity: 0
};

function openAddModal() {
  selectedRoomId.value = null; // We voegen iets nieuws toe
  newEscapeRoom.value = {
    name: '',
    description: '',
    address: '',
    city: '',
    currentCapacity: 0,
    email: '',
    id: 0,
    organizationId: 0,
    phoneNumber: '',
    postalCode: 0,
    website: '',
    maxCapacity: 0,
  };
  showAddModal.value = true;
}

function editEscapeRoom(roomId: number) {
  const room = escapeRooms.value.find(r => r.id === roomId);
  if (!room) return;

  selectedRoomId.value = roomId; // We zijn nu aan het bewerken
  newEscapeRoom.value = { ...room }; // Kopieer de data naar het formulier
  showAddModal.value = true;
}


function closeAddModal() {
  showAddModal.value = false;
}

function saveEscapeRoom() {
  if (!newEscapeRoom.value) return;

  const data: EscapeRoomRequest = {
    name: newEscapeRoom.value.name,
    description: newEscapeRoom.value.description,
    organizationId: newEscapeRoom.value.organizationId,
    address: newEscapeRoom.value.address,
    postalCode: newEscapeRoom.value.postalCode,
    city: newEscapeRoom.value.city,
    email: newEscapeRoom.value.email,
    phoneNumber: newEscapeRoom.value.phoneNumber,
    website: newEscapeRoom.value.website,
    maxCapacity: newEscapeRoom.value.maxCapacity,
  };

  if (selectedRoomId.value === null) {
    // Nieuw toevoegen
    escapeRoomStore.addEscapeRoom(data);
  } else {
    // Bewerken
    escapeRoomStore.updateEscapeRoom(selectedRoomId.value, data); // Deze functie moet je maken in je store
  }

  closeAddModal();
}


function deleteEscapeRoom(roomId: number) {
  escapeRooms.value = escapeRooms.value.filter(room => room.id !== roomId);
  delete localCapacities.value[roomId];
  delete isEditing.value[roomId];
}


onMounted(async () => {
  const res = await fetch('/api/escaperooms');
  const data: EscapeRoom[] = await res.json();
  data.sort((a, b) => a.name.localeCompare(b.name));
  escapeRooms.value = data;

  data.forEach(room => {
    localCapacities.value[room.id] = room.currentCapacity;
    isEditing.value[room.id] = false;
  });

  const filterRes = await fetch('/api/filters');
  const filterData: Filter[] = await filterRes.json();
  filters.value = filterData.sort((a: Filter, b: Filter) => a.id - b.id);
});

function toggleEdit(roomId: number) {
  isEditing.value[roomId] = !isEditing.value[roomId];
}

function saveCapacity(roomId: number) {
  const room = escapeRooms.value.find(r => r.id === roomId);
  if (!room) return;

  const newCap = localCapacities.value[roomId];
  if (newCap >= 0 && newCap <= room.maxCapacity) {
    room.currentCapacity = newCap;
    isEditing.value[roomId] = false;
  }
}
</script>

<template>
  <div class="bg-slate-200 min-h-screen p-4">
    <HeaderComponent />

    <!-- Filters Section -->
    <div class="w-full bg-white mt-10 rounded-lg border border-gray-200 shadow-sm">
      <div class="px-6 py-5">
        <p class="text-lg font-semibold text-gray-800 mb-4">Filters</p>

        <!-- Desktop: toon alles -->
        <div class="hidden sm:flex flex-wrap gap-4 max-h-72 overflow-auto">
          <div v-for="filter in filters" :key="filter.id"
            class="flex flex-col bg-gray-50 rounded-md p-3 min-w-[220px] flex-shrink-0 hover:shadow-md transition-shadow cursor-pointer">
            <template v-if="filter.active">
              <label class="text-sm font-medium text-gray-700 mb-2 block">{{ filter.name }}</label>

              <select v-if="filter.inputType === 'dropdown'"
                class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400">
                <option value="">Kies een optie</option>
                <option>Optie 1</option>
                <option>Optie 2</option>
              </select>

              <input v-else-if="filter.inputType === 'textbox'" type="text"
                class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400" />

              <label v-else-if="filter.inputType === 'checkbox'" class="inline-flex items-center gap-2 text-gray-700">
                <input type="checkbox" class="form-checkbox h-5 w-5 text-indigo-500" />
                <span>{{ filter.name }}</span>
              </label>
            </template>
          </div>
        </div>

        <!-- Mobile: toon max 2 filters + knop -->
        <div class="flex flex-col sm:hidden gap-4">
          <div class="flex flex-wrap gap-4">
            <div v-for="filter in filters.slice(0, 2)" :key="filter.id"
              class="flex flex-col bg-gray-50 rounded-md p-3 min-w-[220px] flex-shrink-0 hover:shadow-md transition-shadow cursor-pointer">
              <template v-if="filter.active">
                <label class="text-sm font-medium text-gray-700 mb-2 block">{{ filter.name }}</label>
                <input v-if="filter.inputType === 'textbox'" type="text"
                  class="w-80 border border-gray-700 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400" />
                <select v-else-if="filter.inputType === 'dropdown'"
                  class="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400">
                  <option value="">Kies een optie</option>
                  <option>Optie 1</option>
                  <option>Optie 2</option>
                </select>
                <label v-else-if="filter.inputType === 'checkbox'" class="inline-flex items-center gap-2 text-gray-700">
                  <input type="checkbox" class="form-checkbox h-5 w-5 text-indigo-500" />
                  <span>{{ filter.name }}</span>
                </label>
              </template>
            </div>
          </div>

          <button v-if="filters.length > 2" @click="openFiltersModal"
            class="self-start mt-2 text-indigo-600 hover:underline text-sm">
            Toon alle filters
          </button>
        </div>

        <button
          class="mt-6 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 px-6 rounded-md shadow-md transition-colors">
          Apply filters
        </button>
      </div>
    </div>



    <table class="w-full border border-gray-300 bg-white mt-10">
      <thead class="bg-white">
        <tr class="text-left border-b">
          <th class="px-4 py-2">Naam ⬍</th>
          <th class="px-4 py-2">Aantal ⬍</th>
          <th class="px-4 py-2 text-center">Openzetten ⬍</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="room in escapeRooms" :key="room.id" class="border-t border-gray-400">
          <td class="px-4 py-2">{{ room.name }}</td>
          <td class="px-4 py-2">
            <div v-if="isEditing[room.id]" class="flex items-center gap-2">
              <input type="number" class="w-20 border rounded px-2 py-1" v-model.number="localCapacities[room.id]"
                :min="0" :max="room.maxCapacity" />
              <span class="text-sm text-gray-600">/ {{ room.maxCapacity }}</span>
            </div>
            <span v-else :class="{
              'bg-green-400 text-white px-3 py-1 rounded-full text-sm': room.currentCapacity < room.maxCapacity,
              'bg-red-400 text-white px-3 py-1 rounded-full text-sm': room.currentCapacity >= room.maxCapacity
            }">
              {{
                room.currentCapacity < room.maxCapacity ? room.maxCapacity - room.currentCapacity : 'Gevuld' }} </span>
          </td>
          <td class="px-4 py-2 flex gap-2 justify-center">
            <button class="border rounded p-1 w-8 h-8 flex items-center justify-center" v-if="isEditing[room.id]"
              @click="saveCapacity(room.id)" title="Opslaan">
              ✔️
            </button>
            <button class="border rounded p-1 w-8 h-8 flex items-center justify-center" v-else
              @click="editEscapeRoom(room.id)" title="Bewerk">
              ✏️
            </button>
            <button class="border rounded p-1 w-8 h-8 flex items-center justify-center text-red-500 hover:bg-red-100"
              @click="deleteEscapeRoom(room.id)" title="Verwijder">
              🗑️
            </button>
          </td>

        </tr>
      </tbody>
    </table>

    <div class="mt-4">
      <button class="bg-blue-800 text-white px-4 py-2 rounded flex items-center gap-2" @click="openAddModal">
        Voeg groep toe <span class="text-xl">+</span>
      </button>
    </div>

    <!-- Modal -->
    <div v-if="showAddModal" class="fixed inset-0 bg-black/50 flex items-center justify-center z-50 overflow-y-auto">
      <div class="bg-white rounded-lg p-6 w-full max-w-md my-10 max-h-[90vh] shadow-lg overflow-y-auto">
        <div class="flex justify-between items-start mb-7">
          <h2 class="text-lg font-bold">Nieuwe groep toevoegen</h2>
          <button @click="closeAddModal" class="text-red-600 hover:text-red-800 text-3xl font-bold leading-none"
            aria-label="Sluit modal">
            &times;
          </button>
        </div>


        <div class="mb-4">
          <label class="block font-semibold mb-1">Name</label>
          <input type="text" v-model="newEscapeRoom!.name" class="w-full border rounded px-3 py-2" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Description</label>
          <textarea type="text" v-model="newEscapeRoom!.description" class="w-full border rounded px-3 py-2 h-30" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Organisation-Id</label>
          <input type="number" v-model.number="newEscapeRoom!.organizationId" min="1"
            class="w-20 border rounded px-3 py-2" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Address</label>
          <textarea type="text" v-model="newEscapeRoom!.address" class="w-full border rounded px-3 py-2 h-30" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Postal Code</label>
          <input type="number" v-model.number="newEscapeRoom!.postalCode" min="1"
            class="w-20 border rounded px-3 py-2" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">City</label>
          <textarea type="text" v-model="newEscapeRoom!.city" class="w-full border rounded px-3 py-2 h-30" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Website</label>
          <textarea type="text" v-model="newEscapeRoom!.website" class="w-full border rounded px-3 py-2 h-30" />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Phone number</label>
          <textarea type="text" v-model="newEscapeRoom!.phoneNumber" class="w-full border rounded px-3 py-2 h-30" />
        </div>

        <div class="mb-4 flex gap-6 justify-between">
          <div>
            <label class="block font-semibold mb-1">Maximum capacity</label>
            <input type="number" v-model.number="newEscapeRoom!.maxCapacity" min="1"
              class="w-20 border rounded px-3 py-2" />
          </div>
        </div>


        <div class="flex justify-end gap-2">
          <button @click="closeAddModal" class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">
            Annuleer
          </button>
          <button @click="saveEscapeRoom" class="px-4 py-2 bg-blue-800 text-white rounded hover:bg-blue-900">
            Opslaan
          </button>
        </div>
      </div>
    </div>
  </div>

  <!-- Filters Modal (Mobile) -->
  <div v-if="showFiltersModal" class="fixed inset-0 z-50 flex items-center justify-center bg-black/50 px-4">
    <div class="bg-white rounded-lg p-6 w-full max-w-lg max-h-[90vh] overflow-y-auto">
      <div class="flex justify-between items-center mb-4">
        <h2 class="text-lg font-semibold">Alle filters</h2>
        <button @click="closeFiltersModal" class="text-red-600 text-2xl leading-none">&times;</button>
      </div>

      <div class="flex flex-col gap-4">
        <div v-for="filter in filters" :key="filter.id" class="flex flex-col bg-gray-50 rounded-md p-3">
          <template v-if="filter.active">
            <label class="text-sm font-medium text-gray-700 mb-2 block">{{ filter.name }}</label>
            <input v-if="filter.inputType === 'textbox'" type="text"
              class="w-full border border-gray-800 rounded-md px-3 py-2" />
            <select v-else-if="filter.inputType === 'dropdown'"
              class="w-full border border-gray-300 rounded-md px-3 py-2">
              <option value="">Kies een optie</option>
              <option>Optie 1</option>
              <option>Optie 2</option>
            </select>
            <label v-else-if="filter.inputType === 'checkbox'" class="inline-flex items-center gap-2 text-gray-700">
              <input type="checkbox" class="form-checkbox h-5 w-5 text-indigo-500" />
              <span>{{ filter.name }}</span>
            </label>
          </template>
        </div>
      </div>

      <div class="mt-6 flex justify-end">
        <button @click="closeFiltersModal" class="px-4 py-2 bg-blue-700 text-white rounded hover:bg-blue-800">
          Sluiten
        </button>
      </div>
    </div>
  </div>

</template>
