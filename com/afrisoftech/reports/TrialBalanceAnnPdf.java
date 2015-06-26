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


public class TrialBalanceAnnPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    String ks;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    double debit = 0.00;
    double credit = 0.00;
    double debit1 = 0.00;
    double credit2 = 0.00;
    int j = 0;
    java.lang.String activity = null;
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void TrialBalanceAnnPdf(java.sql.Connection connDb,java.util.Date begindate, java.util.Date endate) {
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
        
        //		new TrialBalancePdf().TrialBalancePdf();
        
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
                    try {
                        
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Trial Balance List - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {20,30,25,25};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        Phrase phrase = new Phrase("", pFontHeader);
                        
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            
                            phrase = new Phrase("Trial Balance Monthly Movement:     Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase = new Phrase("Activity Code",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Description",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Debit "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Credit "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        //java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        //   for (int j = 0; j < listofStaffNos.length; j++) {
                        try {
                            
                            
                            double diff = 0.00;
                            String suspens = null;
                            String activity1 = null;
                            String User1 = null;
                            String transNo = null;
                            
                            java.sql.Statement st2x = connectDB.createStatement();
                            java.sql.Statement st2c = connectDB.createStatement();
                            java.sql.ResultSet rsetx = st2c.executeQuery("SELECT SUM(debit - credit) from tb_itemized_summary WHERE date <= '"+endDate+"'");
                            while (rsetx.next()) {
                                diff = rsetx.getDouble(1);
                            }
                            java.sql.Statement ps = connectDB.createStatement();
                            java.sql.ResultSet rst = ps.executeQuery("select current_user");
                            while (rst.next()){
                                User1 = rst.getObject(1).toString();
                            }
                            java.sql.Statement pss = connectDB.createStatement();
                            java.sql.ResultSet rsts = pss.executeQuery("select nextval('transaction_no_seq')");
                            while (rsts.next()){
                                transNo = rsts.getObject(1).toString();
                            }
                            
                            java.sql.ResultSet rsetxx = st2x.executeQuery("SELECT code,activity from pb_activity WHERE activity_category ilike 'SPA'");
                            while (rsetxx.next()) {
                                suspens = rsetxx.getString(1);
                                activity1 = rsetxx.getString(2);
                            }
                            /*if (diff != 0.00){
                             
                                java.sql.PreparedStatement pstmt2 = connectDB.prepareStatement("insert into ac_ledger values(?,?,?,?,?,?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
                                pstmt2.setObject(1,suspens);
                                pstmt2.setString(2,activity1);
                                pstmt2.setString(8,"");
                                pstmt2.setString(3,"");
                                pstmt2.setString(4,"Suspense");
                                pstmt2.setString(6,"");
                                pstmt2.setString(7,"");
                                pstmt2.setString(5,"Suspense");
                                pstmt2.setString(9,"");
                                pstmt2.setString(10,"");
                                pstmt2.setString(12,"");
                                pstmt2.setString(11,"");
                                pstmt2.setString(13,"");
                                pstmt2.setString(14,activity);
                                if(diff < 0){
                                    pstmt2.setObject(15,"Account Adjustment");
                                    pstmt2.setDouble(16,diff*-1);
                                    pstmt2.setString(19,transNo);
                                    pstmt2.setDouble(17,0.00);
                                }else{
                                    pstmt2.setObject(15,"Account Adjustment");
                                    pstmt2.setDouble(17,diff);
                                    pstmt2.setString(19,transNo);
                                    pstmt2.setDouble(16,0.00);
                                }
                                pstmt2.setObject(18,endDate.toString());
                                pstmt2.setBoolean(20,false);
                                pstmt2.setBoolean(21,false);
                                pstmt2.setBoolean(22,false);
                                pstmt2.setString(23,User1);
                                pstmt2.executeUpdate();
                            }
                             */
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            
                            java.sql.ResultSet rset = st.executeQuery("SELECT tb.gl_code,sum(debit-tb.credit) from tb_itemized_summary tb WHERE tb.date <= '"+endDate+"' group by tb.gl_code order by tb.gl_code");
                               
                            while (rset.next()) {
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                
                                //  phrase = new Phrase(rset.getString(1), pFontHeader1);
                                table.addCell(phrase);
                                activity = rset.getString(1);
                                
                                debit1 = rset.getDouble(2);
                                // debit = debit + rset.getDouble(2);
                                
                                // credit = credit + rset.getDouble(3);
                                // credit2 = rset.getDouble(3);
                                // }
                                java.sql.ResultSet rset11 = st21.executeQuery("SELECT count (activity) from pb_activity where code = '"+activity+"'");
                                while (rset11.next()) {
                                    j = rset11.getInt(1);
                                }
                                if (j>0){
                                    java.sql.ResultSet rset1 = st2.executeQuery("SELECT CASE WHEN (activity IS NOT NULL) THEN initcap(activity) ELSE '-' END AS activity from pb_activity where code = '"+activity+"'");
                                    while (rset1.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader1);
                                        
                                        // phrase = new Phrase(rset1.getString(1), pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                }else{
                                    phrase = new Phrase("-", pFontHeader1);
                                    table.addCell(phrase);
                                }
                                
                                if(debit1 > 0){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit1)), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0)), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    debit = debit + debit1;
                                    
                                    // credit = credit + rset.getDouble(3);
                                }else{
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(0)), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)), pFontHeader);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit1*-1)), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    //   debit = debit + rset.getDouble(2);
                                    
                                    credit = credit + debit1*-1;
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
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(debit)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(2)), pFontHeader);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(credit)), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            // }
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
    
}





