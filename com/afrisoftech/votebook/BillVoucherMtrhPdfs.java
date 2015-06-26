//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.votebook;

import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class BillVoucherMtrhPdfs implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String vouchNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    String suppName = null;
    String compName = null;
    String date = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    boolean previewPrint;
    String ks;
    String particulars,
            order_no,
            invoice,
            invoice_amt,
            invoice_total_amt,
            prepared_by,
            accounts_code, dates;
    double allocation, total_commitments, balance1, this_entry, balance2;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeaderFO = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
    com.lowagie.text.Font pFontHeader20 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader32 = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    com.lowagie.text.Font pFontHeader33 = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private double witholding_amount;
    private double retention_amount;
    private double vat_amount;

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void VoucherMtrhPdf(java.sql.Connection connDb, java.lang.String voucno) {

        dbObject = new com.afrisoftech.lib.DBObject();
        vouchNo = voucno;
        connectDB = connDb;
        //  previewPrint = printPreview;
        // beginDate = begindate;

        // endDate = endate;
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

            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                    try {


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st2x = connectDB.createStatement();

                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while (rset2x.next()) {
                            ks = rset2x.getObject(1).toString();
                        }
//                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT user_name,dealer from ac_cash_book where voucher_no = '" + vouchNo + "'");
//                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
//                        while (rset2.next()) {
//                            compName = rset2.getObject(1).toString();
//                          // suppName = rset2.getObject(2).toString();
//                        }
                        // while(rset4.next())
                        //   date = rset4.getObject(1).toString();
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Withholding Tax, where applicable,has been deducted and will be remitted to the Tax Authorities"), false);
                        //: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        //docPdf.setFooter(footer);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }


                    docPdf.open();
                    double Vat = 0.00;

                    double Net1 = 0.00;
                    int vno = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {30, 25, 25, 20, 20, 30};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(1);

                        int headerwidths1[] = {100};

                        table1.setWidths(headerwidths1);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);

                        int headerwidths2[] = {40, 60};

                        table2.setWidths(headerwidths2);

                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st6x = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            java.sql.Statement st61 = connectDB.createStatement();
                            java.sql.Statement st611 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st33 = connectDB.createStatement();
                            java.sql.ResultSet rset81 = st5.executeQuery("select * from ac_reprinting_vouchers where voucher_no = '" + vouchNo + "' and invoice_amt!=0 and invoice_total_amt!=0 ");

                            while (rset81.next()) {
                                this.suppName = rset81.getString("payee");

                                // invoice_total_amt= rset81.getString("invoice_total_amt");
                                prepared_by = rset81.getString("prepared_by");
                                accounts_code = rset81.getString("accounts_code");
                                allocation = rset81.getDouble("allocation");
                                total_commitments = rset81.getDouble("total_commitments");
                                balance1 = rset81.getDouble("balance1");
                                this_entry = rset81.getDouble("this_entry");
                                balance2 = rset81.getDouble("balance2");
                                dates = rset81.getString("date");

                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no,pin_no,vat_no  from pb_hospitalprofile");

                            Phrase phrase = new Phrase("");
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            /*1*/ table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.LEFT);

                            table.getDefaultCell().setColspan(2);

                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setFixedHeight(70);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(25);
                            /*2*/ table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT);
                            //java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rs = st.executeQuery("SELECT DISTINCT hospital_name,box_no,main_telno,main_faxno FROM pb_hospitalprofile");
                            while (rs.next()) {

                                table.getDefaultCell().setColspan(5);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rs.getObject(1).toString() + " \nP.O. Box " + rs.getObject(2).toString() + "- Tel: " + rs.getObject(3).toString() + "\n Fax " + rs.getObject(3).toString(), pFontHeader31);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                //phrase = new Phrase("P.O. Box " + rs.getObject(2).toString() + " - Tel: " + rs.getObject(3).toString() + " - Fax " + rs.getObject(3).toString(), pFontHeader1);
                                //table.addCell(phrase);

                            }

                            java.sql.ResultSet rset1 = st33.executeQuery("SELECT postal_address,pin_no,vat_no FROM st_suppliers WHERE supplier_name ILIKE '" + suppName + "'");

                            System.out.println(MNo);
                            /*3*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(5);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("PAYMENT VOUCHER", pFontHeader31);

                            table.addCell(phrase);
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                         /*4*/ table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);


                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("F.O.20 VOTED [  ]", pFontHeaderFO);
                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(1);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("No : " + vouchNo, pFontHeader);
