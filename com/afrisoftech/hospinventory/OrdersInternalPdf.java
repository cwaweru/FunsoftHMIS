//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospinventory;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class OrdersInternalPdf implements java.lang.Runnable {
    
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void OrdersInternalPdf(java.sql.Connection connDb,java.lang.String combox, java.lang.String order) {
        //  public void OrdersPdf() {
        selectSupp = combox;
        
        OrderNo = order;
        
        connectDB = connDb;
        // beginDate = begindate;
        threadSample = new java.lang.Thread(this,"SampleThread");
        
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
                
            } catch(java.lang.InterruptedException IntExec) {
                
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
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }
    
    
    public void generatePdf(java.lang.String memNo) {
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
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
                        
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Purchase Order - Page:",pFontHeader ), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                        
                        docPdf.setFooter(footer);
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    docPdf.open();
                    
                    String Username = null;
                    int numColumns = 9;
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {15,15,30,15,15,15};
                        
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
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(6);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    try {
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(10);
                        table1.getDefaultCell().setPadding(3);
                        
                        int headerwidths1[] = {24,10,8,8,6,8,6,10,12,8};
                        
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
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            //  java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno||' '||other_telno,initcap(street),initcap(town),main_faxno,email,initcap(building_name),room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset2 = st2.executeQuery("select supplier_name,short_name,postal_address,tel1,initcap(street),initcap(avenue),fax_no,email_address,initcap(building_name) from st_suppliers WHERE supplier_name  ilike '"+selectSupp+"'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select DISTINCT order_no,date from st_orders where order_no = '"+OrderNo+"'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset5 = st5.executeQuery("select DISTINCT date_due from st_orders where order_no = '"+OrderNo+"'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.ResultSet rset1 = st1.executeQuery("select current_user");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            while (rset1.next()){
                                Username = rset1.getObject(1).toString();
                            }
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                            
                            while(rset4.next()){
                                
                                
                                table1.getDefaultCell().setColspan(5);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("Order Date: "+"\t" +rset4.getObject(2).toString(), pFontHeader);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table1.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Purchase Order No. "+"\t" +rset4.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                            }
                            
                            while (rset2.next()){
                                table1.getDefaultCell().setColspan(10);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("To: "+rset2.getObject(1).toString(), pFontHeader3);
                                table1.addCell(phrase);
                                
                                
                                
                                table1.getDefaultCell().setColspan(4);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address: "+"\t" +rset2.getString(3), pFontHeader);
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("   Tel: "+"\t"+rset2.getString(4), pFontHeader);
                                
                                table1.addCell(phrase);
                                
                                
                                
                                table1.getDefaultCell().setColspan(3);
                                
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Fax: "+"\t"+ rset2.getString(7), pFontHeader);
                                
                                table1.addCell(phrase);
                                
                            }
                            
                            
                            while(rset5.next()){
                                table1.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Please Supply the following items at : --  ", pFontHeader);
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table1.getDefaultCell().setColspan(3);
                                phrase = new Phrase("On/Before: "+rset5.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table1.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table1.addCell(phrase);
                            }
                            
                            
                            
                            
                            
                            docPdf.add(table1);
                            //       System.out.println("Current Doc size "+ pdfWriter.getTableBottom(table1));
                            //  System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    double Total = 0.00;
                    double discount = 0.00;
                    double vat = 0.00;
                    
                    try{
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);
                        table.getDefaultCell().setPadding(3);
                        
                        int headerwidths[] = {24,15,10,8,6,8,6,8,12,8};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((105));
                        // table.getDefaultCell().setBottom(1);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(10);
                        
                        Phrase phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);
                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                        
                        //  table.addCell(phrase);
                        
                        //  table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM |Rectangle.LEFT | Rectangle.RIGHT);
                        
                        table.getDefaultCell().setColspan(5);
                        
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
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Item ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("Units ", pFontHeader);
                        table.addCell(phrase);
                        // table.getDefaultCell().setColspan(2);
                        
                        phrase = new Phrase("Qty. ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("Price ", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("% ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("Amt ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("% ", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("Amt ", pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Value ", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Bal.", pFontHeader);
                        table.addCell(phrase);
                        
                        table.setHeaderRows(3);
                        
                        System.out.println("First " );
                        System.out.println("First Bottom "+docPdf.bottom());
                        
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        
                        try {
                            
                            // java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset1 = st1.executeQuery("select initcap(item),quantity,units,unit_price,discount,discount_value,vat,vat_amount,net_value,code from st_orders where order_no = '"+OrderNo+"'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);
                            
                            
                            while (rset1.next()) {
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                
                                // table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                //
                                // table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                System.out.println("Second "+docPdf.top());
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)),pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)),pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                discount = discount + rset1.getDouble(6);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)),pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(8)),pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                vat = vat + rset1.getDouble(8);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(9)),pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                Total = Total + rset1.getDouble(9);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(10)),pFontHeader2);
                                
                                table.addCell(phrase);
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(5);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(discount)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(" ",pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(vat)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Total)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                             phrase = new Phrase("             ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(10);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("             ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(10);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("             ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Prepared By :" +Username.toUpperCase(), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Checked By _______________________________", pFontHeader);
                            
                            table.addCell(phrase);
                            
                           /* table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Verified By : ____________________________", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Approved By ______________________________", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Passed By :____________________________", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Authorised By :____________________________", pFontHeader);
                            
                            table.addCell(phrase);*/
                            
                            docPdf.add(table);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        // }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                } catch(java.io.FileNotFoundException fnfExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                    
                }
            } catch(com.lowagie.text.DocumentException lwDocexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());
                
            }
//            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    
    
    
    
}





