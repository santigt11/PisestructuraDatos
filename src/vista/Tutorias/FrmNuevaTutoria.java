package vista.Tutorias;

import controlador.Academico.AsignaturaArchivos;
import controlador.Academico.ContratoArchivos;
import controlador.Admin.PersonaArchivos;
import controlador.Matriculas.MatriculaArchivos;
import controlador.Matriculas.MatriculaAsignaturaArchivos;
import controlador.Tutorias.TutoriaArchivos;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Tutorias.HorarioArchivos;
import controlador.Tutorias.TutoriaMatriculaArchivos;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Matricula;
import modelo.MatriculaAsignatura;
import modelo.Modalidad;
import modelo.Persona;
import vista.listas.util.Utilvista;

public class FrmNuevaTutoria extends javax.swing.JFrame {

    public FrmNuevaTutoria() throws EmptyException {
        initComponents();
        limpiar();
    }

    private TutoriaArchivos tutoriaControl = new TutoriaArchivos();
    private HorarioArchivos horarioControl = new HorarioArchivos();
    private AsignaturaArchivos asignaturaControl = new AsignaturaArchivos();
    private MatriculaAsignaturaArchivos matriculaAsignControl = new MatriculaAsignaturaArchivos();
    private static PersonaArchivos personaControl = new PersonaArchivos();
    private ContratoArchivos contratoControl = new ContratoArchivos();
    private MatriculaArchivos matriculaControl = new MatriculaArchivos();
    private TutoriaMatriculaArchivos tutoriaMatrControl = new TutoriaMatriculaArchivos();
    
    public static void cargarDocente(Persona persona) {
        personaControl.setPersona(persona);
    }

    private void cargarContratos() throws EmptyException {
        personaControl.setPersona(personaControl.buscarBinaria("dni", "1101201301"));
        System.out.println(personaControl.buscarBinaria("dni", "1101201301"));
        contratoControl.setContratos(contratoControl.buscarLineal(contratoControl.all(), "DNIDocente",personaControl.getPersona().getDni()));
        Utilvista.cargarComboAsignaturaContrato(contratoControl.getContratos(), cbxHorario);
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

    private void cargarListaMatriculaAsignatura() throws EmptyException {
        matriculaAsignControl.setAsgMatriculas(matriculaAsignControl.buscarLineal(matriculaAsignControl.getAsgMatriculasTodas(), "asignatura_codigo", asignaturaControl.get(cbxAsignatura.getSelectedIndex()+1).getCodigo()));
        System.out.println(matriculaControl.all());
        MatriculaAsignatura matriculasA[] = matriculaAsignControl.getAsgMatriculas().toArray();
        Matricula matricula;
        for (int i = 0; i < matriculaAsignControl.getAsgMatriculas().getLength(); i++) {
            matricula = matriculaControl.get(matriculasA[i].getMatricula_ID());
            matriculaControl.getMatriculas().add(matricula);
        }
        System.out.println(matriculaControl.getMatriculas());
        Matricula matriculas[] = matriculaControl.getMatriculas().toArray();
        Persona persona;
        for (int i = 0; i < matriculaControl.getMatriculas().getLength(); i++) {
//            matriculaControl.buscarLineal(matriculaControl.getMatriculas(), "persona_dni", valorBuscado)
            persona = personaControl.buscarBinaria("dni", matriculas[i].getPersona_DNI());
            personaControl.getPersonas().add(persona);
        }
        Utilvista.cargarListaPersonas(personaControl.getPersonas(), lstMatriculaAsignatura);
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
            tutoriaControl.getTutoria().setIdHorario(horarioControl.get(cbxHorario.getSelectedIndex()+1).getId());
            tutoriaControl.getTutoria().setModalidad(Modalidad.PRESENCIAL);
            tutoriaControl.getTutoria().setTema(txtTema.getText());
            tutoriaControl.getTutoria().setImpartida(true);
            tutoriaControl.persist(tutoriaControl.getTutoria());
            JOptionPane.showMessageDialog(null, "Datos guardados");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() throws EmptyException {
        cargarContratos();
        cargarListaMatriculaAsignatura();
        lstEstudiantesAsignados.removeAll();
        try {
            Utilvista.cargarComboAsignaturaContrato(contratoControl.getContratos(), cbxAsignatura);
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
        Object p = lstMatriculaAsignatura.getSelectedValue();
        MatriculaAsignatura estudiante = (MatriculaAsignatura) p;
        tutoriaMatrControl.getTutoriaMatricula().setImpartida(true);
        tutoriaMatrControl.getTutoriaMatricula().setMatriculaAsignatura_ID(estudiante.getId());
        tutoriaMatrControl.getTutoriaMatricula().setTutoria_ID(tutoriaControl.getTutoria().getId());
        tutoriaMatrControl.getTutoriaMatriculas().add(tutoriaMatrControl.getTutoriaMatricula());
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
        jPanel2 = new javax.swing.JPanel();
        btAsignarEstudiante1 = new javax.swing.JButton();
        btRemoverEstudiante = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstMatriculaAsignatura = new javax.swing.JList<>();
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
        jLabel6.setText("Asignatura:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(cbxHorario, 0, 162, Short.MAX_VALUE)
                            .addComponent(txtTema))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 410, 230));

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

        lstMatriculaAsignatura.setBackground(new java.awt.Color(212, 173, 107));
        lstMatriculaAsignatura.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstMatriculaAsignatura.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lstMatriculaAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        lstMatriculaAsignatura.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Jose Roberto Alcachofa Tercero", "Pepe Roberto Alcachofa Segundo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(lstMatriculaAsignatura);

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
        bg.add(btCrearTutoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, -1, 30));

        btDescartar.setBackground(new java.awt.Color(212, 173, 107));
        btDescartar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btDescartar.setForeground(new java.awt.Color(102, 51, 0));
        btDescartar.setText("Descartar");
        btDescartar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.add(btDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 110, 30));

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
            Logger.getLogger(FrmNuevaTutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearTutoria1ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmNuevaTutoria().setVisible(true);
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
    private javax.swing.JButton btDescartar;
    private javax.swing.JButton btRemoverEstudiante;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxHorario;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> lstEstudiantesAsignados;
    private javax.swing.JList<String> lstMatriculaAsignatura;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
