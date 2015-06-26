/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.accounting;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Charles Waweru <<cwaweru@systempartners.biz>> 2014-May-11
 * @17:01
 */
public class ClientBill {

    private boolean DirectRev;
    private String transNo;
    private String clientType = null;
    private String paymentMode = null;
    private String schemeName;
    private String schemeAccountNumber;
    private String payerName;
    private Object revenueDepartment;
    private String schemeMemberNumber;
    private String billTotal;
    private java.sql.Connection connectDB;
    //  private javax.swing.JTextField patientNumberTxt = null;
    //  private javax.swing.JTextField patientNumberTxt = null;
    //  private javax.swing.JTextField patientNumberTxt = null;

    private void saveBillData(java.sql.Connection connDB, String patientNumber, String patientName, java.util.Date billingDate, String clientTypes, String payMode, String consultantInCharge, String packageName, String deptName) {

        connectDB = connDB;

        clientType = clientTypes;

        paymentMode = payMode;

        revenueDepartment = deptName;

        com.afrisoftech.lib.DBObject dbObject = null;

        javax.swing.JTable discreetServicesTbl = getDiscreetServicesTable(packageName, deptName);

        billTotal = String.valueOf(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(discreetServicesTbl, 3));

        if (discreetServicesTbl.isEditing()) {
            discreetServicesTbl.getCellEditor().stopCellEditing();
        }
        if (patientName.equalsIgnoreCase("")) {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "You cannot save without the name", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {

            java.sql.Savepoint registerSavePoint = null;
            try {
                try {
                    connectDB.setAutoCommit(false);
                    registerSavePoint = connectDB.setSavepoint("registration");
                } catch (java.sql.SQLException ex) {
                    ex.printStackTrace();
                }


                java.util.Calendar calendar = java.util.Calendar.getInstance();

                long dateNow = calendar.getTimeInMillis();

                java.sql.Date datenowSql1 = new java.sql.Date(dateNow);

                System.out.println(datenowSql1.toString());

                java.sql.Date datenowSql = new java.sql.Date(dateNow);

                System.out.println("This date is not printing " + datenowSql.toString());

                String showDate = null;

                long receiptDate = billingDate.getTime();

                java.sql.Date billDate = new java.sql.Date(receiptDate);

                //receiptDate = billingDate;

                System.out.println("This date is todays date " + billDate);

                String billNo = null;
                transNo = null;
                payMode = null;
                String patientAcc = null;
                String cardNo = null;
                String AccDesc = null;
                String scheme = null;
                String cardName = null;
                String isurer = null;
                java.sql.Date expDate = null;
                String staffNo = null;
                String glAcc = null;
                String user = null;
                String accDesc1 = null;
                String glAcc1 = null;
                String chbox = null;
                String gacc = null;
                String item = null;
                int itemInt = 0;
                int pack = 0;
                double price = 0.00;
                String Store = null;
                String units = null;

                double qty = 0.00;
                String glCode = null;
                String actCode = null;
                double amount = 0.00;
                double sellPrice = 0.00;
                java.util.Date admDate = null;
                int visitid = 0;
                String actNames1 = null;
                String actCode1 = null;
                String invoiceNo = null;
                boolean smartcard = false;
                String iTem = null;
                String gcode = null;
                double quant = 0.00;
                double pRice = 0.00;
                double tTotal = 0.00;

                if (clientType == "OP") {
                    chbox = "OP";
                } else {
                    if (clientType == "WI") {
                        chbox = "WI";
                    } else {
                        if (clientType == "IP") {
                            chbox = "IP";
                        }
                    }
                }
                java.sql.Statement ps = connectDB.createStatement();
                java.sql.ResultSet rst = ps.executeQuery("select nextval('transaction_no_seq')");
                while (rst.next()) {
                    rst.getObject(1).toString();

                    transNo = rst.getObject(1).toString();
                }
                if (clientType == "OP") {
                    java.sql.Statement stmt1cz3 = connectDB.createStatement();
                    java.sql.ResultSet rset1cz3 = stmt1cz3.executeQuery("SELECT account_no,payer_name,usesmartcard FROM ac_schemes WHERE account_no  = '" + schemeAccountNumber + "'");
                    while (rset1cz3.next()) {
                        smartcard = rset1cz3.getBoolean(3);
                    }
                    if (smartcard) {
                        biz.systempartners.claims.Claim claimInstance = new biz.systempartners.claims.Claim();

                        claimInstance.createXMLDoc(discreetServicesTbl, connectDB);
                    }
                } else {
                    //this for inpatient;
                }
                java.sql.Statement stm121 = connectDB.createStatement();
                java.sql.Statement stm1211 = connectDB.createStatement();
                java.sql.Statement stm122 = connectDB.createStatement();
                java.sql.Statement stm122x = connectDB.createStatement();
                java.sql.Statement stm1 = connectDB.createStatement();

                java.sql.Statement stm121x = connectDB.createStatement();

                java.sql.ResultSet rse121x = stm121x.executeQuery("select direct_rev from pb_patient_names");
                while (rse121x.next()) {

                    DirectRev = rse121x.getBoolean(1);

                }

                java.sql.Statement stm12 = connectDB.createStatement();

                java.sql.ResultSet rse12 = stm12.executeQuery("select code,activity from pb_activity where activity_category ='PR'");
                while (rse12.next()) {

                    patientAcc = rse12.getObject(1).toString();
                    AccDesc = rse12.getObject(2).toString();
                }

                java.sql.Statement pss11 = connectDB.createStatement();
                java.sql.ResultSet rsts1 = pss11.executeQuery("select code,activity from pb_activity where activity_category ='DA'");
                while (rsts1.next()) {
                    actCode1 = rsts1.getObject(1).toString();
                    actNames1 = rsts1.getObject(2).toString();
                }
                java.sql.Statement ps11 = connectDB.createStatement();
                java.sql.ResultSet rst11 = ps11.executeQuery("select nextval('billing_no_seq'),current_user");
                while (rst11.next()) {
                    rst11.getObject(1).toString();

                    billNo = rst11.getObject(1).toString();
                    user = rst11.getObject(2).toString();
                }

                if (clientType == "OP") {
                    java.sql.Statement stm1q = connectDB.createStatement();
                    java.sql.ResultSet rse1 = stm1q.executeQuery("select pay_mode,account_no,description,payer,payer,expiry_date,account_no,member_name from hp_patient_register where patient_no ='" + patientNumber + "'");


                    while (rse1.next()) {

                        payMode = dbObject.getDBObject(rse1.getObject(1), "-");
                        cardNo = dbObject.getDBObject(rse1.getObject(2), "-");
                        scheme = dbObject.getDBObject(rse1.getObject(3), "-");
                        schemeName = dbObject.getDBObject(rse1.getObject(3), "-");
                        cardName = dbObject.getDBObject(rse1.getObject(4), "-");
                        isurer = dbObject.getDBObject(rse1.getObject(5), "-");
                        payerName = dbObject.getDBObject(rse1.getObject(5), "-");
                        expDate = rse1.getDate(6);
                        staffNo = dbObject.getDBObject(rse1.getObject(7), "-");
                        schemeMemberNumber = dbObject.getDBObject(rse1.getObject(7), "-");

                        java.sql.PreparedStatement pstmtSA = connectDB.prepareStatement("SELECT account_no FROM ac_schemes WHERE scheme_name = ? ");
                        pstmtSA.setString(1, schemeName);
                        java.sql.ResultSet rsetSA = pstmtSA.executeQuery();
                        while (rsetSA.next()) {
                            schemeAccountNumber = rsetSA.getString(1);
                        }
                    }
                } else {
                    if (clientType == "WK") {
                        patientNumber = "WK" + transNo;
                    }

                }
                if (clientType == "OP" || clientType == "WK") {
                    if (payMode.equalsIgnoreCase("Scheme")) {
                        if (clientType == "WK") {

                            java.sql.Statement pss1 = connectDB.createStatement();
                            java.sql.ResultSet rss1 = pss1.executeQuery("select 'O'||(nextval('pinvoice_no_seq'))");
                            while (rss1.next()) {
                                invoiceNo = rss1.getObject(1).toString();
                            }
                        }
                    }
                    for (int i = 0; i < discreetServicesTbl.getRowCount(); i++) {
                        if (discreetServicesTbl.getValueAt(i, 0) != null) {
                            java.sql.Statement stm121q = connectDB.createStatement();
                            java.sql.ResultSet rse121 = stm121q.executeQuery("select activity from pb_activity where code ='" + discreetServicesTbl.getValueAt(i, 4).toString() + "'");

                            while (rse121.next()) {

                                glAcc = rse121.getObject(1).toString();
                            }
                            if (payMode.equalsIgnoreCase("Scheme")) {
                                if (clientType == "WK") {
                                    java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?))");
                                    pstmt2.setString(1, patientNumber);
                                    pstmt2.setString(2, patientName);
                                    pstmt2.setString(3, paymentMode);
                                    pstmt2.setString(4, clientType);
                                    pstmt2.setString(5, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt2.setDouble(6, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                    pstmt2.setDouble(7, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt2.setObject(8, discreetServicesTbl.getValueAt(i, 4).toString());
                                    pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",billingDate)));
                                    pstmt2.setBoolean(12, false);
                                    pstmt2.setString(10, transNo);
                                    pstmt2.setString(11, user);
                                    pstmt2.setString(13, glAcc);
                                    pstmt2.setInt(14, visitid);
                                    pstmt2.setString(15, consultantInCharge);
                                    pstmt2.executeUpdate();

                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, patientNumber);
                                    pstmt.setObject(2, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt.setString(3, clientType);
                                    pstmt.setString(4, schemeName);
                                    pstmt.setString(5, transNo);
                                    pstmt.setString(7, scheme);
                                    pstmt.setString(6, cardNo);
                                    pstmt.setString(8, cardName);
                                    pstmt.setString(9, isurer);
                                    pstmt.setDate(10, null);//java.sql.Date.valueOf(expDate.toString()));
                                    pstmt.setString(11, "");
                                    pstmt.setDouble(12, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt.setDouble(13, 0.00);
                                    pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                    pstmt.setObject(15, patientAcc);
                                    pstmt.setString(16, glAcc);
                                    pstmt.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                    pstmt.setObject(18, staffNo);
                                    pstmt.setBoolean(19, false);
                                    pstmt.setString(20, "Billing");
                                    pstmt.setBoolean(21, false);
                                    pstmt.setString(22, AccDesc);
                                    pstmt.setString(23, invoiceNo);
                                    pstmt.setString(24, user);
                                    pstmt.setString(25, billNo);
                                    pstmt.setString(26, "OP");
                                    pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.util.Calendar.getInstance().getTime()));
                                    pstmt.setInt(28, visitid);
                                    pstmt.executeUpdate();


                                    java.sql.PreparedStatement pstmt24 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt24.setObject(1, discreetServicesTbl.getValueAt(i, 4).toString());
                                    pstmt24.setString(2, glAcc);
                                    pstmt24.setString(3, patientNumber);
                                    pstmt24.setString(4, patientName);
                                    pstmt24.setString(5, "");
                                    pstmt24.setString(6, cardNo);
                                    pstmt24.setString(7, cardName);
                                    pstmt24.setString(8, "OP");
                                    pstmt24.setString(9, consultantInCharge);
                                    pstmt24.setObject(10, paymentMode);
                                    pstmt24.setString(11, "");
                                    pstmt24.setString(12, "");
                                    pstmt24.setString(13, "");
                                    pstmt24.setString(14, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt24.setString(15, "Revenue");
                                    pstmt24.setDouble(16, 0.00);
                                    pstmt24.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt24.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                    pstmt24.setString(19, transNo);
                                    pstmt24.setBoolean(20, false);
                                    pstmt24.setBoolean(21, false);
                                    pstmt24.setBoolean(22, false);
                                    pstmt24.setString(23, user);
                                    pstmt24.executeUpdate();
                                } else {
                                    java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?))");

                                    pstmt2.setString(1, patientNumber);
                                    pstmt2.setString(10, transNo);
                                    pstmt2.setString(2, patientName);
                                    pstmt2.setString(3, paymentMode);
                                    pstmt2.setString(4, clientType);
                                    pstmt2.setString(5, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt2.setDouble(6, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                    pstmt2.setDouble(7, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt2.setObject(8, discreetServicesTbl.getValueAt(i, 4).toString());
                                    pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",billingDate)));
                                    pstmt2.setBoolean(12, false);
                                    pstmt2.setString(11, user);
                                    pstmt2.setString(13, glAcc);
                                    pstmt2.setInt(14, visitid);
                                    pstmt2.setString(15, consultantInCharge);
                                    pstmt2.executeUpdate();

                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO hp_patient_card VALUES(?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, patientNumber);
                                    pstmt.setObject(2, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt.setString(3, clientType);
                                    pstmt.setString(4, schemeName);
                                    pstmt.setString(5, transNo);
                                    pstmt.setString(7, scheme);
                                    pstmt.setString(6, cardNo);
                                    pstmt.setString(8, cardName);
                                    pstmt.setString(9, isurer);
                                    pstmt.setDate(10, null);//java.sql.Date.valueOf(expDate.toString()));
                                    pstmt.setString(11, "");
                                    pstmt.setDouble(12, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt.setDouble(13, 0.00);
                                    pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                    pstmt.setObject(15, patientAcc);
                                    pstmt.setString(16, glAcc);
                                    pstmt.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                    pstmt.setObject(18, staffNo);
                                    pstmt.setBoolean(19, false);
                                    pstmt.setString(20, "Billing");
                                    pstmt.setBoolean(21, false);
                                    pstmt.setString(22, AccDesc);
                                    pstmt.setInt(23, visitid);
                                    pstmt.setString(24, user);
                                    pstmt.setString(25, billNo);
                                    pstmt.setString(26, "OP");
                                    pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.util.Calendar.getInstance().getTime()));
                                    pstmt.setInt(28, visitid);
                                    pstmt.executeUpdate();

                                    java.sql.PreparedStatement pstmt24 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt24.setObject(1, discreetServicesTbl.getValueAt(i, 4).toString());
                                    pstmt24.setString(2, glAcc);
                                    pstmt24.setString(3, patientNumber);
                                    pstmt24.setString(4, patientName);
                                    pstmt24.setString(5, "");
                                    pstmt24.setString(6, cardNo);
                                    pstmt24.setString(7, cardName);
                                    pstmt24.setString(8, "OP");
                                    pstmt24.setString(9, consultantInCharge);
                                    pstmt24.setObject(10, paymentMode);
                                    pstmt24.setString(11, "");
                                    pstmt24.setString(12, "");
                                    pstmt24.setString(13, "");
                                    pstmt24.setString(14, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt24.setString(15, "Revenue");
                                    pstmt24.setDouble(16, 0.00);
                                    pstmt24.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt24.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                    pstmt24.setString(19, transNo);
                                    pstmt24.setBoolean(20, false);
                                    pstmt24.setBoolean(21, false);
                                    pstmt24.setBoolean(22, false);
                                    pstmt24.setString(23, user);
                                    pstmt24.executeUpdate();
                                }
                            } else {
                                if (DirectRev) {
                                    if (clientType == "WK") {
                                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?))");
                                        pstmt2.setString(1, patientNumber);
                                        pstmt2.setString(10, transNo);
                                        pstmt2.setString(2, patientName);
                                        pstmt2.setString(3, paymentMode);
                                        pstmt2.setString(4, clientType);
                                        pstmt2.setString(5, discreetServicesTbl.getValueAt(i, 0).toString());
                                        pstmt2.setDouble(6, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                        pstmt2.setDouble(7, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                        pstmt2.setObject(8, discreetServicesTbl.getValueAt(i, 4).toString());
                                        pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",billingDate)));
                                        pstmt2.setBoolean(12, false);
                                        pstmt2.setString(11, user);
                                        pstmt2.setString(13, glAcc);
                                        pstmt2.setInt(14, visitid);
                                        pstmt2.setString(15, consultantInCharge);
                                        pstmt2.executeUpdate();
                                    } else {
                                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?))");
                                        pstmt2.setString(1, patientNumber);
                                        pstmt2.setString(10, transNo);
                                        pstmt2.setString(2, patientName);
                                        pstmt2.setString(3, paymentMode);
                                        pstmt2.setString(4, clientType);
                                        pstmt2.setString(5, discreetServicesTbl.getValueAt(i, 0).toString());
                                        pstmt2.setDouble(6, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                        pstmt2.setDouble(7, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                        pstmt2.setObject(8, discreetServicesTbl.getValueAt(i, 4).toString());
                                        pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",billingDate)));
                                        pstmt2.setBoolean(12, false);
                                        pstmt2.setString(11, user);
                                        pstmt2.setString(13, glAcc);
                                        pstmt2.setInt(14, visitid);
                                        pstmt2.setString(15, consultantInCharge);
                                        pstmt2.executeUpdate();
                                    }
                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                    pstmt.setString(1, patientNumber);
                                    pstmt.setObject(2, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt.setString(3, clientType);
                                    pstmt.setString(4, schemeName);
                                    pstmt.setString(5, transNo);
                                    pstmt.setString(7, scheme);
                                    pstmt.setString(6, cardNo);
                                    pstmt.setString(8, cardName);
                                    pstmt.setString(9, isurer);
                                    pstmt.setDate(10, null);//java.sql.Date.valueOf(expDate.toString()));
                                    pstmt.setString(11, "");
                                    pstmt.setDouble(12, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt.setDouble(13, 0.00);
                                    pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                    pstmt.setObject(15, patientAcc);
                                    pstmt.setString(16, glAcc);
                                    pstmt.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                    pstmt.setObject(18, staffNo);
                                    pstmt.setBoolean(19, false);
                                    pstmt.setString(20, "Billing");
                                    pstmt.setBoolean(21, false);
                                    pstmt.setString(22, AccDesc);
                                    pstmt.setInt(23, visitid);
                                    pstmt.setString(24, user);
                                    pstmt.setString(25, billNo);
                                    pstmt.setString(26, "OP");
                                    pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.util.Calendar.getInstance().getTime()));
                                    pstmt.setInt(28, visitid);
                                    pstmt.executeUpdate();




                                    java.sql.PreparedStatement pstmt24 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                    pstmt24.setObject(1, discreetServicesTbl.getValueAt(i, 4).toString());
                                    pstmt24.setString(2, glAcc);
                                    pstmt24.setString(3, patientNumber);
                                    pstmt24.setString(4, patientName);
                                    pstmt24.setString(5, "");
                                    pstmt24.setString(6, cardNo);
                                    pstmt24.setString(7, cardName);
                                    pstmt24.setString(8, "OP");
                                    pstmt24.setString(9, consultantInCharge);
                                    pstmt24.setObject(10, paymentMode);
                                    pstmt24.setString(11, "");
                                    pstmt24.setString(12, "");
                                    pstmt24.setString(13, "");
                                    pstmt24.setString(14, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt24.setString(15, "Revenue");
                                    pstmt24.setDouble(16, 0.00);
                                    pstmt24.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt24.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                    pstmt24.setString(19, transNo);
                                    pstmt24.setBoolean(20, false);
                                    pstmt24.setBoolean(21, false);
                                    pstmt24.setBoolean(22, false);
                                    pstmt24.setString(23, user);
                                    pstmt24.executeUpdate();

                                } else {
                                    java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into hp_patient_billing values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,trim(?))");
                                    pstmt2.setString(1, patientNumber);
                                    pstmt2.setString(10, transNo);
                                    pstmt2.setString(2, patientName);
                                    pstmt2.setString(3, paymentMode);
                                    pstmt2.setString(4, clientType);
                                    pstmt2.setString(5, discreetServicesTbl.getValueAt(i, 0).toString());
                                    pstmt2.setDouble(6, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                    pstmt2.setDouble(7, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                    pstmt2.setObject(8, discreetServicesTbl.getValueAt(i, 4).toString());
                                    pstmt2.setDate(9, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));//java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te",billingDate)));
                                    pstmt2.setBoolean(12, false);
                                    pstmt2.setString(11, user);
                                    pstmt2.setString(13, glAcc);
                                    pstmt2.setInt(14, visitid);
                                    pstmt2.setString(15, consultantInCharge);
                                    pstmt2.executeUpdate();
                                }
                            }
                        }
                    }

                    if (payMode.equalsIgnoreCase("Scheme")) {
                        if (clientType == "WK") {
                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("insert into ac_debtors values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt.setObject(1, actCode1);
                            pstmt.setString(4, patientNumber);
                            pstmt.setObject(3, patientName);
                            pstmt.setString(2, payerName);
                            pstmt.setString(5, schemeName);
                            pstmt.setString(6, "");
                            if (this.schemeAccountNumber.equals(null) || this.schemeAccountNumber.equals("-")) {
                                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Account NO. MISSING !!!", "Confirmation Message!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                pstmt.setString(7, schemeAccountNumber);
                            }
                            pstmt.setString(8, "");
                            pstmt.setString(9, "");
                            pstmt.setString(10, "");
                            pstmt.setString(11, transNo);
                            pstmt.setString(12, "Raise Invoice");
                            pstmt.setDouble(13, java.lang.Double.valueOf(billTotal));
                            pstmt.setDouble(14, 0.00);
                            pstmt.setDate(15, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                            pstmt.setObject(16, invoiceNo);
                            pstmt.setString(17, actNames1);
                            pstmt.setBoolean(18, false);
                            pstmt.setBoolean(19, false);
                            pstmt.setString(20, user);
                            pstmt.setString(21, "");
                            pstmt.setDouble(22, java.lang.Double.valueOf(billTotal));
                            pstmt.setBoolean(23, false);
                            pstmt.setDouble(24, 0.00);
                            pstmt.setObject(25, "");
                            pstmt.setObject(26, null);
                            pstmt.setBoolean(27, false);
                            pstmt.setDouble(28, 0.00);
                            pstmt.setObject(29, null);
                            pstmt.setString(30, "");
                            pstmt.setBoolean(31, false);
                            pstmt.setDouble(32, 0.00);
                            pstmt.setObject(33, null);
                            pstmt.setString(34, "");
                            pstmt.setString(35, "");
                            pstmt.executeUpdate();



                            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into hp_patient_card values(?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                            pstmt1.setString(1, patientNumber);
                            pstmt1.setObject(2, "Invoice");
                            pstmt1.setString(3, "");
                            pstmt1.setString(4, "Scheme");
                            pstmt1.setString(5, transNo);
                            pstmt1.setString(7, schemeName);
                            pstmt1.setString(6, "");
                            pstmt1.setString(8, "");
                            pstmt1.setString(9, "");
                            pstmt1.setDate(10, null);
                            pstmt1.setString(11, "");
                            pstmt1.setDouble(13, java.lang.Double.valueOf(billTotal));
                            pstmt1.setDouble(12, 0.00);
                            pstmt1.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                            pstmt1.setObject(15, patientAcc);
                            pstmt1.setString(16, glAcc);
                            pstmt1.setDouble(17, 1);
                            pstmt1.setObject(18, "");
                            pstmt1.setBoolean(19, true);
                            pstmt1.setString(20, "Receipts");
                            pstmt1.setBoolean(21, true);
                            pstmt1.setString(22, AccDesc);
                            pstmt1.setString(23, invoiceNo);
                            pstmt1.setString(24, user);
                            pstmt1.executeUpdate();
                            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Insert Invoice No. " + invoiceNo + "", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            com.afrisoftech.reports.FinalInvoiceByinvPdf policy = new com.afrisoftech.reports.FinalInvoiceByinvPdf();
                            policy.FinalInvoiceByinvPdf(connectDB, invoiceNo, invoiceNo);
                        }
                    }



                } else {
                    if (clientType == "IP") {
                        String ward = null;
                        float amtPerDay = 0;
                        String revcodes = null;
                        String bedDescr = null;
                        double amtCharged = 0.00; //java.lang.Float.parseFloat(this.bedAmountTxt.getText());
                        java.sql.Statement stm12n = connectDB.createStatement();
                        java.sql.ResultSet rse12n = stm12n.executeQuery("select adm_date from hp_inpatient_register where patient_no ='" + patientNumber + "'");
                        while (rse12n.next()) {
                            admDate = rse12n.getDate(1);

                        }

                        java.sql.Statement pss111 = connectDB.createStatement();
                        java.sql.ResultSet rss111 = pss111.executeQuery("select ward,deposit,visit_id FROM hp_admission WHERE patient_no = '" + patientNumber + "' AND visit_id = '" + schemeMemberNumber + "'");
                        while (rss111.next()) {
                            ward = rss111.getString(1);
                            amtPerDay = rss111.getFloat(2);
                        }
                        System.out.println("Ward [" + ward + " AmountPerDay[" + amtPerDay + "]");
                        java.sql.Statement pss1111 = connectDB.createStatement();
                        java.sql.ResultSet rss1111 = pss1111.executeQuery("SELECT revcode FROM  hp_wards WHERE ward_name = '" + ward + "'");
                        while (rss1111.next()) {
                            revcodes = rss1111.getString(1);

                        }
                        System.out.println("Ward Rev Code[" + revcodes + "]");
                        java.sql.Statement stm121v = connectDB.createStatement();
                        java.sql.ResultSet rse121v = stm121v.executeQuery("select activity from pb_activity where code ='" + revcodes + "'");
                        while (rse121v.next()) {

                            glAcc = rse121v.getObject(1).toString();
                        }
                        java.sql.Statement stm121vv = connectDB.createStatement();
                        java.sql.ResultSet rse121vv = stm121vv.executeQuery("SELECT service_type FROM pb_operating_parameters WHERE gl_account ='" + revcodes + "' AND service_type ILIKE '%bed%'");// AND rate = '" + amtPerDay + "'");
                        while (rse121vv.next()) {

                            bedDescr = rse121vv.getObject(1).toString();
                        }
                        System.out.println("Bedsecr [" + bedDescr + "]");
                        System.out.println(admDate);

                        if (billingDate.before(admDate)) {
                            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Admission date is : " + admDate + " CHECK billing date", "Date Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        } else {

                            if (!(payMode.equals("Cash"))) {
                                java.sql.ResultSet rse1 = stm1.executeQuery("select pay_mode,account_no,description,payer,payer,expiry_date,account_no from hp_inpatient_register where patient_no ='" + patientNumber + "'");
                                while (rse1.next()) {
                                    cardNo = dbObject.getDBObject(rse1.getObject(2), "-");
                                    scheme = dbObject.getDBObject(rse1.getObject(3), "-");
                                    schemeName = dbObject.getDBObject(rse1.getObject(3), "-");
                                    cardName = dbObject.getDBObject(rse1.getObject(4), "-");
                                    payerName = dbObject.getDBObject(rse1.getObject(5), "-");
                                    isurer = dbObject.getDBObject(rse1.getObject(5), "-");
                                    expDate = rse1.getDate(6);
                                    staffNo = dbObject.getDBObject(rse1.getObject(7), "-");

                                    java.sql.PreparedStatement pstmtSA = connectDB.prepareStatement("SELECT account_no FROM ac_schemes WHERE scheme_name = ? ");
                                    pstmtSA.setString(1, schemeName);
                                    java.sql.ResultSet rsetSA = pstmtSA.executeQuery();
                                    while (rsetSA.next()) {
                                        schemeAccountNumber = rsetSA.getString(1);
                                    }
                                }

                                for (int i = 0; i < discreetServicesTbl.getRowCount(); i++) {
                                    if (discreetServicesTbl.getValueAt(i, 0) != null) {

                                        java.sql.ResultSet rse121 = stm121.executeQuery("select activity from pb_activity where code ='" + discreetServicesTbl.getValueAt(i, 4).toString() + "'");
                                        while (rse121.next()) {

                                            glAcc = rse121.getObject(1).toString();
                                        }
                                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO hp_patient_card values(?,?,?,?,?,?,?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)");
                                        pstmt.setString(1, patientNumber);
                                        pstmt.setObject(2, discreetServicesTbl.getValueAt(i, 0).toString());
                                        pstmt.setString(3, clientType);
                                        pstmt.setString(4, paymentMode);
                                        pstmt.setString(5, transNo);
                                        pstmt.setString(7, scheme);
                                        pstmt.setString(6, cardNo);
                                        pstmt.setString(8, cardName);
                                        pstmt.setString(9, isurer);
                                        pstmt.setDate(10, null);//java.sql.Date.valueOf(expDate.toString()));
                                        pstmt.setString(11, "");
                                        pstmt.setDouble(12, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                        pstmt.setDouble(13, 0.00);
                                        pstmt.setDate(14, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                        pstmt.setObject(15, patientAcc);
                                        pstmt.setString(16, glAcc);
                                        pstmt.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                                        pstmt.setObject(18, staffNo);
                                        pstmt.setBoolean(19, false);
                                        pstmt.setString(20, "Billing");
                                        pstmt.setBoolean(21, false);
                                        pstmt.setString(22, AccDesc);
                                        pstmt.setInt(23, visitid);
                                        pstmt.setString(24, user);
                                        pstmt.setString(25, billNo);
                                        pstmt.setString(26, "IP");
                                        pstmt.setTimestamp(27, new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));//com.afrisoftech.lib.SQLDateFormat.getSQLDate(java.util.Calendar.getInstance().getTime()));
                                        pstmt.setInt(28, visitid);
                                        pstmt.executeUpdate();


                                        java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                        pstmt2.setObject(1, discreetServicesTbl.getValueAt(i, 4).toString());
                                        pstmt2.setString(2, glAcc);
                                        pstmt2.setString(3, patientNumber);
                                        pstmt2.setString(4, patientName);
                                        pstmt2.setString(5, "");
                                        pstmt2.setString(6, cardNo);
                                        pstmt2.setString(7, cardName);
                                        pstmt2.setString(8, "IP");
                                        pstmt2.setString(9, consultantInCharge);
                                        pstmt2.setString(10, paymentMode);
                                        pstmt2.setString(11, "");
                                        pstmt2.setString(12, "");
                                        pstmt2.setString(13, "");
                                        pstmt2.setString(14, discreetServicesTbl.getValueAt(i, 0).toString());
                                        pstmt2.setString(15, "Revenue");
                                        pstmt2.setDouble(16, 0.00);
                                        pstmt2.setDouble(17, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                                        pstmt2.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                                        pstmt2.setString(19, transNo);
                                        pstmt2.setBoolean(20, false);
                                        pstmt2.setBoolean(21, false);
                                        pstmt2.setBoolean(22, false);
                                        pstmt2.setString(23, user);
                                        pstmt2.executeUpdate();


                                    }
                                }
                            }
                        }
                    }
                }


                for (int i = 0; i < discreetServicesTbl.getRowCount(); i++) {
                    if (discreetServicesTbl.getValueAt(i, 0) != null) {


                        java.sql.Statement pstBx = connectDB.createStatement();
                        java.sql.ResultSet rsBx = pstBx.executeQuery("select count(product) from stockprices where product = '" + discreetServicesTbl.getValueAt(i, 0) + "' and gl_code =  '" + discreetServicesTbl.getValueAt(i, 4) + "'");

                        while (rsBx.next()) {
                            itemInt = rsBx.getInt(1);
                        }

                        if (itemInt > 0) {
                            java.sql.Statement pstB = connectDB.createStatement();
                            java.sql.ResultSet rsB = pstB.executeQuery("select transfer_price,units,department from stockprices where product = '" + discreetServicesTbl.getValueAt(i, 0) + "' and gl_code =  '" + discreetServicesTbl.getValueAt(i, 4) + "'");

                            while (rsB.next()) {
                                price = rsB.getDouble(1);
                                units = rsB.getString(2);
                                Store = rsB.getString(3);
                            }


                            java.sql.Statement pst211 = connectDB.createStatement();
                            java.sql.ResultSet rs111 = pst211.executeQuery("select cost_of_sale,stock_code from pb_departments where income_account = '" + discreetServicesTbl.getValueAt(i, 4) + "'");
                            while (rs111.next()) {
                                glCode = rs111.getObject(1).toString();
                                actCode = rs111.getObject(2).toString();
                            }

                            qty = java.lang.Double.parseDouble(discreetServicesTbl.getValueAt(i, 1).toString());
                            sellPrice = java.lang.Double.parseDouble(discreetServicesTbl.getValueAt(i, 2).toString());
                            amount = price * qty;



                            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into hp_pharmacy values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt1.setString(1, patientNumber);
                            pstmt1.setString(2, patientName);
                            pstmt1.setDouble(3, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                            pstmt1.setDouble(4, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                            pstmt1.setObject(5, discreetServicesTbl.getValueAt(i, 0).toString());
                            pstmt1.setDouble(7, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 2).toString()));
                            pstmt1.setObject(8, discreetServicesTbl.getValueAt(i, 4).toString());
                            pstmt1.setBoolean(6, true);
                            pstmt1.setString(9, transNo);
                            pstmt1.setObject(10, consultantInCharge);
                            pstmt1.setBoolean(11, true);
                            pstmt1.setString(12, units);
                            pstmt1.setDate(13, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                            pstmt1.setObject(14, Store);
                            pstmt1.setString(15, chbox);
                            pstmt1.setString(16, paymentMode);
                            pstmt1.setObject(17, "");
                            pstmt1.setDouble(18, 0.00);
                            pstmt1.setString(19, user);
                            pstmt1.setObject(20, "");
                            pstmt1.executeUpdate();

                            java.sql.PreparedStatement pstmt1a = connectDB.prepareStatement("insert into st_stock_cardex values(?,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)");
                            pstmt1a.setString(1, null);
                            pstmt1a.setObject(3, discreetServicesTbl.getValueAt(i, 0));
                            pstmt1a.setString(2, Store);
                            pstmt1a.setDate(4, null);
                            pstmt1a.setDouble(5, amount);
                            pstmt1a.setDouble(6, 0.00);
                            pstmt1a.setObject(7, units);
                            pstmt1a.setObject(8, "");
                            pstmt1a.setString(9, null);
                            pstmt1a.setString(10, null);
                            pstmt1a.setDouble(11, 0.00);
                            pstmt1a.setDouble(12, 0.00);
                            pstmt1a.setDouble(13, 0.00);
                            pstmt1a.setString(14, patientName);
                            pstmt1a.setString(15, user);
                            pstmt1a.setString(16, "");
                            pstmt1a.setString(17, null);
                            pstmt1a.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                            pstmt1a.setString(19, Store);
                            pstmt1a.setString(20, "Issuing");
                            pstmt1a.setDouble(21, 0.00);
                            pstmt1a.setString(23, actCode);
                            pstmt1a.setDouble(22, 0.00);
                            pstmt1a.setString(24, "");
                            pstmt1a.setString(25, Store);
                            pstmt1a.setString(26, transNo);
                            pstmt1a.setDouble(27, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                            pstmt1a.setString(28, user);
                            pstmt1a.executeUpdate();


                            java.sql.PreparedStatement pstmt2a = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                            pstmt2a.setObject(1, glCode);
                            pstmt2a.setObject(2, revenueDepartment);
                            pstmt2a.setString(3, patientNumber);
                            pstmt2a.setString(4, patientName);
                            pstmt2a.setString(5, "");
                            pstmt2a.setString(6, "");
                            pstmt2a.setString(7, "");
                            pstmt2a.setString(8, "");
                            pstmt2a.setString(9, "");
                            pstmt2a.setObject(10, paymentMode);
                            pstmt2a.setString(11, "");
                            pstmt2a.setString(12, "");
                            pstmt2a.setString(13, "");
                            pstmt2a.setString(14, discreetServicesTbl.getValueAt(i, 0).toString());
                            pstmt2a.setString(15, "Issuing");
                            pstmt2a.setDouble(16, amount);
                            pstmt2a.setDouble(17, 0.00);
                            pstmt2a.setDate(18, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                            pstmt2a.setString(19, transNo);
                            pstmt2a.setBoolean(20, false);
                            pstmt2a.setBoolean(21, false);
                            pstmt2a.setBoolean(22, false);
                            pstmt2a.setString(23, user);
                            pstmt2a.executeUpdate();


                            java.sql.PreparedStatement pstmt33 = connectDB.prepareStatement("insert into st_sub_stores values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            pstmt33.setObject(1, Store);
                            pstmt33.setObject(2, discreetServicesTbl.getValueAt(i, 0).toString());
                            pstmt33.setDouble(3, 0.00);
                            pstmt33.setDouble(7, 0.00);
                            pstmt33.setDouble(5, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 2).toString()));
                            pstmt33.setDouble(6, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 3).toString()));
                            pstmt33.setString(8, transNo);
                            pstmt33.setDouble(4, java.lang.Double.valueOf(discreetServicesTbl.getValueAt(i, 1).toString()));
                            pstmt33.setObject(9, consultantInCharge);
                            pstmt33.setDate(10, com.afrisoftech.lib.SQLDateFormat.getSQLDate(billingDate));
                            pstmt33.setString(11, user);
                            pstmt33.setString(12, units);
                            pstmt33.setString(13, Store);
                            pstmt33.setString(14, patientNumber + " " + patientName);
                            pstmt33.executeUpdate();

                        }
                        //   System.out.println("How many packages test "+pack);

                        java.sql.Statement pstBx2 = connectDB.createStatement();
                        java.sql.ResultSet rsBx2 = pstBx2.executeQuery("select count(package) from packages where package = '" + discreetServicesTbl.getValueAt(i, 0) + "' and glcode =  '" + discreetServicesTbl.getValueAt(i, 4) + "'");

                        while (rsBx2.next()) {
                            pack = rsBx2.getInt(1);
                        }

                    }
                }
                for (int k = 0; k < discreetServicesTbl.getRowCount(); k++) {
                    for (int r = 0; r < discreetServicesTbl.getColumnCount(); r++) {
                        discreetServicesTbl.setValueAt(null, k, r);
                    }
                }

                connectDB.commit();
                connectDB.setAutoCommit(true);

                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Insert Successful.Bill No. " + transNo + "", "Confirmation Message", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                for (int k = 0; k < discreetServicesTbl.getRowCount(); k++) {
                    for (int r = 0; r < discreetServicesTbl.getColumnCount(); r++) {
                        discreetServicesTbl.setValueAt(null, k, r);
                    }
                }

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();
                try {
                    connectDB.rollback(registerSavePoint);
                } catch (java.sql.SQLException sql) {
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                System.out.println(sq.getMessage());
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    private JTable getDiscreetServicesTable(String packageName, String departmentName) {
        javax.swing.JTable servicesBillingTable = new javax.swing.JTable();
        servicesBillingTable.setModel(com.afrisoftech.dbadmin.TableModel.createTableVectors(connectDB, "SELECT DISTINCT item_service, qty, price, total, glcode WHERE package = '" + packageName + "' and department = '" + departmentName + "'"));
        return servicesBillingTable;
    }
}
