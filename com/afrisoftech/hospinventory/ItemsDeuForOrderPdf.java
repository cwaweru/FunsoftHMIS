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


public class ItemsDeuForOrderPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    
    java.lang.String StoreName = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void ItemsDeuForOrderPdf(java.sql.Connection connDb, java.lang.String store) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        StoreName = store;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new EntranceFeePdf().EntranceFeePdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
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
    
    
    public void generatePdf() {
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String Prd = null;
                    double Total = 0.00;
                    String compName = null;
                    String date = null;
                    try {
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st41 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        java.sql.ResultSet rset21 = st41.executeQuery("SELECT average_day from st_ordering_constants");
                        
                        while(rset21.next()){
                            Prd = rset21.getObject(1).toString();
                        }
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"   Printed On: "+date+" \n Period Based on: "+Prd+" Days"),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Products To Order - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    double osBalance = 0.00;
                    double osBalance1 = 0.00;
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {27,8,8, 8,10,12,15,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(5);
                        
                        Phrase phrase = new Phrase("Products Due For Ordering", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Printed On : "  +date , pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        //  table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase = new Phrase("Item",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Units",pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("BP",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Bal.",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Issues",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("Avg Cons.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Order",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Amt",pFontHeader);
                        table.addCell(phrase);
                        
                       /* table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("Deposit",pFontHeader);
                        table.addCell(phrase);
                        */
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            float leadtime = 0;
                            float bPrice = 0;
                            float quantityToOrder = 0;
                            float ReoderLevel = 0;
                            float sumQty = 0;
                            float avgr = 0;
                            float reorders = 0;
                            
                            float qtyInstock = 0;
                            float balance = 0;
                            float qtyReq = 0;
                            float sumQty1 = 0;
                            
                            float requ = 0;
                            float rlevel = 0;
                            float rtime = 0;
                            float orders = 0;
                            float averageCons = 0;
                            java.sql.Statement stmtTab = connectDB.createStatement();
                            
                            java.sql.ResultSet rsetTab = stmtTab.executeQuery("select average_day::int,read_time,reorder_level from st_ordering_constants");
                            
                            while (rsetTab.next()) {
                                
                                leadtime = rsetTab.getFloat(2);
                                avgr = rsetTab.getInt(1);
                                reorders = rsetTab.getFloat(3);
                            }
                            
                            java.lang.Object listofDays[] = this.getListofActivities();
                            for (int l = 0; l < listofDays.length; l++) {
                                
                                java.sql.Statement stmtTable1 = connectDB.createStatement();
                                //     java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select item,units,sum(issuing) from st_sub_stores where item = '"+listofDays[l]+"' and trans_date::date BETWEEN (date('now') - '"+avgr+"'::int) AND date('now') and issuing>0 group by item,units");
                                
                                java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("select item,units,sum(round(quantity)),sum(round(reorder_level)),sum(round(read_time)),sum(round(cons_average)) from stock_analysis where item = '"+listofDays[l]+"' AND store_name ilike '"+StoreName+"' GROUP BY item,units");
                                
                                //  java.sql.Statement stmtTableq = connectDB.createStatement();
                                //  java.sql.ResultSet rsetTable4 = stmtTableq.executeQuery("select count(item) from stock_analysis where item = '"+listofDays[l]+"'");
                                
                                java.sql.Statement stmtTable11 = connectDB.createStatement();
                                java.sql.ResultSet rsetTable11 = stmtTable11.executeQuery("select sum(qty) from stock_balance where description ilike '"+listofDays[l]+"' AND department ilike '"+StoreName+"'");
                                
                                java.sql.Statement stmtTable111 = connectDB.createStatement();
                                java.sql.ResultSet rsetTable111 = stmtTable111.executeQuery("select buying_price from st_stock_item where description ilike '"+listofDays[l]+"' AND department ilike '"+StoreName+"'");
                                
                                
                                while (rsetTable1.next()) {
                                    
                                    requ = rsetTable1.getFloat(3);
                                    rlevel = rsetTable1.getFloat(4);
                                    rtime = rsetTable1.getFloat(5);
                                    avgr = rsetTable1.getFloat(6);
                                    orders = (rlevel)+(rtime);
                                    while (rsetTable111.next()) {
                                        
                                        bPrice = rsetTable111.getFloat(1);
                                        while (rsetTable11.next()) {
                                            
                                            qtyInstock = rsetTable11.getFloat(1);
                                            
                                            
                                            if (orders > qtyInstock){
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(dbObject.getDBObject(rsetTable1.getObject(1), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(dbObject.getDBObject(rsetTable1.getObject(2), "-"), pFontHeader1);
                                                
                                                table.addCell(phrase);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bPrice)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(qtyInstock)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(requ)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(avgr)),pFontHeader1);
                                                //  osBalance1 = osBalance1 + rset.getDouble(6);
                                                //  osBalancet1 = osBalancet1 + rset.getDouble(6);
                                                
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(orders)),pFontHeader1);
                                                // osBalance = osBalance + rset.getDouble(7);
                                                //  osBalancet = osBalancet + rset.getDouble(7);
                                                
                                                table.addCell(phrase);
                                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancet-osBalancet1)),pFontHeader1);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(orders*bPrice)),pFontHeader1);
                                                osBalance = osBalance + orders*bPrice;
                                                // osBalancet = osBalancet + rset.getDouble(8);
                                                table.addCell(phrase);
                                                
                                                
                                            }
                                            
                                        }
                                        
                                    }
                                }
                            }
                            
                            java.sql.Statement st11 = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                           
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)),pFontHeader);
                            
                            table.addCell(phrase);
                            
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
            
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item FROM stock_analysis WHERE store_name ilike '"+StoreName+"' order by item");// where trans_date BETWEEN (date('now') - '"+average+"'::int) AND date('now') and issuing > 0 order by item");
            
            while (rSet1.next()) {
                listActVector.addElement(rSet1.getObject(1).toString());
            }
            System.out.println("description"+rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
    
}





