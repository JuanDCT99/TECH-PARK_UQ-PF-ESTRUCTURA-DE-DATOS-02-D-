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

        for (Usuario u : usuariosRegistrados) {
            String campoComparar = null;

            switch (rol.toLowerCase()) {
                case "visitante":
                    if (u instanceof Visitante) {
                        campoComparar = ((Visitante) u).getDocumento();
                    }
                    break;
                case "operador":
                case "empleado":
                    if (u instanceof Operador) {
                        campoComparar = ((Empleado) u).getCodigoEmpleado();
                    }
                    break;
                case "administrador":
                    if (u instanceof Administrador) {
                        campoComparar = u.getId();
                    }
                    break;
                default:
                    return null;
            }

            if (campoComparar != null && campoComparar.equals(identificador) && u.getContrasena().equals(contrasena)) {
                Map<String, Object> respuesta = new HashMap<>();
                respuesta.put("id", u.getId());
                respuesta.put("nombre", u.getNombre());
                respuesta.put("rol", rol.toLowerCase());
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

    public boolean registrarVisitante(Map<String, Object> datos) {
        try {
            Visitante nuevo = new Visitante(
                (String) datos.get("id"),
                (String) datos.get("nombre"),
                (String) datos.get("documento"),
                (String) datos.get("correo"),
                (String) datos.get("contrasena"),
                LocalDateTime.now(),
                Integer.parseInt(datos.get("edad").toString()),
                Float.parseFloat(datos.get("estatura").toString()),
                Integer.parseInt(datos.get("saldoVirtual").toString())
            );
            usuariosRegistrados.agregar(nuevo);
            techPark.registrarUsuario(nuevo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean registrarOperador(Map<String, Object> datos) {
        try {
            Turno turno = Turno.valueOf(datos.getOrDefault("turno", "MAÑANA").toString().toUpperCase());
            Operador nuevo = new Operador(
                (String) datos.get("id"),
                (String) datos.get("nombre"),
                (String) datos.get("correo"),
                (String) datos.get("contrasena"),
                LocalDateTime.now(),
                (String) datos.get("codigoEmpleado"),
                Double.parseDouble(datos.get("salario").toString()),
                turno
            );
            usuariosRegistrados.agregar(nuevo);
            techPark.registrarUsuario(nuevo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean registrarAdministrador(Map<String, Object> datos) {
        try {
            Administrador nuevo = new Administrador(
                (String) datos.get("id"),
                (String) datos.get("nombre"),
                (String) datos.get("correo"),
                (String) datos.get("contrasena"),
                LocalDateTime.now(),
                (String) datos.get("codigoEmpleado"),
                Double.parseDouble(datos.get("salario").toString()),
                Integer.parseInt(datos.get("nivelAcceso").toString())
            );
            usuariosRegistrados.agregar(nuevo);
            techPark.registrarUsuario(nuevo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
