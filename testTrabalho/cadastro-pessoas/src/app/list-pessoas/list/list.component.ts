import { Pessoa } from './../pessoa';
import { Component, OnInit } from '@angular/core';
import { EMPTY, empty, Observable, observable } from 'rxjs';
import { ListPessoasService } from '../list-pessoas.service';
import { catchError, switchMap, take } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertModalService } from 'src/app/shared/alert-modal.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  pessoa$: Observable<Pessoa>;

  constructor(
    private service: ListPessoasService,
    private router: Router,
    private route: ActivatedRoute,
    private modal: AlertModalService) { }

  ngOnInit(): void {

    this.onRefresh();

  }
  onRefresh(){
    this.pessoa$ = this.service.list().pipe(
      catchError(error => {
        console.error(error);
        return empty();
      })
    );
  }

  editar(id){
    this.router.navigate(['editar', id], {relativeTo: this.route});
  }

   result$;

  remover(pessoa){
    this.result$ = this.modal.showConfirm('Confirmação', 'Tem certeza que deseja remover essa pessoa?');

    this.result$.asObservable().pipe(
      take(1),
      switchMap(result => result ? this.service.remover(pessoa.id) : EMPTY)
    ).subscribe(
      success => this.onRefresh(),
      error => this.modal.showAlertDanger('Erro ao remover pessoa. Tente novamente mais tarde.')
    );
  }

}
