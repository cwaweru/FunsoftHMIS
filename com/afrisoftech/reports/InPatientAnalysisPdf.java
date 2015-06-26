//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.reports;

import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class InPatientAnalysisPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    int numberSeq = 0;
    int iterations = 0;
    java.lang.String wardName = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    com.afrisoftech.timeseries.DailyAgeing dailySeries = null;
    java.util.Date ageingDates[][] = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void InPatientAnalysisPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String ward) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        wardName = ward;

        iterations = endDate.getDate() - beginDate.getDate();

        dbObject = new com.afrisoftech.lib.DBObject();

        System.out.println("Days Date" + endDate);
        // periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(1, endate);
        dailySeries = new com.afrisoftech.timeseries.DailyAgeing(iterations + 1, endate);

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String District = null;
                    String Region = null;

                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic","postgres","pilsiner");

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


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);

                        int headerwidths[] = {5, 10, 10, 10, 7, 7, 10, 12};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths1[] = {6, 30, 10, 10};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        table1.setHeaderRows(1);


                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

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

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(8);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("IN-PATIENT ANALYSIS FORM", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(8);

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("HOSPITAL : " + compName, pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("WARD : " + wardName, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("BED COMPLEMENT : " + compName, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("MONTH : " + monthString, pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("YEAR : " + yearString, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        try {
                            double totalMale = 0.00;
                            double totalFemale = 0.00;
                            double newMaleTotal = 0.00;
                            double newFemaleTotal = 0.00;
                            double oldMaleTotal = 0.00;
                            double oldFemaleTotal = 0.00;
                            java.lang.Object[] listofAct = this.getListofActivities();

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            int tAdmissions = 0;
                            int tDischarges = 0;
                            int tDeaths = 0;
                            int tTIn = 0;
                            int tTOut = 0;
                            int tCumTotal = 0;

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Date", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Admis.", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Disc.", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Died", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Transfer", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Bed State", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Cum Total", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("In", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Out", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);

                            // variables declalition
                            String pNo = "-";
                            String pName = "-";
                            String pAge = "-";
                            String pGender = "-";
                            String pStatus = "-";

                            int unDischarged = 0;
                            int cumTotal = 0;
                            float days = 0;
                            float patDays = 0;

                            float capacity = 0;
                            float adm = 0;
                            float disc = 0;
                            float deaths = 0;
                            float deathPerCent = 0;
                            float avBedDays = 0;
                            float occPerCent = 0;
                            float avDailyOcc = 0;
                            float avLstay = 0;
                            float tOverBed = 0;
                            float turnOverInt = 0;
                            for (int i = 0; i < listofAct.length; i++) {

                                table.getDefaultCell().setColspan(1);
                                int Admissions = 0;
                                int Discharges = 0;
                                int Deaths = 0;
                                int tIn = 1;
                                int tOut = 1;


                                java.sql.ResultSet rset = st.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE date_admitted::date = '" + listofAct[i] + "' AND ward = '" + wardName + "'");
                                while (rset.next()) {
                                    Admissions = rset.getInt(1);

                                }
                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE discharge_date::date = '" + listofAct[i] + "' AND ward = '" + wardName + "'  AND transaction_type  not ILIKE 'Die%'");
                                while (rset4.next()) {
                                    Discharges = rset4.getInt(1);
                                }

                                java.sql.ResultSet rset5 = st5.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE discharge_date::date = '" + listofAct[i] + "' AND ward = '" + wardName + "'  AND transaction_type  ILIKE 'Die%'");
                                while (rset5.next()) {
                                    Deaths = rset5.getInt(1);
                                }

                                java.sql.ResultSet rset6 = st6.executeQuery("SELECT distinct count(patient_no) FROM hp_ward_to_ward_transfer WHERE date::date = '" + listofAct[i] + "' AND transfered_to = '" + wardName + "'");
                                while (rset6.next()) {
                                    tIn = rset6.getInt(1);
                                }

                                java.sql.ResultSet rset7 = st7.executeQuery("SELECT distinct count(patient_no) FROM hp_ward_to_ward_transfer WHERE date::date = '" + listofAct[i] + "' AND transfered_from = '" + wardName + "'");
                                while (rset7.next()) {
                                    tOut = rset7.getInt(1);
                                }

                                java.sql.Statement st01 = connectDB.createStatement();
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st41 = connectDB.createStatement();
                                java.sql.Statement st51 = connectDB.createStatement();

                                java.sql.ResultSet rset01 = st01.executeQuery("SELECT distinct INITCAP(wd.ward_name),wd.design_capacity FROM hp_wards wd WHERE wd.ward_name = '" + wardName + "'");
                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE date_admitted BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + wardName + "'");
                               // java.sql.ResultSet rset21 = st21.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE discharge_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + wardName + "' AND transaction_type NOT ILIKE 'Die%'");
                               // java.sql.ResultSet rset31 = st31.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE discharge_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + wardName + "' AND transaction_type ILIKE 'Died'");
                                java.sql.ResultSet rset41 = st41.executeQuery("SELECT ('" + endDate + "'::date - '" + beginDate + "'::date)");
                                java.sql.ResultSet rset51 = st51.executeQuery("SELECT COUNT(patient_no),date_admitted FROM hp_admission WHERE date_admitted BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + wardName + "' GROUP BY date_admitted");

                                while (rset01.next()) {
                                    //  ward = rset01.getString(1);
                                    capacity = rset01.getFloat(2);
                                }

                                while (rset11.next()) {
                                    adm = rset11.getFloat(1);
                                }

                              /*  while (rset21.next()) {
                                    disc = rset21.getFloat(1);

                                }
                                
                                while (rset31.next()) {
                                    deaths = rset31.getFloat(1);
                                }
*/
                                while (rset41.next()) {
                                    days = rset41.getFloat(1);
                                }
                                if (deaths < 1) {
                                    deathPerCent = 0;
                                } else {
                                    deathPerCent = deaths / adm * 100;
                                }
                                avBedDays = days * capacity;

                                while (rset51.next()) {
                                    patDays = patDays + rset51.getFloat(1);
                                }
                                if (adm > 0) {
                                    patDays = patDays;
                                } else {
                                    patDays = 0;
                                }
                                occPerCent = patDays / avBedDays * 100;
                                avDailyOcc = patDays / days;
                                if (patDays < 1) {
                                    avLstay = 0;
                                } else {
                                    if (disc < 1 && deaths < 1) {
                                        avLstay = patDays / 1;
                                    } else {
                                        avLstay = patDays / (disc + deaths);
                                    }
                                }
                                tOverBed = (disc + deaths) / days;
                                if (patDays < 1) {
                                    if (disc < 1 && deaths < 1) {
                                        turnOverInt = (avBedDays) / 1;
                                    } else {

                                        turnOverInt = (avBedDays) / (disc + deaths);
                                    }
                                } else {
                                    if (disc < 1 && deaths < 1) {
                                        turnOverInt = (avBedDays - patDays) / 1;
                                    } else {
                                        turnOverInt = (avBedDays - patDays) / (disc + deaths);
                                    }
                                }

                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader1);
                                table.addCell(phrase);


                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(Admissions)), pFontHeader1);
                                table.addCell(phrase);
                                tAdmissions = tAdmissions + Admissions;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(Discharges)), pFontHeader1);
                                table.addCell(phrase);
                                tDischarges = tDischarges + Discharges;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(Deaths)), pFontHeader1);
                                table.addCell(phrase);
                                tDeaths = tDeaths + Deaths;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tIn)), pFontHeader1);
                                table.addCell(phrase);
                                tTIn = tTIn + tIn;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tOut)), pFontHeader1);
                                table.addCell(phrase);
                                tTOut = tTOut + tOut;



                                cumTotal = cumTotal + (Admissions + tIn) - (Discharges + Deaths + tOut);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(cumTotal)), pFontHeader1);
                                table.addCell(phrase);

                                tCumTotal = tCumTotal + cumTotal;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tCumTotal)), pFontHeader1);
                                table.addCell(phrase);
                            }


                            phrase = new Phrase("TOTAL", pFontHeader);
                            // newMaleTotal = newMaleTotal+newMale;
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tAdmissions)), pFontHeader);
                            // newMaleTotal = newMaleTotal+newMale;
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tDischarges)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tDeaths)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tTIn)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tTOut)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tCumTotal)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tCumTotal)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Available Bed Days  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(avBedDays)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Number of Patient Days  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tCumTotal)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Average Daily Occupancy  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tCumTotal / days)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("% Occupancy  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((tCumTotal / avBedDays) * 100)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Average Length of Stay  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            if(tDischarges + tDeaths < 1){
                                tDischarges = 1;
                            }else{
                                tDischarges = tDischarges;
                            }
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tCumTotal / (tDischarges + tDeaths))), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Discharge & Deaths  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tDischarges + tDeaths)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Turn Over Per Bed  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((tDischarges + tDeaths) / avBedDays)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Admissions  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(adm)), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Turn-over Interval  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((avBedDays - tCumTotal) / (tDischarges + tDeaths))), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);

                            docPdf.add(table);
                        // docPdf.add(table1);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    // }

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

    // java.util.Date.
    public java.lang.Object[] getListofActivities() {
        int interval = 0;
        java.lang.Object[][] rangeDates = dailySeries.getAgeingDateSeries();
        java.lang.Object[][] monthDates = ageingSeries.getAgeingDateSeries();

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        //for (int k = 0;  k < monthDates.length; k++){
        for (int k = monthDates.length - 1; k >= 0; k--) {

            for (int t = 0; t < rangeDates.length; t++) {

                listActVector.addElement(rangeDates[t][k]);

            }
        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;

    }
}





