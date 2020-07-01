import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { HospitalFormComponent } from './hospital-form/hospital-form.component';
import { HospitalService } from 'src/app/services/hospital.service';
import { first } from 'rxjs/operators';
import { Config } from 'src/app/config';
import { Hospital } from 'src/model/hospital.model';
import { CodeResponse } from 'src/model/response/code.response';
import { ToastService } from 'src/app/services/toas.service';



@Component({
  selector: 'app-hospital',
  templateUrl: './hospital.component.html',
  styleUrls: ['./hospital.component.scss']
})
export class HospitalComponent implements OnInit {

  data: Hospital[] = [];

  page = 1;
  pageSize = 4;
  collectionSize = this.data.length;


  get tableData(): Hospital[] {
    return this.data
      .map((hospital, i) => ({id: i + 1, ...hospital}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  constructor(private modalService: NgbModal, 
              private service : HospitalService,
              public toastService: ToastService
    ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll () {

    this.service
      .getData()
      .pipe(first())
      .subscribe((response:any)=>{
        this.data = response.items ;
        this.collectionSize = this.data.length ;
      })

  }

  edit(data) {
    const modalRef = this.modalService.open(HospitalFormComponent);
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
    const modalRef = this.modalService.open(HospitalFormComponent);
    modalRef.componentInstance.data = new Hospital();
    modalRef.componentInstance.metodo = Config.NUEVO;

    modalRef.result.then((result)=> {
      console.log(result);

      if (result != 'close') {
        this.getAll() ;
      }

    })
  }


  delete(data){
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
