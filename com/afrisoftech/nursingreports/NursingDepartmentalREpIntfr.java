/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.nursingreports;

import java.sql.Connection;

/**
 *
 * @author saqlever
 */
public class NursingDepartmentalREpIntfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form NursingDepartmentalREpIntfr
     */
    private static Connection connectDB;

    public NursingDepartmentalREpIntfr(java.sql.Connection connDB) {
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

        reportTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cardiologyTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        renalTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        staffTextArea = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        aminipatiTextArea = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        genremTextArea = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        drugShortageTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        emergencydialyTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Departmental /Units Report...");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main Reports", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N
        jPanel5.setLayout(new java.awt.GridBagLayout());

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "PATIENTS SEEN", null, null},
                {null, "PATIENTS OBSERVED", null, null},
                {null, "PATIENTS REHYDRATED", null, null},
                {null, "PATIENTS RESUSCITATED", null, null},
                {null, "ADMITTED", null, null},
                {null, "REFERRAL CASES", null, null},
                {null, "NO OF DEATHS", null, null},
                {null, "BODIES IN WARD", null, null},
                {null, "NO OF BIDS", null, null},
                {null, "NO OF DISCHARGES", null, null},
                {null, "NO OF TRANSFERS IN", null, null},
                {null, "NO OF TRANSFERS OUT", null, null},
                {null, "MORTUARY INFORMED", null, null}
            },
            new String [] {
                "Tick To Report", "Report Type", "No", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainTable.setRowHeight(20);
        jScrollPane1.setViewportView(mainTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel5.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        jPanel2.add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CARDIOLOGY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N
        jPanel6.setLayout(new java.awt.GridBagLayout());

        cardiologyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "ECHOCARDIOGRAMS", null, null},
                {null, "ECG", null, null},
                {null, "EST", null, null},
                {null, "PROCEDURES DONE IN CATH LAB", null, null}
            },
            new String [] {
                "Tick To Report", "Report Type", "No", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cardiologyTable.setRowHeight(20);
        jScrollPane2.setViewportView(cardiologyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel6.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jPanel6, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RENAL..", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N
        jPanel7.setLayout(new java.awt.GridBagLayout());

        renalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "PATIENTS DIALYZED", null, null},
                {null, "PATIENTS WAITING DIALYSIS", null, null},
                {null, "PATIENTS WITH ART ON HD", null, null},
                {null, "PATIENTS DIALYSING", null, null},
                {null, "PATIENTS IN CAPD", null, null},
                {null, "TRANSPLANTED PATIENTS", null, null}
            },
            new String [] {
                "Tick To Report", "Report Type", "NO", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        renalTable.setRowHeight(20);
        jScrollPane3.setViewportView(renalTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        jPanel2.add(jPanel7, gridBagConstraints);

        reportTabbedPane.addTab("Reports", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 0, 204))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        staffTextArea.setColumns(20);
        staffTextArea.setRows(5);
        staffTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrative Incidences Affecting Staff"));
        jScrollPane7.setViewportView(staffTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jScrollPane7, gridBagConstraints);

        aminipatiTextArea.setColumns(20);
        aminipatiTextArea.setRows(5);
        aminipatiTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrative Incidences Affecting Patients"));
        jScrollPane6.setViewportView(aminipatiTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jScrollPane6, gridBagConstraints);

        genremTextArea.setColumns(20);
        genremTextArea.setRows(5);
        genremTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("General Remarks"));
        jScrollPane8.setViewportView(genremTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jScrollPane8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        drugShortageTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null}
            },
            new String [] {
                "Drugs / Other Supplies (Shortage)", "Qty", "Comment"
            }
        ));
        drugShortageTable.setRowHeight(23);
        jScrollPane5.setViewportView(drugShortageTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.7;
        jPanel9.add(jScrollPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        jPanel3.add(jPanel9, gridBagConstraints);

        reportTabbedPane.addTab("STAFF REPORT", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        emergencydialyTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "IP NO", "Paid", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        emergencydialyTable.setRowHeight(24);
        jScrollPane9.setViewportView(emergencydialyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane9, gridBagConstraints);

        reportTabbedPane.addTab("EMERGENCY DIALYSIS", jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        getContentPane().add(reportTabbedPane, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        saveButton.setText("Save....");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(saveButton, gridBagConstraints);

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 300);
        jPanel1.add(clearButton, gridBagConstraints);

        jLabel1.setText("              ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("DEPARTMENT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel10.add(jLabel2, gridBagConstraints);

        departmentComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '0select-') union   (SELECT distinct Clinics FROM pb_clinics )union (select distinct ward_name  from hp_wards )  ORDER BY 1 asc"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 500);
        jPanel10.add(departmentComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        getContentPane().add(jPanel10, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        com.afrisoftech.lib.ClearTable.clearthisTable(mainTable);
        com.afrisoftech.lib.ClearTable.clearthisTable(renalTable);
        com.afrisoftech.lib.ClearTable.clearthisTable(cardiologyTable);
        departmentComboBox.setSelectedIndex(0);

    }//GEN-LAST:event_clearButtonActionPerformed
    private void saveReportDetails() {
        if (reportTabbedPane.getSelectedIndex() == -0) {
            try {
                connectDB.setAutoCommit(false);
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement(
                        "INSERT INTO nursing.unit_report(\n"
                        + "            report_type, no, comment, department, reported_by, date)\n"
                        + "    VALUES (?, ?, ?, ?,  current_user, localtimestamp);");
                for (int i = 0; i > mainTable.getRowCount(); i++) {
                    if (mainTable.getValueAt(i, 0) == Boolean.TRUE) {

                        pstmt.setObject(1, mainTable.getValueAt(i, 1));
                        pstmt.setObject(2, mainTable.getValueAt(i, 2));
                        pstmt.setObject(3, mainTable.getValueAt(i, 3));
                        pstmt.setObject(4, departmentComboBox.getSelectedItem());
                        pstmt.executeUpdate();
                    }
                }
                for (int i = 0; i > cardiologyTable.getRowCount(); i++) {
                    if (cardiologyTable.getValueAt(i, 0) == Boolean.TRUE) {

                        pstmt.setObject(1, cardiologyTable.getValueAt(i, 1));
                        pstmt.setObject(2, cardiologyTable.getValueAt(i, 2));
                        pstmt.setObject(3, cardiologyTable.getValueAt(i, 3));
                        pstmt.setObject(4, departmentComboBox.getSelectedItem());
                        pstmt.executeUpdate();
                    }
                }
                for (int i = 0; i > renalTable.getRowCount(); i++) {
                    if (renalTable.getValueAt(i, 0) == Boolean.TRUE) {

                        pstmt.setObject(1, renalTable.getValueAt(i, 1));
                        pstmt.setObject(2, renalTable.getValueAt(i, 2));
                        pstmt.setObject(3, renalTable.getValueAt(i, 3));
                        pstmt.setObject(4, departmentComboBox.getSelectedItem());
                        pstmt.executeUpdate();
                    }
                }
                connectDB.commit();
                connectDB.setAutoCommit(true);

                javax.swing.JOptionPane.showMessageDialog(this, " saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                System.out.println(" error is " + sq);
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (reportTabbedPane.getSelectedIndex() == 1) {
            try {
                connectDB.setAutoCommit(false);
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement(
                        "INSERT INTO nursing.shortages(\n"
                        + "            drug, shortage, comment, department, reported_by, date)\n"
                        + "    VALUES (?, ?, ?, ?,  current_user, localtimestamp);");
                for (int i = 0; i > drugShortageTable.getRowCount(); i++) {
                    if (drugShortageTable.getValueAt(i, 0) != null) {

                        pstmt.setObject(1, drugShortageTable.getValueAt(i, 1));
                        pstmt.setObject(2, drugShortageTable.getValueAt(i, 2));
                        pstmt.setObject(3, drugShortageTable.getValueAt(i, 3));
                        pstmt.setObject(4, departmentComboBox.getSelectedItem());
                        pstmt.executeUpdate();
                    }
                }

                java.sql.PreparedStatement pstmtst = connectDB.prepareStatement(
                        "INSERT INTO nursing.staff_report(\n"
                        + "            patientincidence, staffincidence, genremarks, department, reported_by, \n"
                        + "            date)\n"
                        + "    VALUES (?, ?, ?, ?,   current_user, localtimestamp);");

                {

                    pstmtst.setObject(1, aminipatiTextArea.getText());
                    pstmtst.setObject(2, staffTextArea.getText());
                    pstmtst.setObject(3, genremTextArea.getText());
                    pstmtst.setObject(4, departmentComboBox.getSelectedItem());
                    pstmtst.executeUpdate();
                }

                connectDB.commit();
                connectDB.setAutoCommit(true);

                javax.swing.JOptionPane.showMessageDialog(this, " saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                System.out.println(" error is " + sq);
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (reportTabbedPane.getSelectedIndex() == 2) {
            try {
                connectDB.setAutoCommit(false);
                java.sql.PreparedStatement pstmt = connectDB.prepareStatement(
                        "INSERT INTO nursing.emergency_dialysis(\n"
                        + "            ipno, paid, comment, department, reported_by, date)\n"
                        + "    VALUES (?, ?, ?, ?,  current_user, localtimestamp);");
                for (int i = 0; i > emergencydialyTable.getRowCount(); i++) {
                    if (emergencydialyTable.getValueAt(i, 0) != null) {

                        pstmt.setObject(1, emergencydialyTable.getValueAt(i, 1));
                        pstmt.setObject(2, emergencydialyTable.getValueAt(i, 2));
                        pstmt.setObject(3, emergencydialyTable.getValueAt(i, 3));
                        pstmt.setObject(4, departmentComboBox.getSelectedItem());
                        pstmt.executeUpdate();
                    }
                }
                connectDB.commit();
                connectDB.setAutoCommit(true);

                javax.swing.JOptionPane.showMessageDialog(this, " saved successfully", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                System.out.println(" error is " + sq);
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (departmentComboBox.getSelectedIndex() > 0) {

            saveReportDetails();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please Select Department First", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_saveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea aminipatiTextArea;
    private javax.swing.JTable cardiologyTable;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox departmentComboBox;
    private javax.swing.JTable drugShortageTable;
    private javax.swing.JTable emergencydialyTable;
    private javax.swing.JTextArea genremTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable mainTable;
    private javax.swing.JTable renalTable;
    private javax.swing.JTabbedPane reportTabbedPane;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextArea staffTextArea;
    // End of variables declaration//GEN-END:variables
}
