/*
 * Claim.java
 *
 * Created on July 2, 2006, 10:53 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package biz.systempartners.claims;

//import com.sun.star.lib.uno.environments.java.java_environment;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles W. Waweru <cwaweru@systempartners.biz>
 */
public class Claim implements java.lang.Runnable {

    /**
     * Creates a new instance of Claim
     */
    //public static String invoiceNo = null;
    public Claim() {

        threadExport2XML = new java.lang.Thread(this, "SerializeClaim2XML");

        readXMLClaimThread = new ReadXMLClaimFileThread();

    }

    /**
     * Setter method for the patient ID.
     */
    public void setPatientNumber(java.lang.String patientNumber) {

        patientNo = patientNumber;

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

        schemeMemberNo = schemeMemberNumber;

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
    public void setAccountNumber(java.lang.String accountNumber) {

        accountNo = accountNumber;

    }

    /**
     * Setter method for the claim invoice number
     */
    public void setInvoiceNumber(java.lang.String invoiceNumber) {

        invoiceNo = invoiceNumber;

    }

    /**
     * Setter method for the health care provider.
     */
    public void setHealthCareProvider(java.lang.String hospitalName) {

        healthcareProviderID = hospitalName;

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

        return patientNo;

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

        return schemeMemberNo;

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
     * Getter method for the scheme account number.
     */
    public java.lang.String getAccountNumber() {

        return accountNo;

    }

    /**
     * Getter method for the claim invoice number.
     */
    public java.lang.String getInvoiceNumber() {

        return invoiceNo;

    }

    /**
     * Getter method for the health care provider.
     */
    public java.lang.String getHealthCareProvider() {

        return healthcareProviderID;

    }

    /**
     * Getter method for the invoice table.
     */
    public javax.swing.JTable getInvoiceTable() {

        return invoiceTable;

    }

    public javax.swing.JTextField getTextField() {

        return coPay;

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

        System.out.println("Claim file name >>>> : " + xmlDocFile);

        return xmlDocFile;

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
    public void createXMLDoc(javax.swing.JTable invoicesTable, java.sql.Connection connDb) throws SQLException {

        connectDB = connDb;

        org.w3c.dom.Document xmlDocument = null;

        System.out.println("Started XML claim serialization ...");

        System.getProperty("docsdir", System.getProperty("user.dir"));

        try {

            // xmlDocFile = java.io.File.createTempFile("CLAIM"+biz.systempartners.claims.DateLables.getDateLabel()+"_", ".xml", new java.io.File(System.getProperty("docsdir")));
            xmlDocFile = new java.io.File(System.getProperty("docsdir"), "HospitalClaimsFile.xml");//.createTempFile("HospitalClaimsFile", ".xml", new java.io.File(System.getProperty("docsdir")));

            //    xmlDocFile.deleteOnExit();
            xmlOutpuStream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(xmlDocFile));

            System.out.println("File name [" + xmlDocFile.getAbsolutePath() + "]");

            fileString = xmlDocFile.getAbsolutePath();

            javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();

            try {

                javax.xml.parsers.DocumentBuilder xmlDocBuilder = documentBuilderFactory.newDocumentBuilder();

                initXmlDoc = xmlDocBuilder.newDocument();

                workonXmlDocument(initXmlDoc, invoicesTable);

                System.out.println("This is update for Gobal_ID : [" + biz.systempartners.claims.XMLClaimFile.cardNumber + "]");
                java.sql.Connection mysqlConn = com.afrisoftech.lib.SmartExchange.getDBConnection(connectDB);
                java.sql.PreparedStatement pstmtMysql = mysqlConn.prepareStatement("UPDATE exchange_files SET  Progress_Flag = ?, Exchange_Date = ?, Exchange_File = ? WHERE Global_id = ? AND Progress_Flag = ? ORDER BY id DESC LIMIT 1");

                pstmtMysql.setInt(1, 2);
                pstmtMysql.setTimestamp(2, com.afrisoftech.lib.ServerTime.getSQLTimeStamp(connDb));
                pstmtMysql.setBlob(3, new java.io.FileInputStream(xmlDocFile));
                pstmtMysql.setString(4, biz.systempartners.claims.XMLClaimFile.cardNumber);
                pstmtMysql.setInt(5, 1);

//                pstmtMysql.setString(3, biz.systempartners.claims.XMLClaimFile.patientNumber);
//                pstmtMysql.setString(4, biz.systempartners.claims.XMLClaimFile.patientNumber);
//
//                pstmtMysql.setInt(6, 1);
//                pstmtMysql.setString(7, biz.systempartners.claims.XMLClaimFile.patientLocation);
                pstmtMysql.executeUpdate();

                pstmtMysql.close();

                // mysqlConn = java.sql.DriverManager.getConnection("jdbc:mysql://192.168.0.104:3307/smartlink", "integ_user", "integ123");
//                java.sql.PreparedStatement pstmtMysqlExchange = mysqlConn.prepareStatement("UPDATE exchange_files SET Progress_Flag = ? WHERE Global_Id = ? ");
//
//                pstmtMysqlExchange.setInt(1, 2);
//
//                pstmtMysqlExchange.setString(2, biz.systempartners.claims.XMLClaimFile.cardNumber);
//
//                pstmtMysqlExchange.executeUpdate();
                javax.swing.JOptionPane.showMessageDialog(null, "Hospital to SmartLink billing file submitted successfully");

//                pstmtMysqlExchange.close();
                mysqlConn.close();

                // javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "Successfully exported table ["+tableName.toUpperCase()+"] to file ["+xmlDocFile.getAbsolutePath()+"]");
                java.io.File cardFile = new java.io.File(System.getProperty("docsdir"), biz.systempartners.claims.XMLClaimFile.cardNumber + ".txt");
                //label for repeat
                int k = 1;

                // steve:
                double bill = java.lang.Double.valueOf(com.afrisoftech.hospital.GeneralBillingIntfr.billTotalTxt.getText());
                if (bill > 0) {
//                    while (k != 0) {
//
//                        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "RETRIEVE FROM SMARTCARD");
//                        if (cardFile.exists()) {
//                            System.out.println("Executing claim file generation for file : " + cardFile.getPath());
//                            cardFile.delete();
//                            java.io.File cardDocFile = new java.io.File(System.getProperty("docsdir"), "ForwardedCardData.xml");
//                            cardDocFile.delete();
//                            k = 0;

                    try {
                        //  connectDB.setAutoCommit(false);

                        java.sql.PreparedStatement pstmt11 = connectDB.prepareStatement("UPDATE ac_cash_collection SET closed = true WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' AND closed = false AND date::date BETWEEN (current_date -1) AND current_date and description ilike 'copay%'");
                        pstmt11.executeUpdate();

//                                connectDB.commit();
//                                connectDB.setAutoCommit(true);
                    } catch (java.sql.SQLException sq) {

                        sq.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(null, sq.getMessage());

                    }

                } else {
                    javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "You cannot save a Zero Bill");
                    cardFile.delete();
                    java.io.File cardDocFile = new java.io.File(System.getProperty("docsdir"), "ForwardedCardData.xml");
                    cardDocFile.delete();
                }
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

        org.w3c.dom.Element rootElement = xmlDoc.createElement("Claim_Data");

        org.w3c.dom.Element headerElement = xmlDoc.createElement("Claim_Header");

        org.w3c.dom.Element invoiceNumberElement = xmlDoc.createElement("Invoice_Number");

        invoiceNumberElement.appendChild(xmlDoc.createTextNode(invoiceNo));

        headerElement.appendChild(invoiceNumberElement);

        org.w3c.dom.Element claimDateElement = xmlDoc.createElement("Claim_Date");

        claimDateElement.appendChild(xmlDoc.createTextNode(txDate));

        headerElement.appendChild(claimDateElement);

        org.w3c.dom.Element claimTimeElement = xmlDoc.createElement("Claim_Time");

        claimTimeElement.appendChild(xmlDoc.createTextNode(txTime));

        headerElement.appendChild(claimTimeElement);

        org.w3c.dom.Element cardSerialElement = xmlDoc.createElement("Card_Serial");

        org.w3c.dom.Element pooledNoElement = xmlDoc.createElement("Pool_Number");

        pooledNoElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.cardNr));

        headerElement.appendChild(pooledNoElement);

        org.w3c.dom.Element totalServicesNumberElement = xmlDoc.createElement("Total_Services");

        int rowCounts = 0;

        for (int m = 0; m < invoiceTable.getRowCount(); m++) {
            if (invoiceTable.getValueAt(m, 0) != null) {
                rowCounts++;
            }
        }

        totalServicesNumberElement.appendChild(xmlDoc.createTextNode(String.valueOf(rowCounts)));

        headerElement.appendChild(totalServicesNumberElement);

        org.w3c.dom.Element grossAmountElement = xmlDoc.createElement("Gross_Amount");

        grossAmountElement.appendChild(xmlDoc.createTextNode(String.valueOf(com.afrisoftech.lib.TableColumnTotal.getTableColumnTotal(invoiceTable, 3))));

        headerElement.appendChild(grossAmountElement);

        org.w3c.dom.Element providerElement = xmlDoc.createElement("Provider");

        org.w3c.dom.Element roleElement = xmlDoc.createElement("Role");

        roleElement.appendChild(xmlDoc.createTextNode("SP"));

        providerElement.appendChild(roleElement);

        org.w3c.dom.Element countryCodeElement = xmlDoc.createElement("Country_Code");

        countryCodeElement.appendChild(xmlDoc.createTextNode("KEN"));

        providerElement.appendChild(countryCodeElement);

        org.w3c.dom.Element hospitalNumberElement = xmlDoc.createElement("Group_Practice_Number");

        hospitalNumberElement.appendChild(xmlDoc.createTextNode("SKSP_3213"));

        providerElement.appendChild(hospitalNumberElement);

        org.w3c.dom.Element hospitalNameElement = xmlDoc.createElement("Group_Practice_Name");

        hospitalNameElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName()));

