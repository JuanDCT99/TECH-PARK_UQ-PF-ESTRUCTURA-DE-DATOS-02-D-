package co.edu.uniquindio.techpark;

import co.edu.uniquindio.techpark.Model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora principal del sistema TECH-PARK UQ.
 * Coordina la lógica entre el modelo, las estructuras de datos y la interfaz.
 */
@Service
public class TechPark {
    private List<Zona> zonas;
    private List<Usuario> usuarios;
    private List<Atraccion> todasLasAtracciones;

    public TechPark() {
        this.zonas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.todasLasAtracciones = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        // Inicialización de datos de prueba
        Zona zonaA = new Zona("Z1", "Zona Extrema", "Norte", 500);
        Atraccion a1 = new Atraccion("A1", "Montaña Rusa X", "Mecánica", 20, 1.4f, 12, 5000);
        Atraccion a2 = new Atraccion("A2", "Caída Libre", "Mecánica", 12, 1.5f, 15, 7000);
        
        agregarZona(zonaA);
        registrarNuevaAtraccion(zonaA, a1);
        registrarNuevaAtraccion(zonaA, a2);

        Zona zonaB = new Zona("Z2", "Zona Acuática", "Sur", 300);
        Atraccion a3 = new Atraccion("A3", "Tobogán Gigante", "Acuática", 5, 1.2f, 8, 3000);
        
        agregarZona(zonaB);
        registrarNuevaAtraccion(zonaB, a3);
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