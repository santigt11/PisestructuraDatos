package vista.Academico;

import controlador.Academico.AsignaturaBD;
import controlador.TDA.listas.Exception.EmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controlador.Academico.ContratoBD;
import controlador.Admin.PersonaBD;
import java.time.ZoneId;
import modelo.*;

import vista.listas.tablas.TablaContrato;
import vista.listas.util.Utilvista;

public class FrmContrato extends javax.swing.JFrame {

    private ContratoBD fileContrato = new ContratoBD();
    private PersonaBD filePersona = new PersonaBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();

    private TablaContrato tc = new TablaContrato();

    private Boolean verificar() {
        return (!txtAsignatura.getText().trim().isEmpty()
                && !txtDni.getText().trim().isEmpty()
                && !dtRegistro.getDate().toString().isEmpty()
                && !dtCulminacion.getDate().toString().isEmpty());
    }

    private void guardar() throws Exception {
        if (verificar()) {
            Object p = lstDocente.getSelectedValue();
            Persona docente = (Persona) p;
            Object a = lstAsignatura.getSelectedValue();
            Asignatura asignatura = (Asignatura) a;
            fileContrato.getContrato().setDniPersona(docente.getDni());
            fileContrato.getContrato().setAsignatura_CODIGO(asignatura.getCodigo());
            fileContrato.getContrato().setFechaRegistro(dtRegistro.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            fileContrato.getContrato().setFechaCulminacion(dtCulminacion.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            fileContrato.persist(fileContrato.getContrato());
            JOptionPane.showMessageDialog(null, "Datos guardados");
            limpiar();
            fileContrato.setContrato(null);
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarVista(Integer var) throws EmptyException {
        switch (var) {
            case 1:
                if (lstDocente.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    limpiarSoft();
                    Object p = lstDocente.getSelectedValue();
                    Persona docente = (Persona) p;
                    txtDni.setText(docente.getDni());
                    txtApellidos.setText(docente.getApellido());
                    txtNombres.setText(docente.getNombre());
                    txtTelefono.setText(docente.getTelefono());
                }
                break;
            case 2:
                if (lstAsignatura.getSelectedValue() != null) {
                    Object a = lstAsignatura.getSelectedValue();
                    Asignatura asignatura = (Asignatura) a;
                    txtAsignatura.setText(asignatura.getNombre());
                }
                break;
            case 3:
                if (tbContrato.getSelectedRow() > -1) {
                    limpiarSoft();
                    Persona docente = filePersona.buscarBinaria("dni", fileContrato.getContrato().getDniPersona());

                    fileContrato.setContrato(tc.getContratos().getInfo(tbContrato.getSelectedRow()));
                    txtDni.setText(docente.getDni());
                    txtApellidos.setText(docente.getApellido());
                    txtNombres.setText(docente.getNombre());
                    txtTelefono.setText(docente.getTelefono());
                    txtAsignatura.setText(fileAsignatura.buscarBinaria("codigo", fileContrato.getContrato().getAsignatura_CODIGO()).getNombre());
                    dtRegistro.setDate(java.sql.Date.valueOf(fileContrato.getContrato().getFechaRegistro()));
                    dtCulminacion.setDate(java.sql.Date.valueOf(fileContrato.getContrato().getFechaCulminacion()));
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void cargarTablaContratos() {
        tc.setContratos(fileContrato.all());
        tbContrato.setModel(tc);
        tbContrato.updateUI();
    }

    private void limpiar() {
        try {
            Utilvista.cargarListaFacultades(lstFacultad);
            Utilvista.cargarListaDocentes(lstDocente);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaContratos();

        limpiarSoft();

        txtDni.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtNombres.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtAsignatura.setEnabled(false);

        Utilvista.limpiarLista(lstCarrera);
        Utilvista.limpiarLista(lstMalla);
        Utilvista.limpiarLista(lstAsignatura);
    }

    private void limpiarSoft() {
        txtDni.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
        txtAsignatura.setText("");
        dtRegistro.setDate(null);
        dtCulminacion.setDate(null);
    }

    /**
     * Creates new form FrmContrato
     */
    public FrmContrato() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jpFCA = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstFacultad = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstCarrera = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstMalla = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstAsignatura = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jpDocentes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDocente = new javax.swing.JList<>();
        jpContrato = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dtRegistro = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        dtCulminacion = new com.toedter.calendar.JDateChooser();
        btCrearContrato = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbContrato = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 51, 0));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Academico Contratos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(435, 435, 435)
                .addComponent(jLabel6)
                .addContainerGap(438, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, -1));

        jpFCA.setBackground(new java.awt.Color(212, 173, 107));

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Facultades");

        jScrollPane8.setPreferredSize(new java.awt.Dimension(262, 130));

        lstFacultad.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstFacultadMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(lstFacultad);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Carreras");

        jScrollPane5.setPreferredSize(new java.awt.Dimension(262, 130));

        lstCarrera.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCarreraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lstCarreraMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(lstCarrera);

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mallas Curriculares");

        jScrollPane7.setPreferredSize(new java.awt.Dimension(262, 130));

        lstMalla.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMallaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(lstMalla);

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Asignaturas");

        jScrollPane6.setPreferredSize(new java.awt.Dimension(262, 130));

        lstAsignatura.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        lstAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(lstAsignatura);

        javax.swing.GroupLayout jpFCALayout = new javax.swing.GroupLayout(jpFCA);
        jpFCA.setLayout(jpFCALayout);
        jpFCALayout.setHorizontalGroup(
            jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFCALayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jpFCALayout.setVerticalGroup(
            jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFCALayout.createSequentialGroup()
                .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpFCALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpFCALayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jpFCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 341, 560, 240));

        jPanel1.setBackground(new java.awt.Color(255, 250, 205));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpDocentes.setBackground(new java.awt.Color(212, 173, 107));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Docentes");

        lstDocente.setFont(new java.awt.Font("Roboto Thin", 0, 13)); // NOI18N
        lstDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDocenteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstDocente);

        javax.swing.GroupLayout jpDocentesLayout = new javax.swing.GroupLayout(jpDocentes);
        jpDocentes.setLayout(jpDocentesLayout);
        jpDocentesLayout.setHorizontalGroup(
            jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDocentesLayout.setVerticalGroup(
            jpDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDocentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jpDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 560, 290));

        jpContrato.setBackground(new java.awt.Color(212, 173, 107));
        jpContrato.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nuevo Contrato");
        jpContrato.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nombres:");
        jpContrato.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 92, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Apellidos:");
        jpContrato.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 65, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("DNI:");
        jpContrato.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 37, -1, 17));

        txtDni.setBackground(new java.awt.Color(35, 35, 35));
        txtDni.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        txtDni.setBorder(null);
        txtDni.setMaximumSize(new java.awt.Dimension(64, 17));
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        jpContrato.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 37, 155, -1));

