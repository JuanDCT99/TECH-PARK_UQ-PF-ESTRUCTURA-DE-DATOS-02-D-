package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Tiquete {
    private String id;
    private TipoTiquete tipo;
    private double precio;
    private String descripcion;
    private LocalDateTime fechaCompra;
    private Visitante propietario;

    public Tiquete(String id, TipoTiquete tipo, double precio, String descripcion, Visitante propietario) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaCompra = LocalDateTime.now();
        this.propietario = propietario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoTiquete getTipo() {
        return tipo;
    }

    public void setTipo(TipoTiquete tipo) {
        this.tipo = tipo;
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

    public Visitante getPropietario() {
        return propietario;
    }

    public void setPropietario(Visitante propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Tiquete [ID=" + id + ", Tipo=" + tipo + ", Propietario=" + propietario.getCorreo() + "]";
    }
}