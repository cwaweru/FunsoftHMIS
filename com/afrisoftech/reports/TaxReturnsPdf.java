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


public class TaxReturnsPdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    int jan = 0;
    
    int feb = 0;
    
    int mar = 0;
    
    int april = 0;
    
    int may = 0;
    
    int jun = 0;
    
    int jul = 0;
    
    int aug = 0;
    
    int sep = 0;
    
    int oct = 0;
    
    int nov = 0;
    
    int dec = 0;
    String ks;
    java.lang.String months = null;
    
    double basic = 0.00;
    
    double non = 0.00;
    
    double valq = 0.00;
    
    double gross = 0.00;
    
    double contri = 0.00;
    
    double fixed = 0.00;
    
    double owner = 0.00;
    
    double retir = 0.00;
    
    double chargable = 0.00;
    
    double tax = 0.00;
    
    double prelief = 0.00;
    
    double paye = 0.00;
    
    double actual = 0.00;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    Phrase phraseA;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD);
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void TaxReturnsPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberListPdf().MemberListPdf();
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    
                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("KENYA REVENUE AUTHORITY "+"\n INCOME TAX DEPARTMENT "+"\n INCOME TAX DEDUCTION CARD YEAR "+endDate,pFontHeader3),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    
                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    headerFoter.setBorderColor(java.awt.Color.WHITE);
                    docPdf.setHeader(headerFoter);
                    
                    
                    //         com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("P9A \t\t\t\t\t (See back of this card for further information required by the Department)."), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    //         docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    try {
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        //  for (int j = 0; j < listofStaffNos.length; j++) {
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {12,25,10,10,10,12,10,12};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM |Rectangle.TOP);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        table.getDefaultCell().setColspan(8);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        // table.addCell(phrase);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT| Rectangle.RIGHT |Rectangle.TOP);
                        
                        table.getDefaultCell().setColspan(1);
                        try{
                        //  table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("YEAR",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("DESCRIPTION",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("DEBIT",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("CREDIT",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("TAX/DIVINDED",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("DATE PAID",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("TAX REFUND "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("DATE REFUNDED",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        
                        
                        java.sql.Statement st = connectDB.createStatement();
                        
                        java.sql.Statement st11b = connectDB.createStatement();
                        java.sql.ResultSet rset1 = st.executeQuery("select tax_year,item_description,debit,credit,tax_divided,case when (date_paid is null) then 'now' else date_paid end as date_paid,tax_refunded,case when (date_refunded is null) then 'now' else date_refunded end as date_refunded from ac_corporation_tax where tax_year BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                        
                        
                        while (rset1.next()) {
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                            phrase = new Phrase(rset1.getObject(1).toString(), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase(rset1.getObject(2).toString().toUpperCase(), pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(3)), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                          //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)), pFontHeader1);
                          //  table.addCell(phrase);
                            
                            phrase = new Phrase(rset1.getObject(6).toString(), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase(rset1.getObject(8).toString(), pFontHeader1);
                            table.addCell(phrase);
                        }
                        
                        docPdf.add(table);
                        
                        
                       /* } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                        }*/
                        System.out.println("Status of new page : "+docPdf.getPageNumber());
                        
                        
                        boolean boolNewPage = docPdf.newPage();
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    // }
                    
                } catch(com.lowagie.text.BadElementException BadElExec) {
                    
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                    
                    
                }
                //
                
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
                //      wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                
                wait_for_Pdf2Show = rt.exec("xpdf "+tempFile+"");
                
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
     
        
        
   // }
 /*   public java.lang.Object[] getListofStaffNos() {
  
        java.lang.Object[] listofStaffNos = null;
  
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
  
  
        try {
  
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
  
            java.sql.Statement stmt1 = connectDB.createStatement();
  
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM tax_card where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by staff_no");
  
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
  
    public java.lang.Object[] getListofStaffNos1() {
  
        java.lang.Object[] listofStaffNos1 = null;
  
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
  
  
        try {
  
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
  
            java.sql.Statement stmt1 = connectDB.createStatement();
  
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT month FROM tax_card where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by month");
  
            while (rSet1.next()) {
  
                listStaffNoVector1.addElement(rSet1.getObject(1).toString());
  
            }
  
        }catch (java.sql.SQLException sqlExec) {
  
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
  
        }
  
        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
  
    }*/
}
}





