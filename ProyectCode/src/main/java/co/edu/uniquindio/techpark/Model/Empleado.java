package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Empleado extends Usuario {
    private String codigoEmpleado;
    private double salario;

    public Empleado(String id, String nombre, String correo, String contrasena, LocalDateTime fechaRegistro, String codigoEmpleado, double salario) {
        super(id, nombre, correo, contrasena, fechaRegistro);
        this.codigoEmpleado = codigoEmpleado;
        this.salario = salario;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}