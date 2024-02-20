/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class Cursa {
    private Integer id;
    private String paralelo;
    private Integer matricula_ID;
    private String asignatura_CODIGO;

    public Cursa() {
    }

    public Cursa(Integer id, String paralelo, Integer matricula_ID, String asignatura_CODIGO) {
        this.id = id;
        this.paralelo = paralelo;
        this.matricula_ID = matricula_ID;
        this.asignatura_CODIGO = asignatura_CODIGO;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Integer getMatricula_ID() {
        return matricula_ID;
    }

    public void setMatricula_ID(Integer matricula_ID) {
        this.matricula_ID = matricula_ID;
    }

    public String getAsignatura_CODIGO() {
        return asignatura_CODIGO;
    }

    public void setAsignatura_CODIGO(String asignatura_CODIGO) {
        this.asignatura_CODIGO = asignatura_CODIGO;
    }
    
    public int compareCampo(String campo, String valorBuscado) {
        switch (campo.toLowerCase()) {
            case "id":
                return this.id.compareTo(Integer.parseInt(valorBuscado));
            case "matricula_id":
                return this.matricula_ID.compareTo(Integer.parseInt(valorBuscado));
            case "asignatura_codigo":
                return this.asignatura_CODIGO.compareTo(valorBuscado);
            default:
                throw new IllegalArgumentException("Campo no válido para comparación: " + campo);
        }
    }
    
    public Boolean compare(Cursa p, String field, Integer type) {
        //0 menor 1 mayor
        switch (type) {
            case 0:// Si el tipo es 0 es menor
                if (field.equalsIgnoreCase("paralelo")) {
                    return paralelo.compareTo(p.getParalelo()) < 0;//compara
                } else if (field.equalsIgnoreCase("asignatura_codigo")) {
                    return asignatura_CODIGO.compareTo(p.getAsignatura_CODIGO()) < 0;
                } else if (field.equalsIgnoreCase("matricula_id")) {
                    return matricula_ID.compareTo(p.getMatricula_ID()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(p.getId()) < 0;
                }
            case 1: //si el tipo es 1 es mayor
                if (field.equalsIgnoreCase("paralelo")) {
                    return paralelo.compareTo(p.getParalelo()) > 0;//compara
                } else if (field.equalsIgnoreCase("asignatura_codigo")) {
                    return asignatura_CODIGO.compareTo(p.getAsignatura_CODIGO()) > 0;
                } else if (field.equalsIgnoreCase("matricula_id")) {
                    return matricula_ID.compareTo(p.getMatricula_ID()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id.compareTo(p.getId()) > 0;
                }
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Codigo de Asignatura: " + asignatura_CODIGO + " id de Matricula: " + matricula_ID;
    }
}
