/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
//import org.openide.util.Exceptions;

/**
 *
 * @author Charles
 */
public class CWCIndicators {

    public static String getIndicatorStatus(java.sql.Connection connectDB, String indicatorCode, String patientNo) {

        String indicatorStatus = null;

        int count = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(cwc_indicator) FROM rh.child_health_follow_up WHERE patient_no = ? AND cwc_indicator = ?");

            pstmt.setString(1, patientNo);

            pstmt.setString(2, indicatorCode);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                count = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }
        if (count < 1) {
            return "N";
        } else {
            return "Y";
        }
    }

    public static int getCWCIndicatorCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String cwcIndicator, int lowerAgeLimit, int upperAgeLimit) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.child_health_follow_up WHERE service_date BETWEEN ? and ? AND cwc_indicator ilike ? AND child_age >= ? AND child_age <= ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, cwcIndicator);

            pstmt.setInt(4, lowerAgeLimit);

            pstmt.setInt(5, upperAgeLimit);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }

    public static int getCWCIndicatorCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String cwcIndicator, int lowerAgeLimit, int upperAgeLimit, String gender) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.child_health_follow_up WHERE service_date BETWEEN ? and ? AND cwc_indicator ilike ? AND child_age >= ? AND child_age <= ? AND gender ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setString(3, cwcIndicator);

            pstmt.setInt(4, lowerAgeLimit);

            pstmt.setInt(5, upperAgeLimit);
            
            pstmt.setString(6, gender);

            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }
        public static int getCWCIndicatorNewVisitCount(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, int lowerAgeLimit, int upperAgeLimit, String gender) {

        int visitCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM rh.child_health_follow_up WHERE service_date BETWEEN ? and ? AND revisit ilike 'N' AND child_age >= ? AND child_age <= ? AND gender ilike ?");

            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));

            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));

            pstmt.setInt(3, lowerAgeLimit);

            pstmt.setInt(4, upperAgeLimit);
            
            pstmt.setString(5, gender);
            
            java.sql.ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                visitCount = rset.getInt(1);

            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
        }
        return visitCount;
    }
}
