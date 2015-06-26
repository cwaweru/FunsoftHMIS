/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.afrisoftech.hospinventory.mtrhreports;



//Author OLSON MUTWIRI
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;


import com.afrisoftech.hospinventory.RcvBranchRequisintfr;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import javax.swing.JOptionPane;

public class PurchaseReqInternalMtrhPdf1_ implements java.lang.Runnable {
    
    int cnt = 0;
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
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    String Department=null;
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PurchaseReqInternalMtrhPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String dept, java.lang.String order) {
        //  public void OrdersPdf() {
        selectSupp = combox;
        Department = dept;
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

                        //   com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Internal Requisition - Page:",pFontHeader ), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        //  docPdf.setFooter(footer);
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
                            Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                            table1.getDefaultCell().setColspan(10);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(50);
                            table1.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(25);
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT hospital_name FROM pb_hospitalprofile");
                            while (rset3.next()) {

                                table1.getDefaultCell().setColspan(6);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
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

                        int headerwidths1[] = {24, 10, 8, 8, 6, 8, 6, 10, 12, 8};

                        //  table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));



                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase("", pFontHeader);




                        try {

                            // java.sql.Statement st3 = conDB.createStatement();

                            //  java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/purchase","postgres","pilsiner");
                            //  java.sql.Statement st3 = conDb.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno||' '||other_telno,initcap(street),initcap(town),main_faxno,email,initcap(building_name),room_no from pb_hospitalprofile");
                            //java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address,tel1,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name  ilike '"+selectSupp+"'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select DISTINCT REQUISITION_no,date_due,cost_center, case when supplier is null then '-' else supplier end as supplier,"
                                    + " received_requisation,reason,date,store_name,requisation FROM st_receive_requisation where REQUISITION_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("select DISTINCT date_due from st_receive_requisation where requisition_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset11 = st11.executeQuery("select initcap(user_name) from st_receive_requisation where requisition_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            while (rset11.next()) {
                                Username = rset11.getObject(1).toString();
                            }
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);

                            while (rset4.next()) {

                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("  ", pFontHeader);
                                //table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("INTERNAL PURCHASE REQUISITION", pFontHeader5);
                                table1.addCell(phrase);
                                 table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("FROM TEAM LEADER LOGISTICS-    "+Department+" ", pFontHeader5);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("TO SUPPLY CHAIN MANAGER", pFontHeader5);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                
                                phrase = new Phrase("NUMBER KNH/"+Department+"/  "+OrderNo, pFontHeader5);
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("PLEASE ORDER THE FOLLOWING MATERIALS/ITEMS", pFontHeader5);
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);


                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                //table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(6);

                                phrase = new Phrase("Requisitioning Store : " + rset4.getObject(3).toString(), pFontHeader);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(4);
                                phrase = new Phrase("DATE: " + rset4.getObject(9).toString() , pFontHeader);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setColspan(10);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Receiving Store : " + rset4.getObject(8).toString(), pFontHeader);
                                table1.addCell(phrase);

//                                table1.getDefaultCell().setColspan(10);
//                                phrase = new Phrase("  " + rset4.getObject(2).toString(), pFontHeader);
//                                table1.addCell(phrase);


                                /*
                                 * table1.getDefaultCell().setColspan(5);
                                 * table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                 * phrase = new Phrase(" ", pFontHeader);
                                 * table1.addCell(phrase); phrase = new
                                 * Phrase("PROCUREMENT METHOD : ", pFontHeader);
                                 * table1.addCell(phrase);
                                 *
                                 * table1.getDefaultCell().setColspan(5); phrase
                                 * = new Phrase(" ", pFontHeader);
                                 * table1.addCell(phrase);
                                 * table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                 * phrase = new Phrase("REQUISITION NO: " +
                                 * rset4.getObject(1).toString(), pFontHeader);
                                 * table1.addCell(phrase);
                                 *
                                 * table1.getDefaultCell().setColspan(5); phrase
                                 * = new Phrase(" ", pFontHeader);
                                 * table1.addCell(phrase);
                                 * table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                 * phrase = new Phrase("ACCOUNT NO: ",
                                 * pFontHeader); table1.addCell(phrase);
                                 *
                                 * table1.getDefaultCell().setColspan(5); phrase
                                 * = new Phrase(" ", pFontHeader);
                                 * table1.addCell(phrase);
                                 * table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                 * phrase = new Phrase("REASON FOR PURCHASE " +
                                 * rset4.getObject(6).toString(), pFontHeader);
                                 * table1.addCell(phrase);
                                 *
                                 * table1.getDefaultCell().setColspan(5);
                                 *
                                 * table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                 * phrase = new Phrase("", pFontHeader);
                                 * table1.addCell(phrase);
                                 *
                                 * table1.getDefaultCell().setColspan(5); phrase
                                 * = new Phrase("NAME OF THE SUPPLIER " +
                                 * rset4.getObject(4).toString(), pFontHeader);
                                 * table1.addCell(phrase);
                                 * table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                 * phrase = new Phrase("CONTRACT NO: ",
                                 * pFontHeader); table1.addCell(phrase);
                                 *
                                 */



                            }

                            docPdf.add(table1);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    double Total = 0.00;
                    double discount = 0.00;
                    double vat = 0.00;
                    double qty = 0.00;
                    double price = 0.00;
                    double value = 0.00;

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);
                        table.getDefaultCell().setPadding(3);

                        int headerwidths[] = {8, 30, 14, 14, 14, 14, 14, 16, 15};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));
                      

                        com.lowagie.text.pdf.PdfPTable table3 = new com.lowagie.text.pdf.PdfPTable(9);
                        // table3.getDefaultCell().setPadding(3);

                        int headerwidths3[] = {8, 30, 14, 14, 14, 14, 14, 16, 15};

                        table3.setWidths(headerwidths3);

                        table3.setWidthPercentage((100));
                        //table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(9);
                        Phrase phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);

                        table3.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                        table3.getDefaultCell().setColspan(1);
                        phrase = new Phrase("NOs ", pFontHeader);
                        table3.addCell(phrase);
                        
                        table3.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Item No/Code ", pFontHeader);
                        table3.addCell(phrase);

                        table3.getDefaultCell().setColspan(2);

                        phrase = new Phrase("Item Description", pFontHeader);
                        table3.addCell(phrase);
                        table3.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Units of Issue. ", pFontHeader);
                        table3.addCell(phrase);
                        
                        table3.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Qty Required. ", pFontHeader);
                        table3.addCell(phrase);
                        
                        table3.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Store Balance ", pFontHeader);
                        table3.addCell(phrase);

                        table3.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Monthly Usage", pFontHeader);
                        table3.addCell(phrase);
                        
                        
                        
