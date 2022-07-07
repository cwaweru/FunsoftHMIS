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
public class LeaveRecommendationFormIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    private int offset;
    private int limit;

    /**
     * Creates new form LeaveRecommendationForm
     *
     * @param connDb
     * @param pconnDB
     */
    public LeaveRecommendationFormIntfr(java.sql.Connection connDb, org.netbeans.lib.sql.pool.PooledConnectionSource pconnDB) {
        connectDB = connDb;
        pConnDB = pconnDB;

        initComponents();
        // populateCmbox();
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
            System.out.println("SELECT emp_no, name_of_employee, job_group, department, type_of_leave, start_date, duration, return_date, reason "
                    + "FROM hr.hr_leave_application WHERE department = '" + employeeDepartmentCmbx.getSelectedItem().toString().trim() + "' "
                    + "ORDER BY timestamp LIMIT " + leaveApplicantsListTbl.getRowCount() + " OFFSET - " + offset + "  ");

            PreparedStatement pst = connectDB.prepareStatement("SELECT emp_no, name_of_employee, job_group, department, type_of_leave, "
                    + "start_date, duration, return_date, reason FROM hr.hr_leave_application "
                    + "WHERE department = '" + employeeDepartmentCmbx.getSelectedItem().toString().trim() + "' "
                    + "AND recommended = FALSE "
                    + "ORDER BY timestamp "
                    + "LIMIT " + leaveApplicantsListTbl.getRowCount() + " OFFSET " + offset + " ");

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
                leaveApplicantsListTbl.setValueAt(rset.getObject(9), i, 8);

                for (int a = 1; a == 9; a++) {
                    System.out.println("Values - " + rset.getObject(a));
                }

                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRecommendationFormIntfr.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        employeeDetailsPanel = new javax.swing.JPanel();
        employeeJobGroupTxt = new javax.swing.JTextField();
        employeeDepartmentTxt = new javax.swing.JTextField();
        employeeNumberTxt = new javax.swing.JTextField();
        employeeNameTxt = new javax.swing.JTextField();
        employeeDepartmentPanel = new javax.swing.JPanel();
        employeeDepartmentCmbx = new javax.swing.JComboBox();
        leaveDetailsPanel = new javax.swing.JPanel();
        employeeTOLTxt = new javax.swing.JTextField();
        leaveStartDateLbl = new javax.swing.JLabel();
        leaveDaysLbl = new javax.swing.JLabel();
        leaveReturnDateLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveReasonTxa = new javax.swing.JTextArea();
        leaveApprovalPanel = new javax.swing.JPanel();
        leaveRecomQLbl = new javax.swing.JLabel();
        leaveRecomYNCmbx = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        leaveReasonRecomTxa = new javax.swing.JTextArea();
        employeeLeaveListPanel = new javax.swing.JPanel();
        leaveApplicantsListScrollPane = new javax.swing.JScrollPane();
        leaveApplicantsListTbl = new com.afrisoftech.dbadmin.JTable();
        btnNavigationPanel = new javax.swing.JPanel();
        naviEditBtn = new javax.swing.JButton();
        navRestBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        recomBtnPanel = new javax.swing.JPanel();
        leaveRecomOKBtn = new javax.swing.JButton();
        leaveRecomClearBtn = new javax.swing.JButton();
        leaveRecomRemoveBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Leave Recommendation Form");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        employeeDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "The Employee's Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(13, 32, 228)));
        employeeDetailsPanel.setLayout(new java.awt.GridBagLayout());

        employeeJobGroupTxt.setEditable(false);
        employeeJobGroupTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Job Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(4, 3, 10)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        employeeDetailsPanel.add(employeeJobGroupTxt, gridBagConstraints);

        employeeDepartmentTxt.setEditable(false);
        employeeDepartmentTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(3, 3, 9)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        employeeDetailsPanel.add(employeeDepartmentTxt, gridBagConstraints);

        employeeNumberTxt.setEditable(false);
        employeeNumberTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(3, 2, 9)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        employeeDetailsPanel.add(employeeNumberTxt, gridBagConstraints);

        employeeNameTxt.setEditable(false);
        employeeNameTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(7, 7, 14)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        employeeDetailsPanel.add(employeeNameTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 2.5;
        getContentPane().add(employeeDetailsPanel, gridBagConstraints);

        employeeDepartmentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select the Department", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(246, 34, 14)));
        employeeDepartmentPanel.setLayout(new java.awt.GridBagLayout());

        employeeDepartmentCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT distinct initcap(depart_name) as name from pb_main_department order by name")
        );
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        getContentPane().add(employeeDepartmentPanel, gridBagConstraints);

        leaveDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Leave Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(28, 36, 227)));
        leaveDetailsPanel.setLayout(new java.awt.GridBagLayout());

        employeeTOLTxt.setEditable(false);
        employeeTOLTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Type of Leave", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(5, 6, 11)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveDetailsPanel.add(employeeTOLTxt, gridBagConstraints);

        leaveStartDateLbl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leave Start Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(3, 2, 12)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveDetailsPanel.add(leaveStartDateLbl, gridBagConstraints);

        leaveDaysLbl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leave Days", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(3, 3, 7)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveDetailsPanel.add(leaveDaysLbl, gridBagConstraints);

        leaveReturnDateLbl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Return Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(8, 9, 15)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveDetailsPanel.add(leaveReturnDateLbl, gridBagConstraints);

        leaveReasonTxa.setEditable(false);
        leaveReasonTxa.setColumns(20);
        leaveReasonTxa.setLineWrap(true);
        leaveReasonTxa.setRows(5);
        leaveReasonTxa.setWrapStyleWord(true);
        leaveReasonTxa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Reason for Leave", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(4, 3, 12)));
        jScrollPane1.setViewportView(leaveReasonTxa);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        leaveDetailsPanel.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 2.5;
        getContentPane().add(leaveDetailsPanel, gridBagConstraints);

        leaveApprovalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leave Recommendation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(25, 39, 238)));
        leaveApprovalPanel.setLayout(new java.awt.GridBagLayout());

        leaveRecomQLbl.setText("Recommended?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveApprovalPanel.add(leaveRecomQLbl, gridBagConstraints);

        leaveRecomYNCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveApprovalPanel.add(leaveRecomYNCmbx, gridBagConstraints);

        leaveReasonRecomTxa.setColumns(20);
        leaveReasonRecomTxa.setLineWrap(true);
        leaveReasonRecomTxa.setRows(5);
        leaveReasonRecomTxa.setWrapStyleWord(true);
        leaveReasonRecomTxa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reason for (YES/NO) Recommendation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(31, 23, 243)));
        jScrollPane2.setViewportView(leaveReasonRecomTxa);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        leaveApprovalPanel.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 2.5;
        getContentPane().add(leaveApprovalPanel, gridBagConstraints);

        employeeLeaveListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List of Employees Who have applied for Leave", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(23, 19, 236)));
        employeeLeaveListPanel.setLayout(new java.awt.GridBagLayout());

        leaveApplicantsListScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Click on the first column to select item to work on", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));

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
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee Number", "Employee Name", "Job Group", "Department", "Type Of Leave", "Leave Start Date", "Leave Days", "Return Date", "Reason for Leave"
            }
        ));
        leaveApplicantsListTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaveApplicantsListTblMouseClicked(evt);
            }
        });
        leaveApplicantsListScrollPane.setViewportView(leaveApplicantsListTbl);
        if (leaveApplicantsListTbl.getColumnModel().getColumnCount() > 0) {
            leaveApplicantsListTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
            leaveApplicantsListTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
            leaveApplicantsListTbl.getColumnModel().getColumn(2).setPreferredWidth(30);
            leaveApplicantsListTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
            leaveApplicantsListTbl.getColumnModel().getColumn(7).setPreferredWidth(50);
            leaveApplicantsListTbl.getColumnModel().getColumn(8).setPreferredWidth(150);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 200.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        employeeLeaveListPanel.add(leaveApplicantsListScrollPane, gridBagConstraints);

        btnNavigationPanel.setBackground(new java.awt.Color(204, 204, 255));
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.weighty = 500.0;
        getContentPane().add(employeeLeaveListPanel, gridBagConstraints);

        recomBtnPanel.setLayout(new java.awt.GridBagLayout());

        leaveRecomOKBtn.setText("Approve");
        leaveRecomOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRecomOKBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        recomBtnPanel.add(leaveRecomOKBtn, gridBagConstraints);

        leaveRecomClearBtn.setText("Clear");
        leaveRecomClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRecomClearBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        recomBtnPanel.add(leaveRecomClearBtn, gridBagConstraints);

        leaveRecomRemoveBtn.setText("Remove");
        leaveRecomRemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRecomRemoveBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        recomBtnPanel.add(leaveRecomRemoveBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 10.0;
        getContentPane().add(recomBtnPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeDepartmentCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeDepartmentCmbxActionPerformed
        offset = 0;
        limit = 10;
        ClearTable.clearthisTable(leaveApplicantsListTbl);
        ShowVisibleQueryItems(offset);

        nextBtn.setEnabled(true);
    }//GEN-LAST:event_employeeDepartmentCmbxActionPerformed

    private void naviEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naviEditBtnActionPerformed

        offset = 0;
        limit = 10;
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
        if (leaveApplicantsListTbl.getSelectedColumn() == 0) {
            if (leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), leaveApplicantsListTbl.getSelectedColumn()) == null) {
                JOptionPane.showMessageDialog(previousBtn, "The Selected Row has Empty Values.", "Empty Values.", JOptionPane.ERROR_MESSAGE);

            } else {
                employeeNumberTxt.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 0).toString());
                employeeNameTxt.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 1).toString());
                employeeJobGroupTxt.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 2).toString());
                employeeDepartmentTxt.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 3).toString());
                employeeTOLTxt.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 4).toString());
                leaveStartDateLbl.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 5).toString());
                leaveDaysLbl.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 6).toString());
                leaveReturnDateLbl.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 7).toString());
                leaveReasonTxa.setText(leaveApplicantsListTbl.getValueAt(leaveApplicantsListTbl.getSelectedRow(), 8).toString());
            }
        }
    }//GEN-LAST:event_leaveApplicantsListTblMouseClicked

    private void leaveRecomRemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRecomRemoveBtnActionPerformed

    }//GEN-LAST:event_leaveRecomRemoveBtnActionPerformed

    private void leaveRecomClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRecomClearBtnActionPerformed
        ClearTable.clearthisTable(leaveApplicantsListTbl);
        clear();

    }//GEN-LAST:event_leaveRecomClearBtnActionPerformed

    private void leaveRecomOKBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRecomOKBtnActionPerformed
        if (employeeNumberTxt.getText().equals("") || leaveReasonRecomTxa.getText().equals("")) {
            if (employeeNumberTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(employeeLeaveListPanel, "Ensure that the Table has some values and then click on the first column (Employee No.)",
                        "Empty Value Noted", JOptionPane.INFORMATION_MESSAGE);
            } else if (leaveReasonRecomTxa.getText().equals("")) {
                JOptionPane.showMessageDialog(employeeLeaveListPanel, "Fill the the reasons for recommendation or no recommendation.",
                        "Empty Value Noted", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(employeeLeaveListPanel, "Empty Value Noted.",
                        "Empty Value Noted", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            String recom;
            if (leaveRecomYNCmbx.getSelectedItem().equals("YES")) {
                recom = "TRUE";
            } else {
                recom = "FALSE";
            }

            String user = "postgres", date = "unknown";

            try {
                java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access WHERE login_name = current_user ORDER BY 1 LIMIT 1), date_part('year', now()::date)||'-'||date_part('month', now()::date)||'-'||date_part('day', now()::date)");
                java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                while (rsetUser.next()) {

                    user = rsetUser.getString(1).toUpperCase();
                    date = rsetUser.getString(2).toUpperCase();

                }

            } catch (java.sql.SQLException SqlExec) {

                SqlExec.printStackTrace();

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

            }

            try {
                connectDB.setAutoCommit(false);

                java.sql.PreparedStatement pstmtOne = connectDB.prepareStatement("UPDATE hr.hr_leave_application"
                        + " SET recom_reason = ?, hod_recomm = ?, date = now()::date, recommended = ? "
                        + " WHERE emp_no = ?"
                        + " AND start_date = ?"
                        + " AND duration = ?"
                        + " AND return_date = ?"
                        + " ");
                pstmtOne.setObject(1, leaveReasonRecomTxa.getText().toString().trim());
                pstmtOne.setObject(2, user);
                pstmtOne.setObject(3, recom);
                pstmtOne.setObject(4, employeeNumberTxt.getText().toString().trim() );
                pstmtOne.setObject(5, leaveStartDateLbl.getText().toString().trim() );
                pstmtOne.setInt(6, Integer.parseInt(leaveDaysLbl.getText().toString().trim()));
                pstmtOne.setObject(7,  leaveReturnDateLbl.getText().toString().trim());

                pstmtOne.executeUpdate();

                connectDB.commit();
                connectDB.setAutoCommit(true);

                System.out.print("\nRecom_reason Update success.");

                JOptionPane.showMessageDialog(employeeLeaveListPanel, "Employee has been recommended successfully.",
                        "Recommendation Success.", JOptionPane.INFORMATION_MESSAGE);

                offset = 0;
                limit = 10;
                ClearTable.clearthisTable(leaveApplicantsListTbl);
                ShowVisibleQueryItems(offset);

                clear();

                nextBtn.setEnabled(true);

            } catch (java.lang.Exception sq) {
                sq.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Ati What? ---> " + sq.getMessage());

            }
        }
    }//GEN-LAST:event_leaveRecomOKBtnActionPerformed

    public void clear() {
        employeeNumberTxt.setText("");
        employeeNameTxt.setText("");
        employeeJobGroupTxt.setText("");
        employeeDepartmentTxt.setText("");
        employeeTOLTxt.setText("");
        leaveStartDateLbl.setText("");
        leaveDaysLbl.setText("");
        leaveReturnDateLbl.setText("");
        leaveReasonTxa.setText("");
        leaveReasonRecomTxa.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnNavigationPanel;
    private javax.swing.JComboBox employeeDepartmentCmbx;
    private javax.swing.JPanel employeeDepartmentPanel;
    private javax.swing.JTextField employeeDepartmentTxt;
    private javax.swing.JPanel employeeDetailsPanel;
    private javax.swing.JTextField employeeJobGroupTxt;
    private javax.swing.JPanel employeeLeaveListPanel;
    private javax.swing.JTextField employeeNameTxt;
    private javax.swing.JTextField employeeNumberTxt;
    private javax.swing.JTextField employeeTOLTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane leaveApplicantsListScrollPane;
    private javax.swing.JTable leaveApplicantsListTbl;
    private javax.swing.JPanel leaveApprovalPanel;
    private javax.swing.JLabel leaveDaysLbl;
    private javax.swing.JPanel leaveDetailsPanel;
    private javax.swing.JTextArea leaveReasonRecomTxa;
    private javax.swing.JTextArea leaveReasonTxa;
    private javax.swing.JButton leaveRecomClearBtn;
    private javax.swing.JButton leaveRecomOKBtn;
    private javax.swing.JLabel leaveRecomQLbl;
    private javax.swing.JButton leaveRecomRemoveBtn;
    private javax.swing.JComboBox leaveRecomYNCmbx;
    private javax.swing.JLabel leaveReturnDateLbl;
    private javax.swing.JLabel leaveStartDateLbl;
    private javax.swing.JButton navRestBtn;
    private javax.swing.JButton naviEditBtn;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton previousBtn;
    private javax.swing.JPanel recomBtnPanel;
    // End of variables declaration//GEN-END:variables
}