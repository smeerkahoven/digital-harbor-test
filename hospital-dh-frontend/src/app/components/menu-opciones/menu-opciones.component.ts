import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PacienteService } from 'src/app/services/paciente.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { HospitalService } from 'src/app/services/hospital.service';

@Component({
  selector: 'app-menu-opciones',
  templateUrl: './menu-opciones.component.html',
  styleUrls: ['./menu-opciones.component.scss'],
 // providers : [{provide: PacienteService}]
})
export class MenuOpcionesComponent implements OnInit {

  @Input()
  //hospital: BehaviorSubject<any> = new BehaviorSubject(null);
  hospital: any ;



  constructor(private route : ActivatedRoute , 
              private hospitalService : HospitalService
    ) {
      
   }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (params )=> {
          //this.setHospital( params.id) ;
          this.hospital = params.id ;
          //this.hospitalService.setSharedData(params.id) ;
        }
      )
  }



 /* getHospital(): Observable<any> {
    return this.hospital;
  }

  setHospital(d: any): void {
    this.hospital.next(d);
  }
*/

}
