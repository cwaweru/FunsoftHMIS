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

public class DetailedPatientStatementPdf implements java.lang.Runnable {

    java.lang.String MNo = null;

    java.util.Date beginDate = null;

    java.util.Date endDate = null;
    String ks;
    public static java.sql.Connection connectDB = null;

    public java.lang.String dbUserName = null;

    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;

    boolean threadCheck = true;
    double debit = 0.0, credit = 0.0, balance = 0.0;

    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);

    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;

    public void DetailedPatientStatementPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
        MNo = combox;

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;
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

            this.generatePdf(MNo);

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {

                        java.sql.Statement st31 = connectDB.createStatement();
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select header_name from pb_header");

                        java.sql.ResultSet rset21 = st31.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset21.next()) {

                            ks = rset21.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient Statement - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths[] = {15, 40, 40, 20, 15, 15, 15};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(7);
                        Phrase phrase = new Phrase("");
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Patient Statement as at : " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Printed on : " + date, pFontHeader);

                        table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();

                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select DISTINCT patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no from hp_patient_register where patient_no = '" + memNo + "' UNION ALL select patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no from hp_inpatient_register where patient_no = '" + memNo + "'");

                            while (rset.next()) {

                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.  " + rset.getObject(1).toString(), pFontHeader);
                                table.addCell(phrase);

                                //   table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name  " + rset.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);

                                //   table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address  " + rset.getObject(3).toString(), pFontHeader);
                                table.addCell(phrase);

                                //   table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Town  " + rset.getObject(4).toString(), pFontHeader);
                                table.addCell(phrase);
                                //   table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No. " + rset.getObject(5).toString(), pFontHeader);
                                table.addCell(phrase);

                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.addCell("Date");

                            table.addCell("Description");

                            table.addCell("Details");

                            table.addCell("Transaction Type");
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.addCell("Debit");

                            table.addCell("Credit");

                            table.addCell("Balance " + ks);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT sum(debit),sum(credit),sum(debit - credit) from ac_ledger where patient_no='" + memNo + "'  AND date::date < '" + beginDate + "'::date");
  ////                         java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit),sum(credit),sum(debit - credit) from hp_patient_card where patient_no = '" + memNo + "' AND date::date < '" + beginDate + "'::date union select sum(debit),sum(credit),sum(debit - credit) from ac_ledger where patient_no='" + memNo + "' and transaction_type ilike 'Unutilized patient deposit' AND date::date < '" + beginDate + "'::date"); //To include OP cash transactions
  
                            while (rsetTotals.next()) {
                                balance = balance + rsetTotals.getDouble(3);
                                credit = credit + rsetTotals.getDouble(2);
                                debit = debit + rsetTotals.getDouble(1);
                            }
                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Opening Balance", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(debit)), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(credit)), pFontHeader);

                            //phrase = new Phrase(" ");
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(balance)), pFontHeader);

                            table.addCell(phrase);

                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT date::date,initcap(service_type) as service,reason,sum(debit),sum(credit),sum(debit - credit) as balance,transaction_no, reason||' '|| (CASE WHEN receipt_no is not null THEN 'RECEIPT_NO'||' '||receipt_no ELSE '' END) FROM ac_ledger where patient_no='" + memNo + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' group by 1,2,3,7,8 order by 1,7");
 //                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT date::date, initcap(service) as service,  transaction_type, sum(debit), sum(credit), sum(debit - credit) as balance,reference, main_service FROM hp_patient_card where patient_no ='" + memNo + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' group by 1,2,3,7,8 UNION select date::date,initcap(service_type) as service,reason,sum(debit),sum(credit),sum(debit - credit) as balance,transaction_no, reason||' '|| (CASE WHEN receipt_no is not null THEN 'RECEIPT_NO'||' '||receipt_no ELSE '' END) FROM ac_ledger where patient_no='" + memNo + "' and transaction_type ilike 'Unutilized patient deposit' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' group by 1,2,3,7,8 order by 1,7"); ////CWW 09/02/2021 to include OP transactions

                            while (rset1.next()) {
                                balance = balance + rset1.getDouble(6);
                                credit = credit + rset1.getDouble(5);
                                debit = debit + rset1.getDouble(4);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader);

                                table.addCell(phrase);
                                // Details
                                phrase = new Phrase(rset1.getObject(8).toString(), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(balance)), pFontHeader);

                                table.addCell(phrase);
                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(debit)), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(credit)), pFontHeader);

                            //phrase = new Phrase(" ");
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(balance)), pFontHeader);

                            table.addCell(phrase);

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();
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

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

}
