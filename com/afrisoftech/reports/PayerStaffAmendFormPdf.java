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


public class PayerStaffAmendFormPdf implements java.lang.Runnable {
    java.lang.String memNo = null;
    
    java.lang.String scheme = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    String ks;
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void PayerStaffAmendFormPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate,java.lang.String combox) {
        
        memNo = combox;
        
        //scheme = schecombox;
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        
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
            
            this.generatePdf(memNo);
            
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
                    
                    
                    String compName = null;
                    String date = null;
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Membership Amendment Report - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {12,10,20,10,10,12,20};
                        
                        table.setWidths(headerwidths);
                        table.setHeaderRows(11);
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(7);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        // double OsBalance = 0.00;
                        
                        try {
                            java.sql.Statement st1111 = connectDB.createStatement();
                            
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st41 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st111 = connectDB.createStatement();
                            java.sql.Statement st2x = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while(rset2x.next()){
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            //  java.sql.ResultSet rset4 = st4.executeQuery("select scheme_manager from ac_scheme_providers where scheme_manager = '"+memNo+"'");
                            java.sql.ResultSet rset21 = st21.executeQuery("select scheme_manager,account_no from ac_scheme_providers where scheme_manager = '"+memNo+"'");
                            // java.sql.ResultSet rset41 = st41.executeQuery("select scheme from hp_patient_card where scheme = '"+listofStaffNos+"'");
                            
                            
                            while (rset3.next()){
                                table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                
                                
                                //   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("P.O. BOX : "+rset3.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Tel : "+rset3.getObject(3).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                
                                //    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Fax : "+ rset3.getObject(5).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                //  table.getDefaultCell().setColspan(3);
                                //     table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Email : "+rset3.getObject(6).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                //     table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Website : " +rset3.getObject(7).toString(), pFontHeader);
                                
                                table.addCell(phrase);
                            }
                            /// table.addCell("  ");
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MEMBERSHIP AMENDMENT REPORT", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            while (rset21.next()){
                                table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset21.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
//                                while (rset41.next())
                                
                                //   while (rset21.next())
                                table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Account No : " +rset21.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(4);
                            
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                                
                                //  table.addCell(phrase);
                                
                                
                                phrase = new Phrase("Period : " +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), pFontHeader1);
                                
                                table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //phrase = new Phrase("Period : '"+beginDate+"' - '"+endDate+"'", pFontHeader1);
                            //table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date " +datenowSql, pFontHeader1);
                            table.addCell(phrase);
                            
                            // table.getDefaultCell().setColspan(3);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Member No.",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Member Name",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Our Ref.",pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Relation",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Instruction Date",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Instruction By",pFontHeader1);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("ADDITIONS",pFontHeader1);
                            table.addCell(phrase);
                            //   java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit) from ac_debtors where dealer = '"+memNo+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            String User = null;
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                double OsBalance = 0.00;
                                double osBalanceD = 0.00;
                                double osBalance1 = 0.00;
                                
                                double current = 0.00;
                                double osBalancebf = 0.00;
                                double osBalanceT = 0.00;
                                java.sql.ResultSet rset4 = st4.executeQuery("select scheme_name,account_no,expiry_date from ac_schemes where account_no = '"+listofStaffNos[j]+"'");
                                
                                //       java.sql.ResultSet rset1 = st1.executeQuery("select pc.date::date,pc.scheme_staff_no, upper(sh.staff_name),pc.invoice_no,sum(pc.credit) from hp_patient_card pc,hp_schemestaff sh where pc.scheme_staff_no = sh.staff_no and pc.isurer = '"+memNo+"' AND pc.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pc.paid = true and pc.credit > 0 group by pc.date::date,pc.scheme_staff_no, sh.staff_name,pc.reference,pc.invoice_no order by pc.invoice_no");
                                // java.sql.ResultSet rset1 = st1.executeQuery("select db.date,db.journal_no, upper(db.item),db.invoice_no,db.debit from ac_debtors db where  db.payee = '"+listofStaffNos[j]+"' AND db.dealer = '"+memNo+"' AND db.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND db.debit != 0 order by db.date");
                                java.sql.ResultSet rset1 = st1.executeQuery("select date_set,staff_no,staff_name,staff_reg_no,relation,instruction_date,sign_name from scheme_newmembers_view where account_no = '"+listofStaffNos[j]+"'  AND date_set BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_set");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                                java.sql.ResultSet rset11 = st11.executeQuery("select count(staff_no) from scheme_newmembers_view where account_no = '"+listofStaffNos[j]+"' AND  date_set BETWEEN '"+beginDate+"' AND '"+endDate+"'");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                                java.sql.ResultSet rset5 = st5.executeQuery("select count(staff_no) from scheme_newmembers_view where account_no = '"+listofStaffNos[j]+"'");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                                java.sql.ResultSet rset111 = st111.executeQuery("select input_date,member_no,name,input_date,member_no,name from pb_scheme_deletions where acc_no = '"+listofStaffNos[j]+"' AND input_date  BETWEEN '"+beginDate+"' AND '"+endDate+"' order by input_date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                                java.sql.ResultSet rset1111 = st1111.executeQuery("select count(member_no) from pb_scheme_deletions where acc_no = '"+listofStaffNos[j]+"' AND input_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                                
                                
                                
                                while (rset4.next()){
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(4);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Scheme Name : " +rset4.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase("Expiry Date : " +rset4.getObject(3).toString(), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Account No : " +rset4.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                }
                                
                                
                                while (rset1.next()){
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                    
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
                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(5), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(6), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(7), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                
                                while (rset11.next()){
                                    table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Count : " +rset11.getObject(1).toString(), pFontHeader1);
                                osBalance1 = osBalance1 + rset11.getDouble(1);
                                
                                table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("DELETIONS",pFontHeader1);
                                table.addCell(phrase);
                                while (rset111.next()){
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset111.getObject(1), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset111.getObject(2), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset111.getObject(3), "-"), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("0", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase( "-", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase( "-", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase( "-", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                }
                                table.getDefaultCell().setColspan(7);
                                
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                
                                
                                //  while (rsetTotals.next()) {
                                
                                
                                while (rset1111.next()){
                                    table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Deletions : " +rset1111.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                osBalanceD = osBalanceD + rset1111.getDouble(1);
                                }
                                
                                while (rset5.next()){
                                    table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset5.getObject(1).toString(), pFontHeader1);
                                osBalanceT = osBalanceT + rset5.getDouble(1);
                                }
                                phrase = new Phrase("Openeng Bal  : " +new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalanceT +osBalanceD)- osBalance1)),pFontHeader1);
                                
                                table.addCell(phrase);
                                phrase = new Phrase("Closing Bal  : " +new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceT)),pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.white);
                                
                                //  table.getDefaultCell().setColspan(14);
                                //phrase = new Phrase(" ");
                                
                                
                            }
                            table.getDefaultCell().setColspan(21);
                            phrase = new Phrase(" ");
                            table.addCell(phrase);
                            phrase = new Phrase("As per your instructions, we have amended your scheme as indicated above.Unless you notify us of an error within 14 days of receipt,the changes will be deemed correct.\n Thank you.",pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(21);
                            phrase = new Phrase(" ");
                            
                            table.addCell(phrase);
                            phrase = new Phrase("Amended by: '"+User+"'      Verified By :__________________________      Stamp:_____________________________",pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            docPdf.add(table);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        // }  // }
                        
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
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT sc.account_no FROM ac_schemes sc,scheme_newmembers_view sv   WHERE  sc.payer_name ilike '"+memNo+"' AND  sv.date_set BETWEEN '"+beginDate+"' AND '"+endDate+"' and  sc.account_no = sv.account_no order by sc.account_no");
            
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





