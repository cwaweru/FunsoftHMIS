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


public class IndividualAbsencePdf implements java.lang.Runnable {
    
    public static java.sql.Connection connectDB = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String names = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.ITALIC);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void IndividualAbsencePdf(java.sql.Connection connDb,java.lang.String textfield) {
        
        names = textfield;
        
        connectDB = connDb;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        // beginDate = begindate;
        
        //  endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
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
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {10,15,20,20,15,20};
                        
                        table1.setWidths(headerwidths);
                        
                        table1.setWidthPercentage((100));
                        
                        table1.setHeaderRows(3);
                        
                        
                        Phrase phrase1 = new Phrase("");
                        
                        
                        table1.getDefaultCell().setColspan(6);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                           table1.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase1 = new Phrase( "ABSENCE SUMMARY FOR "+names+"" , pFontHeader3);
                                
                                table1.addCell(phrase1);
                                
                                table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table1.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                table1.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                phrase1 = new Phrase( " Details " , pFontHeader);
                                //table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table1.addCell(phrase1);
                                table1.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                phrase1 = new Phrase(" " , pFontHeader2);
                                
                                table1.addCell(phrase1);
                        
                        
                                    table1.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                                    table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    phrase1 = new Phrase("Staff No. : " , pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                   
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Employment date :", pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase1 = new Phrase("Department :",pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Designation. : ", pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Job Status. : ", pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("Profile Date ", pFontHeader2);
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    
                                    table1.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                                    
                                    
                        try {
                            java.sql.Statement st1 = connectDB.createStatement();
                            //java.sql.Statement st2 = connectDB.createStatement();
                            //java.sql.Statement st3 = connectDB.createStatement();
                            //java.sql.Statement st4 = connectDB.createStatement();
                            //java.sql.Statement st5 = connectDB.createStatement();
                            //java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset1 = st1.executeQuery("select staff_id,empmt_date,dept,designation,jstatus from  hr.hr_staffresume_fileinfo where staff_id = '"+listofStaffNos[j]+"'");
                                //java.sql.ResultSet rset4 = st2.executeQuery("SELECT date('now') as Date");
                                
                                
                             
                                
                                while (rset1.next()) {
                                    
                                  table1.getDefaultCell().setColspan(1);
                                                                
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(1).toString(), pFontHeader1);
                                    
                                    table1.addCell(phrase1);
                                    table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(2).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                              
                                  
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(3).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(4).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                                  
                                                                       
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset1.getObject(5).toString(), pFontHeader1);
                                    table1.addCell(phrase1);
                                                                      
                                    
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);                                    
                                    phrase1 = new Phrase(date, pFontHeader1);                                   
                                    
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setBorder(Rectangle.BOTTOM);                                  
                                                                    
                                    
                                                                                                            
                                    
                                    docPdf.add(table1);
                                }
                            }
                            
                            
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
    
    
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct staff_id from hr.hr_staffresume_fileinfo WHERE staff_id ILIKE  initcap('"+names+"%')");
            
            
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





