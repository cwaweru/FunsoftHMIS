/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.kitchen;
import java.sql.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 *
 * @author charlie
 */
public class ApproveRequisitionForm extends Interface_Alpha{
       JComponent[] arrComponents;
       java.sql.Connection connectDB = null;
       SimpleDateFormat date1;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    /**
     * Creates new form MachineSetup
     */
    public ApproveRequisitionForm(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        setVisible(true);
        pConnDB = pconnDB;
        connectDB = connDb;
        initComponents();
        uuid = "S11";
        myConn = connectDB;
        arrComponents = new JComponent[10];
        cboItemName.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(SELECT description FROM st_stock_item WHERE broad_category = 'FOODSTUFFS')"));
        // nBCachedRowSet1.setCommand("SELECT distinct(description) FROM public.st_stock_item WHERE broad_category = 'FOODSTUFFS'");
         //nBCachedRowSet1.setConnectionSource(pConnDB);
         //cboItemName.setModel(new org.netbeans.lib.sql.models.ComboBoxModel(nBCachedRowSet1,"description", null,null,null));
         
       //  cboKitchenName = loadDataDynamic(cboKitchenName, 12);
         this.date1 = new SimpleDateFormat("EEE, MMM d yyyy");
         
         
         arrComponents[0] = txtCost;
         arrComponents[1] = txtKitchenName;
         arrComponents[2] = cboItemName;
         arrComponents[3] = txtDate;
         arrComponents[4] = txtQuantity;
         arrComponents[5] = txtOfficialName;
         
         tblReport = loadData(tblReport, this.uuid, "tblReport");
         
         btnApprove.setVisible(false);
        
     
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

        try {
            nBCachedRowSet1 = new org.netbeans.lib.sql.NBCachedRowSet();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cboItemName = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        txtKitchenName = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtQuantity = new javax.swing.JTextField();
        txtOfficialName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnApprove = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();

        nBCachedRowSet1.setConnectionSource(pConnDB);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Requisition Form");
        setMaximumSize(new java.awt.Dimension(900, 300));
        setPreferredSize(new java.awt.Dimension(900, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel11.setMaximumSize(new java.awt.Dimension(473, 55));
        jPanel11.setMinimumSize(new java.awt.Dimension(473, 55));
        jPanel11.setName("cboMonthtoallocate"); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(473, 55));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Item Name : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel8, gridBagConstraints);

        cboItemName.setName("cboItemName"); // NOI18N
        cboItemName.setPreferredSize(new java.awt.Dimension(150, 20));
        cboItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboItemNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel11.add(cboItemName, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Quantity :");
        jLabel18.setName("jLabel18"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel18, gridBagConstraints);

        txtKitchenName.setColumns(15);
        txtKitchenName.setText("MAIN KITCHEN");
        txtKitchenName.setEnabled(false);
        txtKitchenName.setName("txtKitchenName"); // NOI18N
        txtKitchenName.setPreferredSize(new java.awt.Dimension(150, 20));
        txtKitchenName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKitchenNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel11.add(txtKitchenName, gridBagConstraints);

        jLabel167.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel167.setText("Date : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel167, gridBagConstraints);

        jLabel166.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel166.setText("Cost  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel166, gridBagConstraints);

        txtCost.setEditable(false);
        txtCost.setColumns(15);
        txtCost.setMaximumSize(new java.awt.Dimension(32767, 32767));
        txtCost.setMinimumSize(new java.awt.Dimension(23, 18));
        txtCost.setName("txtCost"); // NOI18N
        txtCost.setPreferredSize(new java.awt.Dimension(150, 20));
        txtCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel11.add(txtCost, gridBagConstraints);

        jLabel168.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel168.setText("Official's Name  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel11.add(jLabel168, gridBagConstraints);

        jLabel169.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel169.setText("Kitchen : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jLabel169, gridBagConstraints);

        txtDate.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel11.add(txtDate, gridBagConstraints);

        txtQuantity.setColumns(15);
        txtQuantity.setName("txtQuantity"); // NOI18N
        txtQuantity.setPreferredSize(new java.awt.Dimension(150, 20));
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel11.add(txtQuantity, gridBagConstraints);

        txtOfficialName.setEditable(false);
        txtOfficialName.setColumns(15);
        txtOfficialName.setText("KARIS KAMANDE");
        txtOfficialName.setName("txtOfficialName"); // NOI18N
        txtOfficialName.setPreferredSize(new java.awt.Dimension(150, 20));
        txtOfficialName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOfficialNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel11.add(txtOfficialName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.11;
        jPanel7.add(jPanel11, gridBagConstraints);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(23, 23));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 0));

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
        tblReport.setMaximumSize(new java.awt.Dimension(300, 100));
        tblReport.setMinimumSize(new java.awt.Dimension(60, 100));
        tblReport.setName("tblReport"); // NOI18N
        tblReport.setPreferredSize(new java.awt.Dimension(300, 100));
        tblReport.setRowHeight(20);
        tblReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReportMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblReport);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.11;
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

        btnApprove.setMnemonic('w');
        btnApprove.setText("Approve Details");
        btnApprove.setName("btnApprove"); // NOI18N
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(btnApprove, gridBagConstraints);

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

    private void cboItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboItemNameActionPerformed
           try {
               // TODO add your handling code here:
                myConn = connectDB;
               java.sql.Statement str = connectDB.createStatement();
               ResultSet rs = str.executeQuery("SELECT buying_price,units FROM st_stock_item WHERE broad_category = 'FOODSTUFFS' AND description='"+cboItemName.getSelectedItem()+"'");
               if(rs.next()){
                  txtCost.setText(rs.getObject(1).toString());
                  jLabel18.setText("Quantity in "+rs.getObject(2).toString()+" : ");
               }
           } catch (SQLException ex) {
               Logger.getLogger(ApproveRequisitionForm.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }//GEN-LAST:event_cboItemNameActionPerformed

    private void txtKitchenNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKitchenNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKitchenNameActionPerformed

    private void txtCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtOfficialNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOfficialNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOfficialNameActionPerformed

    private void tblReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMouseClicked
        // TODO add your handling code here:
       btnApprove.setVisible(true);
       updateInterface(tblReport, arrComponents);
       int selected_id = Integer.parseInt(tblReport.getValueAt(tblReport.getSelectedRow(), 0).toString());
        System.out.println("SELECTED ID"+ selected_id);
      
    }//GEN-LAST:event_tblReportMouseClicked

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        // TODO add your handling code here:
        String[][] dataToSave = new String[7][2];
        dataToSave[0][0] = "ApprovedS11";
        dataToSave[0][1] = "1"; //Change to the id of the user who is currently logged in.
        dataToSave[1][0] = "cboItemName";
        dataToSave[1][1] = cboItemName.getSelectedItem().toString();
        dataToSave[2][0] = "txtKitchenName";
        dataToSave[2][1] = txtKitchenName.getText().toString();
        dataToSave[3][0] = "txtQuantity";
        dataToSave[3][1] = txtQuantity.getText().toString();
        dataToSave[4][0] = "txtDate";
        dataToSave[4][1] = this.date1.format(txtDate.getDate());
        
        dataToSave[5][0] = "txtCost";
        dataToSave[5][1] = txtCost.getText().toString();
        dataToSave[6][0] = "txtOfficialName";
        dataToSave[6][1] = txtOfficialName.getText().toString();
        myConn = connectDB;
        saveApplication(dataToSave);
        
        JOptionPane.showMessageDialog(null,"S11 has been approved","Success",JOptionPane.WARNING_MESSAGE);
         txtCost.setText("");
         txtKitchenName.setText("");
         cboItemName.setSelectedIndex(0);
         txtQuantity.setText("");
         
        tblReport = loadData(tblReport, this.uuid, "tblReport");
    }//GEN-LAST:event_btnApproveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JComboBox cboItemName;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private org.netbeans.lib.sql.NBCachedRowSet nBCachedRowSet1;
    private javax.swing.JTable tblReport;
    private javax.swing.JTextField txtCost;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtKitchenName;
    private javax.swing.JTextField txtOfficialName;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}