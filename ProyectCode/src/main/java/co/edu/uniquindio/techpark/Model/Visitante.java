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

    

    

    

    

    
    

}