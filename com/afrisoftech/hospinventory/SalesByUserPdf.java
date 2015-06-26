//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospinventory;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class SalesByUserPdf implements java.lang.Runnable {
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.lang.String StoreName = null;
    
    double receivings = 0.00;
    
    double issuings = 0.00;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void SalesByUserPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate, java.lang.String store) {
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        StoreName = store;
        
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
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Sales By User - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {12,25,15,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(4);
                        
                        Phrase phrase = new Phrase("User Name: " +StoreName, pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            
                            phrase = new Phrase("Sales By "+StoreName.toUpperCase()+" Report :     Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                            
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
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("DATE",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("ITEM",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("Qty",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        
                        phrase = new Phrase("SALES",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //  phrase = new Phrase("AMOUNT",pFontHeader);
                        //  table.addCell(phrase);
                        
                        //   phrase = new Phrase("TOTAL KShs",pFontHeader);
                        //   table.addCell(phrase);
                        
                        //    phrase = new Phrase("PROFIT",pFontHeader);
                        //    table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double cash = 0.00;
                        double chq = 0.00;
                        double cash1 = 0.00;
                        double chq1 = 0.00;
                        double Card = 0.00;
                        try {
                            
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            //  java.sql.Statement pSet1 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            //    java.sql.ResultSet rsetTotals = st.executeQuery("select sum((ph.quantity * st.transfer_price)::numeric(10,2)), sum(amount)::numeric(10,2),sum(amount - ph.quantity * st.transfer_price)::numeric(10,2) from hp_pharmacy ph,st_stock_prices st WHERE st.department = 'Pharmacy' AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description = st.product");
                            
                            
                            // for (int i = 0; i < listofAct.length; i++) {
                            
                            double opStock = 0.00;
                            double supplies = 0.00;
                            double sales = 0.00;
                            double closing = 0.00;
                            double amount = 0.00;
                            int n = 0;
                            int m = 0;
                            int k = 0;
                            //  System.out.println("item" +listofAct[i]);
                              /*  double opStock = 0.00;
                                double supplies = 0.00;
                                double sales = 0.00;
                                double closing = 0.00;
                               */
                            
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st31 = connectDB.createStatement();
                            java.sql.Statement st41 = connectDB.createStatement();
                            java.sql.Statement st32 = connectDB.createStatement();
                            java.sql.Statement st2e = connectDB.createStatement();
                            
                           // java.sql.ResultSet rset11 = st21.executeQuery("select upper(item),SUM(sub_store_issiuing),sum(total),trans_date::date from st_sub_stores WHERE trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND  user_name ILIKE '"+StoreName+"%' and sub_store_issiuing > 0 group by item,trans_date::date order by trans_date,item");
                            java.sql.ResultSet rset11 = st21.executeQuery("select upper(description),sum(quantity),sum(amount),date_prescribed from hp_pharmacy WHERE date_prescribed  BETWEEN '"+beginDate+"' AND '"+endDate+"' AND  user_name ILIKE '"+StoreName+"%' and main_service ilike 'phar%' group by description,date_prescribed order by date_prescribed");
                            //  java.sql.ResultSet rset1 = st2.executeQuery("select SUM(issuing) as receiving from st_sub_stores WHERE trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND item ILIKE '"+listofAct[i]+"%' AND sub_store ILIKE '"+StoreName+"%' group by item");
                            // java.sql.ResultSet rset1e = st2e.executeQuery("select SUM(receiving) as receiving from st_sub_stores WHERE trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND item ilike '"+listofAct[i]+"%' AND store_name ILIKE '"+StoreName+"%' group by item");
                            
                            // java.sql.ResultSet rset2 = st3.executeQuery("select SUM(balance) from st_stock_prices WHERE product = '"+listofAct[i]+"' AND department ILIKE '"+StoreName+"%'");
                            //  java.sql.ResultSet rset21 = st32.executeQuery("select SUM(amount) from stock_audit WHERE date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description = '"+listofAct[i]+"' group by description");
                            
                            //  java.sql.ResultSet rset13 = st41.executeQuery("select DISTINCT count(description) from stock_audit WHERE date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description = '"+listofAct[i]+"' group by description LIMIT 1");
                            //    java.sql.PreparedStatement st23 = connectDB.prepareStatement("select item,SUM(quantity_received - sub_store_receiving) from st_stock_cardex WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' item = '"+listofAct[i]+"' group by item");
                            
                            // java.sql.PreparedStatement st1 = connectDB.prepareStatement("select ph.description,sum(ph.quantity),avg(st.transfer_price)::numeric(10,2),sum((ph.quantity * st.transfer_price)::numeric(10,2)), avg(st.selling_price)::numeric(10,2),sum(amount)::numeric(10,2),sum(amount - ph.quantity * st.transfer_price)::numeric(10,2) from hp_pharmacy ph,st_stock_prices st WHERE st.department = 'Pharmacy' and ph.description = ? AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description = st.product GROUP BY ph.description");
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                            table.getDefaultCell().setColspan(1);
                            while (rset11.next()){
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset11.getObject(4), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(2)),pFontHeader1);
                                chq = chq + rset11.getDouble(2);
                                chq1 = chq1 + rset11.getDouble(2);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(3)),pFontHeader1);
                                cash = cash + rset11.getDouble(3);
                                cash1 = cash1 + rset11.getDouble(3);
                                table.addCell(phrase);
                                
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Net1)),pFontHeader);
                            
                            // table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(chq1)),pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cash1)),pFontHeader);
                            
                            table.addCell(phrase);
                            // phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
                            
                            // table.addCell(phrase);
                            
                                /*while (rset11.next()) {
                                 
                                   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                 
                                    table.addCell(phrase);
                                 
                                 
                                 
                                 
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    //    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontHeader1);
                                    //    table.addCell(phrase);
                                    issuings = rset1.getDouble(1);
                                 
                                 
                                 // while (rset1e.next()){
                                 
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    //    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontHeader1);
                                    //    table.addCell(phrase);
                                    receivings = rset1e.getDouble(1);
                                }
                                 
                                 supplies = receivings+issuings;
                                 
                                 */
                              /*  while (rset2.next()){
                               
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    //       phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(1)),pFontHeader1);
                                    //       table.addCell(phrase);
                                    closing = rset2.getDouble(1);
                                }
                               
                                opStock = (closing+sales)-supplies;
                                if (opStock > 0){
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(opStock)),pFontHeader1);
                                table.addCell(phrase);
                                }else{
                               
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf("0.00")),pFontHeader1);
                                table.addCell(phrase);
                                }
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(supplies)),pFontHeader1);
                                table.addCell(phrase);
                               
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(sales)),pFontHeader1);
                                table.addCell(phrase);
                               
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(closing)),pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                               
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            }
                       /*     while (rsetTotals.next()) {
                               
                                table.getDefaultCell().setColspan(3);
                               
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total", pFontHeader);
                               
                                table.addCell(phrase);
                               
                                table.getDefaultCell().setColspan(1);
                               
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                               
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                               
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                               
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)), pFontHeader);
                               
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(3)), pFontHeader);
                               
                                table.addCell(phrase);
                            }
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
            
            //   java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item FROM st_sub_stores where trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND sub_store ='"+StoreName+"' ORDER BY item");
            //pSet1.setString(1,"Raise Invoice");
            //java.sql.ResultSet rSet1 = pSet1.executeQuery()
            // while (rSet1.next()) {
            //if (rSet1.getFloat(1) > 0){
            //    listActVector.addElement(rSet1.getObject(1).toString());
            //}
            //}
            //                System.out.println("description"+rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}





