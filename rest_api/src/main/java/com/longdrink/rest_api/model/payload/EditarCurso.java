package com.longdrink.rest_api.model.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EditarCurso {
    private Long codCurso;
    private String nombre;
    private String descripcion;
    private String frecuencia;
    private double mensualidad;
    private boolean visibilidad;

    @JsonIgnore
    public EditarCurso limpiarDatos(){
        try{
            return new EditarCurso(this.codCurso,
                    this.nombre.trim().toUpperCase(),
                    this.descripcion.trim(),
                    this.frecuencia.trim(),this.mensualidad,
                    this.visibilidad);
        }
        catch(Exception ex){ return null; }
    }

    @JsonIgnore
    public boolean validarDatos(){
        return this.nombre.length() >= 1 && this.nombre.length() <= 100 &&
                this.descripcion.length() >= 1 && this.descripcion.length() <= 150 &&
                this.frecuencia.length() > 1 &&
                this.mensualidad >= 1 && this.mensualidad <= 1000;
    }
    public EditarCurso(){}

    public EditarCurso(Long codCurso, String nombre, String descripcion, String frecuencia, double mensualidad, boolean visibilidad) {
        this.codCurso = codCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.mensualidad = mensualidad;
        this.visibilidad = visibilidad;
    }

    public Long getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(Long codCurso) {
        this.codCurso = codCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(double mensualidad) {
        this.mensualidad = mensualidad;
    }

    public boolean isVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
