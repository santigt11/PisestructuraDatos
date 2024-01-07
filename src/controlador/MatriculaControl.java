package controlador;

import controlador.TDA.listas.DynamicList;
import modelo.Matricula;

public class MatriculaControl {
    private Matricula matricula = new Matricula();
    private DynamicList<Matricula> matriculas;

    public MatriculaControl(Matricula matricula) {
        this.matricula = matricula;
    }

    public MatriculaControl() {
        this.matriculas = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getMatricula().setId(getMatriculas().getLength());
            getMatriculas().add(getMatricula());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public DynamicList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
