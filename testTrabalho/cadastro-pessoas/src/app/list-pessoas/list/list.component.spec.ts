import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { FormBuilder } from '@angular/forms';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertModalService } from './../../../../../../cursoAngular/requests-http/src/app/shared/alert-modal.service';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { ListPessoasService } from '../list-pessoas.service';

import { ListComponent } from './list.component';
import { of } from 'rxjs';
import { ConfirmModalComponent } from 'src/app/shared/confirm-modal/confirm-modal.component';

describe('ListaPessoas', () => {
  let component: ListComponent;
  let fixture: ComponentFixture<ListComponent>;

  const listPessoasService = jasmine.createSpyObj('listPessoasService', ['list']);
  listPessoasService.list.and.callFake(() => of());

  const router = jasmine.createSpyObj('router', ['navigate']);
  const routerActivated = jasmine.createSpyObj('routerActivated', ['']);
  router.navigate.and.callFake(() => of());

  const alertModalService = jasmine.createSpyObj('alertModalService', ['showConfirm']);
  alertModalService.showConfirm.and.callFake(() => of());

  let bsModalService = jasmine.createSpyObj('bsModalService', ['show']);
  bsModalService.show.and.callFake(() => of());

  beforeEach(() => {

    TestBed.configureTestingModule({
      declarations: [ ListComponent ],
      providers: [
        { provide: ListPessoasService, useValue: listPessoasService },
        { provide: AlertModalService, useValue: alertModalService },
        { provide: BsModalService, useValue: bsModalService },
        { provide: BsModalRef, useValue: {} },
        HttpClient,
        HttpHandler,
        FormBuilder,
        { provide: ActivatedRoute, useValue: routerActivated },
        { provide: Router, useValue: router }
      ]
    });

    fixture = TestBed.createComponent(ListComponent);
    component = fixture.componentInstance;
  });

  it('deve verificar o editar', () => {

    component.editar(1);
    expect(router.navigate).toHaveBeenCalledWith([ 'editar', 1 ], { relativeTo: routerActivated });
  });

  it('deve verificar o onRefresh', waitForAsync(() => {

    component.onRefresh();
    expect(listPessoasService.list).toHaveBeenCalledWith();

  }));

  xit('deve remover', () => {


    component.remover({'id': 1});
    fixture.whenStable().then(() => {
      let fixturee = TestBed.createComponent(ConfirmModalComponent);
      let componentConf = fixturee.componentInstance;
      componentConf.title = 'teste';

      expect(alertModalService.showConfirm('oi', 'oi')).toHaveBeenCalledWith();

    });

  });
});
