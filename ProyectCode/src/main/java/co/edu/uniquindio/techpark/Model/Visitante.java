package co.edu.uniquindio.techpark.Model;

public class Visitante {
    private String nombre;
    private String documento;
    private int edad;
    private float estatura;
    private int saldoVirtual;

    public Visitante(String nombre, String documento, int edad, float estatura, int saldoVirtual) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.estatura = estatura;
        this.saldoVirtual = saldoVirtual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public int getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(int saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    @Override
    public String toString() {
        return "Visitante [nombre=" + nombre + ", documento=" + documento + ", edad=" + edad + ", estatura=" + estatura
                + ", saldoVirtual=" + saldoVirtual + "]";
    }

    

    
    

}