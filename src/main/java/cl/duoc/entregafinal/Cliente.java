package cl.duoc.entregafinal;

public class Cliente {

    private String rut;
    private String nombre;
    private Integer edad;
    private Boolean esEstudiante;
    private static Integer EDAD_TERCERA_EDAD = 65;

    public Cliente(String rut, String nombre, Integer edad, Boolean esEstudiante){
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.esEstudiante = esEstudiante;
    }

    public Boolean esEstudiante(){
        return this.esEstudiante;
    }

    public Boolean esDeTerceraEdad(){
        return edad >= EDAD_TERCERA_EDAD;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }
}
