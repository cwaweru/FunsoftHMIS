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


public class SubStoresBaMainlBelowZeroPdf_1 implements java.lang.Runnable {
    double receivings = 0.00;
    
    double issuings = 0.00;
    int seq = 0;
    java.lang.String CBox = null;
    
    java.util.Date endDate = null;
    java.util.Date beginDate = null;
    String ks;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    
    //public void OrderedItemsPdf(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate) {
    // public void StoresBalPdf_1(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate,java.lang.String cbox) {
    public void SubStoresBaMainlBelowZeroPdf_1(java.sql.Connection connDb, java.util.Date endate, java.util.Date begindate,java.lang.String cbox) {
        
        connectDB = connDb;
        CBox = cbox;
        endDate = endate;
        beginDate = begindate;
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
                    
                    
                    
                    
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset23 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset23.next()){
                            compName = rset23.getObject(1).toString();
                            ks = rset23.getString(2);
                        }
                        while(rset4.next())
                            date = rset4.getObject(1).toString();
                        
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"                                                        Printed On: "+date+"",pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(7);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Stock Balance - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    double osBalance = 0.00;
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);
                        
                        int headerwidths[] = {6,35,15,10,17};
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(5);
                        
                        Phrase phrase = new Phrase(CBox + " STOCK BALANCE (ABOVE ZERO) as at: " +endDate, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        
                        phrase = new Phrase("SNo.",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Item",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Stock Balance",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Buying Price",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Value "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        //    phrase = new Phrase("Country",pFontHeader);
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            //  java.sql.Statement pSet1 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            //    java.sql.ResultSet rsetTotals = st.executeQuery("select sum((ph.quantity * st.transfer_price)::numeric(10,2)), sum(amount)::numeric(10,2),sum(amount - ph.quantity * st.transfer_price)::numeric(10,2) from hp_pharmacy ph,st_stock_prices st WHERE st.department = 'Pharmacy' AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description = st.product");
                            
                            double amount = 0.00;
                            for (int i = 0; i < listofAct.length; i++) {
                                
                                double opStock = 0.00;
                                double supplies = 0.00;
                                double sales = 0.00;
                                double closing = 0.00;
                                double price = 0.00;
                                double qty = 0.00;
                                int n = 0;
                                int m = 0;
                                int k = 0;
                                System.out.println("item [" +listofAct[i]+" ]");
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st41 = connectDB.createStatement();
                                java.sql.Statement st32 = connectDB.createStatement();
                                java.sql.Statement st2e = connectDB.createStatement();
                                
                                
                                // java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(receiving-issuing) from stock_movement_summary WHERE item = ? AND store_name ilike  '"+CBox+"' AND trans_date <= '"+endDate+"'");
                                
                                java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(qty)::NUMERIC(10,0) AS BALANCE from stock_balance_qty where department ilike '"+CBox+"' and description ilike ? AND dates <= '"+endDate+"'  GROUP BY description having sum(qty) > 0");
                                java.sql.PreparedStatement pset = connectDB.prepareStatement("select distinct transfer_price from st_stock_prices WHERE product ilike ? and department ilike  '"+CBox+"'");
                                
                                pset1.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset3 = pset1.executeQuery();
                                
                                pset.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset2 = pset.executeQuery();
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                // phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                table.getDefaultCell().setColspan(1);
                                seq=seq+1;
                                phrase = new Phrase(""+seq+".", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase(listofAct[i].toString(), pFontHeader1);
                                
                                table.addCell(phrase);
                              /* while (rset2.next()){
                               
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    opStock = rset2.getDouble(1);
                                }
                               */
                                while (rset2.next()){
                                    
                                    price = rset2.getDouble(1);
                                    // System.out.println("item Price[" +listofAct[i]+" ] = "+price);
                                }
                                while (rset3.next()){
                                    
                                    qty = rset3.getDouble(1);
                                    // System.out.println("item Qty [" +listofAct[i]+" ] = "+qty);
                                }
                              /* while (rset.next()) {
                               
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)),pFontHeader1);
                                    //  table.addCell(phrase);
                                    sales = rset.getDouble(1);
                                    receivings = rset.getDouble(2);
                                }
                               */
                                
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                       /*
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(opStock)),pFontHeader1);
                                table.addCell(phrase);
                        */
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(qty)),pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(price)),pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(qty*price)),pFontHeader1);
                                table.addCell(phrase);
                                
                                amount = amount + (qty*price);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            }
                            
                            
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount)),pFontHeader);
                            
                            table.addCell(phrase);
                            docPdf.add(table);
                            
                            /*
                            java.sql.Statement st = connectDB.createStatement();
                             
                            java.sql.Statement st2 = connectDB.createStatement();
                             
                            java.lang.Object[] listofAct = this.getListofActivities();
                            for (int i = 0; i < listofAct.length; i++) {
                                 double amount = 0.00;
                                double qty = 0.00;
                                java.sql.ResultSet rset = st.executeQuery("select item,sum(receiving-issuing)::NUMERIC(10,0) AS BALANCE from stock_movement_summary where store_name ilike '"+CBox+"' AND item ILIKE  '"+listofAct[i]+"' AND trans_date <= '"+endDate+"' group by item");// WHERE transaction_no NOTNULL AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY transaction_no ASC");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                java.sql.ResultSet rset2 = st2.executeQuery("select TRANSFER_price from st_stock_PRICES WHERE PRODUCT ILIKE  '"+listofAct[i]+"'");
                             
                             
                             
                             
                                while (rset.next()) {
                                     while (rset2.next()) {
                                    table.getDefaultCell().setColspan(1);
                             
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                             
                                    table.addCell(phrase);
                                    qty = rset.getDouble(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(1)),pFontHeader1);
                                    amount = rset2.getDouble(1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount*qty)),pFontHeader1);
                                    osBalance = osBalance + amount*qty;
                                    table.addCell(phrase);
                                     }
                                }
                            }
                             
                            table.getDefaultCell().setColspan(4);
                             
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);
                             
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                             
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                             
                             
                            //  while (rsetTotals.next()) {
                             
                            table.getDefaultCell().setColspan(2);
                             
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                             
                            table.addCell(phrase);
                             
                            table.getDefaultCell().setColspan(2);
                             
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                             
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)),pFontHeader);
                             
                            table.addCell(phrase);
                             
                             
                            docPdf.add(table);
                             */
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
            
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item FROM stock_movement_summary  WHERE store_name ilike '"+CBox+"' AND trans_date <= '"+endDate+"' order by item");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("select initcap(description) as description, sum(qty) AS BALANCE from stock_balance_qty where department ilike '"+CBox+"' and dates <= '"+endDate+"' GROUP BY description having sum(qty) > 0 order by description");
            
            //pSet1.setString(1,"Raise Invoice");
            //java.sql.ResultSet rSet1 = pSet1.executeQuery()
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
    }
    
}





