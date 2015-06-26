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


public class CorporateTaxPdf implements java.lang.Runnable {
    
    
    public static java.sql.Connection connectDB = null;
    
    double inv = 0.00;
    double wta = 0.00;
    double equity = 0.00;
    double Total = 0.00;
    
    double pl = 0.00;
    double disall = 0.00;
    double taxableIncome = 0.00;
    double taxable = 0.00;
    double loss = 0.00;
    double taxDue = 0.00;
    double taxRate = 0.00;
    
    int taxbf = 0;
    String ks;
    java.util.Date beginDate = null;
    
    java.lang.String dateLastyear = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void CorporateTaxPdf(java.sql.Connection connDb, java.util.Date begindate,java.util.Date endate) {
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //	new BalanceSheetPdf().BalanceSheetPdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                java.lang.Thread.currentThread().sleep(20);
                
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
        
        java.util.Calendar calLast = java.util.Calendar.getInstance();
        
        calLast.roll(java.util.Calendar.YEAR, -1);
        
        dateLastyear = calLast.getTime().toString();
        
        System.out.println(this.dateLastyear);
        
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
                        
                        //    java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
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
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("CORPORATION TAX - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {40,40,20,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        Phrase phrase = new Phrase("");
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(2);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            
                            phrase = new Phrase("Tax Computation : ".toUpperCase() +dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Printed on : " +date, pFontHeader1);
                        
                        table.addCell(phrase);
                         
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            //       java.lang.Object[] listofAct = this.getListofActivities();
                            
                            java.lang.Object[] listofAct1 = this.getListofActivities1();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            //                            System.out.println(listofAct.length);
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.PreparedStatement pstmt31w = connectDB.prepareStatement("delete from ac_corporation_tax WHERE approved = false");
                            pstmt31w.executeUpdate();
     
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.ResultSet rset13 = st13.executeQuery(" select sum(amount) from pl_account_view");
                            
                            
                            //rsetTotals1 = st3.executeQuery("SELECT SUM(debit),SUM(credit) from");
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.addCell("  ");
                            
                          
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader);
                            phrase = new Phrase("NET INCOME", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            //   }
                            
                            while (rset13.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset13.getString(1)), pFontHeader1);
                                
                                table.addCell(phrase);
                                pl = rset13.getDouble(1);
                                java.sql.PreparedStatement pstmtC = connectDB.prepareStatement("insert into ac_corporation_tax values(?, ?, ?, ?, ?,?)");
                                
                                pstmtC.setObject(1,""+endDate+"");
                                pstmtC.setObject(2,"NET INCOME");
                                pstmtC.setObject(3,"Income");
                                if(pl<0){
                                    pstmtC.setDouble(5,pl*-1);
                                }else{
                                    pstmtC.setDouble(5,pl);
                                }
                                pstmtC.setDouble(4,0.00);
                                pstmtC.setDouble(6,0.00);
                                
                                pstmtC.executeUpdate();
                            }
                            
                            
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.addCell("  ");
                            
                             
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Add/Less: Disallowable Items ".toUpperCase(), pFontHeader1);
                            table.addCell(phrase);
                            //table.addCell("Add/Less: Disallowable Items ".toUpperCase(), pFontHeader1);
                            for (int i = 0; i < listofAct1.length; i++) {
                                
                                java.sql.Statement st6 = connectDB.createStatement();
                                
                                java.sql.Statement st7 = connectDB.createStatement();
                                // java.sql.Statement st8 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset7 = st7.executeQuery("select description from ac_corporate_disallowables where gl_code = '"+listofAct1[i].toString()+"'");
                                
                                java.sql.Statement st8 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset8 = st8.executeQuery("select SUM(debit-credit) from ac_ledger where activity_code = '"+listofAct1[i].toString()+"' and date between '"+beginDate+"' AND '"+endDate+"'");
                                //  java.sql.ResultSet rset8 = st8.executeQuery("select SUM(debit-credit) from ac_ledger where activity_code = '"+listofAct1[i].toString()+"' and date between '"+beginDate+"' AND '"+endDate+"'");
                                
                                //rsetTotals1 = st3.executeQuery("SELECT SUM(debit),SUM(credit) from");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.addCell("  ");
                                
                                 while (rset7.next()) {
                                    while (rset8.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset7.getObject(1).toString().toUpperCase(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(1)), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        equity = equity + rset8.getDouble(1);
                                        
                                        java.sql.PreparedStatement pstmt111 = connectDB.prepareStatement("insert into ac_corporation_tax values(?, ?, ?, ?, ?,?)");
                                        
                                        pstmt111.setObject(1,""+endDate+"");
                                        pstmt111.setObject(2,rset7.getObject(1).toString());
                                        pstmt111.setObject(3,"Disallowable");
                                        pstmt111.setDouble(4,rset8.getDouble(1));
                                        pstmt111.setDouble(5,0.00);
                                        pstmt111.setDouble(6,0.00);
                                        
                                        pstmt111.executeUpdate();
                                    }
                                    
                                }
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(equity)), pFontHeader);
                            
