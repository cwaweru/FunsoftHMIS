//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;


import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class LpoPdf implements java.lang.Runnable {

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
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader31 = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String ddate;

    public void LpoPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String order) {
        //  public void OrdersPdf() {
        selectSupp = combox;

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

            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            try {

                try {
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    // pdfWriter = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    // System.out.println("Current Doc size 1 "+ pdfWriter.getCurrentDocumentSize());


                    String compName = null;
                    String date = null;

                    try {


                        java.sql.Statement st6 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Purchase Order - Page:", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        docPdf.setFooter(footer);
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

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
                                // table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(9);
                        table1.getDefaultCell().setPadding(3);

                        int headerwidths1[] = {24, 10, 10, 10, 6, 10, 6, 10, 14};

                        //  table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        double Total = 0.00;
                        double discount = 0.00;
                        double vat = 0.00;
                        int coNo = 0;
                        //try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        table.getDefaultCell().setPadding(3);

                        int headerwidths[] = {7, 10, 30, 7, 10, 10, 13, 15};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));
                        table.setHeaderRows(2);

                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase("", pFontHeader);




                        try {

                            // java.sql.Statement st3 = conDB.createStatement();

                            //  java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/purchase","postgres","pilsiner");
                            //  java.sql.Statement st3 = conDb.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            String vote_class = null;
                            String vote = null;
                            String head = null;
                            String sub_head = null;
                            String itemDet = null;
                            String depart = null;
                            String box = null;

                            // java.sql.Statement st3 = conDB.createStatement();

                            //  java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/purchase","postgres","pilsiner");
                            //  java.sql.Statement st3 = conDb.createStatement();
                            java.sql.Statement st32 = connectDB.createStatement();

                            //  java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno||' '||other_telno,initcap(street),initcap(town),main_faxno,email,initcap(building_name),room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address,tel1,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name  ilike '" + selectSupp + "'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select DISTINCT order_no,date,quotation_no,date_due,ordering_store FROM st_orders where order_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("SELECT DISTINCT organisation_name,box_no  FROM pb_hospitalprofile");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset1 = st1.executeQuery("select current_user");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            while (rset1.next()) {
                                Username = rset1.getObject(1).toString();
                            }
                            while (rset5.next()) {
                                depart = rset5.getObject(1).toString();
                                box = rset5.getObject(2).toString();
                            }

                            while (rset4.next()) {


                                java.sql.ResultSet rset32 = st32.executeQuery("SELECT DISTINCT vote_class, vote, head, subhead, sub_item, sub_item_desc, donor_code,"
                                        + "donor_item FROM st_item_to_quote WHERE quotation_no = '" + rset4.getString(3) + "' LIMIT 1 ");


                                while (rset2.next()) {

                                    while (rset32.next()) {

                                        vote_class = rset32.getString(1);
                                        vote = rset32.getString(2);
                                        head = rset32.getString(3);
                                        sub_head = rset32.getString(4);
                                        itemDet = rset32.getString(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table1.getDefaultCell().setColspan(3);
                                        phrase = new Phrase("ORIGINAL", pFontHeader31);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table1.getDefaultCell().setColspan(3);
                                        phrase = new Phrase("REPUBLIC OF KENYA", pFontHeader31);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        table1.getDefaultCell().setColspan(3);
                                        phrase = new Phrase("LSO No : " + rset4.getObject(1).toString(), pFontHeader31);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("LOCAL PURCHASE ORDER", pFontHeader4);
                                        table1.addCell(phrase);
                                        table1.getDefaultCell().setBorderColor(java.awt.Color.white);


                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Suppliers are warned that this Order is INVALID unless availability of funds is confirmed here below by \n the accountant I/C VBC.", pFontHeader);
                                        table1.addCell(phrase);
                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("To : " + rset2.getObject(1).toString(), pFontHeader31);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);
                                        phrase = new Phrase("TENDER/QUOTATION REF.NO. : " + rset4.getObject(3).toString() + "\n CONTRACT REF.NO. : \n DATE : \n REQUISITION NO. : \n DATE : ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Please deliver the goods listed here below to (full address) ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset4.getObject(5).toString(), pFontHeader31);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("on terms and conditions stated on the back of this Order, on or before : " + rset4.getObject(4).toString(), pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("and send the invoices immediately to Department/Ministry : " + depart, pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(" ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("P.O. Box : " + box, pFontHeader);
                                        table1.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("Item No. ", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(4);

                                        // table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Description of Goods", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);

                                        // table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Quantity ", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("Unit Cost ", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("Total", pFontHeader);
                                        table.addCell(phrase);


                                        // table.getDefaultCell().setBottom(1);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.black);

                                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                                        // table.getDefaultCell().setColspan(8);

                                        //Phrase phrase = new Phrase(" ", pFontHeader);
                                        //table.addCell(phrase);


                                        table.getDefaultCell().setBorderColor(java.awt.Color.DARK_GRAY);
                                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                        //  table.addCell(phrase);

                                        //  table.getDefaultCell().setBorder(Rectangle.LEFT);
                                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                                        /*  table.getDefaultCell().setColspan(4);

                                        phrase = new Phrase(" ", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("Discount ", pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);

                                        phrase = new Phrase("VAT ", pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                         */
                                        /*table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Serial No ", pFontHeader);
                                        table.addCell(phrase);
                                        phrase = new Phrase("Code ", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Item ", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("Qty. ", pFontHeader);
                                        table.addCell(phrase);

                                        // table.getDefaultCell().setColspan(2);


                                        table.getDefaultCell().setColspan(1);

                                        phrase = new Phrase("Unit Price ", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("No Of Units ", pFontHeader);
                                        //  table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);

                                        phrase = new Phrase("Gross ", pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        phrase = new Phrase("% ", pFontHeader);
                                        // table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        phrase = new Phrase("Discount", pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase("Net Value ", pFontHeader);
                                        table.addCell(phrase);

                                        System.out.println("First ");
                                        System.out.println("First Bottom " + docPdf.bottom());
                                         */

                                        table.getDefaultCell().setColspan(1);

                                        //table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);




                                        // java.sql.Statement st6 = connectDB.createStatement();
                                        java.sql.Statement st11 = connectDB.createStatement();

                                        java.sql.ResultSet rset11 = st11.executeQuery("SELECT initcap(item),quantity,unit_price,net_value FROM st_orders WHERE order_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");

                                        table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);


                                        while (rset11.next()) {
                                            //   table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                            table.getDefaultCell().setColspan(1);
                                            // table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);

                                            //
                                            // table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            coNo = coNo + 1;
                                            phrase = new Phrase(java.lang.String.valueOf(coNo), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(4);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                            phrase = new Phrase(rset11.getObject(1).toString(), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(2)), pFontHeader2);

                                            table.addCell(phrase);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(3)), pFontHeader2);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(4)), pFontHeader2);

                                            table.addCell(phrase);

                                            /* table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(4)), pFontHeader2);

                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase("0.00", pFontHeader2);
                                             *
                                            // table.addCell(phrase);



                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(5)), pFontHeader2);

                                            table.addCell(phrase);

                                            vat = vat + rset11.getDouble(5);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(6)), pFontHeader2);

                                            table.addCell(phrase);

                                            discount = discount + rset11.getDouble(6);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(7)), pFontHeader2);

                                            table.addCell(phrase);*/

                                            Total = Total + rset11.getDouble(4);
                                        }

                                        // table.getDefaultCell().setBorderColor(java.awt.Color.LIGHT_GRAY);

                                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                                        /*table.getDefaultCell().setColspan(6);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase("Total", pFontHeader);

                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        phrase = new Phrase(" ", pFontHeader);

                                        table.addCell(phrase);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(vat)), pFontHeader);

                                        //table.addCell(phrase);


                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(discount)), pFontHeader);

                                        //table.addCell(phrase);

                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Total)), pFontHeader);

                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(8);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("             ", pFontHeader);

                                        table.addCell(phrase);
                                         *
                                         */

                                        table1.getDefaultCell().setFixedHeight(320);
                                        table1.getDefaultCell().setColspan(9);

                                        //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        //phrase = new Phrase("             ", pFontHeader);

                                        table1.addCell(table);

                                        // docPdf.add(table);
                                        table1.getDefaultCell().setColspan(6);
                                        table1.getDefaultCell().setFixedHeight(15);
                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase("Total ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(3);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Total)), pFontHeader);

                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Signature", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Vote : " + vote_class + "-" + vote, pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Designation : ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Head : " + head, pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Station : ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(2);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Sub-Head : " + sub_head, pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(3);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Item : " + itemDet, pFontHeader);
                                        table1.addCell(phrase);


                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Date : ", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("A/C NO.: " + vote_class + "-" + vote + "-" + head + "-" + sub_head + "-" + itemDet, pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("I confirm that funds are available and that committment has been noted in the Vote Book", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Signature", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("                  Accountant I/C VBC", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Date", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase("...............................Supplier", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(5);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("                    I acknowledge receipt of this order", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(4);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(".....................................Date", pFontHeader);
                                        table1.addCell(phrase);

                                        table1.getDefaultCell().setColspan(9);

                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("(Refer to the conditions of acceptance overleaf)", pFontHeader);
                                        table1.addCell(phrase);
                                    }
                                }
                            }



                            /* table1.getDefaultCell().setColspan(3);

                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("Date : " + rset4.getObject(2).toString(), pFontHeader);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setColspan(9);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Tel : " + rset2.getString(4), pFontHeader);

                            table1.addCell(phrase);

                            table1.getDefaultCell().setColspan(9);


                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Fax : " + rset2.getString(7), pFontHeader);

                            table1.addCell(phrase);

                            }
                            }
                            while (rset5.next()) {
                            table1.getDefaultCell().setColspan(9);
                            phrase = new Phrase("Please Supply the goods / services / works described below", pFontHeader);
                            table1.addCell(phrase);

                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table1.getDefaultCell().setColspan(3);
                            phrase = new Phrase("On/Before: " + rset5.getObject(1).toString(), pFontHeader);
                            ddate = rset5.getObject(1).toString();
                            //table1.addCell(phrase);

                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader);
                            // table1.addCell(phrase);
                            }
                             */




                            docPdf.add(table1);
                            //       System.out.println("Current Doc size "+ pdfWriter.getTableBottom(table1));
                            //  System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }




                    // }



                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }
//            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
}
