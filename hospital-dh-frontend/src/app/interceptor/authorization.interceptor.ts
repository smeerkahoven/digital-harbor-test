import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { storage } from '../config';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthorizationInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (localStorage.getItem(storage.USUARIO_AUTHORIZATION) != null) {
      req = req.clone({
        setHeaders: {
          Authorization: localStorage.getItem(storage.USUARIO_AUTHORIZATION),
          publicId: localStorage.getItem(storage.USUARIO_PUBLIC_ID),
        },
      });
    }

    return next.handle(req).pipe(
      map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          if (event.url.includes('/login')) {
            localStorage.setItem(
              storage.USUARIO_AUTHORIZATION,
              event.headers.get('Authorization')
            );
          }
        } else if (event instanceof HttpRequest) {
          event.headers.set(
            'Authorization',
            localStorage.getItem(storage.USUARIO_AUTHORIZATION)
          );

          event.headers.set(
            'publicId',
            localStorage.getItem(storage.USUARIO_PUBLIC_ID)
          );
        }
        return event;
      }),
      catchError((e) => {
        return throwError(e);
      })
    );
  }
}
