import { waitForAsync, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { ListPessoasService } from "./list-pessoas.service";
import { Pessoa } from "./pessoa";


let listPessoasService: ListPessoasService;
let pessoa: Pessoa;
let http: any;

beforeEach(() => {
  http = jasmine.createSpyObj('httpClient', ['get', 'post', 'delete', 'put']);
  http.get.and.callFake(() => of([]));
  http.post.and.callFake(() => of([]));
  http.delete.and.callFake(() => of([]));
  http.put.and.callFake(() => of([]));
  listPessoasService = new ListPessoasService(http);
});

it('should call findOne', waitForAsync(() => {

  const requestExpected: Pessoa = {
    id: 123,
    nome: 'paulo',
    cpf: '10026219840',
    dataNascimento: '25/01/2000',
    sexo: 'MASCULINO',
    biografia: ''
  };

  listPessoasService.getId(123);

  expect(http.get).toHaveBeenCalledWith('/api/pessoas/123');

}));

it('deve cadastrar nova pessoa', () => {

  const requestExpected: Pessoa = {
    id: null,
    nome: 'paulo',
    cpf: '10026219840',
    dataNascimento: '25/01/2000',
    sexo: 'MASCULINO',
    biografia: ''
  };

  listPessoasService.save(requestExpected);

  expect(http.post).toHaveBeenCalledWith('/api/pessoas', requestExpected);

});

it('deve cadastrar remover uma pessoa',() => {

  listPessoasService.remover(1);

  expect(http.delete).toHaveBeenCalledWith('/api/pessoas/1');
});

it('deve atualizar uma pessoa', () => {

  const requestExpected: Pessoa = {
    id: 1,
    nome: 'paulo',
    cpf: '10026219840',
    dataNascimento: '25/01/2000',
    sexo: 'MASCULINO',
    biografia: ''
  };

  listPessoasService.save(requestExpected);

  expect(http.put).toHaveBeenCalledWith('/api/pessoas/1', requestExpected);

});

