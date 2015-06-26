/*
 * Requisitions.java
 *
 * Created on April 22, 2003, 5:12 PM
 */
package com.afrisoftech.hospinventory;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ImprestDirectPurchintfr extends javax.swing.JInternalFrame {

    javax.swing.JComboBox cmbox = null;
    javax.swing.JSpinner dateSpinner = null;
    double resVal1 = 0.00;
    double resVal11 = 0.00;
    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    javax.swing.table.TableModel tableModel;
    private boolean uprices;

    public ImprestDirectPurchintfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

        connectDB = connDb;

        pConnDB = pconnDB;

        initComponents();
        jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct staff_veh, payee,voucher_no from ac_petty_cash where (voucher_no !='' and (voucher_no ilike '%PETT%') or (voucher_no ilike '%S_IMPREST%' )or (voucher_no ilike '%T_IMPREST%')) and status != 'Payment Done' GROUP BY staff_veh, payee,voucher_no having sum(debit-credit) >0  ORDER BY 1"));
        //select distinct voucher_no from ac_petty_cash where (voucher_no !='' and voucher_no ilike 'T_IMPREST%' or voucher_no ilike 'PETT%') and status != 'Payment Done'  GROUP BY voucher_no having sum(debit-credit)> 0 ORDER BY 1
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        itemSearchDialog = new javax.swing.JDialog();
        itemSearchPanel = new javax.swing.JPanel();
        itemSearchTxt = new javax.swing.JTextField();
        itemSearchJscrl = new javax.swing.JScrollPane();
        iteamSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton9 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        employeeSearchDialog = new javax.swing.JDialog();
        employeeSearchPanel = new javax.swing.JPanel();
        employeeDetailTxt = new javax.swing.JTextField();
        employeeSearchJscrl = new javax.swing.JScrollPane();
        employeeDetailSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton42 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jSearchDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField111 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton43 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        imprestHolderLbl = new javax.swing.JLabel();
        imprestNumberLbl = new javax.swing.JLabel();
        imprestNumberTxt = new javax.swing.JTextField();
        receivedByLbl = new javax.swing.JLabel();
        receivedByTxt = new javax.swing.JTextField();
        storeNameLbl = new javax.swing.JLabel();
        deliveredBytLbl = new javax.swing.JLabel();
        deliveredByTxt = new javax.swing.JTextField();
        surrenderDatePicker = new com.afrisoftech.lib.DatePicker();
        surrenderDatelbl = new javax.swing.JLabel();
        storeNameCmbx = new javax.swing.JComboBox();
        jPanel41 = new javax.swing.JPanel();
        imprestHolderNameTxt = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        payableRdbtn = new javax.swing.JRadioButton();
        imprestRdbtn = new javax.swing.JRadioButton();
        donationsRdbtn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        imprestHolderNumberTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        unsurrenderedAmtTxt = new javax.swing.JTextField();
        jPanel65 = new javax.swing.JPanel();
        voucherNoSrchTxt = new javax.swing.JTextField();
        searchButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        surrenderBtn = new javax.swing.JButton();
        surrenderPrintBtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        exitbtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        removeRowBtn = new javax.swing.JButton();
        helpBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        surrenderDetailsPanel = new javax.swing.JPanel();
        surrenderDetailsJscrl = new javax.swing.JScrollPane();
        imprestSurrenderTabletxt = new com.afrisoftech.dbadmin.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        grossValueTxt = new javax.swing.JTextField();
        mainStoreChkbx = new javax.swing.JCheckBox();
        subStoreChkbx = new javax.swing.JCheckBox();
        jTextField6 = new javax.swing.JTextField();

        itemSearchDialog.setModal(true);
        itemSearchDialog.setUndecorated(true);
        itemSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        itemSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        itemSearchPanel.setLayout(new java.awt.GridBagLayout());

        itemSearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                itemSearchTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        itemSearchPanel.add(itemSearchTxt, gridBagConstraints);

        iteamSearchTable.setShowHorizontalLines(false);
        /*    try {
            searchRowSet.setCommand("select product,selling_price,gl_code FROM st_stock_prices WHERE department = 'Pharmacy' order by product");
            searchRowSet.setConnectionSource(pConnDB);

            searchRowSet.execute();

            // crset2.setExecuteOnLoad(true);
            iteamSearchTable.setModel(new org.netbeans.lib.sql.models.TableModel(searchRowSet, new org.netbeans.lib.sql.models.TableModel.Column[] {
                new org.netbeans.lib.sql.models.TableModel.Column("product", "Description", false),
                new org.netbeans.lib.sql.models.TableModel.Column("selling_price", "Amount", false),
                new org.netbeans.lib.sql.models.TableModel.Column("gl_code", "Gl_code", false)

            }));
            // itemSearchJscrl.setViewportView(iteamSearchTable);

        } catch(java.sql.SQLException sqlex){
            javax.swing.JOptionPane.showMessageDialog(this,sqlex.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sqlex.getMessage());
        }
        */
        iteamSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iteamSearchTableMouseClicked(evt);
            }
        });
        itemSearchJscrl.setViewportView(iteamSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        itemSearchPanel.add(itemSearchJscrl, gridBagConstraints);

        jButton9.setText("Dispose");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        itemSearchPanel.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        itemSearchDialog.getContentPane().add(itemSearchPanel, gridBagConstraints);

        employeeSearchDialog.setModal(true);
        employeeSearchDialog.setUndecorated(true);
        employeeSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        employeeSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        employeeSearchPanel.setLayout(new java.awt.GridBagLayout());

        employeeDetailTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                employeeDetailTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        employeeSearchPanel.add(employeeDetailTxt, gridBagConstraints);

        employeeDetailSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
        employeeDetailSearchTable.setShowHorizontalLines(false);
        /*javax.swing.table.TableColumn column = null;

        for (int i = 0; i < 4; i++) {

            column = employeeDetailSearchTable.getColumnModel().getColumn(i);

            if (i == 1) {

                column.setPreferredWidth(400);
                //sport column is bigger
            } else {

                column.setPreferredWidth(200);

            }
        }
        */
        employeeDetailSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeDetailSearchTableMouseClicked(evt);
            }
        });
        employeeSearchJscrl.setViewportView(employeeDetailSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        employeeSearchPanel.add(employeeSearchJscrl, gridBagConstraints);

        jButton42.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        employeeSearchPanel.add(jButton42, gridBagConstraints);

        jButton52.setText("Close");
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
        employeeSearchPanel.add(jButton52, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        employeeSearchDialog.getContentPane().add(employeeSearchPanel, gridBagConstraints);

        jSearchDialog.setModal(true);
        jSearchDialog.setUndecorated(true);
        jSearchDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        jTextField111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField111CaretUpdate(evt);
            }
        });
        jTextField111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField111ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel.add(jTextField111, gridBagConstraints);

        jSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable.setShowHorizontalLines(false);
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
        jSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTableMouseClicked(evt);
            }
        });
        jSearchScrollPane.setViewportView(jSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel.add(jSearchScrollPane, gridBagConstraints);

        jButton43.setText("Select");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton43, gridBagConstraints);

        jButton53.setText("Dispose");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton53, gridBagConstraints);

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
        setTitle("Imprest Surrenders/Purchasing");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        /*        javax.swing.SpinnerDateModel spinerDate = new javax.swing.SpinnerDateModel();
        dateSpinner = new javax.swing.JSpinner(spinerDate);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel1.add(dateSpinner, gridBagConstraints);
        */
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        imprestHolderLbl.setText("Imprest Holder Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(imprestHolderLbl, gridBagConstraints);

        imprestNumberLbl.setText("Imprest Serial No.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(imprestNumberLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(imprestNumberTxt, gridBagConstraints);

        receivedByLbl.setText("Received By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(receivedByLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(receivedByTxt, gridBagConstraints);

        storeNameLbl.setText("Store Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(storeNameLbl, gridBagConstraints);

        deliveredBytLbl.setText("Delivered By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(deliveredBytLbl, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(deliveredByTxt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        jPanel1.add(surrenderDatePicker, gridBagConstraints);

        surrenderDatelbl.setText("Invoice Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(surrenderDatelbl, gridBagConstraints);

        storeNameCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeNameCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(storeNameCmbx, gridBagConstraints);

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel41.setLayout(new java.awt.GridBagLayout());

        imprestHolderNameTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel41.add(imprestHolderNameTxt, gridBagConstraints);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton.setToolTipText("Search");
        searchButton.setEnabled(false);
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel41, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridBagLayout());

        buttonGroup2.add(payableRdbtn);
        payableRdbtn.setText("Payable");
        payableRdbtn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(payableRdbtn, gridBagConstraints);

        buttonGroup2.add(imprestRdbtn);
        imprestRdbtn.setSelected(true);
        imprestRdbtn.setText("Imprest Purchases");
        imprestRdbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprestRdbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(imprestRdbtn, gridBagConstraints);

        buttonGroup2.add(donationsRdbtn);
        donationsRdbtn.setText("Donations");
        donationsRdbtn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(donationsRdbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jPanel5, gridBagConstraints);

        jLabel2.setText("Imprest Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel4.setText("Staff Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        imprestHolderNumberTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(imprestHolderNumberTxt, gridBagConstraints);

        jLabel9.setText("Unsurrendered Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        unsurrenderedAmtTxt.setEditable(false);
        unsurrenderedAmtTxt.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        unsurrenderedAmtTxt.setForeground(new java.awt.Color(153, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(unsurrenderedAmtTxt, gridBagConstraints);

        jPanel65.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel65.setMinimumSize(new java.awt.Dimension(24, 25));
        jPanel65.setLayout(new java.awt.GridBagLayout());

        voucherNoSrchTxt.setEditable(false);
        voucherNoSrchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucherNoSrchTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel65.add(voucherNoSrchTxt, gridBagConstraints);

        searchButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        searchButton5.setToolTipText("Search patient name");
        searchButton5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchButton5.setMaximumSize(new java.awt.Dimension(74, 53));
        searchButton5.setMinimumSize(new java.awt.Dimension(74, 53));
        searchButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        jPanel65.add(searchButton5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(jPanel65, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 4.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        surrenderBtn.setMnemonic('s');
        surrenderBtn.setText("Save Imprest Surrender");
        surrenderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surrenderBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(surrenderBtn, gridBagConstraints);

        surrenderPrintBtn.setMnemonic('s');
        surrenderPrintBtn.setText("Save Imprest Surrender & Print");
        surrenderPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surrenderPrintBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(surrenderPrintBtn, gridBagConstraints);

        cancelbtn.setMnemonic('l');
        cancelbtn.setText("Clear form");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(cancelbtn, gridBagConstraints);

        exitbtn.setMnemonic('c');
        exitbtn.setText("Close");
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(exitbtn, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jLabel5, gridBagConstraints);

        removeRowBtn.setMnemonic('r');
        removeRowBtn.setText("Remove Row");
        removeRowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRowBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(removeRowBtn, gridBagConstraints);

        helpBtn.setMnemonic('h');
        helpBtn.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(helpBtn, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jLabel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        surrenderDetailsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        surrenderDetailsPanel.setLayout(new java.awt.GridBagLayout());

        imprestSurrenderTabletxt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Strength", "Unit Pack", "Qty", "Unit Price", "Disc %", "Disc. Total", "Vat %", "Vat Amt.", "Value", "Batch No.", "Exp. Date", "Item Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, false, true, false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        imprestSurrenderTabletxt.setGridColor(new java.awt.Color(204, 204, 255));
        cmbox = new javax.swing.JComboBox();
        //try{
            /*      crset2.setConnectionSource(pConnDB);
            crset2.setCommand("select distinct description FROM st_stock_item order by description");

            cmbox.setModel(new org.netbeans.lib.sql.models.ComboBoxModel(crset2, "description", null, null, null));
            javax.swing.table.TableColumn s = this.jTable1.getColumn("Item");
            s.setCellEditor(new javax.swing.DefaultCellEditor(cmbox));
            cmbox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cmboxActionPerformed(evt);
                }
            });
            //      } catch(java.sql.SQLException sqlExec) {

            //        javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());

            //   }
        */
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < 13; i++) {
            column = imprestSurrenderTabletxt.getColumnModel().getColumn(i);
            if (i == 0) {

                column.setPreferredWidth(500); //sport column is bigger
            } else {
                //if (i == 2) {
                    column.setPreferredWidth(200);
                    // }  else
                //column.setPreferredWidth(50);

            }
        }

        imprestSurrenderTabletxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imprestSurrenderTabletxtMouseClicked(evt);
            }
        });
        imprestSurrenderTabletxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                imprestSurrenderTabletxtKeyReleased(evt);
            }
        });
        surrenderDetailsJscrl.setViewportView(imprestSurrenderTabletxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 50.0;
        surrenderDetailsPanel.add(surrenderDetailsJscrl, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Total Discount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel6, gridBagConstraints);

        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jTextField4, gridBagConstraints);

        jLabel7.setText("VAT Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel7, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jTextField1, gridBagConstraints);

        jLabel8.setText("Net Value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel8, gridBagConstraints);

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jTextField5, gridBagConstraints);

        jLabel1.setText("Gross Value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jLabel1, gridBagConstraints);

        grossValueTxt.setEditable(false);
        grossValueTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        grossValueTxt.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(grossValueTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        surrenderDetailsPanel.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 100.0;
        getContentPane().add(surrenderDetailsPanel, gridBagConstraints);

        buttonGroup1.add(mainStoreChkbx);
        mainStoreChkbx.setText("Main Store");
        mainStoreChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainStoreChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(mainStoreChkbx, gridBagConstraints);

        buttonGroup1.add(subStoreChkbx);
        subStoreChkbx.setText("Sub Store");
        subStoreChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subStoreChkbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(subStoreChkbx, gridBagConstraints);

        jTextField6.setMinimumSize(new java.awt.Dimension(0, 0));
        getContentPane().add(jTextField6, new java.awt.GridBagConstraints());

        setBounds(0, 0, 1095, 450);
    }// </editor-fold>//GEN-END:initComponents

    private void imprestRdbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprestRdbtnActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_imprestRdbtnActionPerformed

    private void employeeDetailSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeDetailSearchTableMouseClicked
        // jTextField1.setText(jSearchTable2.getValueAt(jSearchTable2.getSelectedRow(), 1).toString());
        imprestHolderNameTxt.setText(employeeDetailSearchTable.getValueAt(employeeDetailSearchTable.getSelectedRow(), 0).toString());
        employeeSearchDialog.dispose();
        try {
            java.sql.Statement pstmt = connectDB.createStatement();

            java.sql.ResultSet rs = pstmt.executeQuery("select current_user");
            while (rs.next()) {

                receivedByTxt.setText(rs.getObject(1).toString());
            }
        } catch (java.sql.SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } // Add your handling code here:
    }//GEN-LAST:event_employeeDetailSearchTableMouseClicked

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        this.employeeSearchDialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void employeeDetailTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_employeeDetailTxtCaretUpdate
        if (employeeDetailTxt.getCaretPosition() < 3) {
            System.out.println("Nothing");
        } else {
            employeeDetailSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT first_name||' '||middle_name||' '||last name, employee_no as account_no from master_file where first_name||' '||middle_name||' '||last name ILIKE '%" + employeeDetailTxt.getText() + "%' or employee_no ilike '%" + employeeDetailTxt.getText() + "%' order by 1, 2"));

            employeeDetailSearchTable.setShowHorizontalLines(false);
            employeeSearchJscrl.setViewportView(employeeDetailSearchTable);



        }         // Add your handling code here:
    }//GEN-LAST:event_employeeDetailTxtCaretUpdate
    private void searchButtonClicked() {

        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.imprestHolderNameTxt.getLocationOnScreen();

        employeeSearchDialog.setSize(700, 200);

        employeeSearchDialog.setLocation(point);

        employeeSearchDialog.setVisible(true);



    }
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        for (int k = 0; k < employeeDetailSearchTable.getRowCount(); k++) {
            for (int r = 0; r < employeeDetailSearchTable.getColumnCount(); r++) {
                employeeDetailSearchTable.getModel().setValueAt(null, k, r);
            }
        }
        this.employeeDetailTxt.setText("");
        searchButtonClicked();

        // Add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void subStoreChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subStoreChkbxActionPerformed
        storeNameCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select stores from store_allocation where user_name = current_user and type ilike 'direct purch%'"));

        storeNameCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeNameCmbxActionPerformed(evt);
            }
        });

        // Add your handling code here:
        // Add your handling code here:
    }//GEN-LAST:event_subStoreChkbxActionPerformed

    private void storeNameCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeNameCmbxActionPerformed
        if (this.mainStoreChkbx.isSelected()) {
            try {
                java.sql.Statement pstmt = connectDB.createStatement();

                java.sql.ResultSet rs = pstmt.executeQuery("select glstock_code from st_stores  where store_name ilike '" + storeNameCmbx.getSelectedItem().toString() + "%'");
                while (rs.next()) {

                    this.jTextField6.setText(rs.getObject(1).toString());
                }
            } catch (java.sql.SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
        } else {
            try {
                java.sql.Statement pstmt = connectDB.createStatement();

                java.sql.ResultSet rs = pstmt.executeQuery("select glstock_code from st_stores  where store_name ilike '" + storeNameCmbx.getSelectedItem().toString() + "%'");
                while (rs.next()) {

                    this.jTextField6.setText(rs.getObject(1).toString());
                }
            } catch (java.sql.SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
        }
        /*
         * if(this.jCheckBox1.isSelected()){ try { java.sql.Statement pstmt =
         * connectDB.createStatement();
         *
         * java.sql.ResultSet rs = pstmt.executeQuery("select gl_stock_code from
         * st_main_stores where store_name ilike
         * '"+jComboBox2.getSelectedItem().toString()+"'"); while (rs.next()){
         *
         * this.jTextField6.setText(rs.getObject(1).toString()); } }
         * catch(java.sql.SQLException sqlex){
         * System.out.println(sqlex.getMessage()); } }else{ try {
         * java.sql.Statement pstmt = connectDB.createStatement();
         *
         * java.sql.ResultSet rs = pstmt.executeQuery("select glstock_code from
         * st_stores where store_name ilike
         * '"+jComboBox2.getSelectedItem().toString()+"'"); while (rs.next()){
         *
         * this.jTextField6.setText(rs.getObject(1).toString()); } }
         * catch(java.sql.SQLException sqlex){
         * System.out.println(sqlex.getMessage()); } }
         */
        // Add your handling code here:
    }//GEN-LAST:event_storeNameCmbxActionPerformed

    private void mainStoreChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainStoreChkbxActionPerformed
        storeNameCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-' UNION select stores from store_allocation where user_name = current_user and type ilike 'direct purch%'"));
        storeNameCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeNameCmbxActionPerformed(evt);
            }
        });
        // Add your handling code here:
    }//GEN-LAST:event_mainStoreChkbxActionPerformed

    private void imprestSurrenderTabletxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imprestSurrenderTabletxtMouseClicked
        if (imprestSurrenderTabletxt.getSelectedColumn() == 0) {

            this.cmboxMouseClicked();
        }         // Add your handling code here:
    }//GEN-LAST:event_imprestSurrenderTabletxtMouseClicked
    private void cmboxMouseClicked() {

        System.out.println("Showing dialog");
        java.awt.Point point = surrenderDetailsJscrl.getLocationOnScreen();
        itemSearchDialog.setSize(700, 200);
        itemSearchDialog.setLocation(point);
        itemSearchDialog.setVisible(true);
    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        itemSearchDialog.dispose();         // Add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void itemSearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_itemSearchTxtCaretUpdate
        //if(this.jComboBox2.getSelectedItem().equals("GENERAL STORE")){
        if (this.itemSearchTxt.getCaretPosition() < 1) {
            System.out.print("Nothing");
        } else {
            iteamSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select DISTINCT description,strength,packaging,buying_price,item_code FROM stockitem WHERE description ILIKE '" + itemSearchTxt.getText() + "%' order by description"));

            /*
             * try { // searchRowSet.execute("select DISTINCT
             * description,units,buying_price,packaging FROM st_stock_item WHERE
             * description ILIKE '"+jTextField111.getText()+"%' AND department =
             * '"+jComboBox2.getSelectedItem().toString()+"' order by
             * description"); searchRowSet.execute("select DISTINCT
             * description,units,buying_price,packaging FROM st_stock_item WHERE
             * description ILIKE '"+jTextField111.getText()+"%' order by
             * description");
             *
             * jSearchTable.setModel(new
             * org.netbeans.lib.sql.models.TableModel(searchRowSet, new
             * org.netbeans.lib.sql.models.TableModel.Column[] { new
             * org.netbeans.lib.sql.models.TableModel.Column("description",
             * "Desc.", false), new
             * org.netbeans.lib.sql.models.TableModel.Column("units", "Units",
             * false), new
             * org.netbeans.lib.sql.models.TableModel.Column("packaging",
             * "Package", false), new
             * org.netbeans.lib.sql.models.TableModel.Column("buying_price",
             * "Buying Price", false)
             *
             * }));
             */
            itemSearchJscrl.setViewportView(iteamSearchTable);
            System.out.println("Cannot sort out");

        }
        // Add your handling code here:
    }//GEN-LAST:event_itemSearchTxtCaretUpdate

    private void iteamSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iteamSearchTableMouseClicked
        imprestSurrenderTabletxt.setValueAt(iteamSearchTable.getValueAt(iteamSearchTable.getSelectedRow(), 0), imprestSurrenderTabletxt.getSelectedRow(), 0);
        imprestSurrenderTabletxt.setValueAt(iteamSearchTable.getValueAt(iteamSearchTable.getSelectedRow(), 1), imprestSurrenderTabletxt.getSelectedRow(), 1);
        imprestSurrenderTabletxt.setValueAt(iteamSearchTable.getValueAt(iteamSearchTable.getSelectedRow(), 2), imprestSurrenderTabletxt.getSelectedRow(), 2);
        imprestSurrenderTabletxt.setValueAt(iteamSearchTable.getValueAt(iteamSearchTable.getSelectedRow(), 3), imprestSurrenderTabletxt.getSelectedRow(), 4);
        imprestSurrenderTabletxt.setValueAt(iteamSearchTable.getValueAt(iteamSearchTable.getSelectedRow(), 4), imprestSurrenderTabletxt.getSelectedRow(), 12);

        imprestSurrenderTabletxt.setValueAt(new java.lang.Double(0), imprestSurrenderTabletxt.getSelectedRow(), 5);
        imprestSurrenderTabletxt.setValueAt(new java.lang.Double(0), imprestSurrenderTabletxt.getSelectedRow(), 7);
        imprestSurrenderTabletxt.setValueAt(new java.lang.Double(0), imprestSurrenderTabletxt.getSelectedRow(), 6);
        imprestSurrenderTabletxt.setValueAt(new java.lang.Double(0), imprestSurrenderTabletxt.getSelectedRow(), 9);
        //  jTable1.setValueAt(new java.lang.Double(1), jTable1.getSelectedRow(), 2);
        int j = 0;


        try {
            java.sql.Statement pstmt = connectDB.createStatement();
            java.sql.Statement pstmt1 = connectDB.createStatement();

            java.sql.ResultSet rs1 = pstmt1.executeQuery("select count(product) from st_stock_prices sc where product = '" + iteamSearchTable.getValueAt(iteamSearchTable.getSelectedRow(), 0) + "'");
            while (rs1.next()) {
                j = rs1.getInt(1);
            }
            // if (j > 0){

            //   java.sql.ResultSet rs = pstmt.executeQuery("select sum(transfer_price)::numeric from st_stock_prices sc where product = '"+jSearchTable.getValueAt(jSearchTable.getSelectedRow(),0)+"'");
            //   while (rs.next()){

            //     jTable1.setValueAt(,jTable1.getSelectedRow(), 6);
            //   jTable1.setValueAt(rs.getObject(1),jTable1.getSelectedRow(),3);

            //  }
            // } else {
            //   jTable1.setValueAt("0.00",jTable1.getSelectedRow(),2);
            //  }
        } catch (java.sql.SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }


        itemSearchDialog.dispose();
        itemSearchTxt.setText("");
        // Add your handling code here:
    }//GEN-LAST:event_iteamSearchTableMouseClicked
 private void clear(){
     imprestHolderNameTxt.setText("");
     imprestHolderNumberTxt.setText("");
     unsurrenderedAmtTxt.setText(java.lang.String.valueOf(0.00));
     voucherNoSrchTxt.setText("");
     receivedByTxt.setText("");
     deliveredByTxt.setText("");
     imprestNumberTxt.setText("");
 }
    private void surrenderPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surrenderPrintBtnActionPerformed
        double quantity = 0.00;
        double price = 0.00;
        double pkge = 0.00;
        double qty = 0.00;
        double uprice = 0.00;
        double rates = 0.00;

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        long dateNow = calendar.getTimeInMillis();

        java.sql.Date datenowSql = new java.sql.Date(dateNow);

        String Stock = null;
        String actCode = null;
        String accountCode = null;
        String transNo = null;
        String glType = null;
        String VouchNo = null;
        String StocktransNo = null;

        int invoices = 0;
        try {

            surrenderBtnActionPerformed(evt);
            //  connectDB.setAutoCommit(false);
            //   if(this.jComboBox2.getSelectedItem().toString().equalsIgnoreCase("main store")){
            java.sql.Statement pst2 = connectDB.createStatement();
            java.sql.ResultSet rs = pst2.executeQuery("select code,activity from pb_activity where code ilike '" + this.jTextField6.getText() + "%'");
            while (rs.next()) {
                actCode = rs.getObject(1).toString();
                Stock = rs.getObject(2).toString();
            }
            java.sql.Statement pst1 = connectDB.createStatement();
            java.sql.ResultSet rs1 = pst1.executeQuery("select code,activity from pb_activity where activity_category = 'CP'");
            while (rs1.next()) {
                accountCode = rs1.getObject(1).toString();
                glType = rs1.getObject(2).toString();
            }
            // com.afrisoftech.reports.DiscCreditorsInvoicesPdf policy = new com.afrisoftech.reports.DiscCreditorsInvoicesPdf();
            // policy.DiscCreditorsInvoicesPdf(connectDB, this.datePicker1.getDate().toLocaleString(), this.datePicker1.getDate().toLocaleString(),jTextField91.getText().toString(),this.jTextField7.getText().toString());

            com.afrisoftech.hospinventory.mtrhreports.SthirteenPdf policy = new com.afrisoftech.hospinventory.mtrhreports.SthirteenPdf();
            policy.SthirteenPdf(connectDB, this.surrenderDatePicker.getDate().toLocaleString(), this.surrenderDatePicker.getDate().toLocaleString(), imprestHolderNameTxt.getText().toString(), this.imprestNumberTxt.getText().toString(),transNo);

            //       jTextField7.setText("");
            deliveredByTxt.setText("");
            grossValueTxt.setText("00");
            jTextField4.setText("00");
            jTextField1.setText("00");
            jTextField5.setText("00");

            //  javax.swing.JOptionPane.showMessageDialog(this, "Enter quantity received","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sq.getMessage());
            try {
                connectDB.rollback();
            } catch (java.sql.SQLException sql) {
                javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

        // Add your handling code here:
    }//GEN-LAST:event_surrenderPrintBtnActionPerformed

    private void imprestSurrenderTabletxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imprestSurrenderTabletxtKeyReleased
        double resFloat = 0.00;
        double resVal = 0.00;
        double total = 0.00;
        double total1 = 0.00;
        double discount1 = 0.00;
        double vat = 0.00;
        double VatAmt = 0.00;
        double floatCol2 = 0.00;
        double floatCol3 = 0.00;
        //  double totalSum = 0.00;
        if (imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), imprestSurrenderTabletxt.getSelectedColumn()) != null) {


            floatCol2 = java.lang.Double.parseDouble(imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 3).toString());

            floatCol3 = java.lang.Double.parseDouble(imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 4).toString());

            //            if (jTable1.getValueAt(jTable1.getSelectedRow(), 3) != null) {
            total = floatCol2 * floatCol3;
            //                jTable1.setValueAt(new java.lang.Float(total), jTable1.getSelectedRow(), 7);


            if (imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 5) != null) {
                discount1 = java.lang.Double.parseDouble(imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 5).toString());

                resVal = total * discount1 / 100;
                // total = ((floatCol2 * floatCol3) - ((floatCol2 * floatCol3)*discount1/100));
                // resVal = (floatCol2 * floatCol3)*discount1/100;
                imprestSurrenderTabletxt.setValueAt(new java.lang.Float(resVal), imprestSurrenderTabletxt.getSelectedRow(), 6);
                imprestSurrenderTabletxt.setValueAt(new java.lang.Float(total - resVal), imprestSurrenderTabletxt.getSelectedRow(), 9);

                this.tableModelTableChanged1();

                if (imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 7) != null) {
                    vat = java.lang.Double.parseDouble(imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 7).toString());
                    // total = (((total) + ((floatCol2 * floatCol3)*vat/100)) - ((floatCol2 * floatCol3)*discount1/100));

                    VatAmt = (total - resVal) * vat / 100;
                    imprestSurrenderTabletxt.setValueAt(new java.lang.Float(VatAmt), imprestSurrenderTabletxt.getSelectedRow(), 8);
                    imprestSurrenderTabletxt.setValueAt(new java.lang.Float(resVal), imprestSurrenderTabletxt.getSelectedRow(), 6);
                    imprestSurrenderTabletxt.setValueAt(new java.lang.Float((total - resVal) + VatAmt), imprestSurrenderTabletxt.getSelectedRow(), 9);

                    this.tableModelTableChanged2();
                    //jTable1.setValueAt(new java.lang.Float(resVal), jTable1.getSelectedRow(), 5);
                }

            } else if (imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 7) != null) {
                vat = java.lang.Double.parseDouble(imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 7).toString());

                total1 = ((floatCol2 * floatCol3));

                total = ((floatCol2 * floatCol3) + ((floatCol2 * floatCol3) * vat / 100));
                VatAmt = ((floatCol2 * floatCol3) * vat / 100);
                imprestSurrenderTabletxt.setValueAt(new java.lang.Float(VatAmt), imprestSurrenderTabletxt.getSelectedRow(), 8);

                //this.jTextField1.setText(java.lang.String.valueOf(VatAmt));
                imprestSurrenderTabletxt.setValueAt(new java.lang.Float(total), imprestSurrenderTabletxt.getSelectedRow(), 9);
                this.tableModelTableChanged2();

            } else //  }else
            if (imprestSurrenderTabletxt.getValueAt(imprestSurrenderTabletxt.getSelectedRow(), 7) != null) {
                imprestSurrenderTabletxt.setValueAt(new java.lang.Float(total), imprestSurrenderTabletxt.getSelectedRow(), 9);
            }
            this.tableModelTableChanged();


            for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

                if (imprestSurrenderTabletxt.getModel().getValueAt(i, 0) != null) {//toString().compareToIgnoreCase(null) {

                    resFloat = resFloat + Double.parseDouble(imprestSurrenderTabletxt.getValueAt(i, 9).toString());


                }
            }
        }
        double net = Double.parseDouble(this.grossValueTxt.getText());
        double vats = Double.parseDouble(this.jTextField1.getText());
        jTextField5.setText(java.lang.String.valueOf(net - vats));

        // Add your handling code here:
    }//GEN-LAST:event_imprestSurrenderTabletxtKeyReleased

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
        this.setVisible(false);        // Add your handling code here:
    }//GEN-LAST:event_exitbtnActionPerformed

    private void removeRowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowBtnActionPerformed
        int rows2Delete = imprestSurrenderTabletxt.getSelectedRowCount();

        int[] selectedRows = imprestSurrenderTabletxt.getSelectedRows();

        if (rows2Delete < 1) {

            java.awt.Toolkit.getDefaultToolkit().beep();

            javax.swing.JOptionPane.showMessageDialog(this, "There are no selected rows to delete!");

        } else {

            if (rows2Delete > 1) {

                for (int i = 0; i < selectedRows.length; i++) {



                    javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) imprestSurrenderTabletxt.getModel();

                    defTableModel.removeRow(selectedRows[i]);

                }



            } else {

                javax.swing.table.DefaultTableModel defTableModel = (javax.swing.table.DefaultTableModel) imprestSurrenderTabletxt.getModel();

                defTableModel.removeRow(imprestSurrenderTabletxt.getSelectedRow());
            }
        }
        tableModelTableChanged();
        tableModelTableChanged1();
        tableModelTableChanged2();

        // Add your handling code here:
    }//GEN-LAST:event_removeRowBtnActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        //        jTextField6.setText("");
        imprestNumberTxt.setText("");
        //  jTextField9.setText("");
        deliveredByTxt.setText("");
        grossValueTxt.setText("00");
        jTextField4.setText("00");
        jTextField1.setText("00");
        jTextField5.setText("00");
        for (int k = 0; k < imprestSurrenderTabletxt.getRowCount(); k++) {
            for (int r = 0; r < imprestSurrenderTabletxt.getColumnCount(); r++) {
                imprestSurrenderTabletxt.getModel().setValueAt(null, k, r);
            }
        }

        grossValueTxt.setText("0.00");
        // Add your handling code here:
    }//GEN-LAST:event_cancelbtnActionPerformed
    private void cmboxActionPerformed(java.awt.event.ActionEvent evt) {
        java.lang.Object selectedGuaran = cmbox.getSelectedItem();
        int i = imprestSurrenderTabletxt.getSelectedRow();

        if (selectedGuaran != null) {

            try {
                //java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","pilsiner");
                java.sql.Statement pstmt = connectDB.createStatement();
                // java.sql.ResultSet rs = pstmt.executeQuery("select rate,gl_account from pb_operating_parameters where service_type = '"+selectedGuaran+"'");

                java.sql.ResultSet rs = pstmt.executeQuery("select units from stockitem where description = '" + selectedGuaran + "'");
                while (rs.next()) {

                    imprestSurrenderTabletxt.setValueAt(rs.getObject(1), i, 1);

                }
            } catch (java.sql.SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }

        }

    }

    private void surrenderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surrenderBtnActionPerformed

        java.util.Date periodFrom = null;
        java.util.Date periodTo = null;

        try {

            java.sql.Statement stmtf = connectDB.createStatement();
            java.sql.ResultSet rsetf = stmtf.executeQuery("SELECT period_from,period_to FROM period_setup WHERE period_status ilike 'Open' AND '" + surrenderDatePicker.getDate() + "' BETWEEN period_from AND period_to");
            while (rsetf.next()) {
                periodFrom = rsetf.getDate(1);
                periodTo = rsetf.getDate(2);
            }

        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(sq.getMessage());

        }

        if (surrenderDatePicker.getDate().before(periodFrom) || surrenderDatePicker.getDate().after(periodTo)) {
            javax.swing.JOptionPane.showMessageDialog(this, "You cannot save before or after the accounting period set \n Contact head of accounts".toUpperCase(), "Caution Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {
            if (imprestSurrenderTabletxt.isEditing()) {
                imprestSurrenderTabletxt.getCellEditor().stopCellEditing();
            }
            imprestSurrenderTabletxt.setEditingRow(-1);

            tableModelTableChanged();
            tableModelTableChanged1();
            tableModelTableChanged2();
            double quantity = 0.00;
            double price = 0.00;
            double pkge = 1.00;
            double qty = 0.00;
            double uprice = 0.00;
            double rates = 0.00;

            java.util.Calendar calendar = java.util.Calendar.getInstance();

            long dateNow = calendar.getTimeInMillis();

            java.sql.Date datenowSql = new java.sql.Date(dateNow);
            try {
                String Stock = null;
                String actCode = null;
                String accountCode = null;
                String glType = null;
                String transNo = null;
                String accountCoded = null;
                String glTyped = null;
                String units = null;
                String StocktransNo = null;
                int invoices = 0;
                String supCode = null;
                String Purchases = null;
                String cosCode = null;
                String CostofSale = null;
                String imprestPrincipalHolder = null;
                String department = null;

                java.sql.Savepoint registerSavePoint = null;
                try {
                    connectDB.setAutoCommit(false);
                    registerSavePoint = connectDB.setSavepoint("registration");
                } catch (java.sql.SQLException ex) {
                    ex.printStackTrace();
                }

                try {

                    //   if(this.jComboBox2.getSelectedItem().toString().equalsIgnoreCase("main store")){
                    java.sql.Statement pst2 = connectDB.createStatement();
                    java.sql.ResultSet rs = pst2.executeQuery("select code,activity from pb_activity where code ilike '" + this.jTextField6.getText() + "%'");
                    while (rs.next()) {
                        actCode = rs.getObject(1).toString();
                        Stock = rs.getObject(2).toString();
                    }
                    java.sql.Statement pst1 = connectDB.createStatement();
                    java.sql.ResultSet rs1 = pst1.executeQuery("select activity_code,description,requisitioner, department from ac_petty_cash where voucher_no = '"+voucherNoSrchTxt.getText().toString()+"'");
                    while (rs1.next()) {
                        accountCode = rs1.getString(1);
                        glType = rs1.getString(2);
                        imprestPrincipalHolder = rs1.getString(3);
                        department = rs1.getString(4);
                    }

                    java.sql.Statement pst1i = connectDB.createStatement();
                    java.sql.ResultSet rs1i = pst1i.executeQuery("select code,activity from pb_activity where activity_category ILIKE 'GRN'");
                    while (rs1i.next()) {
                        accountCoded = rs1i.getObject(1).toString();
                        glTyped = rs1i.getObject(2).toString();
                    }
                    java.sql.Statement ps = connectDB.createStatement();
                    java.sql.Statement ps22 = connectDB.createStatement();
                    java.sql.Statement ps11 = connectDB.createStatement();
                    java.sql.Statement ps11r = connectDB.createStatement();
                    java.sql.ResultSet rst = ps11.executeQuery("select nextval('transaction_no_seq')");
                    while (rst.next()) {
                        rst.getObject(1).toString();

                        transNo = rst.getObject(1).toString();
                    }


                    java.sql.Statement pst21 = connectDB.createStatement();
                    java.sql.ResultSet rs111 = pst21.executeQuery("select nextval('stock_delivery_seq')");
                    while (rs111.next()) {
                        StocktransNo = rs111.getObject(1).toString();
                    }

                    java.sql.Statement pstmt1t = connectDB.createStatement();
                    java.sql.ResultSet rs1t = pstmt1t.executeQuery("select update_prices from st_stock_control"); //from orders where supplier ='"+jTable1.getValueAt(i,4).toString()+"'");

                    while (rs1t.next()) {

                        uprices = rs1t.getBoolean(1);
                    }
                    for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {
                        if (imprestSurrenderTabletxt.getValueAt(i, 9) != null) {
                            java.sql.Statement pst21r = connectDB.createStatement();
                            //         java.sql.ResultSet rstr = ps11r.executeQuery("SELECT mark_up FROM st_stores WHERE store_name ILIKE '"+jComboBox2.getSelectedItem().toString()+"'");
                            java.sql.ResultSet rstrs = pst21r.executeQuery("SELECT units FROM stockitem st WHERE st.item_code = '" + imprestSurrenderTabletxt.getValueAt(i, 12).toString() + "'");
                            while (rstrs.next()) {
                                units = rstrs.getString(1);
                            }

                            java.sql.ResultSet rstr = ps11r.executeQuery("SELECT mc.markups FROM  st_main_category mc,stockitem st WHERE st.department ILIKE '" + storeNameCmbx.getSelectedItem().toString() + "' AND mc.description = st.sub_cat_code AND st.item_code = '" + imprestSurrenderTabletxt.getValueAt(i, 12).toString() + "'");
                            while (rstr.next()) {
                                if (rstr.getDouble(1) > 0) {
                                    rates = rstr.getDouble(1);
                                } else {
                                    rates = 1;
                                }
                            }


                            pkge = Double.parseDouble(imprestSurrenderTabletxt.getValueAt(i, 2).toString());

                            qty = Double.parseDouble(imprestSurrenderTabletxt.getValueAt(i, 3).toString());

                            uprice = Double.parseDouble(imprestSurrenderTabletxt.getValueAt(i, 4).toString());

                            quantity = qty * pkge;

                            price = uprice / pkge;

                            System.out.println("New price = [" + price + "]");

                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into st_stock_cardex values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");

                            pstmt.setString(1, voucherNoSrchTxt.getText().toString());
                            pstmt.setString(2, storeNameCmbx.getSelectedItem().toString());
                            pstmt.setObject(3, imprestSurrenderTabletxt.getValueAt(i, 0));
                            pstmt.setDate(4, null);
                            pstmt.setDouble(5, 0.00);
                            pstmt.setDouble(6, price);
                            pstmt.setObject(7, units);
                            pstmt.setString(8, receivedByTxt.getText());
                            pstmt.setString(9, deliveredByTxt.getText());
                            pstmt.setString(10, "");
                            pstmt.setDouble(11, quantity);
                            pstmt.setDouble(12, 0.00);
                            pstmt.setDouble(13, java.lang.Double.valueOf(imprestSurrenderTabletxt.getValueAt(i, 6).toString()));
                            pstmt.setString(14, "");
                            pstmt.setString(15, "");
                            pstmt.setObject(16, imprestHolderNameTxt.getText());
                            pstmt.setString(17, StocktransNo);
                            pstmt.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(surrenderDatePicker.getDate()));
                            pstmt.setString(19, "");
                            pstmt.setString(20, "Receiving");
                            pstmt.setDouble(21, java.lang.Double.valueOf(imprestSurrenderTabletxt.getValueAt(i, 9).toString()));
                            pstmt.setDouble(22, java.lang.Double.valueOf(imprestSurrenderTabletxt.getValueAt(i, 6).toString()));
                            pstmt.setString(23, jTextField6.getText());
                            if (imprestNumberTxt.getText().equals("")) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Invoice_no Missing", "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {

                                pstmt.setString(24, imprestNumberTxt.getText());
                            }
                            pstmt.setString(25, storeNameCmbx.getSelectedItem().toString());
                            pstmt.setString(26, transNo);
                            pstmt.setDouble(27, 0.00);
                            pstmt.setString(28, receivedByTxt.getText());
                            pstmt.setBoolean(29, false);
                            pstmt.setDouble(30, java.lang.Double.valueOf(imprestSurrenderTabletxt.getValueAt(i, 7).toString()));
                            pstmt.setObject(31, null);
                            pstmt.setObject(32, imprestSurrenderTabletxt.getValueAt(i, 12));
                            pstmt.setObject(33, imprestSurrenderTabletxt.getValueAt(i, 1));
                            pstmt.setObject(34, StocktransNo);
                            pstmt.executeUpdate();


                            java.sql.PreparedStatement pstmt11 = connectDB.prepareStatement("insert into st_sub_stores values(? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)");
                            pstmt11.setString(1, storeNameCmbx.getSelectedItem().toString());
                            pstmt11.setObject(2, imprestSurrenderTabletxt.getValueAt(i, 0).toString());
                            pstmt11.setDouble(3, quantity);
                            pstmt11.setDouble(4, 0.00);
                            pstmt11.setDouble(5, 0.00);
                            pstmt11.setDouble(6, java.lang.Double.valueOf(imprestSurrenderTabletxt.getValueAt(i, 9).toString()));
                            pstmt11.setDouble(7, 0.00);
                            pstmt11.setObject(8, StocktransNo);
                            pstmt11.setString(9, "-");
                            pstmt11.setDate(10, com.afrisoftech.lib.SQLDateFormat.getSQLDate(surrenderDatePicker.getDate()));
                            pstmt11.setString(11, receivedByTxt.getText());
                            pstmt11.setObject(12, units);
                            pstmt11.setObject(13, imprestHolderNameTxt.getText());
                            pstmt11.setObject(14, imprestHolderNameTxt.getText());
                            pstmt11.setDouble(15, price);
                            pstmt11.setObject(16, imprestSurrenderTabletxt.getValueAt(i, 12));
                            pstmt11.setObject(17, imprestSurrenderTabletxt.getValueAt(i, 1));

                            pstmt11.execute();


                            if (payableRdbtn.isSelected() || imprestRdbtn.isSelected()) {

                                if (uprices) {
                                    java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("UPDATE st_stock_item SET buying_price = '" + imprestSurrenderTabletxt.getValueAt(i, 4).toString() + "',units = '" + imprestSurrenderTabletxt.getValueAt(i, 2).toString() + "'  WHERE item_code ILIKE '" + imprestSurrenderTabletxt.getValueAt(i, 12).toString() + "' AND department ILIKE '" + storeNameCmbx.getSelectedItem().toString() + "%'");
                                    pstmt31.executeUpdate();
                                    java.sql.PreparedStatement pstmt32c = connectDB.prepareStatement("UPDATE st_stock_prices SET transfer_price = round(" + new java.lang.Double(price * rates) + "), selling_price = round('" + (price * 1.33) + "')  WHERE product_id ILIKE '" + imprestSurrenderTabletxt.getValueAt(i, 12).toString() + "'");//  AND department ILIKE '"+jComboBox2.getSelectedItem().toString()+"%'");
                                    pstmt32c.executeUpdate();
                                    System.out.println("Setting price list 1");

                                    System.out.println("Setting price list 2");
                                    if (rates > 1) {
                                        java.sql.PreparedStatement pstmt32 = connectDB.prepareStatement("UPDATE st_stock_prices SET transfer_price = round(" + new java.lang.Double(price * rates) + "),selling_price = round('" + (price * 1.33) + "')  WHERE product_id ILIKE '" + imprestSurrenderTabletxt.getValueAt(i, 12).toString() + "'");//  //AND department ILIKE '"+jComboBox2.getSelectedItem().toString()+"%'");
                                        pstmt32.executeUpdate();
                                    } else {
                                        java.sql.PreparedStatement pstmt32 = connectDB.prepareStatement("UPDATE st_stock_prices SET transfer_price = round(" + new java.lang.Double(price * rates) + "), selling_price = round('" + (price * 1.33) + "') WHERE product_id ILIKE '" + imprestSurrenderTabletxt.getValueAt(i, 12).toString() + "'");//  AND department ILIKE '"+jComboBox2.getSelectedItem().toString()+"%'");
                                        pstmt32.executeUpdate();
                                    }
                                }
                            }
                        }
                    }

                    java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into ac_petty_cash values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");
                    pstmt1.setString(1, accountCode);
                    pstmt1.setString(2, voucherNoSrchTxt.getText().toString());
                    pstmt1.setString(3, imprestHolderNameTxt.getText());
                    pstmt1.setObject(4, glType);
                    pstmt1.setDouble(5, 0.00);
                    pstmt1.setDouble(6, Double.parseDouble(grossValueTxt.getText()));
                    pstmt1.setDate(7, com.afrisoftech.lib.SQLDateFormat.getSQLDate(surrenderDatePicker.getDate()));
                    pstmt1.setString(8, com.afrisoftech.lib.UserName.getLoginName(connectDB));
                    pstmt1.setBoolean(9, true);
                    pstmt1.setString(10, voucherNoSrchTxt.getText().toString());
                    pstmt1.setDate(11, com.afrisoftech.lib.SQLDateFormat.getSQLDate(surrenderDatePicker.getDate()));
                    pstmt1.setBoolean(12, true);
                    pstmt1.setString(13, department);
                    pstmt1.setString(14, imprestPrincipalHolder);
                    pstmt1.setObject(15, "IMPREST_SURRENDER");
                    pstmt1.setObject(16, StocktransNo);
                    pstmt1.setString(17, transNo);
                    pstmt1.setObject(18, "");
                    pstmt1.setString(19, "");
                    pstmt1.setString(20, transNo);
                    pstmt1.setObject(21, "");
                    pstmt1.setString(22, transNo);
                    pstmt1.setObject(23, "-");
                    pstmt1.setObject(24, "-");
                    pstmt1.setObject(25, "-");
                    pstmt1.setObject(26, imprestHolderNumberTxt.getText());
                    pstmt1.execute();

                    connectDB.commit();
                    connectDB.setAutoCommit(true);

                    javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted Successfully", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    for (int k = 0; k < imprestSurrenderTabletxt.getRowCount(); k++) {
                        for (int r = 0; r < imprestSurrenderTabletxt.getColumnCount(); r++) {
                            imprestSurrenderTabletxt.getModel().setValueAt(null, k, r);
                        }
                    }
clear();
                    deliveredByTxt.setText("");
                    grossValueTxt.setText("00");
                    jTextField4.setText("00");
                    jTextField1.setText("00");
                    jTextField5.setText("00");

                    //  javax.swing.JOptionPane.showMessageDialog(this, "Enter quantity received","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

                } catch (java.sql.SQLException sq) {
                    javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

                    System.out.println(sq.getMessage());
                    try {
                        connectDB.rollback(registerSavePoint);
                    } catch (java.sql.SQLException sql) {
                        javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (java.lang.Exception ex) {
                System.out.println(ex.getMessage());
                javax.swing.JOptionPane.showMessageDialog(this, "TRANSACTION ERROR : Please double check your entries.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);


            }
        }
        // Add your handling code here:
    }//GEN-LAST:event_surrenderBtnActionPerformed

    private void voucherNoSrchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucherNoSrchTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_voucherNoSrchTxtActionPerformed

    private void searchButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton5ActionPerformed
        searchButton22Clicked();
        //  this.jButton13.setEnabled(true);

        // Add your handling code here:
    }//GEN-LAST:event_searchButton5ActionPerformed

    private void jTextField111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField111CaretUpdate
        if(this.jTextField111.getCaretPosition() < 1){
            System.out.print("Nothing");
        }else {
            
            jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct staff_veh, payee,voucher_no from ac_petty_cash where (voucher_no !='' and (voucher_no ilike '%PETT%') or (voucher_no ilike '%S_IMPREST%' )or (voucher_no ilike '%T_IMPREST%')) and (payee ILIKE '%" + jTextField111.getText().toString() + "%' or staff_veh ILIKE '%" + jTextField111.getText().toString() + "%')  and status != 'Payment Done' GROUP BY staff_veh, payee,voucher_no having sum(debit-credit) > 0 ORDER BY 1"));
            
            jSearchScrollPane.setViewportView(jSearchTable);
            System.out.println("Cannot sort out");
            
        }           // Add your handling code here:
    }//GEN-LAST:event_jTextField111CaretUpdate

    private void jTextField111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField111ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextField111ActionPerformed

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
        voucherNoSrchTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 2).toString());
        imprestHolderNameTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1).toString());

