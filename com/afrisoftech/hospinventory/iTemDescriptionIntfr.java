/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.ClearTable;
import com.afrisoftech.lib.ComboBoxModel;
import com.afrisoftech.lib.DBObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.JOptionPane;

;

/**
 *
 * @author saleem
 */
public class iTemDescriptionIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    com.afrisoftech.lib.DBObject DBObject = new com.afrisoftech.lib.DBObject();

    /**
     * Creates new form iTemDescriptionIntfr
     */
    public iTemDescriptionIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {

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

        itemSearch = new javax.swing.JDialog();
        jSearchPanel = new javax.swing.JPanel();
        jTextField111 = new javax.swing.JTextField();
        jSearchScrollPane = new javax.swing.JScrollPane();
        jSearchTable = new com.afrisoftech.dbadmin.JTable();
        jButton9 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        specsTbl = new com.afrisoftech.dbadmin.JTable();
        jPanel9 = new javax.swing.JPanel();
        saveSpecsbtn = new javax.swing.JButton();
        editSpecsbtn = new javax.swing.JButton();
        rmveItembtn = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        codeTxt = new javax.swing.JTextField();
        categoryTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        storeNamecmx = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        descriptionTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        SubStoreCbx = new javax.swing.JCheckBox();
        centralStoresCbx = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        thresholdtxt = new javax.swing.JTextField();
        servicescmb = new javax.swing.JCheckBox();

        itemSearch.setModal(true);
        itemSearch.setUndecorated(true);
        itemSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        jSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSearchPanel.setLayout(new java.awt.GridBagLayout());

        jTextField111.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField111CaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jTextField111, gridBagConstraints);

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
        gridBagConstraints.weighty = 1.0;
        jSearchPanel.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        itemSearch.getContentPane().add(jSearchPanel, gridBagConstraints);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tender Specifications per item  for Tender Evaluation in the Current Fiscal Year");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)));

        specsTbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        specsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "#", "Specification(e.g DIMENSIONS: Length 4m)", "Weight(Value)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        specsTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                specsTblMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                specsTblMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(specsTbl);
        if (specsTbl.getColumnModel().getColumnCount() > 0) {
            specsTbl.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        jPanel8.add(jScrollPane2, gridBagConstraints);

        jPanel9.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        saveSpecsbtn.setBackground(new java.awt.Color(0, 255, 204));
        saveSpecsbtn.setText("Save Specs");
        saveSpecsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSpecsbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        jPanel9.add(saveSpecsbtn, gridBagConstraints);

        editSpecsbtn.setBackground(new java.awt.Color(0, 255, 204));
        editSpecsbtn.setText("       Edit       ");
        editSpecsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSpecsbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        jPanel9.add(editSpecsbtn, gridBagConstraints);

        rmveItembtn.setBackground(new java.awt.Color(0, 255, 204));
        rmveItembtn.setText("Remove Row");
        rmveItembtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmveItembtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        jPanel9.add(rmveItembtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel8.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("Item/Service Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jLabel10, gridBagConstraints);

        codeTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel10.add(codeTxt, gridBagConstraints);

        categoryTxt.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel10.add(categoryTxt, gridBagConstraints);

        jLabel1.setText("Item Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jLabel2, gridBagConstraints);

        storeNamecmx.setBackground(new java.awt.Color(204, 255, 255));
        storeNamecmx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        storeNamecmx.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        storeNamecmx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeNamecmxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel10.add(storeNamecmx, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        descriptionTxt.setEditable(false);
        descriptionTxt.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(descriptionTxt, gridBagConstraints);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel10.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Store Category( Stock Item Specs)"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(SubStoreCbx);
        SubStoreCbx.setText("Sub Stores");
        SubStoreCbx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SubStoreCbxItemStateChanged(evt);
            }
        });
        SubStoreCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubStoreCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(SubStoreCbx, gridBagConstraints);

        buttonGroup1.add(centralStoresCbx);
        centralStoresCbx.setText("Central Stores");
        centralStoresCbx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centralStoresCbxItemStateChanged(evt);
            }
        });
        centralStoresCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centralStoresCbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(centralStoresCbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel2, gridBagConstraints);

        jCheckBox1.setText("Use Existing Values");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel10.add(jCheckBox1, gridBagConstraints);

        jLabel3.setText("Threshold for Evaluation (%)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel10.add(jLabel3, gridBagConstraints);

        thresholdtxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        thresholdtxt.setForeground(new java.awt.Color(204, 0, 51));
        thresholdtxt.setText("100");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel10.add(thresholdtxt, gridBagConstraints);

        servicescmb.setText("Services Specs");
        servicescmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicescmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel10.add(servicescmb, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel8, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void specsTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_specsTblMouseClicked
        // TODO add your handling code here:

        int i = 0;
        int k = 0;
        if (specsTbl.isColumnSelected(0) || specsTbl.isColumnSelected(1)) {
            i = specsTbl.getSelectedRow();
            specsTbl.setValueAt(i + 1, specsTbl.getSelectedRow(), 0);
        }
    }//GEN-LAST:event_specsTblMouseClicked

    private void saveSpecsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSpecsbtnActionPerformed
        // TODO add your handling code here:
        int r = 0;

        if (saveSpecsbtn.getLabel().equalsIgnoreCase("Save Specs")) {
            try {

                for (int i = 0; i < specsTbl.getRowCount(); i++) {
                    if (specsTbl.getValueAt(i, 1) != null) {

                        System.out.println(descriptionTxt.getText().toString() + "---" + specsTbl.getValueAt(i, 1));
                        java.sql.PreparedStatement stmt1 = connectDB.prepareStatement("INSERT INTO st_criteria(pr_no,  item_desc, specs,financial_year,mark,threshold) VALUES (?, ?, ?,?,?,?);");
                        stmt1.setObject(1, "");
                        stmt1.setObject(2, descriptionTxt.getText().toString());
                        stmt1.setObject(3, specsTbl.getValueAt(i, 1));
                        stmt1.setObject(4, com.afrisoftech.lib.FinancialYear.getActiveFinancialYear(connectDB).toUpperCase());
                        stmt1.setDouble(5, Double.valueOf(specsTbl.getValueAt(i, 2).toString()));
                        stmt1.setDouble(6, Double.valueOf(thresholdtxt.getText().toString()));
                        //threshold
                        stmt1.executeUpdate();
                    }

                }
                javax.swing.JOptionPane.showMessageDialog(this, "Data Inserted 0r Updated Successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());

            }
        } else {
            //int a=0;
            try {

                java.sql.PreparedStatement stmt2del = connectDB.prepareStatement("DELETE from st_criteria  WHERE  item_desc ilike '" + descriptionTxt.getText().toString() + "';");
                stmt2del.executeUpdate();

                for (int i = 0; i < specsTbl.getRowCount(); i++) {
                    if (specsTbl.getValueAt(i, 1) != null) {
                        //java.sql.PreparedStatement stmt2 = connectDB.prepareStatement("UPDATE st_criteria SET specs='"+specsTbl.getValueAt(i, 1)+"', mark='"+ Double.valueOf(specsTbl.getValueAt(i, 2).toString())+"' WHERE  item_desc ilike'"+descriptionTxt.getText().toString()+"';");
                        java.sql.PreparedStatement stmt2 = connectDB.prepareStatement("INSERT INTO st_criteria(pr_no,  item_desc, specs,financial_year,mark,threshold) VALUES (?, ?, ?,?,?,?);");
                        stmt2.setObject(1, "-");
                        stmt2.setObject(2, descriptionTxt.getText().toString());
                        stmt2.setObject(3, specsTbl.getValueAt(i, 1));
                        stmt2.setObject(4, com.afrisoftech.lib.FinancialYear.getActiveFinancialYear(connectDB).toUpperCase());
                        stmt2.setDouble(5, Double.valueOf(specsTbl.getValueAt(i, 2).toString()));
                        stmt2.setDouble(6, Double.valueOf(thresholdtxt.getText().toString()));

                        stmt2.executeUpdate();
                    }

                }
                javax.swing.JOptionPane.showMessageDialog(this, "Specs Updated Successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        }

    }//GEN-LAST:event_saveSpecsbtnActionPerformed

    private void editSpecsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSpecsbtnActionPerformed
        // TODO add your handling code here:
        if (descriptionTxt.getText().toString().equalsIgnoreCase("-")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select Item first");

        } else {
            saveSpecsbtn.setLabel("Update");

            ClearTable.clearthisTable(specsTbl);

            try {

                java.sql.Statement b = connectDB.createStatement();
                java.sql.ResultSet a = b.executeQuery("SELECT distinct  specs,mark,threshold FROM st_criteria where item_desc ilike '" + descriptionTxt.getText().toString() + "';");
                int i = 0;
                while (a.next()) {

                    specsTbl.setValueAt(codeTxt.getText() + "00" + i + 1, i, 0);
                    specsTbl.setValueAt(a.getObject(1), i, 1);
                    specsTbl.setValueAt(a.getObject(2), i, 2);
                    thresholdtxt.setText(a.getObject(3).toString());
                    i++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

                Logger.getLogger(RaiseExternalPrInfr.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_editSpecsbtnActionPerformed

    private void centralStoresCbxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_centralStoresCbxItemStateChanged
        // TODO add your handling code here:


    }//GEN-LAST:event_centralStoresCbxItemStateChanged

    private void storeNamecmxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeNamecmxActionPerformed
        // TODO add your handling code here:
        if (centralStoresCbx.isSelected()) {

        } else {
        }
    }//GEN-LAST:event_storeNamecmxActionPerformed

    private void SubStoreCbxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SubStoreCbxItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_SubStoreCbxItemStateChanged

    private void SubStoreCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubStoreCbxActionPerformed
        // TODO add your handling code here:
        storeNamecmx.setModel(ComboBoxModel.ComboBoxModel(connectDB, "select DISTINCT  initcap(store_name) from st_stores ORDER BY 1"));
    }//GEN-LAST:event_SubStoreCbxActionPerformed

    private void centralStoresCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centralStoresCbxActionPerformed
        // TODO add your handling code here:
        storeNamecmx.setModel(ComboBoxModel.ComboBoxModel(connectDB, "select distinct initcap(store_name) from st_main_stores Order by 1"));
    }//GEN-LAST:event_centralStoresCbxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (storeNamecmx.getSelectedItem().toString().equalsIgnoreCase("-")) {

            javax.swing.JOptionPane.showMessageDialog(this, "Please Select A Stock Outlet");
        } else {

            System.out.println("Showing dialog");

            itemSearch.dispose();
            java.awt.Point point = this.jLabel10.getLocationOnScreen();
            itemSearch.setSize(600, 200);
            itemSearch.setLocation(point);
            itemSearch.setVisible(true);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField111CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField111CaretUpdate
        if (jTextField111.getCaretPosition() > 4) {
//        }
//        else {
            com.afrisoftech.dbadmin.setFixedColumns.DisallowReordering(jSearchTable);
            if (centralStoresCbx.isSelected()) {
                jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsCaret(connectDB, "select DISTINCT item_code,description FROM stockitem WHERE (description ILIKE '%" + jTextField111.getText() + "%' OR item_code ILIKE '%" + jTextField111.getText() + "%' )AND department ILIKE '%" + storeNamecmx.getSelectedItem() + "%' order by description"));

                jSearchTable.setShowHorizontalLines(false);
                jSearchScrollPane.setViewportView(jSearchTable);
            } else {
                if (servicescmb.isSelected()) {
                    jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsCaret(connectDB, "SELECT DISTINCT service_code, service FROM services_list WHERE service ILIKE '%" + jTextField111.getText() + "%'  and department='" + storeNamecmx.getSelectedItem() + "'  ORDER BY service_code asc"));
                    jSearchTable.setShowHorizontalLines(false);
                    jSearchScrollPane.setViewportView(jSearchTable);
                } 
                else {
                    jSearchTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectorsCaret(connectDB, "select DISTINCT product_id,product FROM stockprices WHERE (product ILIKE '%" + jTextField111.getText() + "%' OR item_code ILIKE '%" + jTextField111.getText() + "%' ) AND department ILIKE '%" + storeNamecmx.getSelectedItem() + "%' ORDER BY product"));
                }

                jSearchTable.setShowHorizontalLines(false);
                jSearchScrollPane.setViewportView(jSearchTable);

            }

        }

        // Add your handling code here:
    }//GEN-LAST:event_jTextField111CaretUpdate

    private void jSearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchTableMouseClicked
        descriptionTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 1).toString());
        codeTxt.setText(jSearchTable.getValueAt(jSearchTable.getSelectedRow(), 0).toString());

        itemSearch.dispose();

        System.out.println("Starting To Obtain The Item category");
        // int i = issuingItemsTable.getSelectedRow();
        if (this.centralStoresCbx.isSelected()) {
            try {
                java.sql.Statement pstmt1 = connectDB.createStatement();
                java.sql.ResultSet rs1 = pstmt1.executeQuery("select distinct sub_cat_code from st_stock_item where description ilike '" + descriptionTxt.getText() + "'");

                while (rs1.next()) {

                    categoryTxt.setText(DBObject.getDBObject(rs1.getObject(1).toString(), "-"));
                    System.out.println(DBObject.getDBObject(rs1.getObject(1).toString(), "-"));
                }
            } catch (java.sql.SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
        } else {
            try {
                java.sql.Statement pstmt1 = connectDB.createStatement();
                java.sql.ResultSet rs1 = pstmt1.executeQuery("Select category from st_stock_prices where product ilike '" + descriptionTxt.getText() + "'");

                while (rs1.next()) {
                    categoryTxt.setText(DBObject.getDBObject(rs1.getObject(1), "-"));
                    System.out.println(DBObject.getDBObject(rs1.getObject(1), "-"));
                    //jTable1.setValueAt(rs1.getObject(2),i,1);
                }
            } catch (java.sql.SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
        }

        // Add your handling code here:
    }//GEN-LAST:event_jSearchTableMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        itemSearch.dispose();        // Add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void rmveItembtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmveItembtnActionPerformed
        // TODO add your handling code here:
        ClearTable.removeSelectedRows(specsTbl);
    }//GEN-LAST:event_rmveItembtnActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (descriptionTxt.getText().length() > 0) {

            try {

                PreparedStatement pst = connectDB.prepareStatement("SELECT DISTINCT specs from st_criteria WHERE item_desc ilike '" + descriptionTxt.getText() + "'");

                ResultSet rset1 = pst.executeQuery();
                int i = 0;
                while (rset1.next()) {
                    specsTbl.setValueAt(i + 1, i, 0);
                    specsTbl.setValueAt(rset1.getObject(1), i, 1);

                    i++;

                }

                if (i == 0) {
                    JOptionPane.showMessageDialog(this, "Item'S Specs Not Registered", "Error Message", JOptionPane.ERROR_MESSAGE);

                }

            } catch (SQLException ex) {
                Logger.getLogger(iTemDescriptionIntfr.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            JOptionPane.showMessageDialog(this, "No Item Selected", "Error Message", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void specsTblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_specsTblMouseReleased
        // TODO add your handling code here:
        if (specsTbl.getValueAt(specsTbl.getSelectedRow(), 1) != null) {

            specsTbl.setValueAt(1.00, specsTbl.getSelectedRow(), 2);

        }
    }//GEN-LAST:event_specsTblMouseReleased

    private void servicescmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicescmbActionPerformed
        // TODO add your handling code here:
        storeNamecmx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select '-' union select distinct initcap(depart_name) from pb_main_department order by 1"));
    }//GEN-LAST:event_servicescmbActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox SubStoreCbx;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField categoryTxt;
    private javax.swing.JCheckBox centralStoresCbx;
    private javax.swing.JTextField codeTxt;
    private javax.swing.JTextField descriptionTxt;
    private javax.swing.JButton editSpecsbtn;
    private javax.swing.JDialog itemSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jSearchPanel;
    private javax.swing.JScrollPane jSearchScrollPane;
    private javax.swing.JTable jSearchTable;
    private javax.swing.JTextField jTextField111;
    private javax.swing.JButton rmveItembtn;
    private javax.swing.JButton saveSpecsbtn;
    private javax.swing.JCheckBox servicescmb;
    private javax.swing.JTable specsTbl;
    private javax.swing.JComboBox storeNamecmx;
    private javax.swing.JTextField thresholdtxt;
    // End of variables declaration//GEN-END:variables
}
