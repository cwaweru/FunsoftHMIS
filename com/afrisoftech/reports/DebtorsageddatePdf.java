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


public class DebtorsageddatePdf implements java.lang.Runnable {
    double Credit = 0.00;
    
    int over = 0;
    
    int name = 0;
    
    int cnt = 0;
    String ks;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.String Debtor = null;
    
    java.lang.String balnc = null;
    
    java.util.Date endDate = null;
    
    java.util.Date beginDate = null;
    
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    
    com.afrisoftech.timeseries.AgeingSeries ageingSeries= null;
    
    java.util.Date ageingDates[][] = null;
    
    double osBalance = 0.00;
    
    double current = 0.00;
    
    double over30 = 0.00;
    
    double over60 = 0.00;
    
    double over90 = 0.00;
    
    double turnOver = 0.00;
    
    double grandTot = 0.00;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA,8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void DebtorsageddatePdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate, java.lang.String bal) {
        
        balnc = bal;
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date"+endDate);
        //   periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(4, endate);
        
        
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new TransactionsListPdf().TransactionsListPdf();
        
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
        
        // java.lang.Object[][] ageingDates = periodicDates.getMonthlyDates();
        
        ageingDates = ageingSeries.getAgeingDateSeries();
        
        double columnTotals[] = new double[ageingDates.length];
        double columnTotals2[] = new double[ageingDates.length];
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
            
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            
            long dateNow = calendar.getTimeInMillis();
            
            java.sql.Date datenowSql= new java.sql.Date(dateNow);
            
            System.out.println(datenowSql.toString());
            
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName, pFontHeader2),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Ageing  Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    double Totals = 0.00;
                    double OS = 0.00;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(ageingDates.length+5);
                        
                        String headerWidths = null;
                        
                        java.util.Vector headerVector = new java.util.Vector(1,1);
                        
                        int z = ageingDates.length;
                        
                        
                        int headerwidths[] = {7,35,13,13,13,13,13,13,13};//,13,13};
                        
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((107));
                        
                        table.setHeaderRows(2);
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(9);
                        Phrase phrase = new Phrase("");
                        table.getDefaultCell().setColspan(6);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            //                            java.util.Date endDate1 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
                            
                            phrase = new Phrase("Debtors Ageing as at " +dateFormat.format(endDate2), pFontHeader);
                            
                            table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Printed on : "+date,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("No.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("Name",pFontHeader);
                        table.addCell(phrase);
                        
                        for (int x = 0; x < ageingDates.length; x++) {
                            //for (int x = ageingDates.length - 1; x >= 0; x--) {
                            
                            
                            
                            int days = 30;
                            if (x < 1) {
                                phrase = new Phrase("Current",pFontHeader);
                            } else {
                                phrase = new Phrase("+ "+x*days +" Days",pFontHeader);
                                interval = x;
                            }
                            
                            table.addCell(phrase);
                            
                        }
                        
                        phrase = new Phrase("+ "+((interval + 1) * 30)+" Days",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("UnAlloc.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("O/S "+ks,pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            double GrandTotal = 0.00;
                            double Over120Total = 0.00;
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            System.out.println(listofAct.length);
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double TurnOver = 0.00;
                                double Over120 = 0.00;
                                double TotalCount = 0.00;
                                double unalloc = 0.00;
                                double Curr120 = 0.00;
                                
                                double bals = 0.00;
                                // java.lang.Object[] listofActinv = this.getListofActivitiesinv(listofAct[i]);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                java.sql.Statement stmta1 = connectDB.createStatement();
                                java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(account_no) FROM ac_debtors where  account_no = '"+listofAct[i]+"'");
                                java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select distinct account_no||' '||scheme_name from ac_schemes WHERE account_no = ? ");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                pset22.setString(1,listofAct[i].toString().toUpperCase());
                                java.sql.ResultSet rSeta1 = pSeta1.executeQuery();
                                while (rSeta1.next()) {
                                    name = rSeta1.getInt(1);
                                    
                                }
                             //   if (name > 0){
                                    java.sql.ResultSet rset22 = pset22.executeQuery();
                                    
                                    while (rset22.next()){
                                        cnt = cnt + 1;
                                        phrase = new Phrase(""+cnt,pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"),pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        
                                        java.sql.Statement st2 = connectDB.createStatement();
                                        java.sql.Statement st21 = connectDB.createStatement();
                                        java.sql.Statement st22 = connectDB.createStatement();
                                        java.sql.Statement st23 = connectDB.createStatement();
                                        java.sql.Statement st211 = connectDB.createStatement();
                                        java.sql.Statement st221 = connectDB.createStatement();
                                        java.sql.Statement st231 = connectDB.createStatement();
                                        java.sql.Statement st2A = connectDB.createStatement();
                                        java.sql.Statement st2B = connectDB.createStatement();
                                        java.sql.Statement st2C = connectDB.createStatement();
                                        java.sql.Statement stc = connectDB.createStatement();
                                        System.out.println("Dealer Is : ["+listofAct[i]+"].");
                                        java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select sum(debit-credit) from allocations_summary WHERE account_no = ?  AND date <= '"+endDate+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                        pset111.setString(1,listofAct[i].toString());
                                        java.sql.ResultSet rset111 = pset111.executeQuery();
                                        
                                        
                                        
                                        java.sql.Statement st02 = connectDB.createStatement();
                                       // java.sql.PreparedStatement pset112 = connectDB.prepareStatement("select sum(credit) from ac_debtors WHERE account_no = ? AND date <= '"+endDate+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                        java.sql.PreparedStatement pset112 = connectDB.prepareStatement("select sum(credit) from allocations_summary WHERE account_no = ? AND date <= '"+endDate+"'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                       
                                        pset112.setString(1,listofAct[i].toString());
                                        java.sql.ResultSet rset112 = pset112.executeQuery();
                                        
                                          for (int t = ageingDates.length - 1; t >= 0; t--) {
                                          
                                            columnTotals[t] = 0.00;
                                           
                                            double credo = 0.00;
                                           
                                            double credo1 = 0.00;
                                           
                                            double debit = 0.00;
                                            
                                            Over120 = 0.00;
                                           
                                            java.sql.Statement st01 = connectDB.createStatement();
                                          //  java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(debit),invoice_no from ac_debtors WHERE account_no = ?  AND date between '"+ageingDates[t][0]+"' AND '"+ageingDates[t][1]+"'  GROUP BY invoice_no");
                                           
                                            java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(debit),invoice_no from allocations_summary WHERE account_no = ?  AND date between '"+ageingDates[t][0]+"' AND '"+ageingDates[t][1]+"'  GROUP BY invoice_no");
                                            pset.setString(1,listofAct[i].toString().toUpperCase());
                                            java.sql.ResultSet rset = pset.executeQuery();
                                            
                                            
                                            while (rset.next()){
                                                String invoiceNo = rset.getString(2);
                                                
                                                java.sql.Statement st01x = connectDB.createStatement();
                                               // java.sql.PreparedStatement psetx = connectDB.prepareStatement("select sum(credit),invoice_no from ac_debtors WHERE account_no = ?  AND date <= '"+endDate+"' AND invoice_no = '"+invoiceNo+"'  GROUP BY invoice_no");
                                                 java.sql.PreparedStatement psetx = connectDB.prepareStatement("select sum(credit),invoice_no from allocations_summary WHERE account_no = ?  AND date <= '"+endDate+"' AND invoice_no = '"+invoiceNo+"'  GROUP BY invoice_no");
                                               
                                                psetx.setString(1,listofAct[i].toString().toUpperCase());
                                                java.sql.ResultSet rsetx = psetx.executeQuery();
                                                while (rsetx.next()){
                                                    credo = rsetx.getDouble(1);
                                                    debit = rset.getDouble(1);
                                                    //credo = 0.00;
                                                }
                                            }
                                                
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                columnTotals[t] = columnTotals[t] + (debit - credo);
                                                TotalCount = TotalCount + (debit - credo);
                                                columnTotals2[t] = columnTotals2[t] + (debit - credo);
                                                
                                            
                                            
                                        }
                                        
                                        
                                        for (int t = ageingDates.length - 1; t >= 0; t--) {
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals[t])),pFontHeader1);
                                            table.addCell(phrase);
                                            
                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        
                                        while (rset111.next()){
                                            bals = rset111.getDouble(1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bals-TotalCount)),pFontHeader1);
                                            
                                            table.addCell(phrase);
                                            
                                            Over120Total = Over120Total + (bals-TotalCount);
                                           
                                            TurnOver = TotalCount + Curr120;
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(bals-bals)),pFontHeader1);
                                            table.addCell(phrase);
                                            OS = OS + unalloc;
                                            
                                            
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset111.getString(1)),pFontHeader1);
                                            table.addCell(phrase);
                                            
                                            Totals = Totals + rset111.getDouble(1) ;
                                        }
                                        
                                    }
                                    
                               // }
                                
                            }
                            
                              
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            for (int x = ageingDates.length - 1; x >= 0; x--) {
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(columnTotals2[x])), pFontHeader);
                                
                                table.addCell(phrase);
                                grandTot = grandTot + columnTotals2[x];
                            }
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals - grandTot)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(OS)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(Totals)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            
                            docPdf.add(table);
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            SqlExec.printStackTrace();
                            
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
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            if(balnc.equalsIgnoreCase("both")){
               // java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no,sum(debit-credit) as balance FROM allocations_summary WHERE date::date <= '"+endDate+"' and account_no is not null group by account_no HAVING sum(debit-credit) <> 0 order by 2 desc");
                java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT CASE WHEN (account_no IS NULL) THEN '-' ELSE account_no END AS account_no,sum(debit-credit) FROM allocations_summary WHERE date::date <= '"+endDate+"' group by account_no HAVING sum(debit-credit) > 0 order by 1 desc");
                
                //   java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal <> 0 order by account_no");
                java.sql.ResultSet rSet1 = pSet1.executeQuery();
                while (rSet1.next()) {
                    System.out.println(rSet1.getObject(1).toString());
                    listActVector.addElement(rSet1.getObject(1).toString());
                    
                }
            }else{
                if(balnc.equalsIgnoreCase("neg")){
                    //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal < 0 order by account_no");
                    
                    java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT CASE WHEN (account_no IS NULL) THEN '-' ELSE account_no END AS account_no,sum(debit-credit) as balance FROM ac_debtors WHERE date::date <= '"+endDate+"'  group by account_no HAVING sum(debit-credit) < 0 order by 2 desc");
                    java.sql.ResultSet rSet1 = pSet1.executeQuery();
                    while (rSet1.next()) {
                        System.out.println(rSet1.getObject(1).toString());
                        listActVector.addElement(rSet1.getObject(1).toString());
                        
                    }
                }else{
                    java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT CASE WHEN (account_no IS NULL) THEN '-' ELSE account_no END AS account_no,sum(debit-credit) as balance FROM ac_debtors  WHERE date::date <= '"+endDate+"' group by account_no HAVING sum(debit-credit) > 0 order by 2 desc");
                    
                    // java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal > 0 order by account_no");
                    java.sql.ResultSet rSet1 = pSet1.executeQuery();
                    while (rSet1.next()) {
                        System.out.println(rSet1.getObject(1).toString());
                        listActVector.addElement(rSet1.getObject(1).toString());
                        
                    }
                }
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
        
    }
    
    
  /*  public java.lang.Object[] getListofActivitiesinv(java.lang.Object invNo) {
   
        java.lang.Object[] listofActivitiesinv = null;
   
        java.util.Vector listActVectorinv = new java.util.Vector(1,1);
   
   
        try {
   
            java.sql.Statement stmt1 = connectDB.createStatement();
            if(balnc.equalsIgnoreCase("both")){
                java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT invoice_no FROM ac_debtors WHERE date::date <= '"+endDate+"' and account_no  = '"+invNo+"'");
                java.sql.ResultSet rSet1 = pSet1.executeQuery();
                while (rSet1.next()) {
                    System.out.println(rSet1.getObject(1).toString());
                    listActVectorinv.addElement(rSet1.getObject(1).toString());
   
                }
            }
            /*}else{
                if(balnc.equalsIgnoreCase("neg")){
                    //  java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal < 0 order by account_no");
   
                    java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no,sum(debit-credit) as balance FROM ac_debtors WHERE date::date <= '"+endDate+"'  group by account_no HAVING sum(balance-credit_bal) < 0 order by 2 desc");
                    java.sql.ResultSet rSet1 = pSet1.executeQuery();
                    while (rSet1.next()) {
                        System.out.println(rSet1.getObject(1).toString());
                        listActVector.addElement(rSet1.getObject(1).toString());
   
                    }
                }else{
                    java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no,sum(debit-credit) as balance FROM ac_debtors  WHERE date::date <= '"+endDate+"' group by account_no HAVING sum(balance-credit_bal) > 0 order by 2 desc");
   
                    // java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT account_no FROM debtors_account where account_no IS NOT NULL and account_no !='' and bal > 0 order by account_no");
                    java.sql.ResultSet rSet1 = pSet1.executeQuery();
                    while (rSet1.next()) {
                        System.out.println(rSet1.getObject(1).toString());
                        listActVector.addElement(rSet1.getObject(1).toString());
   
                    }
                }
            }
   
        }catch (java.sql.SQLException sqlExec) {
   
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
   
        }
   
        listofActivitiesinv = listActVectorinv.toArray();
        System.out.println("Done list of activitiesinv ...");
        return listofActivitiesinv;
   
    }*/
}





