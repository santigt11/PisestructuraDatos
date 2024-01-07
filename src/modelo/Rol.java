package modelo;

public enum Rol {
    ADMINISTRADOR("Administrador"), DOCENTE("Docente"), ESTUDIANTE("Estudiante");
    
    private String name;
    
    Rol(String name){
        this.name = name;
    }

    public static Rol getADMINISTRADOR() {
        return ADMINISTRADOR;
    }

    public static Rol getDOCENTE() {
        return DOCENTE;
    }

    public static Rol getESTUDIANTE() {
        return ESTUDIANTE;
    }

    public String getName() {
        return name;
    }


}