//                        table3.getDefaultCell().setColspan(1);
//                        phrase = new Phrase(" ", pFontHeader);
//                        table3.addCell(phrase);

//                        phrase = new Phrase("Est.Cost ", pFontHeader);
//                        table3.addCell(phrase);
//
//                        phrase = new Phrase("Actual Cost", pFontHeader);
//                        table3.addCell(phrase);
//
//                        phrase = new Phrase("Tender/Qtn Ref.", pFontHeader);
//                        table3.addCell(phrase);

                        phrase = new Phrase("", pFontHeader);
                        //table.addCell(phrase);
                        table3.setHeaderRows(1);


//                        table3.getDefaultCell().setColspan(1);
//
//                        table3.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        String coment = "";

                        try {

                            // java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();

                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT initcap(item_description),units,cos_glcode,"
                                    + " quantity,price,round(quantity*price,2),mainstore_bal,terms,item_code,monthly_usage from st_receive_requisation"
                                    + " WHERE requisition_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");

                            table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);


                            while (rset1.next()) {

                                // value = qty * price;

                                cnt = cnt + 1;
                                
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table3.getDefaultCell().setColspan(1);
                                phrase = new Phrase("" + cnt, pFontHeader2);
                                table3.getDefaultCell().setBorderColor(java.awt.Color.black);
                                table3.addCell(phrase);
                                
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table3.getDefaultCell().setColspan(1);
                                phrase = new Phrase(rset1.getObject(9).toString(), pFontHeader2);
                                table3.getDefaultCell().setBorderColor(java.awt.Color.black);
                                table3.addCell(phrase);

                                table3.getDefaultCell().setColspan(2);
                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader2);
                                table3.addCell(phrase);
                                
                                table3.getDefaultCell().setColspan(1);
                                phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader2);
                                table3.addCell(phrase);
                                
                                table3.getDefaultCell().setColspan(1);
                                phrase = new Phrase(rset1.getObject(4).toString(), pFontHeader2);
                                table3.addCell(phrase);
                                
                                table3.getDefaultCell().setColspan(1);
                               // JOptionPane.showMessageDialog(null, "this "+rset1.getObject(10).toString());
                                phrase = new Phrase(rset1.getObject(7).toString(), pFontHeader);
                                table3.addCell(phrase);

                                table3.getDefaultCell().setColspan(2);
                                //change
                                phrase = new Phrase(rset1.getObject(10).toString(), pFontHeader2);
                                table3.addCell(phrase);
                                
                                
//                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getObject(4).toString()), pFontHeader2);
//
//                                table3.addCell(phrase);
//
//                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getObject(5).toString()), pFontHeader2);
//                                table3.addCell(phrase);
//
//                                // System.out.println("Second "+docPdf.top());
//                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)), pFontHeader2);
//
//                                table3.addCell(phrase);
//
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)), pFontHeader2);
//
//                                table3.addCell(phrase);
//
//
//                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                value = value + rset1.getDouble(6);
//                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                // phrase = new Phrase(rset1.getObject(8).toString(), pFontHeader2);
//
//                                // table.addCell(phrase);
//                                //  coment = rset1.getObject(12).toString();

                            }

