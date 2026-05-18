package co.edu.uniquindio.techpark.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para el sistema TECH-PARK UQ.
 * Centraliza el manejo de errores y devuelve respuestas JSON consistentes.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja IllegalArgumentException lanzadas por la lógica de negocio.
     * Retorna un HTTP 400 (Bad Request) con el mensaje de error.
     * 
     * @param ex Excepción de argumento inválido
     * @return ResponseEntity con el mensaje de error
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones genéricas no controladas.
     * Retorna un HTTP 500 (Internal Server Error) con mensaje genérico.
     * 
     * @param ex Excepción genérica
     * @return ResponseEntity con mensaje de error interno
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Ha ocurrido un error inesperado en el sistema");
        
        // Log del error para depuración (opcional)
        ex.printStackTrace();
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
