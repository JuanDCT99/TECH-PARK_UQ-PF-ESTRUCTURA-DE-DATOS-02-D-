package co.edu.uniquindio.techpark.service;
/**
 * Clase auxiliar para mapear senderos desde JSON (para Grafo - Fase 3).
 * Representa una arista en el grafo: conexión entre dos atracciones.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
public class Sender {
    private String origen;
    private String destino;
    private int peso;
    
    /**
     * Constructor sin argumentos necesario para Jackson.
     */
    public Sender() {
    }
    
    /**
     * Constructor completo.
     * 
     * @param origen ID de la atracción de origen
     * @param destino ID de la atracción de destino
     * @param peso Peso de la arista (distancia o tiempo)
     */
    public Sender(String origen, String destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    
    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }
}
