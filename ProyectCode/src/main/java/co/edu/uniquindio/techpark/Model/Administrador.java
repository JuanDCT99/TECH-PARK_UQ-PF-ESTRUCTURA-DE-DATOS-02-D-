package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Administrador extends Empleado {
    private int nivelAcceso;

    public Administrador(String id, String nombre, String correo, String contrasena, LocalDateTime fechaRegistro, 
                         String codigoEmpleado, double salario, int nivelAcceso) {
        super(id, nombre, correo, contrasena, fechaRegistro, codigoEmpleado, salario);
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
