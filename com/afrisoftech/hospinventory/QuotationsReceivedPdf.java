//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class QuotationsReceivedPdf implements java.lang.Runnable {

    int cnt = 0;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String selectSupp = null;
    java.lang.String OrderNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    PdfWriter pdfWriter;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader51 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void QuotationsReceivedPdf(java.sql.Connection connDb, java.lang.String order, java.lang.String vendor) {
        //  public void OrdersPdf() {
        selectSupp = vendor;

        OrderNo = order;

        connectDB = connDb;
        // beginDate = begindate;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        // public static void main() {
        //	new OrdersPdf().OrdersPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf(selectSupp);

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

            //        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4,40,40,40,40);

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            try {

                try {
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    // pdfWriter = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    // System.out.println("Current Doc size 1 "+ pdfWriter.getCurrentDocumentSize());


                    String compName = null;
                    String date = null;

                    /*  try {


                    java.sql.Statement st6 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();

                    //com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Internal Requisition - Page:", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    // docPdf.setFooter(footer);
                    } catch (java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }*/

                    docPdf.open();

                    String Username = null;
                    int numColumns = 9;

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));


                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table1.getDefaultCell().setColspan(1);
                        //  table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //  table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(6);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                //table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(10);
                        table1.getDefaultCell().setPadding(3);

                        int headerwidths1[] = {5, 10, 30, 8, 8, 10, 8, 12, 10, 10};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        com.lowagie.text.pdf.PdfPTable table3 = new com.lowagie.text.pdf.PdfPTable(10);

                        int headerwidths3[] = {5, 7, 35, 7, 8, 10, 8, 9, 10, 13};

                        table3.setWidths(headerwidths3);

                        table3.setWidthPercentage((100));

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(4);
                        table2.getDefaultCell().setPadding(3);

                        int headerwidths2[] = {25, 35, 25, 25};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));



                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase("", pFontHeader);





                        try {
                            String quoteNo = null;
                            String quotDate = null;
                            String openDate = null;
                            String buyerDet = null;
                            String supplier = null;
                            String itemDet = null;


                            java.sql.Statement st31 = connectDB.createStatement();
                            java.sql.ResultSet rset31 = st31.executeQuery("SELECT DISTINCT sub_item_desc FROM st_item_to_quote WHERE quotation_no = '" + OrderNo + "' LIMIT 1 ");

                            while (rset31.next()) {
                                itemDet = rset31.getString(1);
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT quotation_no,quotation_date,openning_date,buyer_details,supplier FROM st_floated_quotations WHERE quotation_no = '" + OrderNo + "' AND supplier = '" + selectSupp + "' LIMIT 1 ");

                            while (rset3.next()) {

                                quoteNo = rset3.getString(1);
                                quotDate = rset3.getString(2);
                                openDate = rset3.getString(3);
                                buyerDet = rset3.getString(4);
                                supplier = rset3.getString(5);

                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("REQUEST FOR QUOTATION", pFontHeader1);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(6);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("To:", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(4);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("From:", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(6);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Seller's Name and Address:", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(4);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Buyer's Designation and Address:", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(this.selectSupp, pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Quotation No: " + quoteNo + "\n Date " + quotDate, pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(4);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(buyerDet, pFontHeader5);
                                table1.addCell(phrase);


                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("You are invited to submit quotation on materials listed below", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Notes", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("(a) THIS IS NOT AN ORDER. Read the conditions and instructions on reverse before quoting.", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("(c) Your quotation should indicate final unit price which includes all costs for", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("(b) This quotation should be submitted in a plain wax sealed envelope marked Quotation No." + quoteNo, pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("delivery, discount, duty and sales tax.", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(2);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("for supply of ", pFontHeader5);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(itemDet, pFontHeader51);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(5);
                                phrase = new Phrase("(d) Return the original copy and retain the duplicate for your record.", pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("and be addressed to reach the buyer or be placed in the Quotation/Tender box not later than", pFontHeader5);
                                table1.addCell(phrase);
                                //table1.getDefaultCell().setColspan(5);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("9:30 a.m. on : " + openDate, pFontHeader5);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader5);
                                table1.addCell(phrase);

                            }

                            table3.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table3.getDefaultCell().setColspan(1);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("No ", pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("Code No ".toUpperCase(), pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("Item Description ".toUpperCase(), pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("UNIT", pFontHeader);
                            table3.addCell(phrase);

                            phrase = new Phrase("QUANTITY REQUIRED", pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("UNIT PRICE ", pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("DAY'S TO DELIVERY ", pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("BRAND", pFontHeader);
                            table3.addCell(phrase);

                            phrase = new Phrase("COUNTRY OF ORIGIN ", pFontHeader);
                            table3.addCell(phrase);
                            phrase = new Phrase("REMARKS", pFontHeader);
                            table3.addCell(phrase);




                            //java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            //java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address,tel1,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name  ilike '"+selectSupp+"'");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT item_number, item_code, item_description,units,quantity,unit_price, "
                                    + " days_to_deliver, brand, country_origin, remarks FROM st_floated_quotations WHERE "
                                    + "quotation_no = '" + OrderNo + "' AND supplier = '" + selectSupp + "' ");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                          /* java.sql.ResultSet rset5 = st5.executeQuery("select DISTINCT date_due from st_receive_requisation where REQUISITION_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset11 = st11.executeQuery("select initcap(user_name) from st_receive_requisation where REQUISITION_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            while (rset11.next()) {
                            Username = rset11.getObject(1).toString();
                            }
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                             */
                            while (rset4.next()) {

                                table3.getDefaultCell().setColspan(1);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset4.getString(1), pFontHeader5);
                                table3.addCell(phrase);



                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset4.getString(2), pFontHeader5);
                                table3.addCell(phrase);

                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset4.getString(3), pFontHeader5);
                                table3.addCell(phrase);


                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(rset4.getObject(4).toString(), pFontHeader5);
                                table3.addCell(phrase);


                                //table1.getDefaultCell().setColspan(7);
                                // table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_);
                                phrase = new Phrase(rset4.getObject(5).toString(), pFontHeader5);
                                table3.addCell(phrase);


                                // table1.getDefaultCell().setColspan(3);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset4.getObject(6).toString(), pFontHeader5);
                                table3.addCell(phrase);

                                phrase = new Phrase(rset4.getObject(7).toString(), pFontHeader5);
                                table3.addCell(phrase);
                                phrase = new Phrase(rset4.getObject(8).toString(), pFontHeader5);
                                table3.addCell(phrase);
                                phrase = new Phrase(rset4.getObject(9).toString(), pFontHeader5);
                                table3.addCell(phrase);
                                phrase = new Phrase(rset4.getObject(10).toString(), pFontHeader5);
                                table3.addCell(phrase);

                            }

                            table3.getDefaultCell().setBorder(Rectangle.TOP);
                            table3.getDefaultCell().setColspan(10);
                            table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            table3.addCell(phrase);

                            table1.getDefaultCell().setFixedHeight(200);

                            table1.getDefaultCell().setColspan(10);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            // phrase = new Phrase("FOR OFFICIAL USE ONLY", pFontHeader);
                            table1.addCell(table3);


                            table2.getDefaultCell().setColspan(4);
                            table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("FOR OFFICIAL USE ONLY", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setFixedHeight(20);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader);
                            //table2.addCell(phrase);
                            phrase = new Phrase("Opened by:(1).............................................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Designation:.............................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Signature..................................................", pFontHeader);
                            table2.addCell(phrase);

                            phrase = new Phrase("Seller's Signature ....................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader);
                            // table2.addCell(phrase);
                            phrase = new Phrase("                   (2).............................................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Designation:.............................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Signature..................................................", pFontHeader);
                            table2.addCell(phrase);

                            phrase = new Phrase("Date...........................................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader);
                            //table2.addCell(phrase);
                            phrase = new Phrase("                   (3).............................................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Designation:.............................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Signature..................................................", pFontHeader);
                            table2.addCell(phrase);


                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader);
                            // table2.addCell(phrase);
                            phrase = new Phrase("                   Date..........................................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Time:.........................................................", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            docPdf.add(table1);
                            docPdf.add(table2);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    /*double Total = 0.00;
                    double discount = 0.00;
                    double vat = 0.00;
                    double qty = 0.00;
                    double price = 0.00;
                    double value = 0.00;

                    try {

                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                    table.getDefaultCell().setPadding(3);

                    int headerwidths[] = {8, 40, 14, 10, 14, 14};

                    table.setWidths(headerwidths);

                    table.setWidthPercentage((100));
                    table.getDefaultCell().setBorderColor(java.awt.Color.black);
                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                    table.getDefaultCell().setColspan(6);
                    Phrase phrase = new Phrase(" ", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("No. ", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);

                    phrase = new Phrase("Description ", pFontHeader);
                    table.addCell(phrase);
                    // table.getDefaultCell().setColspan(2);

                    phrase = new Phrase("Qty Reqd. ", pFontHeader);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);

                    phrase = new Phrase("Units. ", pFontHeader);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);

                    phrase = new Phrase("Price ", pFontHeader);
                    table.addCell(phrase);

                    phrase = new Phrase("Value ", pFontHeader);
                    table.addCell(phrase);

                    table.setHeaderRows(2);

                    System.out.println("First ");
                    System.out.println("First Bottom " + docPdf.bottom());


                    table.getDefaultCell().setColspan(1);

                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                    //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);



                    try {
                    /*
                    // java.sql.Statement st6 = connectDB.createStatement();
                    java.sql.Statement st1 = connectDB.createStatement();

                    java.sql.ResultSet rset1 = st1.executeQuery("select initcap(item_description),quantity,units,price,round(quantity*price,2),'0.00','0.00','0.00','0.00','0.00','1',reason from st_receive_requisation where requisition_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");

                    table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);


                    while (rset1.next()) {

                    // value = qty * price;

                    cnt = cnt + 1;
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("" + cnt, pFontHeader2);
                    table.getDefaultCell().setBorderColor(java.awt.Color.black);

                    // table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                    table.addCell(phrase);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader2);

                    table.addCell(phrase);
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader2);

                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader2);
                    table.addCell(phrase);

                    // System.out.println("Second "+docPdf.top());
                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)), pFontHeader2);
                    //phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader2);

                    table.addCell(phrase);


                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader2);

                    value = value + rset1.getDouble(5);
                    table.addCell(phrase);

                    }

                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);


                    table.getDefaultCell().setColspan(5);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                    phrase = new Phrase("Total", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(value)), pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(6);
                    table.getDefaultCell().setBorder(Rectangle.TOP);
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);
                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                    phrase = new Phrase(" ", pFontHeader);


                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("             ", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Comments : ", pFontHeader);

                    table.addCell(phrase);


                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(rset1.getObject(12).toString(), pFontHeader2);

                    table.addCell(phrase);



                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);*/

                    /*


                    table.getDefaultCell().setColspan(6);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Prepared By :" +Username.toUpperCase(), pFontHeader);

                    table.addCell(phrase);
                     */
                    /*
                    table.getDefaultCell().setColspan(2);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Prepared By : " + Username, pFontHeader);

                    table.addCell(phrase);



                    table.getDefaultCell().setColspan(4);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Checked By ______________________________", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(" ", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(4);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase("(STORES)", pFontHeader);

                    table.addCell(phrase);
                     */
                    /*
                    table.getDefaultCell().setColspan(6);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Confirmed By:________________________ ", pFontHeader);

                    table.addCell(phrase);



                    table.getDefaultCell().setColspan(4);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Authorised By ____________________________", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("PROCUREMENT", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(4);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase("C.O.O / C.E.O ", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(6);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Budget Checked  By ____________________", pFontHeader);

                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(3);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("CHIEF ACCOUNTANT", pFontHeader);

                    table.addCell(phrase);


                    table.getDefaultCell().setColspan(3);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    phrase = new Phrase(" ", pFontHeader);

                    table.addCell(phrase);
                     */

                    /*
                    table.getDefaultCell().setColspan(6);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                    phrase = new Phrase("Authorised By :____________________________", pFontHeader);

                    table.addCell(phrase);*/

                    // docPdf.add(table);

                    /*     } catch (java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }*

                    // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }*/

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }
//            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            /* try {

            if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {

            System.out.println(tempFile);

            wait_for_Pdf2Show = rt.exec("kghostview " + tempFile + "");

            wait_for_Pdf2Show.waitFor();

            } else {

            wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe " + tempFile);

            wait_for_Pdf2Show.waitFor();

            }

            } catch (java.lang.InterruptedException intrExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());

            }

             */

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
