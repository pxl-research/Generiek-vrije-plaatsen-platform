export class Filter {
  id: number;
  name: string;
  datatype: string;
  value: string;
  active: boolean;
  inputType: string;

  constructor(id: number, name: string, datatype: string, value: string, active: boolean, inputType: string) {
    this.id = id;
    this.name = name;
    this.datatype = datatype;
    this.value = value;
    this.active = active;
    this.inputType = inputType;
  }
}
