package vista.listas.util;

import controlador.Academico.AsignaturaArchivos;
import controlador.Academico.ContratoArchivos;
import controlador.Academico.HorarioArchivos;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.JComboBox;
import modelo.Facultad;
import modelo.Carrera;
import modelo.MallaCurricular;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Asignatura;
import modelo.Contrato;

public class Utilvista {
    
    public static void cargarComboHorario(JComboBox cbx) throws EmptyException{
        HorarioArchivos horarioControl = new HorarioArchivos();
        horarioControl.setHorarios(horarioControl.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < horarioControl.getHorarios().getLength(); i++) {
                cbx.addItem(horarioControl.getHorarios().getInfo(i));
        }
    }
    
    public static void cargarComboFacultades(JComboBox cbx) throws EmptyException{
        FacultadControl fc = new FacultadControl();
        controlador.Academico.FacultadArchivos vv = new controlador.Academico.FacultadArchivos();
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
        controlador.Academico.CarreraArchivos vv = new controlador.Academico.CarreraArchivos();
        cc.setCarreras(vv.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < cc.getCarreras().getLength(); i++) {
                cbx.addItem(cc.getCarreras().getInfo(i));
        }
    }

    public static Carrera obtenerCarreraControl(JComboBox cbx){
        return (Carrera) cbx.getSelectedItem();
    }

    public static void cargarComboMalla(JComboBox cbx, Carrera carrera) throws EmptyException{
        MallaControl mc = new MallaControl();
        controlador.Academico.MallaArchivos vv = new controlador.Academico.MallaArchivos();
        mc.setMallas(vv.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < mc.getMallas().getLength(); i++) {
            if (mc.getMallas().getInfo(i).getIdCarrera().equals(carrera.getId())) {
                cbx.addItem(mc.getMallas().getInfo(i));
            }
        }
    }

    public static MallaCurricular obtenerMallaControl(JComboBox cbx){
        return (MallaCurricular) cbx.getSelectedItem();
    }

    public static void cargarComboPeriodo(JComboBox cbx) throws EmptyException{
        PeriodoAControl pc = new PeriodoAControl();
        PeriodoArchivos vv = new PeriodoArchivos();
        pc.setPeriodos(vv.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < pc.getPeriodos().getLength(); i++) {
                cbx.addItem(pc.getPeriodos().getInfo(i));
        }
    }

    public static void cargarComboMatricula(JComboBox cbx) throws EmptyException{
        MatriculaControl mc = new MatriculaControl();
        MatriculaArchivos vv = new MatriculaArchivos();
        mc.setMatriculas(vv.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < mc.getMatriculas().getLength(); i++) {
                cbx.addItem(mc.getMatriculas().getInfo(i));
        }
    }

    public static void cargarListaFacultades(JList lst) throws EmptyException {
        FacultadControl fc = new FacultadControl();
        controlador.Academico.FacultadArchivos af = new controlador.Academico.FacultadArchivos();
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

    public static void cargarComboAsignatura(DynamicList<Contrato> contratos, JComboBox cbx) throws EmptyException{
        ContratoArchivos ca = new ContratoArchivos();
        DynamicList<Asignatura> asignaturas = new DynamicList<>();
        Contrato contratosArray[] = contratos.toArray();
        cbx.removeAllItems();
        if (contratos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }else{
            for (int i = 0; i < contratos.getLength(); i++) {
                asignaturas.add(contratosArray[i].getCodigoAsignatura());
            }
        }
    }
    
    public static void cargarcomboRolesHorario(JComboBox cbx) throws EmptyException{
        HorarioArchivos ha = new HorarioArchivos();
        cbx.removeAllItems();
        if (ha.getHorariosTodos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        }else{
            for (int i = 0; i < ha.getHorarios().getLength(); i++) {
                cbx.addItem(ha.getHorarios().getInfo(i));
            }
        }
    }
    
    public static Carrera obtenerCarreraControl(JList lst, Facultad facultad){
        Object carrera = lst.getSelectedValue();
        return (Carrera) carrera;
    }
    
    public static void cargarListaMallas(JList lst, Carrera carrera) throws EmptyException {
        MallaControl mc = new MallaControl();
        controlador.Academico.MallaArchivos am = new controlador.Academico.MallaArchivos();
        mc.setMallas(am.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < mc.getMallas().getLength(); i++) {
            if (mc.getMallas().getInfo(i).getIdCarrera().equals(carrera.getId())) {
                modeloLista.addElement(mc.getMallas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaAsignaturas(JList lst, MallaCurricular malla) throws EmptyException {
        AsignaturaControl ac = new AsignaturaControl();
        controlador.Academico.AsignaturaArchivos aa = new controlador.Academico.AsignaturaArchivos();
        ac.setAsignaturas(aa.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < ac.getAsignaturas().getLength(); i++) {
            if (ac.getAsignaturas().getInfo(i).getIdMalla().equals(malla.getId())) {
                modeloLista.addElement(ac.getAsignaturas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaDocentes(JList lst) throws EmptyException {
        PersonaArchivos ap = new PersonaArchivos();
        PersonaControl pc = new PersonaControl();
        pc.setPersonas(ap.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < pc.getPersonas().getLength(); i++) {
            if (pc.getPersonas().getInfo(i).getRol().toString().equals("DOCENTE")) {
                modeloLista.addElement(pc.getPersonas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaEstudiantes(JList lst) throws EmptyException {
        PersonaArchivos ap = new PersonaArchivos();
        PersonaControl pc = new PersonaControl();
        pc.setPersonas(ap.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < pc.getPersonas().getLength(); i++) {
            if (pc.getPersonas().getInfo(i).getRol().toString().equals("ESTUDIANTE")) {
                modeloLista.addElement(pc.getPersonas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaMatriculasAsignaturas(JList lst, MatriculaControl matriculaControl, MatriculaAsignaturaControl matriculaAsignaturaControl) throws EmptyException {
        MatriculaAsignaturaArchivos maa = new MatriculaAsignaturaArchivos();
        matriculaAsignaturaControl.setAsgMatriculas(maa.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < matriculaAsignaturaControl.getAsgMatriculas().getLength(); i++) {
            if (matriculaAsignaturaControl.getAsgMatriculas().getInfo(i).getIdMatricula().equals(matriculaControl.getMatricula().getId())) {
                modeloLista.addElement(matriculaAsignaturaControl.getAsgMatriculas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void limpiarLista(JList lst){
        DefaultListModel modeloLista = new DefaultListModel();
        lst.setModel(modeloLista);
    }
}
