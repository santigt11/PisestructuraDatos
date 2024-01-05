package controlador.Matricula;

import controlador.Academico.*;
import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.MatriculaAsignatura;

public class MatriculaAsignaturaArchivos extends DaoImplement<MatriculaAsignatura> {
    
    private DynamicList<MatriculaAsignatura> asgMatriculas;
    private MatriculaAsignatura asgMatricula;

    public MatriculaAsignaturaArchivos() {
        super(MatriculaAsignatura.class);
    }

    public DynamicList<MatriculaAsignatura> getAsgMatriculas() {
        asgMatriculas = all();
        return asgMatriculas;
    }

    public void setAsgMatriculas(DynamicList<MatriculaAsignatura> asgMatriculas) {
        this.asgMatriculas = asgMatriculas;
    }

    public MatriculaAsignatura getAsgMatricula() {
        if (asgMatricula == null) {
            asgMatricula = new MatriculaAsignatura();
        }
        return asgMatricula;
    }

    public void setAsgMatricula(MatriculaAsignatura asgMatricula) {
        this.asgMatricula = asgMatricula;
    }

    public Boolean persist() {
        asgMatricula.setId(all().getLength());
        return persist(asgMatricula);
    }
}
