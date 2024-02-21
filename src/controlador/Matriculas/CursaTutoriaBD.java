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
        if (cursaTutorias == null) {
            cursaTutorias = new DynamicList<>();
        }
        return cursaTutorias;
    }

    public void setCursaTutorias(DynamicList<CursaTutoria> cursaTutorias) {
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
        DynamicList<CursaTutoria> listaOrdenada = ordenarMerge(lista, campo, 0);
        CursaTutoria cursaTutorias[] = listaOrdenada.toArray();
        DynamicList<CursaTutoria> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            CursaTutoria cursaTutoria = cursaTutorias[i];
            if (cursaTutoria.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(cursaTutoria);
            }
        }
        return listaBusqueda;
    }

    public CursaTutoria buscarBinaria(DynamicList<CursaTutoria> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<CursaTutoria> listaOrdenada = ordenarMerge(lista, campo, 0);
        int inicio = 0;
        int fin = listaOrdenada.getLength() - 1;
        CursaTutoria cursaTutorias[] = listaOrdenada.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            CursaTutoria cursaTutoria = cursaTutorias[medio];
            int comparacion = cursaTutoria.compareCampo(campo, valorBuscado);

            if (comparacion == 0) {
                return cursaTutoria;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }
    
    public DynamicList<CursaTutoria> ordenarMerge(DynamicList<CursaTutoria> lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<CursaTutoria> izquierda = new DynamicList<>();
            DynamicList<CursaTutoria> derecha = new DynamicList<>();
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

    private void mezclar(DynamicList<CursaTutoria> lista, DynamicList<CursaTutoria> list1, DynamicList<CursaTutoria> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        CursaTutoria[] izquierda = list1.toArray();
        CursaTutoria[] derecha = list2.toArray();
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
