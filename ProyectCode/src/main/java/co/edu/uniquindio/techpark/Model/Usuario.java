package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String contrasena;
    private LocalDateTime fechaRegistro;

    public Usuario(String id, String nombre, String correo, String contrasena, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", correo=" + correo + ", contrasena=" + contrasena + ", fechaRegistro="
                + fechaRegistro + "]";
    }

    

    
}
