/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.ClearTable;

/**
 *
 * @author sytem partners
 */
public class ReversalOfAwards extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReversalOfAwards
     */
     java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    public ReversalOfAwards(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        connectDB = connDb;
       

        pConnDB = pconnDB;
        
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierTbl = new com.afrisoftech.dbadmin.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        reversalsTbl = new com.afrisoftech.dbadmin.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reversal Of Awards");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Save Reversal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel1.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Suppliers List"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        supplierTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct a.supplier Supplier,a.quotation_no Contract_No from st_orders a, st_floated_quotations b WHERE  a.quotation_no=b.quotation_no and a.ordered =false and b.ordered=true "));
        supplierTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(supplierTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 4.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("To be reversed"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        reversalsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Description", "Qty", "Bid Unit  Price", "Reverse"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reversalsTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reversalsTblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(reversalsTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 7.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reversalsTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reversalsTblMouseClicked
        // TODO add your handling code here:
        if(reversalsTbl.getSelectedColumn()==4){
        if(Boolean.valueOf(reversalsTbl.getValueAt(reversalsTbl.getSelectedRow(),4).toString())==Boolean.TRUE){
        int exitOption = javax.swing.JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO REVERSE THIS ITEM [" +reversalsTbl.getValueAt(reversalsTbl.getSelectedRow(),1)+"] FOR SUPPLIER [" +supplierTbl.getValueAt(supplierTbl.getSelectedRow(),0)+"] ?", "Caution before Reversing!", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION); 
        if (exitOption == javax.swing.JOptionPane.YES_OPTION) {
        try{
            
            
        }
        catch(Exception eds){
            eds.printStackTrace();
            
        }
        }
        }
        
        }
    }//GEN-LAST:event_reversalsTblMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void supplierTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierTblMouseClicked
        // TODO add your handling code here:
        
        ClearTable.clearthisTable(reversalsTbl);
                
        reversalsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select Distinct item_code ITEM_CODE,description ITEM,unit UNITS,quantity QTY,price BID_UNIT_PRICE, false REVERSE from st_recommendation where agenda_approval = true  AND supplier='"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),0)+"'  and quotation_no = '"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),1)+"'"));
//        reversalsTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT  distinct a.code as ITEM_CODE, b.item_description ITEM, a.units UNIT, a.quantity QTY,b.unit_price BID_UNIT_PRICE, false REVERSE  FROM st_orders a, st_floated_quotations b "
//                        + "where  b.supplier=a.supplier and a.code=b.item_code and b.quotation_no=a.quotation_no and b.quotation_no = '"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),1)+"' and b.supplier='"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),0)+"'  and a.ordered = false group by a.code, b.item_description, b.quotation_date, b.openning_date , b.units, b.quantity,b.unit_price"));

        
    }//GEN-LAST:event_supplierTblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Boolean check_delete=false;
        try{
            
            
        
             for (int j= 0; j < reversalsTbl.getRowCount(); j++) {
            if (reversalsTbl.getValueAt(j, 0) != null) {
            if (Boolean.valueOf(reversalsTbl.getModel().getValueAt(j, 5).toString()) == Boolean.TRUE) {
                
                ///deleting from st_orders
                                java.sql.PreparedStatement pstmt812123 = connectDB.prepareStatement("DELETE FROM st_orders where supplier='"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),0).toString()+"' and quotation_no='"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),1).toString()+"'");

                                pstmt812123.executeUpdate();
            
            

                                java.sql.PreparedStatement save1 = connectDB.prepareStatement("UPDATE st_floated_quotations SET ordered=false WHERE supplier = '"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),0).toString()+"' and quotation_no ilike '"+supplierTbl.getValueAt(supplierTbl.getSelectedRow(),1).toString()+"' and  item_description = '"+reversalsTbl.getModel().getValueAt(j, 1).toString()+"' ");
                                save1.executeUpdate();
                                check_delete=true;
        }
            }
             }
        
        
        
                                                
        if(check_delete==true){
         connectDB.setAutoCommit(false); 
            connectDB.commit();
            connectDB.setAutoCommit(true);
             javax.swing.JOptionPane.showMessageDialog(this, "Reversal Award Done Successfully", "Comfirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
             supplierTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct a.supplier Supplier,a.quotation_no Contract_No from st_orders a, st_floated_quotations b WHERE  a.quotation_no=b.quotation_no and a.ordered =false and b.ordered=true "));
            ClearTable.clearthisTable(reversalsTbl);

        }
        }
        catch(Exception esd){
        esd.printStackTrace();
        esd.getMessage();
        }
                                     
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        supplierTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select distinct a.supplier Supplier,a.quotation_no Contract_No from st_orders a, st_floated_quotations b WHERE  a.quotation_no=b.quotation_no and a.ordered =false and b.ordered=true "));
    }//GEN-LAST:event_jButton3ActionPerformed
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable reversalsTbl;
    private javax.swing.JTable supplierTbl;
    // End of variables declaration//GEN-END:variables
}
