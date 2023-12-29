package controlador;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.MallaCurricular;

public class MallaControl {
    private MallaCurricular malla = new MallaCurricular();
    private DynamicList<MallaCurricular> mallas;

    public MallaControl(MallaCurricular malla) {
        this.malla = malla;
    }

    public MallaControl() {
        this.mallas = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getMalla().setId(getMallas().getLength());
            getMallas().add(getMalla());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer posVerificar() throws EmptyException {
        
        Integer bandera = 0;

        for (Integer i = 0; i <= this.mallas.getLength(); i++) {
            
            if (this.getMallas().getInfo(i) == null) {
                bandera = i;
                break;
            }
        }
        return bandera;
    }

    public void imprimir() throws EmptyException {
        for (int i = 0; i < this.getMallas().getLength(); i++) {
            System.out.println(getMallas().getInfo(i));
        }
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

    public DynamicList<MallaCurricular> getMallas() {
        return mallas;
    }

    public void setMallas(DynamicList<MallaCurricular> mallas) {
        this.mallas = mallas;
    }

    @Override
    public String toString() {
        return "Pensum" + getMalla().getPensum() + "Descripcion: " + getMalla().getDescripcion();
    }
}
