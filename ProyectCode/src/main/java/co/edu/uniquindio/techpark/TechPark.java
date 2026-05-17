package co.edu.uniquindio.techpark;

import co.edu.uniquindio.techpark.Model.*;
import co.edu.uniquindio.techpark.service.DatosService;
import co.edu.uniquindio.techpark.service.Sender;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private ListaEnlazada<Zona> zonas;
    private ListaEnlazada<Usuario> usuarios;
    private ListaEnlazada<Atraccion> todasLasAtracciones;
    
    // Nuevas estructuras propias (Fase 2)
    private ArbolBinarioBusqueda catalogoAtracciones;
    private Grafo mapaParque;

    // Tickets emitidos
    private ListaEnlazada<Tiquete> tiquetesEmitidos;
    
    @Autowired
    private DatosService datosService;

    /**
     * Constructor de la clase TechPark.
     * Inicializa las listas principales y las estructuras propias.
     */
    public TechPark() {
        this.zonas = new ListaEnlazada<>();
        this.usuarios = new ListaEnlazada<>();
        this.todasLasAtracciones = new ListaEnlazada<>();
        this.catalogoAtracciones = new ArbolBinarioBusqueda();
        this.mapaParque = new Grafo();
        this.tiquetesEmitidos = new ListaEnlazada<>();
    }

    /**
     * Inicializa el parque cargando datos desde archivos JSON.
     * Si falla la carga, usa datos de prueba hardcoded como fallback.
     * Método ejecutado automáticamente después de la construcción del bean.
     */
    @PostConstruct
    public void init() {
        boolean cargaExitosa = cargarDatosDesdeJSON();
        
        if (!cargaExitosa) {
            System.out.println("⚠️ Falló la carga desde JSON. Usando datos hardcoded como fallback...");
            inicializacionHardcoded();
        } else {
            System.out.println("✅ Datos cargados exitosamente desde archivos JSON.");
        }
    }
    
    /**
     * Intenta cargar zonas, atracciones y senderos desde archivos JSON.
     * 
     * @return true si la carga fue exitosa, false en caso contrario
     */
    private boolean cargarDatosDesdeJSON() {
        try {
            // Cargar zonas
            List<Zona> zonasCargadas = datosService.cargarZonas();
            if (zonasCargadas == null || zonasCargadas.isEmpty()) {
                return false;
            }
            
            // Cargar atracciones
            List<Atraccion> atraccionesCargadas = datosService.cargarAtracciones();
            if (atraccionesCargadas == null || atraccionesCargadas.isEmpty()) {
                return false;
            }

            // Alimentar el ABB y la lista global
            for (Atraccion atr : atraccionesCargadas) {
                catalogoAtracciones.insertar(atr);
                this.todasLasAtracciones.agregar(atr);
            }
            
            // Cargar senderos y alimentar el Grafo
            List<Sender> senderosCargados = datosService.cargarSenderos();
            for (Sender sender : senderosCargados) {
                mapaParque.agregarArista(sender.getOrigen(), sender.getDestino(), sender.getPeso(), true);
            }
            
            // Asociar atracciones a zonas (asignamos primero a la primera zona como ejemplo)
            Zona zonaInicial = zonasCargadas.get(0);
            for (Atraccion atr : atraccionesCargadas) {
                zonaInicial.agregarAtraccion(atr);
            }
            
            // Agregar zonas al parque
            for (Zona zona : zonasCargadas) {
                agregarZona(zona);
            }
            
            // Cargar usuarios (visitantes)
            List<Visitante> usuariosCargados = datosService.cargarUsuarios();
            for (Visitante v : usuariosCargados) {
                this.usuarios.agregar(v);
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Error al cargar datos desde JSON: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Recarga los datos desde los archivos JSON.
     * Útil para ser llamado desde el frontend.
     * 
     * @return true si la carga fue exitosa, false en caso contrario
     */
    public boolean recargarDatos() {
        // Limpiar datos actuales
        this.zonas = new ListaEnlazada<>();
        this.usuarios = new ListaEnlazada<>();
        this.todasLasAtracciones = new ListaEnlazada<>();
        this.catalogoAtracciones = new ArbolBinarioBusqueda();
        this.mapaParque = new Grafo();
        
        // Intentar cargar desde JSON
        return cargarDatosDesdeJSON();
    }

    /**
     * Inicialización con datos hardcoded (fallback).
     * Solo se usa si falla la carga desde JSON.
     */
    private void inicializacionHardcoded() {
        Zona zonaA = new Zona("Z1", "Zona Extrema", "Norte", 500);
        Atraccion a1 = new Atraccion("A1", "Montaña Rusa X", "Mecánica", 20, 1.4f, 12, 5000, 100, 100);
        Atraccion a2 = new Atraccion("A2", "Caída Libre", "Mecánica", 12, 1.5f, 15, 7000, 300, 150);
        
        agregarZona(zonaA);
        registrarNuevaAtraccion(zonaA, a1);
        registrarNuevaAtraccion(zonaA, a2);

        // Alimentar ABB
        catalogoAtracciones.insertar(a1);
        catalogoAtracciones.insertar(a2);

        Zona zonaB = new Zona("Z2", "Zona Acuática", "Sur", 300);
        Atraccion a3 = new Atraccion("A3", "Tobogán Gigante", "Acuática", 5, 1.2f, 8, 3000, 200, 300);
        
        agregarZona(zonaB);
        registrarNuevaAtraccion(zonaB, a3);
        catalogoAtracciones.insertar(a3);
    }

    /**
     * Calcula la ruta más corta entre dos atracciones usando Dijkstra.
     * 
     * @param origenId ID de origen
     * @param destinoId ID de destino
     * @return Resultado de la ruta
     */
    public ResultadoRuta obtenerRutaOptima(String origenId, String destinoId) {
        return mapaParque.calcularRutaOptima(origenId, destinoId);
    }

    /**
     * Gestiona el ingreso de un visitante a la fila de una atracción.
     * 
     * @param visitanteId ID del visitante
     * @param atraccionId ID de la atracción
     * @param tipoTiquete Tipo de tiquete (determina prioridad)
     * @return Mensaje de confirmación o error
     */
    public String unirseAFila(String visitanteId, String atraccionId, TipoTiquete tipoTiquete) {
        // Buscar visitante
        Visitante visitante = null;
        for (Usuario u : usuarios) {
            if (u.getId().equals(visitanteId) && u instanceof Visitante) {
                visitante = (Visitante) u;
                break;
            }
        }
        
        if (visitante == null) return "❌ Error: Visitante no encontrado.";

        // Buscar atracción usando ABB
        Atraccion atraccion = catalogoAtracciones.buscarPorId(atraccionId);
        if (atraccion == null) return "❌ Error: Atracción no encontrada.";

        // Validar si puede entrar
        if (!visitante.puedeEntrar(atraccion)) {
            return "⚠️ El visitante no cumple con los requisitos mínimos.";
        }

        // Determinar prioridad
        int prioridad = (tipoTiquete == TipoTiquete.FAST_PASS) ? 1 : 2;

        // Crear entrada y unir a la cola de la atracción
        Tiquete tiquete = new Tiquete("T-" + System.currentTimeMillis(), tipoTiquete, atraccion.getCostoAdicional(), "Registro en fila", visitante);
        EntradaCola entrada = new EntradaCola(visitante, tiquete, prioridad, new java.util.Date(), atraccion);
        
        // La clase Atraccion debe tener una instancia de ColaPrioridad
        if (atraccion.getColaEspera() == null) {
            atraccion.setColaEspera(new ColaPrioridad());
        }
        
        atraccion.getColaEspera().insertar(entrada);

        return "✅ " + visitante.getNombre() + " se ha unido a la fila de " + atraccion.getNombre() + " con prioridad " + (prioridad == 1 ? "ALTA (Fast-Pass)" : "NORMAL");
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
        zonas.agregar(zona);
        // Sincronizar las atracciones existentes de la zona con la lista global del parque
        for (Atraccion atraccion : zona.getListaAtracciones()) {
            boolean existe = false;
            for (Atraccion a : todasLasAtracciones) {
                if (a.getId().equals(atraccion.getId())) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                todasLasAtracciones.agregar(atraccion);
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
        
        boolean existe = false;
        for (Usuario u : usuarios) {
            if (u.getId().equals(usuario.getId())) {
                existe = true;
                break;
            }
        }
        
        if (!existe) {
            usuarios.agregar(usuario);
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
        
        boolean existe = false;
        for (Atraccion a : todasLasAtracciones) {
            if (a.getId().equals(atraccion.getId())) {
                existe = true;
                break;
            }
        }
        
        if (!existe) {
            todasLasAtracciones.agregar(atraccion);
            catalogoAtracciones.insertar(atraccion);
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
     * Procesa al siguiente visitante en la cola de una atracción.
     * Lo desencola, descuenta su saldo y registra la visita.
     * 
     * @param atraccionId ID de la atracción
     * @return Mensaje con el resultado del procesamiento
     */
    public String procesarSiguiente(String atraccionId) {
        Atraccion atraccion = catalogoAtracciones.buscarPorId(atraccionId);
        if (atraccion == null) return "❌ Error: Atracción no encontrada.";

        ColaPrioridad cola = atraccion.getColaEspera();
        if (cola == null || cola.isEmpty()) {
            return "⚠️ La cola de " + atraccion.getNombre() + " está vacía.";
        }

        EntradaCola entrada = cola.eliminar();
        if (entrada == null) return "⚠️ No hay visitantes en la cola.";

        Visitante visitante = entrada.getVisitante();
        visitante.entrarAAtraccion(atraccion);

        return "✅ " + visitante.getNombre() + " ingresó a " + atraccion.getNombre()
            + " | Prioridad: " + (entrada.getPrioridad() == 1 ? "Fast-Pass" : "General")
            + " | Saldo restante: $" + visitante.getSaldoVirtual();
    }

    /**
     * Busca un visitante por su ID en la lista de usuarios.
     */
    private Visitante buscarVisitantePorId(String visitanteId) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(visitanteId) && u instanceof Visitante) {
                return (Visitante) u;
            }
        }
        return null;
    }

    /**
     * Compra un ticket para un visitante según el tipo seleccionado.
     * Precios: GENERAL=$20,000, FAST_PASS=$50,000, FAMILIAR=$45,000.
     * FAMILIAR requiere edad >= 18 (adulto responsable).
     * 
     * @param visitanteId ID del visitante
     * @param tipo Tipo de tiquete
     * @return Mensaje de confirmación o error
     */
    public String comprarTicket(String visitanteId, TipoTiquete tipo) {
        Visitante visitante = buscarVisitantePorId(visitanteId);
        if (visitante == null) return "❌ Error: Visitante no encontrado.";

        int precio;
        String descripcion;

        switch (tipo) {
            case FAST_PASS:
                precio = 50000;
                descripcion = "Fast-Pass: acceso prioritario a filas";
                break;
            case FAMILIAR:
                if (visitante.getEdad() < 18) {
                    return "⚠️ El tiquete Familiar requiere un adulto responsable (edad >= 18).";
                }
                precio = 45000;
                descripcion = "Familiar: descuento para grupos (hasta 4 personas)";
                break;
            default: // GENERAL
                precio = 20000;
                descripcion = "General: acceso estándar al parque";
                break;
        }

        if (visitante.getSaldoVirtual() < precio) {
            return "⚠️ Saldo insuficiente. Tienes $" + visitante.getSaldoVirtual()
                + " y el tiquete " + tipo + " cuesta $" + precio + ".";
        }

        visitante.setSaldoVirtual(visitante.getSaldoVirtual() - precio);
        Tiquete tiquete = new Tiquete(
            "TKT-" + System.currentTimeMillis(), tipo, precio, descripcion, visitante
        );
        tiquetesEmitidos.agregar(tiquete);

        return "✅ " + visitante.getNombre() + " compró tiquete " + tipo
            + " ($" + precio + "). Saldo restante: $" + visitante.getSaldoVirtual();
    }

    /**
     * Obtiene los tiquetes comprados por un visitante.
     * 
     * @param visitanteId ID del visitante
     * @return Arreglo de tiquetes del visitante
     */
    public Tiquete[] getTiquetesVisitante(String visitanteId) {
        ListaEnlazada<Tiquete> resultado = new ListaEnlazada<>();
        for (Tiquete t : tiquetesEmitidos) {
            if (t.getPropietario().getId().equals(visitanteId)) {
                resultado.agregar(t);
            }
        }
        return resultado.toArray(Tiquete.class);
    }

    /**
     * Agrega una atracción a los favoritos de un visitante.
     * 
     * @param visitanteId ID del visitante
     * @param atraccionId ID de la atracción
     * @return Mensaje de confirmación o error
     */
    public String agregarFavorito(String visitanteId, String atraccionId) {
        Visitante visitante = buscarVisitantePorId(visitanteId);
        if (visitante == null) return "❌ Error: Visitante no encontrado.";

        Atraccion atraccion = catalogoAtracciones.buscarPorId(atraccionId);
        if (atraccion == null) return "❌ Error: Atracción no encontrada.";

        boolean agregado = visitante.getFavoritos().agregarFavorito(atraccion);
        if (!agregado) {
            return "⚠️ " + atraccion.getNombre() + " ya está en tus favoritos.";
        }
        return "✅ " + atraccion.getNombre() + " agregada a favoritos.";
    }

    /**
     * Elimina una atracción de los favoritos de un visitante.
     * 
     * @param visitanteId ID del visitante
     * @param atraccionId ID de la atracción
     * @return Mensaje de confirmación o error
     */
    public String eliminarFavorito(String visitanteId, String atraccionId) {
        Visitante visitante = buscarVisitantePorId(visitanteId);
        if (visitante == null) return "❌ Error: Visitante no encontrado.";

        Atraccion atraccion = catalogoAtracciones.buscarPorId(atraccionId);
        if (atraccion == null) return "❌ Error: Atracción no encontrada.";

        boolean eliminado = visitante.getFavoritos().eliminarFavorito(atraccion);
        if (!eliminado) {
            return "⚠️ " + atraccion.getNombre() + " no está en tus favoritos.";
        }
        return "✅ " + atraccion.getNombre() + " eliminada de favoritos.";
    }

    /**
     * Obtiene las atracciones favoritas de un visitante.
     * 
     * @param visitanteId ID del visitante
     * @return Arreglo de atracciones favoritas
     */
    public Atraccion[] getFavoritos(String visitanteId) {
        Visitante visitante = buscarVisitantePorId(visitanteId);
        if (visitante == null) return new Atraccion[0];
        return visitante.getFavoritos().obtenerFavoritos().toArray(Atraccion.class);
    }

    /**
     * Obtiene el arreglo de zonas del parque.
     * 
     * @return Arreglo de zonas
     */
    public Zona[] getZonas() {
        return zonas.toArray(Zona.class);
    }

    /**
     * Obtiene el arreglo de usuarios registrados.
     * 
     * @return Arreglo de usuarios
     */
    public Usuario[] getUsuarios() {
        return usuarios.toArray(Usuario.class);
    }

    /**
     * Obtiene el catálogo completo de atracciones del parque.
     * 
     * @return Arreglo de todas las atracciones
     */
    public Atraccion[] getTodasLasAtracciones() {
        return todasLasAtracciones.toArray(Atraccion.class);
    }

    public ListaEnlazada<Atraccion> getListaAtracciones() {
        return todasLasAtracciones;
    }

    public ListaEnlazada<Zona> getListaZonas() {
        return zonas;
    }
}

