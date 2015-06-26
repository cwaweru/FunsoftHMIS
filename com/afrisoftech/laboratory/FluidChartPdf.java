//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.laboratory;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class FluidChartPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    java.lang.String MNo1 = null;
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    
    double balance = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void FluidChartPdf(java.sql.Connection connDb, java.lang.String combox,java.lang.String combox1) {
        MNo = combox;
        MNo1 = combox1;
        connectDB = connDb;
        
        
        threadSample = new java.lang.Thread(this,"SampleThread");
        
        System.out.println("threadSample created");
        
        threadSample.start();
        
        System.out.println("threadSample fired");
        
    }
    
    public static void main(java.lang.String[] args) {
        
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
        
    }
    
    
    public void run() {
        
        System.out.println("System has entered running mode");
        
        while (threadCheck) {
            
            System.out.println("O.K. see how we execute target program");
            
            this.generatePdf(MNo);
            
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
    
    
    public void generatePdf(java.lang.String MNo) {
        
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
                    
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Medical in Confidence - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
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
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name,current_date from pb_header");
                            while (rset3.next())
                                table1.getDefaultCell().setColspan(8);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                            table1.addCell(phrase);
                            date = rset3.getObject(2).toString();
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    double t1=0.00;
                    double t2=0.00;
                    double t3=0.00;
                    
                    double t4=0.00;
                    
                    double t5=0.00;
                    double t6=0.00;
                    double t7=0.00;
                    double t8=0.00;
                    double t9=0.00;
                    String weight = null;
                    String date1 = null;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(12);
                        
                        int headerwidths[] = {10,15,10,12,10,10,10,10,10,10,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM |Rectangle.TOP|Rectangle.RIGHT|Rectangle.LEFT);
                        
                        table.getDefaultCell().setColspan(6);
                        Phrase phrase = new Phrase("");
                        phrase = new Phrase("  ", pFontHeader);
                        
                        table.addCell(phrase);
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase("Printed on : " +date, pFontHeader);
                        
                        table.addCell(phrase);
                        //    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        try {
                            java.sql.Statement std = connectDB.createStatement();
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            //  java.sql.ResultSet rset2 = st2.executeQuery("select discharge_date,upper(ward),upper(DISCHARGE_METHOD),upper(doctor) from hp_PATIENT_DISCHARGE where WARD_CODE = '"+MNo1+"' ");
                            java.sql.ResultSet rset1 = st1.executeQuery("select DISTINCT patient_no||' '||patient_name,ward from hp_ADMISSION where PATIENT_no = '"+MNo+"'");
                            java.sql.ResultSet rsetd = std.executeQuery("select input_date,weight from hp_progress where PATIENT_no = '"+MNo+"'");
                            
                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select TIME_ATT,ant_type,amt_started,amt_inf,al_type,ngast,oral,ngsuction,vomitus,stool,drain,amount from hp_progress where PATIENT_no = '"+MNo+"'");
                            //  java.sql.ResultSet rset1 = st1.executeQuery("select date_admitted from hp_admission where visit_id = '"+MNo1+"' ");
                            table.getDefaultCell().setColspan(12);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("FLUID BALANCE SHEET", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);
                            
                            
                            while (rsetd.next()) {
                                
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                weight = rsetd.getObject(2).toString();
                                date1 = rsetd.getObject(1).toString();
                                
                            }
                            while (rset1.next()) {
                                
                                table.getDefaultCell().setColspan(7);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("NAME .  "+rset1.getObject(1).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("WARD  : "+rset1.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(12);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("USUAL WEIGHT.."+weight+"   (Kgs)....................date.."+date1+"", pFontHeader);
                                table.addCell(phrase);
                                
                                
                                
                                
                            }
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("INTAKE (IN MLS)", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("OUTPUT (IN MLS)", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TIME", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("INTRAVENOUS", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("ALIMENTARY", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("OUTPUT ", pFontHeader);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TIME", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("TYPE", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("AMOUNT STARTED", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("AMOUNT INFUSED", pFontHeader2);
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("TYPE", pFontHeader2);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("N.G", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("ORAL", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("N.G SUCTION", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("VOMITUS", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("STOOL", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("DRAIN", pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase("URINE", pFontHeader2);
                            table.addCell(phrase);
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(1).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                
                                // table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(2).toString(), pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase(rset.getObject(3).toString(), pFontHeader);
                                table.addCell(phrase);
                                t1= t1+rset.getDouble(3);
                                phrase = new Phrase(rset.getObject(4).toString(), pFontHeader);
                                table.addCell(phrase);
                                t2= t2+rset.getDouble(4);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(5).toString(), pFontHeader);
                                table.addCell(phrase);
                                
                                // table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase(rset.getObject(6).toString(), pFontHeader);
                                table.addCell(phrase);
                                t3= t3+rset.getDouble(6);
                                phrase = new Phrase(rset.getObject(7).toString(), pFontHeader);
                                table.addCell(phrase);
                                t4= t4+rset.getDouble(7);
                                phrase = new Phrase(rset.getObject(8).toString(), pFontHeader);
                                table.addCell(phrase);
                                t5= t5+rset.getDouble(8);
                                phrase = new Phrase(rset.getObject(9).toString(), pFontHeader);
                                table.addCell(phrase);
                                t6= t6+rset.getDouble(9);
                                phrase = new Phrase(rset.getObject(10).toString(), pFontHeader);
                                table.addCell(phrase);
                                t7= t7+rset.getDouble(10);
                                phrase = new Phrase(rset.getObject(11).toString(), pFontHeader);
                                table.addCell(phrase);
                                t8= t8+rset.getDouble(11);
                                phrase = new Phrase(rset.getObject(12).toString(), pFontHeader);
                                table.addCell(phrase);
                                t9= t9+rset.getDouble(12);
                            }
                            
                            
                            
                            table.getDefaultCell().setColspan(2);
                            
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //   while (rsetTotals.next()) {
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total", pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t1)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t2)), pFontHeader);
                            
                            table.addCell(phrase);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t3)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t4)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t5)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t6)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t7)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t8)), pFontHeader);
                            
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t9)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            
                            
                            table.getDefaultCell().setColspan(4);
                            
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //   while (rsetTotals.next()) {
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total Intake", pFontHeader1);
                            
                            table.addCell(phrase);
                            double inp=t2+t3+t4;
                            table.getDefaultCell().setColspan(4);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t2+t3+t4)), pFontHeader);
                            
                            table.addCell(phrase);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            
                             table.getDefaultCell().setColspan(4);
                            
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            //   while (rsetTotals.next()) {
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Total output", pFontHeader1);
                            
                            table.addCell(phrase);
                            double outp=t5+t6+t8+t9;
                            table.getDefaultCell().setColspan(4);
                             phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(t5+t6+t8+t9)), pFontHeader);
                          
                            table.addCell(phrase);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                              phrase = new Phrase("Balance", pFontHeader1);
                            
                            table.addCell(phrase);
                           // double outp=t5+t6+t8+t9;
                            table.getDefaultCell().setColspan(4);
                             phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(inp-outp)), pFontHeader);
                          
                            table.addCell(phrase);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
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
    
    
    
    
    
    
}





