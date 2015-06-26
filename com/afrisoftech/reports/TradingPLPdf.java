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


public class TradingPLPdf implements java.lang.Runnable {
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    double amount = 0.00;
    double income = 0.00;
    double Expenses = 0.00;
    double profit = 0.00;
    boolean threadCheck = true;
    String ks;
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.ITALIC);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void TradingPLPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new ProfitLossPdf().ProfitLossPdf();
        
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
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        //    java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st2x = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while(rset2x.next()){
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"", pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Profit & Loss - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {5,15,30,15,15,15,15,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                        
                        
                        //   table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(8);
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            //  java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            
                            phrase = new Phrase("Profit & Loss :   As At : " +dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("INCOME", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        // table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("DIRECT OVERHEADS", pFontHeader);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("No", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Code", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Description", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        
                        phrase = new Phrase("Income "+ks, pFontHeader);
                        
                        table.addCell(phrase);
                        phrase = new Phrase("Cost Of Sale", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Salary", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Consumables", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Gross", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        
                        
                        //    phrase = new Phrase("Credit KShs.", pFontHeader);
                        
                        //   table.addCell(phrase);
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stv = connectDB.createStatement();
                            java.sql.Statement stv1 = connectDB.createStatement();
                            java.sql.Statement stv2 = connectDB.createStatement();
                            
                            
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            java.sql.Statement st7 = connectDB.createStatement();
                            
                            
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            phrase = new Phrase("INCOME", pFontHeader2);
                            double grossT = 0.00;
                            double cosT = 0.00;
                            double salT = 0.00;
                            double consT = 0.00;
                            int nos = 0;
                            int nos1 = 0;
                            //    table.addCell(phrase);
                            java.lang.Object[] listofAct = this.getListofActivities();
                            for (int i = 0; i < listofAct.length; i++) {
                                double income = 0.00;
                                double cos = 0.00;
                                double sal = 0.00;
                                double cons = 0.00;
                                java.sql.ResultSet rset = st.executeQuery("select pb.code,initcap(pb.activity),sum(ac.credit-ac.debit) from tb_itemized_summary ac,pb_activity pb where pb.code = ac.gl_code and ac.gl_code = '"+listofAct[i].toString()+"' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'  group by pb.code,pb.activity order by pb.code");
                                
                                java.sql.ResultSet rsetv = stv.executeQuery("select sum(ac.debit-ac.credit) from tb_itemized_summary ac,pb_departments pb WHERE pb.cost_of_sale = ac.gl_code AND pb.income_account = '"+listofAct[i].toString()+"' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rsetv1 = stv1.executeQuery("select sum(ac.debit-ac.credit) from tb_itemized_summary ac,pb_departments pb WHERE pb.salary_account = ac.gl_code AND pb.income_account = '"+listofAct[i].toString()+"' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                java.sql.ResultSet rsetv2 = stv2.executeQuery("select sum(ac.debit-ac.credit) from tb_itemized_summary ac,pb_departments pb WHERE pb.consumables_account = ac.gl_code AND pb.income_account = '"+listofAct[i].toString()+"' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.black);
                                
                                
                                
                                
                                while (rset.next()) {
                                    while (rsetv.next()) {
                                        while (rsetv1.next()) {
                                            while (rsetv2.next()) {
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                table.getDefaultCell().setColspan(1);
                                                nos = nos + 1;
                                                phrase = new Phrase(java.lang.String.valueOf(nos), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                
                                                phrase = new Phrase(rset.getObject(1).toString(), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(rset.getObject(2).toString(), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                
                                                amount = amount + rset.getDouble(3);
                                                income = rset.getDouble(3);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetv.getString(1)), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                cos = rsetv.getDouble(1);
                                                cosT = cosT + rsetv.getDouble(1);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetv1.getString(1)), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                sal = rsetv1.getDouble(1);
                                                salT = salT + rsetv1.getDouble(1);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetv2.getString(1)), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                cons = rsetv2.getDouble(1);
                                                consT = consT + rsetv2.getDouble(1);
                                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(income - (cos+sal+cons))), pFontHeader);
                                                
                                                table.addCell(phrase);
                                                
                                                grossT = grossT + (income - (cos+sal+cons));
                                            }
                                            
                                        }
                                    }
                                }
                            }
                            //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            table.getDefaultCell().setColspan(3);
                            //    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL", pFontHeader2);
                            
                            table.addCell(phrase);
                            
                            //  table.addCell("Profit/Loss");
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            // profit = amount;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cosT)), pFontHeader1);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(salT)), pFontHeader1);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(consT)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(grossT)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nos)), pFontHeader1);
                            
                            
                            //       table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            table.getDefaultCell().setColspan(3);
                            //       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //       table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("GROSS PROFIT", pFontHeader2);
                            
                            table.addCell(phrase);
                            
                            //  table.addCell("Profit/Loss");
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            // profit = amount;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount)), pFontHeader1);
                            
                            //table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(cosT)), pFontHeader1);
                            
                            //table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(salT)), pFontHeader1);
                            
                            // table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(consT)), pFontHeader1);
                            
                            //table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(grossT)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(nos)), pFontHeader1);
                            
                            //table.addCell(phrase);
                            // table.addCell(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset6.getString(1)));
                            
                            table.getDefaultCell().setColspan(8);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            //     java.sql.ResultSet rset2 = st2.executeQuery("select pb.code,initcap(pb.activity),sum(ac.debit-ac.credit) from tb_itemized_summary ac,pb_activity pb where pb.code = ac.gl_code and pb.category_class ilike 'ple' and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'  group by pb.code,pb.activity order by code");
                            
                            phrase = new Phrase("INDIRECT OVERHEADS", pFontHeader2);
                            
                            table.addCell(phrase);
                            java.lang.Object[] listofAct1x = this.getListofActivities1x();
                            for (int k = 0; k < listofAct1x.length; k++) {
                                int sbcode = 0;
                                java.sql.Statement st2a = connectDB.createStatement();
                                java.sql.ResultSet rset2a = st2a.executeQuery("select DISTINCT sub_code,UPPER(description) from pb_sub_activities where sub_code = '"+listofAct1x[k]+"'");
                                java.sql.Statement st2az = connectDB.createStatement();
                                java.sql.ResultSet rset2az = st2az.executeQuery("select DISTINCT count(ac.gl_code) from tb_itemized_summary ac,pb_activity pb where ac.gl_code = pb.code AND ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND pb.sub_code = '"+listofAct1x[k]+"'");
                                while (rset2az.next()) {
                                    sbcode = rset2az.getInt(1);
                                }
                                if(sbcode > 0){
                                    while (rset2a.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(rset2a.getObject(1).toString(), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(6);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        phrase = new Phrase(rset2a.getObject(2).toString(), pFontHeader1);
                                        
                                        table.addCell(phrase);
                                    }
                                }
                                java.lang.Object[] listofAct1 = this.getListofActivities1(listofAct1x[k]);
                                for (int i = 0; i < listofAct1.length; i++) {
                                    
                                    java.sql.ResultSet rset2 = st2.executeQuery("select pb.code,initcap(pb.activity),sum(ac.debit-ac.credit) from tb_itemized_summary ac,pb_activity pb where ac.gl_code = '"+listofAct1[i]+"' AND pb.code = ac.gl_code and ac.date BETWEEN '"+beginDate+"' AND '"+endDate+"'  group by pb.code,pb.activity order by code");
                                    
                                    while (rset2.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        nos1 = nos1 + 1;
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(java.lang.String.valueOf(nos1), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        phrase = new Phrase(rset2.getObject(1).toString(), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        //  table.addCell(rset.getObject(1).toString());
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset2.getObject(2).toString(), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(5);
                                        
                                        // table.addCell(rset.getObject(2).toString());
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(3)), pFontHeader);
                                        
                                        //  table.addCell(phrase);
                                        
                                        Expenses = Expenses + rset2.getDouble(3);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(3)), pFontHeader);
                                        
                                        // table.addCell(phrase);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(3)), pFontHeader);
                                        
                                        //  table.addCell(phrase);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset2.getString(3)), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                    }
                                }
                            }
                            //         table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            
                            
                            table.getDefaultCell().setColspan(3);
                            //          table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //          table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Other Expenses Total", pFontHeader2);
                            
                            table.addCell(phrase);
                            
                            //  table.addCell("Profit/Loss");
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            //profit = amount;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Expenses)), pFontHeader1);
                            
                            // table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Expenses)), pFontHeader1);
                            
                            //  table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Expenses)), pFontHeader1);
                            
                            //  table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Expenses)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            //       table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            
                            
                            table.getDefaultCell().setColspan(3);
                            //       table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net Profit/Loss", pFontHeader2);
                            
                            table.addCell(phrase);
                            
                            //  table.addCell("Profit/Loss");
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            // profit = amount;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount-Expenses)), pFontHeader1);
                            
                            //table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount-Expenses)), pFontHeader1);
                            
                            //  table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(amount-Expenses)), pFontHeader1);
                            
                            //  table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(grossT-Expenses)), pFontHeader1);
                            
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
            
            docPdf.close();
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
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT code,activity FROM pb_activity pb WHERE pb.category_class ilike 'pli' order by code");
            
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                // System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());
                
            }
            
            //System.out.println(rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        //  System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
    
    
    public java.lang.Object[] getListofActivities1x() {
        
        java.lang.Object[] listofActivities1x = null;
        
        java.util.Vector listActVector1x = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT sub_code FROM pb_activity WHERE category_class ilike 'ple%' order by 1");
            
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                //          System.out.println(rSet1.getObject(1).toString());
                listActVector1x.addElement(rSet1.getObject(1).toString());
                
            }
            
            //System.out.println(rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities1x = listActVector1x.toArray();
        //  System.out.println("Done list of activities ...");
        return listofActivities1x;
    }
    
    
    public java.lang.Object[] getListofActivities1(java.lang.Object subcode) {
        
        java.lang.Object[] listofActivities1 = null;
        
        java.util.Vector listActVector1 = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT code FROM pb_activity WHERE category_class ilike 'ple' AND sub_code = '"+subcode+"' EXCEPT select cost_of_sale FROM pb_departments order by 1");
            
            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            
            while (rSet1.next()) {
                //          System.out.println(rSet1.getObject(1).toString());
                listActVector1.addElement(rSet1.getObject(1).toString());
                
            }
            
            //System.out.println(rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities1 = listActVector1.toArray();
        //  System.out.println("Done list of activities ...");
        return listofActivities1;
    }
    
    
 /*   public java.lang.Object[] getListofActivities() {
  
        java.lang.Object[] listofActivities = null;
  
        java.util.Vector listActVector = new java.util.Vector(1,1);
  
  
        try {
  
            //   java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
  
            java.sql.Statement stmt1 = connectDB.createStatement();
  
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT sub_code FROM plincome_view order by sub_code");
  
            while (rSet1.next()) {
  
                listActVector.addElement(rSet1.getObject(1).toString());
  
            }
  
        }catch (java.sql.SQLException sqlExec) {
  
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
  
        }
  
        listofActivities = listActVector.toArray();
  
        return listofActivities;
    }
  
    public java.lang.Object[] getListofActivities1() {
  
        java.lang.Object[] listofActivities1 = null;
  
        java.util.Vector listActVector1 = new java.util.Vector(1,1);
  
  
        try {
  
            //  java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
  
            java.sql.Statement stmt1 = connectDB.createStatement();
  
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT sub_code FROM plexpenses_view order by sub_code");
  
            while (rSet1.next()) {
  
                listActVector1.addElement(rSet1.getObject(1).toString());
  
            }
  
        }catch (java.sql.SQLException sqlExec) {
  
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
  
        }
  
        listofActivities1 = listActVector1.toArray();
  
        return listofActivities1;
    }
  */
    
}





