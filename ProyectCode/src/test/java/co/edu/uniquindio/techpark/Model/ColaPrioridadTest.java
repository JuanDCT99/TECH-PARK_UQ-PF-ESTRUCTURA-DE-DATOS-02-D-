package co.edu.uniquindio.techpark.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

class ColaPrioridadTest {

    private Atraccion crearAtraccion(String id) {
        return new Atraccion(id, "Test-" + id, "MECANICA", 50, 1.2f, 8, 0, 0, 0);
    }

    private EntradaCola crearEntrada(int prioridad, String id, Atraccion atr) {
        Visitante v = new Visitante(); v.setId("V" + id); v.setNombre("Visitante " + id);
        Tiquete t = new Tiquete("T" + id, prioridad == 1 ? TipoTiquete.FAST_PASS : TipoTiquete.GENERAL, 0, "", v);
        return new EntradaCola(v, t, prioridad, new Date(), atr);
    }

    @Test
    void testInsertarYEliminar() {
        ColaPrioridad cola = new ColaPrioridad();
        Atraccion atr = crearAtraccion("A1");
        cola.insertar(crearEntrada(1, "1", atr));
        cola.insertar(crearEntrada(2, "2", atr));

        assertEquals(2, cola.size());
        assertNotNull(cola.eliminar());
        assertEquals(1, cola.size());
    }

    @Test
    void testPrioridadFastPassAntesQueGeneral() {
        ColaPrioridad cola = new ColaPrioridad();
        Atraccion atr = crearAtraccion("A2");
        cola.insertar(crearEntrada(2, "G1", atr));
        cola.insertar(crearEntrada(1, "FP1", atr));
        cola.insertar(crearEntrada(2, "G2", atr));

        EntradaCola primero = cola.eliminar();
        assertEquals(1, primero.getPrioridad(), "Fast-Pass (1) debe salir antes que General (2)");
    }

    @Test
    void testPeekSinEliminar() {
        ColaPrioridad cola = new ColaPrioridad();
        Atraccion atr = crearAtraccion("A3");
        cola.insertar(crearEntrada(1, "X", atr));
        assertEquals(1, cola.size());
        assertNotNull(cola.peek());
        assertEquals(1, cola.size(), "peek no debe modificar la cola");
    }

    @Test
    void testColaVacia() {
        ColaPrioridad cola = new ColaPrioridad();
        assertTrue(cola.isEmpty());
        assertNull(cola.eliminar());
        assertNull(cola.peek());
    }

    @Test
    void testRedimensionamiento() {
        ColaPrioridad cola = new ColaPrioridad(2);
        Atraccion atr = crearAtraccion("A4");
        cola.insertar(crearEntrada(2, "1", atr));
        cola.insertar(crearEntrada(2, "2", atr));
        cola.insertar(crearEntrada(1, "3", atr));
        assertEquals(3, cola.size(), "Debe redimensionar al insertar mas alla de capacidad inicial");
    }
}
