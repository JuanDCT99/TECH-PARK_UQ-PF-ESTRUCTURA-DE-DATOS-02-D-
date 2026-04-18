package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Notificacion {
    private String id;
    private String mensaje;
    private LocalDateTime fechaHora;
    private Visitante visitante;
    private boolean leida;
    private TipoNotificacion tipoNotificacion;

    public Notificacion(String id, String mensaje, LocalDateTime fechaHora, Visitante visitante, boolean leida,
            TipoNotificacion tipoNotificacion) {
        this.id = id;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
        this.visitante = visitante;
        this.leida = leida;
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    

    
    
}
