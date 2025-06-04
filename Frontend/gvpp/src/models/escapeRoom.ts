export class Room {
  name: string;
  age: string;
  price: string;
  duration: string;
  players: string;
  timeslots: { time: string; status: string }[];

  constructor(
    name: string,
    age: string,
    price: string,
    duration: string,
    players: string,
    timeslots: { time: string; status: string }[]
  ) {
    this.name = name;
    this.age = age;
    this.price = price;
    this.duration = duration;
    this.players = players;
    this.timeslots = timeslots;
  }
}

export class EscapeRoom {
  id: number;
  name: string;
  description: string;
  organizationId: number;
  address: string;
  postalCode: number;
  city: string;
  email: string;
  phoneNumber: string;
  website: string;
  currentCapacity: number;
  maxCapacity: number;

  // 👇 Add this line:
  ['different-rooms']?: Room[];

  constructor(
    id: number,
    name: string,
    description: string,
    organizationId: number,
    address: string,
    postalCode: number,
    city: string,
    email: string,
    phoneNumber: string,
    website: string,
    currentCapacity: number,
    maxCapacity: number,
    differentRooms?: Room[] // optional param to populate 'different-rooms'
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.organizationId = organizationId;
    this.address = address;
    this.postalCode = postalCode;
    this.city = city;
    this.phoneNumber = phoneNumber;
    this.website = website;
    this.email = email;
    this.currentCapacity = currentCapacity;
    this.maxCapacity = maxCapacity;
    if (differentRooms) {
      this['different-rooms'] = differentRooms;
    }
  }
}

