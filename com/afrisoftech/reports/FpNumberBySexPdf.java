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


public class FpNumberBySexPdf implements java.lang.Runnable {
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
    
    public void FpNumberBySexPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
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
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {12,15,30,15,10,15,10,18};
                        
                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
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
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
             
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable tablex = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths1[] = {10,21,12,12,15,15,15};
                        
                        tablex.setWidths(headerwidths1);
                        
                        tablex.setWidthPercentage((100));
                        tablex.setHeaderRows(2);
                        
                        tablex.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        Phrase phrase;
                        try{
                            tablex.getDefaultCell().setColspan(5);
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            phrase = new Phrase("NUMBER OF CLIENT BY SEX" , pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            
                            tablex.getDefaultCell().setColspan(2);
                            tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(" ", pFontHeader);
                            
                            tablex.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        tablex.getDefaultCell().setColspan(1);
                        
                        //table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("CODE",pFontHeader);
                        tablex.addCell(phrase);
                        
                        tablex.getDefaultCell().setColspan(2);
                        phrase = new Phrase("DESCRIPTION",pFontHeader);
                        tablex.addCell(phrase);
                        tablex.getDefaultCell().setColspan(1);
                        
                        tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("MALE",pFontHeader);
                        tablex.addCell(phrase);
                        
                        phrase = new Phrase("FEMALE",pFontHeader);
                        tablex.addCell(phrase);
                        
                        
                        tablex.getDefaultCell().setColspan(1);
                        phrase = new Phrase("No.",pFontHeader);
                        tablex.addCell(phrase);
                        
                        phrase = new Phrase("CUMM",pFontHeader);
                        tablex.addCell(phrase);
                        
                        
                        tablex.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        tablex.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double occ = 0.00;
                        double total = 0.00;
                        String fpservice = null;
                        try {
                            java.lang.Object[] listofAct1 = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            System.out.println("This is the analysis : "+listofAct1.length);
                            int osBalance = 0;
                            int cum = 0;
                            int r = 0;
                            int na1 = 0;
                            int nax1 = 0;
                            for (int x = 0; x < listofAct1.length; x++) {
                                int nax = 0;
                                int na = 0;
                                System.out.println("This is the analysis 11: "+listofAct1[x].toString().toUpperCase());
                                
                                java.sql.Statement st3d = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3d = st3d.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM fprecord  WHERE patient_type ilike '"+listofAct1[x]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND sex ilike 'M%' AND patient_type NOT ILIKE 'O' AND fp_method not ilike 'Consultation%'");
                                
                                java.sql.Statement st3xd = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3xd = st3xd.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM fprecord  WHERE patient_type ilike '"+listofAct1[x]+"' AND date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND sex ilike 'F%'  AND patient_type NOT ILIKE 'O' AND fp_method not ilike 'Consultation%'");
                                
                                java.sql.Statement st31d = connectDB.createStatement();
                                
                                java.sql.ResultSet rset31d = st31d.executeQuery("SELECT fp_clients FROM fp_clients  WHERE code ilike '"+listofAct1[x]+"'");
                                
                                
                                
                                
                                while (rset31d.next()){
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    tablex.getDefaultCell().setColspan(1);
                                     phrase = new Phrase(dbObject.getDBObject(listofAct1[x].toString().toUpperCase(), "-"), pFontHeader1);
                               
                                   // phrase = new Phrase(listofAct1[x].toString().toUpperCase(), pFontHeader1);
                                    tablex.addCell(phrase);
                                    System.out.println("This is the code : "+listofAct1[x].toString().toUpperCase());
                                    tablex.getDefaultCell().setColspan(2);
                                  //  phrase = new Phrase(rset31d.getString(1).toUpperCase(), pFontHeader1);
                                    phrase = new Phrase(dbObject.getDBObject(rset31d.getObject(1).toString().toUpperCase(), "-"), pFontHeader1);
                                        
                                    tablex.addCell(phrase);
                                    System.out.println("This is the description : "+rset31d.getString(1).toUpperCase());
                                }
                                while (rset3d.next()){
                                    
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    na = rset3d.getInt(1);
                                    na1 = na1 + rset3d.getInt(1);
                                }
                                if(na > 0){
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(na), pFontHeader1);
                                    tablex.addCell(phrase);
                                    na = na;
                                    System.out.println("This is M :"+na);
                                }else{
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                    tablex.addCell(phrase);
                                    na = 0;
                                }
                                
                                while (rset3xd.next()){
                                    tablex.getDefaultCell().setColspan(1);
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    
                                    nax = rset3xd.getInt(1);
                                    
                                    nax1 = nax1 + rset3xd.getInt(1);
                                }
                                if(nax > 0){
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(nax), pFontHeader1);
                                    tablex.addCell(phrase);
                                    nax = nax;
                                    System.out.println("This is M :"+nax);
                                }else{
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                    tablex.addCell(phrase);
                                    nax = 0;
                                }
                                
                                tablex.getDefaultCell().setColspan(1);
                                tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(java.lang.String.valueOf(na+nax), pFontHeader1);
                                tablex.addCell(phrase);
                                cum = cum + (na+nax);
                                phrase = new Phrase(java.lang.String.valueOf(na+nax), pFontHeader1);
                                tablex.addCell(phrase);
                                
                                
                                
                            }
                            
                            tablex.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            tablex.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            //  while (rsetTotals.next()) {
                            
                            tablex.getDefaultCell().setColspan(3);
                            
                            tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            tablex.getDefaultCell().setColspan(1);
                            
                            tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(java.lang.String.valueOf(na1), pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            phrase = new Phrase(java.lang.String.valueOf(nax1), pFontHeader);
                            
                            tablex.addCell(phrase);
                            tablex.getDefaultCell().setColspan(2);
                            phrase = new Phrase(java.lang.String.valueOf(cum), pFontHeader);
                            
                            tablex.addCell(phrase);
                            // }
                            tablex.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            
                            tablex.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" " , pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            tablex.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" " , pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            tablex.getDefaultCell().setColspan(7);
                            tablex.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            tablex.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            phrase = new Phrase("services analysis by Sex".toUpperCase() , pFontHeader);
                            
                            tablex.addCell(phrase);
                            java.lang.Object[] listofAct = this.getListofActivities1();
                            int cumx = 0;
                            int na1x = 0;
                            int nax1x = 0;
                            for (int h = 0; h < listofAct.length; h++) {
                                int fp = 0;
                                int naxf = 0;
                                java.sql.Statement st3d1 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3d1 = st3d1.executeQuery("SELECT distinct cotraceptive,COUNT(patient_no) FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND sex ilike 'M%' AND fp_method = '"+listofAct[h]+"' AND fp_method not ilike 'Consultation%' GROUP BY cotraceptive");
                                
                                java.sql.Statement st3xd1 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset3xd1 = st3xd1.executeQuery("SELECT distinct COUNT(patient_no) FROM fprecord  WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND sex ilike 'F%' AND fp_method = '"+listofAct[h]+"' AND fp_method not ilike 'Consultation%'");
                                
                               tablex.getDefaultCell().setBorderColor(java.awt.Color.WHITE); 
                                tablex.getDefaultCell().setColspan(2);
                                tablex.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader1);
                                tablex.addCell(phrase);
                                tablex.getDefaultCell().setColspan(2);
                                tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                             //   phrase = new Phrase(listofAct[h].toString().toUpperCase(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(listofAct[h].toString().toUpperCase(), "-"), pFontHeader1);
                               
                                tablex.addCell(phrase);
                                while (rset3d1.next()){
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    fp = rset3d1.getInt(2);
                                    na1x = na1x + rset3d1.getInt(2);
                                    fpservice = rset3d1.getString(1);
                                }
                                if(fp > 0){
                                    
                                    
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(fp), pFontHeader1);
                                    tablex.addCell(phrase);
                                    fp = fp;
                                    System.out.println("This is M :"+fp);
                                }else{
                                    
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                    tablex.addCell(phrase);
                                    fp = 0;
                                }
                                
                                while (rset3xd1.next()){
                                    
                                    tablex.getDefaultCell().setColspan(1);
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    
                                    
                                    naxf = rset3xd1.getInt(1);
                                    
                                    nax1x = nax1x + rset3xd1.getInt(1);
                                }
                                if(naxf > 0){
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(naxf), pFontHeader1);
                                    tablex.addCell(phrase);
                                    naxf = naxf;
                                    System.out.println("This is M :"+naxf);
                                }else{
                                    tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    tablex.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(java.lang.String.valueOf(0), pFontHeader1);
                                    tablex.addCell(phrase);
                                    naxf = 0;
                                }
                                
