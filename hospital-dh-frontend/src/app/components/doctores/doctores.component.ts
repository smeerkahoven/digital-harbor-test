import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DoctorService } from 'src/app/services/doctor.service';
import { HospitalService } from 'src/app/services/hospital.service';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-doctores',
  templateUrl: './doctores.component.html',
  styleUrls: ['./doctores.component.scss']
})
export class DoctoresComponent implements OnInit , OnChanges {
  @Input('hospital')
  //hospital: BehaviorSubject<any> = new BehaviorSubject(null);
  hospital : any;

  
  mySubscription: any;

  public _hospital: Observable<number>;

  //public currentUser : Observable<LoginResponse> ;

  data = [];

  constructor(
    private modalService: NgbModal,
    private service: DoctorService,
    private hospitalService : HospitalService,
    private router: Router
  ) {

    /*
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
        this.getData();
      }
    });*/

    /*this.hospital.subscribe(e => {
      console.log('hola');
    })*/

  }
  ngOnChanges(changes: SimpleChanges): void {
    let newVal = changes['hospital'].currentValue ;

    this.service
      .getPacientesByHospital(newVal)
      .pipe(first())
      .subscribe((response:any)=>{
        this.data = response.items ;
      })

  }

/*
  getData() {
     this.service
      .getPacientesByHospital(this.hospitalService.getSharedData())
      .pipe(first())
      .subscribe((response:any)=>{
        this.data = response.items ;
       // return this.data ;
      })
  }
*/
  ngOnInit(): void {
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

  edit(data) {
    //const modalRef = this.modalService.open(HospitalFormComponent);
    //modalRef.componentInstance.data = data;
  }

  delete(data) {}
}

