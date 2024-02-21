package vista.Tutorias;

import controlador.Academico.AsignaturaBD;
import controlador.Academico.AsignacionBD;
import controlador.Admin.PersonaBD;
import controlador.Login.UsuarioBD;
import controlador.Matriculas.CursaTutoriaBD;
import controlador.Matriculas.CursaBD;
import controlador.Matriculas.MatriculaBD;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Tutorias.TutoriaBD;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Asignacion;
import modelo.Asignatura;
import modelo.Cursa;
import modelo.CursaTutoria;
import modelo.Tutoria;
import modelo.Usuario;
import vista.listas.util.Utilvista;

public class FrmTutoriasPrincipal extends javax.swing.JFrame {

    private CursaTutoriaBD fileTutoriaM = new CursaTutoriaBD();
    private TutoriaBD tutoriaControl = new TutoriaBD();
    private CursaTutoriaBD fileMatriculaAsg = new CursaTutoriaBD();
    private static PersonaBD personaControl = new PersonaBD();
    private AsignacionBD asignacionControl = new AsignacionBD();
    private AsignaturaBD asignaturaControl = new AsignaturaBD();
    private CursaTutoriaBD cursaTutoriaControl = new CursaTutoriaBD();
    private MatriculaBD matriculaControl = new MatriculaBD();
    private UsuarioBD usuarioControl = new UsuarioBD();
    private CursaBD cursaControl = new CursaBD();
    private Usuario usuarioNavegacion;

    public FrmTutoriasPrincipal() throws EmptyException {
        initComponents();
        cargarAsignaciones();
        cargarTutorias();
    }

    private void cargarAsignaciones() throws EmptyException {
        usuarioNavegacion = usuarioControl.buscarBinaria(usuarioControl.all(), "id", "2");
        usuarioControl.setUsuario(usuarioControl.buscarBinaria(usuarioControl.all(), "id", "2"));
        cargarUsuario(usuarioNavegacion);
        personaControl.setPersona(personaControl.buscarBinaria(personaControl.all(), "dni", usuarioNavegacion.getPersona_DNI()));
        lbUsuario.setText(personaControl.getPersona().getApellido() + " " + personaControl.getPersona().getNombre());
        if (usuarioNavegacion.getRol_id() == 1) {
            System.out.println("paso 1");
            matriculaControl.setMatricula(matriculaControl.buscarBinaria(matriculaControl.all(), "usuario_ID", String.valueOf(usuarioControl.getUsuario().getId())));
            cursaControl.setCursas(cursaControl.buscarLineal(cursaControl.all(), "matricula_id", String.valueOf(matriculaControl.getMatricula().getId())));
            DynamicList<Asignatura> asignaturasMostrar = new DynamicList<>();
            Asignatura asignatura;
            Cursa[] cursas = cursaControl.getCursas().toArray();
            System.out.println("paso 2");
            for (int i = 0; i < cursaControl.getCursas().getLength(); i++) {
                asignatura = asignaturaControl.buscarBinaria(asignaturaControl.all(), "codigo", cursas[i].getAsignatura_CODIGO());
                asignaturasMostrar.add(asignatura);
            }
            System.out.println("paso 3");
            Utilvista.cargarComboAsignaturas(cbxAsignaturas, asignaturasMostrar);
        } else if (usuarioNavegacion.getRol_id() == 2) {
            usuarioNavegacion = usuarioControl.buscarBinaria(usuarioControl.all(), "id", "1");
            usuarioControl.setUsuario(usuarioControl.buscarBinaria(usuarioControl.all(), "id", "1"));
            personaControl.setPersona(personaControl.buscarBinaria(personaControl.all(), "dni", usuarioNavegacion.getPersona_DNI()));
            asignacionControl.setAsignaciones(asignacionControl.buscarLineal(asignacionControl.all(), "usuario_ID", String.valueOf(usuarioControl.getUsuario().getId())));
            Utilvista.cargarComboAsignacion(asignacionControl.getAsignaciones(), cbxAsignaturas);
            lbUsuario.setText(personaControl.getPersona().getApellido() + " " + personaControl.getPersona().getNombre());
        }
    }

    public void cargarUsuario(Usuario usuario) {
        usuarioControl.setUsuario(usuario);
        if (usuario.getRol_id() == 1) {
            btGenerarInforme.setVisible(false);
            btNuevaTutoria.setVisible(false);
            btNuevaTutoria1.setVisible(false);
        } else {
            chkNo.setVisible(false);
            lbRecibirTutoria.setVisible(false);
            chkSi.setVisible(false);
            btGuardar.setVisible(false);
        }
    }

