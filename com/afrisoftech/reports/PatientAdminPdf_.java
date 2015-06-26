//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PatientAdminPdf_ implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    int numberSeq = 0;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String servicepnt = null;

    public void PatientAdminPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String servicePoint) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;
        servicepnt = servicePoint;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new EntranceFeePdf().EntranceFeePdf();
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

            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        //while(rset2.next())
                        //   compName = rset2.getObject(1).toString();

                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"                                                        Printed On: "+date+"",pFontHeader),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        // headerFoter.setRight(5);
                        // docPdf.setHeader(headerFoter);
                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Bed Occupancy - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        //  java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(8);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();
                        int headerwidths[] = {12, 15, 30, 15, 10, 15, 10, 18};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));

                        //table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table1.getDefaultCell().setColspan(8);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);
                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.sql.Statement st2x = connectDB.createStatement();

                            java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                            while (rset2x.next()) {
                                ks = rset2x.getObject(1).toString();
                            }
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(8);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);
                                table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);

                        int headerwidths[] = {8, 15, 12, 30, 10, 10, 30, 10, 10, 20};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(7);
                        table.setHeaderRows(1);
                        Phrase phrase = null;
                        if (servicepnt.equalsIgnoreCase("-")) {
                            phrase = new Phrase("Detailed Daily Bed Return - Current Statement of IN-Patients", pFontHeader);
                        } else {
                            phrase = new Phrase("Detailed Daily Bed Return - Current Statement of IN-Patients : [" + servicepnt +"]", pFontHeader);
                        }

                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Printed on : [" + date +"]", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        //table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("#", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Adm. Date", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Adm. No.", pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Patient Name", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("Age", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Length of stay", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("User Name", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Pay Mode", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Unit No", pFontHeader);
                        table.addCell(phrase);

                     //   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        phrase = new Phrase("Scheme", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        double occ = 0.00;
                        double total = 0.00;
                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();

                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            System.out.println(listofAct.length);
                            java.sql.Statement st33 = connectDB.createStatement();

                            java.sql.ResultSet rset33 = st33.executeQuery("SELECT count(patient_no) FROM hp_admission WHERE date_admitted::date <= '" + endDate + "' "
                                + " AND( discharge_date::date >=  '" + endDate + "' OR discharge_date::date IS NULL) AND patient_no NOT IN (SELECT DISTINCT patient_no FROM hp_mortuary)");

                            for (int i = 0; i < listofAct.length; i++) {
                                int lineNo = 0;
                                java.sql.Statement st3 = connectDB.createStatement();

                                java.sql.ResultSet rset3 = null;

                                if (servicepnt.equalsIgnoreCase("-")) {
                                    // rset3 = st3.executeQuery("SELECT DISTINCT ad.date_admitted,ad.admission_no,initcap(ad.patient_name),ad.pat_age,bed_no,(ad.user_name),ad.mode_of_payment,ad.ward_code,sub_chief, SUM(current_date-ad.date_admitted) from hp_admission ad WHERE ad.ward ilike'" + listofAct[i] + "' AND date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and check_out=false and discharge=false group by 1,2,3,4,5,6,7,8,9 order by 10 DESC");
                                    rset3 = st3.executeQuery("SELECT DISTINCT ad.date_admitted,ad.admission_no,initcap(ad.patient_name),ad.pat_age::int,bed_no,(ad.user_name),ad.mode_of_payment,ad.ward_code,sub_chief, CASE WHEN discharge_date::date IS NULL THEN current_date-ad.date_admitted ELSE ad.discharge_date::date-ad.date_admitted END as length_of_stay, discharge_date::date from hp_admission ad WHERE patient_no NOT IN (SELECT DISTINCT patient_no FROM hp_mortuary) AND ad.ward ilike '" + listofAct[i] + "'  AND date_admitted::date <= '" + endDate + "' "
                                + " AND( discharge_date::date >=  '" + endDate + "' OR discharge_date::date IS NULL)   group by 1,2,3,4,5,6,7,8,9,11 order by 10 DESC");
                                } else {
                                    rset3 = st3.executeQuery("SELECT DISTINCT ad.date_admitted,ad.admission_no,initcap(ad.patient_name),ad.pat_age,bed_no,(ad.user_name),ad.mode_of_payment,ad.ward_code,sub_chief from hp_admission ad WHERE patient_no NOT IN (SELECT DISTINCT patient_no FROM hp_mortuary) AND patient_no ad.ward ilike'" + listofAct[i] + "' and diagnosis3='" + servicepnt + "' AND date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                                }

                                //  while (rset3.next()){
                                table.getDefaultCell().setColspan(10);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct[i].toString(), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                while (rset3.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    lineNo = lineNo + 1;
                                    numberSeq = numberSeq + 1;

                                    phrase = new Phrase("" + lineNo + "   ", pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(3), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(4), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(10), "0"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(6), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(7), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(9), "-"), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset3.getObject(8), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                }

                            }

                            table.getDefaultCell().setColspan(10);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);

                            //  while (rset33.next()){
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total Count " + dbObject.getDBObject(java.lang.String.valueOf(numberSeq), "-"), pFontHeader);
                            //  phrase = new Phrase("Total Count " +dbObject.getDBObject(rset33.getObject(1), "-"), pFontHeader);
                            table.addCell(phrase);

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

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;

            if (servicepnt.equalsIgnoreCase("-")) {
                rSet1 = stmt1.executeQuery("SELECT DISTINCT upper(ward) as ward FROM hp_admission WHERE  ward is NOT NULL "); // WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by ward");
                // rSet1 = stmt1.executeQuery("SELECT DISTINCT upper(ward) as ward FROM hp_admission WHERE check_out=false  order by ward");
            } else {
                rSet1 = stmt1.executeQuery("SELECT DISTINCT upper(ward) as ward FROM hp_admission WHERE  ward is NOT NULL AND date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and diagnosis3='" + servicepnt + "' order by ward");
            }
                rSet1 = stmt1.executeQuery("SELECT DISTINCT upper(ward) as ward  FROM hp_admission WHERE ward is NOT NULL ORDER BY 1"); // WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by ward");
               
            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
    // }
}
