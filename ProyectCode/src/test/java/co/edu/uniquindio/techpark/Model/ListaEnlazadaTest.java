package co.edu.uniquindio.techpark.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class ListaEnlazadaTest {

    @Test
    void testAgregarYObtener() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");
        assertEquals(3, lista.size());
        assertEquals("A", lista.obtener(0));
        assertEquals("B", lista.obtener(1));
        assertEquals("C", lista.obtener(2));
    }

    @Test
    void testEliminar() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");

        assertTrue(lista.eliminar("B"));
        assertEquals(2, lista.size());
        assertEquals("A", lista.obtener(0));
        assertEquals("C", lista.obtener(1));

        assertTrue(lista.eliminar("A"));
        assertEquals(1, lista.size());
        assertEquals("C", lista.obtener(0));

        assertFalse(lista.eliminar("X"));
    }

    @Test
    void testIterar() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("X");
        lista.agregar("Y");
        StringBuilder sb = new StringBuilder();
        for (String s : lista) {
            sb.append(s);
        }
        assertEquals("XY", sb.toString());
    }

    @Test
    void testToArray() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("P");
        lista.agregar("Q");
        String[] arr = lista.toArray(String.class);
        assertArrayEquals(new String[]{"P", "Q"}, arr);
    }

    @Test
    void testListaVacia() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        assertTrue(lista.isEmpty());
        assertEquals(0, lista.size());
        assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(0));
    }

    @Test
    void testEliminarEnUnicoElemento() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("unico");
        assertTrue(lista.eliminar("unico"));
        assertTrue(lista.isEmpty());
    }
}
