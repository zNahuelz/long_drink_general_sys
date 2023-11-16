package com.longdrink.rest_api.controller;

import com.longdrink.rest_api.model.Alumno;
import com.longdrink.rest_api.model.Curso;
import com.longdrink.rest_api.model.Inscripcion;
import com.longdrink.rest_api.model.Profesor;
import com.longdrink.rest_api.services.AlumnoService;
import com.longdrink.rest_api.services.CursoService;
import com.longdrink.rest_api.services.InscripcionService;
import com.longdrink.rest_api.services.ProfesorService;
import com.longdrink.rest_api.utils.ExportarExcel;
import com.longdrink.rest_api.utils.ExportarPdf;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reporte")
public class ReportesController {

    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private InscripcionService inscripcionService;
    @Autowired
    private ExportarPdf pdfService;

    @GetMapping("/profesor/excel")
    public void exportarProfesorExcel(HttpServletResponse response, @RequestParam(required = false,name = "tipo") Integer tipo) throws IOException{
        if(tipo == null){ tipo = 0; }
        response.setContentType("application/octet-stream");
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatoFecha.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=profesores_"+fechaActual+".xlsx";
        response.setHeader(headerKey,headerValue);
        if(tipo == 1){
            List<Profesor> listaProfesores = profesorService.listarActivos(); //Profesores activos.
            ExportarExcel exportador = new ExportarExcel(listaProfesores,0);
            exportador.exportarProfesores(response);
        }
        else if(tipo == 2){
            List<Profesor> listaProfesores = profesorService.listarActivosNoAsignados(); //Disponibles para nuevo curso.
            ExportarExcel exportador = new ExportarExcel(listaProfesores,0);
            exportador.exportarProfesores(response);
        }
        else{
            List<Profesor> listaProfesores = profesorService.listarProfesores(); //Listado general.
            ExportarExcel exportador = new ExportarExcel(listaProfesores,0);
            exportador.exportarProfesores(response);
        }
    }

    @GetMapping("/curso/excel")
    public void exportarCursoExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatoFecha.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cursos_"+fechaActual+".xlsx";
        response.setHeader(headerKey,headerValue);