        providerElement.appendChild(hospitalNameElement);

        headerElement.appendChild(providerElement);

        rootNodeElement.appendChild(headerElement);

        org.w3c.dom.Element authorizationElement = xmlDoc.createElement("Authorization");

        org.w3c.dom.Element preauthorizationNoElement = xmlDoc.createElement("Pre_Authorization_Number");

        preauthorizationNoElement.appendChild(xmlDoc.createTextNode("12"));

        authorizationElement.appendChild(preauthorizationNoElement);

        org.w3c.dom.Element preauthorizationAmountElement = xmlDoc.createElement("Pre_Authorization_Amount");

        preauthorizationAmountElement.appendChild(xmlDoc.createTextNode("0"));

        authorizationElement.appendChild(preauthorizationAmountElement);

        headerElement.appendChild(authorizationElement);

        org.w3c.dom.Element claimLinesElement = xmlDoc.createElement("Services");

        org.w3c.dom.Element txElement = xmlDoc.createElement("Transaction");

        org.w3c.dom.Element txDateElement = xmlDoc.createElement("Transaction_Date");
        org.w3c.dom.Element txTimeElement = xmlDoc.createElement("Transaction_Time");
        org.w3c.dom.Element serviceProviderElement = xmlDoc.createElement("Service_Provider_Nr");
        org.w3c.dom.Element diagnoseCodeElement = xmlDoc.createElement("Diagnosis_Code");
        org.w3c.dom.Element diagnoseDescriptionElement = xmlDoc.createElement("Diagnosis_Description");
        org.w3c.dom.Element encounterTypeElement = xmlDoc.createElement("Encounter_Type");
        org.w3c.dom.Element codeTypeElement = xmlDoc.createElement("Code_Type");
        org.w3c.dom.Element codeElement = xmlDoc.createElement("Code");
        org.w3c.dom.Element codeDescriptionElement = xmlDoc.createElement("Code_Description");
        org.w3c.dom.Element quantityElement = xmlDoc.createElement("Quantity");
        org.w3c.dom.Element amountElement = xmlDoc.createElement("Amount");

