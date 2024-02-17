package vista.listas.util;

import controlador.Academico.AsignaturaArchivos;
import controlador.Academico.CarreraArchivos;
import controlador.Academico.ContratoArchivos;
import controlador.Academico.FacultadArchivos;
import controlador.Tutorias.HorarioArchivos;
import controlador.Academico.MallaArchivos;
import controlador.Matriculas.MatriculaArchivos;
import controlador.Matriculas.MatriculaAsignaturaArchivos;
import controlador.Matriculas.PeriodoArchivos;
import controlador.Admin.PersonaArchivos;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Tutorias.TutoriaMatriculaArchivos;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import modelo.Facultad;
import modelo.Carrera;
import modelo.MallaCurricular;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Asignatura;
import modelo.Contrato;
import modelo.Matricula;
import modelo.AsignacionMatricula;
import modelo.Persona;
import modelo.TutoriaMatricula;

public class Utilvista {

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void cargarComboHorario(JComboBox cbx) throws EmptyException {
        HorarioArchivos horarioControl = new HorarioArchivos();
        horarioControl.setHorarios(horarioControl.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < horarioControl.getHorarios().getLength(); i++) {
            cbx.addItem(horarioControl.getHorarios().getInfo(i));
        }
    }

    public static void cargarComboFacultades(JComboBox cbx) throws EmptyException {
        FacultadArchivos fa = new FacultadArchivos();
        fa.setFacultades(fa.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < fa.getFacultades().getLength(); i++) {
            cbx.addItem(fa.getFacultades().getInfo(i));
        }

    }

    public static Facultad obtenerFacultadControl(JComboBox cbx) {
        return (Facultad) cbx.getSelectedItem();
    }

    public static void cargarComboCarreras(JComboBox cbx) throws EmptyException {
        CarreraArchivos ca = new CarreraArchivos();
        ca.setCarreras(ca.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < ca.getCarreras().getLength(); i++) {
            cbx.addItem(ca.getCarreras().getInfo(i));
        }
    }

    public static Carrera obtenerCarreraControl(JComboBox cbx) {
        return (Carrera) cbx.getSelectedItem();
    }

    public static void cargarComboMalla(JComboBox cbx, Carrera carrera) throws EmptyException {
        MallaArchivos ma = new MallaArchivos();
        ma.setMallas(ma.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < ma.getMallas().getLength(); i++) {
            if (ma.getMallas().getInfo(i).getCarrera_ID().equals(carrera.getId())) {
                cbx.addItem(ma.getMallas().getInfo(i));
            }
        }
    }

    public static MallaCurricular obtenerMallaControl(JComboBox cbx) {
        return (MallaCurricular) cbx.getSelectedItem();
    }

    public static void cargarComboPeriodos(JComboBox cbx) throws EmptyException {
        PeriodoArchivos pa = new PeriodoArchivos();
        pa.setPeriodos(pa.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < pa.getPeriodos().getLength(); i++) {
            cbx.addItem(pa.getPeriodos().getInfo(i));
        }
    }

    //Matricula
    public static void cargarComboMatriculas(JComboBox cbx) throws EmptyException {
        MatriculaArchivos ma = new MatriculaArchivos();
        ma.setMatriculas(ma.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < ma.getMatriculas().getLength(); i++) {
            cbx.addItem(ma.getMatriculas().getInfo(i));
        }
    }

    public static Matricula obtenerMatriculaControl(JComboBox cbx) {
        return (Matricula) cbx.getSelectedItem();
    }

    //Matricula-Asignatura
    public static void cargarComboMatriculasAsignaturas(JComboBox cbx) throws EmptyException {
        MatriculaAsignaturaArchivos maa = new MatriculaAsignaturaArchivos();
        maa.setAsgMatriculas(maa.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < maa.getAsgMatriculas().getLength(); i++) {
            cbx.addItem(maa.getAsgMatriculas().getInfo(i));
        }
    }

    public static AsignacionMatricula obtenerMatriculaAsignaturaControl(JComboBox cbx) {
        return (AsignacionMatricula) cbx.getSelectedItem();
    }

    public static void cargarListaFacultades(JList lst) throws EmptyException {
        FacultadArchivos fa = new FacultadArchivos();
        fa.setFacultades(fa.all());

        DefaultListModel modeloLista = new DefaultListModel();

        for (Integer i = 0; i < fa.getFacultades().getLength(); i++) {
            modeloLista.addElement(fa.getFacultades().getInfo(i));
        }

        lst.setModel(modeloLista);
    }

