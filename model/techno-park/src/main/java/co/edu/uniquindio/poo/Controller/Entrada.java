package co.edu.uniquindio.poo.Controller;

public class Entrada{

    private String id;
    private double precio;
    private String descripcion;
    private TipoEntrada tipo;

    public Entrada(String id, double precio, String descripcion, TipoEntrada tipo) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoEntrada getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntrada tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "Entrada{" +
                "id='" + id + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }


}