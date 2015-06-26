/*
 * Claim.java
 *
 * Created on July 2, 2006, 10:53 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package biz.systempartners.claims;


import java.io.IOException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 */
public class HDSClaim implements java.lang.Runnable {
    
    java.lang.String txDate;
    
    java.lang.String txTime;
    
    org.w3c.dom.Element claimLinesElement = null;

    public String schemeAdministratorID = null;

    public Element visitElement = null;

    public String visitID = null;

    public String txID = null;

    public Element txIDElement =null;

    public String benefitAffected = null;

    public String servicePoint = null;

    public Element serviceCodeElement = null;

    public String serviceDescription =null;

    public String serviceCode = null;

    public String providerID =null;

    public String providerName =null;

    public String schemeAdministrator = null;

    public String schemeID = null;

    public String schemeAdministratorNumber = null;

    public String schemeMembershipNumber = null;

    public String surname = null;

    public String employeeNumber = null;

    public String otherNames = null;
    
    /** Creates a new instance of Claim */
    public HDSClaim() {
        
        threadExport2XML = new java.lang.Thread(this, "SerializeClaim2XML");
        
        readXMLClaimThread = new ReadXMLClaimFileThread();
        
    }
    /**
     * Setter method for the patient ID.
     */
    
    public void setPatientNumber(java.lang.String patientNumber){
        
        patientNo = patientNumber;
        
    }
    
    /**
     * Setter method for the name of the patient.
     */
    
    public void setPatientName(java.lang.String nameofPatient){
        
        patientName = nameofPatient;
        
    }
    /**
     * Setter method for the scheme member/beneficiary number/ID
     */
    
    public void setSchemeMemberNumber(java.lang.String schemeMemberNumber){
        
        schemeMemberNo = schemeMemberNumber;
        
    }
    /**
     * Setter method for the name of the scheme administrator.
     */
    
    public void setSchemeName(java.lang.String nameofScheme){
        
        schemeName = nameofScheme;
        
    }
    /**
     * Setter method for the scheme payer.
     */
    
    public void setSchemePayer(java.lang.String payerName){
        
        schemePayer = payerName;
        
    }
    /**
     * Setter method for the scheme account number/ID.
     */
    
    public void setAccountNumber(java.lang.String accountNumber){
        
        accountNo = accountNumber;
        
    }
    /**
     * Setter method for the claim invoice number
     */
    
    public void setInvoiceNumber(java.lang.String invoiceNumber){
        
        invoiceNo = invoiceNumber;
        
    }
    /**
     * Setter method for the health care provider.
     */
    
    public void setHealthCareProvider(java.lang.String hospitalName){
        
        healthcareProviderID = hospitalName;
        
    }
    
    /**
     * Setter method for the invoice table.
     */
    
    public void setInvoiceTable(javax.swing.JTable ivoiceItemsTable){
        
        //invoiceTable = ivoiceItemsTable;
        
    }
    /**
     * Getter method for the patient ID.
     */
    
    public java.lang.String getPatientNumber(){
        
        return patientNo;
        
    }
    /**
     * Getter method for the name of the patient.
     */
    
    
    public java.lang.String getPatientName(){
        
        return patientName;
        
    }
    /**
     * Getter method for the scheme member/beneficiary number.
     */
    
    public java.lang.String getSchemeMemberNumber(){
        
        return schemeMemberNo;
        
    }
    /**
     * Getter method for the scheme administrator name.
     */
    
    public java.lang.String getSchemeName(){
        
        return schemeName;
        
    }
    /**
     * Getter method for the scheme payer name.
     */
    
    public java.lang.String getSchemePayer(){
        
        return schemePayer;
        
    }
    /**
     * Getter method for the scheme account number.
     */
    
    public java.lang.String getAccountNumber(){
        
        return accountNo;
        
    }
    /**
     * Getter method for the claim invoice number.
     */
    
    public java.lang.String getInvoiceNumber(){
        
        return invoiceNo;
        
    }
    /**
     * Getter method for the health care provider.
     */
    
    public java.lang.String getHealthCareProvider(){
        
        return healthcareProviderID;
        
    }
    
    /**
     * Getter method for the invoice table.
     */
    
    public javax.swing.JTable getInvoiceTable(){
        
        return invoiceTable;
        
    }
    /**
     * Serializing the claim object to an XML file.
     */
    
