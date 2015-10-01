/*
 * PDFExeport.java
 *
 * Created on March 26, 2004, 9:36 AM
 */

package com.afrisoftech.dbadmin;

import java.io.IOException;
import org.openide.util.Exceptions;

/**
 *
 * @author  root
 */
public class PDFExport {
    
    java.lang.String fileString = null;
    
    javax.swing.JTable tableName2Export = null;
    
    java.lang.String exportTableName;
    
    java.io.BufferedOutputStream pdfOutputStream = null;
    
    java.io.BufferedOutputStream xmlOutputStream = null;
    
    java.lang.String refDocString = null;
    
    /** Creates a new instance of PDFExeport */
    public PDFExport(javax.swing.JTable table2Export, java.lang.String tableName) {
        
        tableName2Export = table2Export;
        
        exportTableName = tableName;
        
        createPDFExportFile();        
    }
   
    public void createPDFExportFile() {
        
        java.io.File pdfDocFile =  null;
        
        java.io.File tempXMLDocFile = null;
        
        com.lowagie.text.Document pdfDocument = null;
        
        System.out.println("Started PDF business ...");
        
        try {
            
            tempXMLDocFile = java.io.File.createTempFile("tempExport2PDFXMLLDoc", ".xml", new java.io.File(System.getProperty("docsdir")));
            
            pdfDocFile = java.io.File.createTempFile("ExportPDFDoc", ".pdf", new java.io.File(System.getProperty("docsdir")));
            
            pdfDocFile.deleteOnExit();
            
            tempXMLDocFile.deleteOnExit();
            
            pdfOutputStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(pdfDocFile));
            
            xmlOutputStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(tempXMLDocFile));
            
            System.out.println("File name ["+tempXMLDocFile.getAbsolutePath()+"]");
            
            fileString = tempXMLDocFile.getAbsolutePath();
            
            refDocString = "iText.dtd";
            
//            refDocString = "";
            
            
        } catch(java.io.IOException ioExec) {
            
            ioExec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
        
        com.afrisoftech.dbadmin.XmlSerializer xmlSerializer = new com.afrisoftech.dbadmin.XmlSerializer(tableName2Export, xmlOutputStream, refDocString, exportTableName);
        
        xmlSerializer.serializeTable2XML();
        
        try {
            
            System.out.println("Creating HTML document");
            
            pdfDocument = new com.lowagie.text.Document();

            
            com.lowagie.text.pdf.PdfWriter.getInstance(pdfDocument, pdfOutputStream);
            
//            htmlDocument.open();
            
            javax.xml.parsers.SAXParser pdfParser = javax.xml.parsers.SAXParserFactory.newInstance().newSAXParser();
            
            pdfParser.parse(fileString, new com.lowagie.text.xml.SAXiTextHandler(pdfDocument));
            
            System.out.println("Finished exporting and parsing file. Thank you!");
 
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table ["+exportTableName.toUpperCase()+"] to file ["+pdfDocFile.getAbsolutePath()+"]");        
         if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.awt.Desktop.getDesktop().open(pdfDocFile);
            } catch (IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                Exceptions.printStackTrace(ex);
            }
        }
//            htmlDocument.close();
            
        } catch(java.lang.Exception exec) {
            
            exec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), exec.getMessage());
            
        }
            
        
            try {
            
        pdfOutputStream.close();
        
        } catch(java.io.IOException ioExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
    }    
}
