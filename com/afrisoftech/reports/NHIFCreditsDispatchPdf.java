package com.afrisoftech.reports;

//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

//package com.afrisoftech.hospital;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class NHIFCreditsDispatchPdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    String dispatchNo = null;
    
    double TotalBal = 0.00;
    
        String ks;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader20 = FontFactory.getFont(FontFactory.TIMES, 25, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void NHIFCreditsDispatchPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String disNo) {
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        dispatchNo = disNo;
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
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
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    //int Days = 0;
                    //int days = 0;
                    int sek = 0;
                    int Days = 0;
                    int days = 0;
                    double balance = 0.00;
                    String Code = null;
                    double Rate = 0.00;
                    double CummBal = 0.00;
                    String Address = null;
                    //  com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("NHIF REBATES - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    //  docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        //java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {15,15,30,15,15,15,15};
                        
                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));
                        
                        
                        
                        
                        table1.getDefaultCell().setColspan(7);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table1.getDefaultCell().setColspan(7);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("NHIF", pFontHeader20);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.white);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table1.getDefaultCell().setColspan(6);
                            phrase = new Phrase(" ", pFontHeader);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setColspan(1);
                            phrase = new Phrase("N.H.I.F. 18", pFontHeader);
                            table1.addCell(phrase);
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(7);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader2);
                                table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);
                        
                        int headerwidths[] = {5,10,11,27,17,13,7,15,10,10};
                        
                        table.setWidths(headerwidths);
                        table.setHeaderRows(2);
                        table.setWidthPercentage((105));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select code,rate,address from pb_nssf_rebeats");
                            while (rset3.next()){
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                Code = dbObject.getDBObject(rset3.getObject(1), "-");
                                Rate = rset3.getDouble(2);
                                
                                Address = dbObject.getDBObject(rset3.getObject(3), "-");
                            }
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.ResultSet rset2 = st2.executeQuery("select sum(debit) from ac_debtors WHERE  dispatch_no = '"+dispatchNo+"' ");
                            while (rset2.next()){
                                TotalBal = rset2.getDouble(1);
                            }
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        Phrase phrase = new Phrase("The Director,", pFontHeader2);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(4);
                        
                        
                        phrase = new Phrase("Hospital Code No : " + Code, pFontHeader2);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(6);
                        
                        
                        phrase = new Phrase("National Hospital Insurance Fund,", pFontHeader2);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(4);
                        
                        
                        phrase = new Phrase("Daily Rebate : " +Rate, pFontHeader2);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(10);
                        phrase = new Phrase("P.O. Box : "+Address, pFontHeader2);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase(" ", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(8);
                        phrase = new Phrase("Dear Sir,", pFontHeader2);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(8);
                            
                            phrase = new Phrase("Statement of Account for Week-Ending : "  +dateFormat.format(endDate1), pFontHeader2);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(8);
                            
                            //      phrase = new Phrase("I enclose herewith claim forms as detailed below to the value of Shs..........."+TotalBal+"........\n"+
                            phrase = new Phrase("I enclose herewith claim forms as detailed below to the value of Shs.................................\n"+
                                    "being benefits allowed to contributions. Please arrange to reimburse this hospital at your earliest convenience.", pFontHeader2);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //  table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("No.",pFontHeader2);
                        table.addCell(phrase);
                        phrase = new Phrase("Claim No.",pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Stamps\n on card ",pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Patient Name.",pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Contributor's \nMembership No.",pFontHeader2);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Statement\n Inv. No.",pFontHeader2);
                        table.addCell(phrase);
                        phrase = new Phrase("Days",pFontHeader2);
                        table.addCell(phrase);
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Amount Claimed "+ks,pFontHeader2);
                        table.addCell(phrase);
                        phrase = new Phrase("Bed No.",pFontHeader2);
                        table.addCell(phrase);
                        phrase = new Phrase("Remarks",pFontHeader2);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        try {
                            java.lang.Object[] listofActx = this.getListofStaffNosx();
                            
                            
                           // System.out.println(listofAct.length);
                            
                            java.sql.Statement st12 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT count(admission_no) FROM ac_debtors WHERE  dispatch_no = '"+dispatchNo+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            for (int m = 0; m < listofActx.length; m++) {
                                
                            
                           // java.lang.Object[] listofAct = this.getListofStaffNos(listofActx[m]);
                           // for (int i = 0; i < listofAct.length; i++) {

                                 java.sql.Statement st12u = connectDB.createStatement();

                            java.sql.ResultSet rset12u = st12u.executeQuery("SELECT DISTINCT visit_id,patient_no FROM hp_admission WHERE invoice_no = '"+listofActx[m]+"'" );// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");

                               /* java.lang.Object[] listofAct1 = this.getListofStaffNos1(listofAct[i]);
                                for (int k = 0; k < listofAct1.length; k++) {*/
                            while (rset12u.next()) {
                               String visitid = rset12u.getString(1);
                               String pno = rset12u.getString(2);
                               System.out.println(visitid);
                                    java.sql.Statement st = connectDB.createStatement();
                                    //  java.sql.ResultSet rset = st.executeQuery("select dt.invoice_no,'',initcap(dt.item),dt.journal_no,pc.invoice_no,ad.discharge_date::date-ad.date_admitted ,dt.debit ,ad.bed_no,'' from ac_debtors dt,hp_patient_card pc,hp_admission ad,hp_patient_discharge ds WHERE dt.date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dt.payee = 'N H I F' and dt.admission_no ='"+listofAct[i]+"' and dt.admission_no = pc.patient_no and dt.admission_no = ad.patient_no and pc.reference = dt.invoice_no and dt.admission_no = ds.patient_no order by dt.invoice_no ,ad.date_admitted limit 1");
                                //    java.sql.ResultSet rset = st.executeQuery("select distinct dt.invoice_no,'',initcap(dt.item),dt.journal_no,pc.invoice_no,dt.debit,'' from ac_debtors dt,hp_patient_card pc WHERE dispatch_no = '"+dispatchNo+"' and dt.payee = 'N H I F' and dt.admission_no ='"+listofAct[i]+"' and dt.admission_no = pc.patient_no and pc.reference = dt.invoice_no and pc.visit_id =  '"+listofAct1[k]+"' order by dt.invoice_no");
                                    java.sql.ResultSet rset = st.executeQuery("select distinct dt.invoice_no,'',"
                                            + "initcap(dt.item),dt.journal_no,pc.invoice_no,dt.debit,'' from "
                                            + "ac_debtors dt,hp_patient_card pc WHERE dispatch_no = '"+dispatchNo+"' "
                                            + " and dt.admission_no ='"+pno+"' "
                                            + "and dt.admission_no = pc.patient_no and pc.reference = dt.journal_no "
                                            + "and pc.visit_id =  '"+visitid+"' AND pc.invoice_no = '"+listofActx[m]+"' "
                                            + "order by dt.invoice_no");
                                    
                                    java.sql.Statement st1 = connectDB.createStatement();
                                    java.sql.ResultSet rset1 = st1.executeQuery("select discharge_date::date-date_admitted::date,bed_no "
                                            + " FROM hp_admission WHERE admission_no ='"+pno+"' and visit_id = '"+visitid+"'");
                                    
                                    
                                    while (rset.next()) {
                                        
                                        while (rset1.next()) {
                                            
                                            
                                            sek = sek + 1;
                                            
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            phrase = new Phrase(""+sek+ "", pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            table.getDefaultCell().setColspan(1);
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(rset.getObject(5).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            
                                            balance = rset.getDouble(6);
                                            days = rset1.getInt(1);
                                            
                                            Days = Days + days;
                                            phrase = new Phrase(""+days, pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(balance)),pFontHeader1);
                                            CummBal = CummBal + balance;
                                            table.addCell(phrase);
                                            
                                            
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(rset.getObject(7).toString(), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                    }
                                }
                            //}
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            
                            table.getDefaultCell().setColspan(5);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Totals", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase((java.lang.String.valueOf(Days)), pFontHeader);
                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(CummBal)),pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(10);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(10);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("  ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(8);
                            
                            phrase = new Phrase("Administrator ...............................................  ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            docPdf.add(table);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        //
                        // }
                        
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
    
    
     public java.lang.Object[] getListofStaffNosx() {
        
        java.lang.Object[] listofStaffNosx = null;
        
        java.util.Vector listStaffNoVectorx = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT invoice_no FROM "
                    + "ac_debtors WHERE  dispatch_no = '"+dispatchNo+"' ORDER BY invoice_no");
            
            while (rSet1.next()) {
                
                listStaffNoVectorx.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofStaffNosx = listStaffNoVectorx.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNosx;
    }
    
    /*public java.lang.Object[] getListofStaffNos(java.lang.Object invoiceNo) {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT admission_no FROM "
                    + "ac_debtors WHERE  dispatch_no = '"+dispatchNo+"' AND invoice_no = '"+invoiceNo+"' "
                    + "and admission_no !='' and admission_no is not null");
            
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
    
    
    public java.lang.Object[] getListofStaffNos1(java.lang.Object patNo) {
        
        java.lang.Object[] listofStaffNos1 = null;
        
        java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT visit_id FROM"
                    + " hp_admission WHERE  discharge_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'"
                    + " and patient_no = '"+patNo+"' ORDER BY visit_id");
            
            while (rSet1.next()) {
                
                listStaffNoVector1.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
    }*/
    
}