    public static Facultad obtenerCarreraControl(JList lst) {
        Object facultad = lst.getSelectedValue();
        return (Facultad) facultad;
    }

    public static void cargarComboAsignaturaContrato(DynamicList<Contrato> contratos, JComboBox cbx) throws EmptyException {
        AsignaturaArchivos aa = new AsignaturaArchivos();
        Contrato contratosArray[] = contratos.toArray();
        cbx.removeAllItems();
        if (contratos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        } else {
            for (int i = 0; i < contratos.getLength(); i++) {
                cbx.addItem(aa.buscarBinaria("codigo", contratosArray[i].getAsignatura_CODIGO()));
            }
        }
    }

    public static void cargarcomboRolesHorario(JComboBox cbx) throws EmptyException {
        HorarioArchivos ha = new HorarioArchivos();
        cbx.removeAllItems();
        if (ha.getHorariosTodos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        } else {
            for (int i = 0; i < ha.getHorarios().getLength(); i++) {
                cbx.addItem(ha.getHorarios().getInfo(i));
            }
        }
    }

    public static Carrera obtenerCarreraControl(JList lst, Facultad facultad) {
        Object carrera = lst.getSelectedValue();
        return (Carrera) carrera;
    }

    public static void cargarListaMallas(JList lst, Carrera carrera) throws EmptyException {
        MallaArchivos ma = new MallaArchivos();
        ma.setMallas(ma.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < ma.getMallas().getLength(); i++) {
            if (ma.getMallas().getInfo(i).getCarrera_ID().equals(carrera.getId())) {
                modeloLista.addElement(ma.getMallas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaAsignaturas(JList lst, MallaCurricular malla) throws EmptyException {
        AsignaturaArchivos aa = new AsignaturaArchivos();
        aa.setAsignaturas(aa.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < aa.getAsignaturas().getLength(); i++) {
            if (aa.getAsignaturas().getInfo(i).getMallaCurricular_ID().equals(malla.getId())) {
                modeloLista.addElement(aa.getAsignaturas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaDocentes(JList lst) throws EmptyException {
        PersonaArchivos pa = new PersonaArchivos();
        pa.setPersonas(pa.all());
        System.out.println(pa.all().toString());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < pa.getPersonas().getLength(); i++) {
            if (pa.getPersonas().getInfo(i).getRol().toString().equals("DOCENTE")) {
                modeloLista.addElement(pa.getPersonas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaEstudiantes(JList lst) throws EmptyException {
        PersonaArchivos ea = new PersonaArchivos();
        ea.setPersonas(ea.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < ea.getPersonas().getLength(); i++) {
            if (ea.getPersonas().getInfo(i).getRol().toString().equals("ESTUDIANTE")) {
                modeloLista.addElement(ea.getPersonas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaTutoriaMatricula(DynamicList<TutoriaMatricula> tutoriasM, JList lst) throws EmptyException {
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < tutoriasM.getLength(); i++) {
            modeloLista.addElement(tutoriasM);
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaMatriculasAsignaturas(JList lst, MatriculaArchivos matriculaControl, MatriculaAsignaturaArchivos matriculaAsignaturaControl) throws EmptyException {
        MatriculaAsignaturaArchivos maa = new MatriculaAsignaturaArchivos();
        matriculaAsignaturaControl.setAsgMatriculas(maa.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < matriculaAsignaturaControl.getAsgMatriculas().getLength(); i++) {
            if (matriculaAsignaturaControl.getAsgMatriculas().getInfo(i).getMatricula_ID().equals(matriculaControl.getMatricula().getId())) {
                modeloLista.addElement(matriculaAsignaturaControl.getAsgMatriculas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaPersonas(DynamicList<Persona> lista, JList lst) throws EmptyException {
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < lista.getLength(); i++) {
            modeloLista.addElement(lista.getInfo(i));
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaCarreras(JList lst, Facultad facultad) throws EmptyException {
        CarreraArchivos ca = new CarreraArchivos();
        ca.setCarreras(ca.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < ca.getCarreras().getLength(); i++) {
            if (ca.getCarreras().getInfo(i).getFacultad_ID().equals(facultad.getId())) {
                modeloLista.addElement(ca.getCarreras().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void limpiarLista(JList lst) {
        DefaultListModel modeloLista = new DefaultListModel();
        lst.setModel(modeloLista);
    }
}
