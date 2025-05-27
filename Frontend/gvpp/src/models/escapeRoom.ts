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

  constructor(id: number, name: string, description: string, organizationId: number, address: string, postalCode: number, city: string, email: string, phoneNumber: string, website: string, currentCapacity: number, maxCapacity: number) {
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
  }
}
