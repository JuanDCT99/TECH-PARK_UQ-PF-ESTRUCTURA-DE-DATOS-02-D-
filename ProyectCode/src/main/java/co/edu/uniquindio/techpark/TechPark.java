package co.edu.uniquindio.techpark.controller;

import co.edu.uniquindio.techpark.Model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora principal del sistema TECH-PARK UQ.
 * Coordina la lógica entre el modelo, las estructuras de datos y la interfaz.
 */
public class TechPark {
    private List<Zona> zonas;
    private List<Usuario> usuarios;
    private List<Atraccion> todasLasAtracciones;

    public TechPark() {
        this.zonas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.todasLasAtracciones = new ArrayList<>();
    }

    // Métodos de gestión global
    public void agregarZona(Zona zona) {
        zonas.add(zona);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Valida el aforo global del parque antes de permitir una nueva entrada.
     */
    public boolean hayAforoGlobal() {
        int totalVisitantes = 0;
        int capacidadTotal = 0;

        for (Zona zona : zonas) {
            totalVisitantes += zona.getVisitantesActuales();
            capacidadTotal += zona.getCapacidadMaxima();
        }

        if (capacidadTotal == 0) return true; // Si no hay zonas, el parque está "vacío"

        return totalVisitantes < capacidadTotal;
    }

    // Getters
    public List<Zona> getZonas() {
        return zonas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Atraccion> getTodasLasAtracciones() {
        return todasLasAtracciones;
    }
}