package controlador.Tutorias;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.TutoriaMatricula;

public class TutoriaMatriculaArchivos extends AdaptadorDao<TutoriaMatricula> {

    private DynamicList<TutoriaMatricula> tutorias;
    private TutoriaMatricula tutoria;

    public TutoriaMatriculaArchivos() {
        super(null);
        tutorias = new DynamicList<>();
    }

    public TutoriaMatriculaArchivos(DynamicList<TutoriaMatricula> tutorias, TutoriaMatricula tutoria) {
        super(TutoriaMatricula.class);
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public DynamicList<TutoriaMatricula> getTutoriaMatriculasTodos() {
        tutorias = all();
        return tutorias;
    }
    
    public DynamicList<TutoriaMatricula> getTutoriaMatriculas() {
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
    public Boolean persist(TutoriaMatricula obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<TutoriaMatricula> buscarLineal(DynamicList<TutoriaMatricula> lista, String campo, String valorBuscado) throws EmptyException {
        TutoriaMatricula tutoriasMatriculas[] = lista.toArray();
        DynamicList<TutoriaMatricula> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            TutoriaMatricula tutoriaMatricula = tutoriasMatriculas[i];
            if (tutoriaMatricula.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(tutoriaMatricula);
            }
        }
        return listaBusqueda;
    }

    public TutoriaMatricula buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<TutoriaMatricula> lista = all();
        int fin = lista.getLength() - 1;
        TutoriaMatricula tutoriasMatriculas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            TutoriaMatricula tutoriaMatricula = tutoriasMatriculas[medio];
            int comparacion = tutoriaMatricula.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return tutoriaMatricula;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
