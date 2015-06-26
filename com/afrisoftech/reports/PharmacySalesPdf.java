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


public class PharmacySalesPdf implements java.lang.Runnable {
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String beginDate = null;
    
    java.lang.String endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    String ks;
    double osBalance = 0.00;
    double current = 0.00;
    double over30 = 0.00;
    double over60 = 0.00;
    double over90 = 0.00;
    double turnOver = 0.00;
    double markup = 0.00;
    double contribution = 0.00;
    double price = 0.00;
    double over601 = 0.00;
    double over901 = 0.00;
    double turnOver1 = 0.00;
    double markup1 = 0.00;
    double contribution1 = 0.00;
    double price1 = 0.00;
    double osBalance1 = 0.00;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void PharmacySalesPdf(java.sql.Connection connDb) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
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
                    try {
                        
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st2x = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while(rset2x.next()){
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName ,pFontHeader2),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Pharmacy List - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);
                        
                        int headerwidths[] = {19,7,10,10,13,13,10,9,9};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(7);
                        
                        Phrase phrase = new Phrase("Pharmacy Sales : " +endDate, pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Printed on : "+date,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Description",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        phrase = new Phrase("Unit",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Last Month Qty",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Current Month Qty",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Total Cost",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Total Sales",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Gross Profit "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("% Markup",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Contribution",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.sql.Statement stk = connectDB.createStatement();
                            // java.sql.ResultSet rsetTotals7 = stk.executeQuery("SELECT SUM(credit - debit) from ac_accounts_payable WHERE description = 'Creditors Payable'");
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st22 = connectDB.createStatement();
                                java.sql.Statement st23 = connectDB.createStatement();
                                java.sql.Statement st211 = connectDB.createStatement();
                                java.sql.Statement st221 = connectDB.createStatement();
                                java.sql.Statement st231 = connectDB.createStatement();
                                java.sql.Statement st2A = connectDB.createStatement();
                                java.sql.Statement st2B = connectDB.createStatement();
                                java.sql.Statement st2C = connectDB.createStatement();
                                //  java.sql.ResultSet rset = st2.executeQuery("select initcap(dealer),sum(credit - debit) from ac_accounts_payable WHERE dealer = '"+listofAct[i]+"' AND dealer IS NOT NULL AND date < 30 group by dealer");
                                
                                //java.sql.ResultSet rset = st.executeQuery("select dealer,sum(credit) from ac_accounts_payable WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and transaction_type = 'Payment' and description = 'Creditors Payable' OR description = 'Other Payables' AND debit >0 GROUP BY voucher_no,date,dealer,item ORDER BY date");
                                java.sql.ResultSet rset = st21.executeQuery("select initcap(description) as description,initcap(units) as units from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND amount > 0 group by description,units");
                                
                                java.sql.ResultSet rset2 = st22.executeQuery("select sum(quantity) as quantity from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND amount > 0 AND date_prescribed <= current_date - interval '1 month' group by description");
                                java.sql.ResultSet rset3 = st23.executeQuery("select sum(quantity) as quantity from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND amount > 0 AND date_prescribed  >= current_date - interval '1 month' group by description");
                                java.sql.ResultSet rset1A = st2A.executeQuery("select avg(price)::numeric(30,2) from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND amount > 0");//select sum(debit) AS debit from ac_ledger WHERE service_type = '"+listofAct[i]+"' AND date < current_date AND date > current_date - interval '1 month'  group by service_type");
                                
                                // java.sql.ResultSet rset1A = st2A.executeQuery("select avg(transfer_price)::numeric(30,2) from st_stock_prices where product = '"+listofAct[i]+"' and department = 'PHA'");//select sum(debit) AS debit from ac_ledger WHERE service_type = '"+listofAct[i]+"' AND date < current_date AND date > current_date - interval '1 month'  group by service_type");
                                java.sql.ResultSet rset2B = st2B.executeQuery("select sum(amount) AS credit from hp_pharmacy WHERE description = '"+listofAct[i]+"' group by description");
                                //   java.sql.ResultSet rset3C = stC.executeQuery("select CASE WHEN (dealer IS NULL) THEN '0.00' ELSE sum(credit-debit) END AS credit from ac_accounts_payable WHERE dealer = '"+listofAct[i]+"' AND date < curr by dealer");//WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and transaction_type = 'Payment' and description = 'Creditors Payable' OR description = 'Other Payables' AND debit >0");
                                
                                java.sql.ResultSet rset11 = st211.executeQuery("select COUNT(description) from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND amount > 0 AND date_prescribed <= current_date - interval '1 month'");
                                java.sql.ResultSet rset21 = st221.executeQuery("select COUNT(description) from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND amount > 0 AND date_prescribed  >= current_date - interval '1 month'");// group by dealer");//WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and transaction_type = 'Payment' and description = 'Creditors Payable' OR description = 'Other Payables' AND debit >0");
                                //java.sql.ResultSet rset31 = st231.executeQuery("select COUNT(description) from hp_pharmacy WHERE description = '"+listofAct[i]+"' AND date < current_date  and date > current_date - interval '1 month'");//WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and transaction_type = 'Payment' and description = 'Creditors Payable' OR description = 'Other Payables' AND debit >0");
                                
                                
                                while (rset.next()){
                                    
                                    table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                
                                //phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                
                                //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                                }
                                int j = 0;
                                int k = 0;
                                int r = 0;
                                //  while (rset1.next()){
                                while (rset11.next()){
                                    j = rset11.getInt(1);
                                }
                                if (j > 0){
                                    
                                    while (rset2.next()){
                                        System.out.println("This Result " +rset2.getString(1));
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(1)),pFontHeader1);
                                        table.addCell(phrase);
                                        current = current + rset2.getDouble(1);
                                    }
                                }else{
                                    phrase = new Phrase("0.00" ,pFontHeader1);
                                    table.addCell(phrase);
                                }
                                
                                while (rset21.next()){
                                    k = rset21.getInt(1);
                                }
                                if (k > 0){
                                    
                                    while (rset3.next()){
                                        System.out.println("This Result1 " +rset3.getString(1));
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(1)),pFontHeader1);
                                        table.addCell(phrase);
                                        over60 = rset3.getDouble(1);
                                        over601 = over601 + rset3.getDouble(1);
                                    }
                                }else{
                                    phrase = new Phrase("0.00" ,pFontHeader1);
                                    table.addCell(phrase);
                                }
                                while (rset1A.next()){
                                    System.out.println("This Result2 " +rset1A.getString(1));
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    over90 = rset1A.getDouble(1);
                                    price = over90*over60;
                                    price1 = price1 + over90*over60;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(price)),pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    // over90 = over90 + rset1A.getDouble(1);
                                }
                                
                                while (rset2B.next()){
                                    System.out.println("This osBalance " +rset2B.getString(1));
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2B.getString(1)),pFontHeader1);
                                    table.addCell(phrase);
                                    osBalance = rset2B.getDouble(1);
                                    
                                    
                                }
                                turnOver = osBalance-price;
                                
                                System.out.println("turnOver " +turnOver);
                                // turnOver1 = turnOver1 + turnOver;
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOver)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                turnOver1 = turnOver1 + (osBalance - price);
                                System.out.println("turnOver1 " +turnOver1);
                                if(price > 0){
                                    markup = ((osBalance-price)/price)*100;
                                }else{
                                    markup = 1;
                                }
                                System.out.println("Markup " +markup);
                                if(price > 0){
                                    markup1 = markup1+((osBalance-price)/price)*100;
                                }else{
                                    markup1 = 1;
                                }
                                System.out.println("markup1 " +markup1);
                                //contribution = (turnOver/turnOver1)*100;
                                
                                //  contribution1 = contribution1+(turnOver/turnOver1)*100;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(markup)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(contribution)), pFontHeader);
                                
                                //   table.addCell(phrase);
                                
                                //   }
                                //   osBalance1 = osBalance1 + rset2B.getDouble(1);
                                osBalance1 = osBalance1 + osBalance;
                                System.out.println("osBalance1 " +osBalance1);
                                contribution = (osBalance/osBalance1)*100;
                                System.out.println("contribution " +contribution);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(contribution)), pFontHeader);
                                
                                
                                table.addCell(phrase);
                                
                                contribution1 = contribution1+(osBalance/osBalance1)*100;
                                System.out.println("contribution1 " +contribution1);
                            }
                            //  while (rsetTotals7.next()){
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(over601)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(price1)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOver1)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(markup1)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(contribution1)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            docPdf.add(table);
                            // }
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
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM hp_pharmacy where amount > 0 and main_service ilike 'pharmacy' order by description");
            
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}
            }
            System.out.println("desciption"+rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}





