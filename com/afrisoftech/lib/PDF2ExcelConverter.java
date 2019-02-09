/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author root
 */
public class PDF2ExcelConverter {

    private static List<String> formats = Arrays.asList(new String[]{"csv", "xml", "xlsx-single", "xlsx-multiple"});

    public static void convertPDf2Excel(String pdfFile2Convert) throws Exception {
        if (pdfFile2Convert.length() < 3) {
            System.out.println("File to convert is mandatory!");
            javax.swing.JOptionPane.showMessageDialog(null, "File to convert is mandatory!");
        } else {

            final String apiKey = "ktxpfvf0i5se";
            final String format = "xlsx-single".toLowerCase();
            final String pdfFilename = pdfFile2Convert;

            if (!formats.contains(format)) {
                System.out.println("Invalid output format: \"" + format + "\"");
            }

            // Avoid cookie warning with default cookie configuration
            RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();

            File inputFile = new File(pdfFilename);

            if (!inputFile.canRead()) {
                System.out.println("Can't read input PDF file: \"" + pdfFilename + "\"");
                javax.swing.JOptionPane.showMessageDialog(null, "File to convert is mandatory!");
            }

            try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build()) {
                HttpPost httppost = new HttpPost("https://pdftables.com/api?format=" + format + "&key=" + apiKey);
                FileBody fileBody = new FileBody(inputFile);

                HttpEntity requestBody = MultipartEntityBuilder.create().addPart("f", fileBody).build();
                httppost.setEntity(requestBody);

                System.out.println("Sending request");

                try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                    if (response.getStatusLine().getStatusCode() != 200) {
                        System.out.println(response.getStatusLine());
                        javax.swing.JOptionPane.showMessageDialog(null, "Internet connection is a must. Consult IT administrator for further assistance");
                    }
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        final String outputFilename = getOutputFilename(pdfFilename, format.replaceFirst("-.*$", ""));
                        System.out.println("Writing output to " + outputFilename);

                        final File outputFile = new File(outputFilename);
                        FileUtils.copyInputStreamToFile(resEntity.getContent(), outputFile);
                        if (java.awt.Desktop.isDesktopSupported()) {
                            try {
                                java.awt.Desktop.getDesktop().open(outputFile);
                            } catch (IOException ex) {
                                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                                ex.printStackTrace();             //Exceptions.printStackTrace(ex);
                            }
                        }
                    } else {
                        System.out.println("Error: file missing from response");
                        javax.swing.JOptionPane.showMessageDialog(null, "Error: file missing from response! Internet connection is a must.");
                    }
                }
            }
        }
    }

    private static String getOutputFilename(String pdfFilename, String suffix) {
        if (pdfFilename.length() >= 5 && pdfFilename.toLowerCase().endsWith(".pdf")) {
            return pdfFilename.substring(0, pdfFilename.length() - 4) + "." + suffix;
        } else {
            return pdfFilename + "." + suffix;
        }
    }

}
