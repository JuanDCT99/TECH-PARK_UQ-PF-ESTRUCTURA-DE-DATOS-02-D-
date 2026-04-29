package co.edu.uniquindio.techpark.Model;

public class PriotityQueue {
    private EntradaCola[] cabeza;
    private int tam;
    private Atraccion atraccion;

    public PriotityQueue(){
        this.cabeza = null;
        this.tam = 0;
    }

    public EntradaCola[] getCabeza() {
        return cabeza;
    }

    public void setCabeza(EntradaCola[] cabeza) {
        this.cabeza = cabeza;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    
}
