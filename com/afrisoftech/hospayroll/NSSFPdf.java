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


public class NSSFPdf implements java.lang.Runnable {
    
    java.lang.String beginDate = null;
    
    java.lang.String bank = null;
    
    java.lang.String endDate = null;
    
    int numSeq = 0;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void NSSFPdf(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate,java.lang.String combox) {
        //public void OutstOrdersPdf(java.sql.Connection connDb) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        bank=combox;
        
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
                    
                    
                    
                    try {
                        
                        java.lang.Class.forName("org.postgresql.Driver");
                        
                    } catch(java.lang.ClassNotFoundException cnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {15,35,10,10,10,15};
                        
                        table2.setWidths(headerwidths);
                        
                        table2.setWidthPercentage((100));
                        
                        table2.setHeaderRows(1);
                        
                        //    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table2.getDefaultCell().setColspan(6);
                        
                        Phrase phrase2;
                        table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,nssf_no,postal_code||' '||box_no,main_telno from pb_hospitalprofile");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT institution_name,address,branch from statutory_institutions where (deduction_type = 'NSSF' OR deduction_type = 'N.S.S.F')");
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                
                                while (rset2.next()) {
                                    
                                    while (rset4.next()) {
                                        
                                        table2.getDefaultCell().setColspan(3);
                                        
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase("EMPLOYER'S NAME :     "+rset2.getObject(1).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        // table.getDefaultCell().setColspan(1);
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase(rset4.getObject(1).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        //   table.getDefaultCell().setColspan(1);
                                        
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase("EMPLOYER'S NUMBER : "+rset2.getObject(2).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        //   table.getDefaultCell().setColspan(1);
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase(rset4.getObject(2).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase("ADDRESS :         "+rset2.getObject(3).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase(rset4.getObject(3).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase("TEL No. :            "+rset2.getObject(4).toString(), pFontHeader);
                                        table2.addCell(phrase2);
                                        
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase2 = new Phrase("  " , pFontHeader);
                                        table2.addCell(phrase2);
                                    }
                                }
                            }
                            table2.getDefaultCell().setColspan(6);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase2 = new Phrase("STANDARD CONTRIBUTIONS RETURN FORM", pFontHeader);
                            table2.addCell(phrase2);
                            
                            table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table2.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                            
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase2 = new Phrase("1. FOR OFFICIAL USE ONLY", pFontHeader);
                            table2.addCell(phrase2);
                            
                            
                            docPdf.add(table2);
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {5,10,15,10,15,15,15,15};
                        
                        table1.setWidths(headerwidths);
                        
                        table1.setWidthPercentage((100));
                        
                        table1.setHeaderRows(1);
                        
                        table1.getDefaultCell().setColspan(8);
                        
                        Phrase phrase1;
                        
                        table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table1.getDefaultCell().setColspan(1);
                        
                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase1 = new Phrase("  ",pFontHeader);
                        table1.addCell(phrase1);
                        
                        phrase1 = new Phrase("BATCH NUMBER",pFontHeader);
                        table1.addCell(phrase1);
                        table1.getDefaultCell().setColspan(1);
                        
                        phrase1 = new Phrase("EMPLOYER NUMBER",pFontHeader);
                        table1.addCell(phrase1);
                        table1.getDefaultCell().setColspan(1);
                        
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase1 = new Phrase("NO. OF ENTRIES",pFontHeader);
                        table1.addCell(phrase1);
                        
                        
                        phrase1 = new Phrase("TOTAL VALUE (SHILLINGS)",pFontHeader);
                        table1.addCell(phrase1);
                        
                        phrase1 = new Phrase("SERIAL NUMBER",pFontHeader);
                        table1.addCell(phrase1);
                        
                        phrase1 = new Phrase("MONTH",pFontHeader);
                        table1.addCell(phrase1);
                        phrase1 = new Phrase("YEAR",pFontHeader);
                        table1.addCell(phrase1);
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,nssf_no,postal_code||' '||box_no,main_telno from pb_hospitalprofile");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(staff_no),sum(amount*2) from posting where (description ILIKE 'NSSF%' or description ilike 'N.s.s%') AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank+"' ");
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            
                            
                            while (rset2.next()) {
                                
                                while (rset4.next()) {
                                    
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("   ", pFontHeader1);
                                    table1.addCell(phrase1);
                                    // table.getDefaultCell().setColspan(1);
                                    
                                    //   table.getDefaultCell().setColspan(1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("   ", pFontHeader);
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset2.getObject(2).toString(), pFontHeader);
                                    table1.addCell(phrase1);
                                    //   table.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase(rset4.getObject(1).toString(), pFontHeader);
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase1 = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(2)),pFontHeader);
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("   ", pFontHeader);
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("    ", pFontHeader);
                                    table1.addCell(phrase1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase1 = new Phrase("  " , pFontHeader);
                                    table1.addCell(phrase1);
                                }
                            }
                            
                            
                            docPdf.add(table1);
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    try {
                        
                        double payee = 0;
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {7,15,35,10,10,10,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(1);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(7);
                        
                        Phrase phrase;
                        
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        phrase = new Phrase("No.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("ID NUMBER",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("EMPLOYEE'S NAME",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        
                        phrase = new Phrase("MEMBER NUMBER",pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("CONTRIBUTION",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("REMARKS",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rsetTotals = st3.executeQuery("SELECT SUM(amount*2) from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'NSSF%' or description ilike 'N.s.s.%') and company_name ilike '"+bank+"'");
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct id_no,first_name||' '||middle_name||' '||last_name as name,nssf_no from master_file where employee_no ILIKE '"+listofStaffNos[j]+"%' AND company_name ilike '"+bank+"' order by name");
                                
                                java.sql.ResultSet rset1 = st2.executeQuery("SELECT sum(amount*2) from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND staff_no = '"+listofStaffNos[j]+"'  AND (description ILIKE 'NSSF%' OR description ilike 'N.s.s.%') AND company_name ilike '"+bank+"' group by staff_no");
                                
                                
                                
                                while (rset.next()) {
                                    numSeq = numSeq + 1;
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase(""+numSeq,  pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(2).toString().toUpperCase(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(3).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(1);
                           
                                while (rset1.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontHeader1);
                                    payee=rset1.getDouble(1);
                                    table.addCell(phrase);
                                    
                                    
                                    
                                }
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ", pFontHeader1);
                                
                                table.addCell(phrase);
                            }
                            
                            while (rsetTotals.next()) {
                                
                                table.getDefaultCell().setColspan(4);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("STANDARD CONTRIBUTIONS", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(4);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("SPECIAL CONTRIBUTIONS & PENALTIES", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(4);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("CASH/CHEQUE NUMBER", pFontHeader1);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("    ", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);
                                table.addCell(phrase);
                            }
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("    ", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                            
                            table.getDefaultCell().setBorder(Rectangle.TOP);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Certified Correct By Company Authorised Officer", pFontHeader1);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("NAME ________________________________________________________________ ", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("SIGNATURE ________________________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("DESIGNATION _________________________________________________________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("DATE _____________________________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                        
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("NB: THIS FORM IS INVALID WITHOUT THE OFFICIAL RUBBER STAMP OF THE EMPLOYER", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("CASH OFFICE                                                   |  2. FOR OFFICIAL USE ONLY  |             COMPUTER DEPARTMENT", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("RECEIVED BY _________________________ DATE_________________ CHECKED BY________________________ DATE ______________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("                                      RETURNS SECTION______________________________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(7);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("CHECKED BY__________________________ DATE_______________ PUNCHED BY__________________________ DATE_______________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("                                                                                                                    PEELED BY____________________________ DATE_______________", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("FORMS SF/FA/A/24A                            ", pFontHeader1);
                            
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
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no,staff_name from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'NSSF%' or description ILIKE 'N.S.S.F%') and company_name ilike '"+bank+"' order by staff_name");
            
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





