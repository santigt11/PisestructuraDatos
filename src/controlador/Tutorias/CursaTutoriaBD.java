package controlador.Tutorias;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.CursaTutoria;

public class AsignacionMatriculaBD1 extends AdaptadorDao<CursaTutoria> {

    private DynamicList<CursaTutoria> tutorias;
    private CursaTutoria tutoria;

    public AsignacionMatriculaBD1() {
        super(null);
        tutorias = new DynamicList<>();
    }

    public AsignacionMatriculaBD1(DynamicList<CursaTutoria> tutorias, CursaTutoria tutoria) {
        super(CursaTutoria.class);
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }


    public DynamicList<CursaTutoria> getTutoriaMatriculasTodos() {
        tutorias = all();
        return tutorias;
    }
    
    public DynamicList<CursaTutoria> getTutoriaMatriculas() {
        return tutorias;
    }
    
    public void setTutorias(DynamicList<CursaTutoria> tutorias) {
        this.tutorias = tutorias;
    }
    
    
    public CursaTutoria getTutoriaMatricula() {
        if (tutoria == null) {
            tutoria = new CursaTutoria();
        }
        return tutoria;
    }

    public void setTutoria(CursaTutoria tutoria) {
        this.tutoria = tutoria;
    }

    @Override
    public Boolean persist(CursaTutoria obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<CursaTutoria> buscarLineal(DynamicList<CursaTutoria> lista, String campo, String valorBuscado) throws EmptyException {
        CursaTutoria tutoriasMatriculas[] = lista.toArray();
        DynamicList<CursaTutoria> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            CursaTutoria tutoriaMatricula = tutoriasMatriculas[i];
            if (tutoriaMatricula.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(tutoriaMatricula);
            }
        }
        return listaBusqueda;
    }

    public CursaTutoria buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<CursaTutoria> lista = all();
        int fin = lista.getLength() - 1;
        CursaTutoria tutoriasMatriculas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            CursaTutoria tutoriaMatricula = tutoriasMatriculas[medio];
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
