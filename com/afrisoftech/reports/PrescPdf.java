//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.afrisoftech.lib.HosPrescriptionsDatePanel;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class PrescPdf implements java.lang.Runnable {

    java.lang.String memNo = null;

    com.afrisoftech.lib.DBObject dbObject;
     ////java.awt.Desktop deskTop = Desktop.getDesktop();

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

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);

    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;
    
    boolean all = false;
    private String doctorAccount;

    public void PrescPdf(java.sql.Connection connDb, java.lang.String combox, boolean alll) {
        memNo = combox;

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;
        all= alll;
  //       beginDate = begindate;

        //endDate = endate;
        threadSample = new java.lang.Thread(this, "SampleThread");

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

            } catch (java.lang.InterruptedException IntExec) {

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

            year_now_strs = "200" + year_now_abs;

        } else {

            year_now_strs = "20" + year_now_abs;

        }

        switch (month_now_str) {

            case 0:
                month_now_strs = "JAN";

                break;

            case 1:
                month_now_strs = "FEB";

                break;

            case 2:
                month_now_strs = "MAR";

                break;

            case 3:
                month_now_strs = "APR";

                break;

            case 4:
                month_now_strs = "MAY";

                break;

            case 5:
                month_now_strs = "JUN";

                break;

            case 6:
                month_now_strs = "JUL";

                break;

            case 7:
                month_now_strs = "AUG";

                break;

            case 8:
                month_now_strs = "SEP";

                break;

            case 9:
                month_now_strs = "OCT";

                break;

            case 10:
                month_now_strs = "NOV";

                break;

            case 11:
                month_now_strs = "DEC";

                break;

            default:
                if (month_now_str < 10) {

                    month_now_strs = "0" + month_now_str;

                } else {

                    month_now_strs = "" + month_now_str;

                }

        }

        if (date_now_str < 10) {

            date_now_strs = "0" + date_now_str;

        } else {

            date_now_strs = "" + date_now_str;

        }

        if (minute_now_str < 10) {

            minute_now_strs = "0" + minute_now_str;

        } else {

            minute_now_strs = "" + minute_now_str;

        }

        if (hour_now_str < 10) {

            hour_now_strs = "0" + hour_now_str;

        } else {

            hour_now_strs = "" + hour_now_str;

        }

        date_label = date_now_strs + month_now_strs + year_now_strs + "@" + hour_now_strs + minute_now_strs;

        return date_label;

    }

    public void generatePdf(java.lang.String memNo) {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String memNo1 = null;

            java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    String date1 = null;
                    String date12 = null;
                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();

                            while (rset4.next()) {
                                date = rset4.getObject(1).toString();

                            }
                        }
                       // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName ,pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                       // headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        // headerFoter.setRight(7);
                        // docPdf.setHeader(headerFoter);
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Prescription - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                       // java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();
                        int headerwidths[] = {15, 15, 40, 15, 15, 15, 15, 15};

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
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(8);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths[] = {30, 25, 20, 20, 20, 20, 20};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);

                        try {
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st8 = connectDB.createStatement();
                            //  java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset2 = st2.executeQuery("select distinct patient_no from pb_doctors_request where inv_no = '" + memNo + "'");
                            while (rset2.next()) {
                                table.getDefaultCell().setColspan(7);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                memNo1 = rset2.getObject(1).toString();
                               // table.addCell(phrase);

                            }
                            String condition = "";
                            
                            if(all){
                                condition = "and trans_date > current_date - 1 and patient_no = '"+memNo1+"' ";
                            }else{
                                condition = " and inv_no = '" + memNo + "' ";
                            }

                            java.sql.ResultSet rset1 = st1.executeQuery("select service,requisition_no,bed_no,dosage,time_due, paid from pb_doctors_request where revenue_code ilike '%pharm%' "+condition+" ORDER BY service asc");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            java.sql.ResultSet rset5 = st5.executeQuery("select distinct inv_no,initcap(doctor) from pb_doctors_request where revenue_code ilike '%pharm%' and inv_no = '" + memNo + "'");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            java.sql.ResultSet rset6 = st6.executeQuery("select distinct curr_date::timestamp(0) from pb_doctors_request where revenue_code ilike '%pharm%' and inv_no = '" + memNo + "'");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");

                            java.sql.ResultSet rset8 = st8.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");

                            while (rset8.next()) {
                                table.getDefaultCell().setColspan(7);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                date12 = rset8.getObject(1).toString();
                                // table.addCell(phrase);
                            }
                            /*  while (rset6.next()){
                             table.getDefaultCell().setColspan(6);
                                
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             date1=rset6.getObject(1).toString();
                               
                             }
                        
                             java.sql.ResultSet rset7 = st7.executeQuery("select date_trunc('seconds', timestamp '"+date1+"')");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                             */

                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("PRESCRIPTION", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("Printed on:" + "\t" + date12, pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            while (rset6.next()) {

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Presc. Time: ", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset6.getString(1), pFontHeader1);
                                table.addCell(phrase);
                            }
                            while (rset5.next()) {

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Presc. No: ", pFontHeader1);

                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(3);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset5.getString(1).toUpperCase(), pFontHeader1);

                                table.addCell(phrase);

                               doctorAccount = rset5.getString(2).toUpperCase();

                            }

                            if (HosPrescriptionsDatePanel.opdRdbtn.isSelected()) {
                              //  if (memNo.startsWith("O")) {
                                java.sql.ResultSet rset = st.executeQuery("select initcap(first_name||' '||second_name),funsoft_patient_age(year_of_birth::date),sex,tel_no,initcap(description),initcap(payer) from hp_patient_register where patient_no = '" + memNo1 + "'");

                                while (rset.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Patient No.:", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(memNo1.toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                                                        
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    // phrase = new Phrase("Patient Name  "+rset.getObject(2).toString(), pFontHeader);
                                    phrase = new Phrase("Patient Name : ", pFontHeader1);

                                    table.addCell(phrase);
                                    
                                    
                                    
                                    
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    // phrase = new Phrase("Patient Name  "+rset.getObject(2).toString(), pFontHeader);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-").toUpperCase(), pFontHeader1);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Age", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Scheme Name", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                                                    
                                    table.getDefaultCell().setColspan(3);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-").toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Gender", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-").toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                                                        

                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Tel No.", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Doctor A/C", pFontHeader1);

                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(doctorAccount, pFontHeader1);

                                table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payer", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                        table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-").toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            } else {
                                java.sql.ResultSet rset = st.executeQuery("select initcap(first_name||' '||second_name),year_of_birth,sex,tel_no,initcap(description),initcap(payer) from hp_inpatient_register where patient_no = '" + memNo1 + "'");

                                while (rset.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Patient No.:  " + memNo1.toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(memNo1.toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Age:  ", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Gender:  ", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-").toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    // phrase = new Phrase("Patient Name  "+rset.getObject(2).toString(), pFontHeader);
                                    phrase = new Phrase("Patient Name : ", pFontHeader1);

                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    // phrase = new Phrase("Patient Name  "+rset.getObject(2).toString(), pFontHeader);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-").toUpperCase(), pFontHeader1);

                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("TEl No.: ", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                                                        table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Scheme:  ", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                                                    
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-").toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Payer: ", pFontHeader1);
                                    table.addCell(phrase);
                                    
                                        table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-").toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
//                                    
//                                    table.getDefaultCell().setColspan(3);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase("Patient No.:  " + memNo1.toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//
//                                    table.getDefaultCell().setColspan(2);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase("Age:  " + dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
//                                    table.addCell(phrase);
//
//                                    table.getDefaultCell().setColspan(2);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase("Gender:  " + dbObject.getDBObject(rset.getObject(3), "-").toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//
//                                    table.getDefaultCell().setColspan(4);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    // phrase = new Phrase("Patient Name  "+rset.getObject(2).toString(), pFontHeader);
//                                    phrase = new Phrase("Patient Name : " + dbObject.getDBObject(rset.getObject(1), "-").toUpperCase(), pFontHeader1);
//
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setColspan(3);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase("TEl No.: " + dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setColspan(3);
//                                    phrase = new Phrase("Scheme:  " + dbObject.getDBObject(rset.getObject(5), "-").toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setColspan(4);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    phrase = new Phrase("Payer: " + dbObject.getDBObject(rset.getObject(6), "-").toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
                                }
                            }
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP | Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Description", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Route", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Freq", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Dosage", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Days", pFontHeader1);
                            table.addCell(phrase);
                            
                                                        phrase = new Phrase("Served", pFontHeader1);
                            table.addCell(phrase);

                            while (rset1.next()) {
                                table.getDefaultCell().setColspan(2);
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
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(5), "-"), pFontHeader);                    
                                table.addCell(phrase);
                                
                                if(rset1.getBoolean(6)){
                                phrase = new Phrase("YES", pFontHeader);                               
                                table.addCell(phrase);
                                } else {
                                phrase = new Phrase("NO", pFontHeader);                               
                                table.addCell(phrase);                                    
                                }
                                
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("  ", pFontHeader1);
                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("  ", pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            //phrase = new Phrase("Doctor : "+compName.toUpperCase(), pFontHeader11);
                            // table.addCell(phrase);
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Signature : __________________________ ", pFontHeader11);
                            table.addCell(phrase);

                                //phrase = new Phrase(" ");
                          //  }
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

}
