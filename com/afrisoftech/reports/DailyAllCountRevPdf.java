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


public class DailyAllCountRevPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    String ks;
    
    int noSeq;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DailyAllCountRevPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void description(java.lang.String[] args) {
        
        //		new GlTransactPdf().GlTransactPdf();
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf();
            
            try {
                
                System.out.println("Right, let's wait for task to complete of fail");
                
                java.lang.Thread.currentThread().sleep(50);
                
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
        java.sql.ResultSet rsetTotals1 = null;
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        
        try {
            
            java.io.File tempFile = java.io.File.createTempFile("REP"+this.getDateLable()+"_", ".pdf");
            
            tempFile.deleteOnExit();
            
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            
            // java.lang.String credit-debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName, pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Activity vs Revenue - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    
                    
                    double avg = 0.00;
                    double TotalAmt =0.00;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);
                        
                        int headerwidths[] = {5,30,9,9,9,9,9,9,9,9};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        
                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(10);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            
                            phrase = new Phrase("Daily Activity vs Revenue :  Period : ".toUpperCase()  +dateFormat.format(endDate11)+" -- "+dateFormat.format(endDate1), pFontHeader11);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        table.getDefaultCell().setColspan(2);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        phrase = new Phrase(" ".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("IN PATIENT".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        
                        
                        phrase = new Phrase("OUT PATIENT".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("BOTH OP+IP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Avg".toUpperCase(), pFontHeader1);
                        
                        // table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        phrase = new Phrase("No".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Deparment".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Self IP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Scheme IP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Total IP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Self OP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Scheme OP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Total OP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("OP+IP".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Avg".toUpperCase(), pFontHeader1);
                        
                        table.addCell(phrase);
                        
                        /*
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Revenue "+ks.toUpperCase(), pFontHeader1);
                         
                        table.addCell(phrase);
                         */
                        //   phrase = new Phrase("Y.T.D KShs.", pFontHeader);
                        
                        //  table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            double dys =0.00;
                            double osBalancebf = 0.00;
                            double osBalancebf2 = 0.00;
                            double osBalancebf11 = 0.00;
                            double osBalancebf1B = 0.00;
                            double osBalancebf21 = 0.00;
                            
                            int osBalancebf11a = 0;
                            int ipSelf1 = 0;
                            int ipSceme1 = 0;
                            int opSelf1 = 0;
                            int opSceme1 = 0;
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                int ipSelf = 0;
                                int ipSceme = 0;
                                int opSelf = 0;
                                int opSceme = 0;
                                java.sql.Statement st32= connectDB.createStatement();
                                
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.ResultSet rset41 = st2.executeQuery("select  upper(activity) from pb_activity where code ilike '"+listofStaffNos[j]+"'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                
                                
                                
                                // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                
                                java.sql.ResultSet rset212 = st32.executeQuery("select ('"+endDate+"'::date - '"+beginDate+"'::date)");
                                while (rset212.next()) {
                                    dys = 1 + rset212.getDouble(1);
                                    System.out.println(dys);
                                }
                                
                                while (rset41.next()) {
                                    
                                    //1
                                    noSeq = noSeq + 1;
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(noSeq),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    //2
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset41.getString(1).toString(), pFontHeader);
                                    
                                    table.addCell(phrase);
                                    java.lang.Object listofStaffNosx[] = this.getListofStaffNos1(listofStaffNos[j]);
                                    
                                    for (int k = 0; k < listofStaffNosx.length; k++) {
                                       int ipCash = 0;
                                       int ipScheme = 0;
                                       int opCash = 0;
                                       int opScheme = 0;
                                        //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                        
                                        
                                        
                                        System.out.println(listofStaffNos[j]);
                                        java.sql.Statement st41 = connectDB.createStatement();
                                        
                                        java.sql.Statement psetc2 = connectDB.createStatement();
                                        java.sql.Statement st1 = connectDB.createStatement();
                                        java.sql.Statement st4 = connectDB.createStatement();
                                        java.sql.Statement st11 = connectDB.createStatement();
                                        java.sql.Statement st41d = connectDB.createStatement();
                                        
                                        java.sql.Statement st1c = connectDB.createStatement();
                                        java.sql.Statement st1d = connectDB.createStatement();
                                        java.sql.Statement st4c = connectDB.createStatement();
                                        java.sql.Statement st4d = connectDB.createStatement();
                                        
                                        java.sql.Statement psetc1 = connectDB.createStatement();
                                        java.sql.Statement st7 = connectDB.createStatement();
                                        java.sql.Statement st71 = connectDB.createStatement();
                                        java.sql.Statement st7c = connectDB.createStatement();
                                        java.sql.Statement st7d = connectDB.createStatement();
                                        
                                        java.sql.Statement stTotal= connectDB.createStatement();
                                        java.sql.Statement stTotalAmt= connectDB.createStatement();
                                        
                                        
                                        java.sql.Statement st3 = connectDB.createStatement();
                                        java.sql.Statement psetc3 = connectDB.createStatement();
                                        //   rsetTotals1 = st3.executeQuery("SELECT SUM(credit-debit) from hp_patient_billing WHERE trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                        System.out.println("Select C done ...");
                                        
                                        
                                        System.out.println("Statements Created ...");
                                        //java.sql.ResultSet rset7 = st7.executeQuery("select count(distinct patient_no) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //            java.sql.ResultSet rset7c = st7c.executeQuery("select sum(credit-debit) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        // java.sql.ResultSet rset71 = st71.executeQuery("select count(distinct patient_no) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'OP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //             java.sql.ResultSet rset7cd = st7d.executeQuery("select sum(credit-debit) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'OP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        
                                        //            java.sql.ResultSet rsetTotal = stTotal.executeQuery("select count(distinct patient_no) from ac_ledger where date  between '"+beginDate+"' AND '"+endDate+"' and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'OP' or drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //            java.sql.ResultSet rsetTotalAmt = stTotalAmt.executeQuery("select sum(credit-debit) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and  (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD' OR gl_code ilike 'SCHEME') and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'OP' or drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        
                                        
                                        java.sql.ResultSet rset1 = st1.executeQuery("select count(distinct patient_no) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and  (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD') and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //              java.sql.ResultSet rset1c = st1c.executeQuery("select sum(credit-debit) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and  (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD') and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        java.sql.ResultSet rset11 = st11.executeQuery("select count(distinct patient_no) from ac_ledger where date  = '"+listofStaffNosx[k]+"' and  (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD') and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'OP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //               java.sql.ResultSet rset1d = st1d.executeQuery("select sum(credit-debit) from ac_ledger where date = '"+listofStaffNosx[k]+"' and  (gl_code ilike 'CASH' OR gl_code ilike 'CHEQUE' OR gl_code ilike 'CREDIT CARD') and activity_code ilike '"+listofStaffNos[j]+"' and drawer ilike 'OP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        
                                        
                                        java.sql.ResultSet rset4 = st4.executeQuery("select count(distinct patient_no) from ac_ledger where date = '"+listofStaffNosx[k]+"' and gl_code ilike 'SCHEME' and activity_code ilike '"+listofStaffNos[j]+"' and  drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //               java.sql.ResultSet rset4c = st4c.executeQuery("select sum(credit-debit) from ac_ledger where date = '"+listofStaffNosx[k]+"' and gl_code ilike 'SCHEME' and activity_code ilike '"+listofStaffNos[j]+"' and  drawer ilike 'IP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        java.sql.ResultSet rset41d = st41d.executeQuery("select count(distinct patient_no) from ac_ledger where date = '"+listofStaffNosx[k]+"' and gl_code ilike 'SCHEME' and activity_code ilike '"+listofStaffNos[j]+"' and  drawer ilike 'OP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        //               java.sql.ResultSet rset4d = st4d.executeQuery("select sum(credit-debit) from ac_ledger where date = '"+listofStaffNosx[k]+"' and gl_code ilike 'SCHEME' and activity_code ilike '"+listofStaffNos[j]+"' and  drawer ilike 'OP'");// tn,credit-debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                                        
                                        
                                        
                                        
                                        
                                        int d3 = 0;
                                        int d1 = 0;
                                        int d = 0;
                                        while (rset1.next()) {
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            ipCash = rset1.getInt(1);
                                            ipSelf = ipSelf + ipCash;
                                            ipSelf1 = ipSelf1 + ipCash;
                                            
                                        }
                                        
                                        //4
                                        
                                        while (rset4.next()) {
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            ///phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset4.getString(2)),pFontNum);
                                            ipScheme = rset4.getInt(1);
                                            ipSceme = ipSceme + ipScheme;
                                            ipSceme1 = ipSceme1 + ipScheme;
                                        }
                                        
                                        
                                        while (rset11.next()) {
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(1)),pFontNum);
                                            //phrase = new Phrase(dbObject.getDBObject(rset11.getString(1), "0.00"), pFontNum);
                                            opCash = rset11.getInt(1);
                                            opSelf = opSelf + opCash;
                                            opSelf1 = opSelf1 + opCash;
                                        }
                                        
                                        
                                        while (rset41d.next()) {
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            // phrase = new Phrase(dbObject.getDBObject(rset41d.getString(1), "0.00"), pFontNum);
                                            opScheme = rset41d.getInt(1);
                                            opSceme = opSceme + opScheme;
                                            opSceme1 = opSceme1 + opScheme;
                                            //osBalancebf11 = osBalancebf11 + rset41d.getDouble(1);
                                            
                                            // table.addCell(phrase);
                                        }
                                        
                                    }
                                    
                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset7.getString(1)),pFontNum);
                                    //       phrase = new Phrase(dbObject.getDBObject(rset7.getString(1), "0.00"), pFontNum);
                                    
                                    osBalancebf11a = osBalancebf11a + (ipSelf+ipSceme);
                                    // phrase = new Phrase(dbObject.getDBObject(rset1.getString(1), "0.00"), pFontNum);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(ipSelf),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(ipSceme),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(ipSelf+ipSceme),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    //phrase = new Phrase(dbObject.getDBObject(rset71.getString(1), "0.00"), pFontNum);
                                    
                                    osBalancebf11 = osBalancebf11 + (opSelf+opSceme);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(opSelf),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(opSceme),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(" "+java.lang.String.valueOf(opSelf+opSceme),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    //}
                                    
                                    double countTotal = 0.00;
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    // phrase = new Phrase(dbObject.getDBObject(rsetTotal.getString(1), "0.00"), pFontNum);
                                    
                                    countTotal = osBalancebf11 + osBalancebf11a;
                                    phrase = new Phrase(" "+java.lang.String.valueOf(ipSelf+ipSceme+opSelf+opSceme),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    avg = (ipSelf+ipSceme+opSelf+opSceme)/dys ;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(avg)),pFontHeader);
                                    
                                    table.addCell(phrase);
                                    
                                    
                                    
                                    
                                }
                                
                            }
                            
                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(" "+java.lang.String.valueOf(ipSelf1),pFontHeader);
                            //osBalancebf1 = osBalancebf1 + rset1.getDouble(1);
                            table.addCell(phrase);
                            
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(" "+java.lang.String.valueOf(ipSceme1),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            
                            //5
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(" "+java.lang.String.valueOf(ipSelf1+ipSceme1),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            // 6
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(" "+java.lang.String.valueOf(opSelf1),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            //7 table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(" "+java.lang.String.valueOf(opSceme1),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            //8
                            phrase = new Phrase(" "+java.lang.String.valueOf(opSelf1+opSceme1),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            //9
                            phrase = new Phrase(" "+java.lang.String.valueOf(ipSelf1+ipSceme1+opSelf1+opSceme1),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            //10
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((ipSelf1+ipSceme1+opSelf1+opSceme1)/dys)),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                            
                            //11
                          /* phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalancebf11)),pFontHeader);
                            //   osBalancebf1 = osBalancebf1 + rset4.getDouble(1);
                            table.addCell(phrase);
                           
                                    table.addCell(phrase);
                           */
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
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct code from pb_activity pb,ac_ledger ac  where category_class ilike 'pli%' and ac.activity_code = pb.code and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by code");
            
            //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct patient_no FROM hp_patient_register WHERE last_visit  BETWEEN ? AND ? AND pay_mode = ? order by patient_no");
            //  pSet1.setString(1,beginDate.toString());
            //  pSet1.setString(2,endDate.toString());
            // pSet1.setString(3,"Raise Invoice");
            //pSet1.setString(4,"''");
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
    
    
    public java.lang.Object[] getListofStaffNos1(java.lang.Object codex) {
        
        java.lang.Object[] listofStaffNos1 = null;
        
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct date FROM ac_ledger ac  WHERE ac.activity_code = '"+codex+"' AND ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY date");
            
            //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct patient_no FROM hp_patient_register WHERE last_visit  BETWEEN ? AND ? AND pay_mode = ? order by patient_no");
            //  pSet1.setString(1,beginDate.toString());
            //  pSet1.setString(2,endDate.toString());
            // pSet1.setString(3,"Raise Invoice");
            //pSet1.setString(4,"''");
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





