package co.edu.uniquindio.techpark.service;

import co.edu.uniquindio.techpark.Model.*;
import co.edu.uniquindio.techpark.TechPark;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@DependsOn("techPark")
public class AuthService {

    @Autowired
    private TechPark techPark;

    @Autowired
    private DatosService datosService;

    private ListaEnlazada<Usuario> usuariosRegistrados;

    public AuthService() {
        this.usuariosRegistrados = new ListaEnlazada<>();
    }

    @PostConstruct
    public void init() {
        Usuario[] existentes = techPark.getUsuarios();
        for (Usuario u : existentes) {
            usuariosRegistrados.agregar(u);
        }
    }

    public Map<String, Object> verificarLogin(String identificador, String contrasena, String rol) {
        if (identificador == null || contrasena == null || rol == null) {
            return null;
        }

        String rolLower = rol.toLowerCase();

        for (Usuario u : usuariosRegistrados) {
            boolean tipoCorrecto = false;
            switch (rolLower) {
                case "visitante":
                    tipoCorrecto = u instanceof Visitante;
                    break;
                case "operador":
                case "empleado":
                    tipoCorrecto = u instanceof Operador;
                    break;
                case "administrador":
                    tipoCorrecto = u instanceof Administrador;
                    break;
                default:
                    return null;
            }

            if (tipoCorrecto && u.getId().equals(identificador) && u.getContrasena().equals(contrasena)) {
                Map<String, Object> respuesta = new HashMap<>();
                respuesta.put("id", u.getId());
                respuesta.put("nombre", u.getNombre());
                respuesta.put("rol", rolLower);
                if (u instanceof Visitante) {
                    respuesta.put("documento", ((Visitante) u).getDocumento());
                    respuesta.put("saldoVirtual", ((Visitante) u).getSaldoVirtual());
                }
                if (u instanceof Empleado) {
                    respuesta.put("codigoEmpleado", ((Empleado) u).getCodigoEmpleado());
                }
                if (u instanceof Administrador) {
                    respuesta.put("nivelAcceso", ((Administrador) u).getNivelAcceso());
                }
                return respuesta;
            }
        }
        return null;
    }

    public Map<String, Object> registrarVisitante(Map<String, Object> datos) {
        try {
            String nuevoId = generarIdVisitante();
            String nombre = (String) datos.get("nombre");
            String documento = (String) datos.get("documento");
            String contrasena = (String) datos.get("contrasena");
            int edad = Integer.parseInt(datos.get("edad").toString());
            float estatura = Float.parseFloat(datos.get("estatura").toString());

            Visitante nuevo = new Visitante(
                nuevoId, nombre, documento, "", contrasena,
                LocalDateTime.now(), edad, estatura, 0
            );

            usuariosRegistrados.agregar(nuevo);
            techPark.registrarUsuario(nuevo);
            datosService.guardarUsuarios(usuariosRegistrados);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("success", true);
            respuesta.put("id", nuevoId);
            respuesta.put("nombre", nombre);
            respuesta.put("mensaje", "Visitante " + nuevoId + " registrado exitosamente.");
            return respuesta;
        } catch (Exception e) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("success", false);
            respuesta.put("mensaje", "Error al registrar: " + e.getMessage());
            return respuesta;
        }
    }

    private String generarIdVisitante() {
        int maxNum = 0;
        for (Usuario u : usuariosRegistrados) {
            if (u instanceof Visitante && u.getId() != null && u.getId().startsWith("V")) {
                try {
                    int num = Integer.parseInt(u.getId().substring(1));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        return "V" + (maxNum + 1);
    }
}
