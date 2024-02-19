package controlador.Tutorias;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.AsignacionMatricula;

public class AsignacionMatriculabd extends AdaptadorDao<AsignacionMatricula> {

    private DynamicList<AsignacionMatricula> tutorias;
    private AsignacionMatricula tutoria;

    public AsignacionMatriculabd() {
        super(null);
        tutorias = new DynamicList<>();
    }

    public AsignacionMatriculabd(DynamicList<AsignacionMatricula> tutorias, AsignacionMatricula tutoria) {
        super(AsignacionMatricula.class);
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public DynamicList<AsignacionMatricula> getTutoriaMatriculasTodos() {
        tutorias = all();
        return tutorias;
    }
    
    public DynamicList<AsignacionMatricula> getTutoriaMatriculas() {
        return tutorias;
    }
    
    public void setTutorias(DynamicList<AsignacionMatricula> tutorias) {
        this.tutorias = tutorias;
    }

    public AsignacionMatricula getTutoriaMatricula() {
        if (tutoria == null) {
            tutoria = new AsignacionMatricula();
        }
        return tutoria;
    }

    public void setTutoria(AsignacionMatricula tutoria) {
        this.tutoria = tutoria;
    }

    @Override
    public Boolean persist(AsignacionMatricula obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<AsignacionMatricula> buscarLineal(DynamicList<AsignacionMatricula> lista, String campo, String valorBuscado) throws EmptyException {
        AsignacionMatricula tutoriasMatriculas[] = lista.toArray();
        DynamicList<AsignacionMatricula> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            AsignacionMatricula tutoriaMatricula = tutoriasMatriculas[i];
            if (tutoriaMatricula.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(tutoriaMatricula);
            }
        }
        return listaBusqueda;
    }

    public AsignacionMatricula buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<AsignacionMatricula> lista = all();
        int fin = lista.getLength() - 1;
        AsignacionMatricula tutoriasMatriculas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            AsignacionMatricula tutoriaMatricula = tutoriasMatriculas[medio];
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
