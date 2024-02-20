package vista.Matriculas;

import vista.Academico.FrmAcademico;
import controlador.Academico.AsignaturaBD;
import controlador.Academico.CarreraBD;
import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controlador.Academico.FacultadBD;
import controlador.Academico.MallaBD;
import controlador.Admin.PersonaBD;
import controlador.Matriculas.MatriculaBD;
import controlador.Matriculas.CursaTutoriaBD;
import java.time.ZoneId;
import modelo.*;

import vista.listas.tablas.TablaMatricula;
import vista.listas.util.Utilvista;

public class FrmMatricula extends javax.swing.JFrame {

    private MatriculaBD fileMatricula = new MatriculaBD();
    private CursaTutoriaBD fileMatriculaAsg = new CursaTutoriaBD();

    private PersonaBD filePersona = new PersonaBD();

    private FacultadBD fileFacultad = new FacultadBD();
    private CarreraBD fileCarrera = new CarreraBD();
    private MallaBD fileMalla = new MallaBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();

    private TablaMatricula tm = new TablaMatricula();

    private Boolean verificar(Integer var) {
        switch (var) {
            case 1:
                return (!txtDni.getText().trim().isEmpty()
                        && !dtRegistro.getDate().toString().isEmpty());
            case 2:
                return (!txtDni.getText().trim().isEmpty()
                        && !txtCurso.getText().trim().isEmpty());
            default:
                throw new AssertionError();
        }
    }

