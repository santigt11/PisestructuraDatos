package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Facultad;

public class FacultadArchivos extends AdaptadorDao<Facultad> {

    private DynamicList<Facultad> facultades;
    private Facultad facultad;

    public FacultadArchivos() {
        super(Facultad.class);
    }

    public DynamicList<Facultad> getFacultades() {
        facultades = all();
        return facultades;
    }

    public void setFacultades(DynamicList<Facultad> facultades) {
        this.facultades = facultades;
    }

    public Facultad getFacultad() {
        if (facultad == null) {
            facultad = new Facultad();
        }
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @Override
    public Boolean persist(Facultad obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Facultad> buscarLineal(DynamicList<Facultad> lista, String campo, String valorBuscado) throws EmptyException {
        Facultad facultades[] = lista.toArray();
        DynamicList<Facultad> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Facultad facultad = facultades[i];
            if (facultad.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(facultad);
            }
        }
        return listaBusqueda;
    }

    public Facultad buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Facultad> lista = all();
        int fin = lista.getLength() - 1;
        Facultad facultades[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Facultad facultad = facultades[medio];
            int comparacion = facultad.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return facultad;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
