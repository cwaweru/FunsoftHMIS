//Author Charles Waweru && Lewis Maina && Amimo Benja 

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospinventory;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class AnalysisNutriDepartReportsPdf implements java.lang.Runnable {
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
    
    java.util.Vector description,debit;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    java.util.Date beginDate1 = null;
    
    java.util.Date endDate1 = null;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    java.lang.String sum1, sum2, d2;
    double gross=0, gross1=0, gross2=0, e, rv1;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    
    java.lang.String bank = null;
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    
    public void AnalysisNutriDepartItemsPdf(java.sql.Connection connDb,java.util.Date begindate,java.util.Date endate,java.util.Date begindate1,java.util.Date endate1,java.lang.String combox) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        
        beginDate1 = begindate1;
        endDate1 = endate1;
        
        
        bank = combox;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        
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
                    String date = null, amt, amt1 = null, b = null;
                    
                    double g_total, g_total1 = 0, a = 0, c = 0;
                  
                    try {
                        
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName,pFontHeader),false);
                        
                        
                        headerFoter.setRight(7);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Nutrition Analysis - Page: ",pFontHeader), true);
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {15, 12, 12, 12};
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        try {
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setFixedHeight(70);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(16);
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset3.next()) {
                                table.getDefaultCell().setColspan(4);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                Phrase phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExect) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExect.getMessage());

                        }
                        
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Phrase  phrase = new Phrase("NUTRITION DEPARTMENT SUPERVISOR'S REPORT", pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("", pFontHeader);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                      
                           try {
                                
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                
                                
                                phrase = new Phrase(bank + " Service/Suppliment Analysis Report ", pFontHeader);// +dateFormat.format(endDate11)+" - "+dateFormat.format(endDate1), pFontHeader);
                                
                                table.addCell(phrase);
                            } catch(java.text.ParseException psExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                                
                            }
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("Printed on : " +date,pFontHeader);
                        table.addCell(phrase);
                       
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(1);
                        
                        phrase = new Phrase("Supplement/Service",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(2); 
                        phrase = new Phrase("Revenue",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Variance",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setFixedHeight(28);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Period 1: "+dateFormat.format(beginDate)+" - "+dateFormat.format(endDate),pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Period 2: "+dateFormat.format(beginDate1)+" - "+dateFormat.format(endDate1),pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        try {
                            
                            getDescriptionAndDebits();
                            
                            System.err.println("Size of description vector = "+description.size());
                            
                            for (int i=0;i<description.size();i++) {  
                                
                                java.sql.Statement st = connectDB.createStatement();
                            
                                java.sql.Statement st1 = connectDB.createStatement();
                            
                            
                                java.sql.ResultSet period1 = st.executeQuery("SELECT sum(credit - debit) AS Amount FROM ac_ledger "
                                        + "WHERE activity_code = '600-60601-60035' AND service_type='"+description.elementAt(i)+"' "
                                        + "AND date BETWEEN '"+beginDate+"' AND '"+endDate+"' "
                                        + "GROUP BY description");
                                
                                java.sql.ResultSet period2 = st1.executeQuery("SELECT sum(credit - debit) AS Amount FROM ac_ledger "
                                        + "WHERE activity_code = '600-60601-60035'AND service_type='"+description.elementAt(i)+"' "
                                        + "AND date BETWEEN '"+beginDate1+"' AND '"+endDate1+"' "
                                        + "GROUP BY description");
                                
                                int p1Rows=0;
                                
                                System.err.println("calling next1");
                                
                                while(period1.next()){
                                    
                                    p1Rows+=1;
                                    sum1=period1.getString(1);
                                    System.err.println("finished iteration "+p1Rows+" for period 1");
                                    System.err.println("Value of sum1 is "+sum1);
                                    System.err.println();
                                }
                                
                                int p2Rows=0;
                                
                                System.err.println("calling next2");
                                
                                while(period2.next()){
                                    p2Rows+=1;
                                    sum2=period2.getString(1);
                                    System.err.println("finished iteration "+p2Rows+" for period 2");
                                    System.err.println("Value of sum2 is "+sum2);
                                    System.err.println();
                                }
                                
                                if(p1Rows==0){
                                    
                                    sum1="0";
                                    System.err.println("The resultSet for period 1 has no rows. The value of sum1 is "+sum1);
                                    
                                }
                                
                                if (p2Rows==0){
                                    sum2="0";
                                    
                                    System.err.println("The resultSet for period 2 has no rows. The value of sum2 is "+sum2);
                                    
                                    
                                   
                                }
                                
                                addITextTableRow(table, (String)description.elementAt(i), sum1, sum2);
                                
                                
                            }
                           
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gross Total", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency()
                                    .Format2Currency(java.lang.String.valueOf(gross1+"")),pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency()
                                    .Format2Currency(java.lang.String.valueOf(gross2+"")),pFontHeader);
                            table.addCell(phrase);
                                
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency()
                                    .Format2Currency(java.lang.String.valueOf(gross+""))+" - ("+d2+"%)",pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setFixedHeight(35);
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("\n\nComments...................................."
                                                + "............................................."
                                                + "............................................."
                                                + ".........................................",pFontHeader);
                            table.addCell(phrase);
                            
                            try {               
                                
                                java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access where login_name = current_user order by 1 limit 1), date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                                java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                                    while (rsetUser.next()) {
                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Complied By : ".toUpperCase() + rsetUser.getString(1), pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Signature : " , pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(4);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Date : ".toUpperCase() + rsetUser.getString(2), pFontHeader);
                                        table.addCell(phrase);

                                    }

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                            
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
    
    public void getDescriptionAndDebits() throws java.sql.SQLException{
        
        System.err.println("getDescriptionAndDebits()");
        
        description=new java.util.Vector();
        debit=new java.util.Vector();
        
        java.sql.Statement statement=connectDB.createStatement();
        
        java.sql.ResultSet resultSet=statement.executeQuery("SELECT DISTINCT service_type FROM ac_ledger"
                + " WHERE activity_code='600-60601-60035' ORDER BY service_type");
        
        System.err.println("getDescriptionAndDebits() query has run!");
        
        while(resultSet.next()){
            description.add(resultSet.getString(1));
        }
        
        System.err.println("Ready to exit getDescriptionAndDebits()");
        
    }
    
    public void addITextTableRow(com.lowagie.text.pdf.PdfPTable table,java.lang.String service,
            java.lang.String amtPeriod1,java.lang.String amtPeriod2){
        com.lowagie.text.Phrase phrase;
        double variance=0, rv, c1;
        
        String b, d1;
        
        System.err.println("Now populating.....please wait...");
        
        table.getDefaultCell().setFixedHeight(18);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
        phrase = new Phrase(service, pFontHeader1);
        table.addCell(phrase);
                                
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(amtPeriod1),pFontHeader);
        table.addCell(phrase);
                                
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(amtPeriod2),pFontHeader);
        table.addCell(phrase);
        
        variance=Double.parseDouble(amtPeriod2)-Double.parseDouble(amtPeriod1);

        b = Double.toString(variance);
        
        if (Double.parseDouble(amtPeriod1) == 0.00) {
            if (Double.parseDouble(amtPeriod2)==0.00) {
                d1 = "0";
            } else {
                d1 = "100";
            }
        } else {
            c1 = ((variance/Double.parseDouble(amtPeriod1))*100);
                                            
            rv = (double) Math.round(c1 * 100) / 100;
                                            
            d1 = Double.toString(rv);
        }                                 
        
                                
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(b) +"  -  ("+d1+"%)",pFontHeader);
        table.addCell(phrase);
        
        gross1+=Double.parseDouble(amtPeriod1);
        gross2+=Double.parseDouble(amtPeriod2);
        gross+=variance;
        
        e = (((gross2 - gross1)/gross1)*100);
        
        rv1 = (double) Math.round(e * 100) / 100;    
        
        
        if (gross1==0) {
            if (gross2==0) {
                d2 = "0";
            } else {
                d2 = "100";
            }
        } else {
            d2 = Double.toString(rv1);
        }
    }
    
}




