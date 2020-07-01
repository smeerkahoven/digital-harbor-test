import { Hospital } from './hospital.model';

export class Paciente {
    id? : number;
    nombre? : string ;
    apellido?: string;
    fechaNacimiento?: string;
    direccion?: string;
    fechaCreacion? : string;

    hospital? : Hospital ;
}