if (voucherNoSrchTxt.getText().length()>0) {
            try {
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT DISTINCT sum(debit-credit) as bal, payee, staff_veh FROM ac_petty_cash WHERE voucher_no = ? and status != 'Payment Done' group by payee, staff_veh order by staff_veh");
                pstmt.setObject(1, voucherNoSrchTxt.getText());
                java.sql.ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    unsurrenderedAmtTxt.setText(rset.getObject(1).toString());
                    imprestHolderNameTxt.setText(rset.getString(2));
                    imprestHolderNumberTxt.setText(rset.getString(3));
                }
                // TODO add your handling code here:
            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                Logger.getLogger(ImprestDirectPurchintfr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //  this.populateTable1(jTextField9.getText());
        this.jSearchDialog.dispose();     // Add your handling code here:
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        this.jSearchDialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void searchButton22Clicked() {
        
        System.out.println("Showing dialog");
        
        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.imprestHolderNameTxt.getLocationOnScreen();
        
        jSearchDialog.setSize(300,200);
        
        jSearchDialog.setLocation(point);
        
        jSearchDialog.setVisible(true);
        
        
    }
    public void tableModelTableChanged() {
        System.out.println("Calculating totals for table 11 and 2.");
        //        double resFloat = 0.00;
        double resFloat = 0.00;
        double Gross = Double.parseDouble(this.grossValueTxt.getText());
        double Disc = Double.parseDouble(this.jTextField1.getText());
        double Vat = Double.parseDouble(this.jTextField4.getText());
        for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

            if (imprestSurrenderTabletxt.getValueAt(i, 0) != null) {


                //                if (jTable1.getSelectedColumn() == 2) {

                resFloat = resFloat + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 9).toString());
                grossValueTxt.setText(java.lang.String.valueOf(resFloat));
                jTextField5.setText(java.lang.String.valueOf(resFloat - Disc - Vat));
                //                }else{

                //  resFloat = resFloat + Double.parseDouble(jTable1.getModel().getValueAt(i, 7).toString());
                //   jTextField2.setText(java.lang.String.valueOf(resFloat));

                //  }
            }

            //               jTextField31.setText(java.lang.String.valueOf(resFloat));
        }
    }

    public void tableModelTableChanged(javax.swing.event.TableModelEvent evt) {
        double Gross = Double.parseDouble(this.grossValueTxt.getText());
        double Disc = Double.parseDouble(this.jTextField1.getText());
        double Vat = Double.parseDouble(this.jTextField4.getText());
        //        double resFloat = 0.00;
        double resFloat = 0.00;



        for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

            if (imprestSurrenderTabletxt.getValueAt(i, 0) != null) {

                //   if (jTable1.getSelectedColumn() == 3) {

                resFloat = resFloat + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 9).toString());

                grossValueTxt.setText(java.lang.String.valueOf(resFloat));
                jTextField5.setText(java.lang.String.valueOf(resFloat - Disc - Vat));


                //  }else{
                //      resFloat = resFloat + Double.parseDouble(jTable1.getModel().getValueAt(i, 7).toString());

                //      jTextField2.setText(java.lang.String.valueOf(resFloat));

                //}
            }
        }

        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }

    public void tableModelTableChanged1() {
        System.out.println("Calculating totals for table 12 and 21.");
        double Gross = Double.parseDouble(this.grossValueTxt.getText());
        double Disc = Double.parseDouble(this.jTextField1.getText());
        double Vat = Double.parseDouble(this.jTextField4.getText());
        double resFloat1 = 0.00;

        for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

            if (imprestSurrenderTabletxt.getValueAt(i, 5) != null) {

                if (imprestSurrenderTabletxt.getSelectedColumn() == 5) {

                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 6).toString());
                    jTextField4.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));

                }

                resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 6).toString());
                jTextField4.setText(java.lang.String.valueOf(resFloat1));
                jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));
                //   else{
                //       javax.swing.JOptionPane.showMessageDialog(this,"Disc. % cannot be null","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                //   }

            }
        }
    }

    public void tableModelTableChanged1(javax.swing.event.TableModelEvent evt) {

        double Gross = Double.parseDouble(this.grossValueTxt.getText());
        double Disc = Double.parseDouble(this.jTextField1.getText());
        double Vat = Double.parseDouble(this.jTextField4.getText());
        double resFloat1 = 0.00;

        for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

            if (imprestSurrenderTabletxt.getValueAt(i, 5) != null) {

                if (imprestSurrenderTabletxt.getSelectedColumn() == 5) {

                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 6).toString());
                    jTextField4.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));

                } else {


                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 6).toString());
                    jTextField4.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));


                    // else{
                    // javax.swing.JOptionPane.showMessageDialog(this,"Disc. % cannot be null","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

                }
            }
        }
        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }

    public void tableModelTableChanged2() {
        System.out.println("Calculating totals for table 12 and 21.");
        //        double resFloat = 0.00;
        double resFloat1 = 0.00;
        double Gross = Double.parseDouble(this.grossValueTxt.getText());
        double Disc = Double.parseDouble(this.jTextField1.getText());
        double Vat = Double.parseDouble(this.jTextField4.getText());
        for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

            if (imprestSurrenderTabletxt.getValueAt(i, 0) != null) {

                if (imprestSurrenderTabletxt.getSelectedColumn() == 7) {

                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 8).toString());
                    jTextField1.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));

                } else {

                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 8).toString());
                    jTextField1.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));


                    //   javax.swing.JOptionPane.showMessageDialog(this,"Vat % cannot be null","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }

            //               jTextField31.setText(java.lang.String.valueOf(resFloat));
        }
    }

    public void tableModelTableChanged2(javax.swing.event.TableModelEvent evt) {

        double Gross = Double.parseDouble(this.grossValueTxt.getText());
        double Disc = Double.parseDouble(this.jTextField1.getText());
        double Vat = Double.parseDouble(this.jTextField4.getText());
        double resFloat1 = 0.00;

        for (int i = 0; i < imprestSurrenderTabletxt.getRowCount(); i++) {

            if (imprestSurrenderTabletxt.getValueAt(i, 7) != null) {

                if (imprestSurrenderTabletxt.getSelectedColumn() == 7) {

                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 7).toString());
                    jTextField1.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));

                } else {

                    resFloat1 = resFloat1 + Double.parseDouble(imprestSurrenderTabletxt.getModel().getValueAt(i, 7).toString());
                    jTextField1.setText(java.lang.String.valueOf(resFloat1));
                    jTextField5.setText(java.lang.String.valueOf(Gross - Disc - Vat));
                    //    javax.swing.JOptionPane.showMessageDialog(this,"Vat % cannot be null","Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);

                }
            }
        }
        //               jTextField31.setText(java.lang.String.valueOf(resFloat));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JTextField deliveredByTxt;
    private javax.swing.JLabel deliveredBytLbl;
    private javax.swing.JRadioButton donationsRdbtn;
    private javax.swing.JTable employeeDetailSearchTable;
    private javax.swing.JTextField employeeDetailTxt;
    private javax.swing.JDialog employeeSearchDialog;
    private javax.swing.JScrollPane employeeSearchJscrl;
    private javax.swing.JPanel employeeSearchPanel;
    public javax.swing.JButton exitbtn;
    private javax.swing.JTextField grossValueTxt;
    private javax.swing.JButton helpBtn;
    private javax.swing.JLabel imprestHolderLbl;
    private javax.swing.JTextField imprestHolderNameTxt;
    private javax.swing.JTextField imprestHolderNumberTxt;
    private javax.swing.JLabel imprestNumberLbl;
    private javax.swing.JTextField imprestNumberTxt;
    private javax.swing.JRadioButton imprestRdbtn;
    private javax.swing.JTable imprestSurrenderTabletxt;
    private javax.swing.JTable iteamSearchTable;
    private javax.swing.JDialog itemSearchDialog;
    private javax.swing.JScrollPane itemSearchJscrl;
    private javax.swing.JPanel itemSearchPanel;
    private javax.swing.JTextField itemSearchTxt;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JDialog jSearchDialog;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField111;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JCheckBox mainStoreChkbx;
    private javax.swing.JRadioButton payableRdbtn;
    private javax.swing.JLabel receivedByLbl;
    private javax.swing.JTextField receivedByTxt;
    private javax.swing.JButton removeRowBtn;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton searchButton5;
    private javax.swing.JComboBox storeNameCmbx;
    private javax.swing.JLabel storeNameLbl;
    private javax.swing.JCheckBox subStoreChkbx;
    private javax.swing.JButton surrenderBtn;
    private com.afrisoftech.lib.DatePicker surrenderDatePicker;
    private javax.swing.JLabel surrenderDatelbl;
    private javax.swing.JScrollPane surrenderDetailsJscrl;
    private javax.swing.JPanel surrenderDetailsPanel;
    private javax.swing.JButton surrenderPrintBtn;
    private javax.swing.JTextField unsurrenderedAmtTxt;
    private javax.swing.JTextField voucherNoSrchTxt;
    // End of variables declaration//GEN-END:variables
}
