package co.edu.uniquindio.techpark.controller;

import co.edu.uniquindio.techpark.TechPark;
import co.edu.uniquindio.techpark.Model.Atraccion;
import co.edu.uniquindio.techpark.Model.Zona;
import co.edu.uniquindio.techpark.Model.ResultadoRuta;
import co.edu.uniquindio.techpark.Model.Reporte;
import co.edu.uniquindio.techpark.Model.Usuario;
import co.edu.uniquindio.techpark.Model.Visitante;
import co.edu.uniquindio.techpark.Model.Tiquete;
import co.edu.uniquindio.techpark.Model.TipoTiquete;
import co.edu.uniquindio.techpark.service.ReporteService;
import co.edu.uniquindio.techpark.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parque")
@CrossOrigin(origins = "*")
public class ParqueController {

    @Autowired
    private TechPark techPark;

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/atracciones")
    public Atraccion[] getAtracciones() {
        return techPark.getTodasLasAtracciones();
    }

    @GetMapping("/senderos")
    public Sender[] getSenderos() {
        return techPark.getSenderos();
    }

    @GetMapping("/zonas")
    public Zona[] getZonas() {
        return techPark.getZonas();
    }

    @GetMapping("/estado")
    public String getEstado() {
        return techPark.hayAforoGlobal() ? "ABIERTO" : "AFORO COMPLETO";
    }

    /**
     * Endpoint para recargar los datos desde archivos JSON.
     * Llamado desde el botón de carga de datos en el frontend.
     */
    @PostMapping("/cargar-datos")
    public ResponseEntity<String> cargarDatos() {
        try {
            boolean exito = techPark.recargarDatos();
            if (exito) {
                return ResponseEntity.ok("✅ Datos cargados exitosamente desde archivos JSON");
            } else {
                return ResponseEntity.status(500).body("⚠️ Error al cargar datos JSON. Usando fallback hardcoded.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Error interno: " + e.getMessage());
        }
    }

    /**
     * Endpoint para obtener la ruta más corta entre dos puntos.
     */
    @GetMapping("/ruta")
    public ResponseEntity<ResultadoRuta> getRuta(@RequestParam("origen") String origen, @RequestParam("destino") String destino) {
        ResultadoRuta ruta = techPark.obtenerRutaOptima(origen, destino);
        if (ruta != null) {
            return ResponseEntity.ok(ruta);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para que un visitante se una a la fila de una atracción.
     */
    @PostMapping("/unirse-fila")
    public ResponseEntity<String> unirseAFila(@RequestParam("visitanteId") String visitanteId, 
                                               @RequestParam("atraccionId") String atraccionId, 
                                               @RequestParam("tipoTiquete") String tipoTiquete) {
        try {
            TipoTiquete tipo = TipoTiquete.valueOf(tipoTiquete.toUpperCase());
            String mensaje = techPark.unirseAFila(visitanteId, atraccionId, tipo);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("❌ Tipo de tiquete inválido.");
        }
    }

    @GetMapping("/usuarios")
    public Visitante[] getUsuarios() {
        Usuario[] todos = techPark.getUsuarios();
        List<Visitante> visitantes = new ArrayList<>();
        for (Usuario u : todos) {
            if (u instanceof Visitante) {
                visitantes.add((Visitante) u);
            }
        }
        return visitantes.toArray(new Visitante[0]);
    }

    @PostMapping("/procesar-fila")
    public ResponseEntity<String> procesarFila(@RequestParam("atraccionId") String atraccionId) {
        String mensaje = techPark.procesarSiguiente(atraccionId);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/comprar-ticket")
    public ResponseEntity<String> comprarTicket(@RequestParam("visitanteId") String visitanteId,
                                                  @RequestParam("tipoTiquete") String tipoTiquete) {
        try {
            TipoTiquete tipo = TipoTiquete.valueOf(tipoTiquete.toUpperCase());
            String mensaje = techPark.comprarTicket(visitanteId, tipo);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("❌ Tipo de tiquete inválido.");
        }
    }

    @GetMapping("/mis-tiquetes")
    public ResponseEntity<Tiquete[]> getMisTiquetes(@RequestParam("visitanteId") String visitanteId) {
        Tiquete[] tiquetes = techPark.getTiquetesVisitante(visitanteId);
        return ResponseEntity.ok(tiquetes);
    }

    @PostMapping("/agregar-favorito")
    public ResponseEntity<String> agregarFavorito(@RequestParam("visitanteId") String visitanteId,
                                                    @RequestParam("atraccionId") String atraccionId) {
        String mensaje = techPark.agregarFavorito(visitanteId, atraccionId);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/eliminar-favorito")
    public ResponseEntity<String> eliminarFavorito(@RequestParam("visitanteId") String visitanteId,
                                                     @RequestParam("atraccionId") String atraccionId) {
        String mensaje = techPark.eliminarFavorito(visitanteId, atraccionId);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/mis-favoritos")
    public ResponseEntity<Atraccion[]> getMisFavoritos(@RequestParam("visitanteId") String visitanteId) {
        Atraccion[] favoritas = techPark.getFavoritos(visitanteId);
        return ResponseEntity.ok(favoritas);
    }

    @GetMapping("/historial")
    public ResponseEntity<Atraccion[]> getHistorial(@RequestParam("visitanteId") String visitanteId) {
        Atraccion[] historial = techPark.getHistorial(visitanteId);
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/recargar-saldo")
    public ResponseEntity<String> recargarSaldo(@RequestParam("visitanteId") String visitanteId,
                                                  @RequestParam("monto") int monto) {
        String mensaje = techPark.recargarSaldo(visitanteId, monto);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/mantenimiento")
    public ResponseEntity<String> mantenimiento(@RequestParam("atraccionId") String atraccionId,
                                                  @RequestParam("accion") String accion) {
        String mensaje;
        switch (accion) {
            case "iniciar":
                mensaje = techPark.iniciarMantenimiento(atraccionId);
                break;
            case "revisar":
                mensaje = techPark.registrarRevision(atraccionId);
                break;
            default:
                return ResponseEntity.badRequest().body("❌ Acción inválida. Use 'iniciar' o 'revisar'.");
        }
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/alerta-clima")
    public ResponseEntity<String> alertaClima(@RequestParam("accion") String accion,
                                               @RequestParam(value = "motivo", defaultValue = "") String motivo) {
        if ("activar".equals(accion)) {
            String m = motivo.isEmpty() ? "Tormenta eléctrica detectada" : motivo;
            techPark.activarAlertaClimatica(m);
            return ResponseEntity.ok("✅ Alerta climática activada: " + m + ". Atracciones acuáticas y mecánicas cerradas.");
        } else if ("desactivar".equals(accion)) {
            techPark.desactivarAlertaClimatica();
            return ResponseEntity.ok("✅ Alerta climática desactivada. Atracciones restauradas.");
        }
        return ResponseEntity.badRequest().body("❌ Acción inválida. Use 'activar' o 'desactivar'.");
    }

    @GetMapping("/reportes/diario")
    public ResponseEntity<Reporte> getReporteDiario() {
        Reporte reporte = reporteService.generarReporteDiario(techPark.getListaAtracciones());
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/reportes/populares")
    public ResponseEntity<Atraccion[]> getAtraccionesPopulares() {
        Atraccion[] populares = reporteService.getAtraccionesMasVisitadas(techPark.getListaAtracciones());
        return ResponseEntity.ok(populares);
    }
}
