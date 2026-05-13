package co.edu.uniquindio.techpark.Model;

/**
 * Clase que representa el resultado de un cálculo de ruta óptima.
 * Contiene el camino de atracciones y el peso total (distancia/tiempo).
 */
public class ResultadoRuta {
    private ListaEnlazada<String> camino;
    private double pesoTotal;

    public ResultadoRuta(ListaEnlazada<String> camino, double pesoTotal) {
        this.camino = camino;
        this.pesoTotal = pesoTotal;
    }

    public ListaEnlazada<String> getCamino() {
        return camino;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ruta: ");
        for (String id : camino) {
            sb.append(id).append(" -> ");
        }
        sb.append(" FIN (Total: ").append(pesoTotal).append(")");
        return sb.toString();
    }
}
