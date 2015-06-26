//Author Francis Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.records.reports;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class BirthWeightPdf implements java.lang.Runnable {
    public static java.sql.Connection connectDB = null;
    java.lang.String bank = null;
    java.util.Date endDate = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date beginDate = null;
    
    String ks = null;
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void BirthWeightPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date enddate) {
        
        // bank = combox;
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = enddate;
        
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
                    int babies = 0;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st41 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT Hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        java.sql.ResultSet rset41 = st41.executeQuery("SELECT COUNT(baby_weight) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"'::date AND '"+endDate+"'::date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getObject(2).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        
                        while(rset41.next()){
                            babies = rset41.getInt(1);
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName,pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    double osBalance1 = 0.00;
                    double osBalance = 0.00;
                    double current = 0.00;
                    double osBalancebf = 0.00;
                    //  double osBalance = 0.00;
//            double current = 0.00;
                    double osBalancecr = 0.00;
                    double osBalancebr = 0.00;
                    
                    
                    
                    
                    String bankName = null;
                    String acct = null;
                    String branch = null;
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {25,25,25,25};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(4);
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        Phrase phrase = new Phrase("ANALYSIS OF BIRTH ACCORDING TO WEIGHT");
                        
                        table.addCell(phrase);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());
                            java.util.Date beginDate1 = dateFormat.parse(beginDate.toLocaleString());
                            
                            phrase = new Phrase("From: " +dateFormat.format(beginDate1) +"  To: " +dateFormat.format(endDate1), pFontHeader1);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("TOTAL BIRTHS : " +babies,pFontHeader1);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("UNDER 2.3 Kg.",pFontHeader1);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("OVER 2.3 Kg.",pFontHeader1);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Printed on : " +date, pFontHeader1);
                        
                        //table.addCell(phrase);
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        // table.addCell("");
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        //table.getDefaultCell().setBorder(Rectangle.ALIGN_RIGHT | Rectangle.ALIGN_RIGHT);
                        
                        
                        table.getDefaultCell().setColspan(1);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase = new Phrase("LIVE",pFontHeader1);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("STILL",pFontHeader1);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("LIVE",pFontHeader1);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("STILL",pFontHeader1);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        
                        
                        try {
                            
                            //  java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st23 = connectDB.createStatement();
                            
                            
                            
                            //java.sql.ResultSet rset = st.executeQuery("select date,initcap(dealer),cheque_no,debit,credit from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and account_no = '"+bank+"' AND debit > 0 ORDER BY date,cheque_no");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            java.sql.ResultSet rset23 = st23.executeQuery("SELECT COUNT(baby_weight) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"'::date AND '"+endDate+"'::date  AND baby_weight < 2.3 AND state_of_infant_at_birth ilike '%live%'");
                            
                            java.sql.ResultSet rsetTotal2 = st2.executeQuery("SELECT COUNT(baby_weight) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"'::date AND '"+endDate+"'::date  AND baby_weight < 2.3 AND state_of_infant_at_birth ilike '%still%'");
                            java.sql.ResultSet rseta = st21.executeQuery("SELECT COUNT(baby_weight) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"'::date AND '"+endDate+"'::date  AND baby_weight >= 2.3 AND state_of_infant_at_birth ilike '%live%'");
                            java.sql.ResultSet rsetB = st22.executeQuery("SELECT COUNT(baby_weight) FROM rh.post_natal_services WHERE service_date::date BETWEEN '"+beginDate+"'::date AND '"+endDate+"'::date  AND baby_weight >= 2.3 AND state_of_infant_at_birth ilike '%still%'");
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(1);
                            while (rset23.next()){
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(rset23.getString(1)),pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            while (rsetTotal2.next()){
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(rsetTotal2.getString(1)),pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            while (rseta.next()){
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(rseta.getString(1)),pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            while (rsetB.next()){
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(rsetB.getString(1)),pFontHeader1);
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
            
             
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
}





