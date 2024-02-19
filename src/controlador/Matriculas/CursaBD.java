package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
<<<<<<< HEAD
import modelo.Cursa;

public class CursaBD extends AdaptadorDao<Cursa> {

    private DynamicList<Cursa> cursas;
    private Cursa cursa;

    public CursaBD() {
        super(Cursa.class);
        cursas = new DynamicList<>();
    }

    public DynamicList<Cursa> getCursasTodas() {
        cursas = all();
        return cursas;
    }
    
    public DynamicList<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(DynamicList<Cursa> cursas) {
        this.cursas = cursas;
    }

    public Cursa getCursa() {
        if (cursa == null) {
            cursa = new Cursa();
        }
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    @Override
    public Boolean persist(Cursa obj){
=======
import modelo.AsignacionMatricula;

public class CursaBD extends AdaptadorDao<AsignacionMatricula> {

    private DynamicList<AsignacionMatricula> asgMatriculas;
    private AsignacionMatricula asgMatricula;

    public CursaBD() {
        super(AsignacionMatricula.class);
        asgMatriculas = new DynamicList<>();
    }

    public DynamicList<AsignacionMatricula> getAsgMatriculasTodas() {
        asgMatriculas = all();
        return asgMatriculas;
    }
    
    public DynamicList<AsignacionMatricula> getAsgMatriculas() {
        return asgMatriculas;
    }

    public void setAsgMatriculas(DynamicList<AsignacionMatricula> asgMatriculas) {
        this.asgMatriculas = asgMatriculas;
    }

    public AsignacionMatricula getAsgMatricula() {
        if (asgMatricula == null) {
            asgMatricula = new AsignacionMatricula();
        }
        return asgMatricula;
    }

    public void setAsgMatricula(AsignacionMatricula asgMatricula) {
        this.asgMatricula = asgMatricula;
    }

    @Override
    public Boolean persist(AsignacionMatricula obj){
>>>>>>> Steven-Luna
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

<<<<<<< HEAD
    public DynamicList<Cursa> buscarLineal(DynamicList<Cursa> lista, String campo, String valorBuscado) throws EmptyException {
        Cursa cursas[] = lista.toArray();
        DynamicList<Cursa> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Cursa cursa = cursas[i];
            if (cursa.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(cursa);
=======
    public DynamicList<AsignacionMatricula> buscarLineal(DynamicList<AsignacionMatricula> lista, String campo, String valorBuscado) throws EmptyException {
        AsignacionMatricula matriculasAsignaturas[] = lista.toArray();
        DynamicList<AsignacionMatricula> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            AsignacionMatricula matriculaAsignatura = matriculasAsignaturas[i];
            if (matriculaAsignatura.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(matriculaAsignatura);
>>>>>>> Steven-Luna
            }
        }
        return listaBusqueda;
    }

<<<<<<< HEAD
    public Cursa buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Cursa> lista = all();
        int fin = lista.getLength() - 1;
        Cursa cursas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Cursa cursa = cursas[medio];
            int comparacion = cursa.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return cursa;
=======
    public AsignacionMatricula buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<AsignacionMatricula> lista = all();
        int fin = lista.getLength() - 1;
        AsignacionMatricula matriculasAsignaturas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            AsignacionMatricula matriculaAsignatura = matriculasAsignaturas[medio];
            int comparacion = matriculaAsignatura.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return matriculaAsignatura;
>>>>>>> Steven-Luna
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
