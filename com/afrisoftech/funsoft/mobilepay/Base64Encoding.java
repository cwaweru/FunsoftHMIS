package com.afrisoftech.funsoft.mobilepay;

//import com.itextpdf.text.pdf.codec.Base64;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import org.bouncycastle.util.encoders.Base64;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Charles
 */
public class Base64Encoding {

    public static String encodetoBase64String(String stringtoEncode) {
        String auth = null;
        String accessToken = null;
        byte[] bytes = null;

        try {
            //bytes = usernameAndPassword.getBytes("ISO-8859-1");
            bytes = stringtoEncode.getBytes("ISO-8859-1");
            // bytes = stringtoEncode.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MobilePayAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        auth = Base64.toBase64String(bytes);
        System.out.println("New oAuth Access Token : [" + auth + "]");

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

                accessToken = myObject.getString("access_token");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "There is a problem connecting to mobile payments service provider. Please contact system administrator");
        }

        return accessToken;
       // return auth;
    }

}
