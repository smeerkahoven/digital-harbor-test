import { Config } from '../config';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EspecialidadService {
  private path = `${Config.baseURL}/especialidad`;

  create(request: any) {
    return this.http.post<any>(`${this.path}`, request).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  read(request: any) {
    return this.http.get<any>(`${this.path}`, request).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  update(request: any) {
    return this.http.post<any>(`${this.path}/update`, request).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  delete(request: any) {
    return this.http.post<any>(`${this.path}/delete`,request).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  get(request: any) {
    return this.http.get<any>(`${this.path}/${request.user.id}`).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  getPacientesByHospital(id: any) {
    return this.http.get<any>(`${this.path}/hospital/${id}`).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  search(request: any) {
    return this.http.post<any>(`${this.path}`, request).pipe(
      map((response: any) => {
        return response;
      })
    );
  }

  getData(): any {
    return this.http.get<any>(`${this.path}`).pipe((response: any) => {
      return response;
    });
  }

  constructor(private http: HttpClient) {}

}
