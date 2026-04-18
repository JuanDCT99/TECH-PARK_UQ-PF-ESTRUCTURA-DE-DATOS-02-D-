package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Visitante extends Usuario{
    private int edad;
    private float estatura;
    private FavoritosSet favoritos;
    private Notificacion Notificaciones;
    private int saldoVirtual;

    public Visitante(String id, String correo, String contrasena, LocalDateTime fechaRegistro, int edad, float estatura,
            FavoritosSet favoritos, Notificacion notificaciones, int saldoVirtual) {
        super(id, correo, contrasena, fechaRegistro);
        this.edad = edad;
        this.estatura = estatura;
        this.favoritos = favoritos;
        Notificaciones = notificaciones;
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

    public FavoritosSet getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(FavoritosSet favoritos) {
        this.favoritos = favoritos;
    }

    public Notificacion getNotificaciones() {
        return Notificaciones;
    }

    public void setNotificaciones(Notificacion notificaciones) {
        Notificaciones = notificaciones;
    }

    public int getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(int saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    @Override
    public String toString() {
        return "Visitante [edad=" + edad + ", estatura=" + estatura + ", favoritos=" + favoritos + ", Notificaciones="
                + Notificaciones + ", saldoVirtual=" + saldoVirtual + "]";
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