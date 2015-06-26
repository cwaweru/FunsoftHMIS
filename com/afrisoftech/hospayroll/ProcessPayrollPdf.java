

//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospayroll;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class ProcessPayrollPdf implements java.lang.Runnable {
    Boolean bank1 ;
    
    java.lang.String bank = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String bank2 = null;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.util.Date dateEnd = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    Phrase phrase1;
    Phrase phrase2;
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);
    com.lowagie.text.Font pFontNum1 = FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void ProcessPayrollPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate,java.lang.String combox) {
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        bank = combox;
        // bank2 = chbox;
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new GlTransactPdf().GlTransactPdf();
        
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
        java.sql.ResultSet rsetTotals1 = null;
        java.sql.ResultSet rsetTotals1o = null;
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
                        
                        
                        java.sql.Statement st31 = connectDB.createStatement();
                        java.sql.Statement st41 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset21 = st31.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset41 = st41.executeQuery("SELECT date('now') as Date");
                        while(rset21.next()){
                            compName = rset21.getObject(1).toString();
                        }
                        while(rset41.next()){
                            date = rset41.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //         com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(2);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Payslip - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        for (int j = 0; j < listofStaffNos.length; j++) {
                            double chargeable = 0;
                            double relief = 0;
                            double earnings = 0;
                            
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(3);
                            
                            int headerwidths[] = {50,15,35};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            table.setHeaderRows(1);
                            
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            Phrase phrase;
                            
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                                
                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                
                                phrase = new Phrase("Pay Period :     Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                                
                                table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            
                            
                            
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            double taxCharged = 0;
                            double paye = 0;
                            double noncash = 0;
                            double totalTaxed = 0;
                            double nonTaxed = 0;
                            try {
                                
                                java.lang.Object[] listofAct = this.getListofActivities();
                                
                                //  java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                
                                System.out.println(listofAct.length);
                                //
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st3A = connectDB.createStatement();
                                java.sql.Statement st4A = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st13 = connectDB.createStatement();
                                java.sql.Statement st15 = connectDB.createStatement();
                                java.sql.Statement st14 = connectDB.createStatement();
                                java.sql.Statement st16 = connectDB.createStatement();
                                java.sql.Statement st17 = connectDB.createStatement();
                                java.sql.Statement st18 = connectDB.createStatement();
                                java.sql.Statement st19 = connectDB.createStatement();
                                java.sql.Statement stss = connectDB.createStatement();
                                java.sql.Statement st4o = connectDB.createStatement();
                                System.out.println("Statements Created ...");
                                
                                
                                
                                System.out.println(listofStaffNos[j].toString());
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct employee_no,first_name||' '||middle_name||' '||last_name,official_desgnation,pin_no from master_file where employee_no = '"+listofStaffNos[j]+"' order by employee_no");
                                java.sql.ResultSet rsetss = stss.executeQuery("select DISTINCT CASE WHEN(bank is null) THEN '-' ELSE bank END AS bank,CASE WHEN (bank_account_no IS NULL) THEN '-' ELSE bank_account_no END AS bank_account_no,CASE WHEN (payment_mode IS NULL) THEN '-' ELSE payment_mode END AS payment_mode from master_file where employee_no = '"+listofStaffNos[j]+"'");
                                java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT sum(tt.amount) from posting tt where tt.staff_no = '"+listofStaffNos[j]+"' AND tt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ILIKE 'Earning%'");
                                
                                
                                while (rset.next()){
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase = new Phrase("Name               : "+dbObject.getDBObject(rset.getObject(2).toString().toUpperCase(),"-"),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    //   phrase = new Phrase("Department       : " +dbObject.getDBObject(rset.getObject(3), "-"),pFontNum);
                                    
                                    phrase = new Phrase("Desgination       :  "+dbObject.getDBObject(rset.getObject(3).toString().toUpperCase(),"-"),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    //                                phrase = new Phrase("PIN No.            : " +dbObject.getDBObject(rset.getObject(4), "-"),pFontNum);
                                    
                                    phrase = new Phrase("EMP No.            :  "+dbObject.getDBObject(rset.getObject(1).toString(),"-"),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                table.getDefaultCell().setColspan(1);
                                
                                phrase = new Phrase("Description", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase("Amount KShs.", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Days/Hrs", pFontHeader);
                                
                                table.addCell(phrase);
                                java.sql.ResultSet rset11F = st13.executeQuery("select initcap(description),sum(amount) from non_taxed_earnings WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' GROUP BY staff_no,description");
                                
                                java.sql.ResultSet rset11 = st31.executeQuery("select initcap(description),sum(amount) from posting where allowance_deduction = 'Less Relief' and staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' group by staff_no,description");
                                java.sql.ResultSet rset12 = st12.executeQuery("select sum(amount) from posting where allowance_deduction = 'Less Relief' and staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                //        java.sql.ResultSet rset13 = st13.executeQuery("select chargeable_pay from tax_card where staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                //  java.sql.ResultSet rset14 = st14.executeQuery("select sum(tx.tax_charged) from tax_card tx where tx.staff_no = '"+listofStaffNos[j]+"' AND tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rset14 = st14.executeQuery("select sum(tx.amount) from posting tx where tx.staff_no = '"+listofStaffNos[j]+"' AND tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'Monthly Personal Relief%' OR tx.description ILIKE 'P.A.Y.E%')");
                                
                                java.sql.ResultSet rset15 = st15.executeQuery("select amount from posting where description = 'Monthly Personal Relief' and staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                //  java.sql.ResultSet rset16 = st16.executeQuery("select tx.paye_tax from tax_card tx where tx.staff_no = '"+listofStaffNos[j]+"' AND tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rset16 = st16.executeQuery("select tx.amount from posting tx where tx.staff_no = '"+listofStaffNos[j]+"' AND tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'P.A.Y.E%')");
                                
                                rsetTotals1o = st4o.executeQuery("SELECT sum(pt.amount) from amount_taxed pt WHERE pt.staff_no = '"+listofStaffNos[j]+"' AND pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                
                                java.sql.ResultSet rset18 = st18.executeQuery("select sum(dv.amount) from deduction_summary dv where dv.staff_no = '"+listofStaffNos[j]+"' AND dv.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rset19 = st19.executeQuery("select sum(amount) from net_pay_view dv where dv.staff_no = '"+listofStaffNos[j]+"' AND dv.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                rsetTotals1 = st4.executeQuery("SELECT sum(pt.amount) from posting pt,deductions_allowances da WHERE pt.staff_no = '"+listofStaffNos[j]+"' AND pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pt.description::text ~~* da.description::text AND da.taxable = true AND da.benefit = true group by pt.allowance_deduction");
                                //                                rsetTotals1 = st4.executeQuery("SELECT sum(pt.amount) from amount_taxable_view pt WHERE pt.staff_no = '"+listofStaffNos[j]+"' AND pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pt.allowance_deduction ILIKE 'Earning%' group by pt.allowance_deduction");
                                java.sql.ResultSet rsetTotals1A = st4A.executeQuery("SELECT sum(pt.amount) from posting pt WHERE pt.staff_no = '"+listofStaffNos[j]+"' AND pt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pt.allowance_deduction ILIKE 'NON CASH%' group by pt.allowance_deduction");
                                java.sql.ResultSet rset1 = st3.executeQuery("select INITCAP(ps.description),sum(ps.amount),ps.hoursdays FROM posting ps,deductions_allowances da WHERE ps.staff_no = '"+listofStaffNos[j]+"' AND ps.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ps.allowance_deduction ILIKE 'Earning%' AND ps.description = da.description AND da.taxable = 'true' GROUP BY ps.allowance_deduction,ps.description,ps.hoursdays ORDER BY ps.description");
                                java.sql.ResultSet rset1A = st3A.executeQuery("select initcap(description),sum(amount) from posting where date BETWEEN '"+beginDate+"' AND '"+endDate+"' and staff_no = '"+listofStaffNos[j]+"' AND allowance_deduction ILIKE 'NON CASH%' group by allowance_deduction,description");
                                System.out.println("Select A done ...");
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                while (rsetTotals1o.next()) {
                                    totalTaxed = rsetTotals1o.getDouble(1);
                                }
                                //  while (rsetTotals1.next()) {
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Taxable Earnings", pFontHeader);
                                
                                table.addCell(phrase);
                                
                               
                                while (rset1.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset1.getObject(1).toString(),pFontNum);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1.getString(2),"0.00")),pFontNum);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1.getString(3),"0.00")),pFontNum);
                                    
                                    table.addCell(phrase);
                                    earnings = earnings + rset1.getDouble(2);
                                    
                                }
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Taxable Earnings", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Non Taxable Earnings", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                while (rset11F.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset11F.getObject(1).toString(),pFontNum);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11F.getString(2),"0.00")),pFontNum);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("",pFontNum);
                                    
                                    table.addCell(phrase);
                                    nonTaxed = nonTaxed + rset11F.getDouble(2);
                                    
                                }
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Non Taxable Earnings", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nonTaxed)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Non Cash Benefits", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                while (rset1A.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset1A.getObject(1).toString(),pFontNum);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset1A.getObject(2).toString(),"-")),pFontNum);
                                    table.addCell(phrase);
                                    
                                    table.addCell(" ");
                                }
                                
                                while (rsetTotals1A.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Non Cash Benefits", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rsetTotals1A.getObject(1).toString(),"-")), pFontHeader);
                                    
                                    table.addCell(phrase);//phrase);
                                    table.addCell("");
                                    
                                    noncash = noncash + rsetTotals1A.getDouble(1);
                                    
                                }
                                
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Gross Earnings".toUpperCase(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings+nonTaxed+noncash)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");
                                
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell(" ");
                                
                                table.getDefaultCell().setColspan(1);
                                
                               
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Taxable Amount", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rsetTotals.getString(1),"-")), pFontHeader);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings+noncash)), pFontHeader);
                                
                                table.addCell(phrase);
                                earnings = earnings + noncash;
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell("");
                                
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Less Relief", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                while (rset11.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset11.getObject(1).toString(),pFontNum);
                                    
                                    table.addCell(phrase);
                                    
                                    //               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //               table.addCell(rset.getObject(4).toString());
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset11.getString(2),"-")),pFontNum);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(3)),pFontNum);
                                    table.addCell("  ");
                                }
                                
                                while (rset12.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Non Taxable Amount",pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    //               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //               table.addCell(rset.getObject(4).toString());
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(rset12.getString(1),"0")),pFontHeader);
                                    table.addCell(phrase);
                                    table.addCell("");
                                    
                                    relief = rset12.getDouble(1);
                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(6)),pFontNum);
                                    // table.addCell(phrase);
                                }
                                
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Net Taxable amount",pFontHeader);
                                
                                table.addCell(phrase);
                                
                                //               table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //               table.addCell(rset.getObject(4).toString());
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                chargeable = earnings - relief;
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(chargeable)),pFontHeader);
                                
                                //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset13.getString(1)),pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.addCell("");
                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(6)),pFontNum);
                                // table.addCell(phrase);
                                //}
                                
                                while (rset14.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Tax Charged",pFontNum);
                                    
                                    table.addCell(phrase);
                                    
                                  
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset14.getString(1)),pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");
                                   
                                }
                                
                                while (rset15.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Monthly Personal Relief",pFontNum);
                                    
                                    table.addCell(phrase);
                                      
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset15.getString(1)),pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");
                                }
                                
                                
                                while (rset16.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Paye",pFontNum);
                                    
                                    table.addCell(phrase);
                                       
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset16.getString(1)),pFontNum);
                                    table.addCell(phrase);
                                    table.addCell("");
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("DEDUCTIONS", pFontHeader);
                                
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("YTD Total", pFontHeader);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                int Nob = 0;
                                
                                boolean bal = false;
                                double balance = 0.00;
                                double deduction = 0.00;
                                double total = 0.00;
                                for (int k = 0; k < listofAct.length; k++) {
                                    
                                    // java.sql.ResultSet rset17 = st17.executeQuery("select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from accubal_merge_view ac where ac.date <= '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from accubal_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from nonaccmerge_view ac where ac.date BETWEEN '"+dateFirst+"' AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from nonaccmerge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from paye_merge_view ac where ac.date BETWEEN '"+dateFirst+"' AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from paye_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description");
                                    //                            //     java.sql.ResultSet rset17 = st17.executeQuery("select initcap(av.description),sum(av.amount),(SELECT SUM(ac.acc_amount) from accubal_merge_view ac where ac.date <= '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from accubal_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select initcap(av.description),sum(av.amount),(SELECT SUM(ac.acc_amount) from nonaccmerge_view ac where ac.date BETWEEN ('1-1-'|| EXTRACT(YEAR from date('"+endDate+"'))):: date AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from nonaccmerge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description");
                                    java.sql.Statement st171 = connectDB.createStatement();
                                    java.sql.Statement st1711 = connectDB.createStatement();
                                    java.sql.Statement st3111 = connectDB.createStatement();
                                    java.sql.Statement sts = connectDB.createStatement();
                                    
                                    java.sql.ResultSet rset17 = st17.executeQuery("select initcap(av.description),sum(av.amount) as amount from deduction_summary av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description ilike '"+listofAct[k]+"' group by av.description");
                                    java.sql.ResultSet rset171 = st171.executeQuery("select av.balance as amount,av.acc_bal from sacco_deductions av where av.staff_no = '"+listofStaffNos[j]+"' and av.sacco_name ilike '"+listofAct[k]+"%'");
                                    java.sql.ResultSet rset1711 = st1711.executeQuery("select count(staff_no) from sacco_deductions av where av.staff_no = '"+listofStaffNos[j]+"' and av.sacco_name ilike '"+listofAct[k]+"%'");
                                    java.sql.ResultSet rset11w = st3111.executeQuery("select sum(np.amount) from posting np where np.description ilike '"+listofAct[k]+"%' and np.staff_no ilike '"+listofStaffNos[j]+"' AND np.date > '"+endDate+"' and np.processed = true group by np.staff_no order by np.staff_no");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                    java.sql.ResultSet rsets = sts.executeQuery("select count(staff_no) from posting np where np.description = '"+listofAct[k]+"%' and np.staff_no ilike '"+listofStaffNos[j]+"' AND np.date > '"+endDate+"'  and np.processed = true");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                    
                                    while (rset17.next()) {
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset17.getObject(1).toString(),pFontNum);
                                        
                                        table.addCell(phrase);
                                        while (rset11w.next()) {
                                            deduction = rset11w.getDouble(1);
                                        }
                                        if (rset17.getFloat(2) > 0) {
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset17.getString(2)),pFontNum);
                                            table.addCell(phrase);
                                            //  deduction = rset17.getDouble(2);
                                        }else{
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase("0.00",pFontNum);
                                            table.addCell(phrase);
                                            //  deduction = 0.00;
                                        }
                                        
                                        
                                        while (rset1711.next()) {
                                            Nob = rset1711.getInt(1);
                                        }
                                        
                                        if (Nob > 0) {
                                            
                                            while (rset171.next()) {
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                bal = rset171.getBoolean(2);
                                                balance = rset171.getDouble(1);
                                                System.out.println("This is The balance " +balance);
                                            }
                                            if (bal==false){
                                                System.out.println("This is The balance1 " +balance);
                                                System.out.println("This is The deduction " +deduction);
                                                total = balance+deduction;
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)),pFontNum);
                                                table.addCell(phrase);
                                            }else{
                                                System.out.println("This is The balance2 " +balance);
                                                System.out.println("This is The deduction2 " +deduction);
                                                total = balance-deduction;
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)),pFontNum);
                                                table.addCell(phrase);
                                            }
                                            //}
                                            
                                        }else{
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase("0.00",pFontNum);
                                            table.addCell(phrase);
                                            balance = 0.00;
                                        }
                                        
                                    }
                                }
                                
                                
                                
                                while (rset18.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("TOTAL DEDUCTIONS",pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                      
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset18.getString(1)),pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.addCell(" ");
                                }
                                
                                while (rset19.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("NET PAY   :",pFontNum1);
                                    System.out.println("Net Pay done");
                                    table.addCell(phrase);
                                    
                                     table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset19.getString(1)),pFontNum1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.addCell(" ");
                                }
                                while (rsetss.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payment By    :   "+dbObject.getDBObject(rsetss.getObject(3).toString(),"-"),pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Bank Name     :   "+dbObject.getDBObject(rsetss.getObject(1).toString(),"-"),pFontHeader);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Account No    :  "+dbObject.getDBObject(rsetss.getObject(2).toString(),"-"),pFontHeader);
                                    table.addCell(phrase);
                                    
                                    
                                    
                                    
                                }
                                
                                docPdf.add(table);
                                
                                
                                
                                System.out.println("Status of new page : "+docPdf.getPageNumber());
                                
                                
                                boolean boolNewPage = docPdf.newPage();
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    // }
                    
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank+"' order by staff_no");
            
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
    
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM deduction_summary where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by description");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
}





