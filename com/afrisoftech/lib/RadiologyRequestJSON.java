/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class RadiologyRequestJSON {

    public static String getOrthanoSystemInstanceUIDD(java.sql.Connection connectDB) throws IOException {
        String uid = "";
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/x-uri");
        String message = "";
        RequestBody body = RequestBody.create(mediaType, "application/json");
        Request request = null;
        String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
        String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);

        String loginPassword = "pacs:Password_123@";
        JSONObject json = new JSONObject();
        try {
            //Sent to PACS
            json.put("AccessionNumber", "test");
            message = json.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        body = RequestBody.create(mediaType, message);
//        String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
//        URLConnection conn = url.openConnection();
//        conn.setRequestProperty("Authorization", "Basic " + encoded);

//public void sendGetRequest(String url) throws IOException {
// OkHttpClient client = new OkHttpClient();
// Request request = new Request.Builder().url(url).build();
// try (Response response = client.newCall(request).execute()) {
//  String responseBody = response.body().string();
//  // ... do something with response
// }
//}

        /*final String credentials = "pacs" + ":" + "Password_123";
        final String basic = "Basic " + Base64.toBase64String(credentials.getBytes());
        request = new Request.Builder()
                .url("https://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study") // for sandbox test cases        
                .addHeader("Authorization", basic)
                .build();
        System.err.println("URL to be used for request : https://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study");
        Response response = client.newCall(request).execute();
        String responseString = null;
        responseString = response.body().string();
        uid = responseString;
        System.out.println("Prints for UID response [" + responseString + "]");*/
//
        try {
            PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
            PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);

            //String url = "http://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study";
                       String url = "http://pacs:Password_123@"+PacsServerIP+":"+PacsPort+"/tools/generate-uid?level=study";
            System.err.println("Orthanc URL>>>>>>>>>> " + url);
             //.url("https://pacs:Password_123@" + PacsServerIP + ":" + PacsPort+"/mwl/create_from_json") //
            URL request_url = new URL(url);
            HttpURLConnection http_conn = null;
            try {
                http_conn = (HttpURLConnection) request_url.openConnection();
                String basicAuth = "pacs:Password_123@";
                 //              basicAuth = "Basic " + new String(Base64.encode(basicAuth.getBytes())// //, Base64.NO_WRAP));
                http_conn.setRequestProperty("Authorization", basicAuth);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            http_conn.setConnectTimeout(100000);
            http_conn.setReadTimeout(100000);
            http_conn.setInstanceFollowRedirects(true);
            http_conn.connect();
            try {
                uid = String.valueOf(http_conn.getResponseMessage());
                //Get Response
                InputStream is = http_conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder responses = new StringBuilder(); // or StringBuffer if Java version 5+
                String line;
                while ((line = rd.readLine()) != null) {
                    responses.append(line);
                    //response.append('\r');
                }
                rd.close();
                uid = responses.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.err.println(uid);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RadiologyRequestJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uid;
    }
    
    public static String getOrthanoSystemInstanceUIDDD(java.sql.Connection connectDB) throws IOException {
        String uid = "";
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/x-uri");
        String message = "";
        RequestBody body = RequestBody.create(mediaType, "application/json");
        Request request = null;
        String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
        String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);

        String loginPassword = "pacs:Password_123@";
        JSONObject json = new JSONObject();
        try {
            //Sent to PACS
            json.put("AccessionNumber", "test");
            message = json.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        body = RequestBody.create(mediaType, message);
//        String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
//        URLConnection conn = url.openConnection();
//        conn.setRequestProperty("Authorization", "Basic " + encoded);

//public void sendGetRequest(String url) throws IOException {
// OkHttpClient client = new OkHttpClient();
// Request request = new Request.Builder().url(url).build();
// try (Response response = client.newCall(request).execute()) {
//  String responseBody = response.body().string();
//  // ... do something with response
// }
//}

        /*final String credentials = "pacs" + ":" + "Password_123";
        final String basic = "Basic " + Base64.toBase64String(credentials.getBytes());
        request = new Request.Builder()
                .url("https://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study") // for sandbox test cases        
                .addHeader("Authorization", basic)
                .build();
        System.err.println("URL to be used for request : https://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study");
        Response response = client.newCall(request).execute();
        String responseString = null;
        responseString = response.body().string();
        uid = responseString;
        System.out.println("Prints for UID response [" + responseString + "]");*/
//
        try {
            PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
            PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);

            //String url = "http://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study";
                       String url = "http://pacs:Password_123@"+PacsServerIP+":"+PacsPort+"/tools/generate-uid?level=study";
            System.err.println("Orthanc URL>>>>>>>>>> " + url);
             //.url("https://pacs:Password_123@" + PacsServerIP + ":" + PacsPort+"/mwl/create_from_json") //
            URL request_url = new URL(url);
            HttpURLConnection http_conn = null;
            try {
                http_conn = (HttpURLConnection) request_url.openConnection();
                String basicAuth = "pacs:Password_123@";
                 //              basicAuth = "Basic " + new String(Base64.encode(basicAuth.getBytes())// //, Base64.NO_WRAP));
                http_conn.setRequestProperty("Authorization", basicAuth);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            http_conn.setConnectTimeout(100000);
            http_conn.setReadTimeout(100000);
            http_conn.setInstanceFollowRedirects(true);
            http_conn.connect();
            try {
                uid = String.valueOf(http_conn.getResponseMessage());
                //Get Response
                InputStream is = http_conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder responses = new StringBuilder(); // or StringBuffer if Java version 5+
                String line;
                while ((line = rd.readLine()) != null) {
                    responses.append(line);
                    //response.append('\r');
                }
                rd.close();
                uid = responses.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.err.println(uid);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RadiologyRequestJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uid;
    }
    
    public static String getOrthanoSystemInstanceUID(java.sql.Connection connectDB) throws IOException {
        String uid = "";
        try {
            String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
            String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);
            String urll = "http://" + PacsServerIP + ":" + PacsPort + "/tools/generate-uid?level=study";
            //urll = "http://41.76.169.23:8042/tools/generate-uid?level=study";
            URL url = new URL (urll);

            Base64 b = new Base64();
            //String encoding = b.encodeAsString(new String("pacs:Password_123").getBytes());
            //String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            String encoding = Base64.toBase64String(new String("pacs:Password_123").getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   =
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                uid = line;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return uid;
    }
    

    public static String getOrthanoSystemInstanceUUID(java.sql.Connection connectDB, String uid) throws IOException {
        String uuid = "";
        
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select uuid from hp_xray_results where uid = '"+uid+"' ");
            while (rset1.next()) {
                uuid = rset1.getObject(1).toString();

            }

        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        }
       /* OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/x-uri");
        String message = "";
        RequestBody body = RequestBody.create(mediaType, "application/json");
        Request request = null;
        String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
        String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);

        String loginPassword = "pacs:Password_123@";
        JSONObject json = new JSONObject();
        try {
            //Sent to PACS
            json.put("", uid);
            message = json.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        body = RequestBody.create(mediaType, message);

        final String credentials = "pacs" + ":" + "Password_123";
        final String basic = "Basic " + Base64.toBase64String(credentials.getBytes());
        request = new Request.Builder()
                .url("http://" + PacsServerIP + ":" + PacsPort + "/tools/lookup") // for sandbox test cases     
                .post(body)
                .addHeader("Authorization", basic)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = null;
        responseString = response.body().string();
        uuid = responseString;
        System.out.println("Prints for UID response [" + responseString + "]");*/

        return uuid;
    }

    public static String getOrthanoSystemUUID(java.sql.Connection connectDB, String uid) {

        //uid = "1.2.276.0.7230010.3.1.2.8323328.4660.1636877933.434819";
        /*String uuid = null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        //MediaType mediaType = MediaType.parse("application/json");

        String message = null;
        String message2 = null;
        JSONObject json = new JSONObject();

        try {
            json.put("", uid);
            message = json.toString();
            message2 = "" + uid;
            System.out.println("This is the LIMS request JSON String : " + message);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        RequestBody body = RequestBody.create(mediaType, message2);
        Request request = null;

        String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
        String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);
        String url = "http://pacs:Password_123@" + PacsServerIP + ":" + PacsPort + "/tools/lookup";

        request = new Request.Builder()
                .url(url)
                .post(body)
                //  .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();

            //    System.out.println(">>><<<"+response.body().string());
            JSONObject myJsonObject = null;
            try {
                JSONArray jsonArray = new JSONArray(response.body().string()); //response.body().toString().trim());
                System.out.println("Array capacity : " + jsonArray.length());
                myJsonObject = jsonArray.getJSONObject(0);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

            System.out.println("Derived JSON string from array" + myJsonObject.toString());
            if (myJsonObject.toString().contains("ID")) {
                try {
                    uuid = myJsonObject.getString("ID");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                uuid = "Not found";
            }

            System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
                
                String uuid = "";
        
        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rset1 = stmt1.executeQuery("select uuid from hp_xray_results where uid = '"+uid+"' ");
            while (rset1.next()) {
                uuid = rset1.getObject(1).toString();

            }

        } catch (java.sql.SQLException sqe) {
            sqe.printStackTrace();
            //  System.out.println("Insert not successful");
        }

        System.err.println("????" + uuid);
        return uuid;
    }

    public static void addPdfToUUID(java.sql.Connection connectDB, String uuid, String pathToPdf, String userName, String title) {
        try {
            File file = new File(pathToPdf);
            byte[] bytes = Files.readAllBytes(file.toPath());

            String b64 = java.util.Base64.getEncoder().encodeToString(bytes);
            System.out.println(b64); //-> "JVBERi..."

            OkHttpClient client = new OkHttpClient();
            //MediaType mediaType = MediaType.parse("text/plain");
            MediaType mediaType = MediaType.parse("application/json");

            String message = null;
            String message2 = null;
            JSONObject json = new JSONObject();

            try {
                json.put("method", "base64");
                json.put("author", userName);
                json.put("title", title);
                json.put("studyuuid", uuid);
                json.put("base64", b64);
                json.put("html", "");
                json.put("return", 1);
                json.put("attach", 1);
                message = json.toString();
                System.out.println("This is the LIMS request JSON String : " + message);

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            RequestBody body = RequestBody.create(mediaType, message);
            Request request = null;

            String PacsServerIP = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerIPAdd(connectDB);
            String PacsPort = com.afrisoftech.lib.RadiologyRequestJSON.getPacsServerPort(connectDB);
            String url = "http://pacs:Password_123@" + PacsServerIP + ":" + PacsPort + "/pdfkit/htmltopdf";

            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    //  .addHeader("authorization", "Bearer " + accessToken)
                    .addHeader("content-type", "application/json")
                    .build();

            //try {
            Response response = client.newCall(request).execute();

            //    System.out.println(">>><<<"+response.body().string());
            JSONObject myJsonObject = null;
            try {
                myJsonObject = new JSONObject(response.body().string()); //response.body().toString().trim());
                //System.out.println("Array capacity : " + jsonArray.length());
                //myJsonObject = jsonArray.getJSONObject(0);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

            System.out.println("Derived JSON string from array" + myJsonObject.toString());
            if (myJsonObject.toString().contains("Success")) {
//                try {
//                    uuid = myJsonObject.getString("ID");
//
//                } catch (JSONException ex) {
//                    ex.printStackTrace();
//                }

                System.err.println("File Added Succefully");
            } else {
                System.err.println("File Addition Failed");
            }

            System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                uid = response.toString();
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
                //requestMap.put("RequestedProcedureDescription", requestedProcedureDescription);

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

    public static JSONArray getScheduledProcedureStepSequence(java.sql.Connection connectDB, String requestNo, String patientNo, String test, String date, String time, String testCode, String modality) {

        JSONArray jsonArray = new JSONArray();

        HashMap requestMap = new HashMap();
        requestMap.put("Modality", modality);
        requestMap.put("ScheduledProcedureStepDescription", test);
        requestMap.put("ScheduledProcedureStepID", testCode);
        requestMap.put("ScheduledProcedureStepStartDate", date);
        requestMap.put("ScheduledProcedureStepStartTime", time);
        requestMap.put("ScheduledProtocolCodeSequence", (Object) com.afrisoftech.lib.RadiologyRequestJSON.getPacsScheduledProtocolCodeSequence(connectDB, requestNo, patientNo));
        requestMap.put("ScheduledStationAETitle", "FUNSOFT_AET");

        jsonArray.put(requestMap);

        return jsonArray;

    }

    public static JSONArray getPacsScheduledProtocolCodeSequence(java.sql.Connection connectDB, String requestNo, String patientNo) {

        JSONArray jsonArray = new JSONArray();

        HashMap requestMap = new HashMap();
        //requestMap.put("CodeMeaning", "[\"Patella\"]");
        requestMap.put("CodeMeaning", "Patella");
        requestMap.put("CodeValue", "T-12730");
        requestMap.put("CodingSchemeDesignator", "SNM3");

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

    public static Duration ping(String host) {
        Instant startTime = Instant.now();
        try {
            InetAddress address = InetAddress.getByName(host);
            if (address.isReachable(1000)) {
                return Duration.between(startTime, Instant.now());
            }
        } catch (IOException e) {
            // Host not available, nothing to do here
        }
        return Duration.ofDays(1);
    }

    public static String getPacsServerIPAdd(java.sql.Connection connectDB) {
        String serverIP = "";
        String publicIP = "";
        boolean isPublicIPReachable = false;
        boolean isServerIPReachable = false;
        try {
            java.sql.PreparedStatement pstmtRequester = connectDB.prepareStatement("SELECT pacs_server_ip_add,public_ip_address FROM pb_patient_names");
            java.sql.ResultSet rsetRequester = pstmtRequester.executeQuery();
            while (rsetRequester.next()) {
                serverIP = rsetRequester.getString(1);
                publicIP = rsetRequester.getString(2);
            }

            //serverIP = publicIP;
            //InetAddress.
//            System.err.println(serverIP + " status "+ping(serverIP));
//            System.err.println(publicIP + " status "+ ping(publicIP));
//            try {
//                isPublicIPReachable = InetAddress.getByName(publicIP).isReachable(3000);
//                System.out.println("Is piblic IP reachable [" + isPublicIPReachable + "]");
//            } catch (IOException ex) {
//                Exceptions.printStackTrace(ex);
//            }
//
//            try {
//                isServerIPReachable = InetAddress.getByName(serverIP).isReachable(3000);
//                System.out.println("Is piblic IP reachable [" + isServerIPReachable + "]");
//            } catch (IOException ex) {
//                Exceptions.printStackTrace(ex);
//            }
//            if(String.valueOf(ping(publicIP)).equals("PT24H")){               
//                System.err.println("  Public IP not Reachable use pacs ip");
//            }else{
//                serverIP = publicIP;
//                System.err.println("  Public  IP is Reachable use public ip");
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //   System.err.println("Pacs IP Address "+serverIP);
        if (Boolean.parseBoolean(System.getProperty("remote.access", "false"))) {
            return publicIP;
        } else {
            return serverIP;
        }

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
