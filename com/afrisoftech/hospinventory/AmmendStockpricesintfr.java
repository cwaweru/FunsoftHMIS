package com.afrisoftech.hospinventory;

/*
 * stockprices.java
 *
 * Created on November 5, 2002, 11:47 AM
 */



/**
 *
 * @author  root
 */
public class AmmendStockpricesintfr extends javax.swing.JInternalFrame {
    javax.swing.JComboBox cmbox2 =null;
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public AmmendStockpricesintfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
        connectDB = connDb;
        
        pConnDB = pconnDB;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        try  {
            java.lang.Class.forName("org.postgresql.Driver");
            System.out.println("Found driver");
        } catch(java.lang.ClassNotFoundException cnf){
            System.out.println("driver not found");
        }
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Amend Stock Selling");
        setPreferredSize(new java.awt.Dimension(650, 700));
        setVisible(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel5, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(225, 240, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(datePicker1, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel2.add(jTextField1, new java.awt.GridBagConstraints());

        jLabel3.setText("Store Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel3, gridBagConstraints);

        jComboBox2.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct store_name from st_stores order by store_name"));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jComboBox2, gridBagConstraints);

        jLabel1.setText("Mark Up");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jPanel2.add(jTextField2, gridBagConstraints);

        buttonGroup1.add(jCheckBox1);
        jCheckBox1.setText("Update Price per Store");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jCheckBox1, gridBagConstraints);

        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Update Prices per Category");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jCheckBox2, gridBagConstraints);

        jLabel4.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(jComboBox1, gridBagConstraints);

        jLabel6.setText("Gl Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jLabel6, gridBagConstraints);

        jTextField3.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(jTextField3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setMnemonic('O');
        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        jButton3.setMnemonic('l');
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton3, gridBagConstraints);

        jButton4.setMnemonic('C');
        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel3, gridBagConstraints);

        setBounds(0, 0, 700, 294);
    }// </editor-fold>//GEN-END:initComponents
    /*   jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct product as item,product_id from st_stock_prices where product ILIKE '"+jTextField1111.getText().toString()+"%' ORDER BY product"));
        
    
        jSearchTable1.setShowHorizontalLines(false);
        jSearchScrollPane1.setViewportView(jSearchTable1);
        */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
        try {
            java.sql.Statement pstmt1 = connectDB.createStatement();
            java.sql.ResultSet rs1 = pstmt1.executeQuery("SELECT mark_up FROM st_stores WHERE store_name ilike '"+this.jComboBox2.getSelectedItem().toString()+"%'");
            while (rs1.next()){
                jTextField2.setText(rs1.getString(1));
            }
            
            java.sql.Statement pstmt = connectDB.createStatement();
            java.sql.ResultSet rs = pstmt.executeQuery("select distinct income_account from pb_departments WHERE department_name ilike '"+jComboBox2.getSelectedItem()+"%'");
            while (rs.next()){
               
                jTextField3.setText(rs.getString(1));
            }
        } catch(java.sql.SQLException sqlex){
            System.out.println(sqlex.getMessage());
            
        }
         jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT distinct category FROM st_stock_prices WHERE department ilike '"+this.jComboBox2.getSelectedItem().toString()+"%' order by category"));
        this.jButton1.setEnabled(true);
        // Add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    private void cmbox2ActionPerformed(java.awt.event.ActionEvent evt){
        
    }
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setVisible(false); // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        long dateNow = calendar.getTimeInMillis();
        
        java.sql.Date datenowSql1= new java.sql.Date(dateNow);
        
        System.out.println(datenowSql1.toString());
        double markup = java.lang.Double.parseDouble(jTextField2.getText());
        
        //  java.sql.Timestamp datenowSql= new java.sql.Timestamp(dateNow);
      java.sql.Savepoint registerSavePoint = null;
        try {
            connectDB.setAutoCommit(false);
            registerSavePoint = connectDB.setSavepoint("registration");
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
        
        try {
            
            if(jCheckBox2.isSelected()){
                java.sql.PreparedStatement pstmt3 = connectDB.prepareStatement("UPDATE st_stock_prices SET selling_price = transfer_price*'"+markup+"' WHERE department ilike '"+jComboBox2.getSelectedItem().toString()+"' AND gl_code = '"+jTextField3.getText()+"'");
                pstmt3.executeUpdate();
            }else{
                java.sql.PreparedStatement pstmt3 = connectDB.prepareStatement("UPDATE st_stock_prices SET selling_price = transfer_price*'"+markup+"' WHERE department ilike '"+jComboBox2.getSelectedItem().toString()+"' AND gl_code = '"+jTextField3.getText()+"' AND category ilike '"+jComboBox1.getSelectedItem()+"'");
                pstmt3.executeUpdate();
            }
            
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Data Successfully Saved","Comfirmation Message!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            
            
        }   catch(java.sql.SQLException sq){
            try {
                connectDB.rollback(registerSavePoint);
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(sq.getMessage());
        }
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    
}
