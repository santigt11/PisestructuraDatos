package controlador;

import controlador.TDA.listas.DynamicList;
import modelo.MatriculaAsignatura;

public class MatriculaAsignaturaControl {
    private MatriculaAsignatura asgMatricula = new MatriculaAsignatura();
    private DynamicList<MatriculaAsignatura> asgMatriculas;

    public MatriculaAsignaturaControl(MatriculaAsignatura asgMatricula) {
        this.asgMatricula = asgMatricula;
    }

    public MatriculaAsignaturaControl() {
        this.asgMatriculas = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getAsgMatricula().setId(getAsgMatriculas().getLength());
            getAsgMatriculas().add(getAsgMatricula());
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public DynamicList<MatriculaAsignatura> getAsgMatriculas() {
        return asgMatriculas;
    }

    public void setAsgMatriculas(DynamicList<MatriculaAsignatura> asgMatriculas) {
        this.asgMatriculas = asgMatriculas;
    }
}
