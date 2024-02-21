package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Matricula;

public class MatriculaBD extends AdaptadorDao<Matricula> {

    private DynamicList<Matricula> matriculas;
    private Matricula matricula;

    public MatriculaBD() {
        super(Matricula.class);
        matriculas = new DynamicList<>();
    }

    public DynamicList<Matricula> getMatriculasTodas() {
        matriculas = all();
        return matriculas;
    }
    
    public DynamicList<Matricula> getMatriculas() {
        if (matriculas == null) {
            matriculas = new DynamicList<>();
        }
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
        DynamicList<Matricula> lista = ordenarMerge(all(), "id", 1);
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
    
    //MergeSort
    public DynamicList<Matricula> ordenarMerge(DynamicList<Matricula    > lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<Matricula> izquierda = new DynamicList<>();
            DynamicList<Matricula> derecha = new DynamicList<>();
            int mitad = lista.getLength() / 2;
            for (int i = 0; i < mitad; i++) {
                izquierda.add(lista.getInfo(i));
            }
            for (int i = mitad; i < lista.getLength(); i++) {
                derecha.add(lista.getInfo(i));
            }
            izquierda = ordenarMerge(izquierda, field, tipo);
            derecha = ordenarMerge(derecha, field, tipo);
            mezclar(lista, izquierda, derecha, field, tipo);
        }
        return lista;
    }

    private void mezclar(DynamicList<Matricula> lista, DynamicList<Matricula> list1, DynamicList<Matricula> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        Matricula[] izquierda = list1.toArray();
        Matricula[] derecha = list2.toArray();
        while (indiceIzq < izquierda.length && indiceDer < derecha.length) {
            if (izquierda[indiceIzq].compare(derecha[indiceDer], field, tipo)) {
                lista.merge(izquierda[indiceIzq], indiceLista);
                indiceIzq += 1;
            } else {
                lista.merge(derecha[indiceDer], indiceLista);
                indiceDer += 1;
            }
            indiceLista += 1;
        }

        while (indiceIzq < izquierda.length) {
            lista.merge(izquierda[indiceIzq], indiceLista);
            indiceIzq += 1;
            indiceLista += 1;
        }

        while (indiceDer < derecha.length) {
            lista.merge(derecha[indiceDer], indiceLista);
            indiceDer += 1;
            indiceLista += 1;
        }
    }
}
