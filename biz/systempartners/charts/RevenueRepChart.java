/*

//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package biz.systempartners.charts;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class RevenueRepChart implements java.lang.Runnable {
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    double dailyTotal = 0.00;
    double dailyCashTotal = 0.00;
    double schemeDailyTotal = 0.00;
    double monthRevTotal = 0.00;
    double monthCashTotal = 0.00;
    double monthTotal = 0.00;
    double cashAverage = 0.00;
    double schemeAverage = 0.00;
    double Average = 0.00;
    double dailySchemeTotal = 0.00;
    double dailyRevTotal = 0.00;
    double monthSchemeTotal = 0.00;
    double dailyCash = 0.00;
    double dailyScheme = 0.00;
    double monthCash = 0.00;
    double monthScheme = 0.00;
    double average = 0.00;
    double grandtotal = 0.00;
    double Revgrandtotal = 0.00;
    double dailyuncateg = 0.00;
    double dailyuncategTotal = 0.00;
    double monthuncateg = 0.00;
    double monthuncategTotal = 0.00;
    double uncategAverage = 0.00;
    String ks;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    org.jfree.dataDefaultPieDataset pieDataSet;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void RevenueRepChart(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        
        pieDataSet = new org.jfree.data.DefaultPieDataset();
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new GlTransactPdf().GlTransactPdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generateChart();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                java.lang.Thread.currentThread().sleep(50);
                
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
    
    
    public void generateChart() {
        java.sql.ResultSet rsetTotals1 = null;
        
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
            
            //  com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        
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
                        while(rset4.next())
                            date = rset4.getObject(1).toString();
                        
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName, pFontHeader),false);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Sales Report - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(11);
                        
                        int headerwidths[] = {50,20,20,20,20,20,20,20,20,20,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(11);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        java.util.Date endDate1 = null;
                        java.util.Date endDate11 = null;
                        java.text.DateFormat dateFormat = null;
                        try {
                            dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(8);
                            
                            phrase = new Phrase("Sales Breakdown:  Period : "  +dateFormat.format(endDate11)+" ------ "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        
                        //table.addCell(phrase);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Income on " +dateFormat.format(endDate1), pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Cummulative Income For The Period", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("Average per Day For The Period", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        
                        phrase = new Phrase("Description", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        phrase = new Phrase("Cash "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Invoices "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Total "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Cash "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Invoices "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Total "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Cash "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Invoices "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Total "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("%", pFontHeader);
                        
                        table.addCell(phrase);
                        //  table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.Statement st21 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.Statement st31 = connectDB.createStatement();
                            
                            java.sql.Statement stx = connectDB.createStatement();
                            
                            java.sql.Statement st2x = connectDB.createStatement();
                            
                            java.sql.Statement st21x = connectDB.createStatement();
                            
                            java.sql.Statement st3x = connectDB.createStatement();
                            
                            java.sql.Statement st3xx = connectDB.createStatement();
                            
                            java.sql.Statement st31x = connectDB.createStatement();
                            
                            java.sql.Statement st31z = connectDB.createStatement();
                            
                            java.sql.Statement sty = connectDB.createStatement();
                            
                            java.sql.Statement st2xsss = connectDB.createStatement();
                            
                            java.sql.Statement st21to = connectDB.createStatement();
                            
                            java.sql.Statement stf = connectDB.createStatement();
                            
                            java.sql.Statement stfu = connectDB.createStatement();
                            
                            java.sql.Statement stfut = connectDB.createStatement();
                            
                            java.sql.ResultSet rsetf = stf.executeQuery("SELECT SUM(credit-debit) as amount  from ac_ledger ac,pb_activity pb where ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ac.activity_code = pb.code AND pb.activity_category ILIKE 'I%'");
                            
                            while (rsetf.next()){
                                Revgrandtotal = rsetf.getDouble(1);
                                System.out.println(Revgrandtotal);
                            }
                            
                            
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double dailyRev = 0.00;
                                double monthRev = 0.00;
                                double startDay = 0.00;
                                double lastDay = 0.00;
                                double Days = 0.00;
                                
                                int dailyReve = 0;
                                int dailyCashe = 0;
                                int dailySchemee = 0;
                                int monthReve = 0;
                                int monthCashe = 0;
                                int monthSchemee = 0;
                                int appearances = 0;
                                
                                System.out.println("Statements Created ...");
                                // java.sql.ResultSet rset = st.executeQuery("select description,SUM(credit) from ac_ledger where date BETWEEN '"+beginDate+"' AND '"+endDate+"' and  transaction_type = 'Revenue' GROUP BY description order by description ");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                System.out.println("Select A done ...");
                                java.sql.ResultSet rset3xx = st3xx.executeQuery("SELECT count(activity_code) from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                
                                while (rset3xx.next()){
                                    appearances = rset3xx.getInt(1);
                                    
                                }
                                
                                if (appearances > 0){
                                    java.sql.ResultSet rset = st2.executeQuery("SELECT SUM(credit-debit) as amount from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date = '"+endDate+"' and (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD')");
                                    
                                    java.sql.ResultSet rset1 = st21.executeQuery("SELECT SUM(credit-debit) as amount  from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date = '"+endDate+"' and gl_code ilike 'Scheme%'");
                                    
                                    java.sql.ResultSet rsetu = stfu.executeQuery("SELECT SUM(credit-debit) as amount from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date = '"+endDate+"' and gl_code not ilike 'CASH' AND gl_code not ilike 'CHEQUE' AND gl_code not ilike 'CREDIT CARD' AND gl_code not ilike 'scheme'");
                                    
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT SUM(credit-debit) as amount  from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD')");
                                    
                                    java.sql.ResultSet rset31 = st31.executeQuery("SELECT SUM(credit-debit) as amount  from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and gl_code ilike 'Scheme%'");
                                    
                                    java.sql.ResultSet rsetu1 = stfut.executeQuery("SELECT SUM(credit-debit) as amount from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND gl_code not ilike 'CASH' AND gl_code not ilike 'CHEQUE' AND gl_code not ilike 'CREDIT CARD' AND gl_code not ilike 'scheme'");
                                    
                                    java.sql.ResultSet rsetx = st2x.executeQuery("SELECT count(activity_code)  from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date = '"+endDate+"' and (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD')");
                                    
                                    java.sql.ResultSet rset1x = st21x.executeQuery("SELECT count(activity_code) from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date = '"+endDate+"' and gl_code ilike 'Scheme%' GROUP BY activity_code");
                                    
                                    java.sql.ResultSet rsetxsss = st2xsss.executeQuery("SELECT initcap(activity) from pb_activity where code ILIKE '"+listofAct[i].toString()+"'");
                                    
                                    java.sql.ResultSet rset3x = st3x.executeQuery("SELECT count(activity_code) from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'and (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD')  GROUP BY activity_code");
                                    
                                    java.sql.ResultSet rset31x = st31x.executeQuery("SELECT count(activity_code) from ac_ledger where activity_code ILIKE '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and gl_code ilike 'Scheme%' GROUP BY activity_code");
                                    
                                    java.sql.ResultSet rsetz = st31z.executeQuery("select EXTRACT(day from date '"+beginDate+"')");
                                    
                                    java.sql.ResultSet rsety = sty.executeQuery("select EXTRACT(day from date '"+endDate+"')");
                                    
                                    // java.sql.ResultSet rset1ww = st21to.executeQuery("SELECT SUM(credit-debit) as amount  from ac_ledger where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (gl_code ilike 'Scheme%' OR gl_code ilike 'Cash%')");
                                    java.sql.ResultSet rset1ww = st21to.executeQuery("SELECT SUM(credit-debit) as amount  from ac_ledger where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ILIKE '"+listofAct[i].toString()+"'");
                                    
                                    while (rset1ww.next()){
                                        grandtotal = rset1ww.getDouble(1);
                                        
                                    }
                                    //   table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    while (rsetxsss.next()){
                                        phrase = new Phrase(rsetxsss.getString(1),pFontHeader);
                                        table.addCell(phrase);
                                        
                                    }
                                    // phrase = new Phrase(listofAct[i].toString(),pFontHeader);
                                    // table.addCell(phrase);
                                    while (rsetz.next()){
                                        startDay = rsetz.getDouble(1);
                                        
                                    }
                                    
                                    while (rsety.next()){
                                        lastDay = rsety.getDouble(1);
                                        
                                    }
                                    System.out.println("This are the days " +startDay);
                                    System.out.println("This are the days " +lastDay);
                                    Days = lastDay - startDay+1;
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    while (rsetx.next()){
                                        dailyCashe = rsetx.getInt(1);
                                    }
                                    if (dailyCashe > 0){
                                        while (rset.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontNum);
                                            table.addCell(phrase);
                                            dailyCash  =  rset.getDouble(1);
                                            dailyCashTotal = dailyCashTotal+ dailyCash;
                                        }
                                    }else{
                                        dailyCash = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                        table.addCell(phrase);
                                        dailyCashTotal = dailyCashTotal+ dailyCash;
                                    }
                                    
                                    
                                    
                                    while (rset1x.next()){
                                        dailySchemee = rset1x.getInt(1);
                                    }
                                    if (dailySchemee > 0){
                                        while (rset1.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontNum);
                                            table.addCell(phrase);
                                            dailyScheme = rset1.getDouble(1);
                                            dailySchemeTotal = dailySchemeTotal + dailyScheme;
                                            
                                        }
                                    }else{
                                        dailyScheme = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(dailyScheme)),pFontNum);
                                        table.addCell(phrase);
                                        dailySchemeTotal = dailySchemeTotal + dailyScheme;
                                        
                                    }
                                    
                                    while (rsetu.next()) {
                                        dailyuncateg = rsetu.getDouble(1);
                                        dailyuncategTotal = dailyuncategTotal + dailyuncateg;
                                        
                                    }
                                    
                                    dailyRev = dailyCash+dailyScheme+dailyuncateg;
                                    //    dailyRev = dailyCash+dailyScheme;
                                    dailyRevTotal = dailyRevTotal+dailyRev;
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(dailyRev)),pFontNum);
                                    table.addCell(phrase);
                                    
                                    
                                    while (rset3x.next()){
                                        monthCashe = rset3x.getInt(1);
                                    }
                                    if (monthCashe > 0){
                                        while (rset3.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(1)),pFontNum);
                                            table.addCell(phrase);
                                            monthCash  = rset3.getDouble(1);
                                        }
                                    } else{
                                        monthCash = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                        table.addCell(phrase);
                                        // monthCashTotal = monthCashTotal + monthCash;
                                    }
                                    
                                    monthCashTotal = monthCashTotal + monthCash;
                                    
                                    
                                    while (rset31x.next()){
                                        monthSchemee = rset31x.getInt(1);
                                    }
                                    if (monthSchemee > 0){
                                        while (rset31.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset31.getString(1)),pFontNum);
                                            table.addCell(phrase);
                                            monthScheme = rset31.getDouble(1);
                                            // monthSchemeTotal = monthSchemeTotal + monthScheme;
                                            // monthRev = monthCash+monthScheme;
                                            // monthRevTotal = monthRevTotal + monthRev;
                                        }
                                    } else {
                                        monthScheme = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                        table.addCell(phrase);
                                        
                                    }
                                    
                                    monthSchemeTotal = monthSchemeTotal + monthScheme;
                                    
                                    while (rsetu1.next()) {
                                        
                                        monthuncateg = rsetu1.getDouble(1);
                                        // monthSchemeTotal = monthSchemeTotal + monthScheme;
                                        // monthRev = monthCash+monthScheme;
                                        // monthRevTotal = monthRevTotal + monthRev;
                                    }
                                    
                                    monthuncategTotal = monthuncategTotal + monthuncateg;
                                    monthRev = monthCash+monthScheme+monthuncateg;
                                    monthRevTotal = monthRevTotal + monthRev;
                                    
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(monthRev)),pFontNum);
                                    table.addCell(phrase);
                                    //Averages
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(monthCash/Days)),pFontNum);
                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                    
                                    table.addCell(phrase);
                                    uncategAverage = uncategAverage + monthuncateg/Days;
                                    cashAverage = cashAverage + monthCash/Days;
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(monthScheme/Days)),pFontNum);
                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                    
                                    table.addCell(phrase);
                                    schemeAverage = schemeAverage + monthScheme/Days;
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((monthCash/Days)+(monthScheme/Days)+(monthuncateg/Days))),pFontNum);
                                    /// phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                    
                                    table.addCell(phrase);
                                    Average = Average + (monthCash/Days)+(monthScheme/Days) + (monthuncateg/Days);
                                    //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((((monthCash/Days)+(monthScheme/Days))/(grandtotal/Days))*100)),pFontNum);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((monthRev/Revgrandtotal)*100)),pFontNum);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                
                            }
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(dailyCashTotal)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(dailySchemeTotal)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(dailyCashTotal+dailySchemeTotal+dailyuncategTotal)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(monthCashTotal)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(monthSchemeTotal)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(monthCashTotal+monthSchemeTotal+monthuncategTotal)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cashAverage)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(schemeAverage)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Average)),pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(((cashAverage+schemeAverage+uncategAverage)/Average)*100)),pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            Average = cashAverage + schemeAverage;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((cashAverage/Average)*100))+"%",pFontHeader);
                            table.addCell(phrase);
                            //pieDataSet.
                            org.jfree.data.DefaultValueDataset categoryDataSet = new org.jfree.data.DefaultValueDataset();
                          //  categoryDataSet.
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((schemeAverage/Average)*100))+"%",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(((cashAverage/Average)*100)+(schemeAverage/Average)*100))+"%",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(" ",pFontHeader);
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
                    
                    // wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    wait_for_Pdf2Show = rt.exec("xpdf "+tempFile+"");
                    
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
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = stmt1.executeQuery("select DISTINCT code,activity from pb_activity WHERE activity_category ilike 'I%' ORDER BY code");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        return listofActivities;
        
    }
    
}

*/


