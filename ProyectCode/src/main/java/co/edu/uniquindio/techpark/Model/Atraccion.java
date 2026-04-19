package co.edu.uniquindio.techpark.Model;

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

    public Atraccion(String id, String nombre, String tipo, int capacidadMax, float alturaMin, int edadMin, int costoAdicional) {
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
    public int getEdadMin() { return edadMin; }
    public int getCostoAdicional() { return costoAdicional; }
    
    public String getTipo() {
        return tipo;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public String getMotivoCierre() { return motivoCierre; }
    public void setMotivoCierre(String motivoCierre) { this.motivoCierre = motivoCierre; }

    // Lógica básica pedida: Bloqueo por mantenimiento
    public void registrarVisita() {
        this.contadorVisitantes++;
        if (this.contadorVisitantes >= 500) {
            this.estado = EstadoAtraccion.EN_MANTENIMIENTO;
            this.motivoCierre = "Mantenimiento preventivo automático (500 visitas)";
        }
    }

    @Override
    public String toString() {
        return "Atraccion [nombre=" + nombre + ", estado=" + estado + ", visitas=" + contadorVisitantes + "]";
    }
}