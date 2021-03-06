//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.laboratory;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class TimelogPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    double osBalance = 0.00;
    double current = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void TimelogPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
        MNo = combox;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        beginDate = begindate;
        
        endDate = endate;
        
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
            
            this.generatePdf(MNo);
            
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
                 /*   try {
                  
                  
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                  
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next())
                            compName = rset2.getObject(1).toString();
                  
                        while(rset4.next())
                            date = rset4.getObject(1).toString();
                  
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"                                                        Printed On: "+date+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                  
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                  
                    } catch(java.sql.SQLException SqlExec) {
                  
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                  
                    }
                  */
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient Time Track - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    String req = null;
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        // java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {15,15,30,15,15,15,15};
                        
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
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement st4 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");
                            
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(7);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
                            }
                            while (rset4.next()){
                                table1.getDefaultCell().setColspan(3);
                                date = rset4.getObject(1).toString();
                                //  phrase = new Phrase("Printed On  :" +date , pFontHeader);
                                
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
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {20,10,10,10,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.RIGHT|Rectangle.LEFT|Rectangle.TOP);
                        
                        table.getDefaultCell().setColspan(6);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        java.util.Date Billing2 = null;
                        
                        try {
                            java.sql.Statement stl = connectDB.createStatement();
                            java.sql.Statement stx = connectDB.createStatement();
                            
                            java.sql.Statement std = connectDB.createStatement();
                            
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            
                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no,initcap(description),initcap(payer) from hp_patient_register where patient_no = '"+memNo+"'");
                            // java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(amount) from patient_bill where patient_no = '"+memNo+"'");
                            
                            
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Time Track", pFontHeader11);
                            table.addCell(phrase);
                            
                            
                            
                              table.getDefaultCell().setColspan(3);
                            try {
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");
                                
                                while (rset4.next()){
                                   // table.getDefaultCell().setColspan(3);
                                    date = rset4.getObject(1).toString();
                                    //  phrase = new Phrase("Printed On  :" +date , pFontHeader);
                                    
                                   // table1.addCell(phrase);
                                }
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                                
                                //  table.addCell(phrase);
                                
                                
                                phrase = new Phrase("Period : " +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), pFontHeader1);
                                
                                table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Printed On  :" +date , pFontHeader1);
                            
                            table.addCell(phrase);
                            
                          
                            
                            
                            
                            
                            
                            
                            
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.:  "+memNo, pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                // phrase = new Phrase("Patient Name  "+rset.getObject(2).toString(), pFontHeader);
                                phrase = new Phrase("Patient Name : "+dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address:  "+dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No.: "+dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Scheme:  "+dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Payer: "+dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            java.sql.ResultSet rset1 = st1.executeQuery("select input_date::time(0),input_date::time(0) from hp_patient_visit where patient_no = '"+memNo+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            java.sql.ResultSet rset12 = st12.executeQuery("select input_date::time(0),input_date::time(0) from hp_signs_record where patient_no = '"+memNo+"' and input_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            
                            
                            while (rset1.next()){
                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Registration", pFontHeader11);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("-", pFontHeader);
                                
                                table.addCell(phrase);
                                phrase = new Phrase("-", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase("-", pFontHeader);
                                
                                table.addCell(phrase);
                                
                            }
                            while (rset12.next()){
                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Observation", pFontHeader11);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rset12.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset12.getObject(2), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("-", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase("-", pFontHeader);
                                
                                table.addCell(phrase);
                                phrase = new Phrase("-", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase(" ",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Desription",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Request",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Billing",pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Bill-Req",pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Results",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Res -Bill",pFontHeader1);
                            table.addCell(phrase);
                             
                            
                            java.sql.ResultSet rsetd = std.executeQuery("select distinct initcap(service),curr_date::time(0) from pb_doctors_request where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by curr_date::time(0)");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            while (rsetd.next()){
                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                req = dbObject.getDBObject(rsetd.getObject(1), "-");  
                                // table.addCell(phrase);
                                
                                java.sql.ResultSet rset13 = st13.executeQuery("select initcap(service),curr_date::time(0) from pb_doctors_request where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' and service ilike '"+req+"' order by curr_date::time(0)");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset14 = st14.executeQuery("select distinct date_curr::time(0) as date from hp_pharmacy where patient_no = '"+memNo+"' and date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' and description ilike '"+req+"'  Union All  select distinct date_curr::time(0) as date from hp_patient_billing where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' and service ilike '"+req+"' order by date");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                //  java.sql.ResultSet rset15 = st15.executeQuery("select initcap(service),date_curr::time,date_curr::time from hp_patient_billing where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rsetl = stl.executeQuery("select distinct input_date::time(0) as date from hp_lab_results where patient_no = '"+memNo+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' and typeof_test ilike '"+req+"' union all select distinct input_date::time(0) as date from hp_xray_results where patient_no = '"+memNo+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' and examination ilike '"+req+"' order by date");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                //  java.sql.ResultSet rsetx = stx.executeQuery("select distinct initcap(examination),input_date::time,input_date::time from hp_xray_results where patient_no = '"+memNo+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by input_date::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                
                                //java.util.TimeZone
                                
                                while (rset13.next()){
                                    long Rqtime;
                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase = new Phrase(dbObject.getDBObject(rset13.getObject(1), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset13.getObject(2), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    Rqtime = rset13.getTime(2).getTime();
                                    
                                    
                                    
                                    
                                    // }
                                    long Billing =0;
                                    while (rset14.next()){
                                        table.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        // Billing =rset14.getTime(1).getTime();
                                        Billing2 =rset14.getTime(1);
                                        Billing =rset14.getTime(1).getTime();
                                    }
                                    
                                    if(Billing2 ==null){
                                        phrase = new Phrase( "-", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase( "-", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                    }else{
                                        
                                        phrase = new Phrase(""+Billing2, pFontHeader);
                                        
                                        table.addCell(phrase);
                                        long Btime ;
                                        long rqtime =Rqtime;
                                        Btime = Billing-rqtime;
                                        phrase = new Phrase(""+Btime/60000, pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                    }
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    
                                    // String Result = null;
                                    java.util.Date Result = null;
                                    long Result1 =0;
                                    while (rsetl.next()){
                                        table.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        //  Result =rsetl.getObject(1).toString();
                                        Result =rsetl.getTime(1);
                                        Result1 =rsetl.getTime(1).getTime();
                                    }
                                    
                                    // String Result = null;
                                    java.util.Date Rtime = null;
                                    
                                    
                                    
                                    
                                    if( Result ==null){
                                        phrase = new Phrase( "-", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        phrase = new Phrase( "-", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        
                                    }else{
                                        
                                        phrase = new Phrase(""+ Result, pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        
                                        long Btime ;
                                        Btime = Result1-Billing;
                                        phrase = new Phrase(""+Btime/60000, pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        //}
                                        
                                    }
                                }
                                
                                
                            }
                            
                            
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
    
    
    
    
    
    
}





