//import { ref } from 'vue';
import { defineStore } from 'pinia';
import {EscapeRoomRequest} from "@/models/escapeRoomRequest.ts";



export const useEscapeRoomStore = defineStore('filterStore', () => {

  async function addEscapeRoom(request: EscapeRoomRequest) {
    try {
      const response = await fetch('http://localhost:8080/api/escaperooms', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(request)
      });

      if (!response.ok) {
        throw new Error("Failed to add Escape room: " + response.statusText);
      }

      console.log("Escape room added");
    } catch (error) {
      console.error("Whoops! Something went wrong: " + error);
    }
  }

  return {
    addEscapeRoom,
  }
});
