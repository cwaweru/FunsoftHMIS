/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib.pdf2excel;

// import Apache HTTP Client v 4.3
import org.apache.http.*;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;

// import JSON
import org.json.*;

public class Job {

    public static String checkThreads = null;
    public static void startJob(String jobID, String apiKey, String outputFileName) throws Exception {
        int jobId = Integer.parseInt(jobID);
   //     String apiKey = "GiVUYsF4A8ssq93FR48H";
    System.out.println("JobID :"+jobId);
        String endpoint = "https://api.zamzar.com/v1/jobs/" + jobId;

        // Create HTTP client and request object
        CloseableHttpClient httpClient = getHttpClient(apiKey);
        HttpGet request = new HttpGet(endpoint);

        // Make request
        CloseableHttpResponse response = httpClient.execute(request);

        // Extract body from response
        HttpEntity responseContent = response.getEntity();
        String result = EntityUtils.toString(responseContent, "UTF-8");

        // Parse result as JSON
        JSONObject json = new JSONObject(result);

        // Print result
        checkThreads = json.get("status").toString();
       
        System.out.println("Job JSON : "+json);
        
        if(json.get("status").toString().equalsIgnoreCase("successful")){
        
        System.out.println("Job : "+json.get("target_files").toString()); //.replace("[", "").replace("]", ""));
  
        String jsonResult = json.get("target_files").toString().replace("[", "").replace("]", "");
        
        JSONObject results = new JSONObject(jsonResult);
     
        DownloadFile.downLoadFile(results.get("id").toString(), apiKey, outputFileName);
        
        }
        // Finalise response and client
        response.close();
        httpClient.close();
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
}
