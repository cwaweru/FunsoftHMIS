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

//import //java.awt.Desktop;
public class XrayPerfPdf implements java.lang.Runnable {
     ////java.awt.Desktop deskTop = Desktop.getDesktop();
    public static java.sql.Connection connectDB = null;
    java.lang.String bank = null;
      java.lang.String Code = null;
    
    
     java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
        com.afrisoftech.lib.DBObject dbObject;
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void XrayPerfPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox,java.lang.String code) {
     
   dbObject = new com.afrisoftech.lib.DBObject();
        
  
        bank = combox;
        Code = code;
        connectDB = connDb;
        
        beginDate = begindate;
        
        endDate = endate;
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //	new CashBookListPdf().CashBookListPdf();
        
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
                    
                    
                    
     /*    try {
      
            java.lang.Class.forName("org.postgresql.Driver");
      
        } catch(java.lang.ClassNotFoundException cnfExec) {
      
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
      
        }
      */
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while(rset2.next())
                            compName = rset2.getObject(1).toString();
                        
                        while(rset4.next())
                            date = rset4.getObject(1).toString();
                        
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName,pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                       // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                         headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                         headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Departmental Rev - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                     double osBalance1 = 0.00;
                     double osBalancet = 0.00;
           
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);
                        
                        int headerwidths[] = {10,10,10,10,10,10,10,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(2);
                         table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                       
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM|Rectangle.TOP|Rectangle.LEFT|Rectangle.RIGHT);
                        
                        table.getDefaultCell().setColspan(9);
                         Phrase phrase = new Phrase("", pFontHeader);
                            
                            // double osBalance = 0.00;
                   
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                
                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                                
                                System.out.println(""+endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                                
                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                
                                phrase = new Phrase(bank +"    Revenue Report"    +"   Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader);
                                
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
                         table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        
                        table.getDefaultCell().setColspan(1);
                       
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        phrase = new Phrase("Description",pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Total",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                       
                        
                         phrase = new Phrase("ECG",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                        phrase = new Phrase("Ultra Sound",pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Xray",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                        phrase = new Phrase("-",pFontHeader);
                        table.addCell(phrase);
                        
                         phrase = new Phrase("No",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                        phrase = new Phrase("Amount",pFontHeader);
                        table.addCell(phrase);
                        
                         phrase = new Phrase("No",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                        phrase = new Phrase("Amount",pFontHeader);
                        table.addCell(phrase);
                         phrase = new Phrase("No",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                        phrase = new Phrase("Amount",pFontHeader);
                        table.addCell(phrase);
                         phrase = new Phrase("No",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        
                        //     table.addCell("Reference No.");
                        
                        phrase = new Phrase("Amount",pFontHeader);
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            
                            //  java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            
                            java.sql.Statement st = connectDB.createStatement();
                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rset = st.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' AND drawer ilike 'oP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                             java.sql.Statement st2eo = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rseteo = st2eo.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type ilike 'e.c.g%' AND drawer ilike 'oP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                           
                              java.sql.Statement st2uo = connectDB.createStatement();
                            
                                java.sql.ResultSet rsetuo = st2uo.executeQuery("select COUNT(ac_ledger.service_type),sum(credit-debit) from ac_ledger,pb_operating_parameters WHERE ac_ledger.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and code ilike 'us%' AND drawer ilike 'OP' and gl_account =activity_code and ac_ledger.service_type ilike pb_operating_parameters.service_type");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                        
                            //  java.sql.ResultSet rsetuo = st2uo.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type ilike '%u/s%' AND drawer ilike 'oP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                             java.sql.Statement st2xo = connectDB.createStatement();
                            
                            
                            
                            //java.sql.ResultSet rsetxo = st2xo.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type not ilike 'e.c.g%' and not service_type ilike '%u/s%' AND drawer ilike 'oP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                              java.sql.ResultSet rsetxo = st2xo.executeQuery("select COUNT(ac_ledger.service_type),sum(credit-debit) from ac_ledger,pb_operating_parameters WHERE ac_ledger.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and ac_ledger.service_type not ilike 'e.c.g%'  and gl_account =activity_code  and code not ilike 'us%' and  drawer ilike 'oP' and ac_ledger.service_type ilike pb_operating_parameters.service_type");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                       
                           
                            
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase("OP", pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 //phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                            }
                                                           
                                while (rseteo.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rseteo.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rseteo.getObject(2), "-"), pFontHeader1);
                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rseteo.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                             while (rsetuo.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetuo.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetuo.getObject(2), "-"), pFontHeader1);
                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetuo.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                            
                             while (rsetxo.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetxo.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetxo.getObject(2), "-"), pFontHeader1);
                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetxo.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                            
                            
                            
                             java.sql.Statement stI = connectDB.createStatement();
                            
                           // java.sql.Statement st2 = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rsetI = stI.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' AND drawer ilike 'IP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                             java.sql.Statement st2eoI = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rseteoI = st2eoI.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type ilike 'e.c.g%' AND drawer ilike 'IP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                           
                              java.sql.Statement st2uoI = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rsetuoI = st2uoI.executeQuery("select COUNT(ac_ledger.service_type),sum(credit-debit) from ac_ledger,pb_operating_parameters WHERE ac_ledger.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and code ilike 'us%' AND drawer ilike 'IP' and gl_account =activity_code and ac_ledger.service_type ilike pb_operating_parameters.service_type");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                             java.sql.Statement st2xoI = connectDB.createStatement();
                            
                            
                            
                           // java.sql.ResultSet rsetxoI = st2xoI.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type not ilike 'e.c.g%' and not service_type ilike '%u/s%' AND drawer ilike 'IP'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                              java.sql.ResultSet rsetxoI = st2xoI.executeQuery("select COUNT(ac_ledger.service_type),sum(credit-debit) from ac_ledger,pb_operating_parameters WHERE ac_ledger.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and ac_ledger.service_type not ilike 'e.c.g%'  and gl_account =activity_code  and code not ilike 'us%' and drawer ilike 'IP' and ac_ledger.service_type ilike pb_operating_parameters.service_type");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                       
                           
                            
                            while (rsetI.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase("IP", pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetI.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetI.getObject(2), "-"), pFontHeader1);
                                //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetI.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                            }
                                                           
                                while (rseteoI.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rseteoI.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rseteoI.getObject(2), "-"), pFontHeader1);
                               //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rseteoI.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                             while (rsetuoI.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetuoI.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                  phrase = new Phrase(dbObject.getDBObject(rsetuoI.getObject(2), "-"), pFontHeader1);
                               //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetuoI.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                            
                             while (rsetxoI.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetxoI.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetxoI.getObject(2), "-"), pFontHeader1);
                              //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetxoI.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                            
                            
                             java.sql.Statement stt = connectDB.createStatement();
                            
                           // java.sql.Statement st2 = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rsett = stt.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and (drawer ilike 'IP' or drawer ilike 'oP')");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                             java.sql.Statement st2eot = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rseteot = st2eot.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type ilike 'e.c.g%' and (drawer ilike 'IP' or drawer ilike 'oP')");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                           
                              java.sql.Statement st2uot = connectDB.createStatement();
                            
                            
                              java.sql.ResultSet rsetuot = st2uot.executeQuery("select COUNT(ac_ledger.service_type),sum(credit-debit) from ac_ledger,pb_operating_parameters WHERE ac_ledger.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and code ilike 'us%' AND (drawer ilike 'IP' or drawer ilike 'oP') and gl_account =activity_code and ac_ledger.service_type ilike pb_operating_parameters.service_type");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                         
                           // java.sql.ResultSet rsetuot = st2uot.executeQuery("select COUNT(service_type),sum(credit-debit) from ac_ledger WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and service_type ilike '%u/s%' and (drawer ilike 'IP' or drawer ilike 'oP')");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                             java.sql.Statement st2xot = connectDB.createStatement();
                            
                            
                            
                            java.sql.ResultSet rsetxot = st2xot.executeQuery("select COUNT(ac_ledger.service_type),sum(credit-debit) from ac_ledger,pb_operating_parameters WHERE ac_ledger.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND activity_code ilike '"+Code+"' and ac_ledger.service_type not ilike 'e.c.g%'  and gl_account =activity_code  and code not ilike 'us%' and (drawer ilike 'IP' or drawer ilike 'oP') and ac_ledger.service_type ilike pb_operating_parameters.service_type");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            
                           
                            
                            while (rsett.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase("Total", pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsett.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsett.getObject(2), "-"), pFontHeader1);
                               //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsett.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                            }
                                                           
                                while (rseteot.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rseteot.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rseteot.getObject(2), "-"), pFontHeader1);
                              // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rseteot.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                             while (rsetuot.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetuot.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetuot.getObject(2), "-"), pFontHeader1);
                               //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetuot.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
                                }
                            
                             while (rsetxot.next()) {
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                               // phrase = new Phrase(rset.getObject(1).toString(),pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetxot.getObject(1), "-"), pFontHeader);
                              
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                              //  phrase = new Phrase(rset.getObject(2).toString(), pFontHeader1);
                                 phrase = new Phrase(dbObject.getDBObject(rsetxot.getObject(2), "-"), pFontHeader1);
                               // phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetxot.getObject(2).toString()),pFontHeader);
                            
                             
                                table.addCell(phrase);
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
            
            docPdf.close();
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
}





