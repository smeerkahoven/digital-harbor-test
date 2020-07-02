import { Component, OnInit, Input } from '@angular/core';
import { PacienteService } from 'src/app/services/paciente.service';
import { ToastService } from 'src/app/services/toas.service';
import { NgbCalendar, NgbDateAdapter, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { Paciente } from 'src/model/paciente.model';
import { first } from 'rxjs/operators';
import { CustomAdapter, CustomDateParserFormatter } from 'src/app/adapters/datepicker.adapter';

@Component({
  selector: 'app-pacientes-buscar',
  templateUrl: './pacientes-buscar.component.html',
  styleUrls: ['./pacientes-buscar.component.scss'],
  providers: [
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
  ],
})
export class PacientesBuscarComponent implements OnInit {

 
  data : Paciente = new Paciente();

  searchData : Paciente[] ;
  btnDisable = false ;

  btnBuscarSubmited = false;

  constructor(
    private service: PacienteService,
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
