package co.edu.uniquindio.techpark.Model;


import co.edu.uniquindio.techpark.service.Sender;

/**
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

    public ListaEnlazada<Sender> obtenerTodosLosSenderos() {
        ListaEnlazada<Sender> resultado = new ListaEnlazada<>();
        ListaEnlazada<String> visitados = new ListaEnlazada<>();
        for (NodoGrafo nodo : nodos) {
            for (Arista arista : nodo.adyacentes) {
                String clave = nodo.atraccionId.compareTo(arista.getDestinoId()) < 0
                    ? nodo.atraccionId + "-" + arista.getDestinoId()
                    : arista.getDestinoId() + "-" + nodo.atraccionId;
                if (!contiene(visitados, clave)) {
                    visitados.agregar(clave);
                    resultado.agregar(new Sender(nodo.atraccionId, arista.getDestinoId(), (int) arista.getPeso()));
                }
            }
        }
        return resultado;
    }

    private boolean contiene(ListaEnlazada<String> lista, String valor) {
        for (String s : lista) {
            if (s.equals(valor)) return true;
        }
        return false;
    }

    /**
     * Implementación del Algoritmo de Dijkstra para encontrar la ruta más corta.
     * @param origenId ID de la atracción inicial
     * @param destinoId ID de la atracción final
     * @return ResultadoRuta con el camino y el peso total
     */
    public ResultadoRuta calcularRutaOptima(String origenId, String destinoId) {
        ListaEnlazada<String> todosLosIds = obtenerTodosLosIds();
        int n = todosLosIds.size();
        
        // Mapas manuales usando arreglos e IDs
        double[] distancias = new double[n];
        String[] predecesores = new String[n];
        boolean[] visitados = new boolean[n];
        String[] ids = new String[n];

        // Inicialización
        int idx = 0;
        int startIdx = -1;
        int endIdx = -1;
        for (String id : todosLosIds) {
            ids[idx] = id;
            distancias[idx] = Double.MAX_VALUE;
            predecesores[idx] = null;
            visitados[idx] = false;
            if (id.equals(origenId)) startIdx = idx;
            if (id.equals(destinoId)) endIdx = idx;
            idx++;
        }

        if (startIdx == -1 || endIdx == -1) return null;

        distancias[startIdx] = 0;

        for (int i = 0; i < n; i++) {
            // Buscar el nodo no visitado con la distancia mínima
            int u = -1;
            double minDist = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visitados[j] && distancias[j] < minDist) {
                    minDist = distancias[j];
                    u = j;
                }
            }

            if (u == -1) break;
            visitados[u] = true;

            if (u == endIdx) break; // Ya llegamos al destino

            // Relajar aristas
            ListaEnlazada<Arista> adyacentes = obtenerAdyacentes(ids[u]);
            for (Arista arista : adyacentes) {
                int v = -1;
                for (int k = 0; k < n; k++) {
                    if (ids[k].equals(arista.getDestinoId())) {
                        v = k;
                        break;
                    }
                }

                if (v != -1 && !visitados[v]) {
                    double nuevaDist = distancias[u] + arista.getPeso();
                    if (nuevaDist < distancias[v]) {
                        distancias[v] = nuevaDist;
                        predecesores[v] = ids[u];
                    }
                }
            }
        }

        // Reconstruir el camino
        if (distancias[endIdx] == Double.MAX_VALUE) return null;

        ListaEnlazada<String> caminoInverso = new ListaEnlazada<>();
        String paso = ids[endIdx];
        while (paso != null) {
            caminoInverso.agregar(paso);
            int pIdx = -1;
            for(int m=0; m<n; m++) if(ids[m].equals(paso)) pIdx = m;
            paso = (pIdx != -1) ? predecesores[pIdx] : null;
        }

        // Invertir el camino
        ListaEnlazada<String> caminoFinal = new ListaEnlazada<>();
        String[] arrCamino = caminoInverso.toArray(String.class);
        for (int i = arrCamino.length - 1; i >= 0; i--) {
            caminoFinal.agregar(arrCamino[i]);
        }

        return new ResultadoRuta(caminoFinal, distancias[endIdx]);
    }
}
