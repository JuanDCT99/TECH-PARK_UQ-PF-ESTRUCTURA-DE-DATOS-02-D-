package co.edu.uniquindio.techpark.controller;

import co.edu.uniquindio.techpark.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String identificador = credenciales.get("identificador");
        String contrasena = credenciales.get("contrasena");
        String rol = credenciales.get("rol");

        if (identificador == null || contrasena == null || rol == null) {
            return ResponseEntity.badRequest()
                .body(Collections.singletonMap("mensaje", "Faltan campos obligatorios"));
        }

        Map<String, Object> usuario = authService.verificarLogin(identificador, contrasena, rol);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("mensaje", "Identificación, contraseña o rol incorrectos"));
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Map<String, Object> datos) {
        String rol = (String) datos.get("rol");
        if (rol == null) {
            return ResponseEntity.badRequest()
                .body(Collections.singletonMap("mensaje", "El rol es obligatorio"));
        }

        String rolLower = rol.toLowerCase();
        if (!"visitante".equals(rolLower)) {
            return ResponseEntity.badRequest()
                .body(Collections.singletonMap("mensaje", "Solo los visitantes pueden registrarse. Empleados y administradores usan credenciales asignadas."));
        }

        Map<String, Object> resultado = authService.registrarVisitante(datos);

        if (resultado != null && Boolean.TRUE.equals(resultado.get("success"))) {
            return ResponseEntity.ok(resultado);
        } else {
            String msg = resultado != null ? (String) resultado.get("mensaje") : "Error al registrar";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("mensaje", msg));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Sesión cerrada exitosamente"));
    }
}