        //org.w3c.dom.Element amountElement = xmlDoc.createElement("Amount");
        xmlDoc.appendChild(rootNodeElement);

        rootNodeElement.appendChild(xmlDoc.createComment("This is a Funsoft generated Claim XML document for healthcare"));

        System.out.println("Sorting claim data ...");

        //   rootElement.appendChild(cardSerialElement);
        //appendClaimHeaderInfo(xmlDoc, rootNodeElement);
        // rootNodeElement.appendChild(rootElement);
        int copayCounter = 0;

        org.w3c.dom.Element paymentModifiersElement = xmlDoc.createElement("Payment_Modifiers");

        org.w3c.dom.Element paymentModifierElement = xmlDoc.createElement("PaymentModifier");

        java.sql.Statement ps1;

        double copayAmount = 0.00;

        String copayDescription = "0";

        String copyReceipt = "0";
        try {

            ps1 = connectDB.createStatement();

            java.sql.ResultSet rst1 = ps1.executeQuery("select description,debit*-1, receipt_no FROM ac_cash_collection WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' AND closed = false AND date::date BETWEEN (current_date -1) AND current_date and description ilike 'copay%'");

            while (rst1.next()) {

                copayAmount = rst1.getDouble(2);

                copayDescription = rst1.getString(1);

                copyReceipt = rst1.getString(3);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }
        org.w3c.dom.Element copayTypeElement = xmlDoc.createElement("Type");

        copayTypeElement.appendChild(xmlDoc.createTextNode("1"));

        paymentModifierElement.appendChild(copayTypeElement);

        org.w3c.dom.Element copayAmountElement = xmlDoc.createElement("Amount");

        copayAmountElement.appendChild(xmlDoc.createTextNode(String.valueOf(0)));

        paymentModifierElement.appendChild(copayAmountElement);

        org.w3c.dom.Element copayReceiptElement = xmlDoc.createElement("Receipt");

        copayReceiptElement.appendChild(xmlDoc.createTextNode("0"));

        paymentModifierElement.appendChild(copayReceiptElement);

        paymentModifiersElement.appendChild(paymentModifierElement);

        java.sql.Statement pstmtNHIF;

        org.w3c.dom.Element paymentModifier2Element = xmlDoc.createElement("Payment_Modifier");
        int count = 0;

        try {

            pstmtNHIF = connectDB.createStatement();

            java.sql.ResultSet rst1 = pstmtNHIF.executeQuery("select nhif_status,nhif_number FROM hp_patient_register WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' AND nhif_status = true AND nhif_number is not null");

            while (rst1.next()) {

                count++;

                org.w3c.dom.Element nhifElement = xmlDoc.createElement("Type");

                nhifElement.appendChild(xmlDoc.createTextNode("5"));

                paymentModifier2Element.appendChild(nhifElement);

                org.w3c.dom.Element nhifNumberElement = xmlDoc.createElement("NHIF_Member_Nr");

                nhifNumberElement.appendChild(xmlDoc.createTextNode(rst1.getString(2)));

                paymentModifier2Element.appendChild(nhifNumberElement);

                org.w3c.dom.Element contibutorNumberElement = xmlDoc.createElement("NHIF_Contributor_Nr");

                contibutorNumberElement.appendChild(xmlDoc.createTextNode(rst1.getString(3)));

                paymentModifier2Element.appendChild(contibutorNumberElement);

                org.w3c.dom.Element employerCodeElement = xmlDoc.createElement("NHIF_Employer_Code");

                employerCodeElement.appendChild(xmlDoc.createTextNode(""));

                paymentModifier2Element.appendChild(employerCodeElement);

                org.w3c.dom.Element siteElement = xmlDoc.createElement("NHIF_Site_Nr");

                siteElement.appendChild(xmlDoc.createTextNode(""));

                paymentModifier2Element.appendChild(siteElement);

                org.w3c.dom.Element relationElement = xmlDoc.createElement("NHIF_Patient_Relation");

                relationElement.appendChild(xmlDoc.createTextNode(""));

                paymentModifier2Element.appendChild(relationElement);

                org.w3c.dom.Element diagnosisElement = xmlDoc.createElement("Diagnosis_Code");

                diagnosisElement.appendChild(xmlDoc.createTextNode(""));

                paymentModifier2Element.appendChild(diagnosisElement);

                org.w3c.dom.Element admissionDateElement = xmlDoc.createElement("Admit_Date");

                admissionDateElement.appendChild(xmlDoc.createTextNode(txDate));

                paymentModifier2Element.appendChild(admissionDateElement);

                org.w3c.dom.Element dischargeDateElement = xmlDoc.createElement("Discharge_Date");

                dischargeDateElement.appendChild(xmlDoc.createTextNode(txDate));

                paymentModifier2Element.appendChild(dischargeDateElement);

                org.w3c.dom.Element inpatientDaysElement = xmlDoc.createElement("Days_Used");

                inpatientDaysElement.appendChild(xmlDoc.createTextNode(""));

                paymentModifier2Element.appendChild(inpatientDaysElement);

                org.w3c.dom.Element rebateAmountElement = xmlDoc.createElement("Amount");

                rebateAmountElement.appendChild(xmlDoc.createTextNode("0.00"));

                paymentModifier2Element.appendChild(rebateAmountElement);

                paymentModifiersElement.appendChild(paymentModifier2Element);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count == 0) {
            org.w3c.dom.Element paymentModifier3Element = xmlDoc.createElement("Payment_Modifier");

            org.w3c.dom.Element nhifElement = xmlDoc.createElement("Type");

            nhifElement.appendChild(xmlDoc.createTextNode("5"));

            paymentModifier3Element.appendChild(nhifElement);

            org.w3c.dom.Element nhifNumberElement = xmlDoc.createElement("NHIF_Member_Nr");

            nhifNumberElement.appendChild(xmlDoc.createTextNode("001"));

            paymentModifier3Element.appendChild(nhifNumberElement);

            org.w3c.dom.Element contibutorNumberElement = xmlDoc.createElement("NHIF_Contributor_Nr");

            contibutorNumberElement.appendChild(xmlDoc.createTextNode("001"));

            paymentModifier3Element.appendChild(contibutorNumberElement);

            org.w3c.dom.Element employerCodeElement = xmlDoc.createElement("NHIF_Employer_Code");

            employerCodeElement.appendChild(xmlDoc.createTextNode("000"));

            paymentModifier3Element.appendChild(employerCodeElement);

            org.w3c.dom.Element siteElement = xmlDoc.createElement("NHIF_Site_Nr");

            siteElement.appendChild(xmlDoc.createTextNode("000"));

            paymentModifier3Element.appendChild(siteElement);

            org.w3c.dom.Element relationElement = xmlDoc.createElement("NHIF_Patient_Relation");

            relationElement.appendChild(xmlDoc.createTextNode("P"));

            paymentModifier3Element.appendChild(relationElement);

            org.w3c.dom.Element diagnosisElement = xmlDoc.createElement("Diagnosis_Code");

            diagnosisElement.appendChild(xmlDoc.createTextNode("0"));

            paymentModifier3Element.appendChild(diagnosisElement);

            org.w3c.dom.Element admissionDateElement = xmlDoc.createElement("Admit_Date");

            admissionDateElement.appendChild(xmlDoc.createTextNode(txDate));

            paymentModifier3Element.appendChild(admissionDateElement);

            org.w3c.dom.Element dischargeDateElement = xmlDoc.createElement("Discharge_Date");

            dischargeDateElement.appendChild(xmlDoc.createTextNode(txDate));

            paymentModifier3Element.appendChild(dischargeDateElement);

            org.w3c.dom.Element inpatientDaysElement = xmlDoc.createElement("Days_Used");

            inpatientDaysElement.appendChild(xmlDoc.createTextNode("0"));

            paymentModifier3Element.appendChild(inpatientDaysElement);

            org.w3c.dom.Element rebateAmountElement = xmlDoc.createElement("Amount");

            rebateAmountElement.appendChild(xmlDoc.createTextNode("0.00"));

            paymentModifier3Element.appendChild(rebateAmountElement);

            paymentModifiersElement.appendChild(paymentModifier3Element);

        }

        headerElement.appendChild(paymentModifiersElement);

        rootNodeElement.appendChild(headerElement);

        org.w3c.dom.Element memberElement = xmlDoc.createElement("Member");

        org.w3c.dom.Element memberNumberElement = xmlDoc.createElement("Membership_Number");

        memberNumberElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.cardNumber));

