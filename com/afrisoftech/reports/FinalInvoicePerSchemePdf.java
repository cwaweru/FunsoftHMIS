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


public class FinalInvoicePerSchemePdf implements java.lang.Runnable {
    java.lang.String MNo1 = null;
    
    java.lang.String MNo = null;
    
    java.lang.String memNo = null;
    
    java.lang.String memNo1 = null;
    
    java.lang.String No = null;
    String ks;
    java.lang.String scheme = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
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
    public void FinalInvoicePerSchemePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate,java.lang.String combox,java.lang.String combox1) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        
        memNo1 = combox1;
        
        memNo = combox;
        
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
                
                java.lang.Thread.currentThread().sleep(2000);
                
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
                    
                    
                    String Address = null;
                    String Tel = null;
                    String compName = null;
                    String Fax = null;
                    String Email = null;
                    String date = null;
                    
                    try {
                        
                        java.sql.Statement st2x = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while(rset2x.next()){
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email||'   '||website,room_no from pb_hospitalprofile");
                        
                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        //   java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            Address = rset2.getObject(2).toString();
                            Tel = rset2.getObject(3).toString();
                            Fax = rset2.getObject(5).toString();
                            Email = rset2.getObject(6).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"\n Address: "+Address+"\n Tel: "+Tel+" \n Fax: "+Fax+"\n Email: "+Email+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.ALIGN_CENTER;
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    String Messg = null;
                    
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("select name from pb_notice");
                        
                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        //   java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            Messg = rset2.getObject(1).toString();
                        }
                        
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(""+Messg+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        
                        docPdf.setFooter(footer);
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        for (int j = 0; j < listofStaffNos.length; j++) {
                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                            
                            int headerwidths[] = {20,35,15,15,15,15};
                            
                            table.setWidths(headerwidths);
                            
                            table.setWidthPercentage((100));
                            
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setColspan(6);
                            
                            Phrase phrase = new Phrase();
                            
                            //  table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            System.out.println(listofStaffNos[j]);
                            try {
                                //-    for (int j = 0; j < listofStaffNos.length; j++) {
                                
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st31 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.ResultSet rset31 = st31.executeQuery("SELECT DISTINCT sp.scheme_name,sp.postal_code||' '||sp.address,sp.tel_main||' '||sp.other_tel,sp.main_fax from ac_schemes sp where sp.account_no = '"+memNo1+"'");
                                //java.sql.ResultSet rset4 = st4.executeQuery("select distinct dealer from ac_debtors where payee = '"+memNo+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rset1 = st1.executeQuery("select pc.date::date,initcap(pc.service) as service,pc.dosage,pc.reference,pc.debit from hp_patient_card pc where pc.invoice_no = '"+listofStaffNos[j]+"' AND pc.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pc.paid = true AND pc.debit <> 0 and pc.reference IS NOT NULL order by pc.date::date");
                                java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(db.debit) from ac_debtors db where db.invoice_no = '"+listofStaffNos[j]+"' AND db.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and db.account_no = '"+memNo1+"'");
                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT from ac_debtors where invoice_no = '"+listofStaffNos[j]+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"' and account_no = '"+memNo1+"'");
                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT DISTINCT invoice_no from ac_debtors where invoice_no = '"+listofStaffNos[j]+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and account_no = '"+memNo1+"' and invoice_no IS NOT NULL");
                                
                                double osBalance = 0.00;
                                double current = 0.00;
                                System.out.println(listofStaffNos[j]);
                                System.out.println("Step1 Complete");
                                
                                java.lang.Object listofStaffNos1[] = this.getListofStaffNos1(listofStaffNos[j]);
                                for (int k = 0; k < listofStaffNos1.length; k++) {
                                     java.sql.ResultSet rset5 = st5.executeQuery("select DISTINCT hp_patient_register.account_no,member_name from hp_patient_register,ac_debtors where patient_no = '"+listofStaffNos1[k]+"'");
                                     java.sql.ResultSet rset = st.executeQuery("select DISTINCT patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no from hp_patient_register where patient_no = '"+listofStaffNos1[k]+"' UNION ALL select patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no from hp_inpatient_register WHERE patient_no = '"+listofStaffNos1[k]+"'");
                              
                                
                                    table.getDefaultCell().setColspan(6);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase("FINAL INVOICE", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(3);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payer Name: -", pFontHeader1);
                                    
                                    //   phrase = new Phrase("Payer Name: "+dbObject.getDBObject(rset4.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(3);
                                    
                                    
                                    //  table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(" ", pFontHeader);
                                    //  phrase = new Phrase("Invoice No. ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(6);
                                    
                                    while (rset31.next()){
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Scheme Name: "+dbObject.getDBObject(rset31.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(3);
                                        
                                        table.getDefaultCell().setColspan(3);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Address.: "+dbObject.getDBObject(rset31.getObject(2), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        while (rset12.next()){
                                            table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Invoice Date.: "+dbObject.getDBObject(rset12.getObject(1), "-"), pFontHeader);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setColspan(6);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Tel.: "+dbObject.getDBObject(rset31.getObject(3), "-"), pFontHeader);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setColspan(3);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Fax.: "+dbObject.getDBObject(rset31.getObject(4), "-"), pFontHeader);
                                            
                                            table.addCell(phrase);
                                            while (rset11.next()){
                                                //  table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Invoice No.: "+dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                                //  phrase = new Phrase("Invoice No. ", pFontHeader);
                                                
                                                table.addCell(phrase);
                                                System.out.println("Step3 Complete");
                                                
                                                table.getDefaultCell().setColspan(3);
                                                while (rset5.next()){
                                                    
                                                    table.getDefaultCell().setColspan(3);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    // phrase = new Phrase("Member No.", pFontHeader);
                                                    
                                                    phrase = new Phrase("Member No.: "+dbObject.getDBObject(rset5.getObject(1), "-"), pFontHeader);
                                                    
                                                    table.addCell(phrase);
                                                    
                                                    System.out.println("Step4 Complete");
                                                    
                                                    
                                                    while (rset.next()){
                                                         
                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Patient Name.: "+dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader);
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        phrase = new Phrase("Member Name.: "+dbObject.getDBObject(rset5.getObject(2), "-"), pFontHeader);
                                                        //   phrase = new Phrase("Member Name  ", pFontHeader);
                                                        
                                                        table.addCell(phrase);
                                                        
                                                        
                                                        table.getDefaultCell().setColspan(3);
                                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                        //   phrase = new Phrase("Member Name  "+rset5.getString(2), pFontHeader);
                                                        phrase = new Phrase("Patient No.: "+dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader);
                                                        
                                                        table.addCell(phrase);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                    
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Date",pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Description",pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Qty",pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Ref",pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Amount",pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Balance"+ks,pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    while (rset1.next()){
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"),pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                                        // osBalance = osBalance + rset.getDouble(5);
                                        table.addCell(phrase);
                                        osBalance = osBalance + rset1.getDouble(5);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                        //   current = current + osBalance;
                                        
                                        table.addCell(phrase);
                                        
                                        
                                    }
                                    table.getDefaultCell().setColspan(3);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                    
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                    
                                    // while (rsetTotals.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("Invoice Amt.", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    //phrase = new Phrase(" ");
                                    
                                    
                                    
                                    //}
                                    
                                    docPdf.add(table);
                                    
                                } catch(java.sql.SQLException SqlExec) {
                                    
                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                    
                                }
                                boolean boolNewPage = docPdf.newPage();
                            }  // }
                            
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
                        
                        wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                        
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
                java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT invoice_no FROM ac_debtors WHERE date  BETWEEN ? AND ? AND ac_debtors.account_no = ? AND invoice_no ilike 'O%' order by invoice_no");
                
                //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct patient_no FROM hp_patient_register WHERE last_visit  BETWEEN ? AND ? AND pay_mode = ? order by patient_no");
                pSet1.setString(1,beginDate.toString());
                pSet1.setString(2,endDate.toString());
                //  pSet1.setString(3,"Raise Invoice");
                //  pSet1.setString(4,"''");
                pSet1.setString(3,memNo1);
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
        
        public java.lang.Object[] getListofStaffNos1(java.lang.Object invNo) {
            
            java.lang.Object[] listofStaffNos1 = null;
            
            java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
            
            
            try {
                
                //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT admission_no FROM ac_debtors WHERE invoice_no = ?");
                
                pSet1.setObject(1,invNo);
                java.sql.ResultSet rSet11 = pSet1.executeQuery();
                
                
                while (rSet11.next()) {
                    
                    listStaffNoVector1.addElement(rSet11.getObject(1).toString());
                    
                }
                
            }catch (java.sql.SQLException sqlExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
                
            }
            
            listofStaffNos1 = listStaffNoVector1.toArray();
            System.out.println("Done list of Staff Nos ...");
            return listofStaffNos1;
        }
        
        
    }
    
    
    
    
    
