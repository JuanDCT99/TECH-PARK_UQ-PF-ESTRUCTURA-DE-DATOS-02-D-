package co.edu.uniquindio.techpark.Model;

import java.util.ArrayList;
import java.util.List;

public class Zona {
    private String id;
    private String nombre;
    private String descripcion;
    private int capacidadMaxima;
    private int visitantesActuales;
    private List<Atraccion> listaAtracciones;

    public Zona(String id, String nombre, String descripcion, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.visitantesActuales = 0;
        this.listaAtracciones = new ArrayList<>();
    }

    // Métodos básicos para gestionar la zona
    public void agregarAtraccion(Atraccion atraccion) {
        this.listaAtracciones.add(atraccion);
    }

    public boolean hayEspacio() {
        return visitantesActuales < capacidadMaxima;
    }

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getVisitantesActuales() {
        return visitantesActuales;
    }

    public void setVisitantesActuales(int visitantesActuales) {
        this.visitantesActuales = visitantesActuales;
    }

    @Override
    public String toString() {
        return "Zona [nombre=" + nombre + ", atracciones=" + listaAtracciones.size() + 
               ", aforo=" + visitantesActuales + "/" + capacidadMaxima + "]";
    }
}