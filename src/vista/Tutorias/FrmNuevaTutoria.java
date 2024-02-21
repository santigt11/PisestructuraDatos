package vista.Tutorias;

import controlador.Academico.AsignaturaBD;
import controlador.Academico.AsignacionBD;
import controlador.Admin.PersonaBD;
import controlador.Login.UsuarioBD;
import controlador.Matriculas.MatriculaBD;
import controlador.Matriculas.CursaBD;
import controlador.Matriculas.CursaTutoriaBD;
import controlador.TDA.listas.DynamicList;
import controlador.Tutorias.TutoriaBD;
import controlador.TDA.listas.Exception.EmptyException;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Matricula;
import modelo.Cursa;
import modelo.Persona;
import modelo.Usuario;
import vista.listas.util.Utilvista;

public class FrmNuevaTutoria extends javax.swing.JFrame {

    public FrmNuevaTutoria(Usuario usuario) throws EmptyException {
        initComponents();
        cargarDocente(usuario);
        limpiar();
    }

    private TutoriaBD tutoriaControl = new TutoriaBD();
    private AsignaturaBD asignaturaControl = new AsignaturaBD();
    private CursaBD cursaControl = new CursaBD();
    private AsignacionBD asignacionControl = new AsignacionBD();
    private UsuarioBD usuarioControl = new UsuarioBD();
    private CursaTutoriaBD cursaTutoriasControl = new CursaTutoriaBD();
    private MatriculaBD matriculaControl = new MatriculaBD();
    private PersonaBD personaControl = new PersonaBD();
    private static Usuario usuarioNavegacion;
    DefaultListModel modeloLista;

    public void cargarDocente(Usuario usuario) {
        if (usuario.getRol_id() == 2) {
            usuarioNavegacion = usuario;
        }
    }