                                tablex.getDefaultCell().setColspan(1);
                                tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(java.lang.String.valueOf(fp+naxf), pFontHeader1);
                                tablex.addCell(phrase);
                                cumx = cumx + (fp+naxf);
                                phrase = new Phrase(java.lang.String.valueOf(fp+naxf), pFontHeader1);
                                tablex.addCell(phrase);
                                
                                
                            }
                            tablex.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            tablex.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            //  while (rsetTotals.next()) {
                            
                            tablex.getDefaultCell().setColspan(3);
                            
                            tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            tablex.getDefaultCell().setColspan(1);
                            
                            tablex.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(java.lang.String.valueOf(na1x), pFontHeader);
                            
                            tablex.addCell(phrase);
                            
                            phrase = new Phrase(java.lang.String.valueOf(nax1x), pFontHeader);
                            
                            tablex.addCell(phrase);
                            tablex.getDefaultCell().setColspan(2);
                            phrase = new Phrase(java.lang.String.valueOf(cumx), pFontHeader);
                            
                            tablex.addCell(phrase);
                            // }
                            
                            docPdf.add(tablex);
                            
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
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_type FROM fprecord WHERE date_seen::date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by patient_type");
            
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
    
    
    public java.lang.Object[] getListofActivities1() {
        
        java.lang.Object[] listofActivities1 = null;
        
        java.util.Vector listActVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT fp_method FROM fprecord order by fp_method");
            
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
    
}





