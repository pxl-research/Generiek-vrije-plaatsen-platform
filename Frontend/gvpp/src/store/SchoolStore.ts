//import { ref } from 'vue';
import {defineStore} from 'pinia';
import {BranchRequest} from "@/models/BranchRequest.ts";

const urlBase = 'http://localhost:8080/api/schools'

export const useSchoolStore = defineStore('schoolStore', {
  state: () => ({

  }),
  actions: {
    async addSchool(request: BranchRequest) {
      try {
        const response = await fetch(urlBase, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(request)
        });

        if (!response.ok) {
          console.log("Failed to add School: " + response.statusText);
        }

        console.log("School added");
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    },

    async getSchools() {
      try {
        const response = await fetch(urlBase, {
          method: 'GET',
          headers: {
            "Content-Type": "application/json"
          }
        });
        if (!response.ok) {
          throw new Error("Failed to get schools: " + response.statusText);
        }
        const data = await response.json();
        console.log(data);
        return data;
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    },

    async deleteSchool(schoolId: number) {
      try {
        const response = await fetch(urlBase + `/${schoolId}`, {
          method: 'DELETE',
        });

        if (!response.ok) {
          console.log(`Failed to delete school with id ${schoolId}`);
        }


      } catch (error: unknown) {
        console.error("Delete error:", error);
        alert("Could not delete school. Please try again.");
      }
    }
  }
});
