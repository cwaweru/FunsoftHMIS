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

public class WayBillPdf implements java.lang.Runnable {
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
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 9, Font.BOLDITALIC);
    
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 9, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void WayBillPdf(java.sql.Connection connDb, java.lang.String inv1) {
        
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
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                          while (rset4.next()){
                             table1.getDefaultCell().setColspan(3);
                            date = rset4.getObject(1).toString();
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                           // table1.addCell(phrase);
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
                        
                        int headerwidths[] = {30,30,30,30};
                        
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
                               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                           
                            
                            
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.ResultSet rset12 = st12.executeQuery("select distinct upper(consigner_comp),upper(consignee_comp),upper(consigner_name) ,upper(consignee_name),initcap(building),floor,date,input_time ,initcap(street),deliver,priority,inst,INITCAP(out_come) from pb_courier where wbill_no = '"+INV01+"'");
                            table.getDefaultCell().setColspan(2);
                           
                            phrase = new Phrase("Courier   Serial No.  "+INV01+"", pFontHeader1);
                            table.addCell(phrase);
                             phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                            while (rset12.next()){
                                
                                table.getDefaultCell().setColspan(2);
                          
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                           
                            phrase = new Phrase("CONSIGNER ", pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("CONSIGNEEE ", pFontHeader1);
                            table.addCell(phrase);
                          
                            
                              phrase = new Phrase("Company : " + dbObject.getDBObject(rset12.getObject(1), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("Company : " + dbObject.getDBObject(rset12.getObject(2), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("Name : " + dbObject.getDBObject(rset12.getObject(3), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("Name : " + dbObject.getDBObject(rset12.getObject(4), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(1);
                              phrase = new Phrase("Building : " + dbObject.getDBObject(rset12.getObject(5), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("Floor : " + dbObject.getDBObject(rset12.getObject(6), "-"), pFontHeader1);
                            table.addCell(phrase);
                       phrase = new Phrase("D/Date : " + dbObject.getDBObject(rset12.getObject(7), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("D/Time : " + dbObject.getDBObject(rset12.getObject(8), "-"), pFontHeader1);
                            table.addCell(phrase);
                               table.getDefaultCell().setColspan(2);
                              phrase = new Phrase("Street : " + dbObject.getDBObject(rset12.getObject(9), "-"), pFontHeader1);
                            table.addCell(phrase);
                              table.getDefaultCell().setColspan(1);
                              phrase = new Phrase( dbObject.getDBObject(rset12.getObject(10), "-"), pFontHeader1);
                            table.addCell(phrase);
                              phrase = new Phrase("Priority : " + dbObject.getDBObject(rset12.getObject(11), "-"), pFontHeader1);
                            table.addCell(phrase);
                               table.getDefaultCell().setColspan(2);
                              phrase = new Phrase( dbObject.getDBObject(rset12.getObject(12), "-"), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                              phrase = new Phrase( dbObject.getDBObject(rset12.getObject(13), "-"), pFontHeader1);
                            table.addCell(phrase);
                            }
                            
                            
                            
                            
                            table.getDefaultCell().setColspan(4);
                             phrase = new Phrase("Customers are advised to self-insure all items of value sent through the courier service.Cash or other valuablesare not carried on the courier service. The company will not accept liability for any loss of such items carried on this service.", pFontHeader);
                            table.addCell(phrase);
                              phrase = new Phrase( "DESCRIPTION OF CONTENT", pFontHeader1);
                            table.addCell(phrase);
                             table.getDefaultCell().setColspan(1);
                             phrase = new Phrase("ITEMS", pFontHeader1);
                            table.addCell(phrase);
                             phrase = new Phrase("QTY", pFontHeader1);
                            table.addCell(phrase);
                             phrase = new Phrase("WEIGHT", pFontHeader1);
                            table.addCell(phrase);
                             phrase = new Phrase( "AMT", pFontHeader1);
                            table.addCell(phrase);
                                java.sql.ResultSet rset = st.executeQuery("select description,qty,weight,amount from pb_courier where wbill_no = '"+INV01+"'");
                         
                          //  java.sql.ResultSet rset = st.executeQuery("select initcap(patient_name),excemptions,recomedations,sick_days,start_date,resume_date,initcap(doctor),trans_date,explanation from hp_sick_sheet where sheet_no = '"+INV01+"'");
                            while (rset.next()){
                                
                                table.getDefaultCell().setColspan(1);
                            phrase = new Phrase( dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            // table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setColspan(1);
                            
                           phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader);
                            table.addCell(phrase);
                           
                          //  table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            }
                            
                        
                            table.getDefaultCell().setColspan(4);
                              phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);
                           
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("CONFIRMED RECEIVED BY: ___________________________", pFontHeader1);
                            table.addCell(phrase);
                           // table.getDefaultCell().setBorder(Rectangle.LEFT|Rectangle.RIGHT);
                             table.getDefaultCell().setColspan(4);
                              phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("SIGNATURE              _________________________", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                           
                             
                            phrase = new Phrase("STAMP", pFontHeader);
                            table.addCell(phrase);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("DATE ______________________-", pFontHeader);
                            table.addCell(phrase);
                             phrase = new Phrase("TIME ______________________-", pFontHeader);
                            table.addCell(phrase);
                              table.getDefaultCell().setColspan(4);
                               phrase = new Phrase(" ", pFontHeader1);
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
            
            docPdf.close();docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
        }
    }
    
    
    
    
    
}





