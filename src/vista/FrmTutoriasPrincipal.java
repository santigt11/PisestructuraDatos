package vista;

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
    
    private void cargarTutoriasLista(DynamicList<TutoriaMatricula> tutoriasM) throws EmptyException{
        Utilvista.cargarListaTutoriaMatricula(tutoriasM, lstTutorias);
    }
    
    private void cargarTutorias() throws EmptyException {
        personaControl.setPersona(personaControl.buscarBinaria("dni", "1101201301"));
        contratoControl.setContratos(contratoControl.buscarLineal(contratoControl.all(), "DNIDocente", String.valueOf(personaControl.getPersona().getDni())));
        Contrato contratos[] = contratoControl.getContratos().toArray();
        Contrato contrato;
        DynamicList<Asignatura> asignaturas= new DynamicList<>();
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
        jPanel1 = new javax.swing.JPanel();
        btNuevaTutoria = new javax.swing.JButton();
        btGenerarInforme = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTutorias = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtAsignatura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTema = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chkSi = new javax.swing.JCheckBox();
        chkNo = new javax.swing.JCheckBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(88, 156, 20));

        btNuevaTutoria.setBackground(new java.awt.Color(242, 242, 242));
        btNuevaTutoria.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btNuevaTutoria.setForeground(new java.awt.Color(0, 0, 0));
        btNuevaTutoria.setText("Nueva Tutoria");
        btNuevaTutoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btGenerarInforme.setBackground(new java.awt.Color(242, 242, 242));
        btGenerarInforme.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btGenerarInforme.setForeground(new java.awt.Color(0, 0, 0));
        btGenerarInforme.setText("Generar Informe");
        btGenerarInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGenerarInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerarInformeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(505, Short.MAX_VALUE)
                .addComponent(btNuevaTutoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btGenerarInforme)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNuevaTutoria)
                    .addComponent(btGenerarInforme))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PROXIMAS TUTORIAS");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 750, -1));

        jScrollPane1.setBorder(null);

        lstTutorias.setBackground(new java.awt.Color(242, 242, 242));
        lstTutorias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 210, 280));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel4.setText("Asignatura:");

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));

        txtAsignatura.setBackground(new java.awt.Color(242, 242, 242));
        txtAsignatura.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        txtAsignatura.setText("Estructura de Datos");
        txtAsignatura.setBorder(null);
        txtAsignatura.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtAsignatura.setEnabled(false);
        txtAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsignaturaActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel2.setText("Tema:");

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));

        txtTema.setBackground(new java.awt.Color(242, 242, 242));
        txtTema.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTema.setForeground(new java.awt.Color(0, 0, 0));
        txtTema.setText("Avance PIS");
        txtTema.setBorder(null);
        txtTema.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtTema.setEnabled(false);
        txtTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTemaActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel3.setText("Fecha:");

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));

        txtFecha.setBackground(new java.awt.Color(242, 242, 242));
        txtFecha.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(0, 0, 0));
        txtFecha.setText("25 de diciembre, 2023");
        txtFecha.setBorder(null);
        txtFecha.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtFecha.setEnabled(false);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel5.setText("¿Ha recibido la tutoria?");

        chkSi.setBackground(new java.awt.Color(242, 242, 242));
        chkSi.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        chkSi.setText("Si");
        chkSi.setBorder(null);
        chkSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSiActionPerformed(evt);
            }
        });

        chkNo.setBackground(new java.awt.Color(242, 242, 242));
        chkNo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        chkNo.setText("No");
        chkNo.setBorder(null);
        chkNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAsignatura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTema, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(chkSi)
                                .addGap(21, 21, 21)
                                .addComponent(chkNo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSi)
                    .addComponent(chkNo))
                .addGap(65, 65, 65))
        );

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 480, 280));
        bg.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGenerarInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerarInformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btGenerarInformeActionPerformed

    private void txtTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTemaActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsignaturaActionPerformed

    private void chkSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSiActionPerformed

    private void chkNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNoActionPerformed

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
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTutoriasPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btGenerarInforme;
    private javax.swing.JButton btNuevaTutoria;
    private javax.swing.JCheckBox chkNo;
    private javax.swing.JCheckBox chkSi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lstTutorias;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
