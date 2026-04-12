package co.edu.uniquindio.techpark.Model;

public class Entrada {
    private Visitante visitante;
    private TipoEntrada tipoEntrada;
    private String descripcion;
    private int precio;
    
    public Entrada(Visitante visitante, TipoEntrada tipoEntrada, String descripcion, int precio) {
        this.visitante = visitante;
        this.tipoEntrada = tipoEntrada;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
