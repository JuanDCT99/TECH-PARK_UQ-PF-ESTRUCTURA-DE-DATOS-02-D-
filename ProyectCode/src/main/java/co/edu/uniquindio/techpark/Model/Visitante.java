package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un visitante del parque TECH-PARK UQ.
 * Extiende la clase Usuario y añade atributos específicos para el control de acceso,
 * saldo virtual, historial de visitas y atracciones favoritas.
 * 
 * @author TECH-PARK UQ Team
 * @version 1.0
 */
public class Visitante extends Usuario {
    private String documento;
    private int edad;
    private float estatura;
    private String rutaFoto; // Opcional
    private FavoritosSet favoritos;
    private List<Notificacion> listaNotificaciones;
    private List<Atraccion> historialVisitas; // TODO: Cambiar a ListaEnlazada
    private int saldoVirtual;

    /**
     * Constructor sin argumentos necesario para deserialización JSON con Jackson.
     */
    public Visitante() {
        super();
        this.favoritos = new FavoritosSet(this);
        this.listaNotificaciones = new ArrayList<>();
        this.historialVisitas = new ArrayList<>(); // TODO: Refactorizar a ListaEnlazada
    }

    /**
     * Constructor del Visitante.
     * 
     * @param id Identificador único
     * @param nombre Nombre completo
     * @param documento Documento de identidad
     * @param correo Correo electrónico
     * @param contrasena Contraseña de acceso
     * @param fechaRegistro Fecha de registro en el sistema
     * @param edad Edad del visitante (debe ser > 0)
     * @param estatura Estatura en metros (debe ser > 0)
     * @param saldoVirtual Saldo inicial en pesos
     * @throws IllegalArgumentException Si edad o estatura son inválidos
     */
    public Visitante(String id, String nombre, String documento, String correo, String contrasena,
                     LocalDateTime fechaRegistro, int edad, float estatura, int saldoVirtual) {
        super(id, nombre, correo, contrasena, fechaRegistro);
        
        if (edad <= 0 || edad >120) {
            throw new IllegalArgumentException("Edad inválida: debe estar entre 1 y 120 años");
        }
        if (estatura <= 0 || estatura > 3.0f) {
            throw new IllegalArgumentException("Estatura inválida: debe estar entre 0 y 3.0 metros");
        }
        
        this.documento = documento;
        this.edad = edad;
        this.estatura = estatura;
        this.favoritos = new FavoritosSet(this);
        this.listaNotificaciones = new ArrayList<>();
        this.historialVisitas = new ArrayList<>(); // TODO: Refactorizar a ListaEnlazada
        this.saldoVirtual = saldoVirtual;
    }

    /**
     * Obtiene la edad del visitante.
     * @return Edad en años
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del visitante.
     * @param edad Nueva edad (debe ser > 0 y <= 120)
     * @throws IllegalArgumentException Si la edad es inválida
     */
    public void setEdad(int edad) {
        if (edad <= 0 || edad > 120) {
            throw new IllegalArgumentException("Edad inválida: debe estar entre 1 y 120 años");
        }
        this.edad = edad;
    }

    /**
     * Obtiene la estatura del visitante.
     * @return Estatura en metros
     */
    public float getEstatura() {
        return estatura;
    }

    /**
     * Establece la estatura del visitante.
     * @param estatura Nueva estatura en metros (debe ser > 0 y <= 3.0)
     * @throws IllegalArgumentException Si la estatura es inválida
     */
    public void setEstatura(float estatura) {
        if (estatura <= 0 || estatura > 3.0f) {
            throw new IllegalArgumentException("Estatura inválida: debe estar entre 0 y 3.0 metros");
        }
        this.estatura = estatura;
    }

    /**
     * Obtiene el número de documento del visitante.
     * @return Número de documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el número de documento del visitante.
     * @param documento Nuevo número de documento
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene la ruta de la fotografía del visitante.
     * @return Ruta del archivo de imagen
     */
    public String getRutaFoto() {
        return rutaFoto;
    }

    /**
     * Establece la ruta de la fotografía del visitante.
     * @param rutaFoto Nueva ruta de la imagen
     */
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    /**
     * Obtiene el conjunto de atracciones favoritas del visitante.
     * @return Set de favoritos
     */
    public FavoritosSet getFavoritos() {
        return favoritos;
    }

    /**
     * Obtiene la lista de notificaciones pendientes del visitante.
     * @return Lista de notificaciones
     */
    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    /**
     * Obtiene el historial de visitas del visitante.
     * @return Lista de atracciones visitadas (TODO: cambiar a ListaEnlazada)
     */
    public List<Atraccion> getHistorialVisitas() {
        return historialVisitas;
    }

    /**
     * Obtiene el saldo virtual actual del visitante.
     * @return Saldo en pesos
     */
    public int getSaldoVirtual() {
        return saldoVirtual;
    }

    /**
     * Establece el saldo virtual del visitante.
     * @param saldoVirtual Nuevo saldo (no se valida negativo por posibles reembolsos)
     */
    public void setSaldoVirtual(int saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    @Override
    public String toString() {
        return "Visitante [nombre=" + getNombre() + ", documento=" + documento + ", saldo=" + saldoVirtual + "]";
    }

    /**
     * Verifica si una edad es válida (entre 1 y 120 años).
     * Método estático de utilidad.
     * 
     * @param edad Edad a verificar
     * @deprecated Usar validación en el constructor y setter en su lugar
     */
    public static void verificarEdad(int edad) {
        if (edad > 0 && edad < 120) {
            System.out.println("La edad es valida");
        } else {
            System.out.println("La edad no es valida");
        }
    }

    
    /**
     * Verifica si el visitante puede ingresar a una atracción específica.
     * Valida: estado de la atracción, estatura, edad y saldo virtual.
     * 
     * @param atraccion Atracción a la cual se desea ingresar
     * @return true si puede ingresar, false en caso contrario
     */
    public boolean puedeEntrar(Atraccion atraccion) {
        // 1. Validar que la atracción esté abierta/activa
        if (atraccion.getEstado() != EstadoAtraccion.ACTIVA) {
            System.out.println("La atracción " + atraccion.getNombre() + " no está disponible actualmente.");
            return false;
        }

        // 2. Validar estatura mínima requerida
        if (this.estatura < atraccion.getAlturaMin()) {
            System.out.println("No cumples con la estatura mínima requerida.");
            return false;
        }

        // 3. Validar edad mínima requerida
        if (this.edad < atraccion.getEdadMin()) {
            System.out.println("No cumples con la edad mínima requerida.");
            return false;
        }

        // 4. Validar si tiene saldo suficiente para el costo adicional
        if (this.saldoVirtual < atraccion.getCostoAdicional()) {
            System.out.println("No tienes saldo suficiente para el costo adicional de esta atracción.");
            return false;
        }

        // Si pasó todas las pruebas anteriores
        return true;
    }

    /**
     * Realiza el ingreso del visitante a una atracción.
     * Valida restricciones, descuenta saldo si es necesario, aumenta el contador de la atracción
     * y registra la visita en el historial del visitante.
     * 
     * @param atraccion Atracción a la cual ingresar
     */
    public void entrarAAtraccion(Atraccion atraccion) {
        if (puedeEntrar(atraccion)) {
            this.saldoVirtual -= atraccion.getCostoAdicional();
            atraccion.registrarVisita();
            this.historialVisitas.add(atraccion);
            System.out.println("Ingreso exitoso a " + atraccion.getNombre() + ". Saldo restante: " + saldoVirtual);
        } else {
            System.out.println("No se pudo completar el ingreso a " + atraccion.getNombre());
        }
    }
}