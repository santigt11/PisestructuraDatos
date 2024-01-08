
package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.Matricula;

public class MatriculaArchivos extends AdaptadorDao<Matricula> {
    
    private DynamicList<Matricula> matriculas;
    private Matricula matricula;

    public MatriculaArchivos() {
        super(Matricula.class);
    }

    public DynamicList<Matricula> getMatriculasTodos() {
        matriculas = all();
        return matriculas;
    }
    
    public DynamicList<Matricula> getMatriculas() {
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

    @Override
    public Integer persist(Matricula obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
