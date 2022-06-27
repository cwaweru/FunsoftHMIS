/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.hmis.automatedoperations;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class AutomatedIPCharges {

    static java.sql.Connection connectDB = null;
    javax.swing.table.DefaultTableModel chargesTableModel = null;// = new javax.swing.table.DefaultTableModel();
    javax.swing.JTable jTable1 = new javax.swing.JTable();
    com.afrisoftech.lib.DBObject dbObject;
    com.afrisoftech.lib.DatePicker datePicker1 = new com.afrisoftech.lib.DatePicker();

    public void AutomatedIPCharges(java.sql.Connection connDb) {

        connectDB = connDb;

        System.out.println("Connection object : [" + connectDB + "]");

        datePicker1.setDate(java.util.Calendar.getInstance().getTime());

        this.chargeDailyFees(connectDB);

        this.billMortCharges(connectDB);

    }

    private void chargeDailyFees(java.sql.Connection connDB) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDB;

        jTable1.setModel(this.getTableModel());

        try {
            String billNo = null;
            String transNo = null;
            String payMode = null;
            String patientAcc = null;
            String cardNo = null;
            String AccDesc = null;
            String scheme = null;
            String cardName = null;
            String isurer = null;
            String expDate = null;
            String staffNo = null;
            String glAcc = null;
            String mainAcc = null;
            String user = null;
            String patientCat = "";
            String mainService = null;
            String service = null;
            double rate = 0.00;
            String visitid = null;
            String glcodesc = null;
            int patno = 0;

            java.sql.Savepoint registerSavePoint = null;
            try {
                connectDB.setAutoCommit(false);
                registerSavePoint = connectDB.setSavepoint("registration");
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
            try {
                java.util.Date periodFrom = null;
                java.util.Date periodTo = null;

                java.sql.Statement stm12q1 = connectDB.createStatement();
                java.sql.ResultSet rse12q1 = stm12q1.executeQuery("select current_user");
                while (rse12q1.next()) {
                    user = rse12q1.getString(1);
                }

                java.sql.Statement stm12 = connectDB.createStatement();
                java.sql.ResultSet rse12 = stm12.executeQuery("select code,activity from pb_activity where activity_category = 'PR'");
                while (rse12.next()) {

                    patientAcc = rse12.getObject(1).toString();
                    AccDesc = rse12.getObject(2).toString();
                }

                java.sql.Statement ps = connectDB.createStatement();
                java.sql.ResultSet rst = ps.executeQuery("select nextval('transaction_no_seq')");
                while (rst.next()) {
                    rst.getObject(1).toString();

                    transNo = rst.getObject(1).toString();

                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    if (jTable1.getValueAt(i, 0) != null) {
                        java.sql.Statement stm121 = connectDB.createStatement();
                        System.err.println("Ward considered" + jTable1.getValueAt(i, 2).toString());
                        java.sql.ResultSet rse121 = stm121.executeQuery("SELECT revcode,revdesc FROM hp_wards WHERE ward_name ILIKE  '" + jTable1.getValueAt(i, 2).toString()/*
                                 * jComboBox1.getSelectedItem()
                                 */ + "'");
                        while (rse121.next()) {

                            glAcc = rse121.getObject(1).toString();
                            mainAcc = rse121.getObject(2).toString();
                        }
                        if (jTable1.getModel().getValueAt(i, 0) != null) {
                            java.sql.Statement stm12q = connectDB.createStatement();
                            java.sql.ResultSet rse12q = stm12q.executeQuery("select count(description) from hp_patient_card where date::date = now()::date AND service ilike 'Accomodation%' AND patient_no =  '" + jTable1.getValueAt(i, 0).toString() + "'");
                            while (rse12q.next()) {
                                patno = rse12q.getInt(1);
                            }

                            if (patno > 0) {
                                // javax.swing.JOptionPane.showMessageDialog(this, "Beds have been Charged for '" + datePicker1.getDate().toString() + "'", "Comfirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            } else {

                                visitid = jTable1.getValueAt(i, 7).toString();
                                // }
                                java.sql.Statement stm1 = connectDB.createStatement();
                                java.sql.ResultSet rse1 = stm1.executeQuery("select pay_mode,account_no,description,payer,payer,expiry_date,account_no from hp_inpatient_register where patient_no ='" + jTable1.getValueAt(i, 0).toString() + "'");

                                //java.sql.ResultSet rse1 = stm1.executeQuery("select pay_mode,payer,account_no,description,category,expiry_date from hp_inpatient_register where patient_no = '"+jTable1.getValueAt(i,0).toString()+"'");
                                java.sql.Statement stm11 = connectDB.createStatement();
                                java.sql.ResultSet rse11 = stm11.executeQuery("SELECT nursing_revcode, nursing_revdesc,'Nursing Daily Charges','" + jTable1.getValueAt(i, 5) + "' FROM hp_wards WHERE ward_name ILIKE  '" + jTable1.getValueAt(i, 2).toString()/*
                                         * jComboBox1.getSelectedItem()
                                         */ + "'");

                                while (rse11.next()) {
                                    mainService = rse11.getObject(2).toString();
                                    service = rse11.getObject(3).toString();
                                    rate = rse11.getDouble(4);
                                    glcodesc = rse11.getObject(1).toString();
                                }
                                while (rse1.next()) {
                                    payMode = dbObject.getDBObject(rse1.getObject(1), "-");
                                    cardNo = dbObject.getDBObject(rse1.getObject(2), "-");
                                    scheme = dbObject.getDBObject(rse1.getObject(3), "-");
                                    cardName = dbObject.getDBObject(rse1.getObject(4), "-");
                                    isurer = dbObject.getDBObject(rse1.getObject(5), "-");
                                    expDate = dbObject.getDBObject(rse1.getObject(6), "'now'");
                                    staffNo = dbObject.getDBObject(rse1.getObject(7), "-");
                                }

                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");

                                pstmt.setObject(1, jTable1.getValueAt(i, 0).toString());
                                pstmt.setObject(2, "Accomodation");
                                pstmt.setString(3, patientCat);
                                pstmt.setString(4, payMode);
                                pstmt.setString(5, transNo);
                                pstmt.setString(7, scheme);
                                pstmt.setString(6, cardNo);
                                pstmt.setString(8, cardName);
                                pstmt.setString(9, isurer);
                                pstmt.setDate(10, null);
                                pstmt.setString(11, "");
                                pstmt.setDouble(12, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                                pstmt.setDouble(13, 0.00);
                                pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                pstmt.setObject(15, patientAcc);
                                pstmt.setString(16, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB, com.afrisoftech.lib.WardGLAccountsFactory.getBedChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString())));
                                pstmt.setDouble(17, java.lang.Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                                pstmt.setObject(18, staffNo);
                                pstmt.setBoolean(19, false);
                                pstmt.setString(20, "Billing");
                                pstmt.setBoolean(21, false);
                                pstmt.setString(22, AccDesc);
                                pstmt.setString(23, visitid);
                                pstmt.setString(24, user);
                                pstmt.setString(25, billNo);
                                pstmt.setString(26, "IP");
                                pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                pstmt.setString(28, visitid);
                                pstmt.executeUpdate();

                                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                pstmt2.setString(1, com.afrisoftech.lib.WardGLAccountsFactory.getBedChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString()));
                                pstmt2.setObject(2, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB, com.afrisoftech.lib.WardGLAccountsFactory.getBedChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString())));
                                pstmt2.setObject(3, jTable1.getValueAt(i, 0).toString());
                                pstmt2.setObject(4, jTable1.getValueAt(i, 1).toString());
                                pstmt2.setString(5, "");
                                pstmt2.setString(6, "");
                                pstmt2.setString(7, "");
                                pstmt2.setString(8, "IP");
                                pstmt2.setString(9, "");
                                pstmt2.setString(10, payMode);
                                pstmt2.setString(11, "");
                                pstmt2.setString(12, "");
                                pstmt2.setString(13, "");
                                pstmt2.setString(14, "Accomodation");
                                pstmt2.setString(15, "Billing");
                                pstmt2.setDouble(16, 0.00);
                                pstmt2.setDouble(17, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                                pstmt2.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                pstmt2.setString(19, transNo);
                                pstmt2.setBoolean(20, false);
                                pstmt2.setBoolean(21, false);
                                pstmt2.setBoolean(22, false);
                                pstmt2.setString(23, user);
                                pstmt2.executeUpdate();

                                if (rate > 0) {
                                    java.sql.PreparedStatement pstmtr = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?,?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");
                                    pstmtr.setObject(1, jTable1.getValueAt(i, 0).toString());
                                    pstmtr.setObject(2, service);
                                    pstmtr.setString(3, patientCat);
                                    pstmtr.setString(4, payMode);
                                    pstmtr.setString(5, transNo);
                                    pstmtr.setString(7, scheme);
                                    pstmtr.setString(6, cardNo);
                                    pstmtr.setString(8, cardName);
                                    pstmtr.setString(9, isurer);
                                    pstmtr.setDate(10, null);
                                    pstmtr.setString(11, "");
                                    pstmtr.setDouble(12, Double.parseDouble(jTable1.getValueAt(i, 6).toString()));
                                    pstmtr.setDouble(13, 0.00);
                                    pstmtr.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmtr.setObject(15, patientAcc);
                                    pstmtr.setString(16, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB, com.afrisoftech.lib.WardGLAccountsFactory.getNursingChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString())));
                                    pstmtr.setDouble(17, Double.parseDouble(jTable1.getValueAt(i, 4).toString()));
                                    pstmtr.setObject(18, staffNo);
                                    pstmtr.setBoolean(19, false);
                                    pstmtr.setString(20, "Billing");
                                    pstmtr.setBoolean(21, false);
                                    pstmtr.setString(22, AccDesc);
                                    pstmtr.setString(23, visitid);
                                    pstmtr.setString(24, user);
                                    pstmtr.setString(25, billNo);
                                    pstmtr.setString(26, "IP");
                                    pstmtr.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                    pstmtr.setString(28, visitid);
                                    pstmtr.executeUpdate();

                                    java.sql.PreparedStatement pstmt2r = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt2r.setString(1, com.afrisoftech.lib.WardGLAccountsFactory.getNursingChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString()));
                                    pstmt2r.setObject(2, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB, com.afrisoftech.lib.WardGLAccountsFactory.getNursingChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString())));
                                    pstmt2r.setObject(3, jTable1.getValueAt(i, 0).toString());
                                    pstmt2r.setString(4, jTable1.getValueAt(i, 1).toString());
                                    pstmt2r.setString(5, "");
                                    pstmt2r.setString(6, "");
                                    pstmt2r.setString(7, "");
                                    pstmt2r.setString(8, "IP");
                                    pstmt2r.setString(9, "");
                                    pstmt2r.setString(10, payMode);
                                    pstmt2r.setString(11, "");
                                    pstmt2r.setString(12, "");
                                    pstmt2r.setString(13, "");
                                    pstmt2r.setString(14, service);
                                    pstmt2r.setString(15, "Billing");
                                    pstmt2r.setDouble(16, 0.00);
                                    pstmt2r.setDouble(17, Double.parseDouble(jTable1.getValueAt(i, 6).toString()));
                                    pstmt2r.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmt2r.setString(19, transNo);
                                    pstmt2r.setBoolean(20, false);
                                    pstmt2r.setBoolean(21, false);
                                    pstmt2r.setBoolean(22, false);
                                    pstmt2r.setString(23, user);
                                    pstmt2r.executeUpdate();

                                }
                                
                                
                                //Charge Consumables
                                service = "Consumables";
                                rate = Double.parseDouble(jTable1.getValueAt(i, 8).toString());
                                
                                if (rate > 0) {
                                    java.sql.PreparedStatement pstmtr = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?,?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");
                                    pstmtr.setObject(1, jTable1.getValueAt(i, 0).toString());
                                    pstmtr.setObject(2, service);
                                    pstmtr.setString(3, patientCat);
                                    pstmtr.setString(4, payMode);
                                    pstmtr.setString(5, transNo);
                                    pstmtr.setString(7, scheme);
                                    pstmtr.setString(6, cardNo);
                                    pstmtr.setString(8, cardName);
                                    pstmtr.setString(9, isurer);
                                    pstmtr.setDate(10, null);
                                    pstmtr.setString(11, "");
                                    pstmtr.setDouble(12, Double.parseDouble(jTable1.getValueAt(i, 8).toString()));
                                    pstmtr.setDouble(13, 0.00);
                                    pstmtr.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmtr.setObject(15, patientAcc);
                                    pstmtr.setString(16, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB,  jTable1.getValueAt(i, 9).toString() ));
                                    pstmtr.setDouble(17, Double.parseDouble(jTable1.getValueAt(i, 4).toString()));
                                    pstmtr.setObject(18, staffNo);
                                    pstmtr.setBoolean(19, false);
                                    pstmtr.setString(20, "Billing");
                                    pstmtr.setBoolean(21, false);
                                    pstmtr.setString(22, AccDesc);
                                    pstmtr.setString(23, visitid);
                                    pstmtr.setString(24, user);
                                    pstmtr.setString(25, billNo);
                                    pstmtr.setString(26, "IP");
                                    pstmtr.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                    pstmtr.setString(28, visitid);
                                    pstmtr.executeUpdate();

                                    java.sql.PreparedStatement pstmt2r = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt2r.setString(1,  jTable1.getValueAt(i, 9).toString() );
                                    pstmt2r.setObject(2, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB,  jTable1.getValueAt(i, 9).toString() ));
                                    pstmt2r.setObject(3, jTable1.getValueAt(i, 0).toString());
                                    pstmt2r.setString(4, jTable1.getValueAt(i, 1).toString());
                                    pstmt2r.setString(5, "");
                                    pstmt2r.setString(6, "");
                                    pstmt2r.setString(7, "");
                                    pstmt2r.setString(8, "IP");
                                    pstmt2r.setString(9, "");
                                    pstmt2r.setString(10, payMode);
                                    pstmt2r.setString(11, "");
                                    pstmt2r.setString(12, "");
                                    pstmt2r.setString(13, "");
                                    pstmt2r.setString(14, service);
                                    pstmt2r.setString(15, "Billing");
                                    pstmt2r.setDouble(16, 0.00);
                                    pstmt2r.setDouble(17, Double.parseDouble(jTable1.getValueAt(i, 8).toString()));
                                    pstmt2r.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                    pstmt2r.setString(19, transNo);
                                    pstmt2r.setBoolean(20, false);
                                    pstmt2r.setBoolean(21, false);
                                    pstmt2r.setBoolean(22, false);
                                    pstmt2r.setString(23, user);
                                    pstmt2r.executeUpdate();

                                }
                                
                                

                                java.sql.Statement stm12qx = connectDB.createStatement();
                                java.sql.ResultSet rse12qx = stm12qx.executeQuery("SELECT service_type, rate, main_service,  gl_account,use_set_revenue_dept  FROM pb_operating_parameters WHERE auto_bill =true");
                                while (rse12qx.next()) {

                                    service = rse12qx.getString(1);
                                    rate = rse12qx.getDouble(2);
                                    String mainservice = rse12qx.getString(3);
                                    String glaccount = rse12qx.getString(4);
                                    
                                    boolean use_set_revenue_dept = rse12qx.getBoolean(5);
                                    
                                    System.err.println("Working on "+service);
                                    if (rate > 0) {
                                        System.err.println("Patiint No : "+jTable1.getValueAt(i, 0).toString());

                                        java.sql.PreparedStatement pstmtr = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?,?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");
                                        pstmtr.setObject(1, jTable1.getValueAt(i, 0).toString());
                                        pstmtr.setObject(2, service);
                                        pstmtr.setString(3, patientCat);
                                        pstmtr.setString(4, payMode);
                                        pstmtr.setString(5, transNo);
                                        pstmtr.setString(7, scheme);
                                        pstmtr.setString(6, cardNo);
                                        pstmtr.setString(8, cardName);
                                        pstmtr.setString(9, isurer);
                                        pstmtr.setDate(10, null);
                                        pstmtr.setString(11, "");
                                        pstmtr.setDouble(12, rate);
                                        pstmtr.setDouble(13, 0.00);
                                        pstmtr.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmtr.setObject(15, patientAcc);
                                        if(use_set_revenue_dept){
                                            pstmtr.setString(16, mainservice);//mainservice);
                                        }else{
                                           pstmtr.setString(16, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB, com.afrisoftech.lib.WardGLAccountsFactory.getBedChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString())));//mainservice);
                                         
                                        }
                                        pstmtr.setDouble(17, 1.00);
                                        pstmtr.setObject(18, staffNo);
                                        pstmtr.setBoolean(19, false);
                                        pstmtr.setString(20, "Billing");
                                        pstmtr.setBoolean(21, false);
                                        pstmtr.setString(22, AccDesc);
                                        pstmtr.setString(23, visitid);
                                        pstmtr.setString(24, user);
                                        pstmtr.setString(25, billNo);
                                        pstmtr.setString(26, "IP");
                                        pstmtr.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                        pstmtr.setString(28, visitid);
                                        pstmtr.executeUpdate();

                                        java.sql.PreparedStatement pstmt2r = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                        if(use_set_revenue_dept){
                                            pstmt2r.setString(1, glaccount);
                                            pstmt2r.setObject(2, mainService);
                                        }else{
                                            pstmt2r.setString(1, com.afrisoftech.lib.WardGLAccountsFactory.getBedChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString()));
                                            pstmt2r.setObject(2, com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connDB, com.afrisoftech.lib.WardGLAccountsFactory.getBedChargesGLAccount(connectDB, jTable1.getValueAt(i, 2).toString())));
                                        }
                                        
                                        pstmt2r.setObject(3, jTable1.getValueAt(i, 0).toString());
                                        pstmt2r.setString(4, jTable1.getValueAt(i, 1).toString());
                                        pstmt2r.setString(5, "");
                                        pstmt2r.setString(6, "");
                                        pstmt2r.setString(7, "");
                                        pstmt2r.setString(8, "IP");
                                        pstmt2r.setString(9, "");
                                        pstmt2r.setString(10, payMode);
                                        pstmt2r.setString(11, "");
                                        pstmt2r.setString(12, "");
                                        pstmt2r.setString(13, "");
                                        pstmt2r.setString(14, service);
                                        pstmt2r.setString(15, "Billing");
                                        pstmt2r.setDouble(16, 0.00);
                                        pstmt2r.setDouble(17, Double.parseDouble(jTable1.getValueAt(i, 6).toString()));
                                        pstmt2r.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                        pstmt2r.setString(19, transNo);
                                        pstmt2r.setBoolean(20, false);
                                        pstmt2r.setBoolean(21, false);
                                        pstmt2r.setBoolean(22, false);
                                        pstmt2r.setString(23, user);
                                        pstmt2r.executeUpdate();
                                    }

                                }

                            }
                        }
                    }
                }

                //  }
                connectDB.commit();
                connectDB.setAutoCommit(true);

                System.out.println("Bed charges completed!");

                for (int k = 0; k < jTable1.getRowCount(); k++) {
                    for (int r = 0; r < jTable1.getColumnCount(); r++) {
                        jTable1.getModel().setValueAt(null, k, r);
                    }
                }

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                try {
                    connectDB.rollback(registerSavePoint);
                } catch (java.sql.SQLException sql) {
                    //  javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                sq.printStackTrace();
                System.out.println(sq.getMessage());
                // javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                ///  //System.exit(1);
            }

        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        }

    }

    public static void main(String args[]) {

        AutomatedIPCharges automatedCharges = new AutomatedIPCharges();

        connectDB = getDBConnection();

        automatedCharges.chargeDailyFees(connectDB);

        // System.exit(0);
        //automatedCharges.billMortCharges(connectDB);
    }

    private static java.sql.Connection getDBConnection() {

        java.sql.Connection connDB = null;

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AutomatedIPCharges.class
                            .getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }
        try {

            connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/funsoft", "postgres", "africana");

            System.out.println("Connection object [" + connDB.toString() + "]");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AutomatedIPCharges.class
                            .getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }

        return connDB;

    }

    javax.swing.table.DefaultTableModel getTableModel() {

        javax.swing.table.DefaultTableModel bedsTableModel = null;

        java.util.Vector bedsRowVector = new java.util.Vector(1, 1);
        java.util.Vector bedsHeaderRowVector = new java.util.Vector(1, 1);

        bedsHeaderRowVector.addElement("Admission No.");
        bedsHeaderRowVector.addElement("Name");
        bedsHeaderRowVector.addElement("Ward");
        bedsHeaderRowVector.addElement("Bed No.");
        bedsHeaderRowVector.addElement("Days");
        bedsHeaderRowVector.addElement("Bed Fee");
        bedsHeaderRowVector.addElement("Nursing Fee");
        bedsHeaderRowVector.addElement("Visit ID");
        bedsHeaderRowVector.addElement("Consumable Rate");
        bedsHeaderRowVector.addElement("Consumable GL");

        int j = 0;
        int i = 0;

        try {
            float noofDays = 0;
            float amount = 0;
            float totalAmt = 0;
            float bedCharged = 0;

            java.sql.Statement stmtTable1 = connectDB.createStatement();

            java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT"
                    + " patient_no,upper(patient_name) as name,ward,bed_no,deposit,nursing,visit_id,\n" +
                    " (SELECT consumable_rate from hp_bed_category  WHERE UPPER(category) = UPPER(wing) ) AS consumable_rate,\n" +
                    " (select consumable_glcode from hp_wards WHERE UPPER(ward_name )= UPPER(ward) ) AS consumable_gl "
                    + " FROM hp_admission WHERE check_out = false AND (ward not ilike '%renal unit%' AND ward NOT ILIKE '%EMERGENCY%')"
                    + " AND patient_no NOT IN (SELECT patient_no FROM hp_mortuary)"
                    + " ORDER BY visit_id");

            while (rsetTable1.next()) {

                // String visitId = null;
                java.sql.Statement pss111 = connectDB.createStatement();
                java.sql.ResultSet rss111 = pss111.executeQuery("select (CURRENT_DATE::date - date_admitted::date),deposit"
                        + " FROM hp_admission WHERE visit_id = '" + rsetTable1.getString(7) + "' ORDER BY visit_id DESC LIMIT 1");
                while (rss111.next()) {
                    noofDays = rss111.getFloat(1);
                    amount = rss111.getFloat(2);
                    totalAmt = /*
                             * noofDays
                             */ 1 * amount;

                }

                java.sql.Statement pss1111 = connectDB.createStatement();
                java.sql.ResultSet rss1111 = pss1111.executeQuery("SELECT SUM(dosage) FROM hp_patient_card"
                        + " WHERE  visit_id = '" + rsetTable1.getString(7) + "' AND service ILIKE '%bed%' AND debit > 0");
                while (rss1111.next()) {
                    bedCharged = rss1111.getFloat(1);

                }
                totalAmt = /*
                         * (noofDays - bedCharged)
                         */ 1 * amount;
                // jTextField23.setText(java.lang.String.valueOf(totalAmt));
                if (totalAmt > 0) {
                    java.util.Vector columnDataVector = new java.util.Vector(1, 1);
                    System.out.println("Working at table row " + i);
                    columnDataVector.addElement(rsetTable1.getObject(1));
                    columnDataVector.addElement(rsetTable1.getObject(2));
                    columnDataVector.addElement(rsetTable1.getObject(3));
                    columnDataVector.addElement(rsetTable1.getObject(4));
                    columnDataVector.addElement(/*
                             * (noofDays - bedCharged)
                             */1);
                    columnDataVector.addElement(totalAmt);
                    columnDataVector.addElement(rsetTable1.getObject(6));
                    columnDataVector.addElement(rsetTable1.getObject(7));
                    columnDataVector.addElement(rsetTable1.getObject(8));
                    columnDataVector.addElement(rsetTable1.getObject(9));
                    i++;
                    bedsRowVector.addElement(columnDataVector);

                }
            }

            bedsTableModel = new javax.swing.table.DefaultTableModel(bedsRowVector, bedsHeaderRowVector);

            // jTable1.setModel(bedsTableModel);
        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            //  //System.exit(1);
            // javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
        }

        return bedsTableModel;

    }

    public void chargeMortuaryDailyFees(java.sql.Connection connDB) {
    }

    public javax.swing.table.TableModel getMortOccupancy() {

        Object mortOccupancy[] = null;
        java.util.Vector mortOccVector = new java.util.Vector(1, 1);
        java.sql.PreparedStatement pstmtMortOcc;
        try {
            pstmtMortOcc = connectDB.prepareStatement("SELECT annual_no FROM hp_mortuary WHERE discharged = false");
            java.sql.ResultSet rsetMortOcc = pstmtMortOcc.executeQuery();
            while (rsetMortOcc.next()) {
                System.out.println("Annual numbers [" + rsetMortOcc.getObject(1) + "]");
                mortOccVector.add(rsetMortOcc.getObject(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AutomatedIPCharges.class
                            .getName()).log(Level.SEVERE, null, ex);
        }

        mortOccupancy = mortOccVector.toArray();

        javax.swing.table.DefaultTableModel bedsTableModel = null;

        java.util.Vector bedsRowVector = new java.util.Vector(1, 1);
        java.util.Vector bedsHeaderRowVector = new java.util.Vector(1, 1);

        bedsHeaderRowVector.addElement("Admission No.");
        bedsHeaderRowVector.addElement("Name");
        bedsHeaderRowVector.addElement("Ward");
        bedsHeaderRowVector.addElement("Service");
        bedsHeaderRowVector.addElement("Days");
        bedsHeaderRowVector.addElement("Bed Fee");
        bedsHeaderRowVector.addElement("Nursing Fee");
        bedsHeaderRowVector.addElement("Visit ID");
        bedsHeaderRowVector.addElement("GL_Code");
        bedsHeaderRowVector.addElement("SERVICE_DEPARTMENT");

        int j = 0;
        int i = 0;

        try {
            float noofDays = 0;
            float amount = 0;
            float totalAmt = 0;
            float bedCharged = 0;
            for (int k = 0; k < mortOccupancy.length; k++) {
                java.sql.Statement stmtTable1 = connectDB.createStatement();

                java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT"
                        + " upper(patient_name) FROM hp_mortuary  WHERE annual_no = '" + mortOccupancy[k] + "'");
                while (rsetTable1.next()) {

                    java.sql.PreparedStatement pstmtAutoServices = connectDB.prepareStatement("SELECT * FROM pb_mort_auto_charges where auto_billing = true");
                    java.sql.ResultSet rsetAutoServices = pstmtAutoServices.executeQuery();

                    while (rsetAutoServices.next()) {
                        //   if (totalAmt > 0) {
                        java.util.Vector columnDataVector = new java.util.Vector(1, 1);
                        System.out.println("Working at farewell table row " + k);
                        columnDataVector.addElement(mortOccupancy[k]);
                        columnDataVector.addElement(rsetTable1.getObject(1));
                        columnDataVector.addElement("Farewell Home");
                        columnDataVector.addElement(rsetAutoServices.getObject(3));
                        columnDataVector.addElement(/*
                                     * (noofDays - bedCharged)
                                 */1);
                        columnDataVector.addElement(rsetAutoServices.getObject(4));
                        columnDataVector.addElement(rsetAutoServices.getObject(5));
                        columnDataVector.addElement(mortOccupancy[k]);
                        columnDataVector.addElement(rsetAutoServices.getObject(1));
                        columnDataVector.addElement(rsetAutoServices.getObject(7));
                        bedsRowVector.addElement(columnDataVector);

                        // }
                    }

                }

            }
            bedsTableModel = new javax.swing.table.DefaultTableModel(bedsRowVector, bedsHeaderRowVector);

            // jTable1.setModel(bedsTableModel);
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            // //System.exit(1);
            // javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
        }

        return bedsTableModel;

        //   return mortOccupancy;
    }

    public void billMortCharges(java.sql.Connection connDb) {

        System.out.println("Entering billing area fro farewell");

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        jTable1.setModel(this.getMortOccupancy());

        try {
            String billNo = null;
            String transNo = null;
            String payMode = null;
            String patientAcc = null;
            String cardNo = null;
            String AccDesc = null;
            String scheme = null;
            String cardName = null;
            String isurer = null;
            String expDate = null;
            String staffNo = null;
            String glAcc = null;
            String mainAcc = null;
            String user = null;
            String patientCat = "";
            String mainService = null;
            String service = null;
            double rate = 0.00;
            String visitid = null;
            String glcodesc = null;
            int patno = 0;

            java.sql.Savepoint registerSavePoint = null;
            try {
                connectDB.setAutoCommit(false);
                registerSavePoint = connectDB.setSavepoint("registration");
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
            try {
                java.util.Date periodFrom = null;
                java.util.Date periodTo = null;

                java.sql.Statement stm12q1 = connectDB.createStatement();
                java.sql.ResultSet rse12q1 = stm12q1.executeQuery("select current_user");
                while (rse12q1.next()) {
                    user = rse12q1.getString(1);
                }

                java.sql.Statement stm12 = connectDB.createStatement();
                java.sql.ResultSet rse12 = stm12.executeQuery("select code,activity from pb_activity where activity_category = 'PR'");
                while (rse12.next()) {

                    patientAcc = rse12.getObject(1).toString();
                    AccDesc = rse12.getObject(2).toString();
                }

                java.sql.Statement ps = connectDB.createStatement();
                java.sql.ResultSet rst = ps.executeQuery("select nextval('transaction_no_seq')");
                while (rst.next()) {
                    rst.getObject(1).toString();

                    transNo = rst.getObject(1).toString();

                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    if (jTable1.getValueAt(i, 0) != null) {
                        java.sql.Statement stm121 = connectDB.createStatement();
                        System.err.println("Farewell Item considered" + jTable1.getValueAt(i, 2).toString());
                        glAcc = jTable1.getValueAt(i, 8).toString();
                        mainAcc = jTable1.getValueAt(i, 9).toString();
                        if (jTable1.getModel().getValueAt(i, 0) != null) {
                            java.sql.Statement stm12q = connectDB.createStatement();
                            java.sql.ResultSet rse12q = stm12q.executeQuery("select count(description) from hp_patient_card where date::date = now()::date AND service ilike 'BED CHARGES%' AND patient_no =  '" + jTable1.getValueAt(i, 0).toString() + "'");
                            while (rse12q.next()) {
                                patno = rse12q.getInt(1);
                            }

                            if (patno > 0) {
                                // javax.swing.JOptionPane.showMessageDialog(this, "Beds have been Charged for '" + datePicker1.getDate().toString() + "'", "Comfirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            } else {

                                visitid = jTable1.getValueAt(i, 0).toString();
                                // }
                                java.sql.Statement stm1 = connectDB.createStatement();

                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");

                                pstmt.setObject(1, jTable1.getValueAt(i, 0).toString());
                                pstmt.setObject(2, jTable1.getValueAt(i, 3));
                                pstmt.setString(3, patientCat);
                                pstmt.setString(4, "CASH");
                                pstmt.setString(5, transNo);
                                pstmt.setString(7, scheme);
                                pstmt.setString(6, cardNo);
                                pstmt.setString(8, cardName);
                                pstmt.setString(9, isurer);
                                pstmt.setDate(10, null);
                                pstmt.setString(11, "");
                                pstmt.setDouble(12, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                                pstmt.setDouble(13, 0.00);
                                pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                pstmt.setObject(15, patientAcc);
                                pstmt.setString(16, mainAcc);
                                pstmt.setDouble(17, java.lang.Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                                pstmt.setObject(18, staffNo);
                                pstmt.setBoolean(19, false);
                                pstmt.setString(20, "Billing");
                                pstmt.setBoolean(21, false);
                                pstmt.setString(22, AccDesc);
                                pstmt.setString(23, visitid);
                                pstmt.setString(24, user);
                                pstmt.setString(25, billNo);
                                pstmt.setString(26, "IP");
                                pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                pstmt.setString(28, visitid);
                                pstmt.executeUpdate();

                                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                pstmt2.setObject(2, mainAcc);
                                pstmt2.setString(1, glAcc);
                                pstmt2.setObject(3, jTable1.getValueAt(i, 0).toString());
                                pstmt2.setObject(4, jTable1.getValueAt(i, 1).toString());
                                pstmt2.setString(5, "");
                                pstmt2.setString(6, "");
                                pstmt2.setString(7, "");
                                pstmt2.setString(8, "IP");
                                pstmt2.setString(9, "");
                                pstmt2.setString(10, payMode);
                                pstmt2.setString(11, "");
                                pstmt2.setString(12, "");
                                pstmt2.setString(13, "");
                                pstmt2.setString(14, jTable1.getValueAt(i, 3).toString());
                                pstmt2.setString(15, "Billing");
                                pstmt2.setDouble(16, 0.00);
                                pstmt2.setDouble(17, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                                pstmt2.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                pstmt2.setString(19, transNo);
                                pstmt2.setBoolean(20, false);
                                pstmt2.setBoolean(21, false);
                                pstmt2.setBoolean(22, false);
                                pstmt2.setString(23, user);
                                pstmt2.executeUpdate();

                                System.out.println("Doing for another body [" + visitid + "]");

                            }
                        }
                    }
                }

                //  }
                connectDB.commit();
                connectDB.setAutoCommit(true);

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                try {
                    connectDB.rollback(registerSavePoint);
                } catch (java.sql.SQLException sql) {
                    //  javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                sq.printStackTrace();
                System.out.println(sq.getMessage());
                // javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                //System.exit(1);
            }

        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        }
    }

    public javax.swing.table.TableModel getMortRegistrationBill(String annualNo) {

        Object mortOccupancy[] = null;
        java.util.Vector mortOccVector = new java.util.Vector(1, 1);
        java.sql.PreparedStatement pstmtMortOcc;
        try {
            pstmtMortOcc = connectDB.prepareStatement("SELECT annual_no FROM hp_mortuary WHERE annual_no = ? and discharged = false");
            pstmtMortOcc.setObject(1, annualNo);
            java.sql.ResultSet rsetMortOcc = pstmtMortOcc.executeQuery();
            while (rsetMortOcc.next()) {
                System.out.println("Annual numbers [" + rsetMortOcc.getObject(1) + "]");
                mortOccVector.add(rsetMortOcc.getObject(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AutomatedIPCharges.class
                            .getName()).log(Level.SEVERE, null, ex);
        }

        mortOccupancy = mortOccVector.toArray();

        javax.swing.table.DefaultTableModel bedsTableModel = null;

        java.util.Vector bedsRowVector = new java.util.Vector(1, 1);
        java.util.Vector bedsHeaderRowVector = new java.util.Vector(1, 1);

        bedsHeaderRowVector.addElement("Admission No.");
        bedsHeaderRowVector.addElement("Name");
        bedsHeaderRowVector.addElement("Ward");
        bedsHeaderRowVector.addElement("Service");
        bedsHeaderRowVector.addElement("Days");
        bedsHeaderRowVector.addElement("Bed Fee");
        bedsHeaderRowVector.addElement("Nursing Fee");
        bedsHeaderRowVector.addElement("Visit ID");
        bedsHeaderRowVector.addElement("GL_Code");
        bedsHeaderRowVector.addElement("SERVICE_DEPARTMENT");

        int j = 0;
        int i = 0;

        try {
            float noofDays = 0;
            float amount = 0;
            float totalAmt = 0;
            float bedCharged = 0;
            for (int k = 0; k < mortOccupancy.length; k++) {
                java.sql.Statement stmtTable1 = connectDB.createStatement();

                java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT"
                        + " upper(patient_name) FROM hp_mortuary  WHERE annual_no = '" + mortOccupancy[k] + "'");
                while (rsetTable1.next()) {

                    java.sql.PreparedStatement pstmtAutoServices = connectDB.prepareStatement("SELECT * FROM pb_mort_auto_charges where one_time_billing = true");
                    java.sql.ResultSet rsetAutoServices = pstmtAutoServices.executeQuery();

                    while (rsetAutoServices.next()) {
                        //   if (totalAmt > 0) {
                        java.util.Vector columnDataVector = new java.util.Vector(1, 1);
                        System.out.println("Working at farewell table row " + k);
                        columnDataVector.addElement(mortOccupancy[k]);
                        columnDataVector.addElement(rsetTable1.getObject(1));
                        columnDataVector.addElement("Farewell Home");
                        columnDataVector.addElement(rsetAutoServices.getObject(3));
                        columnDataVector.addElement(/*
                                     * (noofDays - bedCharged)
                                 */1);
                        columnDataVector.addElement(rsetAutoServices.getObject(4));
                        columnDataVector.addElement(rsetAutoServices.getObject(5));
                        columnDataVector.addElement(mortOccupancy[k]);
                        columnDataVector.addElement(rsetAutoServices.getObject(1));
                        columnDataVector.addElement(rsetAutoServices.getObject(7));
                        bedsRowVector.addElement(columnDataVector);

                        // }
                    }

                }

            }
            bedsTableModel = new javax.swing.table.DefaultTableModel(bedsRowVector, bedsHeaderRowVector);

            // jTable1.setModel(bedsTableModel);
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            ////System.exit(1);
            // javax.swing.JOptionPane.showMessageDialog(this, sqlExec.getMessage());
        }

        return bedsTableModel;

        //   return mortOccupancy;
    }

    public void billRegistrationMortCharges(java.sql.Connection connDb, String annualNo) {

        System.out.println("Entering registration billing area for farewell");

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        jTable1.setModel(this.getMortRegistrationBill(annualNo));

        try {
            String billNo = null;
            String transNo = null;
            String payMode = null;
            String patientAcc = null;
            String cardNo = null;
            String AccDesc = null;
            String scheme = null;
            String cardName = null;
            String isurer = null;
            String expDate = null;
            String staffNo = null;
            String glAcc = null;
            String mainAcc = null;
            String user = null;
            String patientCat = "";
            String mainService = null;
            String service = null;
            double rate = 0.00;
            String visitid = null;
            String glcodesc = null;
            int patno = 0;

            java.sql.Savepoint registerSavePoint = null;
            try {
                connectDB.setAutoCommit(false);
                registerSavePoint = connectDB.setSavepoint("registration");
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
            try {
                java.util.Date periodFrom = null;
                java.util.Date periodTo = null;

                java.sql.Statement stm12q1 = connectDB.createStatement();
                java.sql.ResultSet rse12q1 = stm12q1.executeQuery("select current_user");
                while (rse12q1.next()) {
                    user = rse12q1.getString(1);
                }

                java.sql.Statement stm12 = connectDB.createStatement();
                java.sql.ResultSet rse12 = stm12.executeQuery("select code,activity from pb_activity where activity_category = 'PR'");
                while (rse12.next()) {

                    patientAcc = rse12.getObject(1).toString();
                    AccDesc = rse12.getObject(2).toString();
                }

                java.sql.Statement ps = connectDB.createStatement();
                java.sql.ResultSet rst = ps.executeQuery("select nextval('transaction_no_seq')");
                while (rst.next()) {
                    rst.getObject(1).toString();

                    transNo = rst.getObject(1).toString();

                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    if (jTable1.getValueAt(i, 0) != null) {
                        java.sql.Statement stm121 = connectDB.createStatement();
                        System.err.println("Farewell Item considered" + jTable1.getValueAt(i, 2).toString());
                        glAcc = jTable1.getValueAt(i, 8).toString();
                        mainAcc = jTable1.getValueAt(i, 9).toString();
                        if (jTable1.getModel().getValueAt(i, 0) != null) {
                            java.sql.Statement stm12q = connectDB.createStatement();
                            java.sql.ResultSet rse12q = stm12q.executeQuery("select count(description) from hp_patient_card where date::date = now()::date AND service ilike 'BED CHARGES%' AND patient_no =  '" + jTable1.getValueAt(i, 0).toString() + "'");
                            while (rse12q.next()) {
                                patno = rse12q.getInt(1);
                            }

                            if (patno > 0) {
                                // javax.swing.JOptionPane.showMessageDialog(this, "Beds have been Charged for '" + datePicker1.getDate().toString() + "'", "Comfirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            } else {

                                visitid = jTable1.getValueAt(i, 0).toString();
                                // }
                                java.sql.Statement stm1 = connectDB.createStatement();

                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)");

                                pstmt.setObject(1, jTable1.getValueAt(i, 0).toString());
                                pstmt.setObject(2, jTable1.getValueAt(i, 3));
                                pstmt.setString(3, patientCat);
                                pstmt.setString(4, "CASH");
                                pstmt.setString(5, transNo);
                                pstmt.setString(7, scheme);
                                pstmt.setString(6, cardNo);
                                pstmt.setString(8, cardName);
                                pstmt.setString(9, isurer);
                                pstmt.setDate(10, null);
                                pstmt.setString(11, "");
                                pstmt.setDouble(12, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                                pstmt.setDouble(13, 0.00);
                                pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                pstmt.setObject(15, patientAcc);
                                pstmt.setString(16, mainAcc);
                                pstmt.setDouble(17, java.lang.Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                                pstmt.setObject(18, staffNo);
                                pstmt.setBoolean(19, false);
                                pstmt.setString(20, "Billing");
                                pstmt.setBoolean(21, false);
                                pstmt.setString(22, AccDesc);
                                pstmt.setString(23, visitid);
                                pstmt.setString(24, user);
                                pstmt.setString(25, billNo);
                                pstmt.setString(26, "IP");
                                pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                                pstmt.setString(28, visitid);
                                pstmt.executeUpdate();

                                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                pstmt2.setObject(2, mainAcc);
                                pstmt2.setString(1, glAcc);
                                pstmt2.setObject(3, jTable1.getValueAt(i, 0).toString());
                                pstmt2.setObject(4, jTable1.getValueAt(i, 1).toString());
                                pstmt2.setString(5, "");
                                pstmt2.setString(6, "");
                                pstmt2.setString(7, "");
                                pstmt2.setString(8, "IP");
                                pstmt2.setString(9, "");
                                pstmt2.setString(10, payMode);
                                pstmt2.setString(11, "");
                                pstmt2.setString(12, "");
                                pstmt2.setString(13, "");
                                pstmt2.setString(14, jTable1.getValueAt(i, 3).toString());
                                pstmt2.setString(15, "Billing");
                                pstmt2.setDouble(16, 0.00);
                                pstmt2.setDouble(17, java.lang.Double.valueOf(jTable1.getValueAt(i, 5).toString()));
                                pstmt2.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(datePicker1.getDate()));
                                pstmt2.setString(19, transNo);
                                pstmt2.setBoolean(20, false);
                                pstmt2.setBoolean(21, false);
                                pstmt2.setBoolean(22, false);
                                pstmt2.setString(23, user);
                                pstmt2.executeUpdate();

                                System.out.println("Doing for another body [" + visitid + "]");

                            }
                        }
                    }
                }

                //  }
                connectDB.commit();
                connectDB.setAutoCommit(true);

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                try {
                    connectDB.rollback(registerSavePoint);
                } catch (java.sql.SQLException sql) {
                    //  javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                sq.printStackTrace();
                System.out.println(sq.getMessage());
                // javax.swing.JOptionPane.showMessageDialog(this, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                // //System.exit(1);
            }

        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        }
    }
}
