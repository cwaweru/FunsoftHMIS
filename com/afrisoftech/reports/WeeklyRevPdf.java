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


public class WeeklyRevPdf implements java.lang.Runnable {
    double Credit = 0.00;
    String ks;
    int over = 0;
    int name = 0;
    
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String Debtor = null;
    java.util.Date endDate = null;
    java.util.Date beginDate = null;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    com.afrisoftech.timeseries.WeeklyAgeing ageingSeries= null;
    java.util.Date ageingDates[][] = null;
    
    double osBalance = 0.00;
    double current = 0.00;
    double over30 = 0.00;
    double over60 = 0.00;
    double over90 = 0.00;
    double turnOver = 0.00;
    double turnOver1 = 0.00;
    double turnOver2 = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA,8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.afrisoftech.lib.HosDatePanel datePanel = null;
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void WeeklyRevPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate, com.afrisoftech.lib.HosDatePanel reportDatePanel) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        // Debtor = combox;
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date"+endDate);
        periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        // ageingSeries = new com.afrisoftech.timeseries.WeeklyAgeing(4, endate);
        // long enDate = java.util.Date.parse(endDate);
        datePanel = reportDatePanel;
        
        
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
        
        java.lang.Object[][] rangeDates = periodicDates.getWeeklyDates();
        
        // ageingDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals[] = new double[rangeDates.length];
        
        
        java.lang.Object[][] rangeDates1 = periodicDates.getWeeklyDates();
        
        // ageingDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals1[] = new double[rangeDates1.length];
        
        java.lang.Object[][] rangeDates2 = periodicDates.getWeeklyDates();
        //java.lang.Object[][] rangeDates2= ageingSeries.getAgeingDateSeries();
        // ageingDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals2[] = new double[rangeDates2.length];
        
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
            
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            
            long dateNow = calendar.getTimeInMillis();
            
            java.sql.Date datenowSql= new java.sql.Date(dateNow);
            
            System.out.println(datenowSql.toString());
            
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
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
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT current_timestamp as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName, pFontHeader2),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Ageing  Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    double Totals = 0.00;
                    double Totals1 = 0.00;
                    double Totals2 = 0.00;
                    double OS = 0.00;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+2);
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+2);
                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+2);
                        
                        String headerWidths = null;
                        
                        java.util.Vector headerVector = new java.util.Vector(1,1);
                        
                        int z = rangeDates.length;
                        
                        
                        int headerwidths[] = {33,13,13,13,13,15};//,13,13};
                        
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((107));
                        
                        table.setHeaderRows(2);
                        
                        table1.setWidths(headerwidths);
                        
                        table1.setWidthPercentage((107));
                        
                        table1.setHeaderRows(2);
                        
                        table2.setWidths(headerwidths);
                        
                        table2.setWidthPercentage((107));
                        
                        table2.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        Phrase phrase = new Phrase("");
                        table.getDefaultCell().setColspan(4);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
                            
                            
                            phrase = new Phrase("WEEKLY REVENUE AS AT [ OP ]: " +dateFormat.format(endDate2), pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Printed on : "+date,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("DESCRIPTION",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        for (int x = 0; x < rangeDates.length; x++) {
                            
                            
                            try {
                                
                                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd MMM yyyy");
                                // java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "w");
                                com.afrisoftech.lib.DateFormatter yearFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "yy");
                                
                                java.lang.String monthString = dateFormatter.getDateString();
                                java.lang.String yearStringCurrent = yearFormatterCurrent.getDateString();
                                com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "w");
                                com.afrisoftech.lib.DateFormatter yearFormatterCurrent1 = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "yy");
                                
                                java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                java.lang.String yearStringCurrent1 = yearFormatterCurrent1.getDateString();
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Week ["+monthStringCurrent+"/"+yearStringCurrent+"]", pFontHeader);
                                interval = x;
                                
                                table.addCell(phrase);
                                
                                
                            } catch(java.text.ParseException prs) {
                                prs.printStackTrace();
                            }
                            
                        }
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("+ "+((interval + 1) * 7)+" Days",pFontHeader);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Total "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("O/S",pFontHeader);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            double GrandTotal = 0.00;
                            double Over120Total = 0.00;
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println(listofAct.length);
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double TurnOver = 0.00;
                                double Over120 = 0.00;
                                double TotalCount = 0.00;
                                
                                
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                java.sql.Statement stmta1 = connectDB.createStatement();
                                java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"' AND drawer = 'OP'");
                                java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select activity from pb_activity WHERE code = ?");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                //java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select description from ac_ledger WHERE activity_code = ?");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                
                                pset22.setString(1,listofAct[i].toString().toUpperCase());
                                java.sql.ResultSet rSeta1 = pSeta1.executeQuery();
                                while (rSeta1.next()) {
                                    name = rSeta1.getInt(1);
                                    
                                }
                                if (name > 0){
                                    java.sql.ResultSet rset22 = pset22.executeQuery();
                                    
                                    while (rset22.next()){
                                        
                                        table.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1).toString().toUpperCase(), "-"),pFontHeader1);
                                        table.addCell(phrase);
                                        // columnTotals[t] = columnTotals[t] + rset.getDouble(1);
                                        
                                        
                                    }
                                    
                                    
                                    
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
                                    java.sql.Statement stc = connectDB.createStatement();
                                    System.out.println("Dealer Is : ["+listofAct[i]+"].");
                                    
                                    
                                    java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+rangeDates[rangeDates.length - 1][0]+"' AND drawer = 'OP'");
                                    pset1.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset1 = pset1.executeQuery();
                                    
                                    java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+endDate+"' AND drawer = 'OP'");
                                    pset111.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset111 = pset111.executeQuery();
                                    java.sql.Statement st02 = connectDB.createStatement();
                                    
                                    
                                    java.sql.PreparedStatement pset112 = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ? AND date < '"+endDate+"' AND drawer = 'OP'");
                                    pset112.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset112 = pset112.executeQuery();
                                    
                                    
                                    for (int t = 0; t < rangeDates.length; t++) {
                                        java.sql.Statement st01 = connectDB.createStatement();
                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date between '"+rangeDates[t][1]+"' AND '"+rangeDates[t][0]+"'  AND drawer = 'OP'");
                                        pset.setString(1,listofAct[i].toString().toUpperCase());
                                        java.sql.ResultSet rset = pset.executeQuery();
                                        
                                        
                                        while (rset.next()){
                                            
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                            table.addCell(phrase);
                                            columnTotals[t] = columnTotals[t] + rset.getDouble(1);
                                            
                                            TotalCount = TotalCount + rset.getDouble(1);
                                        }
                                        
                                        
                                        
                                    }
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
                                    table.addCell(phrase);
                                    
                                }else{
                                    
                                    
                                }
                            }
                            // com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+2);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            // while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total "+ks, pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            for (int x = 0; x < rangeDates.length; x++) {
                                // phrase = new Phrase("Current"+2*x,pFontHeader);
                                // table.addCell(phrase);
                                
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[x])), pFontHeader);
                                
                                table.addCell(phrase);
                                turnOver = turnOver + columnTotals[x];
                            }
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOver)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Over120Total)), pFontHeader);
                            
                            // table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(OS)), pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals)), pFontHeader);
                            
                            docPdf.add(table);
                            boolean boolNewPage = docPdf.newPage();
                            
                            
                            
                            table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table1.getDefaultCell().setColspan(6);
                            // Phrase phrase = new Phrase("");
                            table1.getDefaultCell().setColspan(4);
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
                                
                                
                                phrase = new Phrase("WEEKLY REVENUE AS AT [ IP ]: " +dateFormat.format(endDate2), pFontHeader);
                                
                                table1.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table1.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Printed on : "+date,pFontHeader);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("DESCRIPTION",pFontHeader);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            for (int x = 0; x < rangeDates1.length; x++) {
                                
                                
                                try {
                                    
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd MMM yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "w");
                                    com.afrisoftech.lib.DateFormatter yearFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "yy");
                                    
                                    java.lang.String monthString = dateFormatter.getDateString();
                                    java.lang.String yearStringCurrent = yearFormatterCurrent.getDateString();
                                    com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "w");
                                    com.afrisoftech.lib.DateFormatter yearFormatterCurrent1 = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "yy");
                                    
                                    java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                    java.lang.String yearStringCurrent1 = yearFormatterCurrent1.getDateString();
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Week ["+monthStringCurrent+"/"+yearStringCurrent+"]", pFontHeader);
                                    interval = x;
                                    
                                    table1.addCell(phrase);
                                    
                                    
                                } catch(java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                                
                            }
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("+ "+((interval + 1) * 7)+" Days",pFontHeader);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total "+ks,pFontHeader);
                            table1.addCell(phrase);
                            
                            phrase = new Phrase("O/S",pFontHeader);
                            
                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            for (int i = 0; i < listofAct.length; i++) {
                                double TurnOver1 = 0.00;
                                double Over1201 = 0.00;
                                double TotalCount1 = 0.00;
                                
                                
                                
                                table1.getDefaultCell().setColspan(1);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table1.getDefaultCell().setColspan(1);
                                java.sql.Statement stmta11 = connectDB.createStatement();
                                java.sql.PreparedStatement pSeta11 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"' AND drawer = 'IP'");
                                java.sql.PreparedStatement pset221 = connectDB.prepareStatement("select activity from pb_activity WHERE code = ?");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                //java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select description from ac_ledger WHERE activity_code = ?");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                
                                pset221.setString(1,listofAct[i].toString().toUpperCase());
                                java.sql.ResultSet rSeta11 = pSeta11.executeQuery();
                                while (rSeta11.next()) {
                                    name = rSeta11.getInt(1);
                                    
                                }
                                if (name > 0){
                                    java.sql.ResultSet rset221 = pset221.executeQuery();
                                    
                                    while (rset221.next()){
                                        
                                        table1.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(dbObject.getDBObject(rset221.getObject(1).toString().toUpperCase(), "-"),pFontHeader1);
                                        table1.addCell(phrase);
                                        // columnTotals[t] = columnTotals[t] + rset.getDouble(1);
                                        
                                        
                                    }
                                    
                                    
                                    
                                    java.sql.Statement st2S = connectDB.createStatement();
                                    java.sql.Statement st21S = connectDB.createStatement();
                                    java.sql.Statement st22S = connectDB.createStatement();
                                    java.sql.Statement st23S = connectDB.createStatement();
                                    java.sql.Statement st211S = connectDB.createStatement();
                                    java.sql.Statement st221S = connectDB.createStatement();
                                    java.sql.Statement st231S = connectDB.createStatement();
                                    java.sql.Statement st2AS = connectDB.createStatement();
                                    java.sql.Statement st2BS = connectDB.createStatement();
                                    java.sql.Statement st2CS = connectDB.createStatement();
                                    java.sql.Statement stcS = connectDB.createStatement();
                                    System.out.println("Dealer Is : ["+listofAct[i]+"].");
                                    
                                    
                                    java.sql.PreparedStatement pset1S = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+rangeDates1[rangeDates1.length - 1][0]+"' AND drawer = 'IP'");
                                    pset1S.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset1S = pset1S.executeQuery();
                                    
                                    java.sql.PreparedStatement pset111S = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+endDate+"' AND drawer = 'IP'");
                                    pset111S.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset111S = pset111S.executeQuery();
                                    java.sql.Statement st02S = connectDB.createStatement();
                                    
                                    
                                    java.sql.PreparedStatement pset112S = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ? AND date < '"+endDate+"' AND drawer = 'IP'");
                                    pset112S.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset112S = pset112S.executeQuery();
                                    
                                    
                                    for (int t = 0; t < rangeDates1.length; t++) {
                                        java.sql.Statement st01S = connectDB.createStatement();
                                        java.sql.PreparedStatement psetS = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date between '"+rangeDates1[t][1]+"' AND '"+rangeDates1[t][0]+"'  AND drawer = 'IP'");
                                        psetS.setString(1,listofAct[i].toString().toUpperCase());
                                        java.sql.ResultSet rsetS = psetS.executeQuery();
                                        
                                        
                                        while (rsetS.next()){
                                            
                                            table1.getDefaultCell().setColspan(1);
                                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetS.getString(1)),pFontHeader1);
                                            table1.addCell(phrase);
                                            columnTotals1[t] = columnTotals1[t] + rsetS.getDouble(1);
                                            
                                            TotalCount1 = TotalCount1 + rsetS.getDouble(1);
                                        }
                                        
                                        
                                        
                                    }
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount1)), pFontHeader);
                                    table1.addCell(phrase);
                                    
                                }else{
                                    
                                    
                                }
                            }
                            
                            
                            
                            
                            
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table1.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            // while (rsetTotals.next()) {
                            
                            table1.getDefaultCell().setColspan(1);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total "+ks, pFontHeader);
                            
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(1);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            for (int x = 0; x < rangeDates1.length; x++) {
                                // phrase = new Phrase("Current"+2*x,pFontHeader);
                                // table.addCell(phrase);
                                
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals1[x])), pFontHeader);
                                
                                table1.addCell(phrase);
                                turnOver1 = turnOver1 + columnTotals1[x];
                            }
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOver1)), pFontHeader);
                            
                            table1.addCell(phrase);
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Over120Total1)), pFontHeader);
                            
                            // table.addCell(phrase);
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(OS1)), pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals1)), pFontHeader);
                            
                            docPdf.add(table1);
                            
                            
                            
                            boolNewPage = docPdf.newPage();
                            
                            
                            
                            table2.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table2.getDefaultCell().setColspan(6);
                            // Phrase phrase = new Phrase("");
                            table2.getDefaultCell().setColspan(4);
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
                                
                                
                                phrase = new Phrase("WEEKLY REVENUE AS AT [ COMBINED ]: " +dateFormat.format(endDate2), pFontHeader);
                                
                                table2.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Printed on : "+date,pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("DESCRIPTION",pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            for (int x = 0; x < rangeDates2.length; x++) {
                                
                                
                                try {
                                    
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd MMM yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates2[x][0].toString().trim()), "w");
                                    com.afrisoftech.lib.DateFormatter yearFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "yy");
                                    
                                    java.lang.String monthString = dateFormatter.getDateString();
                                    java.lang.String yearStringCurrent = yearFormatterCurrent.getDateString();
                                    com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates2[x][0].toString().trim()), "w");
                                    com.afrisoftech.lib.DateFormatter yearFormatterCurrent1 = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates2[x][0].toString().trim()), "yy");
                                    
                                    java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                    java.lang.String yearStringCurrent1 = yearFormatterCurrent1.getDateString();
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Week ["+monthStringCurrent+"/"+yearStringCurrent+"]", pFontHeader);
                                    interval = x;
                                    
                                    table2.addCell(phrase);
                                    
                                    
                                } catch(java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                                
                            }
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("+ "+((interval + 1) * 7)+" Days",pFontHeader);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total "+ks,pFontHeader);
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("O/S",pFontHeader);
                            
                            table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            for (int i = 0; i < listofAct.length; i++) {
                                double TurnOver2 = 0.00;
                                double Over1202 = 0.00;
                                double TotalCount2 = 0.00;
                                
                                
                                
                                table2.getDefaultCell().setColspan(1);
                                
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table2.getDefaultCell().setColspan(1);
                                java.sql.Statement stmta11 = connectDB.createStatement();
                                java.sql.PreparedStatement pSeta11 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
                                java.sql.PreparedStatement pset221 = connectDB.prepareStatement("select activity from pb_activity WHERE code = ?");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                //java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select description from ac_ledger WHERE activity_code = ?");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                
                                pset221.setString(1,listofAct[i].toString().toUpperCase());
                                java.sql.ResultSet rSeta11 = pSeta11.executeQuery();
                                while (rSeta11.next()) {
                                    name = rSeta11.getInt(1);
                                    
                                }
                                if (name > 0){
                                    java.sql.ResultSet rset221 = pset221.executeQuery();
                                    
                                    while (rset221.next()){
                                        
                                        table2.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(dbObject.getDBObject(rset221.getObject(1).toString().toUpperCase(), "-"),pFontHeader1);
                                        table2.addCell(phrase);
                                        // columnTotals[t] = columnTotals[t] + rset.getDouble(1);
                                        
                                        
                                    }
                                    
                                    
                                    
                                    java.sql.Statement st2S = connectDB.createStatement();
                                    java.sql.Statement st21S = connectDB.createStatement();
                                    java.sql.Statement st22S = connectDB.createStatement();
                                    java.sql.Statement st23S = connectDB.createStatement();
                                    java.sql.Statement st211S = connectDB.createStatement();
                                    java.sql.Statement st221S = connectDB.createStatement();
                                    java.sql.Statement st231S = connectDB.createStatement();
                                    java.sql.Statement st2AS = connectDB.createStatement();
                                    java.sql.Statement st2BS = connectDB.createStatement();
                                    java.sql.Statement st2CS = connectDB.createStatement();
                                    java.sql.Statement stcS = connectDB.createStatement();
                                    System.out.println("Dealer Is : ["+listofAct[i]+"].");
                                    
                                    
                                    java.sql.PreparedStatement pset1S = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+rangeDates2[rangeDates2.length - 1][0]+"'");
                                    pset1S.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset1S = pset1S.executeQuery();
                                    
                                    java.sql.PreparedStatement pset111S = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date < '"+endDate+"'");
                                    pset111S.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset111S = pset111S.executeQuery();
                                    java.sql.Statement st02S = connectDB.createStatement();
                                    
                                    
                                    java.sql.PreparedStatement pset112S = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ? AND date < '"+endDate+"'");
                                    pset112S.setString(1,listofAct[i].toString());
                                    java.sql.ResultSet rset112S = pset112S.executeQuery();
                                    
                                    
                                    for (int t = 0; t < rangeDates2.length; t++) {
                                        java.sql.Statement st01S = connectDB.createStatement();
                                        java.sql.PreparedStatement psetS = connectDB.prepareStatement("select sum(credit-debit) from ac_ledger WHERE activity_code = ?  AND date between '"+rangeDates2[t][1]+"' AND '"+rangeDates2[t][0]+"'");
                                        psetS.setString(1,listofAct[i].toString().toUpperCase());
                                        java.sql.ResultSet rsetS = psetS.executeQuery();
                                        
                                        
                                        while (rsetS.next()){
                                            
                                            table2.getDefaultCell().setColspan(1);
                                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetS.getString(1)),pFontHeader1);
                                            table2.addCell(phrase);
                                            columnTotals2[t] = columnTotals2[t] + rsetS.getDouble(1);
                                            TotalCount2 = TotalCount2 + rsetS.getDouble(1);
                                        }
                                        
                                        
                                        
                                    }
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount2)), pFontHeader);
                                    table2.addCell(phrase);
                                    
                                }else{
                                    
                                    
                                }
                            }
                            
                            
                            
                            
                            
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table2.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            // while (rsetTotals.next()) {
                            
                            table2.getDefaultCell().setColspan(1);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total "+ks, pFontHeader);
                            
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            for (int x = 0; x < rangeDates2.length; x++) {
                                // phrase = new Phrase("Current"+2*x,pFontHeader);
                                // table.addCell(phrase);
                                
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals2[x])), pFontHeader);
                                
                                table2.addCell(phrase);
                                turnOver2 = turnOver2 + columnTotals2[x];
                            }
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(turnOver2)), pFontHeader);
                            
                            table2.addCell(phrase);
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Over120Total1)), pFontHeader);
                            
                            // table.addCell(phrase);
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(OS1)), pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals2)), pFontHeader);
                            
                            docPdf.add(table2);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        // Bad
                        
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
        
        //  com.afrisoftech.lib.HosDatePanel disdia = HosDatePanel(new java.awt.Frame(),new java.lang.Boolean(false),new java.lang.Integer(0),connectDB);
        // disdia.setCursor(new java.awt.Cursor(javax.swing.JFrame.DEFAULT_CURSOR));
        
        datePanel.getParent().setCursor(new java.awt.Cursor(javax.swing.JFrame.DEFAULT_CURSOR));
        
    }
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT code,activity FROM pb_activity where (category_class ilike 'PLI%') order by code");
            
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());
            }
            
            //System.out.println(rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}





