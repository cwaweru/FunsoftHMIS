/*
 * loanpymntintfr.java
 *
 * Created on August 13, 2002, 1:09 PM
 */

package com.afrisoftech.fleetman;

/**
 *
 * @author  root
 */
public class TyreIssuingintfr extends javax.swing.JInternalFrame {
     // private  javax.swing.JComboBox cmbox = new javax.swing.JComboBox();
       //  private  javax.swing.JComboBox cmbox1 = new javax.swing.JComboBox();
        private javax.swing.JComboBox cmbox;
       private javax.swing.JComboBox cmbox1;
     //  private javax.swing.JComboBox cmbox2;
       
    /** Creates new form loanpymntintfr */
         java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
  
    
    public TyreIssuingintfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
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

        pconn1 = new org.netbeans.lib.sql.pool.PooledConnectionSource();
        try {
            crset1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            crset2 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            crset3 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            crset4 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            crset5 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            crset7 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try  {
            java.lang.Class.forName("org.postgresql.Driver");
            System.out.println("Found driver");
        } catch(java.lang.ClassNotFoundException cnf){
            System.out.println("driver not found");
        }
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        pconn1.setDatabase("jdbc:postgresql://localhost:5432/hospital");
        pconn1.setDriver("org.postgresql.Driver");
        pconn1.setPassword("026df53565d26dc85980854333e7a090561a5db1c2b93af5ea35320e7eea64", true);
        pconn1.setUsername("postgres");
        crset1.setCommand("select patient_no from admission where discharge = 'false'");
        crset1.setConnectionSource(pconn1);
        crset1.setPassword("029bf5c18cf923b4229e39f2af85df1b4b90d41e4e7b1798e7d05d1ea53b4dc8", true);
        try {
            crset1.setUrl("jdbc:postgresql://localhost:5432/hospital");
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        crset2.setCommand("select type_of_disease from diseases_setup order by type_of_disease ");
        crset2.setConnectionSource(pconn1);
        try {
            crset2.setUrl("jdbc:postgresql://localhost:5432/hospital");
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        crset3.setCommand("select allergy from allergy_setup order by allergy");
        crset3.setConnectionSource(pconn1);
        try {
            crset3.setUrl("jdbc:postgresql://localhost:5432/hospital");
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        crset4.setCommand("select distinct item from issiuing");
        crset4.setConnectionSource(pconn1);
        crset4.setPassword("02bcd200db8c9955f4d90c1279c3d6a2bbbea1bbbcb7a0f111d77cde08c4d0c9b8013a", true);
        crset5.setCommand("select distinct sub_service from service_rates");
        crset5.setConnectionSource(pconn1);
        crset5.setPassword("026954894326b109085324b40a9c116fef61a04e71b135d81cb29d", true);
        crset7.setCommand("select name from doctors");
        crset7.setConnectionSource(pconn1);
        crset7.setPassword("0276cf8e14c66d0e35c11bbf34870a6efb46c39d38e5947c4c", true);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tyre Issuing");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        jButton1.setMnemonic('O');
        jButton1.setText("Ok");
        jButton1.setToolTipText("Click here to enter data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton1, gridBagConstraints);

        jButton2.setMnemonic('E');
        jButton2.setText("Edit");
        jButton2.setToolTipText("Click here to edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton2, gridBagConstraints);

        jButton3.setMnemonic('r');
        jButton3.setText("Refresh");
        jButton3.setToolTipText("Click here to clear fields");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton3, gridBagConstraints);

        jButton4.setMnemonic('C');
        jButton4.setText("Close");
        jButton4.setToolTipText("Click here to close ");
        jButton4.setSelected(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jLabel7, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Vehicle Tag No", "Tyre Size", "Quantity", "Issuing Date", "Issued To", "Old Tyres Returned"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        /*cmbox.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (crset2, "sub_service", null, null, null));
        javax.swing.table.TableColumn s = this.jTable1.getColumn("Service");
        s.setCellEditor(new javax.swing.DefaultCellEditor(cmbox));
        cmbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxActionPerformed(evt);
            }
        });

        cmbox1.setModel(new org.netbeans.lib.sql.models.ComboBoxModel (crset4, "item", null, null, null));
        javax.swing.table.TableColumn m = this.jTable1.getColumn("Drug Prescribed");
        m.setCellEditor(new javax.swing.DefaultCellEditor(cmbox1));
        cmbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxActionPerformed(evt);
            }
        });
        */
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });

        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 20);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        setBounds(0, 0, 747, 474);
    }//GEN-END:initComponents

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    
        float floatCol2 = java.lang.Float.parseFloat(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        
        float floatCol3 = java.lang.Float.parseFloat(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());

        float resVal = floatCol2 * floatCol3;
        
        java.lang.Float resFloat =new java.lang.Float(resVal);
        
        if (jTable1.getSelectedColumn() == 3) {
            
            jTable1.setValueAt(resFloat, jTable1.getSelectedRow(), 6);
            
        }         // Add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased
 /*private void cmbox2ActionPerformed(java.awt.event.ActionEvent evt){
      int i = jTable1.getSelectedRow();
     try {
            java.lang.Class.forName("org.postgresql.Driver");
        } catch(java.lang.ClassNotFoundException nt) {
            System.out.println(nt.getMessage());
        }
     try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
          java.sql.ResultSet rs = pstmt.executeQuery("select * from wardcos where ward = '"+cmbox2.getSelectedItem()+"' and patient_no = '"+jComboBox1.getSelectedItem()+"' group by code,rate,ward,days,patient_no,amount"); 
     while (rs.next())
            //jTextField4.setText(rs.getObject(1).toString());
            jTable1.setValueAt(rs.getObject(2),i,2);
 } catch(java.sql.SQLException sqlex){
        System.out.println(sqlex.getMessage());
 }
  
   
      try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
            java.sql.ResultSet rs = pstmt.executeQuery("select * from wardcost where ward = '"+cmbox2.getSelectedItem()+"' AND patient_no = '"+jComboBox1.getSelectedItem()+"'");
     while (rs.next())
            jTable1.setValueAt(rs.getObject(1),i,2);
 } catch(java.sql.SQLException sqlex){
        System.out.println(sqlex.getMessage());
 }  
       try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
            java.sql.ResultSet rs = pstmt.executeQuery("select * from wardcost where ward = '"+cmbox2.getSelectedItem()+"' AND patient_no = '"+jComboBox1.getSelectedItem()+"'");
     while (rs.next())
            jTable1.setValueAt(rs.getObject(2),i,7);
 } catch(java.sql.SQLException sqlex){
        System.out.println(sqlex.getMessage());
 }
  }    
    */
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    setVisible(false);        // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//     jTextPane1.setText("");
//      jTextField4.setText(""); // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
        private void cmbox1ActionPerformed(java.awt.event.ActionEvent evt){
        
            int i = jTable1.getSelectedRow();
    try {
             java.sql.Statement pstmt = connectDB.createStatement();
          java.sql.ResultSet rs = pstmt.executeQuery("select * from dcost where item = '"+cmbox1.getSelectedItem()+"' group by activity_code,item,selling_price,total"); 
     while (rs.next())
            //jTextField4.setText(rs.getObject(1).toString());
            jTable1.setValueAt(rs.getObject(2),i,5);
 } catch(java.sql.SQLException sqlex){
        System.out.println(sqlex.getMessage());
 }
   try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
            java.sql.ResultSet rs = pstmt.executeQuery("select * from dcost where item = '"+cmbox1.getSelectedItem()+"' group by activity_code,item,selling_price,total");
     while (rs.next())
            jTable1.setValueAt(rs.getObject(3),i,4);
           } catch(java.sql.SQLException sqlex){ 
         sqlex.printStackTrace();
            System.out.println("Insert not successful");
        }  
          
           try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
            java.sql.ResultSet rs = pstmt.executeQuery("select * from dcost where item = '"+cmbox1.getSelectedItem()+"' group by activity_code,item,selling_price,total");
     while (rs.next())
            jTable1.setValueAt(rs.getObject(4),i,7);
           } catch(java.sql.SQLException sqlex){ 
         sqlex.printStackTrace();
            System.out.println("Insert not successful");
        }
    }  
    
    
   
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    //   javax.swing.JFrame diag = new diagnosis();
      //  diag.setVisible(true);  // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    private void cmboxActionPerformed(java.awt.event.ActionEvent evt){
      int i = jTable1.getSelectedRow();
      
      
      try {
            java.lang.Class.forName("org.postgresql.Driver");
        } catch(java.lang.ClassNotFoundException nt) {
            System.out.println(nt.getMessage());
        }
     try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
          java.sql.ResultSet rs = pstmt.executeQuery("select * from service_rate where sub_service = '"+cmbox.getSelectedItem()+"' AND code LIKE '700%'"); 
     while (rs.next())
            //jTextField4.setText(rs.getObject(1).toString());
            jTable1.setValueAt(rs.getObject(1),i,5);
 } catch(java.sql.SQLException sqlex){
        System.out.println(sqlex.getMessage());
 }
   try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.Statement pstmt = con.createStatement();
            java.sql.ResultSet rs = pstmt.executeQuery("select * from service_rate where sub_service = '"+cmbox.getSelectedItem()+"' AND code LIKE '700%'");
     while (rs.next())
            jTable1.setValueAt(rs.getObject(2),i,7);
 } catch(java.sql.SQLException sqlex){
        System.out.println(sqlex.getMessage());
 }
      
  }  
  
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
        for (int i = 0; i < jTable1.getRowCount(); i++){
       Object selectedchkbx;
        if (this.cmbox1.isEnabled())
        selectedchkbx = jTable1.getValueAt(i,1);
       /// else
           // if (this.jCheckBox2.isSelected())
               // selectedchkbx = jCheckBox2.getText();
                else 
                    
                    selectedchkbx = jTable1.getValueAt(i,0);
        System.out.println(selectedchkbx);
        
/*       try {
             
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.PreparedStatement pstmt = con.prepareStatement("insert into general_ledger values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
             
            pstmt.setString(1,null);
            pstmt.setObject(2,jTable1.getValueAt(i,7).toString());
            pstmt.setObject(3,selectedchkbx);
            pstmt.setString(4,jTextField11.getText());
            pstmt.setString(5,null);
            pstmt.setString(6,null);
            pstmt.setString(7,null);
            pstmt.setString(8,null);
            pstmt.setString(9,null);
            pstmt.setObject(10,"00");
            pstmt.setString(11,jTable1.getValueAt(i,6).toString());
            pstmt.setString(12,null);            
            pstmt.setString(13,jComboBox1.getSelectedItem().toString());  
            pstmt.executeUpdate();
             jLabel7.setForeground(java.awt.Color.blue);
            jLabel7.setText("Insert successful");
              
         
         }catch(java.sql.SQLException sq){
            System.out.println(sq.getMessage());
              }
        
        
        
        
       try {
      //   for (int i = 0; i < jTable1.getRowCount(); i++){
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.PreparedStatement pstmt = con.prepareStatement("insert into patient_account values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1,null);
            pstmt.setObject(2,jTable1.getValueAt(i,7).toString());
            pstmt.setObject(3,selectedchkbx);
            pstmt.setString(4,jComboBox1.getSelectedItem().toString());
            pstmt.setString(6,null);
            pstmt.setString(5,null);
            pstmt.setString(7,null);
            pstmt.setString(8,null);
            pstmt.setString(9,null);
            pstmt.setString(10,null);
            pstmt.setString(11,jTextField11.getText());
            pstmt.setObject(12,jTable1.getValueAt(i,0).toString());
            pstmt.setObject(13,jTable1.getValueAt(i,1).toString());
            pstmt.setObject(14,jTable1.getValueAt(i,5).toString());
            pstmt.setObject(15,jTable1.getValueAt(i,3).toString());   
            pstmt.setString(16,jTable1.getValueAt(i,6).toString());
            pstmt.setObject(17,"00");
            pstmt.setString(18,null); 
            pstmt.setString(19,"00");
            pstmt.executeUpdate();
            jLabel7.setForeground(java.awt.Color.blue);
            jLabel7.setText("Insert successful");
               
          
            
         }catch(java.sql.SQLException sq){
            System.out.println(sq.getMessage());
              }   
        
        
        //  for (int i = 0; i < jTable1.getRowCount(); i++) {
         try {
            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
            java.sql.PreparedStatement pstmt = con.prepareStatement("insert into general_patient_diagnosis values(?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1,jComboBox1.getSelectedItem().toString());
            pstmt.setString(2,jTextField11.getText());
          
            pstmt.setString(3,jComboBox12.getSelectedItem().toString());
            pstmt.setString(4,jComboBox11.getSelectedItem().toString());
            pstmt.setString(5,jTextField2.getText());
            pstmt.setString(6,jTextField1.getText());
            pstmt.setString(7,jTextField3.getText());
            // pstmt.setString(4,jComboBox11.getSelectedItem().toString());
            pstmt.setObject(8,jTable1.getValueAt(i,1).toString());
            pstmt.setObject(9,jTable1.getValueAt(i,0).toString());
            pstmt.setObject(10,jTable1.getValueAt(i,3).toString());
            pstmt.setObject(11,jTable1.getValueAt(i,2).toString());
            pstmt.setString(12,jTextPane1.getText());
            pstmt.setObject(13,jComboBox2.getSelectedItem().toString());
            pstmt.setObject(14,"Admit");
         
            jLabel7.setForeground(java.awt.Color.blue);
            jLabel7.setText("Insert successful");
              
            pstmt.executeUpdate();
                }   catch(java.sql.SQLException sq){
               //   javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
           
               jLabel7.setForeground(java.awt.Color.red);
              jLabel7.setText("Insert not successful");
                  
            System.out.println(sq.getMessage());
              }
 */
        }
              // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.netbeans.lib.sql.NBCachedRowSet crset2;
    private org.netbeans.lib.sql.NBCachedRowSet crset1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private org.netbeans.lib.sql.pool.PooledConnectionSource pconn1;
    private javax.swing.JLabel jLabel7;
    private org.netbeans.lib.sql.NBCachedRowSet crset7;
    private org.netbeans.lib.sql.NBCachedRowSet crset5;
    private org.netbeans.lib.sql.NBCachedRowSet crset4;
    private org.netbeans.lib.sql.NBCachedRowSet crset3;
    // End of variables declaration//GEN-END:variables
    
}
