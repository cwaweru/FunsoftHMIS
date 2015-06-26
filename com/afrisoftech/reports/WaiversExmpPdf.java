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

public class WaiversExmpPdf implements java.lang.Runnable {

    public static java.sql.Connection connectDB = null;
    java.lang.String bank = null;
    java.util.Date beginDate = null;
    java.lang.String paType = null;
    com.afrisoftech.lib.DBObject dbObject;
    String ks;
    java.util.Date endDate = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    String compName = null;
    String date = null;
    String Activity = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private double bal;

    public void WaiversExmpPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox, java.lang.String ptype) {

        bank = combox;

        paType = ptype;

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");
        dbObject = new com.afrisoftech.lib.DBObject();

    }

    public static void main(java.lang.String[] args) {
        //	new CashBookListPdf().CashBookListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf();

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(50);

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





                    try {

                        java.sql.Statement st3 = connectDB.createStatement();

                        java.sql.Statement st5 = connectDB.createStatement();

                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT activity from pb_activity where code ilike '" + bank + "'");


                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st2x = connectDB.createStatement();

                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while (rset2x.next()) {
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset5.next()) {
                            Activity = rset5.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    double Debit = 0.00;
                    double Credit = 0.00;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);

                        int headerwidths[] = {12, 10, 20, 15, 10, 13, 13, 13};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(8);
                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);

                            phrase = new Phrase(bank + "  " + Activity + " : Period : From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);

                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(1);


                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);

                        phrase = new Phrase("Date", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Receipt No.", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Description", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Cashier", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Waiv/Exemp No", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        //     table.addCell("Reference No.");

                        phrase = new Phrase("Receipt Amt", pFontHeader);
                        table.addCell(phrase);


                        phrase = new Phrase("Waiv/Exm Amt", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Running " + ks, pFontHeader);
                        table.addCell(phrase);


                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        if (Activity.startsWith("Wai")) {
                            Activity = "Waive";
                        } else {
                            Activity = "Exemp";
                        }

                        try {


                            //  java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();

                            java.sql.Statement st21 = connectDB.createStatement();

                            if (paType.equalsIgnoreCase("OP")) {
                                java.sql.ResultSet rset = st.executeQuery("select receipt_time::date,receipt_no,dealer,user_name,journal_no,sum(debit),sum(credit) from ac_cash_collection WHERE receipt_time::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND transaction_type ILIKE '" + Activity + "%' GROUP BY receipt_time::date,receipt_no,dealer,user_name,journal_no ORDER BY 1 ASC");

                                while (rset.next()) {
                                    java.sql.ResultSet rsetss = st21.executeQuery("select sum(debit) from ac_cash_collection WHERE receipt_no = '" + rset.getObject(2) + "'");
                                    while (rsetss.next()) {
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);

                                        table.addCell(phrase);



                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

                                        table.addCell(phrase);


                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);

                                        table.addCell(phrase);


                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);

                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetss.getString(1)), pFontHeader1);
                                        Debit = Debit + rsetss.getDouble(1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)), pFontHeader1);
                                        Credit = Credit + rset.getDouble(7);
                                        table.addCell(phrase);
                                        bal = bal + (rsetss.getDouble(1) - rset.getDouble(7));
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bal)), pFontHeader1);

                                        table.addCell(phrase);
                                    }
                                }
                            } else {
                                if (paType.equalsIgnoreCase("IP")) {
                                    java.sql.ResultSet rset = st.executeQuery("select receipt_time::date,receipt_no,dealer,user_name,journal_no,sum(debit),sum(credit) from ac_cash_collection WHERE receipt_time::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND transaction_type ILIKE '" + Activity + "%' GROUP BY receipt_time::date,receipt_no,dealer,user_name,journal_no ORDER BY 1 ASC");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                    while (rset.next()) {
                                        java.sql.ResultSet rsetss = st21.executeQuery("select sum(debit) from ac_cash_collection WHERE receipt_no = '" + rset.getObject(2) + "'");
                                        while (rsetss.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);

                                            table.addCell(phrase);



                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

                                            table.addCell(phrase);


                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);

                                            table.addCell(phrase);


                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetss.getString(1)), pFontHeader1);
                                            Debit = Debit + rsetss.getDouble(1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)), pFontHeader1);
                                            Credit = Credit + rset.getDouble(7);
                                            table.addCell(phrase);
                                            bal = bal + (rsetss.getDouble(1) - rset.getDouble(7));
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bal)), pFontHeader1);

                                            table.addCell(phrase);
                                        }
                                    }
                                } else {
                                    // java.sql.ResultSet rset21 = st21.executeQuery("select sum(tl.debit-tl.credit) from transaction_list_view tl,pb_activity pb WHERE tl.date < '"+beginDate+"' AND tl.activity_code = '"+bank+"' AND tl.activity_code = pb.code AND pb.category_class ilike 'b%'");

                                    java.sql.ResultSet rset = st.executeQuery("select date,receipt_no,dealer,user_name,journal_no,sum(debit),sum(credit) from ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND transaction_type ILIKE '" + Activity + "%' GROUP BY date,receipt_no,dealer,user_name,journal_no ORDER BY 1 ASC");
                                    /*  while (rset21.next()) {
                                    table.getDefaultCell().setColspan(7);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("BBF",pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new  Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(1)), pFontHeader1);
                                    bal = rset21.getDouble(1);
                                    table.addCell(phrase);
                                    // table.addCell(phrase);
                                    }*/
                                    while (rset.next()) {
                                        java.sql.ResultSet rsetss = st21.executeQuery("select sum(debit) from ac_cash_collection WHERE receipt_no = '" + rset.getObject(2) + "'");
                                        while (rsetss.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);

                                            table.addCell(phrase);



                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

                                            table.addCell(phrase);


                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);

                                            table.addCell(phrase);


                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetss.getString(1)), pFontHeader1);
                                            Debit = Debit + rsetss.getDouble(1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)), pFontHeader1);
                                            Credit = Credit + rset.getDouble(7);
                                            table.addCell(phrase);

                                            bal = bal + (rsetss.getDouble(1) - rset.getDouble(7));
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bal)), pFontHeader1);

                                            table.addCell(phrase);
                                        }
                                    }
                                }
                            }



                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Debit)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Credit)), pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bal)), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Debit - Credit", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Debit - Credit)), pFontHeader);

                            //    table.addCell(phrase);


                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bal)), pFontHeader);

                            table.addCell(phrase);


                            docPdf.add(table);

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

            docPdf.close();
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
