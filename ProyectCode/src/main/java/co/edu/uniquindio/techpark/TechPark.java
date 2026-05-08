package co.edu.uniquindio.techpark;

import co.edu.uniquindio.techpark.Model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora principal del sistema TECH-PARK UQ.
 * Coordina la lógica entre el modelo, las estructuras de datos y la interfaz.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
@Service
public class TechPark {
    private List<Zona> zonas;
    private List<Usuario> usuarios;
    private List<Atraccion> todasLasAtracciones;

    /**
     * Constructor de la clase TechPark.
     * Inicializa las listas principales del sistema.
     */
    public TechPark() {
        this.zonas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.todasLasAtracciones = new ArrayList<>();
    }

    /**
     * Inicializa el parque con datos de prueba.
     * Método ejecutado automáticamente después de la construcción del bean.
     */
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

    /**
     * Agrega una zona al parque si no existe previamente.
     * Sincroniza las atracciones de la zona con la lista global.
     * 
     * @param zona Zona a agregar
     */
    public void agregarZona(Zona zona) {
        if (zona == null) {
            throw new IllegalArgumentException("La zona no puede ser null");
        }
        if (buscarZonaPorId(zona.getId()) != null) {
            System.out.println("La zona con ID " + zona.getId() + " ya existe en el parque.");
            return;
        }
        zonas.add(zona);
        // Sincronizar las atracciones existentes de la zona con la lista global del parque
        for (Atraccion atraccion : zona.getListaAtracciones()) {
            if (!todasLasAtracciones.contains(atraccion)) {
                todasLasAtracciones.add(atraccion);
            }
        }
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param usuario Usuario a registrar
     */
    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    /**
     * Registra una nueva atracción directamente en una zona y actualiza el catálogo global.
     * Valida que la atracción no esté duplicada.
     * 
     * @param zona Zona donde registrar la atracción
     * @param atraccion Atracción a registrar
     */
    public void registrarNuevaAtraccion(Zona zona, Atraccion atraccion) {
        if (zona == null || atraccion == null) {
            throw new IllegalArgumentException("La zona y la atracción no pueden ser null");
        }
        zona.agregarAtraccion(atraccion);
        if (!todasLasAtracciones.contains(atraccion)) {
            todasLasAtracciones.add(atraccion);
        }
    }

    /**
     * Busca una zona por su identificador único.
     * 
     * @param id Identificador de la zona
     * @return Zona encontrada o null si no existe
     */
    private Zona buscarZonaPorId(String id) {
        for (Zona zona : zonas) {
            if (zona.getId().equals(id)) {
                return zona;
            }
        }
        return null;
    }

    /**
     * Activa una alerta climática que cierra automáticamente atracciones acuáticas o mecánicas.
     * Notifica a los visitantes afectados.
     * 
     * @param motivo Motivo de la alerta climática
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
     * 
     * @return true si hay aforo disponible, false si se alcanzó el límite
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

    /**
     * Obtiene la lista de zonas del parque.
     * 
     * @return Lista de zonas
     */
    public List<Zona> getZonas() {
        return zonas;
    }

    /**
     * Obtiene la lista de usuarios registrados.
     * 
     * @return Lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Obtiene el catálogo completo de atracciones del parque.
     * 
     * @return Lista de todas las atracciones
     */
    public List<Atraccion> getTodasLasAtracciones() {
        return todasLasAtracciones;
    }
}
