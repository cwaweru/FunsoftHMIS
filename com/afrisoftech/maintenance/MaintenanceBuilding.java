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
public class MaintenanceBuilding extends javax.swing.JInternalFrame {

    /**
     * Creates new form MachineSetup
     */
    public MaintenanceBuilding() {
        super();
        setVisible(true);
        initComponents();
        /*try {
           
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
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Building Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel4, gridBagConstraints);

        jLabel6.setText("Building Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel6, gridBagConstraints);

        jLabel8.setText("Building Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jLabel8, gridBagConstraints);

        jTextField2.setMinimumSize(new java.awt.Dimension(150, 20));
        jTextField2.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jTextField2, gridBagConstraints);

        jTextField3.setMinimumSize(new java.awt.Dimension(156, 170));
        jTextField3.setPreferredSize(new java.awt.Dimension(156, 200));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jTextField3, gridBagConstraints);

        jTextField4.setPreferredSize(new java.awt.Dimension(150, 20));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jTextField4, gridBagConstraints);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     /*   try {

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

        } */                          // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
