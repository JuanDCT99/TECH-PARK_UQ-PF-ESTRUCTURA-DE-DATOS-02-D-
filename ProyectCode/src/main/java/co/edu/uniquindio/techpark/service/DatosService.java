package co.edu.uniquindio.techpark.service;

import co.edu.uniquindio.techpark.Model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para la carga de datos iniciales desde archivos JSON.
 * Requerido por el PDF del proyecto (sección 78 y 102).
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
@Service
public class DatosService {
    
    private final ObjectMapper objectMapper;
    
    public DatosService() {
        this.objectMapper = new ObjectMapper();
        // Configurar para ignorar propiedades desconocidas en el JSON
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    /**
     * Carga la lista de atracciones desde atracciones.json.
     * 
     * @return Lista de atracciones, o lista vacía si hay error
     */
    public List<Atraccion> cargarAtracciones() {
        try {
            var resource = new ClassPathResource("data/atracciones.json");
            return objectMapper.readValue(resource.getInputStream(), 
                new TypeReference<List<Atraccion>>() {});
        } catch (IOException e) {
            System.err.println("Error al cargar atracciones.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Carga la lista de zonas desde zonas.json.
     * 
     * @return Lista de zonas, o lista vacía si hay error
     */
    public List<Zona> cargarZonas() {
        try {
            var resource = new ClassPathResource("data/zonas.json");
            return objectMapper.readValue(resource.getInputStream(), 
                new TypeReference<List<Zona>>() {});
        } catch (IOException e) {
            System.err.println("Error al cargar zonas.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Carga la lista de senderos desde senderos.json (para Grafo - Fase 3).
     * 
     * @return Lista de senderos con origen, destino y peso
     */
    public List<Sender> cargarSenderos() {
        try {
            var resource = new ClassPathResource("data/senderos.json");
            return objectMapper.readValue(resource.getInputStream(), 
                new TypeReference<List<Sender>>() {});
        } catch (IOException e) {
            System.err.println("Error al cargar senderos.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Carga todos los usuarios desde usuarios.json.
     * Soporta carga polimórfica: Visitante, Operador, Administrador según el campo "tipo".
     * 
     * @return Lista de usuarios, o lista vacía si hay error
     */
    public List<Usuario> cargarUsuarios() {
        try {
            var resource = new ClassPathResource("data/usuarios.json");
            List<Map<String, Object>> rawList = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<List<Map<String, Object>>>() {});

            List<Usuario> usuarios = new ArrayList<>();
            for (Map<String, Object> raw : rawList) {
                String tipo = (String) raw.getOrDefault("tipo", "VISITANTE");
                String id = (String) raw.get("id");
                String nombre = (String) raw.get("nombre");
                String correo = (String) raw.getOrDefault("correo", "");
                String contrasena = (String) raw.get("contrasena");

                switch (tipo) {
                    case "VISITANTE":
                        Visitante v = new Visitante(
                            id, nombre, (String) raw.get("documento"),
                            correo, contrasena,
                            java.time.LocalDateTime.now(),
                            Integer.parseInt(raw.get("edad").toString()),
                            Float.parseFloat(raw.get("estatura").toString()),
                            Integer.parseInt(raw.getOrDefault("saldoVirtual", "0").toString())
                        );
                        usuarios.add(v);
                        break;
                    case "OPERADOR":
                        Operador op = new Operador(
                            id, nombre, correo, contrasena,
                            java.time.LocalDateTime.now(),
                            (String) raw.get("codigoEmpleado"),
                            Double.parseDouble(raw.getOrDefault("salario", "0").toString()),
                            Turno.valueOf(raw.getOrDefault("turno", "MAÑANA").toString().toUpperCase())
                        );
                        usuarios.add(op);
                        break;
                    case "ADMINISTRADOR":
                        Administrador ad = new Administrador(
                            id, nombre, correo, contrasena,
                            java.time.LocalDateTime.now(),
                            (String) raw.get("codigoEmpleado"),
                            Double.parseDouble(raw.getOrDefault("salario", "0").toString()),
                            Integer.parseInt(raw.getOrDefault("nivelAcceso", "1").toString())
                        );
                        usuarios.add(ad);
                        break;
                }
            }
            return usuarios;
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Guarda todos los usuarios en usuarios.json.
     * 
     * @param usuarios Lista de usuarios a guardar
     */
    public void guardarUsuarios(ListaEnlazada<Usuario> usuarios) {
        try {
            ClassPathResource resource = new ClassPathResource("data/usuarios.json");
            File archivo = resource.getFile();

            List<Map<String, Object>> lista = new ArrayList<>();
            for (Usuario u : usuarios) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("id", u.getId());
                map.put("nombre", u.getNombre());
                map.put("correo", u.getCorreo());
                map.put("contrasena", u.getContrasena());

                if (u instanceof Visitante) {
                    Visitante v = (Visitante) u;
                    map.put("tipo", "VISITANTE");
                    map.put("documento", v.getDocumento());
                    map.put("edad", v.getEdad());
                    map.put("estatura", v.getEstatura());
                    map.put("saldoVirtual", v.getSaldoVirtual());
                } else if (u instanceof Operador) {
                    Operador op = (Operador) u;
                    map.put("tipo", "OPERADOR");
                    map.put("codigoEmpleado", op.getCodigoEmpleado());
                    map.put("salario", op.getSalario());
                    map.put("turno", op.getTurno().name());
                } else if (u instanceof Administrador) {
                    Administrador ad = (Administrador) u;
                    map.put("tipo", "ADMINISTRADOR");
                    map.put("codigoEmpleado", ad.getCodigoEmpleado());
                    map.put("salario", ad.getSalario());
                    map.put("nivelAcceso", ad.getNivelAcceso());
                }

                lista.add(map);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivo, lista);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios.json: " + e.getMessage());
        }
    }
}
