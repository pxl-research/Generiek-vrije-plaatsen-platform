<script setup lang="ts">
import HeaderComponent from '@/components/HeaderComponent.vue';
import { ref, onMounted } from 'vue';
import type { Filter } from '@/models/filter.ts';
import type { Branch } from '@/models/Branch.ts';
import { useFilterStore } from '@/store/FilterStore.ts';
import type {BranchRequest} from "@/models/BranchRequest.ts";
import {RoomRequest} from "@/models/RoomRequest.ts";
import {useNurseryStore} from "@/store/NurseryStore.ts";
import {useNurseryRoomStore} from "@/store/NurseryRoomStore.ts";

const filterStore = useFilterStore();
const nurseryStore = useNurseryStore();
const roomStore = useNurseryRoomStore();

const nurseriesData = ref<Branch[]>([]);
const filters = ref<Filter[]>([]);
const showAddModal = ref(false);
const newNursery = ref<BranchRequest>();
const isEditing = ref<Record<number, boolean>>({});
const localCapacities = ref<Record<number, number>>({});

const openAddModal = () => showAddModal.value = true;
const closeAddModal = () => showAddModal.value = false;

const expandedCards = ref<{ [key: string]: boolean }>({});

const toggleExpand = (name: string) => {
  expandedCards.value[name] = !expandedCards.value[name];

  const room = nurseriesData.value.find(er => er.name === name);
  if (room) {
    room.rooms.forEach(r => {
      localCapacities.value[r.id] = r.currentCapacity;
    });
  }
};

async function applyFilters() {
  await getNurseriesFromBackend();

  nurseriesData.value = nurseriesData.value.filter((nursery) => {
    return filters.value.every((filter) => {
      if (filter.name === "City") {
        return nursery.city.includes(filter.value);
      }
      // Add more escape room specific filters here
      return true;
    });
  });

  // Further filter rooms within each escape room
  nurseriesData.value.forEach((nursery) => {
    nursery.rooms = nursery.rooms.filter((room) => {
      return filters.value.every((filter) => {
        if (filter.name === "Minimum Age") {
          return room.minimumAge >= Number(filter.value);
        }
        if (filter.name === "Duration") {
          return room.duration >= Number(filter.value);
        }
        return true;
      });
    });
  });

  // Only keep escape rooms that still have rooms after filtering
  nurseriesData.value = nurseriesData.value.filter((nursery) => nursery.rooms.length > 0);
}

async function getFiltersFromBackend() {
  filters.value = await filterStore.getFilters();
  filters.value = filters.value.sort((a, b) => a.id - b.id);
}

async function getNurseriesFromBackend() {
  nurseriesData.value = await nurseryStore.getNurseries();
  nurseriesData.value = nurseriesData.value.sort((a, b) => a.id - b.id);
}

function toggleEdit(roomId: number) {
  isEditing.value[roomId] = !isEditing.value[roomId];
}

async function saveCapacity(roomId: number) {

  const newCapacity = localCapacities.value[roomId];

  if (newCapacity === undefined) return;

  const request = new RoomRequest(newCapacity);

  try {
    await roomStore.updateNurseryRoom(roomId, request);
    await getNurseriesFromBackend(); // Refresh data
    toggleEdit(roomId);
  } catch (error) {
    console.error("Update error:", error);
    alert("Failed to update capacity.");
  }
}

async function deleteNurseryRoom(roomId: number): Promise<void> {
  await roomStore.deleteNurseryRoom(roomId);
}

async function addNursery(): Promise<void> {
  try {
    if (newNursery.value) {
      await nurseryStore.addNursery(newNursery.value);
      await getNurseriesFromBackend();
      closeAddModal();
    }
  } catch (error) {
    console.error("Add error:", error);
    alert("Failed to add new room.");
  }
}

onMounted(async () => {
  await getFiltersFromBackend();
  await getNurseriesFromBackend();
});
</script>

