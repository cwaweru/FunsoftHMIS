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


public class DetailedDiagPerPatPdf implements java.lang.Runnable {
    java.lang.String memNo = null;
    java.lang.String UserName = null;
    
    java.lang.String CashPoint = null;
    
    java.lang.String patName = null;
    
    java.util.Date beginDate = null;
    
    com.afrisoftech.lib.DBObject dbObject;
    
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    public void DetailedDiagPerPatPdf(java.sql.Connection connDb, java.lang.String cashPoint,java.lang.String names) {
        
        //  public void ShiftReportDetailedPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint) {
        //     memNo = combox;
        //beginDate = begindate;
        
        //endDate = endate;
        
        CashPoint = cashPoint;
        
        connectDB = connDb;
        
        patName = names;
        
        //  beginDate = begindate;
        
        /// endDate = endate;
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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient Card - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    double amt = 0.00;
                    int counTotal = 0;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                        
                        int headerwidths[] = {11,20,10,20,7,10,11};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        table.setHeaderRows(2);
                        Phrase phrase = new Phrase(" ", pFontHeader);
                        
                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                            
                            
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            
                            phrase = new Phrase("PATIENT CARD  [ DIAGNOSIS ]" , pFontHeader);
                            
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                            table.addCell(phrase);
                     
                        // phrase = new Phrase("SHIFT No. FROM :     CASHIER : ", pFontHeader);
                        ///table.addCell(phrase);
                        
                        
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {
                            //     java.lang.Object[] listofAct11 = this.getListofActivities11();
                            
                            //   java.lang.Object[] listofAct1 = this.getListofActivities1();
                            //  System.out.println(listofAct.length);
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            // table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Patient No : "+CashPoint.toUpperCase(), pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Patient Name "+patName.toUpperCase(), pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Date", pFontHeader);
                            table.addCell(phrase);
                           
                            phrase = new Phrase("Description", pFontHeader);
                            table.addCell(phrase);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("Details", pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase("Service", pFontHeader);
                            // table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Qty", pFontHeader);
                            // table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Amount", pFontHeader);
                            // table.addCell(phrase);
                            
                            phrase = new Phrase("Running", pFontHeader);
                            // table.addCell(phrase);
                            
                            
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            // java.lang.Object[] listofAct1 = this.getDates();
                            //  for (int k = 0; k < listofAct1.length; k++) {
                            
                            // java.lang.Object[] listofAct1 = this.getDates();
                            // for (int k = 0; k < listofAct1.length; k++) {
                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //  phrase = new Phrase(listofAct1[k].toString().toUpperCase(), pFontHeader);
                            //  table.addCell(phrase);
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            int count = 0;
                            for (int i = 0; i < listofAct.length; i++) {
                                double subTotal = 0.00;
                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(7);
                                
                                java.sql.Statement st = connectDB.createStatement();
                                
                                java.sql.ResultSet rset = st.executeQuery("select distinct initcap(disease) from hp_patient_diagnosis where date_recorded = '"+listofAct[i].toString()+"' AND patient_no = '"+CashPoint+"'");
                                while (rset.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Disease : ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(5);
                                    phrase = new Phrase(rset.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                java.sql.Statement st52x = connectDB.createStatement();
                                
                                java.sql.ResultSet rset2x = st52x.executeQuery("select distinct pat_condition from doctors_notes where date = '"+listofAct[i].toString()+"' AND patient_no = '"+CashPoint+"'");
                                while (rset2x.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Observations : ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(5);
                                    phrase = new Phrase(rset2x.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                
                                java.sql.Statement st52xx = connectDB.createStatement();
                                
                                java.sql.ResultSet rset2xx = st52xx.executeQuery("select distinct doc_diagnosis from doctors_notes where date = '"+listofAct[i].toString()+"' AND patient_no = '"+CashPoint+"'");
                                while (rset2xx.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Diagnosis : ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(5);
                                    phrase = new Phrase(rset2xx.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                java.sql.Statement st52xxx = connectDB.createStatement();
                                java.sql.ResultSet rset2xxx = st52xxx.executeQuery("select distinct initcap(doctor) from doctors_notes where date = '"+listofAct[i].toString()+"' AND patient_no = '"+CashPoint+"'");
                                while (rset2xxx.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Doctor : ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(5);
                                    phrase = new Phrase(rset2xxx.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                
                                int descr = 0;
                                java.sql.Statement st1 = connectDB.createStatement();
                                
                                
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader);
                                
                                
                                
                            }
                            
                            
                            
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
            
             
            
            try {
                
                if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {
                    
                    System.out.println(tempFile);
                    
                    wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");
                    
                    wait_for_Pdf2Show.waitFor();
                    
                    
                } else {
                    
                    wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);
                    
                    wait_for_Pdf2Show.waitFor();
                    
                }
                
            } catch(java.lang.InterruptedException intrExec) {
                
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());
                
            }
            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
   /* public java.lang.Object[] getDates() {
    
        java.lang.Object[] listofDates = null;
    
        java.util.Vector listofDatesVector = new java.util.Vector(1,1);
    
    
        try {
    
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
            java.sql.Statement stmt1 = connectDB.createStatement();
    
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT  FROM st_stock_item where department ilike '"+CashPoint+"' AND sub_cat_code ilike '"+Category+"' ORDER BY sub_cat_code");
    
            while (rSet1.next()) {
    
                listofDatesVector.addElement(rSet1.getObject(1).toString());
    
            }
    
        }catch (java.sql.SQLException sqlExec) {
    
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
        }
    
        listofDates = listofDatesVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofDates;
    }*/
    
    public java.lang.Object[] getListofActivities() {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT activity_code FROM ac_cash_collection where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type not ilike 'Bank%' EXCEPT select code from pb_activity where activity ilike 'Pharmacy%' ORDER BY activity_code");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ph.date FROM doctors_notes ph WHERE ph.patient_no ilike '"+CashPoint+"' ORDER BY ph.date");
            
            while (rSet1.next()) {
                
                listActVector.addElement(rSet1.getObject(1).toString().toUpperCase());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
   /* public java.lang.Object[] getListofActivities1(java.lang.Object suppName) {
    
        java.lang.Object[] listofActivities1 = null;
    
        java.util.Vector listActVector1 = new java.util.Vector(1,1);
    
    
        try {
    
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
            java.sql.Statement stmt1 = connectDB.createStatement();
    
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT receipt_no,dealer FROM ac_cash_collection where  shift_no = '"+memNo+"' and payment_mode = '"+suppName+"'  AND cash_point = '"+CashPoint+"'  order by receipt_no");
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT code FROM pb_activity order by code");
    
            while (rSet1.next()) {
    
                listActVector1.addElement(rSet1.getObject(1).toString());
    
            }
    
        }catch (java.sql.SQLException sqlExec) {
    
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
        }
    
        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities1 ...");
        return listofActivities1;
    }
    
    
    public java.lang.Object[] getListofActivities11() {
    
        java.lang.Object[] listofActivities11 = null;
    
        java.util.Vector listActVector11 = new java.util.Vector(1,1);
    
    
        try {
    
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
            java.sql.Statement stmt1 = connectDB.createStatement();
    
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT activity_code FROM ac_cash_collection where shift_no = '"+memNo+"' order by activity_code");
    
            while (rSet1.next()) {
    
                listActVector11.addElement(rSet1.getObject(1).toString().toUpperCase());
    
            }
    
        }catch (java.sql.SQLException sqlExec) {
    
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
        }
    
        listofActivities11 = listActVector11.toArray();
        System.out.println("Done list of activities11 ...");
        return listofActivities11;
    }
    */
}





