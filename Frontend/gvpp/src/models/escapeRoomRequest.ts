export class EscapeRoomRequest {
  name: string;
  description: string;
  organizationId: number;
  address: string;
  postalCode: number;
  city: string;
  email: string;
  phoneNumber: string;
  website: string;
  maxCapacity: number;

  constructor(name: string, description: string, organizationId: number, address: string, postalCode: number, city: string, email: string, phoneNumber: string, website: string, maxCapacity: number) {
    this.name = name;
    this.description = description;
    this.organizationId = organizationId;
    this.address = address;
    this.postalCode = postalCode;
    this.city = city;
    this.phoneNumber = phoneNumber;
    this.website = website;
    this.email = email;
    this.maxCapacity = maxCapacity;
  }
}
