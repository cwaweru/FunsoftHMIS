/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import com.miginfocom.util.HashMapMap;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class LabRequestJSON {

    public static JSONArray getLimsRequestMap(java.sql.Connection connectDB, String requestNo, String patientNo) {

        JSONObject limsRequestJson = new JSONObject();

        JSONArray limsJsonArray = new JSONArray();

        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT inpatient_no, service, revenue_code, amount FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " UNION "
                    + "SELECT reference, service, main_service, debit as amount FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')");
            pstmtLabRequestJSON.setString(1, requestNo);
            pstmtLabRequestJSON.setString(2, patientNo);
            pstmtLabRequestJSON.setString(3, requestNo);
            pstmtLabRequestJSON.setString(4, patientNo);
            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                HashMap limsRequestMap = new HashMap();
                limsRequestMap.put("requestId", rsetlabRequestJSON.getString(1));
                limsRequestMap.put("service", rsetlabRequestJSON.getString(2));
                limsRequestMap.put("mainService", rsetlabRequestJSON.getString(3));
                limsRequestMap.put("amount", rsetlabRequestJSON.getDouble(4));
                limsJsonArray.put(limsRequestMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return limsJsonArray;

    }

    public static double getLimsRequestTotal(java.sql.Connection connectDB, String requestNo, String patientNo) {

        double labRequestTotal = 0.00;

        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT sum(amount) as amount FROM (SELECT sum(amount) as amount FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " UNION SELECT sum(debit) as amount FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " ) as foo");
            pstmtLabRequestJSON.setString(1, requestNo);
            pstmtLabRequestJSON.setString(2, patientNo);
            pstmtLabRequestJSON.setString(3, requestNo);
            pstmtLabRequestJSON.setString(4, patientNo);
            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                labRequestTotal = rsetlabRequestJSON.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return labRequestTotal;

    }

    public static String getLabRequester(java.sql.Connection connectDB, String requestNo, String patientNo) {

        String labRequester = com.afrisoftech.lib.UserName.getLoginName(connectDB);

        try {
            java.sql.PreparedStatement pstmtRequester = connectDB.prepareStatement("SELECT user_name FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? UNION SELECT user_name FROM hp_patient_card WHERE reference = ? AND patient_no = ? LIMIT 1");
            pstmtRequester.setString(1, requestNo);
            pstmtRequester.setString(2, patientNo);
            pstmtRequester.setString(3, requestNo);
            pstmtRequester.setString(4, patientNo);
            java.sql.ResultSet rsetRequester = pstmtRequester.executeQuery();
            while (rsetRequester.next()) {
                labRequester = rsetRequester.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return labRequester;

    }

    public static boolean isLIMSEnabled(java.sql.Connection connectDB) {

        boolean isLIMSEnabled = false;

        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT DISTINCT lims_enabled FROM pb_patient_names");

            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                isLIMSEnabled = rsetlabRequestJSON.getBoolean(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isLIMSEnabled;

    }

    public static int getPatientAge(java.sql.Connection connectDB, String patientNo, String patientType) {

        int patientAge = 0;

        try {
            if (patientType == "OUT") {
                java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT age::integer as age FROM hp_patient_visit WHERE patient_no = ? ORDER BY input_date DESC LIMIT 1");
                pstmtLabRequestJSON.setString(1, patientNo);
                java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
                while (rsetlabRequestJSON.next()) {
                    patientAge = rsetlabRequestJSON.getInt(1);
                }
            } else {
                java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT pat_age::integer as age FROM hp_admission WHERE patient_no = ? ORDER BY data_capture_time DESC LIMIT 1");
                pstmtLabRequestJSON.setString(1, patientNo);
                java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
                while (rsetlabRequestJSON.next()) {
                    patientAge = rsetlabRequestJSON.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return patientAge;

    }

    public static String getPatientGender(java.sql.Connection connectDB, String patientNo, String patientType) {

        String patientGender = null;

        try {
            if (patientType == "OUT") {
                java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT gender FROM hp_patient_visit WHERE patient_no = ? ORDER BY input_date DESC LIMIT 1");
                pstmtLabRequestJSON.setString(1, patientNo);
                java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();

                while (rsetlabRequestJSON.next()) {
                    patientGender = rsetlabRequestJSON.getString(1);
                }
            } else {
                java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT gender FROM hp_admission WHERE patient_no = ? ORDER BY data_capture_time DESC LIMIT 1");
                pstmtLabRequestJSON.setString(1, patientNo);
                java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();

                while (rsetlabRequestJSON.next()) {
                    patientGender = rsetlabRequestJSON.getString(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return patientGender;

    }
}
