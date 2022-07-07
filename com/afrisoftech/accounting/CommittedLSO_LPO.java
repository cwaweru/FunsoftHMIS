/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package com.afrisoftech.accounting;

import biz.systempartners.reports.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author  funsoft
 */
public class CommittedLSO_LPO extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    String quail=null,quail2=null;
    
    
    /** Creates new form ReportIntfr */
    public CommittedLSO_LPO(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
      //  loadReport();
        
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
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        startDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        printBtn = new javax.swing.JButton();
        invoice_no = new javax.swing.JTextField();
        endDateLbl1 = new javax.swing.JLabel();
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
        setTitle("Funsoft Report Module");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        headerPanel.add(endDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        headerPanel.add(startDatePicker, gridBagConstraints);

        beginDateLbl.setText("LPO/LSO NO ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDateLbl, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        headerPanel.add(endDateLbl, gridBagConstraints);

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Mouse.png"))); // NOI18N
        printBtn.setMnemonic('p');
        printBtn.setText("Go");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(printBtn, gridBagConstraints);

        invoice_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                invoice_noKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(invoice_no, gridBagConstraints);

        endDateLbl1.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        headerPanel.add(endDateLbl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select supplier,refno as LPO_NO,concat(head,'-',subhead) as VOTE ,sum(allocated_amount) as AMOUNT from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') group by 1,2,3 having sum(allocated_amount) >0 and (sum(allocated_amount)-sum(paid_amount))>0 order by supplier"));

        javax.swing.table.TableColumn hyperlinkEditor = reportBodyTable.getColumnModel().getColumn(3);
        com.afrisoftech.lib.HyperLinkCellEditor hyperlinkCellEditor = new  com.afrisoftech.lib.HyperLinkCellEditor(new org.jdesktop.swingx.JXHyperlink(), reportBodyTable);
        hyperlinkEditor.setCellEditor(hyperlinkCellEditor);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRenderer1 = new javax.swing.table.DefaultTableCellRenderer();
        cellRenderer1.setHorizontalAlignment(cellRenderer1.TRAILING);
        hyperlinkEditor.setCellRenderer(cellRenderer1);

        reportBodyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportBodyTableMouseClicked(evt);
            }
        });
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
       
     loadReport();
     
        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        // TODO add your handling code here:
        loadReport();
    }//GEN-LAST:event_printBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void invoice_noKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoice_noKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         
       reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select supplier,refno as LPO_NO,concat(head,'-',subhead) as VOTE ,sum(allocated_amount) as AMOUNT from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited')  and refno ilike '%"+invoice_no.getText()+"%'  group by 1,2,3 having sum(allocated_amount) >0 and (sum(allocated_amount)-sum(paid_amount))>0 order by supplier"));
      
      }
    }//GEN-LAST:event_invoice_noKeyPressed

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
        // TODO add your handling code here:
        quail=reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 0).toString();
        quail2=reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 1).toString();
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShowReport dialog = new ShowReport(new javax.swing.JFrame(), true,connectDB,quail,quail2);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_reportBodyTableMouseClicked
    
    private void loadReport() {
        
       
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"select supplier,refno as LPO_NO,concat(head,'-',subhead) as VOTE ,sum(allocated_amount) as AMOUNT from ac_aie_commitment where (reftype='LPO' or reftype ilike '%LPO cancelled by%' or reftype='Stock Supplies' or reftype='LPO Credited') and committedon between  '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(startDatePicker.getDate())+"' and '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"' group by 1,2,3 having sum(allocated_amount) >0 and (sum(allocated_amount)-sum(paid_amount))>0 order by supplier"));
      
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beginDateLbl;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private javax.swing.JLabel endDateLbl1;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextField invoice_no;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JLabel spaceLable;
    private com.afrisoftech.lib.DatePicker startDatePicker;
    // End of variables declaration//GEN-END:variables
    
}