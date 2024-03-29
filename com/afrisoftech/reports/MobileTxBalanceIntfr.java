/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.reports;

import java.sql.SQLException;


/**
 *
 * @author Charles Waweru ()
 */
public class MobileTxBalanceIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form CashBookReportsIntfr
     */
    java.sql.Connection connectDB = null;

    public MobileTxBalanceIntfr(java.sql.Connection connDB) {

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

        cashbookReportsTabbedPane = new javax.swing.JTabbedPane();
        shiftReportsPanel = new javax.swing.JPanel();
        shiftReportsSearchPanel = new javax.swing.JPanel();
        beginDateLbl1 = new javax.swing.JLabel();
        endDateLbl1 = new javax.swing.JLabel();
        beginDatePicker1 = new com.afrisoftech.lib.DatePicker();
        endDatePicker1 = new com.afrisoftech.lib.DatePicker();
        TxAccountNoTxt = new javax.swing.JTextField();
        shiftReportPanel = new javax.swing.JPanel();
        shiftReportsReportJscrl = new javax.swing.JScrollPane();
        txTable = new com.afrisoftech.dbadmin.JTable();
        shiftReportsAnalysisActionPanel = new javax.swing.JPanel();
        refreshBtn1 = new javax.swing.JButton();
        clearFormBtn1 = new javax.swing.JButton();
        closeBtn1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mobile Transaction Reports");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        shiftReportsPanel.setLayout(new java.awt.GridBagLayout());

        shiftReportsSearchPanel.setLayout(new java.awt.GridBagLayout());

        beginDateLbl1.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsSearchPanel.add(beginDateLbl1, gridBagConstraints);

        endDateLbl1.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsSearchPanel.add(endDateLbl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsSearchPanel.add(beginDatePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsSearchPanel.add(endDatePicker1, gridBagConstraints);

        TxAccountNoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Transaction"));
        TxAccountNoTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxAccountNoTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsSearchPanel.add(TxAccountNoTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsPanel.add(shiftReportsSearchPanel, gridBagConstraints);

        shiftReportPanel.setLayout(new java.awt.GridBagLayout());

        txTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date_collected, (select user_name from ac_shifts where shift_no::varchar = sf.shift_no), shift_no::int,initcap(user_name),SUM(shift_amount) as debit,SUM(amount) as credit,sum(shift_amount-amount) as balance, (select sum(credit) from ac_cash_collection where shift_no = sf.shift_no and transaction_type ilike 'banking') from ac_shift_collections sf WHERE date_collected BETWEEN '" + beginDatePicker1.getDate() + "' AND '" + endDatePicker1.getDate() + "' GROUP BY 1,2,3,4, sf.shift_no ORDER BY 3 ASC"));
        txTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txTableMouseClicked(evt);
            }
        });
        shiftReportsReportJscrl.setViewportView(txTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportPanel.add(shiftReportsReportJscrl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        shiftReportsPanel.add(shiftReportPanel, gridBagConstraints);

        shiftReportsAnalysisActionPanel.setLayout(new java.awt.GridBagLayout());

        refreshBtn1.setText("Refresh Data");
        refreshBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsAnalysisActionPanel.add(refreshBtn1, gridBagConstraints);

        clearFormBtn1.setText("Clear form");
        clearFormBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFormBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsAnalysisActionPanel.add(clearFormBtn1, gridBagConstraints);

        closeBtn1.setText("Close form");
        closeBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsAnalysisActionPanel.add(closeBtn1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        shiftReportsPanel.add(shiftReportsAnalysisActionPanel, gridBagConstraints);

        cashbookReportsTabbedPane.addTab("Transaction Balances", shiftReportsPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(cashbookReportsTabbedPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtn1ActionPerformed
       txTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT mobile_tx_id, account_no,  paid_amount, upper(dealer) as client_name, sum(mp.debit-mp.credit) AS balance FROM public.mobile_payments mp WHERE date::date  BETWEEN '"+beginDatePicker1.getDate()+"' AND  '"+endDatePicker1.getDate()+"' GROUP BY 1,2,3,4 HAVING SUM(debit-credit)>0   ORDER BY mobile_tx_id "));
     
//        try {
//            java.sql.Statement stm121 = connectDB.createStatement();
//            java.sql.ResultSet rse121 = stm121.executeQuery("SELECT transaction_time::time(0), mobile_tx_id, account_no, date, paid_amount, upper(dealer) as client_name, (SELECT sum(mp.debit-mp.credit) FROM mobile_payments   mp WHERE mp.mobile_tx_id = mobile_tx_id) AS balance FROM public.mobile_payments WHERE date::date  BETWEEN '"+beginDatePicker1.getDate()+"' AND  '"+endDatePicker1.getDate()+"' AND mobile_tx_id IN (SELECT mobile_tx_id FROM mobile_payments GROUP BY 1 HAVING SUM(debit-credit)>0    ) ORDER BY date ");
//            while (rse121.next()) {
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_refreshBtn1ActionPerformed

    private void clearFormBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFormBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearFormBtn1ActionPerformed

    private void closeBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeBtn1ActionPerformed

    private void txTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txTableMouseClicked

    private void TxAccountNoTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxAccountNoTxtCaretUpdate
       
        if(TxAccountNoTxt.getCaretPosition()>2){
            txTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT mobile_tx_id, account_no, paid_amount, upper(dealer) as client_name, sum(mp.debit-mp.credit) AS balance FROM public.mobile_payments mp WHERE (account_no ILIKE  '%"+TxAccountNoTxt.getText()+"%' OR mobile_tx_id ILIKE  '%"+TxAccountNoTxt.getText()+"%' )  GROUP BY 1,2,3,4 HAVING SUM(debit-credit)>0   ORDER BY mobile_tx_id "));
      
        }// TODO add your handling code here:
    }//GEN-LAST:event_TxAccountNoTxtCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxAccountNoTxt;
    private javax.swing.JLabel beginDateLbl1;
    private com.afrisoftech.lib.DatePicker beginDatePicker1;
    private javax.swing.JTabbedPane cashbookReportsTabbedPane;
    private javax.swing.JButton clearFormBtn1;
    private javax.swing.JButton closeBtn1;
    private javax.swing.JLabel endDateLbl1;
    private com.afrisoftech.lib.DatePicker endDatePicker1;
    private javax.swing.JButton refreshBtn1;
    private javax.swing.JPanel shiftReportPanel;
    private javax.swing.JPanel shiftReportsAnalysisActionPanel;
    private javax.swing.JPanel shiftReportsPanel;
    private javax.swing.JScrollPane shiftReportsReportJscrl;
    private javax.swing.JPanel shiftReportsSearchPanel;
    private javax.swing.JTable txTable;
    // End of variables declaration//GEN-END:variables
}
