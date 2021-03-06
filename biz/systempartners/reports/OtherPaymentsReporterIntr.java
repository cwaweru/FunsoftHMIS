/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */

package biz.systempartners.reports;

import java.awt.Cursor;

/**
 *
 * @author  funsoft
 */
public class OtherPaymentsReporterIntr extends javax.swing.JInternalFrame {
   
    java.sql.Connection connectDB = null;
    
    
    /** Creates new form ReportIntfr */
    public OtherPaymentsReporterIntr(java.sql.Connection connDb) {
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
        reloadReportBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        reportTabbedPane = new javax.swing.JTabbedPane();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        boardMemberPane = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        startDate = new com.afrisoftech.lib.DatePicker();
        endDate = new com.afrisoftech.lib.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        voucherNoCmb = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        sittingAllTxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        mileageTxt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        accomodationTxt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        grossPayTxt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        payeTxt = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        otherTxt = new javax.swing.JTextField();
        netPayTxt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("BOARD MEMBERS REPORTER");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

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
        headerPanel.add(reloadReportBtn, gridBagConstraints);

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        headerPanel.add(closeBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        reportTabbedPane.setBackground(new java.awt.Color(153, 255, 153));
        reportTabbedPane.setForeground(new java.awt.Color(0, 204, 51));
        reportTabbedPane.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reportTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportTabbedPaneMouseClicked(evt);
            }
        });

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

        reportTabbedPane.addTab("Payments", reportBodyPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(reportTabbedPane, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Member Name/ Member ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel2, gridBagConstraints);

        boardMemberPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Individual Board Member", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10), new java.awt.Color(204, 0, 0))); // NOI18N
        boardMemberPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardMemberPaneMouseClicked(evt);
            }
        });
        boardMemberPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                boardMemberPaneKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(boardMemberPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel6, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
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

        jLabel1.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jLabel1, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(endDateLbl, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/32x32/Download.png"))); // NOI18N
        jButton1.setText("Load");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        jPanel5.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 6.0;
        jPanel3.add(jPanel5, gridBagConstraints);

        voucherNoCmb.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT 'Filter_by_Voucher_No' UNION   SELECT DISTINCT final_voucher_no FROM ac_other_payments WHERE final_voucher_no != '' and transaction_type = 'BOARD MEMBER' order by 1"));
        voucherNoCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                voucherNoCmbItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(voucherNoCmb, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel14.setBackground(new java.awt.Color(204, 255, 255));
        jPanel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Sitting All.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel14, gridBagConstraints);

        sittingAllTxt.setEditable(false);
        sittingAllTxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sittingAllTxt.setForeground(new java.awt.Color(0, 0, 153));
        sittingAllTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        sittingAllTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(sittingAllTxt, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Mileage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel20, gridBagConstraints);

        mileageTxt.setEditable(false);
        mileageTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mileageTxt.setForeground(new java.awt.Color(0, 0, 153));
        mileageTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mileageTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(mileageTxt, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Acc.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel24, gridBagConstraints);

        accomodationTxt.setEditable(false);
        accomodationTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        accomodationTxt.setForeground(new java.awt.Color(0, 0, 153));
        accomodationTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        accomodationTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(accomodationTxt, gridBagConstraints);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Gross Pay");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel25, gridBagConstraints);

        grossPayTxt.setEditable(false);
        grossPayTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        grossPayTxt.setForeground(new java.awt.Color(0, 0, 153));
        grossPayTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        grossPayTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(grossPayTxt, gridBagConstraints);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("PAYE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel26, gridBagConstraints);

        payeTxt.setEditable(false);
        payeTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        payeTxt.setForeground(new java.awt.Color(0, 0, 153));
        payeTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        payeTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(payeTxt, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Other");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel27, gridBagConstraints);

        otherTxt.setEditable(false);
        otherTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        otherTxt.setForeground(new java.awt.Color(0, 0, 153));
        otherTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        otherTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(otherTxt, gridBagConstraints);

        netPayTxt.setEditable(false);
        netPayTxt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        netPayTxt.setForeground(new java.awt.Color(0, 0, 153));
        netPayTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        netPayTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(netPayTxt, gridBagConstraints);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Net Pay");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jLabel28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel14, gridBagConstraints);

        setBounds(0, 0, 805, 486);
    }// </editor-fold>//GEN-END:initComponents

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed
       
        loadReport();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
 
    }//GEN-LAST:event_reportBodyTableMouseClicked

    private void reportTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTabbedPaneMouseClicked
        // TODO add your handling code here:
        if(reportTabbedPane.getSelectedIndex()==0){
         callSumOfFields();
        }else if(reportTabbedPane.getSelectedIndex()==1){
    callSumOfFields();
        }
    }//GEN-LAST:event_reportTabbedPaneMouseClicked

    private void boardMemberPaneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boardMemberPaneKeyReleased
        // TODO add your handling code here:
        
     reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct final_voucher_no as Voucher_No, "
  + "payment_date, dealer, dealer_code, document_no, reason, sitting_allowance, mileage, accomodation, others, gross_pay, paye, "
    + " debit as Net_Amount, user_name as payment_by FROM ac_other_payments where paid = true and (dealer ilike '%"+boardMemberPane.getText().toString()+"%' or dealer_code ilike '%"+boardMemberPane.getText().toString()+"%') and payment_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"'" )); 
         
          callSumOfFields();
    }//GEN-LAST:event_boardMemberPaneKeyReleased

    private void voucherNoCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_voucherNoCmbItemStateChanged
        // TODO add your handling code here:
        boardMemberPane.setText("");
    reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT distinct final_voucher_no as Voucher_No, "
  + "payment_date, dealer, dealer_code, document_no, reason, sitting_allowance, mileage, accomodation, others, gross_pay, paye, "
    + " debit as Net_Amount, user_name as payment_by FROM ac_other_payments where paid = true and final_voucher_no = '"+voucherNoCmb.getSelectedItem().toString()+"' and (payment_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')"));
  callSumOfFields();  
    }//GEN-LAST:event_voucherNoCmbItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boardMemberPane.setText("");
        voucherNoCmb.setSelectedIndex(0);
        loadReport();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void boardMemberPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardMemberPaneMouseClicked
        // TODO add your handling code here:
        voucherNoCmb.setSelectedIndex(0);
    }//GEN-LAST:event_boardMemberPaneMouseClicked
    private void callSumOfFields(){
    sittingAllTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,6))));
    mileageTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,7))));
    accomodationTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,8))));
    grossPayTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,10))));
    payeTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,11))));
    otherTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,9))));
    netPayTxt.setText(String.valueOf(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable,12))));
    
          
    }
    private void loadReport() {
        
  reportTabbedPane.setSelectedIndex(0);
   
  reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB,"SELECT distinct final_voucher_no as Voucher_No, "
  + "payment_date, dealer, dealer_code, document_no, reason, sitting_allowance, mileage, accomodation, others, gross_pay, paye, "
    + " debit as Net_Amount, user_name as payment_by FROM ac_other_payments where paid = true and (payment_date between '"+startDate.getDate()+"' and '"+endDate.getDate()+"')"));
  callSumOfFields();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accomodationTxt;
    private javax.swing.JTextPane boardMemberPane;
    private javax.swing.JButton closeBtn;
    private com.afrisoftech.lib.DatePicker endDate;
    private javax.swing.JLabel endDateLbl;
    private javax.swing.JTextField grossPayTxt;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField mileageTxt;
    private javax.swing.JTextField netPayTxt;
    private javax.swing.JTextField otherTxt;
    private javax.swing.JTextField payeTxt;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JTabbedPane reportTabbedPane;
    private javax.swing.JTextField sittingAllTxt;
    private com.afrisoftech.lib.DatePicker startDate;
    private javax.swing.JComboBox voucherNoCmb;
    // End of variables declaration//GEN-END:variables
    
}
