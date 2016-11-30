//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;

public class FinalInterimPatientlnvSummPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.lang.String endDate = null;
    double osBalance = 0.00;
    double osBalance1 = 0.00;
    double current = 0.00;
    String ks;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeaderX = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String patientNo;

    public void FinalInterimPatientlnvSummPdf(java.sql.Connection connDb, java.lang.String combox, String patNo) {
        MNo = combox;

        patientNo = patNo;

        connectDB = connDb;

        dbObject = new com.afrisoftech.lib.DBObject();

        // beginDate = begindate;
        // endDate = endate;
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

            this.generatePdf(MNo);

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

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;

                    String Messg = null;
                    try {

                        java.sql.Statement st31 = connectDB.createStatement();
                        // java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st31.executeQuery("select name from pb_notice");

                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        //   java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            Messg = rset2.getString(1);
                        }
                        com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("" + Messg + ""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.ALIGN_CENTER;
                        //  headerFoter.setRight(5);
                        docPdf.setFooter(footer);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }
                    docPdf.open();

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //   java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(6);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();
                        int headerwidths[] = {15, 15, 30, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));

                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();
                        Phrase phrase1 = new Phrase();
                        Phrase phrase2 = new Phrase();
                        Phrase phrase3 = new Phrase();
                        Phrase phrase4 = new Phrase();
                        Phrase phrase5 = new Phrase();
                        Phrase phrase6 = new Phrase();
                        Phrase phrase7 = new Phrase();
                        Phrase phrase8 = new Phrase();
                        Phrase phrase9 = new Phrase();
                        Phrase phrase10 = new Phrase();
                        Phrase phrase11 = new Phrase();
                        //  table.addCell(phrase);

                        //table1.getDefaultCell().setColspan(1);
                        //table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        try {

                            java.sql.Statement stc = connectDB.createStatement();
                            java.sql.Statement stb = connectDB.createStatement();
                            java.sql.Statement sta = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st32 = connectDB.createStatement();
                            java.sql.Statement std = connectDB.createStatement();
                            java.sql.Statement st321 = connectDB.createStatement();
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setFixedHeight(70);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.addCell(Image.getInstance(System.getProperty("company.logo")));
                            table1.getDefaultCell().setFixedHeight(16);
                            java.sql.ResultSet rset3 = st321.executeQuery("select header_name from pb_header");
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(4);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader11);
                                table1.addCell(phrase);
                            }
                            String visitId = memNo;
                            // java.sql.ResultSet rset311 = st22.executeQuery("SELECT DISTINCT visit_id FROM hp_patient_card WHERE invoice_no = '"+memNo+"'");
                            //     while (rset311.next()){
                            //         visitId = rset311.getObject(1).toString();
                            //     }
                            java.sql.ResultSet rsetc = stc.executeQuery("select distinct date_admitted,discharge_date::date,(discharge_date::date-date_admitted) from hp_admission where visit_id = '" + visitId + "'");

                            java.sql.ResultSet rsetb = stb.executeQuery("select distinct (hr.discharge_date - hr.adm_date),hr.account_no, hr.discharge_date from hp_inpatient_register hr,hp_patient_card pc where pc.patient_no = hr.patient_no and pc.visit_id = '" + memNo + "'");

                            java.sql.ResultSet rseta = sta.executeQuery("select distinct initcap(ad.ward),initcap(ad.bed_no),initcap(ad.doctor) from hp_admission ad,hp_patient_card pr where pr.visit_id = '" + memNo + "' and pr.patient_no = ad.patient_no");

                            java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT patient_no,initcap(second_name||' '||first_name||' '||last_name),address,residence,tel_no,payer,description,category FROM hp_inpatient_register where patient_no = '" + patientNo + "'");
                           // java.sql.ResultSet rset = st.executeQuery("select distinct pr.patient_no,initcap(pr.first_name||' '||pr.second_name||' '||pr.last_name),pr.address,pr.residence,pr.tel_no,pr.payer,pr.description,pr.category from hp_inpatient_register pr,hp_patient_card ac where ac.visit_id = '" + memNo + "' and ac.patient_no = pr.patient_no");

                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setColspan(6);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(25);
                            phrase = new Phrase("Summarised Interim Invoice".toUpperCase(), pFontHeaderX);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setFixedHeight(16);
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            while (rset.next()) {
                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Payer : ".toUpperCase() + dbObject.getDBObject(rset.getObject(6), "-").toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase1 = new Phrase("Scheme Name : ".toUpperCase() + dbObject.getDBObject(rset.getObject(7), "-").toUpperCase(), pFontHeader1);
                                //table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase2 = new Phrase("Patient No:  ".toUpperCase() + dbObject.getDBObject(rset.getObject(1), "-").toUpperCase(), pFontHeader1);
                                //table1.addCell(phrase);

                                table1.getDefaultCell().setColspan(3);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase3 = new Phrase("Patient Name: ".toUpperCase() + dbObject.getDBObject(rset.getObject(2), "-").toUpperCase(), pFontHeader1);
                                //table1.addCell(phrase);
                            }

                            while (rseta.next()) {
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Invoice No.: ".toUpperCase() + memNo.toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase4 = new Phrase("Member Name: ".toUpperCase(), pFontHeader1);
                                //table1.addCell(phrase);

                                // while (rseta.next())
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase5 = new Phrase("Ward: ".toUpperCase() + dbObject.getDBObject(rseta.getObject(1), "-").toUpperCase(), pFontHeader1);
                                //table1.addCell(phrase);
                                //while (rseta.next())
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase6 = new Phrase("Bed No: ".toUpperCase() + dbObject.getDBObject(rseta.getObject(2), "-").toUpperCase(), pFontHeader1);
                                // table1.addCell(phrase);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase9 = new Phrase("Doctor: ".toUpperCase() + dbObject.getDBObject(rseta.getObject(3), "-").toUpperCase(), pFontHeader1);
                                // table1.addCell(phrase);

                            }

                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //  phrase = new Phrase("Scheme Name : "+dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                            table1.addCell(phrase1);

                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // phrase = new Phrase("Patient No:  "+dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                            table1.addCell(phrase2);

                            while (rsetb.next()) {
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Member No.: ".toUpperCase() + dbObject.getDBObject(rsetb.getObject(2), "-").toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);

                            }
                            // while (rset.next()){
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // phrase = new Phrase("Patient Name: "+dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                            table1.addCell(phrase3);
                            // }
                            // while (rseta.next()){

                            // while (rseta.next())
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //  phrase = new Phrase("Ward: "+dbObject.getDBObject(rseta.getObject(1), "-"), pFontHeader1);
                            table1.addCell(phrase4);
                            //while (rseta.next())
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table1.addCell(phrase5);

                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table1.addCell(phrase6);

                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table1.addCell(phrase9);

                            while (rsetc.next()) {
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Adm Date: ".toUpperCase() + dbObject.getDBObject(rsetc.getObject(1), "-").toUpperCase(), pFontHeader1);
                                table1.addCell(phrase);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase7 = new Phrase("Discharge Date: ".toUpperCase() + dbObject.getDBObject(rsetc.getObject(2), "-").toUpperCase(), pFontHeader1);
                                table1.getDefaultCell().setColspan(6);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase8 = new Phrase("No.of Days : ".toUpperCase() + dbObject.getDBObject(rsetc.getObject(3), "-").toUpperCase(), pFontHeader1);
                                // table1.addCell(phrase);

                            }

                            // while (rsetb.next()){
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table1.addCell(phrase7);

                            //      if(rsetc.getBoolean(1) == true){
                            table1.getDefaultCell().setColspan(6);
                            // while (rsetb.next()){

                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // phrase = new Phrase("No.of Days : "+dbObject.getDBObject(rsetb.getObject(1), "-"), pFontHeader1);
                            table1.addCell(phrase8);
                            //}
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {12, 45, 5, 8, 15, 15};

                        table.setWidths(headerwidths);
                        table.setHeaderRows(1);
                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorder(Rectangle.BOX);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        try {
                            java.sql.Statement st11 = connectDB.createStatement();

                            java.sql.Statement st21 = connectDB.createStatement();

                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            //  }
                            java.sql.Statement st2x = connectDB.createStatement();

                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.ResultSet rset1 = st1.executeQuery(" select initcap(main_service) as service,sum(dosage)::int,sum(debit-credit) from hp_patient_card where visit_id = '" + memNo + "' and service != 'N.H.I.F' AND service != 'N.H.I.F' AND service not ilike 'Receipt%' AND service not ilike 'Receipt Adj' AND service not ilike 'Discou%' AND service != 'Invoice' group by initcap(main_service) order by service");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            java.sql.ResultSet rsetTotals = st2.executeQuery("select sum(debit - credit) from hp_patient_card where visit_id = '" + memNo + "' and service != 'N.H.I.F' AND service not ilike 'Receipt%' AND service not ilike 'Receipt Adj' AND service not ilike 'Discou%' AND service != 'Invoice'");

                            java.sql.ResultSet rset11 = st11.executeQuery(" select initcap(main_service) as service,sum(dosage)::int,sum(credit-debit) from hp_patient_card where visit_id = '" + memNo + "' AND (service = 'N.H.I.F' or service ilike 'Receipt%' or service ilike 'Receipt Adj'  OR service ilike 'Discou%') group by main_service order by main_service");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                            java.sql.ResultSet rsetTotals1 = st21.executeQuery("select sum(credit) from hp_patient_card where visit_id = '" + memNo + "' and (service = 'N.H.I.F' or service ilike 'Receipt' or service ilike 'Receipt Adj'  OR service ilike 'Discou%')");

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            // table.getDefaultCell().setBorderWidth(Rectangle.BOX);
                            //table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Description".toUpperCase(), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Qty ".toUpperCase(), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Amount".toUpperCase(), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Balance ".toUpperCase() + ks.toUpperCase(), pFontHeader1);
                            table.addCell(phrase);

                            while (rset1.next()) {
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-").toUpperCase(), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                int dos = 0;
                                dos = rset1.getInt(2);

                                phrase = new Phrase(java.lang.String.valueOf(dos), pFontHeader);

                                //  phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(3)), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                osBalance = osBalance + rset1.getDouble(3);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader);
                                //   current = current + osBalance;

                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOX);

                            while (rsetTotals.next()) {

                                table.getDefaultCell().setColspan(4);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Invoice Amount".toUpperCase(), pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)), pFontHeader);

                                // table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance)), pFontHeader1);

                                table.addCell(phrase);

                                //phrase = new Phrase(" ");
                            }

                            while (rset11.next()) {
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total ".toUpperCase() + dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                // phrase = new Phrase(dbObject.getDBObject(rset11.getObject(2), "-"), pFontHeader);
                                phrase = new Phrase(dbObject.getDBObject(" ", "-"), pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset11.getString(3)), pFontHeader);

                                table.addCell(phrase);

                                osBalance1 = osBalance1 + rset11.getDouble(3);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader);
                                //   current = current + osBalance;

                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            while (rsetTotals1.next()) {

                               // table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);

                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Total Receipts".toUpperCase(), pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals1.getString(1)), pFontHeader);

                                //table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader1);

                                table.addCell(phrase);

                                //phrase = new Phrase(" ");
                            }

                            //  while (rsetTotals.next()) {
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOX);
                            //  table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Net Amount".toUpperCase(), pFontHeader1);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rsetTotals.getString(1)),pFontHeader);
                            // table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance - osBalance1)), pFontHeader1);

                            table.addCell(phrase);

                            //phrase = new Phrase(" ");
                            // }
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access where login_name = current_user order by 1 limit 1), date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");

                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                            while (rsetUser.next()) {
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Printed by : ".toUpperCase() + rsetUser.getString(1), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Print Date : ".toUpperCase() + rsetUser.getString(2), pFontHeader11);
                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Signature : __________________________________________".toUpperCase(), pFontHeader11);
                            table.addCell(phrase);
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

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
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }
}
