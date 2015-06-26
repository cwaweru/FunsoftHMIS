/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */
package biz.systempartners.reports;

/**
 *
 * @author funsoft
 */
public class DebtorReportIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    com.afrisoftech.lib.DBObject dbObject = null;

    /**
     * Creates new form ReportIntfr
     */
    public DebtorReportIntfr(java.sql.Connection connDb) {
        connectDB = connDb;
        dbObject = new com.afrisoftech.lib.DBObject();
        initComponents();
        //  loadReport();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        headerPanel = new javax.swing.JPanel();
        export2SpreadSheetBtn = new javax.swing.JButton();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        printBtn = new javax.swing.JButton();
        openReportBtn = new javax.swing.JButton();
        transactionTypeCmbx = new javax.swing.JComboBox();
        saveReportBtn = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        reloadReportBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();
        creditTotalTxt = new javax.swing.JTextField();
        balanceTotalTxt = new javax.swing.JTextField();
        debitTotalTxt = new javax.swing.JTextField();
        trialBalanceTabbedPane = new javax.swing.JTabbedPane();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        reportBodyDetailsPanel = new javax.swing.JPanel();
        reportBodyDetailsJscrollPane = new javax.swing.JScrollPane();
        reportBodyDetailsTable = new com.afrisoftech.dbadmin.JTable();
        reportBodyOpeningPanel = new javax.swing.JPanel();
        reportBodyOpeningJscrollPane = new javax.swing.JScrollPane();
        reportBodyOpeningTable = new com.afrisoftech.dbadmin.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Funsoft Report Module - Trial Balance");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
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

        transactionTypeCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT upper(transaction_type) FROM transaction_list_view WHERE transaction_type ilike '%opening%' ORDER BY 1"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(transactionTypeCmbx, gridBagConstraints);

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

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(reloadReportBtn, gridBagConstraints);
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

        balanceTotalTxt.setEditable(false);
        balanceTotalTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Balance"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(balanceTotalTxt, gridBagConstraints);

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
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));

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

        trialBalanceTabbedPane.addTab("Trial Balance Report", reportBodyPanel);

        reportBodyDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyDetailsPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyDetailsTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));

        javax.swing.table.TableColumn hyperlinkEditorDetails = reportBodyDetailsTable.getColumnModel().getColumn(3);
        com.afrisoftech.lib.HyperLinkCellEditor hyperlinkCellEditorDetails = new  com.afrisoftech.lib.HyperLinkCellEditor(new org.jdesktop.swingx.JXHyperlink(), reportBodyDetailsTable);
        hyperlinkEditor.setCellEditor(hyperlinkCellEditorDetails);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRendererDetails = new javax.swing.table.DefaultTableCellRenderer();
        cellRendererDetails.setHorizontalAlignment(cellRendererDetails.TRAILING);
        hyperlinkEditorDetails.setCellRenderer(cellRendererDetails);

        reportBodyDetailsJscrollPane.setViewportView(reportBodyDetailsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyDetailsPanel.add(reportBodyDetailsJscrollPane, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Trial Balance Transaction Details View", reportBodyDetailsPanel);

        reportBodyOpeningPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyOpeningPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyOpeningTable.setAutoCreateRowSorter(true);
        reportBodyOpeningTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyOpeningTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));

        javax.swing.table.TableColumn hyperlinkEditorOpening = reportBodyOpeningTable.getColumnModel().getColumn(3);
        com.afrisoftech.lib.HyperLinkCellEditor hyperlinkCellEditorOpening = new  com.afrisoftech.lib.HyperLinkCellEditor(new org.jdesktop.swingx.JXHyperlink(), reportBodyOpeningTable);
        hyperlinkEditorOpening.setCellEditor(hyperlinkCellEditorOpening);
        //jTable1.setRowHeight(30);
        javax.swing.table.DefaultTableCellRenderer cellRendererOpening = new javax.swing.table.DefaultTableCellRenderer();
        cellRendererOpening.setHorizontalAlignment(cellRendererOpening.TRAILING);
        hyperlinkEditorOpening.setCellRenderer(cellRendererOpening);

        reportBodyOpeningJscrollPane.setViewportView(reportBodyOpeningTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyOpeningPanel.add(reportBodyOpeningJscrollPane, gridBagConstraints);

        trialBalanceTabbedPane.addTab("Opening Trial Balance Report", reportBodyOpeningPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(trialBalanceTabbedPane, gridBagConstraints);

        setBounds(0, 0, 758, 346);
    }// </editor-fold>//GEN-END:initComponents

    private void openReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReportBtnActionPerformed
        if (this.trialBalanceTabbedPane.getSelectedIndex() != 2) {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            loadReport();
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        } else {
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            reportBodyOpeningTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT activity_code, (SELECT activity FROM pb_activity where code = activity_code) as account_description, sum(debit-credit) as balance FROM transaction_list_view WHERE transaction_type ilike '" + transactionTypeCmbx.getSelectedItem() + "' AND date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "' group by 1,2 order by 2"));
//            reportBodyOpeningTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT activity_code, (SELECT activity FROM pb_activity where code = activity_code) as account_description, debit, credit, debit-credit as balance FROM transaction_list_view WHERE transaction_type ilike '" + transactionTypeCmbx.getSelectedItem() + "' AND date between '" + beginDatePicker.getDate() + "' and '" + endDatePicker.getDate() + "' group by 1,2,3,4,5 order by 2"));
//            debitTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyOpeningTable, 2)));
//            creditTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyOpeningTable, 3)));
            balanceTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyOpeningTable, 2)));
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // TODO add your handling code here:
    }//GEN-LAST:event_openReportBtnActionPerformed

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        loadReport();
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        reportBodyDetailsTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT date, description, transaction_type, "
                + "       invoice_no, debit , credit, debit-credit as balance "
                + "  FROM transaction_list_view WHERE activity_code = '" + reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 0) + "' "));
        debitTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyDetailsTable, 4)));
        creditTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyDetailsTable, 5)));
        balanceTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyDetailsTable, 6)));

        this.trialBalanceTabbedPane.setSelectedIndex(1);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));


        // TODO add your handling code here:
    }//GEN-LAST:event_reportBodyTableMouseClicked

    private void trialBalanceTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialBalanceTabbedPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_trialBalanceTabbedPaneMouseClicked

    private void debitTotalTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debitTotalTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debitTotalTxtActionPerformed

    private void loadReport() {
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        biz.systempartners.reports.TrialBalanceRpt trialBalanceReport = new biz.systempartners.reports.TrialBalanceRpt(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate());
        debitTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2)));
        creditTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 3)));
        balanceTotalTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2) - com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 3)));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceTotalTxt;
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTextField creditTotalTxt;
    private javax.swing.JTextField debitTotalTxt;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton export2SpreadSheetBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton openReportBtn;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyDetailsJscrollPane;
    public static javax.swing.JPanel reportBodyDetailsPanel;
    public static javax.swing.JTable reportBodyDetailsTable;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JScrollPane reportBodyOpeningJscrollPane;
    public static javax.swing.JPanel reportBodyOpeningPanel;
    public static javax.swing.JTable reportBodyOpeningTable;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JButton saveReportBtn;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JComboBox transactionTypeCmbx;
    private javax.swing.JTabbedPane trialBalanceTabbedPane;
    // End of variables declaration//GEN-END:variables
}
