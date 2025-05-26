//import { ref } from 'vue';
import { defineStore } from 'pinia';
import {FilterRequest} from "@/models/filterRequest.ts";



export const useFilterStore = defineStore('filterStore', () => {

  async function editFilterProperties(id: number, request: FilterRequest) {
    try {
      const response = await fetch('http://localhost:8080/api/filters/' + id, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(request)
      });

      if (!response.ok) {
        throw new Error("Failed to edit filter: " + response.statusText);
      }

      console.log("Filter applied");
    } catch (error) {
      console.error(error);
    }
  }

  return {
    editFilterProperties,
  }
});
