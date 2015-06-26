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

public class BedOccStatisticsPdf implements java.lang.Runnable {

    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.util.Date ageingDates[][] = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    int numberSeq = 0;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    com.afrisoftech.timeseries.DailyAgeing dailySeries = null;
    int iterations = 0;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void BedOccStatisticsPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

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

                java.lang.Thread.currentThread().sleep(2000);

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

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Ward Occupancy Statistics - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(14);

                        int headerwidths[] = {5, 30, 10, 10, 10, 10, 10, 12, 12, 10, 10, 10, 10, 12};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);
                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(14);


                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(10);

                            phrase = new Phrase("BED OCCUPANCY RATE : Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);

                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("No.", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Ward", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Bed Comp", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Adm.", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Disch.", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Deaths", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("% Deaths", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Avail Bed Days", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Pat Days", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("% Occup", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Av Daily Occp.", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Av L of Stay", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Turn Over Per Bed", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Turn Over Inter.", pFontHeader1);
                        table.addCell(phrase);


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            java.lang.Object[] listofAct1 = this.getListofActivities1();

                            double occ = 0.00;
                            float days = 0;


                            for (int i = 0; i < listofAct.length; i++) {

                                String ward = "-";
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
                                float patDays = 0;
                                int tTIn = 0;
                                int tTOut = 0;


                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.Statement st51 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                java.sql.Statement st7 = connectDB.createStatement();

                                java.sql.ResultSet rset = st1.executeQuery("SELECT distinct INITCAP(wd.ward_name),wd.design_capacity FROM hp_wards wd WHERE wd.ward_name = '" + listofAct[i] + "'");
                                //java.sql.ResultSet rset1 = st1.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE date_admitted BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + listofAct[i] + "'");
                                // java.sql.ResultSet rset2 = st2.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + listofAct[i] + "' AND transaction_type NOT ILIKE 'Die%'");
                                //java.sql.ResultSet rset3 = st3.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ward = '" + listofAct[i] + "' AND transaction_type ILIKE 'Died'");
                                java.sql.ResultSet rset4 = st2.executeQuery("SELECT ('" + endDate + "'::date - '" + beginDate + "'::date)");


                                while (rset4.next()) {
                                    days = rset4.getFloat(1);
                                    if (days > 0) {
                                        days = days;
                                    } else {
                                        days = 1;
                                    }
                                }

                                /*while (rset2.next()) {
                                disc = rset2.getFloat(1);
                                
                                }
                                while (rset3.next()) {
                                deaths = rset3.getFloat(1);
                                }*/
                                int bedState = 0;
                                for (int x = 0; x < listofAct1.length; x++) {
                                    float patDaily = 0;
                                    float admDisch = 0;
                                    float remPat = 0;
                                    int Admissions = 0;
                                    int Discharges = 0;
                                    int Deaths = 0;
                                    int tIn = 0;
                                    int tOut = 0;
                                    // java.sql.ResultSet rset5 = st5.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE date_admitted BETWEEN '" + beginDate + "' AND '" + listofAct1[x] + "' AND ward = '" + listofAct[i] + "'");
                                    // java.sql.ResultSet rset51 = st51.executeQuery("SELECT COUNT(patient_no) FROM hp_admission WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + listofAct1[x] + "' AND ward = '" + listofAct[i] + "'");
                                    java.sql.ResultSet rsetw = st.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE date_admitted::date = '" + listofAct1[x] + "' AND ward = '" + listofAct[i] + "'");
                                    while (rsetw.next()) {
                                        Admissions = rsetw.getInt(1);
                                        adm = adm + Admissions;

                                    }
                                    java.sql.ResultSet rset4f = st4.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE discharge_date::date = '" + listofAct1[x] + "' AND ward = '" + listofAct[i] + "'  AND transaction_type  not ILIKE 'Die%'");
                                    while (rset4f.next()) {
                                        Discharges = rset4f.getInt(1);
                                        disc = disc + Discharges;
                                    }

                                    java.sql.ResultSet rset5 = st5.executeQuery("SELECT distinct count(patient_no) FROM hp_admission WHERE discharge_date::date = '" + listofAct1[x] + "' AND ward = '" + listofAct[i] + "'  AND transaction_type  ILIKE 'Die%'");
                                    while (rset5.next()) {
                                        Deaths = rset5.getInt(1);
                                        deaths = deaths + Deaths;
                                    }

                                    java.sql.ResultSet rset6 = st6.executeQuery("SELECT distinct count(patient_no) FROM hp_ward_to_ward_transfer WHERE date::date = '" + listofAct1[x] + "' AND transfered_to = '" + listofAct[i] + "'");
                                    while (rset6.next()) {
                                        tIn = rset6.getInt(1);
                                        tTIn = tTIn + tIn;
                                    }

                                    java.sql.ResultSet rset7 = st7.executeQuery("SELECT distinct count(patient_no) FROM hp_ward_to_ward_transfer WHERE date::date = '" + listofAct1[x] + "' AND transfered_from = '" + listofAct[i] + "'");
                                    while (rset7.next()) {
                                        tOut = rset7.getInt(1);
                                        tTOut = tTOut + tOut;
                                    }

                                    bedState = bedState + ((Admissions + tIn) - (Discharges + Deaths + tOut));
                                    patDays = patDays + bedState;
                                }
                                while (rset.next()) {
                                    ward = rset.getString(1);
                                    capacity = rset.getFloat(2);
                                }



                                while (rset4.next()) {
                                    days = rset4.getFloat(1);
                                    if (days > 0) {
                                        days = days;
                                    } else {
                                        days = 1;
                                    }
                                }
                                if (deaths < 1) {
                                    deathPerCent = 0;
                                } else {
                                    deathPerCent = deaths / adm * 100;
                                }
                                avBedDays = days * capacity;


                                if (adm > 0) {
                                    patDays = patDays;
                                } else {
                                    patDays = 0;
                                }
                                if (avBedDays == 0) {
                                    occPerCent = patDays / 1 * 100;
                                } else {
                                    occPerCent = patDays / avBedDays * 100;
                                }
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

                                        turnOverInt = (disc + deaths) / (avBedDays);
                                    }
                                } else {
                                    if (disc < 1 && deaths < 1) {
                                        turnOverInt = (avBedDays - patDays) / 1;
                                    } else {
                                        turnOverInt = (avBedDays - patDays) / (disc + deaths);
                                    }
                                }
                                table.getDefaultCell().setColspan(1);
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(ward, pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(capacity)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(adm)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disc)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(deaths)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deathPerCent)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(avBedDays)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patDays)), pFontHeader1);
                                table.addCell(phrase);
                                if (occPerCent < 0) {
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0)), pFontHeader1);
                                    table.addCell(phrase);
                                } else {
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(occPerCent)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(avDailyOcc)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(avLstay)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tOverBed)), pFontHeader1);
                                table.addCell(phrase);
                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOverInt)),pFontHeader1);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOverInt)), pFontHeader1);
                                table.addCell(phrase);

                            }
                            //}

                            docPdf.add(table);

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

             

            /* try {
            
            if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
            
            System.out.println(tempFile);
            
            wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
            
            wait_for_Pdf2Show.waitFor();
            
            } else {*/docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
        //wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);

        //wait_for_Pdf2Show.waitFor();

        /*  }
        
        } catch(java.lang.InterruptedException intrExec) {
        
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
        
        }
         */


        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ward_name FROM hp_wards ORDER BY ward_name");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }

    public java.lang.Object[] getListofActivities1() {
        int interval = 0;
        java.lang.Object[][] rangeDates = dailySeries.getAgeingDateSeries();
        java.lang.Object[][] monthDates = ageingSeries.getAgeingDateSeries();

        java.lang.Object[] listofActivities1 = null;

        java.util.Vector listActVector1 = new java.util.Vector(1, 1);


        //for (int k = 0;  k < monthDates.length; k++){
        for (int k = monthDates.length - 1; k >= 0; k--) {

            for (int t = 0; t < rangeDates.length; t++) {

                listActVector1.addElement(rangeDates[t][k]);

            }
        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;

    }
}





