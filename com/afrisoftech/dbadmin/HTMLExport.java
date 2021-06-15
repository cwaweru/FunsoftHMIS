/*
 * HTMLExport.java
 *
 * Created on March 26, 2004, 9:34 AM
 */

package com.afrisoftech.dbadmin;

import java.io.IOException;
//

/**
 *
 * @author  root
 */
public class HTMLExport {
    
    java.lang.String fileString = null;
    
    javax.swing.JTable tableName2Export = null;
    
    java.lang.String exportTableName;
    
    java.io.BufferedOutputStream htmlOutputStream = null;
    
    java.io.BufferedOutputStream xmlOutputStream = null;
    
    java.lang.String refDocString = null;
    
    /** Creates a new instance of HTMLExport */
    public HTMLExport(javax.swing.JTable table2Export, java.lang.String tableName) {
        
        tableName2Export = table2Export;
        
        exportTableName = tableName;
        
        createHTMLExportFile();
        
    }
    
    public void createHTMLExportFile() {
        
        java.io.File htmlDocFile =  null;
        
        java.io.File tempXMLDocFile = null;
        
        com.lowagie.text.Document htmlDocument = null;
        
        System.out.println("Started HTML business ...");
        
        try {
            
            tempXMLDocFile = java.io.File.createTempFile("tempExportXMLLDoc", ".xml", new java.io.File(System.getProperty("docsdir")));
            
            htmlDocFile = java.io.File.createTempFile("ExportHTMLDoc", ".html", new java.io.File(System.getProperty("docsdir")));
            
            htmlDocFile.deleteOnExit();
            
            tempXMLDocFile.deleteOnExit();
            
            htmlOutputStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(htmlDocFile));
            
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
            
            htmlDocument = new com.lowagie.text.Document();

            
            com.lowagie.text.html.HtmlWriter.getInstance(htmlDocument, htmlOutputStream);
            
//            htmlDocument.open();
            
            javax.xml.parsers.SAXParser htmlParser = javax.xml.parsers.SAXParserFactory.newInstance().newSAXParser();
            
            htmlParser.parse(fileString, new com.lowagie.text.xml.SAXiTextHandler(htmlDocument));

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table ["+exportTableName.toUpperCase()+"] to file ["+htmlDocFile.getAbsolutePath()+"]");        
            
            System.out.println("Finished exporting and parsing file. Thank you!");

                    if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.awt.Desktop.getDesktop().open(htmlDocFile);
            } catch (IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                            ex.printStackTrace();             //ex.printStackTrace();
            }
        }
//            htmlDocument.close();
            
        } catch(java.lang.Exception exec) {
            
            exec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), exec.getMessage());
            
        }
    }
    
}
