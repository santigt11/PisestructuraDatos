package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Asignatura;

public class AsignaturaBD extends AdaptadorDao<Asignatura> {

    private DynamicList<Asignatura> asignaturas;
    private Asignatura asignatura;

    public AsignaturaBD() {
        super(Asignatura.class);
        asignaturas = new DynamicList<>();
    }

    public AsignaturaBD(DynamicList<Asignatura> asignaturas, Asignatura asignatura, Class clazz) {
        super(clazz);
        this.asignaturas = asignaturas;
        this.asignatura = asignatura;
    }

    public DynamicList<Asignatura> getAsignaturas() {
        asignaturas = all();
        return asignaturas;
    }

    public void setAsignaturas(DynamicList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public Boolean persist(Asignatura obj) {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Asignatura> buscarLineal(DynamicList<Asignatura> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<Asignatura> listaOrdenada = ordenarMerge(lista, campo, 0);
        Asignatura asignaturas[] = listaOrdenada.toArray();
        DynamicList<Asignatura> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            Asignatura asignatura = asignaturas[i];
            if (asignatura.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(asignatura);
            }
        }
        return listaBusqueda;
    }

    public Asignatura buscarBinaria(DynamicList<Asignatura> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<Asignatura> listaOrdenada = ordenarMerge(lista, campo, 0);
        int inicio = 0;
        int fin = listaOrdenada.getLength() - 1;
        Asignatura asignaturas[] = listaOrdenada.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Asignatura asignatura = asignaturas[medio];
            int comparacion = asignatura.compareCampo(campo, valorBuscado);

            if (comparacion == 0) {
                return asignatura;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }
    
    //MergeSort
    public DynamicList<Asignatura> ordenarMerge(DynamicList<Asignatura> lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<Asignatura> izquierda = new DynamicList<>();
            DynamicList<Asignatura> derecha = new DynamicList<>();
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

    private void mezclar(DynamicList<Asignatura> lista, DynamicList<Asignatura> list1, DynamicList<Asignatura> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        Asignatura[] izquierda = list1.toArray();
        Asignatura[] derecha = list2.toArray();
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
    
    public boolean buscarCodigo(String text) throws EmptyException {
        asignaturas = all();
        for (int i = 0; i < asignaturas.getLength(); i++) {
            if (asignaturas.getInfo(i).getCodigo().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
