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


public class DebtorsPerformancePdf implements java.lang.Runnable {
    
    int cn= 0;
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.lang.String Debtor = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    String ks;
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
     com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLDITALIC);
   
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DebtorsPerformancePdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate, java.lang.String debtor) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        Debtor = debtor;
        
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
                    
                    
                    
                    //  double osBalance = 0.00;
                    double osBalanceb1 = 0.00;
                    double osBalance1 = 0.00;
                    double OsBalanceOPC1 = 0.00;
                    double osBalanceIP1 = 0.00;
                    double osBalanceIPC1 = 0.00;
                    double osBalanceOPC1 = 0.00;
                    int count = 0;
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
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2 = st2.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while(rset2.next()){
                                ks = rset2.getObject(1).toString();
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(7);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("This report calculates on basis of finalised invoices - Page: ",pFontHeader4), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                   //  docPdf.open();
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {5,22,14,7,14,6,7,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(4);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        Phrase phrase = new Phrase();
                        //  table.getDefaultCell().setColspan(8);
                        
                        try {
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st41 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            //  java.sql.ResultSet rset4 = st4.executeQuery("select scheme_manager from ac_scheme_providers where scheme_manager = '"+memNo+"'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select scheme_manager,account_no from ac_scheme_providers where scheme_manager = '"+Debtor+"'");
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("PAYER PERFORMANCE", pFontHeader);
                            table.addCell(phrase);
                            
                            
                            while (rset4.next()){
                                table.getDefaultCell().setColspan(4);
                                  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Payer Account No : " +rset4.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Printed On: " +datenowSql, pFontHeader);
                            table.addCell(phrase);
                            }
                          //  table.getDefaultCell().setColspan(5);
                            
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                                
                                //  table.addCell(phrase);
                                
                                  table.getDefaultCell().setColspan(8);
                                  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           
                                phrase = new Phrase(Debtor+" SCHEME Performance Between " +dateFormat.format(endDate11)+" AND "+dateFormat.format(endDate1), pFontHeader);
                                
                                table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                           /* 
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date " +datenowSql, pFontHeader);
                            table.addCell(phrase);
                            
                            */
                            table.getDefaultCell().setColspan(1);
                            
                            //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            
                              phrase = new Phrase("No.",pFontHeader);
                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase("Scheme",pFontHeader);
                            table.addCell(phrase);
                            
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("OP Bills "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("OP count",pFontHeader);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("IP Bills "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("IP count",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("TOTAL count",pFontHeader);
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total Bills "+ks,pFontHeader);
                                                table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            
                            
                            //try {
                            double osBalancebf = 0.00;
                            
                            java.sql.Statement stOp = connectDB.createStatement();
                            java.sql.Statement stT = connectDB.createStatement();
                            
                            java.sql.Statement stIp = connectDB.createStatement();
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                double osBalance = 0.00;
                                double OsBalanceOP = 0.00;
                                double osBalanceIP = 0.00;
                                double osBalanceIPC = 0.00;
                                double osBalanceOPC = 0.00;
                                
                                double osBalanceT = 0.00;
                                
                                int countOp = 0;
                                int countIp = 0;
                                int bothIpOp = 0;
                                //  java.sql.ResultSet rset = st.executeQuery("select invoice_no,date::date,dealer,scheme,patient_no,sum(debit),sum(credit),sum(debit-credit) from hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (scheme !='' and invoice_no !='') GROUP BY invoice_no,date::date,isurer,scheme,patient_no");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                //   java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(debit),sum(credit),sum(debit-credit) from hp_patient_card WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (scheme !='' and invoice_no !='')");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables')");
                                java.sql.ResultSet rsetOp = stOp.executeQuery("select sum(debit),count(invoice_no) from ac_debtors db WHERE db.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND db.dealer ILIKE '"+Debtor+"' and account_no = '"+listofStaffNos[j]+"' and invoice_no ilike 'O%'");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rsetIp = stIp.executeQuery("select sum(debit),count(invoice_no) from ac_debtors db WHERE db.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND db.dealer ILIKE '"+Debtor+"' and account_no = '"+listofStaffNos[j]+"' and invoice_no ilike 'I%'");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rsetT = stT.executeQuery("select count(invoice_no),sum(debit) from ac_debtors db WHERE db.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND db.dealer ILIKE '"+Debtor+"' and account_no = '"+listofStaffNos[j]+"' and (invoice_no ilike 'I%' and invoice_no ilike 'O%')");// and (transaction_type = 'Raise Invoice' OR transaction_type = 'Stock Delivery' OR transaction_type = 'Stock Returns') and (description = 'Creditors Payable' OR description = 'Other Payables') GROUP BY invoice_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rset11 = st11.executeQuery("select upper(scheme_name) from ac_schemes where account_no = '"+listofStaffNos[j]+"'");
                                
                                
                               
                                while (rset11.next()){
                                    
                                    cn = cn+ 1;
                                     table.getDefaultCell().setColspan(1);
                                   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(cn+ ".", "-"), pFontHeader);
                                  table.addCell(phrase);
                                
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(""+listofStaffNos[j] + " " +  rset11.getObject(1), pFontHeader1);
                                    table.addCell(phrase);
                              //}
                                
                                while (rsetOp.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetOp.getString(1)),pFontHeader1);
                                    osBalance = osBalance + rsetOp.getDouble(1);
                                    osBalance1 = osBalance1 + rsetOp.getDouble(1);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rsetOp.getObject(2), "0"), pFontHeader);
                                    osBalanceOPC = osBalanceOPC + rsetOp.getDouble(2);
                                    osBalanceOPC1 = osBalanceOPC1 + rsetOp.getDouble(2);
                                    
                                    table.addCell(phrase);
                                }
                                
                                while (rsetIp.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetIp.getString(1)),pFontHeader1);
                                    osBalanceIP = osBalanceIP + rsetIp.getDouble(1);
                                    osBalanceIP1 = osBalanceIP1 + rsetIp.getDouble(1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rsetIp.getObject(2), "0"), pFontHeader);
                                    osBalanceIPC = osBalanceIPC + rsetIp.getDouble(2);
                                    osBalanceIPC1 = osBalanceIPC1 + rsetIp.getDouble(2);
                                    
                                    table.addCell(phrase);
                                }
                          // } 
                                //  while (rsetT.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceIPC+osBalanceOPC)),pFontHeader);
                                  osBalancebf = osBalanceIPC+osBalanceOPC;
                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetT.getString(1)),pFontHeader1);
                                //osBalanceT = osBalanceT + rsetT.getDouble(1);
                                table.addCell(phrase);
                                // phrase = new Phrase(dbObject.getDBObject(rsetT.getObject(2), "-"), pFontHeader);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceIP+osBalance)),pFontHeader);
                                osBalanceb1 = osBalanceIP+osBalance;
                                
                                table.addCell(phrase);
                                
                                
                            }
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //   while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)),pFontHeader);
                            
                            table.addCell(phrase);
                            //  phrase = new Phrase(dbObject.getDBObject(osBalanceOPC1, "-"),pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceOPC1)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceIP1)),pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceIPC1)),pFontHeader);
                            // phrase = new Phrase(osBalanceIPC1,pFontHeader);
                            
                            table.addCell(phrase);
                            //  phrase = new Phrase(osBalanceOPC1+osBalanceIPC1,pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceOPC1+osBalanceIPC1)),pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1+osBalanceIP1)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            //   table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Avg Cost Per OP Visit : ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            if (osBalance1 == 0){
                                osBalance1 = 1;
                            }else{
                                osBalance1 = osBalance1;
                            }
                            
                            if (osBalanceOPC1 == 0){
                                osBalanceOPC1 = 1;
                            }else{
                                osBalanceOPC1 = osBalanceOPC1;
                            }
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1/osBalanceOPC1)),pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Avg.Cost Per IP Admission : ", pFontHeader);
                            
                            table.addCell(phrase);
                            if(osBalanceIP1 == 0 || osBalanceIPC1 == 0){
                                phrase = new Phrase("0.00",pFontHeader);
                                // phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                            }else{
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceIP1/osBalanceIPC1)),pFontHeader);
                                // phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            
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
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT account_no FROM ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dealer = '"+Debtor+"' and debit > 0 order by account_no");
            
            while (rSet1.next()) {
                  System.out.println(rSet1.getObject(1).toString());
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





