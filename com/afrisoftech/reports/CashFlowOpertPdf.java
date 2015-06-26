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


public class CashFlowOpertPdf implements java.lang.Runnable {
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    String ks;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA,10, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void CashFlowOpertPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
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
                    
                    
                    
                    
                    
                    double osBalance1 = 0.00;
                    
                    
                    String compName = null;
                    String date = null;
                    String footers = null;
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName ,pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(7);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Cash Flow Analysis - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    double osBalance = 0.00;
                    double osBalance13 = 0.00;
                    double osBalance2 = 0.00;
                    double osBalance12 = 0.00;
                    double osBalance3 = 0.00;
                    double osBalance33 = 0.00;
                    double osBalance22 = 0.00;
                    double osBalance33I = 0.00;
                    double osBalance33D = 0.00;
                    double osBalance22I = 0.00;
                    double osBalance22D = 0.00;
                    double osBalance22a = 0.00;
                    double osBalance333a = 0.00;
                    double osBalance33a = 0.00;
                    double osBalance22L = 0.00;
                    double osBalance22F = 0.00;
                    double osBalance33L = 0.00;
                    double osBalance33F = 0.00;
                    double osBalance33S = 0.00;
                    double osBalance33c = 0.00;
                    double osBalance22S = 0.00;
                    double osBalance22c = 0.00;
                    double osBalance33O = 0.00;
                    double osBalance22O = 0.00;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(3);
                        
