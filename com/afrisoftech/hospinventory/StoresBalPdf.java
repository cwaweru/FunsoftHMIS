//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospinventory;
import com.afrisoftech.lib.SQLDateFormat;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class StoresBalPdf implements java.lang.Runnable {
    
    java.lang.String CBox = null;
    int seq=0;
    java.lang.String endDate = null;
    java.lang.String beginDate = null;
    String ks;
    String Store = null;
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
    Boolean itemsAboveZeroo = false;
    
    
    //public void OrderedItemsPdf(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate) {
    public void StoresBalPdf(java.sql.Connection connDb,java.lang.String begindate, java.lang.String endate, java.lang.String cbox, java.lang.String stores, Boolean itemsAboveZero) {
        
        connectDB = connDb;
        
        CBox = cbox;
        
        endDate = endate;
        
        Store = stores;
        itemsAboveZeroo =itemsAboveZero;
        
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
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
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
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {5,8,25,8,8,10,12};
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(7);
                        
                        Phrase phrase = new Phrase(CBox + " Stock Balance as at: " +endDate, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        
                        
                        
                        phrase = new Phrase("S/No",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Code",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Item",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Strength",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Balance",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("BPrice",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Value "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        //    phrase = new Phrase("Country",pFontHeader);
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.GRAY);
                        
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double amount = 0.00;
                                double qty = 0.00;
                                
                                if(Store.equalsIgnoreCase("SubStore")){
                                    String condition = "";
                                    if(itemsAboveZeroo) condition = "HAVING sum(qty)::NUMERIC(15,0) > 0";
                                    //java.sql.ResultSet rset = st.executeQuery("SELECT sum(qty)::NUMERIC(15,0) AS BALANCE FROM stock_balance_qty where department = '"+CBox+"' AND item_code = '"+listofAct[i]+"' AND dates <= '"+endDate+"'");
                                    
                                    //java.sql.ResultSet rset = st.executeQuery("SELECT sum(qty)::NUMERIC(15,0) AS BALANCE FROM stock_balance_qty where department = '"+CBox+"' AND item_code = '"+listofAct[i]+"' AND dates <= '"+endDate+"'");
                                    java.sql.ResultSet rset = st.executeQuery("SELECT sum(qty)::NUMERIC(15,0) AS BALANCE FROM stock_balance_qty where department ilike '"+CBox+"%' AND upper(item_code) ilike '"+listofAct[i]+"%' AND dates::date <= '"+endDate+"'"+condition);
                                    //java.sql.ResultSet rset3 = st3.executeQuery("SELECT product_id,initcap(product),initcap(strength) FROM st_stock_prices WHERE department ilike '"+CBox+"' AND product_id = '"+listofAct[i]+"'");
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT product_id,initcap(product),initcap(strength) FROM st_stock_prices WHERE UPPER(department) = UPPER('"+CBox+"') AND product_id = '"+listofAct[i]+"'");
                                    //java.sql.ResultSet rset2 = st2.executeQuery("SELECT transfer_price FROM stockprices WHERE product_id =  '"+listofAct[i]+"' AND department = '"+CBox+"'");
                                    //java.sql.ResultSet rset2 = st2.executeQuery("SELECT transfer_price FROM stockprices WHERE product_id =  '"+listofAct[i]+"' AND department ilike '"+CBox+"'");
                                    java.sql.ResultSet rset2 = st2.executeQuery("SELECT DISTINCT transfer_price FROM stockprices WHERE product_id =  '"+listofAct[i]+"' AND department ilike '"+CBox+"'");
                                    
                                    while (rset3.next()) {
                                        while (rset.next()) {
                                            while (rset2.next()) {
                                                
                                                table.getDefaultCell().setColspan(1);
                                                seq = seq+1;
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(""+seq, pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(rset3.getObject(2).toString(), pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(rset3.getObject(3).toString(), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                                table.addCell(phrase);
                                                qty = rset.getDouble(1);
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
                                }else{
                                    double pkge = 0.00;
                                    String condition = "";
                                    if(itemsAboveZeroo) condition = "HAVING sum(qty)::NUMERIC(15,0) > 0";
                                    //java.sql.ResultSet rset = st.executeQuery("SELECT sum(qty)::NUMERIC(15,0) AS BALANCE FROM stock_balance_qty where department = '"+CBox+"' AND item_code = '"+listofAct[i]+"' AND dates <= '"+endDate+"'");
                                    java.sql.ResultSet rset = st.executeQuery("SELECT sum(qty)::NUMERIC(15,0) AS BALANCE FROM stock_balance_qty where initcap(department) = '"+CBox+"' AND  item_code = '"+listofAct[i]+"' AND dates::date <= '"+endDate+"' "+condition);//OKAY
                                    //java.sql.ResultSet rset3 = st3.executeQuery("SELECT item_code,initcap(description),initcap(strength) FROM st_stock_item WHERE department = '"+CBox+"' AND item_code = '"+listofAct[i]+"'"); --ORIGINAL QUERY BROUGHT DUPLICATES IN REPORT
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT item_code,initcap(description),initcap(strength) FROM st_stock_item WHERE initcap(department) = initcap('"+CBox+"') AND item_code = '"+listofAct[i]+"'");
                                    //java.sql.ResultSet rset2 = st2.executeQuery("SELECT buying_price,packaging FROM stockitem WHERE item_code =  '"+listofAct[i]+"' AND department = '"+CBox+"'"); --ORIGINAL QUERY BROUGHT DUPLICATES IN REPORT
                                    java.sql.ResultSet rset2 = st2.executeQuery("SELECT DISTINCT buying_price,packaging FROM stockitem WHERE item_code =  '"+listofAct[i]+"' AND initcap(department) = '"+CBox+"'");
                                    
                                  
                                    
                                    while (rset3.next()) {
                                        while (rset.next()) {
                                            while (rset2.next()) {

                                               // System.out.println(rset3.getObject(1).toString());
                                                //if(java.util.regex.Pattern.matches("[0-9]*",rset2.getString(2))){
                                                    pkge = rset2.getDouble(2);
                                                /*}else{
                                                    pkge = 1;
                                                }*/
                                                table.getDefaultCell().setColspan(1);
                                                seq = seq+1;
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(""+seq, pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(rset3.getObject(2).toString(), pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(rset3.getObject(3).toString(), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                                table.addCell(phrase);
                                                qty = rset.getDouble(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                if(pkge < 1){
                                                    pkge = 1;
                                                }else{
                                                    pkge = pkge;
                                                }
                                                amount = rset2.getDouble(1)/pkge;
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount)),pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount*qty)),pFontHeader1);
                                                osBalance = osBalance + amount*qty;
                                                table.addCell(phrase);
                                            }
                                        }
                                    }
                                }
                            }
                            
                            table.getDefaultCell().setColspan(7);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            //  while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(5);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)),pFontHeader);
                            
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
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item_code, description FROM stock_balance_qty  WHERE department = '"+CBox+"' AND dates <= '"+endDate+"' AND item_code IS NOT NULL order by 2");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT item_code,description FROM st_stock_item WHERE initcap(department) = '"+CBox+"' AND description NOT LIKE '%FEE' ORDER BY 2,1");
            
            if(!rSet1.next()){
                //javax.swing.JOptionPane.showMessageDialog(null, "Maina, I think we should run the query on st_stock_prices instead.");
                rSet1=null;
                listActVector=new java.util.Vector(1,1);
                
                rSet1=stmt1.executeQuery("SELECT DISTINCT product_id,product FROM st_stock_prices WHERE initcap(department) ilike '"+CBox+"' and product NOT LIKE '%FEE' ORDER BY 2,1");
                
                
            }
            
           
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                System.out.println("description>> "+rSet1.getObject(1).toString());
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
}





