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

public class SickSheetPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    java.lang.String INV01 = null;
    java.lang.String INV02 = null;
    java.lang.String commentary = null;
    java.lang.String interpret = null;
     ////java.awt.Desktop deskTop = Desktop.getDesktop();
    com.afrisoftech.lib.DBObject dbObject;
    String teststatus = null;
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    //    double osBalance = 0.00;
    //   double current = 0.00;
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLDITALIC);
    
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 9, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void SickSheetPdf(java.sql.Connection connDb, java.lang.String inv1) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        
        INV01 = inv1;
        
        
        
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
            
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            int titleFont = 0;
            int bodyFont = 0;
            float Widths = 0;
            float Heights = 0;
            float Margins = 0;
            
            try {
                connectDB.setAutoCommit(false);
                
                java.sql.Statement stm1 = connectDB.createStatement();
                java.sql.ResultSet rse1 = stm1.executeQuery("select header_font,body_font,width,height,margins from receipt_pref");
                while (rse1.next()){
                    titleFont = rse1.getInt(1);
                    bodyFont = rse1.getInt(2);
                    Widths = rse1.getFloat(3);
                    Heights = rse1.getFloat(4);
                    Margins = rse1.getFloat(5);
                }
            }catch(java.sql.SQLException sq){
                
                try {
                    connectDB.rollback();
                }catch (java.sql.SQLException sql){
                    //   javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                System.out.println(sq.getMessage());
                //   javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                
            }
            // com.lowagie.text.Font pFontHeader = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptFontSize")), Font.NORMAL);
            // com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptTitleFontSize")), Font.BOLD);
           // com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, bodyFont, Font.NORMAL);
         ///   com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, titleFont, Font.BOLD);
            
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((Widths), Heights),Margins,Margins,Margins,Margins);
            
            
            
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String Address = null;
                    String Tel = null;
                    String compName = null;
                    String Fax = null;
                    String Email = null;
                    String date = null;
                    
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select header_name from pb_header");
                        
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.TIMES, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    String IssueDate =null;
                    String Messg = null;
                    
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select name from pb_notice");
                        
                        //  table.getDefaultCell().setColspan(2);
                        java.sql.ResultSet rset3 = st4.executeQuery("SELECT current_timestamp(0) ");
                        
                        while (rset3.next()){
                            date = rset3.getObject(1).toString();
                        }
                        // table.addCell(phrase);
                        while(rset2.next()){
                            Messg = rset2.getObject(1).toString();
                        }
                       
                              //  com.lowagie.text.HeaderFooter footer1  = com.lowagie.text.HeaderFooter(new Phrase("This is a computer generated report and is not valid without a signature and  seal."),false);
                           //docPdf.setFooter(footer1);
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Powered by SPL"),false);// FontFactory.getFont(com.lowagie.text.FontFactory.TIMES, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        docPdf.setFooter(footer);
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(2);
                        
                        int headerwidths[] = {65,35};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(2);
                        
                        Phrase phrase = new Phrase();
                        
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        // System.out.println(listofStaffNos[j]);
                        
                        try {
                            java.sql.Statement st = connectDB.createStatement();
                            
                            
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.ResultSet rset12 = st12.executeQuery("select trans_date from hp_sick_sheet where sheet_no = '"+INV01+"'");
                            while (rset12.next()){
                                
                                table.getDefaultCell().setColspan(2);
                            IssueDate = rset12.getObject(1).toString();
                            }
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("DATE ISSUED  : " +IssueDate , pFontHeader2);
                            
                            
                            
                            table.addCell(phrase);
                            phrase = new Phrase("Printed On  : " +date , pFontHeader2);
                            
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("SICK LEAVE/VARIATION OF DUTY RECOMMENDATION                    Serial No.  "+INV01+"", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            
                            java.sql.ResultSet rset = st.executeQuery("select initcap(patient_name),excemptions,recomedations,sick_days,start_date,resume_date,initcap(doctor),trans_date,explanation from hp_sick_sheet where sheet_no = '"+INV01+"'");
                            while (rset.next()){
                                
                                table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("This is to certify that  "+ dbObject.getDBObject(rset.getObject(1)+"  has been found to be, ", "-"), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("unfit to : " + dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            // table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                             phrase = new Phrase("Start Date:  "+dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader);
                            table.addCell(phrase);
                           
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("We Recommend "  + dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                            
                            phrase = new Phrase("Doctor Name :  " +dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader);
                                                       
                            table.addCell(phrase);
                             phrase = new Phrase("Resume work on : "+dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader);
                            table.addCell(phrase);
                           
                            
                            phrase = new Phrase("Doctor's Signature :____________________________", pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Number of sick off days : "+dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader);
                            table.addCell(phrase);
                            
                           
                            phrase = new Phrase("Explanation(If longer than (3) Days) : "+dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader);
                          
                            table.getDefaultCell().setColspan(6);
                            table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(6);
                              phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);
                           
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Notes: -", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("1. Certificate is not valid if it bears alterations.", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("2. This Certificate is not valid unless sealed with hospital seal.", pFontHeader);
                            table.addCell(phrase);
                             phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("3. Not valid for more than 3 days unless accompanied by an explantion.", pFontHeader);
                            table.addCell(phrase);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             phrase = new Phrase("Seal ", pFontHeader1);
                            table.addCell(phrase);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("4. In some instances, the patient may not have fully recovered by the reporting date. In such a case, the patient should seek medical review and additional sick off where necessary. ", pFontHeader);
                            table.addCell(phrase);
                            
                               phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                             table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.TOP);
                          table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("SICK/CONVALESCE LEAVE POLICY ", pFontHeader1);
                            table.addCell(phrase);
                              table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                            phrase = new Phrase("1. Absence from work, school or normal occupation due to illness may be necessary for various reasons. In recommending sick leave or variation or duties, we take into account the needs of the employee,proffessional and ethical standards and the expectations of the employer. ", pFontHeader);
                          table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.addCell(phrase);
                           
                              
                          
                            phrase = new Phrase("2. The sick / convalesce leave recommendation may be general or specific. In making the recommendation, the nature of the employee's job or occupation is considered. It should be recognised that different recommendations may be given for the same illness depending on the nature of the job. For for example, a driver with finger fracture of the left hand cannot work while a right handed employee doing clerical work may still be able to carry out certain duties.", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("As a matter of policy, sick leave is only granted if: -", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("(a) The employee is hospitalised as an inpatient. ", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("(b) Working would constitute a danger to the employee's health.", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("(c) Working would adversly affect recovery from illness. ", pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("(d) The employee is unable to carry out his /her normal duties and cannot be re-deployed or is incapable of performing any form   of productive work.", pFontHeader);
                          
                            table.addCell(phrase);
                            
                            phrase = new Phrase("(e) The illness requires strict bed rest and thus confines the employee to bed at home ", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("(f) The illness makes it impossible for the employee to travel to work. ", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.BOTTOM|Rectangle.RIGHT);
                            
                             //table.getDefaultCell().setBorder(Rectangle.RIGHT);
                              phrase = new Phrase(" ", pFontHeader1);
                       
                             
                            phrase = new Phrase("If in doubt as to why your employee has a sick leave recommendation, please contact us on the address provided.", pFontHeader1);
                            table.addCell(phrase);
                            
                                                        
                            
                            docPdf.add(table);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        //}
                        //  boolean boolNewPage = docPdf.newPage();
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
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
        }
    }
    
    
    
    
    
}





