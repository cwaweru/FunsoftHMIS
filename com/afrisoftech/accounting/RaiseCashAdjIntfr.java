/*
 * stockprices.java
 *
 * Created on November 5, 2002, 11:47 AM
 */

package com.afrisoftech.accounting;

/**
 *
 * @author  root
 */
public class RaiseCashAdjIntfr extends javax.swing.JInternalFrame {
    javax.swing.JComboBox cmbox =null;
    java.sql.Connection connectDB = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    public RaiseCashAdjIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSearchDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField111 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton51 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jComboBox13 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel582 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jTextField36 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        jSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchDialog.setModal(true);
        jSearchDialog.setUndecorated(true);
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(new javax.swing.border.EtchedBorder());
        jTextField111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField111CaretUpdate(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel.add(jTextField111, gridBagConstraints);

        jSearchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTableMouseClicked(evt);
            }
        });

        jSearchScrollPane.setViewportView(jSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel.add(jSearchScrollPane, gridBagConstraints);

        jButton51.setText("Cancel");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton51, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog.getContentPane().add(jSearchPanel, gridBagConstraints);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Raise Cash Voucher");
        setPreferredSize(new java.awt.Dimension(650, 500));
        setVisible(true);
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
        getContentPane().add(jButton1, gridBagConstraints);

        jButton2.setMnemonic('E');
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton2, gridBagConstraints);

        jButton3.setMnemonic('R');
        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton3, gridBagConstraints);

        jButton4.setMnemonic('C');
        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Amount Req.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jTextField1, gridBagConstraints);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Date Required");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel51, gridBagConstraints);

        jLabel2.setText("Payee");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField2, gridBagConstraints);

        jLabel3.setText("Purpose");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(datePicker1, gridBagConstraints);

        jLabel4.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel4, gridBagConstraints);

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select department_name from pb_departments ORDER BY department_name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jLabel6.setText("Requisitioner");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField4, gridBagConstraints);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.orange, null, null));
        jCheckBox1.setText("Reimbursment");
        buttonGroup1.add(jCheckBox1);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel2.add(jCheckBox1);

        jCheckBox2.setText("Advance Payment");
        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel2.add(jCheckBox2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jComboBox13.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, " select activity from pb_activity WHERE  (payment_modes ilike 'Both' OR payment_modes ilike 'payable') order by activity"));
        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jComboBox13, gridBagConstraints);

        jTextField3.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField3.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jTextField3, gridBagConstraints);

        jLabel7.setText("Reason");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jTextField5, gridBagConstraints);

        jLabel8.setText("Quantity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jLabel8, gridBagConstraints);

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jTextField6, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 255), 1, true));
        jLabel582.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel582.setText("Requisition No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel3.add(jLabel582, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        jTextField36.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel21.add(jTextField36, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif")));
        searchButton.setToolTipText("Search");
        searchButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel21.add(searchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        getContentPane().add(jPanel1, gridBagConstraints);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel1, gridBagConstraints);

        jButton5.setMnemonic('h');
        jButton5.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jButton5, gridBagConstraints);

        setBounds(0, 0, 674, 309);
    }//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
 searchButtonClicked();         // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
       jSearchDialog.dispose();   // Add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed
