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


public class PatientAnalysisPdf implements java.lang.Runnable {
    java.lang.String memNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    
    java.util.Date endDate = null;
    String ks;
    double balance = 0.00;
    public static java.sql.Connection connectDB = null;
    
    public java.lang.String dbUserName = null;
    
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    
    boolean threadCheck = true;
    
    
    //  java.lang.String memNo2Use = null;
    
    java.lang.Thread threadSample;
    
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    
    
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    
    java.lang.Process prThread;
    
    public void PatientAnalysisPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
        memNo = combox;
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
            
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            
            try {
                
                try {
                    
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    
                    
                    
                    
                    
                    String compName = null;
                    String date = null;
                    try {
                        
                        
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                         java.sql.Statement st2x = connectDB.createStatement();
                        
                        java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                        while(rset2x.next()){
                            ks = rset2x.getObject(1).toString();
                        }
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");
                        while(rset2.next()){
                            compName = rset2.getObject(1).toString();
                        }
                        while(rset4.next()){
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);
                        
                    } catch(java.sql.SQLException SqlExec) {
                        
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                        
                    }
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient Statements - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    
                    
                    double balance21 =0.00;
                    double balance11 =0.00;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);
                        
                        int headerwidths[] = {12,10,20,20,20,20,20,20};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        
                        
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(8);
                        Phrase phrase = new Phrase("PATIENT STATEMENT (OP)");
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(5);
                        try {
                            
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            
                            phrase = new Phrase("Period : From "  +dateFormat.format(endDate11)+" To "+dateFormat.format(endDate1), pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Printed on : " +date, pFontHeader1);
                        
                        table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            
                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select patient_no,initcap(first_name||' '||second_name||' '||last_name),address,residence,tel_no,date from hp_patient_register where patient_no = '"+memNo+"'");
                            
                            while (rset.next()) {
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.:  " +dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name : " +dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Address : " +dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Town : " +dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No.: " +dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("First Visit: " +dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                table.addCell(phrase);
                                
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);
                            
                            phrase = new Phrase("Date",pFontHeader1);
                            
                            table.addCell(phrase);
                            phrase = new Phrase("Diagnosis",pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            
                            phrase = new Phrase("Doctor",pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Cons",pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("Lab",pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Pharmacy",pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Xray",pFontHeader1);
                            
                            table.addCell(phrase);
                            
                            
                            phrase = new Phrase("Procedure",pFontHeader1);
                            table.addCell(phrase);
                            java.lang.Object[] listofAct = this.getListofActivities();
                            System.out.println(listofAct.length);
                            
                            for (int i = 0; i < listofAct.length; i++) {
                                double balance2 =0.00;
                                java.sql.PreparedStatement pset = connectDB.prepareStatement("select service_type from ac_ledger where patient_no = '"+memNo+"' and date = ? and description ilike 'cons%' ");
                                pset.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset1 = pset.executeQuery();
                                java.sql.PreparedStatement pset2 = connectDB.prepareStatement("select disease,doctor_surgeon from hp_patient_diagnosis where patient_no = '"+memNo+"' AND date_recorded = ?");
                                pset2.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset2 = pset2.executeQuery();
                                
                                java.sql.PreparedStatement pset3 = connectDB.prepareStatement("select service_type from ac_ledger where patient_no = '"+memNo+"' and date = ? and description ilike 'lab%'");
                                pset3.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset3 = pset3.executeQuery();
                                java.sql.PreparedStatement pset4 = connectDB.prepareStatement("select service_type from ac_ledger where patient_no = '"+memNo+"' and date = ? and description ilike 'pha%'");
                                pset4.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset4 = pset4.executeQuery();
                                java.sql.PreparedStatement pset5 = connectDB.prepareStatement("select service_type from ac_ledger where patient_no = '"+memNo+"' and date = ? and description ilike 'xray%'");
                                pset5.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset5 = pset5.executeQuery();
                                java.sql.PreparedStatement pset6 = connectDB.prepareStatement("select service_type from ac_ledger where patient_no = '"+memNo+"' and date = ? and description ilike 'ward%'");
                                pset6.setString(1,listofAct[i].toString());
                                java.sql.ResultSet rset6 = pset6.executeQuery();
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(dbObject.getDBObject(listofAct[i].toString(), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                String stringPhrase ="";
                                
                                while (rset2.next()){
                                    table.getDefaultCell().setColspan(1);
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset2.getObject(1).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                
                                table.addCell(phrase);
                                stringPhrase ="";
                                while (rset2.next()){
                                    table.getDefaultCell().setColspan(1);
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset2.getObject(2).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                table.addCell(phrase);
                                stringPhrase ="";
                                
                                while (rset1.next()){
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset1.getObject(1).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                table.addCell(phrase);
                                stringPhrase ="";
                                
                                while (rset3.next()){
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset3.getObject(1).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                table.addCell(phrase);
                                stringPhrase ="";
                                while (rset4.next()){
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset4.getObject(1).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                
                                table.addCell(phrase);
                                stringPhrase ="";
                                
                                
                                while (rset5.next()){
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset5.getObject(1).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                table.addCell(phrase);
                                stringPhrase ="";
                                while (rset6.next()){
                                    
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    stringPhrase = stringPhrase + dbObject.getDBObject(rset6.getObject(1).toString() + "\n", "-").toString();
                                    
                                }
                                phrase = new Phrase(stringPhrase, pFontHeader);
                                table.addCell(phrase);
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
            
            //  java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT  distinct date FROM ac_ledger where patient_no = '"+memNo+"' and date BETWEEN '"+beginDate+"' AND '"+endDate+"'  order by date");
            //pSet1.setString(1,"Raise Invoice");
            java.sql.ResultSet rSet1 = pSet1.executeQuery();
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}
            }
            System.out.println("description"+rSet1.getObject(1).toString());
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        
        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    
    
    
}





