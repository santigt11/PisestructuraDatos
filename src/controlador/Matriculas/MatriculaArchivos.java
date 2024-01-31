package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Matricula;

public class MatriculaArchivos extends AdaptadorDao<Matricula> {

    private DynamicList<Matricula> matriculas;
    private Matricula matricula;

    public MatriculaArchivos() {
        super(Matricula.class);
        matriculas = new DynamicList<>();
    }

    public DynamicList<Matricula> getMatriculasTodas() {
        matriculas = all();
        return matriculas;
    }
    
    public DynamicList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public Boolean persist(Matricula obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Matricula> buscarLineal(DynamicList<Matricula> lista, String campo, String valorBuscado) throws EmptyException {
        Matricula matriculas[] = lista.toArray();
        DynamicList<Matricula> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Matricula matricula = matriculas[i];
            if (matricula.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(matricula);
            }
        }
        return listaBusqueda;
    }

    public Matricula buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Matricula> lista = all();
        int fin = lista.getLength() - 1;
        Matricula matriculas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Matricula matricula = matriculas[medio];
            int comparacion = matricula.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return matricula;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
