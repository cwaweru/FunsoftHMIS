//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospayroll;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class NhifPdf implements java.lang.Runnable {
    
    java.lang.String beginDate = null;
    
    java.lang.String bank = null;
    
    java.lang.String endDate = null;
    
    int numberSeq = 0;
    
    double total = 0.00;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void NhifPdf(java.sql.Connection connDb,java.lang.String begindate,java.lang.String endate,java.lang.String combox) {
        //public void OutstOrdersPdf(java.sql.Connection connDb) {
        
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        bank=combox;
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
                    
                    
                    
                    try {
                        
                        java.lang.Class.forName("org.postgresql.Driver");
                        
                    } catch(java.lang.ClassNotFoundException cnfExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());
                        
                    }
                    
                    
                    
                    String compName = null;
                    String date = null;
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("NHIF List - Page: ",pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    //   docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
                        
                        int headerwidths[] = {40,20,20,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        // table.setHeaderRows(1);
                        
                        
                        
                        Phrase phrase;
                        table.getDefaultCell().setColspan(4);
                        try {
                            
                            try {
                                
                                //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st4 = connectDB.createStatement();
                                
                                java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,nhif_no from pb_hospitalprofile");
                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT institution_name,branch from statutory_institutions where (deduction_type ILIKE 'NHIF%' OR deduction_type ILIKE 'N.H.I.%' )");
                                
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MONTH_FIELD);//MEDIUM);
                                
                                java.util.Date formattedDate = dateFormat.parse(endDate);//dateInstance.toLocaleString());
                                
                                while(rset2.next()){
                                    while(rset4.next()){
                                        
                                         table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER); 
                                        phrase = new Phrase(""+rset2.getString(1).toUpperCase(), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase("TO :  "+rset4.getString(1).toUpperCase()+"   "+rset4.getString(2).toUpperCase(), pFontHeader);
                                        
                                       // table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT); 
                                        phrase = new Phrase("NHIF MONTHLY CONTRIBUTIONS :  ", pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase(dateFormat.format(formattedDate), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase("EMPLOYER NAME :  "+rset2.getString(1).toUpperCase(), pFontHeader);
                                        
                                        table.addCell(phrase);
                                        
                                        phrase = new Phrase("CODE NO.  "+rset2.getString(2).toUpperCase(), pFontHeader);
                                        
                                        table.addCell(phrase);
                                    }
                                }
                                
                                table.getDefaultCell().setColspan(4);
                                
                                
                                phrase = new Phrase("      ", pFontHeader1);
                                
                                table.addCell(phrase);
                                
                                
                            } catch(java.sql.SQLException SqlExec) {
                                
                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                                
                            }
                            
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        
                        
                        docPdf.add(table);
                        
                        
                        
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {9,9,40,20,20,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        table.setHeaderRows(1);
                        
                        
                        table.getDefaultCell().setColspan(2);
                        
                        Phrase phrase;
                        
                        table.getDefaultCell().setColspan(1);
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER); 
                       phrase = new Phrase("No.",pFontHeader);
                        table.addCell(phrase);
                        
                       table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER); 
                       phrase = new Phrase("Staff No.",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("EMPLOYEE NAME",pFontHeader);
                        table.addCell(phrase);
                        
                        phrase = new Phrase("I.D No.",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        phrase = new Phrase("N.H.I.F No.",pFontHeader);
                        table.addCell(phrase);
                        
                        
                      //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("AMOUNT KShs",pFontHeader);
                        table.addCell(phrase);
                        
                        
                        
                        
                        
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rsetTotals = st3.executeQuery("SELECT SUM(amount) from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'NhiF%' or description ilike 'N.h.i%')");
                            
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            
                            for (int j = 0; j < listofStaffNos.length; j++) {
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.Statement st2 = connectDB.createStatement();
                                
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct first_name||' '||middle_name||' '||last_name as name,id_no,nhif_no from master_file where employee_no = '"+listofStaffNos[j]+"' UNION ALL select distinct first_name||' '||middle_name||' '||last_name as name,id_no,nhif_no from pb_other_staff WHERE staff_no = '"+listofStaffNos[j]+"' order by name");
                                
                                java.sql.ResultSet rset1 = st2.executeQuery("SELECT sum(amount) from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND staff_no = '"+listofStaffNos[j]+"'  AND (description ILIKE 'NhiF%' or description ilike 'N.h.i%') UNION ALL SELECT sum(nhif_amount) from pb_other_staff WHERE staff_no = '"+listofStaffNos[j]+"'  group by staff_no");
                                
                                
                                
                                while (rset.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                  
                                    numberSeq = numberSeq+1;
                                    
                                    phrase = new Phrase(""+numberSeq+"   ", pFontHeader1);
                                    table.addCell(phrase);
                                   
                                    phrase = new Phrase(""+listofStaffNos[j]+"   ", pFontHeader1);
                                    table.addCell(phrase);
                                 
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(1).toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                   
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(2).toString().toUpperCase(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(rset.getObject(3).toString(), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                }
                                    while (rset1.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(1)),pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    total = total+rset1.getDouble(1);
                                    
                                
                                }
                                
                            }
                            
                            while (rsetTotals.next()) {
                                
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TOTAL DUE FOR PAYMENT", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("CH.", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                phrase = new Phrase("KSHs.", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                               
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(total)), pFontHeader);
                                
                             //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);
                                
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
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT staff_no from posting WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND (description ILIKE 'NHIF%' OR description ILIKE 'N.H.I.F%') and company_name ilike '"+bank+"' order by 1");
            
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
}





