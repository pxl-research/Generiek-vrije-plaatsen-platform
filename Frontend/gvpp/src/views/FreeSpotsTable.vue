<script setup lang="ts">
import { ref, onMounted } from 'vue';
import HeaderComponent from "@/components/HeaderComponent.vue";
import type {Filter} from "@/models/filter.ts";
import {EscapeRoomRequest} from "@/models/escapeRoomRequest.ts";
import type {EscapeRoom} from "@/models/escapeRoom.ts";
import {useEscapeRoomStore} from "@/store/EscapeRoomStore.ts";

const escapeRooms = ref<EscapeRoom[]>([]);
const localCapacities = ref<Record<number, number>>({});
const isEditing = ref<Record<number, boolean>>({});
const showAddModal = ref(false);
const filters = ref<Filter[]>([]);

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
  showAddModal.value = true;
}

function closeAddModal() {
  showAddModal.value = false;
}

function addEscapeRoom() {

  const roomToAdd: EscapeRoomRequest = {
    name: newEscapeRoom.value!.name,
    description: newEscapeRoom.value!.description,
    organizationId: newEscapeRoom.value!.organizationId,
    address: newEscapeRoom.value!.address,
    postalCode: newEscapeRoom.value!.postalCode,
    city: newEscapeRoom.value!.city,
    email: newEscapeRoom.value!.email,
    phoneNumber: newEscapeRoom.value!.phoneNumber,
    website: newEscapeRoom.value!.website,
    maxCapacity: newEscapeRoom.value!.maxCapacity,
  };

  try {
    escapeRoomStore.addEscapeRoom(roomToAdd);
  }
  catch (error) {
    console.error("Error: " + error);
  }
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
  filters.value = filterData.sort((a : Filter, b : Filter) => a.id - b.id);
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

    <div class="w-full border border-gray-300 bg-white mt-10">
      <div class="px-4 py-2">
        <p>Filters</p>
        <div class="flex flex-wrap gap-4">
          <div v-for="filter in filters" :key="filter.id" class="flex flex-col">
            <div v-if="filter.active === true" class="py-2">
              <label class="block text-sm font-medium mb-1">{{ filter.name }}</label>
              <select v-if="filter.inputType === 'dropdown'" class="border rounded px-3 py-2">
                <option value="">Kies een optie</option>
                <!-- Placeholder options - you can customize this per filter -->
                <option>Optie 1</option>
                <option>Optie 2</option>
              </select>
              <input v-else-if="filter.inputType === 'textbox'" type="text" class="border-2"/>
              <input v-else-if="filter.inputType === 'checkbox'" type="checkbox"/>
            </div>
          </div>
        </div>
        <button>Apply filters</button>
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
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 overflow-y-auto"
    >
      <div class="bg-white rounded-lg p-6 w-full max-w-md my-10 max-h-[90vh] shadow-lg overflow-y-auto">
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
          <label class="block font-semibold mb-1">Name</label>
          <input
            type="text"
            v-model="newEscapeRoom!.name"
            class="w-full border rounded px-3 py-2"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Description</label>
          <textarea
            type="text"
            v-model="newEscapeRoom!.description"
            class="w-full border rounded px-3 py-2 h-30"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Organisation-Id</label>
          <input
            type="number"
            v-model.number="newEscapeRoom!.organizationId"
            min="1"
            class="w-20 border rounded px-3 py-2"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Address</label>
          <textarea
            type="text"
            v-model="newEscapeRoom!.address"
            class="w-full border rounded px-3 py-2 h-30"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Postal Code</label>
          <input
            type="number"
            v-model.number="newEscapeRoom!.postalCode"
            min="1"
            class="w-20 border rounded px-3 py-2"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">City</label>
          <textarea
            type="text"
            v-model="newEscapeRoom!.city"
            class="w-full border rounded px-3 py-2 h-30"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Website</label>
          <textarea
            type="text"
            v-model="newEscapeRoom!.website"
            class="w-full border rounded px-3 py-2 h-30"
          />
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Phone number</label>
          <textarea
            type="text"
            v-model="newEscapeRoom!.phoneNumber"
            class="w-full border rounded px-3 py-2 h-30"
          />
        </div>

        <div class="mb-4 flex gap-6 justify-between">
          <div>
            <label class="block font-semibold mb-1">Maximum capacity</label>
            <input
              type="number"
              v-model.number="newEscapeRoom!.maxCapacity"
              min="1"
              class="w-20 border rounded px-3 py-2"
            />
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
