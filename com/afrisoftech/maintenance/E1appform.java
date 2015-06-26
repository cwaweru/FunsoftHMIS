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
import org.netbeans.lib.sql.pool.PooledConnection;
import org.netbeans.lib.sql.pool.PooledConnectionSource;

/**
 *
 * @author charlie
 */
public class E1appform extends Interface_Alpha {
    JComponent[] arrComponents;
      public Connection connDb;
    public PooledConnectionSource pconnDB;
    
    /**
     * Creates new form MachineSetup
     */
     public E1appform(Connection cd,PooledConnectionSource pc) {
        setVisible(true);
         setDBCOnnection(cd,pc);
        initComponents(); 
        uuid = "E1appform";
         //Load the buildings
        cboAsset = loadData(cboAsset, this.uuid);
        
        cboDepartments = loadData(cboDepartments, this.uuid);
       
        cboUrgency = loadData(cboUrgency, this.uuid);
        tblReport = loadData(tblReport, this.uuid, "tblReport");
        
        arrComponents = new JComponent[10];
        arrComponents[0] = cboAsset;
        arrComponents[1] = txtFloor;
        arrComponents[2] = cboDepartments;
        arrComponents[3] = cboUrgency;
        arrComponents[4] = tblReport;
        arrComponents[5] = txaDescription;
        arrComponents[6] = txtRoom;
        arrComponents[7] = txtPhoneext;
        arrComponents[8] = txtSubject;
       
        /*try {
         initComponents();
         dbConn mpya=new dbConn();
         Connection conn=mpya.connector();
         machineCbx.removeAllItems();
         String sql="select asset_name from public.asset_registration WHERE make LIKE 'laundry'";
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery( sql );
         while ( rs.next() ) {
         machineCbx.addItem(rs.getString("asset_name"));
         }
         } catch (SQLException ex) {
         Logger.getLogger(MachineSetup.class.getName()).log(Level.SEVERE, null, ex);
         }*/
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
        cboUrgency = new javax.swing.JComboBox();
        jLabel161 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        cboDepartments = new javax.swing.JComboBox();
        jLabel164 = new javax.swing.JLabel();
        cboAsset = new javax.swing.JComboBox();
        txtFloor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRoom = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        txtSubject = new javax.swing.JTextField();
        txtPhoneext = new javax.swing.JTextField();
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
        setTitle("E1 Application Form");
        setMinimumSize(new java.awt.Dimension(776, 517));
        setPreferredSize(new java.awt.Dimension(776, 517));
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
        jLabel16.setText("Room:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel16, gridBagConstraints);

        cboUrgency.setMinimumSize(new java.awt.Dimension(150, 20));
        cboUrgency.setName("cboUrgency"); // NOI18N
        cboUrgency.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(cboUrgency, gridBagConstraints);

        jLabel161.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel161.setText("Floor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel161, gridBagConstraints);

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setText("Urgency:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel81, gridBagConstraints);

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setText("Phone Ext:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel82, gridBagConstraints);

        cboDepartments.setMinimumSize(new java.awt.Dimension(150, 20));
        cboDepartments.setName("cboDepartments"); // NOI18N
        cboDepartments.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(cboDepartments, gridBagConstraints);

        jLabel164.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel164.setText("Building:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel164, gridBagConstraints);

        cboAsset.setMinimumSize(new java.awt.Dimension(150, 20));
        cboAsset.setName("cboAsset"); // NOI18N
        cboAsset.setPreferredSize(new java.awt.Dimension(150, 20));
        cboAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAssetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(cboAsset, gridBagConstraints);

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
        jPanel11.add(txtFloor, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Subject:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel10, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Description:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel9, gridBagConstraints);

        txtRoom.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtRoom.setMinimumSize(new java.awt.Dimension(150, 20));
        txtRoom.setName("txtRoom"); // NOI18N
        txtRoom.setPreferredSize(new java.awt.Dimension(150, 20));
        txtRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(txtRoom, gridBagConstraints);

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        txaDescription.setName("txaDescription"); // NOI18N
        jScrollPane1.setViewportView(txaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jScrollPane1, gridBagConstraints);

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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel11.add(txtSubject, gridBagConstraints);

        txtPhoneext.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtPhoneext.setMinimumSize(new java.awt.Dimension(150, 20));
        txtPhoneext.setName("txtPhoneext"); // NOI18N
        txtPhoneext.setPreferredSize(new java.awt.Dimension(150, 20));
        txtPhoneext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(txtPhoneext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel7.add(jPanel11, gridBagConstraints);

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
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
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
 
        String[][] dataToSave = new String[9][2];
        dataToSave[0][0] = this.uuid;
        dataToSave[0][1] = "1"; //Change to the id of the user who is currently logged in.
        
        dataToSave[1][0] = "cboAsset";
        dataToSave[1][1] = cboAsset.getSelectedItem().toString();

        dataToSave[2][0] = "txtSubject";
        dataToSave[2][1] = txtSubject.getText().toString();
        
        dataToSave[3][0] = "txtRoom";
        dataToSave[3][1] = txtRoom.getText().toString();
        
        dataToSave[4][0] = "txaDescription";
        dataToSave[4][1] = txaDescription.getText().toString();
        
        dataToSave[5][0] = "cboDepartments";
        dataToSave[5][1] = cboDepartments.getSelectedItem().toString();
        
        dataToSave[6][0] = "txtPhoneext";
        dataToSave[6][1] = txtPhoneext.getText().toString(); 
        
        dataToSave[7][0] = "cboUrgency";
        dataToSave[7][1] = cboUrgency.getSelectedItem().toString();
        
        dataToSave[8][0] = "txtFloor";
        dataToSave[8][1] = txtFloor.getText().toString();
        
       
       
        String phonetext = txtRoom.getText().toString();
        String desctext = txaDescription.getText().toString();
        String subjecttext = txtFloor.getText().toString();


        if (phonetext.equals("") || desctext.equals("") || subjecttext.equals("")) {
            JOptionPane.showMessageDialog(null, "Please input all fields");

        } else {
            saveApplication(dataToSave);
            tblReport = loadData(tblReport, this.uuid, "tblReport");
            JOptionPane.showMessageDialog(null, "Data Saved");
            
            txtRoom.setText("");
            txaDescription.setText("");
            txtFloor.setText("");
            txtPhoneext.setText("");
            txtSubject.setText("");
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblReportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMousePressed
        // TODO add your handling code here:
        //tblReport = loadData(tblReport, this.uuid, "tblReport");
    }//GEN-LAST:event_tblReportMousePressed

    private void tblReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMouseClicked
        // TODO add your handling code here:
        //updateInterface(tblReport, arrComponents);
    }//GEN-LAST:event_tblReportMouseClicked

    private void txtFloorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFloorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFloorActionPerformed

    private void txtRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoomActionPerformed

    private void cboAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAssetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAssetActionPerformed

    private void txtSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubjectActionPerformed

    private void txtPhoneextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneextActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            txtRoom.setText("");
            txaDescription.setText("");
            txtFloor.setText("");
            txtPhoneext.setText("");
            txtSubject.setText("");
                    // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboAsset;
    private javax.swing.JComboBox cboDepartments;
    private javax.swing.JComboBox cboUrgency;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
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
    private javax.swing.JTable tblReport;
    private javax.swing.JTextArea txaDescription;
    private javax.swing.JTextField txtFloor;
    private javax.swing.JTextField txtPhoneext;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JTextField txtSubject;
    // End of variables declaration//GEN-END:variables
}
