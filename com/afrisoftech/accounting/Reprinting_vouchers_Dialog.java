/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.accounting;

import javax.swing.JOptionPane;

/**
 *
 * @author sytem partners
 */
public class Reprinting_vouchers_Dialog extends javax.swing.JDialog {

    /**
     * Creates new form Reprinting_vouchers_Dialog
     */
   public static java.sql.Connection connectDB = null;
   public static org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    public Reprinting_vouchers_Dialog(java.awt.Frame parent, boolean modal,java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

         super(parent, modal);
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

        jSearchDialog2 = new javax.swing.JDialog();
        jSearchPanel2 = new javax.swing.JPanel();
        jTextField113 = new javax.swing.JTextField();
        jSearchScrollPane2 = new javax.swing.JScrollPane();
        jSearchTable2 = new com.afrisoftech.dbadmin.JTable();
        jButton52 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dealerNameTxt = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        voucherCbx = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        invoiceTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        supplierChkbx = new javax.swing.JCheckBox();
        imprestChkbx = new javax.swing.JCheckBox();
        imprestChkbx1 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();

        jSearchDialog2.setModal(true);
        jSearchDialog2.setUndecorated(true);
        jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel2.setLayout(new java.awt.GridBagLayout());

        jTextField113.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField113CaretUpdate(evt);
            }
        });
        jTextField113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField113ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel2.add(jTextField113, gridBagConstraints);

        jSearchTable2.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable2.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable2.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable2MouseClicked(evt);
            }
        });
        jSearchScrollPane2.setViewportView(jSearchTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel2.add(jSearchScrollPane2, gridBagConstraints);

        jButton52.setText("Close");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel2.add(jButton52, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog2.getContentPane().add(jSearchPanel2, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Reprinting Vouchers");
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Supplier/Staff");
        jPanel2.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridBagLayout());

        dealerNameTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(dealerNameTxt, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
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
        gridBagConstraints.gridy = 2;
        jPanel4.add(searchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 50);
        jPanel2.add(jPanel4, gridBagConstraints);

        jLabel3.setText("Voucher No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel3, gridBagConstraints);

        voucherCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucherCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 50);
        jPanel2.add(voucherCbx, gridBagConstraints);

        jLabel4.setText("Invoice No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jLabel4, gridBagConstraints);

        invoiceTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 50);
        jPanel2.add(invoiceTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(supplierChkbx);
        supplierChkbx.setText("Supplier/Creditor");
        supplierChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierChkbxActionPerformed(evt);
            }
        });
        jPanel3.add(supplierChkbx, new java.awt.GridBagConstraints());

        buttonGroup1.add(imprestChkbx);
        imprestChkbx.setText("Imprest");
        imprestChkbx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imprestChkbxMouseClicked(evt);
            }
        });
        imprestChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprestChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel3.add(imprestChkbx, gridBagConstraints);

        buttonGroup1.add(imprestChkbx1);
        imprestChkbx1.setText("Payment Schedule");
        imprestChkbx1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imprestChkbx1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel3.add(imprestChkbx1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Reprint Voucher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel5.add(jButton1, gridBagConstraints);

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel5, gridBagConstraints);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchButton2Clicked();        // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        if (this.jTextField113.getCaretPosition() < 3) {
            System.out.print("Nothing");
        } else {
            if (supplierChkbx.isSelected()) {
                //jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT code,supplier_name as name from st_suppliers where supplier_name ILIKE '%" + jTextField113.getText() + "%' order by supplier_name"));
                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT  distinct a.payee   FROM ac_reprinting_vouchers a where a.payee ilike '%" + jTextField113.getText() + "%' and voucher_no ilike '%VC%' union SELECT  distinct a.payee   FROM ac_reprinting_vouchers a,master_file  s where s.first_name||' '||s.last_name in (select first_name||' '||last_name from master_file) and a.payee ilike '%" + jTextField113.getText() + "%' "));
            } else if (imprestChkbx.isSelected()) {
                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT  distinct a.payee   FROM ac_reprinting_vouchers a,master_file  s where s.first_name||' '||s.last_name in (select first_name||' '||last_name from master_file) and a.payee ilike '%" + jTextField113.getText() + "%'"));
            }
            else if (imprestChkbx1.isSelected()) {
                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select dealer from ac_cash_book where description='Pay' and dealer ilike '%" + jTextField113.getText() + "%'"));
            }

            jSearchScrollPane2.setViewportView(jSearchTable2);
            System.out.println("Cannot sort out");

        } // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate

    private void jTextField113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField113ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextField113ActionPerformed

    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked
