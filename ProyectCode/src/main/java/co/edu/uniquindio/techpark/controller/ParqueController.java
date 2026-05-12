package co.edu.uniquindio.techpark.controller;

import co.edu.uniquindio.techpark.TechPark;
import co.edu.uniquindio.techpark.Model.Atraccion;
import co.edu.uniquindio.techpark.Model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parque")
@CrossOrigin(origins = "*")
public class ParqueController {

    @Autowired
    private TechPark techPark;

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
}
