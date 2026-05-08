package co.edu.uniquindio.techpark.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de un Set (Conjunto) para gestionar las atracciones favoritas
 * de un visitante del parque TECH-PARK UQ.
 * 
 * Esta estructura garantiza que no haya atracciones duplicadas en la lista de favoritos.
 * Es una estructura propia que NO utiliza colecciones de Java como HashSet.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
public class FavoritosSet {
    private Visitante visitante;
    private List<Atraccion> favoritos;

    /**
     * Constructor del conjunto de favoritos.
     * 
     * @param visitante Visitante propietario de los favoritos
     */
    public FavoritosSet(Visitante visitante) {
        this.visitante = visitante;
        this.favoritos = new ArrayList<>();
    }

    /**
     * Obtiene el visitante propietario de los favoritos.
     * 
     * @return Visitante
     */
    public Visitante getVisitante() {
        return visitante;
    }

    /**
     * Establece el visitante propietario de los favoritos.
     * 
     * @param visitante Nuevo visitante
     */
    public void setVisitante(Visitante visitante) {
        if (visitante == null) {
            throw new IllegalArgumentException("El visitante no puede ser null");
        }
        this.visitante = visitante;
    }

    /**
     * Agrega una atracción a la lista de favoritos si no está ya presente.
     * Garantiza la unicidad (comportamiento de Set).
     * 
     * @param atraccion Atracción a agregar a favoritos
     * @return true si se agregó, false si ya estaba en la lista
     */
    public boolean agregarFavorito(Atraccion atraccion) {
        if (atraccion == null) {
            throw new IllegalArgumentException("La atracción no puede ser null");
        }
        // Verificar unicidad
        for (Atraccion a : favoritos) {
            if (a.getId().equals(atraccion.getId())) {
                return false; // Ya está en favoritos
            }
        }
        favoritos.add(atraccion);
        return true;
    }

    /**
     * Elimina una atracción de la lista de favoritos.
     * 
     * @param atraccion Atracción a eliminar
     * @return true si se eliminó, false si no estaba en la lista
     */
    public boolean eliminarFavorito(Atraccion atraccion) {
        if (atraccion == null) {
            return false;
        }
        for (int i = 0; i < favoritos.size(); i++) {
            if (favoritos.get(i).getId().equals(atraccion.getId())) {
                favoritos.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si una atracción está en la lista de favoritos.
     * 
     * @param atraccion Atracción a verificar
     * @return true si está en favoritos, false en caso contrario
     */
    public boolean esFavorito(Atraccion atraccion) {
        if (atraccion == null) {
            return false;
        }
        for (Atraccion a : favoritos) {
            if (a.getId().equals(atraccion.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la lista de todas las atracciones favoritas.
     * 
     * @return Lista de atracciones favoritas
     */
    public List<Atraccion> obtenerFavoritos() {
        return new ArrayList<>(favoritos); // Retorna una copia para evitar modificaciones externas
    }

    /**
     * Obtiene el número de atracciones en favoritos.
     * 
     * @return Tamaño del conjunto
     */
    public int size() {
        return favoritos.size();
    }

    /**
     * Verifica si el conjunto de favoritos está vacío.
     * 
     * @return true si no hay favoritos, false en caso contrario
     */
    public boolean isEmpty() {
        return favoritos.isEmpty();
    }
}
