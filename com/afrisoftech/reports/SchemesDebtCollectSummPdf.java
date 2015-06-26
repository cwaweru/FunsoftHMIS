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


public class SchemesDebtCollectSummPdf implements java.lang.Runnable {
    
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.lang.String Debtor = null;
    int SchmCount = 0;
    String ks;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void SchemesDebtCollectSummPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        // Debtor = debtor;
        
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
            
            //            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    double osBalance122 = 0.00;
                    double osBalancebf1 = 0.00;
                    double osBalanceb1 = 0.00;
                    double ospBalance1 = 0.00;
                    double osBalance1 = 0.00;
                    double osBalance12 = 0.00;
                    String compName = null;
                    String date = null;
                    
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
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
                            java.sql.Statement st2x = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while(rset2x.next()){
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(7);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                            table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Scheme Debt Collection Report (summary)- Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {30,15,12,12,12,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        Phrase phrase = new Phrase();
                        //  table.getDefaultCell().setColspan(8);
                        
                        table.getDefaultCell().setColspan(4);
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            
                            phrase = new Phrase( "Debt Collection Report (Summary)- Period : " +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), pFontHeader1);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        //phrase = new Phrase("Period : '"+beginDate+"' - '"+endDate+"'", pFontHeader1);
                        //table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(2);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Date " +datenowSql, pFontHeader1);
                        table.addCell(phrase);
                        
                        // table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setColspan(1);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        
                        
                        
                        
                        phrase = new Phrase("Name",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("O/S",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Invoices",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Receipts",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Unalloc. ",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Bal "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        // java.sql.Statement st111 = connectDB.createStatement();
                        // java.sql.ResultSet rset111 = st111.executeQuery("select distinct count(db.debit) from ac_debtors db WHERE db.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and ");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                        
                        try {
                            java.lang.Object[] listofAct1 = this.getListofStaffNos1();
                            // for (int k = 0; k < listofAct1.length; k++) {
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //         phrase = new Phrase(listofAct1[k].toString().toUpperCase(), pFontHeader);
                            table.addCell(phrase);
                            double osBalancebf = 0.00;
                            double osBalanceb = 0.00;
                            double ospBalance = 0.00;
                            double osBalance = 0.00;
                            double  osBalance12a = 0.00;
                            double osBalance122a = 0.00;
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                // java.lang.Object listofStaffNos[] = this.getListofStaffNos(listofAct1[k]);
                                
                                
                                
                                
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st11 = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st2a = connectDB.createStatement();
                                
                                java.sql.Statement st22 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset22 = st22.executeQuery("select SUM(db.balance-db.credit_bal) from ac_debtors db ,ac_schemes ac WHERE  db.account_no = ac.account_no and ac.contact3 ilike '"+listofStaffNos[j]+"'");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rset1a = st2a.executeQuery("select SUM(db.credit_bal) from ac_debtors db,ac_schemes ac WHERE  db.account_no = ac.account_no and ac.contact3 ilike '"+listofStaffNos[j]+"'");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                
                                java.sql.ResultSet rset1 = st2.executeQuery("select SUM(db.balance) from ac_debtors db ,ac_schemes ac WHERE  db.account_no = ac.account_no and ac.contact3 ilike '"+listofStaffNos[j]+"' ");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                //  java.sql.ResultSet rset = st.executeQuery("select db.account_no,upper(db.scheme_name) from ac_schemes db WHERE db.account_no = '"+listofStaffNos[j]+"' ");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rset11 = st11.executeQuery("select sum(db.debit),sum(db.credit) from ac_debtors db,ac_schemes ac WHERE db.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and db.account_no = ac.account_no and ac.contact3 ilike '"+listofStaffNos[j]+"'");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                
                                   
                                SchmCount = SchmCount +1;
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+listofStaffNos[j], pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                while (rset1.next()) {
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontHeader1);
                                    ospBalance = ospBalance + rset1.getDouble(1);
                                    ospBalance1 = ospBalance1 + rset1.getDouble(1);
                                    table.addCell(phrase);
                                }
                                while (rset11.next()) {
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(1)),pFontHeader1);
                                    osBalance = osBalance + rset11.getDouble(1);
                                    table.addCell(phrase);
                                    osBalance1 = osBalance1 + rset11.getDouble(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(2)),pFontHeader1);
                                    osBalanceb = osBalanceb + rset11.getDouble(2);
                                    table.addCell(phrase);
                                    osBalanceb1 = osBalanceb1 + rset11.getDouble(2);
                                    
                                    
                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance-osBalanceb)),pFontHeader);
                                    
                                    // table.addCell(phrase);
                                    
                                }
                                while (rset1a.next()) {
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1a.getString(1)),pFontHeader1);
                                    osBalance122a = osBalance122a + rset1a.getDouble(1);
                                    osBalance122 = osBalance122 + rset1a.getDouble(1);
                                    table.addCell(phrase);
                                }
                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancebf)),pFontHeader);
                                
                                //table.addCell(phrase);
                                while (rset22.next()) {
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset22.getString(1)),pFontHeader1);
                                    osBalance12a = osBalance12a + rset22.getDouble(1);
                                    osBalance12 = osBalance12 + rset22.getDouble(1);
                                    table.addCell(phrase);
                                }
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //   while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(ospBalance)),pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)),pFontHeader);
                            
                            table.addCell(phrase);
                            // table.getDefaultCell().setColspan(2);
                            
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceb)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancebf)),pFontHeader);
                            
                            // table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance122a)),pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance12a)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                    /*        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                     
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                     
                            //   while (rsetTotals.next()) {
                     
                            table.getDefaultCell().setColspan(1);
                     
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Grand Total", pFontHeader);
                     
                            table.addCell(phrase);
                     
                            table.getDefaultCell().setColspan(1);
                     
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                     
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(ospBalance1)),pFontHeader);
                     
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)),pFontHeader);
                     
                            table.addCell(phrase);
                            // table.getDefaultCell().setColspan(2);
                     
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                     
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceb1)),pFontHeader);
                     
                            table.addCell(phrase);
                     
                            table.getDefaultCell().setColspan(1);
                     
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                     
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancebf)),pFontHeader);
                     
                            // table.addCell(phrase);
                     
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance122)),pFontHeader);
                     
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                     
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance12)),pFontHeader);
                     
                            table.addCell(phrase);
                     */
                            table.getDefaultCell().setColspan(12);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" " , pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Collector Count: " +SchmCount , pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Average Collection : " +new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1/SchmCount)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Average Receipts : " +new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceb1/SchmCount)),pFontHeader);
                            
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
            
            try {
                
                if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                    
                    System.out.println(tempFile);
                    
                    //  wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    
                    wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    
                    wait_for_Pdf2Show.waitFor();
                    
                } else {
                    
                    wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                    
                    wait_for_Pdf2Show.waitFor();
                    
                }
                
            } catch(java.lang.InterruptedException intrExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                
            }
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    public java.lang.Object[] getListofStaffNos1() {
        
        java.lang.Object[] listofStaffNos1 = null;
        
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
        
        
      /*  try {
       
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
       
            java.sql.Statement stmt1 = connectDB.createStatement();
       
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payer_name FROM ac_schemes  WHERE  contact3  ILIKE '"+Debtor+"' ");
       
            while (rSet1.next()) {
       
                listStaffNoVector1.addElement(rSet1.getObject(1).toString());
       
            }
       
        }catch (java.sql.SQLException sqlExec) {
       
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
       
        }
       */
        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
    }
    
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT invoice_no FROM st_stock_cardex WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and supplier = '"+suppName+"' order by invoice_no");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT initcap(contact3) FROM ac_schemes  order by initcap(contact3)");
            
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





