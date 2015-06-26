/*
 * DatePanel.java
 *
 * Created on May 1, 2003, 7:40 PM
 */

package com.afrisoftech.lib;

import com.afrisoftech.hospinventory.ShowTenderedItemsDialog;
import com.afrisoftech.hospinventory.TenderFinancialReportPDF;
import com.afrisoftech.hospinventory.mtrhreports.TendersReceivedMtrhPdf;
import javax.swing.JOptionPane;

/**
 *
 * @author  root
 */
public class TenderedItems extends javax.swing.JDialog {
    
    int reportName;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.sql.Connection connectDB = null;
    
    java.util.Vector dateStartEnd = null;
    
    javax.swing.JSpinner beginDateSpinner = null;
    
    javax.swing.JSpinner endDateSpinner = null;
    
    /** Creates new form DatePanel */
    public TenderedItems(java.awt.Frame parent, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB, boolean modal, int repName, java.sql.Connection connectDb) {
        
        super(parent, modal);
        
        reportName = repName;
        
        connectDB = connectDb;
        
        pConnDB = pconnDB;
        //      dateStartEnd = new java.util.Vector(1,1);
        
        initComponents();
        jCheckBox1.doClick();
        
        //       return dateStartEnd;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        what = new javax.swing.JMenuItem();
        tenderSearch = new javax.swing.JDialog();
        jSearchPanel213 = new javax.swing.JPanel();
        jTextField115 = new javax.swing.JTextField();
        jSearchScrollPane4 = new javax.swing.JScrollPane();
        jSearchTable4 = new com.afrisoftech.dbadmin.JTable();
        jButton93 = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        schedulesbtngrp = new javax.swing.ButtonGroup();
        bidderSearch = new javax.swing.JDialog();
        jSearchPanel214 = new javax.swing.JPanel();
        bidderSearchText = new javax.swing.JTextField();
        jSearchScrollPane5 = new javax.swing.JScrollPane();
        bidderSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton94 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        descTxt = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        tenderNotxt = new javax.swing.JTextField();
        patientSearchbtn1 = new javax.swing.JButton();

        jPopupMenu1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPopupMenu1FocusLost(evt);
            }
        });

        what.setText("What's This?");
        what.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whatActionPerformed(evt);
            }
        });
        what.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                whatFocusLost(evt);
            }
        });
        jPopupMenu1.add(what);

        tenderSearch.setModal(true);
        tenderSearch.setUndecorated(true);
        tenderSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel213.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel213.setLayout(new java.awt.GridBagLayout());

        jTextField115.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField115CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel213.add(jTextField115, gridBagConstraints);

        jSearchTable4.setToolTipText("Click on the target row to select the patient from the search.");
        jSearchTable4.setShowHorizontalLines(false);
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
        jSearchTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable4MouseClicked(evt);
            }
        });
        jSearchScrollPane4.setViewportView(jSearchTable4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel213.add(jSearchScrollPane4, gridBagConstraints);

        jButton93.setText("Dispose");
        jButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton93ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel213.add(jButton93, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tenderSearch.getContentPane().add(jSearchPanel213, gridBagConstraints);

        jCheckBox3.setText("jCheckBox3");

        bidderSearch.setModal(true);
        bidderSearch.setUndecorated(true);
        bidderSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel214.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel214.setLayout(new java.awt.GridBagLayout());

        bidderSearchText.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                bidderSearchTextCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 300.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jSearchPanel214.add(bidderSearchText, gridBagConstraints);

        bidderSearchTable.setToolTipText("Click on the target row to select the patient from the search.");
        bidderSearchTable.setShowHorizontalLines(false);
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
        bidderSearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bidderSearchTableMouseClicked(evt);
            }
        });
        jSearchScrollPane5.setViewportView(bidderSearchTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel214.add(jSearchScrollPane5, gridBagConstraints);

        jButton94.setText("Dispose");
        jButton94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton94ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel214.add(jButton94, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bidderSearch.getContentPane().add(jSearchPanel214, gridBagConstraints);

        setTitle("Begin & End Date");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setMnemonic('n');
        jButton2.setText("Cancel");
        jButton2.setPreferredSize(new java.awt.Dimension(142, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setMnemonic('o');
        jButton1.setText("View Tender Items");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Store here"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Tender Desc");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jLabel5, gridBagConstraints);

        descTxt.setEditable(false);
        descTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descTxtMouseClicked(evt);
            }
        });
        descTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(descTxt, gridBagConstraints);

        schedulesbtngrp.add(jCheckBox1);
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Quantities Per Tender");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jCheckBox1, gridBagConstraints);

        schedulesbtngrp.add(jCheckBox2);
        jCheckBox2.setText("Items Per Supplier");
        jCheckBox2.setEnabled(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jCheckBox2, gridBagConstraints);

        jLabel6.setText("Tender Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jLabel6, gridBagConstraints);

        jPanel43.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel43.setMinimumSize(new java.awt.Dimension(7, 25));
        jPanel43.setPreferredSize(new java.awt.Dimension(7, 25));
        jPanel43.setLayout(new java.awt.GridBagLayout());

        tenderNotxt.setEditable(false);
        tenderNotxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel43.add(tenderNotxt, gridBagConstraints);

        patientSearchbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kiwi/images/date.gif"))); // NOI18N
        patientSearchbtn1.setToolTipText("Search");
        patientSearchbtn1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        patientSearchbtn1.setMaximumSize(new java.awt.Dimension(20, 53));
        patientSearchbtn1.setMinimumSize(new java.awt.Dimension(20, 53));
        patientSearchbtn1.setPreferredSize(new java.awt.Dimension(25, 25));
        patientSearchbtn1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        patientSearchbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientSearchbtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel43.add(patientSearchbtn1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel4.add(jPanel43, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        setSize(new java.awt.Dimension(650, 269));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
                
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if(tenderNotxt.getText().length()<1){
            javax.swing.JOptionPane.showMessageDialog(null, "Please select the Tender before you attempt to produce the report.");
        }
      
        
        
        else{
            
          //  this.getReport(reportName);
            
                 com.afrisoftech.hospinventory.St_items_to_quotePDF policy = new com.afrisoftech.hospinventory.St_items_to_quotePDF();
                
                policy.St_items_to_quotePDF(connectDB, tenderNotxt.getText());
        }
        
        // Add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void descTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descTxtActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_descTxtActionPerformed

    private void jTextField115CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField115CaretUpdate
        if (jTextField115.getCaretPosition() > 2) {
     
            jSearchTable4.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsCaret(connectDB, "select distinct tender_no from st_item_to_quote where tender_no ilike '%"+jTextField115.getText()+"%'"));
            jSearchScrollPane4.setViewportView(jSearchTable4);
            System.out.println("Cannot sort out");
            
            
            //}

        }  // Add your handling code here:
    }//GEN-LAST:event_jTextField115CaretUpdate

    private void jSearchTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable4MouseClicked

   
        tenderNotxt.setText(jSearchTable4.getValueAt(jSearchTable4.getSelectedRow(), 0).toString());

        //bidderSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct supplier from st_orders where quotation_no='"+tenderNotxt.getText()+"' and supplier ilike '"+bidderSearchText.getText()+"%'  and order_no is null"));
        descTxt.setText(com.afrisoftech.lib.GetItemInfo.getITenderDesc(tenderNotxt.getText(), connectDB));

        tenderSearch.dispose();  // Add your handling code here:
    }//GEN-LAST:event_jSearchTable4MouseClicked

    private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
        this.tenderSearch.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton93ActionPerformed

    private void descTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descTxtMouseClicked
        // TODO add your handling code here:
        java.awt.Point p = descTxt.getLocationOnScreen();
        
        jPopupMenu1.setVisible(true);
         
         jPopupMenu1.setLocation(p);
    }//GEN-LAST:event_descTxtMouseClicked

    private void whatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whatActionPerformed
        // TODO add your handling code here:
        jPopupMenu1.setVisible(false);
        if(descTxt.getText().length()>0)  {
//        ShowTenderedItemsDialog sht = new ShowTenderedItemsDialog(null,true,connectDB,supplierTxt.getText());
        
        //sht.setVisible(true);
        
        //jPopupMenu1.setVisible(false);
        
        }
    }//GEN-LAST:event_whatActionPerformed

    private void whatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_whatFocusLost
        // TODO add your handling code here:
        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_whatFocusLost

    private void jPopupMenu1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPopupMenu1FocusLost
        // TODO add your handling code here:
        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jPopupMenu1FocusLost

    private void patientSearchbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientSearchbtn1ActionPerformed
        // TODO add your handling code here:
        System.out.println("Showing dialog");

        // java.awt.Point point = this.jComboBox1311.getLocationOnScreen();
        java.awt.Point point = this.tenderNotxt.getLocationOnScreen();

        tenderSearch.setSize(600, 200);

        tenderSearch.setLocation(point);

        tenderSearch.setVisible(true);
    }//GEN-LAST:event_patientSearchbtn1ActionPerformed

    private void bidderSearchTextCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_bidderSearchTextCaretUpdate
        // TODO add your handling code here:
       // bidderSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct supplier from st_floated_quotations where quotation_no='"+tenderNotxt.getText()+"' and supplier ilike '"+bidderSearchText.getText()+"%'  and unit_price>0"));
        bidderSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT distinct supplier from st_orders where quotation_no='"+tenderNotxt.getText()+"' and supplier ilike '"+bidderSearchText.getText()+"%' and unit_price>0 and order_no is null"));
    }//GEN-LAST:event_bidderSearchTextCaretUpdate

    private void bidderSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bidderSearchTableMouseClicked
        // TODO add your handling code here:
