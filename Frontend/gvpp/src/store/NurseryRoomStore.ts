import {defineStore} from 'pinia';
import type {RoomRequest} from "@/models/RoomRequest.ts";

const urlBase = 'http://localhost:8080/api/nurseries/rooms'

export const useNurseryRoomStore = defineStore('nurseryRoomStore', {
  state: () => ({

  }),
  actions: {
    async deleteNurseryRoom(roomId: number) {
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

    async updateNurseryRoom(roomId: number, request: RoomRequest) {
      try {
        const response = await fetch(urlBase + `/${roomId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(request),
        });

        if (!response.ok) {
          console.log(`Failed to update room with id ${roomId}`);
        }


      } catch (error: unknown) {
        console.error("Update error:", error);
        alert("Could not update room. Please try again.");
      }

    }
  }
})
