package vista.listas.util;

import controlador.Academico.AsignaturaBD;
import controlador.Academico.CarreraBD;
import controlador.Academico.AsignacionBD;
import controlador.Academico.FacultadBD;
import controlador.Tutorias.HorarioBD;
import controlador.Academico.MallaBD;
import controlador.Matriculas.MatriculaBD;
import controlador.Matriculas.CursaTutoriaBD;
import controlador.Matriculas.PeriodoBD;
import controlador.Admin.PersonaBD;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Tutorias.AsignacionMatriculaBD;
import controlador.Tutorias.TutoriaBD;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import modelo.Facultad;
import modelo.Carrera;
import modelo.MallaCurricular;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Asignatura;
import modelo.Asignacion;
import modelo.Matricula;
import modelo.CursaTutoria;
import modelo.Persona;
import modelo.Tutoria;
import modelo.Usuario;

public class Utilvista {

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void cargarComboHorario(JComboBox cbx) throws EmptyException {
        HorarioBD horarioControl = new HorarioBD();
        horarioControl.setHorarios(horarioControl.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < horarioControl.getHorarios().getLength(); i++) {
            cbx.addItem(horarioControl.getHorarios().getInfo(i));
        }
    }

    public static void cargarComboFacultades(JComboBox cbx) throws EmptyException {
        FacultadBD fa = new FacultadBD();
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
        CarreraBD ca = new CarreraBD();
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
        MallaBD ma = new MallaBD();
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
        PeriodoBD pa = new PeriodoBD();
        pa.setPeriodos(pa.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < pa.getPeriodos().getLength(); i++) {
            cbx.addItem(pa.getPeriodos().getInfo(i));
        }
    }

    //Matricula
    public static void cargarComboMatriculas(JComboBox cbx) throws EmptyException {
        MatriculaBD ma = new MatriculaBD();
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
        AsignacionMatriculaBD maa = new AsignacionMatriculaBD();
        maa.setAsgMatriculas(maa.all());
        cbx.removeAllItems();
        for (Integer i = 0; i < maa.getAsgMatriculas().getLength(); i++) {
            cbx.addItem(maa.getAsgMatriculas().getInfo(i));
        }
    }

    public static CursaTutoria obtenerMatriculaAsignaturaControl(JComboBox cbx) {
        return (CursaTutoria) cbx.getSelectedItem();
    }

