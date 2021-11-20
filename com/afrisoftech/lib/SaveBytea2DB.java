/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
////

/**
 *
 * @author Charles
 */
public class SaveBytea2DB {

    static java.sql.Connection connectDB = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SaveBytea2DB beteaTest = new SaveBytea2DB();
        beteaTest.testInsertBytea();
        System.exit(0);
        // TODO code application logic here
    }

    private void testInsertBytea() {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.showOpenDialog(new java.awt.Frame());
        java.io.File selectedFile = fileChooser.getSelectedFile();
        // this.insertBytea(selectedFile);
    }

    public static void insertBytea(java.sql.Connection connDB, java.io.File file, String extensionType, String mimeExtension, String documentRefNumber, String documentName, String documentSource) {

        connectDB = connDB;

        try {
            // java.sql.Connection connectDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5433/funsoft", "postgres", "xxx");

            connectDB.setAutoCommit(false);

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO funsoft_image_graphics("
                    + "            document_ref_no, document_description, "
                    + "            document_data, document_extension, document_mime_type, document_source)"
                    + "    VALUES (?, ?, ?, ?, "
                    + "            ?, ?);");
            java.io.FileInputStream fis;
            try {
                fis = new java.io.FileInputStream(file);
                pstmt.setString(1, documentRefNumber);
                pstmt.setString(2, file.getName());
                pstmt.setBinaryStream(3, fis, (int) file.length());
                pstmt.setString(4, extensionType);
                pstmt.setString(5, mimeExtension);
                pstmt.setString(6, documentSource);

                pstmt.execute();
                connectDB.commit();
                pstmt.close();
                /*
                 java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT * FROM test_binary ORDER BY 1 DESC LIMIT 1");
                 java.sql.ResultSet rs = pstmtR.executeQuery();
                 while (rs.next()) {
                 byte[] imgBytes = rs.getBytes(2);
                 java.io.ByteArrayOutputStream byteaStream = new java.io.ByteArrayOutputStream();
                 try {
                 byteaStream.write(imgBytes);
                 } catch (IOException ex) {
                 ex.printStackTrace();
                 Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 java.io.File tempFile = null;
                    
                 try {
                 tempFile = java.io.File.createTempFile("REP" + com.afrisoftech.lib.DateLables.getDateLabel() + "_", ".pdf");
                 tempFile.deleteOnExit();
                 java.io.OutputStream fileIS = new java.io.FileOutputStream(tempFile);
                 fileIS.write(imgBytes);
                 fileIS.close();
                 // this is where i am trying to display the file
                 //  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
                 } catch (IOException ex) {
                 ex.printStackTrace();
                 Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                 }

                 }
                 */
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insertByteArray(java.sql.Connection connDB, String documentRefNumber, byte[] documentData, String documentSource) {

        connectDB = connDB;

        try {

            connectDB.setAutoCommit(false);

            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO funsoft_image_graphics("
                    + "            document_ref_no, "
                    + "            document_data, document_source)"
                    + "    VALUES (?, "
                    + "            ?, ?);");

            pstmt.setString(1, documentRefNumber);
            pstmt.setBytes(2, documentData);
            pstmt.setString(3, documentSource);

            pstmt.execute();
            connectDB.commit();
            pstmt.close();

            javax.swing.JOptionPane.showMessageDialog(null, "Fingerprint data registered successully.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static byte[] getFingerprintImageByteArray(java.sql.Connection connDB, String documentRefNumber) {
        byte[] imageBytes = null;
        connectDB = connDB;
        try {
            java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT DISTINCT document_data, data_capture_time FROM funsoft_image_graphics  WHERE document_ref_no = ? AND document_source = 'DIGITAL_AUTH_FINGERPRINT' AND document_data IS NOT NULL ORDER BY data_capture_time DESC LIMIT 1");
            pstmtR.setString(1, documentRefNumber);
            java.sql.ResultSet rs = pstmtR.executeQuery();
            while (rs.next()) {
                imageBytes = rs.getBytes(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imageBytes;
    }

    public static java.io.File getStoredImage(java.sql.Connection connDB, String documentRefNumber) {
        java.io.File tempFile = null;
        connectDB = connDB;
        try {
            java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT DISTINCT document_data, data_capture_time FROM funsoft_image_graphics  WHERE document_ref_no = ?  AND document_source = 'OUT_PATIENT_REGISTER' ORDER BY data_capture_time DESC LIMIT 1");
            pstmtR.setString(1, documentRefNumber);
            java.sql.ResultSet rs = pstmtR.executeQuery();
            while (rs.next()) {
                byte[] imgBytes = rs.getBytes(1);
                java.io.ByteArrayOutputStream byteaStream = new java.io.ByteArrayOutputStream();
                try {
                    byteaStream.write(imgBytes);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    tempFile = java.io.File.createTempFile("REP" + com.afrisoftech.lib.DateLables.getDateLabel() + "_", ".jpg");
                    tempFile.deleteOnExit();
                    java.io.OutputStream fileIS = new java.io.FileOutputStream(tempFile);
                    fileIS.write(imgBytes);
                    fileIS.close();
                    System.out.println(tempFile.getPath());
                    // this is where i am trying to display the file
                    //  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempFile;
    }

    public static com.itextpdf.text.Image getImage(java.sql.Connection connDB, String documentRefNumber) {
        java.io.File tempFile = null;
        Image image = null;
        connectDB = connDB;
        try {
            java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT DISTINCT document_data, data_capture_time FROM funsoft_image_graphics  WHERE document_ref_no = ? ORDER BY data_capture_time DESC LIMIT 1");
            pstmtR.setString(1, documentRefNumber);
            java.sql.ResultSet rs = pstmtR.executeQuery();
            while (rs.next()) {
                byte[] imgBytes = rs.getBytes(1);
                java.io.ByteArrayOutputStream byteaStream = new java.io.ByteArrayOutputStream();
                try {
                    byteaStream.write(imgBytes);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    tempFile = java.io.File.createTempFile("REP" + com.afrisoftech.lib.DateLables.getDateLabel() + "_", ".jpg");
                    tempFile.deleteOnExit();
                    java.io.OutputStream fileIS = new java.io.FileOutputStream(tempFile);
                    fileIS.write(imgBytes);
                    try {
                        image = Image.getInstance(tempFile.getPath());
                    } catch (BadElementException ex) {
                        ex.printStackTrace();
                    } catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(tempFile.getPath());
                    // this is where i am trying to display the file
                    //  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;
    }

    public static com.itextpdf.text.Image getImage(java.sql.Connection connDB, String documentRefNumber, java.sql.Timestamp date) {
        java.io.File tempFile = null;
        Image image = null;
        connectDB = connDB;
        try {
            java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT DISTINCT document_data, data_capture_time FROM funsoft_image_graphics  WHERE document_ref_no = ? AND data_capture_time::date = ? ORDER BY data_capture_time DESC LIMIT 1");
            pstmtR.setString(1, documentRefNumber);
            pstmtR.setTimestamp(2, date);
            java.sql.ResultSet rs = pstmtR.executeQuery();
            while (rs.next()) {
                byte[] imgBytes = rs.getBytes(1);
                java.io.ByteArrayOutputStream byteaStream = new java.io.ByteArrayOutputStream();
                try {
                    byteaStream.write(imgBytes);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    tempFile = java.io.File.createTempFile("REP" + com.afrisoftech.lib.DateLables.getDateLabel() + "_", ".png");
                    tempFile.deleteOnExit();
                    java.io.OutputStream fileIS = new java.io.FileOutputStream(tempFile);
                    fileIS.write(imgBytes);
                    try {
                        image = Image.getInstance(tempFile.getPath());
                    } catch (BadElementException ex) {
                        ex.printStackTrace();
                    } catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(tempFile.getPath());
                    // this is where i am trying to display the file
                    //  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;
    }

    public static java.io.ByteArrayOutputStream getImageBytea(java.sql.Connection connDB, String documentRefNumber, java.sql.Timestamp date) {
        java.io.File tempFile = null;
        Image image = null;
        connectDB = connDB;
        java.io.ByteArrayOutputStream byteaStream = null;
        try {
            java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT DISTINCT document_data, data_capture_time FROM funsoft_image_graphics  WHERE document_ref_no = ? AND data_capture_time = ? ORDER BY data_capture_time DESC LIMIT 1");
            pstmtR.setString(1, documentRefNumber);
            pstmtR.setTimestamp(2, date);
            java.sql.ResultSet rs = pstmtR.executeQuery();
            while (rs.next()) {
                byte[] imgBytes = rs.getBytes(1);
                byteaStream = new java.io.ByteArrayOutputStream();
                try {
                    byteaStream.write(imgBytes);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return byteaStream;
    }

    public static byte[] getImageByteArray(java.sql.Connection connDB, String documentRefNumber) {
        byte[] imageBytes = null;
        connectDB = connDB;
         try {
            java.sql.PreparedStatement pstmtR = connectDB.prepareStatement("SELECT DISTINCT document_data, data_capture_time FROM funsoft_image_graphics  WHERE document_ref_no = ? ORDER BY data_capture_time DESC LIMIT 1");
            pstmtR.setString(1, documentRefNumber);
            java.sql.ResultSet rs = pstmtR.executeQuery();
            while (rs.next()) {
                imageBytes = rs.getBytes(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaveBytea2DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imageBytes;
    }
}
