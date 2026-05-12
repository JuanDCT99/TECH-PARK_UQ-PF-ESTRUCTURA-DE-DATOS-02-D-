package co.edu.uniquindio.techpark.Model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una Lista Enlazada Simple propia.
 * Se utiliza para gestionar el historial de visitas y notificaciones de forma dinámica.
 * 
 * @param <T> Tipo de dato almacenado en la lista
 */
public class ListaEnlazada<T> implements Iterable<T> {

    private Nodo<T> cabeza;
    private int tam;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public ListaEnlazada() {
        this.cabeza = null;
        this.tam = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * @param dato Elemento a agregar
     */
    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        tam++;
    }

    /**
     * Obtiene el elemento en una posición específica.
     * @param indice Índice del elemento
     * @return El dato en la posición indicada
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tam) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> aux = cabeza;
        for (int i = 0; i < indice; i++) {
            aux = aux.siguiente;
        }
        return aux.dato;
    }

    /**
     * Elimina la primera ocurrencia de un elemento.
     * @param dato Elemento a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminar(T dato) {
        if (cabeza == null) return false;

        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tam--;
            return true;
        }

        Nodo<T> aux = cabeza;
        while (aux.siguiente != null && !aux.siguiente.dato.equals(dato)) {
            aux = aux.siguiente;
        }

        if (aux.siguiente != null) {
            aux.siguiente = aux.siguiente.siguiente;
            tam--;
            return true;
        }

        return false;
    }
public int size() {
    return tam;
}

public boolean isEmpty() {
    return tam == 0;
}

/**
 * Convierte la lista en un arreglo convencional.
 * Útil para serialización JSON en controladores REST.
 * 
 * @param clazz Clase de los elementos
 * @return Arreglo con los elementos de la lista
 */
@SuppressWarnings("unchecked")
public T[] toArray(Class<T> clazz) {
    T[] arreglo = (T[]) java.lang.reflect.Array.newInstance(clazz, tam);
    Nodo<T> aux = cabeza;
    for (int i = 0; i < tam; i++) {
        arreglo[i] = aux.dato;
        aux = aux.siguiente;
    }
    return arreglo;
}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }

    /**
     * Verifica si un elemento existe en la lista.
     * @param dato Elemento a buscar
     * @return true si existe, false en caso contrario
     */
    public boolean contiene(T dato) {
        if (dato == null) return false;
        for (T elemento : this) {
            if (elemento.equals(dato)) {
                return true;
            }
        }
        return false;
    }
}
