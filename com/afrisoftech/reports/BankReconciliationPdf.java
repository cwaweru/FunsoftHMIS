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


public class BankReconciliationPdf implements java.lang.Runnable {
    
    public static java.sql.Connection connectDB = null;
    java.lang.String bank = null;
    java.util.Date beginDate = null;
    //java.lang.String bank = null;
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date endDate = null;
    String ks;
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void BankReconciliationPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate, java.lang.String combox) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        bank = combox;
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
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
                    
                    double Pettycashos = 0.00;
                    double Pettycashob = 0.00;
                    double Pettycashin = 0.00;
                    double Pettycashout = 0.00;
                    double Rcout = 0;
                    double Rcin = 0;
                    double Rcos = 0;
                    double Rcob = 0;
                    double OutSt = 0;
                    //double Net1 = 0;
                    double cash1 = 0.00;
                    double chq1 = 0.00;
                    double Card = 0.00;
                    double Refund = 0.00;
                    double Net1 = 0;
                    double Rct = 0;
                    String compName = null;
                    String date = null;
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Cashbook Report Summary  - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
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
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2 = st2.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while(rset2.next()){
                                ks = rset2.getObject(1).toString();
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name,current_date from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(8);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                table1.addCell(phrase);
                                date = rset3.getObject(2).toString();
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {20,9,12,12,12,12};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((105));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //  phrase = new Phrase(");
                            
                            
                            
                            table.getDefaultCell().setColspan(4);
                            
                            phrase = new Phrase("Bank Reconciliation Statement As At : "+dateFormat.format(endDate1).toUpperCase(), pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  : "+date , pFontHeader1);
                            
                            table.addCell(phrase);
                            
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        
                        
                        try {
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            java.sql.Statement stpc = connectDB.createStatement();
                            java.sql.Statement stpc1 = connectDB.createStatement();
                            java.sql.Statement stpc11 = connectDB.createStatement();
                      /*      java.sql.ResultSet rsetpc = stpc.executeQuery("select sum(debit),sum(credit),sum(debit-credit) from ac_petty_cash where  date BETWEEN '"+beginDate+"' AND '"+endDate+"'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            java.sql.ResultSet rsetpc1 = stpc1.executeQuery("select sum(debit - credit) from ac_petty_cash where date < '"+beginDate+"'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            java.sql.ResultSet rsetpc11 = stpc11.executeQuery("select sum(debit - credit) from ac_petty_cash");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                       
                            java.sql.Statement strc = connectDB.createStatement();
                            java.sql.Statement strc1 = connectDB.createStatement();
                            java.sql.Statement strc11 = connectDB.createStatement();
                       
                            java.sql.ResultSet rsetrc = strc.executeQuery("select sum(debit),sum(credit),sum(debit-credit) from ac_cash_collection where  date BETWEEN '"+beginDate+"' AND '"+endDate+"'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            java.sql.ResultSet rsetrc1 = strc1.executeQuery("select sum(debit - credit) from ac_cash_collection where date < '"+beginDate+"'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            java.sql.ResultSet rsetrc11 = strc11.executeQuery("select sum(debit - credit) from ac_cash_collection");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                       
                       */
                            
                            
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            
                            
                            System.out.println(listofAct.length);
                            //java.sql.Statement st12 = connectDB.createStatement();
                            
                            //  java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE discharge = false");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double Net = 0;
                                double cash = 0.00;
                                double chq = 0.00;
                                double pays = 0.00;
                                double unbank = 0.00;
                                double unprchq = 0.00;
                                double balSt = 0.00;
                                double unlocated = 0.00;
                                double cast = 0.00;
                                double totalless = 0.00;
                                java.sql.Statement st1 = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                // java.sql.Statement st1 = connectDB.createStatement();
                                
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st13 = connectDB.createStatement();
                                
                                java.sql.Statement st14 = connectDB.createStatement();
                                java.sql.Statement st15 = connectDB.createStatement();
                                
                                java.sql.Statement st1x = connectDB.createStatement();
                                java.sql.Statement st161x = connectDB.createStatement();
                                java.sql.Statement st1xx = connectDB.createStatement();
                                java.sql.Statement st161xx = connectDB.createStatement();
                                java.sql.Statement stN = connectDB.createStatement();
                                
                                java.sql.Statement st16 = connectDB.createStatement();
                                java.sql.Statement st161 = connectDB.createStatement();
                                java.sql.ResultSet rset16 = st16.executeQuery("select sum(debit - credit) from ac_cash_book where account_no ='"+listofAct[i]+"' and date < '"+beginDate+"'");
                                java.sql.ResultSet rset1 = st1.executeQuery("select sum(debit-credit) from ac_cash_book where account_no ='"+listofAct[i]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type ilike '%banki%'");// AND reconciled = true");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rset11 = st11.executeQuery("select bank_account_name,bank_account_no from ac_banks_setup where bank_account_no ='"+listofAct[i]+"'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rset161 = st161.executeQuery("select sum(credit-debit) from ac_cash_book where account_no ='"+listofAct[i]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type ilike 'paym%'");// AND reconciled = true");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                while (rset11.next()){
                                    table.getDefaultCell().setColspan(3);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1).toString().toUpperCase(), "-"), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A/C No. "+dbObject.getDBObject(rset11.getObject(2), "-"), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                }
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("PATICULARS",pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("AMOUNT"+ks,pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                try{
                                    //java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());
                                    java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());
                                    
                                    phrase = new Phrase("Openning Balance as Per Cash Book "+dateFormat.format(endDate11), pFontHeader);
                                    table.addCell(phrase);
                                } catch(java.text.ParseException psExec) {
                                    
                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                    
                                }
                                while (rset16.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset16.getString(1)),pFontHeader);
                                    Net = rset16.getDouble(1);
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Add Deposits ",pFontHeader);
                                table.addCell(phrase);
                                while (rset1.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontHeader);
                                    chq = rset1.getDouble(1);
                                    table.addCell(phrase);
                                    
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("    ",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Total ",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net+chq)),pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Less Payments ",pFontHeader);
                                table.addCell(phrase);
                                while (rset161.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset161.getString(1)),pFontHeader);
                                    pays = rset161.getDouble(1);
                                    table.addCell(phrase);
                                    
                                }
                                
                                try{
                                    java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());
                                    //  java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("Closing Balance As Per Cash Book "+dateFormat.format(endDate1),pFontHeader);
                                    table.addCell(phrase);
                                } catch(java.text.ParseException psExec) {
                                    
                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                    
                                }
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((Net+chq)-pays)),pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("    ",pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("      ",pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("      ",pFontHeader);
                                table.addCell(phrase);
                                
                                // java.sql.ResultSet rset1x = st1x.executeQuery("select sum(debit-credit) from ac_cash_collection where date BETWEEN '"+beginDate+"' AND '"+endDate+"'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rset1x = st1x.executeQuery("select sum(debit-credit) from ac_cash_book where account_no ='"+listofAct[i]+"' AND  date <= '"+endDate+"' AND transaction_type ilike 'banking%' AND (date_reconcilled > '"+endDate+"' OR date_reconcilled is null)");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                           //        java.sql.ResultSet rset161x = st161x.executeQuery("select sum(credit-debit) from ac_cash_book where account_no ='"+listofAct[i]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type ilike 'payment%' AND (date_reconcilled > '"+endDate+"' OR date_reconcilled is null)");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rset161x = st161x.executeQuery("select sum(credit-debit) from ac_cash_book where account_no ='"+listofAct[i]+"' AND  date <= '"+endDate+"' AND transaction_type ilike 'payment%' AND (date_reconcilled > '"+endDate+"' OR date_reconcilled is null)");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rsetx = st1xx.executeQuery("select sum(credit-debit) from ac_cash_book where account_no ='"+listofAct[i]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type ilike '%Casting%'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rset161xx = st161xx.executeQuery("select sum(credit-debit) from ac_cash_book where account_no ='"+listofAct[i]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type ilike '%discrip%'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rsetN = stN.executeQuery("select sum(amount) from ac_bank_rec where acc_no ='"+listofAct[i]+"' AND bnk_date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND narration ilike '%BALANCE%'");// and date between '"+beginDate+"'::date and  '"+endDate+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                while (rsetN.next()){
                                    balSt = rsetN.getDouble(1);
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Balance As Per Statement ",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balSt)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Unbanked Collection ",pFontHeader);
                                table.addCell(phrase);
                                while (rset1x.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1x.getString(1)),pFontHeader);
                                    unbank = rset1x.getDouble(1);
                                    table.addCell(phrase);
                                    
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Total ",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balSt+unbank)),pFontHeader1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("  ",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("   ",pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Less",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Unlocated Cash Credited By Bank",pFontHeader);
                                table.addCell(phrase);
                                while (rset161xx.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset161xx.getString(1)),pFontHeader);
                                    unlocated = rset161xx.getDouble(1);
                                    table.addCell(phrase);
                                    
                                }
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Unpresented Cheques",pFontHeader);
                                table.addCell(phrase);
                                while (rset161x.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset161x.getString(1)),pFontHeader);
                                    unprchq = rset161x.getDouble(1);
                                    table.addCell(phrase);
                                    
                                }
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Casting Error",pFontHeader);
                                table.addCell(phrase);
                                while (rsetx.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetx.getString(1)),pFontHeader);
                                    cast = rsetx.getDouble(1);
                                    table.addCell(phrase);
                                    
                                }
                                
                                totalless = cast+unprchq+unlocated;
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Total ",pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((balSt+unbank)-(totalless))),pFontHeader1);
                                
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
    
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT account_no FROM ac_cash_book WHERE account_no = '"+bank+"'");
            
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





