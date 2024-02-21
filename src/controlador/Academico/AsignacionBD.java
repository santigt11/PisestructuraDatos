package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Asignacion;

public class AsignacionBD extends AdaptadorDao<Asignacion> {

    private DynamicList<Asignacion> asignaciones;
    private Asignacion asignacion;

    public AsignacionBD() {
        super(Asignacion.class);
    }

    public DynamicList<Asignacion> getAsignacionesTodos() {
        asignaciones = all();
        return asignaciones;
    }

    public DynamicList<Asignacion> getAsignaciones() {
        if (asignaciones == null) {
            asignaciones = new DynamicList<>();
        }
        return asignaciones;
    }

    public void setAsignaciones(DynamicList<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Asignacion getAsignacion() {
        if (asignacion == null) {
            asignacion = new Asignacion();
        }
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    @Override
    public Boolean persist(Asignacion obj) {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Asignacion> buscarLineal(DynamicList<Asignacion> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<Asignacion> listaOrdenada = ordenarMerge(lista, campo, 0);
        Asignacion asignaciones[] = listaOrdenada.toArray();
        DynamicList<Asignacion> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            Asignacion asignacion = asignaciones[i];
            if (asignacion.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(asignacion);
            }
        }
        return listaBusqueda;
    }

    public Asignacion buscarBinaria(DynamicList<Asignacion> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<Asignacion> listaOrdenada = ordenarMerge(lista, campo, 0);
        int inicio = 0;
        int fin = listaOrdenada.getLength() - 1;
        Asignacion asignaciones[] = listaOrdenada.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Asignacion asignacion = asignaciones[medio];
            int comparacion = asignacion.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return asignacion;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

    public DynamicList<Asignacion> ordenarMerge(DynamicList<Asignacion> lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<Asignacion> izquierda = new DynamicList<>();
            DynamicList<Asignacion> derecha = new DynamicList<>();
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

    private void mezclar(DynamicList<Asignacion> lista, DynamicList<Asignacion> list1, DynamicList<Asignacion> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        Asignacion[] izquierda = list1.toArray();
        Asignacion[] derecha = list2.toArray();
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
