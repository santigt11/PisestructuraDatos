package controlador.Tutorias;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
<<<<<<< HEAD
import modelo.CursaTutoria;

public class AsignacionMatriculaBD extends AdaptadorDao<CursaTutoria> {

    private DynamicList<CursaTutoria> tutorias;
    private CursaTutoria tutoria;

    public AsignacionMatriculaBD() {
=======
import modelo.AsignacionMatricula;

public class AsignacionMatriculabd extends AdaptadorDao<AsignacionMatricula> {

    private DynamicList<AsignacionMatricula> tutorias;
    private AsignacionMatricula tutoria;

    public AsignacionMatriculabd() {
>>>>>>> Steven-Luna
        super(null);
        tutorias = new DynamicList<>();
    }

<<<<<<< HEAD
    public AsignacionMatriculaBD(DynamicList<CursaTutoria> tutorias, CursaTutoria tutoria) {
        super(CursaTutoria.class);
=======
    public AsignacionMatriculabd(DynamicList<AsignacionMatricula> tutorias, AsignacionMatricula tutoria) {
        super(AsignacionMatricula.class);
>>>>>>> Steven-Luna
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

<<<<<<< HEAD
    public DynamicList<CursaTutoria> getTutoriaMatriculasTodos() {
=======
    public DynamicList<AsignacionMatricula> getTutoriaMatriculasTodos() {
>>>>>>> Steven-Luna
        tutorias = all();
        return tutorias;
    }
    
<<<<<<< HEAD
    public DynamicList<CursaTutoria> getTutoriaMatriculas() {
        return tutorias;
    }
    
    public void setTutorias(DynamicList<CursaTutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public CursaTutoria getTutoriaMatricula() {
        if (tutoria == null) {
            tutoria = new CursaTutoria();
=======
    public DynamicList<AsignacionMatricula> getTutoriaMatriculas() {
        return tutorias;
    }
    
    public void setTutorias(DynamicList<AsignacionMatricula> tutorias) {
        this.tutorias = tutorias;
    }

    public AsignacionMatricula getTutoriaMatricula() {
        if (tutoria == null) {
            tutoria = new AsignacionMatricula();
>>>>>>> Steven-Luna
        }
        return tutoria;
    }

<<<<<<< HEAD
    public void setTutoria(CursaTutoria tutoria) {
=======
    public void setTutoria(AsignacionMatricula tutoria) {
>>>>>>> Steven-Luna
        this.tutoria = tutoria;
    }

    @Override
<<<<<<< HEAD
    public Boolean persist(CursaTutoria obj){
=======
    public Boolean persist(AsignacionMatricula obj){
>>>>>>> Steven-Luna
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

<<<<<<< HEAD
    public DynamicList<CursaTutoria> buscarLineal(DynamicList<CursaTutoria> lista, String campo, String valorBuscado) throws EmptyException {
        CursaTutoria tutoriasMatriculas[] = lista.toArray();
        DynamicList<CursaTutoria> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            CursaTutoria tutoriaMatricula = tutoriasMatriculas[i];
=======
    public DynamicList<AsignacionMatricula> buscarLineal(DynamicList<AsignacionMatricula> lista, String campo, String valorBuscado) throws EmptyException {
        AsignacionMatricula tutoriasMatriculas[] = lista.toArray();
        DynamicList<AsignacionMatricula> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            AsignacionMatricula tutoriaMatricula = tutoriasMatriculas[i];
>>>>>>> Steven-Luna
            if (tutoriaMatricula.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(tutoriaMatricula);
            }
        }
        return listaBusqueda;
    }

<<<<<<< HEAD
    public CursaTutoria buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<CursaTutoria> lista = all();
        int fin = lista.getLength() - 1;
        CursaTutoria tutoriasMatriculas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            CursaTutoria tutoriaMatricula = tutoriasMatriculas[medio];
=======
    public AsignacionMatricula buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<AsignacionMatricula> lista = all();
        int fin = lista.getLength() - 1;
        AsignacionMatricula tutoriasMatriculas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            AsignacionMatricula tutoriaMatricula = tutoriasMatriculas[medio];
>>>>>>> Steven-Luna
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
