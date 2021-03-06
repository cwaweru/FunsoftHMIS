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


public class PettyCashPaymentsShiftPdf implements java.lang.Runnable {
    
    java.lang.String memNo = null;
    
    java.lang.String CashPoint = null;
    
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    double debit = 0.00;
    
    double credit = 0.00;
    
    double totals = 0.00;
    
    double runTotal = 0.00;
    
    double TCredit = 0.00;
    
    double TDebit = 0.00;
    String ks;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void PettyCashPaymentsShiftPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint) {
        
        memNo = combox;
        
        CashPoint = cashPoint;
        
        connectDB = connDb;
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new TrialBalancePdf().TrialBalancePdf();
        
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
                    try {
                        
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"", pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Petty Cash Payments - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    docPdf.open();
                    
                    
                    try {
                        
                        double amountTo = 0.00;
                        String shiftDate = null;
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {12,8,20,25,15,14,13,17};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(8);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        // try {
                        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                        
                        
                        //  java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                        //  java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                        
                        //  System.out.println(""+endDate1);
                        //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                        
                        //  table.addCell(phrase);
                        table.getDefaultCell().setColspan(5);
                        
                        phrase = new Phrase("Petty Cash Payments", pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("Printed On  :" +date , pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(8);
                        phrase = new Phrase("SHIFT No :- "+memNo, pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setColspan(1);
                        
                        // table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("Date",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("V/No.",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Dealer",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Details",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Gl A/C",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Deposits",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Withdrawals",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Balance "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.Statement st3x = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rsetx = st3x.executeQuery("SELECT start_date::date from ac_shifts WHERE shift_no = '"+memNo+"'  AND cash_point = '"+CashPoint+"'");
                            
                            while (rsetx.next()) {
                                shiftDate = rsetx.getString(1);
                            }
                            
                            java.sql.ResultSet rset = st.executeQuery("SELECT date,voucher_no,initcap(payee),initcap(status),code,sum(debit-credit),transaction_no from ac_petty_cash WHERE shift_no = '"+memNo+"' AND cash_point = '"+CashPoint+"' AND paid = 'true' GROUP BY date,voucher_no,initcap(payee),initcap(status),code,transaction_no ORDER BY transaction_no");
                            
                            java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(debit),SUM(credit) from ac_petty_cash WHERE shift_no = '"+memNo+"' AND cash_point = '"+CashPoint+"'  AND paid = 'true'");
                            
                            //java.sql.ResultSet rsetTotals1 = st3.executeQuery("SELECT SUM(debit - credit) from ac_petty_cash WHERE shift_no != '"+memNo+"' AND shift_no < '"+memNo+"' AND date < '"+shiftDate+"'");
                            java.sql.ResultSet rsetTotals1 = st3.executeQuery("SELECT SUM(debit - credit) from ac_petty_cash WHERE shift_no::int < '"+memNo+"' AND shift_no != '"+memNo+"'  AND date <= '"+shiftDate+"'");
                            
                            while (rsetTotals1.next()) {
                                
                                table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("BFWD", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                totals = rsetTotals1.getDouble(1);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            while (rset.next()) {
                                
                                
                                amountTo = rset.getDouble(6);
                                if(amountTo > 0){
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getString(1), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getString(2), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getString(3), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getString(4), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getString(5), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amountTo)), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    totals = totals + rset.getDouble(6);
                                    TDebit = TDebit + (amountTo);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)),pFontHeader);
                                    table.addCell(phrase);
                                    
                                }else{
                                    if(amountTo < 0){
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getString(1), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getString(2), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getString(3), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getString(4), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getString(5), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amountTo*-1)), pFontHeader1);
                                        table.addCell(phrase);
                                        TCredit = TCredit + (amountTo*-1);
                                        totals = totals + rset.getDouble(6);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)),pFontHeader);
                                        table.addCell(phrase);
                                    }
                                }
                                
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            while (rsetTotals.next()) {
                                
                                table.getDefaultCell().setColspan(5);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TDebit)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                debit = rsetTotals.getDouble(1);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TCredit)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                credit = rsetTotals.getDouble(2);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)),pFontHeader);
                                table.addCell(phrase);
                                
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            table.getDefaultCell().setColspan(8);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            //  while (rsetTotals1.next()) {
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Cash In Hand", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totals)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            
                            
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
            
            docPdf.close();
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
}





