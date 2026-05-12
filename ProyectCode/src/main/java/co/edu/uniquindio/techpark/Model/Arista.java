package co.edu.uniquindio.techpark.Model;

/**
 * Representa una conexión entre dos atracciones en el grafo del parque.
 */
public class Arista {
    private String destinoId;
    private double peso; // Distancia o tiempo

    public Arista(String destinoId, double peso) {
        this.destinoId = destinoId;
        this.peso = peso;
    }

    public String getDestinoId() {
        return destinoId;
    }

    public double getPeso() {
        return peso;
    }
}