    public static void cargarListaFacultades(JList lst) throws EmptyException {
        FacultadBD fa = new FacultadBD();
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

    public static void cargarComboAsignaturaContrato(DynamicList<Asignacion> contratos, JComboBox cbx) throws EmptyException {
        AsignaturaBD aa = new AsignaturaBD();
        Asignacion contratosArray[] = contratos.toArray();
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
        HorarioBD ha = new HorarioBD();
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
        MallaBD ma = new MallaBD();
        ma.setMallas(ma.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < ma.getMallas().getLength(); i++) {
            if (ma.getMallas().getInfo(i).getCarrera_ID().equals(carrera.getId())) {
                modeloLista.addElement(ma.getMallas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }


    public static void cargarListaCiclos(JList lst, MallaCurricular mallaCurricular) throws EmptyException {
        CicloBD cla = new CicloBD();
        cla.setCiclos(cla.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < cla.getCiclos().getLength(); i++) {
            if (cla.getCiclos().getInfo(i).getMallaCurricular_ID().equals(mallaCurricular.getId())) {
                modeloLista.addElement(cla.getCiclos().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaAsignaturas(JList lst, Ciclo ciclo) throws EmptyException {
        AsignaturaBD aa = new AsignaturaBD();
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
        PersonaBD pa = new PersonaBD();
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
        PersonaBD ea = new PersonaBD();
        ea.setPersonas(ea.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < ea.getPersonas().getLength(); i++) {
            if (ea.getPersonas().getInfo(i).getRol().toString().equals("ESTUDIANTE")) {
                modeloLista.addElement(ea.getPersonas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaAsignacionMatricula(DynamicList<CursaTutoria> tutoriasM, JList lst) throws EmptyException {
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < tutoriasM.getLength(); i++) {
            modeloLista.addElement(tutoriasM);
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaMatriculasAsignaturas(JList lst, MatriculaBD matriculaControl, AsignacionMatriculaBD matriculaAsignaturaControl) throws EmptyException {
        AsignacionMatriculaBD maa = new AsignacionMatriculaBD();
        matriculaAsignaturaControl.setAsgMatriculas(maa.all());
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < matriculaAsignaturaControl.getAsgMatriculas().getLength(); i++) {
            if (matriculaAsignaturaControl.getAsgMatriculas().getInfo(i).getMatricula_ID().equals(matriculaControl.getMatricula().getId())) {
                modeloLista.addElement(matriculaAsignaturaControl.getAsgMatriculas().getInfo(i));
            }
        }
        lst.setModel(modeloLista);
    }
    
    //
    public static void cargarListaUsuarios(DynamicList<Usuario> lista, JList lst) throws EmptyException {
//
//    public static void cargarListaDocentes(JList lst) throws EmptyException {
//        PersonaArchivos pa = new PersonaArchivos();
//        pa.setPersonas(pa.all());
//        System.out.println(pa.all().toString());
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < pa.getPersonas().getLength(); i++) {
//            if (pa.getPersonas().getInfo(i).getRol().toString().equals("DOCENTE")) {
//                modeloLista.addElement(pa.getPersonas().getInfo(i));
//            }
//        }
//        lst.setModel(modeloLista);
//    }
//
//    public static void cargarListaEstudiantes(JList lst) throws EmptyException {
//        PersonaArchivos ea = new PersonaArchivos();
//        ea.setPersonas(ea.all());
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < ea.getPersonas().getLength(); i++) {
//            if (ea.getPersonas().getInfo(i).getRol().toString().equals("ESTUDIANTE")) {
//                modeloLista.addElement(ea.getPersonas().getInfo(i));
//            }
//        }
//        lst.setModel(modeloLista);
//    }
//
//    public static void cargarListaTutoriaMatricula(DynamicList<TutoriaMatricula> tutoriasM, JList lst) throws EmptyException {
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < tutoriasM.getLength(); i++) {
//            modeloLista.addElement(tutoriasM);
//        }
//        lst.setModel(modeloLista);
//    }
//
//    public static void cargarListaMatriculasAsignaturas(JList lst, MatriculaArchivos matriculaControl, MatriculaAsignaturaArchivos matriculaAsignaturaControl) throws EmptyException {
//        MatriculaAsignaturaArchivos maa = new MatriculaAsignaturaArchivos();
//        matriculaAsignaturaControl.setAsgMatriculas(maa.all());
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < matriculaAsignaturaControl.getAsgMatriculas().getLength(); i++) {
//            if (matriculaAsignaturaControl.getAsgMatriculas().getInfo(i).getMatricula_ID().equals(matriculaControl.getMatricula().getId())) {
//                modeloLista.addElement(matriculaAsignaturaControl.getAsgMatriculas().getInfo(i));
//            }
//        }
//        lst.setModel(modeloLista);
//    }

    public static void cargarListaPersonas(DynamicList<Persona> lista, JList lst) throws EmptyException {
//
//    public static void cargarListaDocentes(JList lst) throws EmptyException {
//        PersonaArchivos pa = new PersonaArchivos();
//        pa.setPersonas(pa.all());
//        System.out.println(pa.all().toString());
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < pa.getPersonas().getLength(); i++) {
//            if (pa.getPersonas().getInfo(i).getRol().toString().equals("DOCENTE")) {
//                modeloLista.addElement(pa.getPersonas().getInfo(i));
//            }
//        }
//        lst.setModel(modeloLista);
//    }
//
//    public static void cargarListaEstudiantes(JList lst) throws EmptyException {
//        PersonaArchivos ea = new PersonaArchivos();
//        ea.setPersonas(ea.all());
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < ea.getPersonas().getLength(); i++) {
//            if (ea.getPersonas().getInfo(i).getRol().toString().equals("ESTUDIANTE")) {
//                modeloLista.addElement(ea.getPersonas().getInfo(i));
//            }
//        }
//        lst.setModel(modeloLista);
//    }
//
//    public static void cargarListaTutoriaMatricula(DynamicList<TutoriaMatricula> tutoriasM, JList lst) throws EmptyException {
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < tutoriasM.getLength(); i++) {
//            modeloLista.addElement(tutoriasM);
//        }
//        lst.setModel(modeloLista);
//    }
//
//    public static void cargarListaMatriculasAsignaturas(JList lst, MatriculaArchivos matriculaControl, MatriculaAsignaturaArchivos matriculaAsignaturaControl) throws EmptyException {
//        MatriculaAsignaturaArchivos maa = new MatriculaAsignaturaArchivos();
//        matriculaAsignaturaControl.setAsgMatriculas(maa.all());
//        DefaultListModel modeloLista = new DefaultListModel();
//        for (Integer i = 0; i < matriculaAsignaturaControl.getAsgMatriculas().getLength(); i++) {
//            if (matriculaAsignaturaControl.getAsgMatriculas().getInfo(i).getMatricula_ID().equals(matriculaControl.getMatricula().getId())) {
//                modeloLista.addElement(matriculaAsignaturaControl.getAsgMatriculas().getInfo(i));
//            }
//        }
//        lst.setModel(modeloLista);
//    }

    public static void cargarListaUsuarios(DynamicList<Usuario> lista, JList lst) throws EmptyException {
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < lista.getLength(); i++) {
            modeloLista.addElement(lista.getInfo(i));
        }
        lst.setModel(modeloLista);
    }

    public static void cargarListaCarreras(JList lst, Facultad facultad) throws EmptyException {
        CarreraBD ca = new CarreraBD();
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
    
    public static void cargarListaTutorias(DynamicList<Tutoria> lista, JList lst) throws EmptyException {
        DefaultListModel modeloLista = new DefaultListModel();
        for (Integer i = 0; i < lista.getLength(); i++) {
            modeloLista.addElement(lista.getInfo(i));
        }
        lst.setModel(modeloLista);
    }
}
