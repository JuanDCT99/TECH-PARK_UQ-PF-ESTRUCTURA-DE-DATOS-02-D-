package co.edu.uniquindio.techpark.Model;

/**
 * Implementación de un Grafo propio (Lista de Adyacencia).
 * Representa el mapa físico del parque TECH-PARK UQ.
 * Nodos: Atracciones.
 * Aristas: Senderos con peso (distancia/tiempo).
 */
public class Grafo {

    private ListaEnlazada<NodoGrafo> nodos;

    private static class NodoGrafo {
        String atraccionId;
        ListaEnlazada<Arista> adyacentes;

        NodoGrafo(String atraccionId) {
            this.atraccionId = atraccionId;
            this.adyacentes = new ListaEnlazada<>();
        }
    }

    public Grafo() {
        this.nodos = new ListaEnlazada<>();
    }

    /**
     * Agrega un nodo (atracción) al grafo si no existe.
     * @param atraccionId ID de la atracción
     */
    public void agregarNodo(String atraccionId) {
        if (buscarNodo(atraccionId) == null) {
            nodos.agregar(new NodoGrafo(atraccionId));
        }
    }

    /**
     * Agrega una arista (sendero) entre dos atracciones.
     * @param origenId ID de la atracción origen
     * @param destinoId ID de la atracción destino
     * @param peso Peso del sendero (distancia/tiempo)
     * @param bidireccional true si el sendero es de doble vía
     */
    public void agregarArista(String origenId, String destinoId, double peso, boolean bidireccional) {
        agregarNodo(origenId);
        agregarNodo(destinoId);

        NodoGrafo origen = buscarNodo(origenId);
        origen.adyacentes.agregar(new Arista(destinoId, peso));

        if (bidireccional) {
            NodoGrafo destino = buscarNodo(destinoId);
            destino.adyacentes.agregar(new Arista(origenId, peso));
        }
    }

    private NodoGrafo buscarNodo(String atraccionId) {
        for (NodoGrafo nodo : nodos) {
            if (nodo.atraccionId.equals(atraccionId)) {
                return nodo;
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de aristas adyacentes a un nodo.
     * @param atraccionId ID de la atracción
     * @return ListaEnlazada de aristas adyacentes
     */
    public ListaEnlazada<Arista> obtenerAdyacentes(String atraccionId) {
        NodoGrafo nodo = buscarNodo(atraccionId);
        return (nodo != null) ? nodo.adyacentes : new ListaEnlazada<>();
    }

    /**
     * Obtiene todos los IDs de las atracciones registradas en el grafo.
     * @return ListaEnlazada de IDs
     */
    public ListaEnlazada<String> obtenerTodosLosIds() {
        ListaEnlazada<String> ids = new ListaEnlazada<>();
        for (NodoGrafo nodo : nodos) {
            ids.agregar(nodo.atraccionId);
        }
        return ids;
    }

    public int totalNodos() {
        return nodos.size();
    }
}
