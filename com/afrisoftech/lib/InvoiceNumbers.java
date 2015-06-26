/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 *
 */
public class InvoiceNumbers {

    static String invoiceNo = null;
    private static String invoiceInitial;
    public static boolean invoiceType;

    public static String getInvoiceNumber() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT ip_initials, op_initials FROM invoice_numbering");

            java.sql.ResultSet rstt = pstmt.executeQuery();

            while (rstt.next()) {
                if (invoiceType) {
                    invoiceInitial = rstt.getString(1);
                } else {
                    invoiceInitial = rstt.getString(2);
                }

            }

            java.sql.Statement pss1 = com.afrisoftech.hospital.HospitalMain.connectDB.createStatement();

            java.sql.ResultSet rss1 = pss1.executeQuery("select '" + invoiceInitial + "'||(nextval('ipinvoice_no_seq'))");
            while (rss1.next()) {
                invoiceNo = rss1.getObject(1).toString();
            }
        } catch (java.sql.SQLException sqEx) {
            sqEx.printStackTrace();
        }
        return invoiceNo;
    }
        public static String getFarewellInvoiceNumber() {
        try {
            java.sql.PreparedStatement pstmt = com.afrisoftech.hospital.HospitalMain.connectDB.prepareStatement("SELECT ip_initials, op_initials FROM invoice_numbering");

            java.sql.ResultSet rstt = pstmt.executeQuery();

            while (rstt.next()) {
                if (invoiceType) {
                    invoiceInitial = rstt.getString(1);
                } else {
                    invoiceInitial = rstt.getString(2);
                }

            }

            java.sql.Statement pss1 = com.afrisoftech.hospital.HospitalMain.connectDB.createStatement();

            java.sql.ResultSet rss1 = pss1.executeQuery("select 'FW'||(nextval('ipinvoice_no_seq'))");
            while (rss1.next()) {
                invoiceNo = rss1.getObject(1).toString();
            }
        } catch (java.sql.SQLException sqEx) {
            sqEx.printStackTrace();
        }
        return invoiceNo;
    }
}
