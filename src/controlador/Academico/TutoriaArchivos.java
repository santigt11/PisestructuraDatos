package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.Tutoria;

public class TutoriaArchivos extends AdaptadorDao<Tutoria> {

    private DynamicList<Tutoria> tutorias;
    private Tutoria tutoria;

    public TutoriaArchivos(DynamicList<Tutoria> tutorias, Tutoria tutoria) {
        super(Tutoria.class);
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public TutoriaArchivos() {
        super(Tutoria.class);
    }

    public DynamicList<Tutoria> getTutorias() {
        tutorias = all();
        return tutorias;
    }

    public void setTutorias(DynamicList<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public Tutoria getTutoria() {
        if (tutoria == null) {
            tutoria = new Tutoria();
        }
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }
    
    @Override
    public Integer persist(Tutoria obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
