package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
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
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<AsignacionMatricula> buscarLineal(DynamicList<AsignacionMatricula> lista, String campo, String valorBuscado) throws EmptyException {
        AsignacionMatricula matriculasAsignaturas[] = lista.toArray();
        DynamicList<AsignacionMatricula> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            AsignacionMatricula matriculaAsignatura = matriculasAsignaturas[i];
            if (matriculaAsignatura.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(matriculaAsignatura);
            }
        }
        return listaBusqueda;
    }

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
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
