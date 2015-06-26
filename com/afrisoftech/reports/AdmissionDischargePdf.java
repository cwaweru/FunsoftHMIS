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


public class AdmissionDischargePdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    int numberSeq = 0;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void AdmissionDischargePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Admission & Discharge List - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    int dayT = 0;
                    int monT = 0;
                    int cashT = 0;
                    int schemeT = 0;
                    int selfT = 0;
                    int copT = 0;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {6,30,10,10,12,12};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(1);
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths1[] = {6,30,10,10};
                        
                        table1.setWidths(headerwidths1);
                        
                        table1.setWidthPercentage((100));
                        
                        table1.setHeaderRows(1);
                        
                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths2[] = {6,30,10,10,10,10,10};
                        
                        table2.setWidths(headerwidths2);
                        
                        table2.setWidthPercentage((100));
                        
                        table2.setHeaderRows(1);
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(6);
                        
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);
                            
                            phrase = new Phrase("ADMISSION & DISCHARGES REPORT : Period From: "  +dateFormat.format(endDate11)+" TO "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase(" ",pFontHeader);
                        // table.addCell(phrase);
                        phrase = new Phrase("No.",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Source",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Adm. for the day",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Cumm. Adm. for the month",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Budget",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Budget Cumm. Adm. for the month",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        // table.addCell("Amount KShs.");
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            for (int i = 0; i < listofAct.length; i++) {
                                
                                int patNo1 = 0;
                                
                                java.sql.ResultSet rset = st.executeQuery("select reffered_from,count(patient_no) from hp_admission WHERE date_admitted = '"+endDate+"' AND reffered_from = '"+listofAct[i]+"' GROUP BY reffered_from ORDER BY reffered_from");
                                
                                java.sql.ResultSet rset2q1 = stw.executeQuery("select reffered_from,count(patient_no) from hp_admission WHERE date_admitted = '"+endDate+"' AND reffered_from = '"+listofAct[i]+"' GROUP BY reffered_from ORDER BY reffered_from");
                                
                                java.sql.ResultSet rset1 = st1.executeQuery("select reffered_from,count(patient_no) from hp_admission WHERE date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' AND reffered_from = '"+listofAct[i]+"' GROUP BY reffered_from ORDER BY reffered_from");
                                while (rset2q1.next()) {
                                    patNo1 = rset2q1.getInt(2);
                                }
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setColspan(1);
                                numberSeq = numberSeq+1;
                                
                                phrase = new Phrase(""+numberSeq+"   ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+listofAct[i], pFontHeader1);
                                table.addCell(phrase);
                                
                                if(patNo1 > 0){
                                    while (rset.next()) {
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        //phrase = new Phrase(rsetq.getObject(2).toString(), pFontHeader1);
                                        
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        dayT = dayT + rset.getInt(2);
                                        
                                        
                                    }
                                }else{
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase("-", pFontHeader1);
                                    table.addCell(phrase);
                                }
                                while (rset1.next()) {
                                    //  while (rset.next()) {
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                       /* phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        */
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase(rset1.getObject(2).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    monT = monT + rset1.getInt(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    //}
                                }
                            }
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Total", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(""+dayT, pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(""+monT, pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setMinimumHeight(40);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            table1.getDefaultCell().setColspan(1);
                            
                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            phrase = new Phrase(" ",pFontHeader);
                            // table.addCell(phrase);
                            phrase = new Phrase("No.",pFontHeader);
                            table1.addCell(phrase);
                            phrase = new Phrase("Schemes",pFontHeader);
                            table1.addCell(phrase);
                            phrase = new Phrase("Adm. for the day",pFontHeader);
                            table1.addCell(phrase);
                            
                            phrase = new Phrase("Cumm. Adm. for the month",pFontHeader);
                            table1.addCell(phrase);
                            
                            //phrase = new Phrase("Budget",pFontHeader);
                            // table.addCell(phrase);
                            
                            //  phrase = new Phrase("Budget Cumm. Adm. for the month",pFontHeader);
                            // table.addCell(phrase);
                            
                            int num = 0;
                            java.sql.Statement stq = connectDB.createStatement();
                            
                            java.sql.Statement st1q = connectDB.createStatement();
                            
                            java.sql.Statement st2q = connectDB.createStatement();
                            
                            java.lang.Object[] listofAct1 = this.getListofStaffNos1();
                            for (int j = 0; j < listofAct1.length; j++) {
                                int patNo = 0;
                                
                                java.sql.ResultSet rsetq = stq.executeQuery("select ward_code,count(patient_no) from hp_admission WHERE date_admitted = '"+endDate+"'  AND ward_code  = '"+listofAct1[j]+"'  GROUP BY ward_code ORDER BY ward_code");
                                
                                java.sql.ResultSet rset2q = st2q.executeQuery("select ward_code,count(patient_no) from hp_admission WHERE date_admitted = '"+endDate+"'  AND ward_code  = '"+listofAct1[j]+"'  GROUP BY ward_code ORDER BY ward_code");
                                
                                java.sql.ResultSet rset1q = st1q.executeQuery("select ward_code,count(patient_no) from hp_admission WHERE date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"'   AND ward_code  = '"+listofAct1[j]+"'  GROUP BY ward_code ORDER BY ward_code");
                                
                                while (rset2q.next()) {
                                    patNo = rset2q.getInt(2);
                                }
                                
                                table1.getDefaultCell().setColspan(1);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                
                                num = num+1;
                                
                                phrase = new Phrase(""+num+"   ", pFontHeader1);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+listofAct1[j], pFontHeader1);
                                table1.addCell(phrase);
                                if(patNo > 0){
                                    while (rsetq.next()) {
                                        
                                        table1.getDefaultCell().setColspan(1);
                                        table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        //phrase = new Phrase(rsetq.getObject(2).toString(), pFontHeader1);
                                        
                                        phrase = new Phrase(dbObject.getDBObject(rsetq.getObject(2), "-"), pFontHeader1);
                                        table1.addCell(phrase);
                                        cashT = cashT + rsetq.getInt(2);
                                        
                                    }
                                }else{
                                    table1.getDefaultCell().setColspan(1);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase("-", pFontHeader1);
                                    table1.addCell(phrase);
                                }
                                
                                while (rset1q.next()) {
                                    table1.getDefaultCell().setColspan(1);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    // phrase = new Phrase(rset1q.getObject(2).toString(), pFontHeader1);
                                    phrase = new Phrase(dbObject.getDBObject(rset1q.getObject(2), "-"), pFontHeader1);
                                    table1.addCell(phrase);
                                    schemeT = schemeT + rset1q.getInt(2);
                                    
                                    //table.addCell(phrase);
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    //  table1.addCell(phrase);
                                    
                                    table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    //    table1.addCell(phrase);
                                    
                                    //}
                                }
                            }
                            
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Total", pFontHeader1);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setColspan(1);
                            phrase = new Phrase(""+cashT, pFontHeader1);
                            table1.addCell(phrase);
                            phrase = new Phrase(""+schemeT, pFontHeader1);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(4);
                            table1.getDefaultCell().setMinimumHeight(40);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table1.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            table2.getDefaultCell().setColspan(1);
                            
                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            
                            phrase = new Phrase("No.",pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Source",pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("Discharges for the day",pFontHeader);
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("Cumm. Discharges for the month",pFontHeader);
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("Total Bills",pFontHeader);
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("Total Paid",pFontHeader);
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("Comment",pFontHeader);
                            table2.addCell(phrase);
                            
                            int num1 = 0;
                            java.sql.Statement stqw = connectDB.createStatement();
                            
                            java.sql.Statement st1qw = connectDB.createStatement();
                            
                            java.sql.Statement st2qw = connectDB.createStatement();
                            
                            java.lang.Object[] listofAct11 = this.getListofStaffNos11();
                            for (int k = 0; k < listofAct11.length; k++) {
                                int patNox = 0;
                                
                                java.sql.ResultSet rsetqw = stqw.executeQuery("select mode_of_payment,count(patient_no) from hp_admission WHERE discharge_date::date= '"+endDate+"'  AND mode_of_payment  = '"+listofAct11[k]+"'  GROUP BY mode_of_payment ORDER BY mode_of_payment");
                                
                                java.sql.ResultSet rset2qw = st2qw.executeQuery("select mode_of_payment,count(patient_no) from hp_admission WHERE discharge_date::date = '"+endDate+"'  AND mode_of_payment  = '"+listofAct11[k]+"'  GROUP BY mode_of_payment ORDER BY mode_of_payment");
                                
                                java.sql.ResultSet rset1qw = st1qw.executeQuery("select mode_of_payment,count(patient_no) from hp_admission WHERE discharge_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'   AND mode_of_payment  = '"+listofAct11[k]+"'  GROUP BY mode_of_payment ORDER BY mode_of_payment");
                                
                                while (rset2qw.next()) {
                                    patNox = rset2qw.getInt(2);
                                }
                                
                                table2.getDefaultCell().setColspan(1);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                
                                num1 = num1+1;
                                
                                phrase = new Phrase(""+num1+"   ", pFontHeader1);
                                table2.addCell(phrase);
                                table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(""+listofAct11[k], pFontHeader1);
                                table2.addCell(phrase);
                                if(patNox > 0){
                                    while (rsetqw.next()) {
                                        
                                        table2.getDefaultCell().setColspan(1);
                                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        //phrase = new Phrase(rsetq.getObject(2).toString(), pFontHeader1);
                                        
                                        phrase = new Phrase(dbObject.getDBObject(rsetqw.getObject(2), "-"), pFontHeader1);
                                        table2.addCell(phrase);
                                        selfT =selfT + rsetqw.getInt(2);
                                        
                                    }
                                }else{
                                    table2.getDefaultCell().setColspan(1);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase("-", pFontHeader1);
                                    table2.addCell(phrase);
                                }
                                
                                while (rset1qw.next()) {
                                    table2.getDefaultCell().setColspan(1);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    // phrase = new Phrase(rset1q.getObject(2).toString(), pFontHeader1);
                                    phrase = new Phrase(dbObject.getDBObject(rset1qw.getObject(2), "-"), pFontHeader1);
                                    table2.addCell(phrase);
                                    copT = copT + rset1qw.getInt(2);
                                    //table.addCell(phrase);
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    table2.addCell(phrase);
                                    
                                    table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    table2.addCell(phrase);
                                    
                                    phrase = new Phrase("", pFontHeader1);
                                    
                                    table2.addCell(phrase);
                                    
                                }
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Total", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(""+selfT, pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(""+copT, pFontHeader1);
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("", pFontHeader1);
                            
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            
                            table2.addCell(phrase);
                            
                            phrase = new Phrase("", pFontHeader1);
                            
                            table2.addCell(phrase);
                            
                            docPdf.add(table);
                            docPdf.add(table1);
                            docPdf.add(table2);
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT reffered_from FROM hp_admission WHERE date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY reffered_from");
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_admission WHERE discharge = false ORDER BY patient_no");
            
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ward_code FROM hp_admission WHERE date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY ward_code");
            
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
    
    
    public java.lang.Object[] getListofStaffNos11() {
        
        java.lang.Object[] listofStaffNos11 = null;
        
        java.util.Vector listStaffNoVector11 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT mode_of_payment FROM hp_admission WHERE discharge_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY mode_of_payment");
            
            while (rSet1.next()) {
                
                listStaffNoVector11.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofStaffNos11 = listStaffNoVector11.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos11;
    }
    
}





