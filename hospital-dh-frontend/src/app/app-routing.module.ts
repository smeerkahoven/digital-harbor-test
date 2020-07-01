import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component' ;
import { PrincipalComponent } from './components/principal/principal.component';
import { MenuOpcionesComponent } from './components/menu-opciones/menu-opciones.component';


const routes: Routes = [
  { path : 'login', component : LoginComponent}, 
  { path : 'hospital', component : PrincipalComponent, children : [
    {path : ':id', component : MenuOpcionesComponent, }
  ]},
  { path : '**', component : LoginComponent},  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
