package co.edu.uniquindio.techpark.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GrafoTest {

    @Test
    void testAgregarAristas() {
        Grafo grafo = new Grafo();
        grafo.agregarArista("A1", "A2", 5.0, true);
        grafo.agregarArista("A2", "A3", 3.0, false);

        assertEquals(3, grafo.totalNodos());
        assertEquals(1, grafo.obtenerAdyacentes("A1").size(), "A1 → A2");
        assertEquals(2, grafo.obtenerAdyacentes("A2").size(), "A2 → A1 (bidir) + A2 → A3 (unidir)");
        assertEquals(0, grafo.obtenerAdyacentes("A3").size(), "A3 sin aristas salientes (solo recibe de A2)");
    }

    @Test
    void testRutaOptimaDirecta() {
        Grafo grafo = new Grafo();
        grafo.agregarArista("A1", "A2", 10.0, true);

        ResultadoRuta ruta = grafo.calcularRutaOptima("A1", "A2");
        assertNotNull(ruta);
        assertEquals(10.0, ruta.getPesoTotal(), 0.001);
    }

    @Test
    void testRutaOptimaConVariosNodos() {
        Grafo grafo = new Grafo();
        grafo.agregarArista("A1", "A2", 5.0, true);
        grafo.agregarArista("A2", "A3", 3.0, true);
        grafo.agregarArista("A1", "A3", 10.0, true);

        ResultadoRuta ruta = grafo.calcularRutaOptima("A1", "A3");
        assertNotNull(ruta);
        assertEquals(8.0, ruta.getPesoTotal(), 0.001, "Ruta A1->A2->A3 = 8.0 debe ser mas corta que A1->A3 = 10.0");
    }

    @Test
    void testRutaOptimaNodosInexistentes() {
        Grafo grafo = new Grafo();
        assertNull(grafo.calcularRutaOptima("X", "Y"));
    }

    @Test
    void testGrafoVacio() {
        Grafo grafo = new Grafo();
        assertEquals(0, grafo.totalNodos());
        assertTrue(grafo.obtenerAdyacentes("CUALQUIERA").isEmpty());
    }
}
