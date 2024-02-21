package vista.Academico;

import controlador.Academico.AsignaturaBD;
import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controlador.Academico.AsignacionBD;
import controlador.Academico.CarreraBD;
import controlador.Academico.CicloBD;
import controlador.Academico.FacultadBD;
import controlador.Academico.HorarioTutoriaBD;
import controlador.Academico.MallaBD;
import controlador.Academico.UniversidadBD;
import controlador.Admin.PersonaBD;
import controlador.Login.UsuarioBD;
import modelo.*;
import vista.Matriculas.FrmMatricula;

import vista.listas.tablas.TablaAsignacion;
import vista.listas.util.Utilvista;

public class FrmAsignacion extends javax.swing.JFrame {

    private AsignacionBD fileAsignacion = new AsignacionBD();
    private PersonaBD filePersona = new PersonaBD();
    private UsuarioBD fileUsuario = new UsuarioBD();
    private final UniversidadBD fileUniversidad = new UniversidadBD();
    private final FacultadBD fileFacultad = new FacultadBD();
    private CarreraBD fileCarrera = new CarreraBD();
    private MallaBD fileMalla = new MallaBD();
    private CicloBD fileCiclo = new CicloBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();
    private HorarioTutoriaBD horarioControl = new HorarioTutoriaBD();
    private TablaAsignacion tc = new TablaAsignacion();

