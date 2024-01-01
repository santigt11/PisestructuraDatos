package controlador;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.Asignatura;

public class AsignaturaControl {
    private Asignatura asignatura = new Asignatura();
    private DynamicList<Asignatura> asignaturas;

    public AsignaturaControl(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public AsignaturaControl() {
        this.asignaturas = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getAsignatura().setId(getAsignaturas().getLength());
            getAsignaturas().add(getAsignatura());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer posVerificar() throws EmptyException {
        
        Integer bandera = 0;

        for (Integer i = 0; i <= this.asignaturas.getLength(); i++) {
            
            if (this.getAsignaturas().getInfo(i) == null) {
                bandera = i;
                break;
            }
        }
        return bandera;
    }

    public void imprimir() throws EmptyException {
        for (int i = 0; i < this.getAsignaturas().getLength(); i++) {
            System.out.println(getAsignaturas().getInfo(i));
        }
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

    public DynamicList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setCarreras(DynamicList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "Codigp: " + getAsignatura().getCodigo() + "Asignatura: " + getAsignatura().getNombre();
    }
}
