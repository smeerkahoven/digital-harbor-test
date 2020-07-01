import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginResponse } from 'src/model/response/login.response';
import { storage, Config } from '../config';
import { LoginRequest } from 'src/model/request/login.request';
import { CodeResponse } from 'src/model/response/code.response';
import { map, catchError } from 'rxjs/operators' ;

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private currentUserSubject : BehaviorSubject<LoginResponse> ;
  public currentUser : Observable<LoginResponse> ;

  constructor(private http: HttpClient) { 
    this.currentUserSubject = new BehaviorSubject<LoginResponse>(
      JSON.parse(localStorage.getItem(storage.USUARIO_DATA))
    ) ;

    this.currentUser = this.currentUserSubject.asObservable();
  }

  public login(request: LoginRequest){
    return this.http
      .post<any>(`${Config.baseURL}/login`, request, Config.httpOptions)
      .pipe(
        map((response:LoginResponse)=>{
          if (response && response.codigo == CodeResponse.RESPONSE_OK){
            localStorage.setItem(storage.USUARIO_DATA, JSON.stringify(response));
            localStorage.setItem(storage.USUARIO_AUTHORIZATION, `Bearer ${response.token}`);
            localStorage.setItem(storage.USUARIO_PUBLIC_ID, response.publicId);
          }
          return response ;
        }), 
        catchError (err => {
          return Observable.throw(err) ;
        })
      )
  }
  
  public logout(){
    localStorage.removeItem(storage.USUARIO_DATA);
    this.currentUserSubject.next(null);
  }

  public get currentUserValue(): LoginResponse {
    return this.currentUserSubject.value;
  }

}
