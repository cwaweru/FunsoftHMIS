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

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 */
public class XMLClaim implements java.lang.Runnable {
    public static String transNo;

    /**
     * Creates a new instance of Claim
     */
    public XMLClaim() {

        setThreadExport2XML(new java.lang.Thread(this, "SerializeClaim2XML"));

        readXMLClaimThread = new ReadXMLClaimFileThread();

    }

    /**
     * Setter method for the patient ID.
     */
    public void setPatientNumber(java.lang.String newPatientNumber) {

        patientNumber = newPatientNumber;

    }

    /**
     * Setter method for the name of the patient.
     */
    public void setPatientName(java.lang.String nameofPatient) {

        patientName = nameofPatient;

    }

    /**
     * Setter method for the scheme member/beneficiary number/ID
     */
    public void setSchemeMemberNumber(java.lang.String schemeMemberNumber) {

        schemeMemberNumber = schemeMemberNumber;

    }

    /**
     * Setter method for the name of the scheme administrator.
     */
    public void setSchemeName(java.lang.String nameofScheme) {

        schemeName = nameofScheme;

    }

    /**
     * Setter method for the scheme payer.
     */
    public void setSchemePayer(java.lang.String payerName) {

        schemePayer = payerName;

    }

    /**
     * Setter method for the scheme account number/ID.
     */
    public void setAccountNumber(java.lang.String newAccountNumber) {

        accountNumber = newAccountNumber;

    }

    /**
     * Setter method for the claim invoice number
     */
    public void setInvoiceNumber(java.lang.String newInvoiceNumber) {

        invoiceNumber = newInvoiceNumber;

    }

    /**
     * Setter method for the health care provider.
     */
    public void setHealthCareProvider(java.lang.String hospitalName) {

        setHealthcareProviderID(hospitalName);

    }

    /**
     * Setter method for the invoice table.
     */
    public void setInvoiceTable(javax.swing.JTable ivoiceItemsTable) {

        //invoiceTable = ivoiceItemsTable;
    }

    /**
     * Getter method for the patient ID.
     */
    public java.lang.String getPatientNumber() {

        return patientNumber;

    }

    /**
     * Getter method for the name of the patient.
     */
    public java.lang.String getPatientName() {

        return patientName;

    }

    /**
     * Getter method for the scheme member/beneficiary number.
     */
    public java.lang.String getSchemeMemberNumber() {

        return schemeMemberNumber;

    }

    /**
     * Getter method for the scheme administrator name.
     */
    public java.lang.String getSchemeName() {

        return schemeName;

    }

    /**
     * Getter method for the scheme payer name.
     */
    public java.lang.String getSchemePayer() {

        return schemePayer;

    }

    /**
     * Getter method for the health care provider.
     */
    public java.lang.String getHealthCareProvider() {

        return getHealthcareProviderID();

    }

    /**
     * Getter method for the invoice table.
     */
    public javax.swing.JTable getInvoiceTable() {

        return invoiceTable;

    }

    public javax.swing.JTextField getTextField() {

        return getCoPay();

    }

    /**
     * Serializing the claim object to an XML file.
     */
    public void serializeClaim2XML() {

        this.createXMLDoc();

        //threadExport2XML.start();
    }

    public void readClaimFromXMLFile() {

    }

    public java.io.File getClaimFile2Send() {

        System.out.println("Claim file name >>>> : " + getXmlDocFile());

        return getXmlDocFile();

    }

    /**
     * Send claim to a Scheme or Payer.
     */
    public void sendClaimViaEmail(java.io.File xmlClaimFile, java.lang.String schemeMailAddress) {

//        this.serializeClaim2XML();
        biz.systempartners.claims.SendClaim.sendClaim(xmlClaimFile, schemeMailAddress);

    }

    public void createXMLDoc() {
    }

    java.sql.Connection connectDB = null;

