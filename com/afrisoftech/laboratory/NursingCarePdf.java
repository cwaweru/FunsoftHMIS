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


public class NursingCarePdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    java.lang.String MNo1 = null;
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    double balance = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
      
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
  
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void NursingCarePdf(java.sql.Connection connDb, java.lang.String combox,java.lang.String combox1) {
        MNo = combox;
        MNo1 = combox1;
        connectDB = connDb;
        
        
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
    
    
    public void generatePdf(java.lang.String MNo) {
        
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
                
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Medical in Confidence - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                     try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {12,15,30,15,10,15,10,18};
                        
                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));
                        
                        
                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table1.getDefaultCell().setColspan(8);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name,current_date from pb_header");
                            while (rset3.next())
                                table1.getDefaultCell().setColspan(8);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                            table1.addCell(phrase);
                            date = rset3.getObject(2).toString();
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {12,20,15,20,17,15,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(5);
                        Phrase phrase = new Phrase("");
                           phrase = new Phrase("NURSING CARE PLAN", pFontHeader1);
                                table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Printed on : " +date, pFontHeader1);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        try {
                            java.sql.Statement stc = connectDB.createStatement();
                           
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement sta = connectDB.createStatement();
                            
                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select patient_no,initcap(first_name||' '||second_name),upper(category),initcap(unit) from hp_inpatient_register where patient_no = '"+MNo+"'");
                              table.getDefaultCell().setColspan(7);
                               java.sql.ResultSet rseta = sta.executeQuery("select distinct ward,bed_no,admission_reason,date_admitted from hp_admission where patient_no = '"+MNo+"' order by date_admitted asc limit 1");
                           java.sql.ResultSet rsetc = stc.executeQuery("select distinct sex,age,weight,food_all,drug_all,religion,doctor,other_docs,date from hp_care_plan where patient_no = '"+MNo+"' order by date desc limit 1");
                          
                              
                                  java.sql.ResultSet rsetN = st1.executeQuery("select DATE::date ||' '||date::time(0),diag,outcome,care_plan,intervention,evaluation,user_name from hp_care_plan where patient_no = '"+MNo+"'");
                        
                              
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(5);
                           
                                 while (rset.next()) 
                               
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("SERVICE NO   " +rset.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                 table.getDefaultCell().setColspan(2);
                                 while (rseta.next()) 
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("DOA   " +rseta.getObject(4).toString(), pFontHeader1);
                                table.addCell(phrase);
                                  table.getDefaultCell().setColspan(5);
                                 while (rset.next()) 
                              
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("NAME : "+rset.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                                 table.getDefaultCell().setColspan(2);
                                 while (rseta.next()) 
                                    
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("WARD : "+rseta.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                               table.getDefaultCell().setColspan(5);
                                 while (rset.next()) 
                                   //  table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("RANK  : " +rset.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);
                                 table.getDefaultCell().setColspan(2);
                                 while (rseta.next()) 
                                   //  table.getDefaultCell().setColspan(2);
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("BED NO : "+rseta.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                               table.getDefaultCell().setColspan(5);
                                 while (rset.next()) 
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("UNIT   : "+rset.getObject(4).toString(), pFontHeader1);
                                table.addCell(phrase);
                                  table.getDefaultCell().setColspan(2);
                                while (rseta.next()) 
                                  
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("DIAGNOSIS : "+rseta.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);
                               
                           
                                  while (rsetc.next()){ 
                                    table.getDefaultCell().setColspan(2);
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Sex : "+rsetc.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                               table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Age   : "+rsetc.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                  table.getDefaultCell().setColspan(3);
                            
                                  
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Weight : "+rsetc.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);
                               table.getDefaultCell().setColspan(2);
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Food Allergy : "+rsetc.getObject(4).toString(), pFontHeader1);
                                table.addCell(phrase);
                               table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Drug Allergy   : "+rsetc.getObject(5).toString(), pFontHeader1);
                                table.addCell(phrase);
                                  table.getDefaultCell().setColspan(3);
                            
                                  
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Religion : "+rsetc.getObject(6).toString(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Primary Doctor : "+rsetc.getObject(7).toString(), pFontHeader1);
                                table.addCell(phrase);
                               table.getDefaultCell().setColspan(4);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Other Doctor   : "+rsetc.getObject(8).toString(), pFontHeader1);
                                table.addCell(phrase);
                                 
                                  
                                  }
                                
                                
                                 table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("DATE", pFontHeader1);
                        
                        table.addCell(phrase);
                             phrase = new Phrase("Nursing Diag", pFontHeader1);
                        
                        table.addCell(phrase);
                         phrase = new Phrase("Exp Outcome", pFontHeader1);
                        
                        table.addCell(phrase);
                             phrase = new Phrase("Plan of Care", pFontHeader1);
                        
                        table.addCell(phrase);
                         phrase = new Phrase("Intervention", pFontHeader1);
                        
                        table.addCell(phrase);
                             phrase = new Phrase("Evaluation", pFontHeader1);
                        
                        table.addCell(phrase);
                         phrase = new Phrase("Nurse", pFontHeader1);
                        
                        table.addCell(phrase);
                             
                        
                                table.getDefaultCell().setBorder(Rectangle.RIGHT|Rectangle.LEFT);
                             while (rsetN.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rsetN.getObject(1).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rsetN.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(rsetN.getObject(3).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rsetN.getObject(4).toString(), pFontHeader);
                                table.addCell(phrase);
                              
                               phrase = new Phrase(rsetN.getObject(5).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rsetN.getObject(6).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                               phrase = new Phrase(rsetN.getObject(7).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                               
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





