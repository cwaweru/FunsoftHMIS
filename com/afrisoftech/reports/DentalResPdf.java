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
//import //java.awt.Desktop;

public class DentalResPdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
     ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date endDate = null;
    java.lang.String vouchNo = null;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
       java.lang.String patNo = null;
 
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DentalResPdf(java.sql.Connection connDb, java.lang.String combox) {
        patNo = combox;
        connectDB = connDb;
     //   vouchNo = voucno;
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next())
                            compName = rset2.getObject(1).toString();
                        
                        while(rset4.next())
                            date = rset4.getObject(1).toString();
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"   Printed On: "+date+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        // headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        // headerFoter.setRight(5);
                        // docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Request List - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
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
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(7);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
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
                        
                        int headerwidths[] = {50,50};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(1);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        
                        
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        
                        
                        table.getDefaultCell().setColspan(1);
                        
                        
                        
                        // table.addCell("Amount KShs.");
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("DENTAL RESULTS");
                        
                        table.addCell(phrase);
                        try {
                             java.sql.Statement st1 = connectDB.createStatement();
                            
                            String DentNo =null;
                            java.sql.ResultSet rset1 = st1.executeQuery("select distinct xray_no from hp_xray_results where patient_no='"+patNo+"'  and xray_no ilike 'D%'");
                             
                            while (rset1.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                
                                
                                
                                
                                DentNo= rset1.getObject(1).toString();
                                
                               java.sql.Statement st = connectDB.createStatement();
                        
                            
                            java.sql.ResultSet rset = st.executeQuery("select input_date::timestamp(0),initcap(patient_no),patient_name,xray_no,age,gender,doctor,1,1,examination,xray_manager,ext_ref,pc,hpc,pdhs,exam,exam_notes,imp_inv,inv,diag,plan,notes,upper(user_name) from hp_xray_results where xray_no='"+DentNo+"'");
                            table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT |Rectangle.TOP);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            
                            
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                
                                
                                
                                
                                phrase = new Phrase("In/Out Dental NO : "+ rset.getObject(4).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("Date/Time : "+rset.getObject(1).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Name : "+rset.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("Service No : " +rset.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Sex : "+ rset.getObject(6).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Age : "+ rset.getObject(5).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Unit : "+ rset.getObject(8).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase("Rank : "+ rset.getObject(9).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Doctor Referring : "+rset.getObject(7).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("EXAMINATION NOTES", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("------------------------------------", pFontHeader1);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                
                                phrase = new Phrase("ALLERGY :  "+ rset.getObject(12).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                phrase = new Phrase("DISEASE : "+ rset.getObject(11).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                                phrase = new Phrase("OTHERS : "+ rset.getObject(22).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                   phrase = new Phrase("------------------------------------", pFontHeader1);
                                table.addCell(phrase);
                              
                                
                                phrase = new Phrase("PC:  "+ rset.getObject(13).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                phrase = new Phrase("HPC : "+ rset.getObject(14).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                                
                                
                                  phrase = new Phrase("PDHX : "+ rset.getObject(15).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                                
                                
                                
                                phrase = new Phrase("EXAMINATION:  "+ rset.getObject(16).toString(), pFontHeader1);
                                table.addCell(phrase);
                               // phrase = new Phrase("NOTES : "+ rset.getObject(18).toString(), pFontHeader1);
                                
                               // table.addCell(phrase);
                                
                                
                                
                                
                                
                                phrase = new Phrase("IMPRESSION INVEST :  "+ rset.getObject(18).toString(), pFontHeader1);
                                table.addCell(phrase);
                                 phrase = new Phrase("INVESTIGATION :  "+ rset.getObject(17).toString(), pFontHeader1);
                                table.addCell(phrase);
                              
                                phrase = new Phrase("INVESTIGATION FINDINGS :  "+ rset.getObject(19).toString(), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("DIAGNOSIS :  "+ rset.getObject(20).toString(), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("PLAN:  "+ rset.getObject(21).toString(), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("PROCEDURE : "+ rset.getObject(10).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                 phrase = new Phrase("CLINICIAN : "+ rset.getObject(23).toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                
                            }
                            
                            
                            phrase = new Phrase("Doctor's Signature : ____________________________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT|Rectangle.BOTTOM|Rectangle.TOP);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
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





