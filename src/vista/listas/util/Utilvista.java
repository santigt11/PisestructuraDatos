package vista.listas.util;

import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.JComboBox;
import modelo.Facultad;
import controlador.FacultadControl;
import modelo.Carrera;
import controlador.CarreraControl;
import controlador.MallaControl;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class Utilvista {
             
    public static void cargarComboFacultades(JComboBox cbx) throws EmptyException{
        FacultadControl fc = new FacultadControl();
        controlador.Academico.FacultadControl vv = new controlador.Academico.FacultadControl();
        fc.setFacultades(vv.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < fc.getFacultades().getLength(); i++) {
                cbx.addItem(fc.getFacultades().getInfo(i));
        }
    }

    public static Facultad obtenerFacultadControl(JComboBox cbx){
        return (Facultad) cbx.getSelectedItem();
    }
    
    public static void cargarComboCarreras(JComboBox cbx) throws EmptyException{
        CarreraControl cc = new CarreraControl();
        controlador.Academico.CarreraControl vv = new controlador.Academico.CarreraControl();
        cc.setCarreras(vv.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < cc.getCarreras().getLength(); i++) {
                cbx.addItem(cc.getCarreras().getInfo(i));
        }
    }

    public static Carrera obtenerCarreraControl(JComboBox cbx){
        return (Carrera) cbx.getSelectedItem();
    }

    public static void cargarListaFacultades(JList lst) throws EmptyException {
        FacultadControl fc = new FacultadControl();
        controlador.Academico.FacultadControl af = new controlador.Academico.FacultadControl();
        fc.setFacultades(af.all());

        DefaultListModel modeloLista = new DefaultListModel();

        for (Integer i = 0; i < fc.getFacultades().getLength(); i++) {
            modeloLista.addElement(fc.getFacultades().getInfo(i));
        }

        lst.setModel(modeloLista);
    }

    public static Facultad obtenerCarreraControl(JList lst){
        Object facultad = lst.getSelectedValue();
        return (Facultad) facultad;
    }

    public static void cargarListaCarreras(JList lst, Facultad facultad) throws EmptyException {
        CarreraControl cc = new CarreraControl();
        controlador.Academico.CarreraControl ac = new controlador.Academico.CarreraControl();
        cc.setCarreras(ac.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < cc.getCarreras().getLength(); i++) {
            if (cc.getCarreras().getInfo(i).getIdFacultad().equals(facultad.getId())) {
                modeloLista.addElement(cc.getCarreras().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static Carrera obtenerCarreraControl(JList lst, Facultad facultad){
        Object carrera = lst.getSelectedValue();
        return (Carrera) carrera;
    }
    
    public static void cargarListaMallas(JList lst, Carrera carrera) throws EmptyException {
        MallaControl mc = new MallaControl();
        controlador.Academico.MallaControl am = new controlador.Academico.MallaControl();
        mc.setMallas(am.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < mc.getMallas().getLength(); i++) {
            if (mc.getMallas().getInfo(i).getIdCarrera().equals(carrera.getId())) {
                modeloLista.addElement(mc.getMallas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }
    
    
    
    public static void limpiarLista(JList lst){
        DefaultListModel modeloLista = new DefaultListModel();
        lst.setModel(modeloLista);
    }
}
