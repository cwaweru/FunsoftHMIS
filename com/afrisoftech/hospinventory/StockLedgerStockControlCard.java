//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import static com.afrisoftech.reports.FinalDescInPatientIntmlnvPdf.connectDB;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class StockLedgerStockControlCard implements java.lang.Runnable {

    java.lang.String memNo = null;
    java.lang.String memNo1 = null;
    java.lang.String drugName = null;
    String district = null;
    double openingBal = 0.00;
    double closingBal = 0.00;
    double buyingPrice = 0.00;
    double price = 0.00;
    double receivingTotal = 0.00;
    double issuingTotal = 0.00;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    com.lowagie.text.HeaderFooter headerFoter;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String ks;

    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void StockLedgerStockControlCard(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox, java.lang.String combox1, java.lang.String drugname) {

        dbObject = new com.afrisoftech.lib.DBObject();

        drugName = drugname;

        memNo1 = combox;

        memNo = combox1;

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

            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            double osBalanceAmt = 0.00;
            double osBalanceQty = 0.00;
            double osBalanceQtybf = 0.00;
            double osBalanceAmtbf = 0.00;
            double ClosingQtyBalance = 0.00;
            double ClosingAmtBalance = 0.00;
            double osBalanceQty1 = 0.00;
            double osBalanceAmt1 = 0.00;
            double current = 0.00;
            double receiving = 0.00;
            double issuing = 0.00;

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

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};
                        try {
                            table1.setWidths(headerwidths);
                            //  if (docPdf.getPageNumber() > 1) {
                            //  table1.setHeaderRows(4);
                            //  }
                            Phrase phrase = new Phrase();
                            table1.setWidthPercentage((100));
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


                            table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table1.getDefaultCell().setColspan(7);

                            //       Phrase phrase = new Phrase();

                            //  table.addCell(phrase);

                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("STORES LEDGER AND STOCK CONTROL CARD", pFontHeader11);

                            table1.addCell(phrase);

                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("ITEM CODE : " + memNo1.toUpperCase(), pFontHeader11);

                            table1.addCell(phrase);
                            table1.getDefaultCell().setColspan(4);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("ITEM DESCRIPTION : " + drugName.toUpperCase(), pFontHeader11);

                            table1.addCell(phrase);



                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);



                            java.sql.Statement st2x = connectDB.createStatement();

                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            /* java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                             while (rset3.next()){
                             table1.getDefaultCell().setColspan(7);
                                
                             table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                             table1.addCell(phrase);
                             }
                             */
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

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};

                        table.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table.setWidthPercentage((100));

                        table.setHeaderRows(3);

                        table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(7);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(16);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();


                        int headerwidths2[] = {15, 10, 35, 15, 10, 20, 15, 10, 10, 15, 20, 10, 10, 10, 10, 10};


                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));



                        Phrase phrase = new Phrase();
                        Phrase phrase2 = new Phrase();
                        //  table.addCell(phrase);


                        try {
                            double price = 0.00;
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st111 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st1q = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st41 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            // java.sql.ResultSet rset1 = st1.executeQuery("select date_prescribed,patient_no ,patient_name,pay_mode,quantity,price,amount from hp_pharmacy where description ilike '"+memNo1+"' and main_service ilike '"+memNo+"%' AND date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_prescribed");
                            // java.sql.ResultSet rset1 = st1.executeQuery("select trans_date::date,'' ,issiued_to,receiving,issuing,price,total from st_sub_stores where item||' '||strength ilike '"+memNo1+"' and store_name ilike '"+memNo+"%' AND trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by trans_date");

                            //            java.sql.ResultSet rset1 = st1.executeQuery("select trans_date::date,transaction_no ,CASE WHEN (transaction_no ilike 'T%')THEN initcap(sub_store) ELSE initcap(issiued_to) END as issiued_to,receiving,issuing,price,total from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND issuing > 0 ORDER BY trans_date");
                            java.sql.ResultSet rset1q = st1q.executeQuery("select sum(receiving) from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                            //            java.sql.ResultSet rset1q1 = st5.executeQuery("select date,invoice_no,supplier,sum(received),sum(price),sum(total) from stores_ledger where item_code ilike '" + memNo1 + "' AND store ILIKE '" + memNo + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND transaction_type ILIKE 'receiving' GROUP BY 1,2,3");

                            java.sql.ResultSet rset4 = st4.executeQuery("select buying_price/packaging from st_stock_item where item_code ilike '" + memNo1 + "'");
                            java.sql.ResultSet rset11 = st11.executeQuery("select sum(receiving-issuing) from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date <= '" + beginDate + "'::date - 1");
                            //java.sql.ResultSet rset111 = st111.executeQuery("select sum(issuing),sum(total) from st_sub_stores where item ilike '"+memNo1+"' and sub_store ilike '"+memNo+"%' AND trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase2 = new Phrase("ITEM CODE : " + memNo1.toUpperCase(), pFontHeader1);
                            table.addCell(phrase2);

                            table.getDefaultCell().setColspan(2);

                            //   phrase2 = new Phrase("No : 777157", pFontHeader11);
                            phrase2 = new Phrase("No : -", pFontHeader11);
                            table.addCell(phrase2);
                            table.getDefaultCell().setColspan(5);

                            phrase2 = new Phrase("DESCRIPTION : " + drugName.toUpperCase(), pFontHeader1);
                            table.addCell(phrase2);
                            table.getDefaultCell().setColspan(2);

                            phrase2 = new Phrase("UNIT OF ISSUE : ", pFontHeader1);
                            table.addCell(phrase2);

                            table.getDefaultCell().setColspan(2);

                            phrase2 = new Phrase("FACILITY CODE : ", pFontHeader1);
                            table.addCell(phrase2);

                            table.getDefaultCell().setColspan(3);

                            phrase2 = new Phrase("DEPT./BRANCH : " + memNo.toUpperCase(), pFontHeader1);
                            table.addCell(phrase2);

                            table.getDefaultCell().setColspan(2);

                            phrase2 = new Phrase("LOCATION : ", pFontHeader1);

                            table.addCell(phrase2);

                            while (rset1q.next()) {
                                openingBal = rset1q.getDouble(1);
                            }

                            while (rset11.next()) {
                                closingBal = rset11.getDouble(1);
                            }

                            while (rset4.next()) {
                                price = rset4.getDouble(1);
                            }

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            // table2.getDefaultCell().setBorderWidth(Rectangle.TOP);

                            table2.setHeaderRows(2);
                            table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase("Date", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Voucher Number", pFontHeader1);
                            table2.addCell(phrase);
                            //table.getDefaultCell().setColspan(2);

                            phrase = new Phrase("Supplier or Requisitioning Office", pFontHeader1);
                            table2.addCell(phrase);

                            //table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table2.getDefaultCell().setColspan(3);

                            phrase = new Phrase("RECEIPTS", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("ISSUES", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("BALANCES", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(5);
                            phrase = new Phrase("CONSUMPTION RECORD", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Qty. ", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Invoice Unit Price", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Value", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Qty", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Average Unit Price", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Value", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Qty.", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Value", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Month", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("20..../", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("20..../", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("20..../", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("20..../", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("B/F ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            // table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader1);
                            table2.addCell(phrase);

                            //table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                            table2.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement vouchersEtc=connectDB.createStatement();
                            java.sql.ResultSet vouchersSet=null;
                            String voucher=null;
                            
                            for (int i = 0; i < listofStaffNos.length; i++) {
                                //           java.sql.ResultSet rset1 = st1.executeQuery("select trans_date::date,transaction_no ,CASE WHEN (transaction_no ilike 'T%')THEN initcap(sub_store ||' '||issiued_to) ELSE initcap(issiued_to) END as issiued_to,receiving,issuing,price,total from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date = '" + listofStaffNos[i] + "'  ORDER BY trans_date");
                                java.sql.ResultSet issuesSet = st1.executeQuery("select trans_date::date,transaction_no||' - '||manual_transfer_no ,CASE WHEN (transaction_no ilike 'T%')THEN initcap(sub_store ||' '||issiued_to) ELSE initcap(issiued_to) END as issiued_to,receiving,issuing,price,total,manual_transfer_no from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date = '" + listofStaffNos[i] + "' AND issuing > 0 ORDER BY trans_date");
                                ////           java.sql.ResultSet rset1q1 = st5.executeQuery("select date,invoice_no,supplier,sum(quantity_received),sum(price_per_item),sum(debit) from st_stock_cardex where item_code ilike '" + memNo1 + "' AND store ILIKE '" + memNo + "' AND date::date = '" + listofStaffNos[i] + "' AND transaction_type ILIKE 'receiving' GROUP BY 1,2,3"
                                ////                   + "UNION select trans_date::date as date,transaction_no as invoice_no ,CASE WHEN (transaction_no ilike 'Stock count%')THEN initcap(sub_store ||' '||issiued_to) ELSE initcap(issiued_to) END as issiued_to, sum(receiving), sum(price),sum(total) from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date = '" + listofStaffNos[i] + "' AND receiving > 0 GROUP BY 1,2,3");
                                //           java.sql.ResultSet rset1q1 = st5.executeQuery("select date,invoice_no,supplier,sum(quantity_received),sum(price_per_item),sum(debit) from st_stock_cardex where item_code ilike '" + memNo1 + "' AND store ILIKE '" + memNo + "' AND date::date = '" + listofStaffNos[i] + "' AND transaction_type ILIKE 'receiving' GROUP BY 1,2,3");
                                java.sql.ResultSet receiptsSet = st5.executeQuery("select trans_date::date as date,transaction_no as invoice_no ,CASE WHEN (transaction_no ilike 'Stock count%')THEN initcap(sub_store ||' '||issiued_to) ELSE initcap(issiued_to) END as issiued_to, sum(receiving), sum(price),sum(total) ,manual_transfer_no from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date = '" + listofStaffNos[i] + "' AND receiving > 0 GROUP BY 1,2,3,7");
                                while (receiptsSet.next()) {
                                    table2.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(receiptsSet.getObject(1), "-"), pFontHeader);

                                    table2.addCell(phrase);
                                    table2.getDefaultCell().setColspan(1);

                                    vouchersSet=vouchersEtc.executeQuery("select 'LPO No '||order_no||', GRN No. '||grn_no FROM st_stock_cardex where requisition_no='"+receiptsSet.getString(1)+"'");
                                    System.out.println("select 'LPO No '||order_no||', GRN No. '||grn_no FROM st_stock_cardex where requisition_no='"+receiptsSet.getString(2)+"'");
                                    while(vouchersSet.next()){
                                        voucher=vouchersSet.getString(1);
                                    }
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(receiptsSet.getObject(2)+" "+dbObject.getDBObject(voucher,"-"), "-"), pFontHeader);

                                    table2.addCell(phrase);
                                    table2.getDefaultCell().setColspan(1);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(receiptsSet.getObject(3), "-"), pFontHeader);
                                    table2.addCell(phrase);

                                    table2.getDefaultCell().setColspan(1);

                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(receiptsSet.getObject(4), "-"), pFontHeader);

                                    table2.addCell(phrase);
                                    receiving = receiptsSet.getDouble(4);

                                    receivingTotal = receivingTotal + receiptsSet.getDouble(4);
                                    closingBal = closingBal + receiptsSet.getDouble(4);
                                    table2.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(receiptsSet.getString(5)), pFontHeader);
                                    issuing = receiptsSet.getDouble(5);
                                    issuingTotal = issuingTotal + receiptsSet.getDouble(5);

                                    table2.addCell(phrase);
                                    //closingBal = closingBal + (receiving - issuing);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(receiptsSet.getString(6)), pFontHeader);

                                    table2.addCell(phrase);

                                    table2.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader);

                                    table2.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);


                                    //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                                    // table2.addCell(phrase);

                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)),pFontHeader);
                                    // osBalanceAmt1 = osBalanceAmt1 + rset1.getDouble(7);
                                    //  table.addCell(phrase);

                                }
                                while (issuesSet.next()) {
                                    table2.getDefaultCell().setColspan(1);
                                    //table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(issuesSet.getObject(1), "-"), pFontHeader);

                                    table2.addCell(phrase);
                                    table2.getDefaultCell().setColspan(1);

                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(issuesSet.getObject(2), "-"), pFontHeader);

                                    table2.addCell(phrase);
                                    table2.getDefaultCell().setColspan(1);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(issuesSet.getObject(3), "-"), pFontHeader);
                                    table2.addCell(phrase);

                                    table2.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(issuesSet.getObject(4), "-"), pFontHeader);

                                    //table2.addCell(phrase);
                                    receiving = issuesSet.getDouble(4);

                                    receivingTotal = receivingTotal + issuesSet.getDouble(4);

                                    table2.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(issuesSet.getString(5)), pFontHeader);
                                    issuing = issuesSet.getDouble(5);
                                    closingBal = closingBal - issuing;
                                    table2.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(issuesSet.getString(6)), pFontHeader);
                                    issuingTotal = issuingTotal + issuesSet.getDouble(5);

                                    table2.addCell(phrase);
                                    //closingBal = closingBal + (receiving - issuing);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(issuesSet.getString(7)), pFontHeader);
                                    table2.addCell(phrase);
                                    //issuing = rset1.getDouble(5);

                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                                    // table2.addCell(phrase);

                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)),pFontHeader);
                                    // osBalanceAmt1 = osBalanceAmt1 + rset1.getDouble(7);
                                    //  table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader);

                                    table2.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table2.addCell(phrase);

                                }
                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("C/F ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            // table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader1);
                            table2.addCell(phrase);

                            //table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                            table2.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);


                            docPdf.add(table);

                            docPdf.add(table2);

                            //       docPdf.add(table);


                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            SqlExec.printStackTrace();
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
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            IOexec.printStackTrace();
        }
    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("select DISTINCT date from store_ledger WHERE item_code ilike '" + memNo1 + "' AND store ILIKE '" + memNo + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");

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
    /*private Object[] getListofStaffNos() {
     return null;
     }*/
}
