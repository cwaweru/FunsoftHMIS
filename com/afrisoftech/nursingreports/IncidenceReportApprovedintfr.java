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
public class IncidenceReportApprovedintfr extends javax.swing.JInternalFrame {

    /**
     * Creates new form IncidenceReportintfr
     */
    private static Connection connectDB;
    private String involves;
    private Object involvesWho;

    public IncidenceReportApprovedintfr(java.sql.Connection connDB) {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox();
        patientComboBox = new javax.swing.JComboBox();
        patCheckBox = new javax.swing.JCheckBox();
        staffCheckBox = new javax.swing.JCheckBox();
        staffComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        readTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportedTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Incidence Reporting");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("DEPARTMENT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        departmentComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "(select '0select-') union   (SELECT distinct Clinics FROM pb_clinics )union (select distinct ward_name  from hp_wards )  ORDER BY 1 asc"));
        departmentComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                departmentComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(departmentComboBox, gridBagConstraints);

        patientComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Patient", "Patient-Patient", "Patient-Staff", "Relative / Client", "Other" }));
        patientComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                patientComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(patientComboBox, gridBagConstraints);

        buttonGroup1.add(patCheckBox);
        patCheckBox.setText("Patient");
        patCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                patCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(patCheckBox, gridBagConstraints);

        buttonGroup1.add(staffCheckBox);
        staffCheckBox.setText("Staff");
        staffCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                staffCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(staffCheckBox, gridBagConstraints);

        staffComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Staff", "staff-Staff", "Relative / Client", "Other" }));
        staffComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                staffComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(staffComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("              ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        reportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT oid as ReportNo,date, invoves, invoveswho,reportedby incidence_detailactiontaken, reportedto,reportinguser,  approved, approved_on, approved_by  FROM incidence where reportedto=current_user order by date desc"));
        reportTable.setRowHeight(23);
        reportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(reportTable);

        jTabbedPane1.addTab("Reports ", jScrollPane5);

        readTextArea.setEditable(false);
        readTextArea.setColumns(20);
        readTextArea.setRows(5);
        jScrollPane3.setViewportView(readTextArea);

        jTabbedPane1.addTab("Read Report", jScrollPane3);

        reportedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(reportedTable);

        jTabbedPane1.addTab("Approval Details", jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void patientComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_patientComboBoxItemStateChanged

        involvesWho = patientComboBox.getSelectedItem();
        reportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT oid as ReportNo,date, invoves, invoveswho,reportedby incidence_detailactiontaken, reportedto,reportinguser,  approved, approved_on, approved_by  "
                + "FROM incidence where reportedto=current_user and invoveswho='" + involvesWho + "' order by date desc"));

    }//GEN-LAST:event_patientComboBoxItemStateChanged

    private void staffComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_staffComboBoxItemStateChanged

        involvesWho = staffComboBox.getSelectedItem();
        reportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT oid as ReportNo,date, invoves, invoveswho,reportedby incidence_detailactiontaken, reportedto,reportinguser,  approved, approved_on, approved_by  "
                + "FROM incidence where reportedto=current_user and invoveswho='" + involvesWho + "' order by date desc"));

    }//GEN-LAST:event_staffComboBoxItemStateChanged

    private void staffCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_staffCheckBoxItemStateChanged
        if (staffCheckBox.isSelected() == Boolean.TRUE) {

            involves = staffCheckBox.getActionCommand();
            staffComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, ""
                    + "SELECT distinct invoveswho  FROM incidence where invoves='" + involves + "' order by 1 asc"));
            reportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT oid as ReportNo,date, invoves, invoveswho,reportedby incidence_detailactiontaken, reportedto,reportinguser,  approved, approved_on, approved_by  "
                    + "FROM incidence where reportedto=current_user and invoves='" + involves + "' order by date desc"));

        }
    }//GEN-LAST:event_staffCheckBoxItemStateChanged

    private void patCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_patCheckBoxItemStateChanged
        if (patCheckBox.isSelected() == Boolean.TRUE) {

            involves = patCheckBox.getActionCommand();
            patientComboBox.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, ""
                    + "SELECT distinct invoveswho  FROM incidence where invoves='" + involves + "' order by 1 asc"));
            reportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT oid as ReportNo,date, invoves, invoveswho,reportedby incidence_detailactiontaken, reportedto,reportinguser,  approved, approved_on, approved_by  "
                    + "FROM incidence where reportedto=current_user and invoves='" + involves + "' order by date desc"));

        }
    }//GEN-LAST:event_patCheckBoxItemStateChanged

    private void departmentComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_departmentComboBoxItemStateChanged
        reportTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT oid as ReportNo,date, invoves, invoveswho,reportedby incidence_detailactiontaken, reportedto,reportinguser,  approved, approved_on, approved_by  "
                + "FROM incidence where reportedto=current_user and department='" + departmentComboBox.getSelectedItem() + "' order by date desc"));

    }//GEN-LAST:event_departmentComboBoxItemStateChanged

    private String SetTextAreaText() {
        String textAreaData = null;
        try {

            java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement("SELECT  invoveswho, \n"
                    + "concat(victims,'  \n', vicperonalno,'  \n', vicunit,'  \n', victel,'  \n',  vicdate_time) asVictims, \n"
                    + "concat(reportedby, '  ',peronalno,'  \n', unit,'  ', tel,'  ', date_time)as Reported_By, \n"
                    + "incidence_detail, \n"
                    + "concat(witnessedby, '  ',wit_relationship,'  \n', wit_peronalno,'  ', wit_unit, '  \n',wit_tel, '  ', wit_date_time)Witnessed_by, \n"
                    + "actiontaken, \n"
                    + "concat(reportedto,'  ', to_designation, '  \n',to_peronalno,'  ',  to_unit, '  \n',to_tel,'  ', to_date_time) as Reported_to, \n"
                    + "concat(to_actiontaken, '  \n',comment,'  ', department) as Action_Taken, \n"
                    + "concat(approved, '  ',approved_on, '  ',approved_by)as Approval\n"
                    + "  FROM incidence WHERE oid='" + reportTable.getValueAt(reportTable.getSelectedRow(), 0) + "'");

            java.sql.ResultSet rsetVector = pstmtVector.executeQuery();
            while (rsetVector.next()) {
                textAreaData = textAreaData + ""
                        + "" + rsetVector.getString(1) + " " + "" + rsetVector.getString(2) + " "
                        + rsetVector.getString(3) + " " + rsetVector.getString(4) + " "
                        + "" + rsetVector.getString(5) + " " + "" + rsetVector.getString(6) + " "
                        + rsetVector.getString(7) + " " + rsetVector.getString(8) + " ";

            }

        } catch (Exception es) {
            es.printStackTrace();
        }
        return textAreaData;
    }
    private void reportTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTableMouseClicked
        jTabbedPane1.setSelectedIndex(1);
        reportedTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, ""
                + "select\n"
                + "reportedto, to_designation, to_peronalno, to_unit, to_tel, \n"
                + "to_date_time, to_actiontaken, comment,approved, approved_on, approved_by\n"
                + "WHERE oid='" + reportTable.getValueAt(reportTable.getSelectedRow(), 0) + "'"));
        readTextArea.setText(SetTextAreaText());//);
    }//GEN-LAST:event_reportTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox departmentComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox patCheckBox;
    private javax.swing.JComboBox patientComboBox;
    private javax.swing.JTextArea readTextArea;
    private javax.swing.JTable reportTable;
    private javax.swing.JTable reportedTable;
    private javax.swing.JCheckBox staffCheckBox;
    private javax.swing.JComboBox staffComboBox;
    // End of variables declaration//GEN-END:variables
}