        txtApellidos.setBackground(new java.awt.Color(35, 35, 35));
        txtApellidos.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        txtApellidos.setBorder(null);
        jpContrato.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 64, 196, -1));

        txtNombres.setBackground(new java.awt.Color(35, 35, 35));
        txtNombres.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        txtNombres.setBorder(null);
        txtNombres.setMaximumSize(new java.awt.Dimension(64, 17));
        jpContrato.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 91, 196, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Telefono:");
        jpContrato.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 119, -1, -1));

        txtTelefono.setBackground(new java.awt.Color(35, 35, 35));
        txtTelefono.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.setMaximumSize(new java.awt.Dimension(64, 17));
        jpContrato.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 118, 155, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Fecha de Registro del Contrato:");
        jpContrato.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 26));

        dtRegistro.setBackground(new java.awt.Color(242, 242, 242));
        dtRegistro.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        jpContrato.add(dtRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 172, 131, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Fecha de Culminacion del Contrato:");
        jpContrato.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 208, -1, 26));

        dtCulminacion.setBackground(new java.awt.Color(242, 242, 242));
        dtCulminacion.setFont(new java.awt.Font("Roboto Light", 0, 11)); // NOI18N
        dtCulminacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtCulminacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dtCulminacionMouseEntered(evt);
            }
        });
        jpContrato.add(dtCulminacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 208, 131, -1));

        btCrearContrato.setBackground(new java.awt.Color(102, 51, 0));
        btCrearContrato.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btCrearContrato.setForeground(new java.awt.Color(255, 255, 255));
        btCrearContrato.setText("Crear Nuevo Contrato");
        btCrearContrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCrearContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearContratoActionPerformed(evt);
            }
        });
        jpContrato.add(btCrearContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 252, -1, 32));

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Contratos:");
        jpContrato.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 296, -1, -1));

        jScrollPane4.setBackground(new java.awt.Color(242, 242, 242));

        tbContrato.setFont(new java.awt.Font("Roboto Thin", 0, 12)); // NOI18N
        tbContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DOCENTE", "ASIGNATURA", "FECHA DE REGISTRO", "FECHA DE CULMINACION"
            }
        ));
        tbContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbContratoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbContrato);

        jpContrato.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 319, 430, 203));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Asignatura:");
        jpContrato.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 146, -1, -1));

        txtAsignatura.setBackground(new java.awt.Color(35, 35, 35));
        txtAsignatura.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        txtAsignatura.setBorder(null);
        txtAsignatura.setMaximumSize(new java.awt.Dimension(64, 17));
        txtAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsignaturaActionPerformed(evt);
            }
        });
        jpContrato.add(txtAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 145, 195, -1));

        jPanel1.add(jpContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 460, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsignaturaActionPerformed

    private void lstCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseClicked
        if (lstCarrera.getSelectedValue() != null) {
            txtAsignatura.setText("");
            Utilvista.limpiarLista(lstMalla);
            Utilvista.limpiarLista(lstAsignatura);
            Object c = lstCarrera.getSelectedValue();
            Carrera carrera = (Carrera) c;
            try {
                Utilvista.cargarListaMallas(lstMalla, carrera);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna carrera disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstCarreraMouseClicked

    private void lstMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMallaMouseClicked
        if (lstMalla.getSelectedValue() != null) {
            txtAsignatura.setText("");
            Utilvista.limpiarLista(lstAsignatura);
            Object m = lstMalla.getSelectedValue();
            MallaCurricular malla = (MallaCurricular) m;
            try {
                Utilvista.cargarListaAsignaturas(lstAsignatura, malla);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna malla curricular disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstMallaMouseClicked

    private void lstFacultadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstFacultadMouseClicked
        if (lstFacultad.getSelectedValue() != null) {
            txtAsignatura.setText("");
            Utilvista.limpiarLista(lstCarrera);
            Utilvista.limpiarLista(lstMalla);
            Utilvista.limpiarLista(lstAsignatura);
            Object f = lstFacultad.getSelectedValue();
            Facultad facultad = (Facultad) f;
            try {
                Utilvista.cargarListaCarreras(lstCarrera, facultad);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmAcademico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna facultad disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstFacultadMouseClicked

    private void lstCarreraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCarreraMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lstCarreraMouseEntered

    private void lstAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstAsignaturaMouseClicked
        if (lstAsignatura.getSelectedValue() != null) {
            txtAsignatura.setText("");
            try {
                cargarVista(2);
            } catch (EmptyException ex) {
                Logger.getLogger(FrmContrato.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna asignatura disponible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lstAsignaturaMouseClicked

    private void btCrearContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearContratoActionPerformed
        try {
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(FrmContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCrearContratoActionPerformed

    private void dtCulminacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtCulminacionMouseClicked


    }//GEN-LAST:event_dtCulminacionMouseClicked

    private void dtCulminacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtCulminacionMouseEntered

    }//GEN-LAST:event_dtCulminacionMouseEntered

    private void lstDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDocenteMouseClicked
        try {
            cargarVista(1);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstDocenteMouseClicked

    private void tbContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbContratoMouseClicked
        try {
            cargarVista(3);
        } catch (EmptyException ex) {
            Logger.getLogger(FrmContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbContratoMouseClicked

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
            java.util.logging.Logger.getLogger(FrmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmContrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCrearContrato;
    private com.toedter.calendar.JDateChooser dtCulminacion;
    private com.toedter.calendar.JDateChooser dtRegistro;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPanel jpContrato;
    private javax.swing.JPanel jpDocentes;
    private javax.swing.JPanel jpFCA;
    private javax.swing.JList<String> lstAsignatura;
    private javax.swing.JList<String> lstCarrera;
    private javax.swing.JList<String> lstDocente;
    private javax.swing.JList<String> lstFacultad;
    private javax.swing.JList<String> lstMalla;
    private javax.swing.JTable tbContrato;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
