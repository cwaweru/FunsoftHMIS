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


public class P9PerStaffPdf_1 implements java.lang.Runnable {
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
    
    double actual = 0.00;
    
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
    
    public void P9PerStaffPdf_1(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate, java.lang.String staffno) {
        
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
                                
                                java.sql.ResultSet rsetss = stm.executeQuery("SELECT UPPER(hospital_name),pin_no from pb_hospitalprofile");
                                
                                while (rsetss.next())
                                    table1.getDefaultCell().setColspan(1);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Employer's Name  :  " +rsetss.getObject(1).toString(),pFontHeader1);
                                
                                table1.addCell(phrase);
                                
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Employer's P.I.N.              : "+rsetss.getObject(2).toString(),pFontHeader1);
                                
                                table1.addCell(phrase);
                                
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
                                
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                java.sql.Statement st7 = connectDB.createStatement();
                                java.sql.Statement st8 = connectDB.createStatement();
                                java.sql.Statement st9 = connectDB.createStatement();
                                java.sql.Statement st10 = connectDB.createStatement();
                                java.sql.Statement st111 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                java.sql.Statement st11b = connectDB.createStatement();
                                java.sql.Statement st11c = connectDB.createStatement();
                                java.sql.Statement st11d = connectDB.createStatement();
                                java.sql.Statement st11e = connectDB.createStatement();
                                java.sql.Statement st11f = connectDB.createStatement();
                                java.sql.Statement st11g = connectDB.createStatement();
                                java.sql.Statement st11h = connectDB.createStatement();
                                java.sql.Statement st11i = connectDB.createStatement();
                                java.sql.Statement st11j = connectDB.createStatement();
                                java.sql.Statement st11k = connectDB.createStatement();
                                java.sql.Statement st11l = connectDB.createStatement();
                                java.sql.Statement st11m = connectDB.createStatement();
                                //java.sql.ResultSet rset11a = st11a.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by staff_no,date");
                                java.sql.ResultSet rset11b = st11b.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'jan%'");
                                java.sql.ResultSet rset11c = st11c.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'feb%'");
                                java.sql.ResultSet rset11d = st11d.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'mar%'");
                                java.sql.ResultSet rset11e = st11e.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'apr%'");
                                java.sql.ResultSet rset11f = st11f.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'may%'");
                                java.sql.ResultSet rset11g = st11g.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'jun%'");
                                java.sql.ResultSet rset11h = st11h.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'jul%'");
                                java.sql.ResultSet rset11i = st11i.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'aug%'");
                                java.sql.ResultSet rset11j = st11j.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'sep%'");
                                java.sql.ResultSet rset11k = st11k.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'oct%'");
                                java.sql.ResultSet rset11l = st11l.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'nov%'");
                                java.sql.ResultSet rset11m = st11m.executeQuery("select count(month) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'dec%'");
                                
                                java.sql.ResultSet rset1 = st1.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'jan%' order by staff_no,date");
                                java.sql.ResultSet rset21 = st21.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'feb%' order by staff_no,date");
                                
                                java.sql.ResultSet rset = st.executeQuery("select sum(basic_salary),sum(benefits_non_cash),sum(value_of_quarters),sum(total_gross_pay),sum(scheme_basic),sum(actual_contribution+retirement_contribution),sum(fixed_contribution),"+
                                "sum(owner_occupied_interest),sum(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),sum(chargeable_pay),sum(tax_charged),sum(personal_relief),sum(paye_tax) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' group by staff_no order by staff_no");
                                java.sql.ResultSet rset2 = st2.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'mar%'");
                                java.sql.ResultSet rset3 = st3.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'apr%'");
                                java.sql.ResultSet rset4 = st4.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'may%'");
                                java.sql.ResultSet rset5 = st5.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'jun%'");
                                java.sql.ResultSet rset6 = st6.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'july%'");
                                java.sql.ResultSet rset7 = st7.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'aug%'");
                                java.sql.ResultSet rset8 = st8.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'sep%'");
                                java.sql.ResultSet rset9 = st9.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'oct%'");
                                java.sql.ResultSet rset10 = st10.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'nov%'");
                                java.sql.ResultSet rset111 = st111.executeQuery("select month,basic_salary,benefits_non_cash,value_of_quarters,total_gross_pay,scheme_basic,(actual_contribution+retirement_contribution),fixed_contribution,owner_occupied_interest,"+
                                "(actual_contribution + owner_occupied_interest+retirement_contribution)::numeric(30,2),chargeable_pay,tax_charged,personal_relief,paye_tax from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and month ilike 'dec%'");
                                
                                while (rset11b.next()){
                                    jan = rset11b.getInt(1);
                                }
                                if (jan > 0){
                                    while (rset1.next()) {
                                        
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        phrase = new Phrase(rset1.getObject(1).toString().toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(2)), pFontHeader1);
                                        table.addCell(phrase);
                                        basic = rset1.getDouble(2);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(3)), pFontHeader1);
                                        table.addCell(phrase);
                                        non = rset1.getDouble(3);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)), pFontHeader1);
                                        table.addCell(phrase);
                                        valq = rset1.getDouble(4);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)), pFontHeader1);
                                        table.addCell(phrase);
                                        gross = rset1.getDouble(5);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(6)), pFontHeader1);
                                        table.addCell(phrase);
                                        actual = rset1.getDouble(6);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(7)), pFontHeader1);
                                        table.addCell(phrase);
                                        contri = rset1.getDouble(7);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(8)), pFontHeader1);
                                        table.addCell(phrase);
                                        fixed = rset1.getDouble(8);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(9)), pFontHeader1);
                                        table.addCell(phrase);
                                        owner = rset1.getDouble(9);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(10)), pFontHeader1);
                                        table.addCell(phrase);
                                        retir = rset1.getDouble(10);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(11)), pFontHeader1);
                                        table.addCell(phrase);
                                        chargable = rset1.getDouble(11);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(12)), pFontHeader1);
                                        table.addCell(phrase);
                                        tax = rset1.getDouble(12);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(13)), pFontHeader1);
                                        table.addCell(phrase);
                                        prelief = rset1.getDouble(12);
                                        
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(14)), pFontHeader1);
                                        table.addCell(phrase);
                                        paye = rset1.getDouble(14);
                                    }
                              /*  if (months.equalsIgnoreCase("january")){
                               
                                    table.getDefaultCell().setColspan(1);
                               
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               
                                    phrase = new Phrase(months, pFontHeader1);
                                    table.addCell(phrase);
                               
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(basic)), pFontHeader1);
                                    table.addCell(phrase);
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(non)), pFontHeader1);
                                    table.addCell(phrase);
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(valq)), pFontHeader1);
                                    table.addCell(phrase);
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(gross)), pFontHeader1);
                                    table.addCell(phrase);
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(contri)), pFontHeader1);
                                    table.addCell(phrase);
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actual)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(fixed)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(owner)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(retir)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(chargable)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tax)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(prelief)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(paye)), pFontHeader1);
                                    table.addCell(phrase);
                               
                               */
                                }else{
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase = new Phrase("JANUARY", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                }
                                    while (rset11c.next()){
                                        feb = rset11c.getInt(1);
                                    }
                                    if (feb > 0){
                                        while (rset21.next()){
                                            table.getDefaultCell().setColspan(1);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            
                                            phrase = new Phrase(rset21.getObject(1).toString().toUpperCase(), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(2)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(3)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(4)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(5)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(6)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(7)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(8)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(9)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(10)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(11)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(12)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(13)), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset21.getString(14)), pFontHeader1);
                                            table.addCell(phrase);
                                    /*
                                        table.getDefaultCell().setColspan(1);
                                     
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                     
                                        phrase = new Phrase(months, pFontHeader1);
                                        table.addCell(phrase);
                                     
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(basic)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(non)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(valq)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(gross)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(contri)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(actual)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(fixed)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(owner)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(retir)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(chargable)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tax)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(prelief)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(paye)), pFontHeader1);
                                        table.addCell(phrase);
                                     
                                     */
                                        }
                                    }else{
                                        
                                        //   while (rset1.next()) {
                                        
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        phrase = new Phrase("FEBRUARY", pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                         }
                                        while (rset11d.next()){
                                            mar = rset11d.getInt(1);
                                        }
                                        if (mar > 0){
                                            while (rset2.next()){
                                                table.getDefaultCell().setColspan(1);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                
                                                phrase = new Phrase(rset2.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(2)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(3)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(4)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(5)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(6)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(7)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(8)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(9)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(10)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(11)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(12)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(13)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(14)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                            }
                                            
                                        }else{
                                            
                                            //   while (rset1.next()) {
                                            
                                            table.getDefaultCell().setColspan(1);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            
                                            phrase = new Phrase("MARCH", pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            }
                                            while (rset11e.next()){
                                                april = rset11e.getInt(1);
                                            }
                                            if (april > 0){
                                                while (rset3.next()){
                                                    table.getDefaultCell().setColspan(1);
                                                    
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    
                                                    phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(2)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(3)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(4)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(5)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(6)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(7)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(8)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(9)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(10)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(11)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(12)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(13)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset3.getString(14)), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                }
                                            }else{
                                                
                                                //   while (rset1.next()) {
                                                
                                                table.getDefaultCell().setColspan(1);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                
                                                phrase = new Phrase("APRIL", pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                 }
                                                while (rset11f.next()){
                                                    may = rset11f.getInt(1);
                                                }
                                                if (may > 0){
                                                    while (rset4.next()){
                                                        table.getDefaultCell().setColspan(1);
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        
                                                        phrase = new Phrase(rset4.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(2)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(3)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(4)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(5)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(6)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(7)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(8)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(9)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(10)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(11)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(12)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(13)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(14)), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                    }
                                                }else{
                                                    
                                                    //   while (rset1.next()) {
                                                    
                                                    table.getDefaultCell().setColspan(1);
                                                    
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    
                                                    phrase = new Phrase("MAY", pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                    table.addCell(phrase);
                                                    
                                                     }
                                                    
                                                    while (rset11g.next()){
                                                        jun = rset11g.getInt(1);
                                                    }
                                                    if (jun > 0){
                                                        while (rset5.next()){
                                                            table.getDefaultCell().setColspan(1);
                                                            
                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                            
                                                            phrase = new Phrase(rset5.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(2)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(3)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(4)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(5)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(6)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(7)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(8)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(9)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(10)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(11)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(12)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(13)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(14)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                        }
                                                        
                                                    }else{
                                                        
                                                        //   while (rset1.next()) {
                                                        
                                                        table.getDefaultCell().setColspan(1);
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        
                                                        phrase = new Phrase("JUNE", pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                        table.addCell(phrase);
                                                    }
                                                        while (rset11h.next()){
                                                            jul = rset11h.getInt(1);
                                                        }
                                                        if (jul > 0){        while (rset6.next()){
                                                            table.getDefaultCell().setColspan(1);
                                                            
                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                            
                                                            phrase = new Phrase(rset6.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(2)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(3)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(4)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(5)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(6)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(7)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(8)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(9)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(10)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(11)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(12)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(13)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(14)), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                        }
                                                        
                                                        }else{
                                                            
                                                            //   while (rset1.next()) {
                                                            
                                                            table.getDefaultCell().setColspan(1);
                                                            
                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                            
                                                            phrase = new Phrase("JULY", pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                            
                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                            table.addCell(phrase);
                                                        }
                                                            while (rset11i.next()){
                                                                aug = rset11i.getInt(1);
                                                            }
                                                            if (aug > 0){
                                                                while (rset7.next()){
                                                                    table.getDefaultCell().setColspan(1);
                                                                    
                                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                    
                                                                    phrase = new Phrase(rset7.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(2)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(3)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(4)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(5)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(6)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(7)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(8)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(9)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(10)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(11)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(12)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(13)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(14)), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                }
                                                                
                                                            }else{
                                                                
                                                                //   while (rset1.next()) {
                                                                
                                                                table.getDefaultCell().setColspan(1);
                                                                
                                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                
                                                                phrase = new Phrase("AUGUST", pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                                
                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                table.addCell(phrase);
                                                            }
                                                                while (rset11j.next()){
                                                                    sep = rset11j.getInt(1);
                                                                }
                                                                if (sep> 0){
                                                                    while (rset8.next()){
                                                                        table.getDefaultCell().setColspan(1);
                                                                        
                                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                        
                                                                        phrase = new Phrase(rset8.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(2)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(3)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(4)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(5)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(6)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(7)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(8)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(9)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(10)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(11)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(12)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(13)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(14)), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                    }
                                                                    
                                                                }else{
                                                                    
                                                                    //   while (rset1.next()) {
                                                                    
                                                                    table.getDefaultCell().setColspan(1);
                                                                    
                                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                    
                                                                    phrase = new Phrase("SEPTEMBER", pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                    
                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                    table.addCell(phrase);
                                                                }
                                                                    while (rset11k.next()){
                                                                        oct = rset11k.getInt(1);
                                                                    }
                                                                    if (oct > 0){
                                                                        while (rset9.next()){
                                                                            table.getDefaultCell().setColspan(1);
                                                                            
                                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                            
                                                                            phrase = new Phrase(rset9.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(2)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(3)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(4)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(5)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(6)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(7)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(8)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(9)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(10)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(11)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(12)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(13)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(14)), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                        }
                                                                    }else{
                                                                        
                                                                        //   while (rset1.next()) {
                                                                        
                                                                        table.getDefaultCell().setColspan(1);
                                                                        
                                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                        
                                                                        phrase = new Phrase("OCTOBER", pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                        
                                                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                        table.addCell(phrase);
                                                                    }
                                                                        while (rset11l.next()){
                                                                            nov = rset11l.getInt(1);
                                                                        }
                                                                        if (nov > 0){
                                                                            
                                                                            while (rset10.next()){
                                                                                table.getDefaultCell().setColspan(1);
                                                                                
                                                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                                
                                                                                phrase = new Phrase(rset10.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(2)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(3)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(4)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(5)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(6)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(7)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(8)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(9)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(10)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(11)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(12)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(13)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset10.getString(14)), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                            }
                                                                        }else{
                                                                            
                                                                            //   while (rset1.next()) {
                                                                            
                                                                            table.getDefaultCell().setColspan(1);
                                                                            
                                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                            
                                                                            phrase = new Phrase("NOVEMBER", pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                            
                                                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                            table.addCell(phrase);
                                                                        }
                                                                            while (rset11m.next()){
                                                                                dec = rset11m.getInt(1);
                                                                            }
                                                                        
                                                                            if (dec > 0){
                                                                                
                                                                                while (rset111.next()){
                                                                                    table.getDefaultCell().setColspan(1);
                                                                                    
                                                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                                    
                                                                                    phrase = new Phrase(rset111.getObject(1).toString().toUpperCase(), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(2)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(3)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(4)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(5)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(6)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(7)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(8)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(9)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(10)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(11)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(12)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(13)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(14)), pFontHeader1);
                                                                                    table.addCell(phrase);
                                                                                    
                                                                                }
                                                                            }else{
                                                                                
                                                                                //   while (rset1.next()) {
                                                                                
                                                                                table.getDefaultCell().setColspan(1);
                                                                                
                                                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                                                
                                                                                phrase = new Phrase("DECEMBER", pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                                                                table.addCell(phrase);
                                                                                
                                                                                
                                                                            }
                                                                            
                                                                      //  }
                                                                   
                                while (rset.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    phrase = new Phrase("TOTALS", pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(2)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(4)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(5)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(6)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(7)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(8)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(9)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(10)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(11)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(12)), pFontHeader2);
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(13)), pFontHeader2);
                                    table.addCell(phrase);
                                }
                                
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
                                        
                                        java.sql.ResultSet rsetA = stA.executeQuery("select sum(paye_tax),sum(chargeable_pay) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' group by staff_no order by staff_no");
                                        java.sql.ResultSet rsetB = stB.executeQuery("select sum(chargeable_pay) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' group by staff_no order by staff_no");
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT month FROM tax_card where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by month");
            
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





