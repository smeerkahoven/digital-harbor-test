import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HospitalComponent } from './components/hospital/hospital.component';
import { DoctoresComponent } from './components/doctores/doctores.component';
import { PacientesComponent } from './components/pacientes/pacientes.component';
import { EspecialidadComponent } from './components/especialidad/especialidad.component';
import { NotasComponent } from './components/notas/notas.component';
import { HospitalFormComponent } from './components/hospital/hospital-form/hospital-form.component';
import { LoginComponent } from './components/login/login.component';
import { PrincipalComponent } from './components/principal/principal.component' ;
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthorizationInterceptor } from './interceptor/authorization.interceptor';
import { MenuOpcionesComponent } from './components/menu-opciones/menu-opciones.component';
import { HospitalService } from './services/hospital.service';
import { PacientesFormComponent } from './components/pacientes/pacientes-form/pacientes-form.component';
import { EspecialidadFormComponent } from './components/especialidad/especialidad-form/especialidad-form.component';
import { HospitalBuscarComponent } from './components/hospital/hospital-buscar/hospital-buscar.component';
import { PacientesBuscarComponent } from './components/pacientes/pacientes-buscar/pacientes-buscar.component';
import { DoctoresFormComponent } from './components/doctores/doctores-form/doctores-form.component';
import { DoctoresBuscarComponent } from './components/doctores/doctores-buscar/doctores-buscar.component';
import { DoctoresFormEditComponent } from './components/doctores/doctores-form-edit/doctores-form-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HospitalComponent,
    DoctoresComponent,
    PacientesComponent,
    EspecialidadComponent,
    NotasComponent,
    HospitalFormComponent,
    LoginComponent,
    PrincipalComponent,
    MenuOpcionesComponent,
    PacientesFormComponent,
    EspecialidadFormComponent,
    HospitalBuscarComponent,
    PacientesBuscarComponent,
    DoctoresFormComponent,
    DoctoresBuscarComponent,
    DoctoresFormEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    HttpModule,
    HttpClientModule,
    NgbPaginationModule,
    NgbModule,
    

  ],
  providers : [
    {
      provide: HTTP_INTERCEPTORS,
      useClass : AuthorizationInterceptor,
      multi: true,
    },
    {
      provide : HospitalService, 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
