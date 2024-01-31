package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.MallaCurricular;

public class MallaArchivos extends AdaptadorDao<MallaCurricular> {

    private DynamicList<MallaCurricular> mallas;
    private MallaCurricular malla;

    public MallaArchivos() {
        super(MallaCurricular.class);
    }

    public DynamicList<MallaCurricular> getMallas() {
        mallas = all();
        return mallas;
    }

    public void setMallas(DynamicList<MallaCurricular> mallas) {
        this.mallas = mallas;
    }

    public MallaCurricular getMalla() {
        if (malla == null) {
            malla = new MallaCurricular();
        }
        return malla;
    }

    public void setMalla(MallaCurricular malla) {
        this.malla = malla;
    }

    @Override
    public Boolean persist(MallaCurricular obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<MallaCurricular> buscarLineal(DynamicList<MallaCurricular> lista, String campo, String valorBuscado) throws EmptyException {
        MallaCurricular mallas[] = lista.toArray();
        DynamicList<MallaCurricular> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            MallaCurricular malla = mallas[i];
            if (malla.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(malla);
            }
        }
        return listaBusqueda;
    }

    public MallaCurricular buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<MallaCurricular> lista = all();
        int fin = lista.getLength() - 1;
        MallaCurricular mallas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            MallaCurricular malla = mallas[medio];
            int comparacion = malla.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return malla;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
