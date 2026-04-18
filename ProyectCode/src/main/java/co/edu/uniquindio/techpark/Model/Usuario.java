package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Usuario {
    public String id;
    public String correo;
    public String contrasena;
    public LocalDateTime fechaRegistro;

    public Usuario(String id, String correo, String contrasena, LocalDateTime fechaRegistro) {
        this.id = id;
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
