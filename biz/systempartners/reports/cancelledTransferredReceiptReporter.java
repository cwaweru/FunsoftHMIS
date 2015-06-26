/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package biz.systempartners.reports;

/**
 *
 * @author  funsoft
 */
public class cancelledTransferredReceiptReporter extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    
    
    /** Creates new form ReportIntfr
     * @param connDb */
    public cancelledTransferredReceiptReporter(java.sql.Connection connDb) {
        connectDB = connDb;
        initComponents();
        
        //loadReport();
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton9 = new javax.swing.JButton();
        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        receiptNoTxt = new javax.swing.JTextField();
        paymentTypeCmb = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        reloadReportBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        balanceTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totalAmountSurrendered = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        totalAmountIssued = new javax.swing.JTextField();
        reportTabbedPane = new javax.swing.JTabbedPane();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailedReportTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        namelabel = new javax.swing.JLabel();
        voucherLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        startDate = new com.afrisoftech.lib.DatePicker();
        endDate = new com.afrisoftech.lib.DatePicker();
        jLabel5 = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        loadFullReport = new javax.swing.JButton();

        jSearchDialog.setModal(true);
        jSearchDialog.setUndecorated(true);
        jSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        jTextField11.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel.add(jTextField11, gridBagConstraints);

        jSearchTable.setShowHorizontalLines(false);
        /*    try {
            searchRowSet.setCommand("select product,selling_price,gl_code FROM st_stock_prices WHERE department = 'Pharmacy' order by product");
            searchRowSet.setConnectionSource(pConnDB);

            searchRowSet.execute();

            // crset2.setExecuteOnLoad(true);
            jSearchTable.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet, new org.netbeans.lib.sql.models.TableModel.Column[] {
                new org.netbeans.lib.sql.models.TableModel.Column("product", "Description", false),
                new org.netbeans.lib.sql.models.TableModel.Column("selling_price", "Amount", false),
                new org.netbeans.lib.sql.models.TableModel.Column("gl_code", "Gl_code", false)

            }));
            // jSearchScrollPane.setViewportView(jSearchTable);

        } catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sqlex.getMessage());
        }
        */
        jSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTableMouseClicked(evt);
            }
        });
        jSearchScrollPane.setViewportView(jSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel.add(jSearchScrollPane, gridBagConstraints);

        jButton9.setText("Dispose");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog.getContentPane().add(jSearchPanel, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Staff Statement");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Receipt Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(jLabel1, gridBagConstraints);

        receiptNoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Click to Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10), new java.awt.Color(255, 0, 0))); // NOI18N
        receiptNoTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                receiptNoTxtCaretUpdate(evt);
            }
        });
        receiptNoTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receiptNoTxtMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(receiptNoTxt, gridBagConstraints);

        paymentTypeCmb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        paymentTypeCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Receipt Type", "Cancelled Receipts", "Transferred Receipts" }));
        paymentTypeCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paymentTypeCmbItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(paymentTypeCmb, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        headerPanel.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        headerPanel.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
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
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(reloadReportBtn, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Balance Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(jLabel2, gridBagConstraints);

        balanceTxt.setEditable(false);
        balanceTxt.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 12.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(balanceTxt, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total Credits");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(jLabel3, gridBagConstraints);

        totalAmountSurrendered.setEditable(false);
        totalAmountSurrendered.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 12.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(totalAmountSurrendered, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total Debits");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(jLabel4, gridBagConstraints);

        totalAmountIssued.setEditable(false);
        totalAmountIssued.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 12.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(totalAmountIssued, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(buttonPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
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
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

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
        reportBodyPanel.add(jTabbedPane1, new java.awt.GridBagConstraints());

        reportTabbedPane.addTab("Receipt Report", reportBodyPanel);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        detailedReportTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(detailedReportTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        reportTabbedPane.addTab("Individual Receipt", jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(reportTabbedPane, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        namelabel.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        namelabel.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(namelabel, gridBagConstraints);

        voucherLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        voucherLabel.setForeground(new java.awt.Color(204, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(voucherLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(153, 204, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(startDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(endDate, gridBagConstraints);

        jLabel5.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jLabel5, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(endDateLbl, gridBagConstraints);

        loadFullReport.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loadFullReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Download.png"))); // NOI18N
        loadFullReport.setText("Load");
        loadFullReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFullReportActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        jPanel5.add(loadFullReport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 6.0;
        getContentPane().add(jPanel5, gridBagConstraints);

        setBounds(0, 0, 805, 499);
    }// </editor-fold>//GEN-END:initComponents

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed
       
        loadReport();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
      totalAmountIssued.setText("0.00");
      totalAmountSurrendered.setText("0.00");
      balanceTxt.setText("0.00");
      //=  '"+reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 2)+"'
          detailedReportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where (transaction_type ilike '%Receipt Transfer%' OR reason ilike '%Receipt Cancellation%') and receipt_no = '"+reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 6)+"' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where (transaction_type ilike '%Receipt%') and requisition_no = '"+reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 6)+"' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where (service_type ilike '%Receipt%' or reason ilike '%Receipt Cancellation%') and receipt_no = '"+reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 6)+"' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select date, patient_no,dealer,transaction_no as reference_no,journal_no as visit_id, transaction_type,receipt_no,debit,credit,user_name from ac_cash_collection where transaction_type ilike '%RECEIPT%' and receipt_no = '"+reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 6)+"' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
  + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where pat_inv = '"+reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 6)+"' and (transaction_type ilike '%RECEIPT CANCELLATION%' or transaction_type ilike '%Receipt Transfer%') and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" )); 
          namelabel.setText(reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 0).toString());
          voucherLabel.setText(reportBodyTable.getModel().getValueAt(reportBodyTable.getSelectedRow(), 2).toString());
          reportTabbedPane.setSelectedIndex(1); 
          double issuedSingleVoucher = 0; 
          double surrenderedSingleVoucher = 0; 
          double balSingleVoucher = 0;
          String activity = null;   
// 
// try{
//         
//
//        }
//        catch(Exception e){
//         e.printStackTrace();
//        }
    }//GEN-LAST:event_reportBodyTableMouseClicked

    private void loadFullReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFullReportActionPerformed
        // TODO add your handling code here:
        for(int t=0;t<reportBodyTable.getModel().getRowCount();t++){
         for(int c=0;c<reportBodyTable.getModel().getColumnCount();c++){
               reportBodyTable.getModel().setValueAt(null, t, c);
                      }
                }
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
         namelabel.setText("LOADING....");
         receiptNoTxt.setText("");
         receiptNoTxt.setEditable(true);
         voucherLabel.setText("");
        paymentTypeCmb.setSelectedIndex(0);
        loadReport();
        namelabel.setText("BOTH TRANSFERRED AND CANCELLED RECEIPTS");
       this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_loadFullReportActionPerformed

    private void receiptNoTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_receiptNoTxtCaretUpdate
        
        namelabel.setText("");
        voucherLabel.setText("");
        totalAmountIssued.setText("0.00");
      totalAmountSurrendered.setText("0.00");
      balanceTxt.setText("0.00");
      //imprestHolderTxt.getText().toString()
      if(receiptNoTxt.getText().length()>2){

      }
    }//GEN-LAST:event_receiptNoTxtCaretUpdate

    private void paymentTypeCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paymentTypeCmbItemStateChanged
      
        for(int t=0;t<reportBodyTable.getModel().getRowCount();t++){
         for(int c=0;c<reportBodyTable.getModel().getColumnCount();c++){
               reportBodyTable.getModel().setValueAt(null, t, c);
                      }
                }
        totalAmountIssued.setText("0.00");
      totalAmountSurrendered.setText("0.00");
      balanceTxt.setText("0.00");
      receiptNoTxt.setText("");
      receiptNoTxt.setEditable(true);
      
        if(paymentTypeCmb.getSelectedIndex()==0){
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
       reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where transaction_type ilike '%Receipt Transfer%' and reason ilike '%Receipt Cancellation%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where (main_service ilike '%Rec trans%' or main_service ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where (service_type ilike '%Receipt trans%' and reason ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select date, patient_no,dealer,transaction_no as reference_no,journal_no as visit_id, transaction_type,receipt_no,debit,credit,user_name from ac_cash_collection where transaction_type ilike '%RECEIPT CANCELLATION%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where (transaction_type ilike '%RECEIPT CANCELLATION%' or transaction_type ilike '%Receipt Transfer%') and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) "));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }else if (paymentTypeCmb.getSelectedIndex()==1){
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where (reason ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where ( main_service ilike '%Receipt Cancellation%')  and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where reason ilike '%Receipt Cancellation%'  and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select date, patient_no,dealer,transaction_no as reference_no,journal_no as visit_id, transaction_type,receipt_no,debit,credit,user_name from ac_cash_collection where transaction_type ilike '%RECEIPT CANCELLATION%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where (transaction_type ilike '%RECEIPT CANCELLATION%' ) and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"));    
       this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }else if (paymentTypeCmb.getSelectedIndex()==2){
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where transaction_type ilike '%Receipt Transfer%' and reason ilike '%Receipt Cancellation%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where (main_service ilike '%Rec trans%' or main_service ilike '%Receipt trans%' or main_service ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where service_type ilike '%Receipt trans%' and reason ilike '%Receipt Cancellation%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
  + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where (transaction_type ilike '%Receipt Transfer%') and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"));    
         this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)); 
        }
      
        callSumOfFields();
    }//GEN-LAST:event_paymentTypeCmbItemStateChanged

    private void jTextField11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11CaretUpdate
        
            if(jTextField11.getCaretPosition() < 3){

                System.out.println("Nothing");
            }else{
                this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT DISTINCT receipt_no as name,patient_no,dealer from ac_cash_collection where receipt_no ILIKE '"+jTextField11.getText()+"%' group by 1,2,3 order by receipt_no"));
                this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jSearchTable.setShowHorizontalLines(false);
                jSearchScrollPane.setViewportView(jSearchTable);

            }
        
    }//GEN-LAST:event_jTextField11CaretUpdate

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
       for(int t=0;t<reportBodyTable.getModel().getRowCount();t++){
         for(int c=0;c<reportBodyTable.getModel().getColumnCount();c++){
               reportBodyTable.getModel().setValueAt(null, t, c);
                      }
                }
       namelabel.setText(paymentTypeCmb.getSelectedItem().toString().toUpperCase()+" ONLY");
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        receiptNoTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0).toString());
        receiptNoTxt.setEditable(false);
        jSearchDialog.dispose(); 
        
                         if(paymentTypeCmb.getSelectedIndex()==0){
         reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where (transaction_type ilike '%Receipt Transfer%' OR reason ilike '%Receipt Cancellation%') and receipt_no ilike '"+receiptNoTxt.getText().toString()+"' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where (main_service ilike '%Rec trans%' or main_service ilike '%Receipt trans%') and requisition_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where (service_type ilike '%Receipt trans%' or reason ilike '%Receipt Cancellation%') and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select date, patient_no,dealer,transaction_no as reference_no,journal_no as visit_id, transaction_type,receipt_no,debit,credit,user_name from ac_cash_collection where transaction_type ilike '%RECEIPT CANCELLATION%' and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
  + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where pat_inv ilike '%"+receiptNoTxt.getText().toString()+"%' and (transaction_type ilike '%RECEIPT CANCELLATION%' or transaction_type ilike '%Receipt Transfer%') and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) "));
        
        }else if (paymentTypeCmb.getSelectedIndex()==1){
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where reason ilike '%Receipt Cancellation%' and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where ( main_service ilike '%Receipt Cancellation%') and requisition_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where reason ilike '%Receipt Cancellation%' and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
 + " union (select date, patient_no,dealer,transaction_no as reference_no,journal_no as visit_id, transaction_type,receipt_no,debit,credit,user_name from ac_cash_collection where transaction_type ilike '%RECEIPT CANCELLATION%' and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
  + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where pat_inv ilike '%"+receiptNoTxt.getText().toString()+"%' and (transaction_type ilike '%RECEIPT CANCELLATION%' ) and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) "));    
        }else if (paymentTypeCmb.getSelectedIndex()==2){
        reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where transaction_type ilike '%Receipt Transfer%' and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where (main_service ilike '%Rec trans%' or main_service ilike '%Receipt trans%') and requisition_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where service_type ilike '%Receipt trans%' and receipt_no ilike '%"+receiptNoTxt.getText().toString()+"%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'))"
  + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where pat_inv ilike '%"+receiptNoTxt.getText().toString()+"%' and (transaction_type ilike '%Receipt Transfer%') and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')) "));    
        }
this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        callSumOfFields();
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.jSearchDialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void receiptNoTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receiptNoTxtMouseClicked
        // TODO add your handling code here:
        searchButtonClicked();
    }//GEN-LAST:event_receiptNoTxtMouseClicked
        private void searchButtonClicked() {
        
        System.out.println("Showing dialog");
        
        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.receiptNoTxt.getLocationOnScreen();
        
        jSearchDialog.setSize(400,200);
        
        jSearchDialog.setLocation(point);
        
        jSearchDialog.setVisible(true);
        
        
        
    }
    
    private void callSumOfFields(){
    totalAmountIssued.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,7))));
    totalAmountSurrendered.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,8))));
    double t =    Double.parseDouble(totalAmountIssued.getText().toString().replace(",", ""))-Double.parseDouble(totalAmountSurrendered.getText().toString().replace(",", ""));
    balanceTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(t)));
          
    }
    private void loadReport() {
        totalAmountIssued.setText("0.00");
      totalAmountSurrendered.setText("0.00");
  reportTabbedPane.setSelectedIndex(0);
     receiptNoTxt.setText("");
     namelabel.setText("");
     voucherLabel.setText("");
  reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"(select date,account_no,dealer,reference_no,payee,reason,receipt_no,debit,credit,user_name from ac_debtors where (transaction_type ilike '%Receipt Transfer%' OR reason ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"') order by date ASC,receipt_no,reference_no) union " +
" (select date, patient_no, scheme, reference as reference_no, visit_id, main_service as reason, requisition_no as receipt_no,debit,credit,user_name from hp_patient_card where (main_service ilike '%Rec trans%' or main_service ilike '%Receipt trans%' or main_service ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"') order by date ASC,requisition_no,reference)" +
"union (select date,patient_no,dealer,transaction_no as reference_no,voucher_no as visit_id,service_type,receipt_no,debit,credit,user_name from ac_ledger where (service_type ilike '%Receipt trans%' or reason ilike '%Receipt Cancellation%') and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"') order by date ASC,receipt_no,transaction_no)"
 + " union (select date, patient_no,dealer,transaction_no as reference_no,journal_no as visit_id, transaction_type,receipt_no,debit,credit,user_name from ac_cash_collection where transaction_type ilike '%RECEIPT CANCELLATION%' and (date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"') order by date ASC,receipt_no,transaction_no)"
 + " union (select input_date as date,patient_no, doctor_name, reference as transaction_no,patient_name,description,pat_inv as receipt_no,debit,credit,user_name from ac_doctors_ledger where (transaction_type ilike '%RECEIPT CANCELLATION%' or transaction_type ilike '%Receipt Transfer%') and (input_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"') order by input_date ASC,receipt_no,reference)"));
  callSumOfFields();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceTxt;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTable detailedReportTable;
    private com.afrisoftech.lib.DatePicker endDate;
    private javax.swing.JLabel endDateLbl;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JDialog jSearchDialog;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JButton loadFullReport;
    private javax.swing.JLabel namelabel;
    private javax.swing.JComboBox paymentTypeCmb;
    private javax.swing.JTextField receiptNoTxt;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JTabbedPane reportTabbedPane;
    private com.afrisoftech.lib.DatePicker startDate;
    private javax.swing.JTextField totalAmountIssued;
    private javax.swing.JTextField totalAmountSurrendered;
    private javax.swing.JLabel voucherLabel;
    // End of variables declaration//GEN-END:variables
    
}
