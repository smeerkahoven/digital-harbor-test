import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginRequest } from 'src/model/request/login.request';
import { first } from 'rxjs/operators';
import { LoginResponse } from 'src/model/response/login.response';
import { CodeResponse } from 'src/model/response/code.response';
import { HashLocationStrategy } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  errors = [] ;
  submitted: boolean = false;
  data : LoginRequest = new LoginRequest() ;

  constructor( private service : LoginService, 
               private router: Router, 
               private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
  }

  login() {

    this.errors = [] ;
    this.submitted =true ;

    const request: LoginRequest = { username : this.data.username, password : this.data.password } ;
    this.service
      .login(request)
      .pipe(first())
      .subscribe(
        (response : LoginResponse)=> {
          if (response && response.codigo == CodeResponse.RESPONSE_OK) {
            this.router.navigate(['/hospital'],{relativeTo: this.route});
          }else {
            this.errors.push(response.mensaje);
          }
          this.submitted = false ;
        },
        error => {
          this.errors.push('Existio un error');
          this.submitted = false ;
        }
      )

  }

}
