/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package com.afrisoftech.accounting;

import biz.systempartners.reports.*;

/**
 *
 * @author  funsoft
 */
public class ViewPendingGrn extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    
    /** Creates new form ReportIntfr */
    public ViewPendingGrn(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
       loadReport();
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        headerPanel = new javax.swing.JPanel();
        printBtn = new javax.swing.JButton();
        openReportBtn = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        reloadReportBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(" Report");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Printer 4.png"))); // NOI18N
        printBtn.setMnemonic('p');
        printBtn.setText("Print ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(printBtn, gridBagConstraints);

        openReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Desktop.png"))); // NOI18N
        openReportBtn.setMnemonic('o');
        openReportBtn.setText("Open Report ...");
        openReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(openReportBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(jTextField1, gridBagConstraints);

        jLabel1.setText("LPO/LSO Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        headerPanel.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Creditor/Payee", "Bank Acc No", "Bank Name", "Branch Name", "Code", "Amount(KES)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        javax.swing.table.TableColumn hyperlinkEditor = reportBodyTable.getColumnModel().getColumn(3);
        com.afrisoftech.lib.HyperLinkCellEditor hyperlinkCellEditor = new  com.afrisoftech.lib.HyperLinkCellEditor(new org.jdesktop.swingx.JXHyperlink(), reportBodyTable);
        hyperlinkEditor.setCellEditor(hyperlinkCellEditor);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer1 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer1.setHorizontalAlignment(cellRenderer1.TRAILING);
        hyperlinkEditor.setCellRenderer(cellRenderer1);

        reportBodyJscrollPane.setViewportView(reportBodyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(reportBodyJscrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(reportBodyPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeBtn, gridBagConstraints);

        reloadReportBtn.setMnemonic('r');
        reloadReportBtn.setText("Reload report");
        reloadReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(reloadReportBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spaceLable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        setBounds(0, 0, 643, 306);
    }// </editor-fold>//GEN-END:initComponents

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed
       
       // loadReport();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void openReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReportBtnActionPerformed
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct order_no as LPO_LSO_NO,supplier as supplier_name from st_stock_cardex st where st.approved=FALSE "
                    + "and supplies=true and chairperson=true and technical=true and certficate_no is not null and order_no ilike '%" + jTextField1.getText() + "%' and st.transaction_type ilike 'Receiving' and delivered_by!= 'Reversal' group by supplier,order_no  order by supplier_name"));
  
        // TODO add your handling code here:
    }//GEN-LAST:event_openReportBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false); 
    }//GEN-LAST:event_closeBtnActionPerformed
    
    private void loadReport() {
        
       //reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code, sum(cb.credit) from ac_cash_book cb where cb.schedule_no ='" + jTextField1.getText() + "' and payment_mode ilike 'RTGS' group by cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code order by 1"));
       reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct order_no as LPO_LSO_NO, supplier as supplier_name from st_stock_cardex st where st.approved=FALSE "
                    + "and supplies=true and chairperson=true and technical=true and certficate_no is not null and st.transaction_type ilike 'Receiving' and delivered_by!= 'Reversal' group by supplier,order_no  order by supplier_name"));
  
        
       // biz.systempartners.reports.TrialBalanceRpt trialBalanceReport = new biz.systempartners.reports.TrialBalanceRpt(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate());
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton openReportBtn;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JLabel spaceLable;
    // End of variables declaration//GEN-END:variables
    
}
