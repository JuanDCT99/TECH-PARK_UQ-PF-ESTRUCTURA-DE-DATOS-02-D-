package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Administrador extends Usuario{
    private int nivelAcceso;

    
    public Administrador(String id, String correo, String contrasena, LocalDateTime fechaRegistro, int nivelAcceso) {
        super(id, correo, contrasena, fechaRegistro);
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String toString() {
        return "Administrador [nivelAcceso=" + nivelAcceso + "]";
    }

    
}
