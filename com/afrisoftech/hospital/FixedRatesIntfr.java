/*
 * offintfr.java
 *
 * Created on August 13, 2002, 1:55 AM
 */
package com.afrisoftech.hospital;

/**
 *
 * @author root
 */
public class FixedRatesIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form offintfr
     */
    private javax.swing.JComboBox cmbox;
    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    public FixedRatesIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        connectDB = connDb;

        pConnDB = pconnDB;

        initComponents();
        
        for (int k = 0; k < jTable1.getRowCount(); k++) {
                jTable1.getModel().setValueAt(false, k, 6);
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        try{
            java.lang.Class.forName("org.postgresql.Driver");
        }catch (java.lang.ClassNotFoundException sl){
            System.out.println(sl.getMessage());
        }
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Service Charter (Charge Sheets)");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Utopia", 3, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel4, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Set the fixed charge for each service below"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Service Code", "Item/Service", "Normal Rate", "Special Rate", "OtherPrices", "Waiting Time (Mins)", "Direct Cash Payment", "OID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setShowHorizontalLines(false);
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < 6; i++) {
            column = jTable1.getColumnModel().getColumn(i);
            if (i == 1) {

                column.setPreferredWidth(400); //sport column is bigger
            } else {

                //if (i == 2) {
                    column.setPreferredWidth(100);
                    // }  else
                //column.setPreferredWidth(50);

            }
        }
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorMoved(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jLabel1.setText("Depatment Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel1, gridBagConstraints);

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select UPPER(ac.activity) as activity from pb_activity ac where (ac.activity_category ilike 'I%' OR ac.activity_category = 'CA'  OR ac.activity_category = 'CF'  ) ORDER BY AC.ACTIVITY"));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jComboBox1, gridBagConstraints);

        jLabel2.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel2, gridBagConstraints);

        jTextField1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTextField1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton1.setMnemonic('S');
        jButton1.setText("Save");
        jButton1.setToolTipText("Click here to enter data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setMnemonic('E');
        jButton2.setText("Edit");
        jButton2.setToolTipText("click to edit&update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jButton2, gridBagConstraints);

        jButton5.setMnemonic('R');
        jButton5.setText("RemoveRow");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jButton5, gridBagConstraints);

        jButton4.setMnemonic('l');
        jButton4.setText("Clear");
        jButton4.setToolTipText("Click to clear fields");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jButton4, gridBagConstraints);

        jButton6.setMnemonic('d');
        jButton6.setText("Delete");
        jButton6.setToolTipText("Click to clear fields");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jButton6, gridBagConstraints);

        jButton3.setMnemonic('C');
        jButton3.setText("Close");
        jButton3.setToolTipText("click to close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jButton3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1000.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jLabel3.setText("Service Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel3, gridBagConstraints);

        jComboBox2.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-' as nothing UNION ALL select category_description FROM pb_lab_maincategory WHERE department = '"+jComboBox1.getSelectedItem()+"'"));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jComboBox2, gridBagConstraints);

        jLabel5.setText("Last Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jLabel5, gridBagConstraints);

        jTextField2.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jTextField2, gridBagConstraints);

        jTextField3.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jTextField3, gridBagConstraints);

        setBounds(0, 0, 713, 389);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("DELETE from pb_operating_parameters WHERE code = '" + jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString() + "' AND main_service ilike  '" + jComboBox1.getSelectedItem() + "' AND oid = '" + jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 7).toString() + "' ");
            System.err.println("DELETE from pb_operating_parameters WHERE code = '" + jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString() + "' AND main_service ilike  '" + jComboBox1.getSelectedItem() + "' AND oid = '" + jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 7).toString() + "'");
            pstmt31.executeUpdate();

            connectDB.commit();
            connectDB.setAutoCommit(true);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
        jButton2ActionPerformed(evt);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorMoved
        // Add your handling code here:
    }//GEN-LAST:event_jTable1AncestorMoved

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jLabel4.setText("");
        for (int k = 0; k < jTable1.getRowCount(); k++) {
            for (int r = 0; r < jTable1.getColumnCount(); r++) {
                jTable1.getModel().setValueAt(null, k, r);
            }
        }
        try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.ResultSet rset = stmt.executeQuery("select code,sub_code from pb_activity where activity ilike '" + jComboBox1.getSelectedItem() + "'");
            while (rset.next()) {
                jTextField1.setText(rset.getObject(1).toString());
                jTextField3.setText(rset.getObject(2).toString());

            }
            java.sql.Statement stmtx = connectDB.createStatement();
            java.sql.ResultSet rsetx = stmtx.executeQuery("select code from pb_operating_parameters WHERE main_service ilike '" + jComboBox1.getSelectedItem() + "' AND gl_account = '" + jTextField1.getText() + "' ORDER BY oid DESC LIMIT 1");
            while (rsetx.next()) {
                jTextField2.setText(rsetx.getObject(1).toString());

            }

            jComboBox2.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '-' AS noth UNION ALL SELECT category_description FROM pb_lab_maincategory WHERE department ilike '" + jComboBox1.getSelectedItem() + "'"));

            /*
             * try {
             *
             * crset6.setCommand("select code,activity,null as charge from
             * pb_activity where sub_code ='"+jTextField1.getText()+"'");
             *
             * // crset6.setCommand("select ac.activity,null as charge from
             * pb_activity ac ,pb_sub_activities sa where ac.sub_code =
             * sa.sub_code and sa.description
             * ='"+jComboBox1.getSelectedItem().toString()+"'"); //
             * crset6.setConnectionSource(pConnDB);
             *
             * // try { crset6.execute();
             *
             * // crset2.setExecuteOnLoad(true); jTable1.setModel(new
             * org.netbeans.lib.sql.models.TableModel(crset6, new
             * org.netbeans.lib.sql.models.TableModel.Column[] { new
             * org.netbeans.lib.sql.models.TableModel.Column("code", "GL
             * Account", false), new
             * org.netbeans.lib.sql.models.TableModel.Column("activity",
             * "Activity", false), new
             * org.netbeans.lib.sql.models.TableModel.Column("charge", "Charge",
             * true)
             *
             * }));
             *
             *
             *
             * jScrollPane1.setViewportView(jTable1); // } // }
             */
        } catch (java.sql.SQLException sqlex) {
            javax.swing.JOptionPane.showMessageDialog(this, sqlex.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sqlex.getMessage());

        }

        // Add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.setValueAt(null, jTable1.getSelectedRow(), i);
        }
        /*
         * int rows2Delete = jTable1.getSelectedRowCount();
         *
         * int[] selectedRows = jTable1.getSelectedRows();
         *
         * if (rows2Delete < 1) {
         *
         * java.awt.Toolkit.getDefaultToolkit().beep();
         *
         * javax.swing.JOptionPane.showMessageDialog(this, "There are no
         * selected rows to delete!");
         *
         * } else {
         *
         * if (rows2Delete > 1) {
         *
         * for (int i = 0; i < selectedRows.length; i++) {
         *
         *
         *
         * javax.swing.table.DefaultTableModel defTableModel =
         * (javax.swing.table.DefaultTableModel)jTable1.getModel();
         *
         * defTableModel.removeRow(selectedRows[i]);
         *
         * }
         *
         *
         *
         * } else {
         *
         * javax.swing.table.DefaultTableModel defTableModel =
         * (javax.swing.table.DefaultTableModel)jTable1.getModel();
         *
         * defTableModel.removeRow(jTable1.getSelectedRow()); }
    }
         */

        // Add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
    private void cmboxActionPerformed(java.awt.event.ActionEvent evt) {
        int i = jTable1.getSelectedRow();






    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(false);  // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        for (int k = 0; k < jTable1.getRowCount(); k++) {
            for (int r = 0; r < jTable1.getColumnCount(); r++) {
                jTable1.getModel().setValueAt(null, k, r);
            }
        }
        jButton1.setLabel("Save");
// Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        for (int k = 0; k < jTable1.getRowCount(); k++) {
            for (int r = 0; r < jTable1.getColumnCount(); r++) {
                jTable1.getModel().setValueAt(null, k, r);
            }
        }
        int i = 0;
        jTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT code,service_type,"
                + "rate as general,anaesthetist_rate as special_rate, other_prices, waiting_time,direct_cash_payment,oid FROM "
                + "pb_operating_parameters WHERE gl_account ILIKE '" + jTextField1.getText() + "' ORDER BY service_type"));
        jButton1.setText("Update");  // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql = new java.sql.Date(dateNow);

        System.out.println(datenowSql.toString());
        int j = 0;
        // java.lang.Object name = "false";
        try {
            connectDB.setAutoCommit(false);


            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if (jTable1.getModel().getValueAt(i, 1) != null) {

                    if (jButton1.getText().equalsIgnoreCase("Update")) {
                        java.sql.PreparedStatement pstmt311 = connectDB.prepareStatement("UPDATE pb_operating_parameters SET service_type =  ? ,rate = ? ,anaesthetist_rate = ?, waiting_time = ?, other_prices = ?,direct_cash_payment = ? WHERE code = ? AND gl_account = ?");
                        pstmt311.setString(1, jTable1.getValueAt(i, 1).toString());
                        pstmt311.setDouble(2, java.lang.Double.valueOf(jTable1.getValueAt(i, 2).toString()));
                        pstmt311.setDouble(3, java.lang.Double.valueOf(jTable1.getValueAt(i, 3).toString()));
                        pstmt311.setBoolean(6, Boolean.valueOf(jTable1.getValueAt(i, 6).toString()));
                        pstmt311.setString(7, jTable1.getValueAt(i, 0).toString());
                        pstmt311.setString(8, jTextField1.getText());
                        pstmt311.setDouble(4, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                        pstmt311.setDouble(5, java.lang.Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                         //pstmt311.setDouble(7, java.lang.Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                        pstmt311.executeUpdate();


                    } else {
                        java.sql.PreparedStatement stmtTable11 = connectDB.prepareStatement("SELECT count(service_type)  FROM pb_operating_parameters WHERE code ilike  ? and rate = ? AND anaesthetist_rate = ?");
                        stmtTable11.setString(1, jTable1.getValueAt(i, 0).toString());
                        stmtTable11.setDouble(2, java.lang.Double.valueOf(jTable1.getValueAt(i, 2).toString()));
                        stmtTable11.setDouble(3, java.lang.Double.valueOf(jTable1.getValueAt(i, 3).toString()));
                        java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery();

                        while (rsetTable11.next()) {
                            j = rsetTable11.getInt(1);
                        }
                        if (j <= 0) {
                            System.out.println("Registering new products/services into charge sheets.");
                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO pb_operating_parameters VALUES (initcap(?),?,UPPER(?),?,?,?,?,?,?,?,?,?,?,?,?,?)");

                            if (jTable1.getModel().getValueAt(i, 0) == null) {

                                javax.swing.JOptionPane.showMessageDialog(this, "Enter service type", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {
                                pstmt.setObject(1, jTable1.getValueAt(i, 1).toString());
                            }
                            pstmt.setDouble(2, java.lang.Double.valueOf(jTable1.getValueAt(i, 2).toString()));

                            pstmt.setObject(3, jComboBox1.getSelectedItem().toString());
                            pstmt.setDate(4, datenowSql);

                            pstmt.setObject(5, jTextField1.getText());
                            pstmt.setDouble(6, java.lang.Double.valueOf(jTable1.getValueAt(i, 3).toString()));
                            pstmt.setObject(7, jTable1.getValueAt(i, 0).toString());
                            pstmt.setObject(8, "");
                            pstmt.setObject(9, jComboBox2.getSelectedItem());
                            pstmt.setObject(10, jTextField3.getText());
                            pstmt.setDouble(11, java.lang.Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                            pstmt.setDouble(12, java.lang.Double.valueOf(jTable1.getValueAt(i, 2).toString()));
                            pstmt.setDate(13, null);
                            pstmt.setObject(14, "Active");
                            pstmt.setDouble(15, 0.00);
                            pstmt.setDouble(16, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                            pstmt.execute();//teUpdate();
                        }
                    }
                }
            }
            jButton1.setText("Save");
            connectDB.commit();
            connectDB.setAutoCommit(true);

            jLabel4.setForeground(java.awt.Color.blue);
            jLabel4.setText("Insert successful");
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            jLabel4.setForeground(java.awt.Color.red);
            jLabel4.setText("Sorry.insert not successful");
        } // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}