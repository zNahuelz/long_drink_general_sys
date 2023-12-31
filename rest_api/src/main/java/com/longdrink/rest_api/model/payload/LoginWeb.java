package com.longdrink.rest_api.model.payload;

public class LoginWeb {
    private String nombreUsuario;
    private String contrasena;
    private String email;
    private String nombreCompleto;
    private String rol;

    public LoginWeb(){}

    public LoginWeb(String nombreUsuario, String contrasena, String email, String nombreCompleto, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
