import { Component, OnInit } from '@angular/core';
import { HospitalService } from 'src/app/services/hospital.service';
import {
  NgbDateAdapter,
  NgbDateParserFormatter,
  NgbCalendar,
} from '@ng-bootstrap/ng-bootstrap';
import {
  CustomAdapter,
  CustomDateParserFormatter,
} from 'src/app/adapters/datepicker.adapter';
import { ToastService } from 'src/app/services/toas.service';
import { Hospital } from 'src/model/hospital.model';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-hospital-buscar',
  templateUrl: './hospital-buscar.component.html',
  styleUrls: ['./hospital-buscar.component.scss'],
  providers: [
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
  ],
})
export class HospitalBuscarComponent implements OnInit {

  data : Hospital = new Hospital();
  searchData : Hospital[] ;
  btnDisable = false ;

  btnBuscarSubmited = false;

  constructor(
    private service: HospitalService,
    public toastService: ToastService,
    private ngbCalendar: NgbCalendar,
    private dateAdapter: NgbDateAdapter<string>
  ) {

  }

  buscar() {

    this.service
      .search(this.data)
      .pipe(first())
      .subscribe((response)=> {
        this.searchData = response.items ;
        this.btnDisable = false ;
        this.btnBuscarSubmited = true ;
      })

   

  }

  ngOnInit(): void {}
}
