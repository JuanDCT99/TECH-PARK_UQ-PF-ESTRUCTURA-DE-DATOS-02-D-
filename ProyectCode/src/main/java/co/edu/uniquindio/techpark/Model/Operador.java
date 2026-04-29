package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Operador extends Empleado {
    private Turno turno;
    private Zona zonaAsignada;
    
    public Operador(String id, String nombre, String correo, String contrasena, LocalDateTime fechaRegistro, 
                    String codigoEmpleado, double salario, Turno turno) {
        super(id, nombre, correo, contrasena, fechaRegistro, codigoEmpleado, salario);
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Zona getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(Zona zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    /**
     * Registra una revisión técnica para una atracción.
     * Solo puede hacerlo si la atracción pertenece a su zona asignada.
     */
    public void registrarRevisionTecnica(Atraccion atraccion) {
        if (zonaAsignada != null && zonaAsignada.getListaAtracciones().contains(atraccion)) {
            atraccion.registrarRevisionTecnica();
            System.out.println("Revisión técnica registrada por " + getNombre() + " para " + atraccion.getNombre());
        } else {
            System.out.println("Error: El operador no tiene permisos para gestionar esta atracción.");
        }
    }

    @Override
    public String toString() {
        return "Operador [turno=" + turno + ", zona=" + (zonaAsignada != null ? zonaAsignada.getNombre() : "Ninguna") + "]";
    }
    
    
}
