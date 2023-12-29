package controlador;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.Carrera;

public class CarreraControl {
    private Carrera carrera = new Carrera();
    private DynamicList<Carrera> carreras;

    public CarreraControl(Carrera carrera) {
        this.carrera = carrera;
    }

    public CarreraControl() {
        this.carreras = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getCarrera().setId(getCarreras().getLength());
            getCarreras().add(getCarrera());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer posVerificar() throws EmptyException {
        
        Integer bandera = 0;

        for (Integer i = 0; i <= this.carreras.getLength(); i++) {
            
            if (this.getCarreras().getInfo(i) == null) {
                bandera = i;
                break;
            }
        }
        return bandera;
    }

    public void imprimir() throws EmptyException {
        for (int i = 0; i < this.getCarreras().getLength(); i++) {
            System.out.println(getCarreras().getInfo(i));
        }
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

    public DynamicList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(DynamicList<Carrera> carreras) {
        this.carreras = carreras;
    }

    @Override
    public String toString() {
        return "Carrera: " + getCarrera().getNombre() + "Num. Ciclos: " + getCarrera().getNumCiclos();
    }
}
