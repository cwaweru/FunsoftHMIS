/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.reports;

/**
 *
 * @author root
 */
public class PaybillPaymentsReportsIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form PaybillPaymentsReportsIntfr
     */
    java.sql.Connection connectDB = null;
    private String paybillNumber;

    public PaybillPaymentsReportsIntfr(java.sql.Connection connDB) {

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

        headerPanel = new javax.swing.JPanel();
        paybillNoLbl = new javax.swing.JLabel();
        transactionNoLbl = new javax.swing.JLabel();
        shiftNoTxt = new javax.swing.JTextField();
        paybillNoCmbx = new javax.swing.JComboBox<>();
        startDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        shiftReportChkbx = new javax.swing.JCheckBox();
        bodyPanel = new javax.swing.JPanel();
        paybillReportScrollPane = new javax.swing.JScrollPane();
        paybillReportTable = new com.afrisoftech.dbadmin.JXTable();
        actionsPanel = new javax.swing.JPanel();
        spacerLabel = new javax.swing.JLabel();
        generateReportBtn = new javax.swing.JButton();
        closeFormBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MobilePay : Paybill Payments Reports");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        paybillNoLbl.setText("Paybill Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(paybillNoLbl, gridBagConstraints);

        transactionNoLbl.setText("Shift No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(transactionNoLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(shiftNoTxt, gridBagConstraints);

        paybillNoCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-' union select paybill_no || ' - '||upper(code) FROM ac_cash_points ORDER BY 1"));
        paybillNoCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paybillNoCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(paybillNoCmbx, gridBagConstraints);

        startDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Start Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(startDatePicker, gridBagConstraints);

        endDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        shiftReportChkbx.setText("Shift Report");
        shiftReportChkbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        shiftReportChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftReportChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        headerPanel.add(shiftReportChkbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        paybillReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        paybillReportScrollPane.setViewportView(paybillReportTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bodyPanel.add(paybillReportScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(bodyPanel, gridBagConstraints);

        actionsPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(spacerLabel, gridBagConstraints);

        generateReportBtn.setText("Generate Paybill report");
        generateReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(generateReportBtn, gridBagConstraints);

        closeFormBtn.setText("Close form");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionsPanel.add(closeFormBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(actionsPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void generateReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportBtnActionPerformed
        if(paybillNoCmbx.getSelectedItem().toString().equalsIgnoreCase("-") && !shiftReportChkbx.isSelected()){
             paybillReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date, receipt_time::time(0) as time, patient_no, initcap(dealer) as received_from, shift_no, receipt_no, account_no as payer_tel, journal_no as Trx_Id, sum(debit-credit) as amount, user_name as operator,paybill_no  FROM ac_cash_collection WHERE paybill_no != '' and paybill_no is not null and paybill_no IN (select paybill_no  FROM ac_cash_points) AND  date BETWEEN '" + startDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "'  GROUP BY 1,2,3,4,5,6,7,8,10,11 ORDER BY 1,2,5,6"));
         
        }else{
            if (!shiftReportChkbx.isSelected()) {
                paybillReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date, receipt_time::time(0) as time, patient_no, initcap(dealer) as received_from, shift_no, receipt_no, account_no as payer_tel, journal_no as Trx_Id, sum(debit-credit) as amount, user_name as operator FROM ac_cash_collection WHERE date BETWEEN '" + startDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND paybill_no = '" + paybillNumber + "' GROUP BY 1,2,3,4,5,6,7,8,10 ORDER BY 1,2,5,6"));
            } else {
                paybillReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date, receipt_time::time(0) as time, patient_no, initcap(dealer) as received_from, paybill_no, receipt_no, account_no as payer_tel, journal_no as Trx_Id, sum(debit-credit) as amount, user_name as operator FROM ac_cash_collection WHERE shift_no = '" + shiftNoTxt.getText() + "'  GROUP BY 1,2,3,4,5,6,7,8,10 ORDER BY 1,2,5,6"));
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_generateReportBtnActionPerformed

    private void shiftReportChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftReportChkbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shiftReportChkbxActionPerformed

    private void paybillNoCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paybillNoCmbxActionPerformed

        String accountNumber = null;
        String[] result = this.paybillNoCmbx.getSelectedItem().toString().split("\\s");
        for (int x = 0; x < result.length; x++) {
            System.out.println("Token " + x + " is [" + result[x] + "]");
        }
        paybillNumber = result[0];
        
        // TODO add your handling code here:
    }//GEN-LAST:event_paybillNoCmbxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton closeFormBtn;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton generateReportBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JComboBox<String> paybillNoCmbx;
    private javax.swing.JLabel paybillNoLbl;
    private javax.swing.JScrollPane paybillReportScrollPane;
    private javax.swing.JTable paybillReportTable;
    private javax.swing.JTextField shiftNoTxt;
    private javax.swing.JCheckBox shiftReportChkbx;
    private javax.swing.JLabel spacerLabel;
    private com.afrisoftech.lib.DatePicker startDatePicker;
    private javax.swing.JLabel transactionNoLbl;
    // End of variables declaration//GEN-END:variables
}