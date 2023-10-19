import { ClientIdentification } from "./client-identification";

export class Client {
    constructor(
    public clientId: string,
    public email: string,
    public name: string,
    public dateOfBirth: string,
    public country: string,
    public postalCode : string,
    public identifications: ClientIdentification[],
    public balance: number,
    public token:number
    ){}
}
