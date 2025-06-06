import { defineStore } from 'pinia';
import {FilterRequest} from "@/models/filterRequest.ts";

const urlBase = "http://localhost:8080/api/filters";

export const useFilterStore = defineStore('filter',  {

  state: () => ({

  }),
  actions: {
    async editFilterProperties(id: number, request: FilterRequest) {
      try {
        const response = await fetch(urlBase + `/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(request)
        });

        if (!response.ok) {
          console.log("Failed to edit filter: " + response.statusText);
        }

        console.log("Filter applied");
      } catch (error) {
        console.error(error);
      }
    },

    async getFilters() {
      try {
        const response = await fetch(urlBase, {
          method: 'GET',
        });
        if (!response.ok) {
          throw new Error("Failed to get Filters: " + response.statusText);
        }
        const result = await response.json();
        return await result;
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    }
  }
});
