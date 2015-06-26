/*
 * XMLClaimFile.java
 *
 * Created on April 23, 2006, 6:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.claims;

import com.afrisoftech.hr.OrganisationalLeadershipIntfr;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class XMLClaimFile {
    
    static java.awt.GridBagConstraints gridBagConstraints;// = new java.awt.GridBagConstraints();
    
    static javax.swing.JFrame xmlFrame;// = new javax.swing.JFrame("XML Data Viewer");
    
    static javax.swing.table.DefaultTableModel xmlDataModel;// = new javax.swing.table.DefaultTableModel();
    
    static javax.swing.JTable xmlDataTable;// = new javax.swing.JTable();
    
    static javax.swing.JScrollPane xmlDataTableScrollPane;// = new javax.swing.JScrollPane();
    
    static java.util.Vector columnIdentifiers;//= new java.util.Vector(1,1);
    
    static java.util.Vector dataVector;// = new java.util.Vector(1,1);
    
    static java.io.File file;
    
    static javax.swing.JFileChooser fileChooser;
    
    static biz.systempartners.claims.Claim xmlClaim = null;
    
    public static String medicalAidExpiry = null;
    
    public static String patientID;
    
//    public static String cardSerialNumber = null;
    /**
     * Creates a new instance of XMLClaimFile
     */
    public XMLClaimFile() {
        
        xmlClaim = new biz.systempartners.claims.Claim();
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        
        xmlFrame = new javax.swing.JFrame("XML Data Viewer");
        
        xmlFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        xmlFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        
        
        
        xmlDataModel = new javax.swing.table.DefaultTableModel();
        
        xmlDataTable = new javax.swing.JTable();
        
        xmlDataTableScrollPane = new javax.swing.JScrollPane();
        
        columnIdentifiers = new java.util.Vector(1,1);
        
        dataVector = new java.util.Vector(1,1);
    }
    
    /**
     * @param args the command line arguments
     */
    //  public static void main(String[] args) {
    
    //    new XMLClaimFile();
    
    //     processFile(null);
    
    //}
    
    public static void processFile(java.io.File xmlClaimFile){
        
        if(xmlClaimFile == null){
            
            fileChooser = new javax.swing.JFileChooser();
            
            fileChooser.showOpenDialog(new java.awt.Frame());
            
            if(fileChooser.getSelectedFile() == null){
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "ERROR: Confirm that the smartcard reader settings are correct!");
                //    this.dispose();
                
            } else {
                
                file = fileChooser.getSelectedFile();
                
            }
            
            
        } else {
            
            file = xmlClaimFile;
            
        }
        
        org.w3c.dom.Document xmlDomDocument = null;
        
        javax.xml.parsers.DocumentBuilderFactory domFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        
        try {
            
            try {
                
                try {
                    
                    javax.xml.parsers.DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
                    
                    //    javax.xml.parsers.SAXParserFactory saxParserFactory = javax.xml.parsers.SAXParserFactory.newInstance();
                    
                    //    javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
                    
//                    saxParser.parse(file, new org.xml.sax.helpers.DefaultHandler());
                    
                    java.io.BufferedInputStream xmlBufferInputStream = new java.io.BufferedInputStream(new java.io.FileInputStream(file));
                    
                    System.out.println("Dealing with file : "+file.getPath());
                    
                    //      saxParser.parse(xmlBufferInputStream, new org.xml.sax.helpers.DefaultHandler());
                    
                    xmlDomDocument = domBuilder.parse(xmlBufferInputStream);
                    
                    xmlDomDocument.normalizeDocument();
                    
                    printNodes(xmlDomDocument.getDocumentElement());
                    
                    xmlDataModel.setDataVector(dataVector,columnIdentifiers);
                    
                    xmlDataTable.setModel(xmlDataModel);
                    
                    xmlClaim.setInvoiceTable(xmlDataTable);
                    
                } catch(java.io.IOException ioEx){
                    
                    ioEx.printStackTrace();
                    
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "ERROR: PLEASE FORWARD FROM SMARTLINK!;\nERROR DETAILS: \n["+ioEx.getMessage()+"]");
                    //System.exit(1);
                    // Claim.
                    
                    medicalAidCode.equals(null);
                    medicalAidNumber.equals(null);
                    patientSurName.equals(null);
                    patientForenames.equals(null);
                    cardBalance.equals(null);
                    cardNumber = null;
                    cardNr = null;
                    patientAddress = null;
                    patientCellPhone = null;
                    patientDOB = null;
                    patientSurName = null;
                    patientForenames = null;
                    cardBalance = null;
                    medicalAidNumber = null;
                    medicalAidPlan = null;
                    medicalAidCode = null;
                    patientNumber = null;
                    expiryDate = null;
                    patientPartnerForNames = null;
                    patientPartnerMaidenName = null;
                    patientEmail = null;
                    patientTelephone = null;
                    employerName = null;
                    patientBirthCountry = null;
                    patientSubDivision = null;
                    patientLocation = null;
                    patientBirthProvince = null;
                }
                
            } catch(org.xml.sax.SAXException saxEx){
                
                saxEx.printStackTrace();
                
                System.exit(1);
                
            }
            
        } catch(javax.xml.parsers.ParserConfigurationException parseEx){
            
            parseEx.printStackTrace();
            
            System.exit(1);
            
        }
        
    }
    
    private static void printNodes(org.w3c.dom.Node docNode){
        
        java.util.Vector cellsVector = new java.util.Vector(1,1);
        
        org.w3c.dom.NodeList nodeList = docNode.getChildNodes();
        
        for (int i = 0; i < nodeList.getLength(); i++){
            
            if(!nodeList.item(i).getNodeName().equalsIgnoreCase("#text") && !nodeList.item(i).getNodeName().equalsIgnoreCase("tableRow") && !nodeList.item(i).getNodeName().equalsIgnoreCase("#comment") && !nodeList.item(i).getNodeName().equalsIgnoreCase("xmlTable") && !nodeList.item(i).getNodeName().equalsIgnoreCase("tableData")&& !nodeList.item(i).getNodeName().equalsIgnoreCase("tableColumnModel")){
                
                //   System.out.println("Node ["+i+"], Element Name ["+nodeList.item(i).getNodeName()+"] is ["+nodeList.item(i).getTextContent().trim()+"]");
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("tableColumn")){
                    
                    columnIdentifiers.addElement(nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("tableCell")){
                    
                    cellsVector.addElement(nodeList.item(i).getTextContent().trim());
                    
                }
                /*
                java.lang.String patientNo;
                java.lang.String patientName;
                java.lang.String schemeMemberNo;
                java.lang.String schemeName;
                java.lang.String schemePayer;
                java.lang.String accountNo;
                java.lang.String invoiceNo;
                java.lang.String healthcareProviderID;
                 */
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("card_serialnumber")){
                    
                    cardNumber = nodeList.item(i).getTextContent().trim();
                    
                    System.out.println("Card Serial Number : ["+nodeList.item(i).getTextContent().trim()+"]");
                    //    xmlClaim.setPatientNumber(nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_partnermaidenname")){
                    
                    xmlClaim.setPatientName(nodeList.item(i).getTextContent().trim());
                    
                    patientPartnerMaidenName = nodeList.item(i).getTextContent().trim();
                    
                    System.out.println("Printing partner maiden name : "+nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_forenames")){
                    
                    xmlClaim.setSchemeMemberNumber(nodeList.item(i).getTextContent().trim());
                    patientForenames = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient forenames "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_id")){
                    
                    xmlClaim.setPatientID(nodeList.item(i).getTextContent().trim());
                    patientID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient ID "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("medicalaid_number")){
                    
                    xmlClaim.setMedicalAidNumber(nodeList.item(i).getTextContent().trim());
                    medicalAidNumber = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient ID "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_surname")){
                    
                    xmlClaim.setSchemeName(nodeList.item(i).getTextContent().trim());
                    patientSurName = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing surname "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_partnerfornames")){
                    
                    xmlClaim.setSchemePayer(nodeList.item(i).getTextContent().trim());
                    patientPartnerForNames = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient partner fornames "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_dob")){
                    
                    xmlClaim.setAccountNumber(nodeList.item(i).getTextContent().trim());
                    patientDOB = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient date of birth : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_address")){
                    
                    xmlClaim.setInvoiceNumber(nodeList.item(i).getTextContent().trim());
                    patientAddress = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing address : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_cellphone")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientCellPhone = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient cell phone : "+nodeList.item(i).getTextContent().trim());
                }
                if (nodeList.item(i).getNodeName().equalsIgnoreCase("Benefit")){
                    org.w3c.dom.NodeList nodeListNr = nodeList.item(i).getChildNodes();
                    
                    for (int j = 0; j < nodeListNr.getLength(); j++){
                        
                        if(!nodeListNr.item(i).getNodeName().equalsIgnoreCase("#text") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("tableRow") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("#comment") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("xmlTable") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("tableData")&& !nodeListNr.item(i).getNodeName().equalsIgnoreCase("tableColumnModel")){
                            
                            if(nodeListNr.item(i).getNodeName().equalsIgnoreCase("Nr")){
                                
                                xmlClaim.setHealthCareProvider(nodeListNr.item(i).getTextContent().trim());
                                cardNr = nodeListNr.item(i).getTextContent().trim();
                                System.out.println("Printing Nr : "+nodeListNr.item(i).getTextContent().trim());
                            }
                        }
                    }
                    
                }
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("Description")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    
                    System.out.println("Printing Description : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("medicalaid_expiry")){
                    
                    xmlClaim.setMedicalAidExpiry(nodeList.item(i).getTextContent().trim());
                    medicalAidExpiry = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing medicalaid_expiry: "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("Amount")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    cardBalance = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Amount : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("medicalaid_number")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    medicalAidNumber = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Medical Number : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_email")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientEmail = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Email : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_telephone")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientTelephone = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Patient Telephone : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_hospitalnumber")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientNumber = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Patient Number : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("medicalaid_plan")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    medicalAidPlan = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Medical Aid Plan : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("medicalaid_code")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    medicalAidCode = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Medical Aid Code : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("employer_name")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    employerName = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Employer Name : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_birthcountry")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientBirthCountry = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Patient Birth Country : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_subdivision")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientSubDivision = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Patient Sub Division : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_location")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientLocation = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Patient Location : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("patient_birthprovince")){
                    
                    xmlClaim.setHealthCareProvider(nodeList.item(i).getTextContent().trim());
                    patientBirthProvince = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing Amount : "+nodeList.item(i).getTextContent().trim());
                }
                
            }
            
            if (nodeList.item(i).hasChildNodes()){
                
                printNodes(nodeList.item(i));
                
            }
            
        }
        
        //     System.out.println("Cells Vector has capacity ["+cellsVector.size()+"]");
        
        if(cellsVector.size() > 0){
            
            dataVector.addElement(cellsVector);
            
        }
        
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {
        //this.dispose();
// TODO add your handling code here:
        
    }
    
    public static String cardNumber = null;
    public static String cardNr = null;
    public static String patientAddress = null;
    public static String patientCellPhone = null;
    public static String patientDOB = null;
    public static String patientSurName = null;
    public static String patientForenames = null;
    public static String cardBalance = null;
    public static String medicalAidNumber = null;
    public static String medicalAidPlan = null;
    public static String medicalAidCode = null;
    public static String patientNumber = null;
    public static String expiryDate = null;
    public static String patientPartnerForNames = null;
    public static String patientPartnerMaidenName = null;
    public static String patientEmail = null;
    public static String patientTelephone = null;
    public static String employerName = null;
    public static String patientBirthCountry = null;
    public static String patientSubDivision = null;
    public static String patientLocation = null;
    public static String patientBirthProvince = null;
    
}
