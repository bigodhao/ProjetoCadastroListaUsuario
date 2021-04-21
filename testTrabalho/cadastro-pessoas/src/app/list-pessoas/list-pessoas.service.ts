import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pessoa } from './pessoa';
import { delay, take, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListPessoasService {

  //private readonly API = `${environment.API}pessoas`;
  public readonly API = "/api/pessoas";
   //private readonly API = "http://localhost:9000/pessoas";

  constructor(private http: HttpClient) { }

  list(){
    return this.http.get<Pessoa[]>(this.API).pipe(
      tap(console.log)
    );
  }

  getId(id: Number) :Observable<Pessoa> {
    return this.http.get<Pessoa>(`${this.API}/${id}`).pipe(take(1));
  }

  private create(pessoa: Pessoa){
    return this.http.post(this.API, pessoa).pipe(take(1));
  }

  private update(pessoa: Pessoa){
    return this.http.put(`${this.API}/${pessoa.id}`,pessoa).pipe(take(1));
  }

  save(pessoa: Pessoa){
    if (pessoa.id) {
      return this.update(pessoa);
    }
    return this.create(pessoa);
  }

  remover(id: number){
    return this.http.delete(`${this.API}/${id}`).pipe(take(1));
  }

}
