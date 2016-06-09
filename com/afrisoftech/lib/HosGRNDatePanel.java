/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */
package com.afrisoftech.lib;

/**
 *
 * @author root
 */
public class HosGRNDatePanel extends javax.swing.JDialog {

    int reportName;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.sql.Connection connectDB = null;
    java.util.Vector dateStartEnd = null;
    javax.swing.JSpinner beginDateSpinner = null;
    javax.swing.JSpinner endDateSpinner = null;
    private String lpoNo;
    private String VoucherNo;
    private String transactionNo = null;

    /**
     * Creates new form DatePanel
     */
    public HosGRNDatePanel(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {

        super(parent, modal);

        reportName = repName;

        connectDB = connectDb;

        pConnDB = pconnDB;
        //      dateStartEnd = new java.util.Vector(1,1);

        initComponents();
        if (reportName == 17) {
            searchButton1.setVisible(false);
        }
        //       return dateStartEnd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSearchDialog11 = new javax.swing.JDialog();
        jSearchPanel11 = new javax.swing.JPanel();
        jTextField11111 = new javax.swing.JTextField();
        jSearchScrollPane11 = new javax.swing.JScrollPane();
        jSearchTable11 = new com.afrisoftech.dbadmin.JTable();
        jButton511 = new javax.swing.JButton();
        jSearchDialog2 = new javax.swing.JDialog();
        jSearchPanel2 = new javax.swing.JPanel();
        jTextField113 = new javax.swing.JTextField();
        jSearchScrollPane2 = new javax.swing.JScrollPane();
        jSearchTable2 = new com.afrisoftech.dbadmin.JTable();
        jButton42 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        datePicker1 = new com.afrisoftech.lib.DatePicker();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        byTransactionDateChbx = new javax.swing.JCheckBox();
        byInvoiceDateChbx = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        supplierNameTxt = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        lpoNumberTxt = new javax.swing.JTextField();
        searchButton1 = new javax.swing.JButton();

        jSearchDialog11.setModal(true);
        jSearchDialog11.setUndecorated(true);
        jSearchDialog11.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel11.setLayout(new java.awt.GridBagLayout());

        jTextField11111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11111CaretUpdate(evt);
            }
        });
        jTextField11111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11111ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel11.add(jTextField11111, gridBagConstraints);

        jSearchTable11.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable11.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable11MouseClicked(evt);
            }
        });
        jSearchScrollPane11.setViewportView(jSearchTable11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel11.add(jSearchScrollPane11, gridBagConstraints);

        jButton511.setText("Close");
        jButton511.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton511ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel11.add(jButton511, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog11.getContentPane().add(jSearchPanel11, gridBagConstraints);

        jSearchDialog2.setModal(true);
        jSearchDialog2.setUndecorated(true);
        jSearchDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel2.setLayout(new java.awt.GridBagLayout());

        jTextField113.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField113CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel2.add(jTextField113, gridBagConstraints);

        jSearchTable2.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable2.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = jSearchTable2.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        jSearchTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable2MouseClicked(evt);
            }
        });
        jSearchScrollPane2.setViewportView(jSearchTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel2.add(jSearchScrollPane2, gridBagConstraints);

        jButton42.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel2.add(jButton42, gridBagConstraints);

        jButton52.setText("Dispose");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel2.add(jButton52, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchDialog2.getContentPane().add(jSearchPanel2, gridBagConstraints);

        setTitle("Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jTextField1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(datePicker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(datePicker2, gridBagConstraints);

        buttonGroup1.add(byTransactionDateChbx);
        byTransactionDateChbx.setSelected(true);
        byTransactionDateChbx.setText("Generate satatement by transaction date");
        byTransactionDateChbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        byTransactionDateChbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byTransactionDateChbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(byTransactionDateChbx, gridBagConstraints);

        buttonGroup1.add(byInvoiceDateChbx);
        byInvoiceDateChbx.setText("Generate statement by invoice date");
        byInvoiceDateChbx.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(byInvoiceDateChbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Recycle Bin Full 2.png"))); // NOI18N
        jButton2.setMnemonic('C');
        jButton2.setText("Cancel and close form");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Document.png"))); // NOI18N
        jButton1.setMnemonic('G');
        jButton1.setText("Generate GRN Document");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Select creditor here.."));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Creditor Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel4, gridBagConstraints);

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel41.setLayout(new java.awt.GridBagLayout());

        supplierNameTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel41.add(supplierNameTxt, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton.setToolTipText("Search");
        searchButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel41.add(searchButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel41, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        jLabel3.setText("LPO/LSO No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabel3, gridBagConstraints);

        jPanel61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel61.setMinimumSize(new java.awt.Dimension(82, 37));
        jPanel61.setLayout(new java.awt.GridBagLayout());

        lpoNumberTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel61.add(lpoNumberTxt, gridBagConstraints);

        searchButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton1.setToolTipText("Search Patient's Invoice");
        searchButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton1.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton1.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel61.add(searchButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jPanel61, gridBagConstraints);

        setSize(new java.awt.Dimension(679, 277));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable2MouseClicked

        if (reportName == 17) {
            supplierNameTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
            lpoNo = jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 2).toString();
            VoucherNo = jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString();
            lpoNumberTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 3).toString());

            jSearchDialog2.dispose();
        } else {
            supplierNameTxt.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 0).toString());
            lpoNo = jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString();
            jSearchDialog2.dispose();
        }

    }//GEN-LAST:event_jSearchTable2MouseClicked

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        this.jSearchDialog2.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jTextField113CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField113CaretUpdate
        if (jTextField113.getCaretPosition() < 3) {
            System.out.println("Nothing");
        } else {

            if (reportName == 17) {
                jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                        + " Select "
                        + "  ac_accounts_payable.voucher_no as VoucherNO, "
                        + "  ac_accounts_payable.dealer as Dealer,ac_accounts_payable.order_no as Number,   ac_accounts_payable.invoice_no  as InvoiceNO "
                        + "From "
                        + "  ac_accounts_payable where ac_accounts_payable.dealer  ilike '%" + jTextField113.getText() + "%' and   ac_accounts_payable.voucher_no is not null "
                        + "union "
                        + "  Select "
                        + "  ac_bills.final_voucher_no as VoucherNO, ac_bills.dealer   as Dealer,dealer_code as Number, ac_bills.voucher_no   as InvoiceNO from  "
                        + "  ac_bills  where ac_bills.dealer ilike '%" + jTextField113.getText() + "%' and ac_bills.final_voucher_no is not null "));

                jSearchTable2.setShowHorizontalLines(false);
                jSearchScrollPane2.setViewportView(jSearchTable2);
            } else {

                if (reportName == 36) {

                    jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct supplier as supplier_name,  order_no as LPO_LSO_NO from st_stock_cardex st where st.approved=FALSE "
                            + "and (order_no ilike '%" + jTextField113.getText() + "%' OR supplier ILIKE '" + jTextField113.getText() + "%') and st.transaction_type ilike 'Receiving' and delivered_by!= 'Reversal' group by supplier,order_no  order by supplier"));

                    jSearchTable2.setShowHorizontalLines(false);
                    jSearchScrollPane2.setViewportView(jSearchTable2);
                } else {
                    jSearchTable2.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                            + "SELECT supplier_name, code as account_no from st_suppliers where supplier_name ILIKE '" + jTextField113.getText() + "%' order by supplier_name"));

                    jSearchTable2.setShowHorizontalLines(false);
                    jSearchScrollPane2.setViewportView(jSearchTable2);
                }
            }
        }        // Add your handling code here:
    }//GEN-LAST:event_jTextField113CaretUpdate
    private void searchButtonClicked() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.supplierNameTxt.getLocationOnScreen();

        jSearchDialog2.setSize(400, 200);

        jSearchDialog2.setLocation(point);

        jSearchDialog2.setVisible(true);

    }
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        for (int k = 0; k < jSearchTable2.getRowCount(); k++) {
            for (int r = 0; r < jSearchTable2.getColumnCount(); r++) {
                jSearchTable2.getModel().setValueAt(null, k, r);
            }
        }
        this.jTextField113.setText("");
        searchButtonClicked();        // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jTextField11111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11111CaretUpdate
        {
            jSearchTable11.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                    + "select distinct trim(invoice_no) as scheme,date as name, requisition_no as transaction_no, order_no from st_stock_cardex"
                    + " where invoice_no ILIKE '%" + jTextField11111.getText().toString() + "%' and supplier = '" + this.supplierNameTxt.getText() + "' and date between '" + this.datePicker1.getDate() + "' and '" + this.datePicker2.getDate() + "' ORDER BY date"));
            jSearchTable11.setShowHorizontalLines(false);
            jSearchScrollPane11.setViewportView(jSearchTable11);
        }
        /*
         try {
         searchRowSet11.execute("select trim(invoice_no) as scheme,date as name from ac_accounts_payable where invoice_no ILIKE '"+jTextField11111.getText().toString()+"%' and dealer = '"+this.jTextField9.getText()+"' and date between '"+this.datePicker1.getDate()+"' and '"+this.datePicker2.getDate()+"' ORDER BY date");
             
         jSearchTable11.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet11, new org.netbeans.lib.sql.models.TableModel.Column[] {
         new org.netbeans.lib.sql.models.TableModel.Column("scheme", "Invoice No.", false),
         new org.netbeans.lib.sql.models.TableModel.Column("name", "Date", false)
             
         }));
         jSearchTable11.setShowHorizontalLines(false);
         jSearchScrollPane11.setViewportView(jSearchTable11);
             
             
         } catch(java.sql.SQLException sqlExec) {
             
         javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
             
         }
         */
        // Add your handling code here:
    }//GEN-LAST:event_jTextField11111CaretUpdate

    private void jButton511ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton511ActionPerformed
        jSearchDialog11.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jButton511ActionPerformed

    private void jSearchTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable11MouseClicked
        lpoNumberTxt.setText(jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 3).toString());
        // jTextField11.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 1).toString());
        transactionNo = jSearchTable11.getValueAt(jSearchTable11.getSelectedRow(), 2).toString();

        jSearchDialog11.dispose();          // Add your handling code here:
    }//GEN-LAST:event_jSearchTable11MouseClicked

    private void jTextField11111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11111ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextField11111ActionPerformed

    private void searchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1ActionPerformed
        searchButton111Clicked();        // Add your handling code here:
    }//GEN-LAST:event_searchButton1ActionPerformed
    private void searchButton111Clicked() {

        System.out.println("Showing dialog");

        jSearchDialog11.dispose();

        java.awt.Point point = this.lpoNumberTxt.getLocationOnScreen();
        jSearchDialog11.setLocation(point);

        jSearchDialog11.setSize(400, 200);

        jSearchDialog11.setVisible(true);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //  this.getReport(reportName);
        this.getReport(reportName, false);
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void byTransactionDateChbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byTransactionDateChbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_byTransactionDateChbxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //    new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }

    public java.util.Vector getBeginEndDates() {

        dateStartEnd = new java.util.Vector(1, 1);

        dateStartEnd.addElement(beginDateSpinner.getValue().toString());

        dateStartEnd.addElement(endDateSpinner.getValue().toString());

        return dateStartEnd;

    }

    public void getReport(int reportName, boolean printState) {

        switch (reportName) {

            case 36: {

//                com.afrisoftech.hospinventory.mtrhreports.SthirteenPdf policy = new com.afrisoftech.hospinventory.mtrhreports.SthirteenPdf();
//                policy.SthirteenPdf(connectDB, this.datePicker1.getDate().toLocaleString(), this.datePicker1.getDate().toLocaleString(), supplierTxt.getText().toString(), this.deliveryNoteNumberTxt.getText().toString(), transactionNo);

                try {
                    java.sql.Statement st119 = connectDB.createStatement();
                    java.sql.ResultSet ress19 = st119.executeQuery("SELECT DISTINCT grn_no FROM st_stock_cardex WHERE order_no = '" + lpoNumberTxt.getText() + "' AND grn_no is not null");
                    while (ress19.next()) {
                        com.afrisoftech.hospinventory.mtrhreports.MtrhSthirteenPdf policy1 = new com.afrisoftech.hospinventory.mtrhreports.MtrhSthirteenPdf();
                        policy1.MtrhSthirteenPdf(connectDB, lpoNumberTxt.getText(), supplierNameTxt.getText(), ress19.getString(1));

                    }
                } catch (Exception t) {
                    t.printStackTrace();
                }
            }
            break;

            case 3600: {
                com.afrisoftech.reports.GRTPdf policy = new com.afrisoftech.reports.GRTPdf();

                policy.GRTPdf(connectDB, this.datePicker1.getDate().toLocaleString(), this.datePicker2.getDate().toLocaleString(), supplierNameTxt.getText().toString(), this.lpoNumberTxt.getText());

                ///    this.dispose();
            }
            break;

            case 47: {
                com.afrisoftech.reports.CreditorsStatementPdf policy = new com.afrisoftech.reports.CreditorsStatementPdf();
                if (byTransactionDateChbx.isSelected()) {
                    policy.CreditorsStatementPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), supplierNameTxt.getText().toString(), true);
                } else {
                    policy.CreditorsStatementPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), supplierNameTxt.getText().toString(), false);
                }
                //   this.dispose();
            }
            break;

            case 68: {
                com.afrisoftech.reports.InvPerCreditorsPdf policy = new com.afrisoftech.reports.InvPerCreditorsPdf();

                policy.InvPerCreditorsPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), supplierNameTxt.getText().toString());

                //   this.dispose();
            }
            break;

            case 69: {
                com.afrisoftech.reports.PaymentsPerCreditorsPdf policy = new com.afrisoftech.reports.PaymentsPerCreditorsPdf();

                policy.PaymentsPerCreditorsPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), supplierNameTxt.getText().toString());

                //   this.dispose();
            }
            break;
            /*           case 181:
                
             {
             com.afrisoftech.reports.CreditorsInvSummPdf policy = new com.afrisoftech.reports.CreditorsInvSummPdf();
                
             policy.CreditorsInvSummPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(),jTextField9.getText().toString());
                
                
             // this.dispose();
                
             } break;
             */

            case 180: {
                com.afrisoftech.reports.CreditorsStmtPdf policy = new com.afrisoftech.reports.CreditorsStmtPdf();

                policy.CreditorsStmtPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), supplierNameTxt.getText().toString());

                //   this.dispose();
            }
            break;

            case 1102: {
                com.afrisoftech.hospinventory.OrdersSummBySuppPdf policy = new com.afrisoftech.hospinventory.OrdersSummBySuppPdf();

                policy.OrdersSummBySuppPdf(connectDB, this.datePicker1.getDate(), this.datePicker2.getDate(), supplierNameTxt.getText().toString());

                //     this.dispose();
            }
            break;

            case 17: {
                com.afrisoftech.accounting.GeneratedVoucherCrdNotePdf voucher = new com.afrisoftech.accounting.GeneratedVoucherCrdNotePdf();
                voucher.GeneratedVoucherCrdNotePdf(connectDB, lpoNo, supplierNameTxt.getText(), lpoNumberTxt.getText(), VoucherNo);

                this.dispose();

            }
            break;

            /*  case 10:
  
             {
             com.afrisoftech.sacco.MemberStatementPdf policy = new com.afrisoftech.sacco.MemberStatementPdf();
  
             policy.MemberStatementPdf(connectDB, this.beginDateSpinner.getValue().toString(), this.endDateSpinner.getValue().toString(),this.jTextField9.getText().toString());
  
             this.dispose();
  
             }*/
            default:
                ;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox byInvoiceDateChbx;
    private javax.swing.JCheckBox byTransactionDateChbx;
    private com.afrisoftech.lib.DatePicker datePicker1;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton511;
    private javax.swing.JButton jButton52;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JDialog jSearchDialog11;
    private javax.swing.JDialog jSearchDialog2;
    private javax.swing.JPanel jSearchPanel11;
    private javax.swing.JPanel jSearchPanel2;
    private javax.swing.JScrollPane jSearchScrollPane11;
    private javax.swing.JScrollPane jSearchScrollPane2;
    private javax.swing.JTable jSearchTable11;
    private javax.swing.JTable jSearchTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11111;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField lpoNumberTxt;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton searchButton1;
    private javax.swing.JTextField supplierNameTxt;
    // End of variables declaration//GEN-END:variables
}
