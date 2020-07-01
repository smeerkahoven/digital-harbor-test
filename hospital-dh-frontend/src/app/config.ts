import { HttpHeaders } from '@angular/common/http';

export class Config {
    public static baseURL : string = "http://localhost:8081/hospital-dh-backend";
    public static httpOptions  = { headers : new HttpHeaders({'Content-Type':'application/json'})} ;
    public static itemsByPage: number  = 10 ;

    public static EDITAR = 1;
    public static NUEVO = 0;
}

export const storage = {
    USUARIO_DATA: 'usuario.data',
    USUARIO_AUTHORIZATION : 'usuario.authorization',
    USUARIO_PUBLIC_ID : 'usuario.public.id',
  };

  export const Mensajes = {
    DESEA_ELIMINAR_REGISTRO : '¿ Desea Eliminar el registro ?',
    DESEA_CERRAR_SESION : '¿ Desea salir de la sesion ?',

  }
  
  export const Titulos = {
    CONFIRMACION : 'Confirmación',
    ERROR : 'Error',
    INFORMACION : 'Información',
  }