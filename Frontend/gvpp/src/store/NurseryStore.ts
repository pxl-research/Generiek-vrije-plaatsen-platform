//import { ref } from 'vue';
import {defineStore} from 'pinia';
import {BranchRequest} from "@/models/BranchRequest.ts";

const urlBase = 'http://localhost:8080/api/nurseries'

export const useNurseryStore = defineStore('nurseryStore', {
  state: () => ({

  }),
  actions: {
    async addNursery(request: BranchRequest) {
      try {
        const response = await fetch(urlBase, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(request)
        });

        if (!response.ok) {
          console.log("Failed to add nursery: " + response.statusText);
        }

        console.log("Nursery added");
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    },

    async getNurseries() {
      try {
        const response = await fetch(urlBase, {
          method: 'GET',
          headers: {
            "Content-Type": "application/json"
          }
        });
        if (!response.ok) {
          throw new Error("Failed to get nurseries: " + response.statusText);
        }
        const data = await response.json();
        console.log(data);
        return data;
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    },

    async deleteSchool(nurseryId: number) {
      try {
        const response = await fetch(urlBase + `/${nurseryId}`, {
          method: 'DELETE',
        });

        if (!response.ok) {
          console.log(`Failed to delete nursery with id ${nurseryId}`);
        }


      } catch (error: unknown) {
        console.error("Delete error:", error);
        alert("Could not delete nursery. Please try again.");
      }
    }
  }
});
