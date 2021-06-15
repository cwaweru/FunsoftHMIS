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


public class HelbPdf implements java.lang.Runnable {
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    int numberSeq = 0;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void HelbPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate) {
        //public void OutstOrdersPdf(java.sql.Connection connDb) {
        
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
            //  com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    try {
                        
                        java.lang.Class.forName("org.postgresql.Driver");
                        
                    } catch(java.lang.ClassNotFoundException cnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    docPdf.open();
                    
  /*                  try {
   
   
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
                        phrase1 = new Phrase("S/NO  ",pFontHeader);
                        table1.addCell(phrase1);
   
                        phrase1 = new Phrase("PAYROLL NO",pFontHeader);
                        table1.addCell(phrase1);
                        table1.getDefaultCell().setColspan(1);
   
                        phrase1 = new Phrase("NAME OF LOANEE",pFontHeader);
                        table1.addCell(phrase1);
                        table1.getDefaultCell().setColspan(1);
   
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase1 = new Phrase("UNIVERSITY REG. NO",pFontHeader);
                        table1.addCell(phrase1);
   
   
                        phrase1 = new Phrase("I/D NO.",pFontHeader);
                        table1.addCell(phrase1);
   
                        phrase1 = new Phrase("PIN NO.",pFontHeader);
                        table1.addCell(phrase1);
   
                        phrase1 = new Phrase("AMOUNT DEDUCTED",pFontHeader);
                        table1.addCell(phrase1);
                        phrase1 = new Phrase("LOAN BALANCE",pFontHeader);
                        table1.addCell(phrase1);
   
                        try {
   
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
   
                        /*    for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st = connectDB.createStatement();
   
                                java.sql.Statement st2 = connectDB.createStatement();
   
   
                                java.sql.ResultSet rset = st.executeQuery("select distinct id_no,first_name||' '||middle_name||' '||last_name as name,nssf_no from master_file where employee_no = '"+listofStaffNos[j]+"' order by name");
   
                                java.sql.ResultSet rset1 = st2.executeQuery("SELECT sum(amount) from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND staff_no = '"+listofStaffNos[j]+"'  AND description = 'NSSF' group by staff_no");
   
                            }
   
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
   
                    }*/
                    
                    try {
                        
                        try {
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                            
                            int headerwidths[] = {5,10,50,15,15,15,15,15};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((105));
                            
                            table.setHeaderRows(1);
                            
                            table.getDefaultCell().setColspan(8);
                            
                            Phrase phrase;
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            java.sql.Statement st301 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset221 = st301.executeQuery("SELECT header_name from pb_header");
                            while (rset221.next()){
                                phrase = new Phrase(rset221.getString(1).toUpperCase(),pFontHeader);
                                table.addCell(phrase);
                            }
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("HELB LOAN MONTHLY REPAYMENT SCHEDULE",pFontHeader4);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                            
                            java.sql.Statement st30 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset22 = st30.executeQuery("SELECT hospital_name,postal_code||' '||box_no,pin_no,plot_no from pb_hospitalprofile");
                            while (rset22.next()){
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("NAME OF EMPLOYER : "+rset22.getString(1).toUpperCase(),pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("EMPLOYER PIN NO : "+rset22.getString(3),pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("POSTAL ADDRESS : " +rset22.getString(2),pFontHeader);
                                table.addCell(phrase);
                           
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("EMPLOYER HELB CODE NO : "+rset22.getString(4),pFontHeader);
                            table.addCell(phrase);
                            }
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("CHEQUE/CASH PAYMENT DETAILS :",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("                  ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("PAYROLL MONTH :",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("NO  ",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("PAY NO",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase("NAME OF LOANEE",pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase("UNIVERSITY REG. NO",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("I/D NO.",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("PIN NO.",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("AMOUNT DEDUCTED",pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("LOAN BALANCE",pFontHeader);
                            table.addCell(phrase);
                            
                            
                            double deduction = 0.00;
                            double balance = 0.00;
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st41 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset2 = st3.executeQuery("SELECT id_no,pin_no from master_file where employee_no ilike '"+listofStaffNos[j]+"'");
                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT staff_no,staff_name,sum(amount) from posting where (description ILIKE 'helb%' or description ilike 'h.e.l%') AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' and staff_no ilike '"+listofStaffNos[j]+"' GROUP BY staff_no,staff_name");
//                                java.sql.ResultSet rset41 = st41.executeQuery("SELECT sacco_no,balance from sacco_deductions where (sacco_name ILIKE 'helb%' or sacco_name ilike 'h.e.l%') and staff_no ilike '"+listofStaffNos[j]+"'");
                                
                                while (rset2.next()) {
                                    while (rset4.next()) {
//                                        while (rset41.next()) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            numberSeq = numberSeq +1;
                                            phrase = new Phrase(""+listofStaffNos[j]+"   ", pFontHeader);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset4.getObject(2).toString().toUpperCase(), pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(listofStaffNos[j].toString(), pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset2.getObject(1).toString(), pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset2.getObject(2).toString(), pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(3)),pFontHeader1);
                                            table.addCell(phrase);
                                            deduction = deduction + rset4.getDouble(3);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(3)),pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            balance = balance + rset4.getDouble(3);
                                            
//                                        }
                                    }
                                }
                                
                            }
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("      ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL DEDUCTIONS [KSHS]",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(deduction)),pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance)),pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("    ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(8);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("PAID BY CHEQUE NO:_____________________",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("    ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("CHEQUE DATE : ____/_____/______",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("PAID IN BY:______________________________",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("    ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("HELB RECEIPT NO ISSUED:________________",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("    ",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("DATE OF RECEIPT:____/____/_______",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase("RECEIPTED BY:__________________________",pFontHeader);
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
            
//            try {
//                
//                if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
//                    
//                    System.out.println(tempFile);
//                    
//                    wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
//                    
//                    
//                    // wait_for_Pdf2Show = rt.exec("xpdf "+tempFile+"");
//                    
//                    wait_for_Pdf2Show.waitFor();
//                    
//                } else {
//                    
//                    wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
//                    
//                    wait_for_Pdf2Show.waitFor();
//                    
//                }
//                
//            } catch(java.lang.InterruptedException intrExec) {
//                
//                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
//                
//            }
            
            
            
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT staff_no from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'helb%' or description ILIKE 'h.e.l%') order by staff_no");
            
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





