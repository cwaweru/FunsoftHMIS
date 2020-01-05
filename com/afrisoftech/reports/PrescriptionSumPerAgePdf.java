//Author Samuel Nderitu
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.afrisoftech.reports.*;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.awt.Desktop;

public class PrescriptionSumPerAgePdf implements java.lang.Runnable {

    java.awt.Desktop deskTop = Desktop.getDesktop();
    int numberSeq = 0;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.lang.String Categ = null;
    java.lang.String ReportType = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    double osBalance = 0;
    double osBalancek = 0;
    double osBalancel = 0;

    public void PrescriptionSumPerAgePdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String categ, java.lang.String repotype) {

        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        Categ = categ;
        ReportType = repotype;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new GlTransactPdf().GlTransactPdf();
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

    public void generatePdf() {
        java.sql.ResultSet rsetTotals1 = null;

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
                    try {

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Prescription by Age- Page: ", pFontHeader1), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();
                    double osBalance1 = 0.00;
                    double osBalance15 = 0.00;
                    double osBalance45 = 0.00;
                    double osBalance61 = 0.00;
                    double osBalanceu1 = 0.00;
                    double ipDiag = 0;
                    double opDiag = 0;
                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);

                        int headerwidths[] = {6, 55, 8, 7, 7, 7, 7, 8, 8, 7};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(3);

                        java.lang.Object[] listofAct = this.getListofActivities1();
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setColspan(5);

                        Phrase phrase = new Phrase("Dispensation by Age Summary : " + Categ.toUpperCase(), com.lowagie.text.FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));

                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        phrase = new Phrase("Printed On : " + date, com.lowagie.text.FontFactory.getFont(FontFactory.HELVETICA, 7));

                        table.addCell(phrase);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(10);

                            phrase = new Phrase("Period : From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }
                        //phrase = new Phrase("Name", pFontHeader);

                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("RANK", pFontHeader);

//                            table.addCell(phrase);
//                            phrase = new Phrase("CODE", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("DRUG", pFontHeader);

                        table.addCell(phrase);

                        phrase = new Phrase("UNDER 1 YR.", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("1-4", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("5-14", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("15-44", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("45-60", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("61+", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("NO. OF CASES", pFontHeader);

                        table.addCell(phrase);
                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        phrase = new Phrase("% TOTAL", pFontHeader);

                        table.addCell(phrase);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {

                            java.sql.Statement st212 = connectDB.createStatement();

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        try {

                            // java.lang.Object[] listofAct = this.getListofActivities();
                            java.lang.Object[] listofAct1 = this.getListofActivities1();

                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st211 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            java.sql.Statement st45 = connectDB.createStatement();
                            java.sql.Statement st61 = connectDB.createStatement();
                            java.sql.Statement stu1 = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st212 = connectDB.createStatement();
                            java.sql.Statement st2k = connectDB.createStatement();
                            java.sql.Statement st2l = connectDB.createStatement();

                            String condition = "";
                            if (Categ.equalsIgnoreCase("OP")) {
                                condition = "AND patient_source ilike 'OP%'";
                            }
                            if (Categ.equalsIgnoreCase("IP")) {
                                condition = "AND patient_source ilike 'IP%'";
                            }

                            java.sql.ResultSet rset11 = st212.executeQuery("select COUNT(*)   from st_sub_stores where  (patient_source  ilike 'OP%' OR patient_source  ilike 'IP%'  ) \n"
                                    + "and   trans_date::date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  " + condition);
                            while (rset11.next()) {
                                
                                 System.out.println("Getting total  itemssss--------------------------");

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                //  osBalance = osBalance + rset1.getDouble(2);
                                osBalance1 = rset11.getDouble(1);
                                // table.addCell(phrase);
                            }
                            // java.sql.ResultSet rset11 = st212.executeQuery("SELECT count(main_service) from hp_patient_diagnosis where date_discharged BETWEEN '"+beginDate+"' AND '"+endDate+"'");

                            for (int i = 0; i < listofAct.length; i++) {
                                java.sql.ResultSet rsetu1 = stu1.executeQuery("SELECT  count(h.item) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 < 1 and h.item='" + listofAct[i] + "' " + condition + " UNION "
                                        + "SELECT  count(h.item) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 < 1 and h.item='" + listofAct[i] + "' " + condition + "");
                                
                                java.sql.ResultSet rseto1 = st2k.executeQuery("SELECT  count(h.item) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 BETWEEN 1 AND 4 and h.item='" + listofAct[i] + "' " + condition + " UNION "
                                        + "SELECT  count(h.item) FROM st_sub_stores h,hp_inpatient_register p where ( patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 BETWEEN 1 AND 4 and h.item='" + listofAct[i] + "' " + condition + "");
                                
                                java.sql.ResultSet rset5 = st2l.executeQuery("SELECT  count(h.item) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 BETWEEN 5 AND 14 and h.item='" + listofAct[i] + "' " + condition + " UNION "
                                        + "SELECT  count(h.item) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 BETWEEN 5 AND 14 and h.item='" + listofAct[i] + "' " + condition + "");
                                
                                java.sql.ResultSet rset15 = st15.executeQuery("SELECT  count(h.item) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 BETWEEN 15 AND 44 and h.item='" + listofAct[i] + "' " + condition + " UNION "
                                        + "SELECT  count(h.item) FROM st_sub_stores h,hp_inpatient_register p where ( patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 BETWEEN 15 AND 44 and h.item='" + listofAct[i] + "' " + condition + "");
                                
                                java.sql.ResultSet rset45 = st45.executeQuery("SELECT  count(h.item) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 BETWEEN 45 AND 60 and h.item='" + listofAct[i] + "' " + condition + " UNION "
                                        + "SELECT  count(h.item) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 BETWEEN 45 AND 60 and h.item='" + listofAct[i] + "' " + condition + "");
                                
                                java.sql.ResultSet rset61 = st61.executeQuery("SELECT  count(h.item) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 60 and h.item='" + listofAct[i] + "' " + condition + " UNION "
                                        + "SELECT  count(h.item) FROM st_sub_stores h,hp_inpatient_register p where ( patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 60 and h.item='" + listofAct[i] + "' " + condition + "");

                                java.sql.ResultSet rset1 = st2.executeQuery("SELECT item, count(item) FROM st_sub_stores where (patient_source  ilike 'OP%' OR patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and  item='" + listofAct[i] + "' " + condition + " group by 1  ORDER BY 1 DESC");

                                while (rset1.next()) {
                                    int under1 = 0, over1 = 0, over5 = 0, over15 = 0, over45 = 0, over61 = 0;
                                    while (rsetu1.next()) {
                                        under1 += rsetu1.getInt(1);

                                    }
                                    while (rseto1.next()) {
                                        over1 += rseto1.getInt(1);
                                    }
                                    while (rset5.next()) {
                                        over5 += rset5.getInt(1);
                                    }
                                    while (rset15.next()) {
                                        over15 += rset15.getInt(1);
                                    }
                                    while (rset45.next()) {
                                        over45 += rset45.getInt(1);
                                    }
                                    while (rset61.next()) {
                                        over61 += rset61.getInt(1);
                                    }

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    numberSeq = numberSeq + 1;

                                    phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                    //table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(listofAct[i], "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                    phrase = new Phrase(dbObject.getDBObject(under1, "-"), pFontHeader);
                                    osBalanceu1 = osBalanceu1 + under1;
                                    table.addCell(phrase);

                                    phrase = new Phrase(dbObject.getDBObject(over1, "-"), pFontHeader);
                                    osBalancek = osBalancek + over1;
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(over5, "-"), pFontHeader);
                                    osBalancel = osBalancel + over5;
                                    table.addCell(phrase);

                                    phrase = new Phrase(dbObject.getDBObject(over15, "-"), pFontHeader);
                                    osBalance15 = osBalance15 + over15;
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(over45, "-"), pFontHeader);
                                    osBalance45 = osBalance45 + over45;
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(over61, "-"), pFontHeader);
                                    osBalance61 = osBalance61 + over61;

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                    osBalance = rset1.getDouble(2);
                                    // osBalance1 = osBalance + rset1.getDouble(2);
                                    table.addCell(phrase);
                                    //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(2)),pFontHeader);
                                    //osBalance = osBalance + rset1.getDouble(2);
                                    //table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance * 100 / osBalance1)), pFontHeader);
                                    //   osBalance1 = osBalance1 + rset1.getDouble(5);
                                    table.addCell(phrase);

                                }
                            }
                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalanceu1)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalancek)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalancel)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalance15)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalance45)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalance61)), pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalance1)), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("100 ", pFontHeader);

                            table.addCell(phrase);

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

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
            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getListofActivities1() {

        java.lang.Object[] listofActivities1 = null;

        java.util.Vector listActVector1 = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;
             System.out.println("Getting itemssss-----------------rr---------");

            String condition = "";
            if (Categ.equalsIgnoreCase("OP")) {
                condition = "AND patient_source ilike 'OP%'";
            }
            if (Categ.equalsIgnoreCase("IP")) {
                condition = "AND patient_source ilike 'IP%'";
            }
            rSet1 = stmt1.executeQuery("SELECT DISTINCT item,COUNT(item) FROM st_sub_stores where (patient_source  ilike 'OP%' OR patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' " + condition + " GROUP BY 1 ORDER BY 2 DESC");

            while (rSet1.next()) {
                System.out.println("Getting itemssss--------------------------");

                listActVector1.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }
}
