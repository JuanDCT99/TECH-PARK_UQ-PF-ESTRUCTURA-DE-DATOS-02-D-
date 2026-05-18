package co.edu.uniquindio.techpark.Controller;

import co.edu.uniquindio.techpark.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Permite peticiones desde tu Frontend React
public class AuthController {

    private final AuthService authService = AuthService.getInstancia();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String id = credenciales.get("id");
        String contrasena = credenciales.get("contrasena");
        String rol = credenciales.get("rol");

        Object usuario = authService.verificarLogin(id, contrasena, rol);
        
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Retorna el objeto del usuario logueado exitosamente
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensaje", "Identificación, contraseña o rol incorrectos"));
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Map<String, Object> datos) {
        String rol = (String) datos.get("rol");
        boolean exito = false;

        if (rol == null) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "El rol es obligatorio"));
        }

        switch (rol.toLowerCase()) {
            case "visitante":
                exito = authService.registrarVisitante(datos);
                break;
            case "operador":
                exito = authService.registrarOperador(datos);
                break;
            case "administrador":
                exito = authService.registrarAdministrador(datos);
                break;
            default:
                return ResponseEntity.badRequest().body(Map.of("mensaje", "Rol no válido"));
        }

        if (exito) {
            return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado exitosamente bajo el rol " + rol));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "Error al registrar el usuario. Verifique restricciones de los campos."));
        }
    }
}