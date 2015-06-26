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


public class SummaryPerDepartPdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
    java.lang.String bank = null;
    java.util.Date endDate = null;
    
    int numberSeq = 0;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    double grossTt = 0.00;
    double netTt = 0.00;
    double otherTt = 0.00;
    double payeeTt = 0.00;
    double nssfTt = 0.00;
    double nhifTt = 0.00;
    double unionTt = 0.00;
    double advanceTt = 0.00;
    //  double deduction1 = 0.00;
    double earningsTt = 0.00;
    double deductionTt = 0.00;
    double basicTt = 0.00;
    double overTt = 0.00;
    double nonTt = 0.00;
    
    
    boolean threadCheck = true;
    Phrase phraseA;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader21 = FontFactory.getFont(FontFactory.TIMES,9, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES,9, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void SummaryPerDepartPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        //   bank = combox;
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    try{
                        java.sql.Statement st = connectDB.createStatement();
                        
                        java.sql.ResultSet rset = st.executeQuery("select hospital_name,TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI') from pb_hospitalprofile");
                        while (rset.next()){
                            compName = rset.getString(1);
                            date =rset.getString(2);
                        }
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("PAYROLL SUMMARY PERIOD ENDING : "  +endDate +"\n "+compName.toUpperCase()+ "  "     +date,pFontHeader21),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    
                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    // headerFoter.setBorderColor(java.awt.Color.WHITE);
                    docPdf.setHeader(headerFoter);
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Payroll Summary - Page : ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        //  for (int j = 0; j < listofStaffNos.length; j++) {
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(15);
                        
                        int headerwidths[] = {5,7,18,8,7,7,7,8,7,7,7,7,7,7,8};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((105));
                        table.setHeaderRows(1);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM |Rectangle.TOP);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        table.getDefaultCell().setColspan(15);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        // table.addCell(phrase);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT| Rectangle.RIGHT |Rectangle.TOP);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        //  table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        phrase = new Phrase("No".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Staff No".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Employee Name".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Basic".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Overtime".toUpperCase(),pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Non Cash".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        //  table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Other Income".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        //      table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("Gross Pay".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Advance".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("P.A.Y.E",pFontHeader2);
                        table.addCell(phrase);
                        phrase = new Phrase("N.S.S.F",pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("N.H.I.F",pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Prov. Fund/ Union".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Other Deductions".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Net".toUpperCase(),pFontHeader2);
                        table.addCell(phrase);
                        
                        
                        try {
                            java.lang.Object listofDepart[] = this.getListofStaffDepartment();
                            for (int x = 0; x < listofDepart.length;  x++) {
                                double gross = 0.00;
                                double net = 0.00;
                                double other = 0.00;
                                double payee = 0.00;
                                double nssf = 0.00;
                                double nhif = 0.00;
                                double union = 0.00;
                                double advance = 0.00;
                                double deduction = 0.00;
                                double earnings = 0.00;
                                double totaldeduction = 0.00;
                                double basic = 0.00;
                                double overtime = 0.00;
                                double noncash = 0.00;
                                
                                
                                double gross1 = 0.00;
                                double net1 = 0.00;
                                double other1 = 0.00;
                                double payee1 = 0.00;
                                double nssf1 = 0.00;
                                double nhif1 = 0.00;
                                double union1 = 0.00;
                                double advance1 = 0.00;
                                double deduction1 = 0.00;
                                double earnings1 = 0.00;
                                double totaldeduction1 = 0.00;
                                double basic1 = 0.00;
                                double overtime1 = 0.00;
                                double noncash1 = 0.00;
                                
                                int over = 0;
                                int non = 0;
                                int incom = 0;
                                int adv = 0;
                                int paye = 0;
                                int nss = 0;
                                int nhi = 0;
                                int prov = 0;
                                int otded = 0;
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(15);
                                phrase = new Phrase(listofDepart[x].toString().toUpperCase(), pFontHeader2);
                                table.addCell(phrase);
                                
                                java.lang.Object listofStaffNos[] = this.getListofStaffNos(listofDepart[x]);
                                
                                
                                for (int j = 0; j < listofStaffNos.length; j++) {
                                    int basis = 0;
                                    java.sql.Statement st = connectDB.createStatement();
                                    java.sql.Statement st1 = connectDB.createStatement();
                                    java.sql.Statement st2 = connectDB.createStatement();
                                    java.sql.Statement st3 = connectDB.createStatement();
                                    java.sql.Statement st4 = connectDB.createStatement();
                                    java.sql.Statement st5 = connectDB.createStatement();
                                    java.sql.Statement st6 = connectDB.createStatement();
                                    java.sql.Statement st7 = connectDB.createStatement();
                                    java.sql.Statement st8 = connectDB.createStatement();
                                    java.sql.Statement st9 = connectDB.createStatement();
                                    java.sql.Statement st10 = connectDB.createStatement();
                                    
                                    java.sql.Statement st01 = connectDB.createStatement();
                                    java.sql.Statement st11 = connectDB.createStatement();
                                    java.sql.Statement st21 = connectDB.createStatement();
                                    java.sql.Statement st31 = connectDB.createStatement();
                                    java.sql.Statement st41 = connectDB.createStatement();
                                    java.sql.Statement st51 = connectDB.createStatement();
                                    java.sql.Statement st61 = connectDB.createStatement();
                                    java.sql.Statement st91 = connectDB.createStatement();
                                    java.sql.Statement st81 = connectDB.createStatement();
                                    java.sql.Statement st12 = connectDB.createStatement();
                                    java.sql.Statement st12x = connectDB.createStatement();
                                    java.sql.ResultSet rset01 = st01.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and description ILIKE 'Over%'");
                                    java.sql.ResultSet rset21 = st21.executeQuery("select count(staff_no) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                    java.sql.ResultSet rset31 = st31.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (description NOT ILIKE 'Over%' OR description NOT ILIKE 'basic%' OR allowance_deduction NOT ILIKE 'non Cash%' AND allowance_deduction ILIKE 'Earning%')");
                                    java.sql.ResultSet rset41 = st41.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description ILIKE 'Advance%'");
                                    java.sql.ResultSet rset51 = st51.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (description ILIKE 'P.A.Y.E%' OR description ILIKE 'PAYE%')");
                                    java.sql.ResultSet rset61 = st61.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'N.s.s.f%' OR description ILIKE 'nssf%')");
                                    java.sql.ResultSet rset81 = st81.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (description ILIKE 'N.H.I.F%' OR description ILIKE 'nhif%')");
                                    java.sql.ResultSet rset91 = st91.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'Provident%' OR description ILIKE 'provident%' OR description ILIKE 'UNION%' OR description ILIKE 'U.N.I.O%')");
                                    //java.sql.ResultSet rset1 = st1.executeQuery("select staff_no,staff_name,sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description ILIKE 'basic Pay%' group by staff_no,staff_name");
                                    
                                    java.sql.ResultSet rset1 = st1.executeQuery("select employee_no,first_name||' '||middle_name||' '||last_name from master_file WHERE employee_no = '"+listofStaffNos[j]+"'");
                                    java.sql.ResultSet rset12x = st12x.executeQuery("select count(staff_no) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description ILIKE 'basic Pay%' group by staff_no");
                                    
                                    java.sql.ResultSet rset12 = st12.executeQuery("select staff_no,staff_name,sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description ILIKE 'basic Pay%' group by staff_no,staff_name");
                                    
                                    java.sql.ResultSet rset = st.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and description ILIKE 'Over%'");
                                    java.sql.ResultSet rset2 = st2.executeQuery("select sum(benefits_non_cash) from tax_card WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                    java.sql.ResultSet rset3 = st3.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (description NOT ILIKE 'Over%' OR description NOT ILIKE 'basic%' OR allowance_deduction NOT ILIKE 'non Cash%' AND allowance_deduction ILIKE 'Earning%')");
                                    java.sql.ResultSet rset4 = st4.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND description ILIKE 'Advance%'");
                                    java.sql.ResultSet rset5 = st5.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (description ILIKE 'P.A.Y.E%' OR description ILIKE 'PAYE%')");
                                    java.sql.ResultSet rset6 = st6.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'N.s.s.f%' OR description ILIKE 'nssf%')");
                                    java.sql.ResultSet rset8 = st8.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (description ILIKE 'N.H.I.F%' OR description ILIKE 'nhif%')");
                                    java.sql.ResultSet rset9 = st9.executeQuery("select SUM(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'Provident%' OR description ILIKE 'provident%' OR description ILIKE 'UNION%' OR description ILIKE 'U.N.I.O%')");
                                    java.sql.ResultSet rset7 = st7.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and (allowance_deduction  ILIKE 'deduction%' OR allowance_deduction ILIKE 'less relief%')");
                                    java.sql.ResultSet rset10 = st10.executeQuery("select sum(amount) from posting WHERE staff_no = '"+listofStaffNos[j]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and allowance_deduction ILIKE 'Earning%'");
                                    
                                    
                                   while (rset1.next()) {
                                        
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        
                                        numberSeq = numberSeq+1;
                                        
                                        phrase = new Phrase(""+numberSeq+"   ", pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        
                                        phrase = new Phrase(rset1.getObject(1).toString().toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(rset1.getObject(2).toString().toUpperCase(), pFontHeader11);
                                        table.addCell(phrase);
                                        while(rset12x.next()){
                                            basis = rset12x.getInt(1);
                                        }
                                        if(basis > 0){
                                            while(rset12.next()){
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                basic = rset12.getDouble(3);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(basic)), pFontHeader1);
                                                table.addCell(phrase);
                                                
                                                basic1 = basic1 + rset12.getDouble(3);
                                            }
                                        }else{
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)), pFontHeader1);
                                            table.addCell(phrase);
                                            basic = 0;
                                            basic1 = basic1 + 0;
                                        }
                                    }
                                   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT); 
                                    while (rset01.next()) {
                                        over = rset01.getInt(1);
                                    }
                                    if (over > 0){
                                        while (rset.next()) {
                                            
                                            overtime = rset.getDouble(1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(overtime)), pFontHeader1);
                                            table.addCell(phrase);
                                            overtime1 = overtime1 + rset.getDouble(1);
                                        }
                                    }else{
                                        overtime = 0;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(overtime)), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                    }
                                    
                                    while (rset21.next()) {
                                        non = rset21.getInt(1);
                                    }
                                    if (non > 0){
                                        while (rset2.next()){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(1)), pFontHeader1);
                                            table.addCell(phrase);
                                            noncash1 = noncash1 + rset2.getDouble(1);
                                        }
                                    }else{
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency("0.00"), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    
                                    
                                    while (rset10.next()) {
                                        
                                        earnings = rset10.getDouble(1);
                                        other = earnings - (basic+overtime);
                                        other1 = other1 + other;
                                    }
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(other)), pFontHeader1);
                                    table.addCell(phrase);
                                    gross = earnings;
                                    gross1 = gross1 + earnings;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(gross)), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    while (rset41.next()) {
                                        adv = rset41.getInt(1);
                                    }
                                    if (adv > 0){
                                        while (rset4.next()) {
                                            
                                            advance = rset4.getDouble(1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(advance)), pFontHeader1);
                                            table.addCell(phrase);
                                            advance1 = advance1 + advance;
                                        }
                                    }else{
                                        advance = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(advance)), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    
                                    while (rset51.next()) {
                                        paye = rset51.getInt(1);
                                    }
                                    if (paye > 0){
                                        
                                        while (rset5.next()) {
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset5.getString(1)), pFontHeader1);
                                            table.addCell(phrase);
                                            payee = rset5.getDouble(1);
                                            payee1 = payee1 + rset5.getDouble(1);
                                        }
                                    } else {
                                        payee = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(payee)), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                    }
                                    while (rset61.next()) {
                                        nss = rset61.getInt(1);
                                    }
                                    if (nss > 0){
                                        
                                        while (rset6.next()) {
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(1)), pFontHeader1);
                                            table.addCell(phrase);
                                            nssf = rset6.getDouble(1);
                                            nssf1 = nssf1 + nssf;
                                        }
                                    }else{
                                        nssf = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nssf)), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    
                                    while (rset81.next()) {
                                        nhi = rset81.getInt(1);
                                    }
                                    if (nhi > 0){
                                        while (rset8.next()) {
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset8.getString(1)), pFontHeader1);
                                            table.addCell(phrase);
                                            nhif = rset8.getDouble(1);
                                            nhif1 = nhif1 + nhif;
                                        }
                                    }else{
                                        nhif = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nhif)), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    
                                    while (rset91.next()) {
                                        prov = rset91.getInt(1);
                                    }
                                    if (prov > 0){
                                        while (rset9.next()) {
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset9.getString(1)), pFontHeader1);
                                            table.addCell(phrase);
                                            union = rset9.getDouble(1);
                                            union1 = union1 + union;
                                        }
                                    }else{
                                        union = 0.00;
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(union)), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    
                                    
                                    while (rset7.next()) {
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(1)), pFontHeader1);
                                        // table.addCell(phrase);
                                        totaldeduction = rset7.getDouble(1);
                                        
                                    }
                                    
                                    deduction = totaldeduction - (payee+union+nhif+nssf+advance);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deduction)), pFontHeader1);
                                    table.addCell(phrase);
                                    totaldeduction1 = totaldeduction1 + deduction;
                                    
                                    net = earnings - totaldeduction;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(net)), pFontHeader1);
                                    table.addCell(phrase);
                                    net1 = net1 + net;
                                }
                                
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                
                                phrase = new Phrase("TOTALS", pFontHeader2);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(basic1)), pFontHeader2);
                                table.addCell(phrase);
                                basicTt = basicTt + basic1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(overtime1)), pFontHeader2);
                                table.addCell(phrase);
                                overTt = overTt + overtime1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(noncash1)), pFontHeader2);
                                table.addCell(phrase);
                                nonTt = nonTt + noncash1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(other1)), pFontHeader2);
                                table.addCell(phrase);
                                otherTt = otherTt + other1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(gross1)), pFontHeader2);
                                table.addCell(phrase);
                                grossTt = grossTt + gross1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(advance1)), pFontHeader2);
                                table.addCell(phrase);
                                advanceTt = advanceTt + advance1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(payee1)), pFontHeader2);
                                table.addCell(phrase);
                                payeeTt = payeeTt + payee1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nssf1)), pFontHeader2);
                                table.addCell(phrase);
                                nssfTt = nssfTt + nssf1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nhif1)), pFontHeader2);
                                table.addCell(phrase);
                                nhifTt = nhifTt + nhif1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(union1)), pFontHeader2);
                                table.addCell(phrase);
                                unionTt = unionTt + union1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totaldeduction1)), pFontHeader2);
                                table.addCell(phrase);
                                deductionTt = deductionTt + totaldeduction1;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(net1)), pFontHeader2);
                                table.addCell(phrase);
                                netTt = netTt + net1;
                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(13)), pFontHeader2);
                                //   table.addCell(phrase);
                                
                            }
                            
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            phrase = new Phrase("NET TOTALS", pFontHeader2);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(basicTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(overTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nonTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(otherTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(grossTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(advanceTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(payeeTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nssfTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nhifTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(unionTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deductionTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(netTt)), pFontHeader2);
                            table.addCell(phrase);
                            
                            
                            //  }
                            docPdf.add(table);
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                        
                    }
                    //
                    
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
    public java.lang.Object[] getListofStaffDepartment() {
        
        java.lang.Object[] listofStaffDepartment = null;
        
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT department FROM  master_file ORDER BY department");
            
            while (rSet1.next()) {
                
                listStaffNoVector1.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofStaffDepartment = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffDepartment;
        
        
        
    }
    
    public java.lang.Object[] getListofStaffNos(java.lang.Object dept) {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM  posting, master_file WHERE posting.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND master_file.department = '"+dept+"' and posting.staff_no = master_file.employee_no order by posting.staff_no");
            
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
}