    public FrmAsignacion() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
    }

    private Boolean verificar() {
        return (!txtAsignatura.getText().trim().isEmpty()
                && !txtDni.getText().trim().isEmpty());
    }
    
    private void limpiar() {
        try {
            if (!fileUniversidad.getUniversidades().isEmpty()) {
                Utilvista.cargarListaDocentes(lstDocente);
                Utilvista.cargarComboUniversidades(cbxUniversidadC);
            }
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las universidades", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        cargarTablaContratos();

        lblA.setVisible(true);
        txtAsignatura.setVisible(true);
        lblP.setVisible(true);
        txtPeriodo.setVisible(true);
        btCrearContrato.setVisible(true);
        btAsignarHorario.setVisible(false);
        lblC.setVisible(true);
        lblDia.setVisible(false);
        cbxDia.setVisible(false);
        lblHI.setVisible(false);
        spnHI.setVisible(false);
        lblHF.setVisible(false);
        spnHF.setVisible(false);
        jpA.setVisible(true);

        limpiarSoft();
        Utilvista.limpiarLista(lstCarrera);
        Utilvista.limpiarLista(lstMalla);
        Utilvista.limpiarLista(lstAsignatura);
    }

    private void limpiarSoft() {
        txtDni.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
        txtAsignatura.setText("");
    }

    private void guardar() throws Exception {
        if (verificar()) {
            Object u = lstDocente.getSelectedValue();
            Usuario docente = (Usuario) u;
            Object a = lstAsignatura.getSelectedValue();
            Asignatura asignatura = (Asignatura) a;
            fileAsignacion.getAsignacion().setUsuario_ID(docente.getId());
            fileAsignacion.getAsignacion().setAsignatura_CODIGO(asignatura.getCodigo());

            fileAsignacion.persist(fileAsignacion.getAsignacion());
            JOptionPane.showMessageDialog(null, "Datos guardados");
            limpiar();
            fileAsignacion.setAsignacion(null);
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarVista(Integer var) throws EmptyException {
        switch (var) {
            case 1:
                if (lstDocente.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    limpiarSoft();
                    Object d = lstDocente.getSelectedValue();
                    Usuario docente = (Usuario) d;
                    Persona p = filePersona.buscarBinaria(filePersona.all(), "dni", docente.getPersona_DNI());
                    txtDni.setText(p.getDni());
                    txtApellidos.setText(p.getApellido());
                    txtNombres.setText(p.getNombre());
                    txtTelefono.setText(p.getTelefono());
                }
                break;
            case 2:
                if (lstAsignatura.getSelectedValue() != null) {
                    Object a = lstAsignatura.getSelectedValue();
                    Asignatura asignatura = (Asignatura) a;
                    txtAsignatura.setText(asignatura.getNombre());
                }
                break;
            case 3:
                if (tbAsignacion.getSelectedRow() > -1) {
                    limpiarSoft();
                    Asignacion[] asignaciones = fileAsignacion.all().toArray();
                    Asignacion asignacion = asignaciones[tbAsignacion.getSelectedRow()];
                    Usuario ud = fileUsuario.getUsuariosTodos().getInfo(asignacion.getUsuario_ID());
                    Persona d = filePersona.buscarBinaria(filePersona.all(), "dni", ud.getPersona_DNI());

                    txtDni.setText(d.getDni());
                    txtApellidos.setText(d.getApellido());
                    txtNombres.setText(d.getNombre());
                    txtTelefono.setText(d.getTelefono());
                    txtAsignatura.setText(fileAsignatura.buscarBinaria(fileAsignatura.all(), "codigo", asignacion.getAsignatura_CODIGO()).getNombre());
                    asignaciones = null;
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void cargarTablaContratos() {
        tc.setAsignaciones(fileAsignacion.all());
        tbAsignacion.setModel(tc);
        tbAsignacion.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jpFCA = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstCarrera = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstMalla = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstAsignatura = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstCiclo = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        cbxUniversidadC = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cbxFacultadC = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jpDocentes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDocente = new javax.swing.JList<>();
        jpContrato = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btCrearContrato = new javax.swing.JButton();
        lblC = new javax.swing.JLabel();
        jpA = new javax.swing.JScrollPane();
        tbAsignacion = new javax.swing.JTable();
        lblA = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        lblP = new javax.swing.JLabel();
        txtPeriodo = new javax.swing.JTextField();
        btAsignarHorario = new javax.swing.JButton();
        lblDia = new javax.swing.JLabel();
        cbxDia = new javax.swing.JComboBox<>();
        lblHI = new javax.swing.JLabel();
        lblHF = new javax.swing.JLabel();
        spnHF = new javax.swing.JSpinner();
        spnHI = new javax.swing.JSpinner();
        btHT = new javax.swing.JButton();
        btAsignacion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 51, 0));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Academico Asignaciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addComponent(jLabel6)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, -1));

        jpFCA.setBackground(new java.awt.Color(212, 173, 107));

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Universidades");

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Carreras");

        jScrollPane5.setPreferredSize(new java.awt.Dimension(262, 130));

        lstCarrera.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
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
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mallas Curriculares");

        jScrollPane7.setPreferredSize(new java.awt.Dimension(262, 130));

        lstMalla.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMallaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(lstMalla);

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Asignaturas");

        jScrollPane6.setPreferredSize(new java.awt.Dimension(262, 130));

        lstAsignatura.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lstAsignatura);

        jScrollPane9.setPreferredSize(new java.awt.Dimension(262, 130));

        lstCiclo.setFont(new java.awt.Font("Roboto Thin", 1, 11)); // NOI18N
        lstCiclo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCicloMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(lstCiclo);

        jLabel16.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Ciclos");

        cbxUniversidadC.setBackground(new java.awt.Color(237, 209, 163));
        cbxUniversidadC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxUniversidadC.setForeground(new java.awt.Color(242, 242, 242));
        cbxUniversidadC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUniversidadCActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Facultades");

        cbxFacultadC.setBackground(new java.awt.Color(237, 209, 163));
        cbxFacultadC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxFacultadC.setForeground(new java.awt.Color(242, 242, 242));
        cbxFacultadC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFCALayout = new javax.swing.GroupLayout(jpFCA);
        jpFCA.setLayout(jpFCALayout);
        jpFCALayout.setHorizontalGroup(
            jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFCALayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(cbxUniversidadC, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxFacultadC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jpFCALayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpFCALayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpFCALayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpFCALayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel15))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );
        jpFCALayout.setVerticalGroup(
            jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFCALayout.createSequentialGroup()
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(cbxUniversidadC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFCALayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(cbxFacultadC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFCALayout.createSequentialGroup()
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel16)))
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(230, 230, 230))
        );

        getContentPane().add(jpFCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 311, 680, 270));

        jPanel1.setBackground(new java.awt.Color(255, 250, 205));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpDocentes.setBackground(new java.awt.Color(212, 173, 107));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Docentes");

        lstDocente.setFont(new java.awt.Font("Roboto Thin", 0, 13)); // NOI18N
        lstDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDocenteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstDocente);

        javax.swing.GroupLayout jpDocentesLayout = new javax.swing.GroupLayout(jpDocentes);
        jpDocentes.setLayout(jpDocentesLayout);
        jpDocentesLayout.setHorizontalGroup(
            jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDocentesLayout.setVerticalGroup(
            jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jpDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 680, 260));

        jpContrato.setBackground(new java.awt.Color(212, 173, 107));
        jpContrato.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nueva Asignacion:");
        jpContrato.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nombres:");
        jpContrato.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 92, -1, 20));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Apellidos:");
        jpContrato.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 65, -1, 20));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("DNI:");
        jpContrato.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 37, -1, 17));

        txtDni.setEditable(false);
        txtDni.setBackground(new java.awt.Color(237, 209, 163));
        txtDni.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtDni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtDni.setMaximumSize(new java.awt.Dimension(64, 17));
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        jpContrato.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 37, 130, -1));

        txtApellidos.setEditable(false);
        txtApellidos.setBackground(new java.awt.Color(237, 209, 163));
        txtApellidos.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtApellidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jpContrato.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 64, 130, -1));

        txtNombres.setEditable(false);
        txtNombres.setBackground(new java.awt.Color(237, 209, 163));
        txtNombres.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtNombres.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtNombres.setMaximumSize(new java.awt.Dimension(64, 17));
        jpContrato.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 91, 130, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Telefono:");
        jpContrato.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 20));

        txtTelefono.setEditable(false);
        txtTelefono.setBackground(new java.awt.Color(237, 209, 163));
        txtTelefono.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtTelefono.setMaximumSize(new java.awt.Dimension(64, 17));
        jpContrato.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 130, -1));

        btCrearContrato.setBackground(new java.awt.Color(102, 51, 0));
        btCrearContrato.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearContrato.setForeground(new java.awt.Color(255, 255, 255));
        btCrearContrato.setText("Crear Nuevo Contrato");
        btCrearContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearContratoActionPerformed(evt);
            }
        });
        jpContrato.add(btCrearContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, 32));

        lblC.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        lblC.setForeground(new java.awt.Color(0, 0, 0));
        lblC.setText("Contratos:");
        jpContrato.add(lblC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jpA.setBackground(new java.awt.Color(242, 242, 242));

        tbAsignacion.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        tbAsignacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DOCENTE", "ASIGNATURA", "PERIODO ACADEMICO"
            }
        ));
        tbAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsignacionMouseClicked(evt);
            }
        });
        jpA.setViewportView(tbAsignacion);

        jpContrato.add(jpA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 320, 220));

        lblA.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblA.setForeground(new java.awt.Color(0, 0, 0));
        lblA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblA.setText("Asignatura:");
        jpContrato.add(lblA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));

        txtAsignatura.setEditable(false);
        txtAsignatura.setBackground(new java.awt.Color(237, 209, 163));
        txtAsignatura.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtAsignatura.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtAsignatura.setMaximumSize(new java.awt.Dimension(64, 17));
        txtAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsignaturaActionPerformed(evt);
            }
        });
        jpContrato.add(txtAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 130, -1));

        lblP.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblP.setForeground(new java.awt.Color(0, 0, 0));
        lblP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblP.setText("Periodo:");
        jpContrato.add(lblP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 50, 20));

        txtPeriodo.setEditable(false);
        txtPeriodo.setBackground(new java.awt.Color(237, 209, 163));
        txtPeriodo.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        txtPeriodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtPeriodo.setMaximumSize(new java.awt.Dimension(64, 17));
        txtPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeriodoActionPerformed(evt);
            }
        });
        jpContrato.add(txtPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 130, -1));

        btAsignarHorario.setBackground(new java.awt.Color(102, 51, 0));
        btAsignarHorario.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btAsignarHorario.setForeground(new java.awt.Color(255, 255, 255));
        btAsignarHorario.setText("Asignar Horario");
        btAsignarHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAsignarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAsignarHorarioActionPerformed(evt);
            }
        });
        jpContrato.add(btAsignarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 140, 30));

        lblDia.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblDia.setForeground(new java.awt.Color(0, 0, 0));
        lblDia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDia.setText("Dia:");
        jpContrato.add(lblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 40, 20));

        cbxDia.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        cbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" }));
        jpContrato.add(cbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 80, -1));

        lblHI.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblHI.setForeground(new java.awt.Color(0, 0, 0));
        lblHI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHI.setText("Hora Inicio:");
        jpContrato.add(lblHI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 30));

        lblHF.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblHF.setForeground(new java.awt.Color(0, 0, 0));
        lblHF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHF.setText("Hora Fin:");
        jpContrato.add(lblHF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 60, 30));

        spnHF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        spnHF.setModel(new javax.swing.SpinnerListModel(new String[] {"07:00,", "07:00,", "09:00,", "10:00,", "11:00,", "12:00,", "13:00,", "14:00,", "15:00,", "16:00,", "17:00,", "18:00"}));
        jpContrato.add(spnHF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        spnHI.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        spnHI.setModel(new javax.swing.SpinnerListModel(new String[] {"07:00,", "07:00,", "09:00,", "10:00,", "11:00,", "12:00,", "13:00,", "14:00,", "15:00,", "16:00,", "17:00,", "18:00"}));
        jpContrato.add(spnHI, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jPanel1.add(jpContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 340, 510));

        btHT.setBackground(new java.awt.Color(102, 51, 0));
        btHT.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btHT.setForeground(new java.awt.Color(255, 255, 255));
        btHT.setText("Horario de Tutorias");
        btHT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHT.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHTActionPerformed(evt);
            }
        });
        jPanel1.add(btHT, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, -1, 40));

        btAsignacion.setBackground(new java.awt.Color(102, 51, 0));
        btAsignacion.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btAsignacion.setForeground(new java.awt.Color(255, 255, 255));
        btAsignacion.setText("Asignacion");
        btAsignacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAsignacion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAsignacionActionPerformed(evt);
            }
        });
        jPanel1.add(btAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 100, 40));

        jButton1.setBackground(new java.awt.Color(102, 51, 0));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsignaturaActionPerformed

    private void lstCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseClicked
        if (lstCarrera.getSelectedValue() != null) {
            if (!fileMalla.getMallas().isEmpty()) {
                Object c = lstCarrera.getSelectedValue();
                Carrera carrera = (Carrera) c;
                Utilvista.limpiarLista(lstMalla);
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

    private void lstMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMallaMouseClicked
        if (lstMalla.getSelectedValue() != null) {
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

    private void lstCarreraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lstCarreraMouseEntered

    private void lstAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAsignaturaMouseClicked
        if (lstAsignatura.getSelectedValue() != null) {
            txtAsignatura.setText("");
            try {
                cargarVista(2);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna asignatura disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstAsignaturaMouseClicked

    private void btCrearContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearContratoActionPerformed
        try {
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(FrmAsignacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearContratoActionPerformed

    private void lstDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDocenteMouseClicked
        if (lstDocente.getSelectedValue() != null)
        try {
            cargarVista(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cargarVista(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAsignacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstDocenteMouseClicked

    private void tbAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsignacionMouseClicked
        try {
            cargarVista(3);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmAsignacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbAsignacionMouseClicked

    private void lstCicloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCicloMouseClicked
        if (lstCiclo.getSelectedValue() != null) {
            if (!fileAsignatura.getAsignaturas().isEmpty()) {
                Object c = lstCiclo.getSelectedValue();
                Ciclo ciclo = (Ciclo) c;
                try {
                    Utilvista.cargarListaAsignaturas(lstAsignatura, ciclo);
                } catch (EmptyException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar lista de asignatura", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ningun ciclo disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstCicloMouseClicked

    private void txtPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeriodoActionPerformed

    private void btHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHTActionPerformed
        lblA.setVisible(false);
        txtAsignatura.setVisible(false);
        lblP.setVisible(false);
        txtPeriodo.setVisible(false);
        btCrearContrato.setVisible(false);
        btAsignarHorario.setVisible(true);
        lblC.setVisible(false);
        lblDia.setVisible(true);
        cbxDia.setVisible(true);
        lblHI.setVisible(true);
        spnHI.setVisible(true);
        lblHF.setVisible(true);
        spnHF.setVisible(true);
        jpA.setVisible(false);
    }//GEN-LAST:event_btHTActionPerformed

    private void btAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignacionActionPerformed
        lblA.setVisible(true);
        txtAsignatura.setVisible(true);
        lblP.setVisible(true);
        txtPeriodo.setVisible(true);
        btCrearContrato.setVisible(true);
        btAsignarHorario.setVisible(false);
        lblC.setVisible(true);
        lblDia.setVisible(false);
        cbxDia.setVisible(false);
        lblHI.setVisible(false);
        spnHI.setVisible(false);
        lblHF.setVisible(false);
        spnHF.setVisible(false);
        jpA.setVisible(true);
    }//GEN-LAST:event_btAsignacionActionPerformed

    private void btAsignarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignarHorarioActionPerformed
        spnHI.getValue().toString();
        spnHF.getValue().toString();
        lblDia.getText();
    }//GEN-LAST:event_btAsignarHorarioActionPerformed

    private void cbxUniversidadCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUniversidadCActionPerformed
        if (!fileFacultad.getFacultades().isEmpty() && cbxUniversidadC.getSelectedItem() != null) {
            Universidad universidad = Utilvista.obtenerUniversidadControl(cbxUniversidadC);
            try {
                Utilvista.cargarComboFacultades(cbxFacultadC, universidad);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar facultades", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cbxUniversidadCActionPerformed

    private void cbxFacultadCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadCActionPerformed
        if (!fileCarrera.getCarreras().isEmpty() && cbxFacultadC.getSelectedItem() != null) {
            Facultad facultad = Utilvista.obtenerFacultadControl(cbxFacultadC);
            try {
                Utilvista.cargarListaCarreras(lstCarrera, facultad);
            } catch (EmptyException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar lista de carreras", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna facultad disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cbxFacultadCActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmAsignacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAsignacion;
    private javax.swing.JButton btAsignarHorario;
    private javax.swing.JButton btCrearContrato;
    private javax.swing.JButton btHT;
    private javax.swing.JComboBox<String> cbxDia;
    private javax.swing.JComboBox<String> cbxFacultadC;
    private javax.swing.JComboBox<String> cbxUniversidadC;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jpA;
    private javax.swing.JPanel jpContrato;
    private javax.swing.JPanel jpDocentes;
    private javax.swing.JPanel jpFCA;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblHF;
    private javax.swing.JLabel lblHI;
    private javax.swing.JLabel lblP;
    private javax.swing.JList<String> lstAsignatura;
    private javax.swing.JList<String> lstCarrera;
    private javax.swing.JList<String> lstCiclo;
    private javax.swing.JList<String> lstDocente;
    private javax.swing.JList<String> lstMalla;
    private javax.swing.JSpinner spnHF;
    private javax.swing.JSpinner spnHI;
    private javax.swing.JTable tbAsignacion;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPeriodo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
