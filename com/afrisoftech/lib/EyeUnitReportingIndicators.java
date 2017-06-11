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
public class EyeUnitReportingIndicators {

    public static int getNoofPatientWithCondition(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, double visualAcuityLower, double visualAcuityHigher, String gender, int ageLower, int ageHigher, String conditionType, String generalVisialAcuity) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND visual_acuity_fraction BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age BETWEEN ? AND ? AND upper(patient_type) = upper(?) AND upper(general_visual_acuity) = upper(?)");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setDouble(4, visualAcuityLower);
            pstmt.setDouble(5, visualAcuityHigher);
            pstmt.setString(6, gender);
            pstmt.setInt(7, ageLower);
            pstmt.setInt(8, ageHigher);
            pstmt.setString(9, conditionType);
            pstmt.setString(10, generalVisialAcuity);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalPatientWithCondition(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?)");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(4, gender);
            pstmt.setString(5, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getNoofChildrenWithCondition(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, String gender, String conditionType, String visualAcuity) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age < 5 AND upper(patient_type) = upper(?) AND upper(general_visual_acuity) = upper(?) ");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(4, gender);
            pstmt.setString(5, conditionType);
            pstmt.setString(6, visualAcuity);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getNoofPatientOver16WithCondition(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, double visualAcuityLower, double visualAcuityHigher, String gender, String conditionType, String generalVisialAcuity) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND visual_acuity_fraction BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age >= 16 AND upper(patient_type) = upper(?) AND upper(general_visual_acuity) = upper(?)");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setDouble(4, visualAcuityLower);
            pstmt.setDouble(5, visualAcuityHigher);
            pstmt.setString(6, gender);
            pstmt.setString(7, conditionType);
            pstmt.setString(8, generalVisialAcuity);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getNoofPatientsWithVisualAcuity(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, double visualAcuityLower, double visualAcuityHigher, String gender, int ageLower, int ageHigher, String conditionType, String generalVisialAcuity) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND visual_acuity_fraction BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age BETWEEN ? AND ? AND upper(patient_type) = upper(?) AND upper(general_visual_acuity) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setDouble(3, visualAcuityLower);
            pstmt.setDouble(4, visualAcuityHigher);
            pstmt.setString(5, gender);
            pstmt.setInt(6, ageLower);
            pstmt.setInt(7, ageHigher);
            pstmt.setString(8, conditionType);
            pstmt.setString(9, generalVisialAcuity);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalPatientOver16WithVisualAcuity(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, double visualAcuityLower, double visualAcuityHigher, String gender, String conditionType, String generalVisialAcuity) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND visual_acuity_fraction BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age >= 16 AND upper(patient_type) = upper(?) AND upper(general_visual_acuity) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setDouble(3, visualAcuityLower);
            pstmt.setDouble(4, visualAcuityHigher);
            pstmt.setString(5, gender);
            pstmt.setString(6, conditionType);
            pstmt.setString(7, generalVisialAcuity);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getNoofChildrenWithVisualAcuity(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String gender, String conditionType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age < 5 AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, gender);
            pstmt.setString(4, conditionType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalChildrenWithVisualAcuity(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String gender, String conditionType, String visualAcuity) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND upper(patient_gender) = upper(?) AND patient_age < 5 AND upper(patient_type) = upper(?) AND upper(general_visual_acuity) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, gender);
            pstmt.setString(4, conditionType);
            pstmt.setString(5, visualAcuity);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalPatientsPerGender(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ?  AND upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, gender);
            pstmt.setString(4, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getnoofSurgeryPatientsUnder5(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND patient_age < 5 AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?)");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(4, gender);
            pstmt.setString(5, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalSurgeryPatientsUnder5(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND patient_age < 5 AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, gender);
            pstmt.setString(4, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getnoofSurgeryPatients16AndAbove(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND patient_age >= 16 AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?)");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(4, gender);
            pstmt.setString(5, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalSurgeryPatients16AndAbove(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND patient_age >= 16 AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, gender);
            pstmt.setString(4, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getnoofSurgeryPatientsAgesCohorts(java.sql.Connection connectDB, String eyeCondition, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType, int ageLower, int ageHigher) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE upper(eye_condition) = upper(?) AND input_date BETWEEN ? AND ? AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?) AND patient_age BETWEEN ? AND ?");
            pstmt.setString(1, eyeCondition);
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(3, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(4, gender);
            pstmt.setString(5, patientType);
            pstmt.setInt(6, ageLower);
            pstmt.setInt(7, ageHigher);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalSurgeryPatientsAgesCohorts(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String gender, String patientType, int ageLower, int ageHigher) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND  upper(patient_gender) = upper(?) AND upper(patient_type) = upper(?) AND patient_age BETWEEN ? AND ?");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, gender);
            pstmt.setString(4, patientType);
            pstmt.setInt(5, ageLower);
            pstmt.setInt(6, ageHigher);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalEyeWardCapacity(java.sql.Connection connectDB) {

        int noofBeds = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT SUM(max_beds) FROM hp_wards WHERE ward_name ilike '%eye%'");
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofBeds = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofBeds;

    }

    public static int getTotalEyeWardsDischarges(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM hp_admission WHERE ward ilike '%eye%' AND discharge_date::date BETWEEN ? AND ?");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalEyeWardsAdmissions(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM hp_admission WHERE ward ilike '%eye%' AND date_admitted::date BETWEEN ? AND ?");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalEyeClinicPatientsNew(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND  upper(visit_type) = upper('New') AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalEyeClinicPatientsRevisit(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND  upper(visit_type) = upper('Old') AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static int getTotalEyeClinicPatients(java.sql.Connection connectDB, java.util.Date beginDate, java.util.Date endDate, String patientType) {

        int noofPatients = 0;

        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT COUNT(*) FROM public.eye_reporting_conditions WHERE input_date BETWEEN ? AND ? AND upper(patient_type) = upper(?)");
            pstmt.setDate(1, com.afrisoftech.lib.SQLDateFormat.getSQLDate(beginDate));
            pstmt.setDate(2, com.afrisoftech.lib.SQLDateFormat.getSQLDate(endDate));
            pstmt.setString(3, patientType);
            java.sql.ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noofPatients = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return noofPatients;

    }

    public static boolean getPatientVisitType(java.sql.Connection connectDB, String patientNo) {

        boolean visitType = false;

        int visitCount = 0;
        
        try {
            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT count(*) FROM hp_admission WHERE patient_no = ?");
            pstmt.setString(1, patientNo);
            java.sql.ResultSet rset = pstmt.executeQuery();
            
            while(rset.next()){
                visitCount = rset.getInt(1);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        if(visitCount > 1){
            
            visitType = true;
        }
        
        return visitType;
        
    }
}
