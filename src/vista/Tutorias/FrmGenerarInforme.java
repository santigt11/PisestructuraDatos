package vista.Tutorias;

public class FrmGenerarInforme extends javax.swing.JFrame {

    public FrmGenerarInforme() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btGenerar = new javax.swing.JButton();
        cbxAsignatura = new javax.swing.JComboBox<>();
        cbxHorario = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTutorias = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        txtTotalHoras = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(88, 156, 20));

        btGenerar.setBackground(new java.awt.Color(242, 242, 242));
        btGenerar.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btGenerar.setText("Generar");
        btGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerarActionPerformed(evt);
            }
        });

        cbxAsignatura.setBackground(new java.awt.Color(242, 242, 242));
        cbxAsignatura.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxAsignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estructura de Datos" }));
        cbxAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cbxHorario.setBackground(new java.awt.Color(242, 242, 242));
        cbxHorario.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 AM - 5 PM" }));
        cbxHorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btGenerar)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGenerar)
                    .addComponent(cbxAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 50));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORME");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 520, -1));

        jScrollPane1.setBorder(null);

        lstTutorias.setBackground(new java.awt.Color(242, 242, 242));
        lstTutorias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lstTutorias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Avance Pis", "Revision de la Proporcion Poblacional", "Distribucion de la Media Muestral" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstTutorias);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 300, 280));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Total de Horas:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, 20));

        txtTotalHoras.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTotalHoras.setText("1180");
        txtTotalHoras.setBorder(null);
        txtTotalHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHorasActionPerformed(evt);
            }
        });
        bg.add(txtTotalHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 30, 20));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 40, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> lstTutorias;
    private javax.swing.JTextField txtTotalHoras;
    // End of variables declaration//GEN-END:variables
}
