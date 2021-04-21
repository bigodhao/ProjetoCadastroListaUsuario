import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, observable } from 'rxjs';
import { AlertModalService } from 'src/app/shared/alert-modal.service';
import { ListPessoasService } from '../list-pessoas.service';
import { Pessoa } from '../pessoa';

import { CadastroComponent } from './cadastro.component';

describe('CadastroComponent', () => {
  let component: CadastroComponent;
  let fixture: ComponentFixture<CadastroComponent>;
  let http: any;

  const service = jasmine.createSpyObj('service', ['save', 'getId']);
  service.save.and.callFake(() => of());
  service.getId.and.callFake(() => of({id:1}));
  const modal = jasmine.createSpyObj('modal', ['showAlertSuccess', 'showAlertDanger']);
  const router = jasmine.createSpyObj('router', ['navigate']);

 // const routerActivated = jasmine.createSpyObj('routerActivated', ['params']);
  let activatedRoute;
  //routerActivated.params.and.callFake(() => of({id:1}));

  router.navigate.and.callFake(() => of());

  beforeEach( waitForAsync(() => {

    http = jasmine.createSpyObj('httpClient', ['post']);
    http.post.and.callFake(() => of([]));

    activatedRoute = {
      params: of({id:1})
    };

    TestBed.configureTestingModule({
      declarations: [ CadastroComponent ],
      providers: [
        { provide: ActivatedRoute, useValue: activatedRoute },
        { provide: Router, useValue: router },
        FormBuilder,
        { provide: AlertModalService, useValue: modal },
        { provide: ListPessoasService, useValue: service }
      ],
      imports: [RouterTestingModule.withRoutes([])]
    });

    fixture = TestBed.createComponent(CadastroComponent);
    component = fixture.componentInstance;



    fixture.detectChanges();
  }));

  it('deve verificar o salvar', waitForAsync(() => {

    component.formulario = component.formBulder();
    let msgSuccess = 'Pessoa cadastrada com sucesso!';
    let msgError = 'Erro ao cadastrar Pessoa, tente novamente!';

    const requestExpected: Pessoa = {
      id: null,
      nome: 'paulo',
      cpf: '10026219840',
      dataNascimento: '25/01/2000',
      sexo: 'MASCULINO',
      biografia: ''
    };

    //http.post.and.callFake(() => of([of(requestExpected)]));

    //let spy = spyOn(service, 'save').and.returnValue(of(requestExpected));

    component.formulario.patchValue(requestExpected);

    component.salvar(msgSuccess, msgError);
    fixture.whenStable().then(() => {
      expect(service.save).toHaveBeenCalledWith(requestExpected);
      let retorno = service.save(requestExpected);
      //expect(retorno.subscribe).toEqual('success');

    });




  }));

  it('deve verificar updateForm', waitForAsync(() => {

    component.formulario = component.formBulder();

    const requestExpected: Pessoa = {
      id: null,
      nome: 'paulo',
      cpf: '10026219840',
      dataNascimento: '25/01/2000',
      sexo: 'MASCULINO',
      biografia: ''
    };

    component.updateForm(requestExpected);

    expect(component.formulario.value).toEqual(requestExpected);

  }));

  it('deve verificar formBulder', waitForAsync(() => {

    component.formulario = component.formBulder();

    expect(component.formulario.get('nome').errors).toEqual({required: true});

    component.formulario.patchValue({nome: '12345678912345678912345678912345678912345'});
    expect(component.formulario.get('nome').errors).toEqual({maxlength: { requiredLength: 30, actualLength: 41}});

    expect(component.formulario.get('dataNascimento').errors).toEqual({required: true});

    expect(component.formulario.get('cpf').errors).toEqual({required: true});

    component.formulario.patchValue({cpf: '045294479100'});
    expect(component.formulario.get('cpf').errors).toEqual({maxlength: {requiredLength: 11, actualLength: 12}});

    component.formulario.patchValue({cpf: '045294479'});
    expect(component.formulario.get('cpf').errors).toEqual({minlength: {requiredLength: 11, actualLength: 9}});

    expect(component.formulario.get('sexo').errors).toEqual({required: true});

  }));

  it('deve verificar o cadastroEditar', waitForAsync(() => {

    component.cadastroEditar();
    fixture.whenStable().then(() => {
      expect(component.formulario.value.id).toEqual(1);
      service.getId.calls.reset();
      activatedRoute.params = of({id: null});
      component.cadastroEditar();
      expect(service.getId).not.toHaveBeenCalled();
    });
  }));


  it('deve verificar o verificaValidTouched', waitForAsync(() => {

    component.formulario = component.formBulder();
    let retorno = component.verificaValidTouched('nome');
    expect(retorno).toBeFalsy();
    component.formulario.get('nome').markAsTouched();
    retorno = component.verificaValidTouched('nome');
    expect(retorno).toBeTruthy();
    component.formulario.patchValue({nome: 'joao'});
    retorno = component.verificaValidTouched('nome');
    expect(retorno).toBeFalsy();

  }));

  it('deve verificar o aplicaCssErro', waitForAsync(() => {

    component.formulario = component.formBulder();

    component.formulario.get('nome').markAsTouched();

    let retorno = component.aplicaCssErro('nome');
    expect(retorno).toEqual({'form-control is-invalid': true});

    component.formulario.patchValue({nome: null});
    retorno = component.aplicaCssErro('nome');
    expect(retorno).toEqual({'form-control is-invalid': true});

    component.formulario.patchValue({nome: 'joao'});
    retorno = component.aplicaCssErro('nome');
    expect(retorno).toEqual({'form-control': true});

  }));


});
