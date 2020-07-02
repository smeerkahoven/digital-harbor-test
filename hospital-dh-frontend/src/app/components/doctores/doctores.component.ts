import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DoctorService } from 'src/app/services/doctor.service';
import { HospitalService } from 'src/app/services/hospital.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { Doctor } from 'src/model/doctor.model';
import { ToastService } from 'src/app/services/toas.service';
import { Config } from 'src/app/config';
import { CodeResponse } from 'src/model/response/code.response';
import { DoctoresFormComponent } from './doctores-form/doctores-form.component';

@Component({
  selector: 'app-doctores',
  templateUrl: './doctores.component.html',
  styleUrls: ['./doctores.component.scss']
})
export class DoctoresComponent implements OnInit , OnChanges {
  @Input('hospital')
  hospital : any;

  data: Doctor[] = [];

  page = 1;
  pageSize = 4;
  collectionSize = this.data.length;

  @Input()
  btnDisabled : boolean ;

  @Input()
  loadMain : boolean ;

  ngOnChanges(changes: SimpleChanges): void {
    let newVal = changes['hospital'].currentValue ;

    this.service
      .getPacientesByHospital(newVal)
      .pipe(first())
      .subscribe((response:any)=>{
        this.data = response.items ;
        this.hospital = newVal ;

        if (this.data!= undefined){
          this.collectionSize = this.data.length ;
        }
      })

  }

  get tableData(): Doctor[] {
    if (this.data == undefined){
      this.data = [] ;
    }

    return this.data
      .map((paciente, i) => ({id: i + 1, ...paciente}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  constructor(private modalService: NgbModal, 
              private service : DoctorService,
              public toastService: ToastService,
              private router : Router, 
              private route : ActivatedRoute,
              
    ) { }

  ngOnInit(): void {
  }

  getAll(){
    if (this.loadMain) {
      this.service
      .getPacientesByHospital(this.hospital)
      .pipe(first())
      .subscribe((response:any)=>{
        this.data = response.items ;
        this.collectionSize = this.data.length ;
      });
    }
  
  }


  edit(data) {
    this.router.navigate([`/doctor/edit/${data.id}`],{relativeTo: this.route});
  }

  nuevo(){
    this.router.navigate([`/doctor/new/${this.hospital}`],{relativeTo: this.route});  }


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

