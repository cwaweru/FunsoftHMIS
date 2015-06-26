//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import static com.afrisoftech.reports.NHIFInvoicePdf.connectDB;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class CreditorsLedgerPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    String ks;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void CreditorsLedgerPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
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

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName + "                                                        Printed On: " + date + "", pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Creditors Ledger - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();
                    

                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(13);

                        int headerwidths[] = {10,20, 20, 20,40, 20,20, 20, 35,50,29, 29,29};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(13);
                        Phrase phrase = new Phrase("");
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(10);

                            phrase = new Phrase("Creditors Ledger : As at " + dateFormat.format(endDate1), pFontHeader11);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  : " + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("", pFontHeader);
                        table.addCell(phrase);
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Trans Date", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Invoice No", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Invoice Date", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Description", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Lpo/Lso", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Voucher", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Cheque", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Trans Type", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Narration", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Debit", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Credit", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Running Amt" + ks, pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        double grossTotal = 0.00;
                        double grossCredit = 0.00;
                        double grossDebit = 0.00;
                        try {

                            java.lang.Object creditors[] = this.getListofStaffNos();
                            String mmme=null;
                            for (int i = 0; i < creditors.length; i++) {
                                mmme=com.afrisoftech.lib.VoteBook.RemoveAppostroph(creditors[i].toString());
                               

                                java.sql.PreparedStatement st = connectDB.prepareStatement("select date,item,voucher_no, transaction_type,invoice_no,sum(debit),sum(credit),journal_no,invoice_date,order_no,reason,reference_no from ac_accounts_payable where dealer ilike '"+mmme+"' AND date::date < '"+endDate+"' :: date +1 GROUP BY 1,2,3,4,5,8,9,10,11,12 order by 1,12,6");

                                //st.setString(1, creditors[i].toString());
                                java.sql.PreparedStatement st2 = connectDB.prepareStatement("SELECT SUM(credit),sum(debit),sum(credit-debit) from ac_accounts_payable WHERE dealer ilike '"+mmme+"' AND date::date < '"+endDate+"' :: date +1");

//                                st2.setString(1, creditors[i].toString());

                                java.sql.ResultSet rset = st.executeQuery(); // "select invoice_no,date,dealer,sum(credit),item,sum(debit),sum(credit-debit) from ac_accounts_payable WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and dealer ilike '" + creditors[i].toString() + "' and (transaction_type ILIKE 'Stock%' OR transaction_type ILIKE 'Raise Invoice' OR transaction_type ILIKE 'INV') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rsetTotals = st2.executeQuery(); //"SELECT SUM(credit),sum(debit),sum(credit-debit) from ac_accounts_payable WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and dealer ilike '" + creditors[i].toString() + "' and (transaction_type ILIKE 'Stock%' OR transaction_type ILIKE 'Raise Invoice' OR transaction_type ILIKE 'INV')");

                                table.getDefaultCell().setColspan(13);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(i+1+". " + creditors[i].toString().toUpperCase(), pFontHeader);

                                table.addCell(phrase);
                                    double runningAmt=0.00;
                                    int noSeq = 0;
                                while (rset.next()) {

                                    noSeq = noSeq + 1;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(String.valueOf(noSeq)+".", pFontHeader1);
                                    table.addCell(phrase);
                                    //1 transdate
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //2 Invoice
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                   // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //3 invoice date
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //4 narration
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //5 lpo
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(10), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    //6 voucher
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                   //7 cheque
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //8 trans type
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //9 narration
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(11), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    //10 Debit
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(6)), pFontHeader1);
                                    table.addCell(phrase);
                                        runningAmt=runningAmt-rset.getDouble(6);
                                    // 11 credit
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)), pFontHeader1);
                                    table.addCell(phrase);
                                        runningAmt=runningAmt+rset.getDouble(7);
                                    // 12 running
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(runningAmt)), pFontHeader1);
                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                while (rsetTotals.next()) {

                                    table.getDefaultCell().setColspan(10);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Creditor Sub Total", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);

                                    grossCredit = grossCredit + rsetTotals.getDouble(1);

                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)), pFontHeader);

                                    table.addCell(phrase);

                                    grossDebit = grossDebit + rsetTotals.getDouble(2);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(3)), pFontHeader);

                                    table.addCell(phrase);

                                    grossTotal = grossTotal + rsetTotals.getDouble(3);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase((""), pFontHeader);

                                    table.addCell(phrase);

                                }

                            }

                            table.getDefaultCell().setColspan(9);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("CREDITORS GROSS TOTAL", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(grossCredit), pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(grossDebit), pFontHeader);

                            table.addCell(phrase);


                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(grossTotal), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                          //  phrase = new Phrase((""), pFontHeader);

                          //  table.addCell(phrase);
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
   
    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            java.sql.Statement st4 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = st4.executeQuery("SELECT distinct dealer FROM ac_accounts_payable where date < '" + endDate + "' and dealer in (select supplier_name from st_suppliers) order by 1");
                   

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

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