    //   public void createXMLDoc(javax.swing.JTable invoicesTable, java.lang.String receipt, java.lang.String names) {
    public void createXMLDoc(javax.swing.JTable invoicesTable, java.sql.Connection connDb) {

        // java.io.File xmlDocFile =  null;
        connectDB = connDb;

        org.w3c.dom.Document xmlDocument = null;

        System.out.println("Started XML claim serialization ...");

        System.getProperty("claimsdir", System.getProperty("user.dir"));

        try {

            setXmlDocFile(java.io.File.createTempFile("CLAIM" + biz.systempartners.claims.DateLables.getDateLabel() + "_", ".xml", new java.io.File(System.getProperty("docsdir"))));
            // xmlDocFile = new java.io.File(System.getProperty("docsdir"),"HospitalClaimsFile.xml");//.createTempFile("HospitalClaimsFile", ".xml", new java.io.File(System.getProperty("docsdir")));

            //    xmlDocFile.deleteOnExit();
            setXmlOutpuStream(new java.io.BufferedOutputStream(new java.io.FileOutputStream(getXmlDocFile())));

            System.out.println("File name [" + getXmlDocFile().getAbsolutePath() + "]");

            fileString = getXmlDocFile().getAbsolutePath();

            javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();

            try {

                javax.xml.parsers.DocumentBuilder xmlDocBuilder = documentBuilderFactory.newDocumentBuilder();

                setInitXmlDoc(xmlDocBuilder.newDocument());

                workonXmlDocument(getInitXmlDoc(), invoicesTable);

                // javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table ["+tableName.toUpperCase()+"] to file ["+xmlDocFile.getAbsolutePath()+"]");
                /// Only for SmartLink
             /*
                 java.io.File cardFile = new java.io.File(System.getProperty("docsdir"),biz.systempartners.claims.XMLClaimFile.cardNumber+".txt");
                 //label for repeat
                 int k =  1;
              
                 // steve:
                 double bill = java.lang.Double.valueOf(com.afrisoftech.hospital.GeneralBillingIntfr.jTextField3.getText());
                 if(bill > 0){
                 while(k != 0) {
              
                 javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "RETRIEVE FROM SMARTCARD");
                 if (cardFile.exists()){
                 cardFile.delete();
                 java.io.File cardDocFile = new java.io.File(System.getProperty("docsdir"),"ForwardedCardData.xml");
                 cardDocFile.delete();
                 k = 0;
              
                 try {
                 connectDB.setAutoCommit(false);
              
                 java.sql.PreparedStatement pstmt11 = connectDB.prepareStatement("UPDATE ac_cash_collection SET closed = true WHERE patient_no = '"+com.afrisoftech.hospital.GeneralBillingIntfr.jTextField9.getText()+"' AND closed = false AND date::date BETWEEN (current_date -1) AND current_date and description ilike 'copay%'");
                 pstmt11.executeUpdate();
              
                 connectDB.commit();
                 connectDB.setAutoCommit(true);
              
                 }catch(java.sql.SQLException sq){
              
                 try {
                 connectDB.rollback();
                 }catch (java.sql.SQLException sql){
                 //  javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                 }
                 System.out.println(sq.getMessage());
                 //  javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
              
                 }
                 } else {
                 //    break steve;
                 }
                 }
              
                 }
                 else
                 {
                 javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "You cannot save a Zero Bill");
                 cardFile.delete();
                 java.io.File cardDocFile = new java.io.File(System.getProperty("docsdir"),"ForwardedCardData.xml");
                 cardDocFile.delete();
                 }
                 *///
            } catch (javax.xml.parsers.ParserConfigurationException parserExec) {

                javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), parserExec.getMessage());

            }

        } catch (java.io.IOException ioExec) {

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());

        }

    }

    public void workonXmlDocument(org.w3c.dom.Document xmlDoc) {
    }

    // public void workonXmlDocument(org.w3c.dom.Document xmlDoc, javax.swing.JTable invoiceTable, java.lang.String receipt, java.lang.String names) {
    public void workonXmlDocument(org.w3c.dom.Document xmlDoc, javax.swing.JTable invoiceTable) {

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        java.util.Date date = calendar.getTime();

        com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(date, "yyyy-MM-dd");

        com.afrisoftech.lib.DateFormatter timeFormatter = new com.afrisoftech.lib.DateFormatter(date, "HH:mm:ss");

        java.lang.String txDate = dateFormatter.getDateString();

        java.lang.String txTime = timeFormatter.getDateString();

        java.lang.Object[] tableColumns = this.getTableColumns(invoiceTable);

        int rowCount = invoiceTable.getRowCount();

        int columnCount = invoiceTable.getColumnCount();

        org.w3c.dom.Element rootNodeElement = xmlDoc.createElement("Claim");

        org.w3c.dom.Element rootClaimHeaderElement = xmlDoc.createElement("Claim_Header");

        org.w3c.dom.Element rootMemberElement = xmlDoc.createElement("Member");

        org.w3c.dom.Element rootPatientElement = xmlDoc.createElement("Patient");

        org.w3c.dom.Element rootProviderElement = xmlDoc.createElement("Provider");

        org.w3c.dom.Element rootTotalServicesElement = xmlDoc.createElement("Total_Services");

        org.w3c.dom.Element rootGrossElement = xmlDoc.createElement("Gross_Amount");

        org.w3c.dom.Element rootPoolNRElement = xmlDoc.createElement("Pool_Number");

        org.w3c.dom.Element rootElement = xmlDoc.createElement("ClaimData");

        org.w3c.dom.Element cardSerialElement = xmlDoc.createElement("CardNumber");

        org.w3c.dom.Element schemePayerElement = xmlDoc.createElement("SchemePayer");

        // org.w3c.dom.Element claimLinesElement = xmlDoc.createElement("Service");
        org.w3c.dom.Element txElement = xmlDoc.createElement("Service");

        org.w3c.dom.Element txDateElement = xmlDoc.createElement("TransactionDate");
        org.w3c.dom.Element txTimeElement = xmlDoc.createElement("TransactionTime");
        org.w3c.dom.Element serviceProviderElement = xmlDoc.createElement("ServiceProvider");
        org.w3c.dom.Element diagnoseCodeElement = xmlDoc.createElement("Diagnosis_Code");
        org.w3c.dom.Element diagnoseDescriptionElement = xmlDoc.createElement("Diagnosis_Description");
        org.w3c.dom.Element codeDescriptionElement = xmlDoc.createElement("Code_Description");
        org.w3c.dom.Element codeElement = xmlDoc.createElement("Code_Element");
        org.w3c.dom.Element accountNumberElement = xmlDoc.createElement("AccountNumber");
        org.w3c.dom.Element codeTypeElement = xmlDoc.createElement("Code_Type");
        org.w3c.dom.Element serviceCodeElement = xmlDoc.createElement("ServiceCode");
        org.w3c.dom.Element serviceDescriptionElement = xmlDoc.createElement("ServiceDescription");
        org.w3c.dom.Element quantityElement = xmlDoc.createElement("Quantity");
        org.w3c.dom.Element invoiceTotalElement = xmlDoc.createElement("InvoiceTotal");
        org.w3c.dom.Element discountElement = xmlDoc.createElement("Discount");
        org.w3c.dom.Element unitCostElement = xmlDoc.createElement("UnitCost");
        org.w3c.dom.Element schemeNameElement = xmlDoc.createElement("SchemeName");
        org.w3c.dom.Element schemeMemberNumberElement = xmlDoc.createElement("SchemeMemberNumber");
        org.w3c.dom.Element totalAmountElement = xmlDoc.createElement("TotalAmount");
        org.w3c.dom.Element paymentModifiersElement = xmlDoc.createElement("Payment_Modifiers");
        org.w3c.dom.Element paymentModifierElement = xmlDoc.createElement("Payment_Modifier");
        org.w3c.dom.Element encounterTypeElement = xmlDoc.createElement("Payment_Modifiers");
        org.w3c.dom.Element amountElement = xmlDoc.createElement("Amount");
        org.w3c.dom.Element invoiceNumberElement = xmlDoc.createElement("Invoice_Number");

        xmlDoc.appendChild(rootNodeElement);

        rootNodeElement.appendChild(rootElement);

        rootNodeElement.appendChild(xmlDoc.createComment("This is a Claim XML document for healthcare"));

        System.out.println("Sorting claim data ...");

        appendClaimHeaderInfo(xmlDoc, rootNodeElement);

        int copayCounter = 0;

        rootNodeElement.appendChild(rootClaimHeaderElement);

        invoiceNumberElement = xmlDoc.createElement("Invoice_Number");
        invoiceNumberElement.appendChild(xmlDoc.createTextNode(transNo));
        rootClaimHeaderElement.appendChild(invoiceNumberElement);

        for (int j = 0; j <= rowCount; j++) {
            if (j < rowCount) {
                if (invoiceTable.getValueAt(j, 0) != null) {
                    txElement = xmlDoc.createElement("Service");

                    txDateElement = xmlDoc.createElement("TransactionDate");
                    txDateElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 0).toString()));
                    txElement.appendChild(txDateElement);

                    serviceDescriptionElement = xmlDoc.createElement("ServiceDescription");
                    serviceDescriptionElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 1).toString()));
                    txElement.appendChild(serviceDescriptionElement);

                    quantityElement = xmlDoc.createElement("Quantity");
                    quantityElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 2).toString()));
                    txElement.appendChild(quantityElement);

                    unitCostElement = xmlDoc.createElement("UnitCost");
                    unitCostElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(java.lang.Double.parseDouble(invoiceTable.getValueAt(j, 3).toString())))); // for testing
                    txElement.appendChild(unitCostElement);

                    discountElement = xmlDoc.createElement("Discount");
                    discountElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 5).toString())); // for testing
                    txElement.appendChild(discountElement);

                    totalAmountElement = xmlDoc.createElement("TotalAmount");
                    totalAmountElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 6).toString()));
                    txElement.appendChild(totalAmountElement);

                    // System.out.println("What is the copay value "+copayCounter);
                }
            }

            // For smart claims
            if (j == rowCount) {
                copayCounter = 1;
                System.out.println("I have arrived");

                try {
                    connectDB.setAutoCommit(false);

                    java.sql.Statement ps1 = connectDB.createStatement();
                    java.sql.ResultSet rst1 = ps1.executeQuery("select description,debit*-1 FROM ac_cash_collection WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' AND closed = false AND date::date BETWEEN (current_date -1) AND current_date and description ilike 'copay%'");
                    while (rst1.next()) {
                        System.out.println("I have started creating copy nodes");
                        txElement = xmlDoc.createElement("Transaction");

                        txDateElement = xmlDoc.createElement("Transaction_Date");
                        txDateElement.appendChild(xmlDoc.createTextNode(txDate));
                        txElement.appendChild(txDateElement);

                        txTimeElement = xmlDoc.createElement("Transaction_Time");
                        txTimeElement.appendChild(xmlDoc.createTextNode(txTime));
                        txElement.appendChild(txTimeElement);

                        serviceProviderElement = xmlDoc.createElement("Service_Provider_Nr");
                        serviceProviderElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.hospital.HospitalMain.getCompanyName().replaceAll("FUNSOFT :: ", "").substring(0, 10)));
                        txElement.appendChild(serviceProviderElement);

                        diagnoseCodeElement = xmlDoc.createElement("Diagnosis_Code");
                        diagnoseCodeElement.appendChild(xmlDoc.createTextNode("0"));
                        txElement.appendChild(diagnoseCodeElement);

                        diagnoseDescriptionElement = xmlDoc.createElement("Diagnosis_Description");
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
                        codeDescriptionElement.appendChild(xmlDoc.createTextNode("Copay"));
                        txElement.appendChild(codeDescriptionElement);

                        quantityElement = xmlDoc.createElement("Quantity");
                        quantityElement.appendChild(xmlDoc.createTextNode("1"));
                        txElement.appendChild(quantityElement);

                        amountElement = xmlDoc.createElement("Amount");
                        amountElement.appendChild(xmlDoc.createTextNode(java.lang.String.valueOf((rst1.getDouble(2)))));
                        txElement.appendChild(amountElement);

                        rootClaimHeaderElement.appendChild(txElement);
                    }

                    connectDB.commit();
                    connectDB.setAutoCommit(true);

                } catch (java.sql.SQLException sq) {

                    try {
                        connectDB.rollback();
                    } catch (java.sql.SQLException sql) {
                        javax.swing.JOptionPane.showMessageDialog(null, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println(sq.getMessage());
                    javax.swing.JOptionPane.showMessageDialog(null, sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                }
            }

            //claimLinesElement.appendChild(txElement);
            rootElement.appendChild(txElement);
            rootNodeElement.appendChild(rootElement);
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

        org.apache.xml.serialize.XMLSerializer xmlSerializer = new org.apache.xml.serialize.XMLSerializer(getXmlOutpuStream(), format);

        try {

            xmlSerializer.asDOMSerializer();

            xmlSerializer.serialize(xmlDoc);

        } catch (java.io.IOException ioExec) {

            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ioExec.getMessage());

        }
        try {

            getXmlOutpuStream().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        printNodeList(rootElement);

    }

    public void printNodeList(org.w3c.dom.Element rootElement) {

        org.w3c.dom.NodeList docNodeList = rootElement.getChildNodes();

        for (int i = 0; i < docNodeList.getLength(); i++) {

            System.out.println("Node type [" + docNodeList.item(i).getNodeType() + "]");

            if (docNodeList.item(i).getNodeType() != org.w3c.dom.Document.NOTATION_NODE) {

                System.out.println(docNodeList.item(i).getNodeValue());

                if (docNodeList.item(i).hasChildNodes()) {

                    printNodeList((org.w3c.dom.Element) docNodeList.item(i));

                }
            }
        }
    }

    public java.lang.Object[] getTableColumns(javax.swing.JTable exportedTable) {

        java.util.Vector columnVector = new java.util.Vector(1, 1);

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
    public void appendClaimHeaderInfo(org.w3c.dom.Document xmlDoc, org.w3c.dom.Element claimHeaderElement) {
        org.w3c.dom.Element cardSerialElement = xmlDoc.createElement("CardSerialNumber");

        cardSerialElement.appendChild(xmlDoc.createTextNode(getCardSerial()));//cardSerial));

        claimHeaderElement.appendChild(cardSerialElement);

////        org.w3c.dom.Element claimHeaderElement = xmlDoc.createElement("claimHeader");
        org.w3c.dom.Element pooledNoElement = xmlDoc.createElement("Pool_Nr");

        pooledNoElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.cardNr));//poolNr));

        claimHeaderElement.appendChild(pooledNoElement);
        //  invoiceNo = com.afrisoftech.hospital.GeneralBillingIntfr.transNo;

        org.w3c.dom.Element invoiceNumber = xmlDoc.createElement("InvoiceNumber");
        invoiceNumber.appendChild(xmlDoc.createTextNode(getInvoiceNumber()));
        claimHeaderElement.appendChild(invoiceNumber);

        org.w3c.dom.Element claimNo = xmlDoc.createElement("Claim_Nr");
        claimNo.appendChild(xmlDoc.createTextNode(getClaimsNo()));
        claimHeaderElement.appendChild(claimNo);

        //  patientName = com.afrisoftech.hospital.GeneralBillingIntfr.jTextField1.getText();
        org.w3c.dom.Element patientNames = xmlDoc.createElement("PatientName");
        patientNames.appendChild(xmlDoc.createTextNode(getPatientName()));
        claimHeaderElement.appendChild(patientNames);

        org.w3c.dom.Element patientNoElement = xmlDoc.createElement("PatientNumber");

        patientNoElement.appendChild(xmlDoc.createTextNode(getPatientNumber()));

        claimHeaderElement.appendChild(patientNoElement);

        org.w3c.dom.Element schemeMemberNumberElement = xmlDoc.createElement("schemeMemberNumber");

        schemeMemberNumberElement.appendChild(xmlDoc.createTextNode(getSchemeMemberNumber()));

        claimHeaderElement.appendChild(schemeMemberNumberElement);

        org.w3c.dom.Element schemeNameElement = xmlDoc.createElement("SchemeName");

        schemeNameElement.appendChild(xmlDoc.createTextNode(getSchemeName()));

        claimHeaderElement.appendChild(schemeNameElement);

        org.w3c.dom.Element schemePayerElement = xmlDoc.createElement("SchemePayer");

        schemePayerElement.appendChild(xmlDoc.createTextNode(getSchemePayer()));

        claimHeaderElement.appendChild(schemePayerElement);

        org.w3c.dom.Element accountNumberElement = xmlDoc.createElement("AccountNumber");

        accountNumberElement.appendChild(xmlDoc.createTextNode(getAccountNumber()));

        claimHeaderElement.appendChild(accountNumberElement);

        org.w3c.dom.Element invoiceTotalElement = xmlDoc.createElement("InvoiceTotal");

        invoiceTotalElement.appendChild(xmlDoc.createTextNode(getInvoiceTotal()));

        claimHeaderElement.appendChild(invoiceTotalElement);

        org.w3c.dom.Element serviceProviderIDElement = xmlDoc.createElement("ServiceProvider");

        serviceProviderIDElement.appendChild(xmlDoc.createTextNode(getServiceProvider()));

        claimHeaderElement.appendChild(serviceProviderIDElement);

    }

    public void run() {

        //  while (export2XML) {
        createXMLDoc();

        try {

            // threadExport2XML.
            Thread.currentThread().sleep(100);

        } catch (java.lang.InterruptedException IntExec) {
            System.out.println(IntExec.getMessage());
        }

        //   export2XML = false;
        // }
    }

    public void processXMLClaimFile() {

        readXMLClaimThread.start();

    }

    void setMedicalAidExpiry(String string) {

    }

    void setPatientID(String string) {

    }

    void setMedicalAidNumber(String string) {

    }

    private class ReadXMLClaimFileThread extends java.lang.Thread {

        public void ReadXMLClaimFileThread() {

        }

        public void run() {

            processXMLClaimFile();

        }

        public void processXMLClaimFile() {

        }

    }

