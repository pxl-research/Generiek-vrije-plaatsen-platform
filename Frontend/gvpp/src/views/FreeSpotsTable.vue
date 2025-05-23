<script setup lang="ts">
import { ref, onMounted } from 'vue';
import HeaderComponent from "@/components/HeaderComponent.vue";

interface EscapeRoom {
  id: number;
  name: string;
  address: string;
  postalCode: number;
  city: string;
  email: string;
  phoneNumber: string;
  website: string;
  currentCapacity: number;
  maxCapacity: number;
}
// filter interface voor option value
interface Filter {
  id: number;
  name: string;
}
const escapeRooms = ref<EscapeRoom[]>([]);
const localCapacities = ref<Record<number, number>>({});
const isEditing = ref<Record<number, boolean>>({});
const showAddModal = ref(false);
const filters = ref<Filter[]>([]);

const newEscapeRoom = ref({
  name: '',
  description: '',
  freeSpots: 1,
  filter: '',
});

function openAddModal() {
  showAddModal.value = true;
}

function closeAddModal() {
  showAddModal.value = false;
  newEscapeRoom.value = { name: '', description: '', freeSpots: 1, filter: '' };
}

function addEscapeRoom() {
  const newId = escapeRooms.value.length ? Math.max(...escapeRooms.value.map(r => r.id)) + 1 : 1;

  const newRoom: EscapeRoom = {
    id: newId,
    name: newEscapeRoom.value.name,
    address: '',
    postalCode: 0,
    city: '',
    email: '',
    phoneNumber: '',
    website: '',
    currentCapacity: 0,
    maxCapacity: Math.max(newEscapeRoom.value.freeSpots, 1),
  };

  escapeRooms.value.push(newRoom);
  escapeRooms.value.sort((a, b) => a.name.localeCompare(b.name));
  closeAddModal();
}

function deleteEscapeRoom(roomId: number) {
  escapeRooms.value = escapeRooms.value.filter(room => room.id !== roomId);
  delete localCapacities.value[roomId];
  delete isEditing.value[roomId];
}


onMounted(async () => {
  try {
    const res = await fetch('/api/escaperooms');
    if (!res.ok) throw new Error(`Failed to fetch escape rooms: ${res.status}`);

    const data: EscapeRoom[] = await res.json();
    data.sort((a, b) => a.name.localeCompare(b.name));
    escapeRooms.value = data;

    data.forEach(room => {
      localCapacities.value[room.id] = room.currentCapacity;
      isEditing.value[room.id] = false;
    });

    const filterRes = await fetch('/api/filters');
    if (!filterRes.ok) throw new Error(`Failed to fetch filters: ${filterRes.status}`);

    const filterData: Filter[] = await filterRes.json();
    filters.value = filterData;

  } catch (error) {
    console.error('Error during mounted hook:', error);
    // Optionally show user feedback, e.g., error message in UI
  }
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


    <table class="w-full border border-gray-300 bg-white mt-10">
      <thead class="bg-white">
      <tr class="text-left border-b">
        <th class="px-4 py-2">Naam ⬍</th>
        <th class="px-4 py-2">Aantal ⬍</th>
        <th class="px-4 py-2 text-center">Openzetten ⬍</th>
      </tr>
      </thead>
      <tbody>
      <tr
        v-for="room in escapeRooms"
        :key="room.id"
        class="border-t border-gray-400"
      >
        <td class="px-4 py-2">{{ room.name }}</td>
        <td class="px-4 py-2">
          <div v-if="isEditing[room.id]" class="flex items-center gap-2">
            <input
              type="number"
              class="w-20 border rounded px-2 py-1"
              v-model.number="localCapacities[room.id]"
              :min="0"
              :max="room.maxCapacity"
            />
            <span class="text-sm text-gray-600">/ {{ room.maxCapacity }}</span>
          </div>
          <span
            v-else
            :class="{
                'bg-green-400 text-white px-3 py-1 rounded-full text-sm': room.currentCapacity < room.maxCapacity,
                'bg-red-400 text-white px-3 py-1 rounded-full text-sm': room.currentCapacity >= room.maxCapacity
              }"
          >
              {{
              room.currentCapacity < room.maxCapacity
                ? room.maxCapacity - room.currentCapacity
                : 'Gevuld'
            }}
            </span>
        </td>
        <td class="px-4 py-2 flex gap-2 justify-center">
          <button
            class="border rounded p-1 w-8 h-8 flex items-center justify-center"
            v-if="isEditing[room.id]"
            @click="saveCapacity(room.id)"
            title="Opslaan"
          >
            ✔️
          </button>
          <button
            class="border rounded p-1 w-8 h-8 flex items-center justify-center"
            v-else
            @click="toggleEdit(room.id)"
            title="Bewerk"
          >
            ✏️
          </button>
          <button
            class="border rounded p-1 w-8 h-8 flex items-center justify-center text-red-500 hover:bg-red-100"
            @click="deleteEscapeRoom(room.id)"
            title="Verwijder"
          >
            🗑️
          </button>
        </td>

      </tr>
      </tbody>
    </table>

    <div class="mt-4">
      <button
        class="bg-blue-800 text-white px-4 py-2 rounded flex items-center gap-2"
        @click="openAddModal"
      >
        Voeg groep toe <span class="text-xl">+</span>
      </button>
    </div>

    <!-- Modal -->
    <div
      v-if="showAddModal"
      class="fixed inset-0 bg-slate-500 bg-opacity-50 flex items-center justify-center z-50"
    >
      <div class="bg-white rounded-lg p-6 w-full max-w-md shadow-lg">
        <div class="flex justify-between items-start mb-7">
          <h2 class="text-lg font-bold">Nieuwe groep toevoegen</h2>
          <button
            @click="closeAddModal"
            class="text-red-600 hover:text-red-800 text-3xl font-bold leading-none"
            aria-label="Sluit modal"
          >
            &times;
          </button>
        </div>


        <div class="mb-4">
          <label class="block font-semibold mb-1">Titel</label>
          <input
            type="text"
            v-model="newEscapeRoom.name"
            class="w-full border rounded px-3 py-2"
            placeholder="Organisatie xx - organisatie"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Beschrijving</label>
          <textarea
            type="text"
            v-model="newEscapeRoom.description"
            class="w-full border rounded px-3 py-2 h-30"
            placeholder="Voer een beschrijving in"
          />
        </div>

        <div class="mb-4 flex gap-6 justify-between">
          <div>
            <label class="block font-semibold mb-1">Aantal</label>
            <input
              type="number"
              v-model.number="newEscapeRoom.freeSpots"
              min="1"
              class="w-20 border rounded px-3 py-2"
            />
          </div>

          <div>
            <label class=" block font-semibold mb-1">&nbsp;</label>
            <select
              v-model="newEscapeRoom.filter"
              class="w-60  border rounded px-3 py-2"
            >
              <option disabled value="">Kies filter</option>
              <option
                v-for="filter in filters"
                :key="filter.id"
                :value="filter.name"
              >
                {{ filter.name }}
              </option>
            </select>
          </div>
        </div>


        <div class="flex justify-end gap-2">
          <button
            @click="closeAddModal"
            class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
          >
            Annuleer
          </button>
          <button
            @click="addEscapeRoom"
            class="px-4 py-2 bg-blue-800 text-white rounded hover:bg-blue-900"
          >
            Opslaan
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
