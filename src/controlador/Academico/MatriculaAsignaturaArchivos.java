
package controlador.Academico;


import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.MatriculaAsignatura;

public class MatriculaAsignaturaArchivos extends AdaptadorDao<MatriculaAsignatura> {
    
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

    @Override
    public Integer persist(MatriculaAsignatura obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
