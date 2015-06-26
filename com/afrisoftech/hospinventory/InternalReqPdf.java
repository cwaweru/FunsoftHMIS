//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.hospinventory;

import com.afrisoftech.lib.DBObject;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternalReqPdf implements java.lang.Runnable {

    int cnt = 0;
    String branch = null;
    String ministry = null;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void InternalReqPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String order) {
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

                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Internal Requisition - Page:", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                        docPdf.setFooter(footer);
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    docPdf.open();

                    String Username = null;
                    int numColumns = 9;
                    java.lang.StringBuffer letterHead=new java.lang.StringBuffer();

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        //com.lowagie.text.pdf.PdfPTable letterHeadTable = new com.lowagie.text.pdf.PdfPTable(6);
                        com.lowagie.text.pdf.PdfPTable tableu = new com.lowagie.text.pdf.PdfPTable(6);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15};

                        tableu.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  letterHeadTable.setHeaderRows(4);
                        //  }
                        //////////letterHeadTable.setWidthPercentage((100));


                        ////////letterHeadTable.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        //////////letterHeadTable.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        tableu.getDefaultCell().setColspan(1);
                        //  letterHeadTable.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //  letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            //////////java.sql.ResultSet rset3 = st3.executeQuery("SELECT organisation_name,hospital_name FROM pb_hospitalprofile LIMIT 1");
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,postal_code,town,main_telno,other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            
                            while (rset3.next()) {
                                
                                letterHead.append(rset3.getString(1)).append("\n");
                                letterHead.append("P. O. BOX "+rset3.getString(2)).append(" - ");
                                letterHead.append(rset3.getString(3)).append("\n");
                                letterHead.append("Tel. No.: "+rset3.getString(5)).append("\n");
                                letterHead.append("Email: "+rset3.getString(9)).append("\n");
                                letterHead.append("Website: "+rset3.getString(10)).append("\n");
                                
//                                ministry = rset3.getObject(1).toString();
//                                this.branch = rset3.getObject(2).toString();
//                                letterHeadTable.getDefaultCell().setColspan(6);
//
//                                letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                ////////// letterHeadTable.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        //docPdf.add(letterHeadTable);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {
                        try{
//                            com.lowagie.text.pdf.PdfPTable tablelogo = new com.lowagie.text.pdf.PdfPTable(10);
//                       
//
//                        int headerwidthslog[] = {15, 10, 10, 10, 10, 10, 10, 10, 10, 12};
//
//                        tablelogo.setWidths(headerwidthslog);
//
//                        tablelogo.setWidthPercentage((100));
//
//
//                         
//
//                        tablelogo.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
//                        tablelogo.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
//                                               
//                               tablelogo.getDefaultCell().setColspan(10);
//                              tablelogo.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                          tablelogo.getDefaultCell().setFixedHeight(90);
//                            tablelogo.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
//                           
//                         docPdf.add(tablelogo);
//
//


                        
                        }
                        catch(Exception logo){
                            System.out.println("your famous error is "+logo);
                            
                        }
                        
                        
                        
                        
                        
                        com.lowagie.text.pdf.PdfPTable letterHeadTable = new com.lowagie.text.pdf.PdfPTable(10);
                       

                        int headerwidths1[] = {15, 10, 10, 10, 10, 10, 10, 10, 10, 12};

                        letterHeadTable.setWidths(headerwidths1);

                        letterHeadTable.setWidthPercentage((100));
                        
                        letterHeadTable.getDefaultCell().setColspan(10);
                        
                        letterHeadTable.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        
                        try {
                            
                            letterHeadTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            letterHeadTable.getDefaultCell().setColspan(2);
                            letterHeadTable.getDefaultCell().setFixedHeight(50);
                            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            letterHeadTable.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            letterHeadTable.getDefaultCell().setFixedHeight(16);
                        
                            letterHeadTable.getDefaultCell().setColspan(6);
                            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(letterHead.toString().toUpperCase(), pFontHeader6);
                            letterHeadTable.addCell(phrase);
                        
                            letterHeadTable.getDefaultCell().setColspan(2);
                            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" Form S 11 ", pFontHeader6);
                            letterHeadTable.addCell(phrase);
                            
                            letterHeadTable.getDefaultCell().setColspan(10);
                            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("");
                            letterHeadTable.addCell(phrase);
                            
                            letterHeadTable.getDefaultCell().setColspan(10);
                            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("COUNTER REQUISITION AND ISSUE VOUCHER", pFontHeader11);
                            letterHeadTable.addCell(phrase);
                            
                            letterHeadTable.getDefaultCell().setColspan(10);
                            phrase = new Phrase("");
                            letterHeadTable.addCell(phrase);
                            
//                            java.sql.Statement st321 =connectDB.createStatement();
//                            java.sql.ResultSet rset3 = st321.executeQuery("select header_name from pb_header");
                                
                            letterHeadTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            //letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
//                            if (rset3.next()) {
//                                letterHeadTable.getDefaultCell().setColspan(10);
//
//                                letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader6);
//                                
//                                System.err.println("Phrase is "+rset3.getObject(1).toString());
//                                letterHeadTable.addCell(phrase);
//                            }
                             
//                            letterHeadTable.getDefaultCell().setColspan(10);
//                            letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                            phrase = new Phrase(ministry, pFontHeader5);
//                            letterHeadTable.addCell(phrase);

                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement transferNoStatement = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.ResultSet rset4 = st4.executeQuery("select DISTINCT requisition_no,date,initcap(cost_center),initcap(store_name),date_due from st_receive_requisation where REQUISITION_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("select DISTINCT date_due from st_receive_requisation where REQUISITION_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset11 = st11.executeQuery("select INITCAP(user_name) from st_receive_requisation where REQUISITION_no = '" + OrderNo + "'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            
                            String transactionNo=null;
                            
                            java.sql.ResultSet transNoSet=transferNoStatement.executeQuery("SELECT DISTINCT transaction_no FROM st_sub_stores WHERE manual_transfer_no ILIKE '"+OrderNo+"'");
                            
                            while(transNoSet.next()){
                                //if(transNoSet!=null){
                                transactionNo=transNoSet.getString(1);
                                //}
//                                else{
//                                transactionNo="-";
//                                }
                            }
                           
                            if(transactionNo==null){
                                transactionNo="-";
                            }
                            
                            while (rset11.next()) {
                                Username = rset11.getObject(1).toString();
                            }
                            
                            letterHeadTable.getDefaultCell().setBorderColor(java.awt.Color.white);

                            while (rset4.next()) {

                               
                                letterHeadTable.getDefaultCell().setColspan(5);
                                letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("FROM (issue point)  " + rset4.getObject(4).toString(), pFontHeader);
                                letterHeadTable.addCell(phrase);
                                

                                letterHeadTable.getDefaultCell().setColspan(5);
                                //letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("TO  : " + rset4.getObject(3).toString(), pFontHeader);
                                letterHeadTable.addCell(phrase);


                                letterHeadTable.getDefaultCell().setColspan(5);
                                letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Requisition Date : " + rset4.getObject(2).toString(), pFontHeader);
                                letterHeadTable.addCell(phrase);
                                
                                
                                System.err.println("The transaction no is "+transactionNo);
                                
                                letterHeadTable.getDefaultCell().setColspan(5);
                                letterHeadTable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Requisition No/Transfer No : " + rset4.getObject(1).toString()+"/ "+transactionNo, pFontHeader);
                                letterHeadTable.addCell(phrase);

                            }

                            docPdf.add(letterHeadTable);

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
                    double value12 = 0.00;
                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);

                        table.getDefaultCell().setPadding(8);

                        int headerwidths[] = {6, 15, 30, 10, 11, 11, 11, 12};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorderColor(java.awt.Color.black);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        Phrase phrase = new Phrase(" ", pFontHeader);
                        //  table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("No ", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Code No. ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);

                        phrase = new Phrase("Item Description ", pFontHeader);
                        table.addCell(phrase);
                        // table.getDefaultCell().setColspan(2);

                        phrase = new Phrase("Unit of Issue ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);

                        phrase = new Phrase("Qty Required ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);

                        phrase = new Phrase("Qty Issued", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Value ", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Remarks ", pFontHeader);
                        table.addCell(phrase);



                        System.out.println("First ");
                        System.out.println("First Bottom " + docPdf.bottom());


                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);



                     try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            String coment = "";
                            // java.sql.Statement st6 = connectDB.createStatement();
                          for(int i=0;i<listofAct.length;i++){
                            java.sql.Statement st1 = connectDB.createStatement();

                            java.sql.ResultSet rset1 = st1.executeQuery(""
                                    + "select upper(item_code),initcap(item_description),units,quantity,qty_issued,round(qty_issued*price),reason"
                                    + " FROM st_receive_requisation WHERE requisition_no = '" + OrderNo + "' AND item_code = '"+listofAct[i].toString()+"'");


                            //table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);


                            while (rset1.next()) {
                                //table.getDefaultCell().setBorder(Rectangle.TOP| Rectangle.BOTTOM  | Rectangle.LEFT | Rectangle.RIGHT);
                                table.getDefaultCell().setColspan(1);

                              
                                cnt = cnt + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("" + cnt, pFontHeader2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                // table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader2);

                                table.addCell(phrase);

                                phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader2);

                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                try{
                                     java.sql.Statement uintst = connectDB.createStatement();

                                 java.sql.ResultSet rsetunit = uintst.executeQuery(""
                                         + "SELECT units  FROM st_stock_item where "
                                         + "item_code ilike '"+rset1.getString(1)+"' and description ilike '"+rset1.getString(2)+"' ;");
                           
                                     if(rsetunit.next()){
                                phrase = new Phrase(rsetunit.getObject(1).toString(), pFontHeader2);
                                }
                                }
                                catch(Exception unit){
                                     phrase = new Phrase("", pFontHeader2);
                                }
                               

                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getObject(4).toString()), pFontHeader2);
                                table.addCell(phrase);

                                // System.out.println("Second "+docPdf.top());
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader2);
                                //phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader2);

                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)), pFontHeader2);
                                //phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader2);

                                table.addCell(phrase);


                                coment = rset1.getObject(7).toString();

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(coment, pFontHeader2);

                                table.addCell(phrase);
                                value = value + rset1.getDouble(5);


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                



                              }

