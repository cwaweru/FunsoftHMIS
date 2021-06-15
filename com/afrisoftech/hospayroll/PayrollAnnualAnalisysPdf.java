//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospayroll;

import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class PayrollAnnualAnalisysPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;

    java.lang.String bank = null;

    java.util.Date beginDate = null;

    java.util.Date endDate = null;

    java.util.Date dateEnd = null;

    com.afrisoftech.lib.PeriodicDates periodicDates = null;

    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;

    java.util.Date ageingDates[][] = null;

    public static java.sql.Connection connectDB = null;

    public java.lang.String dbUserName = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    boolean threadCheck = true;

    Phrase phrase1;
    Phrase phrase2;
    java.lang.Thread threadSample;

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontNum1 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum11 = FontFactory.getFont(FontFactory.TIMES, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();

    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;

    public void PayrollAnnualAnalisysPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(12, endate);

        bank = combox;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {

        //		new GlTransactPdf().GlTransactPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf();

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(100);

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
        java.lang.Object[][] rangeDates = ageingSeries.getAgeingDateSeries();

        // ageingDates = ageingSeries.getAgeingDateSeries();
        double columnTotals[] = new double[rangeDates.length];

        java.sql.ResultSet rsetTotals1 = null;

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        int interval = 0;

        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //  com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {

                        java.sql.Statement st31 = connectDB.createStatement();
                        java.sql.Statement st41 = connectDB.createStatement();

                        java.sql.ResultSet rset21 = st31.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset41 = st41.executeQuery("SELECT date('now') as Date");
                        while (rset21.next()) {
                            compName = rset21.getObject(1).toString();
                        }
                        while (rset41.next()) {

                            date = rset41.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //         com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(2);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    // com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Payslip - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    // docPdf.setFooter(footer);
                    docPdf.open();

                    try {

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                        //   for (int j = 0; j < listofStaffNos.length; j++) {
                        double chargeable = 0;
                        double relief = 0;
                        double earnings = 0;
                        double TotalEarnings = 0;
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length + 2);

                        String headerWidths = null;

                        java.util.Vector headerVector = new java.util.Vector(1, 1);

                        int z = rangeDates.length;

                        int headerwidths[] = {15, 30, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((105));

                        table.setHeaderRows(1);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        System.out.println("Where am I ...");

                        table.getDefaultCell().setColspan(13);

                        Phrase phrase;

                        try {

                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);

                            table.getDefaultCell().setColspan(14);

                            phrase = new Phrase("Pay Period :   " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double taxCharged = 0;
                        double paye = 0;
                        System.out.println("Where am I ...");

                        try {

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st3A = connectDB.createStatement();
                            java.sql.Statement st4A = connectDB.createStatement();
                            java.sql.Statement st31 = connectDB.createStatement();
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st16 = connectDB.createStatement();
                            java.sql.Statement st17 = connectDB.createStatement();
                            java.sql.Statement st18 = connectDB.createStatement();
                            java.sql.Statement st19 = connectDB.createStatement();
                            java.sql.Statement stss = connectDB.createStatement();
                            System.out.println("Statements Created ...");

                            //  java.sql.ResultSet rset = st.executeQuery("select distinct employee_no,first_name||' '||middle_name||' '||last_name,department,pin_no from master_file where employee_no = '"+listofStaffNos[j]+"' order by employee_no");
                            //  java.sql.ResultSet rsetss = stss.executeQuery("select bank,bank_account_no,payment_mode from master_file where employee_no = '"+listofStaffNos[j]+"'");
                            /*    while (rset.next())
                             
                                    table.getDefaultCell().setColspan(3);
                             
                             
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             
                             
                                phrase = new Phrase("EMP No.            :  ".toUpperCase() +dbObject.getDBObject(rset.getObject(1).toString(),"-"),pFontHeader);
                             
                                table.addCell(phrase);
                             
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             
                                table.getDefaultCell().setColspan(5);
                             
                                phrase = new Phrase("Name               : ".toUpperCase() +dbObject.getDBObject(rset.getObject(2).toString().toUpperCase(),"-"),pFontHeader);
                             
                                table.addCell(phrase);
                             
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             
                             
                                phrase = new Phrase("Department       :  ".toUpperCase() +dbObject.getDBObject(rset.getObject(3).toString().toUpperCase(),"-"),pFontHeader);
                             
                                table.addCell(phrase);
                             
                             */
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Code".toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase("Description".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            for (int x = 0; x < rangeDates.length; x++) {

                                try {

                                    // Date parser
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "MMM/yy");

                                    java.lang.String monthString = dateFormatter.getDateString();

                                    int days = 1;
                                    if (x < 1) {

                                        com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "MMM/yy");

                                        java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();

                                        phrase = new Phrase(monthStringCurrent, pFontHeader);
                                    } else {
                                        phrase = new Phrase(monthString, pFontHeader);
                                        //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
                                        interval = x;
                                    }

                                    table.addCell(phrase);

                                    // Catch java.text.parse exception.
                                } catch (java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }

                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            java.lang.Object[] listofAct4 = this.getListofActivities4();

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("".toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(13);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Taxable Earnings".toUpperCase(), pFontHeader);

                            table.addCell(phrase);
                            for (int m = 0; m < listofAct4.length; m++) {
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(getCode(listofAct4[m].toString()), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct4[m].toString().toUpperCase(), pFontNum);
                                table.addCell(phrase);

                                for (int t = 0; t < rangeDates.length; t++) {
                                    java.sql.ResultSet rset1 = st31.executeQuery("select sum(amount) from posting where  description =  '" + listofAct4[m] + "' AND date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND allowance_deduction ILIKE 'Earning%' and company_name ilike '" + bank + "'");

                                    while (rset1.next()) {

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1.getString(1), "0.00")), pFontNum);
                                        table.addCell(phrase);
                                        earnings = rset1.getDouble(1);
                                        columnTotals[t] = columnTotals[t] + rset1.getDouble(1);
                                    }
                                    TotalEarnings = TotalEarnings + earnings;
                                }
                            }

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gross Earnings".toUpperCase(), pFontNum11);

                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset11 = st31.executeQuery("select sum(amount) from posting where date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND allowance_deduction ILIKE 'Earning%' and company_name ilike '" + bank + "'");

                                while (rset11.next()) {

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")), pFontNum11);
                                    table.addCell(phrase);
                                    earnings = rset11.getDouble(1);

                                }

                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setColspan(13);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Non Cash Benefits".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            java.lang.Object[] listofAct5 = this.getListofActivities5();

                            for (int n = 0; n < listofAct5.length; n++) {

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(getCode(listofAct5[n].toString()), pFontHeader);
                                table.addCell(phrase); 
                            
                            
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct5[n].toString().toUpperCase(), pFontNum);
                                table.addCell(phrase);

                                for (int s = 0; s < rangeDates.length; s++) {
                                    java.sql.ResultSet rset11 = st31.executeQuery("select sum(amount) from posting where description =  '" + listofAct5[n] + "' AND date BETWEEN '" + rangeDates[s][0] + "' AND '" + rangeDates[s][1] + "' and company_name ilike '" + bank + "'");

                                    while (rset11.next()) {

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        System.out.println("Values" + new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")));
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")), pFontNum);
                                        table.addCell(phrase);
                                        columnTotals[s] = columnTotals[s] + rset11.getDouble(1);
                                    }
                                }
                            }

                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Non Cash".toUpperCase(), pFontNum11);

                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset1A = st3A.executeQuery("select sum(amount) from posting where date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND allowance_deduction ILIKE 'NON CASH%' and company_name ilike '" + bank + "'");

                                while (rset1A.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1A.getString(1)), pFontNum11);
                                    table.addCell(phrase);

                                }

                            }
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Taxable Amt".toUpperCase(), pFontNum11);

                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                rsetTotals1 = st4.executeQuery("SELECT sum(pt.amount) from posting pt WHERE pt.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND (pt.allowance_deduction ILIKE 'Earning%' OR pt.allowance_deduction ILIKE 'non%') and company_name ilike '" + bank + "'");

                                while (rsetTotals1.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals1.getString(1)), pFontNum11);
                                    table.addCell(phrase);

                                }

                            }

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(13);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Less Relief".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            java.lang.Object[] listofAct6 = this.getListofActivities6();

                            for (int n = 0; n < listofAct6.length; n++) {
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(getCode(listofAct6[n].toString()), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct6[n].toString().toUpperCase(), pFontNum);
                                table.addCell(phrase);

                                for (int s = 0; s < rangeDates.length; s++) {
                                    java.sql.ResultSet rset11 = st31.executeQuery("select sum(amount) from posting where  description ilike  '" + listofAct6[n] + "' AND date BETWEEN '" + rangeDates[s][0] + "' AND '" + rangeDates[s][1] + "' and company_name ilike '" + bank + "'");

                                    while (rset11.next()) {

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        System.out.println("Values" + new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")));
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")), pFontNum);
                                        table.addCell(phrase);
                                        columnTotals[s] = columnTotals[s] + rset11.getDouble(1);
                                    }
                                }
                            }

                            
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("".toUpperCase(), pFontNum11);
                            table.addCell(phrase);

                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Non Taxable Amt".toUpperCase(), pFontNum11);

                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            for (int s = 0; s < rangeDates.length; s++) {
                                java.sql.ResultSet rset11 = st31.executeQuery("select sum(amount) from posting where  date BETWEEN '" + rangeDates[s][0] + "' AND '" + rangeDates[s][1] + "' AND allowance_deduction ILIKE 'Less Re%' and company_name ilike '" + bank + "'");

                                while (rset11.next()) {

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    System.out.println("Values" + new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")));
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")), pFontNum11);
                                    table.addCell(phrase);
                                    relief = rset11.getDouble(1);

                                }
                            }
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("".toUpperCase(), pFontNum11);
                            table.addCell(phrase);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net Taxable Amt".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {

                               

                                
                                rsetTotals1 = st4.executeQuery("SELECT sum(pt.amount) from posting pt,deductions_allowances da WHERE  pt.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND pt.description::text ~~* da.description::text AND da.taxable = true AND da.benefit = true and company_name ilike '" + bank + "'");

                                while (rsetTotals1.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    earnings = rsetTotals1.getDouble(1);
                                }
                                java.sql.ResultSet rset11 = st31.executeQuery("select sum(amount) from posting where date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND allowance_deduction ILIKE 'Less Re%' and company_name ilike '" + bank + "'");

                                while (rset11.next()) {

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    System.out.println("Values" + new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")));
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(1), "0.00")), pFontNum);

                                    relief = rset11.getDouble(1);

                                }
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(earnings - relief), "0.00")), pFontHeader);
                                table.addCell(phrase);
                            }

                            java.lang.Object[] listofAct7 = this.getListofActivities7();

                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontNum11);
                            table.addCell(phrase);
                                
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Tax Charged".toUpperCase(), pFontNum);
                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset14 = st14.executeQuery("select sum(tx.amount) from posting tx where  tx.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'Monthly Personal Relief%' OR tx.description ILIKE 'P.A.Y.E%') and company_name ilike '" + bank + "'");
                                while (rset14.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset14.getString(1)), pFontNum);
                                    table.addCell(phrase);

                                }

                            }

                            table.getDefaultCell().setColspan(1);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontNum11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Monthly Personal Relief".toUpperCase(), pFontNum);
                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset14 = st14.executeQuery("select sum(tx.amount) from posting tx where tx.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND tx.description ILIKE 'Monthly Personal Relief%' and company_name ilike '" + bank + "'");
                                while (rset14.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset14.getString(1)), pFontNum);
                                    table.addCell(phrase);

                                }

                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontNum11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Paye".toUpperCase(), pFontNum);
                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset14 = st14.executeQuery("select sum(tx.amount) from posting tx where tx.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'P.A.Y.E%') and company_name ilike '" + bank + "'");
                                while (rset14.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset14.getString(1)), pFontNum);
                                    table.addCell(phrase);

                                }

                            }

                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontNum11);
                            table.addCell(phrase);
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            table.getDefaultCell().setColspan(13);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Deductions".toUpperCase(), pFontHeader);

                            table.addCell(phrase);

                            for (int k = 0; k < listofAct.length; k++) {
                                
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(getCode(listofAct[k].toString()), pFontNum11);
                            table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct[k].toString().toUpperCase(), pFontNum);
                                table.addCell(phrase);

                                java.sql.Statement st171 = connectDB.createStatement();
                                java.sql.Statement st1711 = connectDB.createStatement();

                                for (int t = 0; t < rangeDates.length; t++) {

                                    java.sql.ResultSet rset17 = st17.executeQuery("select sum(av.amount) as amount from posting av where av.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and av.description ilike '" + listofAct[k] + "' and company_name ilike '" + bank + "'");

                                    while (rset17.next()) {

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset17.getString(1)), pFontNum);
                                        table.addCell(phrase);
                                    }
                                }
                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontNum11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL DEDUCTIONS", pFontNum11);
                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset18 = st18.executeQuery("select sum(dv.amount) from deduction_summary dv where dv.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'");
                                while (rset18.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset18.getString(1)), pFontNum11);
                                    table.addCell(phrase);

                                }

                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontNum11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("NET PAY", pFontHeader);
                            table.addCell(phrase);

                            for (int t = 0; t < rangeDates.length; t++) {
                                java.sql.ResultSet rset19 = st19.executeQuery("select sum(amount) from net_pay_view dv where dv.date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and company_name ilike '" + bank + "'");
                                while (rset19.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset19.getString(1)), pFontHeader);
                                    table.addCell(phrase);

                                }

                            }

                            docPdf.add(table);

                            System.out.println("Status of new page : " + docPdf.getPageNumber());

                            boolean boolNewPage = docPdf.newPage();
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        //   }
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

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and company_name ilike '" + bank + "' order by staff_no");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public String getCode(String name) {
        String code = "";
        try {
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT code FROM deductions_allowances  WHERE upper(description) =   '" + name.toUpperCase() + "' ");
            while (rSet1.next()) {
                code = rSet1.getString(1);
                System.out.println(rSet1.getObject(1).toString());
            }
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
        }
        return code;
    }

    public java.lang.Object[] getListofActivities4() {

        java.lang.Object[] listofActivities4 = null;

        java.util.Vector listActVector4 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description,(SELECT code from deductions_allowances where upper(posting.description)=upper(deductions_allowances.description) )  FROM posting where date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND allowance_deduction ilike 'earning%' and company_name ilike '" + bank + "' order by 2");

            while (rSet1.next()) {

                listActVector4.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities4 = listActVector4.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities4;
    }

    public java.lang.Object[] getListofActivities5() {

        java.lang.Object[] listofActivities5 = null;

        java.util.Vector listActVector5 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM posting where date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND allowance_deduction ilike 'non%' and company_name ilike '" + bank + "' order by description");

            while (rSet1.next()) {

                listActVector5.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities5 = listActVector5.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities5;
    }

    public java.lang.Object[] getListofActivities6() {

        java.lang.Object[] listofActivities6 = null;

        java.util.Vector listActVector6 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM posting where date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND allowance_deduction ilike 'less%' and company_name ilike '" + bank + "' order by description");

            while (rSet1.next()) {

                listActVector6.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities6 = listActVector6.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities6;
    }

    public java.lang.Object[] getListofActivities7() {

        java.lang.Object[] listofActivities7 = null;

        java.util.Vector listActVector7 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tx.description FROM posting tx where tx.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'Monthly Personal Relief%' OR tx.description ILIKE 'P.A.Y.E%') and company_name ilike '" + bank + "' order by tx.description");

            while (rSet1.next()) {

                listActVector7.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities7 = listActVector7.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities7;
    }

    public java.lang.Object[] getListofActivities8() {

        java.lang.Object[] listofActivities8 = null;

        java.util.Vector listActVector8 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tx.description FROM posting tx where tx.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  tx.description ILIKE 'Monthly Personal Relief%' and company_name ilike '" + bank + "' order by tx.description");

            while (rSet1.next()) {

                listActVector8.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities8 = listActVector8.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities8;
    }

    public java.lang.Object[] getListofActivities9() {

        java.lang.Object[] listofActivities9 = null;

        java.util.Vector listActVector9 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tx.description FROM posting tx where tx.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND  (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'P.A.Y.E%') and company_name ilike '" + bank + "' order by tx.description");

            while (rSet1.next()) {

                listActVector9.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities9 = listActVector9.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities9;
    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM deduction_summary where date BETWEEN '" + beginDate + "' AND '" + endDate + "' and company_name ilike '" + bank + "' order by description");

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

}
