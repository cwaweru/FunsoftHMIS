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
public class DebtorsAccViewSummary extends javax.swing.JInternalFrame {

    /**
     * Creates new form DebtorsAccViewSummary
     */
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    public DebtorsAccViewSummary( java.sql.Connection connectDb,org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
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
        openReportBtn = new javax.swing.JButton();
        saveReportBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        balbftxtamnt = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        spaceLable = new javax.swing.JLabel();
        creditTotalTxt = new javax.swing.JTextField();
        totalAmounttxt = new javax.swing.JTextField();
        debitTotalTxt = new javax.swing.JTextField();
        balanceTotalTxt = new javax.swing.JTextField();
        trialBalanceTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        debtorTable = new com.afrisoftech.dbadmin.JTable();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        reportBodyDetailsPanel = new javax.swing.JPanel();
        reportBodyJscrollPane1 = new javax.swing.JScrollPane();
        creditorsTableView = new com.afrisoftech.dbadmin.JTable();
        reportBodyDetailsPanel1 = new javax.swing.JPanel();
        reportBodyJscrollPane2 = new javax.swing.JScrollPane();
        debtorbreakdowntable = new com.afrisoftech.dbadmin.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientBreakdowntable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Debtor Report");
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

        openReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Desktop.png"))); // NOI18N
        openReportBtn.setMnemonic('o');
        openReportBtn.setText("Open Report ...");
        openReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(openReportBtn, gridBagConstraints);

        saveReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Globe 4.png"))); // NOI18N
        saveReportBtn.setMnemonic('s');
        saveReportBtn.setText("Save Report ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(saveReportBtn, gridBagConstraints);

        jLabel1.setText("Bal B/F");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(jLabel1, gridBagConstraints);

        balbftxtamnt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        headerPanel.add(balbftxtamnt, gridBagConstraints);

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

        creditTotalTxt.setEditable(false);
        creditTotalTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Credit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(creditTotalTxt, gridBagConstraints);

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

        debitTotalTxt.setEditable(false);
        debitTotalTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Debit"));
        debitTotalTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debitTotalTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(debitTotalTxt, gridBagConstraints);

        balanceTotalTxt.setEditable(false);
        balanceTotalTxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        balanceTotalTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Balance"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(balanceTotalTxt, gridBagConstraints);

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

        jPanel1.setLayout(new java.awt.GridBagLayout());

        debtorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        debtorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                debtorTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(debtorTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Debtors Report", jPanel1);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setAutoCreateRowSorter(true);
        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
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

        trialBalanceTabbedPane.addTab("Detailed Debtors Balance Report", reportBodyPanel);

        reportBodyDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyDetailsPanel.setLayout(new java.awt.GridBagLayout());

        creditorsTableView.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        creditorsTableView.setForeground(new java.awt.Color(0, 0, 255));
        creditorsTableView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditorsTableViewMouseClicked(evt);
            }
        });
        reportBodyJscrollPane1.setViewportView(creditorsTableView);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyDetailsPanel.add(reportBodyJscrollPane1, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Debtor Details", reportBodyDetailsPanel);

        reportBodyDetailsPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyDetailsPanel1.setLayout(new java.awt.GridBagLayout());

        debtorbreakdowntable.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        debtorbreakdowntable.setForeground(new java.awt.Color(0, 0, 255));
        debtorbreakdowntable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                debtorbreakdowntableMouseClicked(evt);
            }
        });
        reportBodyJscrollPane2.setViewportView(debtorbreakdowntable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyDetailsPanel1.add(reportBodyJscrollPane2, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Debtor Breakdown", reportBodyDetailsPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        patientBreakdowntable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(patientBreakdowntable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Statement", jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(trialBalanceTabbedPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReportBtnActionPerformed

       creditTotalTxt.setText(null);
            debitTotalTxt.setText(null);
            balanceTotalTxt.setText(null);
            totalAmounttxt.setText(null);
            balbftxtamnt.setText(null);
         totalAmounttxt.setVisible(true);
        if (this.trialBalanceTabbedPane.getSelectedIndex() ==0)
        {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

            debitTotalTxt.setText("0.00");
            creditTotalTxt.setText("0.00");
            totalAmounttxt.setText("0.00");

           debtorTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " " +
                "  select distinct dealer,sum(debit-credit)as Balance from ac_debtors " +
                "  where "
                + " date::date <='" + endDatePicker.getDate() + "' " +
               "  group by 1 ORDER BY 1 ASC "));

        totalAmounttxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(debtorTable, 1)));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // TODO add your handling code here:
    }//GEN-LAST:event_openReportBtnActionPerformed

    private void debitTotalTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debitTotalTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debitTotalTxtActionPerformed

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
         totalAmounttxt.setVisible(true);
               for(int t=1;t<creditorsTableView.getModel().getRowCount();t++){
            for(int c=0;c<creditorsTableView.getModel().getColumnCount();c++){
                creditorsTableView.getModel().setValueAt(null, t, c);
            }
            creditTotalTxt.setText(null);
            debitTotalTxt.setText(null);
            balanceTotalTxt.setText(null);
            totalAmounttxt.setText(null);
            balbftxtamnt.setText(null);
        }
               double balance=0.00;
               
                try{
             java.sql.Statement st1 = connectDB.createStatement();
                           java.sql.ResultSet rset1 = st1.executeQuery("select  sum(debit-credit) as Balance "
                    + " from ac_debtors where account_no ilike '"+reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 1)+"' "
                    + " and date::date < '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"'");
                           if(rset1.next()){
                               balance=rset1.getDouble(1);
                           }
                            System.out.println("the balance is "+balance);
                           balbftxtamnt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble((double) balance));
                          

        }
        catch(Exception meza){
            System.out.println("the populate table error is "+meza);
        }
               
               
        try{
            creditorsTableView.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "select admission_no as Patient_No,  upper(item) as Patient_name,invoice_no ||' '||receipt_no as Invoice_no,sum(debit) as Debit,sum(credit) as Credit,sum(debit-credit) as Balance"
                    + " from ac_debtors where account_no ilike '"+reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 1)+"' "
                    + " and date::date BETWEEN '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDatePicker.getDate())+"' "
                    + "AND '"+com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDatePicker.getDate())+"'"
                    + " group by 1,2,3 ORDER BY 1 ASC "));
        }
        catch(Exception meza){
            System.out.println("the creditorsTableView populate table error is "+meza);
        }
         debitTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(creditorsTableView, 3)));
       creditTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(creditorsTableView, 4)));
       balanceTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(creditorsTableView, 5)));
       totalAmounttxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble((com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(creditorsTableView, 5)+(double) balance)));
       

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        // TODO add your handling code here:
    }//GEN-LAST:event_reportBodyTableMouseClicked

    private void trialBalanceTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialBalanceTabbedPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_trialBalanceTabbedPaneMouseClicked

    private void creditorsTableViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditorsTableViewMouseClicked
         this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
         creditTotalTxt.setText(null);
            debitTotalTxt.setText(null);
            balanceTotalTxt.setText(null);
            totalAmounttxt.setText(null);
            balbftxtamnt.setText(null);
         totalAmounttxt.setVisible(false);
         
                
        debtorbreakdowntable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "Select" +
