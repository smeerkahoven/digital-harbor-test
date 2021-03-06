import {
  Component,
  OnInit,
  Input,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PacienteService } from 'src/app/services/paciente.service';
import { first } from 'rxjs/operators';
import { ToastService } from 'src/app/services/toas.service';
import { PacientesFormComponent } from './pacientes-form/pacientes-form.component';
import { Config } from 'src/app/config';
import { CodeResponse } from 'src/model/response/code.response';
import { Paciente } from 'src/model/paciente.model';

@Component({
  selector: 'app-pacientes',
  templateUrl: './pacientes.component.html',
  styleUrls: ['./pacientes.component.scss'],
})
export class PacientesComponent implements OnInit, OnChanges {
  @Input('hospital')
  hospital: any;

  @Input()
  data: Paciente[] = [];

  page = 1;
  pageSize = 4;
  collectionSize = this.data.length;

  @Input()
  btnDisabled: boolean;

  @Input()
  loadMain: boolean;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['hospital'] != undefined) {
      let newVal = changes['hospital'].currentValue;

      this.service
        .getPacientesByHospital(newVal)
        .pipe(first())
        .subscribe((response: any) => {
          this.data = response.items;
          this.hospital = newVal;

          if (this.data != undefined) {
            this.collectionSize = this.data.length;
          }
        });
    }
  }

  get tableData(): Paciente[] {
    if (this.data == undefined) {
      this.data = [];
    }

    return this.data
      .map((paciente, i) => ({ id: i + 1, ...paciente }))
      .slice(
        (this.page - 1) * this.pageSize,
        (this.page - 1) * this.pageSize + this.pageSize
      );
  }

  constructor(
    private modalService: NgbModal,
    private service: PacienteService,
    public toastService: ToastService
  ) {}

  ngOnInit(): void {}

  getAll() {
    if (this.loadMain) {
      this.service
        .getPacientesByHospital(this.hospital)
        .pipe(first())
        .subscribe((response: any) => {
          this.data = response.items;
          this.collectionSize = this.data.length;
        });
    }
  }

  edit(data) {
    console.log(data);
    const modalRef = this.modalService.open(PacientesFormComponent);
    modalRef.componentInstance.data = data;
    modalRef.componentInstance.metodo = Config.EDITAR;

    modalRef.result.then((result) => {
      console.log(result);

      if (result != 'close') {
        this.getAll();
      }
    });
  }

  nuevo() {
    const modalRef = this.modalService.open(PacientesFormComponent);

    let paciente = new Paciente();
    paciente.hospital = { id: this.hospital };

    modalRef.componentInstance.data = paciente;
    modalRef.componentInstance.metodo = Config.NUEVO;

    modalRef.result.then((result) => {
      console.log(result);

      if (result != 'close') {
        this.getAll();
      }
    });
  }

  delete(data) {
    this.service
      .delete(data)
      .pipe(first())
      .subscribe((response: any) => {
        if (response.codigo == CodeResponse.RESPONSE_OK) {
          this.toastService.showSucces('Registro Eliminado');
          this.getAll();
        } else {
          this.toastService.showError(response.mensaje);
        }
      });
  }
}
