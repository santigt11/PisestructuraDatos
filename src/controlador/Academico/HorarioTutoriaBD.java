package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.HorarioTutoria;

public class HorarioTutoriaBD extends AdaptadorDao<HorarioTutoria> {

    private DynamicList<HorarioTutoria> horarios;
    private HorarioTutoria horario;

    public HorarioTutoriaBD() {
        super(HorarioTutoria.class);
    }

    public DynamicList<HorarioTutoria> getHorariosTutoria() {
        horarios = all();
        return horarios;
    }

    public void setHorarioTutoria(DynamicList<HorarioTutoria> horariosTutoria) {
        this.horarios = horariosTutoria;
    }

    public HorarioTutoria getHorarioTutoria() {
        if (horario == null) {
            horario = new HorarioTutoria();
        }
        return horario;
    }

    public void setFacultad(HorarioTutoria horarioTutoria) {
        this.horario = horarioTutoria;
    }

    @Override
    public Boolean persist(HorarioTutoria obj) {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<HorarioTutoria> buscarLineal(DynamicList<HorarioTutoria> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<HorarioTutoria> listaOrdenada = ordenarMerge(lista, campo, 0);
        HorarioTutoria horarios[] = listaOrdenada.toArray();
        DynamicList<HorarioTutoria> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            HorarioTutoria horario = horarios[i];
            if (horario.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(horario);
            }
        }
        return listaBusqueda;
    }

    public HorarioTutoria buscarBinaria(DynamicList<HorarioTutoria> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<HorarioTutoria> listaOrdenada = ordenarMerge(lista, campo, 0);
        int inicio = 0;
        int fin = listaOrdenada.getLength() - 1;
        HorarioTutoria horarios[] = listaOrdenada.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            HorarioTutoria horario = horarios[medio];
            int comparacion = horario.compareCampo(campo, valorBuscado);

            if (comparacion == 0) {
                return horario;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

    //MergeSort
    public DynamicList<HorarioTutoria> ordenarMerge(DynamicList<HorarioTutoria> lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<HorarioTutoria> izquierda = new DynamicList<>();
            DynamicList<HorarioTutoria> derecha = new DynamicList<>();
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

    private void mezclar(DynamicList<HorarioTutoria> lista, DynamicList<HorarioTutoria> list1, DynamicList<HorarioTutoria> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        HorarioTutoria[] izquierda = list1.toArray();
        HorarioTutoria[] derecha = list2.toArray();
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
