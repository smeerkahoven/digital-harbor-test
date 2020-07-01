import { Component, OnInit, Input, ElementRef } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HospitalService } from 'src/app/services/hospital.service';
import { first } from 'rxjs/operators';
import { ToastService } from 'src/app/services/toas.service';
import { CodeResponse } from 'src/model/response/code.response';

@Component({
  selector: 'app-hospital-form',
  templateUrl: './hospital-form.component.html',
  styleUrls: ['./hospital-form.component.scss']
})
export class HospitalFormComponent implements OnInit {

  @Input() data;
  @Input() metodo;

  constructor(public activeModal: NgbActiveModal , 
              private service : HospitalService,
              public toastService: ToastService
    ) { }

  ngOnInit(): void {

  }

  guardar(){

    console.log(this.data);

    this.service.create(this.data)
      .pipe(first())
      .subscribe((response:any)=>{
        
        if (response.codigo == CodeResponse.RESPONSE_OK ){
          this.toastService.showSucces("Registro Insertado");
          this.activeModal.close('save');
        }else {
          this.toastService.showError(response.mensaje);
        }
        
      });

    
  }

  actualizar(){
    this.service.update(this.data)
    .pipe(first())
    .subscribe((response:any)=>{
      
      if (response.codigo == CodeResponse.RESPONSE_OK ){
        this.toastService.showSucces("Registro Actualizado");
        this.activeModal.close('update');
      }else {
        this.toastService.showError(response.mensaje);
      }
      
    })

  }


  eliminar(){
    this.service.delete(this.data)
    .pipe(first())
    .subscribe((response:any)=>{
      
      if (response.codigo == CodeResponse.RESPONSE_OK ){
        this.toastService.showSucces("Registro Elimnado");
        this.activeModal.close('delete');
      }else {
        this.toastService.showError(response.mensaje);
      }
      
    })
  }

}
