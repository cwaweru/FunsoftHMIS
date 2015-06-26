

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


public class ChequeVoucherPdf implements java.lang.Runnable {
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
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    private double netPay;
    
    private double deductions;
    
    public void ChequeVoucherPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate,java.lang.String combox) {
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
                        
                        java.sql.ResultSet rset21 = st31.executeQuery("select header_name from pb_header");
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
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Cheque Payment Voucher - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        for (int j = 0; j < listofStaffNos.length; j++) {
                            double chargeable = 0;
                            double relief = 0;
                            double earnings = 0;
                            double deduction = 0.00;
                            
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                            
                            int headerwidths[] = {25,12,13,50};
                            
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            table.setHeaderRows(1);
                            
                            com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(3);
                            
                            int headerwidths2[] = {33,33, 34};
                            table2.setWidths(headerwidths2);
                            
                            table2.setWidthPercentage((100));
                            
                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setColspan(4);
                            
                            Phrase phrase  = new Phrase("");;
                            Phrase phrase2 = new Phrase("");
                            
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                                
                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                
                                phrase = new Phrase("Pay Period :     Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                                
                                // table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            
                            
                            
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            double taxCharged = 0;
                            double paye = 0;
                            
                            
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
                                System.out.println("Statements Created ...");
                                
                                
                                
                                System.out.println(listofStaffNos[j].toString());
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct employee_no,first_name||' '||middle_name||' '||last_name,department,pin_no from master_file where employee_no = '"+listofStaffNos[j]+"' order by employee_no");
                                java.sql.ResultSet rsetss = stss.executeQuery("select bank,bank_account_no,payment_mode from master_file where employee_no = '"+listofStaffNos[j]+"'");
                                java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT sum(tt.amount) from posting tt where tt.staff_no = '"+listofStaffNos[j]+"' AND tt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ILIKE 'Earning%'");
                                java.sql.ResultSet rset18 = st18.executeQuery("select sum(dv.amount) from deduction_summary dv where dv.staff_no = '"+listofStaffNos[j]+"' AND dv.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rset19 = st19.executeQuery("select sum(amount) from net_pay_view dv where dv.staff_no = '"+listofStaffNos[j]+"' AND dv.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("CHEQUE PAYMENT VOUCHER",pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                while (rsetss.next()) {
                                    
                                    phrase = new Phrase("BANK ACCOUNT NO : "+dbObject.getDBObject(rsetss.getObject(2).toString(),"-"),pFontHeader);
                                    
                                    table.addCell(phrase);
                                }
                                while (rsetTotals.next()) {
                                    
                                    earnings = rsetTotals.getDouble(1);
                                    
                                    
                                }
                                while (rset18.next()) {
                                    deduction = rset18.getDouble(1);
                                }
                                while (rset19.next()) {
                                    netPay = rset19.getDouble(1);
                                }
                                while (rset.next()){
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setMinimumHeight(15);
                                    phrase = new Phrase("DATE : "+date,pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    //   phrase = new Phrase("Department       : " +dbObject.getDBObject(rset.getObject(3), "-"),pFontNum);
                                    
                                    phrase = new Phrase("CHQ/NO :",pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    //                                phrase = new Phrase("PIN No.            : " +dbObject.getDBObject(rset.getObject(4), "-"),pFontNum);
                                    
                                    phrase = new Phrase("AMOUNT : "+new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(netPay)),pFontHeader1);
                                    
                                    
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase("PAID TO : "+dbObject.getDBObject(rset.getObject(2).toString(),"-"),pFontHeader1);
                                    
                                    table.addCell(phrase);
                                }
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setMinimumHeight(10);
                                phrase = new Phrase("DETAILS", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                
                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                boolean bal = false;
                                double balance = 0.00;
                                double total = 0.00;
                                
                                for (int k = 0; k < listofAct.length; k++) {
                                    int Nob = 0;
                                    
                                    
                                    // java.sql.ResultSet rset17 = st17.executeQuery("select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from accubal_merge_view ac where ac.date <= '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from accubal_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from nonaccmerge_view ac where ac.date BETWEEN '"+dateFirst+"' AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from nonaccmerge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select av.description,sum(av.amount),(SELECT SUM(ac.acc_amount) from paye_merge_view ac where ac.date BETWEEN '"+dateFirst+"' AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from paye_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description");
                                    //                            //     java.sql.ResultSet rset17 = st17.executeQuery("select initcap(av.description),sum(av.amount),(SELECT SUM(ac.acc_amount) from accubal_merge_view ac where ac.date <= '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from accubal_merge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description UNION ALL select initcap(av.description),sum(av.amount),(SELECT SUM(ac.acc_amount) from nonaccmerge_view ac where ac.date BETWEEN ('1-1-'|| EXTRACT(YEAR from date('"+endDate+"'))):: date AND '"+endDate+"' and ac.staff_no = '"+listofStaffNos[j]+"' and ac.description = '"+listofAct[k]+"') from nonaccmerge_view av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description = '"+listofAct[k]+"' group by av.description");
                                    java.sql.Statement st171 = connectDB.createStatement();
                                    java.sql.Statement st1711 = connectDB.createStatement();
                                    java.sql.Statement st3111 = connectDB.createStatement();
                                    java.sql.Statement sts = connectDB.createStatement();
                                    
                                    java.sql.ResultSet rset171 = st171.executeQuery("select count(initcap(av.description)) from deduction_summary av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description ilike '"+listofAct[k]+"' group by av.description");
                                    while (rset171.next()) {
                                        Nob = rset171.getInt(1);
                                    }
                                    if(Nob > 0){
                                        java.sql.ResultSet rset17 = st17.executeQuery("select initcap(av.description),sum(av.amount) as amount from deduction_summary av where av.staff_no = '"+listofStaffNos[j]+"' AND av.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and av.description ilike '"+listofAct[k]+"' group by av.description");
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        while (rset17.next()) {
                                            phrase = new Phrase(listofAct[k].toString(),pFontNum);
                                            table.addCell(phrase);
                                            if (rset17.getFloat(2) >= 0) {
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset17.getString(2)),pFontNum);
                                                
                                                table.addCell(phrase);
                                            }
                                        }
                                        
                                        if(k == 0){
                                            try{
                                                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toString().trim()), "MMM/yy");
                                                
                                                java.lang.String monthString = dateFormatter.getDateString();
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                table.getDefaultCell().setColspan(2);
                                                phrase = new Phrase("Salary paid for the month of "+monthString, pFontNum);
                                                table.addCell(phrase);
                                            } catch(java.text.ParseException prs) {
                                                prs.printStackTrace();
                                            }
                                            
                                        }else{
                                            if(k == 1){
                                                // table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Gross Salary ", pFontNum);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings)),pFontNum);
                                                table.addCell(phrase);
                                                
                                            }else{
                                                if(k == 2){
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("Deductions ", pFontNum);
                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deduction)),pFontNum);
                                                    table.addCell(phrase);
                                                } else{
                                                    if(k == 3){
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        table.getDefaultCell().setColspan(1);
                                                        phrase = new Phrase("Net Pay ", pFontNum);
                                                        table.addCell(phrase);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(netPay)),pFontNum);
                                                        table.addCell(phrase);
                                                    }else{
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        table.getDefaultCell().setColspan(1);
                                                        phrase = new Phrase(" ", pFontNum);
                                                        table.addCell(phrase);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        
                                                        phrase = new Phrase("",pFontNum);
                                                        table.addCell(phrase);
                                                    }
                                                }
                                            }
                                            
                                        }
                                    }else{
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(listofAct[k].toString(),pFontNum);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"),pFontNum);
                                        
                                        table.addCell(phrase);
                                        
                                        
                                        
                                        if(k == 0){
                                            try{
                                                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toString().trim()), "MMM/yy");
                                                
                                                java.lang.String monthString = dateFormatter.getDateString();
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                table.getDefaultCell().setColspan(2);
                                                phrase = new Phrase("Salary paid for the month of "+monthString, pFontNum);
                                                table.addCell(phrase);
                                            } catch(java.text.ParseException prs) {
                                                prs.printStackTrace();
                                            }
                                            
                                        }else{
                                            if(k == 1){
                                                // table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Gross Salary ", pFontNum);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(earnings)),pFontNum);
                                                table.addCell(phrase);
                                                
                                            }else{
                                                if(k == 2){
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("Deductions ", pFontNum);
                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deduction)),pFontNum);
                                                    table.addCell(phrase);
                                                } else{
                                                    if(k == 3){
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        table.getDefaultCell().setColspan(1);
                                                        phrase = new Phrase("Net Pay ", pFontNum);
                                                        table.addCell(phrase);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(netPay)),pFontNum);
                                                        table.addCell(phrase);
                                                    }else{
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        table.getDefaultCell().setColspan(1);
                                                        phrase = new Phrase(" ", pFontNum);
                                                        table.addCell(phrase);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        
                                                        phrase = new Phrase("",pFontNum);
                                                        table.addCell(phrase);
                                                    }
                                                }
                                            }
                                            
                                        }
                                    }
                                    
                                    
                                    
                                }
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total ", pFontNum);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deduction)),pFontNum);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ",pFontNum);
                                table.addCell(phrase);
                                
                                table2.getDefaultCell().setColspan(3);
                                phrase2 = new Phrase(" ",pFontNum);
                                table2.addCell(phrase2);
                                table2.getDefaultCell().setColspan(1);
                                phrase2 = new Phrase(" A/C CODE",pFontNum);
                                table2.addCell(phrase2);
                                table2.getDefaultCell().setColspan(2);
                                phrase2 = new Phrase("DEBIT ACCOUNT ",pFontNum);
                                table2.addCell(phrase2);
                                table2.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table2.getDefaultCell().setColspan(3);
                                phrase2 = new Phrase(" ",pFontNum);
                                table2.addCell(phrase2);
                                table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                //table2.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table2.getDefaultCell().setColspan(1);
                                phrase2 = new Phrase("Prepared by : _________________ ",pFontNum);
                                table2.addCell(phrase2);
                                
                                table2.getDefaultCell().setColspan(1);
                                phrase2 = new Phrase("Checked by : ________________",pFontNum);
                                table2.addCell(phrase2);
                                table2.getDefaultCell().setColspan(1);
                                phrase2 = new Phrase("Authorised by : ________________",pFontNum);
                                table2.addCell(phrase2);
                                
                                docPdf.add(table);
                                docPdf.add(table2);
                                
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT UPPER(description) AS description FROM deduction_summary where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by description");
            
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