//                            table.addCell(phrase);
/*5*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Payee Name: " + suppName, pFontHeader);

                            table.addCell(phrase);

                            //fo
                            table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("F.O.21 NOT VOTED [  ]", pFontHeaderFO);

                            table.addCell(phrase);

                            if (rset1.next()) {
                                table.getDefaultCell().setColspan(4);
                                /*6*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address: " + dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                table.addCell(phrase);

                            } else {
                                table.getDefaultCell().setColspan(4);
                                /*6*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address: -", pFontHeader);
                                table.addCell(phrase);
                            }

                            //fo
                            table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("No : " + vouchNo, pFontHeader11);

                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("Particulars", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("LPO/LSO No.", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Invoice", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("Amount " + ks, pFontHeader11);
                            table.addCell(phrase);

                            java.sql.Statement st334 = connectDB.createStatement();
                            java.sql.ResultSet rset819 = st334.executeQuery("select * from ac_reprinting_vouchers where voucher_no = '" + vouchNo + "' order by this_entry desc");
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset819.next()) {
                                particulars = rset819.getString("particulars").toUpperCase();
                                invoice_amt = rset819.getString("this_entry");
                                invoice = rset819.getString("invoice");
                                order_no = rset819.getString("order_no");
                                witholding_amount = rset819.getDouble("witholding_amount");
                                vat_amount = rset819.getDouble("vat_amount");
                                retention_amount = rset819.getDouble("retention_amount");
                                table.getDefaultCell().setColspan(3);
                                //hapa 
                                table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                if (retention_amount != 0 || witholding_amount != 0 || vat_amount != 0) {
                                    table.getDefaultCell().setFixedHeight(35);
                                } else if ((retention_amount != 0 && witholding_amount != 0) || (vat_amount != 0 && witholding_amount != 0) || (vat_amount != 0 && retention_amount != 0) || (vat_amount != 0 && witholding_amount != 0 && retention_amount != 0)) {
                                    table.getDefaultCell().setFixedHeight(35);
                                } else {
                                    table.getDefaultCell().setFixedHeight(180);
                                }
                                //table.getDefaultCell().setFixedHeight(140);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(particulars, pFontHeader20);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(order_no, pFontHeader20);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(invoice, pFontHeader20);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(invoice_amt)), pFontHeader);

                                table.addCell(phrase);
//addition
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                table.getDefaultCell().setColspan(3);
                                if (vat_amount != 0.00) {

                                    if (retention_amount != 0 || witholding_amount != 0) {
                                        table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                        table.getDefaultCell().setFixedHeight(20);
                                    } else {
                                        table.getDefaultCell().setFixedHeight(135);
                                    }
                                    String reason = "";
                                    java.sql.Statement psGetReason = connectDB.createStatement();
                                    java.sql.ResultSet rstGetReason = psGetReason.executeQuery("select dealer,voucher_no,reason from ac_bills where final_voucher_no = '" + vouchNo + "'");
                                    while (rstGetReason.next()) {

                                        reason = rstGetReason.getString(3);
                                    }

                                    if (reason.contains("BOARD MEMBER")) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Less P.A.Y.E ", pFontHeader20);
                                        table.addCell(phrase);
                                    } else {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Less V.A.T ", pFontHeader20);
                                        table.addCell(phrase);
                                    }


                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader20);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("", pFontHeader20);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                    phrase = new Phrase("(" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(vat_amount) + ")", pFontHeader);

                                    table.addCell(phrase);
                                }
                                //table.getDefaultCell().setBorder( Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                table.getDefaultCell().setColspan(3);
                                if (witholding_amount != 0.00) {
                                    if (retention_amount != 0 || vat_amount != 0) {
                                        table.getDefaultCell().setFixedHeight(20);
                                    } else {
                                        table.getDefaultCell().setFixedHeight(100);
                                    }

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Less Withholding Tax Amount", pFontHeader20);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader20);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("", pFontHeader20);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                    phrase = new Phrase("(" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(witholding_amount) + ")", pFontHeader);

                                    table.addCell(phrase);
                                }

                                //added
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                table.getDefaultCell().setColspan(3);
                                if (retention_amount != 0.00) {
                                    if (witholding_amount != 0 || vat_amount != 0) {
                                        table.getDefaultCell().setFixedHeight(20);
                                    } else {
                                        table.getDefaultCell().setFixedHeight(100);
                                    }

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Less Retention Amount", pFontHeader20);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader20);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("", pFontHeader20);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                    phrase = new Phrase("(" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(retention_amount) + ")", pFontHeader);

                                    table.addCell(phrase);
                                }
//                            table.getDefaultCell().setColspan(1);
//                            table.addCell(table1);
//                            table.getDefaultCell().setColspan(2);
//                            table.addCell(table2);

                            }

                            /*                       lm     table.getDefaultCell().setFixedHeight(200);
                             table.getDefaultCell().setColspan(3);
                             //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             phrase = new Phrase("", pFontHeader);
                             table.addCell(phrase);
                           
                             table1.getDefaultCell().setColspan(1);
                             //  table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             phrase = new Phrase("", pFontHeader);
                                

                             table1.addCell(phrase);   
                             table2.getDefaultCell().setColspan(1);
                             phrase = new Phrase("", pFontHeader);
                             table2.addCell(phrase);
                             // table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                             phrase = new Phrase("", pFontHeader);
                             table2.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(1);
                             table.addCell(table1);
                             table.getDefaultCell().setColspan(2);
                             table.addCell(table2);
                             //
                             */
                            table.getDefaultCell().setFixedHeight(20);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);

                            java.sql.Statement st313 = connectDB.createStatement();
                            java.sql.ResultSet rset813 = st313.executeQuery("select sum(this_entry) from ac_reprinting_vouchers where voucher_no = '" + vouchNo + "' ");

                            while (rset813.next()) {


                                invoice_total_amt = rset813.getString(1);
                            }
                            invoice_total_amt = String.valueOf(Double.parseDouble(invoice_total_amt) - (retention_amount + witholding_amount + vat_amount));


                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total", pFontHeader1);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(invoice_total_amt)), pFontHeader1);

                            table.addCell(phrase);
                            /*6*/    // table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);  
