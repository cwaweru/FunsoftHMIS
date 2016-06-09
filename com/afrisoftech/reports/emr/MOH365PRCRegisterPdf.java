//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MOH365PRCRegisterPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date endDate = null;
    int numberSeq = 0;
    java.lang.String Categ = null;
    java.lang.String ReportType = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String Gender = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    com.lowagie.text.Font pFontHeader22 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MOH365PRCRegisterPdf(java.sql.Connection connDb, java.util.Date startDate, java.util.Date lastDate) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = startDate;

        endDate = lastDate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberListPdf().MemberListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf();

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

    public void generatePdf() {

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A1.rotate());

            try {

                try {
                    Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());

                    //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;

                    String District = null;

                    String Region = null;

                    String date = null;

                    try {
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("FACILITY NAME : " + compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        headerFoter.setRight(5);

                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("MOH - 365 : Post Rape Care Register : Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(62);

                        int headerwidths[] = {12, 12, 12, 12, 20, 35, 12, 12, 20, 12, 12, 30, 30, 40, 12, 40, 20, 20, 12, 25, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 30, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 40};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(4);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase("", pFontHeader);

                        try {

                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(62);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(50);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(-1);
                            phrase = new Phrase("MOH 365_PRC Register", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(20);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("POST RAPE CARE REGISTER", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(20);
                            phrase = new Phrase("HEALTH FACILITY : " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase("Serial No.", pFontHeader11);

                            table.addCell(phrase);
                            phrase = new Phrase("OUT Patient No.", pFontHeader11);

                            table.addCell(phrase);
                            phrase = new Phrase("Arrival Date", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Calculated Hours", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Name(s) (Three Names)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Survivor/Perpetrator (S/P)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Type of Case New/Repeat (N/R)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Sub-Location / Land Mark", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Telephone Number (Client/Guardian)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Age", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Sex Male/Female (M/F)", pFontHeader11);
                            table.addCell(phrase);


                            phrase = new Phrase("Marital Status (1-Single, 2-Married, 3-Divorced, 4-Separated, 5-Widowed)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Referred From (1-Health Facility, 2-Police, 3-School, 4-CHW, 5-Chief, 6-Others)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Disability (1-Hearing Impairement, 2-Visual Impairement, 3-Physical Impairement, 4-Mental, 5-Others, 6-Not Applicable)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("OVC (Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Type of Sexual Violence (1-Rape, 2-Attempted Rape, 3-Sexual Assault, 4-Defilement, 5-Attempted Defilement)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Date of Sexual Violence", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Time of Sexual Violence", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Date PRC Form Filled", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Date P3 form Filled (N/A if not filled)", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(10);
                            phrase = new Phrase("LAB Results", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Given 1st Dose of", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Trauma Counselling", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Adherence Counselling", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Referred to (1-Health Centre, 2-CD, 3-L, 4-P, 5-H, 6-S, 7-SG, 8-O, 9-N/A)", pFontHeader11);
                            PdfPCell cell2 = new PdfPCell(phrase);
                           // cell2.setRotation(180);
                            table.addCell(new PdfPCell(phrase));

                            phrase = new Phrase("Date of Next Appointment", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Actual Return Date", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("2nd Week Revisit (2 Weeks))", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date of Next Appointment", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Actual Return Date", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("3rd Visit (4 Weeks)", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date of Next Appointment", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Actual Return Date", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("4th Visit (6 Weeks)", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date of Next Appointment", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Actual Return Date", pFontHeader11);
                            table.addCell(phrase);

//                            phrase = new Phrase("HIV Test (P-Positive, N-Negative, KP-Known Positive, ND-Not Done)", pFontHeader11);
//                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("5th Visit (12 Weeks)", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Remarks/Comments", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("B", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("C", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("E", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("F", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("G", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("H", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("I", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("J", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("K", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("L", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("M", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("N", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("O", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("P", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Q", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("R", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("S", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("T", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("U", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("V", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("W", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("X", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Y", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Z", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AA", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AB", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AC", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AD", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AE", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AF", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AG", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AH", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AI", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AJ", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AK", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AL", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AM", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AN", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AO", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AP", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AQ", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AR", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AS", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AT", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AU", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AV", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AW", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AX", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AY", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AZ", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AAA", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AAB", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AAC", pFontHeader11);
                            table.addCell(phrase);
                                                        
                            phrase = new Phrase("AAD", pFontHeader11);
                            table.addCell(phrase);
                                                        
                            phrase = new Phrase("AAE", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AAF", pFontHeader11);
                            table.addCell(phrase);
                                                        
                            phrase = new Phrase("AAG", pFontHeader11);
                            table.addCell(phrase);
                                                                                    
                            phrase = new Phrase("AAH", pFontHeader11);
                            table.addCell(phrase);
                                                        
                            phrase = new Phrase("AAI", pFontHeader11);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AAJ", pFontHeader11);
                            table.addCell(phrase);
                                                        
                            phrase = new Phrase("AAK", pFontHeader11);
                            table.addCell(phrase);
                            
                        } catch (java.text.ParseException psExec) {

                            psExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        try {

                            int noSeq = 0;

                            java.lang.Object[] listofAct = this.getListofStaffNos();

                            //System.out.println("The array has been called...");

                            for (int i = 0; i < listofAct.length; i++) {

                                java.sql.PreparedStatement stw = connectDB.prepareStatement("SELECT DISTINCT "
                                        + " date_part('day', date) ||'-'||date_part('month', date) ||'-'||date_part('year', date), patient_no, lab_no, patient_name, age::int, initcap(gender),"
                                        + " (SELECT pat_location FROM hp_admission WHERE hp_lab_results.patient_no = hp_admission.patient_no UNION SELECT sub_location FROM "
                                        + " hp_patient_register WHERE hp_lab_results.patient_no = hp_patient_register.patient_no ORDER by 1 DESC LIMIT 1) as sub_location,"
                                        + " (SELECT initcap(residence) FROM hp_inpatient_register"
                                        + " WHERE hp_lab_results.patient_no = hp_inpatient_register.patient_no"
                                        + " UNION SELECT initcap(residence) FROM hp_patient_register WHERE "
                                        + " hp_lab_results.patient_no = hp_patient_register.patient_no ORDER BY 1 "
                                        + " DESC LIMIT 1) as village, (SELECT tel FROM hp_admission WHERE hp_admission.patient_no = hp_lab_results.patient_no UNION "
                                        + " SELECT tel_no FROM hp_patient_register WHERE hp_patient_register.patient_no = hp_lab_results.patient_no ORDER BY 1 DESC LIMIT 1) "
                                        + " as telephone, date_part('day', date::date) ||'-'||date_part('month', date::date) ||'-'||date_part('year', date::date), "
                                        + " date_part('day', spec_time::date) ||'-'||date_part('month', spec_time::date) ||'-'||date_part('year', spec_time::date), initcap(doctor), "
                                        + " (SELECT diagnosis FROM pb_doctors_request WHERE pb_doctors_request.patient_no = hp_lab_results.patient_no AND"
                                        + " pb_doctors_request.trans_date::date = hp_lab_results.date::date AND diagnosis is not null ORDER BY 1 DESC LIMIT 1) as diagnosis,(SELECT service "
                                        + "  FROM pb_doctors_request WHERE pb_doctors_request.patient_no"
                                        + " = hp_lab_results.patient_no AND hp_lab_results.date::date = pb_doctors_request.trans_date::date ORDER BY 1 DESC LIMIT 1) as investigations, input_date::date as analyse_date, '', type_of_specimen, "
                                        + " specimen_condition, initcap(pathologist) as analysing_officer, pathologist_comment, comments  FROM hp_maternity_register WHERE "
                                        + " date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' "
                                        + " AND patient_no = ?");

                                stw.setObject(1, listofAct[i]);

                                java.sql.ResultSet rsetw = stw.executeQuery();
/*
                                while (rsetw.next()) {

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    // Index
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);
                                    // Lab procedure date
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(1), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient Number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(2), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Lab Number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(3), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Revisit No
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(4), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Age
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(5), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Gender
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(6), ""), pFontHeader1);
                                    table.addCell(phrase);

                                    //Village
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(8), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Telephone number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(9), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Clinical diagnosis
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(13), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Prior treatment
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    // Type of specimen
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(17), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Condition of specimen
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(18), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Investigation required
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(14), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample taken
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(10), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample received
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(11), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Clinician/Doctor
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(12), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample analysed
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(15), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Results
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(20), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date result dispatched
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(15), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Amount charged
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    //Receipt number
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    //Referrals
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(16), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Comments
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(21), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Name of analysing officer/pathologist
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(19), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //signature
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(1), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient Number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(2), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Lab Number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(3), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Revisit No
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(4), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Age
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(5), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Gender
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(6), ""), pFontHeader1);
                                    table.addCell(phrase);

                                    //Village
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(8), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Telephone number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(9), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Clinical diagnosis
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(13), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Prior treatment
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    // Type of specimen
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(17), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Condition of specimen
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(18), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Investigation required
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(14), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample taken
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(10), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample received
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(11), ""), pFontHeader1);
                                    table.addCell(phrase);

                                }
                                */

                            }

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

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

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            java.sql.PreparedStatement stmt1 = connectDB.prepareStatement("SELECT DISTINCT patient_no, patient_name, date_admitted FROM hp_admission where date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ORDER BY date_admitted ASC");

            java.sql.ResultSet rSet1 = stmt1.executeQuery();

            while (rSet1.next()) {

                System.out.println("Patient names : [" + rSet1.getString(2) + "]");

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();

        System.out.println("Done list of Staff Nos ...");

        return listofStaffNos;

    }
}
