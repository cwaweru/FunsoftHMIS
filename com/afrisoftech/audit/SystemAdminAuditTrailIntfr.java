/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.audit;

//import javafx.scene.Cursor;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 */
public class SystemAdminAuditTrailIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;

    /**
     * Creates new form PayablesAuditIntfr
     */
    public SystemAdminAuditTrailIntfr(java.sql.Connection connDB) {

        connectDB = connDB;

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

        buttonPanel = new javax.swing.JPanel();
        refreshReportBtn = new javax.swing.JButton();
        clearFormBtn = new javax.swing.JButton();
        closeFormBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        invoicesAuditTabbedPane = new javax.swing.JTabbedPane();
        invoicesPanel = new javax.swing.JPanel();
        bodyPanel = new javax.swing.JPanel();
        auditScrollPane = new javax.swing.JScrollPane();
        auditTable = new com.afrisoftech.dbadmin.JXTable();
        headerPanel = new javax.swing.JPanel();
        startDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        searchTextFieldTxt = new javax.swing.JTextField();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("System Administration Audit Trail");
        setInheritsPopupMenu(true);
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        refreshReportBtn.setText("Generate audit report");
        refreshReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(refreshReportBtn, gridBagConstraints);

        clearFormBtn.setText("Clear form");
        clearFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(clearFormBtn, gridBagConstraints);

        closeFormBtn.setText("Close form");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(closeFormBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        invoicesPanel.setLayout(new java.awt.GridBagLayout());

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        auditTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        auditTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                auditTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                auditTableMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                auditTableMouseExited(evt);
            }
        });
        auditScrollPane.setViewportView(auditTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bodyPanel.add(auditScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 300.0;
        invoicesPanel.add(bodyPanel, gridBagConstraints);

        headerPanel.setLayout(new java.awt.GridBagLayout());

        startDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Start Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        headerPanel.add(startDatePicker, gridBagConstraints);

        endDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        searchTextFieldTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Search box(Type system user names, system administrator or action/activity to search)"));
        searchTextFieldTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchTextFieldTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(searchTextFieldTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        invoicesPanel.add(headerPanel, gridBagConstraints);

        invoicesAuditTabbedPane.addTab("Accounts Transactions Listing", invoicesPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(invoicesAuditTabbedPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void searchTextFieldTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchTextFieldTxtCaretUpdate
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if (!searchTextFieldTxt.getText().isEmpty() && searchTextFieldTxt.getText().length() > 3) {
            this.auditTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT control_id, action_user as sys_admin, account_change_timestamp, account_change_type, host_inet_address, user_account_affected as user_name FROM account_access_control WHERE account_change_timestamp::date BETWEEN '" + startDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND (action_user ilike '%" + searchTextFieldTxt.getText() + "%' or account_change_type ilike '%" + searchTextFieldTxt.getText() + "%' or user_account_affected ilike '%" + searchTextFieldTxt.getText() + "%') ORDER BY 1"));
        } else {
            this.auditTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT control_id, action_user as sys_admin, account_change_timestamp, account_change_type, host_inet_address, user_account_affected FROM account_access_control as user_name WHERE account_change_timestamp::date BETWEEN '" + startDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' ORDER BY 1"));
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldTxtCaretUpdate

    private void clearFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFormBtnActionPerformed

        this.getContentPane().removeAll();

        this.initComponents();

        this.setSize(this.getParent().getSize());

        // TODO add your handling code here:
    }//GEN-LAST:event_clearFormBtnActionPerformed

    private void refreshReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshReportBtnActionPerformed

        this.searchTextFieldTxtCaretUpdate(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_refreshReportBtnActionPerformed

    private void auditTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_auditTableMouseEntered

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // TODO add your handling code here:
    }//GEN-LAST:event_auditTableMouseEntered

    private void auditTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_auditTableMouseExited

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        // TODO add your handling code here:
    }//GEN-LAST:event_auditTableMouseExited

    private void auditTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_auditTableMouseClicked
//        if (auditTable.getValueAt(auditTable.getSelectedRow(), 3) != null && auditTable.getValueAt(auditTable.getSelectedRow(), 3).toString().length() > 0) {
//            System.out.println("Case 1");
//            invoiceDetailsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date::date, invoice_no, scheme as debtor, visit_id, requisition_no as receipt_no, service, debit as bill, credit as settlement, main_service, transaction_type, user_name FROM hp_patient_card WHERE visit_id = '" + auditTable.getValueAt(auditTable.getSelectedRow(), 3) + "' ORDER BY 1"));
//        } else if (auditTable.getValueAt(auditTable.getSelectedRow(), 2) != null && auditTable.getValueAt(auditTable.getSelectedRow(), 2).toString().length() > 0) {
//            System.out.println("Case 2");
//            invoiceDetailsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date::date, invoice_no, scheme as debtor, visit_id, requisition_no as receipt_no, service, debit as bill, credit as settlement, main_service, transaction_type, user_name FROM hp_patient_card WHERE invoice_no = '" + auditTable.getValueAt(auditTable.getSelectedRow(), 2) + "' ORDER BY 1"));
//        } else if(auditTable.getValueAt(auditTable.getSelectedRow(), 0) != null && auditTable.getValueAt(auditTable.getSelectedRow(), 0) != ""){
//            System.out.println("Case 3");
//            invoiceDetailsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date::date, invoice_no, scheme as debtor, visit_id, requisition_no as receipt_no, service, debit as bill, credit as settlement, main_service, transaction_type, user_name FROM hp_patient_card WHERE patient_no = '" + auditTable.getValueAt(auditTable.getSelectedRow(), 0) + "' ORDER BY 1"));
//        }
//        invoicesAuditTabbedPane.setSelectedIndex(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_auditTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane auditScrollPane;
    private javax.swing.JTable auditTable;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton clearFormBtn;
    private javax.swing.JButton closeFormBtn;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTabbedPane invoicesAuditTabbedPane;
    private javax.swing.JPanel invoicesPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton refreshReportBtn;
    private javax.swing.JTextField searchTextFieldTxt;
    private com.afrisoftech.lib.DatePicker startDatePicker;
    // End of variables declaration//GEN-END:variables
}
