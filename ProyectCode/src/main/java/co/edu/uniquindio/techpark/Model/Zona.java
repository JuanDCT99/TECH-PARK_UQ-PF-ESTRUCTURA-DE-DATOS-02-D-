package co.edu.uniquindio.techpark.Model;

/**
 * Representa una zona del parque TECH-PARK UQ.
 * Agrupa atracciones y operadores asignados.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
public class Zona {
    private String id;
    private String nombre;
    private String descripcion;
    private int capacidadMaxima;
    private int visitantesActuales;
    private ListaEnlazada<Atraccion> listaAtracciones;
    private ListaEnlazada<Operador> listaOperadores;

    /**
     * Constructor sin argumentos necesario para deserialización JSON con Jackson.
     */
    public Zona() {
        this.listaAtracciones = new ListaEnlazada<>();
        this.listaOperadores = new ListaEnlazada<>();
        this.visitantesActuales = 0;
    }

    /**
     * Constructor de Zona.
     * 
     * @param id Identificador único de la zona
     * @param nombre Nombre de la zona
     * @param descripcion Descripción o ubicación (Norte, Sur, etc.)
     * @param capacidadMaxima Capacidad máxima de visitantes simultáneos
     * @throws IllegalArgumentException Si id, nombre o descripción son null/vacíos, o capacidad <= 0
     */
    public Zona(String id, String nombre, String descripcion, int capacidadMaxima) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la zona no puede ser null o vacío");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la zona no puede ser null o vacío");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la zona no puede ser null o vacía");
        }
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a 0");
        }
        
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.visitantesActuales = 0;
        this.listaAtracciones = new ListaEnlazada<>();
        this.listaOperadores = new ListaEnlazada<>();
    }

    /**
     * Agrega una atracción a la zona si no está duplicada.
     * 
     * @param atraccion Atracción a agregar
     * @throws IllegalArgumentException Si la atracción es null
     */
    public void agregarAtraccion(Atraccion atraccion) {
        if (atraccion == null) {
            throw new IllegalArgumentException("La atracción no puede ser null");
        }
        // Verificar duplicados por ID
        for (Atraccion a : listaAtracciones) {
            if (a.getId().equals(atraccion.getId())) {
                System.out.println("La atracción con ID " + atraccion.getId() + " ya existe en la zona " + nombre);
                return;
            }
        }
        this.listaAtracciones.agregar(atraccion);
    }

    /**
     * Asigna un operador a esta zona.
     * Verifica que no esté duplicado y establece la zona asignada en el operador.
     * 
     * @param operador Operador a asignar
     * @throws IllegalArgumentException Si el operador es null
     */
    public void asignarOperador(Operador operador) {
        if (operador == null) {
            throw new IllegalArgumentException("El operador no puede ser null");
        }
        // Verificar duplicados
        boolean existe = false;
        for (Operador o : listaOperadores) {
            if (o.getId().equals(operador.getId())) {
                existe = true;
                break;
            }
        }
        
        if (!existe) {
            this.listaOperadores.agregar(operador);
            operador.setZonaAsignada(this);
        }
    }

    /**
     * Verifica si hay espacio disponible en la zona (no se ha alcanzado el aforo).
     * 
     * @return true si hay espacio, false si se alcanzó la capacidad máxima
     */
    public boolean hayEspacio() {
        return visitantesActuales < capacidadMaxima;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único de la zona.
     * 
     * @return ID de la zona
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la zona.
     * 
     * @param id Nuevo ID
     * @throws IllegalArgumentException Si id es null o vacío
     */
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la zona no puede ser null o vacío");
        }
        this.id = id;
    }

    /**
     * Obtiene el nombre de la zona.
     * 
     * @return Nombre de la zona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la zona.
     * 
     * @param nombre Nuevo nombre
     * @throws IllegalArgumentException Si nombre es null o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la zona no puede ser null o vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la zona.
     * 
     * @return Descripción o ubicación
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la zona.
     * 
     * @param descripcion Nueva descripción
     * @throws IllegalArgumentException Si descripción es null o vacía
     */
    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la zona no puede ser null o vacía");
        }
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la capacidad máxima de visitantes simultáneos.
     * 
     * @return Capacidad máxima
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Establece la capacidad máxima de visitantes.
     * 
     * @param capacidadMaxima Nueva capacidad (debe ser > 0)
     * @throws IllegalArgumentException Si capacidad <= 0
     */
    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a 0");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Obtiene el número actual de visitantes en la zona.
     * 
     * @return Visitantes actuales
     */
    public int getVisitantesActuales() {
        return visitantesActuales;
    }

    /**
     * Establece el número actual de visitantes en la zona.
     * 
     * @param visitantesActuales Nuevo número de visitantes
     * @throws IllegalArgumentException Si visitantes < 0 o > capacidadMaxima
     */
    public void setVisitantesActuales(int visitantesActuales) {
        if (visitantesActuales < 0) {
            throw new IllegalArgumentException("El número de visitantes no puede ser negativo");
        }
        if (visitantesActuales > capacidadMaxima) {
            throw new IllegalArgumentException("El número de visitantes no puede superar la capacidad máxima");
        }
        this.visitantesActuales = visitantesActuales;
    }

    /**
     * Obtiene la lista de atracciones en la zona propia.
     * 
     * @return ListaEnlazada de atracciones
     */
    public ListaEnlazada<Atraccion> getListaAtracciones() {
        return listaAtracciones;
    }

    /**
     * Obtiene la lista de operadores asignados a la zona propia.
     * 
     * @return ListaEnlazada de operadores
     */
    public ListaEnlazada<Operador> getListaOperadores() {
        return listaOperadores;
    }

    @Override
    public String toString() {
        return "Zona [nombre=" + nombre + ", atracciones=" + listaAtracciones.size() + 
               ", aforo=" + visitantesActuales + "/" + capacidadMaxima + "]";
    }
}