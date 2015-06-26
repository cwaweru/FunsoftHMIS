/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.maintenance;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlie
 */
public class MaintenanceEquipment extends Interface_Alpha {

    /**
     * Creates new form MachineSetup
     */
    public MaintenanceEquipment() {
        super();
        setVisible(true);
        initComponents();
        this.uuid = "MaintenanceEquipment";

        //Load the buildings
        System.out.println(this.uuid + " - " + cboBuildings.getName());
        cboBuildings = loadData(cboBuildings, this.uuid, "cboBuildings");
        cboFloors = loadData(cboFloors, this.uuid, "cboFloors");
        cboDepartment = loadData(cboDepartment, this.uuid, "cboDepartment");
        /*String[] arrData = getDefaultValueOf(this.uuid, "cboBuildings");
        int i = 0;
        cboBuildings.removeAllItems();
        while (i < arrData.length && arrData[i] != null) {
            cboBuildings.addItem(arrData[i]);
            i++;
        }*/
        /* try {
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
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        txtEquipmentName = new javax.swing.JTextField();
        cboFloors = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cboDepartment = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cboBuildings = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Add Equipments");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Department Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel8.add(jLabel4, gridBagConstraints);

        jLabel6.setText("Equipment Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel6, gridBagConstraints);

        jLabel8.setText("Building Floor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel8.add(jLabel8, gridBagConstraints);

        txtDescription.setMinimumSize(new java.awt.Dimension(156, 170));
        txtDescription.setPreferredSize(new java.awt.Dimension(156, 200));
        txtDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescriptionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(txtDescription, gridBagConstraints);

        txtEquipmentName.setPreferredSize(new java.awt.Dimension(150, 20));
        txtEquipmentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEquipmentNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(txtEquipmentName, gridBagConstraints);

        cboFloors.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboFloors.setMinimumSize(new java.awt.Dimension(150, 20));
        cboFloors.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(cboFloors, gridBagConstraints);

        jLabel9.setText("Equipment Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel9, gridBagConstraints);

        cboDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDepartment.setMinimumSize(new java.awt.Dimension(150, 20));
        cboDepartment.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(cboDepartment, gridBagConstraints);

        jLabel5.setText("Building Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel5, gridBagConstraints);

        cboBuildings.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboBuildings.setMinimumSize(new java.awt.Dimension(150, 20));
        cboBuildings.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(cboBuildings, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        jPanel7.add(jPanel8, gridBagConstraints);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/all_tracks.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSave.setMnemonic('w');
        btnSave.setText("Save Details");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(btnSave, gridBagConstraints);

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

        getAccessibleContext().setAccessibleName("Add Building");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String[][] dataToSave = new String[6][2];
        dataToSave[0][0] = "MaintenanceEquipment";
        dataToSave[0][1] = "1"; //Change to the id of the user who is currently logged in.
        dataToSave[1][0] = "cboBuildings";
        dataToSave[1][1] = cboBuildings.getSelectedItem().toString();
        dataToSave[2][0] = "cboFloors";
        dataToSave[2][1] = cboFloors.getSelectedItem().toString();
        dataToSave[3][0] = "txtEquipmentName";
        dataToSave[3][1] = txtEquipmentName.getText();
        dataToSave[4][0] = "txtDescription";
        dataToSave[4][1] = txtDescription.getText();
        dataToSave[5][0] = "cboDepartment";
        dataToSave[5][1] = cboDepartment.getSelectedItem().toString();
        saveApplication(dataToSave);
        /*try {

         connectDB.setAutoCommit(false);
         java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hr.hr_allocation_of_roles(staff_id,dept,duty_st,begin_date,design,section,supervisor,shift,role_id,resp,objective,kpi,rmks) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
         for (int i = 0; i < jTable1.getRowCount(); i++){
         if (jTable1.getModel().getValueAt(i,0) != null){

         pstmt.setString(1,jComboBox24.getSelectedItem().toString());
         pstmt.setString(2,jComboBox2.getSelectedItem().toString());
         pstmt.setString(3,jComboBox21.getSelectedItem().toString());
         pstmt.setString(4,datePicker1.getDate().toString());
         pstmt.setString(5,jComboBox14.getSelectedItem().toString());
         pstmt.setString(6,jComboBox1.getSelectedItem().toString());
         pstmt.setString(7,jComboBox11.getSelectedItem().toString());
         pstmt.setObject(8,jCheckBox1.getText());
         pstmt.setString(9,jTable1.getValueAt(i,0).toString());
         pstmt.setString(10,jTable1.getValueAt(i,1).toString());
         pstmt.setString(11,jTable1.getValueAt(i,2).toString());
         pstmt.setString(12,jTable1.getValueAt(i,3).toString());
         pstmt.setString(13,jTable1.getValueAt(i,4).toString());

         pstmt.executeUpdate();
         connectDB.commit();
         connectDB.setAutoCommit(true);
         javax.swing.JOptionPane.showMessageDialog(this, "Data saved successfully","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);

         }
         }

         }   catch(java.sql.SQLException sq){
         javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
         try {
         connectDB.rollback();
         }catch (java.sql.SQLException sql){
         javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
         }

         }         */                        // Add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private void txtDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescriptionActionPerformed
    
    private void txtEquipmentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEquipmentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEquipmentNameActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboBuildings;
    private javax.swing.JComboBox cboDepartment;
    private javax.swing.JComboBox cboFloors;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtEquipmentName;
    // End of variables declaration//GEN-END:variables
}
