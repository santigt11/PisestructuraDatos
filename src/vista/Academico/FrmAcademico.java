package vista.Academico;

import controlador.Academico.AsignaturaBD;
import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import controlador.Academico.CarreraBD;
import controlador.Academico.CicloBD;
import controlador.Academico.FacultadBD;
import controlador.Academico.MallaBD;
import java.awt.Color;
import modelo.Facultad;
import modelo.Carrera;
import modelo.MallaCurricular;
import modelo.Asignatura;
import modelo.Ciclo;
import vista.listas.util.Utilvista;


public class FrmAcademico extends javax.swing.JFrame {

    private FacultadBD fileFacultad = new FacultadBD();
    private CarreraBD fileCarrera = new CarreraBD();
    private MallaBD fileMalla = new MallaBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();
    private CicloBD fileCiclo = new CicloBD();

    public FrmAcademico() throws EmptyException {
        initComponents();
        jpFacultad.setVisible(true);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpCiclo.setVisible(false);
        jpAsignatura.setVisible(false);

        limpiar();
    }

    public Boolean verificar(Integer var) {
        switch (var) {
            //Facultad
            case 1:
                return (!txtNombreF.getText().trim().isEmpty()
                        && !txtCodigoF.getText().trim().isEmpty()
                        && !txtCorreoF.getText().trim().isEmpty());

            //Carrera
            case 2:
                String fc = cbxFacultadC.getSelectedItem() != null ? cbxFacultadC.getSelectedItem().toString().trim() : "";
                return (!fc.isEmpty()
                        && !txtNombreC.getText().trim().isEmpty()
                        && (Integer) spnCiclo.getValue() != 0);

            //Malla Curricular
            case 3:
                String fm = cbxFacultadC.getSelectedItem() != null ? cbxFacultadC.getSelectedItem().toString().trim() : "";
                String cm = cbxCarreraM.getSelectedItem() != null ? cbxCarreraM.getSelectedItem().toString().trim() : "";
                return (!cm.isEmpty()
                        && !fm.isEmpty()
                        && !txtDescripcion.getText().trim().isEmpty()
                        && !txtPensum.getText().trim().isEmpty());

            //Ciclo
            case 4:
                String fcl = cbxFacultadCl.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                String ccl = cbxCarreraCl.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                String mcl = cbxMallaCl.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                return (!fcl.isEmpty()
                        && !ccl.isEmpty()
                        && !mcl.isEmpty()
                        && !txtNombreCl.getText().trim().isEmpty());

            //Asigantura
            case 5:
                String fa = cbxFacultadA.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                String ca = cbxCarreraA.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                String ma = cbxMallaA.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                String cla = cbxCicloA.getSelectedItem() != null ? cbxMallaA.getSelectedItem().toString().trim() : "";
                return (!fa.isEmpty()
                        && !ca.isEmpty()
                        && !ma.isEmpty()
                        && !cla.isEmpty()
                        && !txtNombreA.getText().trim().isEmpty()
                        && !txtCodigoA.getText().trim().isEmpty()
                        && (Integer) spnTotalHorasA.getValue() != 0);
            default:
                throw new AssertionError();
        }
    }

