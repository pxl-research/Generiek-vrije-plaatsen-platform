//import { ref } from 'vue';
import {defineStore} from 'pinia';
import {EscapeRoomRequest} from "@/models/escapeRoomRequest.ts";

const urlBase = 'http://localhost:8080/api/escaperooms'

export const useEscapeRoomStore = defineStore('escapeRoomStore', {
  state: () => ({

  }),
  actions: {
    async addEscapeRoom(request: EscapeRoomRequest) {
      try {
        const response = await fetch(urlBase, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(request)
        });

        if (!response.ok) {
          console.log("Failed to add Escape room: " + response.statusText);
        }

        console.log("Escape room added");
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    },

    async getEscapeRooms() {
      try {
        const response = await fetch(urlBase, {
          method: 'GET',
          headers: {
            "Content-Type": "application/json"
          }
        });
        if (!response.ok) {
          throw new Error("Failed to get EscapeRooms: " + response.statusText);
        }
        const data = await response.json();
        console.log(data);
        return data;
      } catch (error) {
        console.error("Whoops! Something went wrong: " + error);
      }
    },

    async deleteEscapeRoom(roomId: number) {
      try {
        const response = await fetch(urlBase + `/${roomId}`, {
          method: 'DELETE',
        });

        if (!response.ok) {
          console.log(`Failed to delete room with id ${roomId}`);
        }


      } catch (error: unknown) {
        console.error("Delete error:", error);
        alert("Could not delete room. Please try again.");
      }
    }
  }
});
