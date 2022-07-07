/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import com.afrisoftech.dbadmin.TableModel;
import com.afrisoftech.lib.ClearTable;
import com.afrisoftech.lib.CurrencyFormatter;
import com.afrisoftech.lib.SQLDateFormat;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author saleem
 */
public class TenderContractsIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form TenderAdIntfr
     */
     org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    java.sql.Connection connectDB = null;
    private int count;
    private double percent;
    private double total;
    private double bond;
    public TenderContractsIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        
     connectDB = connDb;
 
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

        searchtender1 = new javax.swing.JDialog();
        tenderrecordspnl1 = new javax.swing.JPanel();
        tenderrecordstxt1 = new javax.swing.JTextField();
        tenderrecordsspnl1 = new javax.swing.JScrollPane();
        tenderrecordstbl1 = new com.afrisoftech.dbadmin.JTable();
        closebtn2 = new javax.swing.JButton();
        searchtender = new javax.swing.JDialog();
        tenderrecordspnl = new javax.swing.JPanel();
        tenderrecordstxt = new javax.swing.JTextField();
        tenderrecordsspnl = new javax.swing.JScrollPane();
        tenderrecordstbl = new com.afrisoftech.dbadmin.JTable();
        closebtn1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tenderItemstbl = new com.afrisoftech.dbadmin.JTable();
        jPanel1 = new javax.swing.JPanel();
        saveButntender = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox3 = new javax.swing.JCheckBox();
        datePicker2 = new com.afrisoftech.lib.DatePicker();
        jCheckBox1 = new javax.swing.JCheckBox();

        searchtender1.setModal(true);
        searchtender1.setUndecorated(true);
        searchtender1.getContentPane().setLayout(new java.awt.GridBagLayout());

        tenderrecordspnl1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tenderrecordspnl1.setLayout(new java.awt.GridBagLayout());

        tenderrecordstxt1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tenderrecordstxt1CaretUpdate(evt);
            }
        });
        tenderrecordstxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenderrecordstxt1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        tenderrecordspnl1.add(tenderrecordstxt1, gridBagConstraints);

        tenderrecordstbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tenderrecordstbl1.setToolTipText("Click on the target row to select the patient from the search.");
        tenderrecordstbl1.setShowHorizontalLines(false);
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
        tenderrecordstbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenderrecordstbl1MouseClicked(evt);
            }
        });
        tenderrecordsspnl1.setViewportView(tenderrecordstbl1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        tenderrecordspnl1.add(tenderrecordsspnl1, gridBagConstraints);

        closebtn2.setText("Close");
        closebtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tenderrecordspnl1.add(closebtn2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchtender1.getContentPane().add(tenderrecordspnl1, gridBagConstraints);

        searchtender.setModal(true);
        searchtender.setUndecorated(true);
        searchtender.getContentPane().setLayout(new java.awt.GridBagLayout());

        tenderrecordspnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tenderrecordspnl.setLayout(new java.awt.GridBagLayout());

        tenderrecordstxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tenderrecordstxtCaretUpdate(evt);
            }
        });
        tenderrecordstxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenderrecordstxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        tenderrecordspnl.add(tenderrecordstxt, gridBagConstraints);

        tenderrecordstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tenderrecordstbl.setToolTipText("Click on the target row to select the patient from the search.");
        tenderrecordstbl.setShowHorizontalLines(false);
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
        tenderrecordstbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenderrecordstblMouseClicked(evt);
            }
        });
        tenderrecordsspnl.setViewportView(tenderrecordstbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        tenderrecordspnl.add(tenderrecordsspnl, gridBagConstraints);

        closebtn1.setText("Close");
        closebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tenderrecordspnl.add(closebtn1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        searchtender.getContentPane().add(tenderrecordspnl, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tender Contract Setup(To be done Before Award of Contract)");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        tenderItemstbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tenderItemstbl.setForeground(new java.awt.Color(102, 102, 102));
        tenderItemstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tender No", "Total Bid Amt", "Tender Description", "Bidder Awarded", "Performance %", "Performance Bond Value", "Save"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tenderItemstbl.setGridColor(new java.awt.Color(0, 51, 204));
        tenderItemstbl.setSelectionBackground(new java.awt.Color(153, 255, 204));
        tenderItemstbl.getTableHeader().setReorderingAllowed(false);
        javax.swing.table.TableColumn column111 = null;
        for (int i = 0; i < 4; i++) {
            column111 = tenderItemstbl.getColumnModel().getColumn(i);
            if (i == 2) {

                column111.setPreferredWidth(350); //sport column is bigger
            } else {
                if(i==5){
                    column111.setPreferredWidth(250);
                }
                else
                column111.setPreferredWidth(100);

            }
        }
        tenderItemstbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenderItemstblMouseClicked(evt);
            }
        });
        tenderItemstbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tenderItemstblKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tenderItemstbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        saveButntender.setText("Save&Print Tender Contract Setup");
        saveButntender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        saveButntender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButntenderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(saveButntender, gridBagConstraints);

        editBtn.setText("   Edit Previous Entries   ");
        editBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(editBtn, gridBagConstraints);

        jButton1.setText("   Remove Item(s)   ");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jButton1, gridBagConstraints);

        jButton3.setText("    Close form   ");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jCheckBox3.setText("Tick All");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(jCheckBox3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 200);
        jPanel3.add(datePicker2, gridBagConstraints);

        jCheckBox1.setText("View  Unprocessed Tenders ");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jTabbedPane1.addTab("    Contracts Setup    ", jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
       ClearTable.clearthisTable(tenderItemstbl);
       
       
       saveButntender.setLabel("Update Entries");
         try {
            // PreparedStatement getTenders = connectDB.prepareStatement("SELECT DISTINCT quotation_no, SUM(quantity*price),supplier FROM st_recommendation WHERE quotation_no IN (SELECT DISTINCT tender_no FROM st_contract_mgt) AND supplier IN (SELECT DISTINCT supplier FROM st_contract_mgt) AND financial_date::date ='"+SQLDateFormat.getSQLDate(datePicker2.getDate())+"' group by 1,3");
             PreparedStatement getTenders = connectDB.prepareStatement("SELECT DISTINCT quotation_no, SUM(quantity*price),supplier FROM st_recommendation WHERE quotation_no IN (SELECT DISTINCT tender_no FROM st_contract_mgt) AND supplier IN (SELECT DISTINCT supplier FROM st_contract_mgt)  group by 1,3");
             
             
             ResultSet gettinTenders = getTenders.executeQuery();
             int i =0;
             while(gettinTenders.next()){
             
             
             
             tenderItemstbl.setValueAt(gettinTenders.getObject(1), i, 0);

             tenderItemstbl.setValueAt(gettinTenders.getDouble(2), i, 1);
             
             tenderItemstbl.setValueAt(com.afrisoftech.lib.GetItemInfo.getITenderDesc(tenderItemstbl.getValueAt(i, 0).toString(), connectDB), i, 2);
             
             
             tenderItemstbl.setValueAt(gettinTenders.getObject(3), i, 3);
             
             //----------------------------------
             
            
             
             
             i++;
         
         }
             
         if(i<1){
         JOptionPane.showMessageDialog(this, "No Data To Display", "INFO", JOptionPane.INFORMATION_MESSAGE);
         //
         saveButntender.setLabel("Save&Print Tender Contract Setup");
         
         }
         else{
           for(int j=0;j<tenderItemstbl.getRowCount();j++){
                if(tenderItemstbl.getValueAt(j, 0)!=null){
                
             PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT performance_value, cr_performance FROM st_contract_mgt WHERE tender_no = '"+tenderItemstbl.getValueAt(j, 0).toString()+"' AND supplier ILIKE '"+tenderItemstbl.getValueAt(j, 3).toString()+"'");
             ResultSet rset = pst.executeQuery();
             
             while(rset.next()) {
             percent = rset.getDouble(1);
             total= rset.getDouble(2);
 
               }
             tenderItemstbl.setValueAt(percent, j, 4);
             
             tenderItemstbl.setValueAt(total, j, 5);
             
             tenderItemstbl.setValueAt(false, j, 6);
                
                }     
            }
         }
             
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(TenderContractsIntfr.class.getName()).log(Level.SEVERE, null, ex);
         }
       
       
       
       
    }//GEN-LAST:event_editBtnActionPerformed
    private void refreshTable(){
   
        try {
             PreparedStatement getTenders = connectDB.prepareStatement("SELECT DISTINCT quotation_no, SUM(quantity*price),supplier"
                     + " FROM st_recommendation WHERE quotation_no NOT IN (SELECT DISTINCT tender_no FROM st_contract_mgt) or supplier NOT IN (SELECT DISTINCT supplier FROM st_contract_mgt) AND ordered_qty=0.0  GROUP BY 1,3 order by 1");
             
             ResultSet gettinTenders = getTenders.executeQuery();
             int i =0;
             while(gettinTenders.next()){
             
             tenderItemstbl.setValueAt(gettinTenders.getObject(1), i, 0);

             tenderItemstbl.setValueAt(CurrencyFormatter.getFormattedDouble(gettinTenders.getDouble(2)), i, 1);
             
             tenderItemstbl.setValueAt(com.afrisoftech.lib.GetItemInfo.getITenderDesc(tenderItemstbl.getValueAt(i, 0).toString(), connectDB), i, 2);
             
             tenderItemstbl.setValueAt(gettinTenders.getObject(3), i, 3);
             
             tenderItemstbl.setValueAt(1.0, i, 4);
             
             tenderItemstbl.setValueAt(false, i, 6);
             
             i++;

             }

         } catch (SQLException ex) {
             javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
             Logger.getLogger(TenderContractsIntfr.class.getName()).log(Level.SEVERE, null, ex);
         }
      
    }
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        
        
 ClearTable.clearthisTable(tenderItemstbl);
 saveButntender.setLabel("Save&Print Tender Contract Setup");
 refreshTable();
      
      
      
      
      
      
      
      
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void saveButntenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButntenderActionPerformed
        // TODO add your handling code here:
     if(saveButntender.getLabel().toString().equalsIgnoreCase("Save&Print Tender Contract Setup")){   
        for(int i=0;i<tenderItemstbl.getRowCount();i++){
            if(tenderItemstbl.getValueAt(i, 0)!=null && tenderItemstbl.getModel().getValueAt(i, 6)==java.lang.Boolean.TRUE){
                
                
                try {
                    PreparedStatement pst = connectDB.prepareStatement("INSERT INTO st_contract_mgt(tender_no, supplier, performance_value, cr_performance, description_of_entry, entered_by )\n" +
                    "VALUES (?, ?, ?, ?, ?, ?);");
                    
                    
                    pst.setObject(1, tenderItemstbl.getValueAt(i, 0));
                    pst.setObject(2,  tenderItemstbl.getValueAt(i, 3));
                    pst.setObject(3, tenderItemstbl.getValueAt(i, 4));
                    pst.setObject(4, tenderItemstbl.getValueAt(i, 5));
                    pst.setObject(5, "CONTRACT CREDIT SETUP");
                    pst.setObject(6, com.afrisoftech.lib.UserName.getLoginName(connectDB).toString());
                    //pst.setObject(7, com.afrisoftech.lib.ServerTime.serverTimeStamp(connectDB).toString());
                     //pst.setObject(8, tenderItemstbl.getValueAt(i, 5));
                    pst.executeUpdate();
                    
                     connectDB.commit();
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TenderContractsIntfr.class.getName()).log(Level.SEVERE, null, ex);
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                    ex.printStackTrace();
                }
    
            
            }
           
  
        }
        
   JOptionPane.showMessageDialog(this, "Contract Generated", "CONFIRMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);   
   //TenderAdvertisementPDF policy = new TenderAdvertisementPDF();
   //policy.TenderAdvertisementPDF(connectDB, SQLDateFormat.getSQLDate(datePicker2.getDate()).toString());
        ClearTable.clearthisTable(tenderItemstbl);
        refreshTable();
        
        
     }
     
     
     else{
     
     
   for(int i=0;i<tenderItemstbl.getRowCount();i++){
            if(tenderItemstbl.getValueAt(i, 0)!=null && tenderItemstbl.getModel().getValueAt(i, 6)==java.lang.Boolean.TRUE){
                
                
                try {
                    PreparedStatement pst = connectDB.prepareStatement("UPDATE st_contract_mgt SET  performance_value=?, cr_performance=? WHERE tender_no = '"+tenderItemstbl.getValueAt(i, 0)+"' and supplier ilike '"+tenderItemstbl.getValueAt(i, 3)+"'");
                   
                    
                    pst.setObject(1, tenderItemstbl.getValueAt(i, 4));
                    pst.setObject(2, tenderItemstbl.getValueAt(i, 5));
                    
                    pst.executeUpdate();
                    
                   connectDB.commit();
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TenderContractsIntfr.class.getName()).log(Level.SEVERE, null, ex);
                    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                    ex.printStackTrace();
                }
    
            
            }
           
  
        }
     JOptionPane.showMessageDialog(this, "Data Updated Succesfully", "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);
     ClearTable.clearthisTable(tenderItemstbl);
//     TenderAdvertisementPDF policy = new TenderAdvertisementPDF();
//     policy.TenderAdvertisementPDF(connectDB, SQLDateFormat.getSQLDate(datePicker2.getDate()).toString());
     saveButntender.setLabel("Save&Print Tender Contract Setup");
     }
        
    }//GEN-LAST:event_saveButntenderActionPerformed

    private void tenderItemstblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenderItemstblMouseClicked
        // TODO add your handling code here:
   if( tenderItemstbl.getValueAt(tenderItemstbl.getSelectedRow(), 5)==java.lang.Boolean.TRUE){
    if(tenderItemstbl.getValueAt(tenderItemstbl.getSelectedRow(), 0)!=null  ){
    
    
    }
    
    else{
    
    
    JOptionPane.showMessageDialog(this, "Null Selection", "ERROR", JOptionPane.ERROR_MESSAGE);
    
    tenderItemstbl.setValueAt(false,tenderItemstbl.getSelectedRow(), 5);
    
      }
   
   }
    }//GEN-LAST:event_tenderItemstblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      
         ClearTable.removeSelectedRows(tenderItemstbl);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tenderrecordstxt1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tenderrecordstxt1CaretUpdate
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (this.tenderrecordstxt1.getCaretPosition() >3) {
            System.out.print("Got Results");

//        } else
//
//        {
        tenderrecordstbl1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT quotation_no FROM st_floated_quotations  where "
                + "   quotation_no ilike '" +tenderrecordstxt.getText()+"%' order by 1 desc;"));
        tenderrecordstbl1.setShowHorizontalLines(false);

        tenderrecordsspnl1.setViewportView(tenderrecordstbl1);

        }

    }//GEN-LAST:event_tenderrecordstxt1CaretUpdate

    private void tenderrecordstxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenderrecordstxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenderrecordstxt1ActionPerformed

    private void tenderrecordstbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenderrecordstbl1MouseClicked
        
    }//GEN-LAST:event_tenderrecordstbl1MouseClicked

    private void closebtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtn2ActionPerformed
        // TODO add your handling code here:
        this.searchtender1.dispose();
    }//GEN-LAST:event_closebtn2ActionPerformed

    private void tenderrecordstxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tenderrecordstxtCaretUpdate
        // TODO add your handling code here:
        if (this.tenderrecordstxt.getCaretPosition() > 3) {
//            System.out.print("Nothing");
//
//        } else
//
//        {
            tenderrecordstbl.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT tender_no Tender_No FROM st_item_to_quote where "
                + " tender_no ilike '%" +tenderrecordstxt.getText()+"%'  order by 1 desc;"));
        tenderrecordstbl.setShowHorizontalLines(false);

        tenderrecordsspnl.setViewportView(tenderrecordstbl);
        
        }
    }//GEN-LAST:event_tenderrecordstxtCaretUpdate

    private void tenderrecordstxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenderrecordstxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenderrecordstxtActionPerformed

    private void tenderrecordstblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenderrecordstblMouseClicked
      

        
        
