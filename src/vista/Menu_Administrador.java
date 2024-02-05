/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import modelo.Usuario;
import prepis.sSlide;

/**
 *
 * @author Usuario
 */
public class Menu_Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Menu_Administrador
     */
    //variable global
    private Usuario user;
    private sSlide slide;
    private JPanel panel1;
    private JPanel panel2;

    private FrmGenerarInforme frmGenerarInforme;
    private FrmTutoriasPrincipal frmTutoriasPrincipal;

    // constructor con parámetros
    public Menu_Administrador(Usuario usuario) {
        initComponents();
        this.user = usuario;
                inicializarPaneles();
    }

    // constructor
    public Menu_Administrador() {
        initComponents();
       
        slide = new sSlide();
               inicializarPaneles();
    }

   private void inicializarPaneles() {
    panel1 = new javax.swing.JPanel();
    panel2 = new javax.swing.JPanel();
    this.setLocationRelativeTo(null);
    slide = new sSlide();
    // FrmGenerarInforme frm = new FrmGenerarInforme(); // Esto lo comentamos
    // panel2.add(frm, java.awt.BorderLayout.CENTER); // Esto lo comentamos

    // Puedes mostrar el formulario directamente
    FrmGenerarInforme frm = new FrmGenerarInforme();
    frm.setVisible(true);
    panel2.add(frm.getContentPane(), java.awt.BorderLayout.CENTER);

    mostrarPanel(panel2);  // Mostrar el panel2 por defecto
}

    // Método para mostrar un panel específico
    private void mostrarPanel(JPanel panel) {
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        circleComponent1 = new com.jgoodies.animation.components.CircleComponent();
        Panel2 = new javax.swing.JPanel();
        Panel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnDocentes = new org.edisoncor.gui.button.ButtonTransluceIcon();
        btnEstudiantes = new org.edisoncor.gui.button.ButtonTransluceIcon();
        BtnMatricula = new org.edisoncor.gui.button.ButtonTransluceIcon();
        btnPeriodoAcademicos = new org.edisoncor.gui.button.ButtonTransluceIcon();
        btnAdministrarAsignaturas = new org.edisoncor.gui.button.ButtonTransluceIcon();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Exit");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 62, 0, 0));
        jPanel2.getAccessibleContext().setAccessibleName("");
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(circleComponent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, -1, -1));

        Panel2.setBackground(new java.awt.Color(255, 255, 255));
        Panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 1110, 670));

        Panel1.setBackground(new java.awt.Color(151, 133, 95));
        Panel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Panel1.setForeground(new java.awt.Color(23, 61, 40));

        jLabel8.setBackground(new java.awt.Color(23, 61, 40));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(23, 61, 40));
        jLabel8.setText("ADMINISTRAR DOCENTES");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 61, 40));
        jLabel4.setText("ADMINISTRAR ESTUDIANTES");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 61, 40));
        jLabel3.setText("ADMINISTRAR MATRICULAS");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 61, 40));
        jLabel1.setText("ADMINISTRAR PERIODOS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 61, 40));
        jLabel2.setText("ACADEMICOS");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(23, 61, 40));
        jLabel5.setText("ADMINISTRAR ASIGNATURAS");

        btnDocentes.setBackground(new java.awt.Color(203, 222, 188));
        btnDocentes.setForeground(new java.awt.Color(255, 255, 204));
        btnDocentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/45109776-suscriptores-seguidores-usuarios-icono-vector-también-image-can-utilizarse-para-admin-salpicadero_3_-removebg-preview.png"))); // NOI18N
        btnDocentes.setText("buttonTransluceIcon1");
        btnDocentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocentesActionPerformed(evt);
            }
        });

        btnEstudiantes.setBackground(new java.awt.Color(203, 222, 188));
        btnEstudiantes.setForeground(new java.awt.Color(255, 255, 204));
        btnEstudiantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/communities-line-green-and-black-icon-vector-removebg-preview.png"))); // NOI18N
        btnEstudiantes.setText("buttonTransluceIcon1");
        btnEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudiantesActionPerformed(evt);
            }
        });

        BtnMatricula.setBackground(new java.awt.Color(203, 222, 188));
        BtnMatricula.setForeground(new java.awt.Color(255, 255, 204));
        BtnMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/circulo-con-una-persona-y-una-marca-de-verificacion-verde-2k2fm87-removebg-preview.png"))); // NOI18N
        BtnMatricula.setText("buttonTransluceIcon1");
        BtnMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMatriculaActionPerformed(evt);
            }
        });

        btnPeriodoAcademicos.setBackground(new java.awt.Color(203, 222, 188));
        btnPeriodoAcademicos.setForeground(new java.awt.Color(255, 255, 204));
        btnPeriodoAcademicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/images_1_-removebg-preview.png"))); // NOI18N
        btnPeriodoAcademicos.setText("buttonTransluceIcon1");
        btnPeriodoAcademicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodoAcademicosActionPerformed(evt);
            }
        });

        btnAdministrarAsignaturas.setBackground(new java.awt.Color(203, 222, 188));
        btnAdministrarAsignaturas.setForeground(new java.awt.Color(255, 255, 204));
        btnAdministrarAsignaturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pngtree-flat-green-laptop-icon-with-computer-screen-answer-fill-form-vector-png-image_12719274.png"))); // NOI18N
        btnAdministrarAsignaturas.setText("buttonTransluceIcon1");
        btnAdministrarAsignaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrarAsignaturasActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Screenshot_2024-02-04_153540-removebg-preview.png"))); // NOI18N
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Usuario");

        jLabel9.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel1Layout.createSequentialGroup()
                        .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDocentes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPeriodoAcademicos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdministrarAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Panel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addContainerGap(13, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18))))
                            .addGroup(Panel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(Panel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDocentes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPeriodoAcademicos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdministrarAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(167, 167, 167))
        );

        getContentPane().add(Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 280, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDocentesActionPerformed

    private void btnPeriodoAcademicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoAcademicosActionPerformed
    if (frmGenerarInforme == null) {
        frmGenerarInforme = new FrmGenerarInforme();
    }

    Panel2.setLayout(new BorderLayout());
    Panel2.removeAll();  // Elimina cualquier componente existente en Panel2
    Panel2.add(frmGenerarInforme.getContentPane(), BorderLayout.CENTER);
    Panel2.revalidate();
    Panel2.repaint();
    }//GEN-LAST:event_btnPeriodoAcademicosActionPerformed

    private void BtnMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMatriculaActionPerformed
   
    }//GEN-LAST:event_BtnMatriculaActionPerformed

    private void btnEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudiantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstudiantesActionPerformed

    private void btnAdministrarAsignaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarAsignaturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdministrarAsignaturasActionPerformed

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
            java.util.logging.Logger.getLogger(Menu_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Administrador().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTransluceIcon BtnMatricula;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private org.edisoncor.gui.button.ButtonTransluceIcon btnAdministrarAsignaturas;
    private org.edisoncor.gui.button.ButtonTransluceIcon btnDocentes;
    private org.edisoncor.gui.button.ButtonTransluceIcon btnEstudiantes;
    private org.edisoncor.gui.button.ButtonTransluceIcon btnPeriodoAcademicos;
    private com.jgoodies.animation.components.CircleComponent circleComponent1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

    private void mostrarGif() {
    }
}
