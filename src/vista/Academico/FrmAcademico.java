package vista.Academico;

import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import controlador.Academico.AsignaturaBD;
import controlador.Academico.CarreraBD;
import controlador.Academico.FacultadBD;
import controlador.Academico.MallaBD;
import java.awt.Color;
import java.sql.SQLException;

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
    private CicloBD fileCiclo = new CicloBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();

    public Boolean verificar(Integer var) {
        switch (var) {
            case 1:
                return (!txtNombreF.getText().trim().isEmpty());
            case 2:
                String flag1 = cbxFacultad.getSelectedItem() != null ? cbxFacultad.getSelectedItem().toString().trim() : "";
                return (!flag1.isEmpty()
                        && !txtNombreC.getText().trim().isEmpty()
                        && (Integer) spnCiclo.getValue() != 0);
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
                    fileFacultad.setFacultad(fileFacultad.getFacultades().getInfo(lstFacultad.getSelectedIndex()));
                    txtNombreF.setText(fileFacultad.getFacultad().getNombre());
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
                fileCarrera.setCarrera(carrera);
                cbxFacultad.setSelectedIndex(fileCarrera.getCarrera().getFacultad_ID());
                txtNombreC.setText(fileCarrera.getCarrera().getNombre());
                spnCiclo.setValue(fileCarrera.getCarrera().getNumCiclos());

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
                fileMalla.setMalla(malla);
                cbxCarrera.setSelectedIndex(fileMalla.getMalla().getCarrera_ID());
                txtDescripcion.setText(fileMalla.getMalla().getDescripcion());
                txtPensum.setText(fileMalla.getMalla().getPensum());
                if (fileMalla.getMalla().getVigencia()) {
                    cbxVigencia.setSelectedIndex(0);
                } else if (fileMalla.getMalla().getVigencia() == false) {
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
                fileAsignatura.setAsignatura(asignatura);
                cbxCarreraMalla.setSelectedIndex(fileCarrera.getCarreras().getInfo(fileMalla.getMallas().getInfo(fileAsignatura.getAsignatura().getMallaCurricular_ID()).getCarrera_ID()).getId());
                cbxMalla.setSelectedIndex(-1);
                txtMallaRegistrada.setText(fileMalla.getMallas().getInfo(fileAsignatura.getAsignatura().getMallaCurricular_ID()).toString());
                txtNombreA.setText(fileAsignatura.getAsignatura().getNombre());
                txtCodigoA.setText(fileAsignatura.getAsignatura().getCodigo());
                spnTotalHorasA.setValue(fileAsignatura.getAsignatura().getTotalHoras());

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
//           Utilvista.cargarComboCarreras(cbxCarrera);
//            Utilvista.cargarComboCarreras(cbxCarreraMalla);
            Utilvista.cargarListaFacultades(lstFacultad);
            limpiarSoft();
//            limpiarMalla();
//            limpiarAsignatura();
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

        fileFacultad.setFacultad(null);
        fileCarrera.setCarrera(null);
        fileMalla.setMalla(null);
    }

    private void limpiarSoft() {
        Utilvista.limpiarLista(lstCarrera);
//        Utilvista.limpiarLista(lstMalla);
//        Utilvista.limpiarLista(lstAsignatura);

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
        cbxCarrera.setSelectedIndex(0);
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
        fileAsignatura.setAsignatura(null);

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

    private void guardar(Integer flag) throws EmptyException, Exception {
        switch (flag) {
            case 1:
                if (verificar(1)) {
                    fileFacultad.getFacultad().setNombre(txtNombreF.getText());
                    fileFacultad.persist(fileFacultad.getFacultad());
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiar();
                    fileFacultad.setFacultad(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                if (verificar(2)) {
                    fileCarrera.getCarrera().setFacultad_ID(cbxFacultad.getSelectedIndex()+1);
                    System.out.println(cbxFacultad.getSelectedIndex());
                    fileCarrera.getCarrera().setNombre(txtNombreC.getText());
                    fileCarrera.getCarrera().setNumCiclos((Integer) spnCiclo.getValue());
                    System.out.println(fileCarrera.getCarrera().getFacultad_ID());
                    fileCarrera.persist(fileCarrera.getCarrera());
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiar();
                    fileCarrera.setCarrera(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 3:
                if (verificar(3)) {
                    fileMalla.getMalla().setCarrera_ID(cbxCarrera.getSelectedIndex()+1);
                    fileMalla.getMalla().setDescripcion(txtDescripcion.getText());
                    fileMalla.getMalla().setPensum(txtPensum.getText());
                    fileMalla.getMalla().setVigencia(true);
                    fileMalla.persist(fileMalla.getMalla());
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiar();
                    fileMalla.setMalla(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 4:
                if (verificar(4)) {
                    if (fileAsignatura.buscarCodigo(txtCodigoA.getText()) == false) {
                        fileAsignatura.getAsignatura().setMallaCurricular_ID(Utilvista.obtenerMallaControl(cbxMalla).getId());
                        fileAsignatura.getAsignatura().setNombre(txtNombreA.getText());
                        fileAsignatura.getAsignatura().setCodigo(txtCodigoA.getText());
                        fileAsignatura.getAsignatura().setTotalHoras((Integer) spnTotalHorasA.getValue());

                        fileAsignatura.persist(fileAsignatura.getAsignatura());
                        JOptionPane.showMessageDialog(null, "Asignatura guardada");
                        limpiar();
                        fileAsignatura.setAsignatura(null);
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

    private void modificar(Integer flag) throws EmptyException, Exception {
        switch (flag) {
            case 1:
                fileFacultad.getFacultad().setNombre(txtNombreF.getText());
                
                fileFacultad.merge(fileFacultad.getFacultad());
                JOptionPane.showMessageDialog(null, "Cambios guardados");
                limpiar();
                break;
            case 2:
                fileCarrera.getCarrera().setFacultad_ID(Utilvista.obtenerFacultadControl(cbxFacultad).getId());
                fileCarrera.getCarrera().setNombre(txtNombreC.getText());
                fileCarrera.getCarrera().setNumCiclos((Integer) spnCiclo.getValue());
                
                fileCarrera.merge(fileCarrera.getCarrera());
                JOptionPane.showMessageDialog(null, "Cambios guardados");
                limpiar();
                break;
            case 3:
                fileMalla.getMalla().setCarrera_ID(cbxCarrera.getSelectedIndex());
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
                    fileAsignatura.getAsignatura().setMallaCurricular_ID(Utilvista.obtenerMallaControl(cbxMalla).getId());
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

    public FrmAcademico() throws EmptyException {
        initComponents();
        this.setLocationRelativeTo(null);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpAsignatura.setVisible(false);
        
        limpiar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jpAll = new javax.swing.JPanel();
        jpCarrera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxFacultad = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNombreC = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spnCiclo = new javax.swing.JSpinner();
        btCrearC = new javax.swing.JButton();
        btGCCarrera = new javax.swing.JButton();
        jpFacultad = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btCrearF = new javax.swing.JButton();
        btGCFacultad = new javax.swing.JButton();
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
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jpMalla = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxCarrera = new javax.swing.JComboBox<>();
        txtPensum = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btCrearM = new javax.swing.JButton();
        btGCMalla = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cbxVigencia = new javax.swing.JComboBox<>();
        btFacultad = new javax.swing.JButton();
        btCarrera = new javax.swing.JButton();
        btMalla = new javax.swing.JButton();
        btAsignatura = new javax.swing.JButton();
        bg = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
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

        jpCarrera.setBackground(new java.awt.Color(212, 173, 107));
        jpCarrera.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nueva Carrera");
        jpCarrera.add(jLabel2);
        jLabel2.setBounds(10, 10, 123, 22);

        cbxFacultad.setBackground(new java.awt.Color(242, 242, 242));
        cbxFacultad.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        cbxFacultad.setForeground(new java.awt.Color(242, 242, 242));
        cbxFacultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadActionPerformed(evt);
            }
        });
        jpCarrera.add(cbxFacultad);
        cbxFacultad.setBounds(190, 60, 250, 29);

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Facultad:");
        jpCarrera.add(jLabel5);
        jLabel5.setBounds(100, 60, 80, 30);

        txtNombreC.setBackground(new java.awt.Color(242, 242, 242));
        txtNombreC.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtNombreC.setBorder(null);
        jpCarrera.add(txtNombreC);
        txtNombreC.setBounds(200, 100, 300, 30);

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jpCarrera.add(jSeparator2);
        jSeparator2.setBounds(200, 130, 300, 22);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Numero de Ciclos:");
        jpCarrera.add(jLabel6);
        jLabel6.setBounds(500, 60, 140, 30);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nombre:");
        jpCarrera.add(jLabel7);
        jLabel7.setBounds(110, 100, 70, 30);

        spnCiclo.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        jpCarrera.add(spnCiclo);
        spnCiclo.setBounds(650, 60, 85, 29);

        btCrearC.setBackground(new java.awt.Color(102, 51, 0));
        btCrearC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        btGCCarrera.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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

        jpFacultad.setBackground(new java.awt.Color(212, 173, 107));
        jpFacultad.setForeground(new java.awt.Color(51, 51, 51));
        jpFacultad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jpFacultad.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nueva Facultad");
        jpFacultad.add(jLabel1);
        jLabel1.setBounds(10, 10, 110, 19);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nombre:");
        jpFacultad.add(jLabel4);
        jLabel4.setBounds(60, 60, 70, 33);

        txtNombreF.setBackground(new java.awt.Color(242, 242, 242));
        txtNombreF.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtNombreF.setBorder(null);
        jpFacultad.add(txtNombreF);
        txtNombreF.setBounds(150, 60, 380, 30);

        jSeparator1.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jpFacultad.add(jSeparator1);
        jSeparator1.setBounds(150, 90, 380, 22);

        btCrearF.setBackground(new java.awt.Color(102, 51, 0));
        btCrearF.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearF.setForeground(new java.awt.Color(255, 255, 255));
        btCrearF.setText("Crear Facultad");
        btCrearF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearFActionPerformed(evt);
            }
        });
        jpFacultad.add(btCrearF);
        btCrearF.setBounds(230, 120, 130, 30);

        btGCFacultad.setBackground(new java.awt.Color(102, 51, 0));
        btGCFacultad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btGCFacultad.setForeground(new java.awt.Color(255, 255, 255));
        btGCFacultad.setText("Guardar Cambios");
        btGCFacultad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCFacultadActionPerformed(evt);
            }
        });
        jpFacultad.add(btGCFacultad);
        btGCFacultad.setBounds(390, 120, 130, 30);

        jpCarrera.add(jpFacultad);
        jpFacultad.setBounds(0, 0, 550, 160);

        jpAll.add(jpCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 180));

        jpAsignatura.setBackground(new java.awt.Color(212, 173, 107));
        jpAsignatura.setLayout(null);

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel25.setText("Nueva Asignatura");
        jpAsignatura.add(jLabel25);
        jLabel25.setBounds(10, 10, 152, 22);

        cbxMalla.setBackground(new java.awt.Color(242, 242, 242));
        cbxMalla.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        cbxMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMallaActionPerformed(evt);
            }
        });
        jpAsignatura.add(cbxMalla);
        cbxMalla.setBounds(190, 90, 220, 29);

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Malla Curricular:");
        jpAsignatura.add(jLabel26);
        jLabel26.setBounds(70, 90, 90, 30);

        txtNombreA.setBackground(new java.awt.Color(242, 242, 242));
        txtNombreA.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtNombreA.setBorder(null);
        jpAsignatura.add(txtNombreA);
        txtNombreA.setBounds(190, 130, 220, 30);

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Total de Horas:");
        jpAsignatura.add(jLabel27);
        jLabel27.setBounds(660, 130, 120, 30);

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Nombre:");
        jpAsignatura.add(jLabel28);
        jLabel28.setBounds(90, 130, 70, 30);

        spnTotalHorasA.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        jpAsignatura.add(spnTotalHorasA);
        spnTotalHorasA.setBounds(800, 130, 77, 29);

        btCrearA.setBackground(new java.awt.Color(102, 51, 0));
        btCrearA.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        btGCAsignatura.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        jLabel29.setBounds(430, 130, 60, 30);

        txtCodigoA.setBackground(new java.awt.Color(242, 242, 242));
        txtCodigoA.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtCodigoA.setBorder(null);
        jpAsignatura.add(txtCodigoA);
        txtCodigoA.setBounds(510, 130, 140, 30);

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Carrera:");
        jpAsignatura.add(jLabel14);
        jLabel14.setBounds(90, 60, 70, 30);

        cbxCarreraMalla.setBackground(new java.awt.Color(242, 242, 242));
        cbxCarreraMalla.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
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
        jpAsignatura.add(cbxCarreraMalla);
        cbxCarreraMalla.setBounds(190, 60, 220, 29);

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Malla Registrada:");
        jpAsignatura.add(jLabel31);
        jLabel31.setBounds(470, 70, 130, 30);

        txtMallaRegistrada.setBackground(new java.awt.Color(242, 242, 242));
        txtMallaRegistrada.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtMallaRegistrada.setBorder(null);
        txtMallaRegistrada.setEnabled(false);
        jpAsignatura.add(txtMallaRegistrada);
        txtMallaRegistrada.setBounds(620, 70, 250, 30);

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jpAsignatura.add(jSeparator5);
        jSeparator5.setBounds(190, 160, 220, 22);

        jSeparator6.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jpAsignatura.add(jSeparator6);
        jSeparator6.setBounds(620, 100, 250, 22);

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jpAsignatura.add(jSeparator7);
        jSeparator7.setBounds(510, 160, 140, 22);

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
        jLabel3.setBounds(10, 10, 110, 22);

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Descripcion:");
        jpMalla.add(jLabel9);
        jLabel9.setBounds(60, 100, 100, 30);

        txtDescripcion.setBackground(new java.awt.Color(242, 242, 242));
        txtDescripcion.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtDescripcion.setBorder(null);
        jpMalla.add(txtDescripcion);
        txtDescripcion.setBounds(180, 100, 240, 30);

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jpMalla.add(jSeparator3);
        jSeparator3.setBounds(180, 130, 240, 22);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Pensum:");
        jpMalla.add(jLabel10);
        jLabel10.setBounds(490, 60, 70, 30);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Carrera:");
        jpMalla.add(jLabel8);
        jLabel8.setBounds(90, 60, 70, 30);

        cbxCarrera.setBackground(new java.awt.Color(242, 242, 242));
        cbxCarrera.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        cbxCarrera.setForeground(new java.awt.Color(242, 242, 242));
        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpMalla.add(cbxCarrera);
        cbxCarrera.setBounds(180, 60, 210, 29);

        txtPensum.setBackground(new java.awt.Color(242, 242, 242));
        txtPensum.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtPensum.setBorder(null);
        jpMalla.add(txtPensum);
        txtPensum.setBounds(590, 60, 160, 30);

        jSeparator4.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jpMalla.add(jSeparator4);
        jSeparator4.setBounds(590, 90, 160, 22);

        btCrearM.setBackground(new java.awt.Color(102, 51, 0));
        btCrearM.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearM.setForeground(new java.awt.Color(255, 255, 255));
        btCrearM.setText("Crear Malla");
        btCrearM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearMActionPerformed(evt);
            }
        });
        jpMalla.add(btCrearM);
        btCrearM.setBounds(340, 160, 140, 30);

        btGCMalla.setBackground(new java.awt.Color(102, 51, 0));
        btGCMalla.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btGCMalla.setForeground(new java.awt.Color(255, 255, 255));
        btGCMalla.setText("Guardar Cambios");
        btGCMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGCMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCMallaActionPerformed(evt);
            }
        });
        jpMalla.add(btGCMalla);
        btGCMalla.setBounds(520, 160, 130, 30);

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Vigencia:");
        jpMalla.add(jLabel16);
        jLabel16.setBounds(490, 100, 70, 30);

        cbxVigencia.setBackground(new java.awt.Color(242, 242, 242));
        cbxVigencia.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        cbxVigencia.setForeground(new java.awt.Color(242, 242, 242));
        cbxVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vigente", "No Vigente" }));
        cbxVigencia.setEnabled(false);
        jpMalla.add(cbxVigencia);
        cbxVigencia.setBounds(590, 100, 97, 29);

        jpAsignatura.add(jpMalla);
        jpMalla.setBounds(10, 20, 760, 210);

        jpAll.add(jpAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 880, 230));

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
        getContentPane().add(btAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 120, 40));

        bg.setBackground(new java.awt.Color(255, 250, 205));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        btModificarMalla.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btModificarMalla.setForeground(new java.awt.Color(255, 255, 255));
        btModificarMalla.setText("Modificar");
        btModificarMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarMallaActionPerformed(evt);
            }
        });

        btLimpiar.setBackground(new java.awt.Color(102, 51, 0));
        btLimpiar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
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
        btModificarAsignatura.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        btModificarFacultad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btModificarFacultad.setForeground(new java.awt.Color(255, 255, 255));
        btModificarFacultad.setText("Modificar");
        btModificarFacultad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btModificarFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarFacultadActionPerformed(evt);
            }
        });

        btModificarCarrera.setBackground(new java.awt.Color(102, 51, 0));
        btModificarCarrera.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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

        javax.swing.GroupLayout jpRegistroLayout = new javax.swing.GroupLayout(jpRegistro);
        jpRegistro.setLayout(jpRegistroLayout);
        jpRegistroLayout.setHorizontalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btModificarFacultad)))
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12))
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(btModificarCarrera)))
                        .addGap(164, 164, 164)
                        .addComponent(btModificarMalla))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(232, 232, 232))
                        .addGroup(jpRegistroLayout.createSequentialGroup()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(btModificarAsignatura)
                        .addGap(143, 143, 143))))
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(btLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpRegistroLayout.setVerticalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addComponent(jScrollPane4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btModificarAsignatura)
                            .addComponent(btModificarMalla)))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModificarCarrera))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModificarFacultad)))
                .addGap(3, 3, 3)
                .addComponent(btLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        bg.add(jpRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 1010, 260));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCrearCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearCActionPerformed
        try {
            guardar(2);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearCActionPerformed

    private void btCrearMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearMActionPerformed
        try {
            guardar(3);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearMActionPerformed

    private void btCrearFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearFActionPerformed

        try {
            guardar(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
            guardar(4);
        } catch (EmptyException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
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

    private void cbxFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadActionPerformed
        System.out.println(cbxFacultad.getSelectedIndex());
    }//GEN-LAST:event_cbxFacultadActionPerformed

    private void btCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarreraActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(true);
        jpMalla.setVisible(false);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btCarreraActionPerformed

    private void btFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFacultadActionPerformed
        jpFacultad.setVisible(true);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btFacultadActionPerformed

    private void btAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignaturaActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(false);
        jpAsignatura.setVisible(true);
    }//GEN-LAST:event_btAsignaturaActionPerformed

    private void btMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMallaActionPerformed
        jpFacultad.setVisible(false);
        jpCarrera.setVisible(false);
        jpMalla.setVisible(true);
        jpAsignatura.setVisible(false);
    }//GEN-LAST:event_btMallaActionPerformed

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
    private javax.swing.JButton btCrearA;
    private javax.swing.JButton btCrearC;
    private javax.swing.JButton btCrearF;
    private javax.swing.JButton btCrearM;
    private javax.swing.JButton btFacultad;
    private javax.swing.JButton btGCAsignatura;
    private javax.swing.JButton btGCCarrera;
    private javax.swing.JButton btGCFacultad;
    private javax.swing.JButton btGCMalla;
    private javax.swing.JButton btLimpiar;
    private javax.swing.JButton btMalla;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jpAll;
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
