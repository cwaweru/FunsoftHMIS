//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class DetailedCashSummaryPerDptPdf implements java.lang.Runnable {

    java.lang.String memNo = null;
    java.lang.String UserName = null;
    java.lang.String CashPoint = null;
    String ks;
    java.util.Date beginDate = null;
    String Categ = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private double cash = 0.00;
    private double mobile = 0.00;
    private double tMobile = 0.00;
    private double oth = 0.00;
    private double totals = 0.00;
    private double tCash = 0.00;
    private double tCheques = 0.00;
    private double tEfts = 0.00;
    private double tCheque = 0.00;
    private double tCcard = 0.00;
    private double tOth = 0.00;
    private double gTotal = 0.00;

    public void DetailedCashSummaryPerDptPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        //  public void ShiftReportDetailedPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint) {
        //     memNo = combox;
        beginDate = begindate;

        endDate = endate;

        connectDB = connDb;

        dbObject = new com.afrisoftech.lib.DBObject();
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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st4x = connectDB.createStatement();


                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        float mints = 0;
                        java.sql.ResultSet rset4x = st4x.executeQuery("SELECT EXTRACT(MINUTE FROM(current_time::time - input_time::time)) FROM cash_analysis");

                        while (rset4x.next()) {
                            mints = rset4x.getFloat(1);
                        }
                        if (mints > 7 || mints <= 0) {
                            java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("DELETE FROM cash_analysis");
                            pstmt31.executeUpdate();


                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO cash_analysis("
                                    + "activity_code, receipt_time, transaction_type, amount)"
                                    + "SELECT activity_code, receipt_time, payment_mode, amount FROM cash_summary_exe");
                            pstmt.executeUpdate();
                        }

                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Daily Cash Breakdown per Category- Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();
                    double amt = 0.00;
                    int counTotal = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(11);

                        int headerwidths[] = {10, 18, 12, 12, 12, 12, 12,12, 12, 13, 13};

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
                            table.getDefaultCell().setColspan(8);

                            phrase = new Phrase("DAILY CASH COLLECTIONS BY CATEGORY  - Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        // phrase = new Phrase("SHIFT No. FROM :     CASHIER : ", pFontHeader);
                        ///table.addCell(phrase);





                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {

                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            // table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Category", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Cash", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Chq", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Eft", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Mobile Pay", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Exemption", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Waiver", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Refund", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Amount", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Running " + ks, pFontHeader);
                            table.addCell(phrase);

                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

//                            java.lang.Object[] getDates = this.getDates();
                            //for (int k = 0; k < getDates.length; k++) {
                            double subTotal = 0.00;
                            double subcash = 0.00;
                            double subcheque = 0.00;
                            double subccard = 0.00;
                            double suboth = 0.00;
                            double cheques = 0.00;
                            double efts = 0.00;
                            double subcheques = 0.00;
                            double subefts = 0.00;
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(10);
                            //phrase = new Phrase(getDates[k].toString().toUpperCase(), pFontHeader);
                            //table.addCell(phrase);
                            java.lang.Object[] listofAct = this.getListofActivities();
                            // java.lang.Object[] listofAct = this.getListofActivities(getDates[k]);

                            for (int i = 0; i < listofAct.length; i++) {
                                double cheque = 0.00;
                                double ccard = 0.00;
                                int count = 0;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                int descr = 0;
                                //  java.lang.Object[] listofAct1 = this.getListofActivities(listofAct[i]);

                                java.sql.Statement st1 = connectDB.createStatement();

                                java.sql.ResultSet rset1 = st1.executeQuery("select distinct initcap(activity) from pb_activity where code ILIKE '" + listofAct[i].toString() + "'");
                                // if(i == 0){

                                while (rset1.next()) {
                                    phrase = new Phrase(listofAct[i].toString(), pFontHeader);
                                    // table.addCell(phrase);
                                    table.getDefaultCell().setColspan(9);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader);
                                    // table.addCell(phrase);
                                    Categ = rset1.getObject(1).toString();
                                    System.out.println(Categ);
                                    java.sql.Statement st22 = connectDB.createStatement();
                                    java.sql.Statement st5 = connectDB.createStatement();
                                    java.sql.Statement st5x = connectDB.createStatement();
                                    java.sql.Statement st5z = connectDB.createStatement();
                                    java.sql.Statement st5xc = connectDB.createStatement();
                                    java.sql.Statement st5ze = connectDB.createStatement();
                                    java.sql.Statement st5v = connectDB.createStatement();
                                    java.sql.Statement st5m = connectDB.createStatement();
                                    /*   java.sql.ResultSet rset = st5.executeQuery("SELECT SUM(receipts) as amt FROM receipts_sum WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND transaction_type NOT ILIKE 'Banking%' AND activity_code = '" + listofAct[i].toString() + "'  AND payment_mode ILIKE 'Cash%' ");
                                    java.sql.ResultSet rsetxc = st5xc.executeQuery("SELECT SUM(receipts) as amt FROM receipts_sum WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "' AND payment_mode ILIKE 'Cheque%' ");
                                    java.sql.ResultSet rsetze = st5ze.executeQuery("SELECT SUM(receipts) as amt FROM receipts_sum WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "' AND (payment_mode ILIKE 'EFT' OR payment_mode ILIKE 'E.F.T%')");
                                    java.sql.ResultSet rsetx = st5x.executeQuery("SELECT ROUND(sum(exemptions)) FROM exemption_sum where date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "' AND transaction_type = 'Exemptions'");
                                    java.sql.ResultSet rsetz = st5z.executeQuery("SELECT ROUND(sum(exemptions)) FROM exemption_sum where date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "' AND transaction_type = 'Waiver'");

                                    java.sql.ResultSet rsetv = st5v.executeQuery("SELECT SUM(receipts*-1) as amt FROM receipts_sum WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "'  AND transaction_type = 'RECEIPT CANCELLATION'");

                                    java.sql.Statement st52 = connectDB.createStatement();

                                    java.sql.ResultSet rset2 = st52.executeQuery("SELECT distinct count(activity_code) FROM receipts_sum WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code  = '" + listofAct[i].toString() + "' AND transaction_type not ilike 'Bank%'");
                                     */

                                    java.sql.ResultSet rset = st5.executeQuery("SELECT sum(amount) as amt from cash_analysis WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "'  AND transaction_type ILIKE 'Cash%' ");
                                    java.sql.ResultSet rsetm = st5m.executeQuery("SELECT sum(amount) as amt from cash_analysis WHERE receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "'  AND (transaction_type ILIKE 'MPAY' OR transaction_type ILIKE '%Mobile%' OR transaction_type ILIKE '%pesa%') ");
                                    java.sql.ResultSet rsetxc = st5xc.executeQuery("select sum(amount) from cash_analysis where receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' AND activity_code = '" + listofAct[i].toString() + "' AND transaction_type ILIKE 'Cheque%' ");
                                    java.sql.ResultSet rsetze = st5ze.executeQuery("select sum(amount) from cash_analysis where receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' and activity_code = '" + listofAct[i].toString() + "' AND (transaction_type ILIKE 'EFT' OR transaction_type ILIKE 'E.F.T%')");
                                    java.sql.ResultSet rsetx = st5x.executeQuery("select sum(amount) from cash_analysis where receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' and activity_code = '" + listofAct[i].toString() + "' AND transaction_type ILIKE 'Exemptions%'");
                                    java.sql.ResultSet rsetz = st5z.executeQuery("select sum(amount) from cash_analysis where receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' and activity_code = '" + listofAct[i].toString() + "' AND transaction_type ILIKE 'Waiver%'");

                                    java.sql.ResultSet rsetv = st5v.executeQuery("select sum(debit-credit) from ac_cash_collection where date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and activity_code = '" + listofAct[i].toString() + "'  AND transaction_type ILIKE 'RECEIPT CANCELLATION'");

                                    java.sql.Statement st52 = connectDB.createStatement();

                                    java.sql.ResultSet rset2 = st52.executeQuery("select distinct count(activity_code) from cash_analysis where receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' and activity_code  = '" + listofAct[i].toString() + "' AND transaction_type not ilike 'Bank%'");

                                    while (rset2.next()) {
                                        descr = rset2.getInt(1);
                                    }


                                    while (rsetz.next()) {
                                        while (rsetx.next()) {

                                            ccard = rsetz.getDouble(1);

                                            cheque = rsetx.getDouble(1);
                                        }
                                    }

                                    if (descr > 0) {
                                        while (rset.next()) {

                                            while (rsetxc.next()) {
                                                while (rsetze.next()) {

                                                    while (rsetv.next()) {
                                                        
                                                         while (rsetm.next()) {


                                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                                        table.getDefaultCell().setColspan(2);
                                                        phrase = new Phrase(Categ, pFontHeader1);
                                                        table.addCell(phrase);
                                                        table.getDefaultCell().setColspan(1);

                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        //
                                                        cash = rset.getDouble(1);
                                                        mobile = rsetm.getDouble(1);
                                                        cheques = rsetxc.getDouble(1);
                                                        tCheques = tCheques + rsetxc.getDouble(1);
                                                        subcheques = subcheques + rsetxc.getDouble(1);
                                                        efts = rsetze.getDouble(1);

                                                        tEfts = tEfts + rsetze.getDouble(1);
                                                        subefts = subefts + rsetze.getDouble(1);
                                                        //table.addCell(phrase);
                                                        //tCash = tCash + cash;
                                                        //subcash = subcash + cash;
                                                        tCash = tCash + cash - cheque - ccard;
                                                        subcash = subcash + cash - cheque - ccard;

                                                        table.getDefaultCell().setColspan(1);
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cash - cheque - ccard)), pFontHeader1);

                                                        table.addCell(phrase);

                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cheques)), pFontHeader1);

                                                        table.addCell(phrase);

                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(efts)), pFontHeader1);

                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(mobile)), pFontHeader1);
                                                        tMobile =tMobile+mobile;
                                                        table.addCell(phrase);


                                                        //exemption
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cheque)), pFontHeader1);
                                                        tCheque = tCheque + cheque;
                                                        subcheque = subcheque + cheque;
                                                        table.addCell(phrase);

                                                        //waiver
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(ccard)), pFontHeader1);
                                                        tCcard = tCcard + ccard;
                                                        subccard = subccard + ccard;
                                                        table.addCell(phrase);

                                                        //refund
                                                        oth = rsetv.getDouble(1);
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(oth)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        tOth = tOth + oth;
                                                        suboth = suboth + oth;

                                                        table.getDefaultCell().setColspan(1);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        //totals = cash - oth - cheque - ccard + efts + cheques;

                                                        totals = cash + efts + cheques+mobile - cheque - ccard + oth;
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)), pFontHeader1);
                                                        table.addCell(phrase);

                                                        subTotal = subTotal + totals;
                                                        table.getDefaultCell().setColspan(1);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader1);
                                                        table.addCell(phrase);
                                                    }

                                                    }
                                                }
                                            }
                                            // }

                                            //}
                                            //}
                                        }
                                    }
                                }
                            }
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.TOP);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subcash)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subcheques)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subefts)), pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tMobile)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subcheque)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subccard)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(suboth)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(subTotal)), pFontHeader);

                            table.addCell(phrase);

                            gTotal = gTotal + subTotal;
                            //}

                            //}
                            //}
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Grand Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tCash)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tCheques)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tEfts)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tCheque)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tCcard)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tOth)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(gTotal)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(gTotal)), pFontHeader);
                            table.addCell(phrase);

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

             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    /* public java.lang.Object[] getDates() {

    java.lang.Object[] listofDates = null;

    java.util.Vector listofDatesVector = new java.util.Vector(1, 1);




    try {

    //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

    java.sql.Statement stmt1 = connectDB.createStatement();

    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT receipt_time FROM cash_analysis where receipt_time BETWEEN '" + beginDate + "' AND '" + endDate + "' ORDER BY 1");



    while (rSet1.next()) {

    listofDatesVector.addElement(rSet1.getObject(1).toString());



    }

    } catch (java.sql.SQLException sqlExec) {

    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());



    }

    listofDates = listofDatesVector.toArray();
    System.out.println("Done list of Staff Nos ...");


    return listofDates;


    }
     *
     */
    // public java.lang.Object[] getListofActivities(java.lang.Object tDates) {
    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);






        try {


            java.sql.Statement stmt1 = connectDB.createStatement();

            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ac.activity_code FROM ac_cash_collection ac, pb_activity pb where receipt_time::DATE = '" + tDates + "' AND transaction_type not ilike 'Bank%' AND pb.activity_category != 'IEDS' AND pb.activity_category != 'IEXE' AND ac.activity_code = pb.code ORDER BY activity_code");
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ac.activity_code FROM ac_cash_collection ac, pb_activity pb WHERE receipt_time::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "' AND transaction_type not ilike 'Bank%' AND pb.activity_category != 'IEDS' AND pb.activity_category != 'IEXE' AND ac.activity_code = pb.code ORDER BY activity_code");

            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT gl_account FROM pb_operating_parameters ac WHERE ac.category != 'IEDS' AND ac.category != 'IEXE' ORDER BY 1 ASC");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT code FROM pb_activity ac WHERE ( code IN (SELECT activity_code FROM ac_cash_collection where date BETWEEN '" + beginDate + "' AND '" + endDate + "') OR ac.activity_category ILIKE 'I' or ac.activity_category ILIKE 'B' OR ac.activity_category ILIKE 'PLID' OR ac.activity_category ILIKE 'PR') AND  ac.activity_category != 'IEDS' AND ac.activity_category != 'IEXE' ORDER BY 1 ASC");




            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());
                System.out.println(rSet1.getObject(1).toString());




            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());





        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");




        return listofActivities;


    }
}
