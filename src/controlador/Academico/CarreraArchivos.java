package controlador.Academico;

import controlador.TDA.listas.DynamicList;
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
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
