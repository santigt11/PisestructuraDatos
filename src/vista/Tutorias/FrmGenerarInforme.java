package vista.Tutorias;

public class FrmGenerarInforme extends javax.swing.JFrame {

    public FrmGenerarInforme() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTutorias = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        txtTotalHoras = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btGenerar = new javax.swing.JButton();
        cbxHorario = new javax.swing.JComboBox<>();
        cbxAsignatura = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 250, 205));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 39, 34));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORME ESTUDIANTE ");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 260, -1));

        jScrollPane1.setBorder(null);

        lstTutorias.setBackground(new java.awt.Color(102, 51, 0));
        lstTutorias.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstTutorias.setFont(new java.awt.Font("Franklin Gothic Book", 1, 15)); // NOI18N
        lstTutorias.setForeground(new java.awt.Color(255, 255, 255));
        lstTutorias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Avance Pis", "Revision de la Proporcion Poblacional", "Distribucion de la Media Muestral" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstTutorias);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 440, 330));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Hora Tutoria");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 20));

        txtTotalHoras.setBackground(new java.awt.Color(212, 173, 107));
        txtTotalHoras.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        txtTotalHoras.setForeground(new java.awt.Color(0, 0, 0));
        txtTotalHoras.setText("1180");
        txtTotalHoras.setBorder(null);
        txtTotalHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHorasActionPerformed(evt);
            }
        });
        bg.add(txtTotalHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 50, 20));

        jSeparator1.setBackground(new java.awt.Color(58, 39, 34));
        jSeparator1.setForeground(new java.awt.Color(58, 39, 34));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 80, 10));

        btGenerar.setBackground(new java.awt.Color(212, 173, 107));
        btGenerar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        btGenerar.setForeground(new java.awt.Color(102, 51, 0));
        btGenerar.setText("Generar");
        btGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerarActionPerformed(evt);
            }
        });
        bg.add(btGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 460, 90, 30));

        cbxHorario.setBackground(new java.awt.Color(212, 173, 107));
        cbxHorario.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxHorario.setForeground(new java.awt.Color(58, 39, 34));
        cbxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 AM - 5 PM" }));
        cbxHorario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 204), null, null));
        cbxHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.add(cbxHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 180, 30));

        cbxAsignatura.setBackground(new java.awt.Color(212, 173, 107));
        cbxAsignatura.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        cbxAsignatura.setForeground(new java.awt.Color(58, 39, 34));
        cbxAsignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estructura de Datos" }));
        cbxAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.add(cbxAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 180, 30));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Total de Horas:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, 20));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Total de Horas:");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, 20));

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Total de Horas:");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, 20));

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Asignatura");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btGenerarActionPerformed

    private void txtTotalHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHorasActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGenerarInforme().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btGenerar;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> lstTutorias;
    private javax.swing.JTextField txtTotalHoras;
    // End of variables declaration//GEN-END:variables
}
