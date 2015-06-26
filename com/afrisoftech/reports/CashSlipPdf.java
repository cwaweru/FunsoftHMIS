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


public class CashSlipPdf implements java.lang.Runnable {
    
    java.lang.String patNo = null;
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    java.lang.String Scheme = null;
    
    java.lang.String Patient = null;
    
    java.lang.String payer = null;
    
    java.lang.String MemNo = null;
    
    java.lang.String CardNo = null;
    
    java.lang.String visitId = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void CashSlipPdf(java.sql.Connection connDb,java.lang.String combox,java.lang.String patient,java.lang.String cardno, java.lang.String visitid) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        patNo = combox;
        connectDB = connDb;
        Patient = patient;
        CardNo = cardno;
        visitId = visitid;
        //  endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new TransactionsListPdf().TransactionsListPdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                java.lang.Thread.currentThread().sleep(2000);
                
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
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)),pFontHeader);
                            
                            // table.addCell(phrase);
                            
                            
            
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
            
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            //  com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")));
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
            com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, bodyFont, Font.NORMAL);
            com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, titleFont, Font.NORMAL);
            
            
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")));
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((Widths), Heights),Margins,Margins,Margins,Margins);
            
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    try {
                        
                        java.lang.Class.forName("org.postgresql.Driver");
                        
                    } catch(java.lang.ClassNotFoundException cnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
                        
                    }
                    
                    
                    
                    String compName = null;
                    String date = null;
                    
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {25,25,25,25};
                        
                        //     table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement sts2 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name from pb_hospitalprofile");
                            
                            java.sql.ResultSet rset = st.executeQuery("SELECT lpad(currval('service_no'),5,0),date('now')");
                            java.sql.ResultSet rsetTotals22 = st22.executeQuery("SELECT current_user,current_timestamp");
                            while (rset3.next()){
                                table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader1);
                            table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Service Provider Instructions", pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("(Valid for only one visit)", pFontHeader);
                            
                            // table.addCell(phrase);
                            
                            //table.getDefaultCell().setColspan(2);
                            
                            while (rset.next()){
                                
                                table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Service Slip ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date : "+rset.getObject(2).toString(), pFontHeader1);
                            table.addCell(phrase);
                            }
                            
                            table.getDefaultCell().setColspan(4);
                            
                            phrase = new Phrase("Name : " +Patient, pFontHeader1);
                            
                            table.addCell(phrase);
                            
                       
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("OPD No. : " +CardNo, pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("RID No. : " +patNo, pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("VID No. : " +visitId , pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Service No. : "  +rset.getObject(1).toString(), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("This Patient has registered as fees for service patient. Please give the person. ", pFontHeader1);
                            table.addCell(phrase);
                            
                            //        while (rsetTotals2.next())
                            
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("Service on prepaid basis. Valid for today's visit alone. ", pFontHeader1);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            
                            phrase = new Phrase("Signature :_______________________________ ", pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Stamp/sign - Cash Office :___________________ ", pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            while (rsetTotals22.next()){
                                
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Issued By : " +rsetTotals22.getObject(1).toString().toUpperCase(), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Time : " +rsetTotals22.getObject(2).toString(), pFontHeader1);
                            table.addCell(phrase);
                            }
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
            
             
            
            try {
                
                if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                    
                    System.out.println(tempFile);
                    
                    wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    
                    wait_for_Pdf2Show.waitFor();
                    
                } else if (System.getProperty("os.name").equalsIgnoreCase("Windows 98")) {
                    
                    
                    wait_for_Pdf2Show = rt.exec("command.exe /C AcroRd32 /p /h "+tempFile);
                    
                    wait_for_Pdf2Show.waitFor();
                    // wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                    
                    // wait_for_Pdf2Show.waitFor();
                    
                }
                else {
                    
                    wait_for_Pdf2Show = rt.exec("cmd.exe /C AcroRd32 /p /h "+tempFile);
                    
                    wait_for_Pdf2Show.waitFor();
                    // wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                    
                    // wait_for_Pdf2Show.waitFor();
                    
                }
                
            } catch(java.lang.InterruptedException intrExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                
            }
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
}





