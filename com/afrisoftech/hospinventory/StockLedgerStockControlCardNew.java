//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import static com.afrisoftech.reports.FinalDescInPatientIntmlnvPdf.connectDB;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class StockLedgerStockControlCardNew implements java.lang.Runnable {

    java.lang.String storeName = null;
    java.lang.String itemCode = null;
    java.lang.String itemName = null;
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
    //  java.lang.String storeName2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font longTextFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font numberFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font header2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font header1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String ks;

    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void StockLedgerStockControlCardNew(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String iCode, java.lang.String sName, java.lang.String iName) {

        dbObject = new com.afrisoftech.lib.DBObject();

        itemName = iName;

        itemCode = iCode;

        storeName = sName;

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

            this.generatePdf22(storeName);

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

    public void generatePdf22(java.lang.String storeName) {
        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {
            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

            docPdf.open();

            java.util.Calendar calendar = java.util.Calendar.getInstance();

            long dateNow = calendar.getTimeInMillis();

            java.sql.Date datenowSql = new java.sql.Date(dateNow);

            System.out.println(datenowSql.toString());

            com.lowagie.text.pdf.PdfPTable letterHeadTable = new com.lowagie.text.pdf.PdfPTable(2);

            //int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};
            //detailsTable.setWidths(headerwidths);
            Phrase phrase = new Phrase();
            PdfPCell cell;//=new PdfPCell();

            letterHeadTable.setWidthPercentage((100));
            letterHeadTable.getDefaultCell().setBorder(Rectangle.BOX);
            letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
            letterHeadTable.getDefaultCell().setColspan(1);
            letterHeadTable.getDefaultCell().setFixedHeight(70);
            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            letterHeadTable.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
            letterHeadTable.getDefaultCell().setFixedHeight(16);
            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
            java.sql.ResultSet rset3 = st321.executeQuery();
            letterHeadTable.getDefaultCell().setBorder(Rectangle.BOX);
            letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
            while (rset3.next()) {
                letterHeadTable.getDefaultCell().setColspan(1);

                letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), header1);
                letterHeadTable.addCell(phrase);
            }

            letterHeadTable.getDefaultCell().setBorder(Rectangle.BOTTOM);

            letterHeadTable.getDefaultCell().setColspan(2);

            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            phrase = new Phrase("STORES LEDGER AND STOCK CONTROL CARD", header1);

            letterHeadTable.addCell(phrase);

            letterHeadTable.getDefaultCell().setColspan(1);
            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            phrase = new Phrase("ITEM CODE : " + itemCode.toUpperCase(), header1);

            letterHeadTable.addCell(phrase);
            letterHeadTable.getDefaultCell().setColspan(1);
            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            phrase = new Phrase("ITEM DESCRIPTION : " + itemName.toUpperCase(), header1);

            letterHeadTable.addCell(phrase);

            com.lowagie.text.pdf.PdfPTable detailsTable = new com.lowagie.text.pdf.PdfPTable(16);
            float widths[] = new float[]{1f, 1.1f,
                1.3f, 1f,
                1f, 1.1f,
                1f, 1f,
                1.1f, 1.2f,
                1.2f, 0.8f,
                0.8f, 0.8f,
                0.8f, 0.8f};
            //float widths[]=new float[]{1f, 1f, 1f ,1f ,1f ,1f ,  1f ,1f ,1f ,  1f ,1f ,0.8f ,0.8f,0.8f,0.8f,0.8f};
            detailsTable.setWidths(widths);
            detailsTable.setHeaderRows(2);
            detailsTable.setWidthPercentage(100);

            phrase = new Phrase("Date", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            phrase = new Phrase("Voucher No.", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            phrase = new Phrase("Supplier Or Requisitioning Office", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            phrase = new Phrase("Receipts", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            phrase = new Phrase("Issues", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            phrase = new Phrase("Balances", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(2);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            phrase = new Phrase("Consumption Period", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(5);
            cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            detailsTable.addCell(cell);

            //2nd header rows
            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Qty", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Invoice Unit Price", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Value", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Qty", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Average Unit Price", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Value", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Qty", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Value", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("Month", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("20..../", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("20..../", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase("20..../", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase("20..../", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            java.sql.Statement priceStat = connectDB.createStatement();
            java.sql.ResultSet priceSet = priceStat.executeQuery("SELECT buying_price/packaging FROM st_stock_item WHERE item_code ILIKE '" + itemCode + "'");
            double price = 0.00;

            while (priceSet.next()) {
                price = priceSet.getDouble(1);
            }

            java.sql.Statement openingBalStat = connectDB.createStatement();
            java.sql.ResultSet openingBalSet = openingBalStat.executeQuery("SELECT sum(receiving-issuing), sum(receiving), sum(issuing) FROM st_sub_stores "
                    + "WHERE item_code ILIKE '" + itemCode + "' AND store_name ILIKE '" + storeName + "' AND trans_date::date < '" + beginDate + "'");
            double openingBalance = 0.00, openingReceipts = 0.00, openingIssues = 0.00;

            while (openingBalSet.next()) {
                openingBalance = openingBalSet.getDouble(1);
                openingReceipts = openingBalSet.getDouble(2);
                openingIssues = openingBalSet.getDouble(3);

            }

            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("B/F", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(openingReceipts)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);
            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(price)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);
            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(openingReceipts * price)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);

            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(openingIssues)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);
            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(price)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);
            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(openingIssues * price)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);

            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(openingBalance)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);

            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(openingBalance * price)), header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);

            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase("");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            java.sql.Statement transactionsStat = connectDB.createStatement();
            java.sql.ResultSet transactionsSet = transactionsStat.executeQuery("SELECT * FROM st_sub_stores WHERE item_code ILIKE '" + itemCode + "' "
                    + "AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' "
                    + "AND store_name ILIKE '" + storeName + "' "
                    + "ORDER BY trans_date asc, transaction_no desc, issiued_to desc");

            double stockAdditions = 0.00;//receipts, reversal of issues etc
            double stockReductions = 0.00;//issues, reversal of grns etc
            double runningBalance = openingBalance;

            double additionsCumulated = 0.00, reductionsCumulated = 0.00;

            String transNo = null;
            String cardexTransNo = null;
            java.sql.Statement lpo_dnote_Stat = connectDB.createStatement();
            java.sql.ResultSet lpo_dnote_Set = null;
            String countOrDifference = null;

            while (transactionsSet.next()) {

                countOrDifference = transactionsSet.getString(14);
                System.err.println("count or difference is " + countOrDifference + " and the date is " + transactionsSet.getString(10));

                if (transactionsSet.getDouble(3) > 0) {
                    //date
                    phrase = new Phrase(transactionsSet.getString(10), normal);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    //voucher No
                    transNo = transactionsSet.getString(8);
                    lpo_dnote_Set = lpo_dnote_Stat.executeQuery("SELECT order_no,delivery_note_no FROM st_stock_cardex WHERE requisition_no='" + transNo + "' AND item_code='" + itemCode + "'");
                    if (transNo.contains("Stock Count")) {
                        phrase = new Phrase(transNo, longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    } else {
                        while (lpo_dnote_Set.next()) {
                            transNo += " LPO - " + lpo_dnote_Set.getString(1) + ", D.Note - " + lpo_dnote_Set.getString(2);
                        }
                        phrase = new Phrase(transNo, longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    }

                    //supplier or req office
                    if (countOrDifference.contains("Stock Count")) {
                        phrase = new Phrase(countOrDifference, longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    } else {
                        phrase = new Phrase(transactionsSet.getString(13), longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    }

                    transNo = null;

                    //receipt-qty
                    stockAdditions = transactionsSet.getDouble(3);
                    additionsCumulated += stockAdditions;
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(stockAdditions)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //receipt-price
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(transactionsSet.getDouble(5))), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //receipt-value
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(transactionsSet.getDouble(6))), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //receipts have nothing to do with the issues section of the report
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    //balance-qty
                    runningBalance += stockAdditions;
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //balance-value
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance * price)), numberFont);

                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //consumption period
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                } else if (transactionsSet.getDouble(4) > 0) {

                    //date
                    phrase = new Phrase(transactionsSet.getString(10), normal);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    //voucher No
                    if (countOrDifference.contains("Stock Difference")) {
                        phrase = new Phrase(transactionsSet.getString(8), longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    } else {
                        phrase = new Phrase(transactionsSet.getString(8) + " - " + dbObject.getDBObject(transactionsSet.getString(18), "-"), longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    }

                    //supplier or req office
                    if (countOrDifference.contains("Stock Difference")) {
                        phrase = new Phrase(countOrDifference, longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    } else {
                        phrase = new Phrase(transactionsSet.getString(13), longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    }

                    //receipts have nothing to do with issues
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    //issuing
                    stockReductions = transactionsSet.getDouble(4);
                    reductionsCumulated += stockReductions;
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(stockReductions)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //issuing price
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(transactionsSet.getDouble(5))), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //issuing value
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(transactionsSet.getDouble(6))), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //balance-qty
                    runningBalance -= stockReductions;
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //balance-value
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance * price)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //consumption period
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                }
                if (transactionsSet.getDouble(4) < 0) {//taken as receiving

                    //date
                    phrase = new Phrase(transactionsSet.getString(10), normal);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    //voucher No
                    transNo = transactionsSet.getString(8);
                    cardexTransNo = transNo;

                    phrase = new Phrase(transNo, longTextFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    transNo = null;
                    cardexTransNo = null;

                    //supplier or req office
                    if (countOrDifference.contains("Stock Difference")) {
                        phrase = new Phrase(countOrDifference, longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    } else {
                        phrase = new Phrase(transactionsSet.getString(13), longTextFont);
                        cell = new PdfPCell(phrase);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        detailsTable.addCell(cell);
                    }

                    //receipt-qty
                    stockAdditions = transactionsSet.getDouble(4) * -1;//cancelling the negative on this
                    additionsCumulated += stockAdditions;
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(stockAdditions)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //receipt-price
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(transactionsSet.getDouble(5))), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //receipt-value
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(transactionsSet.getDouble(6))), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //receipts have nothing to do with the issues section of the report
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase("");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                    //balance-qty
                    runningBalance += stockAdditions;
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance)), numberFont);
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //balance-value
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance * price)), numberFont);

                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    detailsTable.addCell(cell);

                    //consumption period
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);
                    phrase = new Phrase(" ");
                    cell = new PdfPCell(phrase);
                    cell.setColspan(1);
                    cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    detailsTable.addCell(cell);

                }

                countOrDifference = null;
            }

            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase("C/F", header2);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance)), numberFont);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);

            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(runningBalance * price)), numberFont);
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
            detailsTable.addCell(cell);

            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);
            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

            phrase = new Phrase(" ");
            cell = new PdfPCell(phrase);
            cell.setColspan(1);
            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            detailsTable.addCell(cell);

//            phrase = new Phrase("Running Additions ie GRNs, issue reversals etc "+new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(additionsCumulated)), header2);
//            cell=new PdfPCell(phrase);
//            cell.setColspan(8);
//            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            // detailsTable.addCell(cell);
//            phrase = new Phrase("Running Reductions ie issues, GRN reversals"+new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(reductionsCumulated)), header2);
//            cell=new PdfPCell(phrase);
//            cell.setColspan(8);
//            cell.setHorizontalAlignment(PdfCell.ALIGN_LEFT);
            // detailsTable.addCell(cell);
            docPdf.add(letterHeadTable);
            docPdf.add(detailsTable);

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.lang.Exception exec) {
            exec.printStackTrace();
        }

    }//pdf1

}