    private void guardarImpartida() throws Exception {
        if (chkSi.isSelected()) {
            cursaTutoriaControl.getCursaTutoria().setImpartida(true);
            cursaTutoriaControl.merge(cursaTutoriaControl.getCursaTutoria());
        }else{
            cursaTutoriaControl.getCursaTutoria().setImpartida(false);
            cursaTutoriaControl.merge(cursaTutoriaControl.getCursaTutoria());
        }
    }

    private void limpiar() {
        txtAsignatura.setText("");
        txtTema.setText("");
        txtFecha.setText("");
        chkSi.setSelected(false);
        chkNo.setSelected(false);
    }

    private void cargarTutorias() throws EmptyException {
        personaControl.setPersona(personaControl.buscarBinaria(personaControl.all(), "dni", usuarioNavegacion.getPersona_DNI()));
        System.out.println("paso 4");
        if (usuarioNavegacion.getRol_id() == 1) {
            matriculaControl.setMatricula(matriculaControl.buscarBinaria(matriculaControl.all(), "usuario_id", String.valueOf(usuarioNavegacion.getId())));
            cursaControl.setCursas(cursaControl.buscarLineal(cursaControl.all(), "matricula_id", String.valueOf(matriculaControl.getMatricula().getId())));
            Cursa[] cursas = cursaControl.getCursas().toArray();
            DynamicList<CursaTutoria> cursasTutoriaFinal = new DynamicList<>();
            for (int i = 0; i < cursaControl.getCursas().getLength(); i++) {
                cursaTutoriaControl.setCursaTutorias(cursaTutoriaControl.buscarLineal(cursaTutoriaControl.all(), "cursa_id", String.valueOf(cursas[i].getId())));
                CursaTutoria[] cursasTutorias = cursaTutoriaControl.getCursaTutorias().toArray();
                for (int j = 0; j < cursaTutoriaControl.getCursaTutorias().getLength(); j++) { // Cambio de i a j
                    cursasTutoriaFinal.add(cursasTutorias[j]);
                }
            }

            System.out.println("paso 5");
//            DynamicList<CursaTutoria> cursasTutoriaFinal = new DynamicList<>();
//            for (Cursa cursa : cursas) {
//                cursaTutoriaControl.setCursaTutorias(cursaTutoriaControl.buscarLineal(cursaTutoriaControl.all(), "cursa_id", String.valueOf(cursa.getId())));
//                for (int i = 0; i < cursaTutoriaControl.getCursaTutorias().getLength(); i++) {
//                    cursasTutoriaFinal.add(cursaTutoriaControl.get(i));
//                }
//            }
//            System.out.println("AAAAAAAAAA");
//            System.out.println(cursasTutoriaFinal);

            DynamicList<Tutoria> tutoriaFinal = new DynamicList<>();
            for (CursaTutoria cursaTutoria : cursasTutoriaFinal.toArray()) {
                tutoriaFinal.add(tutoriaControl.buscarBinaria("id", String.valueOf(cursaTutoria.getTutoria_ID())));
            }
            Utilvista.cargarListaTutorias(tutoriaFinal, lstTutorias);
            System.out.println("paso 6");
        } else {
            System.out.println("paso 5 DOCENTE");
            DynamicList<Tutoria> tutorias = new DynamicList<>();
            Asignacion[] asignaciones = asignacionControl.getAsignaciones().toArray();
            tutoriaControl.setTutorias(tutoriaControl.buscarLineal(tutoriaControl.all(), "asignacion_id", String.valueOf(asignaciones[cbxAsignaturas.getSelectedIndex()].getId())));
            for (int j = 0; j < tutoriaControl.getTutorias().getLength(); j++) {
                tutorias.add(tutoriaControl.getTutorias().getInfo(j));
            }
            Utilvista.cargarListaTutorias(tutorias, lstTutorias);
        }
//        asignacionControl.setAsignaciones(asignacionControl.buscarLineal(asignacionControl.all(), "DNIDocente", String.valueOf(personaControl.getPersona().getDni())));
//        Asignacion asignaciones[] = asignacionControl.getAsignaciones().toArray();
//        Asignacion asignacion;
//        DynamicList<Asignatura> asignaturas = new DynamicList<>();
//        for (int i = 0; i < asignacionControl.getAsignaciones().getLength(); i++) {
//            asignacion = asignacionControl.get(asignaciones[i].getId());
//            asignaturas.add(asignaturaControl.buscarBinaria("codigo", asignacion.getAsignatura_CODIGO()));
//        }
//        Asignatura asignaturasArray[] = asignaturaControl.getAsignaturas().toArray();
//        Asignatura asignatura;
//        DynamicList<CursaTutoria> matriculasAsignaturas = new DynamicList<>();
//        for (int i = 0; i < asignaturaControl.getAsignaturas().getLength(); i++) {
//            asignatura = asignaturaControl.get(asignaturasArray[i].getId());
//            matriculasAsignaturas.add(cursaTutoriaControl.buscarBinaria("codigo", asignatura.getCodigo()));
//        }
//        CursaTutoria tMatriculas[] = cursaTutoriaControl.getCursaTutorias().toArray();
//        CursaTutoria tMatricula;
//        DynamicList<CursaTutoria> tutoriasMatricula = new DynamicList<>();
//        for (int i = 0; i < cursaTutoriaControl.getCursaTutorias().getLength(); i++) {
//            tMatricula = fileTutoriaM.get(tMatriculas[i].getId());
//            tutoriasMatricula.add(tMatricula);
//        }
    }

