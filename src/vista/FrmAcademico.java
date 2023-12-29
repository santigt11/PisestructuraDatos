package vista;

import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import controlador.FacultadControl;
import controlador.CarreraControl;
import controlador.MallaControl;
import controlador.TDA.listas.DynamicList;
import controlador.Utiles.Utiles;

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

    private controlador.Academico.FacultadArchivos fControl = new controlador.Academico.FacultadArchivos();
    private controlador.Academico.CarreraArchivos cControl = new controlador.Academico.CarreraArchivos();
    private controlador.Academico.MallaArchivos mControl = new controlador.Academico.MallaArchivos();
    private controlador.Academico.AsignaturaArchivos aControl = new controlador.Academico.AsignaturaArchivos();

    private TablaAsignatura tad = new TablaAsignatura();
    private TablaAsignatura taa = new TablaAsignatura();

    FrmAsignatura nuevaAsignatura = new FrmAsignatura();

    public Boolean verificar(Integer var) {
        switch (var) {
            case 1:
                return (!txtNombreF.getText().trim().isEmpty());
            case 2:
                String flag1 = cbxFacultad.getSelectedItem() != null ? cbxFacultad.getSelectedItem().toString().trim() : "";
                return (!flag1.isEmpty()
                        && !txtNombreC.getText().trim().isEmpty()
                        && !spnCiclo.getValue().toString().trim().isEmpty());
            case 3:
                String flag2 = cbxCarrera.getSelectedItem() != null ? cbxCarrera.getSelectedItem().toString().trim() : "";
                return (!flag2.isEmpty()
                        && !txtDescripcion.getText().trim().isEmpty()
                        && !txtPensum.getText().trim().isEmpty()
                        && !tbAsignaturasAsignadas.toString().trim().isEmpty());
            default:
                throw new AssertionError();
        }
    }

    private void cargarVista(Integer var) {
        switch (var) {
            case 1:
                limpiarSoft();
                try {
                    facultadControl.setFacultad(fControl.getFacultades().getInfo(lstFacultad.getSelectedIndex()));
                    txtNombreF.setText(facultadControl.getFacultad().getNombre());
                    txtNombreF.setEnabled(false);
                    btCrearF.setEnabled(false);
                } catch (Exception ex) {
                    Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2: {
                try {
                    limpiarMalla();
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
                }
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

            case 3: {
                try {
                    limpiarMalla();
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Object m = lstMalla.getSelectedValue();
            MallaCurricular malla = (MallaCurricular) m;
            try {
                mallaControl.setMalla(malla);
                cbxCarrera.setSelectedIndex(mallaControl.getMalla().getIdCarrera());
                txtDescripcion.setText(mallaControl.getMalla().getDescripcion());
                txtPensum.setText(mallaControl.getMalla().getPensum());
                cargarTablaAsignaturaA(malla.getAsignaturaList());

                btCrearM.setEnabled(false);
                cbxCarrera.setEnabled(false);
                txtDescripcion.setEnabled(false);
                txtPensum.setEnabled(false);
                tbAsignaturasDisponibles.setEnabled(false);
                tbAsignaturasAsignadas.setEnabled(false);
                btAgregarAsg.setEnabled(false);
                btRemoverAsg.setEnabled(false);
                btDescartar.setEnabled(false);
            } catch (Exception ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            default:
                throw new AssertionError();
        }
    }

    private void cargarTablaAsignaturaD() {
        tad.setAsignaturas(aControl.all());
        tbAsignaturasDisponibles.setModel(tad);
        tbAsignaturasDisponibles.updateUI();
    }

    private void cargarTablaAsignaturaA(DynamicList<Asignatura> datos) throws EmptyException {
        //Agregar asignaturas a la tabla una por una
        for (Integer i = 0; i < datos.getLength(); i++) {
            taa.getAsignaturas().add(datos.getInfo(i));
        }
        tbAsignaturasAsignadas.setModel(taa);
        tbAsignaturasAsignadas.updateUI();
    }

    private void actualizarTablaAsignaturaA() {
        taa.setAsignaturas(mControl.getMalla().getAsignaturaList());
        tbAsignaturasAsignadas.setModel(taa);
        tbAsignaturasAsignadas.updateUI();
    }

    private void limpiar() throws EmptyException {
        try {
            Utilvista.cargarComboFacultades(cbxFacultad);
            Utilvista.cargarComboCarreras(cbxCarrera);
            Utilvista.cargarListaFacultades(lstFacultad);
            limpiarSoft();
            limpiarMalla();
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        tbAsignaturasAsignadas.clearSelection();
        tbAsignaturasDisponibles.clearSelection();

        txtNombreF.setText("");

        btGCFacultad.setEnabled(false);
        btGCCarrera.setEnabled(false);
        btGCMalla.setEnabled(false);
        btModificarFacultad.setEnabled(false);
        btModificarCarrera.setEnabled(false);
        btModificarMalla.setEnabled(false);

        habilitarTodo();

        facultadControl.setFacultad(null);
        carreraControl.setCarrera(null);
        mallaControl.setMalla(null);

        cargarTablaAsignaturaD();
    }

    private void limpiarSoft() {
        Utilvista.limpiarLista(lstCarrera);
        Utilvista.limpiarLista(lstMalla);

        cbxFacultad.setSelectedIndex(-1);
        txtNombreC.setText("");
        spnCiclo.setValue(0);
        try {
            limpiarMalla();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiarMalla() throws EmptyException {
        cbxCarrera.setSelectedIndex(-1);
        txtDescripcion.setText("");
        txtPensum.setText("");
        actualizarTablaAsignaturaA();

        while (mControl.getMalla().getAsignaturaList().getLength() > 0) {
            mControl.getMalla().getAsignaturaList().extract(0);
        }

        cbxCarrera.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPensum.setEnabled(true);
        tbAsignaturasDisponibles.setEnabled(true);
        tbAsignaturasAsignadas.setEnabled(true);
        btDescartar.setEnabled(true);
        btCrearM.setEnabled(true);
        btAgregarAsg.setEnabled(true);
        btRemoverAsg.setEnabled(true);
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
        tbAsignaturasDisponibles.setEnabled(true);
        tbAsignaturasAsignadas.setEnabled(true);
        btDescartar.setEnabled(true);
    }

    private void guardar(Integer flag) throws EmptyException {
        switch (flag) {
            case 1:
                if (verificar(1)) {
                    facultadControl.getFacultad().setNombre(txtNombreF.getText());
                    if (facultadControl.guardar()) {
                        fControl.setFacultad(facultadControl.getFacultad());
                        fControl.persist();
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
                        cControl.setCarrera(carreraControl.getCarrera());
                        cControl.persist();
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
                    mallaControl.getMalla().setAsignaturaList(mControl.getMalla().getAsignaturaList());
                    if (carreraControl.guardar()) {
                        mControl.setMalla(mallaControl.getMalla());
                        mControl.persist();
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
            default:
                throw new AssertionError();
        }

    }

    private void agregarAsignatura() throws EmptyException {
        int fila = tbAsignaturasDisponibles.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoja una asignatura de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (Utiles.buscarAsignatura(mControl.getMalla().getAsignaturaList(), aControl.getAsignaturas().getInfo(fila))) {
                JOptionPane.showMessageDialog(null, "La asignatura ya esta en la lista", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mControl.getMalla().getAsignaturaList().add(aControl.getAsignaturas().getInfo(fila));
                actualizarTablaAsignaturaA();
            }
        }
    }

    private void removerAsignatura() throws EmptyException {
        int fila = tbAsignaturasAsignadas.getSelectedRow();
        if (fila < 0 || mControl.getMalla().getAsignaturaList().getLength() == 0) {
            JOptionPane.showMessageDialog(null, "No hay ninguna asignatura en la malla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            mControl.getMalla().getAsignaturaList().extract(fila);
            actualizarTablaAsignaturaA();
        }
    }

    private void modificar(Integer flag) throws EmptyException {
        switch (flag) {
            case 1:
                facultadControl.getFacultad().setNombre(txtNombreF.getText());
                if (fControl.merge(facultadControl.getFacultad(), facultadControl.getFacultad().getId())) {
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
                if (cControl.merge(carreraControl.getCarrera(), carreraControl.getCarrera().getId())) {
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
                mallaControl.getMalla().setAsignaturaList(mControl.getMalla().getAsignaturaList());
                if (mControl.merge(mallaControl.getMalla(), mallaControl.getMalla().getId())) {
                    JOptionPane.showMessageDialog(null, "Cambios guardados");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar los cambios, hubo un error");
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
        spAsignaturasAsignadas = new javax.swing.JScrollPane();
        tbAsignaturasAsignadas = new javax.swing.JTable();
        btCrearM = new javax.swing.JButton();
        spAsignaturas = new javax.swing.JScrollPane();
        tbAsignaturasDisponibles = new javax.swing.JTable();
        btNuevaAsignatura = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btAgregarAsg = new javax.swing.JButton();
        btRemoverAsg = new javax.swing.JButton();
        btDescartar = new javax.swing.JButton();
        btGCMalla = new javax.swing.JButton();
        jpRegistro = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstFacultad = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstCarrera = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstMalla = new javax.swing.JList<>();
        btModificarFacultad = new javax.swing.JButton();
        btModificarCarrera = new javax.swing.JButton();
        btModificarMalla = new javax.swing.JButton();
        btLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Academico General");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nueva Facultad");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Nombre:");

        btCrearF.setText("Crear Facultad");
        btCrearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearFActionPerformed(evt);
            }
        });

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
                .addGroup(jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFacultadLayout.createSequentialGroup()
                        .addGroup(jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpFacultadLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addGroup(jpFacultadLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreF, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpFacultadLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btCrearF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGCFacultad)))
                .addContainerGap())
        );
        jpFacultadLayout.setVerticalGroup(
            jpFacultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFacultadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nueva Carrera");

        cbxFacultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Facultad:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Numero de Ciclos:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nombre:");

        btCrearC.setText("Crear Carrera");
        btCrearC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearCActionPerformed(evt);
            }
        });

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
                        .addContainerGap()
                        .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpCarreraLayout.createSequentialGroup()
                                .addComponent(btCrearC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGCCarrera))
                            .addComponent(spnCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpCarreraLayout.setVerticalGroup(
            jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCarreraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spnCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jpCarreraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCrearC)
                    .addComponent(btGCCarrera))
                .addGap(11, 11, 11))
        );

        jpMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpMallaMouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nueva Malla");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Descripcion:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Pensum:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Carrera:");

        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbAsignaturasAsignadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "TOTAL DE HORAS"
            }
        ));
        spAsignaturasAsignadas.setViewportView(tbAsignaturasAsignadas);

        btCrearM.setText("Crear Malla");
        btCrearM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearMActionPerformed(evt);
            }
        });

        tbAsignaturasDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "TOTAL DE HORAS"
            }
        ));
        spAsignaturas.setViewportView(tbAsignaturasDisponibles);

        btNuevaAsignatura.setText("Nueva Asignatura");
        btNuevaAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaAsignaturaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Asignaturas Disponibles");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Asignaturas de la Malla");

        btAgregarAsg.setText("Agregar Asigantura");
        btAgregarAsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarAsgActionPerformed(evt);
            }
        });

        btRemoverAsg.setText("Remover Asigantura");
        btRemoverAsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverAsgActionPerformed(evt);
            }
        });

        btDescartar.setText("Descartar");
        btDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDescartarActionPerformed(evt);
            }
        });

        btGCMalla.setText("Guardar Cambios");
        btGCMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGCMallaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMallaLayout = new javax.swing.GroupLayout(jpMalla);
        jpMalla.setLayout(jpMallaLayout);
        jpMallaLayout.setHorizontalGroup(
            jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMallaLayout.createSequentialGroup()
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMallaLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(spAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btAgregarAsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btNuevaAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btRemoverAsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(spAsignaturasAsignadas, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                    .addGroup(jpMallaLayout.createSequentialGroup()
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMallaLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel3))
                            .addGroup(jpMallaLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                    .addComponent(txtPensum)
                                    .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btCrearM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btDescartar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGCMalla)))
                .addContainerGap())
            .addGroup(jpMallaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(83, 83, 83))
        );
        jpMallaLayout.setVerticalGroup(
            jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMallaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(btCrearM))
                        .addGap(18, 18, 18)
                        .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtPensum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btDescartar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMallaLayout.createSequentialGroup()
                        .addComponent(btGCMalla)
                        .addGap(36, 36, 36)))
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMallaLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btAgregarAsg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btRemoverAsg)
                        .addGap(21, 21, 21)
                        .addComponent(btNuevaAsignatura))
                    .addComponent(spAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spAsignaturasAsignadas, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Facultades:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Carreras:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Mallas Curriculares:");

        lstFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstFacultadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstFacultad);

        lstCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCarreraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstCarrera);

        lstMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMallaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstMalla);

        btModificarFacultad.setText("Modificar");
        btModificarFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarFacultadActionPerformed(evt);
            }
        });

        btModificarCarrera.setText("Modificar");
        btModificarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarCarreraActionPerformed(evt);
            }
        });

        btModificarMalla.setText("Modificar");
        btModificarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarMallaActionPerformed(evt);
            }
        });

        btLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btLimpiar.setText("Limpiar Todo");
        btLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpRegistroLayout = new javax.swing.GroupLayout(jpRegistro);
        jpRegistro.setLayout(jpRegistroLayout);
        jpRegistroLayout.setHorizontalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addGroup(jpRegistroLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpRegistroLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(btModificarFacultad)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(btModificarCarrera)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(btModificarMalla)
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroLayout.createSequentialGroup()
                        .addComponent(btLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );
        jpRegistroLayout.setVerticalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btModificarFacultad)
                .addGap(34, 34, 34)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btModificarCarrera)
                .addGap(34, 34, 34)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btModificarMalla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btLimpiar)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpFacultad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpCarrera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(437, 437, 437))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
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

    private void btNuevaAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaAsignaturaActionPerformed
        nuevaAsignatura.setVisible(true);
    }//GEN-LAST:event_btNuevaAsignaturaActionPerformed

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
        cargarTablaAsignaturaD();
    }//GEN-LAST:event_jpMallaMouseEntered

    private void btAgregarAsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarAsgActionPerformed
        try {
            agregarAsignatura();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAgregarAsgActionPerformed

    private void btRemoverAsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverAsgActionPerformed
        try {
            removerAsignatura();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRemoverAsgActionPerformed

    private void btDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDescartarActionPerformed
        try {
            limpiarMalla();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDescartarActionPerformed

    private void lstFacultadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstFacultadMouseClicked
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
    }//GEN-LAST:event_lstFacultadMouseClicked

    private void lstCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseClicked
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
    }//GEN-LAST:event_lstCarreraMouseClicked

    private void lstMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMallaMouseClicked
        cargarVista(3);
        btModificarMalla.setEnabled(true);
    }//GEN-LAST:event_lstMallaMouseClicked

    private void btModificarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarMallaActionPerformed
        cargarVista(3);
        cbxCarrera.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPensum.setEnabled(true);
        tbAsignaturasDisponibles.setEnabled(true);
        tbAsignaturasAsignadas.setEnabled(true);
        btGCMalla.setEnabled(true);
        btAgregarAsg.setEnabled(true);
        btRemoverAsg.setEnabled(true);
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
    private javax.swing.JButton btAgregarAsg;
    private javax.swing.JButton btCrearC;
    private javax.swing.JButton btCrearF;
    private javax.swing.JButton btCrearM;
    private javax.swing.JButton btDescartar;
    private javax.swing.JButton btGCCarrera;
    private javax.swing.JButton btGCFacultad;
    private javax.swing.JButton btGCMalla;
    private javax.swing.JButton btLimpiar;
    private javax.swing.JButton btModificarCarrera;
    private javax.swing.JButton btModificarFacultad;
    private javax.swing.JButton btModificarMalla;
    private javax.swing.JButton btNuevaAsignatura;
    private javax.swing.JButton btRemoverAsg;
    private javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JComboBox<String> cbxFacultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpCarrera;
    private javax.swing.JPanel jpFacultad;
    private javax.swing.JPanel jpMalla;
    private javax.swing.JPanel jpRegistro;
    private javax.swing.JList<String> lstCarrera;
    private javax.swing.JList<String> lstFacultad;
    private javax.swing.JList<String> lstMalla;
    private javax.swing.JScrollPane spAsignaturas;
    private javax.swing.JScrollPane spAsignaturasAsignadas;
    private javax.swing.JSpinner spnCiclo;
    private javax.swing.JTable tbAsignaturasAsignadas;
    private javax.swing.JTable tbAsignaturasDisponibles;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombreF;
    private javax.swing.JTextField txtPensum;
    // End of variables declaration//GEN-END:variables
}
