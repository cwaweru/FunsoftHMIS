/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hr;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.afrisoftech.lib.ClearTable;
import javax.swing.JOptionPane;

/**
 *
 * @author afro
 */
public class LeaveApprovalFormIntfr extends javax.swing.JInternalFrame {

    com.afrisoftech.lib.DBObject dbObject;

    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    private int offset;
    private int limit;

    /**
     * Creates new form LeaveRecommendationForm
     */
    public LeaveApprovalFormIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;
        pConnDB = pconnDB;

        initComponents();
        populateCmbox();
        employeeDepartmentPanel.setVisible(false);
        datePanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void populateCmbox() {
        employeeDepartmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT DISTINCT department FROM master_file "
                + "ORDER BY department"));
        employeeDepartmentCmbx.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeDepartmentCmbxActionPerformed(evt);
            }
        });
    }

    private void ShowVisibleQueryItems(Integer offset) {

        try {
            System.out.println("SELECT emp_no, name_of_employee, job_group, department, type_of_leave, start_date, duration, end_date, reason "
                    + "FROM hr.hr_leave_application WHERE department = '" + employeeDepartmentCmbx.getSelectedItem().toString().trim() + "' "
                    + "ORDER BY timestamp LIMIT " + leaveApplicantsListTbl.getRowCount() + " OFFSET - " + offset + "  ");

            PreparedStatement pst = null;

            if (allDepartmentOpBtn.isSelected()) {
                pst = connectDB.prepareStatement("SELECT emp_no, name_of_employee, job_group, type_of_leave, "
                        + "leave_days_after, start_date, duration, end_date FROM hr.hr_leave_application "
                        + "WHERE recommended = TRUE AND status = 'Pending' "
                        + "ORDER BY timestamp "
                        + "LIMIT " + leaveApplicantsListTbl.getRowCount() + " OFFSET " + offset + " ");
            } else {
                pst = connectDB.prepareStatement("SELECT emp_no, name_of_employee, job_group, type_of_leave, "
                        + "leave_days_after, start_date, duration, end_date FROM hr.hr_leave_application "
                        + "WHERE department = '" + employeeDepartmentCmbx.getSelectedItem().toString().trim() + "' "
                        + "AND recommended = TRUE AND status = 'Pending' "
                        + "ORDER BY timestamp "
                        + "LIMIT " + leaveApplicantsListTbl.getRowCount() + " OFFSET " + offset + " ");
            }

            ResultSet rset = pst.executeQuery();
            int i = 0;
            while (rset.next()) {
                leaveApplicantsListTbl.setValueAt(rset.getObject(1), i, 0);
                leaveApplicantsListTbl.setValueAt(rset.getObject(2), i, 1);
                leaveApplicantsListTbl.setValueAt(rset.getObject(3), i, 2);
                leaveApplicantsListTbl.setValueAt(rset.getObject(4), i, 3);
                leaveApplicantsListTbl.setValueAt(rset.getObject(5), i, 4);
                leaveApplicantsListTbl.setValueAt(rset.getObject(6), i, 5);
                leaveApplicantsListTbl.setValueAt(rset.getObject(7), i, 6);
                leaveApplicantsListTbl.setValueAt(rset.getObject(8), i, 7);

                for (int a = 1; a <= 8; a++) {
                    System.out.println("Values - " + rset.getObject(a));
                }

                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveApprovalFormIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        radioGroupBtn = new javax.swing.ButtonGroup();
        employeeDepartmentPanel = new javax.swing.JPanel();
        employeeDepartmentCmbx = new javax.swing.JComboBox();
        employeeLeaveListPanel = new javax.swing.JPanel();
        leaveApplicantsListScrollPane = new javax.swing.JScrollPane();
        leaveApplicantsListTbl = new javax.swing.JTable();
        btnNavigationPanel = new javax.swing.JPanel();
        naviEditBtn = new javax.swing.JButton();
        navRestBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        recomBtnPanel = new javax.swing.JPanel();
        optionButtonsPanel = new javax.swing.JPanel();
        allDepartmentOpBtn = new javax.swing.JRadioButton();
        specificDepartmentRbtn = new javax.swing.JRadioButton();
        datePanel = new javax.swing.JPanel();
        dateApprovedDatePicker = new com.afrisoftech.lib.DatePicker();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Leave Approval Form");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        employeeDepartmentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select the Department", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(246, 34, 14)));
        employeeDepartmentPanel.setLayout(new java.awt.GridBagLayout());

        employeeDepartmentCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        employeeDepartmentCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeDepartmentCmbxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        employeeDepartmentPanel.add(employeeDepartmentCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        getContentPane().add(employeeDepartmentPanel, gridBagConstraints);

        employeeLeaveListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List of Employees Who have applied for Leave", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(23, 19, 236)));
        employeeLeaveListPanel.setLayout(new java.awt.GridBagLayout());

        leaveApplicantsListTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee Number", "Employee Name", "Job Group", "Type Of Leave", "Leave Days Remaining", "Leave Start Date", "Days", "Leave End Date", "Approve?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leaveApplicantsListTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaveApplicantsListTblMouseClicked(evt);
            }
        });
        leaveApplicantsListScrollPane.setViewportView(leaveApplicantsListTbl);
        if (leaveApplicantsListTbl.getColumnModel().getColumnCount() > 0) {
            leaveApplicantsListTbl.getColumnModel().getColumn(0).setPreferredWidth(70);
            leaveApplicantsListTbl.getColumnModel().getColumn(1).setPreferredWidth(150);
            leaveApplicantsListTbl.getColumnModel().getColumn(2).setPreferredWidth(30);
            leaveApplicantsListTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
            leaveApplicantsListTbl.getColumnModel().getColumn(7).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(8).setResizable(false);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 200.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        employeeLeaveListPanel.add(leaveApplicantsListScrollPane, gridBagConstraints);

        btnNavigationPanel.setBackground(new java.awt.Color(223, 220, 217));
        btnNavigationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnNavigationPanel.setLayout(new java.awt.GridBagLayout());

        naviEditBtn.setText("   Edit Entries   ");
        naviEditBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        naviEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naviEditBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        btnNavigationPanel.add(naviEditBtn, gridBagConstraints);

        navRestBtn.setText("Reset Table");
        navRestBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        navRestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navRestBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        btnNavigationPanel.add(navRestBtn, gridBagConstraints);

        previousBtn.setBackground(new java.awt.Color(204, 204, 204));
        previousBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PREV1.GIF"))); // NOI18N
        previousBtn.setEnabled(false);
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        btnNavigationPanel.add(previousBtn, gridBagConstraints);

        nextBtn.setBackground(new java.awt.Color(204, 204, 204));
        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NEXT1.GIF"))); // NOI18N
        nextBtn.setEnabled(false);
        nextBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextBtn.setPreferredSize(new java.awt.Dimension(95, 25));
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        btnNavigationPanel.add(nextBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        employeeLeaveListPanel.add(btnNavigationPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 500.0;
        getContentPane().add(employeeLeaveListPanel, gridBagConstraints);

        recomBtnPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 10.0;
        getContentPane().add(recomBtnPanel, gridBagConstraints);

        optionButtonsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select one of the Options below.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(239, 54, 11)));
        optionButtonsPanel.setLayout(new java.awt.GridBagLayout());

        radioGroupBtn.add(allDepartmentOpBtn);
        allDepartmentOpBtn.setSelected(true);
        allDepartmentOpBtn.setText("Leave in All Departments");
        allDepartmentOpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allDepartmentOpBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 10);
        optionButtonsPanel.add(allDepartmentOpBtn, gridBagConstraints);

        radioGroupBtn.add(specificDepartmentRbtn);
        specificDepartmentRbtn.setText("Leave Per Department");
        specificDepartmentRbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specificDepartmentRbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        optionButtonsPanel.add(specificDepartmentRbtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        getContentPane().add(optionButtonsPanel, gridBagConstraints);

        datePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Leave Approval Date"));
        datePanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        datePanel.add(dateApprovedDatePicker, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(datePanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeDepartmentCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeDepartmentCmbxActionPerformed
        offset = 0;
        limit = 20;
        ClearTable.clearthisTable(leaveApplicantsListTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(true);
    }//GEN-LAST:event_employeeDepartmentCmbxActionPerformed

    private void naviEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naviEditBtnActionPerformed

        offset = 0;
        limit = 20;
        ClearTable.clearthisTable(leaveApplicantsListTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(true);
    }//GEN-LAST:event_naviEditBtnActionPerformed

    private void navRestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navRestBtnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        ClearTable.clearthisTable(leaveApplicantsListTbl);

        nextBtn.setEnabled(false);
        previousBtn.setEnabled(false);
    }//GEN-LAST:event_navRestBtnActionPerformed

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousBtnActionPerformed
        // TODO add your handling code here:
        if (offset > 0) {
            offset = offset - leaveApplicantsListTbl.getRowCount();

            ClearTable.clearthisTable(leaveApplicantsListTbl);
            ShowVisibleQueryItems(offset);
        } else {

            JOptionPane.showMessageDialog(rootPane, "The End of the File.", "End of Table List", JOptionPane.INFORMATION_MESSAGE);
            previousBtn.setEnabled(false);
        }
    }//GEN-LAST:event_previousBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        offset = offset + leaveApplicantsListTbl.getRowCount();

        ClearTable.clearthisTable(leaveApplicantsListTbl);
        ShowVisibleQueryItems(offset);

        previousBtn.setEnabled(true);
    }//GEN-LAST:event_nextBtnActionPerformed

    private void leaveApplicantsListTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveApplicantsListTblMouseClicked

        if (leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0) == null) {
            System.out.println("Empty Values.");
            JOptionPane.showMessageDialog(employeeLeaveListPanel, "The table has no values.", "Empty Values.", JOptionPane.ERROR_MESSAGE);
            leaveApplicantsListTbl.setValueAt(false, leaveApplicantsListTbl.getSelectedRow(), 8);

        } else {
            if (leaveApplicantsListTbl.getSelectedColumn() == 8) {
                if (leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 8) == Boolean.TRUE) {
                    System.out.println("Approve selected!");

                    String leave_days = null, emp_no = null;

                    try {
                        connectDB.setAutoCommit(false);

                        java.sql.Statement stm = connectDB.createStatement();
                        java.sql.ResultSet rse = stm.executeQuery("SELECT family_leave_days FROM hr.hr_job_family WHERE "
                                + "family_desc = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 2) + "'");

                        while (rse.next()) {
                            leave_days = rse.getObject(1).toString();
                        }

//                            connectDB.commit();
//                            connectDB.setAutoCommit(true);
//                    } catch (final Exception es) {
//                        System.out.println(es);
//
//                    }
                        String leave_end_date = null, login_name = null;

//                    try {
//                            connectDB.setAutoCommit(false);
                        java.sql.Statement stm1 = connectDB.createStatement();
                        java.sql.ResultSet rse1 = stm1.executeQuery("SELECT end_date, login_name FROM hr.hr_leave_application WHERE "
                                + "emp_no = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0) + "' "
                                + "AND start_date = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5) + "' "
                                + "AND end_date = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 7) + "' "
                                + "AND recommended = TRUE ");

                        while (rse1.next()) {
                            leave_end_date = rse1.getObject(1).toString();
                            login_name = rse1.getObject(2).toString();
                        }

//                            connectDB.commit();
//                            connectDB.setAutoCommit(true);
//                    } catch (final Exception es) {
//                        System.out.println(es);
//
//                    }
                        String leave_return_date = null;

//                    try {
//                            connectDB.setAutoCommit(false);
                        java.sql.Statement stm2 = connectDB.createStatement();
                        java.sql.ResultSet rse2 = stm2.executeQuery("SELECT return_date FROM hr.hr_leave_application WHERE "
                                + "emp_no = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0) + "' "
                                + "AND start_date = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5) + "' "
                                + "AND end_date = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 7) + "' "
                                + "AND recommended = TRUE ");

                        while (rse2.next()) {
                            leave_return_date = rse2.getObject(1).toString();
                        }

//                            connectDB.commit();
//                            connectDB.setAutoCommit(true);
//                    } catch (final Exception es) {
//                        System.out.println(es);
//
//                    }
                        String lv_rem_days = "0.00";

//                    try {
                        connectDB.setAutoCommit(false);

                        java.sql.Statement stmA = connectDB.createStatement();
                        java.sql.ResultSet rseA = stmA.executeQuery("SELECT leave_days FROM master_file "
                                + "WHERE employee_no = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0) + "'");

                        while (rseA.next()) {
                            lv_rem_days = dbObject.getDBObject(rseA.getObject(1), "01").toString();
                        }

//                        connectDB.commit();
//                        connectDB.setAutoCommit(true);
//                    } catch (final Exception es) {
//                        System.out.println(es);
//
//                    }
                        int a = JOptionPane.showConfirmDialog(employeeLeaveListPanel, "Do you want to Approve the Selected Leave?",
                                "Leave Approval.", JOptionPane.YES_NO_CANCEL_OPTION);

                        if (a == 0) {

//                                try {
//                                    connectDB.setAutoCommit(false);
                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO hr.hr_leave_approval "
                                    + "(emp_no, emp_name, job_group, type_of_leave, leave_days, applied_leave_days, "
                                    + "leave_start_date, leave_end_date, leave_return_date, leave_remaining_days, login_name, "
                                    + "date_approved) "
                                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                            pstmt.setString(1, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString().trim());
                            pstmt.setString(2, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 1).toString().trim());
                            pstmt.setString(3, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 2).toString().trim());
                            pstmt.setString(4, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 3).toString().trim());
                            pstmt.setInt(5, Integer.valueOf(leave_days));
                            pstmt.setInt(6, Integer.valueOf(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 6).toString().trim()));
                            pstmt.setString(7, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5).toString().trim());
                            pstmt.setString(8, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 7).toString().trim());
                            pstmt.setString(9, leave_return_date);
                            pstmt.setInt(10, Integer.valueOf(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 4).toString().trim()));
                            pstmt.setString(11, login_name);
                            pstmt.setDate(12, com.afrisoftech.lib.SQLDateFormat.getSQLDate(dateApprovedDatePicker.getDate()));

                            java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("UPDATE hr.hr_leave_approval"
                                    + " SET approved = 'TRUE'"
                                    + " WHERE emp_no = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString().trim() + "'"
                                    + " AND leave_start_date = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5).toString().trim() + "'"
                                    + " AND applied_leave_days = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 6).toString().trim() + "'"
                                    + " AND leave_return_date = '" + leave_return_date + "'");

                            java.sql.PreparedStatement pstmt5 = connectDB.prepareStatement("UPDATE hr.hr_leave_application"
                                    + " SET status = 'Approved'"
                                    + " WHERE emp_no = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString().trim() + "'"
                                    + " AND type_of_leave = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 3).toString().trim() + "'");

//                                        int d = Integer.valueOf(lv_rem_days)-Integer.valueOf(leave_days);
                            java.sql.PreparedStatement pstmt6 = connectDB.prepareStatement("UPDATE master_file"
                                    + " SET leave_days = '" + 0 + "'"
                                    + " WHERE employee_no = '" + leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString().trim() + "'");

                            System.out.println("--------------------------------------------------------------------------------------------------------------");
                            System.out.println("Remaining Balance - ");
                            System.out.println("--------------------------------------------------------------------------------------------------------------");

                            pstmt.executeUpdate();
//                                        connectDB.commit();
//                                        connectDB.setAutoCommit(true);

                            pstmt2.executeUpdate();
//                                        connectDB.commit();
//                                        connectDB.setAutoCommit(true);  

                            pstmt5.executeUpdate();
//                                        connectDB.commit();
//                                        connectDB.setAutoCommit(true); 

                            pstmt6.executeUpdate();
                            connectDB.commit();
                            connectDB.setAutoCommit(true);

                            System.out.print("\nSuccess!!! Data committed successfully.");
                            JOptionPane.showMessageDialog(employeeLeaveListPanel, "The Leave has been Approved successfully. ",
                                    "Leave Approval success.", JOptionPane.INFORMATION_MESSAGE);

                            PrintLeaveApprovalForm();

                            offset = 0;
                            limit = 20;
                            ClearTable.clearthisTable(leaveApplicantsListTbl);
                            ShowVisibleQueryItems(offset);

                        } else {
                            JOptionPane.showMessageDialog(employeeLeaveListPanel, "No Leave has been Approved.",
                                    "Nothing Approved.", JOptionPane.INFORMATION_MESSAGE);
                            leaveApplicantsListTbl.setValueAt(false, leaveApplicantsListTbl.getSelectedRow(), 8);
                        }
                    } catch (java.lang.Exception sq) {
                        sq.printStackTrace();
                        try {
                            connectDB.rollback();
                        } catch (java.sql.SQLException sql) {
                            javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println(sq.getMessage());
                        javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                        leaveApplicantsListTbl.setValueAt(false, leaveApplicantsListTbl.getSelectedRow(), 8);
                    }

                }
            } else {
                System.out.println("Approve not selected!");
                leaveApplicantsListTbl.setValueAt(false, leaveApplicantsListTbl.getSelectedRow(), 8);

            }

        }
    }//GEN-LAST:event_leaveApplicantsListTblMouseClicked

    private void specificDepartmentRbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specificDepartmentRbtnActionPerformed
        employeeDepartmentPanel.setVisible(true);
        ClearTable.clearthisTable(leaveApplicantsListTbl);
    }//GEN-LAST:event_specificDepartmentRbtnActionPerformed

    private void allDepartmentOpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allDepartmentOpBtnActionPerformed
        employeeDepartmentPanel.setVisible(false);
        offset = 0;
        limit = 20;
        ClearTable.clearthisTable(leaveApplicantsListTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(true);
    }//GEN-LAST:event_allDepartmentOpBtnActionPerformed

    private void PrintLeaveApprovalForm() {
        com.afrisoftech.reports.LeaveApplicationFormPdf policy = new com.afrisoftech.reports.LeaveApplicationFormPdf();

        policy.LeaveApplicationPdf(connectDB, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 3).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 1).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 6).toString());

        com.afrisoftech.reports.LeaveApprovalFormPdf policyOne = new com.afrisoftech.reports.LeaveApprovalFormPdf();

        policyOne.LeaveApprovalPdf(connectDB, leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 3).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 1).toString(),
                leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 6).toString());

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allDepartmentOpBtn;
    private javax.swing.JPanel btnNavigationPanel;
    private com.afrisoftech.lib.DatePicker dateApprovedDatePicker;
    private javax.swing.JPanel datePanel;
    private javax.swing.JComboBox employeeDepartmentCmbx;
    private javax.swing.JPanel employeeDepartmentPanel;
    private javax.swing.JPanel employeeLeaveListPanel;
    private javax.swing.JScrollPane leaveApplicantsListScrollPane;
    private javax.swing.JTable leaveApplicantsListTbl;
    private javax.swing.JButton navRestBtn;
    private javax.swing.JButton naviEditBtn;
    private javax.swing.JButton nextBtn;
    private javax.swing.JPanel optionButtonsPanel;
    private javax.swing.JButton previousBtn;
    private javax.swing.ButtonGroup radioGroupBtn;
    private javax.swing.JPanel recomBtnPanel;
    private javax.swing.JRadioButton specificDepartmentRbtn;
    // End of variables declaration//GEN-END:variables
}
