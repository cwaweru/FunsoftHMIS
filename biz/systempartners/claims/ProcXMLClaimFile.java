/*
 * XMLClaimFile.java
 *
 * Created on April 23, 2006, 6:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.claims;


/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class ProcXMLClaimFile {
    
    static java.awt.GridBagConstraints gridBagConstraints;// = new java.awt.GridBagConstraints();
    
    static javax.swing.JFrame xmlFrame;// = new javax.swing.JFrame("XML Data Viewer");
    
    static javax.swing.table.DefaultTableModel xmlDataModel;// = new javax.swing.table.DefaultTableModel();
    
    static javax.swing.JTable xmlDataTable;// = new javax.swing.JTable();
    
    static javax.swing.JScrollPane xmlDataTableScrollPane;// = new javax.swing.JScrollPane();
    
    static java.util.Vector columnIdentifiers;//= new java.util.Vector(1,1);
    
    static java.util.Vector dataVector;// = new java.util.Vector(1,1);
    
    static java.io.File file;
    
    static javax.swing.JFileChooser fileChooser;
    
    public static biz.systempartners.claims.XMLClaim xmlClaim = null;
    
    public static String medicalAidExpiry = null;
    
    public static String patientID;
    
//    public static String cardSerialNumber = null;
    /**
     * Creates a new instance of XMLClaimFile
     */
    public ProcXMLClaimFile() {
        
        xmlClaim = new biz.systempartners.claims.XMLClaim();
        
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
                    
                    java.io.BufferedInputStream xmlBufferInputStream = new java.io.BufferedInputStream(new java.io.FileInputStream(file));
                    
                    System.out.println("Dealing with file : "+file.getPath());
                    
                    //      saxParser.parse(xmlBufferInputStream, new org.xml.sax.helpers.DefaultHandler());
                    
                    xmlDomDocument = domBuilder.parse(xmlBufferInputStream);
                    
                    xmlDomDocument.normalizeDocument();
                    
                    printNodes(xmlDomDocument.getDocumentElement());
                    
                } catch(java.io.IOException ioEx){
                    
                    ioEx.printStackTrace();
                    
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "ERROR: PLEASE FORWARD FROM SMARTLINK!");
                    
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
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("CardSerialNumber")){
                    
                    cardNumber = nodeList.item(i).getTextContent().trim();
                    
                    System.out.println("Card Serial Number : ["+nodeList.item(i).getTextContent().trim()+"]");
                    //    xmlClaim.setPatientNumber(nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("PatientName")){
                    
                    xmlClaim.setPatientName(nodeList.item(i).getTextContent().trim());
                    
                    //  patientPartnerMaidenName = nodeList.item(i).getTextContent().trim();
                    
                    System.out.println("Printing Patient Names : "+nodeList.item(i).getTextContent().trim());
                    
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeMemberNumber")){
                    
                    xmlClaim.setSchemeMemberNumber(nodeList.item(i).getTextContent().trim());
                    //   patientForenames = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme member number "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("PatientNumber")){
                    
                    xmlClaim.setPatientNumber(nodeList.item(i).getTextContent().trim());
                    //  patientID = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing patient number "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("ServiceProvider")){
                    
                    xmlClaim.setServiceProvider(nodeList.item(i).getTextContent().trim());
                    //medicalAidNumber = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing service provider "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemeName")){
                    
                    xmlClaim.setSchemeName(nodeList.item(i).getTextContent().trim());
                    // patientSurName = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme name "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("SchemePayer")){
                    
                    xmlClaim.setSchemePayer(nodeList.item(i).getTextContent().trim());
                    // patientPartnerForNames = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme payer "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("AccountNumber")){
                    
                    xmlClaim.setAccountNumber(nodeList.item(i).getTextContent().trim());
                    // patientDOB = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing scheme account number : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("InvoiceNumber")){
                    
                    xmlClaim.setInvoiceNumber(nodeList.item(i).getTextContent().trim());
                    // patientAddress = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing address : "+nodeList.item(i).getTextContent().trim());
                }
                
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("InvoiceTotal")){
                    
                    xmlClaim.setInvoiceTotal(nodeList.item(i).getTextContent().trim());
                    // patientAddress = nodeList.item(i).getTextContent().trim();
                    System.out.println("Printing invoice total : "+nodeList.item(i).getTextContent().trim());
                }
                
                
                if (nodeList.item(i).getNodeName().equalsIgnoreCase("ClaimLines")){
                    org.w3c.dom.NodeList nodeListNr = nodeList.item(i).getChildNodes();
                    getClaimsTableModel().setRowCount(nodeListNr.getLength()/2);
                    //   ClaimsViewer.invoiceDataTable.
                    getClaimsTableModel().setColumnIdentifiers(new String[]{"Transaction Date","Item/Service Description","Quantity","Unit Cost (KSH)","Discount (KSH)","Total Transaction Cost (KSH)"});
                    
                    for (int j = 0; j < nodeListNr.getLength(); j++){
                        
                        if(nodeListNr.item(j).getNodeName().equalsIgnoreCase("Transaction") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("#text") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("tableRow") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("#comment") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("xmlTable") && !nodeListNr.item(j).getNodeName().equalsIgnoreCase("tableData")&& !nodeListNr.item(j).getNodeName().equalsIgnoreCase("tableColumnModel")){
                            
                            org.w3c.dom.NodeList nodeListTransaction = nodeListNr.item(j).getChildNodes();
                            
                            getClaimsTableModel().setColumnCount(nodeListTransaction.getLength()/2);
                            
                            for(int k = 0; k < nodeListTransaction.getLength(); k++){
                                
                                if(!nodeListTransaction.item(k).getNodeName().equalsIgnoreCase("#text") && !nodeListTransaction.item(k).getNodeName().equalsIgnoreCase("tableRow") && !nodeListTransaction.item(k).getNodeName().equalsIgnoreCase("#comment") && !nodeListTransaction.item(k).getNodeName().equalsIgnoreCase("xmlTable") && !nodeListTransaction.item(k).getNodeName().equalsIgnoreCase("tableData")&& !nodeListTransaction.item(k).getNodeName().equalsIgnoreCase("tableColumnModel")){
                                    
                                    claimsTableModel.setValueAt(nodeListTransaction.item(k).getTextContent().trim(), ((j + 1)/2) -1, ((k + 1)/2) - 1);
                                    
                                    System.out.println("Printing Transaction details : ["+nodeListTransaction.item(k).getTextContent().trim()+"]");
                                    
                              //      System.out.println("System k ------["+(k + 1)/2 +"]");
                                    
                                }
                                
                            }
                        }
                    }
                    
                }
                
            }
            
            if (nodeList.item(i).hasChildNodes()){
                
                printNodes(nodeList.item(i));
                
            }
            
        }
        
        if(cellsVector.size() > 0){
            
            dataVector.addElement(cellsVector);
            
        }
        
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {
       // this.dispose();
// TODO add your handling code here:
        
    }
    
    public static javax.swing.table.DefaultTableModel getClaimsTableModel() {
        return claimsTableModel;
    }
    
    public static void setClaimsTableModel(javax.swing.table.DefaultTableModel aClaimsTableModel) {
        claimsTableModel = aClaimsTableModel;
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
    public static javax.swing.table.DefaultTableModel claimsTableModel = new javax.swing.table.DefaultTableModel();
//}
    
}
