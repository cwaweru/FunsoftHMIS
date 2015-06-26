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

public class ShiftReconPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private double banked;
    private double bankDiff;
    private double shiftExcess;

    public void ShiftReconPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new TransactionsListPdf().TransactionsListPdf();
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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                    try {

                        java.lang.Class.forName("org.postgresql.Driver");

                    } catch (java.lang.ClassNotFoundException cnfExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());

                    }



                    String compName = null;
                    String date = null;
                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
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
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + "", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        //  docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    double debit = 0;
                    double credit = 0;
                    try {

                        try {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);


                            int headerwidths[] = {10, 20, 10, 20, 10, 10, 10, 10, 10, 10};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));
                            Phrase phrase = null;

                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setFixedHeight(70);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(16);
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            while (rset3.next()) {
                                table.getDefaultCell().setColspan(8);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader1);
                                table.addCell(phrase);
                            }

                            table.setHeaderRows(3);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOX);

                            table.getDefaultCell().setColspan(5);

                            phrase = new Phrase("", pFontHeader);


                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);

                                phrase = new Phrase("Shift Reconcilliation Report : From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase("Printed On  : " + date, pFontHeader);

                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setFixedHeight(25);

                            phrase = new Phrase("Date Reconcilled", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Cashier", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Shift No", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Reconc. By", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Shift Amt (" + ks + ")", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Actual Amount (" + ks + ")", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Excess/(Shortage) (" + ks + ")", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Banked Amount (" + ks + ")", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Bank Receipt. No.", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Over/(Under) Banking (" + ks + ")", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);


                            //    try {

                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();



                            java.sql.ResultSet rset = st.executeQuery("SELECT date_collected, (select user_name from ac_shifts where shift_no::varchar = sf.shift_no), shift_no::int,initcap(user_name),SUM(shift_amount) as debit,SUM(amount) as credit,sum(shift_amount-amount) as balance, (select sum(credit) from ac_cash_collection where shift_no = sf.shift_no and transaction_type ilike 'banking') from ac_shift_collections sf WHERE date_collected BETWEEN '" + beginDate + "' AND '" + endDate + "' GROUP BY 1,2,3,4, sf.shift_no ORDER BY 2 ASC");

                            // java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(debit) as debit,SUM(credit) as credit,sum(debit-credit) as balance from tb_itemized_summary WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' GROUP BY date ORDER BY date ASC");



                            while (rset.next()) {
                                // Printing reconcilliation date
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);

                                table.addCell(phrase);

                                //Printing Cashier
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //   phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);

                                table.addCell(phrase);

                                //Printing Shift number
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);

                                table.addCell(phrase);

                                //Printing head cachier reconciled by
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

                                table.addCell(phrase);
                                //Printing Shift Amount
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(5)), pFontHeader1);
                                table.addCell(phrase);
                                //Printing Reconcilled amount / Actual surrendered amount
                                debit = debit + rset.getDouble(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(6)), pFontHeader1);
                                table.addCell(phrase);
                                ////Printing excess / short
                                credit = credit + rset.getDouble(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)), pFontHeader1);
                                table.addCell(phrase);
                                shiftExcess = shiftExcess + rset.getDouble(7);
                                // Printing banked amount
                                banked = banked + rset.getDouble(8);

                                bankDiff = bankDiff + rset.getDouble(8) - rset.getDouble(6);


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(8)), pFontHeader1);//rset.getDouble(7) - rset.getDouble(6))), pFontHeader1);
                                table.addCell(phrase);

                                // Fetching bank slip number
                                java.sql.PreparedStatement pstmtC = connectDB.prepareStatement("select transaction_no from ac_cash_collection where shift_no = ? and transaction_type ilike 'banking'");

                                pstmtC.setString(1, rset.getObject(3).toString());

                                java.sql.ResultSet rsetC = pstmtC.executeQuery();

                                String slipNumber = "";

                                while (rsetC.next()) {
                                    java.sql.PreparedStatement pstmtS = connectDB.prepareStatement("select slip_no from ac_cash_book where transaction_no = ?");
                                    pstmtS.setString(1, rsetC.getObject(1).toString());
                                    java.sql.ResultSet rsetS = pstmtS.executeQuery();
                                    while (rsetS.next()) {
                                        slipNumber = rsetS.getString(1);
                                    }
                                }

                                //Printing bank slip number
                                phrase = new Phrase(slipNumber, pFontHeader1);

                                table.addCell(phrase);

                                //Printing over/under banking
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(rset.getDouble(8) - rset.getDouble(6))), pFontHeader1);
                                table.addCell(phrase);
                            }




                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOX);//TTOM | Rectangle.TOP);

                            // while (rsetTotals.next()) {

                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader11);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit)), pFontHeader11);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(credit)), pFontHeader11);

                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit - credit)), pFontHeader11);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(banked)), pFontHeader11);

                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bankDiff)), pFontHeader11);

                            table.addCell(phrase);

                            //}

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

            //        docPdf.close();
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
