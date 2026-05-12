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
     * Inserta un nuevo elemento en la cola de prioridad.
     * Mantiene la propiedad de heap (min-heap basado en prioridad y tiempo).
     * 
     * @param entrada Elemento a insertar
     */
    public void insertar(EntradaCola entrada) {
        if (entrada == null) return;
        
        if (tam == elementos.length) {
            redimensionar();
        }
        
        elementos[tam] = entrada;
        subir(tam);
        tam++;
    }

    /**
     * Elimina y retorna el elemento con mayor prioridad (raíz del heap).
     * 
     * @return El elemento con mayor prioridad, o null si la cola está vacía
     */
    public EntradaCola eliminar() {
        if (isEmpty()) return null;
        
        EntradaCola raiz = elementos[0];
        elementos[0] = elementos[tam - 1];
        elementos[tam - 1] = null;
        tam--;
        
        if (tam > 0) {
            bajar(0);
        }
        
        return raiz;
    }

    /**
     * Retorna el elemento con mayor prioridad sin eliminarlo.
     * 
     * @return El elemento con mayor prioridad, o null si está vacía
     */
    public EntradaCola peek() {
        if (isEmpty()) return null;
        return elementos[0];
    }

    /**
     * Mantiene la propiedad de heap hacia arriba.
     */
    private void subir(int index) {
        int padre = (index - 1) / 2;
        
        while (index > 0 && comparar(elementos[index], elementos[padre]) < 0) {
            intercambiar(index, padre);
            index = padre;
            padre = (index - 1) / 2;
        }
    }

    /**
     * Mantiene la propiedad de heap hacia abajo.
     */
    private void bajar(int index) {
        int menor = index;
        int izq = 2 * index + 1;
        int der = 2 * index + 2;
        
        if (izq < tam && comparar(elementos[izq], elementos[menor]) < 0) {
            menor = izq;
        }
        
        if (der < tam && comparar(elementos[der], elementos[menor]) < 0) {
            menor = der;
        }
        
        if (menor != index) {
            intercambiar(index, menor);
            bajar(menor);
        }
    }

    /**
     * Compara dos entradas de cola para determinar prioridad.
     * Retorna < 0 si e1 tiene mayor prioridad que e2.
     * Retorna > 0 si e1 tiene menor prioridad que e2.
     * Retorna 0 si son iguales.
     */
    private int comparar(EntradaCola e1, EntradaCola e2) {
        // Primero comparamos la prioridad (1=Fast-Pass es mayor prioridad que 2=General)
        // En un Min-Heap, el valor más bajo sale primero.
        if (e1.getPrioridad() != e2.getPrioridad()) {
            return Integer.compare(e1.getPrioridad(), e2.getPrioridad());
        }
        
        // Si la prioridad es igual, comparamos el tiempo de ingreso (FIFO)
        return e1.getHoraIngreso().compareTo(e2.getHoraIngreso());
    }

    /**
     * Intercambia dos elementos en el arreglo.
     */
    private void intercambiar(int i, int j) {
        EntradaCola temp = elementos[i];
        elementos[i] = elementos[j];
        elementos[j] = temp;
    }

    /**
     * Duplica el tamaño del arreglo interno.
     */
    private void redimensionar() {
        EntradaCola[] nuevo = new EntradaCola[elementos.length * 2];
        System.arraycopy(elementos, 0, nuevo, 0, elementos.length);
        elementos = nuevo;
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