"  hp_patient_card.date,  hp_patient_card.patient_no,  hp_patient_card.service, " +
"  hp_patient_card.patient_category,  hp_patient_card.payment_mode,  hp_patient_card.scheme, " +
"   hp_patient_card.invoice_no,  hp_patient_card.debit,  hp_patient_card.credit " +
" From " +
"  hp_patient_card where      hp_patient_card.patient_no='"+creditorsTableView.getValueAt(creditorsTableView.getSelectedRow(), 0)+"' "
                + " and hp_patient_card.invoice_no='"+creditorsTableView.getValueAt(creditorsTableView.getSelectedRow(),2).toString().trim()+"' ORDER BY 1,2 ASC ")); 

        debitTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(debtorbreakdowntable, 7)));
       creditTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(debtorbreakdowntable, 8)));
       balanceTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble((com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(debtorbreakdowntable, 7)- com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(debtorbreakdowntable, 8))));

         this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_creditorsTableViewMouseClicked

    private void debtorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debtorTableMouseClicked
        
       creditTotalTxt.setText(null);
            debitTotalTxt.setText(null);
            balanceTotalTxt.setText(null);
            totalAmounttxt.setText(null);
            balbftxtamnt.setText(null);
         totalAmounttxt.setVisible(true);
        if (this.trialBalanceTabbedPane.getSelectedIndex() ==0)
        {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

            debitTotalTxt.setText("0.00");
            creditTotalTxt.setText("0.00");
            totalAmounttxt.setText("0.00");
            System.out.println("  select dealer,account_no as scheme, sum(debit-credit) as Balance from ac_debtors " +
                "  where dealer='"+debtorTable.getValueAt(debtorTable.getSelectedRow(), 1).toString().trim()+"' and "
                + " date::date <='" + endDatePicker.getDate() + "' " +
                "  group by 1,2 ORDER BY 1 ASC ");

           reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " " +
                "  select dealer,account_no as scheme, sum(debit-credit) as Balance from ac_debtors " +
                "  where dealer='"+debtorTable.getValueAt(debtorTable.getSelectedRow(), 0).toString().trim()+"' and "
                + " date::date <='" + endDatePicker.getDate() + "' " +
                "  group by 1,2 ORDER BY 1 ASC "));
        totalAmounttxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2)));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_debtorTableMouseClicked

    private void debtorbreakdowntableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debtorbreakdowntableMouseClicked
        creditTotalTxt.setText(null);
            debitTotalTxt.setText(null);
            balanceTotalTxt.setText(null);
            totalAmounttxt.setText(null);
            balbftxtamnt.setText(null);
         totalAmounttxt.setVisible(true);
   //     if (this.trialBalanceTabbedPane.getSelectedIndex() ==0)
        {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

            debitTotalTxt.setText("0.00");
            creditTotalTxt.setText("0.00");
            totalAmounttxt.setText("0.00");
            System.out.println("  select dealer,account_no as scheme, sum(debit-credit) as Balance from ac_debtors " +
                "  where dealer='"+debtorTable.getValueAt(debtorTable.getSelectedRow(), 1).toString().trim()+"' and "
                + " date::date <='" + endDatePicker.getDate() + "' " +
                "  group by 1,2 ORDER BY 1 ASC ");

           patientBreakdowntable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                   + "SELECT patient_no, service, patient_category, payment_mode," +
"debit,  credit,main_service, dosage, scheme_staff_no, " +
"       paid,  invoice_no, " +
"       cash_point,   visit_id, requisition_no" +
"  FROM hp_patient_card where patient_no='"+debtorbreakdowntable.getValueAt(debtorbreakdowntable.getSelectedRow(), 1)+"' "
                   + "and invoice_no='"+debtorbreakdowntable.getValueAt(debtorbreakdowntable.getSelectedRow(), 2)+"';"));
        totalAmounttxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2)));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_debtorbreakdowntableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceTotalTxt;
    private javax.swing.JTextField balbftxtamnt;
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextField creditTotalTxt;
    public static javax.swing.JTable creditorsTableView;
    private javax.swing.JTextField debitTotalTxt;
    private javax.swing.JTable debtorTable;
    public static javax.swing.JTable debtorbreakdowntable;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton export2SpreadSheetBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton openReportBtn;
    private javax.swing.JTable patientBreakdowntable;
    private javax.swing.JButton printBtn;
    public static javax.swing.JPanel reportBodyDetailsPanel;
    public static javax.swing.JPanel reportBodyDetailsPanel1;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JScrollPane reportBodyJscrollPane1;
    public static javax.swing.JScrollPane reportBodyJscrollPane2;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JButton saveReportBtn;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JTextField totalAmounttxt;
    private javax.swing.JTabbedPane trialBalanceTabbedPane;
    // End of variables declaration//GEN-END:variables
}
