import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'listPessoas'},
  { path: 'listPessoas', loadChildren: () => import('./list-pessoas/list-pessoas.module').then(m => m.ListPessoasModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
