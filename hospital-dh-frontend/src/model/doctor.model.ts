import { Hospital } from './hospital.model';
import { Especialidad } from './especialidad.model';

export class Doctor {
    id? : number;
    nombre? : string ;
    apellido?: string;
    fechaNacimiento?: string;
    direccion?: string;
    fechaCreacion? : string;

    hospital? : Hospital ;
    especialidades : Especialidad[] ;

}