import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ListPessoasRoutingModule } from './list-pessoas-routing.module';
import { ListComponent } from './list/list.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [ListComponent, CadastroComponent],
  imports: [
    CommonModule,
    ListPessoasRoutingModule,
    ReactiveFormsModule,
    NgxMaskModule.forChild(),
    SharedModule
  ]
})
export class ListPessoasModule { }
