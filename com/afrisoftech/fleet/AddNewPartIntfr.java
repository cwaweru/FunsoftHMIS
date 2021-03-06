/*
 * AddNewPartIntfr.java
 *
 * Created on February 21, 2005, 9:52 PM
 */

package com.afrisoftech.fleet;

/**
 *
 * @author  Administrator
 */
public class AddNewPartIntfr extends javax.swing.JInternalFrame {
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    /** Creates new form AddNewPartIntfr */
    public AddNewPartIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
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

        try {
            reset1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            reset2 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        okbtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Enablechbox = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        reset1.setCommand("select company_name from fleet_schema.insurance_company order by company_name");
        reset1.setConnectionSource(pConnDB);
        reset2.setCommand("select part_cat from fleet_schema.category order by part_cat");
        reset2.setConnectionSource(pConnDB);
        reset2.setPassword("02a586f7b2c81abcda5a6231e867f2080700072b081f657319", true);
        try {
            reset2.setUrl("jdbc:postgresql://localhost:5432/fleet");
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        reset2.setUsername("postgres");

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setTitle("Add New Part");
        setVisible(true);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Parts Identification"));
        jLabel1.setText("Part #");
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        jLabel2.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Vender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Bin #");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setText("% Markup %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Base Cost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setText("Warranty");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        jPanel1.add(jLabel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField2, gridBagConstraints);

        jComboBox1.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (reset1, "company_name", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (reset2, "part_cat", null, null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jComboBox2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 90.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("Reorder Point");
        jLabel11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel11, gridBagConstraints);

        jLabel12.setText("Reorder Qty");
        jLabel12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel12, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Dialog", 2, 12));
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("To adjust inventory levels, click on the grid");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel14, gridBagConstraints);

        jTextField9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jTextField9, gridBagConstraints);

        jTextField11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jTextField11, gridBagConstraints);

        jTextField12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jTextField12, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Receipts for this part"));
        jScrollPane1.setEnabled(false);
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "On Hand", "Unit Cost", "Date Rcvd", "Vendor"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        okbtn.setText("Ok");
        okbtn.setEnabled(false);
        okbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okbtnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(okbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        jLabel13.setText("Lead Time");
        jLabel13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel13, gridBagConstraints);

        Enablechbox.setText("Enable Inventory Tracking");
        Enablechbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnablechboxActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(Enablechbox, gridBagConstraints);

        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 50.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel7, gridBagConstraints);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        setBounds(0, 0, 553, 513);
    }//GEN-END:initComponents
    
    private void okbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okbtnActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_okbtnActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String selecdk = null;
        
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into fleet_schema.newpart values( ?,?,?,?,?,?,?,?,?,?)");
            if(jTextField1.getText().equals("")){
                javax.swing.JOptionPane.showMessageDialog(this, "You must enter Part Number","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }else{
                pstmt.setString(1,jTextField1.getText());
            }
            pstmt.setString(2,jTextField2.getText());
            pstmt.setString(3,jTextField3.getText());
            pstmt.setString(4,jComboBox1.getSelectedItem().toString());
            pstmt.setString(5,jComboBox2.getSelectedItem().toString());
            pstmt.setString(6,jTextField4.getText());
            pstmt.setString(7,jTextField7.getText());
            pstmt.setString(8,jTextField8.getText());
            pstmt.setString(9,jTextField5.getText());
            pstmt.setString(10,jTextField6.getText());
            
            pstmt.executeUpdate();
             
            
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            System.out.println(sq.getMessage());
            sq.printStackTrace();
        }
        
         
        
        if (Enablechbox.isSelected()){
            try {
                java.sql.PreparedStatement pstmt56 = connectDB.prepareStatement("update fleet_schema.newpart set reorder_point = '"+jTextField11.getText()+"',reoder_qty = '"+jTextField9.getText()+"',lead_time = '"+jTextField12.getText()+"' where part_no ='"+jTextField1.getText()+"'");
                    pstmt56.executeUpdate();
               
                
            }catch (java.sql.SQLException sqd){
                javax.swing.JOptionPane.showMessageDialog(this,sqd.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                
                System.out.println(sqd.getMessage());
                sqd.printStackTrace();
            }
        }
        
        
         
        
        if (Enablechbox.isSelected()){
            try {
                for(int i = 0; i< jTable1.getRowCount(); i++) {
                    if (jTable1.getModel().getValueAt(i,0) != null){
                         java.sql.PreparedStatement pstmt3 = connectDB.prepareStatement("update fleet_schema.newpart set on_hand = '"+jTable1.getModel().getValueAt(i,0).toString()+"',unit_cost = '"+jTable1.getModel().getValueAt(i,1).toString()+"',date_rcvd = '"+jTable1.getModel().getValueAt(i,2).toString()+"',vendor = '"+jTable1.getModel().getValueAt(i,3).toString()+"' where part_no ='"+jTextField1.getText()+"'");
                    pstmt3.executeUpdate();
                        
                    }
                }
            }catch (java.sql.SQLException sqdd){
                javax.swing.JOptionPane.showMessageDialog(this,sqdd.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                
                System.out.println(sqdd.getMessage());
                sqdd.printStackTrace();
            }
        }
       
   
                
                
                
                
                
                // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
            
    private void EnablechboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnablechboxActionPerformed
        if (Enablechbox.isSelected()){
            jLabel11.setEnabled(true);
            jLabel12.setEnabled(true);
            jLabel13.setEnabled(true);
            jTextField11.setEnabled(true);
            jTextField9.setEnabled(true);
            jTextField12.setEnabled(true);
            jTable1.setEnabled(true);
            okbtn.setVisible(true);
        }else {
            jLabel11.setEnabled(false);
            jLabel12.setEnabled(false);
            jLabel13.setEnabled(false);
            jTextField11.setEnabled(false);
            jTextField9.setEnabled(false);
            jTextField12.setEnabled(false);
            jTable1.setEnabled(false);
            okbtn.setVisible(false);
            
        }
        // Add your handling code here:
    }//GEN-LAST:event_EnablechboxActionPerformed
    
    private void EneblechboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EneblechboxActionPerformed
        
        
        
        // Add your handling code here:
    }//GEN-LAST:event_EneblechboxActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jButton2;
    private org.netbeans.lib.sql.NBCachedRowSet reset1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton okbtn;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JCheckBox Enablechbox;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jTextField1;
    private org.netbeans.lib.sql.NBCachedRowSet reset2;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
    
        }
