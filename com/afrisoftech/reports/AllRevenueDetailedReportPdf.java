//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class AllRevenueDetailedReportPdf implements java.lang.Runnable {

    java.lang.String memNo = null;
    java.lang.String UserName = null;
    java.lang.String CashPoint = null;
    String ks;
    java.util.Date beginDate = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void AllRevenueDetailedReportPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        //  public void ShiftReportDetailedPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint) {
        //     memNo = combox;
        beginDate = begindate;
        endDate = endate;

        connectDB = connDb;

        //  beginDate = begindate;

        /// endDate = endate;
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

    public void generatePdf(java.lang.String memNo) {

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


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Workloads against Billed services report- Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();
                    double amt = 0.00;
                    double amtExpected = 0.00;
                    int counTotal = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);

                        int headerwidths[] = {9, 15, 25, 10, 7, 13, 13, 13, 13, 13};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));
                        table.setHeaderRows(2);
                        Phrase phrase = new Phrase(" ", pFontHeader);

                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);


                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);

                            phrase = new Phrase("DEPARTMENTAL WORKLOAD AGAINST BILLED SERVICES REPORT BY CATEGORY  - Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            // table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Code", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Category", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Service", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Count", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Unit Price", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Expected Amount", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Actual Amount", pFontHeader);
                            table.addCell(phrase);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Variance", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Running " + ks, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                            for (int i = 0; i < listofAct.length; i++) {
                                double subTotal = 0.00;
                                double subTotalExpected = 0.00;
                                int count = 0;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                int descr = 0;
                                int numb = 0;

                                java.sql.Statement st1 = connectDB.createStatement();

                                java.sql.ResultSet rset1 = st1.executeQuery("select distinct activity from pb_activity where code ILIKE '" + listofAct[i].toString() + "'");

                                while (rset1.next()) {

                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rset1.getObject(1), "").toUpperCase(), pFontHeader);
                                    table.addCell(phrase);


                                    java.sql.Statement st22 = connectDB.createStatement();
                                    java.sql.PreparedStatement st5 = connectDB.prepareStatement("select TRIM(initcap(service_type)),"
                                        + " (select sum(qty) from (select sum(quantity) as qty from "
                                        + "ac_cash_collection where upper(ac_cash_collection.description) = "
                                        + "upper(ac_ledger.service_type) and ac_cash_collection.date BETWEEN"
                                        + " '" + beginDate + "' AND '" + endDate + "' AND "
                                        + "ac_cash_collection.activity_code = '" + listofAct[i].toString() + "' "
                                        + " UNION select sum(dosage) as qty from hp_patient_card where"
                                        + " upper(hp_patient_card.service) = upper(ac_ledger.service_type)"
                                        + " AND hp_patient_card.service NOT ILIKE 'receipt%' AND hp_patient_card.date BETWEEN '" + beginDate + "' AND '" 
                                        + endDate + "' AND upper(hp_patient_card.main_service) ="
                                        + " upper('"+com.afrisoftech.lib.GLCodesFactory.getActivityDescription(connectDB, listofAct[i].toString())+"')) as foo),"
                                        + " sum(credit-debit) from ac_ledger where date BETWEEN '" + beginDate + "' AND '" + endDate + "'"
                                        + " AND activity_code = '" + listofAct[i].toString() + "'  group by service_type, ac_ledger.date order by service_type");


                                java.sql.ResultSet rset = st5.executeQuery();
                               
                                java.sql.PreparedStatement st52 = connectDB.prepareStatement("select count(service_type) from ac_ledger where date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code  = '" + listofAct[i].toString() + "'  group by service_type order by service_type");
                                    java.sql.ResultSet rset2 = st52.executeQuery();
                                    while (rset2.next()) {
                                        descr = rset2.getInt(1);
                                    }

                                    while (rset.next()) {
                                        String itemCode = null;
                                        double itemPrice = 0.00;
                                        java.sql.PreparedStatement pstmtItemCode = connectDB.prepareStatement("SELECT code, rate FROM pb_operating_parameters WHERE upper(trim(service_type)) = upper(trim(?)) AND gl_account = ? UNION ALL select product_id as code,selling_price as rate FROM st_stock_prices where upper(trim(product)) = upper(trim(?)) ORDER BY 1 LIMIT 1");
                                        pstmtItemCode.setString(1, rset.getString(1));
                                        pstmtItemCode.setString(2, listofAct[i].toString());
                                        pstmtItemCode.setString(3, rset.getString(1));
                             //           pstmtItemCode.setString(4, listofAct[i].toString());

                                        java.sql.ResultSet rsetItemCode = pstmtItemCode.executeQuery();
                                        while (rsetItemCode.next()) {
                                            itemCode = rsetItemCode.getString("code");
                                            itemPrice = rsetItemCode.getDouble("rate");

                                        }
                                        
                                        
//                                        if (itemPrice == 0.00) {
//                                            itemPrice = rset.getDouble(4); // rset.getInt(2);
//                                        } 

                                        numb = numb + 1;
                                        if (numb == 1) {
                                            table.getDefaultCell().setColspan(2);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rset.getObject(1), ""), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rset.getInt(2), ""), pFontHeader1);
                                            table.addCell(phrase);
                                            count = count + rset.getInt(2);
                                            counTotal = counTotal + rset.getInt(2);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(itemPrice)), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(itemPrice * rset.getInt(2))), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(Double.parseDouble(rset.getString(3)) - itemPrice * rset.getInt(2))), pFontHeader1);
                                            table.addCell(phrase);

                                            amt = amt + rset.getDouble(3);
                                            subTotal = subTotal + rset.getDouble(3);
                                            amtExpected = amtExpected + (rset.getInt(2) * itemPrice);
                                            subTotalExpected = subTotalExpected + (rset.getInt(2) * itemPrice);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader1);
                                            table.addCell(phrase);

                                        } else {

                                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setColspan(2);
                                            phrase = new Phrase(" ", pFontHeader);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(2);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rset.getObject(1), ""), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rset.getInt(2), ""), pFontHeader1);
                                            table.addCell(phrase);
                                            count = count + rset.getInt(2);
                                            counTotal = counTotal + rset.getInt(2);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(itemPrice)), pFontHeader1);
                                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader1);
                                            table.addCell(phrase);

                                            amt = amt + rset.getDouble(3);
                                            subTotal = subTotal + rset.getDouble(3);
                                            amtExpected = amtExpected + (rset.getInt(2) * itemPrice);
                                            subTotalExpected = subTotalExpected + (rset.getInt(2) * itemPrice);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(itemPrice)), pFontHeader1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(itemPrice * rset.getInt(2))), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader1);
                                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(itemPrice * rset.getInt(2))), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(Double.parseDouble(rset.getString(3)) - itemPrice * rset.getInt(2))), pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                    }

                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("  ", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(" Total", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("" + count, pFontHeader);

                                    table.getDefaultCell().setColspan(1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader);

                                    table.getDefaultCell().setColspan(1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotalExpected)), pFontHeader);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader);



                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal - subTotalExpected)), pFontHeader);


                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader);

                                    table.addCell(phrase);
                                }

                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Grand Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase =
                                    new Phrase("" + counTotal, pFontHeader);
                            table.addCell(phrase);

                            phrase =
                                    new Phrase(" ", pFontHeader);
                            table.addCell(phrase);

                            phrase =
                                    new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amtExpected)), pFontHeader);
                            table.addCell(phrase);

                            phrase =
                                    new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt)), pFontHeader);
                            table.addCell(phrase);

                            phrase =
                                    new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amtExpected - amt)), pFontHeader);
                            table.addCell(phrase);


                            phrase =
                                    new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt)), pFontHeader);
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

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getDates() {

        java.lang.Object[] listofDates = null;

        java.util.Vector listofDatesVector = new java.util.Vector(1, 1);


        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM ac_ledger where date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND activity_code ilike '6%' ORDER BY date");

            while (rSet1.next()) {

                listofDatesVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofDates = listofDatesVector.toArray();

        System.out.println("Done list of Staff Nos ...");

        return listofDates;
    }

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {


            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT activity_code FROM ac_ledger where date BETWEEN '" + beginDate + "' AND '" + endDate + "'   AND activity_code ilike '6%' ORDER BY activity_code");

            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());

            }

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();

        System.out.println("Done list of activities ...");

        return listofActivities;

    }
}
