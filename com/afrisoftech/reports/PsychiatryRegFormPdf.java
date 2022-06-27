//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileOutputStream;
//import com.itextpdf.text.pdf.BarcodeQRCode;

public class PsychiatryRegFormPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject = new com.afrisoftech.lib.DBObject();
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
    com.lowagie.text.Font pFontHeader2y = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader2x = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
    com.lowagie.text.Font pFontHeadercolor = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new java.awt.Color(0, 0, 255));
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientRegFormPdf(java.sql.Connection connDb) {

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

    public void callPdf(java.sql.Connection connDb, String patNo, String patientType) {
        connectDB = connDb;
        String name = "";
        String age = "";
        String patgender = "";
        String nationality = "";
        String idPassport = "";
        String placeBirth = "";
        String occupation = "";
        String maritalstatus = "";
        String patientReligion = "";
        String patientTelephone = "";
        String patientEmail = "";
        String patientVillage = "";
        String patientLocation = "";
        String patientHomeCounty = "";
        String patientResidenceCounty = "";
        String patientNOK = "";
        String patientRelation = "";
        String patientNOKTelephone = "";
        String patientReferFrom = "";
        String bookingDate = "";
        String specialtyClinic = "";
        String statusNHIF = "";
        String NHIFNumber = "";
        String educationLevel = "";
        String employerTelephone = "";
        String patientEmployer = "";
        String paidAmount = "";
        String receiptNumber = "";
        String cSheetNo = "";
        String invoiceNumber = "";
        String interviewerName = "";
        String interviewDate = "";
        String servicePoint = "";
        String unitNumber = "";
        String patientNumber = "";
        String admissionWard = "";
        String admissionDate = "";
        String admissionType = "";
        String claimNumber = "";
        java.util.Date date = null;
        String gender = "";
        String typeofAccident = "";
        String modeofArrival = "";
        String nameofPoliceOfficer = "";
        String policeForceNO = "";
        String policeStationAccident = "";
        String dateAccident = "";
        String nameofDriver = "";
        String accidentVehicleNo = "";
        String driverLicenceNo = "";
        String driversIDNo = "";
        String registrationTime = "";
        String sNam = "";
        java.util.Date dobb = null;
        String noOfChildre = "";
        String patRess = "-";
        String chieff = "-";
        String subchieff = "-";
        String nokAddresss = "-";
        String broughtBy = "-";
        String broughtByRel = "-";
        String broughtByAddress = "-";
        String broughtByTel = "-";
        String authorityy = "-";
        String nokIDD = "-";
        String assignedClinicc = "-";
        String purposes = "-";

        try {
            if (patientType.equalsIgnoreCase("OP")) {
                java.sql.Statement stm12fd = connectDB.createStatement();
                java.sql.ResultSet rse12fd = stm12fd.executeQuery("SELECT  second_name || ' ' || last_name, nok, residence, \n"
                        + "       address, year_of_birth::date, tel_no, sex, date, pay_mode, payer, account_no, \n"
                        + "       description, category, expiry_date, last_visit, department, member_name, \n"
                        + "       card_no, emails, id_no, nok_add, old_patient_no, pat_nationality, \n"
                        + "       nok_telno, nok_relationship, nok_residence, nok_email, pat_marital_status, \n"
                        + "       tribe, district, locations, sub_location, chief_name, sub_chief, \n"
                        + "       information_source, education_level, occupation, pat_religion, \n"
                        + "       patient_race, birth_place, waiting_patient, email, home_county, \n"
                        + "       residence_county, nhif_status, nhif_number, employer_name, employer_telephone, \n"
                        + "       refer_source, charge_sheet_no, specialty_clinic, (select input_date"
                        + " from hp_patient_visit where hp_patient_register.patient_no = hp_patient_visit.patient_no order by input_date desc limit 1) as data_capture_time \n"
                        + " , first_name, no_of_children,brought_by,brought_by_relation,brought_by_address, "
                        + " brought_by_tel,authority, assigned_clinic, purpose FROM hp_patient_register where patient_no='" + patNo + "'");
                while (rse12fd.next()) {

                    name = rse12fd.getString(1);
                    sNam = rse12fd.getString("first_name");
                    noOfChildre = rse12fd.getString("no_of_children");
                    patRess = rse12fd.getString("residence");
                    chieff = rse12fd.getString("chief_name");
                    subchieff = rse12fd.getString("sub_chief");
                    nokAddresss = rse12fd.getString("nok_residence");
                    broughtBy = rse12fd.getString("brought_by");
                    broughtByRel = rse12fd.getString("brought_by_relation");
                    broughtByAddress = rse12fd.getString("brought_by_address");
                    broughtByTel = rse12fd.getString("brought_by_tel");
                    authorityy = rse12fd.getString("authority");
                    assignedClinicc = rse12fd.getString("assigned_clinic");
                    purposes = rse12fd.getString("purpose");
                    java.util.Date dob = (java.util.Date) (rse12fd.getDate(5));
                    dobb = dob;
                    age = String.valueOf(com.afrisoftech.lib.PatientAge.getPatientActualAge(connectDB, dob));
                    patgender = rse12fd.getString(7);
                    nationality = rse12fd.getString(23);
                    idPassport = rse12fd.getString(20);
                    placeBirth = rse12fd.getString(40);
                    occupation = rse12fd.getString(37);
                    maritalstatus = rse12fd.getString(28);
                    patientReligion = rse12fd.getString(38);
                    patientTelephone = rse12fd.getString(6);
                    patientEmail = rse12fd.getString(19);
                    nokIDD = rse12fd.getString("nok_email");
                    patientVillage = rse12fd.getString(32);
                    patientLocation = rse12fd.getString(3);
                    patientHomeCounty = rse12fd.getString(43);
                    patientResidenceCounty = rse12fd.getString(44);
                    patientNOK = rse12fd.getString(2);
                    patientRelation = rse12fd.getString(25);
                    patientNOKTelephone = rse12fd.getString(24);
                    patientReferFrom = rse12fd.getString(49);
                    bookingDate = "";
                    specialtyClinic = rse12fd.getString(51);
                    System.out.println("Interested [" + new com.afrisoftech.lib.DBObject() + "] [" + rse12fd.getString(45));
                    if (new com.afrisoftech.lib.DBObject().getDBObject(rse12fd.getString(45), "-").startsWith("t")) {
                        statusNHIF = "YES";
                    } else {
                        statusNHIF = "NO";
                    }
                    NHIFNumber = rse12fd.getString(46);
                    educationLevel = rse12fd.getString(36);
                    employerTelephone = rse12fd.getString(48);
                    patientEmployer = rse12fd.getString(47);
                    cSheetNo = rse12fd.getString(50);
                    unitNumber = rse12fd.getString(39);
                    gender = rse12fd.getString(7);
                    registrationTime = dbObject.getDBObject(rse12fd.getTimestamp("data_capture_time"), "-").toString();

                    paidAmount = "";
                    receiptNumber = "";
                    invoiceNumber = "";
                    patientNumber = patNo;
                    admissionWard = "";
                    admissionDate = "";
                    admissionType = "";
                    claimNumber = "";
                }

                java.sql.Statement stm12fdx1 = connectDB.createStatement();
                java.sql.ResultSet rse12fdx1 = stm12fdx1.executeQuery("SELECT  date, user_name , parameter FROM hp_patient_visit  where patient_no='" + patNo + "' order by 1 desc limit 1 ");

                while (rse12fdx1.next()) {
                    interviewerName = rse12fdx1.getString(2);
                    interviewDate = rse12fdx1.getString(1);;
                    servicePoint = rse12fdx1.getString(3);;
                    date = rse12fdx1.getDate(1);

                }

                java.sql.Statement stm12fdx1s = connectDB.createStatement();
                java.sql.ResultSet rse12fdx1s = stm12fdx1s.executeQuery("SELECT  appointment_date from pb_bookings  where patient_no='" + patNo + "' and clinic='" + specialtyClinic + "' ");

                while (rse12fdx1s.next()) {

                    if (rse12fdx1s.getDate(1).after(date)) {
                        date = rse12fdx1s.getDate(1);
                    }
                }

                java.sql.Statement stm12fdx = connectDB.createStatement();
                java.sql.ResultSet rse12fdx = stm12fdx.executeQuery("SELECT  accident_type, arrival_mode, police_officer_no, police_station, \n"
                        + "       date_time, driver_name, accident_vehicle_no, driver_license, \n"
                        + "       driver_id_no\n"
                        + "  FROM rta_info where patient_no='" + patNo + "' ");

                while (rse12fdx.next()) {

                    typeofAccident = rse12fdx.getString(1);
                    modeofArrival = rse12fdx.getString(2);
                    nameofPoliceOfficer = rse12fdx.getString(1);
                    policeForceNO = rse12fdx.getString(3);
                    policeStationAccident = rse12fdx.getString(4);
                    dateAccident = rse12fdx.getString(5);
                    nameofDriver = rse12fdx.getString(6);
                    accidentVehicleNo = rse12fdx.getString(7);
                    driverLicenceNo = rse12fdx.getString(8);
                    driversIDNo = rse12fdx.getString(9);
                }

            } else {

                java.sql.Statement stm12fdx = connectDB.createStatement();
                java.sql.ResultSet rse12fdx = stm12fdx.executeQuery("SELECT  year_of_birth, tel_no, sex,first_name,second_name || ' ' || last_name as names  FROM hp_inpatient_register where patient_no='" + patNo + "'");
                while (rse12fdx.next()) {
                    java.util.Date dob = (java.util.Date) (rse12fdx.getDate(1));
                    dobb = dob;
                    //java.util.Date dob=new java.util.Date(rse12fdx.getDate(1).getDate());
                    age = String.valueOf(com.afrisoftech.lib.PatientAge.getPatientActualAge(connectDB, dob));
                    name = rse12fdx.getString("first_name");
                    sNam = rse12fdx.getString("names");

                }

                java.sql.Statement stm12fd = connectDB.createStatement();
                java.sql.ResultSet rse12fd = stm12fd.executeQuery("SELECT  patient_name,  ward, nok, tel, address, id_no, mode_of_payment, reffered_from, nationality, residence, nhif, date_admitted, user_name, \n"
                        + "       diagnosis3, gender, marital_status, occupation,  pat_religion, pat_location,  sub_chief, nok_residence, nok_relationship,  nhif_status, \n"
                        + "       home_county, residence_county, education_level, c_sheet_no ,pat_district,nhif_status,speciality,chief_name, data_capture_time FROM hp_admission where patient_no='" + patNo + "'  order by date_admitted desc limit 1 ");
                while (rse12fd.next()) {

                    patgender = rse12fd.getString(15);
                    nationality = rse12fd.getString(9);
                    idPassport = rse12fd.getString(6);
                    placeBirth = rse12fd.getString(28);
                    occupation = rse12fd.getString(17);
                    maritalstatus = rse12fd.getString(16);
                    patientReligion = rse12fd.getString(18);
                    patientTelephone = rse12fd.getString(4);
                    patientEmail = "";
                    patientVillage = rse12fd.getString(28);
                    patientLocation = rse12fd.getString(10);
                    patientHomeCounty = rse12fd.getString(24);
                    patientResidenceCounty = rse12fd.getString(25);
                    patientNOK = rse12fd.getString(3);
                    patientRelation = rse12fd.getString(22);
                    patientNOKTelephone = rse12fd.getString(31);
                    patientReferFrom = "";
                    bookingDate = "";
                    specialtyClinic = "";
                    if (dbObject.getDBObject(rse12fd.getString(29), "-").startsWith("t")) {
                        statusNHIF = "YES";
                    } else {
                        statusNHIF = "NO";
                    }
                    NHIFNumber = "";
                    educationLevel = rse12fd.getString(26);
                    employerTelephone = "";
                    patientEmployer = "";
                    cSheetNo = rse12fd.getString(27);
                    unitNumber = rse12fd.getString(20);
                    date = rse12fd.getDate(12);
                    gender = rse12fd.getString(15);
                    interviewerName = rse12fd.getString(13);
                    interviewDate = rse12fd.getString(12);;
                    servicePoint = rse12fd.getString(14);;
                    registrationTime = rse12fd.getString("data_capture_time");

                    paidAmount = "";
                    receiptNumber = "";
                    invoiceNumber = "";
                    patientNumber = patNo;
                    admissionWard = rse12fd.getString(2);;
                    admissionDate = rse12fd.getString(12);
                    admissionType = rse12fd.getString(30);
                    claimNumber = "";
                }

            }

            generatePdf(sNam, name, age, patgender,
                    nationality, idPassport, placeBirth,
                    occupation, maritalstatus, patientReligion,
                    patientTelephone, patientEmail, patientVillage,
                    patientLocation, patientHomeCounty, patientResidenceCounty,
                    patientNOK, patientRelation, patientNOKTelephone,
                    patientReferFrom, bookingDate, specialtyClinic,
                    statusNHIF, NHIFNumber, educationLevel, employerTelephone,
                    patientEmployer, paidAmount, receiptNumber, cSheetNo,
                    invoiceNumber, interviewerName, interviewDate, servicePoint,
                    unitNumber, patientNumber, admissionWard, admissionDate,
                    admissionType, claimNumber, date, gender,
                    typeofAccident, modeofArrival, nameofPoliceOfficer, policeForceNO,
                    policeStationAccident, dateAccident, nameofDriver, accidentVehicleNo,
                    driverLicenceNo, driversIDNo, registrationTime, dobb, noOfChildre, patRess, chieff, subchieff, nokAddresss,
                    broughtBy, broughtByRel, broughtByAddress, broughtByTel, authorityy, nokIDD, assignedClinicc, purposes);

        } catch (java.sql.SQLException sql) {
            sql.printStackTrace();
            //javax.swing.JOptionPane.showMessageDialog(this, sql.getMessage(), "Error Message!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generatePdf(String sName, String patientName, String patientAge, String PatientGender,
            String patientNationality, String patientIDPassport, String placeofBirth,
            String patientOccupation, String maritalStatus, String patientReligion,
            String patientTelephone, String patientEmail, String patientVillage,
            String patientLocation, String patientHomeCounty, String patientResidenceCounty,
            String patientNOK, String patientRelation, String patientNOKTelephone,
            String patientReferFrom, String bookingDate, String specialtyClinic,
            String statusNHIF, String NHIFNumber, String educationLevel, String employerTelephone,
            String patientEmployer, String paidAmount, String receiptNumber, String cSheetNo,
            String invoiceNumber, String interviewerName, String interviewDate, String servicePoint,
            String unitNumber, String patientNumber, String admissionWard, String admissionDate,
            String admissionType, String claimNumber, java.util.Date date, String gender,
            String typeofAccident, String modeofArrival, String nameofPoliceOfficer, String policeForceNO,
            String policeStationAccident, String dateAccident, String nameofDriver, String accidentVehicleNo,
            String driverLicenceNo, String driversIDNo, String registrationTimes, java.util.Date dob,
            String noOfChildren, String patRes, String chief, String subchief, String nokAddress,
            String broughtByy, String broughtByRell, String broughtByAddresss, String broughtByTell,
            String authority, String nokID, String assignedClinic, String purpose) {

        java.util.Date date111 = date;
        java.lang.String formLabel = null;

        if (admissionWard.toCharArray().length < 1) {

            formLabel = "PATIENT REGISTRATION / ADMISSION FORM";

        } else {
            formLabel = "PATIENT REGISTRATION / ADMISSION FORM";
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

                    //docPdf.setFooter(footer);
                    docPdf.open();

                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    long dateNow = calendar.getTimeInMillis();

                    java.sql.Date datenowSql = new java.sql.Date(dateNow);

                    System.out.println(datenowSql.toString());

                    try {
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {28, 16, 16, 16, 16, 28};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((105));

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(3);

                        int headerwidths2[] = {30, 70, 30};

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

                        table2.getDefaultCell().setColspan(1);
                        Phrase phrase = new Phrase("IDENTIFICATION UNIT NUMBER " + patientNumber, pFontHeader2);
                        table2.addCell(phrase);
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
                                compName = dbObject.getDBObject(rset2.getObject(1), "");
                                District = dbObject.getDBObject(rset2.getObject(2), "");
                                Region = dbObject.getDBObject(rset2.getObject(3), "");
                            }
                            while (rset4.next()) {
                                date2 = dbObject.getDBObject(rset4.getObject(1), "");
                            }
                        } catch (java.sql.SQLException ex) {
                            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                            ex.printStackTrace();
                        }
                        table2.getDefaultCell().setFixedHeight(30);
                        table.getDefaultCell().setColspan(6);
                        table2.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().set
                        phrase = new Phrase(compName.toUpperCase(), pFontHeader2y);
                        table2.addCell(phrase);

                        table2.getDefaultCell().setColspan(1);
                        phrase = new Phrase("HOSPITAL/UNIT ", pFontHeader2);
                        table2.addCell(phrase);

                        table.addCell(table2); // painting the logo

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((105));

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(6);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase("MOH 327", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(formLabel, pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase(" ", pFontHeader2);
                        //table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase("", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("A. IDENTIFICATION PARTICULARS", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        //table2.getDefaultCell().setFixedHeight(70);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Surname", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Christian & Other Names", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Patient's Name", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(sName, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(patientName, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Date of Birth", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Age", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Sex", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("M. Status : " + dbObject.getDBObject(maritalStatus, ""), pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(dob), pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(dbObject.getDBObject((Object) patientAge, "") + "", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(dbObject.getDBObject(gender, ""), pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("No. of children : " + noOfChildren, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Religion : " + patientReligion + "        " + "Edu : " + educationLevel + "", pFontHeader2x);
                        table.addCell(phrase);

                        /**/
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);
                        /**/

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Occupation", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Address & Department - Place of Work", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Home address", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(patientOccupation, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(patientEmployer, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(patientVillage, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Tel No : " + employerTelephone, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Tel No : " + patientTelephone + "   ID No.:" + patientIDPassport, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        //-------------------------------------------------------------
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Residence/Village", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Location : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("" + patientLocation, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("District", pFontHeader2x);
                        table.addCell(phrase);

                        //--
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        //--
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(patRes, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Chief : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("" + chief, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("" + patientResidenceCounty, pFontHeader2x);
                        table.addCell(phrase);

                        //--
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("  ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        //--
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("  Plot No : .........", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Sub Chief : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("" + subchief, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Next of KIN : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(patientNOK, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Brought in By ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(broughtByy, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Relationship : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(patientRelation, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Relationship ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(broughtByRell, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Address : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(nokAddress, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Address ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(broughtByAddresss, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  ID No. : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(nokID, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("   ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Telephone : ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(patientNOKTelephone, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("  Telephone ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(broughtByTell, pFontHeader2x);
                        table.addCell(phrase);

                        //-----------------------
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(12);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase("", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("B. ADMISSION", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(12);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Present Admission", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.RIGHT);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Previous Admission", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Date", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Ward", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Authority", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Date", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Ward", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Authority", pFontHeader2x);
                        table.addCell(phrase);

                        //--------
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(assignedClinic, pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(authority, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(" ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(12);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Referred By :  ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(patientReferFrom, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Address :  ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("....................... ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(12);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("PURPOSE :  ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        phrase = new Phrase(purpose, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setBorder(Rectangle.RIGHT);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Interviewer's Name :  ", pFontHeader2x);
                        table.addCell(phrase);

                        try {

                            java.sql.Statement stmt1 = connectDB.createStatement();
                            java.sql.ResultSet rset1 = stmt1.executeQuery("select DISTINCT f_name||' '||l_name as name FROM secure_menu_access WHERE login_name = '" + interviewerName + "'");
                            while (rset1.next()) {
                                interviewerName = rset1.getObject(1).toString();

                            }

                        } catch (java.sql.SQLException sqe) {
                            sqe.printStackTrace();
                            //  System.out.println("Insert not successful");
                        }

                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase(interviewerName, pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Signature :  ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("...................", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        phrase = new Phrase(" ", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Date :  ", pFontHeader2x);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(interviewDate, pFontHeader2x);
                        table.addCell(phrase);

                        /*table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        // table.getDefaultCell().set
                        phrase = new Phrase("A. IDENTIFICATION PARTICULARS", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);*/
                        dbObject = new com.afrisoftech.lib.DBObject();
                        /*
                        phrase = new Phrase("Patient Name: " + patientName, pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Age: " + dbObject.getDBObject((Object) patientAge, "") + "", pFontHeader);
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
                        phrase = new Phrase("Registration Time : " + dbObject.getDBObject(registrationTimes, ""), pFontHeader);
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
                        phrase = new Phrase("Residence: " + dbObject.getDBObject(patientLocation, ""), pFontHeader);
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
                        phrase = new Phrase("4. CURRENT ATTENDANCE AT FACILITY", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Referred From: " + dbObject.getDBObject(patientReferFrom, ""), pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Appointment Date: " + dbObject.getDBObject(date111, ""), pFontHeadercolor);
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
                        phrase = new Phrase("Admission Date: " + dbObject.getDBObject(admissionDate, ""), pFontHeadercolor);
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
                        if (typeofAccident.length() > 0 || modeofArrival.length() > 0
                                || nameofPoliceOfficer.length() > 0 || policeForceNO.length() > 0
                                || policeStationAccident.length() > 0 || dateAccident.length() > 0
                                || nameofDriver.length() > 0 || accidentVehicleNo.length() > 0
                                || driverLicenceNo.length() > 0 || driversIDNo.length() > 0) {

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("RTA DETAILS : ", pFontHeader2);
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
                            phrase = new Phrase(dbObject.getDBObject("Police Station Accident Scene :- " + policeStationAccident, "") + ". "
                                    + dbObject.getDBObject("Accident Date:-" + dateAccident, ""), pFontHeader);
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

                        }*/

                        ///RTA END
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ", pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setBorder(PdfCell.RECTANGLE);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" Patient Number : ".toUpperCase() + dbObject.getDBObject(patientNumber, "").toUpperCase(), pFontHeader3);
                        table.addCell(phrase);
                        //   phrase = new Phrase(" ", pFontHeader);
                        //   table.addCell(phrase);
                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Unit Number : ".toUpperCase() + dbObject.getDBObject(unitNumber, "").toUpperCase(), pFontHeader2);

                        //table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);
                        //table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ", pFontHeader);

                        //table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        //PdfWriter writer = PdfWriter.getInstance(docPdf, new FileOutputStream(tempFile));
                        PdfContentByte cb = writer.getDirectContent();

                        Barcode128 code128 = new Barcode128();

                        code128.setCode(patientNumber.toUpperCase() + " " + sName + " " + patientName.toUpperCase());

                        code128.setBarHeight(16);

                        code128.setTextAlignment(Element.ALIGN_LEFT);

                        docPdf.add(table);

                        docPdf.add(code128.createImageWithBarcode(cb, null, null));

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

//    public void generatePdf(String string, String text, String selectedStatus, String toString, String text0, String toString0, String toString1, String selectedStatus0, String toString2, String text1, String _, String text2, String toString3, String toString4, String toString5, String text3, String toString6, String text4, String text5, String _0, String toString7, String nhifStatusTxt, String text6, String toString8, String text7, String text8, String _1, String _2, String text9, String _3, String interviewer, String serverTimeStamp, String toString9, String text10, String text11, String toString10, String serverTimeStamp0, String toString11, String text12) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