                            table.addCell(phrase); 
                            }
                           
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(4);
                            // table.addCell("Add/Less: Allowable Items ");
                            phrase = new Phrase("Add/Less: Allowable Items ".toUpperCase(), pFontHeader1);
                            
                            table.addCell(phrase);
                            // for (int i = 0; i < listofAct1.length; i++) {
                            
                            java.sql.Statement st6 = connectDB.createStatement();
                            
                            java.sql.Statement st7 = connectDB.createStatement();
                            
                            java.sql.Statement st83 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset83 = st83.executeQuery("select sum(investment_allow) from ac_asset_allocation");
                            
                            //rsetTotals1 = st3.executeQuery("SELECT SUM(debit),SUM(credit) from");
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            
                            //   table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            
                            while (rset83.next()) {
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ".toUpperCase(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Investment Allowance".toUpperCase(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset83.getString(1)), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                inv = inv + rset83.getDouble(1);
                                
                                phrase = new Phrase("    ".toUpperCase(), pFontHeader);
                                
                                table.addCell(phrase);
                                java.sql.PreparedStatement pstmt1A = connectDB.prepareStatement("insert into ac_corporation_tax values(?, ?, ?, ?, ?,?)");
                                
                                pstmt1A.setObject(1,""+endDate+"");
                                pstmt1A.setObject(2,"Investment Allowance");
                                pstmt1A.setObject(3,"Allowable");
                                pstmt1A.setDouble(5,inv);
                                pstmt1A.setDouble(4,0.00);
                                pstmt1A.setDouble(6,0.00);
                                
                                pstmt1A.executeUpdate();
                                
                            }
                            
                            inv = inv;
                            java.lang.Object[] listofAct2 = this.getListofActivities2();
                            for (int y = 0; y < listofAct2.length; y++) {
                                
                                java.sql.Statement st81 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset81 = st81.executeQuery("select sum(wear_tear_allow*depreciation_rate/100) from ac_asset_allocation where gl_code = '"+listofAct2[y].toString()+"'");
                                while (rset81.next()) {
                                    
                                    
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    wta = wta = + rset81.getDouble(1);
                                    // table.addCell(phrase);
                                }
                                Total = Total+wta;
                            }
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ".toUpperCase(), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Wear & Tear Allowance".toUpperCase(), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Total)), pFontHeader);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(inv+Total)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            java.sql.PreparedStatement pstmtB1 = connectDB.prepareStatement("insert into ac_corporation_tax values(?, ?, ?, ?, ?,?)");
                            
                            pstmtB1.setObject(1,""+endDate+"");
                            pstmtB1.setObject(2,"Wear & Tear Allowance");
                            pstmtB1.setObject(3,"Allowable");
                            pstmtB1.setDouble(5,Total);
                            pstmtB1.setDouble(4,0.00);
                            pstmtB1.setDouble(6,0.00);
                            
                            pstmtB1.executeUpdate();
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("ADJUSTED TAXABLE INCOME".toUpperCase(), pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            if(pl<0){
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((inv+Total+pl)-(-equity))), pFontHeader);
                                
                                table.addCell(phrase);
                                taxableIncome = (inv+Total+pl)-(-equity);
                            }else{
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((inv+Total+pl)-equity)), pFontHeader);
                                
                                table.addCell(phrase);
                                taxableIncome = (inv+Total+pl)-(equity);
                            }
                            
                            java.sql.Statement st8111 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset8111 = st8111.executeQuery("select count(debit) from ac_corporation_tax where category Ilike 'Tax Loss BFWD'");
                            while (rset8111.next()) {
                                taxbf = rset8111.getInt(1);
                            }
                            
                            if (taxbf > 0){
                                java.sql.Statement st811 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset811 = st811.executeQuery("select sum(debit-tax_divided) as debit from ac_corporation_tax WHERE category Ilike 'Tax loss BFWD'");
                                while (rset811.next()) {
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("TAX LOSSES BFWD".toUpperCase(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset811.getString(1)), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    loss = rset811.getDouble(1);
                                }
                            }else{
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TAX LOSSES BFWD".toUpperCase(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                loss = 0.00;
                            }
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TAXABLE INCOME".toUpperCase(), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            taxable = taxableIncome - loss;
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(taxable)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            java.sql.Statement st8112 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset8112 = st8112.executeQuery(" select ps.rate from pb_statutory ps, pb_activity pa where ps.gl_code = pa.code and pa.activity_category ilike 'CTX'");
                            while (rset8112.next()) {
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TAX DUE".toUpperCase(), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8112.getString(1)), pFontHeader1);
                                taxRate = rset8112.getDouble(1);
                            }
                            
                            taxDue = taxable * taxRate/100;
                            
                            if(taxDue>0){
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(taxDue)), pFontHeader);
                                
                                table.addCell(phrase);
                            }else{
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader);
                                
                                table.addCell(phrase);
                            }
                            java.sql.PreparedStatement pstmt1 = connectDB.prepareStatement("insert into ac_corporation_tax values(?, ?, ?, ?, ?,?)");
                            
                            pstmt1.setObject(1,""+endDate+"");
                            if(taxDue < 0){
                                pstmt1.setObject(2,"Tax Loss BFWD");
                                pstmt1.setObject(3,"Tax Loss BFWD");
                                pstmt1.setDouble(4,taxable*-1);
                                pstmt1.setDouble(6,0.00);
                            }else{
                                pstmt1.setObject(2,"Tax For The Year");
                                pstmt1.setObject(3,"Tax Due");
                                pstmt1.setDouble(4,0.00);
                                pstmt1.setDouble(6,taxDue);
                            }
                            
                            pstmt1.setDouble(5,0.00);
                            pstmt1.executeUpdate();
                            
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
        
 /*       java.util.Vector listActVector = new java.util.Vector(1,1);
  
  
  
        try {
  
  
            java.sql.Statement stmt1 = connectDB.createStatement();
  
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ba' order by main_code");
  
            while (rSet1.next()) {
  
                listActVector.addElement(rSet1.getObject(1).toString());
  
            }
  
        }catch (java.sql.SQLException sqlExec) {
  
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
  
        }
  
        listofActivities = listActVector.toArray();
  */
        return listofActivities;
    }
    
    
    public java.lang.Object[] getListofActivities1() {
        
        java.lang.Object[] listofActivities1 = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        
        try {
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT gl_code FROM ac_corporate_disallowables order by gl_code");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities1 = listActVector.toArray();
        
        return listofActivities1;
    }
    
    public java.lang.Object[] getListofActivities2() {
        
        java.lang.Object[] listofActivities2 = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        
        try {
            
            //  java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/insurance","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT gl_code FROM ac_asset_allocation where cost > depreciation");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities2 = listActVector.toArray();
        
        System.out.println("Total list of activities = "+listofActivities2.length);
        
        return listofActivities2;
    }
    
}





