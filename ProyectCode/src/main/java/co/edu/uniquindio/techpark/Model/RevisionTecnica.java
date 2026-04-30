package co.edu.uniquindio.techpark.Model;

import java.util.*;

public class RevisionTecnica {
    private String id;
    private Atraccion atraccion;
    private Operador operador;
    private Date fecha;
    private boolean aprovada;
    
    public RevisionTecnica(String id, Atraccion atraccion, Operador operador, Date fecha) {
        this.id = id;
        this.atraccion = atraccion;
        this.operador = operador;
        this.fecha = fecha;
        this.aprovada = false;
    }

    public boolean isAprobada() {
        return aprovada;
    }

    public void setAprobada(boolean aprovada) {
        this.aprovada = aprovada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "RevisionTecnica [id=" + id + ", atraccion=" + atraccion + ", operador=" + operador + ", fecha=" + fecha
                + "]";
    }

    

}