    private void cargarVistaCursaTutoria(CursaTutoria asgMatricula) throws EmptyException {
        fileTutoriaM.setCursaTutoria(asgMatricula);
        CursaTutoria matriculaAsg = fileMatriculaAsg.buscarBinaria(fileMatriculaAsg.all(), "id", String.valueOf(fileTutoriaM.getCursaTutoria().getCursa_ID()));
        Asignatura asignatura = asignaturaControl.buscarBinaria(asignaturaControl.all(), "id", matriculaAsg.getId().toString());
        Tutoria tutoria = tutoriaControl.buscarBinaria("id", fileTutoriaM.getCursaTutoria().getTutoria_ID().toString());
        txtAsignatura.setText(asignatura.getNombre());
        txtTema.setText(tutoria.getTema());
        txtFecha.setText(tutoria.getFecha().format((DateTimeFormatter) Utilvista.FORMATO_FECHA));
        if (fileTutoriaM.getCursaTutoria().getImpartida()) {
            chkSi.setSelected(true);
            chkNo.setSelected(false);
        } else {
            chkSi.setSelected(false);
            chkNo.setSelected(true);
        }
    }

    private void cargarVistaTutoria() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTutorias = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lbRecibirTutoria = new javax.swing.JLabel();
        chkSi = new javax.swing.JCheckBox();
        chkNo = new javax.swing.JCheckBox();
        btGuardar = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        cbxAsignaturas = new javax.swing.JComboBox<>();
        btNuevaTutoria = new javax.swing.JButton();
        btGenerarInforme = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btNuevaTutoria1 = new javax.swing.JButton();
        lbUsuario = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 250, 205));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PROXIMAS TUTORIAS");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 260, -1));

        jScrollPane1.setBorder(null);

        lstTutorias.setBackground(new java.awt.Color(212, 173, 107));
        lstTutorias.setFont(new java.awt.Font("Franklin Gothic Book", 1, 15)); // NOI18N
        lstTutorias.setForeground(new java.awt.Color(0, 0, 0));
        lstTutorias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Avance Pis", "Revision de la Proporcion Poblacional", "Distribucion de la Media Muestral" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstTutorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstTutoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstTutorias);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 260, 370));

        jPanel2.setBackground(new java.awt.Color(102, 51, 0));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Asignatura");

        txtAsignatura.setBackground(new java.awt.Color(212, 173, 107));
        txtAsignatura.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        txtAsignatura.setForeground(new java.awt.Color(255, 250, 205));
        txtAsignatura.setText("Estructura de Datos");
        txtAsignatura.setBorder(null);
        txtAsignatura.setCaretColor(new java.awt.Color(255, 250, 205));
        txtAsignatura.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAsignatura.setEnabled(false);
        txtAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsignaturaActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 250, 205));
        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 250, 205));
        jLabel2.setText("Tema:");

        txtTema.setBackground(new java.awt.Color(212, 173, 107));
        txtTema.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        txtTema.setForeground(new java.awt.Color(0, 0, 0));
        txtTema.setText("Avance PIS");
        txtTema.setBorder(null);
        txtTema.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTema.setEnabled(false);
        txtTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTemaActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 250, 205));
        jLabel3.setText("Fecha:");

        txtFecha.setBackground(new java.awt.Color(212, 173, 107));
        txtFecha.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(0, 0, 0));
        txtFecha.setText("25 de diciembre, 2023");
        txtFecha.setBorder(null);
        txtFecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFecha.setEnabled(false);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        lbRecibirTutoria.setBackground(new java.awt.Color(51, 51, 51));
        lbRecibirTutoria.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        lbRecibirTutoria.setForeground(new java.awt.Color(255, 250, 205));
        lbRecibirTutoria.setText("¿Ha recibido la tutoria?");

        chkSi.setBackground(new java.awt.Color(212, 173, 107));
        buttonGroup1.add(chkSi);
        chkSi.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        chkSi.setForeground(new java.awt.Color(0, 0, 0));
        chkSi.setText("Si");
        chkSi.setBorder(null);
        chkSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSiActionPerformed(evt);
            }
        });

        chkNo.setBackground(new java.awt.Color(212, 173, 107));
        buttonGroup1.add(chkNo);
        chkNo.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        chkNo.setForeground(new java.awt.Color(0, 0, 0));
        chkNo.setText("No");
        chkNo.setBorder(null);
        chkNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNoActionPerformed(evt);
            }
        });

        btGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btGuardar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btGuardar.setText("Guardar");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(198, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(chkSi)
                                .addGap(18, 18, 18)
                                .addComponent(chkNo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btGuardar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRecibirTutoria)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTema, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbRecibirTutoria)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btGuardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkSi)
                            .addComponent(chkNo))))
                .addGap(33, 33, 33))
        );

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 500, 370));
        bg.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jButton1.setBackground(new java.awt.Color(102, 51, 0));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salir");
        bg.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, -1, -1));

        cbxAsignaturas.setBackground(new java.awt.Color(212, 173, 107));
        cbxAsignaturas.setForeground(new java.awt.Color(0, 0, 0));
        cbxAsignaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxAsignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxAsignaturasMouseClicked(evt);
            }
        });
        bg.add(cbxAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 220, -1));

        btNuevaTutoria.setBackground(new java.awt.Color(102, 51, 0));
        btNuevaTutoria.setForeground(new java.awt.Color(255, 255, 255));
        btNuevaTutoria.setText("Nueva Tutoria");
        btNuevaTutoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaTutoriaActionPerformed(evt);
            }
        });
        bg.add(btNuevaTutoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        btGenerarInforme.setBackground(new java.awt.Color(102, 51, 0));
        btGenerarInforme.setForeground(new java.awt.Color(255, 255, 255));
        btGenerarInforme.setText("Generar Informe");
        btGenerarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerarInformeActionPerformed(evt);
            }
        });
        bg.add(btGenerarInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 130, -1));

        jDateChooser1.setBackground(new java.awt.Color(212, 173, 107));
        bg.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 170, -1));

        btNuevaTutoria1.setBackground(new java.awt.Color(102, 51, 0));
        btNuevaTutoria1.setText("Nueva Tutoria");
        bg.add(btNuevaTutoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        lbUsuario.setForeground(new java.awt.Color(0, 0, 0));
        lbUsuario.setText("Usuario:");
        bg.add(lbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 150, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Usuario:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 50, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lstTutoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstTutoriasMouseClicked
        if (lstTutorias.getSelectedValue() != null) {
            Object t = lstTutorias.getSelectedValue();
            if (usuarioNavegacion.getRol_id() == 1) {
                Tutoria tutoria = (Tutoria) t;
                try {
                    System.out.println(tutoria.getId());
                    cursaTutoriaControl.setCursaTutoria(cursaTutoriaControl.buscarBinaria(cursaTutoriaControl.getCursaTutorias(), "tutoria_id", String.valueOf(tutoria.getId())));
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmTutoriasPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    cargarVistaCursaTutoria(cursaTutoriaControl.getCursaTutoria());
                } catch (EmptyException ex) {
                    System.out.println(ex.toString());
                }
            } else if (usuarioNavegacion.getRol_id() == 2) {
                Tutoria tutoria = (Tutoria) t;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El objeto es nulo!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstTutoriasMouseClicked

    private void btNuevaTutoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaTutoriaActionPerformed
        try {
            FrmNuevaTutoria tutoriaFrm = new FrmNuevaTutoria(usuarioNavegacion);
            tutoriaFrm.setVisible(true);

        } catch (EmptyException ex) {
            Logger.getLogger(FrmTutoriasPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btNuevaTutoriaActionPerformed

    private void chkNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNoActionPerformed

    private void chkSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSiActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTemaActionPerformed

    private void txtAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsignaturaActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        try {
            guardarImpartida();
            JOptionPane.showMessageDialog(null, "Tutoria guardada con exito");
            limpiar();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btGuardarActionPerformed

    private void cbxAsignaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxAsignaturasMouseClicked
        try {
            cargarTutorias();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmTutoriasPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxAsignaturasMouseClicked

    private void btGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerarInformeActionPerformed
        try {
            FrmGenerarInforme informeFrm = new FrmGenerarInforme();
            informeFrm.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(FrmTutoriasPrincipal.class.getName()).log(Level.SEVERE, null, e.toString());
        }
    }//GEN-LAST:event_btGenerarInformeActionPerformed

    public static void main(String args[]) throws UnsupportedLookAndFeelException {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmTutoriasPrincipal().setVisible(true);
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmTutoriasPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btGenerarInforme;
    private javax.swing.JToggleButton btGuardar;
    private javax.swing.JButton btNuevaTutoria;
    private javax.swing.JButton btNuevaTutoria1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxAsignaturas;
    private javax.swing.JCheckBox chkNo;
    private javax.swing.JCheckBox chkSi;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbRecibirTutoria;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JList<String> lstTutorias;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
