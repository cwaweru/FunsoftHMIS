/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */
package biz.systempartners.reports;

import java.awt.Color;

/**
 *
 * @author funsoft
 */
public class CreditSalesDetailsIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;

    /**
     * Creates new form ReportIntfr
     */
    public CreditSalesDetailsIntfr(java.sql.Connection connDb) {
        connectDB = connDb;
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
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        openReportBtn = new javax.swing.JButton();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable(){
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        reloadReportBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();
        export2SpreadSheetBtn = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        saveReportBtn = new javax.swing.JButton();
        totalsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        totalCreditSalesTxt = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Funsoft Report Module - Credit Sales Detailed Report (Revenue Streams)");
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
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

        openReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/CD Cases/Cases without pen/DVDR 3.png"))); // NOI18N
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(openReportBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setAutoCreateRowSorter(true);
        reportBodyTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "G/L Code", "Sales Department", "Sales Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
        gridBagConstraints.weighty = 500.0;
        getContentPane().add(reportBodyPanel, gridBagConstraints);

        buttonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buttonPanel.setLayout(new java.awt.GridBagLayout());

        closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Folders/Folder Red.png"))); // NOI18N
        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeBtn, gridBagConstraints);

        reloadReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Download.png"))); // NOI18N
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

        export2SpreadSheetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Floppy Disk Green.png"))); // NOI18N
        export2SpreadSheetBtn.setMnemonic('x');
        export2SpreadSheetBtn.setText("Export report ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(export2SpreadSheetBtn, gridBagConstraints);

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Printer 4.png"))); // NOI18N
        printBtn.setMnemonic('p');
        printBtn.setText("Print ...");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(printBtn, gridBagConstraints);

        saveReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Devices & Hardware/Floppy Disk Blue.png"))); // NOI18N
        saveReportBtn.setMnemonic('s');
        saveReportBtn.setText("Save Report ...");
        saveReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        buttonPanel.add(saveReportBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(buttonPanel, gridBagConstraints);

        totalsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        totalsPanel.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Total Credit Sales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        totalsPanel.add(jLabel3, gridBagConstraints);

        totalCreditSalesTxt.setEditable(false);
        totalCreditSalesTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        totalsPanel.add(totalCreditSalesTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(totalsPanel, gridBagConstraints);

        setBounds(0, 0, 979, 306);
    }// </editor-fold>//GEN-END:initComponents

    private void openReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReportBtnActionPerformed

        loadReport();
        // TODO add your handling code here:
    }//GEN-LAST:event_openReportBtnActionPerformed

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed

        loadReport();

        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void saveReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReportBtnActionPerformed

   
        // TODO add your handling code here:
    }//GEN-LAST:event_saveReportBtnActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        
       
        // TODO add your handling code here:
    }//GEN-LAST:event_printBtnActionPerformed

    private void loadReport() {
    //    reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT "
                + "CASE WHEN (gl_code is null) THEN '600-60600-60031' ELSE (select code from pb_activity where pb_activity.activity ILIKE service) END as gl_code, CASE WHEN"
                + " (service ilike '%pharmacy%') THEN 'Pharmacy fee' ELSE service END as service, sum(total) as amount"
                + " from (select * from funsoft_credit_sales('" + beginDatePicker.getDate() + "','" 
                + endDatePicker.getDate() + "')) as foo  "
                + "group by 2, 1  UNION select * from funsoft_credit_sales_others('"
                + beginDatePicker.getDate() + "','" 
                + endDatePicker.getDate() + "') as foo group by 2, 1,3"
                + " order by 2"));
        javax.swing.table.TableColumn hyperlinkEditor = reportBodyTable.getColumnModel().getColumn(2);
//        com.afrisoftech.lib.HyperLinkCellEditor hyperlinkCellEditor = new com.afrisoftech.lib.HyperLinkCellEditor(new org.jdesktop.swingx.JXHyperlink(), reportBodyTable);
//        hyperlinkEditor.setCellEditor(hyperlinkCellEditor);
//
//        javax.swing.table.DefaultTableCellRenderer cellRenderer1 = new javax.swing.table.DefaultTableCellRenderer();
//        cellRenderer1.setHorizontalAlignment(cellRenderer1.TRAILING);
//        hyperlinkEditor.setCellRenderer(cellRenderer1);

        if(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2) >= 0.00){
            totalCreditSalesTxt.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2)));
            totalCreditSalesTxt.setForeground(Color.BLUE);
        } else {
            totalCreditSalesTxt.setText("("+com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable, 2))+")");
            totalCreditSalesTxt.setForeground(Color.RED);
        }
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        reportBodyTable.setAutoCreateRowSorter(true);
        // biz.systempartners.reports.TrialBalanceRpt trialBalanceReport = new biz.systempartners.reports.TrialBalanceRpt(connectDB, this.beginDatePicker.getDate(), this.endDatePicker.getDate());
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JButton export2SpreadSheetBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton openReportBtn;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JButton saveReportBtn;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JTextField totalCreditSalesTxt;
    private javax.swing.JPanel totalsPanel;
    // End of variables declaration//GEN-END:variables
}
