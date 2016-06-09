//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.reports;
import static com.afrisoftech.reports.FinalDescInPatientIntmlnvPdf.connectDB;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class OutPatientBillPdf implements java.lang.Runnable {
    java.lang.String MNo = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    String ks;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    double osBalance = 0.00;
    double current = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void OutPatientBillPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
        MNo = combox;
        
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
    
    
    public void generatePdf(java.lang.String memNo) {
        
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
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("OUT-Patient Invoice(interim) - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //       java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};
                        try {
                            table1.setWidths(headerwidths);
                            //  if (docPdf.getPageNumber() > 1) {
                            //  table1.setHeaderRows(4);
                            //  }
                            Phrase phrase = new Phrase();
                            table1.setWidthPercentage((100));
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setFixedHeight(70);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table1.getDefaultCell().setFixedHeight(16);
                            java.sql.PreparedStatement st321 = connectDB.prepareStatement("select header_name from pb_header");
                            java.sql.ResultSet rset3 = st321.executeQuery();
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(5);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);
                            }


                            table1.getDefaultCell().setBorder(Rectangle.BOX);

                            table1.getDefaultCell().setColspan(7);

                            //       Phrase phrase = new Phrase();

                            //  table.addCell(phrase);

                            table1.getDefaultCell().setColspan(1);
                            table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);



                            java.sql.Statement st2x = connectDB.createStatement();

                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            /* java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                             while (rset3.next()){
                             table1.getDefaultCell().setColspan(7);
                                
                             table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                             table1.addCell(phrase);
                             }
                             */
                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }


                        docPdf.add(table1);

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }


                    
                    
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {25,25,20,15,15,15};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        
                        table.getDefaultCell().setColspan(6);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st2x = connectDB.createStatement();
                            
                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while(rset2x.next()){
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.ResultSet rset3 = st3.executeQuery("SELECT header_name from pb_header");
                            
                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no from hp_patient_register where patient_no = '"+memNo+"'");
                            java.sql.ResultSet rset1 = st1.executeQuery("select date_prescribed::date,initcap(description) as service,quantity,gl_code,amount from patient_bill_scheme where patient_no = '"+memNo+"' and date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_prescribed::date");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            // java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(amount) from patient_bill where patient_no = '"+memNo+"'");
                            
                            
                            while (rset3.next()){
                                table.getDefaultCell().setColspan(6);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                           //     table.addCell(phrase);
                                
                            }
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("OUT PATIENT INTERIM INVOICE", pFontHeader1);
                            table.addCell(phrase);
                             table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.  "+memNo, pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase("Patient Name : "+dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase("P.O Box  "+dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase("Town  "+dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader);
                                table.addCell(phrase);
                                phrase = new Phrase("TEl No. "+dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader);
                                table.addCell(phrase);
                            }
                            
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Description",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Qty",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Ref",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Amt",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Bal. "+ks,pFontHeader1);
                            table.addCell(phrase);
                            
                            while (rset1.next()){
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);
                                
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(5)),pFontHeader);
                                
                                table.addCell(phrase);
                                
                                osBalance = osBalance + rset1.getDouble(5);
                                
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                //   current = current + osBalance;
                                
                                table.addCell(phrase);
                                
                                
                            }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            // while (rsetTotals.next()) {
                            
                            table.getDefaultCell().setColspan(5);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);
                            
                            //  table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                            
                            table.addCell(phrase);
                            
                            //phrase = new Phrase(" ");
                            
                            
                            
                            //  }
                            
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
            
            docPdf.close();
            docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    
    
    
    
    
}





