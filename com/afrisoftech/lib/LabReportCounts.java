/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.sql.SQLException;
//

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class LabReportCounts {

    public static int getTestCount(java.sql.Connection connectDB, String testName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE (typeof_test ilike '%" + testName + "%') AND date BETWEEN ? AND ? GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
    public static int getParameterCount(java.sql.Connection connectDB, String parameterName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            String condition = "";
            String[] splitName = parameterName.split("--");
            for(int i = 0; i<splitName.length; i++){
                if(i == 0)
                    condition = condition + " parameter ilike '%" + splitName[i] + "%'  OR typeof_test ilike '%" + splitName[i] + "%' ";
                else
                    condition = condition + " OR parameter ilike '%" + splitName[i] + "%'  OR typeof_test ilike '%" + splitName[i] + "%' ";
            }
      
            
            
            //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE ("+condition+") AND date BETWEEN ? AND ? GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
       public static int getParameterCount(java.sql.Connection connectDB, String parameterName, String typeofTest, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE parameter ilike '%" + parameterName + "%'  AND typeof_test ilike '%" + typeofTest + "%' AND date BETWEEN ? AND ? GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
       
       public static int getParameterCountExtByAge(java.sql.Connection connectDB, String parameterName, String parameterNameExt, java.util.Date beginDate, java.util.Date endDate, int lowerAge, int upperAge) {

        int parameterCount = 0;

        try {
            //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE (parameter ilike '%" + parameterName + "%'  or typeof_test ilike '%" + parameterName + "%' OR parameter ilike '%" + parameterNameExt + "%'  or typeof_test ilike '%" + parameterNameExt + "%') AND date BETWEEN ? AND ? AND age >= "+lowerAge+" AND age < "+upperAge+" GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
        public static int getParameterCountExt(java.sql.Connection connectDB, String parameterName, String parameterNameExt, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            //          java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%'  or typeof_test ilike '%"+parameterName+"%') AND date BETWEEN ? AND ? ");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) from (select typeof_test, date, patient_no FROM hp_lab_results WHERE (parameter ilike '%" + parameterName + "%'  or typeof_test ilike '%" + parameterName + "%' OR parameter ilike '%" + parameterNameExt + "%'  or typeof_test ilike '%" + parameterNameExt + "%') AND date BETWEEN ? AND ? GROUP BY 1,2,3) as foo");
//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
        
        
        public static int getParameterResultCountHigh(java.sql.Connection connectDB, String parameterName,  java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            
            String condition = "";
            String[] splitName = parameterName.split("--");
            for(int i = 0; i<splitName.length; i++){
                if(i == 0)
                    condition = condition + " parameter ilike '%" + splitName[i] + "%'  OR typeof_test ilike '%" + splitName[i] + "%' ";
                else
                    condition = condition + " OR parameter ilike '%" + splitName[i] + "%'  OR typeof_test ilike '%" + splitName[i] + "%' ";
            }
            //java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE ("+condition+") AND result > upper_limit  AND  date BETWEEN ? AND ? GROUP BY 2,3");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter)  FROM hp_lab_results WHERE ("+condition+") AND result > upper_limit  AND  date BETWEEN ? AND ?  ");
   
//            pstmt.setString(1, parameterName);
//            pstmt.setDouble(2, thresholdLow);
//            pstmt.setDouble(3, thresholdHigh);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
        public static int getParameterResultCountLow(java.sql.Connection connectDB, String parameterName,  java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            
            String condition = "";
            String[] splitName = parameterName.split("--");
            for(int i = 0; i<splitName.length; i++){
                if(i == 0)
                    condition = condition + " parameter ilike '%" + splitName[i] + "%'  OR typeof_test ilike '%" + splitName[i] + "%' ";
                else
                    condition = condition + " OR parameter ilike '%" + splitName[i] + "%'  OR typeof_test ilike '%" + splitName[i] + "%' ";
            }
            //java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE ("+condition+") AND  result < lower_limit AND  date BETWEEN ? AND ? GROUP BY 2,3");
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter)  FROM hp_lab_results WHERE ("+condition+") AND  result < lower_limit AND  date BETWEEN ? AND ?  ");

//            pstmt.setString(1, parameterName);
//            pstmt.setDouble(2, thresholdLow);
//            pstmt.setDouble(3, thresholdHigh);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountOutsideRange(java.sql.Connection connectDB, String parameterName, double thresholdLow, double thresholdHigh, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%' OR typeof_test ilike '%"+parameterName+"%') AND result > upper_limit AND result < lower_limit AND  date BETWEEN ? AND ? GROUP BY 2,3");

//            pstmt.setString(1, parameterName);
//            pstmt.setDouble(2, thresholdLow);
//            pstmt.setDouble(3, thresholdHigh);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
    
    public static int getParameterResultCountOutsideRange(java.sql.Connection connectDB, String parameterName, String parameterGroup, double thresholdLow, double thresholdHigh, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter), date, patient_no FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%' AND typeof_test ilike '%"+parameterGroup+"%') AND result > upper_limit AND result < lower_limit AND  date BETWEEN ? AND ? GROUP BY 2,3");

//            pstmt.setString(1, parameterName);
//            pstmt.setDouble(2, thresholdLow);
//            pstmt.setDouble(3, thresholdHigh);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getParameterResultCountPositive(java.sql.Connection connectDB, String parameterName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%' OR typeof_test ilike '%"+parameterName+"%') AND (general_result = true OR out_come ilike 'positive' OR out_come ILIKE '%+%' OR out_come ilike 'true' OR  out_come ilike 'pos%') AND  date BETWEEN ? AND ?");

//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
    public static int getParameterResultCountPositive(java.sql.Connection connectDB, String parameterName, String typeofTest, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike '%"+parameterName+"%' AND typeof_test ilike '%"+typeofTest+"%' AND (general_result = true OR out_come ilike 'positive' OR out_come ILIKE '%+%' OR out_come ilike 'true' OR  out_come ilike 'pos%' OR  out_come ilike 'seen') AND  date BETWEEN ? AND ?");

//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
    public static int getParameterResultCountPositivePus(java.sql.Connection connectDB, String parameterName, String typeofTest, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE parameter ilike '%"+parameterName+"%' AND typeof_test ilike '%"+typeofTest+"%' AND (general_result = true OR out_come ilike 'positive' OR out_come ILIKE '%+%' OR out_come ilike 'true' OR  out_come ilike 'pos%' OR  out_come ilike 'seen' OR  out_come ilike '%5-10%' OR  out_come ilike '%10-20%' ) AND  date BETWEEN ? AND ?");

//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
    public static int getParameterResultCountPositiveExtByAge(java.sql.Connection connectDB, String parameterName, String parameterNameExt, java.util.Date beginDate, java.util.Date endDate , int lowerAge, int upperAge) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%' OR typeof_test ilike '%"+parameterName+"%' OR typeof_test ilike '%"+parameterNameExt+"%' OR parameter ilike '%"+parameterNameExt+"%') AND (general_result = true OR out_come ilike 'positive' OR out_come ilike 'pos%' OR out_come ILIKE '%+%' OR  out_come ~ '^-?\\d*\\.?\\d+$') AND  date BETWEEN ? AND ? AND age >= "+lowerAge+" AND age < "+upperAge+"");

//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
    
        public static int getParameterResultCountPositiveExt(java.sql.Connection connectDB, String parameterName, String parameterNameExt, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE (parameter ilike '%"+parameterName+"%' OR typeof_test ilike '%"+parameterName+"%' OR typeof_test ilike '%"+parameterNameExt+"%' OR parameter ilike '%"+parameterNameExt+"%') AND (general_result = true OR out_come ilike 'positive' OR out_come ilike 'pos%' OR out_come ILIKE '%+%') AND  date BETWEEN ? AND ?");

//            pstmt.setString(1, parameterName);
//            pstmt.setString(2, parameterName);
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }

    public static int getSpecimenPositiveCount(java.sql.Connection connectDB, String specimenName, java.util.Date beginDate, java.util.Date endDate) {

        int parameterCount = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(parameter) FROM hp_lab_results WHERE type_of_specimen ilike ? AND (general_result = true or out_come ilike 'pos%') AND  date BETWEEN ? AND ?");

            pstmt.setString(1, specimenName);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                parameterCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
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
                        ex.printStackTrace();             //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return parameterCount;

    }
}
