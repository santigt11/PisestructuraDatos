package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.MatriculaAsignatura;

public class MatriculaAsignaturaArchivos extends AdaptadorDao<MatriculaAsignatura> {

    private DynamicList<MatriculaAsignatura> asgMatriculas;
    private MatriculaAsignatura asgMatricula;

    public MatriculaAsignaturaArchivos() {
        super(MatriculaAsignatura.class);
    }

    public DynamicList<MatriculaAsignatura> getAsgMatriculasTodas() {
        asgMatriculas = all();
        return asgMatriculas;
    }

    public DynamicList<MatriculaAsignatura> getAsgMatriculas() {
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
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<MatriculaAsignatura> buscarLineal(DynamicList<MatriculaAsignatura> lista, String campo, String valorBuscado) throws EmptyException {
        MatriculaAsignatura matriculasAsignaturas[] = lista.toArray();
        DynamicList<MatriculaAsignatura> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            MatriculaAsignatura matriculaAsignatura = matriculasAsignaturas[i];
            if (matriculaAsignatura.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(matriculaAsignatura);
            }
        }
        return listaBusqueda;
    }

    public MatriculaAsignatura buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<MatriculaAsignatura> lista = all();
        int fin = lista.getLength() - 1;
        MatriculaAsignatura matriculasAsignaturas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            MatriculaAsignatura matriculaAsignatura = matriculasAsignaturas[medio];
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
