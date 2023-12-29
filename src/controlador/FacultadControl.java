package controlador;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.Facultad;

public class FacultadControl {
    private Facultad facultad = new Facultad();
    private DynamicList<Facultad> facultades;

    public FacultadControl(Facultad facultad) {
        this.facultad = facultad;
    }

    public FacultadControl() {
        this.facultades = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getFacultad().setId(getFacultades().getLength());
            getFacultades().add(getFacultad());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public Integer posVerificar() throws EmptyException {
//        
//        Integer bandera = 0;
//
//        for (Integer i = 0; i <= this.facultades.getLength(); i++) {
//            
//            if (this.getFacultades().getInfo(i) == null) {
//                bandera = i;
//                break;
//            }
//        }
//        return bandera;
//    }
//
//    public void imprimir() throws EmptyException {
//        for (int i = 0; i < this.getFacultades().getLength(); i++) {
//            System.out.println(getFacultades().getInfo(i));
//        }
//    }

    public Facultad getFacultad() {
        if (facultad == null) {
            facultad = new Facultad();
        }
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public DynamicList<Facultad> getFacultades() {
        return facultades;
    }

    public void setFacultades(DynamicList<Facultad> facultades) {
        this.facultades = facultades;
    }

////    @Override
////    public String toString() {
////        return getFacultad().getNombre();
////    }
}
