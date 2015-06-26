/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.maintenance;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.netbeans.lib.sql.pool.PooledConnectionSource;

/**
 *
 * @author charlie
 */
public class CompletionBoss extends Interface_Alpha {
    JComponent[] arrComponents;
     public Connection connDb;
    public PooledConnectionSource pconnDB;
    
    /**
     * Creates new form MachineSetup
     */
    public CompletionBoss(Connection cd,PooledConnectionSource pc) {
        setVisible(true);
         setDBCOnnection(cd,pc);
        initComponents();
        uuid = "CompletionBoss";
        
         //Load the buildings
       
        lblSatisfaction.setText("");
        lblMaintenanceOfficial.setText("");
       
        
        tblReport = loadData(tblReport, this.uuid, "tblReport");
        
      arrComponents = new JComponent[10];
        arrComponents[0] = txtBuilding;
        arrComponents[1] = txtDepartment;
        arrComponents[2] = txtFloor;
        arrComponents[3] = txtSubject;
        arrComponents[4] = lblSatisfaction;
        arrComponents[5] = tblReport;
        arrComponents[6] = txaComplainantDescription;  
        arrComponents[7] = txaWorkerDescription;
        arrComponents[8] = lblMaintenanceOfficial;
  
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        txtBuilding = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaComplainantDescription = new javax.swing.JTextArea();
        txtSubject = new javax.swing.JTextField();
        txtFloor = new javax.swing.JTextField();
        txtDepartment = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        lblSatisfaction = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaWorkerDescription = new javax.swing.JTextArea();
        lblMaintenanceOfficial = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReport = new com.afrisoftech.dbadmin.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(" Boss Completion Process");
        setMinimumSize(new java.awt.Dimension(849, 517));
        setName("Completion"); // NOI18N
        setPreferredSize(new java.awt.Dimension(849, 517));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Department:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel8, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Satisfactory Level:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel11.add(jLabel16, gridBagConstraints);

        jLabel161.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel161.setText("Floor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel161, gridBagConstraints);

        jLabel164.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel164.setText("Building:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel11.add(jLabel164, gridBagConstraints);

        txtBuilding.setEditable(false);
        txtBuilding.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtBuilding.setMinimumSize(new java.awt.Dimension(23, 18));
        txtBuilding.setName("txtBuilding"); // NOI18N
        txtBuilding.setPreferredSize(new java.awt.Dimension(28, 20));
        txtBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuildingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel11.add(txtBuilding, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Subject:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Complainant Description:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jLabel11, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(180, 96));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(175, 96));

        txaComplainantDescription.setEditable(false);
        txaComplainantDescription.setColumns(20);
        txaComplainantDescription.setRows(5);
        txaComplainantDescription.setMinimumSize(new java.awt.Dimension(164, 94));
        txaComplainantDescription.setName("txaComplainantDescription"); // NOI18N
        jScrollPane3.setViewportView(txaComplainantDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jScrollPane3, gridBagConstraints);

        txtSubject.setEditable(false);
        txtSubject.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtSubject.setMinimumSize(new java.awt.Dimension(150, 20));
        txtSubject.setName("txtSubject"); // NOI18N
        txtSubject.setPreferredSize(new java.awt.Dimension(150, 20));
        txtSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubjectActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        jPanel11.add(txtSubject, gridBagConstraints);

        txtFloor.setEditable(false);
        txtFloor.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtFloor.setMinimumSize(new java.awt.Dimension(150, 20));
        txtFloor.setName("txtFloor"); // NOI18N
        txtFloor.setPreferredSize(new java.awt.Dimension(150, 20));
        txtFloor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFloorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        jPanel11.add(txtFloor, gridBagConstraints);

        txtDepartment.setEditable(false);
        txtDepartment.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtDepartment.setMinimumSize(new java.awt.Dimension(150, 20));
        txtDepartment.setName("txtDepartment"); // NOI18N
        txtDepartment.setPreferredSize(new java.awt.Dimension(150, 20));
        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        jPanel11.add(txtDepartment, gridBagConstraints);

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setText("Official's Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel11.add(jLabel83, gridBagConstraints);

        lblSatisfaction.setText("2");
        lblSatisfaction.setName("lblSatisfaction"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(lblSatisfaction, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Official's Description:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel11.add(jLabel9, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(175, 96));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(175, 96));

        txaWorkerDescription.setEditable(false);
        txaWorkerDescription.setColumns(20);
        txaWorkerDescription.setRows(5);
        txaWorkerDescription.setMinimumSize(new java.awt.Dimension(164, 94));
        txaWorkerDescription.setName("txaWorkerDescription"); // NOI18N
        jScrollPane1.setViewportView(txaWorkerDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jScrollPane1, gridBagConstraints);

        lblMaintenanceOfficial.setText("James Opio");
        lblMaintenanceOfficial.setName("lblMaintenanceOfficial"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(lblMaintenanceOfficial, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel7.add(jPanel11, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(19, 200));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(19, 200));

        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblReport.setMinimumSize(new java.awt.Dimension(300, 64));
        tblReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReportMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblReportMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblReport);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        jPanel7.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel7, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton1.setMnemonic('w');
        jButton1.setText("Save Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jButton1, gridBagConstraints);

        jButton4.setMnemonic('C');
        jButton4.setText("Clear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jButton4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 30.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Assistance"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton5.setMnemonic('H');
        jButton5.setText("Help");
        jPanel6.add(jButton5, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMousePressed
        // TODO add your handling code here:
        //tblReport = loadData(tblReport, this.uuid, "tblReport");
    }//GEN-LAST:event_tblReportMousePressed

    private void tblReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMouseClicked
        // TODO add your handling code here:
     
            updateInterface(tblReport, arrComponents);
        
       
    }//GEN-LAST:event_tblReportMouseClicked

    private void txtBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuildingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuildingActionPerformed

    private void txtSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubjectActionPerformed

    private void txtFloorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFloorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFloorActionPerformed

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMaintenanceOfficial;
    private javax.swing.JLabel lblSatisfaction;
    private javax.swing.JTable tblReport;
    private javax.swing.JTextArea txaComplainantDescription;
    private javax.swing.JTextArea txaWorkerDescription;
    private javax.swing.JTextField txtBuilding;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtFloor;
    private javax.swing.JTextField txtSubject;
    // End of variables declaration//GEN-END:variables
}
