import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ListComponent } from './list/list.component';

const routes: Routes = [
  { path: '', component: ListComponent},
  { path: 'novo', component: CadastroComponent},
  { path: 'editar/:id', component: CadastroComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ListPessoasRoutingModule { }
