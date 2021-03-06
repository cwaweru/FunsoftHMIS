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


public class BalanceSheetMvntPdf implements java.lang.Runnable {
    
    
    public static java.sql.Connection connectDB = null;
    
    double assets = 0.00;
    double liabilities = 0.00;
    double equity = 0.00;
    double profitloss = 0.00;
    double totalLiabEquity = 0.00;
    double subTotal = 0.00;
    String ks;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    java.util.Date beginDate = null;
    
    java.lang.String dateLastyear = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void BalanceSheetMvntPdf(java.sql.Connection connDb, java.util.Date begindate,java.util.Date endate) {
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(2, endate);
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
        
        java.lang.Object[][] ageingDates = ageingSeries.getAgeingDateSeries();
        
        ageingDates = ageingSeries.getAgeingDateSeries();
        
        
        double columnTotals1[] = new double[ageingDates.length];
        double assets1[] = new double[ageingDates.length];
        
        double Liab[] = new double[ageingDates.length];
        double Equity[] = new double[ageingDates.length];
        
        double profitLoss[] = new double[ageingDates.length];
        double profitLossl[] = new double[ageingDates.length];
        
        java.util.Calendar calLast = java.util.Calendar.getInstance();
        
        calLast.roll(java.util.Calendar.YEAR, -1);
        
        dateLastyear = calLast.getTime().toString();
        
        System.out.println(this.dateLastyear);
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        int interval = 0;
        
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
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
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
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Balance Sheet - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {20,40,20,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        Phrase phrase = new Phrase("");
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(2);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            
                            
                            phrase = new Phrase("BALANCE SHEET AS AT : " +dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Printed on : " +date, pFontHeader1);
                        
                        table.addCell(phrase);
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        // table.addCell("");
                        table.getDefaultCell().setColspan(2);
                        
                        table.addCell("");
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        for (int x = 0; x < ageingDates.length; x++) {
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            //int days = 1;
                            try {
                                
                                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(ageingDates[x][0].toString().trim()), "MMM/yy");
                                
                                java.lang.String monthString = dateFormatter.getDateString();
                                
                                //int days = 1;
                                
                                phrase = new Phrase(monthString +" "+ks, pFontHeader);
                                
                                interval = x;
                                
                                table.addCell(phrase);
                                
                                
                            } catch(java.text.ParseException prs) {
                                prs.printStackTrace();
                            }
                            
                        }
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            //   double columnTotals[] = new double[ageingDates.length];
                            double diff = 0.00;
                            String suspens = null;
                            String activity = null;
                            String User1 = null;
                            String transNo = null;
                            
                            java.sql.Statement stc = connectDB.createStatement();
                            // java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st2c = connectDB.createStatement();
                            java.sql.Statement st2x = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            java.lang.Object[] listofAct1 = this.getListofActivities1();
                            java.lang.Object[] listofAct2 = this.getListofActivities2();
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                
                                double columnTotals[] = new double[ageingDates.length];
                                
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                java.sql.Statement st3 = connectDB.createStatement();
                                
                                java.sql.Statement stx = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3 = st3.executeQuery("select DISTINCT initcap(at.main_category) from pb_activity at where at.main_code = '"+listofAct[i].toString()+"'");
                                java.sql.ResultSet rset = st.executeQuery("select distinct ac.sub_code,initcap(ac.sub_category) from tb_itemized_summary bv,pb_activity ac where ac.main_code = '"+listofAct[i].toString()+"' and bv.date  <= '"+endDate+"' and bv.gl_code = ac.code and ac.category_class = 'ba' group by ac.sub_code,ac.sub_category order by ac.sub_code");
                                
                                
                                
                                //   java.sql.ResultSet rsetTotals = st2.executeQuery("SELECT SUM(bv.debit-bv.credit) from tb_itemized_summary bv,pb_activity ac where ac.main_code = '"+listofAct[i].toString()+"' AND bv.gl_code = ac.code and bv.date <= '"+ageingDates[t][0]+"' and ac.category_class = 'ba' group by ac.main_code");
                                
                                System.out.println("This stage has been passed");
                                //rsetTotals1 = st3.executeQuery("SELECT SUM(debit),SUM(credit) from");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    /*
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.addCell("  ");
                                     
                                     
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                     */
                                
                                
                                while (rset3.next()) {
                                    
                                    table.getDefaultCell().setColspan(4);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                
                                while (rset.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    double currTotal = 0.00;
                                    
                                    for (int t = 0; t < ageingDates.length; t++) {
                                        double actTotal = 0.00;
                                        double actTotal1 = 0.00;
                                        
                                        String codes = null;
                                        String subCat = null;
                                        java.sql.ResultSet rsetx = stx.executeQuery("select sum(bv.debit-bv.credit) from tb_itemized_summary bv where bv.gl_code ilike '"+rset.getObject(1).toString()+"%' and bv.date <= '"+ageingDates[t][1]+"'");
                                        while (rsetx.next()) {
                                            
                                            actTotal = rsetx.getDouble(1);
                                            //    columnTotals[t] = rsetx.getDouble(1);
                                        }
                                       /* for (int x = 0; x < ageingDates.length; x++) {
                                           phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[x])), pFontHeader1);
                                        
                                        }*/
                                        if(t == 1){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actTotal)), pFontHeader1);
                                            //  interval = t;
                                            table.addCell(phrase);
                                        }else{
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actTotal)), pFontHeader1);
                                            //  interval = t;
                                            table.addCell(phrase);
                                        }
                                        columnTotals[t] = columnTotals[t] + actTotal;
                                        
                                    }
                                    
                                    
                                }
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase("Sub Total", pFontHeader);
                                
                                table.addCell(phrase);
                                //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                for (int x = 0; x < ageingDates.length; x++) {
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[x])), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    assets1[x] = assets1[x] + columnTotals[x];
                                    
                                }
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // table.addCell("  ");
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Assets", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            for (int x = 0; x < ageingDates.length; x++) {
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(assets1[x])), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            for (int i = 0; i < listofAct1.length; i++) {
                                
                                double columnTotalsx[] = new double[ageingDates.length];
                                
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                java.sql.Statement st3 = connectDB.createStatement();
                                
                                java.sql.Statement stx = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3 = st3.executeQuery("select DISTINCT initcap(at.main_category) from pb_activity at where at.main_code = '"+listofAct1[i].toString()+"'");
                                java.sql.ResultSet rset = st.executeQuery("select distinct ac.sub_code,initcap(ac.sub_category) from tb_itemized_summary bv,pb_activity ac where ac.main_code = '"+listofAct1[i].toString()+"' and bv.date  <= '"+endDate+"' and bv.gl_code = ac.code and ac.category_class = 'bl' group by ac.sub_code,ac.sub_category order by ac.sub_code");
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                while (rset3.next()) {
                                    
                                    table.getDefaultCell().setColspan(4);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                
                                while (rset.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    double currTotal = 0.00;
                                    
                                    for (int t = 0; t < ageingDates.length; t++) {
                                        double actTotal = 0.00;
                                        double actTotal1 = 0.00;
                                        
                                        String codes = null;
                                        String subCat = null;
                                        java.sql.ResultSet rsetx = stx.executeQuery("select sum(bv.credit-bv.debit) from tb_itemized_summary bv where bv.gl_code ilike '"+rset.getObject(1).toString()+"%' and bv.date <= '"+ageingDates[t][1]+"'");
                                        while (rsetx.next()) {
                                            
                                            actTotal = rsetx.getDouble(1);
                                            //    columnTotals[t] = rsetx.getDouble(1);
                                        }
                                       /* for (int x = 0; x < ageingDates.length; x++) {
                                           phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[x])), pFontHeader1);
                                        
                                        }*/
                                        if(t == 1){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actTotal)), pFontHeader1);
                                            //  interval = t;
                                            table.addCell(phrase);
                                        }else{
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actTotal)), pFontHeader1);
                                            //  interval = t;
                                            table.addCell(phrase);
                                        }
                                        columnTotalsx[t] = columnTotalsx[t] + actTotal;
                                        
                                    }
                                    
                                    
                                }
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase("Sub Total", pFontHeader);
                                
                                table.addCell(phrase);
                                //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                for (int x = 0; x < ageingDates.length; x++) {
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotalsx[x])), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    Liab[x] = Liab[x] + columnTotalsx[x];
                                    
                                }
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // table.addCell("  ");
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Liabilities", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            for (int x = 0; x < ageingDates.length; x++) {
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Liab[x])), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            
                            for (int i = 0; i < listofAct2.length; i++) {
                                
                                double columnTotals2[] = new double[ageingDates.length];
                                
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                java.sql.Statement st3 = connectDB.createStatement();
                                
                                java.sql.Statement stx = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3 = st3.executeQuery("select DISTINCT initcap(at.main_category) from pb_activity at where at.main_code = '"+listofAct2[i].toString()+"'");
                                java.sql.ResultSet rset = st.executeQuery("select distinct ac.sub_code,initcap(ac.sub_category) from tb_itemized_summary bv,pb_activity ac where ac.main_code = '"+listofAct2[i].toString()+"' and bv.date  <= '"+endDate+"' and bv.gl_code = ac.code and ac.category_class = 'ble' group by ac.sub_code,ac.sub_category order by ac.sub_code");
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                while (rset3.next()) {
                                    
                                    table.getDefaultCell().setColspan(4);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                
                                while (rset.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    double currTotal = 0.00;
                                    
                                    for (int t = 0; t < ageingDates.length; t++) {
                                        double actTotal = 0.00;
                                        double actTotal1 = 0.00;
                                        
                                        String codes = null;
                                        String subCat = null;
                                        java.sql.ResultSet rsetx = stx.executeQuery("select sum(bv.credit-bv.debit) from tb_itemized_summary bv where bv.gl_code ilike '"+rset.getObject(1).toString()+"%' and bv.date <= '"+ageingDates[t][1]+"'");
                                        while (rsetx.next()) {
                                            
                                            actTotal = rsetx.getDouble(1);
                                            //    columnTotals[t] = rsetx.getDouble(1);
                                        }
                                       /* for (int x = 0; x < ageingDates.length; x++) {
                                           phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[x])), pFontHeader1);
                                        
                                        }*/
                                        if(t == 1){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actTotal)), pFontHeader1);
                                            //  interval = t;
                                            table.addCell(phrase);
                                        }else{
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actTotal)), pFontHeader1);
                                            //  interval = t;
                                            table.addCell(phrase);
                                        }
                                        columnTotals2[t] = columnTotals2[t] + actTotal;
                                        
                                    }
                                    
                                    
                                }
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase("Sub Total", pFontHeader);
                                
                                // table.addCell(phrase);
                                //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                for (int x = 0; x < ageingDates.length; x++) {
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals2[x])), pFontHeader);
                                    
                                    //    table.addCell(phrase);
                                    
                                    Equity[x] = Equity[x] + columnTotals2[x];
                                    
                                }
                            }
                            
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.ResultSet rset13 = st13.executeQuery("SELECT distinct initcap(description),sub_code from pb_sub_activities where identifier iLike 'pl'");
                            double columnTotals21[] = new double[ageingDates.length];
                            for (int f = 0; f < ageingDates.length; f++) {
                                double actTotal = 0.00;
                                double actTotal1 = 0.00;
                                java.sql.Statement st13m = connectDB.createStatement();
                                //  java.sql.ResultSet rset13m = st13m.executeQuery("SELECT SUM(bv.credit-bv.debit) FROM tb_itemized_summary bv, pb_activity ac where bv.gl_code = ac.code AND (ac.category_class ilike 'pli%' OR ac.category_class ilike 'ple%') and bv.date <= '"+ageingDates[f][1]+"'");
                                java.sql.ResultSet rset13m = st13m.executeQuery("SELECT SUM(bv.credit-bv.debit) FROM tb_itemized_summary bv, pb_activity ac where bv.gl_code = ac.code AND ac.category_class ilike 'pl%' and bv.date <= '"+ageingDates[f][1]+"'");
                                
                                while (rset13.next()){ //{
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase = new Phrase(rset13.getObject(2).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase = new Phrase(rset13.getObject(1).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    // table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(1);
                                while (rset13m.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    actTotal = rset13m.getDouble(1);
                                }
                                columnTotals21[f] = columnTotals21[f] + actTotal;
                            }
                            for (int x = 0; x < ageingDates.length; x++) {
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals21[x])), pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                profitLoss[x] = profitLoss[x] + columnTotals21[x];
                                
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // table.addCell("  ");
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Equity", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            for (int x = 0; x < ageingDates.length; x++) {
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Equity[x]+profitLoss[x])), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // table.addCell("  ");
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Liability+Equity", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            for (int x = 0; x < ageingDates.length; x++) {
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Equity[x]+profitLoss[x]+Liab[x])), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                            }
                           /* float num_a = 0;
                            float num_b = 0;
                            float num_c = 0;
                            float totals = 0;
                            int j = 0;
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.ResultSet rset13 = st13.executeQuery("SELECT distinct initcap(description),sub_code from pb_sub_activities where identifier iLike 'pl%' AND sub_code not ilike '200%'");
                            java.sql.Statement st13m = connectDB.createStatement();
                            java.sql.ResultSet rset13m = st13m.executeQuery("SELECT SUM(bv.credit-bv.debit) FROM tb_itemized_summary bv, pb_activity ac where bv.gl_code = ac.code AND (ac.category_class ilike 'pli%' OR ac.category_class ilike 'ple%') and bv.date <= '"+endDate+"'");
                            
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.ResultSet rset14= st14.executeQuery("select sum(bv.credit-bv.debit) FROM tb_itemized_summary bv, pb_activity ac where bv.gl_code = ac.code AND ac.category_class ilike 'ble' and bv.date <= '"+endDate+"'");
                            System.out.println("This stage has been passed 2");
                            for (int i = 0; i < listofAct2.length; i++) {
                            
                                java.sql.Statement st10 = connectDB.createStatement();
                            
                                java.sql.Statement st8 = connectDB.createStatement();
                            
                                java.sql.Statement st11 = connectDB.createStatement();
                            
                                //   java.sql.ResultSet rset10 = st10.executeQuery("select * from equity_view");
                            
                            
                                java.sql.ResultSet rset11 = st11.executeQuery("select DISTINCT initcap(ac.main_category) FROM balance_sheet_view bv,pb_activity ac where ac.main_code = '"+listofAct2[i].toString()+"' AND bv.code = ac.code AND ac.category_class ilike 'ble'");
                            
                                java.sql.Statement st15 = connectDB.createStatement();
                                java.sql.ResultSet rset15 = st15.executeQuery("select ac.sub_code,initcap(ac.sub_category),sum(bv.credit-bv.debit) from tb_itemized_summary bv,pb_activity ac where ac.main_code = '"+listofAct2[i].toString()+"' AND bv.date <= '"+endDate+"' AND bv.gl_code = ac.code and ac.category_class ilike 'ble' group by ac.sub_code,ac.sub_category order by ac.sub_code");
                            
                                System.out.println("This stage has been passed 3");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.addCell("  ");
                            
                            
                                while (rset11.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(3);
                            
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset11.getObject(1).toString(), pFontHeader);
                                    System.out.println( rset11.getObject(1).toString().toUpperCase());
                            
                                    table.addCell(phrase);
                            
                                }
                            
                                while (rset15.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                            
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset15.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset15.getObject(2).toString(), pFontHeader1);
                                    System.out.println(rset15.getObject(2).toString());
                                    table.addCell(phrase);
                            
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset15.getString(3)), pFontHeader1);
                            
                                    table.addCell(phrase);
                                    equity = (equity + rset15.getDouble(3));//+profitloss;
                            
                                }
                                //            }
                                while (rset13.next()){ //{
                            
                            
                                    table.getDefaultCell().setColspan(1);
                            
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                                    phrase = new Phrase(rset13.getObject(2).toString(), pFontHeader1);
                            
                                    table.addCell(phrase);
                            
                            
                                    table.getDefaultCell().setColspan(1);
                            
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                                    phrase = new Phrase(rset13.getObject(1).toString(), pFontHeader1);
                            
                                    table.addCell(phrase);
                            
                            
                                    // table.addCell(phrase);
                            
                                    table.getDefaultCell().setColspan(1);
                            
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    while (rset13m.next()){
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset13m.getString(1)), pFontHeader1);
                            
                                        table.addCell(phrase);
                                        profitloss = rset13m.getDouble(1);
                                        //   profitloss = profitloss + rset13.getDouble(3);
                            
                                        // }
                                    }
                                }
                            
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setBorder(Rectangle.TOP);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                // table.getDefaultCell().
                                phrase = new Phrase("Total Equity".toUpperCase(), pFontHeader);
                            
                                table.addCell(phrase);
                            
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            
                            
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(equity+profitloss)), pFontHeader);
                            
                                table.addCell(phrase);
                            
                            
                            
                            }
                            totalLiabEquity = equity + liabilities;
                            
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.addCell("  ");
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            
                            //    while (rset16.next()) {
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // table.getDefaultCell().
                            phrase = new Phrase("Total Liabilities + Equity".toUpperCase(), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            //   totalLiabEquity = equity + liabilities;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(equity+profitloss+liabilities)), pFontHeader);
                            
                            table.addCell(phrase);*/
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
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ba' order by main_code");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        
        return listofActivities;
    }
    
    
    public java.lang.Object[] getListofActivities1() {
        
        java.lang.Object[] listofActivities1 = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        
        try {
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'bl' order by main_code");
            
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ble' order by main_code");
            
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





