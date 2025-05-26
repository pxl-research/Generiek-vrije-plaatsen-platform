export class FilterRequest {
  name: string;
  datatype: string;
  value: string;
  active: boolean;
  inputType: string;

  constructor(name: string, datatype: string, value: string, active: boolean, inputType: string) {
    this.name = name;
    this.datatype = datatype;
    this.value = value;
    this.active = active;
    this.inputType = inputType;
  }
}
