import { Pessoa } from './../pessoa';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { map, switchMap } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal.service';
import { ListPessoasService } from '../list-pessoas.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  formulario: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private service: ListPessoasService,
    private router: Router,
    private modal: AlertModalService) { }

  ngOnInit(): void {

    this.formulario = this.formBulder();

    this.cadastroEditar();

  }

  cadastroEditar() {
    this.activatedRoute.params.subscribe(test => {
      console.log(test.id);
      if (test.id != null) {
        console.log('entrou');
          let pessoa: Observable<Pessoa> = this.service.getId(test.id);
          pessoa.subscribe(pessoa => this.updateForm(pessoa));
      }
      console.log('nao entrou');
    });
    // this.route.params.pipe(
    //   map((params: any) => params['id']),
    //   switchMap(id => this.service.getId(id))
    // ).subscribe(pessoa => this.updateForm(pessoa));
  }

  formBulder() {
    return this.formBuilder.group({
      id: [null],
      nome: [null, [Validators.required, Validators.maxLength(30)]],
      dataNascimento: [null, Validators.required],
      cpf: [null, [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
      sexo: [null, Validators.required],
      biografia: [null]
    });
  }

  updateForm(pessoa){
    this.formulario.patchValue({
      id: pessoa.id,
      nome: pessoa.nome,
      dataNascimento: pessoa.dataNascimento,
      cpf: pessoa.cpf,
      sexo: pessoa.sexo,
      biografia: pessoa.biografia
    });
  }

  onSubmit(){

    if (this.formulario.valid) {
      console.log(this.formulario.value);
      let msgSuccess = 'Pessoa cadastrada com sucesso!';
      let msgError = 'Erro ao cadastrar Pessoa, tente novamente!';
      if(this.formulario.value.id){
        msgSuccess = 'Pessoa atualizado com sucesso!';
        msgError = 'Erro ao atualizar pessoa, tente novamente!';
      }

      this.salvar(msgSuccess, msgError);

    }
  }

  salvar(msgSuccess: string, msgError: string) {
    this.service.save(this.formulario.value).subscribe(
      success => {
        console.log(success);
        this.modal.showAlertSuccess(msgSuccess);
        this.router.navigate([''], { relativeTo: this.activatedRoute });
      },
      error => {
        this.modal.showAlertDanger(msgError);
        console.log(error);
      }

    );
  }

  verificaValidTouched(campo){
    return !this.formulario.get(campo).valid && this.formulario.get(campo).touched;
  }

  aplicaCssErro(campo){
    if (this.verificaValidTouched(campo)) {
      return {'form-control is-invalid': this.verificaValidTouched(campo)};
    }
    return {'form-control': !this.verificaValidTouched(campo)};
  }

}