<template>
  <HeaderComponent />
  <div class="bg-slate-200 min-h-screen p-4">
    <div class="w-full border border-gray-300 bg-white mt-10">
      <div class="px-4 py-2">
        <h1 class="text-2xl font-bold w-full text-left mt-2">Admin</h1>
        <p>Filters</p>
        <div class="flex flex-wrap gap-4">
          <div v-for="filter in filters" :key="filter.id" class="flex flex-col">
            <div v-if="filter.active" class="py-2">
              <label class="block text-sm font-medium mb-1">{{ filter.name }}</label>
              <select v-if="filter.inputType === 'dropdown'" class="border rounded px-3 py-2">
                <option value="">Kies een optie</option>
                <option>Optie 1</option>
                <option>Optie 2</option>
              </select>
              <input v-else-if="filter.inputType === 'textbox'" type="text" class="border-2" />
              <input v-else-if="filter.inputType === 'checkbox'" type="checkbox" />
            </div>
          </div>
        </div>
        <button class="mt-4 border border-gray-400 rounded px-4 py-2 bg-white hover:bg-gray-100" @click="applyFilters">
          Pas filters toe
        </button>
      </div>
    </div>

    <div class="w-full mt-10 mb-5 grid gap-6">
      <div v-for="escaperoom in nurseriesData" :key="escaperoom.id"
           class="bg-white p-5 rounded-lg shadow-md border-indigo-400 border">
        <div class="flex justify-between items-start">
          <div>
            <h2 class="text-xl font-bold">{{ escaperoom.name }}</h2>
            <p class="pt-2">{{ escaperoom.address }}, {{ escaperoom.postalCode }} {{ escaperoom.city }}</p>
          </div>
          <a :href="escaperoom.website" target="_blank" class="text-blue-500 underline text-sm">
            Visit Website
          </a>
        </div>

        <div v-if="expandedCards[escaperoom.name]" class="mt-4">
          <div
            v-for="room in escaperoom.rooms"
            :key="room.id"
            class="flex justify-between items-center p-3 bg-blue-100 border-b"
          >
            <div>
              <h3 class="font-bold">{{ room.name }}</h3>
              <p class="text-sm text-gray-600">Minimumleeftijd: {{ room.minimumAge }}</p>
              <p class="text-sm text-gray-600">Duratie: {{ room.duration }} uur</p>
            </div>
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
            <button
              class="border rounded p-1 w-8 h-8 flex items-center justify-center"
              v-if="isEditing[room.id]"
              @click="saveCapacity(room.id)"
              :disabled="localCapacities[room.id] === room.currentCapacity"
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
              @click="deleteNurseryRoom(room.id)"
              title="Verwijder"
            >
              🗑️
            </button>
          </div>
        </div>

        <button @click="toggleExpand(escaperoom.name)"
                class="bg-[#2473BA] text-white px-3 py-2 rounded-md hover:bg-blue-600 transition mt-4">
          {{ expandedCards[escaperoom.name] ? "Less Info" : "More Info" }}
        </button>
      </div>
    </div>

    <div class="mt-4">
      <button
        class="bg-blue-800 text-white px-4 py-2 rounded flex items-center gap-2"
        @click="openAddModal"
      >
        Voeg groep toe <span class="text-xl">+</span>
      </button>
    </div>

    <div
      v-if="showAddModal"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 overflow-y-auto">
      <div class="bg-white rounded-lg p-6 w-full max-w-md my-10 max-h-[90vh] shadow-lg overflow-y-auto">
        <div class="flex justify-between items-start mb-7">
          <h2 class="text-lg font-bold">Nieuwe groep toevoegen</h2>
          <button
            @click="closeAddModal"
            class="text-red-600 hover:text-red-800 text-3xl font-bold leading-none"
            aria-label="Sluit modal">&times;</button>
        </div>

        <div class="mb-4">
          <label class="block font-semibold mb-1">Name</label>
          <input type="text" v-model="newNursery!.name" class="w-full border rounded px-3 py-2" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">Description</label>
          <textarea v-model="newNursery!.description" class="w-full border rounded px-3 py-2 h-30" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">Organisation-Id</label>
          <input type="number" v-model.number="newNursery!.organizationId" min="1" class="w-20 border rounded px-3 py-2" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">Address</label>
          <textarea v-model="newNursery!.address" class="w-full border rounded px-3 py-2 h-30" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">Postal Code</label>
          <input type="number" v-model.number="newNursery!.postalCode" min="1" class="w-20 border rounded px-3 py-2" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">City</label>
          <textarea v-model="newNursery!.city" class="w-full border rounded px-3 py-2 h-30" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">Website</label>
          <textarea v-model="newNursery!.website" class="w-full border rounded px-3 py-2 h-30" />
        </div>
        <div class="mb-4">
          <label class="block font-semibold mb-1">Phone number</label>
          <textarea v-model="newNursery!.phoneNumber" class="w-full border rounded px-3 py-2 h-30" />
        </div>

        <div class="flex justify-end gap-2">
          <button @click="closeAddModal" class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">Annuleer</button>
          <button @click="addNursery" class="px-4 py-2 bg-blue-800 text-white rounded hover:bg-blue-900">Opslaan</button>
        </div>
      </div>
    </div>
  </div>
</template>
