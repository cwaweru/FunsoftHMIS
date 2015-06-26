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


public class SalesReportPdf implements java.lang.Runnable {
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.lang.String CashPoint = null;
    
    java.lang.String gAccount = null;
    
    java.util.Date endDate = null;
    
    int counter = 0;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    String ks;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void SalesReportPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate,java.lang.String cashPoint, java.lang.String glAccount) {
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        CashPoint = cashPoint;
        
        gAccount = glAccount;
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"",pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Transaction List - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);
                        
                        int headerwidths[] = {5,25,10,10,14,10,14,14,8};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(9);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            
                            phrase = new Phrase("Sales [" +CashPoint+ "] Report :     Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
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
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("No",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("ITEM",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("UNIT",pFontHeader);
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("COUNT",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("UNIT COST",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("TOTAL COST",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("SELLING PRICE",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("TOTAL "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("PROFIT "+ks,pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("MARKUP%",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        double osBalance11 = 0.00;
                        double osBalance31 = 0.00;
                        
                        double current = 0.00;
                        double osBalance1 = 0.00;
                        
                        double osBalance3 = 0.00;
                        try {
                            java.lang.Object[] listofAct1 = this.getDates();
                            // for (int k = 0; k < listofAct1.length; k++) {
                            double osBalance21 = 0.00;
                            double osBalance22 = 0.00;
                            double osBalance23 = 0.00;
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //     phrase = new Phrase(listofAct1[k].toString().toUpperCase(), pFontHeader);
                            //  table.addCell(phrase);
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            //  java.sql.Statement pSet1 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double qtys = 0.00;
                                double osBalance2 = 0.00;
//AND st.transfer_price > 0
                                System.out.println("item" +listofAct[i]);
                                //  java.sql.Statement stbx = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                //java.sql.PreparedStatement st1 = connectDB.prepareStatement("select ph.description,sum(ph.quantity) as quantity,avg(st.transfer_price)::numeric(10,2) as bprice,sum((ph.quantity * st.transfer_price)::numeric(10,2)) as cost, CASE WHEN (sum(ph.quantity) > 0 ) THEN (sum(ph.amount)/sum(ph.quantity))::numeric(10,2) ELSE 1.00 END AS sprice,sum(amount)::numeric(10,2) as amt,sum(amount) - sum(ph.quantity * st.transfer_price)::numeric(10,2),CASE WHEN (sum(transfer_price) = 0) THEN 0.00 ELSE (avg(st.selling_price)/avg(st.transfer_price))::numeric(10,2) END AS markup from hp_pharmacy ph,st_stock_prices st WHERE ph.main_service ilike '"+CashPoint+"%' AND st.department ilike '"+CashPoint+"%' and ph.description = ? AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description ilike st.product GROUP BY ph.description");
                                java.sql.PreparedStatement st1 = connectDB.prepareStatement("select ph.description,sum(ph.quantity) as quantity,avg(st.transfer_price)::numeric(10,2) as bprice,sum((ph.quantity * st.transfer_price)::numeric(10,2)) as cost, CASE WHEN (sum(ph.quantity) > 0 ) THEN (sum(ph.amount)/sum(ph.quantity))::numeric(10,2) ELSE 1.00 END AS sprice,sum(amount)::numeric(10,2) as amt,sum(amount) - sum(ph.quantity * st.transfer_price)::numeric(10,2),CASE WHEN (sum(transfer_price) = 0) THEN 0.00 ELSE (avg(st.selling_price)/avg(st.transfer_price))::numeric(10,2) END AS markup from hp_pharmacy ph,st_stock_prices st WHERE ph.main_service ilike '"+CashPoint+"%' AND st.department ilike '"+CashPoint+"%' and ph.description = ? AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description = st.product GROUP BY ph.description");
                                
                                // java.sql.PreparedStatement stbx = connectDB.prepareStatement("select sum(credit-debit) FROM ac_ledger WHERE activity_code ilike '"+gAccount+"%' and service_type ilike ? AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (transaction_type = 'Revenue' OR transaction_type = 'Refunds') GROUP BY service_type");
                                java.sql.PreparedStatement stbx = connectDB.prepareStatement("select sum(credit-debit) FROM ac_ledger WHERE activity_code = '"+gAccount+"' and service_type = ? AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' GROUP BY service_type");
                                st1.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset = st1.executeQuery();
                                stbx.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rsetbx = stbx.executeQuery();
                                while (rset.next()) {
                                    while (rsetbx.next()) {
                                        // table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        counter = counter + 1;
                                        phrase = new Phrase(""+counter, pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)),pFontHeader1);
                                        table.addCell(phrase);
                                        qtys = rset.getDouble(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)),pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(4)),pFontHeader1);
                                        osBalance1 = rset.getDouble(4);
                                        osBalance21 = osBalance21 + rset.getDouble(4);
                                        osBalance11 = rset.getDouble(4);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                       /* phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(6)),pFontHeader1);
                                        osBalance2 = osBalance2 + rset.getDouble(6);
                                        osBalance22 = osBalance22 + rset.getDouble(6);
                                        table.addCell(phrase);
                                        */
                                        
                                        //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetbx.getString(1)),pFontHeader1);
                                        osBalance2 =  rsetbx.getDouble(1);
                                        osBalance22 = osBalance22 + rsetbx.getDouble(1);
                                        if(qtys > 0){
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2/qtys)),pFontHeader1);
                                        table.addCell(phrase);
                                        }else{
                                         phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2/1)),pFontHeader1);
                                        table.addCell(phrase);   
                                        }
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2)),pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                       // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)),pFontHeader1);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2-osBalance1)),pFontHeader1);
                                        
                                       /* osBalance3 = osBalance3 + rset.getDouble(7);
                                        osBalance23 = osBalance23 + rset.getDouble(7);
                                        osBalance31 = rset.getDouble(7);
                                        */
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        if(osBalance1 > 0){
                                       // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(8)),pFontHeader1);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2/osBalance1)),pFontHeader1);
                                       
                                        table.addCell(phrase);
                                        }else{
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2/1)),pFontHeader1);
                                       
                                        table.addCell(phrase);    
                                        }
                                    }
                                }
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //  while (rsetTotals.next()) {
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(""+counter, pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance21)), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22)), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22-osBalance21)), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            //   phrase = new Phrase(" ", pFontHeader);
                            if(osBalance21 > 0){
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance22/osBalance21)), pFontHeader);
                                
                                table.addCell(phrase);
                            }else{
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader);
                                
                                table.addCell(phrase);
                            }
                            //  }
                         /*   table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                          
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                          
                            //  while (rsetTotals.next()) {
                            table.getDefaultCell().setColspan(2);
                          
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("  ", pFontHeader);
                          
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                          
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Sum Total", pFontHeader);
                          
                            table.addCell(phrase);
                          
                            table.getDefaultCell().setColspan(1);
                          
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                          
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader);
                          
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                          
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                          
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                          
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance2)), pFontHeader);
                          
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                          
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance3)), pFontHeader);
                          
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                          
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                          
                            table.addCell(phrase);
                          */
                            
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
    
    public java.lang.Object[] getDates() {
        
        java.lang.Object[] listofDates = null;
        
        java.util.Vector listofDatesVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT category FROM st_stock_prices where department ilike '"+CashPoint+"' ORDER BY category");
            
            while (rSet1.next()) {
                
                listofDatesVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofDates = listofDatesVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofDates;
    }
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT activity_code FROM ac_cash_collection where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type not ilike 'Bank%' EXCEPT select code from pb_activity where activity ilike 'Pharmacy%' ORDER BY activity_code");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ph.service_type FROM ac_ledger ph where ph.date BETWEEN '"+beginDate+"' AND '"+endDate+"'  AND ph.activity_code = '"+gAccount+"' ORDER BY ph.service_type");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
 /*   public java.lang.Object[] getListofActivities() {
  
        java.lang.Object[] listofActivities = null;
  
        java.util.Vector listActVector = new java.util.Vector(1,1);
  
  
        try {
  
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
  
            java.sql.Statement stmt1 = connectDB.createStatement();
  
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM hp_pharmacy where amount > 0 AND date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by description");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM hp_pharmacy where date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by description");
  
  
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}
            }
            System.out.println("description"+rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
  
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
  
        }
  
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }*/
}