    private void guardar(Integer var) throws Exception {
        switch (var) {
            case 1:
                if (verificar(1)) {
                    Object p = lstEstudiante.getSelectedValue();
                    Persona estudiante = (Persona) p;
                    fileMatricula.getMatricula().setPersona_DNI(estudiante.getDni());
                    fileMatricula.getMatricula().setFecha(dtRegistro.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    fileMatricula.getMatricula().setExpedienteActivo(true);

                    fileMatricula.persist(fileMatricula.getMatricula());
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiar();
                    fileMatricula.setMatricula(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                if (verificar(2)) {
                    Object a = lstAsignatura.getSelectedValue();
                    Asignatura asignatura = (Asignatura) a;
                    fileMatriculaAsg.getCursaTutoria().setMatricula_ID(cbxMatricula.getSelectedIndex());
                    fileMatriculaAsg.getCursaTutoria().setAsignatura_Codigo(asignatura.getCodigo());
                    fileMatriculaAsg.getCursaTutoria().setCurso(txtCurso.getText());

                    fileMatriculaAsg.persist(fileMatriculaAsg.getCursaTutoria());
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    limpiar();
                    fileMatriculaAsg.setCursaTutoria(null);
                }
                break;

            default:
                throw new AssertionError();
        }
    }

    private void cargarVista(Integer var) throws EmptyException {
        switch (var) {
            case 1:
                if (lstEstudiante.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    limpiarPersona();
                    Object p = lstEstudiante.getSelectedValue();
                    Persona docente = (Persona) p;
                    txtDni.setText(docente.getDni());
                    txtApellidos.setText(docente.getApellido());
                    txtNombres.setText(docente.getNombre());
                    txtTelefono.setText(docente.getTelefono());
                }
                break;
            case 2:
                if (lstAsignatura.getSelectedValue() != null) {
                    Object f = lstFacultad.getSelectedValue();
                    Facultad facultad = (Facultad) f;
                    Object c = lstCarrera.getSelectedValue();
                    Carrera carrera = (Carrera) c;
                    Object m = lstMalla.getSelectedValue();
                    MallaCurricular malla = (MallaCurricular) m;
                    Object a = lstAsignatura.getSelectedValue();
                    Asignatura asignatura = (Asignatura) a;

                    txtFacultad.setText(facultad.getNombre());
                    txtCarreras.setText(carrera.getNombre());
                    txtMalla.setText(malla.getDescripcion());
                    txtAsignatura.setText(asignatura.getNombre());
                }
                break;
            case 3:
                if (tbMatricula.getSelectedRow() > -1) {
                    limpiarSoft();
                    if (fileMatricula.getMatricula().getExpedienteActivo()) {
                        cbxExpediente.setSelectedIndex(0);
                    } else if (fileMatricula.getMatricula().getExpedienteActivo() == false) {
                        cbxExpediente.setSelectedIndex(1);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void cargarTablaContratos() {
        tm.setMatriculas(fileMatricula.all());
        tbMatricula.setModel(tm);
        tbMatricula.updateUI();
    }

    private void limpiar() {
        try {
            Utilvista.cargarListaFacultades(lstFacultad);
            Utilvista.cargarListaEstudiantes(lstEstudiante);
            Utilvista.cargarComboMatriculas(cbxMatricula);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaContratos();

        limpiarSoft();

        Utilvista.limpiarLista(lstCarrera);
        Utilvista.limpiarLista(lstMalla);
        Utilvista.limpiarLista(lstAsignatura);
    }

    private void limpiarSoft() {
        txtFacultad.setText("");
        txtCarreras.setText("");
        txtMalla.setText("");
        txtAsignatura.setText("");
        txtCurso.setText("");
        cbxExpediente.setSelectedIndex(0);
        cbxMatricula.setSelectedIndex(-1);
        dtRegistro.setDate(null);
    }

    private void limpiarPersona() {
        txtDni.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
    }

//    private void modificar() {
//        if (verificar(1)) {
//            Object p = lstEstudiante.getSelectedValue();
//            Persona estudiante = (Persona) p;
//            Object a = lstAsignatura.getSelectedValue();
//            Asignatura asignatura = (Asignatura) a;
//            matriculaControl.getMatricula().setIdPersona(estudiante.getId());
//            matriculaControl.getMatricula().setFecha(dtRegistro.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            matriculaControl.getMatricula().setIdPAcademico(cbxPeriodo.getSelectedIndex());
//            matriculaControl.getMatricula().setExpActivo(true);
//            if (fileMatricula.merge(matriculaControl.getMatricula(), matriculaControl.getMatricula().getId())) {
//                fileMatricula.setMatricula(matriculaControl.getMatricula());
//                fileMatricula.persist();
//                matriculaAsgControl.getAsgMatricula().setIdMatricula(matriculaControl.getMatricula().getId());
//                matriculaAsgControl.getAsgMatricula().setIdAsignatura(asignatura.getId());
//                matriculaAsgControl.getAsgMatricula().setCurso(txtCurso.getText());
//                if (fileMatriculaAsg.merge(matriculaAsgControl.getAsgMatricula(), matriculaAsgControl.getAsgMatricula().getId())) {
//                    fileMatriculaAsg.setAsgMatricula(matriculaAsgControl.getAsgMatricula());
//                    fileMatriculaAsg.persist();
//                    JOptionPane.showMessageDialog(null, "Cambios guardados");
//                    limpiar();
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "No se pudo guardar los cambios, hubo un error");
//            }
//        }
//    }
    /**
     * Creates new form FrmContrato
     */
    public FrmMatricula() {
        initComponents();
        this.setLocationRelativeTo(null);
        jpMatriculaAsg.setVisible(false);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jpDocentes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstEstudiante = new javax.swing.JList<>();
        jpFCA = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstFacultad = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstCarrera = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstMalla = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstAsignatura = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jpMatricula = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dtRegistro = new com.toedter.calendar.JDateChooser();
        btCrearMatricula = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cbxExpediente = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txtPeriodo = new javax.swing.JTextField();
        jpMatriculaAsg = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtFacultad = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCarreras = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtMalla = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        txtCurso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btAgregarAsignaturaMatricula = new javax.swing.JButton();
        cbxMatricula = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMatricula = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstMatriculaAsg = new javax.swing.JList<>();
        jLabel22 = new javax.swing.JLabel();
        btMatricula = new javax.swing.JButton();
        btMatriculaAsg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 250, 205));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 51, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Matriculas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(479, 479, 479)
                .addComponent(jLabel6)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 30));

        jpDocentes.setBackground(new java.awt.Color(212, 173, 107));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel4.setText("Estudiantes");

        lstEstudiante.setFont(new java.awt.Font("Roboto Thin", 0, 13)); // NOI18N
        lstEstudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstEstudianteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstEstudiante);

        javax.swing.GroupLayout jpDocentesLayout = new javax.swing.GroupLayout(jpDocentes);
        jpDocentes.setLayout(jpDocentesLayout);
        jpDocentesLayout.setHorizontalGroup(
            jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDocentesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
                    .addGroup(jpDocentesLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpDocentesLayout.setVerticalGroup(
            jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDocentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel1.add(jpDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 41, 560, 290));

        jpFCA.setBackground(new java.awt.Color(212, 173, 107));

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel18.setText("Facultades");

        jScrollPane8.setPreferredSize(new java.awt.Dimension(262, 130));

        lstFacultad.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstFacultadMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(lstFacultad);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel2.setText("Carreras");

        jScrollPane5.setPreferredSize(new java.awt.Dimension(262, 130));

        lstCarrera.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCarreraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lstCarreraMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(lstCarrera);

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel3.setText("Mallas Curriculares");

        jScrollPane7.setPreferredSize(new java.awt.Dimension(262, 130));

        lstMalla.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMallaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(lstMalla);

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel15.setText("Asignaturas");

        jScrollPane6.setPreferredSize(new java.awt.Dimension(262, 130));

        lstAsignatura.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lstAsignatura);

        javax.swing.GroupLayout jpFCALayout = new javax.swing.GroupLayout(jpFCA);
        jpFCA.setLayout(jpFCALayout);
        jpFCALayout.setHorizontalGroup(
            jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFCALayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jpFCALayout.setVerticalGroup(
            jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFCALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18))
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel1.add(jpFCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 336, 560, 240));

        jPanel4.setBackground(new java.awt.Color(212, 173, 107));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpMatricula.setBackground(new java.awt.Color(212, 173, 107));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel5.setText("Nueva Matricula:");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nombres:");
        jLabel7.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Apellidos:");
        jLabel8.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("DNI:");
        jLabel9.setToolTipText("");

        txtDni.setEditable(false);
        txtDni.setBackground(new java.awt.Color(237, 209, 163));
        txtDni.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtDni.setMinimumSize(new java.awt.Dimension(68, 30));
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });

        txtApellidos.setEditable(false);
        txtApellidos.setBackground(new java.awt.Color(237, 209, 163));
        txtApellidos.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtApellidos.setMinimumSize(new java.awt.Dimension(68, 30));
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        txtNombres.setEditable(false);
        txtNombres.setBackground(new java.awt.Color(237, 209, 163));
        txtNombres.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Telefono:");
        jLabel10.setToolTipText("");

        txtTelefono.setEditable(false);
        txtTelefono.setBackground(new java.awt.Color(237, 209, 163));
        txtTelefono.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Fecha de Registro:");
        jLabel12.setToolTipText("");

        dtRegistro.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        dtRegistro.setPreferredSize(new java.awt.Dimension(93, 24));

        btCrearMatricula.setBackground(new java.awt.Color(102, 51, 0));
        btCrearMatricula.setForeground(new java.awt.Color(255, 255, 255));
        btCrearMatricula.setText("Crear Nuevo Matricula");
        btCrearMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearMatriculaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Expediente:");
        jLabel20.setToolTipText("");

        cbxExpediente.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        cbxExpediente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cbxExpediente.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Periodo Academico:");
        jLabel21.setToolTipText("");

        txtPeriodo.setEditable(false);
        txtPeriodo.setBackground(new java.awt.Color(237, 209, 163));
        txtPeriodo.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N

        javax.swing.GroupLayout jpMatriculaLayout = new javax.swing.GroupLayout(jpMatricula);
        jpMatricula.setLayout(jpMatriculaLayout);
        jpMatriculaLayout.setHorizontalGroup(
            jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMatriculaLayout.createSequentialGroup()
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMatriculaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5))
                    .addGroup(jpMatriculaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpMatriculaLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel10)
                                .addGap(20, 20, 20)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpMatriculaLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(19, 19, 19)
                                .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpMatriculaLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(dtRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpMatriculaLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpMatriculaLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jpMatriculaLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btCrearMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(60, 60, 60))
        );
        jpMatriculaLayout.setVerticalGroup(
            jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMatriculaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(7, 7, 7)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMatriculaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jpMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCrearMatricula)
                .addContainerGap())
        );

        jPanel4.add(jpMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 370, 290));

        jpMatriculaAsg.setBackground(new java.awt.Color(212, 173, 107));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel16.setText("Facultad:");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtFacultad.setEditable(false);
        txtFacultad.setBackground(new java.awt.Color(237, 209, 163));
        txtFacultad.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtFacultad.setBorder(null);
        txtFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFacultadActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel17.setText("Carrera:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtCarreras.setEditable(false);
        txtCarreras.setBackground(new java.awt.Color(237, 209, 163));
        txtCarreras.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtCarreras.setBorder(null);
        txtCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarrerasActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel19.setText("Malla Curricular:");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtMalla.setEditable(false);
        txtMalla.setBackground(new java.awt.Color(237, 209, 163));
        txtMalla.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtMalla.setBorder(null);
        txtMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMallaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel14.setText("Asignatura:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtAsignatura.setEditable(false);
        txtAsignatura.setBackground(new java.awt.Color(237, 209, 163));
        txtAsignatura.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtAsignatura.setBorder(null);
        txtAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsignaturaActionPerformed(evt);
            }
        });

        txtCurso.setBackground(new java.awt.Color(237, 209, 163));
        txtCurso.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        txtCurso.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel13.setText("Paralelo:");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel23.setText("Nueva Matricula - Asignatura:");

        btAgregarAsignaturaMatricula.setBackground(new java.awt.Color(102, 51, 0));
        btAgregarAsignaturaMatricula.setForeground(new java.awt.Color(255, 255, 255));
        btAgregarAsignaturaMatricula.setText("Agregar Asignatura a Matricula");
        btAgregarAsignaturaMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAgregarAsignaturaMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarAsignaturaMatriculaActionPerformed(evt);
            }
        });

        cbxMatricula.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel24.setText("Matricula:");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel24.setPreferredSize(new java.awt.Dimension(53, 14));

        javax.swing.GroupLayout jpMatriculaAsgLayout = new javax.swing.GroupLayout(jpMatriculaAsg);
        jpMatriculaAsg.setLayout(jpMatriculaAsgLayout);
        jpMatriculaAsgLayout.setHorizontalGroup(
            jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(cbxMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel16)
                        .addGap(22, 22, 22)
                        .addComponent(txtFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel17)
                        .addGap(6, 6, 6)
                        .addComponent(txtCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel19)
                        .addGap(13, 13, 13)
                        .addComponent(txtMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel14)
                        .addGap(9, 9, 9)
                        .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btAgregarAsignaturaMatricula)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jpMatriculaAsgLayout.setVerticalGroup(
            jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMatriculaAsgLayout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(23, 23, 23)
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jpMatriculaAsgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(btAgregarAsignaturaMatricula)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.add(jpMatriculaAsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 430, 290));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 460, 310));

        jPanel2.setBackground(new java.awt.Color(212, 173, 107));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane4.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N

        tbMatricula.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        tbMatricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTUDIANTE", "CARRERA", "ASIGNATURA", "FECHA DE REGISTRO"
            }
        ));
        tbMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMatriculaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbMatricula);

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel11.setText("Matriculas:");

        jScrollPane9.setPreferredSize(new java.awt.Dimension(262, 130));

        lstMatriculaAsg.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstMatriculaAsg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMatriculaAsgMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(lstMatriculaAsg);

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel22.setText("Matriculas por Asignatura:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 384, 460, 190));

        btMatricula.setBackground(new java.awt.Color(102, 51, 0));
        btMatricula.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btMatricula.setForeground(new java.awt.Color(255, 255, 255));
        btMatricula.setText("Matricula");
        btMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMatricula.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMatriculaActionPerformed(evt);
            }
        });
        jPanel1.add(btMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 120, 40));

        btMatriculaAsg.setBackground(new java.awt.Color(102, 51, 0));
        btMatriculaAsg.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btMatriculaAsg.setForeground(new java.awt.Color(255, 255, 255));
        btMatriculaAsg.setText("Matricula - Asignatura");
        btMatriculaAsg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMatriculaAsg.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btMatriculaAsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMatriculaAsgActionPerformed(evt);
            }
        });
        jPanel1.add(btMatriculaAsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseClicked
        if (lstCarrera.getSelectedValue() != null) {
            limpiarSoft();
            Utilvista.limpiarLista(lstMalla);
            Utilvista.limpiarLista(lstAsignatura);
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
            limpiarSoft();
            Utilvista.limpiarLista(lstAsignatura);
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

    private void lstFacultadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstFacultadMouseClicked
        if (lstFacultad.getSelectedValue() != null) {
            limpiarSoft();
            Utilvista.limpiarLista(lstCarrera);
            Utilvista.limpiarLista(lstMalla);
            Utilvista.limpiarLista(lstAsignatura);
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

    private void lstCarreraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lstCarreraMouseEntered

    private void lstAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAsignaturaMouseClicked
        if (lstAsignatura.getSelectedValue() != null) {
            txtAsignatura.setText("");
            try {
                cargarVista(2);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna asignatura disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstAsignaturaMouseClicked

    private void lstEstudianteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstEstudianteMouseClicked
        try {
            cargarVista(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstEstudianteMouseClicked

    private void btMatriculaAsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMatriculaAsgActionPerformed
        jpMatricula.setVisible(false);
        jpMatriculaAsg.setVisible(true);
    }//GEN-LAST:event_btMatriculaAsgActionPerformed

    private void btMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMatriculaActionPerformed
        jpMatricula.setVisible(true);
        jpMatriculaAsg.setVisible(false);
    }//GEN-LAST:event_btMatriculaActionPerformed

    private void lstMatriculaAsgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMatriculaAsgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lstMatriculaAsgMouseClicked

    private void tbMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMatriculaMouseClicked
        try {
            cargarVista(3);
            Utilvista.cargarListaMatriculasAsignaturas(lstMatriculaAsg, fileMatricula, fileMatriculaAsg);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbMatriculaMouseClicked

    private void btAgregarAsignaturaMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarAsignaturaMatriculaActionPerformed
        try {
            guardar(2);
        } catch (Exception ex) {
            Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAgregarAsignaturaMatriculaActionPerformed

    private void txtAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsignaturaActionPerformed

    private void txtMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMallaActionPerformed

    private void txtCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarrerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarrerasActionPerformed

    private void txtFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFacultadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacultadActionPerformed

    private void btCrearMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearMatriculaActionPerformed
        try {
            guardar(1);
        } catch (Exception ex) {
            Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearMatriculaActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMatricula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregarAsignaturaMatricula;
    private javax.swing.JButton btCrearMatricula;
    private javax.swing.JButton btMatricula;
    private javax.swing.JButton btMatriculaAsg;
    private javax.swing.JComboBox<String> cbxExpediente;
    private javax.swing.JComboBox<String> cbxMatricula;
    private com.toedter.calendar.JDateChooser dtRegistro;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel jpDocentes;
    private javax.swing.JPanel jpFCA;
    private javax.swing.JPanel jpMatricula;
    private javax.swing.JPanel jpMatriculaAsg;
    private javax.swing.JList<String> lstAsignatura;
    private javax.swing.JList<String> lstCarrera;
    private javax.swing.JList<String> lstEstudiante;
    private javax.swing.JList<String> lstFacultad;
    private javax.swing.JList<String> lstMalla;
    private javax.swing.JList<String> lstMatriculaAsg;
    private javax.swing.JTable tbMatricula;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtCarreras;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFacultad;
    private javax.swing.JTextField txtMalla;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPeriodo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
