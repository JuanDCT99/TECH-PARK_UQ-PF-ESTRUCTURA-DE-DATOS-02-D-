package co.edu.uniquindio.techpark.controller;

import co.edu.uniquindio.techpark.TechPark;
import co.edu.uniquindio.techpark.Model.Atraccion;
import co.edu.uniquindio.techpark.Model.Zona;
import co.edu.uniquindio.techpark.Model.ResultadoRuta;
import co.edu.uniquindio.techpark.Model.Reporte;
import co.edu.uniquindio.techpark.Model.Usuario;
import co.edu.uniquindio.techpark.Model.Tiquete;
import co.edu.uniquindio.techpark.Model.TipoTiquete;
import co.edu.uniquindio.techpark.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<ResultadoRuta> getRuta(@RequestParam String origen, @RequestParam String destino) {
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
    public ResponseEntity<String> unirseAFila(@RequestParam String visitanteId, 
                                              @RequestParam String atraccionId, 
                                              @RequestParam String tipoTiquete) {
        try {
            TipoTiquete tipo = TipoTiquete.valueOf(tipoTiquete.toUpperCase());
            String mensaje = techPark.unirseAFila(visitanteId, atraccionId, tipo);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("❌ Tipo de tiquete inválido.");
        }
    }

    @GetMapping("/usuarios")
    public Usuario[] getUsuarios() {
        return techPark.getUsuarios();
    }

    @PostMapping("/procesar-fila")
    public ResponseEntity<String> procesarFila(@RequestParam String atraccionId) {
        String mensaje = techPark.procesarSiguiente(atraccionId);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/comprar-ticket")
    public ResponseEntity<String> comprarTicket(@RequestParam String visitanteId,
                                                 @RequestParam String tipoTiquete) {
        try {
            TipoTiquete tipo = TipoTiquete.valueOf(tipoTiquete.toUpperCase());
            String mensaje = techPark.comprarTicket(visitanteId, tipo);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("❌ Tipo de tiquete inválido.");
        }
    }

    @GetMapping("/mis-tiquetes")
    public ResponseEntity<Tiquete[]> getMisTiquetes(@RequestParam String visitanteId) {
        Tiquete[] tiquetes = techPark.getTiquetesVisitante(visitanteId);
        return ResponseEntity.ok(tiquetes);
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
