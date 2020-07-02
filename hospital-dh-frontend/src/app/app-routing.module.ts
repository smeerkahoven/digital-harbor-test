import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component' ;
import { PrincipalComponent } from './components/principal/principal.component';
import { MenuOpcionesComponent } from './components/menu-opciones/menu-opciones.component';
import { HospitalBuscarComponent } from './components/hospital/hospital-buscar/hospital-buscar.component';
import { PacientesBuscarComponent } from './components/pacientes/pacientes-buscar/pacientes-buscar.component';
import { DoctoresBuscarComponent } from './components/doctores/doctores-buscar/doctores-buscar.component';
import { DoctoresFormComponent } from './components/doctores/doctores-form/doctores-form.component';
import { DoctoresFormEditComponent } from './components/doctores/doctores-form-edit/doctores-form-edit.component';


const routes: Routes = [
  { path : 'login', component : LoginComponent}, 
  { path : 'hospital', component : PrincipalComponent, children : [
    {path : ':id', component : MenuOpcionesComponent, },
  ]},
  {path : 'buscar', children : [
    {path : 'hospital',  component : HospitalBuscarComponent},
    {path : 'paciente',  component : PacientesBuscarComponent},
    {path : 'doctor',  component : DoctoresBuscarComponent},
  ] },

  { path : 'doctor',  children : [
    {path : 'edit/:id',  component : DoctoresFormEditComponent},
    {path : 'new/:id',  component : DoctoresFormComponent},
  ]},

  { path : '**', component : LoginComponent},  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
