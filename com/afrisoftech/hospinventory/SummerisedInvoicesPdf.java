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


public class SummerisedInvoicesPdf implements java.lang.Runnable {
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
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
    
    public void SummerisedInvoicesPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
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
                
                java.lang.Thread.currentThread().sleep(2000);
                
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
            //     docPdf.
            
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName,pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Orders - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {10,30,10,10,10,10,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(3);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(8);
                        
                        Phrase phrase = new Phrase("SUPPLIERS PURCHASE STATEMENTS: " +endDate, pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(8);
                        phrase = new Phrase("Printed on : " +date,pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        
                        phrase = new Phrase("Invoice Date",pFontHeader);
                        table.addCell(phrase);
                       /*
                        phrase = new Phrase("INV. NO",pFontHeader);
                        table.addCell(phrase);
                        */
                        phrase = new Phrase("Item Description",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Qty",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Unit Price(KSH)",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Gross Item Value(KSH)",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Returns/Discount Value(KSH)",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("Net Value(KSH)",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Running Total(KSH)",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        Object[] suppliers = this.getListofActivities();
                        double grossTotal = 0.00;
                        for (int j = 0; j < suppliers.length; j++){
                            double subTotal = 0.00;
                            table.getDefaultCell().setColspan(8);
                            
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Supplier : "+suppliers[j].toString().toUpperCase(),pFontHeader);
                            table.addCell(phrase);
                            try {
                                
                                Object[] invoices = this.getListofInvoices(suppliers[j].toString());
                                for(int k = 0; k < invoices.length; k++){
                                    java.sql.Statement st = connectDB.createStatement();
                                    //       java.sql.ResultSet rsetInvoices= st.executeQuery("SELECT SUM(debit - credit) from ac_accounts_payable WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                    
                                    java.sql.Statement st2 = connectDB.createStatement();
                                    java.sql.ResultSet rset = st.executeQuery("select date,supplier,invoice_no, (debit - credit) as credit,transaction_type from st_stock_cardex  WHERE supplier = '"+suppliers[j].toString()+"' and transaction_type   ilike 'Receiving' AND invoice_no = '"+invoices[k]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY date,supplier limit 1");
                                    
                                    //                               java.sql.ResultSet rset = st.executeQuery("select date,supplier,invoice_no,credit,transaction_type from st_stock_cardex  WHERE supplier = '"+suppliers[j].toString()+"' and transaction_type   not like 'payment' and transaction_type   not like 'Stock Returns' and credit > 0.00 AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY date,supplier");
                                    //   java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(debit - credit) from ac_accounts_payable WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                    
                                    
                                    
                                    while (rset.next()) {
                                        java.sql.PreparedStatement pstmtTotal = connectDB.prepareStatement("select sum(debit)::numeric(15,2) as invoice_total from st_stock_cardex  WHERE supplier = ?  and invoice_no = ?");
                                        pstmtTotal.setString(1,suppliers[j].toString());
                                        pstmtTotal.setString(2,rset.getString("invoice_no"));
                                        java.sql.ResultSet rseTotal = pstmtTotal.executeQuery();//transaction_type   ilike 'Receiving' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' group by ORDER BY date,supplier");
                                        
                                        table.getDefaultCell().setColspan(8);
                                        phrase = new Phrase("",pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getObject(1).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getObject(2).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("INVOICE NUMBER : "+rset.getObject(3).toString(), pFontHeader);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase("", pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        while(rseTotal.next()){
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rseTotal.getString("invoice_total"), pFontHeader);
                                            table.addCell(phrase);
                                            subTotal = subTotal + Double.parseDouble(rseTotal.getString("invoice_total"));
                                        }
                                        
                                        
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase("", pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase("", pFontHeader);
                                        table.addCell(phrase);
                                        
                                        
                                        
                                        
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(subTotal)),pFontHeader);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(8);
                                        phrase = new Phrase("",pFontHeader);
                                        table.addCell(phrase);
                                        java.sql.PreparedStatement pstmt = connectDB.prepareStatement("SELECT item, price_per_item," +
                                                " quantity_received, supplier, delivery_note_no, date, (price_per_item * quantity_received)::numeric(15,2) as gross_value, credit, " +
                                                "description, vat_amount, debit as net_value FROM st_stock_cardex" +
                                                " WHERE invoice_no = ? AND transaction_type ilike 'receiving' order by date");
                                        
                                        pstmt.setString(1, rset.getObject(3).toString());
                                        
                                        java.sql.ResultSet rsetDetails = pstmt.executeQuery();
                                        
                                        while(rsetDetails.next()){
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rsetDetails.getObject(6).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rsetDetails.getObject(1).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            //    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rsetDetails.getObject(3).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(rsetDetails.getObject("price_per_item").toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rsetDetails.getObject("gross_value").toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rsetDetails.getObject("credit").toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rsetDetails.getObject("net_value").toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase("", pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                        
                                    }
                                    
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                
                                //  while (rsetTotals.next()) {
                                
                                //      table.getDefaultCell().setColspan(4);
                                
                                grossTotal = grossTotal + subTotal;
                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" Sub Total for : "+suppliers[j], pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(subTotal)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(8);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                //  phrase = new Phrase((""), pFontHeader);
                                
                                //  table.addCell(phrase);
                                
                                // }
                                
                            } catch(java.sql.SQLException SqlExec) {
                                SqlExec.printStackTrace();
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            
                        }
                        table.getDefaultCell().setColspan(7);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Gross Total for all supplies", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(String.valueOf(grossTotal)), pFontHeader);
                        
                        table.addCell(phrase);
                        
                        docPdf.add(table);
                        docPdf.leftMargin();
                        
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT supplier FROM st_stock_cardex WHERE transaction_type ilike 'receiving' AND date between '"+beginDate+"' AND '"+endDate+"' order by supplier");
            //pSet1.setString(1,"Raise Invoice");
            //java.sql.ResultSet rSet1 = pSet1.executeQuery()
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                System.out.println("description"+rSet1.getObject(1).toString());
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    public java.lang.Object[] getListofInvoices(String supplierName) {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.PreparedStatement stmt1 = connectDB.prepareStatement("SELECT DISTINCT invoice_no  FROM st_stock_cardex WHERE supplier = ? AND date between '"+beginDate+"' AND '"+endDate+"' order by invoice_no");
            stmt1.setString(1, supplierName);
            java.sql.ResultSet rSet1 = stmt1.executeQuery();
            //pSet1.setString(1,"Raise Invoice");
            //java.sql.ResultSet rSet1 = pSet1.executeQuery()
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                System.out.println("description"+rSet1.getObject(1).toString());
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
}





