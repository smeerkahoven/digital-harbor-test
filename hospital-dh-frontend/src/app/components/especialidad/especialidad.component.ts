import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { EspecialidadService } from 'src/app/services/especialidad.service';
import { Especialidad } from 'src/model/especialidad.model';
import { ToastService } from 'src/app/services/toas.service';
import { Config } from 'src/app/config';
import { EspecialidadFormComponent } from './especialidad-form/especialidad-form.component';
import { CodeResponse } from 'src/model/response/code.response';

@Component({
  selector: 'app-especialidad',
  templateUrl: './especialidad.component.html',
  styleUrls: ['./especialidad.component.scss']
})
export class EspecialidadComponent implements OnInit , OnChanges {
  @Input('hospital')
  hospital : any;

  data: Especialidad[] = [];

  page = 1;
  pageSize = 4;
  collectionSize = this.data.length;

  ngOnChanges(changes: SimpleChanges): void {
    let newVal = changes['hospital'].currentValue ;

    this.service
      .getPacientesByHospital(newVal)
      .pipe(first())
      .subscribe((response:any)=>{
        this.data = response.items ;
        this.hospital = newVal ;

        if(this.data != undefined) {
          this.collectionSize = this.data.length ;
        }
        
      })

  }

  get tableData(): Especialidad[] {
    if (this.data == undefined)
      this.data = [] ;

    return this.data
      .map((paciente, i) => ({id: i + 1, ...paciente}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  constructor(private modalService: NgbModal, 
              private service : EspecialidadService,
              public toastService: ToastService
    ) { }

  ngOnInit(): void {
  }

  getAll(){
    this.service
    .getPacientesByHospital(this.hospital)
    .pipe(first())
    .subscribe((response:any)=>{
      this.data = response.items ;
      this.collectionSize = this.data.length ;
    })
  }


  edit(data) {
    console.log(data);
    const modalRef = this.modalService.open(EspecialidadFormComponent);
    modalRef.componentInstance.data = data;
    modalRef.componentInstance.metodo = Config.EDITAR;

    modalRef.result.then((result)=> {
      console.log(result);

      if (result != 'close') {
        this.getAll() ;
      }

    })
  }

  nuevo(){
    const modalRef = this.modalService.open(EspecialidadFormComponent);

    let paciente = new Especialidad() ;
    paciente.hospital ={id : this.hospital};

    modalRef.componentInstance.data = paciente;
    modalRef.componentInstance.metodo = Config.NUEVO;

    modalRef.result.then((result)=> {
      console.log(result);

      if (result != 'close') {
        this.getAll() ;
      }

    })
  }


  eliminar(data){
    this.service.delete(data)
    .pipe(first())
    .subscribe((response:any)=>{
      
      if (response.codigo == CodeResponse.RESPONSE_OK ){
        this.toastService.showSucces("Registro Eliminado");
        this.getAll() ;
      }else {
        this.toastService.showError(response.mensaje);
      }
      
    })
    
  }
}


