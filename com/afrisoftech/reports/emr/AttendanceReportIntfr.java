/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.reports.emr;

import java.sql.SQLException;
import java.text.ParseException;


/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 */
public class AttendanceReportIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    java.util.Date endDate = null;
    java.util.Date beginDate = null;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    com.afrisoftech.timeseries.DailyAgeing dailySeries = null;
    java.util.Date ageingDates[][] = null;
    int iterations = 0;

    /**
     * Creates new form AttendanceReportIntfr
     */
    public AttendanceReportIntfr(java.sql.Connection connDB) {

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

        analyticsTabbedPane = new javax.swing.JTabbedPane();
        monthlyAnalyticsPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        clinicsCmbx = new javax.swing.JComboBox<>();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        schemeCmbx = new javax.swing.JComboBox<>();
        reportBodyPanel = new javax.swing.JPanel();
        reportScrollPane = new javax.swing.JScrollPane();
        reportTable = new com.afrisoftech.dbadmin.JXTable();
        actionPanel = new javax.swing.JPanel();
        spacerLbl = new javax.swing.JLabel();
        refreshBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        yearlyAnalyticsPanel = new javax.swing.JPanel();
        headerPanel1 = new javax.swing.JPanel();
        clinicsCmbx1 = new javax.swing.JComboBox<>();
        beginDatePicker1 = new com.afrisoftech.lib.DatePicker();
        endDatePicker1 = new com.afrisoftech.lib.DatePicker();
        schemeCmbx1 = new javax.swing.JComboBox<>();
        reportBodyPanel1 = new javax.swing.JPanel();
        reportScrollPane1 = new javax.swing.JScrollPane();
        reportTable1 = new com.afrisoftech.dbadmin.JXTable();
        actionPanel1 = new javax.swing.JPanel();
        spacerLbl1 = new javax.swing.JLabel();
        refreshBtn1 = new javax.swing.JButton();
        closeBtn1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("OUT-Patient Attendance Analysis Report");
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        monthlyAnalyticsPanel.setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());

        clinicsCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' as clinics UNION SELECT DISTINCT initcap(clinics) FROM pb_clinics ORDER BY 1")
        );
        clinicsCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("Clinics"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(clinicsCmbx, gridBagConstraints);

        beginDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Begin Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(beginDatePicker, gridBagConstraints);

        endDatePicker.setBorder(javax.swing.BorderFactory.createTitledBorder("End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(endDatePicker, gridBagConstraints);

        schemeCmbx.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' as scheme_name UNION SELECT DISTINCT scheme_name FROM ac_schemes ORDER BY 1")
        );
        schemeCmbx.setBorder(javax.swing.BorderFactory.createTitledBorder("Scheme Name"));
        schemeCmbx.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel.add(schemeCmbx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        monthlyAnalyticsPanel.add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attendance report"));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        reportScrollPane.setViewportView(reportTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(reportScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        monthlyAnalyticsPanel.add(reportBodyPanel, gridBagConstraints);

        actionPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(spacerLbl, gridBagConstraints);

        refreshBtn.setText("Generate monthly report");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(refreshBtn, gridBagConstraints);

        closeBtn.setText("Close form");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel.add(closeBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        monthlyAnalyticsPanel.add(actionPanel, gridBagConstraints);

        analyticsTabbedPane.addTab("Monthly Analytics", monthlyAnalyticsPanel);

        yearlyAnalyticsPanel.setLayout(new java.awt.GridBagLayout());

        headerPanel1.setLayout(new java.awt.GridBagLayout());

        clinicsCmbx1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' as clinics UNION SELECT DISTINCT initcap(clinics) FROM pb_clinics ORDER BY 1")
        );
        clinicsCmbx1.setBorder(javax.swing.BorderFactory.createTitledBorder("Clinics"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel1.add(clinicsCmbx1, gridBagConstraints);

        beginDatePicker1.setBorder(javax.swing.BorderFactory.createTitledBorder("Begin Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel1.add(beginDatePicker1, gridBagConstraints);

        endDatePicker1.setBorder(javax.swing.BorderFactory.createTitledBorder("End Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel1.add(endDatePicker1, gridBagConstraints);

        schemeCmbx1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "SELECT '--ALL--' as scheme_name UNION SELECT DISTINCT scheme_name FROM ac_schemes ORDER BY 1")
        );
        schemeCmbx1.setBorder(javax.swing.BorderFactory.createTitledBorder("Scheme Name"));
        schemeCmbx1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerPanel1.add(schemeCmbx1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 20.0;
        yearlyAnalyticsPanel.add(headerPanel1, gridBagConstraints);

        reportBodyPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Attendance report"));
        reportBodyPanel1.setLayout(new java.awt.GridBagLayout());

        reportTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        reportScrollPane1.setViewportView(reportTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel1.add(reportScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        yearlyAnalyticsPanel.add(reportBodyPanel1, gridBagConstraints);

        actionPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel1.add(spacerLbl1, gridBagConstraints);

        refreshBtn1.setText("Generate report");
        refreshBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel1.add(refreshBtn1, gridBagConstraints);

        closeBtn1.setText("Close form");
        closeBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        actionPanel1.add(closeBtn1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        yearlyAnalyticsPanel.add(actionPanel1, gridBagConstraints);

        analyticsTabbedPane.addTab("Yearly Analytics", yearlyAnalyticsPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(analyticsTabbedPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_closeBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed

        AttendanceThread attThread = new AttendanceThread();

        attThread.start();

        // TODO add your handling code here:
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void refreshBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtn1ActionPerformed
        AttendanceYearlyThread attThread = new AttendanceYearlyThread();

        attThread.start();        // TODO add your handling code here:
    }//GEN-LAST:event_refreshBtn1ActionPerformed

    private void closeBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeBtn1ActionPerformed

    class AttendanceThread extends Thread {

        public void run() {

            generateReport(beginDatePicker.getDate(), endDatePicker.getDate());

        }

    }
    class AttendanceYearlyThread extends Thread {

        public void run() {

            generateYearlyReport(beginDatePicker1.getDate(), endDatePicker1.getDate());

        }

    }
    private void generateReport(java.util.Date begindate, java.util.Date enddate) {
        beginDate = begindate;
        endDate = enddate;
        iterations = endDate.getDate() - beginDate.getDate();
//        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date" + endDate);
        // periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(1, endDate);
        dailySeries = new com.afrisoftech.timeseries.DailyAgeing(iterations + 1, endDate);

        java.lang.Object[] listofAct = this.getListofActivities();

        java.util.Vector dataVector = new java.util.Vector(1);

        java.util.Vector columnVector = new java.util.Vector(1);

        columnVector.addElement("DATE");

        columnVector.addElement("NEW");

        columnVector.addElement("REATT");

        columnVector.addElement("TOTAL");

        columnVector.addElement("CASH");

        columnVector.addElement("SCHEME");

        java.util.Vector rowVectors = new java.util.Vector();
        rowVectors.addElement("");
        rowVectors.addElement("Attendance Report");
        rowVectors.addElement("for period : ");
        rowVectors.addElement(begindate.toString() + " to ");
        rowVectors.addElement(endDate.toString());
        dataVector.addElement(rowVectors);

        int totalNew = 0;
        int totalReatt = 0;
        int totalAll = 0;
        int totalCash = 0;
        int totalScheme = 0;

        for (int i = 0; i < listofAct.length; i++) {

            java.util.Vector rowVector = new java.util.Vector();

            rowVector.addElement(i + 1);

            try {
                if (clinicsCmbx.getSelectedItem().toString().contains("--ALL--")) {
                    java.sql.PreparedStatement pstmtNew = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date = '" + listofAct[i] + "' AND UPPER(comments) = UPPER(?)");
                    //  pstmtNew.setObject(1, );
                    pstmtNew.setString(1, "New");
                    int countNew = 0;
                    java.sql.ResultSet rsetNew = pstmtNew.executeQuery();
                    while (rsetNew.next()) {
                        countNew = rsetNew.getInt(1);
                    }
                    rowVector.addElement(countNew);
                    totalNew = totalNew + countNew;
                    pstmtNew.close();
                    rsetNew.close();

                    java.sql.PreparedStatement pstmtOld = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date = '" + listofAct[i] + "' AND UPPER(comments) = UPPER(?)");
                    //pstmtOld.setObject(1, listofAct[i]);
                    pstmtOld.setObject(1, "Old");
                    int countOld = 0;
                    java.sql.ResultSet rsetOld = pstmtOld.executeQuery();
                    while (rsetOld.next()) {
                        countOld = rsetOld.getInt(1);
                    }
                    rowVector.addElement(countOld);
                    totalReatt = totalReatt + countOld;
                    pstmtOld.close();
                    rsetOld.close();

                    java.sql.PreparedStatement pstmtTotal = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date =  '" + listofAct[i] + "'");
                    //pstmtTotal.setObject(1, listofAct[i]);
                    int countTotal = 0;
                    java.sql.ResultSet rsetTotal = pstmtTotal.executeQuery();
                    while (rsetTotal.next()) {
                        countTotal = rsetTotal.getInt(1);
                    }
                    rowVector.addElement(countTotal);
                    totalAll = totalAll + countTotal;
                    pstmtTotal.close();
                    rsetTotal.close();

                    java.sql.PreparedStatement pstmtCash = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date =  '" + listofAct[i] + "' AND UPPER(payment) NOT LIKE UPPER(?)");
                    //pstmtCash.setObject(1, listofAct[i]);
                    pstmtCash.setObject(1, "Scheme");
                    int countCash = 0;
                    java.sql.ResultSet rsetCash = pstmtCash.executeQuery();
                    while (rsetCash.next()) {
                        countCash = rsetCash.getInt(1);
                    }
                    rowVector.addElement(countCash);
                    totalCash = totalCash + countCash;
                    pstmtCash.close();
                    rsetCash.close();

                    java.sql.PreparedStatement pstmtScheme = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date =  '" + listofAct[i] + "' AND UPPER(payment) = UPPER(?)");
                    // pstmtScheme.setObject(1, listofAct[i]);
                    pstmtScheme.setObject(1, "Scheme");
                    int countScheme = 0;
                    java.sql.ResultSet rsetScheme = pstmtScheme.executeQuery();
                    while (rsetScheme.next()) {
                        countScheme = rsetScheme.getInt(1);
                    }
                    rowVector.addElement(countScheme);
                    totalScheme = totalScheme + countScheme;
                    pstmtScheme.close();
                    rsetScheme.close();

                    dataVector.addElement(rowVector);
                } else {
                    java.sql.PreparedStatement pstmtNew = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date = '" + listofAct[i] + "' AND UPPER(comments) = UPPER(?) AND UPPER(clinic) = ?");
                    //  pstmtNew.setObject(1, );
                    pstmtNew.setString(1, "New");
                    pstmtNew.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countNew = 0;
                    java.sql.ResultSet rsetNew = pstmtNew.executeQuery();
                    while (rsetNew.next()) {
                        countNew = rsetNew.getInt(1);
                    }
                    rowVector.addElement(countNew);
                    totalNew = totalNew + countNew;
                    pstmtNew.close();
                    rsetNew.close();

                    java.sql.PreparedStatement pstmtOld = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date = '" + listofAct[i] + "' AND UPPER(comments) = UPPER(?) AND UPPER(clinic) = ?");
                    //pstmtOld.setObject(1, listofAct[i]);
                    pstmtOld.setObject(1, "Old");
                    pstmtOld.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countOld = 0;
                    java.sql.ResultSet rsetOld = pstmtOld.executeQuery();
                    while (rsetOld.next()) {
                        countOld = rsetOld.getInt(1);
                    }
                    rowVector.addElement(countOld);
                    totalReatt = totalReatt + countOld;
                    pstmtOld.close();
                    rsetOld.close();

                    java.sql.PreparedStatement pstmtTotal = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date =  '" + listofAct[i] + "' AND UPPER(clinic) = ?");
                    //pstmtTotal.setObject(1, listofAct[i]);
                    pstmtTotal.setString(1, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countTotal = 0;
                    java.sql.ResultSet rsetTotal = pstmtTotal.executeQuery();
                    while (rsetTotal.next()) {
                        countTotal = rsetTotal.getInt(1);
                    }
                    rowVector.addElement(countTotal);
                    totalAll = totalAll + countTotal;
                    pstmtTotal.close();
                    rsetTotal.close();

                    java.sql.PreparedStatement pstmtCash = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date =  '" + listofAct[i] + "' AND UPPER(payment) NOT LIKE UPPER(?) AND UPPER(clinic) = ?");
                    //pstmtCash.setObject(1, listofAct[i]);
                    pstmtCash.setObject(1, "Scheme");
                    pstmtCash.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countCash = 0;
                    java.sql.ResultSet rsetCash = pstmtCash.executeQuery();
                    while (rsetCash.next()) {
                        countCash = rsetCash.getInt(1);
                    }
                    rowVector.addElement(countCash);
                    totalCash = totalCash + countCash;
                    pstmtCash.close();
                    rsetCash.close();

                    java.sql.PreparedStatement pstmtScheme = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date =  '" + listofAct[i] + "' AND UPPER(payment) = UPPER(?) AND UPPER(clinic) = ?");
                    // pstmtScheme.setObject(1, listofAct[i]);
                    pstmtScheme.setObject(1, "Scheme");
                    pstmtScheme.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countScheme = 0;
                    java.sql.ResultSet rsetScheme = pstmtScheme.executeQuery();
                    while (rsetScheme.next()) {
                        countScheme = rsetScheme.getInt(1);
                    }
                    rowVector.addElement(countScheme);
                    totalScheme = totalScheme + countScheme;
                    pstmtScheme.close();
                    rsetScheme.close();

                    dataVector.addElement(rowVector);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }

        java.util.Vector rowVector = new java.util.Vector();
        rowVector.addElement("Total");
        rowVector.addElement(totalNew);
        rowVector.addElement(totalReatt);
        rowVector.addElement(totalAll);
        rowVector.addElement(totalCash);
        rowVector.addElement(totalScheme);
        dataVector.addElement(rowVector);

        reportTable.setModel(new javax.swing.table.DefaultTableModel(dataVector, columnVector));

    }

    private void generateYearlyReport(java.util.Date begindate, java.util.Date enddate) {
        beginDate = begindate;
        endDate = enddate;
        iterations = endDate.getDate() - beginDate.getDate();
//        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date" + endDate);
        com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
        // periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(12, endDate);
        java.lang.Object[][] rangeDates = ageingSeries.getAgeingDateSeries();
//        dailySeries = new com.afrisoftech.timeseries.DailyAgeing(iterations + 1, endDate);

//        java.lang.Object[] listofAct = this.getListofActivities();

        java.util.Vector dataVector = new java.util.Vector(1);

        java.util.Vector columnVector = new java.util.Vector(1);

        columnVector.addElement("MONTH");

        columnVector.addElement("NEW");

        columnVector.addElement("REATT");

        columnVector.addElement("TOTAL");

        columnVector.addElement("CASH");

        columnVector.addElement("SCHEME");

        java.util.Vector rowVectors = new java.util.Vector();
        rowVectors.addElement("");
        rowVectors.addElement("Attendance Report");
        rowVectors.addElement("for period : ");
        rowVectors.addElement(begindate.toString() + " to ");
        rowVectors.addElement(endDate.toString());
        dataVector.addElement(rowVectors);

        int totalNew = 0;
        int totalReatt = 0;
        int totalAll = 0;
        int totalCash = 0;
        int totalScheme = 0;

        for (int x = 0; x < rangeDates.length; x++) {

            java.util.Vector rowVector = new java.util.Vector();

            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            com.afrisoftech.lib.DateFormatter dateFormatter = null;
            try {
                dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "MMM/yy");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            java.lang.String monthString = dateFormatter.getDateString();

            rowVector.addElement(monthString);

            try {
                if (clinicsCmbx.getSelectedItem().toString().contains("--ALL--")) {
                    java.sql.PreparedStatement pstmtNew = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(comments) = UPPER(?)");
                    //  pstmtNew.setObject(1, );
                    pstmtNew.setString(1, "New");
                    int countNew = 0;
                    java.sql.ResultSet rsetNew = pstmtNew.executeQuery();
                    while (rsetNew.next()) {
                        countNew = rsetNew.getInt(1);
                    }
                    rowVector.addElement(countNew);
                    totalNew = totalNew + countNew;
                    pstmtNew.close();
                    rsetNew.close();

                    java.sql.PreparedStatement pstmtOld = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(comments) = UPPER(?)");
                    //pstmtOld.setObject(1, listofAct[i]);
                    pstmtOld.setObject(1, "Old");
                    int countOld = 0;
                    java.sql.ResultSet rsetOld = pstmtOld.executeQuery();
                    while (rsetOld.next()) {
                        countOld = rsetOld.getInt(1);
                    }
                    rowVector.addElement(countOld);
                    totalReatt = totalReatt + countOld;
                    pstmtOld.close();
                    rsetOld.close();

                    java.sql.PreparedStatement pstmtTotal = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"'");
                    //pstmtTotal.setObject(1, listofAct[i]);
                    int countTotal = 0;
                    java.sql.ResultSet rsetTotal = pstmtTotal.executeQuery();
                    while (rsetTotal.next()) {
                        countTotal = rsetTotal.getInt(1);
                    }
                    rowVector.addElement(countTotal);
                    totalAll = totalAll + countTotal;
                    pstmtTotal.close();
                    rsetTotal.close();

                    java.sql.PreparedStatement pstmtCash = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(payment) NOT LIKE UPPER(?)");
                    //pstmtCash.setObject(1, listofAct[i]);
                    pstmtCash.setObject(1, "Scheme");
                    int countCash = 0;
                    java.sql.ResultSet rsetCash = pstmtCash.executeQuery();
                    while (rsetCash.next()) {
                        countCash = rsetCash.getInt(1);
                    }
                    rowVector.addElement(countCash);
                    totalCash = totalCash + countCash;
                    pstmtCash.close();
                    rsetCash.close();

                    java.sql.PreparedStatement pstmtScheme = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(payment) = UPPER(?)");
                    // pstmtScheme.setObject(1, listofAct[i]);
                    pstmtScheme.setObject(1, "Scheme");
                    int countScheme = 0;
                    java.sql.ResultSet rsetScheme = pstmtScheme.executeQuery();
                    while (rsetScheme.next()) {
                        countScheme = rsetScheme.getInt(1);
                    }
                    rowVector.addElement(countScheme);
                    totalScheme = totalScheme + countScheme;
                    pstmtScheme.close();
                    rsetScheme.close();

                    dataVector.addElement(rowVector);
                } else {
                    java.sql.PreparedStatement pstmtNew = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(comments) = UPPER(?) AND UPPER(clinic) = ?");
                    //  pstmtNew.setObject(1, );
                    pstmtNew.setString(1, "New");
                    pstmtNew.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countNew = 0;
                    java.sql.ResultSet rsetNew = pstmtNew.executeQuery();
                    while (rsetNew.next()) {
                        countNew = rsetNew.getInt(1);
                    }
                    rowVector.addElement(countNew);
                    totalNew = totalNew + countNew;
                    pstmtNew.close();
                    rsetNew.close();

                    java.sql.PreparedStatement pstmtOld = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(comments) = UPPER(?) AND UPPER(clinic) = ?");
                    //pstmtOld.setObject(1, listofAct[i]);
                    pstmtOld.setObject(1, "Old");
                    pstmtOld.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countOld = 0;
                    java.sql.ResultSet rsetOld = pstmtOld.executeQuery();
                    while (rsetOld.next()) {
                        countOld = rsetOld.getInt(1);
                    }
                    rowVector.addElement(countOld);
                    totalReatt = totalReatt + countOld;
                    pstmtOld.close();
                    rsetOld.close();

                    java.sql.PreparedStatement pstmtTotal = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"'  AND UPPER(clinic) = ?");
                    //pstmtTotal.setObject(1, listofAct[i]);
                    pstmtTotal.setString(1, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countTotal = 0;
                    java.sql.ResultSet rsetTotal = pstmtTotal.executeQuery();
                    while (rsetTotal.next()) {
                        countTotal = rsetTotal.getInt(1);
                    }
                    rowVector.addElement(countTotal);
                    totalAll = totalAll + countTotal;
                    pstmtTotal.close();
                    rsetTotal.close();

                    java.sql.PreparedStatement pstmtCash = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(payment) NOT LIKE UPPER(?) AND UPPER(clinic) = ?");
                    //pstmtCash.setObject(1, listofAct[i]);
                    pstmtCash.setObject(1, "Scheme");
                    pstmtCash.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countCash = 0;
                    java.sql.ResultSet rsetCash = pstmtCash.executeQuery();
                    while (rsetCash.next()) {
                        countCash = rsetCash.getInt(1);
                    }
                    rowVector.addElement(countCash);
                    totalCash = totalCash + countCash;
                    pstmtCash.close();
                    rsetCash.close();

                    java.sql.PreparedStatement pstmtScheme = connectDB.prepareStatement("SELECT COUNT(patient_no) FROM hp_patient_visit WHERE date between '"+rangeDates[x][0]+"' AND '"+rangeDates[x][1]+"' AND UPPER(payment) = UPPER(?) AND UPPER(clinic) = ?");
                    // pstmtScheme.setObject(1, listofAct[i]);
                    pstmtScheme.setObject(1, "Scheme");
                    pstmtScheme.setString(2, clinicsCmbx.getSelectedItem().toString().toUpperCase());
                    int countScheme = 0;
                    java.sql.ResultSet rsetScheme = pstmtScheme.executeQuery();
                    while (rsetScheme.next()) {
                        countScheme = rsetScheme.getInt(1);
                    }
                    rowVector.addElement(countScheme);
                    totalScheme = totalScheme + countScheme;
                    pstmtScheme.close();
                    rsetScheme.close();

                    dataVector.addElement(rowVector);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }

        java.util.Vector rowVector = new java.util.Vector();
        rowVector.addElement("Total");
        rowVector.addElement(totalNew);
        rowVector.addElement(totalReatt);
        rowVector.addElement(totalAll);
        rowVector.addElement(totalCash);
        rowVector.addElement(totalScheme);
        dataVector.addElement(rowVector);

        reportTable1.setModel(new javax.swing.table.DefaultTableModel(dataVector, columnVector));

    }

    public java.lang.Object[] getListofActivities() {
        int interval = 0;
        java.lang.Object[][] rangeDates = dailySeries.getAgeingDateSeries();
        java.lang.Object[][] monthDates = ageingSeries.getAgeingDateSeries();

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        //for (int k = 0;  k < monthDates.length; k++){
        for (int k = monthDates.length - 1; k >= 0; k--) {

            for (int t = 0; t < rangeDates.length; t++) {

                listActVector.addElement(rangeDates[t][k]);

            }
        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JPanel actionPanel1;
    private javax.swing.JTabbedPane analyticsTabbedPane;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private com.afrisoftech.lib.DatePicker beginDatePicker1;
    private javax.swing.JComboBox<String> clinicsCmbx;
    private javax.swing.JComboBox<String> clinicsCmbx1;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton closeBtn1;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private com.afrisoftech.lib.DatePicker endDatePicker1;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel headerPanel1;
    private javax.swing.JPanel monthlyAnalyticsPanel;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton refreshBtn1;
    private javax.swing.JPanel reportBodyPanel;
    private javax.swing.JPanel reportBodyPanel1;
    private javax.swing.JScrollPane reportScrollPane;
    private javax.swing.JScrollPane reportScrollPane1;
    private javax.swing.JTable reportTable;
    private javax.swing.JTable reportTable1;
    private javax.swing.JComboBox<String> schemeCmbx;
    private javax.swing.JComboBox<String> schemeCmbx1;
    private javax.swing.JLabel spacerLbl;
    private javax.swing.JLabel spacerLbl1;
    private javax.swing.JPanel yearlyAnalyticsPanel;
    // End of variables declaration//GEN-END:variables
}