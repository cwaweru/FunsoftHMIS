//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class DetailedShiftReportPdf implements java.lang.Runnable {
    java.lang.String memNo = null;
    
    java.lang.String UserName = null;
    
    java.lang.String CashPoint = null;
    
    java.lang.String beginDate = null;
    String ks;
    java.lang.String endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    public void DetailedShiftReportPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint,java.lang.String userName) {
        
        //  public void ShiftReportDetailedPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint) {
        memNo = combox;
        UserName = userName;
        
        CashPoint = cashPoint;
        
        connectDB = connDb;
        
        //  beginDate = begindate;
        
        /// endDate = endate;
        threadSample = new java.lang.Thread(this,"SampleThread");
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Shift Report Breakdown- Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    double amt = 0.00;
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {11,10,18,10,17,10,13};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        table.setHeaderRows(3);
                        Phrase phrase = new Phrase(" ", pFontHeader);
                        
                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        phrase = new Phrase("CASHIER'S SHIFT REPORT BREAKDOWN", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        
                        phrase = new Phrase("Date : "+date, pFontHeader);
                        table.addCell(phrase);
                        
                        // phrase = new Phrase("SHIFT No. FROM :     CASHIER : ", pFontHeader);
                        ///table.addCell(phrase);
                        
                        
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            System.out.println(listofAct.length);
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.ResultSet rsetTotals1 = st2.executeQuery("SELECT SUM(debit),sum(credit),sum(debit-credit) from ac_cash_collection WHERE shift_no = '"+memNo+"'  AND cash_point = '"+CashPoint+"'");
                            java.sql.ResultSet rset2 = st3.executeQuery("select user_name,shift_no,status from ac_shifts where shift_no = '"+memNo+"'");
                            
                            while (rset2.next()){
                                table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Shift No. : "+rset2.getObject(2).toString(), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Cashier :  "+rset2.getObject(1).toString(), pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Status :  "+rset2.getObject(3).toString(), pFontHeader);
                            table.addCell(phrase);
                            }
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            // table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date", pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Receipt No.", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Payer", pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Service", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Amount "+ks, pFontHeader);
                            table.addCell(phrase);
                            
                            
                            
                         
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            //   java.lang.Object[] listofAct = this.getListofStaffNos();
                            for (int i = 0; i < listofAct.length; i++) {
                                
                                //  java.lang.Object[] listofAct1 = this.getListofActivities(listofAct[i]);
                                
                                java.sql.Statement st1 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset1 = st1.executeQuery("select distinct date,receipt_no,initcap(dealer),sum(debit-credit) from ac_cash_collection where shift_no = '"+memNo+"'  AND cash_point = '"+CashPoint+"' and receipt_no ILIKE '"+listofAct[i].toString()+"' group by date,receipt_no,dealer");
                                if(i == 0){
                                    
                                    while (rset1.next()){
                                        
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader);
                                        
                                        
                                        //table.addCell(phrase);
                                        phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(4);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(" ", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        //}
                                        java.sql.Statement st22 = connectDB.createStatement();
                                        java.sql.Statement st5 = connectDB.createStatement();
                                        
                                        java.sql.ResultSet rset = st5.executeQuery("select initcap(description),sum(debit-credit) from ac_cash_collection where shift_no = '"+memNo+"' AND cash_point = '"+CashPoint+"' and receipt_no = '"+listofAct[i].toString()+"' group by description order by description");
                                        
                                        while (rset.next()) {
                                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setColspan(4);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("  ", pFontHeader);
                                            
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(2);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)),pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                        
                                        table.getDefaultCell().setColspan(4);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("  ", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                        
                                        table.getDefaultCell().setBorder(Rectangle.TOP);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Total", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)),pFontHeader);
                                        amt = amt + rset1.getDouble(4);
                                        table.addCell(phrase);
                                        
                                        // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt)),pFontHeader);
                                        
                                        //table.addCell(phrase);
                                    }
                                }else{
                                    while (rset1.next()){
                                        
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        phrase = new Phrase(" ", pFontHeader);
                                        
                                        
                                        table.addCell(phrase);
                                        phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(rset1.getObject(3).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(4);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(" ", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        //}
                                        java.sql.Statement st22 = connectDB.createStatement();
                                        java.sql.Statement st5 = connectDB.createStatement();
                                        
                                        java.sql.ResultSet rset = st5.executeQuery("select initcap(description),sum(debit-credit) from ac_cash_collection where shift_no = '"+memNo+"' AND cash_point = '"+CashPoint+"' and receipt_no = '"+listofAct[i].toString()+"' group by description order by description");
                                        
                                        // while (rset22.next()) {
                                        while (rset.next()) {
                                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setColspan(4);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("  ", pFontHeader);
                                            
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(2);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)),pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                        
                                        table.getDefaultCell().setColspan(4);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("  ", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                        
                                        table.getDefaultCell().setBorder(Rectangle.TOP);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Total", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)),pFontHeader);
                                        amt = amt + rset1.getDouble(4);
                                        table.addCell(phrase);
                                        
                                        // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt)),pFontHeader);
                                        
                                        //table.addCell(phrase);
                                    }
                                }
                            }
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                        
                        
                        table.getDefaultCell().setColspan(4);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Grand Total", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(3);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        
                        
                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amt)),pFontHeader);
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
        
        docPdf.close();
        
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
        
        //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
        
        java.sql.Statement stmt1 = connectDB.createStatement();
        
        java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT receipt_no FROM ac_cash_collection where shift_no = '"+memNo+"'   AND cash_point = '"+CashPoint+"' order by receipt_no");
        
        while (rSet1.next()) {
            
            listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());
            
        }
        
    }catch (java.sql.SQLException sqlExec) {
        
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
        
    }
    
    listofActivities = listActVector.toArray();
    System.out.println("Done list of activities ...");
    return listofActivities;
}


   /* public java.lang.Object[] getListofActivities1(java.lang.Object suppName) {
    
        java.lang.Object[] listofActivities1 = null;
    
        java.util.Vector listActVector1 = new java.util.Vector(1,1);
    
    
        try {
    
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
            java.sql.Statement stmt1 = connectDB.createStatement();
    
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT receipt_no,dealer FROM ac_cash_collection where  shift_no = '"+memNo+"' and payment_mode = '"+suppName+"'  AND cash_point = '"+CashPoint+"'  order by receipt_no");
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT code FROM pb_activity order by code");
    
            while (rSet1.next()) {
    
                listActVector1.addElement(rSet1.getObject(1).toString());
    
            }
    
        }catch (java.sql.SQLException sqlExec) {
    
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
        }
    
        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities1 ...");
        return listofActivities1;
    }
    
    
    public java.lang.Object[] getListofActivities11() {
    
        java.lang.Object[] listofActivities11 = null;
    
        java.util.Vector listActVector11 = new java.util.Vector(1,1);
    
    
        try {
    
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
            java.sql.Statement stmt1 = connectDB.createStatement();
    
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT activity_code FROM ac_cash_collection where shift_no = '"+memNo+"' order by activity_code");
    
            while (rSet1.next()) {
    
                listActVector11.addElement(rSet1.getObject(1).toString().toUpperCase());
    
            }
    
        }catch (java.sql.SQLException sqlExec) {
    
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
        }
    
        listofActivities11 = listActVector11.toArray();
        System.out.println("Done list of activities11 ...");
        return listofActivities11;
    }
    */
}