    private void cargarAsignaciones() throws EmptyException {
        asignacionControl.setAsignaciones(null);
        asignacionControl.setAsignacion(null);
        personaControl.setPersona(null);
        personaControl.setPersonas(null);
        personaControl.setPersona(personaControl.buscarBinaria(personaControl.all(), "dni", usuarioNavegacion.getPersona_DNI()));
        lbDocente.setText(personaControl.getPersona().getApellido() + " " + personaControl.getPersona().getNombre());
        asignacionControl.setAsignaciones(asignacionControl.buscarLineal(asignacionControl.all(), "usuario_ID", String.valueOf(usuarioNavegacion.getId())));
        Utilvista.cargarComboAsignacion(asignacionControl.getAsignaciones(), cbxAsignatura);
    }

//    private void ordenar(){1101201301"
//        String criterio = cbxCriterio.getSelectedItem().toString();
//        Integer tipo = 0;
//        if (btn_tipo.isSelected()) {
//            tipo = 1;
//        }
//        try {
//            mtp.setPersonas(control.ordenar(control.all(), tipo, criterio));
//            tbPersona.setModel(mtp);
//            tbPersona.updateUI();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public Boolean verificar() {
        return (!txtTema.getText().trim().isEmpty()
                && !(cbxHoraInicio.getSelectedIndex() > 0)
                && !dcFecha.getDate().equals(null));
    }

    private void cargarListaCursas() throws EmptyException {
        lstCursa.removeAll(); // Elimina todos los elementos de la lista
        if (cbxAsignatura.getSelectedIndex() >= 0) { // Verificar que el índice seleccionado sea válido
            cursaControl.setCursas(cursaControl.buscarLineal(cursaControl.all(), "asignatura_codigo", asignaturaControl.buscarBinaria(asignaturaControl.all(),"nombre", cbxAsignatura.getSelectedItem().toString()).getCodigo()));
            if (!cursaControl.getCursas().isEmpty()) {
                Matricula matricula;
                for (int i = 0; i < cursaControl.getCursas().getLength(); i++) {
                    matricula = matriculaControl.get(cursaControl.getCursas().getInfo(i).getMatricula_ID());
                    usuarioControl.getUsuarios().add(usuarioControl.get(matricula.getUsuario_ID()));
                }
                Persona persona;
                for (int i = 0; i < usuarioControl.getUsuarios().getLength(); i++) {
                    persona = personaControl.buscarBinaria(personaControl.getPersonasTodos(), "dni", usuarioControl.getUsuarios().getInfo(i).getPersona_DNI());
                    System.out.println(personaControl.buscarBinaria(personaControl.getPersonasTodos(), "dni", usuarioControl.getUsuarios().getInfo(i).getPersona_DNI()));
                    personaControl.setPersonas(null);
                    personaControl.getPersonas().add(persona);
                }
                System.out.println("\nPERSONAS GUARDADAS EN PERSONA CONTROL");
                System.out.println(personaControl.getPersonas());
                Utilvista.cargarListaPersonas(personaControl.getPersonas(), lstCursa);
                personaControl.setPersonas(null);
            }else{
                Utilvista.cargarListaPersonas(new DynamicList<>(), lstCursa);
            }
        }
    }

    private void crearTutoria() throws Exception {
        if (verificar()) {
            tutoriaControl.getTutoria().setFecha(dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            tutoriaControl.getTutoria().setTema(txtTema.getText());
            tutoriaControl.getTutoria().setModalidad_ID(cbxModalidad.getSelectedIndex() + 1);
            tutoriaControl.getTutoria().setHoraFin(cbxHoraFin.getSelectedItem().toString());
            tutoriaControl.getTutoria().setHoraInicio(cbxHoraInicio.getSelectedItem().toString());
            tutoriaControl.getTutoria().setHorarioValido(true);
            tutoriaControl.getTutoria().setId(tutoriaControl.all().getLength() + 1);
            asignacionControl.setAsignaciones(asignacionControl.buscarLineal(asignacionControl.all(), "asignatura_codigo", asignaturaControl.buscarBinaria(asignaturaControl.all(), "nombre", cbxAsignatura.getSelectedItem().toString()).getCodigo()));
            asignacionControl.setAsignacion(asignacionControl.buscarBinaria(asignacionControl.getAsignaciones(), "usuario_id", String.valueOf(usuarioNavegacion.getId())));
            tutoriaControl.getTutoria().setAsignacion_ID(asignacionControl.getAsignacion().getId());
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() throws EmptyException {
        // Eliminamos la recarga de datos redundante
        Utilvista.limpiarLista(lstEstudiantesAsignados);
        Utilvista.limpiarLista(lstCursa);
        cargarAsignaciones();
        cargarListaCursas();
        txtTema.setText("");
        txtTema.setEnabled(true);
        cbxAsignatura.setSelectedIndex(0);
        cbxHoraInicio.setSelectedIndex(0);
        cbxHoraFin.setSelectedIndex(0);
        tutoriaControl.setTutoria(null);
        tutoriaControl.setTutorias(null);
        asignacionControl.setAsignacion(null);
        personaControl.setPersona(null);
        personaControl.setPersonas(null);
        cursaTutoriasControl.setCursaTutorias(null);
        cursaTutoriasControl.setCursaTutoria(null);
    }

    private void cargarEstudiante() throws Exception {
        Object p = lstCursa.getSelectedValue();
        crearTutoria();
        if (p != null) {
            Persona estudiante = (Persona) p;
            personaControl.getPersonas().add(estudiante);
            usuarioControl.setUsuario(usuarioControl.buscarBinaria(usuarioControl.all(), "persona_dni", estudiante.getDni()));
            matriculaControl.setMatricula(matriculaControl.buscarBinaria(matriculaControl.all(), "usuario_id", String.valueOf(usuarioControl.getUsuario().getId())));
            cursaControl.setCursas(cursaControl.buscarLineal(cursaControl.all(), "matricula_id", String.valueOf(matriculaControl.getMatricula().getId())));
            cursaControl.setCursa(cursaControl.buscarBinaria(cursaControl.getCursas(), "asignatura_codigo", asignaturaControl.get(cbxAsignatura.getSelectedIndex() + 1).getCodigo()));
            cursaTutoriasControl.getCursaTutoria().setTutoria_ID(tutoriaControl.getTutoria().getId());
            cursaTutoriasControl.getCursaTutoria().setImpartida(false);
            cursaTutoriasControl.getCursaTutoria().setId(cursaTutoriasControl.all().getLength() + 1);
            cursaTutoriasControl.getCursaTutoria().setCursa_ID(cursaControl.getCursa().getId());
            cursaTutoriasControl.getCursaTutorias().add(cursaTutoriasControl.getCursaTutoria());
            Utilvista.cargarListaPersonas(personaControl.getPersonas(), lstEstudiantesAsignados);
        } else {
            System.out.println("Error al elegir estudiante");
        }
    }

    private boolean guardarTutoria() throws Exception {
        if (tutoriaControl.persist(tutoriaControl.getTutoria())) {
            for (int i = 0; i < cursaTutoriasControl.getCursaTutorias().getLength(); i++) {
                cursaTutoriasControl.getCursaTutorias().getInfo(i).setTutoria_ID(tutoriaControl.getTutoria().getId());
                cursaTutoriasControl.persist(cursaTutoriasControl.getCursaTutorias().getInfo(i));
            }
            limpiar();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la tutoria", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbxAsignatura = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        cbxHoraInicio = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbxModalidad = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbxHoraFin = new javax.swing.JComboBox<>();
        btDescartar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btAsignarEstudiante1 = new javax.swing.JButton();
        btRemoverEstudiante = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstCursa = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstEstudiantesAsignados = new javax.swing.JList<>();
        btCrearTutoria1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbDocente = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 250, 205));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 51, 0));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Modalidad:");

        cbxAsignatura.setBackground(new java.awt.Color(212, 173, 107));
        cbxAsignatura.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        cbxAsignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estructura de Datos" }));
        cbxAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxAsignaturaMouseClicked(evt);
            }
        });
        cbxAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAsignaturaActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Fecha:");

        dcFecha.setBackground(new java.awt.Color(212, 173, 107));
        dcFecha.setForeground(new java.awt.Color(0, 0, 0));
        dcFecha.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Hora Inicio:");

        cbxHoraInicio.setBackground(new java.awt.Color(212, 173, 107));
        cbxHoraInicio.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxHoraInicio.setForeground(new java.awt.Color(0, 0, 0));
        cbxHoraInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30" }));
        cbxHoraInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxHoraInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxHoraInicioMouseClicked(evt);
            }
        });
        cbxHoraInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHoraInicioActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tema:");

        txtTema.setBackground(new java.awt.Color(212, 173, 107));
        txtTema.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 14)); // NOI18N
        txtTema.setForeground(new java.awt.Color(0, 0, 0));
        txtTema.setText("Avance PIS");

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Asignatura:");

        cbxModalidad.setBackground(new java.awt.Color(212, 173, 107));
        cbxModalidad.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxModalidad.setForeground(new java.awt.Color(0, 0, 0));
        cbxModalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presencial", "Virtual" }));
        cbxModalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxModalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxModalidadActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Hora Final:");

        cbxHoraFin.setBackground(new java.awt.Color(212, 173, 107));
        cbxHoraFin.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxHoraFin.setForeground(new java.awt.Color(0, 0, 0));
        cbxHoraFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30" }));
        cbxHoraFin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxHoraFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxHoraFinMouseClicked(evt);
            }
        });

        btDescartar1.setBackground(new java.awt.Color(212, 173, 107));
        btDescartar1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btDescartar1.setForeground(new java.awt.Color(102, 51, 0));
        btDescartar1.setText("Descartar");
        btDescartar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDescartar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDescartar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(cbxHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addComponent(btDescartar1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbxModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbxHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDescartar1))
                .addContainerGap())
        );

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 750, 230));

        jPanel2.setBackground(new java.awt.Color(102, 51, 0));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        btAsignarEstudiante1.setBackground(new java.awt.Color(212, 173, 107));
        btAsignarEstudiante1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btAsignarEstudiante1.setForeground(new java.awt.Color(102, 51, 0));
        btAsignarEstudiante1.setText("AsignarEstudiante");
        btAsignarEstudiante1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAsignarEstudiante1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAsignarEstudiante1ActionPerformed(evt);
            }
        });

        btRemoverEstudiante.setBackground(new java.awt.Color(212, 173, 107));
        btRemoverEstudiante.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btRemoverEstudiante.setForeground(new java.awt.Color(102, 51, 0));
        btRemoverEstudiante.setText("Remover Estudiante");
        btRemoverEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRemoverEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverEstudianteActionPerformed(evt);
            }
        });

        jScrollPane8.setBorder(null);

        lstCursa.setBackground(new java.awt.Color(212, 173, 107));
        lstCursa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstCursa.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lstCursa.setForeground(new java.awt.Color(0, 0, 0));
        lstCursa.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Jose Roberto Alcachofa Tercero", "Pepe Roberto Alcachofa Segundo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(lstCursa);

        jScrollPane7.setBorder(null);

        lstEstudiantesAsignados.setBackground(new java.awt.Color(212, 173, 107));
        lstEstudiantesAsignados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstEstudiantesAsignados.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lstEstudiantesAsignados.setForeground(new java.awt.Color(0, 0, 0));
        lstEstudiantesAsignados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Jose Ignacio Foquencio Cuarto", "Federico Medellin Nirvana Octavo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstEstudiantesAsignados.setPreferredSize(new java.awt.Dimension(219, 42));
        jScrollPane7.setViewportView(lstEstudiantesAsignados);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btAsignarEstudiante1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRemoverEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btAsignarEstudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btRemoverEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 750, 260));

        btCrearTutoria1.setBackground(new java.awt.Color(212, 173, 107));
        btCrearTutoria1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btCrearTutoria1.setForeground(new java.awt.Color(102, 51, 0));
        btCrearTutoria1.setText("Crear Tutoria");
        btCrearTutoria1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearTutoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearTutoria1ActionPerformed(evt);
            }
        });
        bg.add(btCrearTutoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 610, -1, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 51));
        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 39, 34));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NUEVA TUTORIA");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, -1));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Estudiantes asignados");
        bg.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, -1, -1));

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Lista de Estudiantes");
        bg.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        lbDocente.setBackground(new java.awt.Color(51, 51, 51));
        lbDocente.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        lbDocente.setForeground(new java.awt.Color(0, 0, 0));
        lbDocente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDocente.setText("Docente:");
        bg.add(lbDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Docente:");
        bg.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        jButton1.setBackground(new java.awt.Color(102, 51, 0));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        bg.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAsignarEstudiante1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignarEstudiante1ActionPerformed
        try {
            cargarEstudiante();
        } catch (Exception ex) {
            System.out.println("al cargar estudiante");
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btAsignarEstudiante1ActionPerformed

    private void btRemoverEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRemoverEstudianteActionPerformed

    private void cbxAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAsignaturaActionPerformed

    private void btCrearTutoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearTutoria1ActionPerformed
        try {
            if (cbxHoraInicio.getSelectedIndex() < cbxHoraFin.getSelectedIndex()) {
                guardarTutoria();
            } else {
                JOptionPane.showMessageDialog(null, "La fecha de inicio y fin de tutoría se encuentran incorrectas");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btCrearTutoria1ActionPerformed

    private void cbxModalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxModalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxModalidadActionPerformed

    private void cbxHoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxHoraInicioActionPerformed

    private void btDescartar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDescartar1ActionPerformed
        try {
            limpiar();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmNuevaTutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDescartar1ActionPerformed

    private void cbxAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxAsignaturaMouseClicked
        try {
            cargarListaCursas();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmNuevaTutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxAsignaturaMouseClicked

    private void cbxHoraInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxHoraInicioMouseClicked

    }//GEN-LAST:event_cbxHoraInicioMouseClicked

    private void cbxHoraFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxHoraFinMouseClicked

    }//GEN-LAST:event_cbxHoraFinMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmNuevaTutoria(new Usuario()).setVisible(true);
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmNuevaTutoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btAsignarEstudiante1;
    private javax.swing.JButton btCrearTutoria1;
    private javax.swing.JButton btDescartar1;
    private javax.swing.JButton btRemoverEstudiante;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxHoraFin;
    private javax.swing.JComboBox<String> cbxHoraInicio;
    private javax.swing.JComboBox<String> cbxModalidad;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbDocente;
    private javax.swing.JList<String> lstCursa;
    private javax.swing.JList<String> lstEstudiantesAsignados;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
