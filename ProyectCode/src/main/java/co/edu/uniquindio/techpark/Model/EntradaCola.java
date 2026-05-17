package co.edu.uniquindio.techpark.Model;

import java.util.Date;
import java.time.Instant;

/**
 * Representa una entrada en la cola de espera de una atracción.
 * Almacena la información del visitante, su tiquete, prioridad y hora de ingreso.
 * 
 * La prioridad se define por el tipo de tiquete:
 * - Prioridad 1: Fast-Pass
 * - Prioridad 2: General
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
public class EntradaCola {
    private Visitante visitante;
    private Tiquete tiquete;
    private int prioridad;
    private Date horaIngreso;
    private Atraccion atraccion;

    /**
     * Constructor de EntradaCola.
     * 
     * @param visitante Visitante que entra a la cola
     * @param tiquete Tiquete del visitante (determina prioridad)
     * @param prioridad Nivel de prioridad (1=Fast-Pass, 2=General)
     * @param horaIngreso Hora de ingreso a la cola
     * @param atraccion Atracción a la cual se hace la cola
     * @throws IllegalArgumentException Si visitante, tiquete o atraccion son null, o prioridad inválida
     */
    public EntradaCola(Visitante visitante, Tiquete tiquete, int prioridad, Date horaIngreso, Atraccion atraccion) {
        if (visitante == null) {
            throw new IllegalArgumentException("El visitante no puede ser null");
        }
        if (tiquete == null) {
            throw new IllegalArgumentException("El tiquete no puede ser null");
        }
        if (atraccion == null) {
            throw new IllegalArgumentException("La atracción no puede ser null");
        }
        if (prioridad < 1 || prioridad > 2) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (Fast-Pass) o 2 (General)");
        }
        
        this.visitante = visitante;
        this.tiquete = tiquete;
        this.prioridad = prioridad;
        this.horaIngreso = horaIngreso != null ? horaIngreso : Date.from(Instant.now());
        this.atraccion = atraccion;
    }

    /**
     * Obtiene el visitante asociado a esta entrada en cola.
     * 
     * @return Visitante en la cola
     */
    public Visitante getVisitante() {
        return visitante;
    }

    /**
     * Establece el visitante asociado a esta entrada.
     * 
     * @param visitante Nuevo visitante
     * @throws IllegalArgumentException Si el visitante es null
     */
    public void setVisitante(Visitante visitante) {
        if (visitante == null) {
            throw new IllegalArgumentException("El visitante no puede ser null");
        }
        this.visitante = visitante;
    }

    /**
     * Obtiene el tiquete del visitante.
     * 
     * @return Tiquete que determina la prioridad
     */
    public Tiquete getTiquete() {
        return tiquete;
    }

    /**
     * Establece el tiquete del visitante.
     * 
     * @param tiquete Nuevo tiquete
     * @throws IllegalArgumentException Si el tiquete es null
     */
    public void setTiquete(Tiquete tiquete) {
        if (tiquete == null) {
            throw new IllegalArgumentException("El tiquete no puede ser null");
        }
        this.tiquete = tiquete;
    }

    /**
     * Obtiene la prioridad de esta entrada.
     * 
     * @return Prioridad (1=Fast-Pass, 2=General)
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Establece la prioridad de esta entrada.
     * 
     * @param prioridad Nueva prioridad (1 o 2)
     * @throws IllegalArgumentException Si la prioridad no es 1 o 2
     */
    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 2) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (Fast-Pass) o 2 (General)");
        }
        this.prioridad = prioridad;
    }

    /**
     * Obtiene la hora de ingreso a la cola.
     * 
     * @return Hora de ingreso
     */
    public Date getHoraIngreso() {
        return horaIngreso;
    }

    /**
     * Establece la hora de ingreso a la cola.
     * 
     * @param horaIngreso Nueva hora de ingreso
     */
    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    /**
     * Obtiene la atracción asociada a esta cola.
     * 
     * @return Atracción de la cola
     */
    public Atraccion getAtraccion() {
        return atraccion;
    }

    /**
     * Establece la atracción asociada a esta cola.
     * 
     * @param atraccion Nueva atracción
     * @throws IllegalArgumentException Si la atracción es null
     */
    public void setAtraccion(Atraccion atraccion) {
        if (atraccion == null) {
            throw new IllegalArgumentException("La atracción no puede ser null");
        }
        this.atraccion = atraccion;
    }

    @Override
    public String toString() {
        return "EntradaCola [visitante=" + visitante + ", tiquete=" + tiquete + ", prioridad=" + prioridad
                + ", horaIngreso=" + horaIngreso + ", atraccion=" + atraccion + "]";
    }

    

    
    
}