//        supplierTxt.setText(bidderSearchTable.getValueAt(bidderSearchTable.getSelectedRow(), 0).toString());
      //  bidderSearch.dispose();
        
    }//GEN-LAST:event_bidderSearchTableMouseClicked

    private void jButton94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton94ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton94ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
      //  bidderSearchbtn.setEnabled(false);
        
        reportName=2015;
        
        //JOptionPane.showMessageDialog(this, "For Specific Bidders Tick the -Schedules For Bidders- Button", "Message", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
//        bidderSearchbtn.setEnabled(true);
        reportName= 2016;
    }//GEN-LAST:event_jCheckBox2ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //    new DatePanel(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    public java.util.Vector getBeginEndDates() {
        
        dateStartEnd = new java.util.Vector(1,1);
        
        dateStartEnd.addElement(beginDateSpinner.getValue().toString());
        
        dateStartEnd.addElement(endDateSpinner.getValue().toString());
        
        return dateStartEnd;
        
    }
    
    public void getReport(int reportName) {
      
//        switch (reportName) {
//            
//           
//            
//            case 2015:
//                
//            {
//                com.afrisoftech.hospinventory.TenderQtyBalancePDF policy = new com.afrisoftech.hospinventory.TenderQtyBalancePDF();
//                
//                policy.TenderQtyBalancePDF(connectDB, tenderNotxt.getText());
//                
//                 
//            } break;
//            
//            case 2016:
//                
//                
//            {
//                            com.afrisoftech.hospinventory.mtrhreports.SupplierNotificationPDF policys = new com.afrisoftech.hospinventory.mtrhreports.SupplierNotificationPDF();
//
//                            policys.SupplierNotificationPDF(connectDB,tenderNotxt.getText().toString(),supplierTxt.getText());
//                
//                
//                //this.dispose();
//                
//            } break;
//                
//            
//            
//            
//            default :  ;
//        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog bidderSearch;
    private javax.swing.JTable bidderSearchTable;
    private javax.swing.JTextField bidderSearchText;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField descTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton93;
    private javax.swing.JButton jButton94;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPanel jSearchPanel213;
    private javax.swing.JPanel jSearchPanel214;
    private javax.swing.JScrollPane jSearchScrollPane4;
    private javax.swing.JScrollPane jSearchScrollPane5;
    private javax.swing.JTable jSearchTable4;
    private javax.swing.JTextField jTextField115;
    private javax.swing.JButton patientSearchbtn1;
    private javax.swing.ButtonGroup schedulesbtngrp;
    public static javax.swing.JTextField tenderNotxt;
    private javax.swing.JDialog tenderSearch;
    private javax.swing.JMenuItem what;
    // End of variables declaration//GEN-END:variables
    
}