//                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//
//                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
//                            table.getDefaultCell().setFixedHeight(350);
//                            table.addCell(table3);
//
//                            table.getDefaultCell().setFixedHeight(20);
//
////                         
//
//
//                            table.getDefaultCell().setColspan(9);
//                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
//                            phrase = new Phrase(" ", pFontHeader);
//
//                            table.addCell(phrase);
//                            table.getDefaultCell().setBorder(Rectangle.TOP);
//                           
//                            table.addCell(phrase);
                            //table.getDefaultCell().setBorder(Rectangle.TOP);



                            /*
                             *
                             *
                             * table.getDefaultCell().setColspan(6);
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new Phrase("Prepared By :"
                             * +Username.toUpperCase(), pFontHeader);
                             *
                             * table.addCell(phrase);
                             */

//                            table.getDefaultCell().setColspan(3);
//                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("PREPARED BY.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Sign.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Date.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                          phrase = new Phrase("Time.................................\n", pFontHeader);
//                          table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(3);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("AIE HOLDER/USER.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Sign.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Date.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Time.................................\n", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(3);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("APPROVED BY(TL LOGISTICS).................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Sign.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Date.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Time.................................\n", pFontHeader);
//
//                            table.addCell(phrase);
//                            table.getDefaultCell().setColspan(4);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Amount Voted..................................................", pFontHeader);
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(5);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Vote Balance.........................................................", pFontHeader);
//                            table.addCell(phrase);
//
//
//                            table.getDefaultCell().setColspan(3);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Vote Holder's Authority.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Sign.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Date.................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Time.................................", pFontHeader);
//
//                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           // phrase = new Phrase("SIGN..........................................................", pFontHeader);
                            //table.addCell(phrase);

                            table.getDefaultCell().setColspan(9);

                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("", pFontHeader);

                            table.addCell(phrase);

                            //phrase = new Phrase("FOR PROCUREMENT USE ONLY", pFontHeader);

                            //table.addCell(phrase);
                            //table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

//                            table.getDefaultCell().setColspan(3);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Approved/Not Approved.....................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            //table.getDefaultCell().setColspan(5);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Date............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Time............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("ACTION:- I/C Supplies & Proc .....................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            //table.getDefaultCell().setColspan(5);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Date............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Time............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Procurement Section.....................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            //table.getDefaultCell().setColspan(5);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Date............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            phrase = new Phrase("Time............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Stock Ctr/: D/Note.............................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Inv No.......................", pFontHeader);
//
//                            table.addCell(phrase);
//
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Sign....................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Date.......................................", pFontHeader);
//
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(1);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Time...........................", pFontHeader);
//
//                            table.addCell(phrase);

                            /*
                             * table.getDefaultCell().setColspan(5);
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new
                             * Phrase("REMARK___________________________________",
                             * pFontHeader);
                             *
                             * table.addCell(phrase);
                             * table.getDefaultCell().setColspan(4);
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new Phrase("DATE
                             * RECEIVED____________________________",
                             * pFontHeader); table.addCell(phrase);
                             *
                             * table.getDefaultCell().setColspan(5);
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new Phrase(" ", pFontHeader);
                             * table.addCell(phrase);
                             *
                             * table.getDefaultCell().setColspan(9);
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             * phrase = new Phrase(" ", pFontHeader);
                             *
                             * table.addCell(phrase);
                             * table.getDefaultCell().setBorder(Rectangle.TOP);
                             *
                             * table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                             *
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new Phrase("1. Original__________ Use
                             * department", pFontHeader);
                             *
                             * table.addCell(phrase);
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new Phrase("2. Duplicate________
                             * Procurement office", pFontHeader);
                             *
                             * table.addCell(phrase);
                             *
                             *
                             * table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             * phrase = new Phrase("3. Triplicate_________ Book
                             * copy", pFontHeader);
                             *
                             * table.addCell(phrase);
                             */


                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }
                    
                   try{
                       
                       Phrase phrase =  new Phrase();
                   
                       com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);
                        table.getDefaultCell().setPadding(3);

                        int headerwidths[] = {8, 30, 14, 14, 14, 14, 14, 16, 15};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setFixedHeight(350);
                            table.addCell(table);

                            table.getDefaultCell().setFixedHeight(20);

//                         


                            table.getDefaultCell().setColspan(9);
                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.TOP);
                           
                            table.addCell(phrase);
                        
                        
                         table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("PREPARED BY.................................", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Sign.................................", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("Date.................................", pFontHeader);

                            table.addCell(phrase);

                          phrase = new Phrase("Time.................................\n", pFontHeader);
                          table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("AIE HOLDER/USER.................................", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Sign.................................", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("Date.................................", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("Time.................................\n", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("APPROVED BY(TL LOGISTICS).................................", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Sign.................................", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("Date.................................", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("Time.................................\n", pFontHeader);

                            table.addCell(phrase);
                        
                        
                       //down
                        
                        docPdf.add(table);
                    
                   }
                        catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());    

                    }
                }
                   
                   catch (java.io.FileNotFoundException fnfExec) {

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

