package com.afrisoftech.lib.pdf2excel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// import Apache HTTP Client v 4.3
import org.apache.http.*;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;

// import JSON
import org.json.*;

// import from JDK
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartJob {

    static JSONObject json = null;
    static String apiKey = null;
    static String outputFileName = null;
    static CheckThread checkThread = null;

    public static void startJob(String path2File, String initialpiKey, String outPutFileName) {
        //    String apiKey = "b1148e630177542bdb3d9f902b9b03bfea9ee40f";
        String endpoint = "https://api.zamzar.com/v1/jobs";
        String sourceFile = path2File;
        String targetFormat = "xlsx";
        apiKey = initialpiKey;
        outputFileName = outPutFileName;

        // Create HTTP client and request object
        CloseableHttpClient httpClient = getHttpClient(apiKey);
        HttpEntity requestContent = MultipartEntityBuilder.create()
                .addPart("source_file", new FileBody(new File(sourceFile)))
                .addPart("target_format", new StringBody(targetFormat, ContentType.TEXT_PLAIN))
                .build();
        HttpPost request = new HttpPost(endpoint);
        request.setEntity(requestContent);

        // Make request
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Extract body from response
        HttpEntity responseContent = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseContent, "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Parse result as JSON
        try {
            json = new JSONObject(result);
        } catch (JSONException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            Job.startJob(json.get("id").toString(), apiKey, outputFileName);
//        } catch (Exception ex) {
//            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("StartJob : " + json);
        checkThread = new CheckThread();
        checkThread.run();
        // Print result
  //      System.out.println("StartJob : " + json);
        try {
            System.out.println("StartJob ID : " + json.get("id").toString());
        } catch (JSONException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Finalise response and client
            response.close();
        } catch (IOException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            httpClient.close();
        } catch (IOException ex) {
            Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Creates a HTTP client object that always makes requests
    // that are signed with the specified API key via Basic Auth
    private static CloseableHttpClient getHttpClient(String apiKey) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(apiKey, ""));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        return httpClient;
    }

    private static class CheckThread extends Thread {

        public void run() {
            boolean check = true;
            while (check) {
                try {
                    System.out.println("Checking results ...");
                    Job.startJob(json.get("id").toString(), apiKey, outputFileName);
                    if(Job.checkThreads.equalsIgnoreCase("successful")){
                      //  DownloadFile.downLoadFile(json.get("id").toString(), apiKey, outputFileName);
                        check = false;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(StartJob.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Thread.sleep(3000);
                } catch (java.lang.InterruptedException intE) {
                    intE.printStackTrace();
                }
            }
        }

    }

}
