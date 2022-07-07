/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */
package biz.systempartners.reports;

import com.afrisoftech.hospinventory.ShowIssuedItemsDialog;
import com.afrisoftech.lib.ComboBoxModel;
import com.afrisoftech.lib.GetItemInfo;
import com.afrisoftech.lib.SQLDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author funsoft
 */
public class TenderFlowIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    private String code;

    /**
     * Creates new form ReportIntfr
     */
    public TenderFlowIntfr(java.sql.Connection connDb) {
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

        StoreDialog = new javax.swing.JDialog();
        jSearchPanel4 = new javax.swing.JPanel();
        storeSearchTxt = new javax.swing.JTextField();
        jSearchScrollPane4 = new javax.swing.JScrollPane();
        storeSearchTbl = new com.afrisoftech.dbadmin.JTable();
        jButton93 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        reportBodyPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unawardedbodyTbl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        unawardedQtytxt = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();
        headerPanel = new javax.swing.JPanel();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        stratDatePicker = new com.afrisoftech.lib.DatePicker();
        endDateLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        tenderNotxt = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        tenderDesctxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        categoryCmbx = new javax.swing.JComboBox();
        unenteredItemschk = new javax.swing.JCheckBox();
        enteredbal = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        StoreDialog.setModal(true);
        StoreDialog.setUndecorated(true);
        StoreDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel4.setLayout(new java.awt.GridBagLayout());

        storeSearchTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                storeSearchTxtCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        jSearchPanel4.add(storeSearchTxt, gridBagConstraints);

        storeSearchTbl.setShowHorizontalLines(false);
        storeSearchTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                storeSearchTblMouseClicked(evt);
            }
        });
        jSearchScrollPane4.setViewportView(storeSearchTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel4.add(jSearchScrollPane4, gridBagConstraints);

        jButton93.setText("Dispose");
        jButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton93ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jSearchPanel4.add(jButton93, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        StoreDialog.getContentPane().add(jSearchPanel4, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Stock-Tender Items");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

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
        jPanel1.add(reportBodyJscrollPane, gridBagConstraints);

        jTabbedPane1.addTab("Tender Items ", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        unawardedbodyTbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        unawardedbodyTbl.setForeground(new java.awt.Color(102, 102, 102));
        unawardedbodyTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(unawardedbodyTbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Unawarded Qty (In Specified Units)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel5, gridBagConstraints);

        unawardedQtytxt.setEditable(false);
        unawardedQtytxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        unawardedQtytxt.setForeground(new java.awt.Color(255, 0, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        jPanel2.add(unawardedQtytxt, gridBagConstraints);

        jTabbedPane1.addTab("Award Balances", jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(jTabbedPane1, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeBtn, gridBagConstraints);
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

        headerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(endDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(stratDatePicker, gridBagConstraints);

        endDateLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        endDateLbl.setForeground(new java.awt.Color(0, 0, 204));
        endDateLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        headerPanel.add(endDateLbl, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tender Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        headerPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Description of Tender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        headerPanel.add(jLabel2, gridBagConstraints);

        jPanel59.setLayout(new java.awt.GridBagLayout());

        tenderNotxt.setEditable(false);
        tenderNotxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tenderNotxt.setForeground(new java.awt.Color(102, 102, 102));
        tenderNotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenderNotxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel59.add(tenderNotxt, gridBagConstraints);

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel59.add(jButton23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 500);
        headerPanel.add(jPanel59, gridBagConstraints);

        tenderDesctxt.setEditable(false);
        tenderDesctxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tenderDesctxt.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(tenderDesctxt, gridBagConstraints);

        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("End date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        headerPanel.add(jLabel3, gridBagConstraints);

        jButton1.setText("Populate Items");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        headerPanel.add(jButton1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Items Category");
        jLabel4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        headerPanel.add(jLabel4, gridBagConstraints);

        categoryCmbx.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        categoryCmbx.setForeground(new java.awt.Color(0, 0, 204));
        categoryCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        categoryCmbx.setBorder(new javax.swing.border.MatteBorder(null));
        categoryCmbx.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 80);
        headerPanel.add(categoryCmbx, gridBagConstraints);

        buttonGroup1.add(unenteredItemschk);
        unenteredItemschk.setText("Unentered Tender Items");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        headerPanel.add(unenteredItemschk, gridBagConstraints);

        buttonGroup1.add(enteredbal);
        enteredbal.setText("Entered Tender Items");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        headerPanel.add(enteredbal, gridBagConstraints);

        jLabel6.setText("Item Code/ Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        headerPanel.add(jLabel6, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField1.setText("Search item ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        getContentPane().add(headerPanel, gridBagConstraints);

        setBounds(0, 0, 1125, 481);
    }// </editor-fold>//GEN-END:initComponents

    private void tenderNotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenderNotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenderNotxtActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        System.out.println("Showing dialog");
        java.awt.Point point = tenderNotxt.getLocationOnScreen();
        StoreDialog.setSize(400, 200);
        StoreDialog.setLocation(point);
        StoreDialog.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void storeSearchTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_storeSearchTxtCaretUpdate
        // TODO add your handling code here:
        if (storeSearchTxt.getCaretPosition() > 2) {

            //            System.out.println("Nothing");
            //        } else {
            storeSearchTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct upper(tender_no) from st_item_to_quote where tender_no ilike '%" + storeSearchTxt.getText() + "%' order by 1"));

            jSearchScrollPane4.setViewportView(storeSearchTbl);

        }
    }//GEN-LAST:event_storeSearchTxtCaretUpdate

    private void storeSearchTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeSearchTblMouseClicked
        // TODO add your handling code here:
        tenderNotxt.setText(storeSearchTbl.getValueAt(storeSearchTbl.getSelectedRow(), 0).toString());
        tenderDesctxt.setText(GetItemInfo.getITenderDesc(tenderNotxt.getText(), connectDB));
        //refreshRecords();
        categoryCmbx.setModel(ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT sub_cat_code FROM st_stock_item UNION SELECT DISTINCT category FROM st_stock_prices ORDER BY 1"));
        StoreDialog.dispose();

    }//GEN-LAST:event_storeSearchTblMouseClicked

    private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
        // TODO add your handling code here:
        StoreDialog.dispose();
    }//GEN-LAST:event_jButton93ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (!categoryCmbx.getSelectedItem().toString().equalsIgnoreCase("-")) {
            refreshRecords(categoryCmbx.getSelectedItem().toString(), tenderNotxt.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Not A Valid category", "Check Category", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private java.lang.Double getAwardedQty(java.lang.String Tender_no, java.lang.String supplier, String code) {
        Double qty_ordered = 0.00;
        try {
            java.sql.PreparedStatement pst = connectDB.prepareStatement("select SUM(quantity) FROM st_orders where quotation_no = '" + Tender_no + "'  and supplier ilike '%" + supplier + "%' and code ='" + code + "' and order_no IS NULL ");
            java.sql.ResultSet rsetqty = pst.executeQuery();
            while (rsetqty.next()) {

                qty_ordered = rsetqty.getDouble(1);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return qty_ordered;

    }

    private Double getTenderQty(String tender, String item_code) {
        Double tenderQty = 0.00;
        PreparedStatement pst;
        try {
            pst = connectDB.prepareStatement("SELECT DISTINCT quantity from st_item_to_quote WHERE item_code ilike '" + item_code + "' AND tender_no='" + tender + "'");

            ResultSet ret = pst.executeQuery();
            while (ret.next()) {

                tenderQty = ret.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TenderFlowIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tenderQty;

    }

    private Double getSumAwarded(String tender, String code) {
        Double sumAwarded = 0.00;
        PreparedStatement pst;
        try {
            pst = connectDB.prepareStatement("SELECT DISTINCT sum(quantity) from st_orders WHERE code ilike '" + code + "' AND quotation_no='" + tender + "' and order_no is null");

            ResultSet ret = pst.executeQuery();
            while (ret.next()) {

                sumAwarded = ret.getDouble(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(TenderFlowIntfr.class.getName()).log(Level.SEVERE, null, ex);

        }

        return sumAwarded;

    }
    private void reportBodyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportBodyTableMouseClicked
        // TODO add your handling code here:

        if (reportBodyTable.getSelectedColumn() == 0 && reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 0) != null && enteredbal.isSelected()) {
            jTabbedPane1.setSelectedIndex(1);
            String item_code = reportBodyTable.getValueAt(reportBodyTable.getSelectedRow(), 0).toString();
            //0159039 

            com.afrisoftech.dbadmin.setFixedColumns.DisallowReordering(unawardedbodyTbl);
            unawardedbodyTbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT supplier,'' QTY_AWARDED FROM st_orders where quotation_no='" + tenderNotxt.getText() + "' AND code='" + item_code + "' "));

            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            for (int i = 0; i < unawardedbodyTbl.getRowCount(); i++) {

                unawardedbodyTbl.setValueAt(getAwardedQty(tenderNotxt.getText(), unawardedbodyTbl.getValueAt(i, 0).toString(), item_code), i, 1);

            }
            unawardedQtytxt.setText(String.valueOf(getTenderQty(tenderNotxt.getText(), item_code) - getSumAwarded(tenderNotxt.getText(), item_code)));

            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        }
    }//GEN-LAST:event_reportBodyTableMouseClicked
    private void refreshRecords(String category, String tender) {

        if (unenteredItemschk.isSelected()) {

            //reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct item_code,description,units from st_stock_item where sub_cat_code ilike '"+category+"' and item_code not in (select distinct item_code from st_item_to_quote where tender_no = '"+tender+"')"));
            reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct a.item_code,a.description,a.units  from st_stock_item a,st_item_to_quote  b where a.sub_cat_code ilike '" + category + "' and a.item_code NOT IN ( select distinct item_code from st_item_to_quote) and b.tender_no ilike '" + tender + "'"));

            System.err.println("select distinct a.item_code,a.description,a.units from st_stock_item a,st_item_to_quote  b where a.sub_cat_code ilike '" + category + "' and a.item_code NOT IN ( select distinct item_code from st_item_to_quote) and b.tender_no ilike '" + tender + "'");
            
        } else if (enteredbal.isSelected()) {

            reportBodyTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select distinct item_code,item_description,units,quantity,'' as tender_price from st_item_to_quote where  tender_no = '" + tender + "' order by 1"));

            System.err.println("select distinct item_code,item_description,units,quantity,'' AS tender_price  from st_item_to_quote where  tender_no = '" + tender + "' order by 1')");

//          
           for(int i=0;i<reportBodyTable.getRowCount();i++){
               code=reportBodyTable.getValueAt(i, 0).toString();
               
               reportBodyTable.setValueAt(GetItemInfo.getTenderprice(tender, code, connectDB), i, 4);
           }
        }
    }
    ;    

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog StoreDialog;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JComboBox categoryCmbx;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JCheckBox enteredbal;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton93;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jSearchPanel4;
    private javax.swing.JScrollPane jSearchScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JTable storeSearchTbl;
    private javax.swing.JTextField storeSearchTxt;
    private com.afrisoftech.lib.DatePicker stratDatePicker;
    private javax.swing.JTextField tenderDesctxt;
    private javax.swing.JTextField tenderNotxt;
    private javax.swing.JTextField unawardedQtytxt;
    private javax.swing.JTable unawardedbodyTbl;
    private javax.swing.JCheckBox unenteredItemschk;
    // End of variables declaration//GEN-END:variables

}