    public void serializeClaim2XML(){
        
        this.createXMLDoc();
        
        //threadExport2XML.start();
        
    }
    
    public void readClaimFromXMLFile(){
        
        
        
    }
    
    public java.io.File getClaimFile2Send(){
        
        System.out.println("Claim file name >>>> : "+xmlDocFile);
        
        return xmlDocFile;
        
    }
    
    /**
     * Send claim to a Scheme or Payer.
     */
    
    public void sendClaimViaEmail(java.io.File xmlClaimFile, java.lang.String schemeMailAddress){
        
//        this.serializeClaim2XML();
        
        biz.systempartners.claims.SendClaim.sendClaim(xmlClaimFile, schemeMailAddress);
        
    }
    
    public void createXMLDoc() {}
    public void createXMLDoc(javax.swing.JTable invoicesTable) {
        // java.io.File xmlDocFile =  null;
        
        org.w3c.dom.Document xmlDocument = null;
        
        System.out.println("Started XML claim serialization ...");
        
        System.getProperty("docsdir",System.getProperty("user.dir"));
        
        try {
            
            // xmlDocFile = java.io.File.createTempFile("CLAIM"+biz.systempartners.claims.DateLables.getDateLabel()+"_", ".xml", new java.io.File(System.getProperty("docsdir")));
            xmlDocFile = new java.io.File(System.getProperty("docsdir"),"HospitalClaimsFile.xml");//.createTempFile("HospitalClaimsFile", ".xml", new java.io.File(System.getProperty("docsdir")));
            
            //    xmlDocFile.deleteOnExit();
            
            xmlOutpuStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(xmlDocFile));
            
            System.out.println("File name ["+xmlDocFile.getAbsolutePath()+"]");
            
            fileString = xmlDocFile.getAbsolutePath();
            
            javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            
            try {
                
                javax.xml.parsers.DocumentBuilder xmlDocBuilder = documentBuilderFactory.newDocumentBuilder();
                
                initXmlDoc = xmlDocBuilder.newDocument();
                
                workonXmlDocument(initXmlDoc, invoicesTable);
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table ["+tableName.toUpperCase()+"] to file ["+xmlDocFile.getAbsolutePath()+"]");
                
                
            } catch(javax.xml.parsers.ParserConfigurationException parserExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), parserExec.getMessage());
                
            }
            
        } catch(java.io.IOException ioExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
        
    }
    
    public void workonXmlDocument(org.w3c.dom.Document xmlDoc) {}
    
    public void workonXmlDocument(org.w3c.dom.Document xmlDoc, javax.swing.JTable invoiceTable) {
        
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        java.util.Date date = calendar.getTime();
        
        com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(date, "dd/MM/yyyy");
        
        com.afrisoftech.lib.DateFormatter timeFormatter = new com.afrisoftech.lib.DateFormatter(date, "HH:mm:ss");
        
//        java.text.SimpleDateFormat dateFormat = java.text.SimpleDateFormat.
        
        txDate = dateFormatter.getDateString();
        
        txTime = timeFormatter.getDateString();
        
        java.lang.Object[] tableColumns = this.getTableColumns(invoiceTable);
        
        int rowCount = invoiceTable.getRowCount();
        
        int columnCount = invoiceTable.getColumnCount();
        
        ////  org.w3c.dom.Element rootElement = xmlDoc.createElement("claimXMLDoc");
        org.w3c.dom.Element rootElement = xmlDoc.createElement("ClaimFile");
        org.w3c.dom.Element claimHeaderElement = xmlDoc.createElement("ClaimHeader");
        org.w3c.dom.Element claimElement = xmlDoc.createElement("Claim");
        org.w3c.dom.Element cardSerialElement = xmlDoc.createElement("SchemeMembershipNumber");
        org.w3c.dom.Element memberElement = xmlDoc.createElement("Member");
        org.w3c.dom.Element staffNoElement = xmlDoc.createElement("EmployeeNumber");
        org.w3c.dom.Element schemeAdministratorNameElement = xmlDoc.createElement("SchemeAdministrator");
        org.w3c.dom.Element schemeAdministratorIDElement = xmlDoc.createElement("SchemeAdministratorID");
        org.w3c.dom.Element schemeAdministratorNumberElement = xmlDoc.createElement("SchemeAdministratorNumber");
        org.w3c.dom.Element schemeMembershipNumberNumberElement = xmlDoc.createElement("SchemeMembershipNumber");
        org.w3c.dom.Element patientNumberElement = xmlDoc.createElement("PatientNumber");
        org.w3c.dom.Element patientSurnameElement = xmlDoc.createElement("Surname");
        org.w3c.dom.Element patientOtherNamesElement = xmlDoc.createElement("OtherNames");
        visitElement = xmlDoc.createElement("Visit");
        visitElement.setAttribute("ID", visitID);
        
        org.w3c.dom.Element schemeIDElement = xmlDoc.createElement("SchemeID");
        org.w3c.dom.Element schemeNameElement = xmlDoc.createElement("SchemeName");
        org.w3c.dom.Element medisatNumberElement = xmlDoc.createElement("MedisatNumber");
        
        org.w3c.dom.Element txElement = xmlDoc.createElement("Invoice");
        org.w3c.dom.Element invoiceNumberElement = xmlDoc.createElement("InvoiceNumber");
        org.w3c.dom.Element claimNoElement = xmlDoc.createElement("ClaimNumber");
        org.w3c.dom.Element claimDateElement = xmlDoc.createElement("ClaimDate");
        org.w3c.dom.Element claimTimeElement = xmlDoc.createElement("ClaimTime");
        org.w3c.dom.Element dateElement = xmlDoc.createElement("Date");
        org.w3c.dom.Element timeElement = xmlDoc.createElement("Time");
        org.w3c.dom.Element providerIDElement = xmlDoc.createElement("ProviderID");
        org.w3c.dom.Element serviceProviderElement = xmlDoc.createElement("ProviderName");
        org.w3c.dom.Element diagnoseCodeElement = xmlDoc.createElement("AilmentCode");
        org.w3c.dom.Element diagnoseDescriptionElement = xmlDoc.createElement("AilmentDescription");
        org.w3c.dom.Element encounterTypeElement = xmlDoc.createElement("Encounter_Type");
        org.w3c.dom.Element codeTypeElement = xmlDoc.createElement("Code_Type");
        org.w3c.dom.Element codeElement = xmlDoc.createElement("Code");
        org.w3c.dom.Element codeDescriptionElement = xmlDoc.createElement("Code_Description");
        org.w3c.dom.Element quantityElement = xmlDoc.createElement("Quantity");
        org.w3c.dom.Element totalAmountElement = xmlDoc.createElement("TotalAmount");
        org.w3c.dom.Element benefitAffectedElement = xmlDoc.createElement("BenefitAffected");
        org.w3c.dom.Element servicePointElement = xmlDoc.createElement("ServicePoint");
        org.w3c.dom.Element serviceDescriptionElement = xmlDoc.createElement("ServiceDecsription");
        org.w3c.dom.Element serviceDodeElement = xmlDoc.createElement("ServiceCode");
        org.w3c.dom.Element coPaymentElement = xmlDoc.createElement("Co-Payment");
        org.w3c.dom.Element amountElement = xmlDoc.createElement("Amount");
        org.w3c.dom.Element cashPaymentElement = xmlDoc.createElement("CashPayment");
        org.w3c.dom.Element receiptNumberElement = xmlDoc.createElement("ReceiptNumber");
        org.w3c.dom.Element nhifPaymentElement = xmlDoc.createElement("NHIFPayment");
        org.w3c.dom.Element ailmentCodeElement = xmlDoc.createElement("AilmentCode");
        org.w3c.dom.Element ailmentDescriptionElement = xmlDoc.createElement("AilmentDescription");
        org.w3c.dom.Element icd10CodeElement = xmlDoc.createElement("ICD10Code");
        org.w3c.dom.Element ailmentDateElement = xmlDoc.createElement("AilmentDate");
        org.w3c.dom.Element doctorsNotesElement = xmlDoc.createElement("DoctorsNotes");
        org.w3c.dom.Element consolidatedInvoiceNoElement = xmlDoc.createElement("ConsolidatedInvoiceNumber");
        org.w3c.dom.Element consolidatedInvoiceAmountElement = xmlDoc.createElement("ConsolidatedInvoiceAmount");
        org.w3c.dom.Element txInvoiceElement = xmlDoc.createElement("Transaction");
        
////        org.w3c.dom.Element tableCellElement = xmlDoc.createElement("tableCell");
        ////      org.w3c.dom.Element tableDataElement = xmlDoc.createElement("tableData");
        
        xmlDoc.appendChild(rootElement);
        
//        rootElement.appendChild(xmlDoc.createComment("This is a Claim XML document for healthcare"));
        
        System.out.println("Sorting claim data ...");
        
        appendClaimHeaderInfo(xmlDoc, rootElement);

        for (int j = 0; j < rowCount; j++) {
            
            if(invoiceTable.getValueAt(j,0) != null){
                txElement = xmlDoc.createElement("Transaction");
                txElement.setAttribute("Type", "OutPatient");
              //  txElement.appendChild(txInvoiceElement);

//                txIDElement = xmlDoc.createElement("CardSerialNumber");
//                txIDElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.hospital.HospitalMain.cardSerialNo));
//                txElement.appendChild(txIDElement);  
                
                txIDElement = xmlDoc.createElement("InvoiceNumber");
                txIDElement.appendChild(xmlDoc.createTextNode(txID));
                txElement.appendChild(txIDElement);                
                //  for (int k = 0; k < columnCount; k++) {
                
                txDateElement = xmlDoc.createElement("Date");
                txDateElement.appendChild(xmlDoc.createTextNode(txDate));
                txElement.appendChild(txDateElement);
                
                txTimeElement = xmlDoc.createElement("Time");
                txTimeElement.appendChild(xmlDoc.createTextNode(txTime));
                txElement.appendChild(txTimeElement);
                
                benefitAffectedElement = xmlDoc.createElement("BenefitAffected");
                benefitAffectedElement.appendChild(xmlDoc.createTextNode(benefitAffected));
                txElement.appendChild(benefitAffectedElement);
                
                servicePointElement = xmlDoc.createElement("ServicePoint");
                servicePointElement.appendChild(xmlDoc.createTextNode(servicePoint));
                txElement.appendChild(servicePointElement);
                
                serviceDescriptionElement = xmlDoc.createElement("ServiceDescription");
                serviceDescriptionElement.appendChild(xmlDoc.createTextNode(serviceDescription));
                txElement.appendChild(serviceDescriptionElement);
                
                serviceCodeElement = xmlDoc.createElement("ServiceCode");
                serviceCodeElement.appendChild(xmlDoc.createTextNode(serviceCode));
                txElement.appendChild(serviceCodeElement);
                
                totalAmountElement = xmlDoc.createElement("TotalAmount");
                totalAmountElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j,3).toString()));
                txElement.appendChild(totalAmountElement);
                
                diagnoseCodeElement = xmlDoc.createElement("AilmentCode");
                diagnoseCodeElement.appendChild(xmlDoc.createTextNode("0"));
                txElement.appendChild(diagnoseCodeElement);
                
                diagnoseDescriptionElement = xmlDoc.createElement("AilmentDescription");
                diagnoseDescriptionElement.appendChild(xmlDoc.createTextNode("Unkown disease"));
                txElement.appendChild(diagnoseDescriptionElement);
                
                encounterTypeElement = xmlDoc.createElement("Encounter_Type");
                encounterTypeElement.appendChild(xmlDoc.createTextNode("Medication"));
                txElement.appendChild(encounterTypeElement);
                
                codeTypeElement = xmlDoc.createElement("Code_Type");
                codeTypeElement.appendChild(xmlDoc.createTextNode("Mcode")); // Testing
                txElement.appendChild(codeTypeElement);
                
                codeElement = xmlDoc.createElement("Code");
                codeElement.appendChild(xmlDoc.createTextNode("1")); // for testing
                txElement.appendChild(codeElement);
                
                codeDescriptionElement = xmlDoc.createElement("Code_Description");
                codeDescriptionElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j,0).toString()));
                txElement.appendChild(codeDescriptionElement);
                
                quantityElement = xmlDoc.createElement("Quantity");
                quantityElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j,1).toString()));
                txElement.appendChild(quantityElement);

                //    }
                
            }
            claimElement.appendChild(txElement);
            rootElement.appendChild(claimElement);
        }
                /*
                if (invoiceTable.getValueAt(j, k) != null) {
                 
                    tableCellElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, k).toString()));
                 
                } else if (invoiceTable.getValueAt(j, k) == "") {
                 
                    tableCellElement.appendChild(xmlDoc.createTextNode("-"));
                 
                } else {
                 
                    tableCellElement.appendChild(xmlDoc.createTextNode("-"));
                 
                }
                 
                tableRowElement.appendChild(tableCellElement);
                 
            }
                 
            tableDataElement.appendChild(tableRowElement);
                 
        }
                 */
        org.apache.xml.serialize.OutputFormat format = new org.apache.xml.serialize.OutputFormat();
        
        format.setIndenting(true);
        
        org.apache.xml.serialize.XMLSerializer xmlSerializer = new org.apache.xml.serialize.XMLSerializer(xmlOutpuStream, format);
        
        try {
            
            xmlSerializer.asDOMSerializer();
            
            xmlSerializer.serialize(xmlDoc);
            
        } catch(java.io.IOException ioExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());
            
        }
        try {
            
            xmlOutpuStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        printNodeList(rootElement);
        
    }
    
    public void printNodeList(org.w3c.dom.Element rootElement) {
        
        org.w3c.dom.NodeList docNodeList = rootElement.getChildNodes();
        
        for (int i = 0; i < docNodeList.getLength(); i++) {
            
            System.out.println("Node type ["+docNodeList.item(i).getNodeType()+"]");
            
            if (docNodeList.item(i).getNodeType() != org.w3c.dom.Document.NOTATION_NODE) {
                
                System.out.println(docNodeList.item(i).getNodeValue());
                
                if (docNodeList.item(i).hasChildNodes()) {
                    
                    printNodeList((org.w3c.dom.Element)docNodeList.item(i));
                    
                }
            }
        }
    }
    
    public java.lang.Object[] getTableColumns(javax.swing.JTable exportedTable) {
        
        java.util.Vector columnVector = new java.util.Vector(1,1);
        
        java.lang.Object[] columnArray = null;
        
        java.util.Enumeration columnsEnumeration = exportedTable.getColumnModel().getColumns();
        
        while (columnsEnumeration.hasMoreElements()) {
            
            columnVector.addElement(columnsEnumeration.nextElement());
            
        }
        
        columnArray = columnVector.toArray();
        
        return columnArray;
    }
    
    /**
     * Adding claim header information.
     *
     */
    public void appendClaimHeaderInfo(org.w3c.dom.Document xmlDoc, org.w3c.dom.Element claimHeaderElement){
        
        org.w3c.dom.Element claimElement = xmlDoc.createElement("ClaimHeader");
        
        claimElement.appendChild(xmlDoc.createTextNode(invoiceNo));
        
        claimHeaderElement.appendChild(claimElement);
        
        
        org.w3c.dom.Element invoiceNoElement = xmlDoc.createElement("ClaimNumber");
        
        invoiceNoElement.appendChild(xmlDoc.createTextNode(invoiceNo));
        
        claimElement.appendChild(invoiceNoElement);
        
        
        org.w3c.dom.Element claimDateElement = xmlDoc.createElement("ClaimDate");
        
        claimDateElement.appendChild(xmlDoc.createTextNode(txDate));
        
        claimElement.appendChild(claimDateElement);
        
        
        org.w3c.dom.Element claimTimeElement = xmlDoc.createElement("ClaimTime");
        
        claimTimeElement.appendChild(xmlDoc.createTextNode(txTime));
        
        claimElement.appendChild(claimTimeElement);
        
        org.w3c.dom.Element providerIDElement = xmlDoc.createElement("ProviderID");
        
        providerIDElement.appendChild(xmlDoc.createTextNode(providerID));
        
        claimElement.appendChild(providerIDElement);
        
        org.w3c.dom.Element providerNameElement = xmlDoc.createElement("ProviderID");
        
        providerNameElement.appendChild(xmlDoc.createTextNode(providerName));
        
        claimElement.appendChild(providerNameElement);
        
        org.w3c.dom.Element schemeAdministratorIDElement = xmlDoc.createElement("SchemeAdministratorID");
        
        schemeAdministratorIDElement.appendChild(xmlDoc.createTextNode(schemeAdministratorID));
        
        claimElement.appendChild(schemeAdministratorIDElement);
        
        org.w3c.dom.Element schemeAdministratorElement = xmlDoc.createElement("SchemeAdministrator");
        
        schemeAdministratorElement.appendChild(xmlDoc.createTextNode(schemeAdministrator));
        
        claimElement.appendChild(schemeAdministratorElement);
        
        org.w3c.dom.Element schemeIDElement = xmlDoc.createElement("SchemeID");
        
        schemeIDElement.appendChild(xmlDoc.createTextNode(schemeID));
        
        claimElement.appendChild(schemeIDElement);
        
        org.w3c.dom.Element schemeNameElement = xmlDoc.createElement("SchemeName");
        
        schemeNameElement.appendChild(xmlDoc.createTextNode(schemeName));
        
        claimElement.appendChild(schemeNameElement);
        
        org.w3c.dom.Element memberElement = xmlDoc.createElement("Member");
        
//        claimElement.appendChild(memberElement);
        
        
        org.w3c.dom.Element schemeAdministratorNumberElement = xmlDoc.createElement("SchemeAdministratorNumber");
        
        schemeAdministratorNumberElement.appendChild(xmlDoc.createTextNode(schemeAdministratorNumber));
        
        memberElement.appendChild(schemeAdministratorNumberElement);
        
        org.w3c.dom.Element schemeMembershipNumberElement = xmlDoc.createElement("SchemeMembershipNumber");
        
        schemeMembershipNumberElement.appendChild(xmlDoc.createTextNode(schemeMembershipNumber));
        
        memberElement.appendChild(schemeMembershipNumberElement);
        
        org.w3c.dom.Element employeeNumberElement = xmlDoc.createElement("EmployeeNumber");
        
        employeeNumberElement.appendChild(xmlDoc.createTextNode(employeeNumber));
        
        memberElement.appendChild(employeeNumberElement);
        
        org.w3c.dom.Element medisatNumberElement = xmlDoc.createElement("MedisatNumber");
        
        medisatNumberElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.hospital.HospitalMain.cardSerialNo));//cardSerial));
        
        memberElement.appendChild(medisatNumberElement);
        
        org.w3c.dom.Element surnameElement = xmlDoc.createElement("Surname");
        
        surnameElement.appendChild(xmlDoc.createTextNode(surname));
        
        memberElement.appendChild(surnameElement);
        
        org.w3c.dom.Element otherNamesElement = xmlDoc.createElement("OtherNames");
        
        otherNamesElement.appendChild(xmlDoc.createTextNode(otherNames));
        
        memberElement.appendChild(otherNamesElement);
        
       claimElement.appendChild(memberElement);

    }
    
    public void run() {
        
        //  while (export2XML) {
        
        createXMLDoc();
        
        
        
        try {
            
            // threadExport2XML.
            
            Thread.currentThread().sleep(100);
            
        } catch(java.lang.InterruptedException IntExec){ System.out.println(IntExec.getMessage());}
        
        //   export2XML = false;
        
        // }
        
    }
    
    public void processXMLClaimFile(){
        
        readXMLClaimThread.start();
        
    }

    void setMainMemberMedisatNumber(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setMemberMedisatNumber(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setMemberSurname(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setMemberFullName(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeMemberID(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeMainMemberID(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeID(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setRelationship(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeAdminMainMemberID(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeAdminMemberID(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setAdminName(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeAdminName(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setSchemeAdminID(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setStatus(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setAuthentication(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setPIN(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setGender(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setMaritalStatus(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setBloodGroup(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setNationalIDNumber(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setPostalAddress(String string) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    void setPhysicalAddress(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setEmailAddress(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    void setTelephoneNumber(String string) {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }
    
    private class ReadXMLClaimFileThread extends java.lang.Thread {
        
        public void ReadXMLClaimFileThread(){
            
        }
        
        public void run(){
            
            processXMLClaimFile();
            
        }
        
        public void processXMLClaimFile(){
            
        }
        
    }
    
    // Claim class attributes.
    java.util.Vector invoiceChildVector = new java.util.Vector(1,1);
    java.lang.String patientNo;
    java.lang.String patientName;
    java.lang.String schemeMemberNo;
    java.lang.String schemeName;
    java.lang.String schemePayer;
    java.lang.String accountNo;
    java.lang.String invoiceNo;
    java.lang.String healthcareProviderID;
    javax.swing.JTable invoiceTable;
    java.io.File xmlClaimFile;
    java.io.BufferedOutputStream xmlOutpuStream = null;
    java.lang.Thread threadExport2XML = null;
    org.w3c.dom.Document initXmlDoc = null;
    java.lang.String tableName = "Claims_Table";
    boolean export2XML = true;
    java.lang.String fileString = null;
    ReadXMLClaimFileThread readXMLClaimThread = null;
    java.io.File xmlDocFile = null;
    static String cardSerial = null;
    static String poolNr = null;
    
    private Element txDateElement;
    
    private Node txTimeElement;
}