//                            table.getDefaultCell().setColspan(8);
//                            table.getDefaultCell().setBorder(Rectangle.TOP);
//
//                            phrase = new Phrase("", pFontHeader2);
//
//                            table.addCell(phrase);


                       }
                          
                          table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                          table.getDefaultCell().setColspan(8);
                          phrase = new Phrase("");
                          table.addCell(phrase);
                            /*table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);


                            table.getDefaultCell().setColspan(5);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(value)), pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
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
                            phrase = new Phrase(coment, pFontHeader2);

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

                            com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(3);

                            table2.getDefaultCell().setPadding(3);

                            int headerwidths2[] = {35, 35, 30};

                            table2.setWidths(headerwidths2);

                            table2.setWidthPercentage((100));

                            table2.getDefaultCell().setBorderColor(java.awt.Color.white);

                            Phrase phrase2 = new Phrase(" ", pFontHeader);
//RaiseInternalProcRequisitionIntfr
                            table2.getDefaultCell().setColspan(3);

                            //table2.getDefaultCell().setFixedHeight(20);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("", pFontHeader);

                            table2.addCell(phrase2);
                            table2.getDefaultCell().setColspan(2);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Account No...............................................", pFontHeader);

                            table2.addCell(phrase2);
                            table2.getDefaultCell().setColspan(1);

                            phrase2 = new Phrase("Date :.............................................", pFontHeader);
                                                  
                            table2.addCell(phrase2);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Requisitioning Officer: " + Username.toUpperCase(), pFontHeader);

                            table2.addCell(phrase2);



                            // table.getDefaultCell().setColspan(2);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Designation ...................................", pFontHeader);
                            table2.addCell(phrase2);

                            //table.getDefaultCell().setColspan(4);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Signature......................................", pFontHeader);

                            table2.addCell(phrase2);
                           
                            //authorised 
                            //table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Authorised By : "+getApprover(OrderNo).toLowerCase()+"\n", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            java.sql.Statement issuedReceivedStat=connectDB.createStatement();
                            java.sql.ResultSet issuedReceivedSet=issuedReceivedStat.executeQuery("SELECT user_name,issiued_to FROM st_sub_stores WHERE manual_transfer_no='"+OrderNo+"'");
                            String issuedBy=null,receivedBy=null;
                            
                            while(issuedReceivedSet.next()){
                                issuedBy=issuedReceivedSet.getString(1);
                                receivedBy=issuedReceivedSet.getString(2);
                                
                                System.err.println("This isn't supposed to execute.");
                            }
                            
                            if(issuedBy==null){
                                issuedBy="-";
                            }
                            
                            if(receivedBy==null){
                                receivedBy="-";
                            }
                            
                            System.err.println("Orderno "+OrderNo);
                            System.err.println("Issued by "+issuedBy);
                            System.err.println("Received by "+receivedBy);


                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //phrase2 = new Phrase("Issued by :.......................................", pFontHeader);
                            phrase2 = new Phrase("Issued by : "+issuedBy.toUpperCase(), pFontHeader);                                                               
                            table2.addCell(phrase2);


                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Signature........................................", pFontHeader);
                            table2.addCell(phrase2);


                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Date :............................................", pFontHeader);

                            table2.addCell(phrase2);


                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //phrase2 = new Phrase("Received by: ", pFontHeader);
                            phrase2 = new Phrase("Received by: "+receivedBy.toUpperCase(), pFontHeader);

                            table2.addCell(phrase2);


                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Designation ....................................", pFontHeader);
                            table2.addCell(phrase2);


                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase2 = new Phrase("Signature......................................", pFontHeader);
                            table2.addCell(phrase2);



                            /*
                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Approved By ______________________________", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Passed By :____________________________", pFontHeader);

                            table.addCell(phrase);
*/

                            
                            docPdf.add(table);
                            
                            //docPdf.add(com.lowagie.text.Chunk.NEWLINE);
                            
                            docPdf.add(table2);
                        } 
                     
                     catch (java.sql.SQLException SqlExec) {

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
//            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
     public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


           try {

       

        java.sql.Statement stmt1 = connectDB.createStatement();

        java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item_code from st_receive_requisation WHERE requisition_no = '"+OrderNo+"' AND irq_approval=true");//FROM st_receive_requisation WHERE requisition_no 

        while (rSet1.next()) {

        listActVector.addElement(rSet1.getObject(1).toString());
        System.out.println(rSet1.getObject(1).toString());
       // System.out.println(OrderNo);

        }

        }catch (java.sql.SQLException sqlExec) {

        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }
         

        listofActivities = listActVector.toArray();
        System.out.println("Done list of Items in the Requisition ...");
        return listofActivities;
    }
     
     public String getApprover( String reqno){
         DBObject dbobject = new DBObject();
         String user="-";
        try {
            PreparedStatement pst = connectDB.prepareStatement("select distinct irq_approved_by from st_receive_requisation where requisition_no='"+OrderNo+"' limit 1");
            ResultSet rset = pst.executeQuery();
            while (rset.next()){
                user= dbobject.getDBObject(rset.getObject(1), "-");
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InternalReqPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
        return user;
         
     }
}
