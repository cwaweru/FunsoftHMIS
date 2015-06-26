//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import static com.afrisoftech.reports.NHIFStatementAccPdf.connectDB;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class PaymentSchedulePdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String vouchNo = null;
    java.lang.String scheduleNo = null;
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
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLDITALIC);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void PaymentSchedulePdf(java.sql.Connection connDb, java.lang.String scheduleNumber, boolean printPreview) {

        dbObject = new com.afrisoftech.lib.DBObject();
        //    vouchNo = voucno;

        scheduleNo = scheduleNumber;
        connectDB = connDb;
        previewPrint = printPreview;
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
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

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
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT user_name,dealer from ac_cash_book where voucher_no = '" + vouchNo + "'");

                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            suppName = rset2.getObject(2).toString();
                        }

                        //  com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Withholding Tax, where applicable,has been deducted and will be remitted to the Tax Authorities"),false);

                        //  docPdf.setFooter(footer);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Creditors'/Imprest Payment Schedule  - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

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
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

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

                    double Vat = 0.00;
                    double Net = 0.00;
                    double Net1 = 0.00;
                    int vno = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths[] = {10, 50, 25, 25, 20, 10, 20};

                        table.setWidths(headerwidths);

                        table.setHeaderRows(2);

                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorder(Rectangle.BOX);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        try {

                            java.sql.Statement st = connectDB.createStatement();
//////////select dealer,chq_date,cheque_no,initcap(payment_mode), account_no, account_name,sum(credit) as amount  from ac_cash_book  where schedule_no = '" + scheduleNo + "' group by dealer,chq_date,cheque_no,initcap(payment_mode), account_no, account_name
                            java.sql.ResultSet rs = st.executeQuery("select dealer,chq_date,cheque_no,initcap(payment_mode), account_no, account_name,sum(credit) as amount  from ac_cash_book  where schedule_no = '" + scheduleNo + "' group by dealer,chq_date,cheque_no,initcap(payment_mode), account_no, account_name");
                            //java.sql.ResultSet rs = st.executeQuery("select 1,chq_date,cb.cheque_no,initcap(cb.payment_mode), account_no, account_name from ac_cash_book cb where cb.schedule_no = '" + scheduleNo +"' group by 1,chq_date,cb.cheque_no,initcap(cb.payment_mode), account_no, account_name");

                            System.out.println(MNo);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            String bankName = "";
                            String accountNumber = "";
                            String chequeNumber = "";
                            double rtgsTotal = 0.00;
                            double eftTotal = 0.00;
                            double grandTotal = 0.00;
                            table.getDefaultCell().setColspan(7);
                            while (rs.next()) {
                                bankName = rs.getString(6);
                                accountNumber = rs.getString(5);
                                chequeNumber = rs.getString(3);
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            Phrase phrase = new Phrase("CREDITORS'/IMPREST PAYMENTS SCHEDULE NO: [" + scheduleNo + "] of DATE : [" + com.afrisoftech.lib.ServerTime.serverDate(connectDB)
                                    + "]\n"
                                    + " A/C Name : [" + bankName.toUpperCase() + "]  A/C. No.: [" + accountNumber.toUpperCase() + "]", pFontHeader1);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorder(Rectangle.BOX);

                            phrase = new Phrase("Index", pFontHeader1);

                            table.addCell(phrase);
                            phrase = new Phrase("Creditor Name", pFontHeader1);

                            table.addCell(phrase);
                            phrase = new Phrase("Bank Account No.", pFontHeader1);

                            table.addCell(phrase);
                            phrase = new Phrase("Bank", pFontHeader1);

                            table.addCell(phrase);
                            phrase = new Phrase("Branch", pFontHeader1);

                            table.addCell(phrase);
                            phrase = new Phrase("Code", pFontHeader1);

                            table.addCell(phrase);
                            phrase = new Phrase("Amount(KES)", pFontHeader1);

                            table.addCell(phrase);

                            java.sql.Statement stRTGSCount = connectDB.createStatement();

                            java.sql.ResultSet rsRTGSCount = stRTGSCount.executeQuery("select count(*) from ac_cash_book cb where cb.schedule_no = '" + scheduleNo + "' and cb.payment_mode ilike 'RTGS'");

                            int rtgsCount = 0;

                            while (rsRTGSCount.next()) {
                                rtgsCount = rsRTGSCount.getInt(1);
                            }
                            if (rtgsCount > 0) {
                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                phrase = new Phrase("RTGS PAYMENTS", pFontHeader1);

                                table.addCell(phrase);

                                java.sql.Statement stDetails = connectDB.createStatement();

                                //java.sql.ResultSet rsDetails = stDetails.executeQuery("select cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code, cb.credit from ac_cash_book cb where cb.schedule_no = '" + scheduleNo + "' and payment_mode ilike 'RTGS'");
                                java.sql.ResultSet rsDetails = stDetails.executeQuery("select cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code, sum(cb.credit) from ac_cash_book cb where cb.schedule_no ='" + scheduleNo + "' and payment_mode ilike 'RTGS' group by cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code");
                                        
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                int index = 1;
                                while (rsDetails.next()) {


                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(String.valueOf(index), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(1), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rsDetails.getDouble(6))), pFontHeader);
                                    rtgsTotal = rtgsTotal + rsDetails.getDouble(6);
                                    table.addCell(phrase);

                                    index++;
                                }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Sub Total RTGS Payments", pFontHeader1);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rtgsTotal)), pFontHeader1);
                                // rtgsTotal = rtgsTotal + rsDetails.getDouble(6);
                                table.addCell(phrase);
                            }
                            java.sql.Statement stEFTCount = connectDB.createStatement();

                            java.sql.ResultSet rsEFTCount = stRTGSCount.executeQuery("select count(*) from ac_cash_book cb where cb.schedule_no = '" + scheduleNo + "' and cb.payment_mode ilike 'EFT'");

                            int eftCount = 0;

                            while (rsEFTCount.next()) {
                                eftCount = rsEFTCount.getInt(1);
                            }
                            if (eftCount > 0) {
                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                phrase = new Phrase("EFT PAYMENTS", pFontHeader1);

                                table.addCell(phrase);

                                java.sql.Statement stDetails = connectDB.createStatement();

                                java.sql.ResultSet rsDetails = stDetails.executeQuery("select cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code, cb.credit from ac_cash_book cb where cb.schedule_no = '" + scheduleNo + "' and payment_mode ilike 'EFT'");


                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                int index = 1;
                                table.getDefaultCell().setColspan(1);
                                while (rsDetails.next()) {

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(String.valueOf(index), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(1), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rsDetails.getDouble(6))), pFontHeader);
                                    eftTotal = eftTotal + rsDetails.getDouble(6);
                                    table.addCell(phrase);

                                    index++;
                                }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Sub Total EFT Payments", pFontHeader1);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(eftTotal)), pFontHeader1);
                                // rtgsTotal = rtgsTotal + rsDetails.getDouble(6);
                                table.addCell(phrase);
                            }

                          //  Cheque payments for imprest
                            java.sql.Statement stCHQCount = connectDB.createStatement();

                            java.sql.ResultSet rsCHQCount = stCHQCount.executeQuery("select count(*) from ac_cash_book cb where cb.schedule_no = '" + scheduleNo + "' and cb.payment_mode ilike 'CHEQUE'");

                            int chqCount = 0;

                            while (rsCHQCount.next()) {
                                chqCount = rsCHQCount.getInt(1);
                            }
                            if (chqCount > 0) {
                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                phrase = new Phrase("IMPREST/CHEQUE PAYMENTS", pFontHeader1);

                                table.addCell(phrase);

                                java.sql.Statement stDetails = connectDB.createStatement();

                                java.sql.ResultSet rsDetails = stDetails.executeQuery("select cb.dealer,cb.payee_bank_account_no,cb.payee_bank_name, cb.payee_bank_branch, cb.payee_bank_transfer_code, cb.credit from ac_cash_book cb where cb.schedule_no = '" + scheduleNo + "' and payment_mode ilike 'CHEQUE'");


                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                int index = 1;
                                table.getDefaultCell().setColspan(1);
                                while (rsDetails.next()) {

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(String.valueOf(index), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(1), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rsDetails.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(rsDetails.getDouble(6))), pFontHeader);
                                    eftTotal = eftTotal + rsDetails.getDouble(6);
                                    table.addCell(phrase);

                                    index++;
                                }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Sub Total IMPREST/CHEQUE Payments", pFontHeader1);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(eftTotal)), pFontHeader1);
                                // rtgsTotal = rtgsTotal + rsDetails.getDouble(6);
                                table.addCell(phrase);
                            }




                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            //while (rset5.next()) //{
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Grand Total".toUpperCase(), pFontHeader1);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Cheque Number : [" + chequeNumber + "]".toUpperCase(), pFontHeader1);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(eftTotal + rtgsTotal)), pFontHeader1);

                            table.addCell(phrase);


                            table.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table.getDefaultCell().setColspan(7);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);

                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("PREPARED BY : " + com.afrisoftech.lib.UserName.getUserName(connectDB).toUpperCase() + ", DATE : " + com.afrisoftech.lib.ServerTime.serverDate(connectDB), pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("1st SIGNATURE Name : ____________________________________    Signature ______________________________ ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Date:__________________________", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("2nd SIGNATURE Name : ____________________________________    Signature ______________________________ ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Date:_______________________ ", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("3rd SIGNATURE Name : ____________________________________    Signature ______________________________ ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Date:_______________________ ", pFontHeader1);
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

            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
