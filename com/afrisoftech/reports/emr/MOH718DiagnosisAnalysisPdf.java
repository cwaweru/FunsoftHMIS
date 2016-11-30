//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MOH718DiagnosisAnalysisPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date endDate = null;
    int numberSeq = 0;
    java.lang.String ReportType = null;
    java.lang.String Categ = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MOH718DiagnosisAnalysisPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String reportType, java.lang.String categ) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        ReportType = reportType;

        Categ = categ;

        //    ReportType = repotype;

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

            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String District = null;
                    String Region = null;
                    String country = null;

                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch, country from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getString(1);
                            District = rset2.getString(2);
                            country = rset2.getString(3);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {
                        SqlExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(27);

                        int headerwidths[] = {5, 10, 40, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(6);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase("", pFontHeader);

                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            //java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(20);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("FACILITY NAME - " + country.toString().toUpperCase(), pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("MOH 718", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(20);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("INPATIENT MORBIDITY AND MORTALITY SUMMARY SHEET", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("DATE  : " + dateFormat.format(endDate1), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(13);
                            phrase = new Phrase("", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("MONTH  : " + monthString.toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("YEAR  : " + yearString.toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(27);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("District: " + District.toUpperCase() + " Facility Name: " + compName.toUpperCase() + " Quarter :________________ Year :__________ Sex: " + ReportType.toUpperCase() + " Report Type : " + Categ, pFontHeader3);
                            table.addCell(phrase);
                            phrase = new Phrase("", pFontHeader3);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("#", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("CODE", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("DISEASE", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("<1 YR", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("1-4", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("5-14", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("15-24", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("25-34", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("35-44", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("45-54", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("55-64", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("65+", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("U/Age", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("TOTALS", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("ALS", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("A", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("A", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase("D", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {
                            psExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        try {
                            int tmUnderOneA = 0;
                            int tmUnderOne = 0;
                            int tmOneFourA = 0;
                            int tmOneFour = 0;
                            int tmFiveFourteenA = 0;
                            int tmFiveFourteen = 0;
                            int tmFifteenTwentyFourA = 0;
                            int tmFifteenTwentyFour = 0;
                            int tmTwentyFiveThirtyFourA = 0;
                            int tmTwentyFiveThirtyFour = 0;
                            int tmThirtyFiveFourtyFourA = 0;
                            int tmThirtyFiveFourtyFour = 0;
                            int tmFourtyFiveFiftyFourA = 0;
                            int tmFourtyFiveFiftyFour = 0;
                            int tmFiftyFiveSixtyFourA = 0;
                            int tmFiftyFiveSixtyFour = 0;
                            int tmOverSixtyFiveA = 0;
                            int tmOverSixtyFive = 0;
                            int tmUnknownAgeA = 0;
                            int tmUnknownAge = 0;
                            int tmTotalAlive = 0;
                            int tmTotalDead = 0;

                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement stw1 = connectDB.createStatement();

                            // Male statement defination
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();

                            // Female statement definition
                            java.sql.Statement st10 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();

                            java.sql.Statement stA = connectDB.createStatement();
                            java.sql.Statement st1A = connectDB.createStatement();
                            java.sql.Statement st2A = connectDB.createStatement();
                            java.sql.Statement st3A = connectDB.createStatement();
                            java.sql.Statement st4A = connectDB.createStatement();
                            java.sql.Statement st5A = connectDB.createStatement();
                            java.sql.Statement st10A = connectDB.createStatement();
                            java.sql.Statement st11A = connectDB.createStatement();
                            java.sql.Statement st12A = connectDB.createStatement();
                            java.sql.Statement st13A = connectDB.createStatement();

                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            int noSeq = 0;
                            for (int i = 0; i < listofAct.length; i++) {
                                // variables declarition

                                int averageLengthofStay = 0;
                                int mUnderOneA = 0;
                                int mUnderOne = 0;
                                int mOneFourA = 0;
                                int mOneFour = 0;
                                int mFiveFourteenA = 0;
                                int mFiveFourteen = 0;
                                int mFifteenTwentyFourA = 0;
                                int mFifteenTwentyFour = 0;
                                int mTwentyFiveThirtyFourA = 0;
                                int mTwentyFiveThirtyFour = 0;
                                int mThirtyFiveFourtyFourA = 0;
                                int mThirtyFiveFourtyFour = 0;
                                int mFourtyFiveFiftyFourA = 0;
                                int mFourtyFiveFiftyFour = 0;
                                int mFiftyFiveSixtyFourA = 0;
                                int mFiftyFiveSixtyFour = 0;
                                int mOverSixtyFiveA = 0;
                                int mOverSixtyFive = 0;
                                int mUnknownAgeA = 0;
                                int mUnknownAge = 0;
                                int patNo1 = 0;
                                int totalAlive = 0;
                                int totalDead = 0;

                                if (ReportType.equalsIgnoreCase("Male")) {
                                    if (Categ.equalsIgnoreCase("IN-patient only")) {
                                        // Male IN-Patient Cases only
                                        //Live - Morbidity cases
                                        java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        //Dead - Mortality cases
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay
                                        java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND gender ilike 'male' AND pat_category ilike 'IP'");

                                        while (rsetw1.next()) {
                                            averageLengthofStay = rsetw1.getInt(1);
                                        }

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                        pstmtD.setObject(1, listofAct[i]);
                                        java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                        String code = "-";
                                        while (rsetD.next()) {
                                            code = rsetD.getString(1);

                                        }
                                        phrase = new Phrase(code, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rsetA.next()) {
                                            mUnderOneA = rsetA.getInt(1);
                                            totalAlive = totalAlive + mUnderOneA;
                                        }

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                            totalDead = totalDead + mUnderOne;
                                        }

                                        while (rset1A.next()) {
                                            mOneFourA = rset1A.getInt(1);
                                            totalAlive = totalAlive + mOneFourA;
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                            totalDead = totalDead + mOneFour;
                                        }

                                        while (rset2A.next()) {
                                            mFiveFourteenA = rset2A.getInt(1);
                                            totalAlive = totalAlive + mFiveFourteenA;
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                            totalDead = totalDead + mFiveFourteen;
                                        }

                                        while (rset3A.next()) {
                                            mFifteenTwentyFourA = rset3A.getInt(1);
                                            totalAlive = totalAlive + mFifteenTwentyFourA;
                                        }

                                        while (rset3.next()) {
                                            mFifteenTwentyFour = rset3.getInt(1);
                                            totalDead = totalDead + mFifteenTwentyFour;
                                        }

                                        while (rset4A.next()) {
                                            mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                            totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                        }

                                        while (rset4.next()) {
                                            mTwentyFiveThirtyFour = rset4.getInt(1);
                                            totalDead = totalDead + mTwentyFiveThirtyFour;
                                        }

                                        while (rset5A.next()) {
                                            mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                            totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                        }

                                        while (rset5.next()) {
                                            mThirtyFiveFourtyFour = rset5.getInt(1);
                                            totalDead = totalDead + mThirtyFiveFourtyFour;
                                        }

                                        while (rset10A.next()) {
                                            mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                            totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                        }

                                        while (rset10.next()) {
                                            mFourtyFiveFiftyFour = rset10.getInt(1);
                                            totalDead = totalDead + mFourtyFiveFiftyFour;
                                        }

                                        while (rset11A.next()) {
                                            mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                            totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                        }

                                        while (rset11.next()) {
                                            mFiftyFiveSixtyFour = rset11.getInt(1);
                                            totalDead = totalDead + mFiftyFiveSixtyFour;
                                        }

                                        while (rset12A.next()) {
                                            mOverSixtyFiveA = rset12A.getInt(1);
                                            totalAlive = totalAlive + mOverSixtyFiveA;
                                        }

                                        while (rset12.next()) {
                                            mOverSixtyFive = rset12.getInt(1);
                                            totalDead = totalDead + mOverSixtyFive;
                                        }

                                        while (rset13A.next()) {
                                            mUnknownAgeA = rset13A.getInt(1);
                                            totalAlive = totalAlive + mUnknownAgeA;
                                        }
                                        while (rset13.next()) {
                                            mUnknownAge = rset13.getInt(1);
                                            totalDead = totalDead + mUnknownAge;
                                        }
                                    } else if (Categ.equalsIgnoreCase("OUT-Patient only")) {
                                        //Male OUT-Patient Cases only
                                        //Live - Morbidity cases
                                        java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        //Dead - Mortality cases
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay
                                        java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND gender ilike 'male' AND pat_category ilike 'OP'");

                                        while (rsetw1.next()) {
                                            averageLengthofStay = rsetw1.getInt(1);
                                        }

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                        pstmtD.setObject(1, listofAct[i]);
                                        java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                        String code = "-";
                                        while (rsetD.next()) {
                                            code = rsetD.getString(1);

                                        }
                                        phrase = new Phrase(code, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rsetA.next()) {
                                            mUnderOneA = rsetA.getInt(1);
                                            totalAlive = totalAlive + mUnderOneA;
                                        }

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                            totalDead = totalDead + mUnderOne;
                                        }

                                        while (rset1A.next()) {
                                            mOneFourA = rset1A.getInt(1);
                                            totalAlive = totalAlive + mOneFourA;
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                            totalDead = totalDead + mOneFour;
                                        }

                                        while (rset2A.next()) {
                                            mFiveFourteenA = rset2A.getInt(1);
                                            totalAlive = totalAlive + mFiveFourteenA;
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                            totalDead = totalDead + mFiveFourteen;
                                        }

                                        while (rset3A.next()) {
                                            mFifteenTwentyFourA = rset3A.getInt(1);
                                            totalAlive = totalAlive + mFifteenTwentyFourA;
                                        }

                                        while (rset3.next()) {
                                            mFifteenTwentyFour = rset3.getInt(1);
                                            totalDead = totalDead + mFifteenTwentyFour;
                                        }

                                        while (rset4A.next()) {
                                            mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                            totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                        }

                                        while (rset4.next()) {
                                            mTwentyFiveThirtyFour = rset4.getInt(1);
                                            totalDead = totalDead + mTwentyFiveThirtyFour;
                                        }

                                        while (rset5A.next()) {
                                            mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                            totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                        }

                                        while (rset5.next()) {
                                            mThirtyFiveFourtyFour = rset5.getInt(1);
                                            totalDead = totalDead + mThirtyFiveFourtyFour;
                                        }

                                        while (rset10A.next()) {
                                            mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                            totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                        }

                                        while (rset10.next()) {
                                            mFourtyFiveFiftyFour = rset10.getInt(1);
                                            totalDead = totalDead + mFourtyFiveFiftyFour;
                                        }

                                        while (rset11A.next()) {
                                            mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                            totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                        }

                                        while (rset11.next()) {
                                            mFiftyFiveSixtyFour = rset11.getInt(1);
                                            totalDead = totalDead + mFiftyFiveSixtyFour;
                                        }

                                        while (rset12A.next()) {
                                            mOverSixtyFiveA = rset12A.getInt(1);
                                            totalAlive = totalAlive + mOverSixtyFiveA;
                                        }

                                        while (rset12.next()) {
                                            mOverSixtyFive = rset12.getInt(1);
                                            totalDead = totalDead + mOverSixtyFive;
                                        }

                                        while (rset13A.next()) {
                                            mUnknownAgeA = rset13A.getInt(1);
                                            totalAlive = totalAlive + mUnknownAgeA;
                                        }
                                        while (rset13.next()) {
                                            mUnknownAge = rset13.getInt(1);
                                            totalDead = totalDead + mUnknownAge;
                                        }
                                    } else {
                                        //Male cases both IN-Patient and OUT-Patient 
                                        //Live - Morbidity cases
                                        java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome NOT ILIKE 'DIED'");
                                        //Dead - Mortality cases
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'male' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay
                                        java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND gender ilike 'male'");

                                        while (rsetw1.next()) {
                                            averageLengthofStay = rsetw1.getInt(1);
                                        }

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                        pstmtD.setObject(1, listofAct[i]);
                                        java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                        String code = "-";
                                        while (rsetD.next()) {
                                            code = rsetD.getString(1);

                                        }
                                        phrase = new Phrase(code, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rsetA.next()) {
                                            mUnderOneA = rsetA.getInt(1);
                                            totalAlive = totalAlive + mUnderOneA;
                                        }

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                            totalDead = totalDead + mUnderOne;
                                        }

                                        while (rset1A.next()) {
                                            mOneFourA = rset1A.getInt(1);
                                            totalAlive = totalAlive + mOneFourA;
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                            totalDead = totalDead + mOneFour;
                                        }

                                        while (rset2A.next()) {
                                            mFiveFourteenA = rset2A.getInt(1);
                                            totalAlive = totalAlive + mFiveFourteenA;
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                            totalDead = totalDead + mFiveFourteen;
                                        }

                                        while (rset3A.next()) {
                                            mFifteenTwentyFourA = rset3A.getInt(1);
                                            totalAlive = totalAlive + mFifteenTwentyFourA;
                                        }

                                        while (rset3.next()) {
                                            mFifteenTwentyFour = rset3.getInt(1);
                                            totalDead = totalDead + mFifteenTwentyFour;
                                        }

                                        while (rset4A.next()) {
                                            mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                            totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                        }

                                        while (rset4.next()) {
                                            mTwentyFiveThirtyFour = rset4.getInt(1);
                                            totalDead = totalDead + mTwentyFiveThirtyFour;
                                        }

                                        while (rset5A.next()) {
                                            mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                            totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                        }

                                        while (rset5.next()) {
                                            mThirtyFiveFourtyFour = rset5.getInt(1);
                                            totalDead = totalDead + mThirtyFiveFourtyFour;
                                        }

                                        while (rset10A.next()) {
                                            mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                            totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                        }

                                        while (rset10.next()) {
                                            mFourtyFiveFiftyFour = rset10.getInt(1);
                                            totalDead = totalDead + mFourtyFiveFiftyFour;
                                        }

                                        while (rset11A.next()) {
                                            mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                            totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                        }

                                        while (rset11.next()) {
                                            mFiftyFiveSixtyFour = rset11.getInt(1);
                                            totalDead = totalDead + mFiftyFiveSixtyFour;
                                        }

                                        while (rset12A.next()) {
                                            mOverSixtyFiveA = rset12A.getInt(1);
                                            totalAlive = totalAlive + mOverSixtyFiveA;
                                        }

                                        while (rset12.next()) {
                                            mOverSixtyFive = rset12.getInt(1);
                                            totalDead = totalDead + mOverSixtyFive;
                                        }

                                        while (rset13A.next()) {
                                            mUnknownAgeA = rset13A.getInt(1);
                                            totalAlive = totalAlive + mUnknownAgeA;
                                        }
                                        while (rset13.next()) {
                                            mUnknownAge = rset13.getInt(1);
                                            totalDead = totalDead + mUnknownAge;
                                        }
                                    }
                                } else if (ReportType.equalsIgnoreCase("female")) {
                                    if (Categ.equalsIgnoreCase("OUT-Patient only")) {
                                        //Female OUT-Patient cases only
                                        //Live - Morbidity cases
                                        java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        //Dead - Mortality cases
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP'' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'OP' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay
                                        java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND gender ilike 'female' AND pat_category ilike 'OP'");

                                        while (rsetw1.next()) {
                                            averageLengthofStay = rsetw1.getInt(1);
                                        }

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                        pstmtD.setObject(1, listofAct[i]);
                                        java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                        String code = "-";
                                        while (rsetD.next()) {
                                            code = rsetD.getString(1);

                                        }
                                        phrase = new Phrase(code, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rsetA.next()) {
                                            mUnderOneA = rsetA.getInt(1);
                                            totalAlive = totalAlive + mUnderOneA;
                                        }

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                            totalDead = totalDead + mUnderOne;
                                        }

                                        while (rset1A.next()) {
                                            mOneFourA = rset1A.getInt(1);
                                            totalAlive = totalAlive + mOneFourA;
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                            totalDead = totalDead + mOneFour;
                                        }

                                        while (rset2A.next()) {
                                            mFiveFourteenA = rset2A.getInt(1);
                                            totalAlive = totalAlive + mFiveFourteenA;
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                            totalDead = totalDead + mFiveFourteen;
                                        }

                                        while (rset3A.next()) {
                                            mFifteenTwentyFourA = rset3A.getInt(1);
                                            totalAlive = totalAlive + mFifteenTwentyFourA;
                                        }

                                        while (rset3.next()) {
                                            mFifteenTwentyFour = rset3.getInt(1);
                                            totalDead = totalDead + mFifteenTwentyFour;
                                        }

                                        while (rset4A.next()) {
                                            mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                            totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                        }

                                        while (rset4.next()) {
                                            mTwentyFiveThirtyFour = rset4.getInt(1);
                                            totalDead = totalDead + mTwentyFiveThirtyFour;
                                        }

                                        while (rset5A.next()) {
                                            mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                            totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                        }

                                        while (rset5.next()) {
                                            mThirtyFiveFourtyFour = rset5.getInt(1);
                                            totalDead = totalDead + mThirtyFiveFourtyFour;
                                        }

                                        while (rset10A.next()) {
                                            mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                            totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                        }

                                        while (rset10.next()) {
                                            mFourtyFiveFiftyFour = rset10.getInt(1);
                                            totalDead = totalDead + mFourtyFiveFiftyFour;
                                        }

                                        while (rset11A.next()) {
                                            mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                            totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                        }

                                        while (rset11.next()) {
                                            mFiftyFiveSixtyFour = rset11.getInt(1);
                                            totalDead = totalDead + mFiftyFiveSixtyFour;
                                        }

                                        while (rset12A.next()) {
                                            mOverSixtyFiveA = rset12A.getInt(1);
                                            totalAlive = totalAlive + mOverSixtyFiveA;
                                        }

                                        while (rset12.next()) {
                                            mOverSixtyFive = rset12.getInt(1);
                                            totalDead = totalDead + mOverSixtyFive;
                                        }

                                        while (rset13A.next()) {
                                            mUnknownAgeA = rset13A.getInt(1);
                                            totalAlive = totalAlive + mUnknownAgeA;
                                        }
                                        while (rset13.next()) {
                                            mUnknownAge = rset13.getInt(1);
                                            totalDead = totalDead + mUnknownAge;
                                        }

                                    } else if (Categ.equalsIgnoreCase("IN_Patient only")) {
                                        //Female IN-Patient cases only
                                        //Live - Morbidity cases
                                        java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        //Dead - Mortality cases
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND pat_category ilike 'IP' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay
                                        java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND gender ilike 'female' AND pat_category ilike 'IP'");

                                        while (rsetw1.next()) {
                                            averageLengthofStay = rsetw1.getInt(1);
                                        }

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                        pstmtD.setObject(1, listofAct[i]);
                                        java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                        String code = "-";
                                        while (rsetD.next()) {
                                            code = rsetD.getString(1);

                                        }
                                        phrase = new Phrase(code, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rsetA.next()) {
                                            mUnderOneA = rsetA.getInt(1);
                                            totalAlive = totalAlive + mUnderOneA;
                                        }

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                            totalDead = totalDead + mUnderOne;
                                        }

                                        while (rset1A.next()) {
                                            mOneFourA = rset1A.getInt(1);
                                            totalAlive = totalAlive + mOneFourA;
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                            totalDead = totalDead + mOneFour;
                                        }

                                        while (rset2A.next()) {
                                            mFiveFourteenA = rset2A.getInt(1);
                                            totalAlive = totalAlive + mFiveFourteenA;
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                            totalDead = totalDead + mFiveFourteen;
                                        }

                                        while (rset3A.next()) {
                                            mFifteenTwentyFourA = rset3A.getInt(1);
                                            totalAlive = totalAlive + mFifteenTwentyFourA;
                                        }

                                        while (rset3.next()) {
                                            mFifteenTwentyFour = rset3.getInt(1);
                                            totalDead = totalDead + mFifteenTwentyFour;
                                        }

                                        while (rset4A.next()) {
                                            mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                            totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                        }

                                        while (rset4.next()) {
                                            mTwentyFiveThirtyFour = rset4.getInt(1);
                                            totalDead = totalDead + mTwentyFiveThirtyFour;
                                        }

                                        while (rset5A.next()) {
                                            mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                            totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                        }

                                        while (rset5.next()) {
                                            mThirtyFiveFourtyFour = rset5.getInt(1);
                                            totalDead = totalDead + mThirtyFiveFourtyFour;
                                        }

                                        while (rset10A.next()) {
                                            mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                            totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                        }

                                        while (rset10.next()) {
                                            mFourtyFiveFiftyFour = rset10.getInt(1);
                                            totalDead = totalDead + mFourtyFiveFiftyFour;
                                        }

                                        while (rset11A.next()) {
                                            mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                            totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                        }

                                        while (rset11.next()) {
                                            mFiftyFiveSixtyFour = rset11.getInt(1);
                                            totalDead = totalDead + mFiftyFiveSixtyFour;
                                        }

                                        while (rset12A.next()) {
                                            mOverSixtyFiveA = rset12A.getInt(1);
                                            totalAlive = totalAlive + mOverSixtyFiveA;
                                        }

                                        while (rset12.next()) {
                                            mOverSixtyFive = rset12.getInt(1);
                                            totalDead = totalDead + mOverSixtyFive;
                                        }

                                        while (rset13A.next()) {
                                            mUnknownAgeA = rset13A.getInt(1);
                                            totalAlive = totalAlive + mUnknownAgeA;
                                        }
                                        while (rset13.next()) {
                                            mUnknownAge = rset13.getInt(1);
                                            totalDead = totalDead + mUnknownAge;
                                        }
                                    } else {
                                        // Female OUT-Patient and IN-Patient cases
                                        //Live - Morbidity cases
                                        java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome NOT ILIKE 'DIED'");
                                        //Dead - Mortality cases
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND gender ilike 'female' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay
                                        java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND gender ilike 'female'");

                                        while (rsetw1.next()) {
                                            averageLengthofStay = rsetw1.getInt(1);
                                        }

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                        pstmtD.setObject(1, listofAct[i]);
                                        java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                        String code = "-";
                                        while (rsetD.next()) {
                                            code = rsetD.getString(1);

                                        }
                                        phrase = new Phrase(code, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rsetA.next()) {
                                            mUnderOneA = rsetA.getInt(1);
                                            totalAlive = totalAlive + mUnderOneA;
                                        }

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                            totalDead = totalDead + mUnderOne;
                                        }

                                        while (rset1A.next()) {
                                            mOneFourA = rset1A.getInt(1);
                                            totalAlive = totalAlive + mOneFourA;
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                            totalDead = totalDead + mOneFour;
                                        }

                                        while (rset2A.next()) {
                                            mFiveFourteenA = rset2A.getInt(1);
                                            totalAlive = totalAlive + mFiveFourteenA;
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                            totalDead = totalDead + mFiveFourteen;
                                        }

                                        while (rset3A.next()) {
                                            mFifteenTwentyFourA = rset3A.getInt(1);
                                            totalAlive = totalAlive + mFifteenTwentyFourA;
                                        }

                                        while (rset3.next()) {
                                            mFifteenTwentyFour = rset3.getInt(1);
                                            totalDead = totalDead + mFifteenTwentyFour;
                                        }

                                        while (rset4A.next()) {
                                            mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                            totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                        }

                                        while (rset4.next()) {
                                            mTwentyFiveThirtyFour = rset4.getInt(1);
                                            totalDead = totalDead + mTwentyFiveThirtyFour;
                                        }

                                        while (rset5A.next()) {
                                            mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                            totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                        }

                                        while (rset5.next()) {
                                            mThirtyFiveFourtyFour = rset5.getInt(1);
                                            totalDead = totalDead + mThirtyFiveFourtyFour;
                                        }

                                        while (rset10A.next()) {
                                            mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                            totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                        }

                                        while (rset10.next()) {
                                            mFourtyFiveFiftyFour = rset10.getInt(1);
                                            totalDead = totalDead + mFourtyFiveFiftyFour;
                                        }

                                        while (rset11A.next()) {
                                            mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                            totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                        }

                                        while (rset11.next()) {
                                            mFiftyFiveSixtyFour = rset11.getInt(1);
                                            totalDead = totalDead + mFiftyFiveSixtyFour;
                                        }

                                        while (rset12A.next()) {
                                            mOverSixtyFiveA = rset12A.getInt(1);
                                            totalAlive = totalAlive + mOverSixtyFiveA;
                                        }

                                        while (rset12.next()) {
                                            mOverSixtyFive = rset12.getInt(1);
                                            totalDead = totalDead + mOverSixtyFive;
                                        }

                                        while (rset13A.next()) {
                                            mUnknownAgeA = rset13A.getInt(1);
                                            totalAlive = totalAlive + mUnknownAgeA;
                                        }
                                        while (rset13.next()) {
                                            mUnknownAge = rset13.getInt(1);
                                            totalDead = totalDead + mUnknownAge;
                                        }
                                    }
                                } else {
                                    // Both Male and Female and Both IN-Patient and OUT-Patient cases
                                    //Live - Morbidity cases
                                    java.sql.ResultSet rsetA = stA.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset1A = st1A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset2A = st2A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset3A = st3A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset4A = st4A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset5A = st5A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset10A = st10A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset11A = st11A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset12A = st12A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset13A = st13A.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    //Dead - Mortality cases
                                    java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'   AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'    AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 64 AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age is null AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "'  AND admission_outcome ILIKE 'DIED'");

                                    // Average stay
                                    java.sql.ResultSet rsetw1 = stw1.executeQuery("SELECT sum(length_of_stay)/count(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  replace(disease, '''', '')  ilike '" + listofAct[i].toString().replaceAll("'", "") + "' ");

                                    while (rsetw1.next()) {
                                        averageLengthofStay = rsetw1.getInt(1);
                                    }

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);
                                    java.sql.PreparedStatement pstmtD = connectDB.prepareStatement("SELECT DISTINCT main_service FROM hp_patient_diagnosis WHERE initcap(disease) = initcap(?)");
                                    pstmtD.setObject(1, listofAct[i]);
                                    java.sql.ResultSet rsetD = pstmtD.executeQuery();
                                    String code = "-";
                                    while (rsetD.next()) {
                                        code = rsetD.getString(1);

                                    }
                                    phrase = new Phrase(code, pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    while (rsetA.next()) {
                                        mUnderOneA = rsetA.getInt(1);
                                        totalAlive = totalAlive + mUnderOneA;
                                    }

                                    while (rset.next()) {
                                        mUnderOne = rset.getInt(1);
                                        totalDead = totalDead + mUnderOne;
                                    }

                                    while (rset1A.next()) {
                                        mOneFourA = rset1A.getInt(1);
                                        totalAlive = totalAlive + mOneFourA;
                                    }

                                    while (rset1.next()) {
                                        mOneFour = rset1.getInt(1);
                                        totalDead = totalDead + mOneFour;
                                    }

                                    while (rset2A.next()) {
                                        mFiveFourteenA = rset2A.getInt(1);
                                        totalAlive = totalAlive + mFiveFourteenA;
                                    }

                                    while (rset2.next()) {
                                        mFiveFourteen = rset2.getInt(1);
                                        totalDead = totalDead + mFiveFourteen;
                                    }

                                    while (rset3A.next()) {
                                        mFifteenTwentyFourA = rset3A.getInt(1);
                                        totalAlive = totalAlive + mFifteenTwentyFourA;
                                    }

                                    while (rset3.next()) {
                                        mFifteenTwentyFour = rset3.getInt(1);
                                        totalDead = totalDead + mFifteenTwentyFour;
                                    }

                                    while (rset4A.next()) {
                                        mTwentyFiveThirtyFourA = rset4A.getInt(1);
                                        totalAlive = totalAlive + mTwentyFiveThirtyFourA;
                                    }

                                    while (rset4.next()) {
                                        mTwentyFiveThirtyFour = rset4.getInt(1);
                                        totalDead = totalDead + mTwentyFiveThirtyFour;
                                    }

                                    while (rset5A.next()) {
                                        mThirtyFiveFourtyFourA = rset5A.getInt(1);
                                        totalAlive = totalAlive + mThirtyFiveFourtyFourA;
                                    }

                                    while (rset5.next()) {
                                        mThirtyFiveFourtyFour = rset5.getInt(1);
                                        totalDead = totalDead + mThirtyFiveFourtyFour;
                                    }

                                    while (rset10A.next()) {
                                        mFourtyFiveFiftyFourA = rset10A.getInt(1);
                                        totalAlive = totalAlive + mFourtyFiveFiftyFourA;
                                    }

                                    while (rset10.next()) {
                                        mFourtyFiveFiftyFour = rset10.getInt(1);
                                        totalDead = totalDead + mFourtyFiveFiftyFour;
                                    }

                                    while (rset11A.next()) {
                                        mFiftyFiveSixtyFourA = rset11A.getInt(1);
                                        totalAlive = totalAlive + mFiftyFiveSixtyFourA;
                                    }

                                    while (rset11.next()) {
                                        mFiftyFiveSixtyFour = rset11.getInt(1);
                                        totalDead = totalDead + mFiftyFiveSixtyFour;
                                    }

                                    while (rset12A.next()) {
                                        mOverSixtyFiveA = rset12A.getInt(1);
                                        totalAlive = totalAlive + mOverSixtyFiveA;
                                    }

                                    while (rset12.next()) {
                                        mOverSixtyFive = rset12.getInt(1);
                                        totalDead = totalDead + mOverSixtyFive;
                                    }

                                    while (rset13A.next()) {
                                        mUnknownAgeA = rset13A.getInt(1);
                                        totalAlive = totalAlive + mUnknownAgeA;
                                    }
                                    while (rset13.next()) {
                                        mUnknownAge = rset13.getInt(1);
                                        totalDead = totalDead + mUnknownAge;
                                    }
                                }
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnderOneA)), pFontHeader1);
                                tmUnderOneA = tmUnderOneA + mUnderOneA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnderOne)), pFontHeader1);
                                tmUnderOne = tmUnderOne + mUnderOne;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOneFourA)), pFontHeader1);
                                tmOneFourA = tmOneFourA + mOneFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOneFour)), pFontHeader1);
                                tmOneFour = tmOneFour + mOneFour;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiveFourteenA)), pFontHeader1);
                                tmFiveFourteenA = tmFiveFourteenA + mFiveFourteenA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiveFourteen)), pFontHeader1);
                                tmFiveFourteen = tmFiveFourteen + mFiveFourteen;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFifteenTwentyFourA)), pFontHeader1);
                                tmFifteenTwentyFourA = tmFifteenTwentyFourA + mFifteenTwentyFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFifteenTwentyFour)), pFontHeader1);
                                tmFifteenTwentyFour = tmFifteenTwentyFour + mFifteenTwentyFour;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mTwentyFiveThirtyFourA)), pFontHeader1);
                                tmTwentyFiveThirtyFourA = tmTwentyFiveThirtyFourA + mTwentyFiveThirtyFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mTwentyFiveThirtyFour)), pFontHeader1);
                                tmTwentyFiveThirtyFour = tmTwentyFiveThirtyFour + mTwentyFiveThirtyFour;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mThirtyFiveFourtyFourA)), pFontHeader1);
                                tmThirtyFiveFourtyFourA = tmThirtyFiveFourtyFourA + mThirtyFiveFourtyFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mThirtyFiveFourtyFour)), pFontHeader1);
                                tmThirtyFiveFourtyFour = tmThirtyFiveFourtyFour + mThirtyFiveFourtyFour;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFourtyFiveFiftyFourA)), pFontHeader1);
                                tmFourtyFiveFiftyFourA = tmFourtyFiveFiftyFourA + mFourtyFiveFiftyFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFourtyFiveFiftyFour)), pFontHeader1);
                                tmFourtyFiveFiftyFour = tmFourtyFiveFiftyFour + mFourtyFiveFiftyFour;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiftyFiveSixtyFourA)), pFontHeader1);
                                tmFiftyFiveSixtyFourA = tmFiftyFiveSixtyFourA + mFiftyFiveSixtyFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiftyFiveSixtyFour)), pFontHeader1);
                                tmFiftyFiveSixtyFour = tmFiftyFiveSixtyFour + mFiftyFiveSixtyFour;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOverSixtyFiveA)), pFontHeader1);
                                tmOverSixtyFiveA = tmOverSixtyFiveA + mOverSixtyFiveA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOverSixtyFive)), pFontHeader1);
                                tmOverSixtyFive = tmOverSixtyFive + mOverSixtyFive;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnknownAgeA)), pFontHeader1);
                                tmUnknownAgeA = tmUnknownAgeA + mUnknownAgeA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnknownAge)), pFontHeader1);
                                tmUnknownAge = tmUnknownAge + mUnknownAge;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalAlive)), pFontHeader1);
                                tmTotalAlive = tmTotalAlive + totalAlive;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalDead)), pFontHeader1);
                                tmTotalDead = tmTotalDead + totalDead;
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(averageLengthofStay)), pFontHeader1);
                                tmUnknownAge = tmUnknownAge + mUnknownAge;
                                table.addCell(phrase);

                            }
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
                            table.getDefaultCell().setMinimumHeight(20);
                            phrase = new Phrase("Total for all ages", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnderOneA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnderOne)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOneFourA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOneFour)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiveFourteenA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiveFourteen)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFifteenTwentyFourA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFifteenTwentyFour)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmTwentyFiveThirtyFourA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmTwentyFiveThirtyFour)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmThirtyFiveFourtyFourA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmThirtyFiveFourtyFour)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFourtyFiveFiftyFourA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFourtyFiveFiftyFour)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiftyFiveSixtyFourA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiftyFiveSixtyFour)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOverSixtyFiveA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOverSixtyFive)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnknownAgeA)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnknownAge)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmTotalAlive)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmTotalDead)), pFontHeader);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(27);
                            phrase = new Phrase("Key: A-Alive D-Dead ALS-Average Length of Stay U/Age-Unknown Age", pFontHeader4);
                            table.addCell(phrase);
                            phrase = new Phrase("Compiled By (Name): ____________________________ Designation: ___________________ Sign: _________________ Date: _________________", pFontHeader);
                            table.addCell(phrase);
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

            if (ReportType.equalsIgnoreCase("Male")) {
                if (Categ.equalsIgnoreCase("Both")) {
                    java.sql.Statement stmt1 = connectDB.createStatement();

                    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender ILIKE 'male' GROUP BY disease ORDER BY 2 DESC");

                    while (rSet1.next()) {

                        listStaffNoVector.addElement(rSet1.getObject(1).toString());

                    }
                } else {
                    if (Categ.equalsIgnoreCase("IN-Patient only")) {
                        java.sql.Statement stmt1 = connectDB.createStatement();

                        java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender ILIKE 'male' AND pat_category ILIKE 'IP' GROUP BY disease ORDER BY 2 DESC");

                        while (rSet1.next()) {

                            listStaffNoVector.addElement(rSet1.getObject(1).toString());

                        }
                    } else {
                        if (Categ.equalsIgnoreCase("OUT-Patient only")) {
                            java.sql.Statement stmt1 = connectDB.createStatement();

                            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender ILIKE 'male' AND pat_category ILIKE 'OP' GROUP BY disease ORDER BY 2 DESC");

                            while (rSet1.next()) {

                                listStaffNoVector.addElement(rSet1.getObject(1).toString());

                            }
                        }
                    }
                }
            } else if (ReportType.equalsIgnoreCase("Female")) {
                if (Categ.equalsIgnoreCase("Both")) {
                    java.sql.Statement stmt1 = connectDB.createStatement();

                    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender ILIKE 'female' GROUP BY disease ORDER BY 2 DESC");

                    while (rSet1.next()) {

                        listStaffNoVector.addElement(rSet1.getObject(1).toString());

                    }
                } else {
                    if (Categ.equalsIgnoreCase("IN-Patient only")) {
                        java.sql.Statement stmt1 = connectDB.createStatement();

                        java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender ILIKE 'female' AND pat_category ILIKE 'IP' GROUP BY disease ORDER BY 2 DESC");

                        while (rSet1.next()) {

                            listStaffNoVector.addElement(rSet1.getObject(1).toString());

                        }
                    } else {
                        if (Categ.equalsIgnoreCase("OUT-Patient only")) {
                            java.sql.Statement stmt1 = connectDB.createStatement();

                            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender ILIKE 'female' AND pat_category ILIKE 'OP' GROUP BY disease ORDER BY 2 DESC");

                            while (rSet1.next()) {

                                listStaffNoVector.addElement(rSet1.getObject(1).toString());

                            }
                        }
                    }
                }
            } else {
                java.sql.Statement stmt1 = connectDB.createStatement();

                java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "' GROUP BY disease ORDER BY 2 DESC");

                while (rSet1.next()) {

                    listStaffNoVector.addElement(rSet1.getObject(1).toString());

                }

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
