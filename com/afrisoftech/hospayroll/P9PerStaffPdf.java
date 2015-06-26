//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospayroll;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class P9PerStaffPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.lang.String staffNo = null;
    
    int jan = 0;
    
    int feb = 0;
    
    int mar = 0;
    
    int april = 0;
    
    int may = 0;
    
    int jun = 0;
    
    int jul = 0;
    
    int aug = 0;
    
    int sep = 0;
    
    int oct = 0;
    
    int nov = 0;
    
    int dec = 0;
    
    java.lang.String months = null;
    
    double basic = 0.00;
    
    double non = 0.00;
    
    double valq = 0.00;
    
    double gross = 0.00;
    
    double contri = 0.00;
    
    double fixed = 0.00;
    
    double owner = 0.00;
    
    double retir = 0.00;
    
    double chargable = 0.00;
    
    double tax = 0.00;
    
    double prelief = 0.00;
    
    double paye = 0.00;
    
    double nontax = 0.00;
    
    double basicT = 0.00,
            nonT = 0.00,
            valqT = 0.00,
            grossT = 0.00,
            totathT = 0.00,
            contriT = 0.00,
            consT = 0.00,
            ownerT = 0.00,
            retirT = 0.00,
            chargableT = 0.00,
            taxT = 0.00,
            preliefT = 0.00,
            payeT = 0.00;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    Phrase phraseA;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD);
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void P9PerStaffPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate, java.lang.String staffno) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        staffNo = staffno;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberListPdf().MemberListPdf();
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    
                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("KENYA REVENUE AUTHORITY "+"\n INCOME TAX DEPARTMENT "+"\n INCOME TAX DEDUCTION CARD YEAR "+endDate,pFontHeader3),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    
                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    headerFoter.setBorderColor(java.awt.Color.WHITE);
                    docPdf.setHeader(headerFoter);
                    
                    
                    //         com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("P9A \t\t\t\t\t (See back of this card for further information required by the Department)."), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    //         docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    int numColumns = 2;
                    java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                    
                    for (int j = 0; j < listofStaffNos.length; j++) {
                        try {
                            com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(2);
                            table1.getDefaultCell().setPadding(3);
                            
                            int headerwidths1[] = {70,30};
                            
                            //  table1.setWidths(headerwidths1);
                            
                            table1.setWidthPercentage((100));
                            
                            
                            
                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            Phrase phrase = new Phrase("", pFontHeader);
                            
                            //  table1.addCell(phrase);
                            try{
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct employee_no,upper(last_name),upper(first_name||' '||middle_name),pin_no from master_file where employee_no = '"+listofStaffNos[j]+"' order by employee_no");
                                
                                java.sql.Statement stm = connectDB.createStatement();
                                
                                java.sql.ResultSet rsetss = stm.executeQuery("SELECT DISTINCT UPPER(hospital_name),pin_no from pb_hospitalprofile");
                                
                                while (rsetss.next()){
                                    table1.getDefaultCell().setColspan(1);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Employer's Name  :  " +rsetss.getObject(1).toString(),pFontHeader1);
                                
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Employer's P.I.N.              : "+rsetss.getObject(2).toString(),pFontHeader1);
                                
                                table1.addCell(phrase);
                                }
                                while (rset.next()){
                                    table1.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Employee's Main Name  :  " +rset.getObject(2).toString(),pFontHeader1);
                                    
                                    table1.addCell(phrase);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Employee's P.I.N.              : "+rset.getObject(4).toString(),pFontHeader1);
                                    
                                    table1.addCell(phrase);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Employee's Other Names  :  "+rset.getObject(3).toString(),pFontHeader1);
                                    
                                    table1.addCell(phrase);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Emp No.            :  "+rset.getObject(1).toString(),pFontHeader1);
                                    
                                    table1.addCell(phrase);
                                }
                                docPdf.add(table1);
                                //       System.out.println("Current Doc size "+ pdfWriter.getTableBottom(table1));
                                //  System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            
                        } catch(com.lowagie.text.BadElementException BadElExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                            
                        }
                        
                        
                        try {
                            //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            //  for (int j = 0; j < listofStaffNos.length; j++) {
                            
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(14);
                            
                            int headerwidths[] = {8,7,7,7,7,7,7,7,7,8,8,7,7,7};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM |Rectangle.TOP);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setColspan(14);
                            
                            Phrase phrase = new Phrase("", pFontHeader);
                            
                            // table.addCell(phrase);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT| Rectangle.RIGHT |Rectangle.TOP);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            //  table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Month",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Basic Salary",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Non Cash Benefits",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Value of Quarters",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Total Gross Pay",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Defined Contribution Retirement Scheme",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase("Owner Occupied Interest",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Retirement Contribution & Owner Occupied Interest",pFontHeader);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("Chargeable Pay",pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Tax on (H)",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Relief Monthly",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("PAYE Tax (J-K)",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorder( Rectangle.LEFT| Rectangle.RIGHT);
                            
                            phrase = new Phrase(" ",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("A",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("B",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("C",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("D",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT| Rectangle.RIGHT);
                            
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("E",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorder( Rectangle.LEFT| Rectangle.RIGHT);
                            
                            
                            phrase = new Phrase("F  "+"\n  Amount of Interest",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("G  "+"\n  The Lowest of E added to F",pFontHeader);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("H",pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("J",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT| Rectangle.RIGHT);
                            
                            phrase = new Phrase("K",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder( Rectangle.LEFT| Rectangle.RIGHT);
                            
                            phrase = new Phrase("L",pFontHeader);
                            table.addCell(phrase);
                            
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT| Rectangle.RIGHT);
                            
                            phrase = new Phrase(" ",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("e1 "+"\n  30% A",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("e2 "+"\n  Actual",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("e3 "+"\n  Fixed",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            // table.addCell("Amount KShs.");
                            
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            try {
                                java.lang.Object[] listofAct = this.getListofStaffNos1();
                                for (int l = 0; l < listofAct.length; l++) {
                                    
                                    chargable = 0.00;
                                    contri = 0.00;
                                    basic = 0.00;
                                    non = 0.00;
                                    valq = 0.00;
                                    retir = 0.00;
                                    tax = 0.00;
                                    paye = 0.00;
                                    prelief = 0.00;
                                    java.sql.Statement st = connectDB.createStatement();
                                    java.sql.Statement st1 = connectDB.createStatement();
                                    java.sql.Statement st3 = connectDB.createStatement();
                                    java.sql.Statement st4 = connectDB.createStatement();
                                    java.sql.Statement st5 = connectDB.createStatement();
                                    java.sql.Statement st6 = connectDB.createStatement();
                                    java.sql.Statement st21 = connectDB.createStatement();
                                    java.sql.Statement st2 = connectDB.createStatement();
                                    
                                    java.sql.Statement st11b = connectDB.createStatement();
                                    java.sql.Statement st11c = connectDB.createStatement();
                                    java.sql.Statement st11d = connectDB.createStatement();
                                    java.sql.Statement st11e = connectDB.createStatement();
                                    java.sql.Statement st11f = connectDB.createStatement();
                                    java.sql.Statement st11g = connectDB.createStatement();
                                    java.sql.Statement st11h = connectDB.createStatement();
                                    java.sql.Statement st11m = connectDB.createStatement();
                                    
                                    java.sql.ResultSet rs1 = st11b.executeQuery("SELECT count(month) FROM tax_card WHERE date = '"+listofAct[l]+"'");
                                    java.sql.ResultSet rs2 = st11c.executeQuery("SELECT count(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Earning'");
                                    java.sql.ResultSet rs3 = st11d.executeQuery("SELECT count(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Non Cash%'");
                                    java.sql.ResultSet rs4 = st11e.executeQuery("SELECT count(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND description ilike 'House Value'");
                                    java.sql.ResultSet rs5 = st11f.executeQuery("SELECT count(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Less Relief'");
                                    java.sql.ResultSet rs7 = st11g.executeQuery("SELECT count(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND description ilike '%P.A.%'");
                                    java.sql.ResultSet rs6 = st11h.executeQuery("SELECT count(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND description ilike 'Monthly Personal%'");
                                    java.sql.ResultSet rs8 = st11m.executeQuery("select count(amount) from non_taxed_earnings WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"'");
                                    
                                    
                                    
                                    java.sql.ResultSet rset1x = st6.executeQuery("SELECT DISTINCT month FROM tax_card WHERE date = '"+listofAct[l]+"'");
                                   // java.sql.ResultSet rset1 = st1.executeQuery("SELECT date,sum(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Earning' GROUP BY date ORDER BY date ASC");
                                    java.sql.ResultSet rset1 = st1.executeQuery("SELECT date,sum(amount) FROM earnings WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND amount > 0 GROUP BY date ORDER BY date ASC");
                                 
                                    java.sql.ResultSet rset =  st.executeQuery("SELECT date, sum(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Non Cash%' AND description not ilike 'House value' GROUP BY date ORDER BY date ASC");
                                    //java.sql.ResultSet rset =  st.executeQuery("SELECT 0 FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Non Cash%'");
                                    
                                    java.sql.ResultSet rset2 = st2.executeQuery("SELECT date, sum(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND description ilike 'House Value' GROUP BY date ORDER BY date ASC");
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT date, sum(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND allowance_deduction ILIKE 'Less Relief' GROUP BY date ORDER BY date ASC");
                                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT date, sum(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND description ilike '%P.A.%' GROUP BY date ORDER BY date ASC");
                                    java.sql.ResultSet rset5 = st5.executeQuery("SELECT date, sum(amount) FROM posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"' AND description ilike 'Monthly Personal%' GROUP BY date ORDER BY date ASC");
                                    
                                    java.sql.ResultSet rset11F = st21.executeQuery("select sum(amount) from non_taxed_earnings WHERE staff_no = '"+listofStaffNos[j]+"' AND date = '"+listofAct[l]+"'");
                                    
                                    while (rs1.next()) {
                                        while (rs2.next()) {
                                            while (rs3.next()) {
                                                while (rs4.next()) {
                                                    while (rs5.next()) {
                                                        while (rs6.next()) {
                                                            while (rs7.next()) {
                                                                while (rs8.next()){
                                                                    
                                                                    jan = rs1.getInt(1);
                                                                    
                                                                    feb = rs2.getInt(1);
                                                                    
                                                                    mar = rs3.getInt(1);
                                                                    
                                                                    april = rs4.getInt(1);
                                                                    
                                                                    may = rs5.getInt(1);
                                                                    
                                                                    jun = rs6.getInt(1);
                                                                    
                                                                    jul = rs7.getInt(1);
                                                                    
                                                                    aug = rs8.getInt(1);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    if(jan > 0){
                                        while (rset1x.next()) {
                                            phrase = new Phrase(rset1x.getString(1).toUpperCase(), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                    }else{
                                        phrase = new Phrase("".toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    if(aug > 0){
                                        while (rset11F.next()) {
                                            nontax = rset11F.getDouble(1);
                                        }
                                    }else{
                                        nontax = 0.00;
                                        // }
                                    }
                                    
                                    if(feb > 0){
                                        while (rset1.next()) {
                                        //    basic = rset1.getDouble(2)-nontax;
                                            basic = rset1.getDouble(2);
                                            
                                            
                                        }
                                    }else{
                                        basic = 0.00;
                                        // }
                                    }
                                    while (rset.next()) {
                                        if(april > 0){
                                            non = rset.getDouble(2);
                                            
                                        }else{
                                            non = 0.00;
                                        }
                                    }
                                    while (rset2.next()) {
                                        
                                        if(mar > 0){
                                            valq = rset2.getDouble(2);
                                            
                                        }else{
                                            valq = 0.00;
                                        }
                                    }
                                    
                                    while (rset3.next()) {
                                        
                                        if(may > 0){
                                            contri = rset3.getDouble(2);
                                            retir = rset3.getDouble(2);
                                            
                                        }else{
                                            contri = 0.00;
                                            retir = 0.00;
                                        }
                                    }
                                    while (rset4.next()) {
                                        
                                        if(jul > 0){
                                            paye = rset4.getDouble(2);
                                            
                                        }else{
                                            paye = 0.00;
                                        }
                                    }
                                    while (rset5.next()) {
                                        if(jun > 0){
                                            prelief = rset5.getDouble(2);
                                            
                                        }else{
                                            prelief = 0.00;
                                        }
                                    }
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(basic),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(non),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(valq),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(basic+non+valq),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    gross = basic+non+valq;
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf((basic+non+valq)*0.3),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    // actual = rset1.getDouble(6);
                                    
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(contri),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    if(basic > 0){
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("17,500.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        fixed = 17500.00;
                                    }else{
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        fixed = 0.00;
                                    }
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    owner = 0.00;
                                    
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(retir),"0.0")), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(basic+non+valq-retir),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    chargable = basic+non+valq-retir;
                                    
                                    tax = paye+prelief;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(tax),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    //  tax = rset4.getDouble(2)+rset5.getDouble(2);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(prelief),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(paye),"0.00")), pFontHeader1);
                                    table.addCell(phrase);
                                    System.out.println("Am here What is happening");
                                    
                                    basicT = basicT +basic-valq;
                                    nonT = nonT + non;
                                    valqT = valqT + valq;
                                    grossT = grossT + basic+non+valq;
                                    totathT = totathT + ((basic+non+valq)*0.3);
                                    contriT = contriT + contri;
                                    consT = consT + fixed;
                                    ownerT = ownerT + owner;
                                    retirT = retirT + retir;
                                    chargableT = chargableT + chargable;
                                    taxT = taxT + tax;
                                    preliefT = preliefT + prelief;
                                    payeT = payeT + paye;
                                }
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("TOTALS", pFontHeader2);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(basicT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nonT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(valqT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(grossT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totathT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(contriT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(consT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(ownerT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(retirT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(chargableT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(taxT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(preliefT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(payeT)), pFontHeader2);
                                table.addCell(phrase);
                                
                                
                                docPdf.add(table);
                                try {
                                    com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);
                                    table2.getDefaultCell().setPadding(3);
                                    
                                    int headerwidths1[] = {50,50};
                                    
                                    //  table1.setWidths(headerwidths1);
                                    
                                    table2.setWidthPercentage((100));
                                    
                                    
                                    
                                    table2.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table2.getDefaultCell().setColspan(1);
                                    try{
                                        java.sql.Statement stA = connectDB.createStatement();
                                        java.sql.Statement stB = connectDB.createStatement();
                                        
                                        java.sql.ResultSet rsetA = stA.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description ilike 'P.A.Y.E'");
                                        java.sql.ResultSet rsetB = stB.executeQuery("SELECT sum(amount) from amount_taxed WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                        while(rsetA.next()){
                                            table2.getDefaultCell().setColspan(2);
                                            table2.getDefaultCell().setBorder(Rectangle.TOP);
                                            table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                            table2.getDefaultCell().setColspan(1);
                                            phrase = new Phrase(" ", pFontHeader2);
                                            table2.addCell(phrase);
                                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table2.getDefaultCell().setColspan(1);
                                            phrase = new Phrase("TOTAL TAX (COL. L) Kshs."+ new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetA.getString(1)), pFontHeader2);
                                            table2.addCell(phrase);
                                            
                                        }
                                        
                                        table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table2.getDefaultCell().setColspan(2);
                                        phrase = new Phrase("To be completed by Employer at end of year ", pFontHeader2);
                                        table2.addCell(phrase);
                                        while(rsetB.next()){
                                            
                                            
                                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table2.getDefaultCell().setColspan(1);
                                            phrase = new Phrase("TOTAL CHARGEABLE PAY(COL. H) Kshs."+ new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetB.getString(1)), pFontHeader2);
                                            table2.addCell(phrase);
                                            table2.getDefaultCell().setColspan(1);
                                            phrase = new Phrase("b) Attach (i) Photostat copy of interest  certificate and statement of accont from the financial institution."+"\n(ii) The DECLARATION duly signed by the employee to form P9A ", pFontHeader1);
                                            table2.addCell(phrase);
                                            
                                        }
                                        
                                        
                                        
                                        //       System.out.println("Current Doc size "+ pdfWriter.getTableBottom(table1));
                                        //  System.out.println("Current Doc size "+ pdfWriter.getCurrentDocumentSize());
                                    } catch(java.sql.SQLException SqlExec) {
                                        
                                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                        
                                    }
                                    phrase = new Phrase("IMPORTANT "+"\n 1. Use P9A (a) For all liable employees and where director/ employee received benefits in "+
                                            "addition to cash emoluments."+"\n (b) Where an employee is eligible to deduction on owner occupier interest. "+"\n 2. (a) Deductable "+
                                            "interest in interest of any month must not exceed Kshs. 8337/- except for December the amount shall be Kshs. 8,337/-", pFontHeader1);
                                    table2.addCell(phrase);
                                    table2.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("NAMES OF FINANCIAL INSTITUTION ADVANCING MORTGAGE LOAN "+"\n "+"\n __________________________________________________________________________"+"\n "+"\n LR NO. "+
                                            "OF OWNER OCCUPIED PROPERTY :_____________________________________"+"\n"+"\n DATE OF OCCUPATION OF HOUSE :____________________________________________", pFontHeader1);
                                    table2.addCell(phrase);
                                    
                                    table2.getDefaultCell().setColspan(2);
                                    phrase = new Phrase("P9A \t\t\t\t\t (See back of this card for further information required by the Department).", pFontHeader1);
                                    table2.addCell(phrase);
                                    
                                    
                                    docPdf.add(table2);
                                    
                                } catch(com.lowagie.text.BadElementException BadElExec) {
                                    
                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                                    
                                }
                                System.out.println("Status of new page : "+docPdf.getPageNumber());
                                
                                
                                boolean boolNewPage = docPdf.newPage();
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            
// }
                            
                        } catch(com.lowagie.text.BadElementException BadElExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                            
                            
                        }
                        //
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
                    //      wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    
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
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM tax_card where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND staff_no = '"+staffNo+"' order by staff_no");
            
            while (rSet1.next()) {
                
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
        
    }
    
    public java.lang.Object[] getListofStaffNos1() {
        
        java.lang.Object[] listofStaffNos1 = null;
        
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM tax_card where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date ASC");
            
            while (rSet1.next()) {
                
                listStaffNoVector1.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
        
    }
}





