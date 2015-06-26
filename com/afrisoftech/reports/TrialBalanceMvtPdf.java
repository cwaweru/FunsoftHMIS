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


public class TrialBalanceMvtPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    double debit = 0.00;
    double credit = 0.00;
    double debit1 = 0.00;
    double credit2 = 0.00;
    double balance = 0.00;
    double totalDebits = 0.00;
    double totalCredits = 0.00;
    double totalDebits1 = 0.00;
    double totalCredits1 = 0.00;
    double totalDebits11 = 0.00;
    double totalCredits11 = 0.00;
    
    int j = 0;
    String ks;
    java.lang.String activity = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void TrialBalanceMvtPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
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
        
        double columnTotals[] = new double[ageingDates.length];
        
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
                        
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic","postgres","pilsiner");
                        
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Extendend Trial Balance  - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {8,20,12,12,12,12,12,12};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(3);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        
                        try {
                            
                            double GrandTotal = 0.00;
                            double Over120Total = 0.00;
                            
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            
                            phrase = new Phrase("Extended Trial Balance : As at "+dateFormat.format(endDate1), pFontHeader);
                            
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
                        try{
                            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toString().trim()), "MMMM yyyy");
                            
                            java.lang.String monthString = dateFormatter.getDateString();
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            phrase = new Phrase("  ",pFontHeader);
                            // table.addCell(phrase);
                            
                            phrase = new Phrase("  ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Balance BF",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(monthString +" Movement",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Ending Balance",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase("GL. Code",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Description",pFontHeader);
                            table.addCell(phrase);
                            
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Debit "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Credit "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Debit "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Credit "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Debit "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Credit "+ks,pFontHeader);
                            table.addCell(phrase);
                            
                        } catch(java.text.ParseException prs) {
                            prs.printStackTrace();
                        }
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        java.lang.Object[] listofAct = this.getListofActivities();
                        
                        //   for (int j = 0; j < listofStaffNos.length; j++) {
                        try {
                            
                            int name = 0;
                            double TurnOver = 0.00;
                            double Over120 = 0.00;
                            double TotalCount = 0.00;
                            double current = 0.00;
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                
                                
                                
                                java.sql.Statement stmta1 = connectDB.createStatement();
                                java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(gl_code) FROM tb_itemized_summary where  gl_code = '"+listofAct[i]+"'");
                                java.sql.ResultSet rSeta1 = pSeta1.executeQuery();
                                while (rSeta1.next()) {
                                    name = rSeta1.getInt(1);
                                    
                                }
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                if (name > 0){
                                    
                                    java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select distinct code,initcap(activity) from pb_activity WHERE code = ? GROUP BY code,activity");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                    
                                    pset22.setString(1,listofAct[i].toString());
                                    
                                    java.sql.ResultSet rset22 = pset22.executeQuery();
                                    while (rset22.next()){
                                        
                                        table.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1).toString(), "-"),pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(dbObject.getDBObject(rset22.getObject(2).toString(), "-"),pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        //for (int t = ageingDates.length - 1; t >= 0; t--) {
                                        double amount1 = 0.00;
                                        double amounta = 0.00;
                                        double amount1a = 0.00;
                                        
                                        for (int t = 0; t < ageingDates.length; t++) {
                                            double amount = 0.00;
                                            double amount1b = 0.00;
                                            
                                            System.out.println("Checking dates : "+ageingDates[t][1].toString());
                                            
                                            java.sql.Statement st01 = connectDB.createStatement();
                                            // java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(mtd_debit-mtd_credit) from ac_trial_balance WHERE code = ?  AND date between '"+ageingDates[t][0]+"' AND '"+ageingDates[t][1]+"'");
                                            java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(debit-credit) from tb_itemized_summary WHERE gl_code = ?  AND date <= '"+ageingDates[t][1]+"'");
                                            
                                            pset.setString(1,listofAct[i].toString().toUpperCase());
                                            
                                            java.sql.ResultSet rset = pset.executeQuery();
                                            
                                            while (rset.next()){
                                                amount = rset.getDouble(1);
                                                columnTotals[t] = columnTotals[t] + amount;
                                                
                                            }
                                            
                                            if(t == 0){
                                                if (amount > 0){
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount)),pFontHeader1);
                                                    table.addCell(phrase);
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontHeader1);
                                                    table.addCell(phrase);
                                                    totalDebits = totalDebits + amount;
                                                    amount1 = amount;
                                                }else{
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontHeader1);
                                                    table.addCell(phrase);
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount*-1)),pFontHeader1);
                                                    table.addCell(phrase);
                                                    totalCredits = totalCredits + (amount*-1);
                                                    amount1 = amount;
                                                }
                                            }else{
                                                
                                                java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(debit),sum(credit) from tb_itemized_summary WHERE gl_code = ?  AND date between '"+ageingDates[t][0]+"' AND '"+ageingDates[t][1]+"'");
                                                
                                                pset1.setString(1,listofAct[i].toString().toUpperCase());
                                                
                                                java.sql.ResultSet rset1 = pset1.executeQuery();
                                                
                                                while (rset1.next()){
                                                    amount = rset1.getDouble(1);
                                                    amount1b = rset1.getDouble(2);
                                                    columnTotals[t] = columnTotals[t] + amount;
                                                    
                                                }
                                                
                                                // if (amount > 0){
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount)),pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount1b)),pFontHeader1);
                                                table.addCell(phrase);
                                                totalDebits1 = totalDebits1 + amount;
                                                totalCredits1 = totalCredits1 + amount1b;
                                                amounta = amount-amount1b;
                                          
                                            }
                                            
                                        }
                                        
                                        balance = amounta + amount1;
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        if (balance > 0){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance)),pFontHeader1);
                                            table.addCell(phrase);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontHeader1);
                                            table.addCell(phrase);
                                            totalDebits11 = totalDebits11 + balance;
                                        }else{
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontHeader1);
                                            table.addCell(phrase);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance*-1)),pFontHeader1);
                                            table.addCell(phrase);
                                            totalCredits11 = totalCredits11 + balance;
                                        }
                                    }
                                }
                            }
                            
                            // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            // while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //for (int x = ageingDates.length - 1; x >= 0; x--) {
                            
                            for (int t = 0; t < ageingDates.length; t++) {
                                if(t == 0){
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalDebits)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalCredits)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                }else{
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalDebits1)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalCredits1)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    // totalDebits = columnTotals[x] + columnTotals1[x];
                                }
                            }
                          /*  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalDebits1+totalDebits-)), pFontHeader);
                           
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalCredits1+totalCredits)), pFontHeader);
                           
                            table.addCell(phrase);*/
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalDebits11)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalCredits11*-1)), pFontHeader);
                            
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
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT code FROM pb_activity order by code");
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
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





