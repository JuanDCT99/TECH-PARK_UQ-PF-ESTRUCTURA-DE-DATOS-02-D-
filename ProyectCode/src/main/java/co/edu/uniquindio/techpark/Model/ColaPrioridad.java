package co.edu.uniquindio.techpark.Model;

/**
 * Implementación de una Cola de Prioridad (Priority Queue) para gestionar
 * el acceso a las atracciones del parque.
 * 
 * Prioridad 1: Fast-Pass (sale primero)
 * Prioridad 2: General
 * 
 * Esta estructura de datos es propia y no utiliza colecciones de Java.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
public class ColaPrioridad {
    private EntradaCola[] elementos;
    private int tam;
    private Atraccion atraccion;

    /**
     * Constructor de la cola de prioridad.
     * Inicializa una cola vacía con capacidad inicial de 100 elementos.
     */
    public ColaPrioridad() {
        this.elementos = new EntradaCola[100];
        this.tam = 0;
    }

    /**
     * Constructor con capacidad inicial personalizada.
     * 
     * @param capacidadInicial Tamaño inicial del arreglo interno
     */
    public ColaPrioridad(int capacidadInicial) {
        this.elementos = new EntradaCola[capacidadInicial];
        this.tam = 0;
    }

    /**
     * Obtiene el arreglo de elementos en la cola.
     * @return Arreglo de EntradaCola
     */
    public EntradaCola[] getElementos() {
        return elementos;
    }

    /**
     * Establece el arreglo de elementos en la cola.
     * @param elementos Nuevo arreglo de elementos
     */
    public void setElementos(EntradaCola[] elementos) {
        this.elementos = elementos;
    }

    /**
     * Obtiene el tamaño actual de la cola.
     * @return Número de elementos en la cola
     */
    public int getTam() {
        return tam;
    }

    /**
     * Establece el tamaño de la cola.
     * @param tam Nuevo tamaño
     */
    public void setTam(int tam) {
        this.tam = tam;
    }

    /**
     * Obtiene la atracción asociada a esta cola.
     * @return Atracción de la cola
     */
    public Atraccion getAtraccion() {
        return atraccion;
    }

    /**
     * Establece la atracción asociada a esta cola.
     * @param atraccion Atracción a asociar
     */
    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    /**
     * Verifica si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return tam == 0;
    }

    /**
     * Obtiene el número de elementos en la cola.
     * @return Tamaño de la cola
     */
    public int size() {
        return tam;
    }
}
