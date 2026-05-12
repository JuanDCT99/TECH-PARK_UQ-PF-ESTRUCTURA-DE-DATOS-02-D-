package co.edu.uniquindio.techpark.Model;

/**
 * Implementación de un Árbol Binario de Búsqueda (ABB) propio.
 * Se utiliza para almacenar y buscar atracciones de forma eficiente (O(log n)).
 * El criterio de ordenamiento es el ID de la atracción.
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tam;

    private static class Nodo {
        Atraccion dato;
        Nodo izquierdo, derecho;

        Nodo(Atraccion dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tam = 0;
    }

    /**
     * Inserta una nueva atracción en el árbol.
     * @param atraccion Atracción a insertar
     */
    public void insertar(Atraccion atraccion) {
        if (atraccion == null) return;
        raiz = insertarRecursivo(raiz, atraccion);
        tam++;
    }

    private Nodo insertarRecursivo(Nodo actual, Atraccion atraccion) {
        if (actual == null) {
            return new Nodo(atraccion);
        }

        // Comparar por ID
        int comparacion = atraccion.getId().compareTo(actual.dato.getId());

        if (comparacion < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, atraccion);
        } else if (comparacion > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, atraccion);
        }
        // Si el ID es igual, no se inserta (evita duplicados)

        return actual;
    }

    /**
     * Busca una atracción por su ID.
     * @param id Identificador de la atracción
     * @return La atracción encontrada o null
     */
    public Atraccion buscarPorId(String id) {
        return buscarRecursivo(raiz, id);
    }

    private Atraccion buscarRecursivo(Nodo actual, String id) {
        if (actual == null) {
            return null;
        }

        int comparacion = id.compareTo(actual.dato.getId());

        if (comparacion == 0) {
            return actual.dato;
        }

        return comparacion < 0 
            ? buscarRecursivo(actual.izquierdo, id) 
            : buscarRecursivo(actual.derecho, id);
    }

    /**
     * Busca una atracción por su nombre (búsqueda exhaustiva si no es la clave).
     * Nota: En un ABB ordenado por ID, la búsqueda por nombre requiere recorrer el árbol.
     * @param nombre Nombre de la atracción
     * @return La atracción encontrada o null
     */
    public Atraccion buscarPorNombre(String nombre) {
        return buscarPorNombreRecursivo(raiz, nombre);
    }

    private Atraccion buscarPorNombreRecursivo(Nodo actual, String nombre) {
        if (actual == null) return null;

        if (actual.dato.getNombre().equalsIgnoreCase(nombre)) {
            return actual.dato;
        }

        Atraccion izq = buscarPorNombreRecursivo(actual.izquierdo, nombre);
        if (izq != null) return izq;

        return buscarPorNombreRecursivo(actual.derecho, nombre);
    }

    /**
     * Retorna una lista enlazada con todas las atracciones (recorrido In-Order).
     * @return ListaEnlazada de atracciones
     */
    public ListaEnlazada<Atraccion> toLista() {
        ListaEnlazada<Atraccion> lista = new ListaEnlazada<>();
        toListaInOrder(raiz, lista);
        return lista;
    }

    private void toListaInOrder(Nodo actual, ListaEnlazada<Atraccion> lista) {
        if (actual != null) {
            toListaInOrder(actual.izquierdo, lista);
            lista.agregar(actual.dato);
            toListaInOrder(actual.derecho, lista);
        }
    }

    public int size() {
        return tam;
    }

    public boolean isEmpty() {
        return tam == 0;
    }
}
