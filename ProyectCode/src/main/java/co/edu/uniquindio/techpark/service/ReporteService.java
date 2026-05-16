package co.edu.uniquindio.techpark.service;

import co.edu.uniquindio.techpark.Model.*;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ReporteService {

    public Reporte generarReporteDiario(ListaEnlazada<Atraccion> atracciones) {
        int ingresosDiarios = 0;
        int totalVisitantes = 0;
        double sumaTiempos = 0;
        int contadorTiempos = 0;
        int cierresClima = 0;
        int alertasMantenimiento = 0;

        for (Atraccion atr : atracciones) {
            totalVisitantes += atr.getContadorVisitantes();
            ingresosDiarios += atr.getContadorVisitantes() * atr.getCostoAdicional();

            if (atr.getEstado() == EstadoAtraccion.CERRADA) {
                String motivo = atr.getMotivoCierre();
                if (motivo != null && motivo.contains("CLIMA")) {
                    cierresClima++;
                }
            }

            if (atr.getEstado() == EstadoAtraccion.EN_MANTENIMIENTO) {
                alertasMantenimiento++;
            }

            if (atr.getTiempoEspera() > 0) {
                sumaTiempos += atr.getTiempoEspera();
                contadorTiempos++;
            }
        }

        double tiempoPromEspera = contadorTiempos > 0 ? sumaTiempos / contadorTiempos : 0;

        return new Reporte(new Date(), ingresosDiarios, totalVisitantes, tiempoPromEspera, cierresClima, alertasMantenimiento);
    }

    public Atraccion[] getAtraccionesMasVisitadas(ListaEnlazada<Atraccion> atracciones) {
        Atraccion[] arr = atracciones.toArray(Atraccion.class);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getContadorVisitantes() < arr[j + 1].getContadorVisitantes()) {
                    Atraccion temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }
}
