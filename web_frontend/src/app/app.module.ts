import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/shared/login/login.component';
import { DashboardComponent } from './components/shared/dashboard/dashboard.component';
import { ListadoCursosComponent } from './components/modulo_admin/curso/listado-cursos/listado-cursos.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { ListadoInscripcionesComponent } from './components/modulo_admin/inscripcion/listado-inscripciones/listado-inscripciones.component';
import { NuevoCursoComponent } from './components/modulo_admin/curso/nuevo-curso/nuevo-curso.component';
import { ListadoAlumnosComponent } from './components/modulo_admin/alumno/listado-alumnos/listado-alumnos.component';
import { ListadoProfesoresComponent } from './components/modulo_admin/profesor/listado-profesores/listado-profesores.component';
import { NuevoProfesorComponent } from './components/modulo_admin/profesor/nuevo-profesor/nuevo-profesor.component';
import { DetalleInscripcionComponent } from './components/modulo_admin/inscripcion/detalle-inscripcion/detalle-inscripcion.component';
import { MiCuentaComponent } from './components/shared/mi-cuenta/mi-cuenta.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenav, MatSidenavContainer, MatSidenavModule } from '@angular/material/sidenav';
import { RouterModule } from '@angular/router';
import { MatListItemIcon, MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon'
import { MatCommonModule } from '@angular/material/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatExpansionModule} from '@angular/material/expansion';
import { DetalleCursoAdminComponent } from './components/modulo_admin/curso/detalle-curso-admin/detalle-curso-admin.component';
import { MisCursosComponent } from './components/modulo_alumnos/mis-cursos/mis-cursos.component';
import { DetalleCursoAlumnoComponent } from './components/modulo_alumnos/detalle-curso-alumno/detalle-curso-alumno.component';
import { MisCursosProfesorComponent } from './components/modulo_profesores/mis-cursos-profesor/mis-cursos-profesor.component';
import { DetalleCursoProfesorComponent } from './components/modulo_profesores/detalle-curso-profesor/detalle-curso-profesor.component';
import { MatTableModule } from '@angular/material/table';
import { ExportarAlumnosComponent } from './components/modulo_admin/exportaciones/exportar-alumnos/exportar-alumnos.component';
import { ExportarDocentesComponent } from './components/modulo_admin/exportaciones/exportar-docentes/exportar-docentes.component';
import { ExportarCursosComponent } from './components/modulo_admin/exportaciones/exportar-cursos/exportar-cursos.component';
import { ExportarInscripcionesComponent } from './components/modulo_admin/exportaciones/exportar-inscripciones/exportar-inscripciones.component';
import { ExportarGeneralComponent } from './components/modulo_admin/exportaciones/exportar-general/exportar-general.component' 
import { InscripcionAlumnoComponent } from './components/modulo_admin/alumno/inscripcion-alumno/inscripcion-alumno.component' 
import { GuiasEstudioComponent } from './components/modulo_admin/curso/guias-estudio/guias-estudio.component';
import { SetGuiasEstudioComponent } from './components/modulo_admin/curso/set-guias-estudio/set-guias-estudio.component';
import { InscripcionAlumnoAntiguoComponent } from './components/modulo_admin/alumno/inscripcion-alumno-antiguo/inscripcion-alumno-antiguo.component';
import { EditarAlumnoComponent } from './components/modulo_admin/alumno/editar-alumno/editar-alumno.component';
import { EditarProfesorComponent } from './components/modulo_admin/profesor/editar-profesor/editar-profesor.component';
import { EditarCursoComponent } from './components/modulo_admin/curso/editar-curso/editar-curso.component';
import { RecuperarCuentaComponent } from './components/shared/recuperar-cuenta/recuperar-cuenta.component';
import { MisPagosComponent } from './components/modulo_alumnos/mis-pagos/mis-pagos.component';
import { MarcarAsistenciaComponent } from './components/modulo_alumnos/marcar-asistencia/marcar-asistencia.component';
import { ListadoAsistenciasComponent } from './components/modulo_admin/asistencias/listado-asistencias/listado-asistencias.component';
import { ListadoSeccionesComponent } from './components/modulo_admin/secciones/listado-secciones/listado-secciones.component';
import { AsignarSeccionCursoComponent } from './components/modulo_admin/secciones/asignar-seccion-curso/asignar-seccion-curso.component';
import { NuevoPagoAlumnoComponent } from './components/modulo_admin/pagos/nuevo-pago-alumno/nuevo-pago-alumno.component';
import { ListadoPagosAlumnosComponent } from './components/modulo_admin/pagos/listado-pagos-alumnos/listado-pagos-alumnos.component';
import { DetalleSeccionComponent } from './components/modulo_admin/secciones/detalle-seccion/detalle-seccion.component' 

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ListadoCursosComponent,
    HeaderComponent,
    ListadoInscripcionesComponent,
    NuevoCursoComponent,
    ListadoAlumnosComponent,
    ListadoProfesoresComponent,
    NuevoProfesorComponent,
    DetalleInscripcionComponent,
    MiCuentaComponent,
    DetalleCursoAdminComponent,
    MisCursosComponent,
    DetalleCursoAlumnoComponent,
    MisCursosProfesorComponent,
    DetalleCursoProfesorComponent,
    ExportarAlumnosComponent,
    ExportarDocentesComponent,
    ExportarCursosComponent,
    ExportarInscripcionesComponent,
    ExportarGeneralComponent,
    InscripcionAlumnoComponent,
    GuiasEstudioComponent,
    SetGuiasEstudioComponent,
    InscripcionAlumnoAntiguoComponent,
    EditarAlumnoComponent,
    EditarProfesorComponent,
    EditarCursoComponent,
    RecuperarCuentaComponent,
    MisPagosComponent,
    MarcarAsistenciaComponent,
    ListadoAsistenciasComponent,
    ListadoSeccionesComponent,
    AsignarSeccionCursoComponent,
    NuevoPagoAlumnoComponent,
    ListadoPagosAlumnosComponent,
    DetalleSeccionComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatCommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatExpansionModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }