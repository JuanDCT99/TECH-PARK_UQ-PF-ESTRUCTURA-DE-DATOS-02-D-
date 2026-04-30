package co.edu.uniquindio.techpark.Model;

import java.util.*;

public class Reporte {
    private Date fecha;
    private int ingresosDiarios;
    private int totalVisitantes;
    private double tiempoPromEspera;
    private int cierresClima;
    private int alertasMantenimiento;
    
    public Reporte(Date fecha, int ingresosDiarios, int totalVisitantes, double tiempoPromEspera, int cierresClima,
            int alertasMantenimiento) {
        this.fecha = fecha;
        this.ingresosDiarios = ingresosDiarios;
        this.totalVisitantes = totalVisitantes;
        this.tiempoPromEspera = tiempoPromEspera;
        this.cierresClima = cierresClima;
        this.alertasMantenimiento = alertasMantenimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIngresosDiarios() {
        return ingresosDiarios;
    }

    public void setIngresosDiarios(int ingresosDiarios) {
        this.ingresosDiarios = ingresosDiarios;
    }

    public int getTotalVisitantes() {
        return totalVisitantes;
    }

    public void setTotalVisitantes(int totalVisitantes) {
        this.totalVisitantes = totalVisitantes;
    }

    public double getTiempoPromEspera() {
        return tiempoPromEspera;
    }

    public void setTiempoPromEspera(double tiempoPromEspera) {
        this.tiempoPromEspera = tiempoPromEspera;
    }

    public int getCierresClima() {
        return cierresClima;
    }

    public void setCierresClima(int cierresClima) {
        this.cierresClima = cierresClima;
    }

    public int getAlertasMantenimiento() {
        return alertasMantenimiento;
    }

    public void setAlertasMantenimiento(int alertasMantenimiento) {
        this.alertasMantenimiento = alertasMantenimiento;
    }

    @Override
    public String toString() {
        return "Reporte [fecha=" + fecha + ", ingresosDiarios=" + ingresosDiarios + ", totalVisitantes="
                + totalVisitantes + ", tiempoPromEspera=" + tiempoPromEspera + ", cierresClima=" + cierresClima
                + ", alertasMantenimiento=" + alertasMantenimiento + "]";
    }

    
}
