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


public class DailyRevBudgetRepPdf implements java.lang.Runnable {
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    String ks;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DailyRevBudgetRepPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
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
            
            java.lang.String debitTotal = null;
            
            java.lang.String creditTotal = null;
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    double osBalance1 = 0;
                    double budgetT = 0;
                    double diffs = 0;
                    double osBalanceday = 0;
                    
                    
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
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Daily Rev. - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {5,30,10,10,15,15,15,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        
                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(8);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            
                            phrase = new Phrase("Daily Revenue Against Budget:  Period : "  +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        phrase = new Phrase("No", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        
                        
                        phrase = new Phrase("Description", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        phrase = new Phrase("Activity for the Day", pFontHeader);
                        
                        table.addCell(phrase);
                        phrase = new Phrase("Cumm. for the Month", pFontHeader);
                        
                        table.addCell(phrase);
                        phrase = new Phrase("Budget", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Revenue for the Day", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Cumm for the Month", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Cumm/Budget ",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                       // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            java.sql.Statement st34 = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement stx = connectDB.createStatement();
                            
                            java.sql.Statement stday = connectDB.createStatement();
                            
                            java.sql.Statement st1 = connectDB.createStatement();
                            
                            java.sql.Statement stday1 = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.Statement st32 = connectDB.createStatement();
                            
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            
                            System.out.println(listofAct.length);
                            int nos = 0;
                            int TdayTotal  = 0;
                            int TdayTot = 0;
                            for (int i = 0; i < listofAct.length; i++) {
                                int coe = 0;
                                double budget = 0.00;
                                double dys = 0;
                                double revnu = 0.00;
                                int days = 0;
                                int Tdays = 0;
                                
                                System.out.println("Statements Created ...");
                                java.sql.ResultSet rset = st.executeQuery("select pb.code,initcap(pb.activity),sum(ac.credit-ac.debit) from ac_ledger ac,pb_activity pb WHERE ac.activity_code = '"+listofAct[i]+"' and pb.code = '"+listofAct[i]+"' AND ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"' GROUP BY pb.code,pb.activity");
                                java.sql.ResultSet rsetday = stday1.executeQuery("select sum(ac.credit-ac.debit) from ac_ledger ac WHERE ac.activity_code = '"+listofAct[i]+"' AND ac.date = '"+endDate+"'");
                                java.sql.ResultSet rset1 = stx.executeQuery("select count(activity_code) from ac_ledger ac WHERE ac.activity_code = '"+listofAct[i]+"' AND ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rsetday1 = stday.executeQuery("select count(activity_code) from ac_ledger ac WHERE ac.activity_code = '"+listofAct[i]+"' AND ac.date = '"+endDate+"'");
                                
                                java.sql.ResultSet rset2 = st2.executeQuery("select count(gl_code) from ac_budgets WHERE gl_code = '"+listofAct[i]+"'");
                                
                                  
                             
                                while (rset2.next()) {
                                    coe = rset2.getInt(1);
                                    System.out.println(coe);
                                    
                                }
                                
                                if(coe > 0){
                                    java.sql.ResultSet rset214 = st34.executeQuery("select amounts from ac_budgets WHERE gl_code = '"+listofAct[i]+"'");
                                    while (rset214.next()) {
                                        budget = rset214.getDouble(1);
                                        System.out.println(budget);
                                    }
                                    
                                }
                                java.sql.ResultSet rset212 = st32.executeQuery("select ('"+endDate+"'::date - '"+beginDate+"'::date)");
                                while (rset212.next()) {
                                    dys = 1 + rset212.getDouble(1);
                                }
                                
                                while (rset1.next()) {
                                    days = rset1.getInt(1);
                                    TdayTot = TdayTot + days;
                                }
                                while (rsetday1.next()) {
                                    Tdays = rsetday1.getInt(1);
                                    TdayTotal = TdayTotal + Tdays;
                                }
                                //while (rsetTotals.next())
                                while (rset.next()) {
                                    while (rsetday.next()) {
                                        nos = nos + 1;
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(java.lang.String.valueOf(nos), pFontNum);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getObject(1).toString(), pFontNum);
                                        
                                        //table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset.getObject(2).toString(), pFontNum);
                                        
                                        table.addCell(phrase);
                                        
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(java.lang.String.valueOf(Tdays), pFontNum);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(java.lang.String.valueOf(days), pFontNum);
                                        
                                        table.addCell(phrase);
                                        
                                        revnu = rset.getDouble(3);
                                        //table.addCell(phrase);
                                        if(coe > 0){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(budget*dys)),pFontNum);
                                            table.addCell(phrase);
                                            
                                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(revnu - (budget*dys))),pFontNum);
                                            // table.addCell(phrase);
                                            
                                            budgetT = budgetT + (budget*dys);
                                            diffs = diffs + (revnu - (budget*dys));
                                        }else{
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0.00)),pFontNum);
                                            table.addCell(phrase);
                                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(revnu)),pFontNum);
                                            //  table.addCell(phrase);
                                            budgetT = budgetT + 0;
                                            diffs = diffs + (revnu);
                                        }
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetday.getString(1)),pFontNum);
                                        osBalanceday = osBalanceday + rsetday.getDouble(1);
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)),pFontNum);
                                        osBalance1 = osBalance1 + rset.getDouble(3);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        if(coe > 0){
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((revnu/(budget*dys))*100)),pFontNum);
                                            
                                            table.addCell(phrase);
                                        }else{
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(100)),pFontNum);
                                            
                                            table.addCell(phrase);
                                        }
                                        
                                    }
                                }
                                
                            }
                            
                             
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gross Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase(java.lang.String.valueOf(TdayTot), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(java.lang.String.valueOf(TdayTotal), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(budgetT)),pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalanceday)),pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)),pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf((osBalance1/budgetT)*100)),pFontHeader);
                            
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
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT ac.activity_code,sum(ac.credit-ac.debit) FROM ac_ledger ac,pb_activity pb WHERE ac.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (category_class ilike 'pli%') and ac.activity_code = pb.code group by ac.activity_code order by 2 desc");
            
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
            //System.out.println(rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
}





