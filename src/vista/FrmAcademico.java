package vista;

import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import controlador.TDA.listas.DynamicList;
import controlador.Utiles.Utiles;

import controlador.FacultadControl;
import controlador.CarreraControl;
import controlador.MallaControl;
import controlador.AsignaturaControl;

import controlador.Academico.AsignaturaArchivos;
import controlador.Academico.CarreraArchivos;
import controlador.Academico.FacultadArchivos;
import controlador.Academico.MallaArchivos;

import modelo.Facultad;
import modelo.Carrera;
import modelo.MallaCurricular;
import modelo.Asignatura;

import vista.listas.tablas.TablaAsignatura;

import vista.listas.util.Utilvista;

public class FrmAcademico extends javax.swing.JFrame {

    private FacultadControl facultadControl = new FacultadControl();
    private CarreraControl carreraControl = new CarreraControl();
    private MallaControl mallaControl = new MallaControl();
    private AsignaturaControl asignaturaControl = new AsignaturaControl();

    private FacultadArchivos fileFacultad = new FacultadArchivos();
    private CarreraArchivos fileCarrera = new CarreraArchivos();
    private MallaArchivos fileMalla = new MallaArchivos();
    private AsignaturaArchivos fileAsignatura = new AsignaturaArchivos();

    public Boolean verificar(Integer var) {
        switch (var) {
            case 1:
                return (!txtNombreF.getText().trim().isEmpty());
            case 2:
                String flag1 = cbxFacultad.getSelectedItem() != null ? cbxFacultad.getSelectedItem().toString().trim() : "";
                return (!flag1.isEmpty()
                        && !txtNombreC.getText().trim().isEmpty()
                        && (Integer )spnCiclo.getValue() != 0);
            case 3:
                String flag2 = cbxCarrera.getSelectedItem() != null ? cbxCarrera.getSelectedItem().toString().trim() : "";
                return (!flag2.isEmpty()
                        && !txtDescripcion.getText().trim().isEmpty()
                        && !txtPensum.getText().trim().isEmpty());
            case 4:
                String flag3 = cbxMalla.getSelectedItem() != null ? cbxMalla.getSelectedItem().toString().trim() : "";
                return (!flag3.isEmpty()
                        && !txtNombreA.getText().trim().isEmpty()
                        && !txtCodigoA.getText().trim().isEmpty()
                        && (Integer) spnTotalHorasA.getValue() != 0);
            default:
                throw new AssertionError();
        }
    }

