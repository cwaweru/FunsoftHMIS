/*
 * DefaultInternalFrame.java
 *
 * Created on May 31, 2004, 1:53 PM
 */

package com.afrisoftech.laboratory;

/**
 *
 * @author  postgres
 */
public class FpContraceptivesIntfr extends javax.swing.JInternalFrame {
    
    java.sql.Connection connectDB = null;
    
    javax.swing.table.TableModel tableModel = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    /** Creates new form DefaultInternalFrame */
    public FpContraceptivesIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        labelPanel = new javax.swing.JPanel();
        imgPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        actionsPanel = new javax.swing.JPanel();
        newAction = new javax.swing.JButton();
        updateAction = new javax.swing.JButton();
        deleteAction = new javax.swing.JButton();
        clearAction = new javax.swing.JButton();
        spacerPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        helpPanel = new javax.swing.JPanel();
        helpAction = new javax.swing.JButton();
        subMainPanel = new javax.swing.JPanel();
        fieldsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.afrisoftech.dbadmin.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        dataPanel = new javax.swing.JPanel();
        firstAction = new javax.swing.JButton();
        previousAction = new javax.swing.JButton();
        nextAction = new javax.swing.JButton();
        lastAction = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Family Planning Contraceptives Setup");
        setVisible(true);
        mainPanel.setLayout(new java.awt.GridBagLayout());

        labelPanel.setLayout(new java.awt.GridBagLayout());

        labelPanel.setBorder(new javax.swing.border.TitledBorder(""));
        imgPanel.setLayout(new java.awt.GridBagLayout());

        imgPanel.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        imgPanel.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel.add(imgPanel, gridBagConstraints);

        actionsPanel.setLayout(new java.awt.GridBagLayout());

        actionsPanel.setBorder(new javax.swing.border.TitledBorder("Actions"));
        newAction.setMnemonic('w');
        newAction.setText("New");
        newAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newActionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(newAction, gridBagConstraints);

        updateAction.setMnemonic('U');
        updateAction.setText("Update");
        updateAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(updateAction, gridBagConstraints);

        deleteAction.setMnemonic('D');
        deleteAction.setText("Delete");
        deleteAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(deleteAction, gridBagConstraints);

