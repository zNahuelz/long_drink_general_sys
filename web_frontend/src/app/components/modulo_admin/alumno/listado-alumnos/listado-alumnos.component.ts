import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno.model';
import { AlumnoService } from 'src/app/services/alumno.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-listado-alumnos',
  templateUrl: './listado-alumnos.component.html',
  styleUrls: ['./listado-alumnos.component.css']
})
export class ListadoAlumnosComponent implements OnInit {
  alumnos: Alumno[] = [];
  criterioSeleccionado = '';

  columnas: string[] = ['codAlumno','apellidoPaterno','apellidoMaterno','nombre','dni','telefono','activo','acciones']

  constructor(private storageService: StorageService, private router: Router, private alumnoService: AlumnoService) { }
  ngOnInit(): void {
    this.storageService.comprobarSesion();
    this.storageService.denegarAcceso('ALUMNOyDOCENTE');
    this.getAlumnosActivos();
  }

  onSelected(indice: string): void{
    console.log(indice);
    this.criterioSeleccionado = indice;
    if(this.criterioSeleccionado === 'listadoGeneral'){
      this.getAlumnosGeneral();
    }
    else{
      this.getAlumnosActivos();
    }
  }

  getAlumnosActivos(): void{
    this.alumnos.length = 0;
    this.alumnoService.getAlumnosActivos().subscribe({
      next: (data) => {
        this.alumnos = data;
        console.log(this.alumnos);
      },
      error: (err) =>{
        console.log(err);
      }
    });
  }

  getAlumnosGeneral(): void{
    this.alumnos.length = 0;
    this.alumnoService.getAlumnos().subscribe({
      next: (data) => {
        this.alumnos = data;
        console.log(this.alumnos);
      },
      error: (err) =>{
        console.log(err);
      }
    });
  }

}
