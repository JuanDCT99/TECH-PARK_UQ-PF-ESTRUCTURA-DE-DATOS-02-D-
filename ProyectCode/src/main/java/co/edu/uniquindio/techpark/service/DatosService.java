package co.edu.uniquindio.techpark.service;

import co.edu.uniquindio.techpark.Model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * Carga la lista de usuarios desde usuarios.json.
     * Nota: El JSON contiene campos de Visitante, por lo que se mapea a List<Visitante>.
     * 
     * @return Lista de visitantes, o lista vacía si hay error
     */
    public List<Visitante> cargarUsuarios() {
        try {
            var resource = new ClassPathResource("data/usuarios.json");
            return objectMapper.readValue(resource.getInputStream(), 
                new TypeReference<List<Visitante>>() {});
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