private void searchButtonClicked() {
    
       System.out.println("Showing dialog");
       
       java.awt.Point point = this.jTextField36.getLocationOnScreen();
      
       jSearchDialog.setSize(400,200);
      
       jSearchDialog.setLocation(point);
      
       jSearchDialog.setVisible(true);
      
    }    
    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
 jTextField36.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0).toString());
   jTextField4.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1).toString());
 double limit = 0;
  double Amt = 0;
  double Exceeded = 0;
        jSearchDialog.dispose(); 
       
        
        try {
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select payee,department,purpose,credit,reason,quantity from ac_petty_cash_requisition where requisition_no ='"+jTextField36.getText()+"'");
            while (rset1.next()){
                jTextField2.setText(rset1.getObject(1).toString());
                jTextField1.setText(rset1.getObject(4).toString());
                jTextField5.setText(rset1.getObject(5).toString());
                jTextField6.setText(rset1.getObject(6).toString());
                
                this.jComboBox1.setSelectedItem(rset1.getObject(2).toString());
                 this.jComboBox13.setSelectedItem(rset1.getObject(3).toString());
            }
        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            
        }
        
        
      /*  limit = java.lang.Double.parseDouble(this.jTextField2.getText());
        Exceeded = Amt-limit;
        if(Exceeded >0){
        this.jTextField4.setText(java.lang.String.valueOf(Exceeded));
        }else{
             this.jTextField4.setText("0.00");
        }  
       */// Add your handling code here:
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jTextField111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField111CaretUpdate
if(jTextField111.getCaretPosition() < 3){

      System.out.println("Nothing");
     }else{
     jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select trim(requisition_no) as scheme ,requisitioner from ac_petty_cash_requisition where requisition_no ILIKE '"+jTextField111.getText().toString()+"%' and approved = false order by requisition_no")); 

        jSearchTable.setShowHorizontalLines(false);
        jSearchScrollPane.setViewportView(jSearchTable);

 
  }        // Add your handling code here:
    }//GEN-LAST:event_jTextField111CaretUpdate
    
    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        try{
            java.sql.Statement stm1211 = connectDB.createStatement();
            java.sql.ResultSet rse1211 = stm1211.executeQuery("select code from pb_activity where activity = '"+jComboBox13.getSelectedItem().toString()+"'");
            while (rse1211.next())
                //  rname = rse1211.getObject(1).toString();
                jTextField3.setText(rse1211.getObject(1).toString());
        } catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
            System.out.println(sqlex.getMessage());
        }        // Add your handling code here:
    }//GEN-LAST:event_jComboBox13ActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        
        this.jTextField1.setText("");
        this.jTextField2.setText("");
        jTextField3.setText("");
        // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setVisible(false); // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //    javax.swing.JFrame bank = new Stockprices(connectDB, pConnDB);
        //    bank.setVisible(true);// Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String selectedchkbx;
       
        String voucherNo = null;
        String actCode = null;
        String actName = null;
        String User = null;
        try {
            connectDB.setAutoCommit(false);
    
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement ("begin work; update ac_petty_cash_requisition set payee = '"+jTextField2.getText()+"',code ='"+jTextField3.getText()+"', department ='"+this.jComboBox1.getSelectedItem().toString()+"',purpose ='"+this.jComboBox13.getSelectedItem().toString()+"',credit ='"+jTextField1.getText()+"',reason ='"+jTextField5.getText()+"',quantity ='"+jTextField6.getText()+"'  where requisition_no ='"+jTextField36.getText()+"';commit work");
            //java.sql.PreparedStatement pstmt = con.prepareStatement ("begin work; update shares_receiptsb set deposit = (deposit + '"+jTextField3.getText()+"' + '"+jTextField12.getText()+"') where code = '"+jComboBox1.getSelectedItem()+"'and deposit = '"+jCheckBox1.getText().toString()+"';commit work");
             pstmt.executeUpdate();
          
            connectDB.commit();
            connectDB.setAutoCommit(true);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Insert Successful","Confirmation Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            PettyVoucherPdf policy = new PettyVoucherPdf();
            policy.PettyVoucherPdf(connectDB,jTextField36.getText());
            this.jTextField1.setText("");
            //  this.jTextField2.setText("");
           // jTextField3.setText("");
        }   catch(java.sql.SQLException sq){
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            try {
                connectDB.rollback();
            }catch (java.sql.SQLException sql){
                javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            // jLabel1.setForeground(java.awt.Color.red);
            //  jLabel1.setText("Sorry.insert not successful");
        }
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JDialog jSearchDialog;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField111;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jButton51;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel582;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox jComboBox1;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
    
}
