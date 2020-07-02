import { Component, OnInit } from '@angular/core';
import { NgbDateAdapter, NgbDateParserFormatter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { CustomAdapter, CustomDateParserFormatter } from 'src/app/adapters/datepicker.adapter';
import { Doctor } from 'src/model/doctor.model';
import { DoctorService } from 'src/app/services/doctor.service';
import { ToastService } from 'src/app/services/toas.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-doctores-buscar',
  templateUrl: './doctores-buscar.component.html',
  styleUrls: ['./doctores-buscar.component.scss'],
  providers: [
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
  ],
})
export class DoctoresBuscarComponent implements OnInit {


  data : Doctor = new Doctor();

  searchData : Doctor[] ;
  btnDisable = false ;

  btnBuscarSubmited = false;

  constructor(
    private service: DoctorService,
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


  ngOnInit(): void {
  }

}
