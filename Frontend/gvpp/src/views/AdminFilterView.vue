<template>
  <HeaderComponent />
  <h1 class="mt-20 text-xl ml-20">Filters</h1>
  <div class="filtercontainer bg-gray-300 w-80 h-90 m-auto mt-20 p-4 rounded-lg">
    <div class="row1 flex bg-gray-100 justify-between p-3 rounded-md">
      <p>Checkboxes</p>
      <p>Filter</p>
    </div>

    <div
      v-for="(filter, index) in filters"
      :key="index"
      class="row1 flex justify-between mt-5 border-b pb-2"
    >
      <input
        type="checkbox"
        :id="filter.id"
        v-model="selectedFilters[filter.id]"
        class="w-5 h-5 text-blue-600 bg-gray-200 border-gray-300 rounded focus:ring-blue-500 ml-2"
      />
      <label :for="filter.id" class="mr-5">{{ filter.label }}</label>
    </div>
  </div>

  <button
    class="bg-green-500 text-white rounded-xl p-3 flex mx-auto mt-10 hover:bg-green-600 transition"
    @click="saveFilters"
  >
    Save
  </button>

  <RouterLink to="/freespotsdashboard">
    <button class="bg-blue-600 text-white px-4 py-2 rounded-xl p-3 flex mx-auto mt-10 hover:bg-blue-700 ">
      Ga naar Vrije Plaatsen Dashboard
    </button>
  </RouterLink>



</template>

<script setup lang="ts">
import { reactive } from 'vue';
import HeaderComponent from '@/components/HeaderComponent.vue';
import FreeSpotsTable from '@/views/FreeSpotsTable.vue';

interface Filter {
  id: string;
  label: string;
}

const filters: Filter[] = [
  { id: "CheckboxLocation", label: "Location" },
  { id: "CheckboxAge", label: "Age filter" },
  { id: "CheckboxMinPrice", label: "Min price" },
  { id: "CheckboxMaxPrice", label: "Max price" },
];

const selectedFilters = reactive<Record<string, boolean>>({});

const saveFilters = (): void => {
  const selected = Object.keys(selectedFilters).filter(
    (key) => selectedFilters[key]
  );
  alert(`Selected Filters: ${selected.length ? selected.join(", ") : "None"}`);
};
</script>
