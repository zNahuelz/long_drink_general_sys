import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Curso } from '../models/curso.model';

const API = 'http://localhost:8080/api/v1';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type' : 'application/json'})
}; 

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor(private http: HttpClient) { }

  getCursos(): Observable<Curso[]>{
    return this.http.get<Curso[]>(API+'/curso');
  }

  getCursosActivos(): Observable<Curso[]>{
    return this.http.get<Curso[]>(API+'/curso/activos');
  }

  getCurso(cod?: number): Observable<Curso>{
    return this.http.get<Curso>(API+'/curso/'+cod);
  }
  
  getCursosDisponibles(): Observable<Curso[]>{
    return this.http.get<Curso[]>(API+'/curso/disponibles');
  }

  nuevoCurso(carga: any): Observable<any>{
    return this.http.post(
      API+`/curso`,
      carga,
      httpOptions
    )
  }
  editarCurso(carga: any): Observable<any>{
    return this.http.put(API+`/curso`,carga,httpOptions);
  }
  
}
