import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormControl } from '@angular/forms';
import { of } from 'rxjs';

import { ErrorMsgComponent } from './error-msg.component';

describe('ErrorMsgComponent', () => {
  let component: ErrorMsgComponent;
  let fixture: ComponentFixture<ErrorMsgComponent>;

  const formControl = jasmine.createSpyObj('formControl', ['error']);
  formControl.error.and.callFake(() => of({}));

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        ErrorMsgComponent,
        { provide: FormControl, useValue: formControl },
      ]
    });

    fixture = TestBed.createComponent(ErrorMsgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('deve testar o errorMessage', () => {

    let retorno = component.errorMessage();

  });
});