                        int headerwidths[] = {30,25,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(3);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        phrase = new Phrase("CASH FLOW STATEMENT ", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            
                            phrase = new Phrase("Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  : " +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        
                        phrase = new Phrase("Description",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Debit "+ks,pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Credit "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            java.sql.Statement st33S = connectDB.createStatement();
                            java.sql.Statement st33c = connectDB.createStatement();
                            java.sql.Statement st33O = connectDB.createStatement();
                            
                            java.sql.Statement st33F = connectDB.createStatement();
                            java.sql.Statement st33L = connectDB.createStatement();
                            java.sql.Statement st33a = connectDB.createStatement();
                            
                            java.sql.Statement st33I = connectDB.createStatement();
                            java.sql.Statement st33D = connectDB.createStatement();
                            //  java.sql.Statement st33a = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st33 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset = st.executeQuery("select 'Debtors' ,sum(debit),sum(credit) from ac_cash_collection WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (transaction_type = 'Receipts')  and receipt_source ilike 'debtors'");
                            // java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(credit) from ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and transaction_type = 'Receipts'");
                            java.sql.ResultSet rset2 = st2.executeQuery("select 'RECEIPTs' ,sum(debit),sum(credit) from ac_cash_collection WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (transaction_type = 'Receipts')   and receipt_source ilike 'op'");
                            
                            java.sql.ResultSet rset3 = st3.executeQuery("select 'Inpatients' ,sum(debit),sum(credit) from ac_cash_collection WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (transaction_type = 'Receipts')   and receipt_source ilike 'ip'");
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("CASH FROM OPERATING ACTIVITIES", pFontHeader);
                            
                            table.addCell(phrase);
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)),pFontHeader1);
                                osBalance1 = osBalance1 + rset.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)),pFontHeader1);
                                osBalance = osBalance + rset.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            while (rset2.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset2.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(2)),pFontHeader1);
                                osBalance12 = osBalance12 + rset2.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(3)),pFontHeader1);
                                osBalance2 = osBalance2 + rset2.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            while (rset3.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(2)),pFontHeader1);
                                osBalance13 = osBalance13 + rset3.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(3)),pFontHeader1);
                                osBalance3= osBalance3 + rset3.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1+osBalance12+osBalance13)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance+osBalance2+osBalance3)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("CASH TO OPERATING ACTIVITIES", pFontHeader);
                            
                            table.addCell(phrase);
                            //java.sql.ResultSet rset33 = st33.executeQuery("select 'Expenses' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code not ilike '64-00%'");
                            java.sql.ResultSet rset33 = st33.executeQuery("select 'Salaries' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_source ilike 'salaries%'");
                            
                            
                            while (rset33.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33.getString(2)),pFontHeader1);
                                osBalance33 = osBalance33 + rset33.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33.getString(3)),pFontHeader1);
                                osBalance22 = osBalance22 + rset33.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            java.sql.ResultSet rset33S = st33S.executeQuery("select 'Suppliers' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'  AND payment_source ilike 'creditors%'");
                            
                            
                            while (rset33S.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33S.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33S.getString(2)),pFontHeader1);
                                osBalance33S = osBalance33S + rset33S.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33S.getString(3)),pFontHeader1);
                                osBalance22S = osBalance22S + rset33S.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            java.sql.ResultSet rset33c= st33c.executeQuery("select 'Consultants' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'  AND payment_source ilike 'Doctors%'");
                            
                            
                            while (rset33c.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33c.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33c.getString(2)),pFontHeader1);
                                osBalance33c = osBalance33c + rset33c.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33c.getString(3)),pFontHeader1);
                                osBalance22c = osBalance22c + rset33c.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            java.sql.ResultSet rset33O = st33O.executeQuery("select 'Others' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_source ilike 'others%'");
                            
                            
                            while (rset33O.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33O.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33O.getString(2)),pFontHeader1);
                                osBalance33O = osBalance33O + rset33O.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33O.getString(3)),pFontHeader1);
                                osBalance22O = osBalance22O + rset33O.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance33+osBalance33O+osBalance33c+osBalance33S)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22+osBalance22O+osBalance22c+osBalance22S)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net Cash From Operating Activities", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalance1+osBalance12+osBalance13)-(osBalance22+osBalance22O+osBalance22c+osBalance22S))),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            phrase = new Phrase("INVESTING ACTIVITIES", pFontHeader);
                            
                            table.addCell(phrase);
                            java.sql.ResultSet rset33I = st33I.executeQuery("select 'Interest Paid' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND credit > 0 AND description ilike '%Interest%'");
                            
                            
                            while (rset33I.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33I.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33I.getString(2)),pFontHeader1);
                                osBalance33I = osBalance33I + rset33I.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33I.getString(3)),pFontHeader1);
                                osBalance22I = osBalance22I + rset33I.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            java.sql.ResultSet rset33D = st33D.executeQuery("select 'Interest Received' ,sum(debit),sum(credit) from ac_DEBTORS WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND DEBIT > 0 AND ITEM ilike '%Interest%'");
                            
                            
                            while (rset33D.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33D.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33D.getString(2)),pFontHeader1);
                                osBalance33D = osBalance33D + rset33D.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33D.getString(3)),pFontHeader1);
                                osBalance22D = osBalance22D + rset33D.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            java.sql.ResultSet rset33a = st33a.executeQuery("select 'Purchase of fixed assets' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_source ilike 'Assets%'");
                            
                            
                            while (rset33a.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33a.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33a.getString(2)),pFontHeader1);
                                osBalance33a = osBalance33a + rset33a.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33a.getString(3)),pFontHeader1);
                                osBalance22a = osBalance22a + rset33a.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total investing activities", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance33I+osBalance33D+osBalance33a)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22I+osBalance22D+osBalance22a)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net cash used in investing activities", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalance33I+osBalance33D+osBalance33a)-(osBalance22I+osBalance22D+osBalance22a))),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            phrase = new Phrase("FINANCING ACTIVITIES", pFontHeader);
                            
                            table.addCell(phrase);
                            java.sql.ResultSet rset33F = st33F.executeQuery("select 'Sale of Shares' ,sum(debit),sum(credit) from ac_cash_COLLECTION WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'  AND description ilike '%SHARES%'");
                            
                            
                            while (rset33F.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33F.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33F.getString(2)),pFontHeader1);
                                osBalance33F = osBalance33F + rset33F.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33F.getString(3)),pFontHeader1);
                                osBalance22F = osBalance22F + rset33F.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            java.sql.ResultSet rset33L = st33L.executeQuery("select 'Loan Acquired/(Repaid)' ,sum(debit),sum(credit) from ac_cash_book WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'  AND activity_code ilike '64-00%'");
                            
                            
                            while (rset33L.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset33L.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33L.getString(2)),pFontHeader1);
                                osBalance33L = osBalance33L + rset33L.getDouble(2);
                                
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset33L.getString(3)),pFontHeader1);
                                osBalance22L = osBalance22L + rset33L.getDouble(3);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Financing Activities", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance33F+osBalance33L)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22L+osBalance22F)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net cash used in investing activities", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalance33F+osBalance33L)-(osBalance22L+osBalance22F))),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("GROSS", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance33F+osBalance33L+osBalance33I+osBalance33D+osBalance33a+osBalance1+osBalance12+osBalance13)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22L+osBalance22F+osBalance22I+osBalance22D+osBalance22a+osBalance22+osBalance22O+osBalance22c+osBalance22S)),pFontHeader);
                            
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Increase/(Decrease) in Cash and Cash Equivalents", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalance33F+osBalance33L+osBalance33I+osBalance33D+osBalance33a+osBalance1+osBalance12+osBalance13)-(osBalance22L+osBalance22F+osBalance22I+osBalance22D+osBalance22a+osBalance22+osBalance22O+osBalance22c+osBalance22S))),pFontHeader);
                            
                            
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
            
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
}





