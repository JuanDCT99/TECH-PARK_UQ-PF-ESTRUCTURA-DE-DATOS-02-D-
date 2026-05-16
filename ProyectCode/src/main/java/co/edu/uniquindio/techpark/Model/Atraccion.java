package co.edu.uniquindio.techpark.Model;

/**
 * Representa una atracción del parque TECH-PARK UQ.
 * Posee lógica de mantenimiento automático y gestión de colas de prioridad.
 */
public class Atraccion {
    private String id;
    private String nombre;
    private String tipo;
    private int capacidadMax;
    private float alturaMin;
    private int edadMin;
    private int costoAdicional;
    private int contadorVisitantes;
    private int tiempoEspera;
    private EstadoAtraccion estado;
    private String motivoCierre;
    private ColaPrioridad colaEspera;
    private int x; // Coordenada X
    private int y; // Coordenada Y

    /**
     * Constructor sin argumentos necesario para deserialización JSON con Jackson.
     */
    public Atraccion() {
        this.contadorVisitantes = 0;
        this.tiempoEspera = 0;
        this.estado = EstadoAtraccion.ACTIVA;
        this.colaEspera = new ColaPrioridad();
    }

    /**
     * Constructor completo de Atraccion.
     */
    public Atraccion(String id, String nombre, String tipo, int capacidadMax, float alturaMin, int edadMin, int costoAdicional, int x, int y) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMax = capacidadMax;
        this.alturaMin = alturaMin;
        this.edadMin = edadMin;
        this.costoAdicional = costoAdicional;
        this.contadorVisitantes = 0;
        this.tiempoEspera = 0;
        this.estado = EstadoAtraccion.ACTIVA;
        this.colaEspera = new ColaPrioridad();
        this.x = x;
        this.y = y;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getContadorVisitantes() { return contadorVisitantes; }
    public void setContadorVisitantes(int contadorVisitantes) { this.contadorVisitantes = contadorVisitantes; }

    public EstadoAtraccion getEstado() { return estado; }
    public void setEstado(EstadoAtraccion estado) { this.estado = estado; }

    public float getAlturaMin() { return alturaMin; }
    public void setAlturaMin(float alturaMin) { this.alturaMin = alturaMin; }

    public int getEdadMin() { return edadMin; }
    public void setEdadMin(int edadMin) { this.edadMin = edadMin; }

    public int getCostoAdicional() { return costoAdicional; }
    public void setCostoAdicional(int costoAdicional) { this.costoAdicional = costoAdicional; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCapacidadMax() { return capacidadMax; }
    public void setCapacidadMax(int capacidadMax) { this.capacidadMax = capacidadMax; }

    public int getTiempoEspera() { return tiempoEspera; }
    public void setTiempoEspera(int tiempoEspera) { this.tiempoEspera = tiempoEspera; }

    public ColaPrioridad getColaEspera() { return colaEspera; }
    public void setColaEspera(ColaPrioridad colaEspera) { this.colaEspera = colaEspera; }

    public String getMotivoCierre() { return motivoCierre; }
    public void setMotivoCierre(String motivoCierre) { this.motivoCierre = motivoCierre; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    /**
     * Registra una visita y activa mantenimiento si llega a 500.
     */
    public void registrarVisita() {
        this.contadorVisitantes++;
        if (this.contadorVisitantes >= 500) {
            this.estado = EstadoAtraccion.EN_MANTENIMIENTO;
            this.motivoCierre = "Mantenimiento preventivo automático (500 visitas)";
        }
    }

    /**
     * Registra una revisión técnica satisfactoria.
     */
    public void registrarRevisionTecnica() {
        this.contadorVisitantes = 0;
        this.estado = EstadoAtraccion.ACTIVA;
        this.motivoCierre = null;
    }

    @Override
    public String toString() {
        return "Atraccion [nombre=" + nombre + ", estado=" + estado + ", visitas=" + contadorVisitantes + "]";
    }
}