    private void limpiar() throws EmptyException {
        try {
            if (!fileFacultad.getFacultades().isEmpty()) {
                Utilvista.cargarComboFacultades(cbxFacultadC);
                Utilvista.cargarComboFacultades(cbxFacultadM);
                Utilvista.cargarComboFacultades(cbxFacultadCl);
                Utilvista.cargarComboFacultades(cbxFacultadA);
                Utilvista.cargarListaFacultades(lstFacultad);
            }
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        limpiarFacultad();
        limpiarCarrera();
        limpiarMalla();
        limpiarCiclo();
        limpiarAsignatura();
    }

    private void limpiarFacultad() {
        txtNombreF.setText("");
        txtCodigoF.setText("");
        txtCorreoF.setText("");

        txtNombreF.setEditable(true);
        txtCodigoF.setEditable(true);
        txtCorreoF.setEditable(true);
        btCrearF.setEnabled(true);
        btCrearF.setForeground(Color.WHITE);
        btGCFacultad.setEnabled(false);
        btGCFacultad.setForeground(Color.DARK_GRAY);
        btModificarFacultad.setEnabled(false);
        btModificarFacultad.setForeground(Color.DARK_GRAY);

        Utilvista.limpiarLista(lstCarrera);

        fileFacultad.setFacultad(null);
        fileFacultad.limpiarConexion();
    }

    private void limpiarCarrera() {
        //cbxFacultadC.setSelectedIndex(0);
        txtNombreC.setText("");
        spnCiclo.setValue(0);
        txtFacultadCR.setText("");

        cbxFacultadC.setEnabled(true);
        txtNombreC.setEditable(true);
        spnCiclo.setEnabled(true);
        txtFacultadCR.setEditable(true);
        btCrearC.setEnabled(true);
        btCrearC.setForeground(Color.WHITE);
        btGCCarrera.setEnabled(false);
        btGCCarrera.setForeground(Color.DARK_GRAY);
        btModificarCarrera.setEnabled(false);
        btModificarCarrera.setForeground(Color.DARK_GRAY);

        cbxFacultadC.setVisible(true);
        txtFacultadCR.setText("");
        txtFacultadCR.setVisible(false);

        Utilvista.limpiarLista(lstMalla);

        fileCarrera.setCarrera(null);
        fileCarrera.limpiarConexion();
    }

    private void limpiarMalla() {
        //cbxFacultadM.setSelectedIndex(0);
        //cbxCarreraM.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtPensum.setText("");
        //cbxVigencia.setSelectedIndex(0);

        cbxFacultadM.setEnabled(true);
        cbxCarreraM.setEnabled(true);
        txtDescripcion.setEditable(true);
        txtPensum.setEditable(true);
        cbxVigencia.setEnabled(false);
        btCrearM.setEnabled(true);
        btCrearM.setForeground(Color.WHITE);
        btGCMalla.setEnabled(false);
        btGCMalla.setForeground(Color.DARK_GRAY);
        btModificarMalla.setEnabled(false);
        btModificarMalla.setForeground(Color.DARK_GRAY);

        cbxFacultadM.setVisible(true);
        cbxCarreraM.setVisible(true);
        txtFacultadMR.setText("");
        txtFacultadMR.setVisible(false);
        txtCarreraMR.setText("");
        txtCarreraMR.setVisible(false);

        Utilvista.limpiarLista(lstCiclo);

        fileMalla.setMalla(null);
        fileMalla.limpiarConexion();
    }

    private void limpiarCiclo() {
        //cbxFacultadCl.setSelectedIndex(0);
        //cbxCarreraCl.setSelectedIndex(0);
        //cbxMallaCl.setSelectedIndex(0);
        txtNombreCl.setText("");
        //cbxEstadoCl.setSelectedIndex(0);

        cbxFacultadCl.setEnabled(true);
        cbxCarreraCl.setEnabled(true);
        cbxMallaCl.setEnabled(true);
        txtNombreCl.setEditable(true);
        cbxEstadoCl.setEnabled(false);
        btCrearCl.setEnabled(true);
        btCrearCl.setForeground(Color.WHITE);
        btGCCiclo.setEnabled(false);
        btGCCiclo.setForeground(Color.DARK_GRAY);
        btModificarCiclo.setEnabled(false);
        btModificarCiclo.setForeground(Color.DARK_GRAY);

        cbxFacultadCl.setVisible(true);
        cbxCarreraCl.setVisible(true);
        cbxMallaCl.setVisible(true);
        txtFacultadClR.setText("");
        txtFacultadClR.setVisible(false);
        txtCarreraClR.setText("");
        txtCarreraClR.setVisible(false);
        txtMallaClR.setText("");
        txtMallaClR.setVisible(false);

        Utilvista.limpiarLista(lstAsignatura);

        fileCiclo.setCiclo(null);
        fileCiclo.limpiarConexion();
    }

    private void limpiarAsignatura() {
        //cbxFacultadA.setSelectedIndex(0);
        //cbxCarreraA.setSelectedIndex(0);
        //cbxMallaA.setSelectedIndex(0);
        //cbxCicloA.setSelectedIndex(0);
        txtNombreA.setText("");
        txtCodigoA.setText("");
        spnTotalHorasA.setValue(0);

        txtMallaAR.setText("");

        cbxFacultadA.setEnabled(true);
        cbxCarreraA.setEnabled(true);
        cbxMallaA.setEnabled(true);
        cbxCicloA.setEnabled(true);
        txtNombreA.setEditable(true);
        txtCodigoA.setEditable(true);
        spnTotalHorasA.setEnabled(true);
        btCrearA.setEnabled(true);
        btCrearA.setForeground(Color.WHITE);
        btGCAsignatura.setEnabled(false);
        btGCAsignatura.setForeground(Color.DARK_GRAY);
        btModificarAsignatura.setEnabled(false);
        btModificarAsignatura.setForeground(Color.DARK_GRAY);

        cbxFacultadA.setVisible(true);
        cbxCarreraA.setVisible(true);
        cbxMallaA.setVisible(true);
        cbxMallaA.setVisible(true);
        txtFacultadAR.setText("");
        txtFacultadAR.setVisible(false);
        txtCarreraAR.setText("");
        txtCarreraAR.setVisible(false);
        txtMallaAR.setText("");
        txtMallaAR.setVisible(false);
        txtCicloAR.setText("");
        txtCicloAR.setVisible(false);

        fileAsignatura.setAsignatura(null);
        fileAsignatura.limpiarConexion();
    }

    private void guardar(Integer flag) throws EmptyException, Exception {
        switch (flag) {
            //Facultad
            case 1:
                if (verificar(1)) {
                    if (fileFacultad.buscarCodigo(txtCodigoF.getText()) == false) {
                        fileFacultad.getFacultad().setNombre(txtNombreF.getText());
                        fileFacultad.getFacultad().setCodigo(txtCodigoF.getText());
                        fileFacultad.getFacultad().setCorreo(txtCorreoF.getText());

                        fileFacultad.persist(fileFacultad.getFacultad());
                        JOptionPane.showMessageDialog(null, "Datos guardados");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El codigo de la facultad ya existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Carrera
            case 2:
                if (verificar(2)) {
                    if (fileCarrera.buscarCodigo(txtFacultadCR.getText()) == false) {
                        fileCarrera.getCarrera().setFacultad_ID(cbxFacultadC.getSelectedIndex() + 1);
                        fileCarrera.getCarrera().setNombre(txtNombreC.getText());
                        fileCarrera.getCarrera().setNumCiclo((Integer) spnCiclo.getValue());
                        fileCarrera.getCarrera().setCodigo(txtCodigoC.getText());

                        fileCarrera.persist(fileCarrera.getCarrera());
                        JOptionPane.showMessageDialog(null, "Datos guardados");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El codigo de la carrera ya existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Malla Curricular 
            case 3:
                if (verificar(3)) {
                    fileMalla.getMalla().setCarrera_ID(Utilvista.obtenerCarreraControl(cbxCarreraM).getId());
                    fileMalla.getMalla().setDescripcion(txtDescripcion.getText());
                    fileMalla.getMalla().setPensum(txtPensum.getText());
                    fileMalla.getMalla().setVigencia(true);

                    fileMalla.persist(fileMalla.getMalla());
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Ciclo
            case 4:
                if (verificar(4)) {
                    if (fileCiclo.buscarNombre(txtCodigoA.getText()) == false) {
                        fileCiclo.getCiclo().setMallaCurricular_ID(Utilvista.obtenerMallaControl(cbxMallaCl).getId());
                        fileCiclo.getCiclo().setNombre(txtNombreCl.getText());
                        fileCiclo.getCiclo().setEstadoActivo(true);

                        fileCiclo.persist(fileCiclo.getCiclo());
                        JOptionPane.showMessageDialog(null, "Ciclo guardado");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El ciclo ya existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Asignatura
            case 5:
                if (verificar(5)) {
                    if (fileAsignatura.buscarCodigo(txtCodigoA.getText()) == false) {
                        fileAsignatura.getAsignatura().setCiclo_ID(Utilvista.obtenerCicloControl(cbxCicloA).getId());
                        fileAsignatura.getAsignatura().setNombre(txtNombreA.getText());
                        fileAsignatura.getAsignatura().setCodigo(txtCodigoA.getText());
                        fileAsignatura.getAsignatura().setTotalHoras((Integer) spnTotalHorasA.getValue());

                        fileAsignatura.persist(fileAsignatura.getAsignatura());
                        JOptionPane.showMessageDialog(null, "Asignatura guardada");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El codigo de la asignatura ya existe");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                throw new AssertionError();
        }

    }

    private void cargarVista(Integer var) {
        Facultad facultad = null;
        Carrera carrera = null;
        MallaCurricular malla = null;
        Ciclo ciclo = null;
        Asignatura asignatura = null;

        switch (var) {
            //Facultad
            case 1:
                limpiarCarrera();
                limpiarMalla();
                limpiarCiclo();
                limpiarAsignatura();

                Object f = lstFacultad.getSelectedValue();
                facultad = (Facultad) f;
                try {
                    //fileFacultad.setFacultad(facultad);
                    txtNombreF.setText(facultad.getNombre());
                    txtCodigoF.setText(facultad.getCodigo());
                    txtCorreoF.setText(facultad.getCorreo());

                    txtNombreF.setEditable(false);
                    txtCodigoF.setEditable(false);
                    txtCorreoF.setEditable(false);
                    btCrearF.setEnabled(false);
                    btCrearF.setForeground(Color.DARK_GRAY);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Carrera
            case 2:
                limpiarMalla();
                limpiarCiclo();
                limpiarAsignatura();

                Object fc = lstFacultad.getSelectedValue();
                facultad = (Facultad) fc;
                Object c = lstCarrera.getSelectedValue();
                carrera = (Carrera) c;
                try {
                    //fileCarrera.setCarrera(carrera);
                    txtNombreC.setText(carrera.getNombre());
                    txtCodigoC.setText(carrera.getCodigo());
                    spnCiclo.setValue(carrera.getNumCiclo());

                    txtFacultadCR.setText(facultad.getNombre());
                    txtFacultadCR.setVisible(true);
                    cbxFacultadC.setVisible(false);

                    cbxFacultadC.setEnabled(false);
                    txtNombreC.setEditable(false);
                    txtFacultadCR.setEditable(false);
                    btCrearC.setEnabled(false);
                    btCrearC.setForeground(Color.DARK_GRAY);
                    spnCiclo.setEnabled(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Malla Curricular
            case 3:
                limpiarCiclo();
                limpiarAsignatura();

                Object fm = lstFacultad.getSelectedValue();
                facultad = (Facultad) fm;
                Object cm = lstCarrera.getSelectedValue();
                carrera = (Carrera) cm;
                Object m = lstMalla.getSelectedValue();
                malla = (MallaCurricular) m;
                try {
                    //fileMalla.setMalla(malla);
                    txtDescripcion.setText(malla.getDescripcion());
                    txtPensum.setText(malla.getPensum());
                    if (malla.getVigencia()) {
                        cbxVigencia.setSelectedIndex(0);
                    } else if (!malla.getVigencia()) {
                        cbxVigencia.setSelectedIndex(1);
                    }

                    txtFacultadMR.setText(facultad.getNombre());
                    txtFacultadMR.setVisible(true);
                    txtCarreraMR.setText(carrera.getNombre());
                    txtCarreraMR.setVisible(true);
                    cbxFacultadM.setVisible(false);
                    cbxCarreraM.setVisible(false);

                    btCrearM.setEnabled(false);
                    btCrearM.setForeground(Color.DARK_GRAY);
                    cbxCarreraM.setEnabled(false);
                    cbxVigencia.setEnabled(false);
                    txtDescripcion.setEditable(false);
                    txtPensum.setEditable(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Ciclo
            case 4:
                limpiarAsignatura();

                Object fcl = lstFacultad.getSelectedValue();
                facultad = (Facultad) fcl;
                Object ccl = lstCarrera.getSelectedValue();
                carrera = (Carrera) ccl;
                Object mcl = lstMalla.getSelectedValue();
                malla = (MallaCurricular) mcl;
                Object cl = lstCiclo.getSelectedValue();
                ciclo = (Ciclo) cl;
                try {
                    //fileCiclo.setCiclo(ciclo);
                    txtNombreCl.setText(ciclo.getNombre());
                    if (ciclo.getEstadoActivo()) {
                        cbxEstadoCl.setSelectedIndex(0);
                    } else if (!ciclo.getEstadoActivo()) {
                        cbxEstadoCl.setSelectedIndex(1);
                    }

                    txtFacultadClR.setText(facultad.getNombre());
                    txtFacultadClR.setVisible(true);
                    txtCarreraClR.setText(carrera.getNombre());
                    txtCarreraClR.setVisible(true);
                    txtMallaClR.setText(malla.getDescripcion());
                    txtMallaClR.setVisible(true);
                    cbxFacultadCl.setVisible(false);
                    cbxCarreraCl.setVisible(false);
                    cbxMallaCl.setVisible(false);

                    btCrearCl.setEnabled(false);
                    btCrearCl.setForeground(Color.DARK_GRAY);
                    cbxFacultadCl.setVisible(false);
                    cbxCarreraCl.setVisible(false);
                    cbxMallaCl.setVisible(false);
                    cbxEstadoCl.setEnabled(false);
                    txtNombreCl.setEnabled(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            //Asignatura
            case 5:
                Object fa = lstFacultad.getSelectedValue();
                facultad = (Facultad) fa;
                Object ca = lstCarrera.getSelectedValue();
                carrera = (Carrera) ca;
                Object ma = lstMalla.getSelectedValue();
                malla = (MallaCurricular) ma;
                Object cla = lstCiclo.getSelectedValue();
                ciclo = (Ciclo) cla;
                Object a = lstAsignatura.getSelectedValue();
                asignatura = (Asignatura) a;
                try {
                    //fileAsignatura.setAsignatura(asignatura);
                    txtNombreA.setText(asignatura.getNombre());
                    txtCodigoA.setText(asignatura.getCodigo());
                    spnTotalHorasA.setValue(asignatura.getTotalHoras());

                    txtFacultadAR.setText(facultad.getNombre());
                    txtFacultadAR.setVisible(true);
                    txtCarreraAR.setText(carrera.getNombre());
                    txtCarreraAR.setVisible(true);
                    txtMallaAR.setText(malla.getDescripcion());
                    txtMallaAR.setVisible(true);
                    txtCicloAR.setText(ciclo.getNombre());
                    txtCicloAR.setVisible(true);
                    cbxFacultadA.setVisible(false);
                    cbxCarreraA.setVisible(false);
                    cbxMallaA.setVisible(false);
                    cbxMallaA.setVisible(false);

                    btCrearA.setEnabled(false);
                    btCrearA.setForeground(Color.DARK_GRAY);
                    cbxCarreraA.setEnabled(false);
                    cbxMallaA.setEnabled(false);
                    txtNombreA.setEnabled(false);
                    txtCodigoA.setEnabled(false);
                    spnTotalHorasA.setEnabled(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void modificar(Integer flag) throws EmptyException, Exception {
        switch (flag) {
            case 1:
                fileFacultad.getFacultad().setNombre(txtNombreF.getText());

                fileFacultad.merge(fileFacultad.getFacultad());
                JOptionPane.showMessageDialog(null, "Cambios guardados");
                limpiar();
                break;
            case 2:
                fileCarrera.getCarrera().setFacultad_ID(Utilvista.obtenerFacultadControl(cbxFacultadC).getId());
                fileCarrera.getCarrera().setNombre(txtNombreC.getText());
                fileCarrera.getCarrera().setNumCiclo((Integer) spnCiclo.getValue());

                fileCarrera.merge(fileCarrera.getCarrera());
                JOptionPane.showMessageDialog(null, "Cambios guardados");
                limpiar();
                break;
            case 3:
                fileMalla.getMalla().setCarrera_ID(cbxCarreraM.getSelectedIndex());
                fileMalla.getMalla().setDescripcion(txtDescripcion.getText());
                fileMalla.getMalla().setPensum(txtPensum.getText());
                if (cbxVigencia.getSelectedIndex() == 0) {
                    fileMalla.getMalla().setVigencia(true);
                } else if (cbxVigencia.getSelectedIndex() == 1) {
                    fileMalla.getMalla().setVigencia(false);
                }

                fileMalla.merge(fileMalla.getMalla());
                JOptionPane.showMessageDialog(null, "Cambios guardados");
                limpiar();

                break;
            case 4:
                if (verificar(4)) {
                    //fileAsignatura.getAsignatura().setMallaCurricular_ID(Utilvista.obtenerMallaControl(cbxMallaA).getId());
                    fileAsignatura.getAsignatura().setNombre(txtNombreA.getText());
                    fileAsignatura.getAsignatura().setCodigo(txtCodigoA.getText());
                    fileAsignatura.getAsignatura().setTotalHoras((Integer) spnTotalHorasA.getValue());

                    fileAsignatura.merge(fileAsignatura.getAsignatura());
                    JOptionPane.showMessageDialog(null, "Cambios guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jpAll = new javax.swing.JPanel();
        jpFacultad = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreF = new javax.swing.JTextField();
        btCrearF = new javax.swing.JButton();
        btGCFacultad = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtCorreoF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCodigoF = new javax.swing.JTextField();
        jpCarrera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxFacultadC = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNombreC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spnCiclo = new javax.swing.JSpinner();
        btCrearC = new javax.swing.JButton();
        btGCCarrera = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtFacultadCR = new javax.swing.JTextField();
        txtCodigoC = new javax.swing.JTextField();
        jpMalla = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbxFacultadM = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxCarreraM = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPensum = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbxVigencia = new javax.swing.JComboBox<>();
        btCrearM = new javax.swing.JButton();
        btGCMalla = new javax.swing.JButton();
        txtFacultadMR = new javax.swing.JTextField();
        txtCarreraMR = new javax.swing.JTextField();
        jpCiclo = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cbxMallaCl = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        txtNombreCl = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        btCrearCl = new javax.swing.JButton();
        btGCCiclo = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbxCarreraCl = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        cbxEstadoCl = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cbxFacultadCl = new javax.swing.JComboBox<>();
        txtFacultadClR = new javax.swing.JTextField();
        txtMallaClR = new javax.swing.JTextField();
        txtCarreraClR = new javax.swing.JTextField();
        jpAsignatura = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        cbxMallaA = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtNombreA = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        spnTotalHorasA = new javax.swing.JSpinner();
        btCrearA = new javax.swing.JButton();
        btGCAsignatura = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtCodigoA = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbxCarreraA = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cbxFacultadA = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        cbxCicloA = new javax.swing.JComboBox<>();
        txtFacultadAR = new javax.swing.JTextField();
        txtCarreraAR = new javax.swing.JTextField();
        txtMallaAR = new javax.swing.JTextField();
        txtCicloAR = new javax.swing.JTextField();
        btFacultad = new javax.swing.JButton();
        btCarrera = new javax.swing.JButton();
        btMalla = new javax.swing.JButton();
        btCiclo = new javax.swing.JButton();
        btAsignatura = new javax.swing.JButton();
        jpRegistro = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstMalla = new javax.swing.JList<>();
        btModificarMalla = new javax.swing.JButton();
        btLimpiar = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        btModificarAsignatura = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstAsignatura = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstFacultad = new javax.swing.JList<>();
        btModificarFacultad = new javax.swing.JButton();
        btModificarCarrera = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstCarrera = new javax.swing.JList<>();
        btModificarCiclo = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstCiclo = new javax.swing.JList<>();
        bg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 51, 0));

        jLabel15.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Academico General");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(444, 444, 444)
                .addComponent(jLabel15)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 30));

        jpAll.setBackground(new java.awt.Color(212, 173, 107));
        jpAll.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpFacultad.setBackground(new java.awt.Color(212, 173, 107));
        jpFacultad.setForeground(new java.awt.Color(51, 51, 51));
        jpFacultad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jpFacultad.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nueva Facultad");
        jpFacultad.add(jLabel1);
        jLabel1.setBounds(20, 20, 110, 20);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nombre:");
        jpFacultad.add(jLabel4);
        jLabel4.setBounds(60, 60, 70, 33);

        txtNombreF.setBackground(new java.awt.Color(237, 209, 163));
        txtNombreF.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombreF.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreF.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpFacultad.add(txtNombreF);
        txtNombreF.setBounds(150, 60, 250, 30);

        btCrearF.setBackground(new java.awt.Color(102, 51, 0));
        btCrearF.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btCrearF.setForeground(new java.awt.Color(255, 255, 255));
        btCrearF.setText("Crear Facultad");
        btCrearF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearFActionPerformed(evt);
            }
        });
        jpFacultad.add(btCrearF);
        btCrearF.setBounds(270, 160, 130, 30);

        btGCFacultad.setBackground(new java.awt.Color(102, 51, 0));
        btGCFacultad.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btGCFacultad.setForeground(new java.awt.Color(255, 255, 255));
        btGCFacultad.setText("Guardar Cambios");
        btGCFacultad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCFacultadActionPerformed(evt);
            }
        });
        jpFacultad.add(btGCFacultad);
        btGCFacultad.setBounds(430, 160, 130, 30);

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Correo:");
        jpFacultad.add(jLabel20);
        jLabel20.setBounds(60, 110, 70, 33);

        txtCorreoF.setBackground(new java.awt.Color(237, 209, 163));
        txtCorreoF.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCorreoF.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreoF.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpFacultad.add(txtCorreoF);
        txtCorreoF.setBounds(150, 110, 250, 30);

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Codigo:");
        jpFacultad.add(jLabel21);
        jLabel21.setBounds(430, 60, 70, 33);

        txtCodigoF.setBackground(new java.awt.Color(237, 209, 163));
        txtCodigoF.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodigoF.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigoF.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpFacultad.add(txtCodigoF);
        txtCodigoF.setBounds(520, 60, 160, 30);

        jpAll.add(jpFacultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 200));

        jpCarrera.setBackground(new java.awt.Color(212, 173, 107));
        jpCarrera.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nueva Carrera");
        jpCarrera.add(jLabel2);
        jLabel2.setBounds(10, 10, 123, 22);

        cbxFacultadC.setBackground(new java.awt.Color(237, 209, 163));
        cbxFacultadC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxFacultadC.setForeground(new java.awt.Color(242, 242, 242));
        cbxFacultadC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFacultadC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadCActionPerformed(evt);
            }
        });
        jpCarrera.add(cbxFacultadC);
        cbxFacultadC.setBounds(190, 60, 250, 29);

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Facultad:");
        jpCarrera.add(jLabel5);
        jLabel5.setBounds(100, 60, 80, 30);

        txtNombreC.setBackground(new java.awt.Color(237, 209, 163));
        txtNombreC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombreC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCarrera.add(txtNombreC);
        txtNombreC.setBounds(190, 100, 250, 30);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Numero de Ciclos:");
        jpCarrera.add(jLabel6);
        jLabel6.setBounds(460, 60, 140, 30);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nombre:");
        jpCarrera.add(jLabel7);
        jLabel7.setBounds(110, 100, 70, 30);

        spnCiclo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jpCarrera.add(spnCiclo);
        spnCiclo.setBounds(610, 60, 85, 29);

        btCrearC.setBackground(new java.awt.Color(102, 51, 0));
        btCrearC.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btCrearC.setForeground(new java.awt.Color(255, 255, 255));
        btCrearC.setText("Crear Carrera");
        btCrearC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearCActionPerformed(evt);
            }
        });
        jpCarrera.add(btCrearC);
        btCrearC.setBounds(270, 150, 130, 30);

        btGCCarrera.setBackground(new java.awt.Color(102, 51, 0));
        btGCCarrera.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btGCCarrera.setForeground(new java.awt.Color(255, 255, 255));
        btGCCarrera.setText("Guardar Cambios");
        btGCCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCCarreraActionPerformed(evt);
            }
        });
        jpCarrera.add(btGCCarrera);
        btGCCarrera.setBounds(430, 150, 130, 30);

        jLabel22.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Codigo:");
        jpCarrera.add(jLabel22);
        jLabel22.setBounds(530, 100, 70, 30);

        txtFacultadCR.setEditable(false);
        txtFacultadCR.setBackground(new java.awt.Color(237, 209, 163));
        txtFacultadCR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFacultadCR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCarrera.add(txtFacultadCR);
        txtFacultadCR.setBounds(190, 60, 250, 30);

        txtCodigoC.setBackground(new java.awt.Color(237, 209, 163));
        txtCodigoC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodigoC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCarrera.add(txtCodigoC);
        txtCodigoC.setBounds(610, 100, 190, 30);

        jpAll.add(jpCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 840, 180));

        jpMalla.setBackground(new java.awt.Color(212, 173, 107));
        jpMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpMallaMouseEntered(evt);
            }
        });
        jpMalla.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel3.setText("Nueva Malla");
        jpMalla.add(jLabel3);
        jLabel3.setBounds(20, 20, 110, 22);

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Facultad:");
        jpMalla.add(jLabel18);
        jLabel18.setBounds(90, 50, 70, 30);

        cbxFacultadM.setBackground(new java.awt.Color(237, 209, 163));
        cbxFacultadM.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxFacultadM.setForeground(new java.awt.Color(242, 242, 242));
        cbxFacultadM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFacultadM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadMActionPerformed(evt);
            }
        });
        jpMalla.add(cbxFacultadM);
        cbxFacultadM.setBounds(180, 50, 210, 29);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Carrera:");
        jpMalla.add(jLabel8);
        jLabel8.setBounds(90, 90, 70, 30);

        cbxCarreraM.setBackground(new java.awt.Color(237, 209, 163));
        cbxCarreraM.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxCarreraM.setForeground(new java.awt.Color(242, 242, 242));
        cbxCarreraM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpMalla.add(cbxCarreraM);
        cbxCarreraM.setBounds(180, 90, 210, 29);

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Descripcion:");
        jpMalla.add(jLabel9);
        jLabel9.setBounds(60, 130, 100, 30);

        txtDescripcion.setBackground(new java.awt.Color(237, 209, 163));
        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpMalla.add(txtDescripcion);
        txtDescripcion.setBounds(180, 130, 240, 30);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Pensum:");
        jpMalla.add(jLabel10);
        jLabel10.setBounds(490, 90, 70, 30);

        txtPensum.setBackground(new java.awt.Color(237, 209, 163));
        txtPensum.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtPensum.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpMalla.add(txtPensum);
        txtPensum.setBounds(590, 90, 160, 30);

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Vigencia:");
        jpMalla.add(jLabel16);
        jLabel16.setBounds(490, 130, 70, 30);

        cbxVigencia.setBackground(new java.awt.Color(237, 209, 163));
        cbxVigencia.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxVigencia.setForeground(new java.awt.Color(242, 242, 242));
        cbxVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vigente", "No Vigente" }));
        cbxVigencia.setEnabled(false);
        jpMalla.add(cbxVigencia);
        cbxVigencia.setBounds(590, 130, 97, 29);

        btCrearM.setBackground(new java.awt.Color(102, 51, 0));
        btCrearM.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btCrearM.setForeground(new java.awt.Color(255, 255, 255));
        btCrearM.setText("Crear Malla");
        btCrearM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearMActionPerformed(evt);
            }
        });
        jpMalla.add(btCrearM);
        btCrearM.setBounds(340, 190, 140, 30);

        btGCMalla.setBackground(new java.awt.Color(102, 51, 0));
        btGCMalla.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btGCMalla.setForeground(new java.awt.Color(255, 255, 255));
        btGCMalla.setText("Guardar Cambios");
        btGCMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCMallaActionPerformed(evt);
            }
        });
        jpMalla.add(btGCMalla);
        btGCMalla.setBounds(520, 190, 130, 30);

        txtFacultadMR.setEditable(false);
        txtFacultadMR.setBackground(new java.awt.Color(237, 209, 163));
        txtFacultadMR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFacultadMR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpMalla.add(txtFacultadMR);
        txtFacultadMR.setBounds(180, 50, 210, 30);

        txtCarreraMR.setEditable(false);
        txtCarreraMR.setBackground(new java.awt.Color(237, 209, 163));
        txtCarreraMR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCarreraMR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpMalla.add(txtCarreraMR);
        txtCarreraMR.setBounds(180, 90, 210, 30);

        jpAll.add(jpMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 230));

        jpCiclo.setBackground(new java.awt.Color(212, 173, 107));
        jpCiclo.setLayout(null);

        jLabel32.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel32.setText("Nueva Ciclo");
        jpCiclo.add(jLabel32);
        jLabel32.setBounds(0, 10, 152, 22);

        cbxMallaCl.setBackground(new java.awt.Color(237, 209, 163));
        cbxMallaCl.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxMallaCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMallaClActionPerformed(evt);
            }
        });
        jpCiclo.add(cbxMallaCl);
        cbxMallaCl.setBounds(180, 80, 220, 29);

        jLabel33.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Malla Curricular:");
        jpCiclo.add(jLabel33);
        jLabel33.setBounds(60, 80, 90, 30);

        txtNombreCl.setBackground(new java.awt.Color(237, 209, 163));
        txtNombreCl.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombreCl.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCiclo.add(txtNombreCl);
        txtNombreCl.setBounds(180, 120, 220, 30);

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Nombre:");
        jpCiclo.add(jLabel35);
        jLabel35.setBounds(80, 120, 70, 30);

        btCrearCl.setBackground(new java.awt.Color(102, 51, 0));
        btCrearCl.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btCrearCl.setForeground(new java.awt.Color(255, 255, 255));
        btCrearCl.setText("Crear Ciclo");
        btCrearCl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearClActionPerformed(evt);
            }
        });
        jpCiclo.add(btCrearCl);
        btCrearCl.setBounds(370, 180, 130, 30);

        btGCCiclo.setBackground(new java.awt.Color(102, 51, 0));
        btGCCiclo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btGCCiclo.setForeground(new java.awt.Color(255, 255, 255));
        btGCCiclo.setText("Guardar Cambios");
        btGCCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCCicloActionPerformed(evt);
            }
        });
        jpCiclo.add(btGCCiclo);
        btGCCiclo.setBounds(580, 180, 130, 30);

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Carrera:");
        jpCiclo.add(jLabel17);
        jLabel17.setBounds(500, 40, 70, 30);

        cbxCarreraCl.setBackground(new java.awt.Color(237, 209, 163));
        cbxCarreraCl.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxCarreraCl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxCarreraCl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxCarreraClMouseClicked(evt);
            }
        });
        cbxCarreraCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCarreraClActionPerformed(evt);
            }
        });
        jpCiclo.add(cbxCarreraCl);
        cbxCarreraCl.setBounds(580, 40, 220, 29);

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Estado:");
        jpCiclo.add(jLabel37);
        jLabel37.setBounds(520, 90, 50, 30);

        cbxEstadoCl.setBackground(new java.awt.Color(237, 209, 163));
        cbxEstadoCl.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxEstadoCl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cbxEstadoCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoClActionPerformed(evt);
            }
        });
        jpCiclo.add(cbxEstadoCl);
        cbxEstadoCl.setBounds(580, 90, 120, 29);

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Facultad:");
        jpCiclo.add(jLabel19);
        jLabel19.setBounds(80, 40, 70, 30);

        cbxFacultadCl.setBackground(new java.awt.Color(237, 209, 163));
        cbxFacultadCl.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxFacultadCl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFacultadCl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxFacultadClMouseClicked(evt);
            }
        });
        cbxFacultadCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadClActionPerformed(evt);
            }
        });
        jpCiclo.add(cbxFacultadCl);
        cbxFacultadCl.setBounds(180, 40, 220, 29);

        txtFacultadClR.setBackground(new java.awt.Color(237, 209, 163));
        txtFacultadClR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFacultadClR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCiclo.add(txtFacultadClR);
        txtFacultadClR.setBounds(180, 40, 220, 30);

        txtMallaClR.setBackground(new java.awt.Color(237, 209, 163));
        txtMallaClR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMallaClR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCiclo.add(txtMallaClR);
        txtMallaClR.setBounds(180, 80, 220, 30);

        txtCarreraClR.setBackground(new java.awt.Color(237, 209, 163));
        txtCarreraClR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCarreraClR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpCiclo.add(txtCarreraClR);
        txtCarreraClR.setBounds(580, 40, 220, 30);

        jpAll.add(jpCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 950, 230));

        jpAsignatura.setBackground(new java.awt.Color(212, 173, 107));
        jpAsignatura.setLayout(null);

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel25.setText("Nueva Asignatura");
        jpAsignatura.add(jLabel25);
        jLabel25.setBounds(0, 10, 152, 22);

        cbxMallaA.setBackground(new java.awt.Color(237, 209, 163));
        cbxMallaA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxMallaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMallaAActionPerformed(evt);
            }
        });
        jpAsignatura.add(cbxMallaA);
        cbxMallaA.setBounds(180, 80, 220, 29);

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Malla Curricular:");
        jpAsignatura.add(jLabel26);
        jLabel26.setBounds(60, 80, 90, 30);

        txtNombreA.setBackground(new java.awt.Color(237, 209, 163));
        txtNombreA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombreA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpAsignatura.add(txtNombreA);
        txtNombreA.setBounds(180, 120, 220, 30);

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Total de Horas:");
        jpAsignatura.add(jLabel27);
        jLabel27.setBounds(650, 120, 120, 30);

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Nombre:");
        jpAsignatura.add(jLabel28);
        jLabel28.setBounds(80, 120, 70, 30);

        spnTotalHorasA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jpAsignatura.add(spnTotalHorasA);
        spnTotalHorasA.setBounds(790, 120, 77, 29);

        btCrearA.setBackground(new java.awt.Color(102, 51, 0));
        btCrearA.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btCrearA.setForeground(new java.awt.Color(255, 255, 255));
        btCrearA.setText("Crear Asignatura");
        btCrearA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearAActionPerformed(evt);
            }
        });
        jpAsignatura.add(btCrearA);
        btCrearA.setBounds(370, 180, 130, 30);

        btGCAsignatura.setBackground(new java.awt.Color(102, 51, 0));
        btGCAsignatura.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btGCAsignatura.setForeground(new java.awt.Color(255, 255, 255));
        btGCAsignatura.setText("Guardar Cambios");
        btGCAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCAsignaturaActionPerformed(evt);
            }
        });
        jpAsignatura.add(btGCAsignatura);
        btGCAsignatura.setBounds(580, 180, 130, 30);

        jLabel29.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Codigo:");
        jpAsignatura.add(jLabel29);
        jLabel29.setBounds(420, 120, 60, 30);

        txtCodigoA.setBackground(new java.awt.Color(237, 209, 163));
        txtCodigoA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodigoA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpAsignatura.add(txtCodigoA);
        txtCodigoA.setBounds(500, 120, 140, 30);

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Carrera:");
        jpAsignatura.add(jLabel14);
        jLabel14.setBounds(450, 50, 70, 30);

        cbxCarreraA.setBackground(new java.awt.Color(237, 209, 163));
        cbxCarreraA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxCarreraA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxCarreraAMouseClicked(evt);
            }
        });
        cbxCarreraA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCarreraAActionPerformed(evt);
            }
        });
        jpAsignatura.add(cbxCarreraA);
        cbxCarreraA.setBounds(550, 50, 220, 29);

        jLabel23.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Facultad:");
        jpAsignatura.add(jLabel23);
        jLabel23.setBounds(80, 50, 70, 30);

        cbxFacultadA.setBackground(new java.awt.Color(237, 209, 163));
        cbxFacultadA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxFacultadA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFacultadA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxFacultadAMouseClicked(evt);
            }
        });
        cbxFacultadA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadAActionPerformed(evt);
            }
        });
        jpAsignatura.add(cbxFacultadA);
        cbxFacultadA.setBounds(180, 50, 220, 29);

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Ciclo:");
        jpAsignatura.add(jLabel24);
        jLabel24.setBounds(450, 80, 70, 30);

        cbxCicloA.setBackground(new java.awt.Color(237, 209, 163));
        cbxCicloA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxCicloA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxCicloAMouseClicked(evt);
            }
        });
        cbxCicloA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCicloAActionPerformed(evt);
            }
        });
        jpAsignatura.add(cbxCicloA);
        cbxCicloA.setBounds(550, 80, 220, 29);

        txtFacultadAR.setBackground(new java.awt.Color(237, 209, 163));
        txtFacultadAR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFacultadAR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtFacultadAR.setEnabled(false);
        jpAsignatura.add(txtFacultadAR);
        txtFacultadAR.setBounds(180, 50, 220, 30);

        txtCarreraAR.setBackground(new java.awt.Color(237, 209, 163));
        txtCarreraAR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCarreraAR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtCarreraAR.setEnabled(false);
        jpAsignatura.add(txtCarreraAR);
        txtCarreraAR.setBounds(550, 50, 220, 30);

        txtMallaAR.setBackground(new java.awt.Color(237, 209, 163));
        txtMallaAR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMallaAR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtMallaAR.setEnabled(false);
        jpAsignatura.add(txtMallaAR);
        txtMallaAR.setBounds(180, 80, 220, 30);

        txtCicloAR.setBackground(new java.awt.Color(237, 209, 163));
        txtCicloAR.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCicloAR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtCicloAR.setEnabled(false);
        jpAsignatura.add(txtCicloAR);
        txtCicloAR.setBounds(550, 80, 220, 30);

        jpAll.add(jpAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 970, 230));

        getContentPane().add(jpAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1010, 240));

        btFacultad.setBackground(new java.awt.Color(102, 51, 0));
        btFacultad.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btFacultad.setForeground(new java.awt.Color(255, 255, 255));
        btFacultad.setText("Facultad");
        btFacultad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFacultad.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFacultadActionPerformed(evt);
            }
        });
        getContentPane().add(btFacultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, 40));

        btCarrera.setBackground(new java.awt.Color(102, 51, 0));
        btCarrera.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btCarrera.setForeground(new java.awt.Color(255, 255, 255));
        btCarrera.setText("Carrera");
        btCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCarrera.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarreraActionPerformed(evt);
            }
        });
        getContentPane().add(btCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 120, 40));

        btMalla.setBackground(new java.awt.Color(102, 51, 0));
        btMalla.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btMalla.setForeground(new java.awt.Color(255, 255, 255));
        btMalla.setText("Malla Curricular");
        btMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMalla.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMallaActionPerformed(evt);
            }
        });
        getContentPane().add(btMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 120, 40));

        btCiclo.setBackground(new java.awt.Color(102, 51, 0));
        btCiclo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btCiclo.setForeground(new java.awt.Color(255, 255, 255));
        btCiclo.setText("Ciclo");
        btCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCiclo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCicloActionPerformed(evt);
            }
        });
        getContentPane().add(btCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 120, 40));

        btAsignatura.setBackground(new java.awt.Color(102, 51, 0));
        btAsignatura.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btAsignatura.setForeground(new java.awt.Color(255, 255, 255));
        btAsignatura.setText("Asignatura");
        btAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAsignatura.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAsignaturaActionPerformed(evt);
            }
        });
        getContentPane().add(btAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 120, 40));

        jpRegistro.setBackground(new java.awt.Color(212, 173, 107));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Mallas Curriculares:");

        lstMalla.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMallaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstMalla);

        btModificarMalla.setBackground(new java.awt.Color(102, 51, 0));
        btModificarMalla.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btModificarMalla.setForeground(new java.awt.Color(255, 255, 255));
        btModificarMalla.setText("Modificar");
        btModificarMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarMallaActionPerformed(evt);
            }
        });

        btLimpiar.setBackground(new java.awt.Color(102, 51, 0));
        btLimpiar.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btLimpiar.setText("Limpiar Todo");
        btLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Asignaturas:");

        btModificarAsignatura.setBackground(new java.awt.Color(102, 51, 0));
        btModificarAsignatura.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btModificarAsignatura.setForeground(new java.awt.Color(255, 255, 255));
        btModificarAsignatura.setText("Modificar");
        btModificarAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarAsignaturaActionPerformed(evt);
            }
        });

        lstAsignatura.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lstAsignatura);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Facultades:");

        lstFacultad.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstFacultadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstFacultad);

        btModificarFacultad.setBackground(new java.awt.Color(102, 51, 0));
        btModificarFacultad.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btModificarFacultad.setForeground(new java.awt.Color(255, 255, 255));
        btModificarFacultad.setText("Modificar");
        btModificarFacultad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarFacultadActionPerformed(evt);
            }
        });

        btModificarCarrera.setBackground(new java.awt.Color(102, 51, 0));
        btModificarCarrera.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btModificarCarrera.setForeground(new java.awt.Color(255, 255, 255));
        btModificarCarrera.setText("Modificar");
        btModificarCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarCarreraActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Carreras:");

        lstCarrera.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCarreraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstCarrera);

        btModificarCiclo.setBackground(new java.awt.Color(102, 51, 0));
        btModificarCiclo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btModificarCiclo.setForeground(new java.awt.Color(255, 255, 255));
        btModificarCiclo.setText("Modificar");
        btModificarCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarCicloActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Ciclos:");

        lstCiclo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCicloMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lstCiclo);

        javax.swing.GroupLayout jpRegistroLayout = new javax.swing.GroupLayout(jpRegistro);
        jpRegistro.setLayout(jpRegistroLayout);
        jpRegistroLayout.setHorizontalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btModificarFacultad)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btModificarCarrera)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btModificarMalla))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModificarCiclo)
                        .addGap(68, 68, 68))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel31))
                        .addGap(12, 12, 12)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btModificarAsignatura)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(440, 440, 440))
        );
        jpRegistroLayout.setVerticalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpRegistroLayout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btModificarCarrera))
                                .addGroup(jpRegistroLayout.createSequentialGroup()
                                    .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel31))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jpRegistroLayout.createSequentialGroup()
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btModificarCiclo)
                                                .addComponent(btModificarAsignatura)))
                                        .addGroup(jpRegistroLayout.createSequentialGroup()
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(btModificarMalla)))))
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btModificarFacultad)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jpRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 1010, 260));

        bg.setBackground(new java.awt.Color(255, 250, 205));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCrearCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearCActionPerformed
        try {
            guardar(2);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar carrera", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCrearCActionPerformed

    private void btCrearMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearMActionPerformed
        try {
            guardar(3);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar malla curricular", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCrearMActionPerformed

    private void btCrearFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearFActionPerformed
        try {
            guardar(1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar facultad", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCrearFActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

    }//GEN-LAST:event_formMouseDragged

    private void jpMallaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpMallaMouseEntered

    }//GEN-LAST:event_jpMallaMouseEntered

    private void btGCFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCFacultadActionPerformed
        try {
            modificar(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCFacultadActionPerformed

    private void btGCCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCCarreraActionPerformed
        try {
            modificar(2);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCCarreraActionPerformed

    private void btGCMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCMallaActionPerformed
        try {
            modificar(3);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCMallaActionPerformed

    private void btCrearAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearAActionPerformed
        try {
            guardar(5);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar asignatura", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCrearAActionPerformed

    private void btGCAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCAsignaturaActionPerformed
        try {
            modificar(4);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCAsignaturaActionPerformed

    private void cbxCarreraAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCarreraAActionPerformed
        if (!fileMalla.getMallas().isEmpty() && cbxCarreraA.getSelectedItem() != null) {
            Carrera carrera = Utilvista.obtenerCarreraControl(cbxCarreraA);
            try {
                Utilvista.cargarComboMalla(cbxMallaA, carrera);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar mallas curriculares", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxCarreraAActionPerformed

    private void cbxCarreraAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCarreraAMouseClicked
    }//GEN-LAST:event_cbxCarreraAMouseClicked

    private void cbxMallaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMallaAActionPerformed
        if (!fileCiclo.getCiclos().isEmpty() && cbxMallaA.getSelectedItem() != null) {
            MallaCurricular malla = Utilvista.obtenerMallaControl(cbxMallaA);
            try {
                Utilvista.cargarComboCiclos(cbxCicloA, malla);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar ciclos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxMallaAActionPerformed

    private void cbxFacultadCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadCActionPerformed

    }//GEN-LAST:event_cbxFacultadCActionPerformed

    private void btCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarreraActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(true);
        jpMalla.setVisible(false);
        jpCiclo.setVisible(false);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btCarreraActionPerformed

    private void btFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFacultadActionPerformed
        jpFacultad.setVisible(true);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpCiclo.setVisible(false);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btFacultadActionPerformed

    private void btAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignaturaActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpCiclo.setVisible(false);
        jpAsignatura.setVisible(true);
    }//GEN-LAST:event_btAsignaturaActionPerformed

    private void btMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMallaActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(true);
        jpCiclo.setVisible(false);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btMallaActionPerformed

    private void cbxFacultadMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadMActionPerformed
        if (!fileCarrera.getCarreras().isEmpty() && cbxFacultadM.getSelectedItem() != null) {
            Facultad facultad = Utilvista.obtenerFacultadControl(cbxFacultadM);
            try {
                Utilvista.cargarComboCarreras(cbxCarreraM, facultad);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar carreras", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxFacultadMActionPerformed

    private void btCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCicloActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpCiclo.setVisible(true);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btCicloActionPerformed

    private void cbxMallaClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMallaClActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMallaClActionPerformed

    private void btCrearClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearClActionPerformed
        try {
            guardar(4);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar ciclo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCrearClActionPerformed

    private void btGCCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCCicloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btGCCicloActionPerformed

    private void cbxCarreraClMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCarreraClMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCarreraClMouseClicked

    private void cbxCarreraClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCarreraClActionPerformed
        if (!fileMalla.getMallas().isEmpty() && cbxCarreraCl.getSelectedItem() != null) {
            Carrera carrera = Utilvista.obtenerCarreraControl(cbxCarreraCl);
            try {
                Utilvista.cargarComboMalla(cbxMallaCl, carrera);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar mallas curriculares", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxCarreraClActionPerformed

    private void cbxEstadoClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoClActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoClActionPerformed

    private void cbxFacultadClMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxFacultadClMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFacultadClMouseClicked

    private void cbxFacultadClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadClActionPerformed
        if (!fileCarrera.getCarreras().isEmpty() && cbxFacultadCl.getSelectedItem() != null) {
            Facultad facultad = Utilvista.obtenerFacultadControl(cbxFacultadCl);
            try {
                Utilvista.cargarComboCarreras(cbxCarreraCl, facultad);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar carreras", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxFacultadClActionPerformed

    private void cbxFacultadAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxFacultadAMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFacultadAMouseClicked

    private void cbxFacultadAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadAActionPerformed
        if (!fileCarrera.getCarreras().isEmpty() && cbxFacultadA.getSelectedItem() != null) {
            Facultad facultad = Utilvista.obtenerFacultadControl(cbxFacultadA);
            try {
                Utilvista.cargarComboCarreras(cbxCarreraA, facultad);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar carreras", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxFacultadAActionPerformed

    private void cbxCicloAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCicloAMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCicloAMouseClicked

    private void cbxCicloAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCicloAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCicloAActionPerformed

    private void btModificarCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarCicloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btModificarCicloActionPerformed

    private void lstCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseClicked
        if (lstCarrera.getSelectedValue() != null) {
            cargarVista(2);
            btModificarCarrera.setEnabled(true);
            btModificarMalla.setEnabled(false);
            btModificarCiclo.setEnabled(false);
            btModificarAsignatura.setEnabled(false);
            if (!fileMalla.getMallas().isEmpty()) {
                Object c = lstCarrera.getSelectedValue();
                Carrera carrera = (Carrera) c;
                try {
                    Utilvista.cargarListaMallas(lstMalla, carrera);
                } catch (EmptyException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar lista de mallas curriculares", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna carrera disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstCarreraMouseClicked

    private void btModificarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarCarreraActionPerformed
        cargarVista(2);
        btGCCarrera.setEnabled(true);
        cbxFacultadC.setEnabled(true);
        txtNombreC.setEnabled(true);
        spnCiclo.setEnabled(true);
    }//GEN-LAST:event_btModificarCarreraActionPerformed

    private void btModificarFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarFacultadActionPerformed
        cargarVista(1);
        btGCFacultad.setEnabled(true);
        txtNombreF.setEditable(true);
        txtCodigoF.setEditable(true);
        txtCorreoF.setEditable(true);
    }//GEN-LAST:event_btModificarFacultadActionPerformed

    private void lstFacultadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstFacultadMouseClicked
        if (lstFacultad.getSelectedValue() != null) {
            cargarVista(1);

            btModificarFacultad.setEnabled(true);
            btModificarCarrera.setEnabled(false);
            btModificarMalla.setEnabled(false);
            btModificarCiclo.setEnabled(false);
            btModificarAsignatura.setEnabled(false);

            if (!fileCarrera.getCarreras().isEmpty()) {
                Object f = lstFacultad.getSelectedValue();
                Facultad facultad = (Facultad) f;
                try {
                    Utilvista.cargarListaCarreras(lstCarrera, facultad);
                } catch (EmptyException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar lista de carreras", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna facultad disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstFacultadMouseClicked

    private void lstAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAsignaturaMouseClicked
        if (lstAsignatura.getSelectedValue() != null) {
            cargarVista(5);
            btModificarAsignatura.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna asignatura disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstAsignaturaMouseClicked

    private void btModificarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarAsignaturaActionPerformed
        cargarVista(4);
        cbxCarreraA.setEnabled(true);
        cbxMallaA.setEnabled(true);
        txtNombreA.setEnabled(true);
        txtCodigoA.setEnabled(true);
        spnTotalHorasA.setEnabled(true);
        btGCAsignatura.setEnabled(true);
    }//GEN-LAST:event_btModificarAsignaturaActionPerformed

    private void btLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpiarActionPerformed
        try {
            limpiar();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btLimpiarActionPerformed

    private void btModificarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarMallaActionPerformed
        cargarVista(3);
        cbxCarreraM.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPensum.setEnabled(true);
        btGCMalla.setEnabled(true);
        cbxVigencia.setEnabled(true);
    }//GEN-LAST:event_btModificarMallaActionPerformed

    private void lstMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMallaMouseClicked
        if (lstMalla.getSelectedValue() != null) {
            cargarVista(3);
            btModificarMalla.setEnabled(true);
            btModificarCiclo.setEnabled(false);
            btModificarAsignatura.setEnabled(false);

            if (!fileCiclo.getCiclos().isEmpty()) {
                Object m = lstMalla.getSelectedValue();
                MallaCurricular malla = (MallaCurricular) m;
                try {
                    Utilvista.cargarListaCiclos(lstCiclo, malla);
                } catch (EmptyException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar lista de ciclos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna malla curricular disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstMallaMouseClicked

    private void lstCicloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCicloMouseClicked
        Object cl = lstCiclo.getSelectedValue();
        Ciclo ciclo = (Ciclo) cl;
        try {
            Utilvista.cargarListaAsignaturas(lstAsignatura, ciclo);
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar lista de asignaturas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstCicloMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmAcademico().setVisible(true);
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btAsignatura;
    private javax.swing.JButton btCarrera;
    private javax.swing.JButton btCiclo;
    private javax.swing.JButton btCrearA;
    private javax.swing.JButton btCrearC;
    private javax.swing.JButton btCrearCl;
    private javax.swing.JButton btCrearF;
    private javax.swing.JButton btCrearM;
    private javax.swing.JButton btFacultad;
    private javax.swing.JButton btGCAsignatura;
    private javax.swing.JButton btGCCarrera;
    private javax.swing.JButton btGCCiclo;
    private javax.swing.JButton btGCFacultad;
    private javax.swing.JButton btGCMalla;
    private javax.swing.JButton btLimpiar;
    private javax.swing.JButton btMalla;
    private javax.swing.JButton btModificarAsignatura;
    private javax.swing.JButton btModificarCarrera;
    private javax.swing.JButton btModificarCiclo;
    private javax.swing.JButton btModificarFacultad;
    private javax.swing.JButton btModificarMalla;
    private javax.swing.JComboBox<String> cbxCarreraA;
    private javax.swing.JComboBox<String> cbxCarreraCl;
    private javax.swing.JComboBox<String> cbxCarreraM;
    private javax.swing.JComboBox<String> cbxCicloA;
    private javax.swing.JComboBox<String> cbxEstadoCl;
    private javax.swing.JComboBox<String> cbxFacultadA;
    private javax.swing.JComboBox<String> cbxFacultadC;
    private javax.swing.JComboBox<String> cbxFacultadCl;
    private javax.swing.JComboBox<String> cbxFacultadM;
    private javax.swing.JComboBox<String> cbxMallaA;
    private javax.swing.JComboBox<String> cbxMallaCl;
    private javax.swing.JComboBox<String> cbxVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpAll;
    private javax.swing.JPanel jpAsignatura;
    private javax.swing.JPanel jpCarrera;
    private javax.swing.JPanel jpCiclo;
    private javax.swing.JPanel jpFacultad;
    private javax.swing.JPanel jpMalla;
    private javax.swing.JPanel jpRegistro;
    private javax.swing.JList<String> lstAsignatura;
    private javax.swing.JList<String> lstCarrera;
    private javax.swing.JList<String> lstCiclo;
    private javax.swing.JList<String> lstFacultad;
    private javax.swing.JList<String> lstMalla;
    private javax.swing.JSpinner spnCiclo;
    private javax.swing.JSpinner spnTotalHorasA;
    private javax.swing.JTextField txtCarreraAR;
    private javax.swing.JTextField txtCarreraClR;
    private javax.swing.JTextField txtCarreraMR;
    private javax.swing.JTextField txtCicloAR;
    private javax.swing.JTextField txtCodigoA;
    private javax.swing.JTextField txtCodigoC;
    private javax.swing.JTextField txtCodigoF;
    private javax.swing.JTextField txtCorreoF;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFacultadAR;
    private javax.swing.JTextField txtFacultadCR;
    private javax.swing.JTextField txtFacultadClR;
    private javax.swing.JTextField txtFacultadMR;
    private javax.swing.JTextField txtMallaAR;
    private javax.swing.JTextField txtMallaClR;
    private javax.swing.JTextField txtNombreA;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombreCl;
    private javax.swing.JTextField txtNombreF;
    private javax.swing.JTextField txtPensum;
    // End of variables declaration//GEN-END:variables
}
