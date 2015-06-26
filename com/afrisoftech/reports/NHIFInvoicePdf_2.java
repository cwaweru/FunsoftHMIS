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


public class NHIFInvoicePdf_2 implements java.lang.Runnable {
    java.lang.String MNo = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    String ks;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    //    double osBalance = 0.00;
    //   double current = 0.00;
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void NHIFInvoicePdf(java.sql.Connection connDb, java.lang.String name, java.util.Date begindate, java.util.Date endate) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        MNo = name;
        
        beginDate = begindate;
        
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf(MNo);
            
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
    
    
    public void generatePdf(java.lang.String memNo) {
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        //   java.lang.Object listofStaffNos1[] = this.getListofStaffNos1();
        
        //    for (int k = 0; k < listofStaffNos1.length; k++) {
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf1 = new com.lowagie.text.Document();
            try {
                
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    
                    docPdf.open();
                    java.lang.Object listofStaffNos1[] = this.getListofStaffNos1();
                    
                    for (int k = 0; k < listofStaffNos1.length; k++) {
                        
                        
                        try {
                            java.lang.String invoice = null;
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                            
                            int headerwidths[] = {15,35,2,6,21,21};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            
                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            
                            table.getDefaultCell().setColspan(6);
                            
                            Phrase phrase = new Phrase();
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            System.out.println("StepA Complete");
                            try {
                                //-    for (int j = 0; j < listofStaffNos.length; j++) {
                                
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.Statement st111 = connectDB.createStatement();
                                java.sql.Statement stA = connectDB.createStatement();
                                java.sql.Statement stnh = connectDB.createStatement();
                                java.sql.Statement st1111 = connectDB.createStatement();
                                java.sql.Statement st2x = connectDB.createStatement();
                                
                                java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                                while(rset2x.next()){
                                    ks = rset2x.getObject(1).toString();
                                }
                                java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno from pb_hospitalprofile");
                                java.sql.ResultSet rsetA = stA.executeQuery("select DISTINCT code from pb_nssf_rebeats");
                                java.sql.ResultSet rset = st.executeQuery("select patient_no,initcap(first_name||' '||second_name||' '||last_name),address,adm_date,discharge_date from hp_inpatient_register where patient_no = '"+MNo+"'");
                                java.sql.ResultSet rset111 = st111.executeQuery("select disease from hp_patient_discharge where patient_no = '"+MNo+"' ORDER BY discharge_date DESC LIMIT 1");
                                java.sql.ResultSet rset1111 = st1111.executeQuery("select discharge_date-adm_date from hp_inpatient_register where patient_no = '"+MNo+"'");
                                
                                java.sql.ResultSet rset12 = st12.executeQuery("select distinct date::date from hp_patient_card where patient_no = '"+MNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND paid = true and reference IS NOT NULL");
                                java.sql.ResultSet rset11 = st11.executeQuery("select distinct invoice_no from hp_patient_card where patient_no = '"+MNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND paid = true and reference IS NOT NULL");
                                
                                double osBalance = 0.00;
                                double current = 0.00;
                                double patient = 0.00;
                                double paid = 0.00;
                                System.out.println("Step1 Complete");
                                while (rset3.next()){
                                    while (rset.next()){
                                        while (rset11.next()){
                                            while (rsetA.next()){
                                                while (rset111.next()){
                                                    while (rset1111.next()){
                                                        table.getDefaultCell().setColspan(6);
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                        phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader2);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        table.getDefaultCell().setColspan(6);
                                                        table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                                        
                                                        phrase = new Phrase("P.O. Box : "+rset3.getObject(2).toString(), pFontHeader);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase("Tel : "+rset3.getObject(3).toString(), pFontHeader);
                                                        
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        table.getDefaultCell().setColspan(6);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                        phrase = new Phrase("N.H.I.F INVOICE", pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        
                                                        //                          while (rset11.next())
                                                        
                                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                                        
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Serial No. C"+rset11.getString(1).toUpperCase(), pFontHeader1);
                                                        invoice = rset11.getString(1);
                                                        
                                                        table.addCell(phrase);
                                                        
                                                        //                            while (rset.next())
                                                        table.getDefaultCell().setColspan(3);
                                                        
                                                        System.out.println("Step3 Complete");
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        
                                                        System.out.println("Step4 Complete");
                                                        
                                                        
                                                        //                            while (rsetA.next())
                                                        
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("CODE NO : " +dbObject.getDBObject(rsetA.getObject(1), "-"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        //                            while (rset.next())
                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Patient No : " +dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                                        
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Name : " +dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        table.getDefaultCell().setColspan(6);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Address : " +dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                                        
                                                        table.addCell(phrase);
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        
                                                        phrase = new Phrase("D.O.A :  " +dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                                        
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase("D.O.D  :  " +dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        phrase = new Phrase("Diagnosis  :  " +dbObject.getDBObject(rset111.getObject(1), "-"), pFontHeader1);
                                                        table.addCell(phrase);
                                                        
                                                        phrase = new Phrase("Days Admitted :   " +dbObject.getDBObject(rset1111.getObject(1), "-"), pFontHeader1);
                                                        table.addCell(phrase);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                docPdf.add(table);
                                
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            //    boolean boolNewPage = docPdf.newPage();
                            //}  // }
                            
                        } catch(com.lowagie.text.BadElementException BadElExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                            
                        }
                        
                        try {
                            java.lang.String invoice = null;
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                            
                            int headerwidths[] = {15,35,2,6,21,21};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            table.setHeaderRows(1);
                            
                            table.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                            
                            table.getDefaultCell().setColspan(6);
                            
                            Phrase phrase = new Phrase();
                            
                            //  table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            System.out.println("StepA Complete");
                            try {
                                //-    for (int j = 0; j < listofStaffNos.length; j++) {
                                
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.Statement st111 = connectDB.createStatement();
                                java.sql.Statement stA = connectDB.createStatement();
                                java.sql.Statement stnh = connectDB.createStatement();
                                
                                java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit-credit) from hp_patient_card where patient_no = '"+MNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                
                                double osBalance = 0.00;
                                double current = 0.00;
                                double patient = 0.00;
                                double paid = 0.00;
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Date",pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Description",pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Qty",pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Price @",pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total "+ks,pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(6);
                                
                                for (int j = 0; j < listofStaffNos.length; j++) {
                                    //      java.sql.ResultSet rset1 = st1.executeQuery("select date::date,initcap(service) as service,dosage,(debit-credit)/dosage,debit-credit from hp_patient_card where patient_no = '"+MNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and main_service = '"+listofStaffNos[j]+"' order by date::date");
                                    
                                    java.sql.ResultSet rset1 = st1.executeQuery("select date::date,initcap(service) as service,dosage,(debit)/dosage,debit from hp_patient_card where patient_no = '"+MNo+"' and main_service = '"+listofStaffNos[j]+"' AND debit >0 AND invoice_no = '"+listofStaffNos1[k]+"' UNION ALL select date::date,initcap(service) as service,dosage,(credit)/dosage,-credit from hp_patient_card where patient_no = '"+MNo+"' and main_service = '"+listofStaffNos[j]+"' AND credit >0 AND invoice_no = '"+listofStaffNos1[k]+"' AND transaction_type ILIKE 'Billing%' order by date::date");
                                    table.getDefaultCell().setColspan(6);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(listofStaffNos[j].toString().toUpperCase(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    while (rset1.next()){
                                        
                                        
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(4)),pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        osBalance = osBalance + rset1.getDouble(5);
                                        
                                    }
                                }
                                table.getDefaultCell().setColspan(6);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                                while (rsetTotals.next()) {
                                    
                                    table.getDefaultCell().setColspan(2);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Bill Amt.", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    //phrase = new Phrase(" ");
                                    
                                    
                                    
                                }
                                
                                java.sql.ResultSet rsetnh = stnh.executeQuery("select distinct sum(credit) from hp_patient_card where patient_no = '"+MNo+"' AND reference IS NOT NULL AND isurer = 'N.H.I.F' AND invoice_no = '"+listofStaffNos1[k]+"'");
                                //    java.sql.ResultSet rsetnh = stnh.executeQuery("select distinct sum(debit-credit) from ac_debtors where admission_no = '"+MNo+"' AND dealer = 'N.H.I.F' AND date between '"+beginDate+"' AND '"+endDate+"'");
                                
                                
                                while (rsetnh.next()) {
                                    
                                    table.getDefaultCell().setColspan(3);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(2);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("NHIF To Pay :", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetnh.getString(1)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(3);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(2);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Balance :", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    paid = osBalance - rsetnh.getDouble(1);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(paid)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    
                                }
                                
                                //  }
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("PREPARED BY :", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("BILLING CLERK :.................................................................", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("HOSP. ADM :................................................................", pFontHeader);
                                
                                table.addCell(phrase);
         /*
                            table.getDefaultCell().setColspan(3);
          
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("NAME :..................................................................................", pFontHeader);
          
                            table.addCell(phrase);
          
                          table.getDefaultCell().setColspan(3);
          
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("HOSP. ADM :.........................................................................", pFontHeader);
          
                            table.addCell(phrase);
          */
                                table.getDefaultCell().setColspan(3);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("SIGNATURE :.......................................................................", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("SIGNATURE :...............................................................", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("DATE :..................................................................................", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(3);
                                
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("DATE :...........................................................................", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                
                                docPdf.add(table);
                                boolean boolNewPage = docPdf.newPage();
                                
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            // }
                            
                        } catch(com.lowagie.text.BadElementException BadElExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                            
                        }
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
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct main_service FROM hp_patient_card WHERE date::date BETWEEN ? AND ? AND patient_no = '"+MNo+"' and debit > 0 order by main_service");
            pSet1.setString(1,beginDate.toString());
            pSet1.setString(2,endDate.toString());
            //  pSet1.setString(3,"Scheme");
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            // java.sql.Statement stmt1 = connectDB.createStatement();
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' order by patient_no");
            
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
    // }
    
    public java.lang.Object[] getListofStaffNos1() {
        
        java.lang.Object[] listofStaffNos1 = null;
        
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct invoice_no FROM hp_patient_card WHERE date::date BETWEEN ? AND ? AND patient_no = '"+MNo+"' order by invoice_no");
            pSet1.setString(1,beginDate.toString());
            pSet1.setString(2,endDate.toString());
            //  pSet1.setString(3,"Scheme");
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            // java.sql.Statement stmt1 = connectDB.createStatement();
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' order by patient_no");
            
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





