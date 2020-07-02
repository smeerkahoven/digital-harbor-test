import { Component, OnInit, Input } from '@angular/core';
import { NgbDateAdapter, NgbDateParserFormatter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { CustomAdapter, CustomDateParserFormatter } from 'src/app/adapters/datepicker.adapter';
import { DoctorService } from 'src/app/services/doctor.service';
import { ToastService } from 'src/app/services/toas.service';
import { first } from 'rxjs/operators';
import { CodeResponse } from 'src/model/response/code.response';
import { Router, ActivatedRoute } from '@angular/router';
import { Especialidad } from 'src/model/especialidad.model';
import { Doctor } from 'src/model/doctor.model';

@Component({
  selector: 'app-doctores-form-edit',
  templateUrl: './doctores-form-edit.component.html',
  styleUrls: ['./doctores-form-edit.component.scss'],
  providers: [
    {provide: NgbDateAdapter, useClass: CustomAdapter},
    {provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter}
  ]
})
export class DoctoresFormEditComponent implements OnInit {

  @Input() data : Doctor = new Doctor();
  @Input() metodo;

  especialidades : Especialidad [] ;
  selectedEspecialidad : any ;

  addEspecialidad(id) {

  }
  
  constructor(
    private service : DoctorService,
    public toastService: ToastService,
    private ngbCalendar: NgbCalendar, 
    private dateAdapter: NgbDateAdapter<string>,
    private router : Router,
    private route:ActivatedRoute

  ) { }


  actualizar(){
    this.service.update(this.data)
    .pipe(first())
    .subscribe((response:any)=>{
      
      if (response.codigo == CodeResponse.RESPONSE_OK ){
        this.toastService.showSucces("Registro Actualizado");
        //this.activeModal.close('update');
      }else {
        this.toastService.showError(response.mensaje);
      }
      
    })

  }


  eliminar(){/*
    this.service.delete(this.data)
    .pipe(first())
    .subscribe((response:any)=>{
      
      if (response.codigo == CodeResponse.RESPONSE_OK ){
        this.toastService.showSucces("Registro Eliminado");
        //this.activeModal.close('delete');
      }else {
        this.toastService.showError(response.mensaje);
      }
      
    })*/
  }

  ngOnInit(): void {
    this.route.params
    .subscribe(
      (params )=> {
        //this.setHospital( params.id) ;
        console.log(params);
        //this.hospitalService.setSharedData(params.id) ;

        if (params != undefined){
          if (params.hasOwnProperty('id')) {
            this.service.get(params)
              .pipe(first())
              .subscribe((response)=> {
                if (response.codigo == CodeResponse.RESPONSE_OK){
                  this.data = response.items[0] ;
                }else {
                  this.toastService.showError(response.mensaje);
                }
              })
          }
        }

      }
    )
  }

}
