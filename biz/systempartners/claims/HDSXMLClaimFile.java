/*
 * HDSXMLClaimFile.java
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
public class HDSXMLClaimFile {
    
    static java.awt.GridBagConstraints gridBagConstraints;// = new java.awt.GridBagConstraints();
    
    static javax.swing.JFrame xmlFrame;// = new javax.swing.JFrame("XML Data Viewer");
    
    static javax.swing.table.DefaultTableModel xmlDataModel;// = new javax.swing.table.DefaultTableModel();
    
    static javax.swing.JTable xmlDataTable;// = new javax.swing.JTable();
    
    static javax.swing.JScrollPane xmlDataTableScrollPane;// = new javax.swing.JScrollPane();
    
    static java.util.Vector columnIdentifiers;//= new java.util.Vector(1,1);
    
    static java.util.Vector dataVector;// = new java.util.Vector(1,1);
    
    static java.io.File file;
    
    static javax.swing.JFileChooser fileChooser;
    
    static biz.systempartners.claims.HDSClaim xmlClaim = null;
    
    static String mainMemberMedisatNumber;
    
    public static String memberMedisatNumber;
    
    public static String memberSurname;
    
    public static String memberFullName;
    
    public static String shemeMemberID;
    
    public static String schemeMainMemberID;
    
    public static String schemeID;
    
    public static String schemeName;
    
    public static String relationship;
    
    public static String schemeAdminMainMemberID;
    
    public static String schemeAdminMember;
    
    public static String schemeAdminName;
    
    public static String schemeAdminID;
    
    public static String status;
    
    public static String authentication;
    
    public static String pin;
    
    public static String gender;
    
    public static String maritalStatus;
    
    public static String bloodGroup;
    
    public static String nationalIDNumber;
    
    public static String postalAddress;
    
    public static String physicalAddress;
    
 //   private static int i;
    
    public static String emailAddress;
    
    public static String telephoneNumber;
    
//    public static String cardSerialNumber = null;
    /**
     * Creates a new instance of XMLClaimFile
     */
    public HDSXMLClaimFile() {
        
        xmlClaim = new biz.systempartners.claims.HDSClaim();
        
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
                
                //this.dispose();
                
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
                    
                    //   System.exit(1);
                    
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
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("MainMemberMedisatNumber")){
                    
                    mainMemberMedisatNumber = nodeList.item(i).getTextContent().trim();
                    
                    System.out.println("Main Member Medisat Number : ["+nodeList.item(i).getTextContent().trim()+"]");
                    xmlClaim.setMainMemberMedisatNumber(nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("MemberMedisatNumber")){
                    
                    xmlClaim.setMemberMedisatNumber(nodeList.item(i).getTextContent().trim());
                    
                    memberMedisatNumber = nodeList.item(i).getTextContent().trim();
                    
                    System.out.println("Printing member medisat number : "+nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("MemberSurname")){
                    
                    xmlClaim.setMemberSurname(nodeList.item(i).getTextContent().trim());
                    memberSurname = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing member surname : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("MemberFullName")){
                    
                    xmlClaim.setMemberFullName(nodeList.item(i).getTextContent().trim());
                    memberFullName = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing member full name : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeMemberID")){
                    
                    xmlClaim.setSchemeMemberID(nodeList.item(i).getTextContent().trim());
                    shemeMemberID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme member ID : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeMainMemberID")){
                    
                    xmlClaim.setSchemeMainMemberID(nodeList.item(i).getTextContent().trim());
                    schemeMainMemberID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme main member ID : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeID")){
                    
                    xmlClaim.setSchemeID(nodeList.item(i).getTextContent().trim());
                    schemeID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme ID : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeName")){
                    
                    xmlClaim.setSchemeName(nodeList.item(i).getTextContent().trim());
                    setSchemeName(nodeList.item(i).getTextContent().trim());
                    System.out.println("Printing scheme name : "+nodeList.item(i).getTextContent().trim());
                }
                if (nodeList.item(i).getNodeName().equalsIgnoreCase("ContactTelephoneNumbers")){
                    org.w3c.dom.NodeList nodeListNr = nodeList.item(i).getChildNodes();
                    
                    for (int j = 0; j < nodeListNr.getLength(); j++){
                        
                        if(!nodeListNr.item(i).getNodeName().equalsIgnoreCase("#text") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("tableRow") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("#comment") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("xmlTable") && !nodeListNr.item(i).getNodeName().equalsIgnoreCase("tableData")&& !nodeListNr.item(i).getNodeName().equalsIgnoreCase("tableColumnModel")){
                            
                            if(nodeListNr.item(i).getNodeName().equalsIgnoreCase("TelephoneNumber")){
                                
                                xmlClaim.setTelephoneNumber(nodeListNr.item(i).getTextContent().trim());
                                telephoneNumber = nodeListNr.item(i).getTextContent().trim();
                                System.out.println("Printing office mobile telephone number : "+nodeListNr.item(i).getTextContent().trim());
                            }
                        }
                    }
                    
                }
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("Relationship")){
                    
                    xmlClaim.setRelationship(nodeList.item(i).getTextContent().trim());
                    relationship = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing relationship : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeAdminMainMemberID")){
                    
                    xmlClaim.setSchemeAdminMainMemberID(nodeList.item(i).getTextContent().trim());
                    schemeAdminMainMemberID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme main member ID : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeAdminMemberID")){
                    
                    xmlClaim.setSchemeAdminMemberID(nodeList.item(i).getTextContent().trim());
                    schemeAdminMember = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme admin member ID : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeAdminName")){
                    
                    xmlClaim.setSchemeAdminName(nodeList.item(i).getTextContent().trim());
                    schemeAdminName = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme admin name : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeAdminID")){
                    
                    xmlClaim.setSchemeAdminID(nodeList.item(i).getTextContent().trim());
                    schemeAdminID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme admin ID : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("Status")){
                    
                    xmlClaim.setStatus(nodeList.item(i).getTextContent().trim());
                    status = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing status : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("Authentication")){
                    
                    xmlClaim.setAuthentication(nodeList.item(i).getTextContent().trim());
                    authentication = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing authentication : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("PIN")){
                    
                    xmlClaim.setPIN(nodeList.item(i).getTextContent().trim());
                    pin = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing PIN : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("Gender")){
                    
                    xmlClaim.setGender(nodeList.item(i).getTextContent().trim());
                    gender = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing gender : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("MaritalStatus")){
                    
                    xmlClaim.setMaritalStatus(nodeList.item(i).getTextContent().trim());
                    maritalStatus = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing marital status : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("BloodGroup")){
                    
                    xmlClaim.setBloodGroup(nodeList.item(i).getTextContent().trim());
                    bloodGroup = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing blood group : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("NationalIDNumber")){
                    
                    xmlClaim.setNationalIDNumber(nodeList.item(i).getTextContent().trim());
                    nationalIDNumber = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing national ID number : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("PostalAddress")){
                    
                    xmlClaim.setPostalAddress(nodeList.item(i).getTextContent().trim());
                    postalAddress = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing postal address : "+nodeList.item(i).getTextContent().trim());
                }
                
            }
            
            if(nodeList.item(i).getNodeName().equalsIgnoreCase("PhysicalAddress")){
                
                xmlClaim.setPhysicalAddress(nodeList.item(i).getTextContent().trim());
                physicalAddress = nodeList.item(i).getTextContent().trim();
                System.out.println("Printing physical address : "+nodeList.item(i).getTextContent().trim());
            }
            
     //   }
        
        if(nodeList.item(i).getNodeName().equalsIgnoreCase("EmailAddress")){
            
            xmlClaim.setEmailAddress(nodeList.item(i).getTextContent().trim());
            emailAddress = nodeList.item(i).getTextContent().trim();
            System.out.println("Printing email address : "+nodeList.item(i).getTextContent().trim());
        }
        
        
        if (nodeList.item(i).getNodeName().equalsIgnoreCase("MedicalCover")){
            org.w3c.dom.NodeList nodeListNr = nodeList.item(i).getChildNodes();
            
            for (int j = 0; j < nodeListNr.getLength(); j++){
                
                if(!nodeListNr.item(j).getNodeName().equalsIgnoreCase("#text") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("tableRow") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("#comment") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("xmlTable") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("tableData")&& !nodeListNr.item(j).getNodeName().equalsIgnoreCase("tableColumnModel")){
                    
                    if(nodeListNr.item(j).getNodeName().equalsIgnoreCase("TelephoneNumber")){
                        
                        xmlClaim.setTelephoneNumber(nodeListNr.item(j).getTextContent().trim());
                        telephoneNumber = nodeListNr.item(j).getTextContent().trim();
                        System.out.println("Printing office mobile telephone number : "+nodeListNr.item(j).getTextContent().trim());
                    }
                }
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
  //        this.dispose();
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
    
    public static String getSchemeName() {
        return schemeName;
    }
    
    public static void setSchemeName(String aSchemeName) {
        schemeName = aSchemeName;
    }
    
}
