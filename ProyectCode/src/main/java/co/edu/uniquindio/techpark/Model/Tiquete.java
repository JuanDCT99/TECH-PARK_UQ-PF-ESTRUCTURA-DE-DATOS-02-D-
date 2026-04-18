package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Tiquete {
    private String id;
    private TipoEntrada tipo;
    private double precio;
    private LocalDateTime fechaCompra;
    private Visitante propietario;

    public Tiquete(String id, TipoEntrada tipo, double precio, Visitante propietario) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.fechaCompra = LocalDateTime.now();
        this.propietario = propietario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoEntrada getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntrada tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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