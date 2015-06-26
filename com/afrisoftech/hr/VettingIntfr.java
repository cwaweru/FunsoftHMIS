/*
 * DefaultInternalFrame.java
 *
 * Created on May 31, 2004, 1:53 PM
 */

package com.afrisoftech.hr;

/**
 *
 * @author  postgres
 */
public class VettingIntfr extends javax.swing.JInternalFrame {
    
    java.sql.Connection connectDB = null;
    
    javax.swing.table.TableModel tableModel = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public VettingIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        try {
            nBCachedRowSet1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet2 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            nBCachedRowSet3 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
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
        helpPanel = new javax.swing.JPanel();
        helpAction = new javax.swing.JButton();
        subMainPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldsPanel = new javax.swing.JPanel();
        indicatoridLbl = new javax.swing.JLabel();
        indicatornameLbl = new javax.swing.JLabel();
        qualifiableamountLbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3111 = new javax.swing.JLabel();
        facilityidLbl11611 = new javax.swing.JLabel();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jCheckBox212 = new javax.swing.JCheckBox();
        jCheckBox211 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jLabel311112 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("RECRUITMENT PROCESSING");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
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
        clearAction.setText("Close");
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
        buttonPanel.setLayout(new java.awt.GridBagLayout());

        buttonPanel.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel1.setText("VETTING");
        buttonPanel.add(jLabel1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        subMainPanel.add(buttonPanel, gridBagConstraints);

        fieldsPanel.setLayout(new java.awt.GridBagLayout());

        fieldsPanel.setBorder(new javax.swing.border.TitledBorder("Data manipulation utility"));
        indicatoridLbl.setText("Educational Qualifications");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(indicatoridLbl, gridBagConstraints);

        indicatornameLbl.setText("Professional Qualifications");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(indicatornameLbl, gridBagConstraints);

        qualifiableamountLbl.setText("Experiences");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(qualifiableamountLbl, gridBagConstraints);

        jLabel3.setText("Searching Criteria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jTextField1, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "File Id", "First Name", "Second Name", "Last Name"
            }
        ));
        jTable1.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        fieldsPanel.add(jScrollPane1, gridBagConstraints);

        jButton1.setMnemonic('F');
        jButton1.setText("Save to Excel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jButton1, gridBagConstraints);

        jButton2.setMnemonic('P');
        jButton2.setText("Apply Advanced Vetting");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jButton2, gridBagConstraints);

        jButton3.setMnemonic('N');
        jButton3.setText("Save to Database");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jButton3, gridBagConstraints);

        jButton4.setMnemonic('L');
        jButton4.setText("Print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jButton4, gridBagConstraints);

        jLabel3111.setText("Requisition No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jLabel3111, gridBagConstraints);

        facilityidLbl11611.setText(" Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(facilityidLbl11611, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(datePicker2, gridBagConstraints);

        jCheckBox212.setText("Withdrawn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jCheckBox212, gridBagConstraints);

        jCheckBox211.setText("Closed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jCheckBox211, gridBagConstraints);

        jCheckBox21.setText("Filled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jCheckBox21, gridBagConstraints);

        jLabel311112.setText("Requisition Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jLabel311112, gridBagConstraints);

        jTextField2.setText("search resume");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jTextField2, gridBagConstraints);

        jTextField21.setText("search resume");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jTextField21, gridBagConstraints);

        jTextField22.setText("search resume");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jTextField22, gridBagConstraints);

        jTextField23.setText("search resume");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        fieldsPanel.add(jTextField23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 20.0;
        subMainPanel.add(fieldsPanel, gridBagConstraints);

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

        setBounds(0, 0, 631, 312);
    }//GEN-END:initComponents
    
    private void helpActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpActionActionPerformed
    
    private void clearActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionActionPerformed
    
    private void deleteActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionActionPerformed
    
    private void updateActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionActionPerformed
    
    private void newActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionActionPerformed
        try {
            
            connectDB.setAutoCommit(false);
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_recruit_vetting (searching_cri,edu_qualif,experiences,prof_qualif,file_id,first_name,second_name,last_name,req_no,date,req_status) values(?,?,?,?,?,?,?,?,?,?,?)");
            for (int i = 0; i < jTable1.getRowCount(); i++){
                if (jTable1.getModel().getValueAt(i,0) != null){
                    pstmt.setObject(1,jTextField1.getText());
                    pstmt.setObject(2,jTextField2.getText());
                    pstmt.setObject(3,jTextField23.getText());
                    pstmt.setObject(4,jTextField22.getText());
                    pstmt.setObject(5,jTable1.getValueAt(i,0).toString());
                    pstmt.setObject(6,jTable1.getValueAt(i,1).toString());
                    pstmt.setObject(7,jTable1.getValueAt(i,2).toString());
                    pstmt.setObject(8,jTable1.getValueAt(i,3).toString());
                    pstmt.setObject(9,jTextField21.getText());
                    pstmt.setObject(10,datePicker2.getDate());
                    pstmt.setBoolean(11,jCheckBox21.isSelected());
                    
                    pstmt.executeUpdate();
                    
                }
            }
            
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        // TODO add your handling code here:       // TODO add your handling code here:
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
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox211;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField22;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet2;
    private javax.swing.JCheckBox jCheckBox212;
    private javax.swing.JButton clearAction;
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel spacerPanel;
    private javax.swing.JLabel indicatornameLbl;
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JLabel jLabel311112;
    private javax.swing.JButton newAction;
    private javax.swing.JLabel indicatoridLbl;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton updateAction;
    private javax.swing.JTextField jTextField23;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet3;
    private javax.swing.JLabel facilityidLbl11611;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JLabel qualifiableamountLbl;
    private javax.swing.JPanel subMainPanel;
    private javax.swing.JTextField jTextField2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3111;
    private javax.swing.JButton deleteAction;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton helpAction;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}