        clearAction.setMnemonic('C');
        clearAction.setText("Clear");
        clearAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(clearAction, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        actionsPanel.add(spacerPanel, gridBagConstraints);

        jButton1.setMnemonic('o');
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        labelPanel.add(actionsPanel, gridBagConstraints);

        helpPanel.setLayout(new java.awt.GridBagLayout());

        helpPanel.setBorder(new javax.swing.border.TitledBorder("Assistance"));
        helpAction.setMnemonic('H');
        helpAction.setText("Help");
        helpAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionActionPerformed(evt);
            }
        });

        helpPanel.add(helpAction, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        labelPanel.add(helpPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(labelPanel, gridBagConstraints);

        subMainPanel.setLayout(new java.awt.GridBagLayout());

        subMainPanel.setBorder(new javax.swing.border.TitledBorder(""));
        fieldsPanel.setLayout(new java.awt.GridBagLayout());

        fieldsPanel.setBorder(new javax.swing.border.TitledBorder(""));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Description", "Cycle", "Qty", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 50.0;
        fieldsPanel.add(jScrollPane1, gridBagConstraints);

        jLabel3.setText("FP Method");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        fieldsPanel.add(jLabel3, gridBagConstraints);

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct fp_method from fp_methods order by fp_method"));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        fieldsPanel.add(jComboBox1, gridBagConstraints);

        jLabel4.setText("Method Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        fieldsPanel.add(jLabel4, gridBagConstraints);

        jTextField1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        fieldsPanel.add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel.add(fieldsPanel, gridBagConstraints);

        dataPanel.setLayout(new java.awt.GridBagLayout());

        dataPanel.setBorder(new javax.swing.border.TitledBorder("Data navagation actions"));
        firstAction.setMnemonic('F');
        firstAction.setText("First");
        firstAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionActionPerformed(evt);
            }
        });

        dataPanel.add(firstAction, new java.awt.GridBagConstraints());

        previousAction.setMnemonic('P');
        previousAction.setText("Previous");
        previousAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionActionPerformed(evt);
            }
        });

        dataPanel.add(previousAction, new java.awt.GridBagConstraints());

        nextAction.setMnemonic('N');
        nextAction.setText("Next");
        nextAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionActionPerformed(evt);
            }
        });

        dataPanel.add(nextAction, new java.awt.GridBagConstraints());

        lastAction.setMnemonic('L');
        lastAction.setText("Last");
        lastAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionActionPerformed(evt);
            }
        });

        dataPanel.add(lastAction, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(dataPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(subMainPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);

        setBounds(0, 0, 566, 300);
    }//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
            try {
            java.sql.Statement stmt = connectDB.createStatement();
            java.sql.ResultSet rset = stmt.executeQuery("select fp_method_code from fp_methods where fp_method ='"+jComboBox1.getSelectedItem()+"'");
            while (rset.next()){
                jTextField1.setText(rset.getObject(1).toString());
            }
            rset.close();
            stmt.close();
            //  conn.close();
            
            
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("Insert not successful");
        }    // Add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void helpActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpActionActionPerformed
    
    private void clearActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionActionPerformed
      for (int k = 0; k < jTable1.getRowCount(); k++ ) {
            for (int r = 0; r < jTable1.getColumnCount(); r++ ) {
                jTable1.getModel().setValueAt(null,k,r);
            }
        }     // TODO add your handling code here:
    }//GEN-LAST:event_clearActionActionPerformed
    
    private void deleteActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionActionPerformed
    
    private void updateActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionActionPerformed
    
    private void newActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionActionPerformed
       String patientAcc = null;
       String AccDesc = null;
        try {
            connectDB.setAutoCommit(false);
             java.sql.Statement stm12 = connectDB.createStatement();
            
            java.sql.ResultSet rse12 = stm12.executeQuery("select code,activity from pb_activity where activity_category ='IF'");
              while (rse12.next()){
                
                patientAcc = rse12.getObject(1).toString();
                AccDesc = rse12.getObject(2).toString();
            }
            for (int i = 0; i < jTable1.getRowCount(); i++){
                if (jTable1.getModel().getValueAt(i,0) != null){
                    java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into fp_contraceptives values(?,?,initcap(?),?,?,?,?,?)");
                    pstmt1.setObject(1,jTextField1.getText());
                    pstmt1.setObject(2,jTable1.getValueAt(i,0));
                    pstmt1.setObject(3,jTable1.getValueAt(i,1));
                    pstmt1.setObject(4,jTable1.getValueAt(i,2));
                    pstmt1.setObject(5,jTable1.getValueAt(i,3));
                    pstmt1.setObject(6,jTable1.getValueAt(i,4));
                    pstmt1.setObject(7,patientAcc);
                    pstmt1.setObject(8,jComboBox1.getSelectedItem());
                    pstmt1.executeUpdate();
                    
                }
                
            }
            connectDB.commit();
            connectDB.setAutoCommit(true);
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Done Successfully","Comfirmation",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(sq.getMessage());
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_newActionActionPerformed
    
    private void lastActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastActionActionPerformed
    
    private void nextActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextActionActionPerformed
    
    private void previousActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_previousActionActionPerformed
    
    private void firstActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstActionActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton nextAction;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton clearAction;
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel spacerPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JButton firstAction;
    private javax.swing.JButton newAction;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton updateAction;
    private javax.swing.JButton previousAction;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JPanel subMainPanel;
    private javax.swing.JButton deleteAction;
    private javax.swing.JButton lastAction;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton helpAction;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}