if(supplierChkbx.isSelected()){
     voucherCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT voucher_no from ac_reprinting_vouchers where payee='"+jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(),0)+"' and voucher_no ilike '%VC%') ORDER BY 1"));
}else if(imprestChkbx.isSelected()){
     voucherCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION SELECT voucher_no from ac_reprinting_vouchers where payee='"+jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(),0)+"' and voucher_no ilike '%IMP%') ORDER BY 1"));
}
else if(imprestChkbx1.isSelected()){
     voucherCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB,"(SELECT '-' UNION select schedule_no from ac_cash_book where description='Pay' and dealer ilike '%"+jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(),0)+"%') ORDER BY 1"));
}
       
         dealerNameTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(),0).toString());
         this.jSearchDialog2.dispose();
    // Add your handling code here:
    }//GEN-LAST:event_jSearchTable2MouseClicked

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        this.jSearchDialog2.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void voucherCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucherCbxActionPerformed
       invoiceTxt.setText(null);
       
       if(imprestChkbx1.isSelected()){
             try{
       java.sql.Statement ps = connectDB.createStatement();
       java.sql.ResultSet rst = ps.executeQuery("select chq_date from ac_cash_book where description='Pay' and schedule_no='"+voucherCbx.getSelectedItem()+"'");
       while (rst.next()) {
      invoiceTxt.setText(rst.getObject(1).toString());
       }
      
      }
      catch(Exception esd){
      esd.printStackTrace();
      
      }
       }else {
       try{
       java.sql.Statement ps = connectDB.createStatement();
       java.sql.ResultSet rst = ps.executeQuery("select invoice,payee from ac_reprinting_vouchers where voucher_no='"+voucherCbx.getSelectedItem()+"'");
       while (rst.next()) {
      invoiceTxt.setText(rst.getObject(1).toString());
      dealerNameTxt.setText(rst.getObject(2).toString());
       }
      }
      catch(Exception esd){
      esd.printStackTrace();
      
      }
       }
    }//GEN-LAST:event_voucherCbxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!voucherCbx.getSelectedItem().equals("-")&&supplierChkbx.isSelected()){
        com.afrisoftech.votebook.BillVoucherMtrhPdfs policy = new com.afrisoftech.votebook.BillVoucherMtrhPdfs();

         policy.VoucherMtrhPdf(connectDB, voucherCbx.getSelectedItem().toString());
         }
        else if(!voucherCbx.getSelectedItem().equals("-")&&imprestChkbx.isSelected()){
         com.afrisoftech.votebook.PettyCashVoucherPdf policy = new com.afrisoftech.votebook.PettyCashVoucherPdf();

         policy.PettyCashVoucherPdf(connectDB, voucherCbx.getSelectedItem().toString());  
         com.afrisoftech.votebook.ImprestSurrenderPdf policys = new com.afrisoftech.votebook.ImprestSurrenderPdf();
                        policys.ImprestSurrenderPdf(connectDB,voucherCbx.getSelectedItem().toString());
            
        }if(!voucherCbx.getSelectedItem().equals("-")&&imprestChkbx1.isSelected()){
       com.afrisoftech.reports.PaymentSchedulePdf policy = new com.afrisoftech.reports.PaymentSchedulePdf();
                            policy.PaymentSchedulePdf(connectDB,voucherCbx.getSelectedItem().toString(), true);

         
         }
        else{
        JOptionPane.showMessageDialog(null,"You need to select the voucher no", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void imprestChkbxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imprestChkbxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imprestChkbxMouseClicked

    private void imprestChkbx1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imprestChkbx1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imprestChkbx1MouseClicked

    private void supplierChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierChkbxActionPerformed
        // TODO add your handling code here:
        
        voucherCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct voucher_no from ac_reprinting_vouchers where voucher_no ilike '%VC%' order by 1 "));
    }//GEN-LAST:event_supplierChkbxActionPerformed

    private void imprestChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprestChkbxActionPerformed
        // TODO add your handling code here:
        voucherCbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select distinct voucher_no from ac_reprinting_vouchers where voucher_no ilike '%IMP%' order by 1 "));
    }//GEN-LAST:event_imprestChkbxActionPerformed
  private void searchButton2Clicked() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.dealerNameTxt.getLocationOnScreen();

        jSearchDialog2.setSize(600, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reprinting_vouchers_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reprinting_vouchers_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reprinting_vouchers_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reprinting_vouchers_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Reprinting_vouchers_Dialog dialog = new Reprinting_vouchers_Dialog(new javax.swing.JFrame(), true,connectDB,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField dealerNameTxt;
    private javax.swing.JCheckBox imprestChkbx;
    private javax.swing.JCheckBox imprestChkbx1;
    private javax.swing.JTextField invoiceTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton52;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JTable jSearchTable2;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JButton searchButton;
    private javax.swing.JCheckBox supplierChkbx;
    private javax.swing.JComboBox voucherCbx;
    // End of variables declaration//GEN-END:variables
}
