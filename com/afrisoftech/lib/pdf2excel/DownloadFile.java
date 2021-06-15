/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib.pdf2excel;

/**
 *
 * @author root
 */
// import Apache HTTP Client v 4.3
import org.apache.http.*;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;

// import from JDK
import java.io.*;

public class DownloadFile {

    public static void downLoadFile(String fileID, String apiKey, String localFileName) throws Exception {
        int fileId = Integer.parseInt(fileID);
    //    String apiKey = "GiVUYsF4A8ssq93FR48H";
        System.out.println("Download ID : "+fileId);
        String endpoint = "https://api.zamzar.com/v1/files/" + fileID + "/content";
        String localFilename = localFileName; //"/tmp/portrait.png";

        // Create HTTP client and request object
        CloseableHttpClient httpClient = getHttpClient(apiKey);
        HttpGet request = new HttpGet(endpoint);

        // Make request
        CloseableHttpResponse response = httpClient.execute(request);

        // Extract body from response
        HttpEntity responseContent = response.getEntity();

        // Save response content to file on local disk
        BufferedInputStream bis = new BufferedInputStream(responseContent.getContent());
        FileOutputStream fos = new FileOutputStream(localFilename);
        
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        
        int inByte;
        while((inByte = bis.read()) != -1) {
            bos.write(inByte);
        }

        // Print success message
        System.out.println("File downloaded");

        // Finalise response, client and streams
        response.close();
        httpClient.close();
        bos.close();
        bis.close();
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
