import type {EscapeRoomRoom} from "@/models/escapeRoomRoom.ts";

export class EscapeRoom {
  id: number;
  name: string;
  description: string;
  address: string;
  postalCode: number;
  city: string;
  email: string;
  phoneNumber: string;
  website: string;
  rooms: EscapeRoomRoom[];


  constructor(
    id: number,
    name: string,
    description: string,
    address: string,
    postalCode: number,
    city: string,
    email: string,
    phoneNumber: string,
    website: string,
    rooms: EscapeRoomRoom[]
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.address = address;
    this.postalCode = postalCode;
    this.city = city;
    this.phoneNumber = phoneNumber;
    this.website = website;
    this.email = email;
    this.rooms = rooms;
  }
}
