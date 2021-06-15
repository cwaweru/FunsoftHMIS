/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import java.awt.Color;
import java.sql.SQLException;
import org.jdesktop.swingx.decorator.ColorHighlighter;
//

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class StoresLedgerReportsIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;

    /**
     * Creates new form StoresLedgerReportsIntfr
     */
    public StoresLedgerReportsIntfr(java.sql.Connection connDB) {
        connectDB = connDB;
        initComponents();

        java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
        com.afrisoftech.dbadmin.JXTable predicateTable = (com.afrisoftech.dbadmin.JXTable) abcAnalysisTable;
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate2 = new org.jdesktop.swingx.decorator.PatternPredicate("C", 2);
        ColorHighlighter cyan = new ColorHighlighter(patternPredicate2, Color.CYAN, null, Color.CYAN, null);
        tableHighlighters.add(cyan);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate3 = new org.jdesktop.swingx.decorator.PatternPredicate("B", 2);
        ColorHighlighter orange = new ColorHighlighter(patternPredicate3, Color.ORANGE, null, Color.ORANGE, null);
        tableHighlighters.add(orange);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate4 = new org.jdesktop.swingx.decorator.PatternPredicate("A", 2);
        ColorHighlighter green = new ColorHighlighter(patternPredicate4, Color.GREEN, null, Color.GREEN, null);
        tableHighlighters.add(green);
        ColorHighlighter tableHighlightersArray[] = new ColorHighlighter[]{cyan, green, orange};
        predicateTable.setHighlighterPipeline(predicateTable, tableHighlightersArray);

        java.util.Vector<org.jdesktop.swingx.decorator.Highlighter> tableHighlighters1 = new java.util.Vector<org.jdesktop.swingx.decorator.Highlighter>(1, 1);
        //org.jdesktop.swingx.decorator.Highlighter tableHighlighterArray[] ;// = new org.jdesktop.swingx.decorator.Highlighter()[];
        com.afrisoftech.dbadmin.JXTable predicateTable1 = (com.afrisoftech.dbadmin.JXTable) movementAnalysisTable;
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate21 = new org.jdesktop.swingx.decorator.PatternPredicate("C", 2);
        ColorHighlighter cyan1 = new ColorHighlighter(patternPredicate21, Color.CYAN, null, Color.CYAN, null);
        tableHighlighters.add(cyan1);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate31 = new org.jdesktop.swingx.decorator.PatternPredicate("B", 2);
        ColorHighlighter orange1 = new ColorHighlighter(patternPredicate31, Color.ORANGE, null, Color.ORANGE, null);
        tableHighlighters.add(orange1);
        org.jdesktop.swingx.decorator.PatternPredicate patternPredicate41 = new org.jdesktop.swingx.decorator.PatternPredicate("A", 2);
        ColorHighlighter green1 = new ColorHighlighter(patternPredicate41, Color.GREEN, null, Color.GREEN, null);
        tableHighlighters.add(green1);
        ColorHighlighter tableHighlightersArray1[] = new ColorHighlighter[]{cyan1, green1, orange1};
        predicateTable1.setHighlighterPipeline(predicateTable, tableHighlightersArray1);
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
        startDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        bodyPanel = new javax.swing.JPanel();
        storeDashboardTabbedPane = new javax.swing.JTabbedPane();
        StoreBalancesPanel = new javax.swing.JPanel();
        stockValuationScrollPane = new javax.swing.JScrollPane();
        stockValuationTable = new com.afrisoftech.dbadmin.JXTable();
        storeDetailedBalancesPanel = new javax.swing.JPanel();
        storeDetailedBalancesScrollPane = new javax.swing.JScrollPane();
        storeDetailedBalancesTable = new com.afrisoftech.dbadmin.JXTable();
        storeNameCmbx = new javax.swing.JComboBox();
        stockAuditTrailPanel = new javax.swing.JPanel();
        storesAuditScrollPane = new javax.swing.JScrollPane();
        storesAuditTrailTable = new com.afrisoftech.dbadmin.JXTable();
        itemBalancesPanel = new javax.swing.JPanel();
        itemBalancesScrollPane = new javax.swing.JScrollPane();
        itemBalancesTable = new com.afrisoftech.dbadmin.JXTable();
        stockItemNameCmbx = new javax.swing.JComboBox();
        abcAnalysisPanel = new javax.swing.JPanel();
        abcAnalysisJscrl = new javax.swing.JScrollPane();
        abcAnalysisTable = new com.afrisoftech.dbadmin.JXTable();
        abcPanel = new javax.swing.JPanel();
        aThresholdTxt = new javax.swing.JFormattedTextField();
        bThresholdTxt = new javax.swing.JFormattedTextField();
        cThresholdTxt = new javax.swing.JFormattedTextField();
        reorderLevelPanel = new javax.swing.JPanel();
        reorderLevelJscrl = new javax.swing.JScrollPane();
        reorderLevelTable = new com.afrisoftech.dbadmin.JXTable();
        movementAnalysisPanel = new javax.swing.JPanel();
        movementAnalysisJscrl = new javax.swing.JScrollPane();
        movementAnalysisTable = new com.afrisoftech.dbadmin.JXTable();
        abcPanel1 = new javax.swing.JPanel();
        aThresholdTxt1 = new javax.swing.JFormattedTextField();
        bThresholdTxt1 = new javax.swing.JFormattedTextField();
        cThresholdTxt1 = new javax.swing.JFormattedTextField();
        buttonPanel = new javax.swing.JPanel();
        generateReportBtn = new javax.swing.JButton();
        refreshReportBtn = new javax.swing.JButton();
        closeFormBtn = new javax.swing.JButton();
        spacerLabel = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Supply Chain Management Dashboard - Stores/Inventory Ledger Reports");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        startDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Start Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(startDatePicker, gridBagConstraints);

        endDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        StoreBalancesPanel.setLayout(new java.awt.GridBagLayout());

        stockValuationScrollPane.setViewportView(stockValuationTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        StoreBalancesPanel.add(stockValuationScrollPane, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Stores Ledger Balances Report", StoreBalancesPanel);

        storeDetailedBalancesPanel.setLayout(new java.awt.GridBagLayout());

        storeDetailedBalancesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT initcap(item) as item_name, sum(debit-quantity_ordered) FROM st_stock_cardex WHERE upper(store) = upper('"+storeNameCmbx.getSelectedItem()+"') GROUP BY 1 ORDER BY 1")
        );
        storeDetailedBalancesScrollPane.setViewportView(storeDetailedBalancesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        storeDetailedBalancesPanel.add(storeDetailedBalancesScrollPane, gridBagConstraints);

        storeNameCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT initcap(store_name) FROM st_stores WHERE patient_store = true ORDER BY 1")
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        storeDetailedBalancesPanel.add(storeNameCmbx, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Detailed Store Balances Report", storeDetailedBalancesPanel);

        stockAuditTrailPanel.setLayout(new java.awt.GridBagLayout());

        storesAuditTrailTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT initcap(store) as store_name, initcap(item) as item_name, quantity_received, debit as value_received, sub_store_issuing as issued_qty, quantity_ordered as value_issued, data_capture_time, user_name FROM st_stock_cardex WHERE data_capture_time::date between '"+startDatePicker.getDate()+"' AND '"+endDatePicker.getDate()+"' ORDER BY data_capture_time")
        );
        storesAuditScrollPane.setViewportView(storesAuditTrailTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        stockAuditTrailPanel.add(storesAuditScrollPane, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Stock Movement Audit Trail", stockAuditTrailPanel);

        itemBalancesPanel.setLayout(new java.awt.GridBagLayout());

        itemBalancesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT store, sum(debit-quantity_ordered) FROM st_stock_cardex WHERE initcap(item) = '"+stockItemNameCmbx.getSelectedItem()+"' GROUP BY 1 ORDER BY 1")
        );
        itemBalancesScrollPane.setViewportView(itemBalancesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        itemBalancesPanel.add(itemBalancesScrollPane, gridBagConstraints);

        stockItemNameCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT initcap(description) FROM st_stock_item ORDER BY 1")
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        itemBalancesPanel.add(stockItemNameCmbx, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Stock Item Balances Per Store Report", itemBalancesPanel);

        abcAnalysisPanel.setLayout(new java.awt.GridBagLayout());

        abcAnalysisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        abcAnalysisJscrl.setViewportView(abcAnalysisTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        abcAnalysisPanel.add(abcAnalysisJscrl, gridBagConstraints);

        abcPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "The A  and C - Threshold figures MUST be set before the generating the report ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 51, 51)));
        abcPanel.setLayout(new java.awt.GridBagLayout());

        aThresholdTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "A Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        aThresholdTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        aThresholdTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcPanel.add(aThresholdTxt, gridBagConstraints);

        bThresholdTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        bThresholdTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        bThresholdTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcPanel.add(bThresholdTxt, gridBagConstraints);

        cThresholdTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "C Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 255, 0)));
        cThresholdTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        cThresholdTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcPanel.add(cThresholdTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcAnalysisPanel.add(abcPanel, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Inventory ABC Analysis", abcAnalysisPanel);

        reorderLevelPanel.setLayout(new java.awt.GridBagLayout());

        reorderLevelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        reorderLevelJscrl.setViewportView(reorderLevelTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reorderLevelPanel.add(reorderLevelJscrl, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Items Below Re-Order Level", reorderLevelPanel);

        movementAnalysisPanel.setLayout(new java.awt.GridBagLayout());

        movementAnalysisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        movementAnalysisJscrl.setViewportView(movementAnalysisTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 300.0;
        movementAnalysisPanel.add(movementAnalysisJscrl, gridBagConstraints);

        abcPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "The A  and C - Threshold figures MUST be set before the generating the report ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 51, 51)));
        abcPanel1.setLayout(new java.awt.GridBagLayout());

        aThresholdTxt1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "A Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));
        aThresholdTxt1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        aThresholdTxt1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcPanel1.add(aThresholdTxt1, gridBagConstraints);

        bThresholdTxt1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 255)));
        bThresholdTxt1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        bThresholdTxt1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcPanel1.add(bThresholdTxt1, gridBagConstraints);

        cThresholdTxt1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "C Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 255, 0)));
        cThresholdTxt1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        cThresholdTxt1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        abcPanel1.add(cThresholdTxt1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        movementAnalysisPanel.add(abcPanel1, gridBagConstraints);

        storeDashboardTabbedPane.addTab("Inventory Movement Analysis", movementAnalysisPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bodyPanel.add(storeDashboardTabbedPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 500.0;
        getContentPane().add(bodyPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        generateReportBtn.setText("Generate Report");
        generateReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(generateReportBtn, gridBagConstraints);

        refreshReportBtn.setText("Refresh Report");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(refreshReportBtn, gridBagConstraints);

        closeFormBtn.setText("Close form");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeFormBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spacerLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed

        this.dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void generateReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportBtnActionPerformed
        spacerLabel.setForeground(Color.BLUE);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if (storeDashboardTabbedPane.getSelectedIndex() == 1) {
            storeDetailedBalancesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT initcap(trim((SELECT DISTINCT description FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code ORDER BY 1 LIMIT 1))) as item_name, sum(quantity_received - sub_store_issuing) as qty_balance, (SELECT buying_price FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code) AS UNIT_PRICE, (sum(quantity_received - sub_store_issuing) * (SELECT buying_price FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code))::numeric(15,0) as stock_value FROM st_stock_cardex WHERE upper(store) = upper('" + storeNameCmbx.getSelectedItem().toString().toUpperCase() + "') AND date::date <= '" + endDatePicker.getDate() + "' AND initcap(trim((SELECT DISTINCT description FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code ORDER BY 1 LIMIT 1))) != '' AND initcap(trim((SELECT DISTINCT description FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code ORDER BY 1 LIMIT 1))) is not null  GROUP BY 1, st_stock_cardex.item_code  ORDER BY 1"));
            spacerLabel.setText("Stores Balance Totals : [" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(storeDetailedBalancesTable, 3)) + "]");
        } else if (storeDashboardTabbedPane.getSelectedIndex() == 2) {
            storesAuditTrailTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT  date::date, initcap(store) as store_name, initcap(trim((SELECT DISTINCT description FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code ORDER BY 1 LIMIT 1))) as item_name, quantity_received, debit as value_received, sub_store_issuing as issued_qty, quantity_ordered as value_issued, supplier, transaction_type, user_name FROM st_stock_cardex WHERE date::date between '" + startDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' ORDER BY date"));
        } else if (storeDashboardTabbedPane.getSelectedIndex() == 3) {
            itemBalancesTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT upper(store), sum(quantity_received-sub_store_issuing), (SELECT buying_price FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code) AS UNIT_PRICE, (sum(quantity_received - sub_store_issuing) * (SELECT buying_price FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code))::numeric(15,0) as stock_value FROM st_stock_cardex WHERE item_code = '" + com.afrisoftech.lib.StockItemFactory.getItemCode(connectDB, stockItemNameCmbx.getSelectedItem().toString()) + "' GROUP BY 1, st_stock_cardex.item_code ORDER BY 1"));
            spacerLabel.setText("Stores Balance Totals : [" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(itemBalancesTable, 3)) + "]");
        } else if (storeDashboardTabbedPane.getSelectedIndex() == 0) {
            stockValuationTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT * FROM funsoft_stock_valuation_final('" + endDatePicker.getDate() + "') ORDER BY 1"));
            spacerLabel.setText("Stores Balance Totals : [" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(stockValuationTable, 1)) + "]");
        } else if (storeDashboardTabbedPane.getSelectedIndex() == 4) {
            abcAnalysisTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT initcap(trim((SELECT DISTINCT description FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code ORDER BY 1 LIMIT 1))) as item_name,  sum(debit-quantity_ordered)::numeric(15,0) as store_balance, (CASE WHEN sum(debit-quantity_ordered) > " + Double.parseDouble(aThresholdTxt.getText().replace(",", "")) + " THEN 'A' ELSE (CASE WHEN sum(debit-quantity_ordered) < " + Double.parseDouble(cThresholdTxt.getText().replace(",", "")) + " THEN 'C' ELSE 'B' END) END) as abc_category FROM st_stock_cardex WHERE  date <= '" + endDatePicker.getDate() + "' AND initcap(store) in (SELECT distinct initcap(store) FROM st_stock_cardex INTERSECT SELECT DISTINCT initcap(store_name) FROM st_stores WHERE patient_store = true) GROUP BY 1  ORDER BY 2 DESC"));
        } else if (storeDashboardTabbedPane.getSelectedIndex() == 5) {
            reorderLevelTable.setModel(getReorderTableModel());
        } else {
            movementAnalysisTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT  initcap(trim((SELECT DISTINCT st_stock_item.description FROM st_stock_item WHERE st_stock_item.item_code = st_stock_cardex.item_code ORDER BY 1 LIMIT 1))) as item_name, sum(debit - quantity_ordered)::numeric(15,0) as movement, (CASE WHEN sum(debit-quantity_ordered) > " + Double.parseDouble(aThresholdTxt1.getText().replace(",", "")) + " THEN 'A' ELSE (CASE WHEN sum(debit-quantity_ordered) < " + Double.parseDouble(cThresholdTxt1.getText().replace(",", "")) + " THEN 'C' ELSE 'B' END) END) as abc_category FROM st_stock_cardex WHERE date::date between '" + startDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "'  AND initcap(store) in (SELECT distinct initcap(store) FROM st_stock_cardex INTERSECT SELECT DISTINCT initcap(store_name) FROM st_stores WHERE patient_store = true) GROUP BY 1 HAVING sum(debit-quantity_ordered) <> 0 ORDER BY 2 DESC"));;
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
// TODO add your handling code here:
    }//GEN-LAST:event_generateReportBtnActionPerformed

    private javax.swing.table.TableModel getReorderTableModel() {
        java.util.Vector rowDataVector = new java.util.Vector(1, 1);

        java.util.Vector columnNamesVector = new java.util.Vector(1, 1);
        columnNamesVector.addElement("Item Name");
        columnNamesVector.addElement("Item Code");
        columnNamesVector.addElement("Consumption Quantity");
        columnNamesVector.addElement("Consumption Average");
        columnNamesVector.addElement("Current Stock Level");
        columnNamesVector.addElement("Optimum Stock Level");
        columnNamesVector.addElement("Below Re-Order Level");
        columnNamesVector.addElement("Quantity To Order");
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT DISTINCT item_code, description FROM st_stock_item ORDER BY 1");

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                java.util.Vector columnDataVector = new java.util.Vector(1, 1);
                System.out.println("Row : [" + rset.getString(1) + "]");
                if (InventoryLevels.getReOrderQuantity(rset.getString(1)) > 0) {
                    columnDataVector.addElement(rset.getString(2));
                    columnDataVector.addElement(rset.getString(1));
                    columnDataVector.addElement(InventoryLevels.getConsumptionNumbers(rset.getString(1)));
                    columnDataVector.addElement(InventoryLevels.getAverageConsumption(rset.getString(1)));
                    columnDataVector.addElement(InventoryLevels.getStockLevel(rset.getString(1)));
                    columnDataVector.addElement(InventoryLevels.getOptimumStockLevel(rset.getString(1)));
                    columnDataVector.addElement(InventoryLevels.getReorderStatus(rset.getString(1)));
                    columnDataVector.addElement(InventoryLevels.getReOrderQuantity(rset.getString(1)));
                    rowDataVector.addElement(columnDataVector);
                }

            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return new javax.swing.table.DefaultTableModel(rowDataVector, columnNamesVector);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel StoreBalancesPanel;
    private javax.swing.JFormattedTextField aThresholdTxt;
    private javax.swing.JFormattedTextField aThresholdTxt1;
    private javax.swing.JScrollPane abcAnalysisJscrl;
    private javax.swing.JPanel abcAnalysisPanel;
    private javax.swing.JTable abcAnalysisTable;
    private javax.swing.JPanel abcPanel;
    private javax.swing.JPanel abcPanel1;
    private javax.swing.JFormattedTextField bThresholdTxt;
    private javax.swing.JFormattedTextField bThresholdTxt1;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JFormattedTextField cThresholdTxt;
    private javax.swing.JFormattedTextField cThresholdTxt1;
    private javax.swing.JButton closeFormBtn;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton generateReportBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel itemBalancesPanel;
    private javax.swing.JScrollPane itemBalancesScrollPane;
    private javax.swing.JTable itemBalancesTable;
    private javax.swing.JScrollPane movementAnalysisJscrl;
    private javax.swing.JPanel movementAnalysisPanel;
    private javax.swing.JTable movementAnalysisTable;
    private javax.swing.JButton refreshReportBtn;
    private javax.swing.JScrollPane reorderLevelJscrl;
    private javax.swing.JPanel reorderLevelPanel;
    private javax.swing.JTable reorderLevelTable;
    private javax.swing.JLabel spacerLabel;
    private com.afrisoftech.lib.DatePicker startDatePicker;
    private javax.swing.JPanel stockAuditTrailPanel;
    private javax.swing.JComboBox stockItemNameCmbx;
    private javax.swing.JScrollPane stockValuationScrollPane;
    private javax.swing.JTable stockValuationTable;
    private javax.swing.JTabbedPane storeDashboardTabbedPane;
    private javax.swing.JPanel storeDetailedBalancesPanel;
    private javax.swing.JScrollPane storeDetailedBalancesScrollPane;
    private javax.swing.JTable storeDetailedBalancesTable;
    private javax.swing.JComboBox storeNameCmbx;
    private javax.swing.JScrollPane storesAuditScrollPane;
    private javax.swing.JTable storesAuditTrailTable;
    // End of variables declaration//GEN-END:variables
}