// Claim class attributes.
    private java.util.Vector invoiceChildVector = new java.util.Vector(1, 1);
    private java.lang.String patientNumber;
    private java.lang.String patientName;
    private java.lang.String schemeMemberNumber;
    private java.lang.String schemeName;
    private java.lang.String schemePayer;
    private java.lang.String accountNumber;
    private java.lang.String invoiceNumber;
    private java.lang.String claimsNo;
    private java.lang.String healthcareProviderID;
    private java.lang.String serviceProvider;
    private java.lang.String invoiceTotal;
    private java.lang.String serviceCode;
    private java.lang.String quantity;
    private java.lang.String serviceDescription;
    private java.lang.String unitCost;
    private java.lang.String Discount;
    private javax.swing.JTable claimsTable;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JTextField coPay;
    private java.io.File xmlClaimFile;
    private java.io.BufferedOutputStream xmlOutpuStream = null;
    private java.lang.Thread threadExport2XML = null;
    private org.w3c.dom.Document initXmlDoc = null;
    private java.lang.String tableName = "Claims_Table";
    private boolean export2XML = true;
    java.lang.String fileString = null;
    ReadXMLClaimFileThread readXMLClaimThread = null;
    private java.io.File xmlDocFile = null;
    private static String cardSerial = null;
    private static String poolNr = null;

    public java.util.Vector getInvoiceChildVector() {
        return invoiceChildVector;
    }

    public void setInvoiceChildVector(java.util.Vector invoiceChildVector) {
        this.invoiceChildVector = invoiceChildVector;
    }

    public java.lang.String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(java.lang.String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public java.lang.String getHealthcareProviderID() {
        return healthcareProviderID;
    }

    public void setHealthcareProviderID(java.lang.String healthcareProviderID) {
        this.healthcareProviderID = healthcareProviderID;
    }

    public java.lang.String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(java.lang.String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public java.lang.String getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(java.lang.String newInvoiceTotal) {
        invoiceTotal = newInvoiceTotal;
    }

    public java.lang.String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(java.lang.String newServiceCode) {
        this.serviceCode = newServiceCode;
    }

    public java.lang.String getQuantity() {
        return quantity;
    }

    public void setQuantity(java.lang.String newQuantity) {
        this.quantity = newQuantity;
    }

    public java.lang.String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(java.lang.String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public java.lang.String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(java.lang.String newUnitCost) {
        this.unitCost = newUnitCost;
    }

    public java.lang.String getDiscount() {
        return Discount;
    }

    public void setDiscount(java.lang.String newDiscount) {
        this.Discount = newDiscount;
    }

    public javax.swing.JTextField getCoPay() {
        return coPay;
    }

    public void setCoPay(javax.swing.JTextField newcoPay) {
        this.coPay = newcoPay;
    }

    public java.io.File getXmlClaimFile() {
        return xmlClaimFile;
    }

    public void setXmlClaimFile(java.io.File xmlClaimFile) {
        this.xmlClaimFile = xmlClaimFile;
    }

    public java.io.BufferedOutputStream getXmlOutpuStream() {
        return xmlOutpuStream;
    }

    public void setXmlOutpuStream(java.io.BufferedOutputStream xmlOutpuStream) {
        this.xmlOutpuStream = xmlOutpuStream;
    }

    public java.lang.Thread getThreadExport2XML() {
        return threadExport2XML;
    }

    public void setThreadExport2XML(java.lang.Thread threadExport2XML) {
        this.threadExport2XML = threadExport2XML;
    }

    public org.w3c.dom.Document getInitXmlDoc() {
        return initXmlDoc;
    }

    public void setInitXmlDoc(org.w3c.dom.Document initXmlDoc) {
        this.initXmlDoc = initXmlDoc;
    }

    public java.lang.String getTableName() {
        return tableName;
    }

    public void setTableName(java.lang.String tableName) {
        this.tableName = tableName;
    }

    public boolean isExport2XML() {
        return export2XML;
    }

    public void setExport2XML(boolean export2XML) {
        this.export2XML = export2XML;
    }

    public java.io.File getXmlDocFile() {
        return xmlDocFile;
    }

    public void setXmlDocFile(java.io.File xmlDocFile) {
        this.xmlDocFile = xmlDocFile;
    }

    public static String getCardSerial() {
        return cardSerial;
    }

    public static void setCardSerial(String aCardSerial) {
        cardSerial = aCardSerial;
    }

    public static String getPoolNr() {
        return poolNr;
    }

    public static void setPoolNr(String aPoolNr) {
        poolNr = aPoolNr;
    }

    public java.lang.String getInvoiceNumber() {
        return invoiceNumber;
    }

    public java.lang.String getAccountNumber() {
        return accountNumber;
    }
}
