//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hr;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.sql.SQLException;


public class DutyRota implements java.lang.Runnable {
    
    public static java.sql.Connection connectDB = null;
    public int duty_rota_id;
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String dept = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void AllApplicantResumePdf(java.sql.Connection connDb) {
        
        // dept = combox;
        
        connectDB = connDb;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        // beginDate = begindate;
        
        //  endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public String getStaffName(String staff_id){
        String staff_name=" ";
        try{
            java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st4.executeQuery("select first_name,second_name,staff_id from hr.hr_staffresume_fileinfo where staff_id='"+staff_id+"'");
            while(rset2.next()){
               String fname=rset2.getObject(1).toString().trim();
               fname=fname.substring(0, 1);
               staff_name=fname+". "+rset2.getObject(2).toString().trim();
            }
        }catch(Exception e){
            
        }
        return staff_name;
    }
    
    public static void main(java.lang.String[] args) {
        
        //	new CashBookListPdf().CashBookListPdf();
        
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
            
            java.io.File tempFile = java.io.File.createTempFile("HR_REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
           // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
     
                try{
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
     /*    try {
      
            java.lang.Class.forName("org.postgresql.Driver");
      
        } catch(java.lang.ClassNotFoundException cnfExec) {
      
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
      
        }
      */
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT UPPER(organisation_name) from hr.hr_company_profile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"",pFontHeader),false);
                        
                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(7);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(
                    "FunSoft Human Resources Management Systems                         Printed On: "+date+"               Page:",pFontHeader2), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);
                        
                        int headerwidths[] = {15,7,7,15,10,8,8,7,7,8};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((99));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        
                        table.getDefaultCell().setColspan(10);
                        String obj="DUTY ROSTA REPORT";
                        try{
                         java.sql.Statement st3 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("select * from hr.hr_duty_rota where duty_rota_id='"+this.duty_rota_id+"'");
                        while(rset2.next()){
                         obj=rset2.getObject(2).toString();
                        }
                                }catch(Exception e){
                                    System.err.println("ERROR IS "+e);
                                }
                        
                        Phrase phrase = new Phrase("");
                        
                        table.getDefaultCell().setColspan(7);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                       
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase(obj, pFontHeader3);
                        
                        table.addCell(phrase);
                        
                        
                        
                        phrase = new Phrase(" " , pFontHeader2);
                        
                        table.addCell(phrase);
                        
                        
                        //  while (rset1.next()) {
                        
                        
                        
                        table.getDefaultCell().setColspan(1);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Shift",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("From",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        phrase = new Phrase("To",pFontHeader);
                        table.addCell(phrase);
                         table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("MON",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("TUE",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("WED",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("THUR",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("FRI",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        phrase = new Phrase("SAT",pFontHeader);
                        table.addCell(phrase);
                        
                         phrase = new Phrase("SUN",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        try {
                          //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            
                            java.sql.Statement st1 = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                              java.sql.Statement st3 = connectDB.createStatement();
                            //for (int j = 0; j < listofStaffNos.length; j++) {
                                
                                java.sql.ResultSet rset1 = st1.executeQuery("select *  from hr.hr_duty_rota_shifts where hr.hr_duty_rota_shifts.duty_rota_id='"+this.duty_rota_id+"'");
                              //  java.sql.ResultSet rset2 = st2.executeQuery("select edu_level from hr.hr_appresume_qualif");
                                
                                
                                String staff_name="";
                                
                                while (rset1.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"));
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(6), "-"));
                                    
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"));
                                   
                                    table.addCell(phrase);
                                    
                                    try{
                                    
                                  java.sql.ResultSet sheet = st3.executeQuery("select *  from hr.hr_duty_rota_reports where hr.hr_duty_rota_reports.shift_id='"+rset1.getObject(1)+"'");
                                 
                                    
                                    int r=0;
                                    int x=0;
                                    
                                         table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                         table.getDefaultCell().setColspan(1);
                                         
                                         if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                         
                                         
                                    if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                    
                                       
                                    if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                    
                                    
                                    if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                    
                                    
                                    if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                    
                                    if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                    
                                    
                                     if(sheet.next()){
                                         staff_name=getStaffName(sheet.getObject(4).toString());        
                                    phrase = new Phrase(staff_name);
                                    table.addCell(phrase);
                                         }else{
                                               phrase = new Phrase("");
                                    table.addCell(phrase);
                                         }
                                         
                                 
                                    
                                }catch(SQLException sq){
                                    
                                }
                                    
                                    
                                 
                                }
                                
                                
                                
                                
                            //}
                            
                            docPdf.add(table);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
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
        
    }

    






