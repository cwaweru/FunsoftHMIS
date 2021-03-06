/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saleem
 */
public class ServicesSalesViewSummary extends javax.swing.JInternalFrame {

    /**
     * Creates new form DebtorsAccViewSummary
     */
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    public ServicesSalesViewSummary( java.sql.Connection connectDb,org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        connectDB = connectDb;
        
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerPanel = new javax.swing.JPanel();
        export2SpreadSheetBtn = new javax.swing.JButton();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        printBtn = new javax.swing.JButton();
        saveReportBtn = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        spaceLable = new javax.swing.JLabel();
        totalAmounttxt = new javax.swing.JTextField();
        trialBalanceTabbedPane = new javax.swing.JTabbedPane();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new javax.swing.JTable();
        reportBodyDetailsPanel = new javax.swing.JPanel();
        reportBodyJscrollPane1 = new javax.swing.JScrollPane();
        creditorsTableView = new com.afrisoftech.dbadmin.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Services Report");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        export2SpreadSheetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Appointments.png"))); // NOI18N
        export2SpreadSheetBtn.setMnemonic('x');
        export2SpreadSheetBtn.setText("Export report ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(export2SpreadSheetBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        beginDateLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDateLbl, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDateLbl, gridBagConstraints);

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Printer 4.png"))); // NOI18N
        printBtn.setMnemonic('p');
        printBtn.setText("Print ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(printBtn, gridBagConstraints);

        saveReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Globe 4.png"))); // NOI18N
        saveReportBtn.setMnemonic('s');
        saveReportBtn.setText("Save Report ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(saveReportBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spaceLable, gridBagConstraints);

        totalAmounttxt.setEditable(false);
        totalAmounttxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalAmounttxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Amount"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(totalAmounttxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        trialBalanceTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialBalanceTabbedPaneMouseClicked(evt);
            }
        });

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setAutoCreateRowSorter(true);
        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct main_service   FROM pb_operating_parameters order by 1;"));
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

        trialBalanceTabbedPane.addTab("Debtors Balance Report", reportBodyPanel);

        reportBodyDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyDetailsPanel.setLayout(new java.awt.GridBagLayout());

        creditorsTableView.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        creditorsTableView.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyJscrollPane1.setViewportView(creditorsTableView);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyDetailsPanel.add(reportBodyJscrollPane1, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Debtor Details", reportBodyDetailsPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(trialBalanceTabbedPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trialBalanceTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialBalanceTabbedPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_trialBalanceTabbedPaneMouseClicked

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        totalAmounttxt.setVisible(true);
        for(int t=1;t<creditorsTableView.getModel().getRowCount();t++){
            for(int c=0;c<creditorsTableView.getModel().getColumnCount();c++){
                creditorsTableView.getModel().setValueAt(null, t, c);
            }
         
            totalAmounttxt.setText(null);
           
        }
   

        

        try{
            creditorsTableView.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "  SELECT activity_code,   service_type,sum(credit-debit) as Sales " +
"   FROM ac_ledger "
               + "where description ilike  '%"+reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 0)+"%' "
                + " and date::date BETWEEN '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"' "
                + "AND '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'"
                + " group by 1,2"));
        }
        catch(Exception meza){
            System.out.println("the creditorsTableView populate table error is "+meza);
        }
        totalAmounttxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble((com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(creditorsTableView, 2))));

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        // TODO add your handling code here:
    }//GEN-LAST:event_reportBodyTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTable creditorsTableView;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton export2SpreadSheetBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton printBtn;
    private javax.swing.JPanel reportBodyDetailsPanel;
    private javax.swing.JScrollPane reportBodyJscrollPane;
    private javax.swing.JScrollPane reportBodyJscrollPane1;
    private javax.swing.JPanel reportBodyPanel;
    private javax.swing.JTable reportBodyTable;
    private javax.swing.JButton saveReportBtn;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JTextField totalAmounttxt;
    private javax.swing.JTabbedPane trialBalanceTabbedPane;
    // End of variables declaration//GEN-END:variables
}
