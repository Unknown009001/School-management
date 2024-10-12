package src.model;

public class Profesor implements Comparable<Profesor> {

    private String nombre;
    private String apellido;
    private int edad;
    private String asignatura;
    private int ID;
    private String fechaNacimiento;
    private int DNI;

    

    public Profesor(String nombre, String apellido, int edad, String asignatura, int iD, String fechaNacimiento, int DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.asignatura = asignatura;
        ID = iD;
        this.fechaNacimiento = fechaNacimiento;
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getAsignatura() {
        return asignatura;
    }
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public int getDNI() {
        return DNI;
    }
    public void setDNI(int dNI) {
        DNI = dNI;
    }
    
    @Override
    public int compareTo(Profesor otro) {
        return Integer.compare(this.ID, otro.ID);
    }

    @Override
    public String toString(){
        return nombre + " " + apellido + " (ID: " + ID + ") - Asignatura: " + asignatura;
    }
    
}
