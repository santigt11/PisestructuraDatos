package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Universidad;

public class UniversidadBD extends AdaptadorDao<Universidad> {

    private DynamicList<Universidad> universidades;
    private Universidad universidad;

    public UniversidadBD() {
        super(Universidad.class);
    }

    public DynamicList<Universidad> getUniversidades() {
        universidades = all();
        return universidades;
    }

    public void setUniversidades(DynamicList<Universidad> universidades) {
        this.universidades = universidades;
    }

    public Universidad getUniversidad() {
        if (universidad == null) {
            universidad = new Universidad();
        }
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    @Override
    public Boolean persist(Universidad obj) {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Universidad> buscarLineal(DynamicList<Universidad> lista, String campo, String valorBuscado) throws EmptyException {
        Universidad universidades[] = lista.toArray();
        DynamicList<Universidad> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Universidad universidad = universidades[i];
            if (universidad.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(universidad);
            }
        }
        return listaBusqueda;
    }

    public Universidad buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Universidad> lista = all();
        int fin = lista.getLength() - 1;
        Universidad universidades[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Universidad universidad = universidades[medio];
            int comparacion = universidad.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return universidad;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

    public boolean buscarNombre(String text) throws EmptyException {
        universidades = all();
        for (int i = 0; i < universidades.getLength(); i++) {
            if (universidades.getInfo(i).getNombre().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
