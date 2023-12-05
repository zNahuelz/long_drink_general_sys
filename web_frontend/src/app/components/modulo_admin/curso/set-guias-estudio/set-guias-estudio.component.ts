import { Component, OnInit } from '@angular/core';
import { Curso } from 'src/app/models/curso.model';
import { TemaCurso } from 'src/app/models/tema.curso.model';
import { Tema } from 'src/app/models/tema.model';
import { CursoService } from 'src/app/services/curso.service';
import { StorageService } from 'src/app/services/storage.service';
import { TemaService } from 'src/app/services/tema.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-set-guias-estudio',
  templateUrl: './set-guias-estudio.component.html',
  styleUrls: ['./set-guias-estudio.component.css']
})
export class SetGuiasEstudioComponent implements OnInit{
  listaCursos: Curso[] = [];
  listaTemas: TemaCurso[] = [];
  cursoSeleccionado = 1;
  temasSeleccionados: Tema[] = [];
  listaCodigos: number[] = [];
  
  constructor(private storageService: StorageService, private cursoService: CursoService, private temaService: TemaService) {}
  ngOnInit(): void {
    this.storageService.comprobarSesion();
    this.storageService.denegarAcceso("ALUMNOyDOCENTE");
    this.llenarCursos();
    this.llenarTemas();
  }
  /* 
    Planeado para tercer sprint, probablemente haya una mejor forma de hacerlo sin checks.
  */

  llenarCursos(): void{
    this.cursoService.getCursos().subscribe({
      next: (data) =>{
        this.listaCursos = data;
      },
      error: (err) =>{
        this.mensajeError();
      }
    })
  }

  llenarTemas(): void{
    this.temaService.obtenerTemas().subscribe({
      next: (data) => {
        this.listaTemas = data;
      },
      error: (err) =>{
        this.mensajeError();
      }
    })
  }

  setCurso(curso: number | any): void{
    this.cursoSeleccionado = curso;
    console.log(this.cursoSeleccionado);
    this.setTemas(curso);
  }

  setTemas(curso: number): void{ //Llenar temas de curso en especifico.
    this.temaService.obtenerTemaPorCurso(curso).subscribe({
      next: (data) => {
        this.temasSeleccionados = data;
        console.log(this.temasSeleccionados);
        this.setCheck();
      },
      error: (err) =>{
        console.log(err)
      }
    })
  }

  setCheck(): void{
    this.listaTemas.forEach((e) =>{
      this.temasSeleccionados.forEach((x) =>{
        if(this.temasSeleccionados.find(i => i.codTema === e.codTema)){
          e.checked = true;
        }
        else{
          e.checked = false;
        }
      })
    })
    this.listaCodigos.length = 0;
    this.temasSeleccionados.forEach((i) =>{
      var cod: number = +i.codTema!;
      this.listaCodigos.push(cod);
    })
    console.log(this.listaCodigos);
  }

  checkChange(codTema: number | any){
    var cod: number = +codTema;
    this.listaCodigos.push(cod);
    console.log(this.listaCodigos);
  }

  uncheckAll(): void{
    this.listaTemas.forEach((e) =>{
      e.checked = false;
    })
  }


  public mensajeError(): void{
    Swal.fire({
      title: "Ups! Ha sucedido un error.",
      text: "No se encontraron guías de estudio asociadas a ningún curso.",
      icon: "error"
    })
  }
}