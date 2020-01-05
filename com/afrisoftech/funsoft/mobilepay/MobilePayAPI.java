/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.funsoft.mobilepay;

//import com.bazaarvoice.jolt.JsonUtils;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.directory.server.core.authn.PasswordUtil;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONException;
import org.json.JSONObject;
//import org.openide.util.Exceptions;

//import org.ow2.util.base64.Base64;
/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class MobilePayAPI {

    //String accessToken = null;
    public MobilePayAPI() {

        Thread thread = new Thread(new LaunchTest());

        thread.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MobilePayAPI mobilePayAPI = new MobilePayAPI();

    }

    private class LaunchTest implements Runnable {

        @Override
        public void run() {

            encryptInitiatorPassword("cert.cer", "erer");
            System.out.println("Encrypted Initiator Password : [" + encryptInitiatorPassword("cert.cer", "erer") + "]");

            String app_key = "Si1Y0dik7IoBEFC9buVTGBBdM0A9mQLw";
            String app_secret = "DlPLOhUtuwdAjzDB";
            String appKeySecret = app_key + ":" + app_secret;
            String auth = null;
            byte[] bytes = null;

            try {
                //bytes = usernameAndPassword.getBytes("ISO-8859-1");
                bytes = appKeySecret.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            auth = Base64.toBase64String(bytes);
            auth = auth.trim();
            System.out.println(Arrays.toString(bytes));
            System.out.println("Athentication : [" + auth + "]");

            // String auth = Base64.encode(bytes);
            OkHttpClient client = new OkHttpClient();

            System.out.println("OkHttpClient : [" + client + "]");

            Request request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                    .get()
                    .addHeader("Authorization", "Basic " + auth)
                    .addHeader("cache-control", "no-cache")
                    .build();
            System.out.println("Request : [" + request + "]");
            Response response;
            // String accessToken = null;
            try {
                response = client.newCall(request).execute();
                JSONObject myObject = null;
                try {
                    myObject = new JSONObject(response.body().string());
                } catch (JSONException ex) {
                    Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("Response : " + myObject.toString());

                try {
                    System.out.println("Access Token : [" + myObject.getString("access_token") + "]");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public String getOAuthAccessToken() {
        String accessToken = null;
        String app_key = "Si1Y0dik7IoBEFC9buVTGBBdM0A9mQLw";
        String app_secret = "DlPLOhUtuwdAjzDB";
        String appKeySecret = app_key + ":" + app_secret;
        String auth = null;
        byte[] bytes = null;

        try {
            //bytes = usernameAndPassword.getBytes("ISO-8859-1");
            bytes = appKeySecret.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        auth = com.itextpdf.text.pdf.codec.Base64.encodeBytes(bytes);//Base64.toBase64String(bytes);
        auth = auth.trim();
        System.out.println(bytes);
        System.out.println("Athentication : [" + auth + "]");

        // String auth = Base64.encode(bytes);
        OkHttpClient client = new OkHttpClient();

        System.out.println("OkHttpClient : [" + client + "]");

        Request request = new Request.Builder()
                .url("https://api.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                // https://api.safaricom.co.ke/oauth/v1/generate
                // .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("Authorization", "Basic " + auth)
                .addHeader("cache-control", "no-cache")
                .build();
        System.out.println("Request : [" + request + "]");
        Response response;
        // String accessToken = null;
        try {
            response = client.newCall(request).execute();
            JSONObject myObject = null;
            try {
                myObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Response : " + myObject.toString());

            try {
                System.out.println("Access Token : [" + myObject.getString("access_token") + "]");

                accessToken = myObject.getString("access_token");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return accessToken;
    }

    // Function to encrypt the initiator credentials
    public String encryptInitiatorPassword(String securityCertificate, String password) {
        String encryptedPassword = "safaricom329!";
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {

                                        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
                                        byte[] input = password.getBytes();

                                        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
                                        FileInputStream fin = new FileInputStream(new File(securityCertificate));
                                        CertificateFactory cf = CertificateFactory.getInstance("X.509");
                                        X509Certificate certificate = (X509Certificate) cf.generateCertificate(fin);
                                        PublicKey pk = certificate.getPublicKey();
                                        cipher.init(Cipher.ENCRYPT_MODE, pk);

                                        byte[] cipherText = cipher.doFinal(input);

                                        // Convert the resulting encrypted byte array into a string using base64 encoding
                                        encryptedPassword = Base64.toBase64String(cipherText);

                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (NoSuchProviderException ex) {

                                    Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (NoSuchPaddingException ex) {
                                Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (CertificateException ex) {
                        Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (BadPaddingException ex) {
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encryptedPassword;
    }

    public void sendPaymentRequest(String accessToken) {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String message = null;
        JSONObject json = new JSONObject();
        try {
            json.put("InitiatorName", "Charles Waweru");
            json.put("SecurityCredential", this.encryptInitiatorPassword("cert.cer", "3414"));
            json.put("CommandID", "CustomerPayBillOnline");
            json.put("Amount", "10");
            json.put("PartyA", "254714433693");
            json.put("PartyB", "881100");
            json.put("Remarks", "Testing Rest API");
            json.put("QueueTimeOutURL", "");
            json.put("ResultURL", "https://192.162.85.226:17933/FunsoftWebServices/funsoft/InvoiceService/mpesasettlement");
            json.put("Occasion", "1234567891");
            message = json.toString();
            System.out.println("This is the JSON String : " + message);

        } catch (JSONException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestBody body = RequestBody.create(mediaType, message);

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest")
                .post(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .build();

        try {
            System.out.println("Request Build Security Credentials : [" + this.encryptInitiatorPassword("cert.cer", "3414") + "]");
            System.out.println("Request Build : [" + request.body().toString() + "]");
            Response response = client.newCall(request).execute();
            JSONObject myObject = null;
            try {
                myObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Payment Request Response : " + myObject.toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void simulateTransaction(String accessToken) {
        System.out.println("Access token to use : [" + accessToken + "]");
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"ShortCode\":\"881100\","
                + "  \"CommandID\":\"CustomerPayBillOnline\","
                + "  \"Amount\":\"1\","
                + "  \"Msisdn\":\"254714433693\","
                + "  \"BillRefNumber\":\"1234567890\" }");
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate")
                .post(body)
                .addHeader("authorization", "Bearer " + accessToken.trim())
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject myObject = null;
            try {
                myObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Response for simulate transaction : [" + myObject.toString() + "]\n\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static boolean sendProcessRequest(String accessToken, String accountNo, String payerMobilePhone, String billedAmount, String shortCode) {
        boolean checkoutRequestStatus = true;
        OkHttpClient client = new OkHttpClient();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setCalendar(calendar);
        String timeStamp = dateFormat.format(calendar.getTime());
        System.out.println("Timestamp : [" + dateFormat.format(calendar.getTime()) + "]");
        System.out.println("Shortcode : [" + shortCode + "]");
        String password = shortCode + com.afrisoftech.hospital.HospitalMain.passKey + timeStamp;
//        String password = shortCode + "48d34200abe6ebbcbc3bc644487c3651936d129f2274f6ee95" + timeStamp;
//            json.put("CallBackURL", "https://192.162.85.226:17933/FunsoftWebServices/funsoft/InvoiceService/mpesasettlement");
//        String password = shortCode + "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919" + timeStamp; // for testing purposes
        String encodedPassword = Base64.toBase64String(password.getBytes());
        System.out.println("Unencoded password : [" + password + "]");
        System.out.println("Encoded password : [" + encodedPassword + "]");
        MediaType mediaType = MediaType.parse("application/json");

        String message = null;
        JSONObject json = new JSONObject();
        try {
            json.put("BusinessShortCode", shortCode);
            json.put("Password", encodedPassword);
            json.put("Timestamp", timeStamp);
            json.put("TransactionType", "CustomerPayBillOnline");
            json.put("Amount", billedAmount);
            json.put("PartyA", payerMobilePhone);
            json.put("PartyB", shortCode);
            json.put("PhoneNumber", payerMobilePhone);
            json.put("CallBackURL", com.afrisoftech.hospital.HospitalMain.callBackURL);
//            json.put("CallBackURL", "https://192.162.85.226:17933/FunsoftWebServices/funsoft/InvoiceService/mpesasettlement");
            json.put("AccountReference", accountNo);
            json.put("TransactionDesc", "Settlement for client bill");
            message = json.toString();
            System.out.println("This is the JSON String : " + message);

        } catch (JSONException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestBody body = RequestBody.create(mediaType, message);
        Request request = null;
        if (com.afrisoftech.hospital.HospitalMain.mobileTxTest) {
            request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest") // for sandbox test cases        
                    .post(body)
                    .addHeader("authorization", "Bearer " + accessToken)
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
            request = new Request.Builder()
                    .url("https://api.safaricom.co.ke/mpesa/stkpush/v1/processrequest")
                    .post(body)
                    .addHeader("authorization", "Bearer " + accessToken)
                    .addHeader("content-type", "application/json")
                    .build();
        }
        try {
            Response response = client.newCall(request).execute();
            JSONObject myJsonObject = null;
            try {
                myJsonObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (myJsonObject.toString().contains("error")) {
                try {
//                    checkoutRequestID = myJsonObject.getString("errorMessage");
                    checkoutRequestStatus = false;
                    System.out.println("Checkout Request ID : [" + myJsonObject.getString("errorMessage") + "]");
                    javax.swing.JOptionPane.showMessageDialog(null, "Payment Request Error : " + myJsonObject.getString("errorMessage") + ". Try again.");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else if (myJsonObject.toString().contains("Success")) {
                try {
                    checkoutRequestStatus = true;
                    com.afrisoftech.hospital.GeneralBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.hospinventory.PatientsBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.InpatientDepositIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.InpatientRecpIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.hospital.HospitalMain.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.GovBillPaymentsIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    System.out.println("Checout Request ID : [" + myJsonObject.getString("CheckoutRequestID") + "]");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return checkoutRequestStatus;
    }

    public static boolean sendLabRequest(java.sql.Connection connectDB, String accessToken, String requestNo, String patientNo, String patientName, String paymentMode, String schemeName, String requesterAccount, String patientType) {
        boolean checkoutRequestStatus = true;
        OkHttpClient client = new OkHttpClient();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setCalendar(calendar);
        String timeStamp = dateFormat.format(calendar.getTime());
        System.out.println("Timestamp : [" + dateFormat.format(calendar.getTime()) + "]");
        MediaType mediaType = MediaType.parse("application/json");

        String message = null;
        JSONObject json = new JSONObject();

        try {
            json.put("OrderID", requestNo);
//            json.put("Password", encodedPassword);
            json.put("Timestamp", timeStamp);
//            json.put("TransactionType", "CustomerPayBillOnline");
            json.put("patientNumber", patientNo);
            json.put("age", com.afrisoftech.lib.LabRequestJSON.getPatientAge(connectDB, patientNo, patientType));
            json.put("gender", "Male");
            json.put("physician", requesterAccount);
            if (!paymentMode.contains("Scheme")) {
                json.put("payment", paymentMode);
            } else {
                HashMap schemeMap = new HashMap();
                schemeMap.put("scheme", schemeName);
                json.put("payment", schemeMap);
            }
            json.put("paymentStatus", 1);
            json.put("patientName", patientName);
            json.put("amount", com.afrisoftech.lib.LabRequestJSON.getLimsRequestTotal(connectDB, requestNo, patientNo));
            json.put("tests", (Object) com.afrisoftech.lib.LabRequestJSON.getLimsRequestMap(connectDB, requestNo, patientNo));
            message = json.toString();
            System.out.println("This is the LIMS request JSON String : " + message);

        } catch (JSONException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestBody body = RequestBody.create(mediaType, message);
        Request request = null;

        String limsServerIP = com.afrisoftech.lib.LabRequestJSON.getLimsServerIPAdd(connectDB);
        String limsPort = com.afrisoftech.lib.LabRequestJSON.getLimsServerPort(connectDB);

        request = new Request.Builder()
                .url("http://" + limsServerIP + ":" + limsPort + "/lims/tests/makeTestOrder") // for sandbox test cases        
                .post(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .build();
        System.err.println("URL-------------> http://" + limsServerIP + ":" + limsPort + "/lims/tests/makeTestOrder");

        try {
            java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement("INSERT INTO public.hp_lims_request(  patient_no, patien_name, request_no, test, json_string, response)   VALUES (?, ?, ?, ?, ?, ?);");
            pstmt21.setString(1, patientNo);
            pstmt21.setString(2, patientName);
            pstmt21.setString(3, requestNo);
            pstmt21.setString(4, com.afrisoftech.lib.LabRequestJSON.getLimsRequestMap(connectDB, requestNo, patientNo).toString());
            pstmt21.setObject(5, message);
            pstmt21.setObject(6, "");
            pstmt21.executeUpdate();
        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();

        }

        try {
            Response response = client.newCall(request).execute();
            JSONObject myJsonObject = null;
            try {
                myJsonObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (myJsonObject.toString().contains("error")) {
                try {
//                    checkoutRequestID = myJsonObject.getString("errorMessage");
                    checkoutRequestStatus = false;
                    System.out.println("Checkout Request ID : [" + myJsonObject.getString("errorMessage") + "]");
                    javax.swing.JOptionPane.showMessageDialog(null, "Payment Request Error : " + myJsonObject.getString("errorMessage") + ". Try again.");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else if (myJsonObject.toString().contains("Success")) {
                try {
                    checkoutRequestStatus = true;
                    com.afrisoftech.hospital.GeneralBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.hospinventory.PatientsBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.InpatientDepositIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.InpatientRecpIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.hospital.HospitalMain.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.GovBillPaymentsIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    System.out.println("Checout Request ID : [" + myJsonObject.getString("CheckoutRequestID") + "]");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                java.sql.PreparedStatement pstmtCheckout = connectDB.prepareStatement("UPDATE hp_lims_request SET response = ? WHERE request_no = ?");
                pstmtCheckout.setString(1, myJsonObject.toString());
                pstmtCheckout.setString(2, requestNo);
                pstmtCheckout.executeUpdate();

            } catch (java.sql.SQLException sq) {
                sq.printStackTrace();

            }
            System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return checkoutRequestStatus;
    }

    public static void sendLabRequestBlis(java.sql.Connection connectDB, String accessToken, String requestNo, String patientNo, String patientName, String paymentMode, String schemeName, String requesterAccount, String patientType) {
        try {
            
            
            String doa = "";
            String  pd ="";
            String comments ="";
            String dpt = "";
            
            java.sql.Statement stm12p = connectDB.createStatement();
            java.sql.ResultSet rse12p = null;
            
            if(patientType.equalsIgnoreCase("OP")){
                rse12p = stm12p.executeQuery("select date,clinic from hp_patient_visit where date = current_date and patient_no = '"+patientNo+"' order by date desc limit 1 ");
            }else{
                rse12p = stm12p.executeQuery("select date_admitted,ward from hp_admission where patient_no = '"+patientNo+"' order by date_admitted desc limit 1");
          
            }
            while (rse12p.next()) {
                doa = rse12p.getString(1);
                dpt = rse12p.getString(2);
            }//
            
            rse12p = stm12p.executeQuery("select  description,date from hp_clinical_results where patient_no = '"+patientNo+"' and date > current_date - 10 and description is not null and description !=''  order by date desc limit 1 ");
             while (rse12p.next()) {
                pd = rse12p.getString(1);
            }
             
             

            System.err.println("Patient Typeeeeeee------->" + patientType);
            System.err.println("SELECT inpatient_no, service, revenue_code, amount FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " UNION "
                    + "SELECT reference, service, main_service, debit as amount FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')");
            java.sql.PreparedStatement pstmtLabRequestJSON = connectDB.prepareStatement("SELECT inpatient_no, service, revenue_code, amount FROM hp_patient_billing WHERE doctor = ? AND patient_no = ? AND UPPER(revenue_code) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')"
                    + " UNION "
                    + "SELECT reference, service, main_service, debit as amount FROM hp_patient_card WHERE reference = ? AND patient_no = ? AND UPPER(main_service) IN (SELECT UPPER(activity) FROM pb_activity WHERE department = 'LAB')");
            pstmtLabRequestJSON.setString(1, requestNo);
            pstmtLabRequestJSON.setString(2, patientNo);
            pstmtLabRequestJSON.setString(3, requestNo);
            pstmtLabRequestJSON.setString(4, patientNo);
            java.sql.ResultSet rsetlabRequestJSON = pstmtLabRequestJSON.executeQuery();
            while (rsetlabRequestJSON.next()) {
                String test = rsetlabRequestJSON.getString(2);
                double amount = rsetlabRequestJSON.getDouble(4);
                
                rse12p = stm12p.executeQuery("select  notes,trans_date from pb_doctors_request where patient_no = '"+patientNo+"' and upper(service) = '"+test.toUpperCase()+"' order by trans_date desc limit 1");
                while (rse12p.next()) {
                   comments = rse12p.getString(1);
               }

                boolean checkoutRequestStatus = true;
                OkHttpClient client = new OkHttpClient();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormat.setCalendar(calendar);
                String timeStamp = dateFormat.format(calendar.getTime());
                System.out.println("Timestamp : [" + dateFormat.format(calendar.getTime()) + "]");
                MediaType mediaType = MediaType.parse("application/json");

                String message = null;
                JSONObject json = new JSONObject();

                try {
                    //Sent to Bliss
                    json.put("system_id", "fansoft_bg");
                    json.put("api_key", accessToken);
                    json.put("lab_request", com.afrisoftech.lib.LabRequestJSON.getLabRequestBlis(connectDB, accessToken, requestNo, patientNo, patientName, paymentMode, schemeName, requesterAccount, patientType, test, amount,doa,dpt,pd,comments));

                    message = json.toString();
                    System.out.println("This is the LIMS request JSON String : " + message);
                    System.err.println("Lab Request" + com.afrisoftech.lib.LabRequestJSON.getLabRequestBlis(connectDB, accessToken, requestNo, patientNo, patientName, paymentMode, schemeName, requesterAccount, patientType, test, amount,doa,dpt,pd,comments));

                } catch (JSONException ex) {
                    Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestBody body = RequestBody.create(mediaType, message);
                Request request = null;

                String limsServerIP = com.afrisoftech.lib.LabRequestJSON.getLimsServerIPAdd(connectDB);
                String limsPort = com.afrisoftech.lib.LabRequestJSON.getLimsServerPort(connectDB);

                request = new Request.Builder()
                        .url("http://" + limsServerIP + "" + "/api/receiver") // for sandbox test cases        
                        .post(body)
                        .addHeader("api_key", accessToken)
                        .addHeader("system_id", "fansoft_bg")
                        .addHeader("lab_request", com.afrisoftech.lib.LabRequestJSON.getLabRequestBlis(connectDB, accessToken, requestNo, patientNo, patientName, paymentMode, schemeName, requesterAccount, patientType, test, amount,doa,dpt,pd,comments))
                        .addHeader("content-type", "application/json")
                        .build();
                System.err.println("URL-------------> http://" + limsServerIP + "" + "/api/receiver");
                System.err.println("Request : \n" + request);

                try {
                    java.sql.PreparedStatement pstmt21 = connectDB.prepareStatement("INSERT INTO public.hp_lims_request(  patient_no, patien_name, request_no, test, json_string, response)   VALUES (?, ?, ?, ?, ?, ?);");
                    pstmt21.setString(1, patientNo);
                    pstmt21.setString(2, patientName);
                    pstmt21.setString(3, requestNo);
                    pstmt21.setString(4, com.afrisoftech.lib.LabRequestJSON.getLimsRequestMap(connectDB, requestNo, patientNo).toString());
                    pstmt21.setObject(5, message);
                    pstmt21.setObject(6, "");
                    pstmt21.executeUpdate();
                } catch (java.sql.SQLException sq) {
                    sq.printStackTrace();

                }

                try {
                    Response response = client.newCall(request).execute();
                    JSONObject myJsonObject = null;
                    //  System.err.println("Response :"+response.body().string());
                    try {
                        myJsonObject = new JSONObject(response.body().string());
                    } catch (JSONException ex) {
                        Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (myJsonObject.toString().contains("error")) {
                        try {
//                    checkoutRequestID = myJsonObject.getString("errorMessage");
                            checkoutRequestStatus = false;
                            System.out.println("Checkout Request ID : [" + myJsonObject.getString("status") + "]");
                            javax.swing.JOptionPane.showMessageDialog(null, "Payment Request Error : " + myJsonObject.getString("status") + ". Try again.");

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    } else if (myJsonObject.toString().contains("Success")) {
                        try {
                            checkoutRequestStatus = true;
                            com.afrisoftech.hospital.GeneralBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                            com.afrisoftech.hospinventory.PatientsBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                            com.afrisoftech.accounting.InpatientDepositIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                            com.afrisoftech.accounting.InpatientRecpIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                            com.afrisoftech.hospital.HospitalMain.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                            com.afrisoftech.accounting.GovBillPaymentsIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                            System.out.println("Checout Request ID : [" + myJsonObject.getString("CheckoutRequestID") + "]");

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }

                    try {
                        java.sql.PreparedStatement pstmtCheckout = connectDB.prepareStatement("UPDATE hp_lims_request SET response = ? WHERE request_no = ?");
                        pstmtCheckout.setString(1, myJsonObject.toString());
                        pstmtCheckout.setString(2, requestNo);
                        pstmtCheckout.executeUpdate();

                    } catch (java.sql.SQLException sq) {
                        sq.printStackTrace();

                    }
                    System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }

        //return checkoutRequestStatus;
    }

    public static void sendSMS(String accessToken, String telephoneNumber, String smsText) {

        boolean checkoutRequestStatus = true;
        OkHttpClient client = new OkHttpClient();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setCalendar(calendar);
        String timeStamp = dateFormat.format(calendar.getTime());
        System.out.println("Timestamp : [" + dateFormat.format(calendar.getTime()) + "]");
        MediaType mediaType = MediaType.parse("application/json");

        String message = null;
        JSONObject json = new JSONObject();

        try {
            //Sent to Bliss
            json.put("phone", telephoneNumber);
            json.put("key", accessToken);
            json.put("message", smsText);

            message = json.toString();
     //       System.out.println("This is the SMS request JSON String : " + message);

        } catch (JSONException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestBody body = RequestBody.create(mediaType, message);
        Request request = null;

        request = new Request.Builder()
                .url("https://textbelt.com/text") // for sandbox test cases        
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        System.err.println("Request : \n" + request);

        try {
            Response response = client.newCall(request).execute();
            JSONObject myJsonObject = null;
            //  System.err.println("Response :"+response.body().string());
            try {
                myJsonObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (myJsonObject.toString().contains("error")) {
                try {
//                    checkoutRequestID = myJsonObject.getString("errorMessage");
                    checkoutRequestStatus = false;
                    System.out.println("Checkout Request ID : [" + myJsonObject.getString("status") + "]");
                    javax.swing.JOptionPane.showMessageDialog(null, "Payment Request Error : " + myJsonObject.getString("status") + ". Try again.");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else if (myJsonObject.toString().contains("Success")) {
                try {
                    checkoutRequestStatus = true;
                    com.afrisoftech.hospital.GeneralBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.hospinventory.PatientsBillingIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.InpatientDepositIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.InpatientRecpIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.hospital.HospitalMain.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    com.afrisoftech.accounting.GovBillPaymentsIntfr.checkoutRequestID = myJsonObject.getString("CheckoutRequestID");
                    System.out.println("Checout Request ID : [" + myJsonObject.getString("CheckoutRequestID") + "]");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }

            System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //return checkoutRequestStatus;
    }

    public static void sendProcessRequestStatus(String accessToken, String checkoutRequestID) {

        OkHttpClient client = new OkHttpClient();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setCalendar(calendar);
        String timeStamp = dateFormat.format(calendar.getTime());
        System.out.println("Timestamp : [" + dateFormat.format(calendar.getTime()) + "]");
        String password = "174379" + "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919" + timeStamp;
        String encodedPassword = Base64.toBase64String(password.getBytes());;

        System.out.println("Unencoded password : [" + password + "]");
        System.out.println("Encoded password : [" + encodedPassword + "]");
        MediaType mediaType = MediaType.parse("application/json");

        String message = null;
        JSONObject json = new JSONObject();
        try {
            json.put("BusinessShortCode", "174379");
            //json.put("Password", "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTYwMjE2MTY1NjI3");
            json.put("Password", encodedPassword);
            //json.put("Timestamp", "20160216165627");
            json.put("Timestamp", timeStamp);
            json.put("CheckoutRequestID", checkoutRequestID);
            message = json.toString();
            System.out.println("This is the JSON String : " + message);

        } catch (JSONException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestBody body = RequestBody.create(mediaType, message);

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/stkpushquery/v1/query")
                .post(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject myObject = null;
            try {
                myObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Response for Process Request : [" + myObject.toString() + "]");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    public static void processPayment(String billNumber, double billAmount, String payerTelephoneNumber) {
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/json");
//        String message = null;
//        JSONObject json = new JSONObject();
//        try {
//            json.put("amount", String.valueOf(billAmount));
//            json.put("phoneNumber", payerTelephoneNumber);
//            json.put("account", billNumber);
//            message = json.toString();
//            System.out.println("This is the JSON String : " + message);
//
//        } catch (JSONException ex) {
//            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        RequestBody body = RequestBody.create(mediaType, message);
//
//        Request request = new Request.Builder()
//                .url("http://46.101.32.103:3000/users/makePaymet")
//                .post(body)
//                    .addHeader("content-type", "application/json")
//                .build();
//
//        try {
//            // System.out.println("Printing process request Access Token : [" + accessToken + "] and Password : [" + Base64.encode("881100:bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919:201709211537".getBytes()) + "]");
//            post("", json);
//            Response response = client.newCall(request).execute();
//            System.out.println("Done sending payment request");
//            JSONObject myObject = null;
//            System.out.println(response.body().string());
//            try {
//                myObject = new JSONObject(response.body().string());
//            } catch (JSONException ex) {
//                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            System.out.println("Response for Process Request : [" + myObject.toString() + "]");
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            //Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    protected static String post(String url, org.json.JSONObject myJsonObject) {
//        String payload = "data={"
//                + "\"username\": \"admin\", "
//                + "\"first_name\": \"System\", "
//                + "\"last_name\": \"Administrator\""
//                + "}";
//        StringEntity entity = new StringEntity(payload,
//                ContentType.APPLICATION_JSON);
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost request = new HttpPost("http://46.101.32.103:3000/users/makePaymet");
//        request.setEntity(entity);
//
//        HttpResponse response = null;
//        try {
//            response = httpClient.execute(request);
//        } catch (IOException ex) {
//            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(response.getStatusLine().getStatusCode());
//
//        return "Success";
//    }
    public static boolean registerCallbackURL(String accessToken, String shortCode, String callBackURL, String validationURL) {
        boolean checkoutRequestStatus = true;

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        String message = null;
        JSONObject json = new JSONObject();
        try {
            json.put("ShortCode", shortCode);
            json.put("ConfirmationURL", callBackURL);
            json.put("ValidationURL", callBackURL);
            json.put("ResponseType", "Success");
            message = json.toString();
            System.out.println("This is the JSON String : " + message);

        } catch (JSONException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestBody body = RequestBody.create(mediaType, message);
        Request request = null;
        if (com.afrisoftech.hospital.HospitalMain.mobileTxTest) {
            request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl") // for sandbox test cases        
                    .post(body)
                    .addHeader("authorization", "Bearer " + accessToken)
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
            request = new Request.Builder()
                    .url("https://api.safaricom.co.ke/mpesa/c2b/v1/registerurl")
                    .post(body)
                    .addHeader("authorization", "Bearer " + accessToken)
                    .addHeader("content-type", "application/json")
                    .build();
        }
        try {
            Response response = client.newCall(request).execute();
            JSONObject myJsonObject = null;
            try {
                myJsonObject = new JSONObject(response.body().string());
            } catch (JSONException ex) {
                Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (myJsonObject.toString().contains("error")) {
                try {
//                    checkoutRequestID = myJsonObject.getString("errorMessage");
                    checkoutRequestStatus = false;
                    System.out.println("Checkout Request ID : [" + myJsonObject.getString("errorMessage") + "]");
                    javax.swing.JOptionPane.showMessageDialog(null, "Payment Request Error : " + myJsonObject.getString("errorMessage") + ". Try again.");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else if (myJsonObject.toString().contains("Success")) {
                try {
                    checkoutRequestStatus = true;

                    System.out.println("Checout Request ID : [" + myJsonObject.getString("CheckoutRequestID") + "]");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Response for Process Request : [" + myJsonObject.toString() + "]");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return checkoutRequestStatus;
    }
}