        memberElement.appendChild(memberNumberElement);

        org.w3c.dom.Element cardSerialNumberElement = xmlDoc.createElement("card_serialnumber");

        cardSerialNumberElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.cardSerialNumber));

        memberElement.appendChild(cardSerialNumberElement);

        org.w3c.dom.Element schemeNumberElement = xmlDoc.createElement("Scheme_Code");

        schemeNumberElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.medicalAidPlan));

        memberElement.appendChild(schemeNumberElement);

        org.w3c.dom.Element payerNumberElement = xmlDoc.createElement("Scheme_Plan");

        payerNumberElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.medicalAidCode));

        memberElement.appendChild(payerNumberElement);

        rootNodeElement.appendChild(memberElement);

        org.w3c.dom.Element patientElement = xmlDoc.createElement("Patient");

        org.w3c.dom.Element patientTypeElement = xmlDoc.createElement("Dependant");

        patientTypeElement.appendChild(xmlDoc.createTextNode("N"));

        patientElement.appendChild(patientTypeElement);

        org.w3c.dom.Element fnameElement = xmlDoc.createElement("First_Name");

        fnameElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.patientForenames));

        patientElement.appendChild(fnameElement);

        org.w3c.dom.Element mnameElement = xmlDoc.createElement("Middle_Name");

        mnameElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.patientSurName));

        patientElement.appendChild(mnameElement);

        org.w3c.dom.Element lnameElement = xmlDoc.createElement("Surname");

        lnameElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.patientSurName));

        patientElement.appendChild(lnameElement);

        org.w3c.dom.Element dobElement = xmlDoc.createElement("Date_Of_Birth");

        dobElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.patientDOB));

        patientElement.appendChild(dobElement);

        org.w3c.dom.Element genderElement = xmlDoc.createElement("Gender");

        String gender = "";

        try {

            java.sql.Statement pstmtPatient = connectDB.createStatement();

            java.sql.ResultSet rst1 = pstmtPatient.executeQuery("select DISTINCT sex FROM hp_patient_register WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' ORDER BY 1 LIMIT 1");

            while (rst1.next()) {
                if (rst1.getString(1).equalsIgnoreCase("Male")) {
                    gender = "M";
                } else {
                    gender = "F";
                }
            }

        } catch (java.sql.SQLException sq) {
            sq.printStackTrace();
        }
        genderElement.appendChild(xmlDoc.createTextNode(gender));

        patientElement.appendChild(genderElement);

        rootNodeElement.appendChild(patientElement);

        org.w3c.dom.Element dischangeNotesElement = xmlDoc.createElement("Discharge_Notes");
        dischangeNotesElement.appendChild(xmlDoc.createTextNode("Data as Discharge"));
        rootElement.appendChild(dischangeNotesElement);

        int k = 1;
        for (int j = 0; j <= rowCount; j++) {
            if (j < rowCount) {
                if (invoiceTable.getValueAt(j, 0) != null) {
                    k++;
                    txElement = xmlDoc.createElement("Service");

                    //  for (int k = 0; k < columnCount; k++) {
                    txDateElement = xmlDoc.createElement("Number");
                    txDateElement.appendChild(xmlDoc.createTextNode(String.valueOf(j + 1)));
                    txElement.appendChild(txDateElement);

                    txDateElement = xmlDoc.createElement("Invoice_Number");
                    txDateElement.appendChild(xmlDoc.createTextNode(invoiceNo));
                    txElement.appendChild(txDateElement);

                    txDateElement = xmlDoc.createElement("Global_Invoice_Nr");
                    txDateElement.appendChild(xmlDoc.createTextNode(invoiceNo));
                    txElement.appendChild(txDateElement);

                    txDateElement = xmlDoc.createElement("Start_Date");
                    txDateElement.appendChild(xmlDoc.createTextNode(txDate));
                    txElement.appendChild(txDateElement);

                    txTimeElement = xmlDoc.createElement("Start_Time");
                    txTimeElement.appendChild(xmlDoc.createTextNode(txTime));
                    txElement.appendChild(txTimeElement);

                    serviceProviderElement = xmlDoc.createElement("Provider");
                    org.w3c.dom.Element sproleElement = xmlDoc.createElement("Role");

                    sproleElement.appendChild(xmlDoc.createTextNode("SP"));

                    serviceProviderElement.appendChild(sproleElement);

                    org.w3c.dom.Element practiceElement = xmlDoc.createElement("Practice_Number");

                    practiceElement.appendChild(xmlDoc.createTextNode("SKSP_3213"));

                    serviceProviderElement.appendChild(practiceElement);

                    //serviceProviderElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase()));
                    txElement.appendChild(serviceProviderElement);

                    diagnoseCodeElement = xmlDoc.createElement("Diagnosis");

                    org.w3c.dom.Element stageElement = xmlDoc.createElement("Stage");

                    stageElement.appendChild(xmlDoc.createTextNode("P"));

                    diagnoseCodeElement.appendChild(stageElement);

                    org.w3c.dom.Element dcodeElement = xmlDoc.createElement("Code_Type");

                    dcodeElement.appendChild(xmlDoc.createTextNode("ICD"));

                    diagnoseCodeElement.appendChild(dcodeElement);

                    org.w3c.dom.Element realcodeElement = xmlDoc.createElement("Code");

                    String realCodes = "0";

                    try {
                        
                        java.sql.PreparedStatement pstmtDisease = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' AND date_recorded >= now()::date - 1");
                        java.sql.ResultSet rsetDisease = pstmtDisease.executeQuery();
                        while (rsetDisease.next()) {
                            realCodes = rsetDisease.getString(1);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    realcodeElement.appendChild(xmlDoc.createTextNode(realCodes));

                    diagnoseCodeElement.appendChild(realcodeElement);

                    txElement.appendChild(diagnoseCodeElement);

                    String department = "";
                    
                    try {
                        java.sql.PreparedStatement pstmtDept = connectDB.prepareStatement("SELECT DISTINCT UPPER(main_service) FROM pb_operating_parameters WHERE gl_account = ? ORDER BY 1 LIMIT 1");
                    
                        pstmtDept.setString(1, invoiceTable.getValueAt(j, 4).toString());
                        
                        java.sql.ResultSet rsetDept = pstmtDept.executeQuery();
                        
                        while(rsetDept.next()){
                            department = rsetDept.getString(1);
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
                        javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                    encounterTypeElement = xmlDoc.createElement("Encounter_Type");
                    encounterTypeElement.appendChild(xmlDoc.createTextNode(department));
                    txElement.appendChild(encounterTypeElement);

                    codeTypeElement = xmlDoc.createElement("Code_Type");
                    codeTypeElement.appendChild(xmlDoc.createTextNode("Internal")); // Testing
                    txElement.appendChild(codeTypeElement);

                    String itemCode = "0";

                    if (invoiceTable.getValueAt(j, 0) != null) {
                        if (com.afrisoftech.lib.GetItemInfo.getItemCode(String.valueOf(invoiceTable.getValueAt(j, 0)), connectDB).length() > 0) {
                            itemCode = com.afrisoftech.lib.GetItemInfo.getItemCode(String.valueOf(invoiceTable.getValueAt(j, 0)), connectDB);
                        }
                    }
                    codeElement = xmlDoc.createElement("Code");
                    codeElement.appendChild(xmlDoc.createTextNode(itemCode)); // for testing
                    txElement.appendChild(codeElement);

                    codeDescriptionElement = xmlDoc.createElement("Code_Description");
                    codeDescriptionElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 0).toString()));
                    txElement.appendChild(codeDescriptionElement);

                    quantityElement = xmlDoc.createElement("Quantity");
                    quantityElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 1).toString()));
                    txElement.appendChild(quantityElement);

                    amountElement = xmlDoc.createElement("Total_Amount");
                    amountElement.appendChild(xmlDoc.createTextNode(invoiceTable.getValueAt(j, 3).toString()));
                    txElement.appendChild(amountElement);

                    org.w3c.dom.Element reasonElement = xmlDoc.createElement("Reason");
                    reasonElement = xmlDoc.createElement("Reason");
                    reasonElement.appendChild(xmlDoc.createTextNode("0"));
                    txElement.appendChild(reasonElement);
                    // System.out.println("What is the copay value "+copayCounter);
                }
            }

            rootElement.appendChild(txElement);
        }
        txElement = xmlDoc.createElement("Service");

        //  for (int k = 0; k < columnCount; k++) {
        txDateElement = xmlDoc.createElement("Number");
        txDateElement.appendChild(xmlDoc.createTextNode(String.valueOf(k)));
        txElement.appendChild(txDateElement);

        txDateElement = xmlDoc.createElement("Invoice_Number");
        txDateElement.appendChild(xmlDoc.createTextNode(invoiceNo));
        txElement.appendChild(txDateElement);

        txDateElement = xmlDoc.createElement("Global_Invoice_Nr");
        txDateElement.appendChild(xmlDoc.createTextNode(invoiceNo));
        txElement.appendChild(txDateElement);

        txDateElement = xmlDoc.createElement("Start_Date");
        txDateElement.appendChild(xmlDoc.createTextNode(txDate));
        txElement.appendChild(txDateElement);

        txTimeElement = xmlDoc.createElement("Start_Time");
        txTimeElement.appendChild(xmlDoc.createTextNode(txTime));
        txElement.appendChild(txTimeElement);

        serviceProviderElement = xmlDoc.createElement("Provider");
        org.w3c.dom.Element sproleElement = xmlDoc.createElement("Role");

        sproleElement.appendChild(xmlDoc.createTextNode("SP"));

        serviceProviderElement.appendChild(sproleElement);

        org.w3c.dom.Element practiceElement = xmlDoc.createElement("Practice_Number");

        practiceElement.appendChild(xmlDoc.createTextNode("SKSP_3213"));

        serviceProviderElement.appendChild(practiceElement);

        //serviceProviderElement.appendChild(xmlDoc.createTextNode(com.afrisoftech.hospital.HospitalMain.getAbsoluteCompanyName().toUpperCase()));
        txElement.appendChild(serviceProviderElement);

        diagnoseCodeElement = xmlDoc.createElement("Diagnosis_Code");

        org.w3c.dom.Element stageElement = xmlDoc.createElement("Stage");

        stageElement.appendChild(xmlDoc.createTextNode("P"));

        diagnoseCodeElement.appendChild(practiceElement);

        org.w3c.dom.Element dcodeElement = xmlDoc.createElement("Code_Type");

        dcodeElement.appendChild(xmlDoc.createTextNode("ICD"));

        diagnoseCodeElement.appendChild(dcodeElement);

        org.w3c.dom.Element realcodeElement = xmlDoc.createElement("Code");

        try {
            java.sql.PreparedStatement pstmtDisease = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE patient_no = '" + com.afrisoftech.hospital.GeneralBillingIntfr.patientNumberTxt.getText() + "' AND date_recorded >= now()::date - 1");
            java.sql.ResultSet rsetDisease = pstmtDisease.executeQuery();
            while (rsetDisease.next()) {
                realcodeElement.appendChild(xmlDoc.createTextNode(rsetDisease.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }
        diagnoseCodeElement.appendChild(realcodeElement);

        txElement.appendChild(diagnoseCodeElement);

        encounterTypeElement = xmlDoc.createElement("Encounter_Type");
        encounterTypeElement.appendChild(xmlDoc.createTextNode("Consultations"));
        txElement.appendChild(encounterTypeElement);

        codeTypeElement = xmlDoc.createElement("Code_Type");
        codeTypeElement.appendChild(xmlDoc.createTextNode("Internal")); // Testing
        txElement.appendChild(codeTypeElement);

        codeElement = xmlDoc.createElement("Code");
        codeElement.appendChild(xmlDoc.createTextNode("")); // for testing
        txElement.appendChild(codeElement);

        codeDescriptionElement = xmlDoc.createElement("Code_Description");
        codeDescriptionElement.appendChild(xmlDoc.createTextNode(copayDescription));
        txElement.appendChild(codeDescriptionElement);

        quantityElement = xmlDoc.createElement("Quantity");
        quantityElement.appendChild(xmlDoc.createTextNode("1"));
        txElement.appendChild(quantityElement);

        amountElement = xmlDoc.createElement("Total_Amount");
        amountElement.appendChild(xmlDoc.createTextNode(String.valueOf(copayAmount)));
        txElement.appendChild(amountElement);

        org.w3c.dom.Element reasonElement = xmlDoc.createElement("Reason");
        reasonElement = xmlDoc.createElement("Reason");
        reasonElement.appendChild(xmlDoc.createTextNode(""));
        txElement.appendChild(reasonElement);

        rootNodeElement.appendChild(rootElement);

        org.apache.xml.serialize.OutputFormat format = new org.apache.xml.serialize.OutputFormat();

        format.setIndenting(true);

        org.apache.xml.serialize.XMLSerializer xmlSerializer = new org.apache.xml.serialize.XMLSerializer(xmlOutpuStream, format);

        try {

            xmlSerializer.asDOMSerializer();

            xmlSerializer.serialize(xmlDoc);

        } catch (java.io.IOException ioExec) {

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
        org.w3c.dom.Element cardSerialElement = xmlDoc.createElement("Card_Serial");

        cardSerialElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.cardNumber));//cardSerial));

        claimHeaderElement.appendChild(cardSerialElement);

////        org.w3c.dom.Element claimHeaderElement = xmlDoc.createElement("claimHeader");
        org.w3c.dom.Element pooledNoElement = xmlDoc.createElement("Pool_Nr");

        pooledNoElement.appendChild(xmlDoc.createTextNode(biz.systempartners.claims.XMLClaimFile.cardNr));//poolNr));

        claimHeaderElement.appendChild(pooledNoElement);
        invoiceNo = com.afrisoftech.hospital.GeneralBillingIntfr.transNo;

        org.w3c.dom.Element receiptNo = xmlDoc.createElement("Receipt_Nr");
        receiptNo.appendChild(xmlDoc.createTextNode(invoiceNo));
        claimHeaderElement.appendChild(receiptNo);

        org.w3c.dom.Element claimNo = xmlDoc.createElement("Claim_Nr");
        claimNo.appendChild(xmlDoc.createTextNode(claimsNo));
        claimHeaderElement.appendChild(claimNo);

        patientName = com.afrisoftech.hospital.GeneralBillingIntfr.patientNameTxt.getText();
        org.w3c.dom.Element patient = xmlDoc.createElement("Patient_Name");
        patient.appendChild(xmlDoc.createTextNode(patientName));
        claimHeaderElement.appendChild(patient);
        /*
         org.w3c.dom.Element patientNoElement = xmlDoc.createElement("patientNo");
       
         patientNoElement.appendChild(xmlDoc.createTextNode(patientNo));
       
         claimHeaderElement.appendChild(patientNoElement);
       
         org.w3c.dom.Element patientNameElement = xmlDoc.createElement("patientName");
       
         patientNameElement.appendChild(xmlDoc.createTextNode(patientName));
       
         claimHeaderElement.appendChild(patientNameElement);
         /*
         org.w3c.dom.Element schemeMemberNoElement = xmlDoc.createElement("schemeMemberNo");
       
         schemeMemberNoElement.appendChild(xmlDoc.createTextNode(schemeMemberNo));
       
         claimHeaderElement.appendChild(schemeMemberNoElement);
       
         org.w3c.dom.Element schemeNameElement = xmlDoc.createElement("schemeName");
       
         schemeNameElement.appendChild(xmlDoc.createTextNode(schemeName));
       
         claimHeaderElement.appendChild(schemeNameElement);
       
         org.w3c.dom.Element schemePayerElement = xmlDoc.createElement("schemePayer");
       
         schemePayerElement.appendChild(xmlDoc.createTextNode(schemePayer));
       
         claimHeaderElement.appendChild(schemePayerElement);
       
         org.w3c.dom.Element accountNoElement = xmlDoc.createElement("accountNo");
       
         accountNoElement.appendChild(xmlDoc.createTextNode(accountNo));
       
         claimHeaderElement.appendChild(accountNoElement);
       
         org.w3c.dom.Element invoiceNoElement = xmlDoc.createElement("invoiceNo");
       
         invoiceNoElement.appendChild(xmlDoc.createTextNode(invoiceNo));
       
         claimHeaderElement.appendChild(invoiceNoElement);
       
         org.w3c.dom.Element healthcareProviderIDElement = xmlDoc.createElement("healthCareProviderID");
       
         healthcareProviderIDElement.appendChild(xmlDoc.createTextNode(healthcareProviderID));
       
         claimHeaderElement.appendChild(healthcareProviderIDElement);
         */
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

    /**
     * @return the locationID
     */
    public java.lang.String getLocationID() {
        return locationID;
    }

    /**
     * @param locationID the locationID to set
     */
    public void setLocationID(java.lang.String locationID) {
        this.locationID = locationID;
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
    java.util.Vector invoiceChildVector = new java.util.Vector(1, 1);
    java.lang.String patientNo;
    java.lang.String patientName;
    java.lang.String schemeMemberNo;
    java.lang.String schemeName;
    java.lang.String schemePayer;
    java.lang.String accountNo;
    public static java.lang.String invoiceNo;
    java.lang.String claimsNo;
    java.lang.String healthcareProviderID;
    javax.swing.JTable invoiceTable;
    private java.lang.String locationID;
    javax.swing.JTextField coPay;
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
}
