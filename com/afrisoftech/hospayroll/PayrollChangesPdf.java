

//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospayroll;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class PayrollChangesPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.util.Date dateEnd = null;
    java.lang.String bank = null;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    
    com.afrisoftech.timeseries.AgeingSeries ageingSeries= null;
    
    java.util.Date ageingDates[][] = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    Phrase phrase1;
    Phrase phrase2;
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
    com.lowagie.text.Font pFontNum1 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum11 = FontFactory.getFont(FontFactory.TIMES, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void PayrollChangesPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate,java.lang.String combox) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        connectDB = connDb;
        
        beginDate = begindate;
        bank=combox;
        endDate = endate;
        
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(2, endate);
        
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new GlTransactPdf().GlTransactPdf();
        
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
        java.lang.Object[][] rangeDates = ageingSeries.getAgeingDateSeries();
        
        // ageingDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals[] = new double[rangeDates.length];
        
        java.sql.ResultSet rsetTotals1 = null;
        
        java.lang.Process wait_for_Pdf2Show;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        
        java.util.Date dateStampPdf = cal.getTime();
        
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        
        int interval = 0;
        
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
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        
                        java.sql.Statement st31 = connectDB.createStatement();
                        java.sql.Statement st41 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset21 = st31.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset41 = st41.executeQuery("SELECT date('now') as Date");
                        while(rset21.next()){
                            compName = rset21.getObject(1).toString();
                        }
                        while(rset41.next()){
                            date = rset41.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //         com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(2);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    // com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Payslip - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    // docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    try {
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        //   for (int j = 0; j < listofStaffNos.length; j++) {
                        
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+2);
                        
                        String headerWidths = null;
                        
                        java.util.Vector headerVector = new java.util.Vector(1,1);
                        
                        int z = rangeDates.length;
                        
                        
                        int headerwidths[] = {15,30,15,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(1);
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        System.out.println("Where am I ...");
                        
                        table.getDefaultCell().setColspan(4);
                        
                        Phrase phrase;
                        
                        try {
                            
                            
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            
                            table.getDefaultCell().setColspan(4);
                            
                            phrase = new Phrase("Pay Period :     Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double taxCharged = 0;
                        double paye = 0;
                        System.out.println("Where am I ...");
                        
                        try {
                            
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st311 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st3A = connectDB.createStatement();
                            java.sql.Statement st4A = connectDB.createStatement();
                            java.sql.Statement st31 = connectDB.createStatement();
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st16 = connectDB.createStatement();
                            java.sql.Statement st17 = connectDB.createStatement();
                            java.sql.Statement st18 = connectDB.createStatement();
                            java.sql.Statement st19 = connectDB.createStatement();
                            java.sql.Statement stss = connectDB.createStatement();
                            System.out.println("Statements Created ...");
                            
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Staff No".toUpperCase(), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase("Description".toUpperCase(), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            for (int x = 0; x < rangeDates.length; x++) {
                                
                                try {
                                    
                                    // Date parser
                                    
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "MMM/yy");
                                    
                                    java.lang.String monthString = dateFormatter.getDateString();
                                    
                                    int days = 1;
                                    if (x < 1) {
                                        
                                        com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "MMM/yy");
                                        
                                        java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                        
                                        phrase = new Phrase(monthStringCurrent ,pFontHeader);
                                    } else {
                                        phrase = new Phrase(monthString, pFontHeader);
                                        //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
                                        interval = x;
                                    }
                                    
                                    table.addCell(phrase);
                                    
                                    // Catch java.text.parse exception.
                                    
                                } catch(java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                            }
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            java.lang.Object[] listofAct4 = this.getListofActivities4();
                            String Descr = null;
                            
                            for (int m = 0; m < listofAct4.length; m++) {
                                double chargeable = 0;
                                double relief = 0;
                                
                                double TotalEarnings = 0;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                java.sql.ResultSet rset11 = st311.executeQuery("select description from posting where  staff_no =  '"+listofAct4[m]+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ILIKE 'Earning%' and company_name ilike '"+bank+"' order by description");
                                
                                while (rset11.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(listofAct4[m].toString().toUpperCase(), pFontNum);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1), "-"),pFontNum);
                                    table.addCell(phrase);
                                    Descr = rset11.getObject(1).toString();
                                    //for (int t = 0; t < rangeDates.length; t++) {
                                        for (int t = 0; t < rangeDates.length; t++) {
                                        double earnings = 0;
                                        java.sql.ResultSet rset1 = st31.executeQuery("select amount from posting where  staff_no =  '"+listofAct4[m]+"' AND date BETWEEN '"+rangeDates[t][0]+"' AND '"+rangeDates[t][1]+"' AND allowance_deduction ILIKE 'Earning%' and company_name ilike '"+bank+"' and description ilike '"+Descr+"'");
                                        
                                        while (rset1.next()) {
                                            // table.getDefaultCell().setColspan(1);
                                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            // phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"),pFontNum);
                                            // table.addCell(phrase);
                                            earnings = rset1.getDouble(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(earnings),"0.00")),pFontNum);
                                            table.addCell(phrase);
                                            
                                            columnTotals[t] = columnTotals[t] + rset1.getDouble(1);
                                        }
                                        
                                    }
                                }
                            }
                            
                            
                            
                            
                            
                            docPdf.add(table);
                            
                            
                            
                            System.out.println("Status of new page : "+docPdf.getPageNumber());
                            
                            
                            boolean boolNewPage = docPdf.newPage();
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        //   }
                        
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank+"' order by staff_no");
            
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
    
    
    public java.lang.Object[] getListofActivities4() {
        
        java.lang.Object[] listofActivities4 = null;
        
        java.util.Vector listActVector4 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT staff_no FROM posting where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ilike 'earning%' and company_name ilike '"+bank+"' order by staff_no");
            
            while (rSet1.next()) {
                
                listActVector4.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities4 = listActVector4.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities4;
    }
    
    public java.lang.Object[] getListofActivities5() {
        
        java.lang.Object[] listofActivities5 = null;
        
        java.util.Vector listActVector5 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM posting where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ilike 'non%' and company_name ilike '"+bank+"' order by description");
            
            while (rSet1.next()) {
                
                listActVector5.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities5 = listActVector5.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities5;
    }
    
    public java.lang.Object[] getListofActivities6() {
        
        java.lang.Object[] listofActivities6 = null;
        
        java.util.Vector listActVector6 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM posting where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND allowance_deduction ilike 'less%' and company_name ilike '"+bank+"' order by description");
            
            while (rSet1.next()) {
                
                listActVector6.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities6 = listActVector6.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities6;
    }
    
    public java.lang.Object[] getListofActivities7() {
        
        java.lang.Object[] listofActivities7 = null;
        
        java.util.Vector listActVector7 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tx.description FROM posting tx where tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND  (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'Monthly Personal Relief%' OR tx.description ILIKE 'P.A.Y.E%') and company_name ilike '"+bank+"' order by tx.description");
            
            while (rSet1.next()) {
                
                listActVector7.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities7 = listActVector7.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities7;
    }
    
    public java.lang.Object[] getListofActivities8() {
        
        java.lang.Object[] listofActivities8 = null;
        
        java.util.Vector listActVector8 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tx.description FROM posting tx where tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND  tx.description ILIKE 'Monthly Personal Relief%' and company_name ilike '"+bank+"' order by tx.description");
            
            while (rSet1.next()) {
                
                listActVector8.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities8 = listActVector8.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities8;
    }
    
    public java.lang.Object[] getListofActivities9() {
        
        java.lang.Object[] listofActivities9 = null;
        
        java.util.Vector listActVector9 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT tx.description FROM posting tx where tx.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND  (tx.description ILIKE 'PAYE%' OR tx.description ILIKE 'P.A.Y.E%') and company_name ilike '"+bank+"' order by tx.description");
            
            while (rSet1.next()) {
                
                listActVector9.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities9 = listActVector9.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities9;
    }
    
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT description FROM deduction_summary where date BETWEEN '"+beginDate+"' AND '"+endDate+"' and company_name ilike '"+bank+"' order by description");
            
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





