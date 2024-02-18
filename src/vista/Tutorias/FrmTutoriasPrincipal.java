package vista.Tutorias;

import controlador.Academico.AsignaturaArchivos;
import controlador.Academico.CarreraArchivos;
import controlador.Academico.ContratoArchivos;
import controlador.Academico.FacultadArchivos;
import controlador.Academico.MallaArchivos;
import controlador.Admin.PersonaArchivos;
import controlador.Matriculas.MatriculaAsignaturaArchivos;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Tutorias.TutoriaArchivos;
import controlador.Tutorias.TutoriaMatriculaArchivos;
import java.time.format.DateTimeFormatter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;
import modelo.Asignatura;
import modelo.Contrato;
import modelo.MatriculaAsignatura;
import modelo.Persona;
import modelo.Tutoria;
import modelo.TutoriaMatricula;
import vista.listas.util.Utilvista;

public class FrmTutoriasPrincipal extends javax.swing.JFrame {

    private TutoriaMatriculaArchivos fileTutoriaM = new TutoriaMatriculaArchivos();
    private TutoriaArchivos fileTutoria = new TutoriaArchivos();
    private MatriculaAsignaturaArchivos fileMatriculaAsg = new MatriculaAsignaturaArchivos();
    private static PersonaArchivos personaControl = new PersonaArchivos();
    private FacultadArchivos fileFacultad = new FacultadArchivos();
    private CarreraArchivos fileCarrera = new CarreraArchivos();
    private MallaArchivos fileMalla = new MallaArchivos();
    private AsignaturaArchivos fileAsignatura = new AsignaturaArchivos();
    private ContratoArchivos contratoControl = new ContratoArchivos();
    private AsignaturaArchivos asignaturaControl = new AsignaturaArchivos();
    private MatriculaAsignaturaArchivos matriculaAsignaturaControl = new MatriculaAsignaturaArchivos();

    public FrmTutoriasPrincipal() {
        initComponents();
    }

    public static void cargarDocente(Persona persona) {
        personaControl.setPersona(persona);
    }

    private void limpiar() {
        txtAsignatura.setText("");
        txtTema.setText("");
        txtFecha.setText("");
        chkSi.setSelected(false);
        chkNo.setSelected(false);
    }

    private void cargarTutoriasLista(DynamicList<TutoriaMatricula> tutoriasM) throws EmptyException {
        Utilvista.cargarListaTutoriaMatricula(tutoriasM, lstTutorias);
    }

    private void cargarTutorias() throws EmptyException {
        personaControl.setPersona(personaControl.buscarBinaria("dni", "1101201301"));
        contratoControl.setContratos(contratoControl.buscarLineal(contratoControl.all(), "DNIDocente", String.valueOf(personaControl.getPersona().getDni())));
        Contrato contratos[] = contratoControl.getContratos().toArray();
        Contrato contrato;
        DynamicList<Asignatura> asignaturas = new DynamicList<>();
        for (int i = 0; i < contratoControl.getContratos().getLength(); i++) {
            contrato = contratoControl.get(contratos[i].getId());
            asignaturas.add(asignaturaControl.buscarBinaria("codigo", contrato.getAsignatura_CODIGO()));
        }
        Asignatura asignaturasArray[] = asignaturaControl.getAsignaturas().toArray();
        Asignatura asignatura;
        DynamicList<MatriculaAsignatura> matriculasAsignaturas = new DynamicList<>();
        for (int i = 0; i < asignaturaControl.getAsignaturas().getLength(); i++) {
            asignatura = asignaturaControl.get(asignaturasArray[i].getId());
            matriculasAsignaturas.add(matriculaAsignaturaControl.buscarBinaria("codigo", asignatura.getCodigo()));
        }
        MatriculaAsignatura tMatriculas[] = matriculaAsignaturaControl.getAsgMatriculas().toArray();
        TutoriaMatricula tMatricula;
        DynamicList<TutoriaMatricula> tutoriasMatricula = new DynamicList<>();
        for (int i = 0; i < matriculaAsignaturaControl.getAsgMatriculas().getLength(); i++) {
            tMatricula = fileTutoriaM.get(tMatriculas[i].getId());
            tutoriasMatricula.add(tMatricula);
        }
    }

    private void cargarVista(TutoriaMatricula tutoriaAsg) throws EmptyException {
        fileTutoriaM.setTutoria(tutoriaAsg);
        MatriculaAsignatura matriculaAsg = fileMatriculaAsg.buscarBinaria("id", fileTutoriaM.getTutoriaMatricula().getMatriculaAsignatura_ID().toString());
        Asignatura asignatura = fileAsignatura.buscarBinaria("id", matriculaAsg.getAsignatura_Codigo().toString());
        Tutoria tutoria = fileTutoria.buscarBinaria("id", fileTutoriaM.getTutoriaMatricula().getTutoria_ID().toString());

        txtAsignatura.setText(asignatura.getNombre());
        txtTema.setText(tutoria.getTema());
        txtFecha.setText(tutoria.getFecha().format((DateTimeFormatter) Utilvista.FORMATO_FECHA));
        if (fileTutoriaM.getTutoriaMatricula().isImpartida()) {
            chkSi.setSelected(true);
            chkNo.setSelected(false);
        } else {
            chkSi.setSelected(false);
            chkNo.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel5 = new javax.swing.JLabel();
        chkSi = new javax.swing.JCheckBox();
        chkNo = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 250, 205));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PROXIMAS TUTORIAS");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 240, -1));

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

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 250, 205));
        jLabel5.setText("¿Ha recibido la tutoria?");

        chkSi.setBackground(new java.awt.Color(212, 173, 107));
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

        jToggleButton1.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton1.setText("Guardar");

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
                                .addComponent(jToggleButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jToggleButton1))
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
        bg.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 500, -1, -1));

        jComboBox1.setBackground(new java.awt.Color(212, 173, 107));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bg.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 220, -1));

        jButton2.setBackground(new java.awt.Color(102, 51, 0));
        jButton2.setText("Nueva Tutoria");
        bg.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        jButton3.setBackground(new java.awt.Color(102, 51, 0));
        jButton3.setText("Generar Informe");
        bg.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 130, -1));

        jDateChooser1.setBackground(new java.awt.Color(212, 173, 107));
        bg.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lstTutoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstTutoriasMouseClicked
        if (lstTutorias.getSelectedValue() != null) {
            Object t = lstTutorias.getSelectedValue();
            TutoriaMatricula tutoria = (TutoriaMatricula) t;
            try {
                cargarVista(tutoria);
            } catch (EmptyException ex) {

            }
        }
    }//GEN-LAST:event_lstTutoriasMouseClicked

    private void btNuevaTutoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaTutoriaActionPerformed
        // TODO add your handling code here:
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

    public static void main(String args[]) throws UnsupportedLookAndFeelException {

      

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTutoriasPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JCheckBox chkNo;
    private javax.swing.JCheckBox chkSi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JList<String> lstTutorias;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
