/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import com.miginfocom.util.HashMapMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class RadiologyRequestJSON {

    public static String getOrthanoSystemInstanceUID(java.sql.Connection connectDB) {
        String uid = "";
        try {
            String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
            String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);
            
            String url = "http://alice:alicePassword@"+PacsServerIP+":"+PacsPort+"/tools/generate-uid?level=study";
            URL request_url = new URL(url);
            HttpURLConnection http_conn = null;
            try {
                http_conn = (HttpURLConnection) request_url.openConnection();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            http_conn.setConnectTimeout(100000);
            http_conn.setReadTimeout(100000);
            http_conn.setInstanceFollowRedirects(true);
            try {
                //uid = String.valueOf(http_conn.getResponseMessage());
                //Get Response
                InputStream is = http_conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    //response.append('\r');
                }
                rd.close();
                uid =  response.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.err.println(uid);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RadiologyRequestJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uid;
    }

    
    public static String getOrthanoSystemInstanceUID() {
        String uid = "";
        try {
            String url = "http://funsoft.systempartners.biz:8042/tools/generate-uid?level=study";
            URL request_url = new URL(url);
            HttpURLConnection http_conn = null;
            try {
                http_conn = (HttpURLConnection) request_url.openConnection();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            http_conn.setConnectTimeout(100000);
            http_conn.setReadTimeout(100000);
            http_conn.setInstanceFollowRedirects(true);
            try {
                //uid = String.valueOf(http_conn.getResponseMessage());
                //Get Response
                InputStream is = http_conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    //response.append('\r');
                }
                rd.close();
                uid =  response.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.err.println(uid);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RadiologyRequestJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uid;
    }

    public static JSONArray getPacsRequestingPhysicianIdentificationSeqMap(java.sql.Connection connectDB, String requestNo, String patientNo) {

        JSONArray jsonArray = new JSONArray();

        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT hospital_name from pb_hospitalprofile");
            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                HashMap requestMap = new HashMap();
                requestMap.put("InstitutionName", rsetlabRequestJSON.getString(1));
                requestMap.put("PersonIdentificationCodeSequence", (Object) com.afrisoftech.lib.RadiologyRequestJSON.getPacsPersonIdentificationSeqMap(connectDB, requestNo, patientNo));
                requestMap.put("PersonTelephoneNumbers", "070000");

                jsonArray.put(requestMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return jsonArray;

    }

    public static JSONArray getPacsPersonIdentificationSeqMap(java.sql.Connection connectDB, String requestNo, String patientNo) {

        JSONArray jsonArray = new JSONArray();

        HashMap requestMap = new HashMap();
        requestMap.put("CodeMeaning", "Local Code");
        requestMap.put("CodeValue", "0001");
        requestMap.put("CodingSchemeDesignator", "L");

        jsonArray.put(requestMap);

        return jsonArray;

    }

    public static JSONArray getScheduledProcedureStepSequence(java.sql.Connection connectDB, String requestNo, String patientNo, String test, String date, String time, String testCode,String modality) {

        JSONArray jsonArray = new JSONArray();

        HashMap requestMap = new HashMap();
        requestMap.put("Modality", modality);
        requestMap.put("ScheduledProcedureStepDescription", test);
        requestMap.put("ScheduledProcedureStepID", testCode);
        requestMap.put("ScheduledProcedureStepStartDate", date);
        requestMap.put("ScheduledProcedureStepStartTime", time);
        requestMap.put("ScheduledProtocolCodeSequence", (Object) com.afrisoftech.lib.RadiologyRequestJSON.getPacsScheduledProtocolCodeSequence(connectDB, requestNo, patientNo));
        requestMap.put("ScheduledStationAETitle", "NRH");

        jsonArray.put(requestMap);

        return jsonArray;

    }

    public static JSONArray getPacsScheduledProtocolCodeSequence(java.sql.Connection connectDB, String requestNo, String patientNo) {

        JSONArray jsonArray = new JSONArray();

        HashMap requestMap = new HashMap();
        requestMap.put("CodeMeaning", "[\"70551\"]");
        requestMap.put("CodeValue", "70551");
        requestMap.put("CodingSchemeDesignator", "C4");

        jsonArray.put(requestMap);

        return jsonArray;

    }

    public static HashMap getLimsRequestPatientMap(java.sql.Connection connectDB, String requestNo, String patientNo, String patientType) {

        JSONObject limsRequestJson = new JSONObject();
        HashMap limsRequestMap = new HashMap();

        //JSONArray limsJsonArray = new JSONArray();
        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = null;
            if (patientType.equalsIgnoreCase("OP")) {
                pstmtLabRequestJSON = connectDB.prepareStatement("SELECT patient_no,first_name,second_name,last_name,year_of_birth::date,sex FROM hp_patient_register WHERE  patient_no = ? ");
                System.err.println("SELECT patient_no,first_name,second_name,last_name,year_of_birth::date,sex FROM hp_patient_register WHERE  patient_no =");
            } else {
                pstmtLabRequestJSON = connectDB.prepareStatement("SELECT patient_no,first_name,second_name,last_name,year_of_birth::date,sex FROM hp_inpatient_register WHERE  patient_no = ? ");
                System.err.println("SELECT patient_no,first_name,second_name,last_name,year_of_birth::date,sex FROM hp_inpatient_register WHERE  patient_no ");
            }
            pstmtLabRequestJSON.setString(1, patientNo);
            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {

                limsRequestMap.put("id", rsetlabRequestJSON.getString(1));
                limsRequestMap.put("first_name", rsetlabRequestJSON.getString(2));
                limsRequestMap.put("middle_name", rsetlabRequestJSON.getString(3));
                limsRequestMap.put("last_name", rsetlabRequestJSON.getString(4));
                limsRequestMap.put("date_of_birth", rsetlabRequestJSON.getString(5));
                if (rsetlabRequestJSON.getString(6).startsWith("M")) {
                    limsRequestMap.put("gender", "M");
                } else {
                    limsRequestMap.put("gender", "F");
                }

                //  limsJsonArray.put(limsRequestMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return limsRequestMap;

    }

    public static String getLabRequestBlis(java.sql.Connection connectDB, String accessToken, String requestNo, String patientNo, String patientName, String paymentMode, String schemeName, String requesterAccount, String patientType, String test, double amount, String doa, String dpt, String pd, String comments) {
        boolean checkoutRequestStatus = true;
        com.squareup.okhttp.OkHttpClient client = new com.squareup.okhttp.OkHttpClient();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setCalendar(calendar);
        String timeStamp = dateFormat.format(calendar.getTime());
        System.out.println("Timestamp : [" + dateFormat.format(calendar.getTime()) + "]");
        com.squareup.okhttp.MediaType mediaType = com.squareup.okhttp.MediaType.parse("application/json");

        String message = null;
        JSONObject json = new JSONObject();
        // HashMap json = new HashMap();

        try {
            json.put("cost", amount);
            json.put("receipt_number", requestNo);
            json.put("receipt_type", paymentMode);
            json.put("lab_number", requestNo);
            json.put("parent_lab_number", 0);
            json.put("requesting_clinician", requesterAccount);
            json.put("investigation", test);
            json.put("request_date", timeStamp);
            json.put("patient_visit_type", patientType);
            json.put("patient_visit_number", patientNo);
            json.put("department", dpt);
            json.put("date_of_admission", doa);
            json.put("provisional_diagnosis", pd);
            json.put("request_notes", comments);
            json.put("patient", (Object) com.afrisoftech.lib.RadiologyRequestJSON.getLimsRequestPatientMap(connectDB, requestNo, patientNo, patientType));
            json.put("address", (Object) com.afrisoftech.lib.RadiologyRequestJSON.getLimsRequestAddressMap(connectDB, requestNo, patientNo, patientType));

            message = json.toString();
            //System.out.println("This is the LIMS request JSON String : " + message);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RadiologyRequestJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return message;

    }

    public static HashMap getLimsRequestAddressMap(java.sql.Connection connectDB, String requestNo, String patientNo, String patientType) {

        JSONObject limsRequestJson = new JSONObject();
        HashMap limsRequestMap = new HashMap();

//        JSONArray limsJsonArray = new JSONArray();
        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = null;
            if (patientType.equalsIgnoreCase("OP")) {
                pstmtLabRequestJSON = connectDB.prepareStatement("SELECT address,residence,tel_no,home_county,locations,residence_county FROM hp_patient_register WHERE  patient_no = ? ");
            } else {
                pstmtLabRequestJSON = connectDB.prepareStatement("SELECT address,residence,tel_no,'' as home_county,residence,(SELECT residence_county FROM hp_admission WHERE hp_admission.patient_no = hp_inpatient_register.patient_no LIMIT 1) as residence_county FROM hp_inpatient_register WHERE  patient_no = ? ");

            }
            pstmtLabRequestJSON.setString(1, patientNo);
            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {

                limsRequestMap.put("address", rsetlabRequestJSON.getString(1));
                limsRequestMap.put("postal_code", rsetlabRequestJSON.getString(2));
                limsRequestMap.put("phone_number", rsetlabRequestJSON.getString(3));
                limsRequestMap.put("city", rsetlabRequestJSON.getString(2));
                limsRequestMap.put("county", rsetlabRequestJSON.getString(4));
                limsRequestMap.put("sub_county", rsetlabRequestJSON.getString(5));
                limsRequestMap.put("ward", rsetlabRequestJSON.getString(5));
                limsRequestMap.put("village", rsetlabRequestJSON.getString(2));
                limsRequestMap.put("county_of_residence", rsetlabRequestJSON.getString(6));

                //limsJsonArray.put(limsRequestMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return limsRequestMap;

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

    public static String getPacsServerIPAdd(java.sql.Connection connectDB) {
        String serverIP = "";
        try {
            java.sql.PreparedStatement pstmtRequester = connectDB.prepareStatement("SELECT pacs_server_ip_add FROM pb_patient_names");
            java.sql.ResultSet rsetRequester = pstmtRequester.executeQuery();
            while (rsetRequester.next()) {
                serverIP = rsetRequester.getString(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return serverIP;
    }

    public static String getPacsServerPort(java.sql.Connection connectDB) {
        String port = "";
        try {
            java.sql.PreparedStatement pstmtRequester = connectDB.prepareStatement("SELECT pacs_server_port FROM pb_patient_names");
            java.sql.ResultSet rsetRequester = pstmtRequester.executeQuery();
            while (rsetRequester.next()) {
                port = rsetRequester.getString(1);
            }

        } catch (SQLException ex) {
            //     ex.printStackTrace();
            ex.printStackTrace();
        }
        return port;
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

    public static boolean isPacsEnabled(java.sql.Connection connectDB) {

        boolean isPACsEnabled = false;
//SELECT DISTINCT lims_enabled FROM pb_patient_names
        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT DISTINCT pacs_enabled FROM pb_patient_names");

            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                isPACsEnabled = rsetlabRequestJSON.getBoolean(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isPACsEnabled;

    }

    public static String getLIMSSystemName(java.sql.Connection connectDB) {

        String LIMSSystemName = "";

        try {
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT DISTINCT lims_system_name FROM pb_patient_names");

            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                LIMSSystemName = rsetlabRequestJSON.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return LIMSSystemName;

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
