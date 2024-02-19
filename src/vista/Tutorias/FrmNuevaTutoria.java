package vista.Tutorias;

import controlador.Academico.AsignaturaBD;
import controlador.Academico.AsignacionBD;
import controlador.Admin.PersonaBD;
import controlador.Login.UsuarioDB;
import controlador.Matriculas.MatriculaBD;
import controlador.Matriculas.AsignacionMatriculaBD;
import controlador.Matriculas.CursaBD;
import controlador.Tutorias.TutoriaBD;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Tutorias.HorarioBD;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Matricula;
import modelo.Cursa;
import modelo.Persona;
import modelo.Usuario;
import vista.listas.util.Utilvista;

public class FrmNuevaTutoria extends javax.swing.JFrame {

    public FrmNuevaTutoria() {
        initComponents();
//        limpiar();
    }

    private TutoriaBD tutoriaControl = new TutoriaBD();
    private HorarioBD horarioControl = new HorarioBD();
    private AsignaturaBD asignaturaControl = new AsignaturaBD();
    private AsignacionMatriculaBD matriculaAsignControl = new AsignacionMatriculaBD();
    private CursaBD cursaControl = new CursaBD();
    private PersonaBD personaControl = new PersonaBD();
    private AsignacionBD contratoControl = new AsignacionBD();
    private MatriculaBD matriculaControl = new MatriculaBD();
    private static UsuarioDB usuarioControl = new UsuarioDB();
    private AsignacionMatriculaBD tutoriaMatrControl = new AsignacionMatriculaBD();
    
    public static void cargarDocente(Usuario usuario) {
        if (usuario.getRol_id() == 2) {
            usuarioControl.setUsuario(usuario);
        }
    }

    private void cargarAsignaciones() throws EmptyException {
//        personaControl.setPersona(personaControl.buscarBinaria("dni", "1101201301"));
//        System.out.println(personaControl.buscarBinaria("dni", "1101201301"));
        contratoControl.setAsignaciones(contratoControl.buscarLineal(contratoControl.all(), "usuario_ID",String.valueOf(usuarioControl.getUsuario().getId())));
        Utilvista.cargarComboAsignaturaContrato(contratoControl.getAsignaciones(), cbxHorario);
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
                && !(cbxHorario.getSelectedIndex() > 0)
                && !dcFecha.getDate().equals(null));
    }
    
    //MatriculaAsignatura = Cursa
    //matriculaAsignControl = cursaControl
    private void cargarListaCursas() throws EmptyException {
        cursaControl.setCursas(cursaControl.buscarLineal(cursaControl.getCursasTodas(), "asignatura_codigo", asignaturaControl.get(cbxAsignatura.getSelectedIndex()+1).getCodigo()));
        System.out.println(cursaControl.all());
        Cursa cursas[] = cursaControl.getCursas().toArray();
        System.out.println(cursas[0]);
        Matricula matricula;
        for (int i = 0; i < cursaControl.getCursas().getLength(); i++) {
            matricula = matriculaControl.get(cursas[i].getMatricula_ID());
            matriculaControl.getMatriculas().add(matricula);
        }
        System.out.println(matriculaControl.getMatriculas());
        //No obtener en matricula Personas si no Uusarios
        Matricula matriculas[] = matriculaControl.getMatriculas().toArray();
        Persona persona;
        for (int i = 0; i < matriculaControl.getMatriculas().getLength(); i++) {
//            matriculaControl.buscarLineal(matriculaControl.getMatriculas(), "persona_dni", valorBuscado)
            persona = personaControl.buscarBinaria("dni", matriculas[i].getPersona_DNI());
            personaControl.getPersonas().add(persona);
        }
        Utilvista.cargarListaUsuarios(personaControl.getPersonas(), lstCursa);
    }

//    private void buscar() {
//        String texto = txtTextoBuscar.getText();
//        try {
//            mtp.setPersonas(control.all());
//            tbPersona.setModel(mtp);
//            tbPersona.updateUI();
//        } catch (Exception e) {
//        }
//    }
    private void guardar() throws EmptyException, Exception {
        if (verificar()) {
            tutoriaControl.getTutoria().setFecha(dcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            tutoriaControl.getTutoria().setModalidad_ID(cbxModalidad.getSelectedIndex() + 1);
            tutoriaControl.getTutoria().setTema(txtTema.getText());
            tutoriaControl.persist(tutoriaControl.getTutoria());
            JOptionPane.showMessageDialog(null, "Datos guardados");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() throws EmptyException {
        cargarAsignaciones();
        cargarListaCursas();
        lstEstudiantesAsignados.removeAll();
        try {
            Utilvista.cargarComboAsignaturaContrato(contratoControl.getAsignaciones(), cbxAsignatura);
            Utilvista.cargarcomboRolesHorario(cbxHorario);
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        lstEstudiantesAsignados.clearSelection();
        txtTema.setText("");
        txtTema.setEnabled(true);
        cbxAsignatura.setSelectedIndex(0);
        cbxHorario.setSelectedIndex(0);
    }

    private void cargarEstudiante() {
        Object p = lstCursa.getSelectedValue();
        Cursa estudiante = (Cursa) p;
        tutoriaMatrControl.getAsgMatricula().setMatriculaAsignatura_ID(estudiante.getId());
        tutoriaMatrControl.getAsgMatricula().setTutoria_ID(tutoriaControl.getTutoria().getId());
        tutoriaMatrControl.getAsgMatriculas().add(tutoriaMatrControl.getAsgMatricula());
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
        cbxHorario = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbxModalidad = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btAsignarEstudiante1 = new javax.swing.JButton();
        btRemoverEstudiante = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstCursa = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstEstudiantesAsignados = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btCrearTutoria1 = new javax.swing.JButton();
        btDescartar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jLabel8.setText("Horario:");

        cbxHorario.setBackground(new java.awt.Color(212, 173, 107));
        cbxHorario.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxHorario.setForeground(new java.awt.Color(0, 0, 0));
        cbxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 AM - 5 PM" }));
        cbxHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 600, 230));

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

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 750, 260));

        jLabel1.setBackground(new java.awt.Color(255, 255, 51));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 39, 34));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA TUTORIA");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, -1));

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
        bg.add(btCrearTutoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, -1, 30));

        btDescartar.setBackground(new java.awt.Color(212, 173, 107));
        btDescartar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btDescartar.setForeground(new java.awt.Color(102, 51, 0));
        btDescartar.setText("Descartar");
        btDescartar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.add(btDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAsignarEstudiante1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignarEstudiante1ActionPerformed
        cargarEstudiante();
    }//GEN-LAST:event_btAsignarEstudiante1ActionPerformed

    private void btRemoverEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRemoverEstudianteActionPerformed

    private void cbxAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAsignaturaActionPerformed

    private void btCrearTutoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearTutoria1ActionPerformed
        try {
            guardar();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btCrearTutoria1ActionPerformed

    private void cbxModalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxModalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxModalidadActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmNuevaTutoria().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btAsignarEstudiante1;
    private javax.swing.JButton btCrearTutoria1;
    private javax.swing.JButton btDescartar;
    private javax.swing.JButton btRemoverEstudiante;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JComboBox<String> cbxModalidad;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> lstCursa;
    private javax.swing.JList<String> lstEstudiantesAsignados;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
