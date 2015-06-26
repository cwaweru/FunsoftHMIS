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


public class FpCotraceptivesPerCategoryPdf implements java.lang.Runnable {
    java.util.Date beginDate = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date endDate = null;
    
    
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
    
    public void FpCotraceptivesPerCategoryPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
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
        
        //		new EntranceFeePdf().EntranceFeePdf();
        
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
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    String compName = null;
                    String date = null;
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Family planning summary - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {12,15,30,15,10,15,10,18};
                        
                        table1.setWidths(headerwidths);
                        
                        table1.setWidthPercentage((100));
                        
                        
                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table1.getDefaultCell().setColspan(8);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name ,current_date from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(8);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader);
                            table1.addCell(phrase);
                            date = rset3.getObject(2).toString();
                            }
                            //table1.addCell(phrase);
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {20,32,9,9,9,9,2,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        Phrase phrase;
                        try{
                            table.getDefaultCell().setColspan(4);
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            phrase = new Phrase("CONTRACEPTIVES PER CLIENT TYPE : "  +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        table.getDefaultCell().setColspan(1);
                        
                        //table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("CODE",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("DESCRIPTION",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("NA",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("CA",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("R",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("O",pFontHeader);
                        // table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Total",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Cumm.",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double occ = 0.00;
                        double total = 0.00;
                        try {
                            int osBalance = 0;
                            int ca1 = 0;
                            int nr = 0;
                            int cr = 0;
                            int rr = 0;
                            int rro = 0;
                            int rrx = 0;
                            
                            java.lang.Object[] listofActx = this.getListofActivities1();
                            for (int y = 0; y < listofActx.length; y++) {
                                int cum = 0;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(8);
                                phrase = new Phrase(listofActx[y].toString().toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                                java.lang.Object[] listofAct = this.getListofActivities(listofActx[y]);
                                
                                System.out.println(listofAct.length);
                                
                                
                                for (int i = 0; i < listofAct.length; i++) {
                                    int r = 0;
                                    int na = 0;
                                    int ca = 0;
                                    int ra = 0;
                                    int rax = 0;
                                    int ro = 0;
                                    String pnca = null;
                                    String pnca1 = null;
                                    String pnca1x = null;
                                    java.sql.Statement st3 = connectDB.createStatement();
                                    java.sql.Statement st31 = connectDB.createStatement();
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM fprecord  WHERE cotraceptive = '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'NA' AND fp_method not ilike 'Consultation%'");
                                    java.sql.ResultSet rset31 = st31.executeQuery("SELECT DISTINCT cotraceptive FROM fprecord  WHERE cotraceptive = '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                    
                                    
                                    while (rset31.next()){
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(" ", pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rset31.getObject(1).toString().toUpperCase(), "-"), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        
                                        while (rset3.next()){
                                            
                                            na = rset3.getInt(1);
                                            nr = nr + rset3.getInt(1);
                                        }
                                        
                                        if (na > 0){
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(java.lang.String.valueOf(na), pFontHeader1);
                                            table.addCell(phrase);
                                            na = na;
                                        }else{
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                            table.addCell(phrase);
                                            na = 0;
                                        }
                                        
                                        java.sql.Statement st32c = connectDB.createStatement();
                                        
                                        java.sql.ResultSet rset32c = st32c.executeQuery("SELECT DISTINCT patient_no FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE = 'CA' AND fp_method not ilike 'Consultation%' GROUP BY patient_no  EXCEPT SELECT patient_no FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE = 'NA' group by patient_no");
                                        while (rset32c.next()){
                                            
                                            pnca = rset32c.getString(1);
                                            
                                            java.sql.Statement st32 = connectDB.createStatement();
                                            
                                            //java.sql.ResultSet rset32 = st32.executeQuery("SELECT DISTINCT COUNT(patient_no)  FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'CA' AND fp_method not ilike 'Consultation%' AND patient_no = '"+pnca+"' GROUP BY patient_type EXCEPT SELECT COUNT(patient_no) FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND cotraceptive ilike '"+listofAct[i]+"' AND PATIENT_TYPE ILIKE 'NA' AND PATIENT_TYPE ILIKE 'R' AND fp_method not ilike 'Consultation%'  AND patient_no = '"+pnca+"'");
                                            java.sql.ResultSet rset32 = st32.executeQuery("SELECT DISTINCT COUNT(patient_no)  FROM fprecord  WHERE cotraceptive = '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE = 'CA' AND fp_method not ilike 'Consultation%' AND patient_no = '"+pnca+"' GROUP BY patient_no LIMIT 1");
                                            
                                            while (rset32.next()){
                                                if (rset32.getInt(1) > 1){
                                                    ca = ca + 0;
                                                    cr = cr + 0;
                                                }else{
                                                    ca = ca + rset32.getInt(1);
                                                    cr = cr + rset32.getInt(1);
                                                }
                                                // phrase = new Phrase(java.lang.String.valueOf(r), pFontHeader1);
                                                //  table.addCell(phrase);
                                            }
                                        }
                                        if (ca > 0){
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(java.lang.String.valueOf(ca), pFontHeader1);
                                            table.addCell(phrase);
                                            ca = ca;
                                        }else{
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                            table.addCell(phrase);
                                            ca = 0;
                                        }
                                        
                                        //  java.sql.ResultSet rset32 = st32.executeQuery("SELECT COUNT(patient_no) FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'CA' AND fp_method not ilike 'Consultation%' EXCEPT SELECT COUNT(patient_no) FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'NA' AND PATIENT_TYPE ILIKE 'R' AND fp_method not ilike 'Consultation%'");
                                        //   java.sql.ResultSet rset32 = st32.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'CA' AND fp_method not ilike 'Consultation%'");
                                        
                                        java.sql.Statement st32c1 = connectDB.createStatement();
                                        
                                        java.sql.ResultSet rset32c1 = st32c1.executeQuery("SELECT DISTINCT patient_no FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE = 'R' AND fp_method not ilike 'Consultation%' GROUP BY patient_no  EXCEPT SELECT patient_no FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (PATIENT_TYPE = 'NA' OR PATIENT_TYPE = 'CA') group by patient_no");
                                        while (rset32c1.next()){
                                            
                                            pnca1 = rset32c1.getString(1);
                                            
                                            
                                            java.sql.Statement st33 = connectDB.createStatement();
                                            //  java.sql.ResultSet rset33 = st33.executeQuery("SELECT COUNT(patient_no) FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"%' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'R' EXCEPT SELECT COUNT(patient_no) FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (PATIENT_TYPE ILIKE 'NA' OR PATIENT_TYPE ILIKE 'CA')");
                                            
                                            java.sql.ResultSet rset33 = st33.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM fprecord  WHERE cotraceptive = '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND patient_no = '"+pnca1+"' AND fp_method not ilike 'Consultation%' GROUP BY patient_no");
                                            java.sql.Statement st331 = connectDB.createStatement();
                                            
                                            while (rset33.next()){
                                                if (rset33.getInt(1) > 1){
                                                    ra = ra + 0;
                                                    rr = rr + 0;
                                                }else{
                                                    ra = ra + rset33.getInt(1);
                                                    rr = rr + rset33.getInt(1);
                                                }
                                            }
                                        }
                                        
                                        java.sql.Statement st32cx = connectDB.createStatement();
                                        
                                        java.sql.ResultSet rset32cx = st32cx.executeQuery("SELECT DISTINCT patient_no FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND cotraceptive = '"+listofAct[i]+"' AND fp_method ilike 'Consult%' group by patient_no EXCEPT SELECT DISTINCT patient_no FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND fp_method not ilike 'Consult%' group by patient_no");
                                        while (rset32cx.next()){
                                            
                                            pnca1x = rset32cx.getString(1);
                                            
                                            
                                            java.sql.Statement st33x = connectDB.createStatement();
                                            //  java.sql.ResultSet rset33 = st33.executeQuery("SELECT COUNT(patient_no) FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"%' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND PATIENT_TYPE ILIKE 'R' EXCEPT SELECT COUNT(patient_no) FROM fprecord  WHERE cotraceptive ilike '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (PATIENT_TYPE ILIKE 'NA' OR PATIENT_TYPE ILIKE 'CA')");
                                            
                                            java.sql.ResultSet rset33x = st33x.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM fprecord  WHERE cotraceptive = '"+listofAct[i]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND patient_no = '"+pnca1x+"' AND fp_method ilike 'Consultation' GROUP BY patient_no");
                                            
                                            while (rset33x.next()){
                                                if (rset33x.getInt(1) > 1){
                                                    rax = rax + 0;
                                                    rrx = rrx + 0;
                                                }else{
                                                    rax = rax + rset33x.getInt(1);
                                                    rrx = rrx + rset33x.getInt(1);
                                                }
                                            }
                                        }
                                        
                                        
                                        if (ra > 0 || rax > 0){
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(java.lang.String.valueOf(ra+rax), pFontHeader1);
                                            table.addCell(phrase);
                                            ra = ra+rax;
                                        }else{
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                            table.addCell(phrase);
                                            ra = 0;
                                        }
                                        
                                        
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(java.lang.String.valueOf(na+ca+ra), pFontHeader1);
                                        table.addCell(phrase);
                                        cum = cum + na+ca+ra;
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(java.lang.String.valueOf(cum), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                    
                                }
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            //  while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(java.lang.String.valueOf(nr), pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(java.lang.String.valueOf(cr), pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(java.lang.String.valueOf(rr+rrx), pFontHeader);
                            table.addCell(phrase);
                            
                            phrase = new Phrase(java.lang.String.valueOf(rro), pFontHeader);
                            // table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(java.lang.String.valueOf(nr+cr+rr+rrx), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            docPdf.add(table);
                            
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
    
    
    public java.lang.Object[] getListofActivities1() {
        
        java.lang.Object[] listofActivities1 = null;
        
        java.util.Vector listActVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT fp_method FROM fprecord WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND fp_method NOT ILIKE 'Other%' order by fp_method");
            
            while (rSet1.next()) {
                
                listActVector1.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }
    
    public java.lang.Object[] getListofActivities(java.lang.Object fpMethod) {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT cotraceptive FROM fprecord WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND fp_method = '"+fpMethod+"' order by cotraceptive");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
    
}





