//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import static com.afrisoftech.hospinventory.StockLedgerStockControlCard.connectDB;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class DrugFlowPdf implements java.lang.Runnable {

    java.lang.String memNo = null;
    java.lang.String memNo1 = null;
    java.lang.String drugName = null;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String ks;

    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void DrugFlowPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox, java.lang.String combox1, java.lang.String drugname) {

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

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("*Key: For issues:Transaction_no/IRQ No ; For Receipts: Transaction No/DNote* \n  Item movement report- Page: ", pFontHeader1), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);
                    
                    docPdf.open();

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                        //com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        int headerwidths[] = {15, 15, 25, 15, 15, 15, 15, 20};
                        try {
                            table1.setWidths(headerwidths);
                            //  if (docPdf.getPageNumber() > 1) {
                            //  table1.setHeaderRows(4);
                            //  }
                            Phrase phrase = new Phrase();
                            table1.setWidthPercentage((100));
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setColspan(3);
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

                            table1.getDefaultCell().setColspan(8);

                            //       Phrase phrase = new Phrase();
                            //  table.addCell(phrase);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("STOCK ITEM MOVEMENT REPORT", pFontHeader11);

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

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();
                        //int headerwidths[] = {15, 15, 35, 20, 15, 20, 20, 15}; ORIGINAL table widths
                        int headerwidths[] = {15, 15, 30, 20, 15, 20, 20, 20};

                        table.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        table.setHeaderRows(4);
                        //  }
                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(8);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

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
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            // java.sql.ResultSet rset1 = st1.executeQuery("select date_prescribed,patient_no ,patient_name,pay_mode,quantity,price,amount from hp_pharmacy where description ilike '"+memNo1+"' and main_service ilike '"+memNo+"%' AND date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_prescribed");
                            // java.sql.ResultSet rset1 = st1.executeQuery("select trans_date::date,'' ,issiued_to,receiving,issuing,price,total from st_sub_stores where item||' '||strength ilike '"+memNo1+"' and store_name ilike '"+memNo+"%' AND trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by trans_date");
//Issiuingintfr
                            //java.sql.ResultSet rset1 = st1.executeQuery("select trans_date::date,'' ,CASE WHEN (transaction_no ilike 'T%')THEN initcap(sub_store) ELSE initcap(issiued_to) END as issiued_to,receiving,issuing,price,total,user_name from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by trans_date");

                            //java.sql.ResultSet rset1 = st1.executeQuery("select trans_date,'' ,CASE WHEN (transaction_no ilike 'T%')THEN initcap(sub_store) ELSE initcap(issiued_to) END as issiued_to,receiving,issuing,price,total,user_name from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by 1");
                            java.sql.ResultSet rset1 = st1.executeQuery("select trans_date::date,'' ,CASE WHEN (transaction_no ilike 'T%')THEN initcap(sub_store) ELSE initcap(issiued_to) END as issiued_to,receiving,issuing,price,total,user_name,CASE WHEN (transaction_no NOT LIKE 'Stock%')THEN upper(transaction_no) ELSE '-' END as transaction_no,CASE WHEN (manual_transfer_no IS NOT NULL)THEN upper(manual_transfer_no) ELSE '-' END as manual_transfer_no from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by 1");
                            java.sql.ResultSet rset1q = st1q.executeQuery("select sum(receiving) from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select buying_price/packaging from st_stock_item where item_code ilike '" + memNo1 + "'");
                            java.sql.ResultSet rset11 = st11.executeQuery("select sum(receiving-issuing) from st_sub_stores where item_code ilike '" + memNo1 + "' and store_name ilike '" + memNo + "' AND trans_date::date < '" + beginDate + "'");
                            //java.sql.ResultSet rset111 = st111.executeQuery("select sum(issuing),sum(total) from st_sub_stores where item ilike '"+memNo1+"' and sub_store ilike '"+memNo+"%' AND trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Store : " + memNo, pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Item Name : " + drugName.toUpperCase(), pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Item Code : " + memNo1.toUpperCase(), pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);

                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);
                                phrase = new Phrase("Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader1);

                                table.addCell(phrase);

                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date " + datenowSql, pFontHeader1);
                            table.addCell(phrase);
                            while (rset1q.next()) {
                                openingBal = rset1q.getDouble(1);
                            }

                            while (rset11.next()) {
                                closingBal = rset11.getDouble(1);
                            }

                            while (rset4.next()) {
                                price = rset4.getDouble(1);
                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase("Date", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);

                            phrase = new Phrase("Received From/Issued To", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase("In", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Out", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Balance", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Value", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Transacted By", pFontHeader1);
                            table.addCell(phrase);

                            //
                            table.getDefaultCell().setColspan(5);
                            //  phrase = new Phrase("Amount ",pFontHeader1);
                            //  table.addCell(phrase);
                            phrase = new Phrase("Opening Stock ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                            table.addCell(phrase);

                            table.addCell(new Phrase(""));

                            while (rset1.next()) {
                                System.err.println(rset1.getObject(2) + "[" + rset1.getObject(9) + "/" + rset1.getObject(10) + "]");

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-") + " \n[" + rset1.getObject(9) + "/" + rset1.getObject(10) + "]", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);

                                table.addCell(phrase);
                                receiving = rset1.getDouble(4);

                                receivingTotal = receivingTotal + rset1.getDouble(4);

                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader);
                                issuing = rset1.getDouble(5);
                                issuingTotal = issuingTotal + rset1.getDouble(5);

                                table.addCell(phrase);
                                closingBal = closingBal + (receiving - issuing);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader);

                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(8), "-"), pFontHeader);
                                table.addCell(phrase);

                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)),pFontHeader);
                                // osBalanceAmt1 = osBalanceAmt1 + rset1.getDouble(7);
                                //  table.addCell(phrase);
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            //phrase = new Phrase(" ");
                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Closing Stock", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(receivingTotal)), pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(issuingTotal)), pFontHeader);

                            table.addCell(phrase);

                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalanceQtybf + osBalanceQty) - osBalanceQty1)),pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal)), pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closingBal * price)), pFontHeader);

                            table.addCell(phrase);

                            table.addCell(new Phrase(""));

                            table.getDefaultCell().setColspan(3);

                            /*  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             phrase = new Phrase("Total", pFontHeader);
                           
                             table.addCell(phrase);
                           
                             table.getDefaultCell().setColspan(2);
                           
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                           
                             phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceQty1)),pFontHeader);
                           
                             table.addCell(phrase);
                           
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                           
                             phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceAmt1)),pFontHeader);
                           
                             */
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

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
        }
    }
    /*   public java.lang.Object[] getListofStaffNos() {
  
     java.lang.Object[] listofStaffNos = null;
  
     java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
  
  
     try {
  
     //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
  
     java.sql.Statement stmt1 = connectDB.createStatement();
  
     java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT admission_no FROM ac_debtors WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND invoice_no IS NOT NULL and admission_no IS NOT NULL");
  
     while (rSet1.next()) {
  
     listStaffNoVector.addElement(rSet1.getObject(1).toString());
  
     }
  
     }catch (java.sql.SQLException sqlExec) {
  
     javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
  
     }
  
     listofStaffNos = listStaffNoVector.toArray();
     System.out.println("Done list of Staff Nos ...");
     return listofStaffNos;
     }
  
     */

    private Object[] getListofStaffNos() {
        return null;
    }
}
