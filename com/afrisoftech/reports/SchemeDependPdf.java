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


public class SchemeDependPdf implements java.lang.Runnable {
    java.lang.String memNo = null;
    
    java.lang.String memNo1 = null;
    
    java.lang.String memNo3 = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    String ks;
    com.lowagie.text.HeaderFooter headerFoter;
    
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    //  public void FinalPatientInvoicePdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void SchemeDependPdf(java.sql.Connection connDb, java.lang.String combox,java.lang.String combox1,java.lang.String combox3) {
        
        dbObject = new com.afrisoftech.lib.DBObject();
        
        memNo1 = combox1;
        memNo = combox;
        memNo3 = combox3;
        connectDB = connDb;
        
        //   beginDate = begindate;
        
        // endDate = endate;
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
            
            this.generatePdf(memNo);
            
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
            double osBalance1 = 0.00;
            double osBalance = 0.00;
            double current = 0.00;
            double osBalancebf = 0.00;
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    String compName = null;
                    String date = null;
                    String Messg = null;
                    
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st2x = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while(rset2x.next()){
                            ks = rset2x.getObject(1).toString();
                        }  java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        
                        
                        
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        // com.lowagie.textHeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(""+Messg+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        
                        
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    
               
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Schemes Dependants List - Page: ", pFontHeader1), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    //  docPdf.open();
                    docPdf.open();
                    
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {15,15,30,15,15,15,15};
                        
                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));
                        
                        
                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table1.getDefaultCell().setColspan(7);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()){
                                table1.getDefaultCell().setColspan(7);
                            
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                            table1.addCell(phrase);
                            }
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        docPdf.add(table1);
                    } catch(com.lowagie.text.BadElementException BadElExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
                        
                    }
                    
                    //  } catch(java.io.FileNotFoundException fnfExec) {
                    
                    //  javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());
                    
                    /// }
                    try {
                        
                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        
                        long dateNow = calendar.getTimeInMillis();
                        
                        java.sql.Date datenowSql= new java.sql.Date(dateNow);
                        
                        System.out.println(datenowSql.toString());
                        
                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(3);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);
                        
                        // table.endHeaders();
                        
                        int headerwidths[] = {50,20,30};
                        
                        table.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        table.setHeaderRows(6);
                        //  }
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        table.getDefaultCell().setColspan(3);
                        
                        Phrase phrase = new Phrase();
                        
                        //  table.addCell(phrase);
                        
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st41 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            //  java.sql.ResultSet rset4 = st4.executeQuery("select scheme_manager from ac_scheme_providers where scheme_manager = '"+memNo+"'");
                            java.sql.ResultSet rset4 = st4.executeQuery("select scheme_name,account_no from ac_schemes where scheme_name = '"+memNo1+"'");
                            java.sql.ResultSet rset41 = st41.executeQuery("select payer_name from ac_schemes where scheme_name = '"+memNo1+"'");
                            //   java.sql.ResultSet rset1 = st1.executeQuery("select ac.date,pc.scheme_staff_no, ac.payee,ac.reference_no,sum(ac.debit),ac.admission_no from hp_patient_card pc,ac_debtors ac where pc.patient_no = ac.admission_no and ac.dealer = '"+memNo+"' and ac.dealer = pc.scheme AND ac.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and ac.debit > 0 group by ac.date,pc.scheme_staff_no, ac.payee,ac.reference_no,ac.admission_no");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                            
                            java.sql.ResultSet rset1 = st1.executeQuery("select name,relation,birth_date from pb_scheme_dependants where member_no = '"+memNo+"' and company = '"+memNo1+"' order by name");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                            java.sql.ResultSet rset11 = st11.executeQuery("select count(member_no) from pb_scheme_dependants where member_no = '"+memNo+"' and company = '"+memNo1+"'");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                            
                            //java.sql.ResultSet rsetTotals2 = st22.executeQuery("select sum(debit),sum(balance) from ac_debtors where account_no = '"+memNo+"' AND date::date < '"+beginDate+"'");
                            
                            //  java.sql.ResultSet rset1 = st1.executeQuery("select pc.date::date,pc.scheme_staff_no, initcap(sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pc.reference,sum(pc.credit),pc.patient_no from hp_patient_card pc,hp_patient_register sh where pc.scheme_staff_no = sh.account_no and pc.isurer = '"+memNo+"' AND pc.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pc.credit > 0 group by pc.date::date,pc.scheme_staff_no, name,pc.reference,pc.invoice_no,pc.patient_no");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
                            // java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit),sum(credit) from ac_debtors where account_no = '"+memNo+"' AND date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP | Rectangle.BOTTOM);
                            
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Scheme Dependants", pFontHeader11);
                            table.addCell(phrase);
                            
                            
                            while (rset4.next()){
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset4.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Account No : " +rset4.getObject(2).toString(), pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                            while (rset41.next()){
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset41.getString(1), pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                    
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Staff No." + memNo + " Staff Name " + memNo3,pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Print Date " +date, pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Name",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            
                            
                            phrase = new Phrase("Relation",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Birth Date",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            
                          
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
                                
                                
                                
                            }
                            table.getDefaultCell().setColspan(3);
                            
                            phrase = new Phrase(" ", pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                            
                            //  while (rsetTotals.next()) {
                            
                            
                            while (rset11.next()){
                                table.getDefaultCell().setColspan(3);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Count : " +rset11.getObject(1).toString(), pFontHeader1);
                                table.addCell(phrase);
                            }
                            
                     
                            
                            docPdf.add(table);
                            
                            
                            
                        } catch(java.sql.SQLException SqlExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        
                        // }  // }
                        
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
    public java.lang.Object[] getListofStaffNos() {
        
        java.lang.Object[] listofStaffNos = null;
        
        java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
        
   /*
        try {
    
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
            java.sql.Statement stmt1 = connectDB.createStatement();
    
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT admission_no FROM ac_debtors WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND invoice_no IS NOT NULL and admission_no IS NOT NULL");
    
            while (rSet1.next()) {
    
                listStaffNoVector.addElement(rSet1.getObject(1).toString());
    
            }
    
        }catch (java.sql.SQLException sqlExec) {
    
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
        }
    */
        
        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
    
    
    
    
}





