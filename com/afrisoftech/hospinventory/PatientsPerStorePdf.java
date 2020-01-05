//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;

package com.afrisoftech.hospinventory;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;


public class PatientsPerStorePdf implements java.lang.Runnable {
    java.lang.String memNo = null;
    java.lang.String UserName = null;
    
    java.lang.String CashPoint = null;
    
    java.lang.String Category = null;
    
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
    public void PatientsPerStorePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String cashPoint, java.lang.String categ) {
        
        //  public void ShiftReportDetailedPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String cashPoint) {
        //     memNo = combox;
        beginDate = begindate;
        
        endDate = endate;
        
        CashPoint = cashPoint;
        
        connectDB = connDb;
        
        Category = categ;
        
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
                    
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient's Per Pharmacy- Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                    
                    docPdf.setFooter(footer);
                    
                    
                    docPdf.open();
                    double amt = 0.00;
                    int counTotal = 0;
                    try {
                        
                        
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidths[] = {5,15,50,8,10,10};
                        
                        table.setWidths(headerwidths);
                        
                        table.setWidthPercentage((100));
                        table.setHeaderRows(2);
                        Phrase phrase = new Phrase(" ", pFontHeader);
                        
                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                       // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println(""+endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            
                            phrase = new Phrase(CashPoint.toUpperCase()+ " Patient's  - Period : "  +dateFormat.format(endDate11)+ "  -  " +dateFormat.format(endDate1)+"              "+"Printed On  :" +date, pFontHeader);
                            
                            table.addCell(phrase);
                            //table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT); 
                            
                           // phrase = new Phrase("Printed On  :" +date , pFontHeader);
                            
                           // table.addCell(phrase);
                        } catch(java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // phrase = new Phrase("SHIFT No. FROM :     CASHIER : ", pFontHeader);
                        ///table.addCell(phrase);
                        
                        
                        
                        
                        
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {
                            //     java.lang.Object[] listofAct11 = this.getListofActivities11();
                            
                            //   java.lang.Object[] listofAct1 = this.getListofActivities1();
                            //  System.out.println(listofAct.length);
                            java.sql.Statement st3 = connectDB.createStatement();
                            
                            
                           // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            
                            // table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.black);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("#", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Pat No.", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Patient Name", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Age", pFontHeader);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Gender", pFontHeader);
                            table.addCell(phrase);
                            
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date Issued", pFontHeader);
                            table.addCell(phrase);
                            
                            
                            java.sql.Statement st52x = connectDB.createStatement();
                             java.sql.Statement st52xx = connectDB.createStatement();
                             int patientCount=1;
                             int in = 0, out = 0 , walkin = 0;
                                    
                                    java.sql.ResultSet rset2x = st52x.executeQuery("select distinct  issiued_to,trans_date::date,patient_no,patient_source  from st_sub_stores where store_name ilike '"+CashPoint+"' and (patient_source  ilike 'OP%' OR patient_source  ilike 'IP%' OR patient_source  ilike 'WI%' ) and  trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by 2 ");
                                   int quantitytotal=0,amounttotal=0;
                                    while (rset2x.next()) {
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(String.valueOf(patientCount), pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        String issued_to =rset2x.getObject(1).toString();
                                        String patNo = rset2x.getObject(3).toString();
                                        String patSource = rset2x.getObject(4).toString();
                                        String patName = issued_to.substring(issued_to.indexOf(' ') + 1);
                                        
                                        String age="";
                                        String gender="";
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(patNo, pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(patName.trim(), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        java.sql.Statement stmtTable1 = connectDB.createStatement();
                                        java.sql.ResultSet rsetTable1 = stmtTable1.executeQuery("SELECT  ( '"+rset2x.getDate(2)+"'::date - year_of_birth::date )/365 , sex  FROM public.hp_patient_register WHERE patient_no = '"+patNo+"' UNION SELECT  ( '"+rset2x.getDate(2)+"'::date - year_of_birth::date )/365 , sex  FROM public.hp_inpatient_register WHERE patient_no = '"+patNo+"' ");

                                        while (rsetTable1.next()) {
                                            age = rsetTable1.getString(1);
                                            gender=rsetTable1.getString(2);

                                        }
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(age, pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(gender, pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(rset2x.getObject(2).toString(), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        
                                        patientCount++;
                                        if(patSource.startsWith("OP")){
                                            out++;
                                        }
                                        if(patSource.startsWith("IP")){
                                            in++;
                                        }
                                        if(patSource.startsWith("WI")){
                                            walkin++;
                                        }
                                    }
                            
                            
                                    patientCount--;
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            // java.lang.Object[] listofAct1 = this.getDates();
                            //  for (int k = 0; k < listofAct1.length; k++) {
                            
                            java.lang.Object[] listofAct1 = this.getDates();
                            
                            //   }
                            
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                            
                           
                            table.getDefaultCell().setColspan(6);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Walkins  " +walkin, pFontHeader);                           
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Outpatients " +out, pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Inpatient  " +in, pFontHeader);
                            
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Count  " +patientCount, pFontHeader);
                            
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
            

            docPdf.close(); com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            

            
            
            
        } catch(java.io.IOException IOexec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());
            
        }
        
        
        
    }
    
    public java.lang.Object[] getDates() {
        
        java.lang.Object[] listofDates = null;
        
        java.util.Vector listofDatesVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT sub_cat_code FROM st_stock_item where department ilike '"+CashPoint+"' AND sub_cat_code ilike '"+Category+"' ORDER BY sub_cat_code");
            
            while (rSet1.next()) {
                
                listofDatesVector.addElement(rSet1.getObject(1).toString());
                
            }
            
        }catch (java.sql.SQLException sqlExec) {
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
            
        }
        
        listofDates = listofDatesVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofDates;
    }
    
    public java.lang.Object[] getListofActivities(java.lang.Object Cate) {
        
        java.lang.Object[] listofActivities = null;
        
        java.util.Vector listActVector = new java.util.Vector(1,1);
        
        
        try {
            
            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            
            java.sql.Statement stmt1 = connectDB.createStatement();
            
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT activity_code FROM ac_cash_collection where date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND transaction_type not ilike 'Bank%' EXCEPT select code from pb_activity where activity ilike 'Pharmacy%' ORDER BY activity_code");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ph.date_prescribed FROM hp_pharmacy ph,st_stock_item st where date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"'  AND ph.main_service ilike '"+CashPoint+"%' and ph.description ilike st.description and st.sub_cat_code ilike '"+Cate+"%' ORDER BY ph.date_prescribed");
            
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
    
    
  
}





