package co.edu.uniquindio.techpark.service;

import co.edu.uniquindio.techpark.Model.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    // Simulamos la base de datos mapeando por ID
    private Map<String, Visitante> baseDatosVisitantes = new HashMap<>();
    private Map<String, Operador> baseDatosOperadores = new HashMap<>();
    private Map<String, Administrador> baseDatosAdministradores = new HashMap<>();

    // INSTANCIA ÚNICA (Singleton) para mantener persistencia en memoria durante pruebas
    private static AuthService instancia;
    public static synchronized AuthService getInstancia() {
        if (instancia == null) instancia = new AuthService();
        return instancia;
    }

    // --- LOGICA DE LOGIN ---
    public Object verificarLogin(String id, String contrasena, String rol) {
        switch (rol.toLowerCase()) {
            case "visitante":
                Visitante v = baseDatosVisitantes.get(id);
                if (v != null && v.getContrasena().equals(contrasena)) return v;
                break;
            case "operador":
                Operador o = baseDatosOperadores.get(id);
                if (o != null && o.getContrasena().equals(contrasena)) return o;
                break;
            case "administrador":
                Administrador a = baseDatosAdministradores.get(id);
                if (a != null && a.getContrasena().equals(contrasena)) return a;
                break;
        }
        return null; // Usuario o contraseña inválidos
    }

    // --- LÓGICA DE REGISTRO ---
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
            baseDatosVisitantes.put(nuevo.getId(), nuevo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean registrarOperador(Map<String, Object> datos) {
        try {
            // Asumimos un Turno por defecto o parseado
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
            baseDatosOperadores.put(nuevo.getId(), nuevo);
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
            baseDatosAdministradores.put(nuevo.getId(), nuevo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}