    private void cargarVista(Integer var) {
        switch (var) {
            case 1:
                limpiarSoft();
                try {
                    facultadControl.setFacultad(fileFacultad.getFacultades().getInfo(lstFacultad.getSelectedIndex()));
                    txtNombreF.setText(facultadControl.getFacultad().getNombre());
                    txtNombreF.setEnabled(false);
                    btCrearF.setEnabled(false);
                } catch (Exception ex) {
                    Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2: 
                try {
                limpiarMalla();
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }

            Object c = lstCarrera.getSelectedValue();
            Carrera carrera = (Carrera) c;
            try {
                carreraControl.setCarrera(carrera);
                cbxFacultad.setSelectedIndex(carreraControl.getCarrera().getIdFacultad());
                txtNombreC.setText(carreraControl.getCarrera().getNombre());
                spnCiclo.setValue(carreraControl.getCarrera().getNumCiclos());

                btCrearC.setEnabled(false);
                cbxFacultad.setEnabled(false);
                txtNombreC.setEnabled(false);
                spnCiclo.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case 3: 
                try {
                limpiarMalla();
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }

            Object m = lstMalla.getSelectedValue();
            MallaCurricular malla = (MallaCurricular) m;
            try {
                mallaControl.setMalla(malla);
                cbxCarrera.setSelectedIndex(mallaControl.getMalla().getIdCarrera());
                txtDescripcion.setText(mallaControl.getMalla().getDescripcion());
                txtPensum.setText(mallaControl.getMalla().getPensum());
                if (mallaControl.getMalla().isVigencia()) {
                    cbxVigencia.setSelectedIndex(0);
                } else if (mallaControl.getMalla().isVigencia() == false) {
                    cbxVigencia.setSelectedIndex(1);
                }

                btCrearM.setEnabled(false);
                cbxCarrera.setEnabled(false);
                cbxVigencia.setEnabled(false);
                txtDescripcion.setEnabled(false);
                txtPensum.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case 4:
                try {
                limpiarAsignatura();
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object a = lstAsignatura.getSelectedValue();
            Asignatura asignatura = (Asignatura) a;
            try {
                asignaturaControl.setAsignatura(asignatura);
                cbxCarreraMalla.setSelectedIndex(fileCarrera.getCarreras().getInfo(fileMalla.getMallas().getInfo(asignaturaControl.getAsignatura().getIdMalla()).getIdCarrera()).getId());
                cbxMalla.setSelectedIndex(-1);
                txtMallaRegistrada.setText(fileMalla.getMallas().getInfo(asignaturaControl.getAsignatura().getIdMalla()).toString());
                txtNombreA.setText(asignaturaControl.getAsignatura().getNombre());
                txtCodigoA.setText(asignaturaControl.getAsignatura().getCodigo());
                spnTotalHorasA.setValue(asignaturaControl.getAsignatura().getTotalHoras());

                btCrearA.setEnabled(false);
                cbxCarreraMalla.setEnabled(false);
                cbxMalla.setEnabled(false);
                txtNombreA.setEnabled(false);
                txtCodigoA.setEnabled(false);
                spnTotalHorasA.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            default:
                throw new AssertionError();
        }
    }

    private void limpiar() throws EmptyException {
        try {
            Utilvista.cargarComboFacultades(cbxFacultad);
            Utilvista.cargarComboCarreras(cbxCarrera);
            Utilvista.cargarComboCarreras(cbxCarreraMalla);
            Utilvista.cargarListaFacultades(lstFacultad);
            limpiarSoft();
            limpiarMalla();
            limpiarAsignatura();
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        txtNombreF.setText("");

        btGCFacultad.setEnabled(false);
        btGCCarrera.setEnabled(false);
        btGCMalla.setEnabled(false);
        btGCAsignatura.setEnabled(false);
        btModificarFacultad.setEnabled(false);
        btModificarCarrera.setEnabled(false);
        btModificarMalla.setEnabled(false);
        btModificarAsignatura.setEnabled(false);

        habilitarTodo();

        facultadControl.setFacultad(null);
        carreraControl.setCarrera(null);
        mallaControl.setMalla(null);
    }

    private void limpiarSoft() {
        Utilvista.limpiarLista(lstCarrera);
        Utilvista.limpiarLista(lstMalla);
        Utilvista.limpiarLista(lstAsignatura);

        cbxFacultad.setSelectedIndex(-1);
        txtNombreC.setText("");
        spnCiclo.setValue(0);
        try {
            limpiarMalla();
            limpiarAsignatura();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiarMalla() throws EmptyException {
        limpiarAsignatura();
        cbxCarrera.setSelectedIndex(-1);
        txtDescripcion.setText("");
        txtPensum.setText("");

        cbxCarrera.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPensum.setEnabled(true);
        btCrearM.setEnabled(true);
    }

    private void limpiarAsignatura() throws EmptyException {
        cbxCarreraMalla.setSelectedIndex(-1);
        cbxMalla.setSelectedIndex(-1);
        cbxVigencia.setSelectedIndex(0);
        txtMallaRegistrada.setText("");
        txtNombreA.setText("");
        txtCodigoA.setText("");
        spnTotalHorasA.setValue(0);
        asignaturaControl.setAsignatura(null);
        
        btCrearA.setEnabled(true);
        cbxCarreraMalla.setEnabled(true);
        cbxMalla.setEnabled(true);
        txtNombreA.setEnabled(true);
        txtCodigoA.setEnabled(true);
        spnTotalHorasA.setEnabled(true);
    }

    private void habilitarTodo() {
        txtNombreF.setEnabled(true);
        btCrearF.setEnabled(true);
        btCrearC.setEnabled(true);
        cbxFacultad.setEnabled(true);
        txtNombreC.setEnabled(true);
        spnCiclo.setEnabled(true);
        btCrearM.setEnabled(true);
        cbxCarrera.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPensum.setEnabled(true);
    }

    private void guardar(Integer flag) throws EmptyException {
        switch (flag) {
            case 1:
                if (verificar(1)) {
                    facultadControl.getFacultad().setNombre(txtNombreF.getText());
                    if (facultadControl.guardar()) {
                        fileFacultad.persist(fileFacultad.setFacultad(facultadControl.getFacultad()));
                        JOptionPane.showMessageDialog(null, "Datos guardados");
                        limpiar();
                        facultadControl.setFacultad(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 2:
                if (verificar(2)) {
                    carreraControl.getCarrera().setIdFacultad(Utilvista.obtenerFacultadControl(cbxFacultad).getId());
                    carreraControl.getCarrera().setNombre(txtNombreC.getText());
                    carreraControl.getCarrera().setNumCiclos((Integer) spnCiclo.getValue());

                    if (carreraControl.guardar()) {
                        fileCarrera.persist(fileCarrera.setCarrera(carreraControl.getCarrera()));
                        JOptionPane.showMessageDialog(null, "Datos guardados");
                        limpiar();
                        carreraControl.setCarrera(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 3:
                if (verificar(3)) {
                    mallaControl.getMalla().setIdCarrera(cbxCarrera.getSelectedIndex());
                    mallaControl.getMalla().setDescripcion(txtDescripcion.getText());
                    mallaControl.getMalla().setPensum(txtPensum.getText());
                    if (carreraControl.guardar()) {
                        fileMalla.persist(fileMalla.setMalla(mallaControl.getMalla()));
                        JOptionPane.showMessageDialog(null, "Datos guardados");
                        limpiar();
                        mallaControl.setMalla(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 4:
                if (verificar(4)) {
                    if (fileAsignatura.buscarCodigo(txtCodigoA.getText()) == false) {
                        asignaturaControl.getAsignatura().setIdMalla(Utilvista.obtenerMallaControl(cbxMalla).getId());
                        asignaturaControl.getAsignatura().setNombre(txtNombreA.getText());
                        asignaturaControl.getAsignatura().setCodigo(txtCodigoA.getText());
                        asignaturaControl.getAsignatura().setTotalHoras((Integer) spnTotalHorasA.getValue());

                        if (asignaturaControl.guardar()) {
                            fileAsignatura.persist(fileAsignatura.setCarrera(asignaturaControl.getAsignatura()));
                            JOptionPane.showMessageDialog(null, "Asignatura guardada");
                            limpiar();
                            asignaturaControl.setAsignatura(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
                        }
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

    private void modificar(Integer flag) throws EmptyException {
        switch (flag) {
            case 1:
                facultadControl.getFacultad().setNombre(txtNombreF.getText());
                if (fileFacultad.merge(facultadControl.getFacultad(), facultadControl.getFacultad().getId())) {
                    JOptionPane.showMessageDialog(null, "Cambios guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar los cambios, hubo un error");
                }
                break;
            case 2:
                carreraControl.getCarrera().setIdFacultad(Utilvista.obtenerFacultadControl(cbxFacultad).getId());
                carreraControl.getCarrera().setNombre(txtNombreC.getText());
                carreraControl.getCarrera().setNumCiclos((Integer) spnCiclo.getValue());
                if (fileCarrera.merge(carreraControl.getCarrera(), carreraControl.getCarrera().getId())) {
                    JOptionPane.showMessageDialog(null, "Cambios guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar los cambios, hubo un error");
                }
                break;
            case 3:
                mallaControl.getMalla().setIdCarrera(cbxCarrera.getSelectedIndex());
                mallaControl.getMalla().setDescripcion(txtDescripcion.getText());
                mallaControl.getMalla().setPensum(txtPensum.getText());
                if(cbxVigencia.getSelectedIndex() == 0){
                    mallaControl.getMalla().setVigencia(true);
                } else if(cbxVigencia.getSelectedIndex() == 1){
                    mallaControl.getMalla().setVigencia(false);
                }
                
                if (fileMalla.merge(mallaControl.getMalla(), mallaControl.getMalla().getId())) {
                    JOptionPane.showMessageDialog(null, "Cambios guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar los cambios, hubo un error");
                }
                break;
            case 4:
                if(verificar(4)){
                asignaturaControl.getAsignatura().setIdMalla(Utilvista.obtenerMallaControl(cbxMalla).getId());
                asignaturaControl.getAsignatura().setNombre(txtNombreA.getText());
                asignaturaControl.getAsignatura().setCodigo(txtCodigoA.getText());
                asignaturaControl.getAsignatura().setTotalHoras((Integer) spnTotalHorasA.getValue());
                if (fileAsignatura.merge(asignaturaControl.getAsignatura(), asignaturaControl.getAsignatura().getId())) {
                    JOptionPane.showMessageDialog(null, "Cambios guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar los cambios, hubo un error");
                }
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Creates new form FrmAcademico
     */
    public FrmAcademico() throws EmptyException {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jpFacultad = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreF = new javax.swing.JTextField();
        btCrearF = new javax.swing.JButton();
        btGCFacultad = new javax.swing.JButton();
        jpCarrera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxFacultad = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNombreC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spnCiclo = new javax.swing.JSpinner();
        btCrearC = new javax.swing.JButton();
        btGCCarrera = new javax.swing.JButton();
        jpMalla = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxCarrera = new javax.swing.JComboBox<>();
        txtPensum = new javax.swing.JTextField();
        btCrearM = new javax.swing.JButton();
        btGCMalla = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cbxVigencia = new javax.swing.JComboBox<>();
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
        jpAsignatura = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        cbxMalla = new javax.swing.JComboBox<>();
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
        cbxCarreraMalla = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txtMallaRegistrada = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel15.setText("Academico General");

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel1.setText("Nueva Facultad");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Nombre:");

        txtNombreF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btCrearF.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearF.setText("Crear Facultad");
        btCrearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearFActionPerformed(evt);
            }
        });

        btGCFacultad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btGCFacultad.setText("Guardar Cambios");
        btGCFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCFacultadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFacultadLayout = new javax.swing.GroupLayout(jpFacultad);
        jpFacultad.setLayout(jpFacultadLayout);
        jpFacultadLayout.setHorizontalGroup(
            jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFacultadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFacultadLayout.createSequentialGroup()
                        .addGroup(jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jpFacultadLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreF, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpFacultadLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btCrearF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGCFacultad)))
                .addContainerGap())
        );
        jpFacultadLayout.setVerticalGroup(
            jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFacultadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCrearF)
                    .addComponent(btGCFacultad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel2.setText("Nueva Carrera");

        cbxFacultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Facultad:");

        txtNombreC.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setText("Numero de Ciclos:");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Nombre:");

        spnCiclo.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btCrearC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearC.setText("Crear Carrera");
        btCrearC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearCActionPerformed(evt);
            }
        });

        btGCCarrera.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btGCCarrera.setText("Guardar Cambios");
        btGCCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCCarreraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCarreraLayout = new javax.swing.GroupLayout(jpCarrera);
        jpCarrera.setLayout(jpCarreraLayout);
        jpCarreraLayout.setHorizontalGroup(
            jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCarreraLayout.createSequentialGroup()
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCarreraLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(jpCarreraLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpCarreraLayout.createSequentialGroup()
                                .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(spnCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpCarreraLayout.createSequentialGroup()
                                .addComponent(btCrearC)
                                .addGap(18, 18, 18)
                                .addComponent(btGCCarrera)))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jpCarreraLayout.setVerticalGroup(
            jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCarreraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(spnCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGCCarrera)
                    .addComponent(btCrearC))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jpMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpMallaMouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel3.setText("Nueva Malla");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel9.setText("Descripcion:");

        txtDescripcion.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Pensum:");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Carrera:");

        cbxCarrera.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtPensum.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btCrearM.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearM.setText("Crear Malla");
        btCrearM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearMActionPerformed(evt);
            }
        });

        btGCMalla.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btGCMalla.setText("Guardar Cambios");
        btGCMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCMallaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel16.setText("Vigencia:");

        cbxVigencia.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vigente", "No Vigente" }));
        cbxVigencia.setEnabled(false);

        javax.swing.GroupLayout jpMallaLayout = new javax.swing.GroupLayout(jpMalla);
        jpMalla.setLayout(jpMallaLayout);
        jpMallaLayout.setHorizontalGroup(
            jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jpMallaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMallaLayout.createSequentialGroup()
                                .addComponent(btCrearM)
                                .addGap(18, 18, 18)
                                .addComponent(btGCMalla))
                            .addGroup(jpMallaLayout.createSequentialGroup()
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(cbxVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpMallaLayout.createSequentialGroup()
                                .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtPensum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpMallaLayout.setVerticalGroup(
            jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtPensum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(25, 25, 25)
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel16)
                    .addComponent(cbxVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCrearM)
                    .addComponent(btGCMalla))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Mallas Curriculares:");

        lstMalla.setFont(new java.awt.Font("Roboto Thin", 1, 12)); // NOI18N
        lstMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMallaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstMalla);

        btModificarMalla.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btModificarMalla.setText("Modificar");
        btModificarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarMallaActionPerformed(evt);
            }
        });

        btLimpiar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btLimpiar.setText("Limpiar Todo");
        btLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel30.setText("Asignaturas:");

        btModificarAsignatura.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btModificarAsignatura.setText("Modificar");
        btModificarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarAsignaturaActionPerformed(evt);
            }
        });

        lstAsignatura.setFont(new java.awt.Font("Roboto Thin", 1, 12)); // NOI18N
        lstAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lstAsignatura);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Facultades:");

        lstFacultad.setFont(new java.awt.Font("Roboto Thin", 1, 12)); // NOI18N
        lstFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstFacultadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstFacultad);

        btModificarFacultad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btModificarFacultad.setText("Modificar");
        btModificarFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarFacultadActionPerformed(evt);
            }
        });

        btModificarCarrera.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btModificarCarrera.setText("Modificar");
        btModificarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarCarreraActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setText("Carreras:");

        lstCarrera.setFont(new java.awt.Font("Roboto Thin", 1, 12)); // NOI18N
        lstCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCarreraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstCarrera);

        javax.swing.GroupLayout jpRegistroLayout = new javax.swing.GroupLayout(jpRegistro);
        jpRegistro.setLayout(jpRegistroLayout);
        jpRegistroLayout.setHorizontalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btModificarFacultad)
                        .addGap(114, 114, 114))
                    .addComponent(jLabel11)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btModificarCarrera)
                        .addGap(115, 115, 115))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(btModificarMalla))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(193, 193, 193)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(btModificarAsignatura)
                        .addGap(119, 119, 119))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel30)))
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(591, 591, 591))
        );
        jpRegistroLayout.setVerticalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpRegistroLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btModificarCarrera))
                        .addGroup(jpRegistroLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(12, 12, 12)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btModificarFacultad)))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btModificarMalla))
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btModificarAsignatura)))))
                .addGap(18, 18, 18)
                .addComponent(btLimpiar)
                .addContainerGap())
        );

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel25.setText("Nueva Asignatura");

        cbxMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMallaActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel26.setText("Malla Curricular:");

        txtNombreA.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel27.setText("Total de Horas:");

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel28.setText("Nombre:");

        spnTotalHorasA.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btCrearA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearA.setText("Crear Asignatura");
        btCrearA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearAActionPerformed(evt);
            }
        });

        btGCAsignatura.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btGCAsignatura.setText("Guardar Cambios");
        btGCAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCAsignaturaActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel29.setText("Codigo:");

        txtCodigoA.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("Carrera:");

        cbxCarreraMalla.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxCarreraMalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxCarreraMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxCarreraMallaMouseClicked(evt);
            }
        });
        cbxCarreraMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCarreraMallaActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel31.setText("Malla Registrada");

        txtMallaRegistrada.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtMallaRegistrada.setEnabled(false);

        javax.swing.GroupLayout jpAsignaturaLayout = new javax.swing.GroupLayout(jpAsignatura);
        jpAsignatura.setLayout(jpAsignaturaLayout);
        jpAsignaturaLayout.setHorizontalGroup(
            jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsignaturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAsignaturaLayout.createSequentialGroup()
                        .addGap(0, 60, Short.MAX_VALUE)
                        .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpAsignaturaLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpAsignaturaLayout.createSequentialGroup()
                                        .addComponent(btCrearA)
                                        .addGap(21, 21, 21)
                                        .addComponent(btGCAsignatura))
                                    .addGroup(jpAsignaturaLayout.createSequentialGroup()
                                        .addComponent(txtNombreA, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel29)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCodigoA, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel27)
                                        .addGap(18, 18, 18)
                                        .addComponent(spnTotalHorasA, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(jpAsignaturaLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxCarreraMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMallaRegistrada, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))))
                    .addGroup(jpAsignaturaLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jpAsignaturaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpAsignaturaLayout.setVerticalGroup(
            jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsignaturaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel25)
                .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAsignaturaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCarreraMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(20, 20, 20)
                        .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(txtCodigoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel27)
                            .addComponent(spnTotalHorasA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btCrearA)
                            .addComponent(btGCAsignatura)))
                    .addGroup(jpAsignaturaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jpAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMallaRegistrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(573, 573, 573))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jpRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jpFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jpMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jpMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jpAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jpRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCrearCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearCActionPerformed
        try {
            guardar(2);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearCActionPerformed

    private void btCrearMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearMActionPerformed
        try {
            guardar(3);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearMActionPerformed

    private void btCrearFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearFActionPerformed

        try {
            guardar(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearFActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

    }//GEN-LAST:event_formMouseDragged

    private void jpMallaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpMallaMouseEntered

    }//GEN-LAST:event_jpMallaMouseEntered

    private void lstFacultadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstFacultadMouseClicked
        if (lstFacultad.getSelectedValue() != null) {
            cargarVista(1);
            btModificarFacultad.setEnabled(true);
            btModificarCarrera.setEnabled(false);
            btModificarMalla.setEnabled(false);
            btCrearC.setEnabled(true);
            cbxFacultad.setEnabled(true);
            txtNombreC.setEnabled(true);
            spnCiclo.setEnabled(true);
            Object f = lstFacultad.getSelectedValue();
            Facultad facultad = (Facultad) f;
            try {
                Utilvista.cargarListaCarreras(lstCarrera, facultad);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna facultad disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstFacultadMouseClicked

    private void lstCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseClicked
        if (lstCarrera.getSelectedValue() != null) {
            cargarVista(2);
            btModificarCarrera.setEnabled(true);
            btModificarMalla.setEnabled(false);
            Object c = lstCarrera.getSelectedValue();
            Carrera carrera = (Carrera) c;
            try {
                Utilvista.cargarListaMallas(lstMalla, carrera);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna carrera disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstCarreraMouseClicked

    private void lstMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMallaMouseClicked
        if (lstMalla.getSelectedValue() != null) {
            cargarVista(3);
            btModificarMalla.setEnabled(true);
            Object m = lstMalla.getSelectedValue();
            MallaCurricular malla = (MallaCurricular) m;
            try {
                Utilvista.cargarListaAsignaturas(lstAsignatura, malla);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna malla curricular disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstMallaMouseClicked

    private void btModificarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarMallaActionPerformed
        cargarVista(3);
        cbxCarrera.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPensum.setEnabled(true);
        btGCMalla.setEnabled(true);
        cbxVigencia.setEnabled(true);
    }//GEN-LAST:event_btModificarMallaActionPerformed

    private void btModificarFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarFacultadActionPerformed
        cargarVista(1);
        btGCFacultad.setEnabled(true);
        txtNombreF.setEnabled(true);
    }//GEN-LAST:event_btModificarFacultadActionPerformed

    private void btModificarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarCarreraActionPerformed
        cargarVista(2);
        btGCCarrera.setEnabled(true);
        cbxFacultad.setEnabled(true);
        txtNombreC.setEnabled(true);
        spnCiclo.setEnabled(true);
    }//GEN-LAST:event_btModificarCarreraActionPerformed

    private void btLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpiarActionPerformed
        try {
            limpiar();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btLimpiarActionPerformed

    private void btGCFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCFacultadActionPerformed
        try {
            modificar(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCFacultadActionPerformed

    private void btGCCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCCarreraActionPerformed
        try {
            modificar(2);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCCarreraActionPerformed

    private void btGCMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCMallaActionPerformed
        try {
            modificar(3);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCMallaActionPerformed

    private void btCrearAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearAActionPerformed
        try {
            guardar(4);
        } catch (EmptyException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btCrearAActionPerformed

    private void btGCAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGCAsignaturaActionPerformed
        try {
            modificar(4);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btGCAsignaturaActionPerformed

    private void btModificarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarAsignaturaActionPerformed
        cargarVista(4);
        cbxCarreraMalla.setEnabled(true);
        cbxMalla.setEnabled(true);
        txtNombreA.setEnabled(true);
        txtCodigoA.setEnabled(true);
        spnTotalHorasA.setEnabled(true);
        btGCAsignatura.setEnabled(true);
    }//GEN-LAST:event_btModificarAsignaturaActionPerformed

    private void lstAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAsignaturaMouseClicked
        if (lstAsignatura.getSelectedValue() != null) {
            cargarVista(4);
            btModificarAsignatura.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna asignatura disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstAsignaturaMouseClicked

    private void cbxCarreraMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCarreraMallaActionPerformed
        if (cbxCarreraMalla.getSelectedItem() != null) {
            Carrera carrera = Utilvista.obtenerCarreraControl(cbxCarreraMalla);
            try {
                Utilvista.cargarComboMalla(cbxMalla, carrera);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbxCarreraMallaActionPerformed

    private void cbxCarreraMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCarreraMallaMouseClicked

    }//GEN-LAST:event_cbxCarreraMallaMouseClicked

    private void cbxMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMallaActionPerformed
        if (cbxMalla.getSelectedItem() != null) {
            MallaCurricular mallaAsg = (MallaCurricular) cbxMalla.getSelectedItem();
            txtMallaRegistrada.setText(mallaAsg.toString());
        }
    }//GEN-LAST:event_cbxMallaActionPerformed

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
    private javax.swing.JButton btCrearA;
    private javax.swing.JButton btCrearC;
    private javax.swing.JButton btCrearF;
    private javax.swing.JButton btCrearM;
    private javax.swing.JButton btGCAsignatura;
    private javax.swing.JButton btGCCarrera;
    private javax.swing.JButton btGCFacultad;
    private javax.swing.JButton btGCMalla;
    private javax.swing.JButton btLimpiar;
    private javax.swing.JButton btModificarAsignatura;
    private javax.swing.JButton btModificarCarrera;
    private javax.swing.JButton btModificarFacultad;
    private javax.swing.JButton btModificarMalla;
    private javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JComboBox<String> cbxCarreraMalla;
    private javax.swing.JComboBox<String> cbxFacultad;
    private javax.swing.JComboBox<String> cbxMalla;
    private javax.swing.JComboBox<String> cbxVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpAsignatura;
    private javax.swing.JPanel jpCarrera;
    private javax.swing.JPanel jpFacultad;
    private javax.swing.JPanel jpMalla;
    private javax.swing.JPanel jpRegistro;
    private javax.swing.JList<String> lstAsignatura;
    private javax.swing.JList<String> lstCarrera;
    private javax.swing.JList<String> lstFacultad;
    private javax.swing.JList<String> lstMalla;
    private javax.swing.JSpinner spnCiclo;
    private javax.swing.JSpinner spnTotalHorasA;
    private javax.swing.JTextField txtCodigoA;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtMallaRegistrada;
    private javax.swing.JTextField txtNombreA;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombreF;
    private javax.swing.JTextField txtPensum;
    // End of variables declaration//GEN-END:variables
}
