import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal, NgbDateAdapter, NgbDateParserFormatter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { HospitalService } from 'src/app/services/hospital.service';
import { ToastService } from 'src/app/services/toas.service';
import { first } from 'rxjs/operators';
import { CodeResponse } from 'src/model/response/code.response';
import { CustomAdapter, CustomDateParserFormatter } from 'src/app/adapters/datepicker.adapter';
import { PacienteService } from 'src/app/services/paciente.service';

@Component({
  selector: 'app-pacientes-form',
  templateUrl: './pacientes-form.component.html',
  styleUrls: ['./pacientes-form.component.scss'], 
  providers: [
    {provide: NgbDateAdapter, useClass: CustomAdapter},
    {provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter}
  ]
})
export class PacientesFormComponent implements OnInit {


  @Input() data;
  @Input() metodo;

  constructor(public activeModal: NgbActiveModal , 
              private service : PacienteService,
              public toastService: ToastService,
              private ngbCalendar: NgbCalendar, private dateAdapter: NgbDateAdapter<string>
    ) { }

  ngOnInit(): void {

  }

  guardar(){

    console.log(this.data);

    this.service.create(this.data)
      .pipe(first())
      .subscribe((response:any)=>{
        
        if (response.codigo == CodeResponse.RESPONSE_OK ){
          this.toastService.show('Registro Insertado', { classname: 'bg-success text-light', delay: 10000 });
          this.activeModal.close('save');
        }else {
          this.toastService.show("Existio un Error", { classname: 'bg-danger text-light', delay: 15000 });
        }
        
      });

    
  }

  actualizar(){
    this.service.update(this.data)
    .pipe(first())
    .subscribe((response:any)=>{
      
      if (response.codigo == CodeResponse.RESPONSE_OK ){
        this.toastService.showSucces("Registro Insertado");
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
        this.toastService.showSucces("Registro Eliminado");
        this.activeModal.close('delete');
      }else {
        this.toastService.showError(response.mensaje);
      }
      
    })
  }


}
