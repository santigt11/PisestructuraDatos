package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.CursaTutoria;

public class CursaTutoriaBD extends AdaptadorDao<CursaTutoria> {

    private DynamicList<CursaTutoria> cursaTutorias;
    private CursaTutoria cursaTutoria;

    public CursaTutoriaBD() {
        super(CursaTutoria.class);
        cursaTutorias = new DynamicList<>();
    }

    public DynamicList<CursaTutoria> getCursaTutoriasTodos() {
        cursaTutorias = all();
        return cursaTutorias;
    }
    
    public DynamicList<CursaTutoria> getCursaTutorias() {
        return cursaTutorias;
    }

    public void setAsgMatriculas(DynamicList<CursaTutoria> cursaTutorias) {
        this.cursaTutorias = cursaTutorias;
    }

    public CursaTutoria getCursaTutoria() {
        if (cursaTutoria == null) {
            cursaTutoria = new CursaTutoria();
        }
        return cursaTutoria;
    }

    public void setCursaTutoria(CursaTutoria asgMatricula) {
        this.cursaTutoria = asgMatricula;
    }

    @Override
    public Boolean persist(CursaTutoria obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<CursaTutoria> buscarLineal(DynamicList<CursaTutoria> lista, String campo, String valorBuscado) throws EmptyException {
        CursaTutoria matriculasAsignaturas[] = lista.toArray();
        DynamicList<CursaTutoria> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            CursaTutoria matriculaAsignatura = matriculasAsignaturas[i];
            if (matriculaAsignatura.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(matriculaAsignatura);
            }
        }
        return listaBusqueda;
    }

    public CursaTutoria buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<CursaTutoria> lista = all();
        int fin = lista.getLength() - 1;
        CursaTutoria matriculasAsignaturas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            CursaTutoria matriculaAsignatura = matriculasAsignaturas[medio];
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
