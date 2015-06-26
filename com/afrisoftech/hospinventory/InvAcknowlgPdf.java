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


public class InvAcknowlgPdf implements java.lang.Runnable {
    
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void InvAcknowlgPdf(java.sql.Connection connDb,java.lang.String combox, java.lang.String order) {
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);
            
            try {
                
                try {
                    
                    pdfWriter = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    System.out.println("Current Doc size 1 "+ pdfWriter.getCurrentDocumentSize());
                    
                    
                    String compName = null;
                    String date = null;
                    
                    try {
                        
                        
                        java.sql.Statement st6 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        
                        
                        
                        
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Invoice Acknowledgement - Page:",pFontHeader ), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                        
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
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    double Total = 0.00;
                    double discount = 0.00;
                    double vat = 0.00;
                    
                    try{
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        table.getDefaultCell().setPadding(3);
                        
                        int headerwidths[] = {25,25,25,25};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        // table.getDefaultCell().setBottom(1);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(4);
                        
                        Phrase phrase = new Phrase(" INVOICE/DELIVERY ACKNOWLEDGEMENT ", pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);
                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                        
                        //  table.addCell(phrase);
                        
                        //  table.getDefaultCell().setBorder(Rectangle.LEFT);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM |Rectangle.LEFT | Rectangle.RIGHT);
                        
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
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                       
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
                        table.setHeaderRows(3);
                       
                        System.out.println("First " );
                        System.out.println("First Bottom "+docPdf.bottom());
                       */
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        
                        try {
                            
                            // java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            
                            
                            java.sql.ResultSet rset1 = st1.executeQuery("select supplier,invoice_no,lpo_no,input_date::date,category,amount,returned,net_amt,current_timestamp(0) from st_stock_bookings where invoice_no = '"+OrderNo+"'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            //  java.sql.ResultSet rsetTotals = st6.executeQuery("select sum(discount_value),sum(vat_amount),sum(net_value) from orders");// where member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            java.sql.Statement st11 = connectDB.createStatement();
                            
                            
                            java.sql.ResultSet rset11 = st11.executeQuery("select reason,action,user_name from st_stock_bookings where invoice_no = '"+OrderNo+"'");// where supplier_name = 'Uchumi'member_no = '"+memNo+"'  AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.lightGray);
                            
                            
                            while (rset1.next()) {
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                table.getDefaultCell().setColspan(3);
                                // table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Supplier " + rset1.getObject(1).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Time " + rset1.getObject(9).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Invoice No. "+ rset1.getObject(2).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("LPO No " + rset1.getObject(3).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Date Received. "+ rset1.getObject(4).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Category " + rset1.getObject(5).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(8);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("  " , pFontHeader2);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("Supplier Amt " , pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)),pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Rejected Amt " , pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)),pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Accepted Amt " , pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(8)),pFontHeader2);
                                
                                table.addCell(phrase);
                            }
                            
                            table.getDefaultCell().setColspan(8);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("             ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(4);
                            while (rset11.next()) {
                                phrase = new Phrase("Reason :" + rset11.getObject(1).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase("Action :" + rset11.getObject(2).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Prepared By :" + rset11.getObject(3).toString(), pFontHeader2);
                                
                                table.addCell(phrase);
                            }
                            
                            
                            table.getDefaultCell().setColspan(8);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("             ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Received By ____________________________", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(8);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("             ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Stamp ____________________________", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            docPdf.add(table);
                            
                            // java.sql.PreparedStatement pstmt3 = connectDB.prepareStatement("UPDATE st_orders SET ordered ='true' WHERE supplier = '"+selectSupp+"'");
                            //  pstmt3.executeUpdate();
                            
                            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
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
            System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    
    
    
    
}





