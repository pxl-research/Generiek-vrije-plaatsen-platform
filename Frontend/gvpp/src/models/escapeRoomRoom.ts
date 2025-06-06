export class EscapeRoomRoom {
  id: number;
  name: string;
  minimumAge: number;
  duration: number;
  currentCapacity: number;
  maxCapacity: number;

  constructor(id: number, name: string, minimumAge: number, duration: number, currentCapacity: number, maxCapacity: number) {
    this.id = id;
    this.name = name;
    this.minimumAge = minimumAge;
    this.duration = duration;
    this.currentCapacity = currentCapacity;
    this.maxCapacity = maxCapacity;
  }
}