searchtender.dispose();
        
        
        
        
    }//GEN-LAST:event_tenderrecordstblMouseClicked

    private void closebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtn1ActionPerformed
        // TODO add your handling code here:
        searchtender.dispose();

    }//GEN-LAST:event_closebtn1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        for(int i=0;i<tenderItemstbl.getRowCount();i++){
         if(tenderItemstbl.getValueAt(i, 1)!=null){
         tenderItemstbl.setValueAt(true, i, 6);
         
         
         
         
         }
        
        
        
        }
        
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void tenderItemstblKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenderItemstblKeyReleased
        // TODO add your handling code here:
    if(tenderItemstbl.getValueAt(tenderItemstbl.getSelectedRow(), 1)!=null){    
        Double total_bid = Double.valueOf(tenderItemstbl.getValueAt(tenderItemstbl.getSelectedRow(), 1).toString().replace(",", ""));
        
        Double percent = Double.valueOf(tenderItemstbl.getValueAt(tenderItemstbl.getSelectedRow(), 4).toString().replace(",", ""));
        
        
        bond = total_bid*percent/100;
        
        tenderItemstbl.setValueAt(bond, tenderItemstbl.getSelectedRow(), 5);
    }
    else
        JOptionPane.showMessageDialog(this, "Nothing On this Row","Error",JOptionPane.ERROR_MESSAGE);
        
        
        
        
        
        
    }//GEN-LAST:event_tenderItemstblKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closebtn1;
    private javax.swing.JButton closebtn2;
    private com.afrisoftech.lib.DatePicker datePicker2;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton saveButntender;
    private javax.swing.JDialog searchtender;
    private javax.swing.JDialog searchtender1;
    private javax.swing.JTable tenderItemstbl;
    private javax.swing.JPanel tenderrecordspnl;
    private javax.swing.JPanel tenderrecordspnl1;
    private javax.swing.JScrollPane tenderrecordsspnl;
    private javax.swing.JScrollPane tenderrecordsspnl1;
    private javax.swing.JTable tenderrecordstbl;
    private javax.swing.JTable tenderrecordstbl1;
    private javax.swing.JTextField tenderrecordstxt;
    private javax.swing.JTextField tenderrecordstxt1;
    // End of variables declaration//GEN-END:variables
}