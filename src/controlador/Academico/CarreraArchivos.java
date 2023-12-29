package controlador.Academico;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Carrera;

public class CarreraArchivos extends DaoImplement<Carrera> {

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

    public Boolean persist() {
        carrera.setId(all().getLength());
        return persist(carrera);
    }
}
