package co.edu.uniquindio.techpark;

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
        // Sincronizar las atracciones existentes de la zona con la lista global del parque
        for (Atraccion atraccion : zona.getListaAtracciones()) {
            if (!todasLasAtracciones.contains(atraccion)) {
                todasLasAtracciones.add(atraccion);
            }
        }
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Registra una nueva atracción directamente en una zona y actualiza el catálogo global.
     */
    public void registrarNuevaAtraccion(Zona zona, Atraccion atraccion) {
        zona.agregarAtraccion(atraccion);
        if (!todasLasAtracciones.contains(atraccion)) {
            todasLasAtracciones.add(atraccion);
        }
    }

    /**
     * Activa una alerta climática que cierra automáticamente atracciones acuáticas o mecánicas.
     */
    public void activarAlertaClimatica(String motivo) {
        for (Atraccion atr : todasLasAtracciones) {
            String tipo = atr.getTipo().toLowerCase();
            if (tipo.contains("acuática") || tipo.contains("mecánica")) {
                atr.setEstado(EstadoAtraccion.CERRADA);
                atr.setMotivoCierre("CIERRE AUTOMÁTICO POR CLIMA: " + motivo);
            }
        }
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