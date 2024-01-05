package controlador.Matricula;

import controlador.Academico.*;
import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Matricula;

public class MatriculaArchivos extends DaoImplement<Matricula> {
    
    private DynamicList<Matricula> matriculas;
    private Matricula matricula;

    public MatriculaArchivos() {
        super(Matricula.class);
    }

    public DynamicList<Matricula> getMatriculas() {
        matriculas = all();
        return matriculas;
    }

    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
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

    public Boolean persist() {
        matricula.setId(all().getLength());
        return persist(matricula);
    }
}
