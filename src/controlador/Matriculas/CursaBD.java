package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
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
    public Boolean persist(Cursa obj) {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Cursa> buscarLineal(DynamicList<Cursa> lista, String campo, String valorBuscado) throws EmptyException {
        Cursa cursas[] = lista.toArray();
        lista = ordenarMerge(lista, "id", 0);
        DynamicList<Cursa> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Cursa cursa = cursas[i];
            if (cursa.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(cursa);
            }
        }
        return listaBusqueda;
    }

    public Cursa buscarBinaria(DynamicList<Cursa> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<Cursa> listaOrdenada = ordenarMerge(lista, campo, 0);
        int inicio = 0;
        int fin = listaOrdenada.getLength() - 1;
        Cursa cursas[] = listaOrdenada.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Cursa cursa = cursas[medio];
            int comparacion = cursa.compareCampo(campo, valorBuscado);

            if (comparacion == 0) {
                return cursa;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

    public DynamicList<Cursa> ordenarMerge(DynamicList<Cursa> lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<Cursa> izquierda = new DynamicList<>();
            DynamicList<Cursa> derecha = new DynamicList<>();
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

    private void mezclar(DynamicList<Cursa> lista, DynamicList<Cursa> list1, DynamicList<Cursa> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        Cursa[] izquierda = list1.toArray();
        Cursa[] derecha = list2.toArray();
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
