/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class FPServiceIndicators {

    public static String getIndicatorStatus(java.sql.Connection connectDB, String indicatorCode, String patientNo, java.util.Date beginDate, java.util.Date endDate) {

        String indicatorStatus = null;

        int count = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(service_given) FROM rh.fp_services_register WHERE patient_no = ? AND service_given = ? AND service_date BETWEEN ? and ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                count = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        if (count == 1) {
            return "1";
        } else if(count > 1) {
            return "2";
        } else {
            return "";
        }
    }

    public static String getDosageGiven(java.sql.Connection connectDB, String indicatorCode, String patientNo, java.util.Date beginDate, java.util.Date endDate) {

        String dosageGiven = null;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT service_dosage FROM rh.fp_services_register WHERE patient_no = ? AND service_given = ? AND service_date BETWEEN ? and ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                dosageGiven = rset.getString(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return dosageGiven;
    }
    
    
    public static int getQuantityDispensed(java.sql.Connection connectDB, String indicatorCode, String patientNo, java.util.Date beginDate, java.util.Date endDate) {

        int dispensedQty = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT dispensed_qty FROM rh.fp_services_register WHERE patient_no = ? AND service_given = ? AND service_date BETWEEN ? and ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                dispensedQty = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            Exceptions.printStackTrace(ex);
        }
        return dispensedQty;
    }
    
}
