package vista;

public class FrmNuevaTutoria extends javax.swing.JFrame {

    public FrmNuevaTutoria() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbxAsignatura1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        dcFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        cbxHorario1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtTema1 = new javax.swing.JTextField();
        btDescartar = new javax.swing.JButton();
        btCrearTutoria1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btAsignarEstudiante1 = new javax.swing.JButton();
        btRemoverEstudiante = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstEstudiantes = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstEstudiantesAsignados = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(88, 156, 20));
        jPanel1.setPreferredSize(new java.awt.Dimension(780, 50));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA TUTORIA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, -1));

        jPanel3.setBackground(new java.awt.Color(242, 242, 242));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Asignatura:");

        cbxAsignatura1.setBackground(new java.awt.Color(242, 242, 242));
        cbxAsignatura1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxAsignatura1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estructura de Datos" }));
        cbxAsignatura1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Fecha:");

        dcFecha1.setBackground(new java.awt.Color(242, 242, 242));
        dcFecha1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Horario:");

        cbxHorario1.setBackground(new java.awt.Color(242, 242, 242));
        cbxHorario1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbxHorario1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 AM - 5 PM" }));
        cbxHorario1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tema:");

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));

        txtTema1.setBackground(new java.awt.Color(242, 242, 242));
        txtTema1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTema1.setForeground(new java.awt.Color(0, 0, 0));
        txtTema1.setText("Avance PIS");
        txtTema1.setBorder(null);
        txtTema1.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtTema1.setEnabled(false);
        txtTema1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTema1ActionPerformed(evt);
            }
        });

        btDescartar.setBackground(new java.awt.Color(88, 156, 20));
        btDescartar.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btDescartar.setForeground(new java.awt.Color(255, 255, 255));
        btDescartar.setText("Descartar");
        btDescartar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btCrearTutoria1.setBackground(new java.awt.Color(88, 156, 20));
        btCrearTutoria1.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btCrearTutoria1.setForeground(new java.awt.Color(255, 255, 255));
        btCrearTutoria1.setText("Crear Tutoria");
        btCrearTutoria1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dcFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxAsignatura1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 192, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDescartar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCrearTutoria1)
                .addGap(15, 15, 15))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2)
                            .addComponent(txtTema1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxAsignatura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTema1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCrearTutoria1)
                    .addComponent(btDescartar))
                .addGap(15, 15, 15))
        );

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 450, 220));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        btAsignarEstudiante1.setBackground(new java.awt.Color(88, 156, 20));
        btAsignarEstudiante1.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btAsignarEstudiante1.setForeground(new java.awt.Color(255, 255, 255));
        btAsignarEstudiante1.setText("AsignarEstudiante");
        btAsignarEstudiante1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAsignarEstudiante1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAsignarEstudiante1ActionPerformed(evt);
            }
        });

        btRemoverEstudiante.setBackground(new java.awt.Color(88, 156, 20));
        btRemoverEstudiante.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        btRemoverEstudiante.setForeground(new java.awt.Color(255, 255, 255));
        btRemoverEstudiante.setText("Remover Estudiante");
        btRemoverEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRemoverEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverEstudianteActionPerformed(evt);
            }
        });

        jScrollPane8.setBorder(null);

        lstEstudiantes.setBackground(new java.awt.Color(229, 229, 229));
        lstEstudiantes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstEstudiantes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lstEstudiantes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Jose Roberto Alcachofa Tercero", "Pepe Roberto Alcachofa Segundo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(lstEstudiantes);

        jScrollPane7.setBorder(null);

        lstEstudiantesAsignados.setBackground(new java.awt.Color(229, 229, 229));
        lstEstudiantesAsignados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstEstudiantesAsignados.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAsignarEstudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRemoverEstudiante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btAsignarEstudiante1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btRemoverEstudiante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 450, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAsignarEstudiante1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsignarEstudiante1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAsignarEstudiante1ActionPerformed

    private void txtTema1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTema1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTema1ActionPerformed

    private void btRemoverEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRemoverEstudianteActionPerformed
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
    private javax.swing.JComboBox<String> cbxAsignatura1;
    private javax.swing.JComboBox<String> cbxHorario1;
    private com.toedter.calendar.JDateChooser dcFecha1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<String> lstEstudiantes;
    private javax.swing.JList<String> lstEstudiantesAsignados;
    private javax.swing.JTextField txtTema1;
    // End of variables declaration//GEN-END:variables
}
