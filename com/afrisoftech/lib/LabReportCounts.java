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
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class LabReportCounts {

    public static int getParameterCount(java.sql.Connection connectDB, String parameterName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE (parameter ilike '%" + parameterName + "%'  or typeof_test ilike '%" + parameterName + "%') AND date BETWEEN ? AND ? GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountRange(java.sql.Connection connectDB, String parameterName, float thresholdLow, float thresholdHigh, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE parameter ilike ? AND result > ? AND result < ? AND  date BETWEEN ? AND ? GROUP BY 2,3");

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
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPercentOver(java.sql.Connection connectDB, String parameterName, String resultType, float threshold, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE parameter ilike ? AND result > ? AND date BETWEEN ? AND ? GROUP BY 2,3");

            pstmt.setString(1, parameterName);
            pstmt.setDouble(2, threshold);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPercentBelow(java.sql.Connection connectDB, String parameterName, String resultType, float threshold, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND result < ? AND  date BETWEEN ? AND ? ");

            pstmt.setString(1, parameterName);
            pstmt.setDouble(2, threshold);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPositive(java.sql.Connection connectDB, String parameterName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND general_result = true AND  date BETWEEN ? AND ?");

            pstmt.setString(1, parameterName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterIsolate(java.sql.Connection connectDB, String parameterName, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND type_of_specimen ilike ? AND  date BETWEEN ? AND ?");

            pstmt.setString(1, parameterName);
            pstmt.setString(2, specimenName);
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(4, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getSpecimenCount(java.sql.Connection connectDB, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE type_of_specimen ilike ? AND  date BETWEEN ? AND ?");

            pstmt.setString(1, specimenName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getSpecimenCultureCount(java.sql.Connection connectDB, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE type_of_specimen ilike ? AND culture_process = true AND  date BETWEEN ? AND ?");

            pstmt.setString(1, specimenName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getSpecimenPositiveCount(java.sql.Connection connectDB, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE type_of_specimen ilike ? AND general_result = true AND  date BETWEEN ? AND ?");

            pstmt.setString(1, specimenName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getReferralCount(java.sql.Connection connectDB, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE referral_path ilike ? AND  date BETWEEN ? AND ?");

            pstmt.setString(1, specimenName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getReferralResultsCount(java.sql.Connection connectDB, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE referral_path ilike ? AND referral_result_received = true AND  date BETWEEN ? AND ?");

            pstmt.setString(1, specimenName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
        public static int getCancerInfectiveCount(java.sql.Connection connectDB, String parameter, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND cancer_infective_status = true AND  date BETWEEN ? AND ?");

            pstmt.setString(1, parameter);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
       public static int getCancerNonInfectiveBenignCount(java.sql.Connection connectDB, String parameter, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND cancer_infective_status = false AND cancer_non_infective_status ilike 'benign' AND date BETWEEN ? AND ?");

            pstmt.setString(1, parameter);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
        public static int getCancerNonInfectiveMalignantCount(java.sql.Connection connectDB, String parameter, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike ? AND cancer_non_infective_status ilike 'malignant' AND date BETWEEN ? AND ?");

            pstmt.setString(1, parameter);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
}
