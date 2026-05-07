package co.edu.uniquindio.techpark.controller;

import co.edu.uniquindio.techpark.TechPark;
import co.edu.uniquindio.techpark.Model.Atraccion;
import co.edu.uniquindio.techpark.Model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parque")
@CrossOrigin(origins = "*")
public class ParqueController {

    @Autowired
    private TechPark techPark;

    @GetMapping("/atracciones")
    public List<Atraccion> getAtracciones() {
        return techPark.getTodasLasAtracciones();
    }

    @GetMapping("/zonas")
    public List<Zona> getZonas() {
        return techPark.getZonas();
    }

    @GetMapping("/estado")
    public String getEstado() {
        return techPark.hayAforoGlobal() ? "ABIERTO" : "AFORO COMPLETO";
    }
}
