///Author Charles Waweru

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


public class PatientMedPdf implements java.lang.Runnable {
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
      com.lowagie.text.Font pFontHeader111 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
   
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void PatientMedPdf(java.sql.Connection connDb,  java.lang.String combox) {
        MNo = combox;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        //beginDate = begindate;
        
        // endDate = endate;
        
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
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient Medical Report - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
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
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {15,15,15,25};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(4);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        String Exam = null;
                        String Doctor = null;
                        String Hist=null;
                        String visit=null;
                        String reg=null;
                        try {
                            java.sql.Statement st2T = connectDB.createStatement();
                            java.sql.Statement st2d = connectDB.createStatement();
                            
                            
                            
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            java.sql.Statement sth = connectDB.createStatement();
                            java.sql.Statement stp = connectDB.createStatement();
                            java.sql.Statement st2c = connectDB.createStatement();
                            java.sql.Statement st2e = connectDB.createStatement();
                            java.sql.Statement sth2 = connectDB.createStatement();
                            java.sql.Statement sth22 = connectDB.createStatement();
                            java.sql.Statement st2c1 = connectDB.createStatement();
                            java.sql.Statement st2c11 = connectDB.createStatement();
                            
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MEDICAL REPORT", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                              table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                          
                            phrase = new Phrase("STRICTLY CONFIDENTIAL", pFontHeader11);
                            table.addCell(phrase);
                             //java.sql.ResultSet rseth = stp.executeQuery("select distinct patient_no,initcap(first_name||' '||second_name),Category,residence,tel_no,last_visit,sex,nok,year_of_birth,pay_mode from hp_patient_register where patient_no = '"+memNo+"'");
                            java.sql.ResultSet rseth = sth.executeQuery(" select distinct patient_no,upper(patient_name),req_by,sex,patient_state,date_req,adm_date,discharge_date,rec_name,desig,org,box,tel,comp,find_phy,invest,diagnosis,mngmt,recom,doctor,discharge_no from hp_med_rep where discharge_no= '"+memNo+"'  ");
                          ///  table.getDefaultCell().setColspan(4);
                            while (rseth.next()) {
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Serial No :  "+dbObject.getDBObject(rseth.getObject(21), "-"), pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                                 table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("This report is strictly confidential and is restricted to the recipient/ addressee and solely for the stated purpose.", pFontHeader111);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.  "+rseth.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name : "+rseth.getObject(2).toString(), pFontHeader11);
                                table.addCell(phrase);
                                 
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Requested By : "+dbObject.getDBObject(rseth.getObject(3), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                //  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Sex/Age  "+dbObject.getDBObject(rseth.getObject(4), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Stated Purpose : "+dbObject.getDBObject(rseth.getObject(5), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("Date Requested  : "+dbObject.getDBObject(rseth.getObject(6), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                //phrase = new Phrase("Next Of Kin : "+dbObject.getDBObject(rseth.getObject(8), "-"), pFontHeader11);
                                // table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("D.O.A : "+dbObject.getDBObject(rseth.getObject(8), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("D.O.D : "+dbObject.getDBObject(rseth.getObject(7), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("STATED RECIPIENT : ", pFontHeader11);
                                table.addCell(phrase);
                               
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Name : "+dbObject.getDBObject(rseth.getObject(9), "-"), pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase("Designation :  "+dbObject.getDBObject(rseth.getObject(10), "-"), pFontHeader);
                                table.addCell(phrase);
                              //  phrase = new Phrase(dbObject.getDBObject(rseth.getObject(10), "-"), pFontHeader);
                               // table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                               
                                phrase = new Phrase("Organisation:  "+dbObject.getDBObject(rseth.getObject(11), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(2);

                                phrase = new Phrase("Box :  "+dbObject.getDBObject(rseth.getObject(12), "-"+ " Tel :  "+dbObject.getDBObject(rseth.getObject(13), "-")), pFontHeader);
                                table.addCell(phrase);
                                
                               
                               
                                
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Dear Sir/Madam", pFontHeader11);
                                table.addCell(phrase);
                                 phrase = new Phrase("Following a request by the person stated and for the purpose here above stated, we have issued a medical report hereuder: ", pFontHeader111);
                                table.addCell(phrase);
                                 phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Presenting Complaints :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(14), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Physical Findings :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(15), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Investigations Done :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(16), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Diagnosis :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(17), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Management :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(18), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Summary Recommedations :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(19), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Dr. name :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rseth.getObject(20), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                
                                phrase = new Phrase("Signature :  ", pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Date :  ", pFontHeader11);
                                table.addCell(phrase);
                                
                            }
                            
                            
                            table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("LIMITING CONDITIONS", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP);
                        
                        table.getDefaultCell().setColspan(4);
                        
                          
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                       // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setColspan(4);
                                  phrase = new Phrase("This report is strictly confidential and is restricted to the recipient/ addressee and solely for the stated purpose.", pFontHeader111);
                                table.addCell(phrase);
                                    table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                        
                        table.getDefaultCell().setColspan(4);
                        
                          
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                       // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("The report is issued on a without prejudice basis and neither the author nor the hospital shall be held liable or responsible to any consequencies arising out of issuance of the report  ", pFontHeader111);
                                table.addCell(phrase);
                            
                           //  table.getDefaultCell().setColspan(2);
                                
                                phrase = new Phrase(".No part of this report may be used for any other purpose or produced,stored in a retrieval system or transmitted in any form,by any macro,electronic,electrostatic,mechanical,photocopied or otherwise withoutthe express written permission of Metropolitan Hospital Nairobi.  ", pFontHeader111);
                                table.addCell(phrase);
                              phrase = new Phrase("This report is issued without any alterations whatsoever", pFontHeader111);
                                table.addCell(phrase);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(4);
                        
                          
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                       // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                             table.getDefaultCell().setColspan(4);
                              phrase = new Phrase("This report is not valid unless it bears the hospital seal and is signed by a qualified doctor and the Health Records Officer", pFontHeader111);
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
    
    
    
    
    
    
}





