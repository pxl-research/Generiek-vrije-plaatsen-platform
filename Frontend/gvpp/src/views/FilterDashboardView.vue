<script setup lang="ts">

import HeaderComponent from "@/components/HeaderComponent.vue";
import { ref, onMounted } from 'vue';
import {useFilterStore} from "@/store/FilterStore.ts";
import {FilterRequest} from "@/models/filterRequest.ts";
import type {Filter} from "@/models/filter.ts";

const filters = ref<Filter[]>([]);
const filterStore = useFilterStore();

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080/api/filters');
    if (!response.ok) {
      throw new Error('Failed to fetch filters');
    }
    const data = await response.json();
    filters.value = data.sort((a : Filter, b : Filter) => a.id - b.id);
    console.log(filters.value)
  } catch (error) {
    console.error(error)
  }
})

function enableOrDisableFilter(event : Event, filter : Filter) {
  console.log("checkbox ticked for filter: ", filter.name);
  const isChecked = (event.target as HTMLInputElement).checked;

  const filterRequest : FilterRequest = new FilterRequest(filter.name, filter.datatype, filter.value, filter.active, filter.inputType);

  filterRequest.active = isChecked;

  filterStore.editFilterProperties(filter.id, filterRequest);
}

function switchInputType(event : Event, filter : Filter) {
  const selected = (event.target as HTMLSelectElement).value;
  console.log(selected);

  const filterRequest : FilterRequest = new FilterRequest(filter.name, filter.datatype, filter.value, filter.active, filter.inputType);

  filterRequest.inputType = selected;

  filterStore.editFilterProperties(filter.id, filterRequest)

}


</script>

<template>
  <HeaderComponent/>
  <div class="p-6">
    <h2 class="text-xl font-semibold mb-4">Filters</h2>

    <table class="w-full border border-gray-300 rounded-lg overflow-hidden">
      <thead class="bg-gray-100">
      <tr>
        <th class="text-left p-3 border-b">Filter</th>
        <th class="text-left p-3 border-b">Datatype</th>
        <th class="text-left p-3 border-b">Is Active</th>
        <th class="text-left p-3 border-b">Input type</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(filter, index) in filters" :key="index" class="hover:bg-gray-50">
        <td class="p-3 border-b">{{ filter.name }}</td>
        <td class="p-3 border-b">{{ filter.datatype }}</td>
        <td class="p-3 border-b">
          <input type="checkbox" :checked="filter.active" @click="enableOrDisableFilter($event, filter)"/>
        </td>
        <td class="p-3 border-b">
          <select name="UI element" :value="filter.inputType" @change="switchInputType($event, filter)" class="input-dropdown">
            <option value="textbox">Textbox</option>
            <option value="dropdown">Dropdown</option>
            <option value="checkbox">Checkbox</option>
            <option value="slider">Slider</option>
          </select>
        </td>
      </tr>
      <tr v-if="filters.length === 0">
        <td colspan="3" class="p-3 text-center text-gray-500">No filters available</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>
