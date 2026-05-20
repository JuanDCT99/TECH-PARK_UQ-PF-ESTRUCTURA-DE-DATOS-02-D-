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

        boolean exito = false;

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
                return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("mensaje", "Rol no válido"));
        }

        if (exito) {
            return ResponseEntity.ok(
                Collections.singletonMap("mensaje", "Usuario registrado exitosamente bajo el rol " + rol)
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("mensaje", "Error al registrar el usuario. Verifique los campos."));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Collections.singletonMap("mensaje", "Sesión cerrada exitosamente"));
    }
}
