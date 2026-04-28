package co.edu.uniquindio.techpark.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permite que React se conecte
public class TestController {

    @GetMapping("/test")
    public Map<String, String> testConnection() {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "¡Conexión exitosa entre Java y el mundo exterior!");
        response.put("proyecto", "Tech Park UQ");
        response.put("estado", "Backend listo para recibir a React");
        return response;
    }
}
