//Author olson mutwiri
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.afrisoftech.lib.PatientAge;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileOutputStream;

public class PatientRegReprintFormPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    int numberSeq = 0;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientRegReprintFormPdf(java.sql.Connection connDb) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new EntranceFeePdf().EntranceFeePdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            //    this.generatePdf();

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(200);

                System.out.println("It's time for us threads to get back to work after the nap");

            } catch (java.lang.InterruptedException IntExec) {

                System.out.println(IntExec.getMessage());

            }

            threadCheck = false;


            System.out.println("We shall be lucky to get back to start in one piece");

        }

        if (!threadCheck) {



            Thread.currentThread().stop();

        }

    }

    public java.lang.String getDateLable() {

        java.lang.String date_label = null;

        java.lang.String month_now_strs = null;

        java.lang.String date_now_strs = null;

        java.lang.String year_now_strs = null;

        java.lang.String minute_now_strs = null;

        java.lang.String hour_now_strs = null;

        java.lang.Runtime rt = java.lang.Runtime.getRuntime();

        java.util.Calendar calinst = java.util.Calendar.getInstance();

        java.util.Date date_now = calinst.getTime();

        int date_now_str = date_now.getDate();

        int month_now_str = date_now.getMonth();

        int year_now_str = date_now.getYear();

        int hour_now_str = date_now.getHours();

        int minute_now_str = date_now.getMinutes();

        int year_now_abs = year_now_str - 100;

        if (year_now_abs < 10) {

            year_now_strs = "200" + year_now_abs;

        } else {

            year_now_strs = "20" + year_now_abs;

        }

        switch (month_now_str) {

            case 0:
                month_now_strs = "JAN";

                break;

            case 1:
                month_now_strs = "FEB";

                break;

            case 2:
                month_now_strs = "MAR";

                break;

            case 3:
                month_now_strs = "APR";

                break;

            case 4:
                month_now_strs = "MAY";

                break;

            case 5:
                month_now_strs = "JUN";

                break;

            case 6:
                month_now_strs = "JUL";

                break;

            case 7:
                month_now_strs = "AUG";

                break;

            case 8:
                month_now_strs = "SEP";

                break;

            case 9:
                month_now_strs = "OCT";

                break;

            case 10:
                month_now_strs = "NOV";

                break;

            case 11:
                month_now_strs = "DEC";

                break;

            default:
                if (month_now_str < 10) {

                    month_now_strs = "0" + month_now_str;

                } else {

                    month_now_strs = "" + month_now_str;

                }

        }

        if (date_now_str < 10) {

            date_now_strs = "0" + date_now_str;

        } else {

            date_now_strs = "" + date_now_str;

        }

        if (minute_now_str < 10) {

            minute_now_strs = "0" + minute_now_str;

        } else {

            minute_now_strs = "" + minute_now_str;

        }

        if (hour_now_str < 10) {

            hour_now_strs = "0" + hour_now_str;

        } else {

            hour_now_strs = "" + hour_now_str;

        }

        date_label = date_now_strs + month_now_strs + year_now_strs + "@" + hour_now_strs + minute_now_strs;

        return date_label;

    }

    public void generatePdf(java.sql.Connection con, String patientNo, String type, String receipt_no, String receipt_time) {

        String patientName = null;
        String patientAge = null;
        String PatientGender = null;
        String patientNationality = null;
        String patientIDPassport = null;
        String placeofBirth = null;
        String patientOccupation = null;
        String maritalStatus = null;
        String patientReligion = null;
        String patientTelephone = null;
        String patientEmail = null;
        String patientVillage = null;
        String patientLocation = null;
        String patientHomeCounty = null;
        String patientResidenceCounty = null;
        String patientNOK = null;
        String patientRelation = null;
        String patientNOKTelephone = null;
        String patientReferFrom = null;
        String bookingDate = null;
        String specialtyClinic = null;
        String statusNHIF = null;
        String NHIFNumber = null;
        String educationLevel = null;
        String employerTelephone = null;
        String patientEmployer = null;
        String paidAmount = null;
        String receiptNumber = null;
        String cSheetNo = null;
        String invoiceNumber = null;
        String interviewerName = null;
        String interviewDate = null;
        String servicePoint = null;
        String unitNumber = null;
        String patientNumber = null;
        String admissionWard = null;
        String admissionDate = null;
        String admissionType = null;
        String claimNumber = null;
        java.util.Date date = null;
        String gender = null;
        String typeofAccident = null;
        String modeofArrival = null;
        String nameofPoliceOfficer = null;
        String policeForceNO = null;
        String policeStationAccident = null;
        String dateAccident = null;
        String nameofDriver = null;
        String accidentVehicleNo = null;
        String driverLicenceNo = null;
        String driversIDNo = null;
        String patNo = null;
        patNo = patientNo;
        try {


//            java.sql.ResultSet rse124 = stm124.executeQuery("SELECT first_name, second_name, nok, residence, \n" +
//"       address, year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, \n" +
//"       description, category, expiry_date, last_visit, department, member_name, \n" +
//"       card_no, emails, id_no, nok_add, old_patient_no, pat_nationality, \n" +
//"       nok_telno, nok_relationship, nok_residence, nok_email, pat_marital_status, \n" +
//"       tribe, district, locations, sub_location, chief_name, sub_chief, \n" +
//"       information_source, education_level, occupation, pat_religion, \n" +
//"       patient_race, birth_place, waiting_patient, email, home_county, \n" +
//"       residence_county, nhif_status, nhif_number, employer_name, employer_telephone, \n" +
//"       refer_source, charge_sheet_no, specialty_clinic\n" +
//"  FROM hp_patient_register; where patient_no='"+patNo+"' ");
            if (type.equals("OP")) {

                java.sql.Statement stm12456 = con.createStatement();
                java.sql.ResultSet rse124 = stm12456.executeQuery("SELECT   hp.patient_no, first_name, second_name, last_name, nok, residence,"
                        + " address, year_of_birth, tel_no, sex, date, pay_mode, payer, account_no, "
                        + " description, category, expiry_date, last_visit, department, member_name,"
                        + " card_no, emails, id_no, nok_add, old_patient_no, pat_nationality,"
                        + " nok_telno, nok_relationship, nok_residence, nok_email, pat_marital_status,"
                        + " tribe, district, locations, sub_location, chief_name, sub_chief,"
                        + " information_source, education_level, occupation, pat_religion,"
                        + " patient_race, birth_place, waiting_patient, email, home_county,"
                        + " residence_county, nhif_status, nhif_number, employer_name, employer_telephone,"
                        + " refer_source, charge_sheet_no, specialty_clinic, accident_type, arrival_mode, police_officer_no, police_station,date_time, driver_name, accident_vehicle_no, driver_license, driver_id_no,service_point,user_name FROM  hp_patient_register hp LEFT JOIN rta_info rt  on hp.patient_no=rt.patient_no WHERE hp.patient_no='" + patNo + "' ORDER BY date_time desc  limit 1 ");
                while (rse124.next()) {

                    patientName = rse124.getString(2).toUpperCase() + " " + rse124.getString(3).toUpperCase();
                    patientAge = PatientAge.getPatientActualAge(con, com.afrisoftech.lib.SQLDateFormat.getSQLDate(rse124.getDate("year_of_birth")));
                    PatientGender = rse124.getString("sex");
                    patientNationality = rse124.getString("pat_nationality");
                    patientIDPassport = rse124.getString("id_no");
                    placeofBirth = rse124.getString("birth_place");
                    patientOccupation = rse124.getString("occupation");
                    maritalStatus = rse124.getString("pat_marital_status");
                    patientReligion = rse124.getString("pat_religion");
                    patientTelephone = rse124.getString("tel_no");
                    patientEmail = rse124.getString("emails");
                    patientVillage = rse124.getString("residence");
                    patientLocation = rse124.getString("locations");
                    patientHomeCounty = rse124.getString("home_county");
                    patientResidenceCounty = rse124.getString("residence_county");
                    patientNOK = rse124.getString("nok");
                    patientRelation = rse124.getString("nok_relationship");
                    //jddfsjdsj
                    patientNOKTelephone = rse124.getString("nok_telno");
                    patientReferFrom = rse124.getString("refer_source");
                    bookingDate = rse124.getString("date");
                    specialtyClinic = rse124.getString("specialty_clinic");
                    statusNHIF = rse124.getString("nhif_status");
                    NHIFNumber = rse124.getString("nhif_number");
                    educationLevel = rse124.getString("education_level");
                    employerTelephone = rse124.getString("employer_telephone");
                    patientEmployer = rse124.getString("employer_name");
                    paidAmount = "";
                    receiptNumber = "";
                    cSheetNo = rse124.getString("charge_sheet_no");
                    invoiceNumber = "";
                    interviewerName = rse124.getString("user_name");
                    interviewDate = rse124.getString("date");
                    servicePoint = rse124.getString("service_point");
                    unitNumber = rse124.getString("patient_race");
                    patientNumber = rse124.getString("patient_no");
                    admissionWard = "";
                    admissionDate = "";
                    admissionType = "";
                    claimNumber = "";
                    date = com.afrisoftech.lib.SQLDateFormat.getSQLDate(rse124.getDate("date"));
                    gender = rse124.getString("sex");
                    typeofAccident = rse124.getString("accident_type");
                    modeofArrival = rse124.getString("arrival_mode");
                    nameofPoliceOfficer = rse124.getString("police_officer_no");
                    policeForceNO = rse124.getString("police_officer_no");
                    policeStationAccident = rse124.getString("police_station");
                    dateAccident = rse124.getString("date_time");
                    nameofDriver = rse124.getString("driver_name");
                    accidentVehicleNo = rse124.getString("accident_vehicle_no");
                    driverLicenceNo = rse124.getString("driver_license");
                    driversIDNo = rse124.getString("driver_id_no");
                    System.out.println("");

                }

                ///getting the receipt details
                try {
                    java.sql.Statement stmt11 = connectDB.createStatement();
                    //java.sql.ResultSet rset11 = stmt11.executeQuery("SELECT  description,receipt_no, debit  FROM ac_cash_collection where  patient_no='" + patientNumberTxt.getText() + "' and receipt_time::date >= current_date-1 ");
                    java.sql.ResultSet rset11 = stmt11.executeQuery("SELECT  description,receipt_no, debit  FROM ac_cash_collection where  patient_no='" + patNo + "'   and receipt_no='" + receipt_no + "' and receipt_time::date='" + receipt_time + "'::date ");
                    while (rset11.next()) {
                        receiptNumber = rset11.getObject(2).toString();
                        paidAmount = rset11.getObject(3).toString();

                        System.out.println("This is the receipt no " + receiptNumber + " and this is the AMOUNT " + paidAmount);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    ex.getMessage();
                }


                ///end
            } else if (type.equals("IP")) {
            }


        } catch (java.sql.SQLException sq) {
            javax.swing.JOptionPane.showMessageDialog(null, sq.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);

            System.out.println(sq.getMessage());
        }

        java.util.Date date111 = date;
        java.lang.String formLabel = null;

//        if(admissionWard.toCharArray().length < 1){
//            
//            formLabel = "PATIENT REGISTRATION FORM";
//            
//        } else {
//            formLabel = "PATIENT ADMISSION FORM";
//        }
        if (type.equals("OP")) {

            formLabel = "PATIENT REGISTRATION FORM";

        } else {
            formLabel = "PATIENT ADMISSION FORM";
        }

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();


        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    PdfWriter writer = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(formLabel + " : ", pFontHeader2), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    long dateNow = calendar.getTimeInMillis();

                    java.sql.Date datenowSql = new java.sql.Date(dateNow);

                    System.out.println(datenowSql.toString());


                    try {
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {16, 16, 16, 16, 16, 16};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((105));

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);

                        int headerwidths2[] = {30, 70};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));

                        table2.getDefaultCell().setFixedHeight(50);
                        //phrase = new Phrase("", pFontHeader1);

                        //table.addCell(phrase);
                        table2.getDefaultCell().setColspan(2);
                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                        img.scalePercent(50);
                        // img.sc//aleAbsolute(200, 200);
                        table2.addCell(img);
                        String compName = null;
                        String District = null;
                        String Region = null;
                        String date2 = null;
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();

                            java.sql.Statement st4 = connectDB.createStatement();

                            java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch,region FROM pb_hospitalprofile");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                            while (rset2.next()) {
                                compName = rset2.getObject(1).toString();
                                District = rset2.getObject(2).toString();
                                Region = rset2.getObject(3).toString();
                            }
                            while (rset4.next()) {
                                date2 = rset4.getObject(1).toString();
                            }
                        } catch (java.sql.SQLException ex) {
                            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                            ex.printStackTrace();
                        }
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().set
                        Phrase phrase = new Phrase(compName.toUpperCase(), pFontHeader3);
                        table2.addCell(phrase);


                        table.addCell(table2); // painting the logo


                        table.setWidths(headerwidths);

                        table.setWidthPercentage((105));


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(5);

                        phrase = new Phrase(formLabel, pFontHeader2);

                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("FORM: 260", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        // table.getDefaultCell().set
                        phrase = new Phrase("1. PATIENT PERSONAL DETAILS", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        dbObject = new com.afrisoftech.lib.DBObject();

                        phrase = new Phrase("Patient Name: " + patientName, pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Age: " + dbObject.getDBObject((Object) patientAge, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Nationality: " + dbObject.getDBObject(patientNationality, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("ID-Passport: " + dbObject.getDBObject(patientIDPassport, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Place of Birth: " + dbObject.getDBObject(placeofBirth, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Occupation: " + dbObject.getDBObject(patientOccupation, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Marital Status: " + dbObject.getDBObject(maritalStatus, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Religion: " + dbObject.getDBObject(patientReligion, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Patient Tel.: " + dbObject.getDBObject(patientTelephone, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Patient Email.: " + dbObject.getDBObject(patientEmail, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Patient Gender.: " + dbObject.getDBObject(gender, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("2. PATIENT RESIDENCE", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Home Village: " + dbObject.getDBObject(patientVillage, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Home Location: " + dbObject.getDBObject(patientLocation, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("County of Birth: " + dbObject.getDBObject(patientHomeCounty, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("County of Residence: " + dbObject.getDBObject(patientResidenceCounty, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("3. NEXT OF KIN", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Next of Kin: " + dbObject.getDBObject(patientNOK, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Relationship: " + dbObject.getDBObject(patientRelation, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("NOK Telephone: " + dbObject.getDBObject(patientNOKTelephone, ""), pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("4. CURRENT ATTENDANCE AT KNH", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Referred From: " + dbObject.getDBObject(patientReferFrom, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Appointment Dates: " + dbObject.getDBObject(date111, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Specialty Clinic: " + dbObject.getDBObject(specialtyClinic, ""), pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("5. PATIENT SOCIAL ECONOMIC HISTORY", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        if (statusNHIF != null) {
                            if (statusNHIF.contains("f")) {
                                statusNHIF = "NO";
                            } else {
                                statusNHIF = "YES";
                            }
                        } else {
                            statusNHIF = "NO";
                        }

                        phrase = new Phrase("NHIF Registered: " + dbObject.getDBObject(statusNHIF, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("NHIF No.: " + dbObject.getDBObject(NHIFNumber, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Education Level: " + dbObject.getDBObject(educationLevel, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Employer: " + dbObject.getDBObject(patientEmployer, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Telephone: " + dbObject.getDBObject(employerTelephone, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("6. DEPOSITS AND OTHER PAYMENTS", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Amount Paid: " + dbObject.getDBObject(paidAmount, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Receipt No.: " + dbObject.getDBObject(receiptNumber, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("C-Sheet No.: " + dbObject.getDBObject(cSheetNo, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Invoice Number: " + dbObject.getDBObject(invoiceNumber, ""), pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("NHIF Claim No.: " + dbObject.getDBObject(claimNumber, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase(" ", pFontHeader);
                        // table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("7. INTERVIEWERS INFORMATION", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Interviewer: " + dbObject.getDBObject(interviewerName, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Date: " + dbObject.getDBObject(interviewDate, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Service Point: " + dbObject.getDBObject(servicePoint, ""), pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("8. ADMISSION INFORMATION", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Admission Ward: " + dbObject.getDBObject(admissionWard, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Admission Date: " + dbObject.getDBObject(admissionDate, ""), pFontHeader2);
                        table.addCell(phrase);
                        phrase = new Phrase("Admission Type: " + dbObject.getDBObject(admissionType, ""), pFontHeader);
                        table.addCell(phrase);



                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        ///RTA DETAILS
//                            if(typeofAccident.length()>0 || modeofArrival.length()>0 ||
//                                    nameofPoliceOfficer.length()>0 || policeForceNO.length()>0 ||
//                                    policeStationAccident.length()>0 ||  dateAccident.length()>0 ||
//                                    nameofDriver.length()>0 || accidentVehicleNo.length()>0 ||
//                                    driverLicenceNo.length()>0 || driversIDNo.length()>0 ){
                        if (typeofAccident != null || modeofArrival != null
                                || nameofPoliceOfficer != null || policeForceNO != null
                                || policeStationAccident != null || dateAccident != null
                                || nameofDriver != null || accidentVehicleNo != null
                                || driverLicenceNo != null || driversIDNo != null) {


                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("RTA DETAILS : ", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase(dbObject.getDBObject("Accident Date:-" + dateAccident, ""), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase(dbObject.getDBObject("Type of Accident:- " + typeofAccident, "") + "" + dbObject.getDBObject(". MODE of ARRIVAL:-" + modeofArrival, ""), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase(dbObject.getDBObject("Name Of Police Officer :- " + nameofPoliceOfficer, "") + "."
                                    + "" + dbObject.getDBObject(" Police Officer Force No:-" + policeForceNO, ""), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase(dbObject.getDBObject("Police Station Accident Scene :- " + policeStationAccident, ""), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase(dbObject.getDBObject("Name of Driver:- " + nameofDriver, "") + dbObject.getDBObject(". Accident Vehicle NO:-" + accidentVehicleNo, ""), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Driving Licence No:- " + dbObject.getDBObject(driverLicenceNo, "") + ". Driver's ID No :-" + dbObject.getDBObject(driversIDNo, ""), pFontHeader);
                            table.addCell(phrase);

                        }

                        ///RTA END

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ", pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setBorder(PdfCell.RECTANGLE);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" Patient Number : ".toUpperCase() + dbObject.getDBObject(patNo.toUpperCase(), ""), pFontHeader2);
                        table.addCell(phrase);
                        //   phrase = new Phrase(" ", pFontHeader);
                        //   table.addCell(phrase);
                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Unit Number : ".toUpperCase() + dbObject.getDBObject(unitNumber, ""), pFontHeader3);

                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        //PdfWriter writer = PdfWriter.getInstance(docPdf, new FileOutputStream(tempFile));
                        PdfContentByte cb = writer.getDirectContent();

                        Barcode128 code128 = new Barcode128();

                        System.out.println("PATIENT NO " + patNo + " NAME " + patientName);
                        code128.setCode(patNo + " " + patientName);


                        code128.setBarHeight(16);

                        code128.setTextAlignment(Element.ALIGN_LEFT);

                        docPdf.add(table);

                        docPdf.add(code128.createImageWithBarcode(cb, null, null));

                        //  docPdf.add(table);


                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();

            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }
    }

    public void generatePdf(String string, String text, String selectedStatus, String toString, String text0, String toString0, String toString1, String selectedStatus0, String toString2, String text1, String text_, String text2, String toString3, String toString4, String toString5, String text3, String toString6, String text4, String text5, String _0, String toString7, String nhifStatusTxt, String text6, String toString8, String text7, String text8, String _1, String _2, String text9, String _3, String interviewer, String serverTimeStamp, String toString9, String text10, String text11, String toString10, String serverTimeStamp0, String toString11, String text12) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
