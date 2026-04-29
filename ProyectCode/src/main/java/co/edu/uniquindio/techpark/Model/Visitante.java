package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Visitante extends Usuario{
    private String documento;
    private int edad;
    private float estatura;
    private String rutaFoto; // Opcional
    private FavoritosSet favoritos;
    private List<Notificacion> listaNotificaciones;
    private List<Atraccion> historialVisitas; // Placeholder para la Lista Enlazada
    private int saldoVirtual;

    public Visitante(String id, String nombre, String documento, String correo, String contrasena, 
                     LocalDateTime fechaRegistro, int edad, float estatura, int saldoVirtual) {
        super(id, nombre, correo, contrasena, fechaRegistro);
        this.documento = documento;
        this.edad = edad;
        this.estatura = estatura;
        this.favoritos = new FavoritosSet(this);
        this.listaNotificaciones = new ArrayList<>();
        this.historialVisitas = new ArrayList<>();
        this.saldoVirtual = saldoVirtual;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public FavoritosSet getFavoritos() {
        return favoritos;
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public List<Atraccion> getHistorialVisitas() {
        return historialVisitas;
    }

    public int getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(int saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    @Override
    public String toString() {
        return "Visitante [nombre=" + getNombre() + ", documento=" + documento + ", saldo=" + saldoVirtual + "]";
    }

    public static void VerificarEdad(int edad){
        if (edad > 0 && edad < 120){
            System.out.println("La edad es valida");
        }else{
            System.out.println("La edad no es valida");
        }
    }

    
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
}