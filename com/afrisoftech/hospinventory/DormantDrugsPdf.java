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


public class DormantDrugsPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    String Consumption;
    String MarkUp;
    String Department;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DormantDrugsPdf(java.sql.Connection connDb,java.lang.String cons,java.lang.String markup, java.lang.String dept) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        Consumption = cons;
        MarkUp = markup;
        Department = dept;
        
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
                
                java.lang.Thread.currentThread().sleep(100);
                
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
                    
                    
                    // String Prd = 0.00;
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
                        
                        // while(rset21.next())
                        //     Prd = Consp;
                        
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"   Printed On: "+date+" \n Period Based on: "+Consumption+" Days"),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Projected Consumption report - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    double osBalance = 0.00;
                    double osBalance1 = 0.00;
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);
                        
                        int headerwidths[] = {27,8,8, 8,10,12,15,10,8};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(5);
                        
                        Phrase phrase = new Phrase("Dormant Products Report", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Printed On : "  +date , pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        //  table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase = new Phrase("Item",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Cons.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Avg Cons.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Bp",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Proj. Cons.",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Cost",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Sales",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Profit",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("%margin",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            double Consp = java.lang.Double.parseDouble(Consumption);
                            double Mark = java.lang.Double.parseDouble(MarkUp);
                            
                            java.sql.Statement st11 = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.ResultSet rset11 = st.executeQuery("select round(sum((sc.receiving-sc.issuing)/(st.packaging*so.average_day)*st.buying_price)*'"+Consp+"'*'"+MarkUp+"',2) from st_sub_stores sc, st_stock_item st, st_ordering_constants so where sc.item = st.description and sc.receiving >0 and sc.trans_date < current_date - (so.average_day)::integer and st.department ilike '"+Department+"'");
                            while(rset11.next()){
                                Total = rset11.getDouble(1);
                            }
                            java.lang.Object[] listofAct = this.getListofActivities();
                            for (int i = 0; i < listofAct.length; i++) {
                                java.sql.ResultSet rset = st.executeQuery("select distinct(st.description),st.buying_price,ROUND(sum(sc.receiving-sc.issuing)),round(sum(sc.receiving-sc.issuing),2) ,round(sum(sc.receiving-sc.issuing)*'"+Consp+"'),round(sum(sc.receiving-sc.issuing)*st.buying_price*'"+Consp+"',2) as sales from st_sub_stores sc, st_stock_item st, st_ordering_constants so WHERE sc.item = '"+listofAct[i]+"' AND st.description = '"+listofAct[i]+"' and sc.receiving >0 AND st.department ilike '"+Department+"' AND sc.store_name ilike '"+Department+"' group by st.description,st.buying_price order by sales desc");
                                
                                java.sql.Statement st1 = connectDB.createStatement();
                                
                                while (rset.next()) {
                                    double osBalancet = 0.00;
                                    double osBalancet1 = 0.00;
                                    double bp = 0.00;
                                    double pc = 0.00;
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "0"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "0"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "0"), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    bp = rset.getDouble(2);
                                    
                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "0"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    pc = rset.getDouble(5);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bp*pc)),pFontHeader1);
                                    
                                    osBalance1 = osBalance1 + bp*pc;
                                    osBalancet1 = osBalancet1 + bp*pc;
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bp*pc*Mark)),pFontHeader1);
                                    
                                    osBalance = osBalance + (bp*pc*Mark);
                                    osBalancet = osBalancet + (bp*pc*Mark);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancet-osBalancet1)),pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalancet-osBalancet1)*100/Total)),pFontHeader1);
                                    
                                    table.addCell(phrase);
                                }
                            }
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setColspan(5);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)),pFontHeader);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)),pFontHeader);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance-osBalance1)),pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                
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
                
                 
                
                try {
                    
                    if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                        
                        System.out.println(tempFile);
                        
                        wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    } else {
                        
                        wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                        
                        wait_for_Pdf2Show.waitFor();
                        
                    }
                    
                } catch(java.lang.InterruptedException intrExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                    
                }
                
                
                
            } catch(java.io.IOException IOexec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
                
            }
            
            
            
        }
        
        public java.lang.Object[] getListofActivities() {
            
            java.lang.Object[] listofActivities = null;
            
            java.util.Vector listActVector = new java.util.Vector(1,1);
            
            
            try {
                
                java.sql.Statement stmt1 = connectDB.createStatement();
                
                java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM st_stock_item EXCEPT  SELECT DISTINCT item FROM st_sub_stores where  trans_date > current_date - ('"+Consumption+"')::integer");
                
                
                while (rSet1.next()) {
                    //if (rSet1.getFloat(1) > 0){
                    listActVector.addElement(rSet1.getObject(1).toString());
                    //}
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
    
    
    
    
    