        List<Curso> listaCursos = cursoService.listarCursos();
        ExportarExcel exportador = new ExportarExcel(listaCursos);
        exportador.exportarCursos(response);
    }

    @GetMapping("/alumno/excel")
    public void exportarAlumnoExcel(HttpServletResponse response, @RequestParam(required = false,name = "tipo") Integer tipo) throws IOException{
        if(tipo == null){ tipo = 0; }
        response.setContentType("application/octet-stream");
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatoFecha.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=alumnos_"+fechaActual+".xlsx";
        response.setHeader(headerKey,headerValue);

        if(tipo == 1){
            List<Alumno> listaAlumnos = alumnoService.listarAlumnosActivos(); //Alumnos activos.
            ExportarExcel exportador = new ExportarExcel(listaAlumnos,"a");
            exportador.exportarAlumnos(response);
        }
        else{
            List<Alumno> listaAlumnos = alumnoService.listarAlumnos(); //Listado general.
            ExportarExcel exportador = new ExportarExcel(listaAlumnos,"a");
            exportador.exportarAlumnos(response);
        }
    }

    @GetMapping("/inscripciones/excel")
    public void exportarInscripcionExcel(HttpServletResponse response,@RequestParam(required = false,name = "tipo") Integer tipo,
                                         @RequestParam(required = false, name = "dni") String dni,
                                         @RequestParam(required = false, name = "codCurso") Long codCurso) throws IOException{
        if(tipo == null){ tipo = 0;}
        response.setContentType("application/octet-stream");
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatoFecha.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=inscripciones_"+fechaActual+".xlsx";
        response.setHeader(headerKey,headerValue);
        if(tipo == 1){
            if(alumnoService.getPorDNI(dni) != null){
                List<Inscripcion> listaInscripciones = inscripcionService.listarPorDniAlumno(dni);      //Por alumno.
                ExportarExcel exportador = new ExportarExcel(listaInscripciones,1L);
                exportador.exportarInscripcion(response);
            }
            else{ exportarInscripcionesGeneral(response); }
        }
        else if(tipo == 2){
            if(cursoService.getPorCod(codCurso) != null){
                if(!cursoService.getPorCod(codCurso).getInscripciones().isEmpty()){
                    List<Inscripcion> listaInscripciones = inscripcionService.listarPorCurso(codCurso);
                    ExportarExcel exportador = new ExportarExcel(listaInscripciones,1L); //Por curso.
                    exportador.exportarInscripcion(response);
                }
                else{ exportarInscripcionesGeneral(response); }  //Generales.
            }
            else{ exportarInscripcionesGeneral(response); }
        }
        else{ exportarInscripcionesGeneral(response); }
    }

    @GetMapping("/alumno/pdf")
    public void ExportarAlumnosPDF(HttpServletResponse response, @RequestParam(name="activo", required = false) Integer activo) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue;
        if (activo == 1){
            headerValue = "attachment; filename= AlumnosActivos-" + currentDateTime + ".pdf";
        }
        else{
           headerValue = "attachment; filename= AlumnosGeneral-" + currentDateTime + ".pdf";
        }
        response.setHeader(headerKey, headerValue);
        //activo == 1 -> Listar todos los alumnos activos
        //activo == 0 -> Listado GENERAL de alumnos
        this.pdfService.exportAlumnos(response, activo);
    }

    @GetMapping("/curso/pdf")
    public void ExportarCursosPDF(HttpServletResponse response, @RequestParam(name="visible", required = false) Integer visible) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue;
        if (visible == 1){
            headerValue = "attachment; filename= CursosActivos-" + currentDateTime + ".pdf";
        }
        else{
           headerValue = "attachment; filename= CursosGeneral-" + currentDateTime + ".pdf";
        }
        response.setHeader(headerKey, headerValue);
        //activo == 1 -> Listar todos los cursos activos
        //activo == 0 -> Listado GENERAL de cursos
        this.pdfService.exportCursos(response, visible);
    }

    @GetMapping("/docente/pdf")
    public void ExportarDocentesPDF(HttpServletResponse response, @RequestParam(required = false, name="activo") Integer activo) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue;
        if (activo == null || activo.intValue() == 0){
            headerValue = "attachment; filename= DocentesGeneral-" + currentDateTime + ".pdf";

        }
        else{
            headerValue = "attachment; filename= DocentesActivos-" + currentDateTime + ".pdf";
        }

        response.setHeader(headerKey, headerValue);
        //activo == 1 -> Listar todos los cursos activos
        //activo == 0 -> Listado GENERAL de cursos
        this.pdfService.exportDocentes(response, activo);
    }

    @GetMapping("/inscripciones/pdf")
    public void ExportarInscripcionesPDF(HttpServletResponse response, @RequestParam(required = false, name="tipo") int tipo, @RequestParam(required = false, name="cod") int codigo) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "";
        if (tipo == 1){
            headerValue = "attachment; filename= InscripcionesPorAlumno-" + currentDateTime + ".pdf";
        }
        else if (tipo == 2){
            headerValue = "attachment; filename= InscripcionesPorCurso-" + currentDateTime + ".pdf";
        }
        else if (tipo == 0){
            headerValue = "attachment; filename= InscripcionesGeneral-" + currentDateTime + ".pdf";
        }

        response.setHeader(headerKey, headerValue);
        //activo == 1 -> Listar todos los cursos activos
        //activo == 0 -> Listado GENERAL de cursos
        this.pdfService.exportInscripcion(response, tipo, codigo);
    }

    public void exportarInscripcionesGeneral(HttpServletResponse response) throws IOException {
        List<Inscripcion> listaInscripciones = inscripcionService.listarInscripciones();
        ExportarExcel exportador = new ExportarExcel(listaInscripciones,1L); //General.
        exportador.exportarInscripcion(response);
    }

}
