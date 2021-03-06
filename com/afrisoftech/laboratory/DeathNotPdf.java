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


public class DeathNotPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    
     com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    double balance = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
  
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DeathNotPdf(java.sql.Connection connDb, java.lang.String combox) {
        MNo = combox;
        
          dbObject = new com.afrisoftech.lib.DBObject();
        
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
         //    com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
           try {
                
                try {
                   
        
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    
                    
                    String compName = null;
                    String date = null;
                  
                        
                   
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Note: To be filled in duplicates: original copy-Next of kin who should sign upon collection "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
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
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(2);
                        
                        int headerwidths[] = {50,50,};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(1);
                        Phrase phrase = new Phrase("");
                       
                         
                      
                         phrase = new Phrase("NOTIFICATION OF DEATH", pFontHeader);
                        
                        table.addCell(phrase);
                          
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Printed on : " +date, pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                             java.sql.Statement st21 = connectDB.createStatement();
                         
                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select initcap(diceased_name),age_no,gender,op_no,dbox_no,destate,dtel_no,dhouse_no,brought_by,vrelation,vbox,vtel,vno,vlicence,nok,nestate,nstreet,nhouse,brought_from,pname,pid,pbox,ptel,prelation,pofdeath,pupils,stimuli,sounds,cardiac,morris,others,police_station,date,stime,sname,designation,cname,crelation,ctel,cid,dstreet_no from hp_death_notification where sheet_no ilike '"+MNo+"'");
                            //java.sql.ResultSet rset1 = st1.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking  from hp_patients_hist where patient_no = '"+memNo+"'  ");
                            
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Name of person : "+rset.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("age : "+rset.getObject(2).toString()+ "         Sex : "+rset.getObject(3).toString(), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("OP/IP No : "+dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                 table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Physical Address", pFontHeader);
                                table.addCell(phrase);
                                  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Box  : "+dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Estate  : "+dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No.  "+dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Street/road : "+dbObject.getDBObject(rset.getObject(41), "-"), pFontHeader11);
                                table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("House No.  : "+dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                   table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                
                                  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Body in by (full names)  : "+dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Relationship  : "+dbObject.getDBObject(rset.getObject(10), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Box No.  "+dbObject.getDBObject(rset.getObject(11), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Vehical reg No : "+dbObject.getDBObject(rset.getObject(13), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                
                                  
                                  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Tel  : "+dbObject.getDBObject(rset.getObject(13), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("D/Licence  : "+dbObject.getDBObject(rset.getObject(14), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                  table.getDefaultCell().setColspan(2);
                                  
                                   table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                  
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Next of kin", pFontHeader);
                                table.addCell(phrase);
                               //   table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Full Names  : "+dbObject.getDBObject(rset.getObject(15), "-"), pFontHeader);
                                table.addCell(phrase);
                                
                                 phrase = new Phrase("Physical address", pFontHeader);
                                table.addCell(phrase);
                              table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Estate  : "+dbObject.getDBObject(rset.getObject(16), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("House No.  "+dbObject.getDBObject(rset.getObject(17), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Street/road : "+dbObject.getDBObject(rset.getObject(18), "-"), pFontHeader11);
                                table.addCell(phrase);
                             phrase = new Phrase("Brought from (site) : "+dbObject.getDBObject(rset.getObject(19), "-"), pFontHeader11);
                                table.addCell(phrase); 
                                 table.getDefaultCell().setColspan(2);
                                 
                                  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                  phrase = new Phrase("The above particulars were provided by", pFontHeader);
                                table.addCell(phrase);
                                
                              table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Full Names  : "+dbObject.getDBObject(rset.getObject(20), "-"), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("ID no  : "+dbObject.getDBObject(rset.getObject(21), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Box.  "+dbObject.getDBObject(rset.getObject(22), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Tel : "+dbObject.getDBObject(rset.getObject(23), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                              table.getDefaultCell().setColspan(2);
                             phrase = new Phrase("Relationship to the deceased : "+dbObject.getDBObject(rset.getObject(24), "-"), pFontHeader11);
                                table.addCell(phrase); 
                                
                                 table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                               phrase = new Phrase("Place of Death : "+dbObject.getDBObject(rset.getObject(25), "-"), pFontHeader);
                                table.addCell(phrase); 
                                
                                 table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                   phrase = new Phrase("For the medical practitioner", pFontHeader);
                                table.addCell(phrase);
                                
                              table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Pupils  : "+dbObject.getDBObject(rset.getObject(26), "-"), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Response to stimuli  : "+dbObject.getDBObject(rset.getObject(27), "-"), pFontHeader11);
                                table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("R/S Breath sounds.  "+dbObject.getDBObject(rset.getObject(28), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Cardiarc activity : "+dbObject.getDBObject(rset.getObject(29), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                              table.getDefaultCell().setColspan(2);
                             phrase = new Phrase("Rigor moris : "+dbObject.getDBObject(rset.getObject(30), "-"), pFontHeader11);
                                table.addCell(phrase); 
                                
                                 phrase = new Phrase("Other significant findings  : "+dbObject.getDBObject(rset.getObject(31), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                 table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                   phrase = new Phrase("Based on the above findings, I certify that the person named is dead", pFontHeader);
                                table.addCell(phrase);
                                
                            //  table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Police station referred to  : "+dbObject.getDBObject(rset.getObject(32), "-"), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               //  table.addCell(phrase);
                              //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Date.  "+dbObject.getDBObject(rset.getObject(33), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Time : "+dbObject.getDBObject(rset.getObject(34), "-"), pFontHeader11);
                                table.addCell(phrase);
                                
                              table.getDefaultCell().setColspan(2);
                                 phrase = new Phrase("ID no  : "+dbObject.getDBObject(rset.getObject(35), "-"), pFontHeader11);
                            
                             phrase = new Phrase("Relationship to the deceased : "+dbObject.getDBObject(rset.getObject(36), "-"), pFontHeader11);
                               // table.addCell(phrase); 
                                
                                phrase = new Phrase("Name...."+dbObject.getDBObject(rset.getObject(35), "-")+  "    Designation......"+dbObject.getDBObject(rset.getObject(36), "-")  + " ................Signature& Stamp........................... ", pFontHeader);
                                table.addCell(phrase); 
                                 table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase("Original copy collected by", pFontHeader);
                                table.addCell(phrase); 
                                    table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Full Names : "+dbObject.getDBObject(rset.getObject(37), "-"), pFontHeader11);
                                table.addCell(phrase); 
                                phrase = new Phrase("Relationship : "+dbObject.getDBObject(rset.getObject(38), "-"), pFontHeader11);
                                table.addCell(phrase); 
                                   phrase = new Phrase("Box/Tel : "+dbObject.getDBObject(rset.getObject(39), "-"), pFontHeader11);
                                table.addCell(phrase); 
                                 phrase = new Phrase("ID No. : "+dbObject.getDBObject(rset.getObject(40), "-"), pFontHeader11);
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
    
    
      public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            
            
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM hp_clinical_results where patient_no = '"+MNo+"'  order by date");
            
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
    
    
    
}





