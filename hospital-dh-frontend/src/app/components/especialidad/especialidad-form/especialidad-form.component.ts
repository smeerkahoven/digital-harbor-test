import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EspecialidadService } from 'src/app/services/especialidad.service';
import { ToastService } from 'src/app/services/toas.service';
import { first } from 'rxjs/operators';
import { CodeResponse } from 'src/model/response/code.response';

@Component({
  selector: 'app-especialidad-form',
  templateUrl: './especialidad-form.component.html',
  styleUrls: ['./especialidad-form.component.scss']
})
export class EspecialidadFormComponent implements OnInit {

  @Input() data;
  @Input() metodo;

  constructor(public activeModal: NgbActiveModal , 
              private service : EspecialidadService,
              public toastService: ToastService,
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

