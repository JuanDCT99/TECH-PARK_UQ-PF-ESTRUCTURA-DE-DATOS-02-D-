package co.edu.uniquindio.techpark.Model;

public class Alerta {
    private String id;
    private TipoAlerta tipo;
    private Atraccion atraccion;
    private int prioridad;
    private String descripcion;

    public Alerta(String id, TipoAlerta tipo, Atraccion atraccion, int prioridad, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.atraccion = atraccion;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoAlerta getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlerta tipo) {
        this.tipo = tipo;
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Alerta [id=" + id + ", tipo=" + tipo + ", atraccion=" + atraccion + ", prioridad=" + prioridad
                + ", descripcion=" + descripcion + "]";
    }
    
    
    
}
