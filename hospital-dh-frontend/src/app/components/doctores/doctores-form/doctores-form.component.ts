import { Component, OnInit, Input } from '@angular/core';
import { NgbDateAdapter, NgbDateParserFormatter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { CustomAdapter, CustomDateParserFormatter } from 'src/app/adapters/datepicker.adapter';
import { DoctorService } from 'src/app/services/doctor.service';
import { ToastService } from 'src/app/services/toas.service';
import { first } from 'rxjs/operators';
import { CodeResponse } from 'src/model/response/code.response';
import { Router, ActivatedRoute } from '@angular/router';
import { Doctor } from 'src/model/doctor.model';
import { HospitalService } from 'src/app/services/hospital.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-doctores-form',
  templateUrl: './doctores-form.component.html',
  styleUrls: ['./doctores-form.component.scss'],
  providers: [
    {provide: NgbDateAdapter, useClass: CustomAdapter},
    {provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter}
  ]
})
export class DoctoresFormComponent implements OnInit {

  @Input() data = new Doctor();
  @Input() metodo;
  
  constructor(
    private service : DoctorService,
    public toastService: ToastService,
    private ngbCalendar: NgbCalendar, 
    private dateAdapter: NgbDateAdapter<string>,
    private router : Router,
    private route:ActivatedRoute,
    private hospitaService:  HospitalService,
    private _location: Location

  ) { }

  guardar(){

    this.service.create(this.data)
      .pipe(first())
      .subscribe((response:any)=>{
        
        if (response.codigo == CodeResponse.RESPONSE_OK ){
          this.toastService.showSucces('Registro Insertado');
          this._location.back();
          //this.activeModal.close('save');
        }else {
          this.toastService.showError("Existio un Error");
        }
        
      });
    
  }


  ngOnInit(): void {
    this.route.params
    .subscribe(
      (params )=> {
        console.log(params);
        this.data.hospital = { id : params.id}
      }
    )
  }

}
