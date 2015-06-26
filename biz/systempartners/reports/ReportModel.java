/*
 * ReportModel.java
 *
 * Created on 31 March 2008, 08:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.reports;

import javax.xml.parsers.ParserConfigurationException;

import org.jdesktop.swing.data.JavaBeanDataModel;

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 */
public class ReportModel {
    
    java.lang.String reportName = null;
    
    /** Creates a new instance of ReportModel */
    public ReportModel() {
        
    
    }
    
    public org.w3c.dom.Document getReportDocument(){
       
        return reportXMLDocument;
    }
    
    public static org.w3c.dom.Document generateReportDocument(java.lang.String reportFileName){
        
        reportXMLFile = new java.io.File(System.getProperty("reports.dir"), reportFileName);
        
        javax.xml.parsers.DocumentBuilderFactory documentFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
       
        javax.xml.parsers.DocumentBuilder documentBuilder = null;
       
        try {
         
            documentBuilder = documentFactory.newDocumentBuilder();
        
        } catch (ParserConfigurationException ex) {
        
            ex.printStackTrace();
        
        }

        reportXMLDocument = documentBuilder.newDocument();
        
        org.w3c.dom.Node reportNode = reportXMLDocument.createTextNode("reportNode");
        
        return reportXMLDocument;
        
    }
    
    public void addReportNode(java.lang.Object newReportNode, java.util.Vector newNodeAttributes){
        
    }
    
    public void retrieveReportModel(java.lang.String reportName){
        
    }
    
    public void saveReportDefinition(){
        
    }
    
    
  public static org.w3c.dom.Document reportXMLDocument; 
  
  public static java.io.File reportXMLFile;
  
  public static org.w3c.dom.Node reportNode;
}