//                            table.getDefaultCell().setColspan(6);
//                        
//
//                            phrase = new Phrase(" ", pFontHeader1);
//
//                            table.addCell(phrase);
                            //added
                            // Double d;
                            // d = Double.parseDouble(invoice_total_amt);
                            //    Double.
                            System.err.println("here it is " + com.afrisoftech.lib.CurrencyFormatter.parseFormattedString(invoice_total_amt));
                            System.err.println("here it is " + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(invoice_total_amt)));

                            String cashWord = "";
                            java.sql.ResultSet rset_cash = st7.executeQuery("select replace(cash_words('" + com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(invoice_total_amt)) + "'::money),'cents','')");
                            // System.err.println("here it is "+Double.parseDouble(invoice_total_amt));
                            if (rset_cash.next()) {
                                cashWord = rset_cash.getString(1);
                            }
                            java.sql.ResultSet rset_cashs = st61.executeQuery("select replace('" + cashWord + "','dollars and','cents')");
                            // System.err.println("here it is "+Double.parseDouble(invoice_total_amt));
                            if (rset_cashs.next()) {
                                cashWord = rset_cashs.getString(1);
                            }
                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setFixedHeight(40);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("Amount payable in Words (Ksh): " + cashWord + " only", pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(6);
//                            table.getDefaultCell().setBorder(Rectangle.TOP);
//
//                            phrase = new Phrase(" ", pFontHeader1);
//
//                            table.addCell(phrase);
                    /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setFixedHeight(20);
                            //aded                         
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Checked by:        " + prepared_by, pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Signature.................................. ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            /*8*/ table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date " + dates, pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Checked by:............................ ", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Designation.................................. ", pFontHeader1);
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(2);
//                            phrase = new Phrase("Date...........................................", pFontHeader1);
//                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);


                            phrase = new Phrase("Account Code        " + accounts_code, pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                            //
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("Examined by:............................ ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Signature.................................. ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date...........................................", pFontHeader1);
                            table.addCell(phrase);

                            // phrase = new Phrase(" ", pFontHeader1);

                            //table.addCell(phrase);
                     /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            phrase = new Phrase("Vote Book Section", pFontHeader32);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Vote Holder's Certificate (AIE)", pFontHeader32);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Budget Allocation Kshs", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(allocation), pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(1);
//                            phrase = new Phrase("            ", pFontHeader1);
//                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Name..........................................................................", pFontHeader1);
                            table.addCell(phrase);



                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Total Expenditure & Commitments Kshs", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(total_commitments), pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(1);
//                            phrase = new Phrase("            ", pFontHeader1);
//                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Signature................................", pFontHeader1);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Date...............................", pFontHeader1);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Balance Kshs", pFontHeader1);
                            table.addCell(phrase);


                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(balance1), pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                            table.getDefaultCell().setColspan(1);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Department:....................................................................... ", pFontHeader1);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("This entry Kshs", pFontHeader1);
                            table.addCell(phrase);


                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(Double.parseDouble(invoice_amt)), pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(1);
//                            phrase = new Phrase("            ", pFontHeader1);
//                            table.addCell(phrase);
//                            
                             /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Designation........................................................................", pFontHeader1);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Balance Kshs", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(balance2), pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(1);
//                            phrase = new Phrase("            ", pFontHeader1);
//                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Authority to pay", pFontHeader32);
                            table.addCell(phrase);




                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Date              " + dates, pFontHeader1);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Name...........................................", pFontHeader1);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.RIGHT);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Signature...........................................", pFontHeader1);
                            table.addCell(phrase);



//                            /*7*/  
//                            table.getDefaultCell().setBorder( Rectangle.RIGHT ); 
//                            table.getDefaultCell().setColspan(1);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Name...........................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Signature.........................", pFontHeader1);
                            table.addCell(phrase);

//                            table.getDefaultCell().setColspan(4);
//                            phrase = new Phrase("", pFontHeader1);
//                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            /*7*/ table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("For Finance Manager", pFontHeader1);
                            table.addCell(phrase);




                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
                            phrase = new Phrase("Accountant I/C Vote Book Section", pFontHeader11);
                            table.addCell(phrase);

                            /*7*/ table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date.........................................", pFontHeader1);
                            table.addCell(phrase);





                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.LEFT);

                            phrase = new Phrase("Payment Voucher No   " + vouchNo, pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Entered in Cash Book Folio No......................... ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Paid by Cash / Cheque No/ EFT No...............................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBorder(Rectangle.LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Name.................................................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Signature.................................................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Payee Signature ID...........................................................", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Accountant I/c of Cash Office", pFontHeader32);
                            table.addCell(phrase);
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        BadElExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    fnfExec.printStackTrace();

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                lwDocexec.printStackTrace();

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
