//Author Francis Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.records.reports;

import com.afrisoftech.reports.*;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class DiagnosisPerAgePdf_1 implements java.lang.Runnable {

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
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void DiagnosisPerAgePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String categ, java.lang.String repotype) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        Categ = categ;

        ReportType = repotype;

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
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String District = null;
                    String Region = null;

                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    int dayT = 0;
                    int monT = 0;
                    int cashT = 0;
                    int schemeT = 0;
                    int selfT = 0;
                    int copT = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(15);

                        int headerwidths[] = {5, 40, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(5);

                        /* com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths1[] = {6,30,10,10};
                        
                        table1.setWidths(headerwidths1);
                        
                        table1.setWidthPercentage((100));
                        
                        table1.setHeaderRows(1);
                        
                        /*    com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths2[] = {30,10,10,10,10,10,10};
                        
                        table2.setWidths(headerwidths2);
                        
                        table2.setWidthPercentage((100));
                        
                        table2.setHeaderRows(1);*/
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

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
                            table.getDefaultCell().setColspan(13);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            if (ReportType.equalsIgnoreCase("Morbidity")) {
                                phrase = new Phrase("DIAGNOSIS BY AGE & GENDER - MORBIDITY", pFontHeader2);

                                table.addCell(phrase);
                            } else {
                                phrase = new Phrase("DIAGNOSIS BY AGE & GENDER - MORTALITY", pFontHeader2);

                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("DATE  : " + dateFormat.format(endDate1), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(13);
                            phrase = new Phrase("", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("MONTH  : " + monthString.toUpperCase(), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(13);
                            phrase = new Phrase(" ", pFontHeader2);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("YEAR  : " + yearString.toUpperCase(), pFontHeader1);
                            table.addCell(phrase);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(8);

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("MALE CASES", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("FEMALE CASES", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("RK", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("DISEASE", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("UNDER 1 YR.", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("1-4", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("5-14", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("15-44", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("45-60", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("61+", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("UNDER 1 YR.", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("1-4", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("5-14", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("15-44", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("45-60", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("61+", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("TOTAL", pFontHeader1);
                            table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            int tmUnderOne = 0;
                            int tmOneFour = 0;
                            int tmFiveFourteen = 0;
                            int tmFifteenFortyFour = 0;
                            int tmFortyFiveSixty = 0;
                            int tmOverSixtyOne = 0;

                            // female variables
                            int tfUnderOne = 0;
                            int tfOneFour = 0;
                            int tfFiveFourteen = 0;
                            int tfFifteenFortyFour = 0;
                            int tfFortyFiveSixty = 0;
                            int tfOverSixtyOne = 0;

                            int tMaleFemale = 0;
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            java.sql.Statement stw = connectDB.createStatement();

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
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            int noSeq = 0;
                            for (int i = 0; i < listofAct.length; i++) {
                                // variables declalition
                                // male variables
                                int mUnderOne = 0;
                                int mOneFour = 0;
                                int mFiveFourteen = 0;
                                int mFifteenFortyFour = 0;
                                int mFortyFiveSixty = 0;
                                int mOverSixtyOne = 0;

                                // female variables
                                int fUnderOne = 0;
                                int fOneFour = 0;
                                int fFiveFourteen = 0;
                                int fFifteenFortyFour = 0;
                                int fFortyFiveSixty = 0;
                                int fOverSixtyOne = 0;

                                int totalMaleFemale = 0;
                                String Gender = null;

                                int patNo1 = 0;


                                if (ReportType.equalsIgnoreCase("Morbidity")) {
                                    if (Categ.equalsIgnoreCase("Both")) {
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");

                                        // Female result sets.
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset14 = st14.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset15 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                        }

                                        while (rset3.next()) {
                                            mFifteenFortyFour = rset3.getInt(1);
                                        }

                                        while (rset4.next()) {
                                            mFortyFiveSixty = rset4.getInt(1);
                                        }

                                        while (rset5.next()) {
                                            mOverSixtyOne = rset5.getInt(1);
                                        }


                                        // female results.

                                        while (rset10.next()) {
                                            fUnderOne = rset10.getInt(1);
                                        }

                                        while (rset11.next()) {
                                            fOneFour = rset11.getInt(1);
                                        }

                                        while (rset12.next()) {
                                            fFiveFourteen = rset12.getInt(1);
                                        }

                                        while (rset13.next()) {
                                            fFifteenFortyFour = rset13.getInt(1);
                                        }

                                        while (rset14.next()) {
                                            fFortyFiveSixty = rset14.getInt(1);
                                        }

                                        while (rset15.next()) {
                                            fOverSixtyOne = rset15.getInt(1);
                                        }
                                    } else {
                                        if (Categ.equalsIgnoreCase("OP")) {
                                            java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");

                                            // Female result sets.
                                            java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset14 = st14.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset15 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            table.getDefaultCell().setColspan(1);
                                            noSeq = noSeq + 1;
                                            phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            while (rset.next()) {
                                                mUnderOne = rset.getInt(1);
                                            }

                                            while (rset1.next()) {
                                                mOneFour = rset1.getInt(1);
                                            }

                                            while (rset2.next()) {
                                                mFiveFourteen = rset2.getInt(1);
                                            }

                                            while (rset3.next()) {
                                                mFifteenFortyFour = rset3.getInt(1);
                                            }

                                            while (rset4.next()) {
                                                mFortyFiveSixty = rset4.getInt(1);
                                            }

                                            while (rset5.next()) {
                                                mOverSixtyOne = rset5.getInt(1);
                                            }


                                            // female results.

                                            while (rset10.next()) {
                                                fUnderOne = rset10.getInt(1);
                                            }

                                            while (rset11.next()) {
                                                fOneFour = rset11.getInt(1);
                                            }

                                            while (rset12.next()) {
                                                fFiveFourteen = rset12.getInt(1);
                                            }

                                            while (rset13.next()) {
                                                fFifteenFortyFour = rset13.getInt(1);
                                            }

                                            while (rset14.next()) {
                                                fFortyFiveSixty = rset14.getInt(1);
                                            }

                                            while (rset15.next()) {
                                                fOverSixtyOne = rset15.getInt(1);
                                            }
                                        } else {
                                            if (Categ.equalsIgnoreCase("IP")) {
                                                java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");

                                                // Female result sets.
                                                java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset14 = st14.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset15 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");

                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                                table.getDefaultCell().setColspan(1);
                                                noSeq = noSeq + 1;
                                                phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                                table.addCell(phrase);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                                while (rset.next()) {
                                                    mUnderOne = rset.getInt(1);
                                                }

                                                while (rset1.next()) {
                                                    mOneFour = rset1.getInt(1);
                                                }

                                                while (rset2.next()) {
                                                    mFiveFourteen = rset2.getInt(1);
                                                }

                                                while (rset3.next()) {
                                                    mFifteenFortyFour = rset3.getInt(1);
                                                }

                                                while (rset4.next()) {
                                                    mFortyFiveSixty = rset4.getInt(1);
                                                }

                                                while (rset5.next()) {
                                                    mOverSixtyOne = rset5.getInt(1);
                                                }


                                                // female results.

                                                while (rset10.next()) {
                                                    fUnderOne = rset10.getInt(1);
                                                }

                                                while (rset11.next()) {
                                                    fOneFour = rset11.getInt(1);
                                                }

                                                while (rset12.next()) {
                                                    fFiveFourteen = rset12.getInt(1);
                                                }

                                                while (rset13.next()) {
                                                    fFifteenFortyFour = rset13.getInt(1);
                                                }

                                                while (rset14.next()) {
                                                    fFortyFiveSixty = rset14.getInt(1);
                                                }

                                                while (rset15.next()) {
                                                    fOverSixtyOne = rset15.getInt(1);
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    
                                  if (Categ.equalsIgnoreCase("Both")) {
                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");
                                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male'");

                                        // Female result sets.
                                        java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset14 = st14.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");
                                        java.sql.ResultSet rset15 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female'");

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        while (rset.next()) {
                                            mUnderOne = rset.getInt(1);
                                        }

                                        while (rset1.next()) {
                                            mOneFour = rset1.getInt(1);
                                        }

                                        while (rset2.next()) {
                                            mFiveFourteen = rset2.getInt(1);
                                        }

                                        while (rset3.next()) {
                                            mFifteenFortyFour = rset3.getInt(1);
                                        }

                                        while (rset4.next()) {
                                            mFortyFiveSixty = rset4.getInt(1);
                                        }

                                        while (rset5.next()) {
                                            mOverSixtyOne = rset5.getInt(1);
                                        }


                                        // female results.

                                        while (rset10.next()) {
                                            fUnderOne = rset10.getInt(1);
                                        }

                                        while (rset11.next()) {
                                            fOneFour = rset11.getInt(1);
                                        }

                                        while (rset12.next()) {
                                            fFiveFourteen = rset12.getInt(1);
                                        }

                                        while (rset13.next()) {
                                            fFifteenFortyFour = rset13.getInt(1);
                                        }

                                        while (rset14.next()) {
                                            fFortyFiveSixty = rset14.getInt(1);
                                        }

                                        while (rset15.next()) {
                                            fOverSixtyOne = rset15.getInt(1);
                                        }
                                    } else {
                                        if (Categ.equalsIgnoreCase("OP")) {
                                            java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'OP'");

                                            // Female result sets.
                                            java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset14 = st14.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");
                                            java.sql.ResultSet rset15 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'OP'");

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            table.getDefaultCell().setColspan(1);
                                            noSeq = noSeq + 1;
                                            phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            while (rset.next()) {
                                                mUnderOne = rset.getInt(1);
                                            }

                                            while (rset1.next()) {
                                                mOneFour = rset1.getInt(1);
                                            }

                                            while (rset2.next()) {
                                                mFiveFourteen = rset2.getInt(1);
                                            }

                                            while (rset3.next()) {
                                                mFifteenFortyFour = rset3.getInt(1);
                                            }

                                            while (rset4.next()) {
                                                mFortyFiveSixty = rset4.getInt(1);
                                            }

                                            while (rset5.next()) {
                                                mOverSixtyOne = rset5.getInt(1);
                                            }


                                            // female results.

                                            while (rset10.next()) {
                                                fUnderOne = rset10.getInt(1);
                                            }

                                            while (rset11.next()) {
                                                fOneFour = rset11.getInt(1);
                                            }

                                            while (rset12.next()) {
                                                fFiveFourteen = rset12.getInt(1);
                                            }

                                            while (rset13.next()) {
                                                fFifteenFortyFour = rset13.getInt(1);
                                            }

                                            while (rset14.next()) {
                                                fFortyFiveSixty = rset14.getInt(1);
                                            }

                                            while (rset15.next()) {
                                                fOverSixtyOne = rset15.getInt(1);
                                            }
                                        } else {
                                            if (Categ.equalsIgnoreCase("IP")) {
                                                java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset5 = st5.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND pat_category ilike 'IP'");

                                                // Female result sets.
                                                java.sql.ResultSet rset10 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age < 1 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1 AND 4 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset13 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 44 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset14 = st14.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 44 AND 60 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");
                                                java.sql.ResultSet rset15 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_recorded::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 61 AND disease ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND pat_category ilike 'IP'");

                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                                table.getDefaultCell().setColspan(1);
                                                noSeq = noSeq + 1;
                                                phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                                table.addCell(phrase);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                                while (rset.next()) {
                                                    mUnderOne = rset.getInt(1);
                                                }

                                                while (rset1.next()) {
                                                    mOneFour = rset1.getInt(1);
                                                }

                                                while (rset2.next()) {
                                                    mFiveFourteen = rset2.getInt(1);
                                                }

                                                while (rset3.next()) {
                                                    mFifteenFortyFour = rset3.getInt(1);
                                                }

                                                while (rset4.next()) {
                                                    mFortyFiveSixty = rset4.getInt(1);
                                                }

                                                while (rset5.next()) {
                                                    mOverSixtyOne = rset5.getInt(1);
                                                }


                                                // female results.

                                                while (rset10.next()) {
                                                    fUnderOne = rset10.getInt(1);
                                                }

                                                while (rset11.next()) {
                                                    fOneFour = rset11.getInt(1);
                                                }

                                                while (rset12.next()) {
                                                    fFiveFourteen = rset12.getInt(1);
                                                }

                                                while (rset13.next()) {
                                                    fFifteenFortyFour = rset13.getInt(1);
                                                }

                                                while (rset14.next()) {
                                                    fFortyFiveSixty = rset14.getInt(1);
                                                }

                                                while (rset15.next()) {
                                                    fOverSixtyOne = rset15.getInt(1);
                                                }
                                            }
                                        }
                                    }  
                                    
                                }
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnderOne)), pFontHeader1);
                                tmUnderOne = tmUnderOne + mUnderOne;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOneFour)), pFontHeader1);
                                tmOneFour = tmOneFour + mOneFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiveFourteen)), pFontHeader1);
                                tmFiveFourteen = tmFiveFourteen + mFiveFourteen;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFifteenFortyFour)), pFontHeader1);
                                tmFifteenFortyFour = tmFifteenFortyFour + mFifteenFortyFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFortyFiveSixty)), pFontHeader1);
                                tmFortyFiveSixty = tmFortyFiveSixty + mFortyFiveSixty;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOverSixtyOne)), pFontHeader1);
                                tmOverSixtyOne = tmOverSixtyOne + mOverSixtyOne;
                                table.addCell(phrase);


                                // female
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fUnderOne)), pFontHeader1);
                                tfUnderOne = tfUnderOne + fUnderOne;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fOneFour)), pFontHeader1);
                                tfOneFour = tfOneFour + fOneFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFiveFourteen)), pFontHeader1);
                                tfFiveFourteen = tfFiveFourteen + fFiveFourteen;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFifteenFortyFour)), pFontHeader1);
                                tfFifteenFortyFour = tfFifteenFortyFour + fFifteenFortyFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFortyFiveSixty)), pFontHeader1);
                                tfFortyFiveSixty = tfFortyFiveSixty + fFortyFiveSixty;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fOverSixtyOne)), pFontHeader1);
                                tfOverSixtyOne = tfOverSixtyOne + fOverSixtyOne;
                                table.addCell(phrase);

                                totalMaleFemale = (mUnderOne + mOneFour + mFiveFourteen + mFifteenFortyFour + mFortyFiveSixty + mOverSixtyOne) + (fUnderOne + fOneFour + fFiveFourteen + fFifteenFortyFour + fFortyFiveSixty + fOverSixtyOne);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMaleFemale)), pFontHeader1);
                                tMaleFemale = tMaleFemale + totalMaleFemale;
                                table.addCell(phrase);
                            }
                            // }
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
                            table.getDefaultCell().setMinimumHeight(20);
                            phrase = new Phrase("All Ages", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnderOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOneFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiveFourteen)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFifteenFortyFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFortyFiveSixty)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOverSixtyOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfUnderOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOneFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFiveFourteen)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFifteenFortyFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFortyFiveSixty)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOverSixtyOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tMaleFemale)), pFontHeader1);
                            table.addCell(phrase);

                            docPdf.add(table);
                        //docPdf.add(table1);
                        //docPdf.add(table2);
                        } catch (java.sql.SQLException SqlExec) {

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

             

docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            if (ReportType.equalsIgnoreCase("Morbidity")) {
                if (Categ.equalsIgnoreCase("Both")) {
                    java.sql.Statement stmt1 = connectDB.createStatement();

                    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_recorded BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND admission_outcome NOT ILIKE 'Died' GROUP BY disease ORDER BY 2 DESC");

                    while (rSet1.next()) {

                        listStaffNoVector.addElement(rSet1.getObject(1).toString());

                    }
                } else {
                    if (Categ.equalsIgnoreCase("IP")) {
                        java.sql.Statement stmt1 = connectDB.createStatement();

                        java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_recorded BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND admission_outcome NOT ILIKE 'Died' AND pat_category ILIKE 'IP' GROUP BY disease ORDER BY 2 DESC");

                        while (rSet1.next()) {

                            listStaffNoVector.addElement(rSet1.getObject(1).toString());

                        }
                    } else {
                        if (Categ.equalsIgnoreCase("OP")) {
                            java.sql.Statement stmt1 = connectDB.createStatement();

                            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_recorded BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND admission_outcome NOT ILIKE 'Died' AND pat_category ILIKE 'OP' GROUP BY disease ORDER BY 2 DESC");

                            while (rSet1.next()) {

                                listStaffNoVector.addElement(rSet1.getObject(1).toString());

                            }
                        }
                    }
                }
            } else {
                if (Categ.equalsIgnoreCase("Both")) {
                    java.sql.Statement stmt1 = connectDB.createStatement();

                    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_recorded BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND admission_outcome ILIKE 'Died' GROUP BY disease ORDER BY 2 DESC");

                    while (rSet1.next()) {

                        listStaffNoVector.addElement(rSet1.getObject(1).toString());

                    }
                } else {
                    if (Categ.equalsIgnoreCase("IP")) {
                        java.sql.Statement stmt1 = connectDB.createStatement();

                        java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_recorded BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND admission_outcome ILIKE 'Died' AND pat_category ILIKE 'IP' GROUP BY disease ORDER BY 2 DESC");

                        while (rSet1.next()) {

                            listStaffNoVector.addElement(rSet1.getObject(1).toString());

                        }
                    } else {
                        if (Categ.equalsIgnoreCase("OP")) {
                            java.sql.Statement stmt1 = connectDB.createStatement();

                            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(disease),count(disease) FROM hp_patient_diagnosis where date_recorded BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND admission_outcome ILIKE 'Died' AND pat_category ILIKE 'OP' GROUP BY disease ORDER BY 2 DESC");

                            while (rSet1.next()) {

                                listStaffNoVector.addElement(rSet1.getObject(1).toString());

                            }
                        }
                    }
                }
            }
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}





