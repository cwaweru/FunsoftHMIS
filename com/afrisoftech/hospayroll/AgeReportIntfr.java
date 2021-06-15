/*
 * ReportIntfr.java
 *
 * Created on July 6, 2008, 4:40 PM
 */
package com.afrisoftech.hospayroll;

import biz.systempartners.reports.*;
import java.sql.SQLException;
//import org.openide.util.Exceptions;
//import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class AgeReportIntfr extends javax.swing.JInternalFrame {

    java.sql.Connection connectDB = null;
    String bank = "";

    /**
     * Creates new form ReportIntfr
     */
    public AgeReportIntfr(java.sql.Connection connDb) {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        cashSalesPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        beginDatePicker = new com.afrisoftech.lib.DatePicker();
        endDatePicker = new com.afrisoftech.lib.DatePicker();
        beginDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        openReportBtn = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        reportBodyPanel = new javax.swing.JPanel();
        reportBodyJscrollPane = new javax.swing.JScrollPane();
        reportBodyTable = new com.afrisoftech.dbadmin.JXTable();
        buttonPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        reloadReportBtn = new javax.swing.JButton();
        spaceLable = new javax.swing.JLabel();
        totalsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        progressStatusShiftsTxt = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Funsoft Payroll DashBoard & Analysis");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        cashSalesPanel.setLayout(new java.awt.GridBagLayout());

        headerPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        headerPanel.add(beginDatePicker, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        headerPanel.add(endDatePicker, gridBagConstraints);

        beginDateLbl.setText("Begin Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        headerPanel.add(beginDateLbl, gridBagConstraints);

        endDateLbl.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        headerPanel.add(endDateLbl, gridBagConstraints);

        openReportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/16x16/Desktop.png"))); // NOI18N
        openReportBtn.setMnemonic('o');
        openReportBtn.setText("Open Report ...");
        openReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        headerPanel.add(openReportBtn, gridBagConstraints);

        jComboBox1.setModel(com.afrisoftech.lib.ComboBoxModel.ComboBoxModel(connectDB, "select DISTINCT company_name from payroll_comsetup order by company_name"));
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder("Company"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerPanel.add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Active Employees", "Active Selected Month" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerPanel.add(jComboBox2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        cashSalesPanel.add(headerPanel, gridBagConstraints);

        reportBodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        reportBodyPanel.setLayout(new java.awt.GridBagLayout());

        reportBodyTable.setForeground(new java.awt.Color(0, 0, 255));
        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        reportBodyJscrollPane.setViewportView(reportBodyTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        reportBodyPanel.add(reportBodyJscrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 200.0;
        cashSalesPanel.add(reportBodyPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        closeBtn.setMnemonic('l');
        closeBtn.setText("Close Reporter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(closeBtn, gridBagConstraints);

        reloadReportBtn.setMnemonic('r');
        reloadReportBtn.setText("Reload report");
        reloadReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadReportBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(reloadReportBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 1.0;
        buttonPanel.add(spaceLable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        cashSalesPanel.add(buttonPanel, gridBagConstraints);

        totalsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        totalsPanel.setLayout(new java.awt.GridBagLayout());

        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Progress status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        totalsPanel.add(jLabel3, gridBagConstraints);

        progressStatusShiftsTxt.setEditable(false);
        progressStatusShiftsTxt.setForeground(new java.awt.Color(0, 255, 0));
        progressStatusShiftsTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 50.0;
        gridBagConstraints.weighty = 1.0;
        totalsPanel.add(progressStatusShiftsTxt, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        cashSalesPanel.add(totalsPanel, gridBagConstraints);

        jTabbedPane1.addTab("Detail Analysis", cashSalesPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        setBounds(0, 0, 926, 455);
    }// </editor-fold>//GEN-END:initComponents

    private void openReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReportBtnActionPerformed

        loadReport();
        // TODO add your handling code here:
    }//GEN-LAST:event_openReportBtnActionPerformed

    private void reloadReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadReportBtnActionPerformed

        loadReport();

        // TODO add your handling code here:
    }//GEN-LAST:event_reloadReportBtnActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    

    

    private void loadReport() {

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

        bank = jComboBox1.getSelectedItem().toString();
        

        java.lang.Object[] staffNoArray = getListofStaff();


        java.util.Vector rowVector = new java.util.Vector(1);

        java.util.Vector columnVector = new java.util.Vector(1);

        columnVector.addElement("Staff No.");

        columnVector.addElement("Staff Name");
        //columnVector.addElement("Scale");
        columnVector.addElement("Designation");

        columnVector.addElement("Department");
        columnVector.addElement("Directorate");
        columnVector.addElement("ID No");
        columnVector.addElement("Date of Employment");
        columnVector.addElement("Date of Birth");
        columnVector.addElement("Age");

       

        // Detail for revenue streams
        for (int i = 0; i < staffNoArray.length; i++) {

           

            //Earnings ------
            java.util.Vector valuesVector = new java.util.Vector(1);
            valuesVector.addElement(staffNoArray[i]);
            valuesVector.addElement(getStaffName(connectDB, staffNoArray[i].toString()));

            valuesVector.addElement(getStaffDes(connectDB, staffNoArray[i].toString()));
            valuesVector.addElement(getStaffDepartment(connectDB, staffNoArray[i].toString()));
            valuesVector.addElement(getStaffDirectorate(connectDB, staffNoArray[i].toString()));

            valuesVector.addElement(getStaffIDNo(connectDB, staffNoArray[i].toString()));
            valuesVector.addElement(getStaffdoe(connectDB, staffNoArray[i].toString()));
            valuesVector.addElement(getStaffdob(connectDB, staffNoArray[i].toString()));
            valuesVector.addElement(getStaffAge(connectDB, staffNoArray[i].toString()));
           // valuesVector.addElement(getStaffPinNo(connectDB, staffNoArray[i].toString()));

          //  valuesVector.addElement(getStaffNssf(connectDB, staffNoArray[i].toString()));
           // valuesVector.addElement(getStaffNhif(connectDB, staffNoArray[i].toString()));

            

            


            rowVector.addElement(valuesVector);

        }

        reportBodyTable.setModel(new javax.swing.table.DefaultTableModel(rowVector, columnVector));

        // totalCreditSalesTxt1.setText(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(reportBodyTable1, 5)));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }

    private String getStaffName(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT last_name || ' ' || first_name || ' ' || middle_name FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }


    private String getStaffScale(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT employee_grade FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffNhif(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT nhif_no FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffNssf(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT nssf_no FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffPinNo(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT pin_no FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffIDNo(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT id_no FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }
    private String getStaffdob(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT  birth_date FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

//        if (staffName.length() > 1) {
            return staffName;
//        } else {
//            return "-";
//        }
        //return activityDescription;
    }
    private String getStaffAge(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT  (current_date - birth_date)/365 FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }


            return staffName;
        
        //return activityDescription;
    }
    
    private String getStaffdoe(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT date_employed FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffDirectorate(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT directorate FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffDepartment(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT department FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    private String getStaffDes(java.sql.Connection connDB, String glCode) {
        String staffName = "";
        try {
            java.sql.PreparedStatement pstmt = connDB.prepareStatement("SELECT DISTINCT official_desgnation FROM master_file WHERE employee_no = ?  ");
            pstmt.setString(1, glCode);
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                staffName = rset.getString(1);
            }

            //activityDescription=glCode;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        if (staffName.length() > 1) {
            return staffName;
        } else {
            return "-";
        }
        //return activityDescription;
    }

    

    public java.lang.Object[] getListofEarnings() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement stmtShifts = connectDB.prepareStatement("SELECT DISTINCT description,(SELECT code from deductions_allowances where upper(posting.description)=upper(deductions_allowances.description) )  FROM posting where date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND allowance_deduction ilike 'earning%' and company_name ilike '" + bank + "' order by 2    ");

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSetShifts = stmtShifts.executeQuery();

            while (rSetShifts.next()) {

                listStaffNoVector.addElement(rSetShifts.getObject(1));

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofreliefs() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement stmtShifts = connectDB.prepareStatement("SELECT DISTINCT description FROM posting where date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND allowance_deduction ilike 'less%' and company_name ilike '" + bank + "' order by description   ");

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSetShifts = stmtShifts.executeQuery();

            while (rSetShifts.next()) {

                listStaffNoVector.addElement(rSetShifts.getObject(1));

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofBalances() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement stmtShifts = connectDB.prepareStatement("SELECT DISTINCT  sacco_name FROM payroll_balances2 where company_name='" + bank + "' AND  sacco_name not ilike 'Staff Welfare' AND  sacco_name not ilike 'UNION DUE' ");

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSetShifts = stmtShifts.executeQuery();

            while (rSetShifts.next()) {

                listStaffNoVector.addElement(rSetShifts.getObject(1));

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofDeductions() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement stmtShifts = connectDB.prepareStatement("SELECT DISTINCT description FROM deduction_summary where date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' and company_name ilike '" + bank + "' order by description  ");

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSetShifts = stmtShifts.executeQuery();

            while (rSetShifts.next()) {

                listStaffNoVector.addElement(rSetShifts.getObject(1));

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofnonCashBenefit() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement stmtShifts = connectDB.prepareStatement("SELECT DISTINCT description FROM posting where date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND allowance_deduction ilike 'non%' and company_name ilike '" + bank + "' order by description    ");

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSetShifts = stmtShifts.executeQuery();

            while (rSetShifts.next()) {

                listStaffNoVector.addElement(rSetShifts.getObject(1));

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

//    public java.lang.Object[] getListofCostCentres() {
//
//        java.lang.Object[] listofStaffNos = null;
//
//        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);
//
//        try {
//
//            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
//            java.sql.PreparedStatement stmtCostCentres = connectDB.prepareStatement("SELECT DISTINCT (SELECT ward FROM hp_admission WHERE hp_admission.patient_no = ac_ledger.patient_no AND ac_ledger.date > hp_admission.date_admitted LIMIT 1) FROM ac_ledger WHERE date BETWEEN '" + beginDatePicker6.getDate() + "' AND '" + endDatePicker6.getDate() + "' AND (SELECT ward FROM hp_admission WHERE hp_admission.patient_no = ac_ledger.patient_no AND ac_ledger.date > hp_admission.date_admitted LIMIT 1) not ilike '%mortuary%' AND (SELECT ward FROM hp_admission WHERE hp_admission.patient_no = ac_ledger.patient_no AND ac_ledger.date > hp_admission.date_admitted LIMIT 1) not ilike '%abscond%' ORDER BY 1");
//
//            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
//            java.sql.ResultSet rSetCostCentres = stmtCostCentres.executeQuery();
//
//            while (rSetCostCentres.next()) {
//
//                listStaffNoVector.addElement(rSetCostCentres.getObject(1));
//
//            }
//
//        } catch (java.sql.SQLException sqlExec) {
//            sqlExec.printStackTrace();
//            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
//
//        }
//
//        listofStaffNos = listStaffNoVector.toArray();
//        System.out.println("Done list of Staff Nos ...");
//        return listofStaffNos;
//    }
    public java.lang.Object[] getListofStaff() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement stmtShifts = null;

            if(jComboBox2.getSelectedItem().toString().equalsIgnoreCase("All")){
                stmtShifts = connectDB.prepareStatement("SELECT DISTINCT employee_no FROM master_file WHERE  company_name ilike '" + bank + "' ORDER BY 1");
            }else  if(jComboBox2.getSelectedItem().toString().equalsIgnoreCase("Active Employees")){
                stmtShifts = connectDB.prepareStatement("SELECT DISTINCT employee_no FROM master_file WHERE  retired = false and suspend = false and company_name ilike '" + bank + "' ORDER BY 1");
            }else{
                stmtShifts = connectDB.prepareStatement("SELECT DISTINCT staff_no FROM posting WHERE date BETWEEN '" + beginDatePicker.getDate() + "' AND '" + endDatePicker.getDate() + "' AND company_name ilike '" + bank + "' ORDER BY 1");
            }
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSetShifts = stmtShifts.executeQuery();

            while (rSetShifts.next()) {

                listStaffNoVector.addElement(rSetShifts.getObject(1));

            }

        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beginDateLbl;
    private com.afrisoftech.lib.DatePicker beginDatePicker;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel cashSalesPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel endDateLbl;
    private com.afrisoftech.lib.DatePicker endDatePicker;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton openReportBtn;
    private javax.swing.JTextField progressStatusShiftsTxt;
    private javax.swing.JButton reloadReportBtn;
    public static javax.swing.JScrollPane reportBodyJscrollPane;
    public static javax.swing.JPanel reportBodyPanel;
    public static javax.swing.JTable reportBodyTable;
    private javax.swing.JLabel spaceLable;
    private javax.swing.JPanel totalsPanel;
    // End of variables declaration//GEN-END:variables

}