package co.edu.uniquindio.techpark.Model;

import java.time.LocalDateTime;

public class Operador extends Usuario{
    private Turno turno;
    
    public Operador(String id, String correo, String contrasena, LocalDateTime fechaRegistro, Turno turno) {
        super(id, correo, contrasena, fechaRegistro);
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Operador [turno=" + turno + "]";
    }
    
    
}
