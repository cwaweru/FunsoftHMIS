/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.accounting;

import java.sql.SQLException;
//

/**
 *
 * @author Charles
 */
public class MergingElectronicAndManualOrdersInfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form MergingElectronicAndManualOrdersInfr
     */
    java.sql.Connection connectDB = null;
    
    public MergingElectronicAndManualOrdersInfr(java.sql.Connection connDB) {
       connectDB = connDB;
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

        sectionsDialog = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton9 = new javax.swing.JButton();
        sectionsDialog1 = new javax.swing.JDialog();
        jSearchPanel1 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jSearchScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable1 = new com.afrisoftech.dbadmin.JTable();
        jButton10 = new javax.swing.JButton();
        searchOrdersPanel = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        commitedLPONumberTxt = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jPanel60 = new javax.swing.JPanel();
        deliveryLPOTxt = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        deliveryLPODetailsTxt = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        commitedLPODetailsTxt = new javax.swing.JTextArea();
        commandsPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        closeFormBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        sectionsDialog.setModal(true);
        sectionsDialog.setUndecorated(true);
        sectionsDialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        jTextField11.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel.add(jTextField11, gridBagConstraints);

        jSearchTable.setShowHorizontalLines(false);
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
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sectionsDialog.getContentPane().add(jSearchPanel, gridBagConstraints);

        sectionsDialog1.setModal(true);
        sectionsDialog1.setUndecorated(true);
        sectionsDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel1.setLayout(new java.awt.GridBagLayout());

        jTextField12.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField12CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 100);
        jSearchPanel1.add(jTextField12, gridBagConstraints);

        jSearchTable1.setShowHorizontalLines(false);
        jSearchTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchTable1MouseClicked(evt);
            }
        });
        jSearchScrollPane1.setViewportView(jSearchTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        jSearchPanel1.add(jSearchScrollPane1, gridBagConstraints);

        jButton10.setText("Dispose");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel1.add(jButton10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sectionsDialog1.getContentPane().add(jSearchPanel1, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Form for merging electonically generated orders to manual orders");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        searchOrdersPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchOrdersPanel.setLayout(new java.awt.GridBagLayout());

        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder("Committed LPO number"));
        jPanel59.setLayout(new java.awt.GridBagLayout());

        commitedLPONumberTxt.setEditable(false);
        commitedLPONumberTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        commitedLPONumberTxt.setForeground(new java.awt.Color(102, 102, 102));
        commitedLPONumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commitedLPONumberTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel59.add(commitedLPONumberTxt, gridBagConstraints);

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
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        searchOrdersPanel.add(jPanel59, gridBagConstraints);

        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder("LPO Number used to generate GRN"));
        jPanel60.setLayout(new java.awt.GridBagLayout());

        deliveryLPOTxt.setEditable(false);
        deliveryLPOTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        deliveryLPOTxt.setForeground(new java.awt.Color(102, 102, 102));
        deliveryLPOTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryLPOTxtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel60.add(deliveryLPOTxt, gridBagConstraints);

        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel60.add(jButton24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        searchOrdersPanel.add(jPanel60, gridBagConstraints);

        deliveryLPODetailsTxt.setEditable(false);
        deliveryLPODetailsTxt.setColumns(20);
        deliveryLPODetailsTxt.setRows(5);
        jScrollPane1.setViewportView(deliveryLPODetailsTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        searchOrdersPanel.add(jScrollPane1, gridBagConstraints);

        commitedLPODetailsTxt.setEditable(false);
        commitedLPODetailsTxt.setColumns(20);
        commitedLPODetailsTxt.setRows(5);
        jScrollPane2.setViewportView(commitedLPODetailsTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 5.0;
        searchOrdersPanel.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        getContentPane().add(searchOrdersPanel, gridBagConstraints);

        commandsPanel.setLayout(new java.awt.GridBagLayout());

        saveBtn.setText("Merge Order Numbers");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        commandsPanel.add(saveBtn, gridBagConstraints);

        closeFormBtn.setText("Close form");
        closeFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        commandsPanel.add(closeFormBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        commandsPanel.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(commandsPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void commitedLPONumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commitedLPONumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commitedLPONumberTxtActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:

        System.out.println("Showing dialog");
        java.awt.Point point = commitedLPONumberTxt.getLocationOnScreen();
        sectionsDialog.setSize(700, 200);
        sectionsDialog.setLocation(point);
        sectionsDialog.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void deliveryLPOTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryLPOTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryLPOTxtActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:

        System.out.println("Showing dialog");
        java.awt.Point point = deliveryLPOTxt.getLocationOnScreen();
        sectionsDialog1.setSize(700, 200);
        sectionsDialog1.setLocation(point);
        sectionsDialog1.setVisible(true);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTextField11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11CaretUpdate
        if (jTextField11.getCaretPosition() > 3) {

            //
            jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT refno as order_no, supplier, allocated_amount from ac_aie_commitment where refno ilike '%" + jTextField11.getText() + "%' "));
            //jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select activity, code FROM pb_activity WHERE (activity ILIKE '%pharm%' OR activity ILIKE '%supplies%' OR sub_category ILIKE '%stock%') AND activity ilike '%" + jTextField11.getText() + "%'"));

            jSearchScrollPane.setViewportView(jSearchTable);

            System.out.println("Listed The Stock Outlets");

        }        // Add your handling code here:
    }//GEN-LAST:event_jTextField11CaretUpdate

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked

        commitedLPONumberTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0).toString());
        
        commitedLPODetailsTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0).toString()+ " " +jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1).toString() +" " + jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 2).toString());
        
        this.sectionsDialog.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.sectionsDialog.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField12CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField12CaretUpdate
        if (jTextField12.getCaretPosition() > 3) {

            //
            jSearchTable1.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, " SELECT order_no, store, item, quantity_received, debit from st_stock_cardex where order_no ilike '%" + jTextField12.getText() + "%'"));
            //jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "select activity, code FROM pb_activity WHERE (activity ILIKE '%pharm%' OR activity ILIKE '%supplies%' OR sub_category ILIKE '%stock%') AND activity ilike '%" + jTextField11.getText() + "%'"));

            jSearchScrollPane1.setViewportView(jSearchTable1);

            System.out.println("Listed The Stock Outlets");

        }        // Add your handling code here:
    }//GEN-LAST:event_jTextField12CaretUpdate

    private void jSearchTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTable1MouseClicked

        deliveryLPOTxt.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString());

        deliveryLPODetailsTxt.setText(jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 0).toString()+ " " +jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 1).toString() +" " + jSearchTable1.getValueAt(jSearchTable1.getSelectedRow(), 2).toString());
       
        this.sectionsDialog1.dispose();
        // Add your handling code here:
    }//GEN-LAST:event_jSearchTable1MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.sectionsDialog1.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void closeFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormBtnActionPerformed

        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_closeFormBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("UPDATE st_stock_cardex SET order_no = ? WHERE order_no = ?");
            pstmt.setString(1, commitedLPONumberTxt.getText());
            pstmt.setString(2, deliveryLPOTxt.getText());
            pstmt.executeUpdate();
            
            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("INSERT INTO lpo_update_trail(original_lpo_number, new_lpo_number) VALUES (?, ?)");
            pstmt1.setString(1, deliveryLPOTxt.getText());
            pstmt1.setString(2, commitedLPONumberTxt.getText());
            pstmt1.executeUpdate();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Order number updated successfully");
            // TODO add your handling code here:
        } catch (SQLException ex) {
            //javax.swing.JOptionPane.showMessageDialog(this, "Order number updated successfully");
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
                        ex.printStackTrace();             //ex.printStackTrace();
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeFormBtn;
    private javax.swing.JPanel commandsPanel;
    private javax.swing.JTextArea commitedLPODetailsTxt;
    private javax.swing.JTextField commitedLPONumberTxt;
    private javax.swing.JTextArea deliveryLPODetailsTxt;
    private javax.swing.JTextField deliveryLPOTxt;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JPanel jSearchPanel1;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JScrollPane jSearchScrollPane1;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTable jSearchTable1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel searchOrdersPanel;
    private javax.swing.JDialog sectionsDialog;
    private javax.swing.JDialog sectionsDialog1;
    // End of variables declaration//GEN-END:variables
}