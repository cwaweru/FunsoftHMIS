//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import static com.afrisoftech.reports.ShiftSurrenderPdf.connectDB;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class SchemeStatementAccPdf implements java.lang.Runnable {

    java.lang.String memNo = null;
    java.lang.String memNo1 = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    com.lowagie.text.HeaderFooter headerFoter;
    String ks;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    java.util.Date rangeDates[][] = null;
    double osBalance = 0.00;
    double current = 0.00;
    double over30 = 0.00;
    double over60 = 0.00;
    double over90 = 0.00;
    double turnOver = 0.00;
    double columnTo = 0.00;
    double OS = 0.00;
    int over = 0;
    int name = 0;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void NHIFStatementAccPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox, java.lang.String combox1) {

        dbObject = new com.afrisoftech.lib.DBObject();

        memNo1 = combox1;

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        System.out.println("Days Date" + endDate);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(4, endate);
        //periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf(memNo);

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

    public void generatePdf(java.lang.String memNo) {
        rangeDates = ageingSeries.getAgeingDateSeries();

        double columnTotals[] = new double[rangeDates.length];
        //java.lang.Object[][] rangeDates = periodicDates.getMonthlyDates();

        //double columnTotals[] = new double[rangeDates.length];

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            double osBalance = 0.00;
            double current = 0.00;
            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String date = null;
                    String Messg = null;


                    docPdf.open();

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));


                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        try {

                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setFixedHeight(70);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table1.getDefaultCell().setFixedHeight(16);
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(5);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);
                            }

                            //  table.addCell(phrase);

                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            //         try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st2x = connectDB.createStatement();

                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            
                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                  
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //       java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(11);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {12, 12, 30, 10, 12, 12, 12, 12, 13, 13, 15};

                        table.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        table.setHeaderRows(8);
                        //  }
                        table.setWidthPercentage((100));


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(8);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st41 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset4 = st4.executeQuery("select scheme_name,account_no,tel_main,address from ac_schemes where account_no = '" + memNo1 + "' ORDER BY 1 ASC");
                            java.sql.ResultSet rset41 = st41.executeQuery("select payer_name from ac_schemes where account_no = '" + memNo1 + "' ORDER BY 1 ASC");
                            java.sql.ResultSet rset1 = st1.executeQuery("select date,admission_no, upper(item),invoice_no ||' '||receipt_no,journal_no,debit,credit, (select distinct f_name || ' '|| l_name from secure_menu_access where login_name = ac_debtors.user_name limit 1), reference_no from ac_debtors where account_no = '" + memNo1 + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and (balance > 0 or credit_bal > 0) order by date,invoice_no,reference_no");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                            java.sql.ResultSet rsetTotals2 = st22.executeQuery("select sum(balance-credit_bal) from ac_debtors where account_no = '" + memNo1 + "' AND date::date < '" + beginDate + "'");

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP | Rectangle.BOTTOM);

                            table.getDefaultCell().setColspan(11);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Statement of Account", pFontHeader11);
                            table.addCell(phrase);


                            while (rset4.next()) {
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(11);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset4.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);

                                while (rset41.next()) {
                                    table.getDefaultCell().setColspan(11);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset41.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(11);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Account No : " + dbObject.getDBObject(rset4.getObject(2), "-"), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject("Tel : " + rset4.getObject(3), "-"), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rset4.getObject(4), "-"), pFontHeader1);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(5);

                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);


                                phrase = new Phrase("Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader1);

                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }
                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //phrase = new Phrase("Period : '"+beginDate+"' - '"+endDate+"'", pFontHeader1);
                            //table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date " + datenowSql, pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Patient No.", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Name", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Invoice No.", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Member No.", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Claim No..", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Ward Name", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Prepared By", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Debit", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Credit ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Running Amt. " + ks, pFontHeader1);
                            table.addCell(phrase);
                            //table
                            double osBalancex = 0.00;
                            double osBalancex1 = 0.00;

                            while (rsetTotals2.next()) {
                                table.getDefaultCell().setColspan(5);

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("BAL/BF", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals2.getString(1)), pFontHeader1);
                                osBalance = osBalance + rsetTotals2.getDouble(1);
                                table.addCell(phrase);
                            }
                            while (rset1.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(5), "-"), pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(9), "-"), pFontHeader);

                                table.addCell(phrase);

                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT distinct main_service from hp_patient_card where patient_no = ? AND service ilike '%daily bed%' order by main_service desc limit 1");
                                pstmt.setString(1, rset1.getString(2));
                                java.sql.ResultSet rsets = pstmt.executeQuery();
                                String wardName = "-";
                               // System.out.println("Printing visit_id number ");
                                while (rsets.next()) {
                                  
                                    System.out.println("Ward name = ["+rsets.getString(1)+"]");
                                    wardName = rsets.getString(1);
                                    
                                }
                                rsets.close();
                                pstmt.close();
                                phrase = new Phrase(wardName, pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(8), "-").toUpperCase(), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)), pFontHeader);

                                table.addCell(phrase);
                                osBalancex = osBalancex + rset1.getDouble(6);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)), pFontHeader);
                                osBalancex1 = osBalancex1 + rset1.getDouble(7);
                                osBalance = osBalance + rset1.getDouble(6)-rset1.getDouble(7);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);

                                table.addCell(phrase);

                            }
                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                            //  while (rsetTotals.next()) {

                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancex)), pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancex1)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("     ''  ", pFontHeader);

                            table.addCell(phrase);


                            docPdf.add(table);

                            double Totals = 0.00;


                            try {

                                com.lowagie.text.pdf.PdfPTable table12 = new com.lowagie.text.pdf.PdfPTable(rangeDates.length + 4);

                                String headerWidths = null;

                                java.util.Vector headerVector = new java.util.Vector(1, 1);

                                int z = rangeDates.length;


                                int headerwidth[] = {22, 13, 13, 13, 13, 13, 13, 13};//,13,13};


                                table12.setWidths(headerwidth);

                                table12.setWidthPercentage((100));

                                table12.setHeaderRows(2);

                                table12.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                //
                                table.getDefaultCell().setColspan(8);
                                Phrase phraseA = new Phrase("");


                                table12.getDefaultCell().setColspan(1);

                                phraseA = new Phrase("AGEING DETAILS", pFontHeader1);
                                table12.addCell(phraseA);

                                interval = 0;
                                for (int x = rangeDates.length - 1; x >= 0; x--) {
                                    table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    try {

                                        // Date parser

                                        table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                        com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "MMM/yy");

                                        java.lang.String monthString = dateFormatter.getDateString();

                                        int days = 1;

                                        phraseA = new Phrase(monthString, pFontHeader1);
                                        table12.addCell(phraseA);


                                    } catch (java.text.ParseException prs) {
                                        prs.printStackTrace();
                                    }

                                    interval++;
                                }
                                phraseA = new Phrase("+ " + (interval * 30) + " Days", pFontHeader1);
                                table12.addCell(phraseA);

                                phraseA = new Phrase("Un Alloc.", pFontHeader1);
                                table12.addCell(phraseA);

                                phraseA = new Phrase("O/S " + ks, pFontHeader1);
                                table12.addCell(phraseA);

                                table12.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table12.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                                try {

                                    double GrandTotal = 0.00;
                                    double Over120Total = 0.00;
                                    java.lang.Object[] listofAct = this.getListofActivities();

                                    //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                                    System.out.println(listofAct.length);

                                    //  for (int i = 0; i < listofAct.length; i++) {
                                    double TurnOver = 0.00;
                                    double Over120 = 0.00;
                                    double TotalCount = 0.00;



                                    table12.getDefaultCell().setColspan(1);
                                    java.sql.Statement stmta1 = connectDB.createStatement();
                                    java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(distinct(account_no)) FROM ac_debtors where account_no IS NOT NULL and account_no ='" + memNo1 + "'");
                                    java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select distinct account_no||' '||scheme_name from ac_schemes WHERE account_no = '" + memNo1 + "' ");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                    //   pset22.setString(1,""+memNo1.toUpperCase());
                                    java.sql.ResultSet rSeta1 = pSeta1.executeQuery();
                                    while (rSeta1.next()) {
                                        name = rSeta1.getInt(1);

                                    }
                                    if (name > 0) {
                                        java.sql.ResultSet rset22 = pset22.executeQuery();

                                        while (rset22.next()) {

                                            table12.getDefaultCell().setColspan(1);
                                            //     phraseA = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"),pFontHeader1);
                                            phraseA = new Phrase(dbObject.getDBObject(" ", "-"), pFontHeader1);

                                            table12.addCell(phraseA);
                                            // columnTotals[t] = columnTotals[t] + rset.getDouble(1);


                                        }
                                    } else {
                                        phraseA = new Phrase("-", pFontHeader1);
                                        table12.addCell(phraseA);
                                    }
                                    // java.sql.Statement st2 = connectDB.createStatement();

                                    table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);



                                    java.sql.Statement st2B = connectDB.createStatement();
                                    java.sql.Statement st21 = connectDB.createStatement();
                                    java.sql.Statement st22A = connectDB.createStatement();
                                    java.sql.Statement st23 = connectDB.createStatement();
                                    java.sql.Statement st211 = connectDB.createStatement();
                                    java.sql.Statement st221 = connectDB.createStatement();
                                    java.sql.Statement st233 = connectDB.createStatement();
                                    java.sql.Statement st2A = connectDB.createStatement();
                                    java.sql.Statement st2B1 = connectDB.createStatement();
                                    java.sql.Statement st2C = connectDB.createStatement();
                                    java.sql.Statement stc = connectDB.createStatement();
                                    System.out.println("Dealer Is : '" + memNo1 + "'");
                                    java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE account_no = '" + memNo1 + "' AND dealer IS NOT NULL AND date < '" + rangeDates[0][0] + "'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                    // pset1.setString(1,""+memNo1);
                                    java.sql.ResultSet rset1A = pset1.executeQuery();

                                    java.sql.PreparedStatement pset112 = connectDB.prepareStatement("select sum(credit_bal) from ac_debtors WHERE account_no = ? AND date <= '" + endDate + "'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                    pset112.setString(1, memNo1);
                                    java.sql.ResultSet rset112 = pset112.executeQuery();

                                    for (int t = rangeDates.length - 1; t >= 0; t--) {
                                        java.sql.Statement st81 = connectDB.createStatement();
                                        java.sql.Statement st01 = connectDB.createStatement();


                                        java.sql.ResultSet rset01 = st01.executeQuery("select count(balance) from ac_debtors WHERE account_no = '" + memNo1 + "' AND dealer IS NOT NULL AND date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'");

                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(debit - credit) from ac_debtors WHERE account_no = '" + memNo1 + "' AND dealer IS NOT NULL AND date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                        while (rset01.next()) {
                                            over = rset01.getInt(1);
                                        }
                                        if (t == 3) {
                                            java.sql.PreparedStatement pset1x = connectDB.prepareStatement("select sum(debit - credit) from ac_debtors WHERE account_no = '" + memNo1 + "' AND dealer IS NOT NULL AND date between '" + rangeDates[t][0] + "' AND '" + endDate + "'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                            System.out.print(rangeDates[t][0] + " AND " + endDate);
                                            if (over > 0) {
                                                //  pset.setString(1,""+memNo1.toString().toUpperCase());
                                                java.sql.ResultSet rset = pset1x.executeQuery();
                                                while (rset.next()) {

                                                    table12.getDefaultCell().setColspan(1);
                                                    table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                    phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)), pFontHeader1);
                                                    table12.addCell(phraseA);
                                                    columnTotals[t] = columnTotals[t] + rset.getDouble(1);

                                                    TotalCount = TotalCount + rset.getDouble(1);
                                                }


                                            } else {

                                                table12.getDefaultCell().setColspan(1);
                                                table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table12.addCell(phraseA);

                                            }
                                        } else {
                                            if (over > 0) {
                                                //  pset.setString(1,""+memNo1.toString().toUpperCase());
                                                java.sql.ResultSet rset = pset.executeQuery();
                                                while (rset.next()) {

                                                    table12.getDefaultCell().setColspan(1);
                                                    table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                    phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)), pFontHeader1);
                                                    table12.addCell(phraseA);
                                                    columnTotals[t] = columnTotals[t] + rset.getDouble(1);

                                                    TotalCount = TotalCount + rset.getDouble(1);
                                                }


                                            } else {

                                                table12.getDefaultCell().setColspan(1);
                                                table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table12.addCell(phraseA);

                                            }
                                        }
                                    }


                                    //GrandTotal = GrandTotal + TotalCount;
                                    while (rset1A.next()) {
                                        System.out.println("This Result " + rset1A.getString(1));
                                        table12.getDefaultCell().setColspan(1);
                                        table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1A.getString(1)), pFontHeader1);
                                        Over120 = rset1A.getDouble(1);
                                        table12.addCell(phraseA);
                                        Over120Total = Over120Total + rset1A.getDouble(1);
                                        TurnOver = TotalCount + Over120;

                                    }

                                    while (rset112.next()) {
                                        phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset112.getString(1)), pFontHeader1);
                                        table12.addCell(phraseA);
                                        OS = OS + rset112.getDouble(1);
                                    }
                                    // Over120 = Over120+Over120;
                                    phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TurnOver - OS)), pFontHeader1);
                                    table12.addCell(phraseA);
                                    Totals = Totals + TurnOver;



                                    //}

                                    table12.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    table12.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                    // while (rsetTotals.next()) {

                                    table12.getDefaultCell().setColspan(1);

                                    table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phraseA = new Phrase("Total", pFontHeader);

                                    table12.addCell(phraseA);

                                    table12.getDefaultCell().setColspan(1);

                                    table12.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                                    for (int x = rangeDates.length - 1; x >= 0; x--) {
                                        // phrase = new Phrase("Current"+2*x,pFontHeader);
                                        // table.addCell(phrase);


                                        phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[x])), pFontHeader);

                                        table12.addCell(phraseA);
                                        columnTo = columnTo + columnTotals[x];
                                    }

                                    phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Over120Total)), pFontHeader);

                                    table12.addCell(phraseA);

                                    phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(OS)), pFontHeader);

                                    table12.addCell(phraseA);

                                    phraseA = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((columnTo + Over120Total) + OS)), pFontHeader);

                                    table12.addCell(phraseA);


                                    try {


                                        java.sql.Statement st31 = connectDB.createStatement();
                                        // java.sql.Statement st4 = connectDB.createStatement();
                                        java.sql.ResultSet rset2 = st31.executeQuery("select name from pb_notice");

                                        while (rset2.next()) {
                                            Messg = rset2.getString(1);
                                        }
                                        com.lowagie.text.HeaderFooter footer1 = new com.lowagie.text.HeaderFooter(new Phrase("" + Messg + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                                        docPdf.setFooter(footer1);




                                    } catch (java.sql.SQLException SqlExec) {
                                        
                                        SqlExec.printStackTrace();

                                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                                    }
                                    docPdf.add(table12);

                                } catch (java.sql.SQLException SqlExec) {

                                    SqlExec.printStackTrace();

                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                                }



                            } catch (com.lowagie.text.BadElementException BadElExec) {

                                // Bad

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                            }



                            //  docPdf.add(table);



                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }  // }

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
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



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
            // java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT dealer FROM ac_debtors where date BETWEEN ? AND ? and dealer IS NOT NULL order by dealer");
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no,payee FROM ac_debtors where account_no IS NOT NULL and account_no ='" + memNo1 + "' and balance > 0 order by account_no");

            // pSet1.setString(1,beginDate.toString());
            // pSet1.setString(2,endDate.toString());

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT dealer FROM ac_debtors where date BETWEEN '1-2-2003' AND '11-12-2003' and dealer IS NOT NULL order by dealer");

            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());

            }

            //System.out.println(rSet1.getObject(1).toString());
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
