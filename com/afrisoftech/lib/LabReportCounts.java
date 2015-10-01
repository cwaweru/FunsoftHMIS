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
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class LabReportCounts {

    public static int getParameterCount(java.sql.Connection connectDB, String parameterName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
  //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountRange(java.sql.Connection connectDB, String parameterName, float thresholdLow, float thresholdHigh, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE parameter ilike ? AND out_come  date BETWEEN ? AND ? GROUP BY 2,3");

            pstmt.setString(1, parameterName);
            pstmt.setDouble(2, thresholdLow);
            pstmt.setDouble(3, thresholdHigh);
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(5, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPercentOver(java.sql.Connection connectDB, String parameterName, String resultType, float threshold, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE parameter ilike ? AND out_come > ? date BETWEEN ? AND ? GROUP BY 2,3");

            pstmt.setString(1, parameterName);
            pstmt.setDouble(2, threshold);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPercentBelow(java.sql.Connection connectDB, String parameterName, String resultType, float threshold, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND out_come < ?  date BETWEEN ? AND ? ");

            pstmt.setString(1, parameterName);
            pstmt.setDouble(2, threshold);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPositive(java.sql.Connection connectDB, String parameterName, String resultType, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND out_come ilike ? AND  date BETWEEN ? AND ?");

            pstmt.setString(1, parameterName);
            pstmt.setString(2, resultType);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
}
