import {defineStore} from 'pinia';
import type {EscapeRoomRoomRequest} from "@/models/EscapeRoomRoomRequest.ts";

const urlBase = 'http://localhost:8080/api/escaperooms/rooms'

export const useEscapeRoomRoomStore = defineStore('EscapeRoomRoomStore', {
  state: () => ({

  }),
  actions: {
    async deleteEscapeRoomRoom(roomId: number) {
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
    },

    async updateEscapeRoomRoom(roomId: number, request: EscapeRoomRoomRequest) {
      try {
        const response = await fetch(urlBase + `/${roomId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(request),
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
})
