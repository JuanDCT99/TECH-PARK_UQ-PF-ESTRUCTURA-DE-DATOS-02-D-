package co.edu.uniquindio.techpark.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArbolBinarioBusquedaTest {

    private Atraccion crearAtraccion(String id, String nombre) {
        return new Atraccion(id, nombre, "MECANICA", 50, 1.2f, 8, 0, 0, 0);
    }

    @Test
    void testInsertarYBuscarPorId() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(crearAtraccion("ATR02", "Montaña Rusa"));
        arbol.insertar(crearAtraccion("ATR01", "Rueda Gigante"));
        arbol.insertar(crearAtraccion("ATR03", "Casa del Terror"));

        assertNotNull(arbol.buscarPorId("ATR01"));
        assertNotNull(arbol.buscarPorId("ATR02"));
        assertNotNull(arbol.buscarPorId("ATR03"));
        assertNull(arbol.buscarPorId("ATR99"));
    }

    @Test
    void testBuscarPorNombre() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(crearAtraccion("ATR01", "Montaña Rusa"));
        arbol.insertar(crearAtraccion("ATR02", "Rueda Gigante"));

        assertNotNull(arbol.buscarPorNombre("Montaña Rusa"));
        assertNotNull(arbol.buscarPorNombre("Rueda Gigante"));
        assertNull(arbol.buscarPorNombre("Inexistente"));
    }

    @Test
    void testToListaInOrder() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(crearAtraccion("B", "Beta"));
        arbol.insertar(crearAtraccion("A", "Alfa"));
        arbol.insertar(crearAtraccion("C", "Gamma"));

        ListaEnlazada<Atraccion> lista = arbol.toLista();
        assertEquals(3, lista.size());
        assertEquals("A", lista.obtener(0).getId());
        assertEquals("B", lista.obtener(1).getId());
        assertEquals("C", lista.obtener(2).getId());
    }

    @Test
    void testSizeYEmpty() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        assertTrue(arbol.isEmpty());
        assertEquals(0, arbol.size());

        arbol.insertar(crearAtraccion("X", "X"));
        assertFalse(arbol.isEmpty());
        assertEquals(1, arbol.size());
    }

    @Test
    void testInsertarNull() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(null);
        assertEquals(0, arbol.size(), "Insertar null no debe modificar el arbol");
    }
}
