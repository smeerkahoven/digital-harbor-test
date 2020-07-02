import { Config } from '../config';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
  })
export class HospitalService {
    private path = `${Config.baseURL}/hospital` ;

    create(request: any) {
        return this.http
          .post<any>(`${this.path}`, request)
          .pipe(
            map((response: any) => {
              return response ;
            }),
          );
      }
    
      read(request: any) {
        return this.http
          .get<any>(`${this.path}`, request)
          .pipe(
            map((response: any) => {
              return response ;
            }),
          );
      }
    
      update(request: any) {
        return this.http
          .post<any>(`${this.path}/update`, request)
          .pipe(
            map((response: any) => {
              return response ;
            }),
          );
      }
    
      delete(request: any) {
        return this.http
        .post<any>(`${this.path}/delete`, request)
        .pipe(
          map((response: any) => {
            return response ;
          }),
        );
      }
    
      get(request: any) {
        return this.http
          .get<any>(`${this.path}/${request.id}`)
          .pipe(
            map((response: any) => {
              return response ;
            }),
          );
      }

      search(request: any) {
        return this.http
          .post<any>(`${this.path}/search`, request)
          .pipe(
            map((response: any) => {
              return response ;
            }),
          );
      }
    
      getData(): any {
        return this.http
        .get<any>(`${this.path}`)
        .pipe((response: any) =>{
          return response ;
        }) ;
      }
    
      constructor(private http: HttpClient) {
      }


      private sharedHospital: BehaviorSubject<any> = new BehaviorSubject<any>(null);
      sharedHospital$: Observable<any> = this.sharedHospital.asObservable();
    
      setSharedData(sharedData) {
        this.sharedHospital.next(sharedData);
        console.log(sharedData);
      }
    
      getSharedData() : any{
        if (this.sharedHospital != undefined || this.sharedHospital != null){
          return this.sharedHospital.getValue();
        }
        
      }
    
}