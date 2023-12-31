package com.longdrink.rest_api.dao;

import com.longdrink.rest_api.model.Inscripcion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IInscripcionDAO extends CrudRepository<Inscripcion,Long> {
    List<Inscripcion> findAllByAlumnoCodAlumno(Long codAlumno);

    List<Inscripcion> findAllByAlumnoDni(String dni);

    @Query("SELECT I FROM Inscripcion I WHERE I.fechaTerminado != null")
    List<Inscripcion> findAllByEstado();

    @Query("SELECT I FROM Inscripcion I WHERE I.fechaTerminado = null")
    List<Inscripcion> findAllByEnCurso();

    Optional<Inscripcion> findByCodInscripcion(Long codInscripcion);

    List<Inscripcion> findAllByEstadoAndFechaTerminado(boolean estado, Date fechaTerminado);

    List<Inscripcion> findAllBySeccionCodSeccion(Long codSeccion);

    List<Inscripcion> findAllBySeccionCursoCodCurso(Long codCurso);

}
