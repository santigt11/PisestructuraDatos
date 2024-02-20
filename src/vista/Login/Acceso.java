/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Login;

import controlador.Admin.PersonaBD;
<<<<<<< HEAD
import controlador.Login.UsuarioDB;
=======
import controlador.Login.UsuarioBD;
>>>>>>> Steven-Luna
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import static java.lang.reflect.Array.set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import modelo.Persona;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class Acceso extends javax.swing.JFrame {

    /**
     * Creates new form Acceso
     */
   
    public Acceso() {

        initComponents();
        //jPanel1.setBackground(Color.BLACK);
        //this.getContentPane().setBackground(new Color(255,255,255));
       mostarGif();
    }
    void mostarGif() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1122, 670);

        // Crea un JLabel y carga el GIF
        ImageIcon gifIcon = new ImageIcon("src/imagenes/GIF.gif"); // Reemplaza con la ruta de tu archivo GIF
        JLabel backgroundLabel = new JLabel(gifIcon);

        // Establece el layout del JFrame como BorderLayout
        setLayout(new BorderLayout());

        // Agrega el JLabel al fondo del JFrame
        getContentPane().add(backgroundLabel, BorderLayout.CENTER);

        // Hacer visible el JFrame
        setVisible(true);
    }

    //creacion de instancia de los controladores
    private PersonaBD controlPersona = new PersonaBD();
<<<<<<< HEAD
    private UsuarioDB controlUsuario = new UsuarioDB();
=======
    private UsuarioBD controlUsuario = new UsuarioBD();
>>>>>>> Steven-Luna
    
    // limpiar
    private void limpiar() {
        txtContraseña.setText("");
        txtNombreUsuario.setText("");

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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnRecuperarClave = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnInicio = new org.edisoncor.gui.button.ButtonRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/photo4987874516348807274.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 470));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setEnabled(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 250, 205));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreUsuario.setBackground(new java.awt.Color(255, 250, 205));
        txtNombreUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreUsuario.setActionCommand("<Not Set>");
        txtNombreUsuario.setBorder(null);
        txtNombreUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 242, 40));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 61, 40));
        jLabel2.setText("Nombre del Usuario:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 61, 40));
        jLabel4.setText("Contraseña:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 61, 40));
        jLabel3.setText("INICIAR SESIÓN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jCheckBox1.setBackground(new java.awt.Color(255, 250, 205));
        jCheckBox1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(23, 61, 40));
        jCheckBox1.setText("Recuérdame");
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 344, -1, -1));

        btnRecuperarClave.setBackground(new java.awt.Color(255, 255, 255));
        btnRecuperarClave.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRecuperarClave.setForeground(new java.awt.Color(51, 51, 51));
        btnRecuperarClave.setText("Recuperar Contraseña");
        btnRecuperarClave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRecuperarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarClaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnRecuperarClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 160, 20));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 51, -1, 133));

        txtContraseña.setBackground(new java.awt.Color(255, 250, 205));
        txtContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtContraseña.setBorder(null);
        jPanel2.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 240, 40));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 310, 30));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 326, 320, 12));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/candado-removebg-preview(1)(1).png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 70, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mujer(1)(1).png"))); // NOI18N
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 60, 60));

        btnInicio.setBackground(new java.awt.Color(102, 51, 0));
        btnInicio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInicio.setText("Iniciar Sesion");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jPanel2.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 190, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        if (!txtNombreUsuario.getText().isEmpty() && !String.valueOf(txtContraseña.getPassword()).isEmpty()) {
            Usuario user= null;
            try {
                user = controlUsuario.autenticarse(txtNombreUsuario.getText(), String.valueOf(txtContraseña.getPassword()));
                //System.out.println(user.getClave());
                //System.out.println(user.getCorreo());
            } catch (EmptyException ex) {
                java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //controlUsuario.getPerson(user.getId_Persona())
            //Actualiza el usuario en el controlador de usuarios
            controlUsuario.setUsuario(user);
            Persona persona = null;//
            try {
                //buscar persona por medio del dni
            persona = controlPersona.buscarBinaria("dni", user.getPersona_DNI());                //System.out.println(persona.getNombre().toString());
            } catch (EmptyException ex) {
                //Manejo de excepciones si no se encuentra la persona
                java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
            if (persona != null) {
                //System.out.println(persona.getRol().getName());
                // Si se encontró la persona, verifica su rol
                if (null != user.getRol()) {
                    switch ( user.getRol()) {
                        case ADMINISTRADOR -> {
                            //Inicia sesión como administrador y muestra un mensaje
                            Menu_Administrador menuAdmi = new Menu_Administrador(user,persona);
                            menuAdmi.setVisible(true);
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso como ADMINISTRADOR!");
                        }
                        case DOCENTE -> {// Abrir Frm_Main_Docente
                            Menu_Docente menuDoc = new Menu_Docente(user,persona);
                            menuDoc.setVisible(true);
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso como DOCENTE!");
                        }
                        case ESTUDIANTE -> {// Abrir Frm_Main_Estudiante.
                            Menu_Estudiante menuEstu = new Menu_Estudiante(user,persona);
                            menuEstu.setVisible(true);
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso como ESTUDIANTE!");
                        }
                        default -> {
                        }
                    }
                } else {
                    System.out.println("No se pudo identificar el rol");
                }
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Rellene todos los campos");
        }
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnRecuperarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarClaveActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnRecuperarClaveActionPerformed

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
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                               
            }
        });
          SwingUtilities.invokeLater(() -> new Acceso().mostarGif());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRect btnInicio;
    private javax.swing.JButton btnRecuperarClave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables

}
