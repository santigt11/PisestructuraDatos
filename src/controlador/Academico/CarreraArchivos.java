package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Carrera;

public class CarreraArchivos extends AdaptadorDao<Carrera> {

    private DynamicList<Carrera> carreras;
    private Carrera carrera;

    public CarreraArchivos() {
        super(Carrera.class);
    }

    public DynamicList<Carrera> getCarreras() {
        carreras = all();
        return carreras;
    }

    public void setCarreras(DynamicList<Carrera> carreras) {
        this.carreras = carreras;
    }

    public Carrera getCarrera() {
        if (carrera == null) {
            carrera = new Carrera();
        }
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public Integer persist(Carrera obj) throws Exception {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Carrera> buscarLineal(DynamicList<Carrera> lista, String campo, String valorBuscado) throws EmptyException {
        Carrera carreras[] = lista.toArray();
        DynamicList<Carrera> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Carrera carrera = carreras[i];
            if (carrera.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(carrera);
            }
        }
        return listaBusqueda;
    }

    public Carrera buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Carrera> lista = all();
        int fin = lista.getLength() - 1;
        Carrera carreras[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Carrera carrera = carreras[medio];
            int comparacion = carrera.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return carrera;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
