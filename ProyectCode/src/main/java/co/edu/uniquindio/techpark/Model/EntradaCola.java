package co.edu.uniquindio.techpark.Model;

import java.sql.Date;

public class EntradaCola {
    private Visitante visitante;
    private Tiquete tiquete;
    private int prioridad;
    private Date horaIngreso;
    private Atraccion atraccion;

    public EntradaCola(Visitante visitante, Tiquete tiquete, int prioridad, Date horaIngreso, Atraccion atraccion) {
        this.visitante = visitante;
        this.tiquete = tiquete;
        this.prioridad = prioridad;
        this.horaIngreso = horaIngreso;
        this.atraccion = atraccion;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    @Override
    public String toString() {
        return "EntradaCola [visitante=" + visitante + ", tiquete=" + tiquete + ", prioridad=" + prioridad
                + ", horaIngreso=" + horaIngreso + ", atraccion=" + atraccion + "]";
    }

    

    
    
}
