package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.DAO.DaoInterface;
import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.TutoriaMatricula;

public class TutoriaMatriculaArchivos extends AdaptadorDao<TutoriaMatricula> {

    private DynamicList<TutoriaMatricula> tutorias;
    private TutoriaMatricula tutoria;

    public TutoriaMatriculaArchivos() {
        super(null);
    }

    public TutoriaMatriculaArchivos(DynamicList<TutoriaMatricula> tutorias, TutoriaMatricula tutoria) {
        super(TutoriaMatricula.class);
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public DynamicList<TutoriaMatricula> getTutorias() {
        tutorias = all();
        return tutorias;
    }

    public void setTutorias(DynamicList<TutoriaMatricula> tutorias) {
        this.tutorias = tutorias;
    }

    public TutoriaMatricula getTutoriaMatricula() {
        if (tutoria == null) {
            tutoria = new TutoriaMatricula();
        }
        return tutoria;
    }

    public void setTutoria(TutoriaMatricula tutoria) {
        this.tutoria = tutoria;
    }

    @Override
    public Integer persist(TutoriaMatricula obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
