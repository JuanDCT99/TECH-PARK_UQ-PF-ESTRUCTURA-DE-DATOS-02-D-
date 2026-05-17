package co.edu.uniquindio.techpark.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FavoritosSet {
    private Visitante visitante;
    private ListaEnlazada<Atraccion> favoritos;

    /**
     * Constructor del conjunto de favoritos.
     * 
     * @param visitante Visitante propietario de los favoritos
     */
    public FavoritosSet(Visitante visitante) {
        this.visitante = visitante;
        this.favoritos = new ListaEnlazada<>();
    }

    /**
     * Obtiene el visitante propietario de los favoritos.
     * 
     * @return Visitante
     */
    @JsonIgnore
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
        if (esFavorito(atraccion)) {
            return false; // Ya está en favoritos
        }
        favoritos.agregar(atraccion);
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
        return favoritos.eliminar(atraccion);
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
     * Obtiene la lista propia de todas las atracciones favoritas.
     * 
     * @return ListaEnlazada de atracciones favoritas
     */
    public ListaEnlazada<Atraccion> obtenerFavoritos() {
        return favoritos;
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
