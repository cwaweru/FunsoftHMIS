///Author @author SaQlever
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laboratory;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;
import java.awt.Color;
import java.util.Date;
import java.util.HashMap;

public class LabRequestPdf implements java.lang.Runnable {

    java.lang.String patientNO = null;
    java.lang.String MName = null;
    com.afrisoftech.lib.DBObject dbObject;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD);
    com.lowagie.text.Font pFontHeadert = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String visitId;
    java.lang.String pdfheader;
    java.util.HashMap<String, java.util.ArrayList> datapdf = new HashMap();
    static java.util.ArrayList columnVector;

    static java.util.ArrayList dataViewVector;
    static java.util.ArrayList childVector;
    static java.util.ArrayList sizeVector;
    Date Qdate = null;

    public void FunsoftCustomPdf(java.sql.Connection connDb, String header, String patient, Date date) {
        dbObject = new com.afrisoftech.lib.DBObject();
        Qdate = date;
        pdfheader = header;
        connectDB = connDb;
        patientNO = patient;
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

            this.generatePdf();

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String date = null;

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(" - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        try {
                            com.lowagie.text.pdf.PdfPTable headertable = new com.lowagie.text.pdf.PdfPTable(6);

                            int headerwidthstable[] = {15, 7, 25, 7, 8, 18};

                            headertable.setWidths(headerwidthstable);

                            headertable.setWidthPercentage((100));

                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            headertable.getDefaultCell().setColspan(6);

                            Phrase headerphrase = new Phrase();

                            headertable.getDefaultCell().setBorder(Rectangle.BOX);
                            headertable.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            headertable.getDefaultCell().setColspan(2);
                            headertable.getDefaultCell().setFixedHeight(70);
                            headertable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            headertable.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");

                            while (rset3.next()) {
                                headertable.getDefaultCell().setColspan(4);

                                headertable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                headertable.getDefaultCell().setBorderColor(Color.BLACK);
                                headertable.getDefaultCell().setBorder(Rectangle.BOX);
                                headerphrase = new Phrase("\n\n" + rset3.getObject(1).toString(), pFontHeadert);
                                headertable.addCell(headerphrase);
                            }
                            docPdf.add(headertable);
                        } catch (Exception sy) {
                            sy.printStackTrace();
                        }

                        /**
                         * key 1 header key2 no of columns/columns key 3 column
                         * span key 4 data
                         *
                         */
                        {

                            {

                                com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(7);
                                int totalColspan = 0;
                                int headerwidths[] = {15, 18, 25, 27, 12, 18, 18};

                                table.setWidths(headerwidths);
                                table.setWidthPercentage((100));
                                Phrase phrase = new Phrase();

                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setBorderColor(Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOX);
                                phrase = new Phrase(pdfheader, pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(7);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setBorderColor(Color.BLACK);
                                //   table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                phrase = new Phrase("".toUpperCase(), pFontHeader11);
                                table.addCell(phrase);

                                try {
                                  
                                    java.sql.PreparedStatement pstmtVectord = connectDB.prepareStatement(""
                                            + "SELECT typeof_test, description, date, user_name\n"
                                            + "  FROM hp_clinical_results where patient_no='" + patientNO + "' and date='" + Qdate + "'  ");

                                    java.sql.ResultSet rsetVectord = pstmtVectord.executeQuery();
                                    table.getDefaultCell().setColspan(7);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setBorderColor(Color.BLACK);
                                    table.getDefaultCell().setBorder(Rectangle.BOX);
                                    phrase = new Phrase("CLincal Examination".toUpperCase(), pFontHeader11);
                                    table.addCell(phrase);
                                    while (rsetVectord.next()) {
                                        table.getDefaultCell().setColspan(5);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        phrase = new Phrase(rsetVectord.getString(1) + "".toUpperCase(), pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        phrase = new Phrase(rsetVectord.getString(3) + "".toUpperCase(), pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        phrase = new Phrase(rsetVectord.getString(4) + "".toUpperCase(), pFontHeader11);
                                        table.addCell(phrase);

                                    }

                                    rsetVectord = pstmtVectord.executeQuery();
                                    table.getDefaultCell().setColspan(7);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setBorderColor(Color.BLACK);
                                    table.getDefaultCell().setBorder(Rectangle.BOX);
                                    phrase = new Phrase("provisional diagnosis".toUpperCase(), pFontHeader11);
                                    table.addCell(phrase);
                                    while (rsetVectord.next()) {
                                        table.getDefaultCell().setColspan(5);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        phrase = new Phrase(rsetVectord.getString(2) + "".toUpperCase(), pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        phrase = new Phrase(rsetVectord.getString(3) + "".toUpperCase(), pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                        phrase = new Phrase(rsetVectord.getString(4) + "".toUpperCase(), pFontHeader11);
                                        table.addCell(phrase);

                                    }

                                    Object listOfLabs[] = this.getListofStaffNos1(Qdate);
                                    ////writing down the columns 
                                    for (int k = 0; k < listOfLabs.length; k++) {
                                        System.out.println(""
                                                + "SELECT request_id, diagnosis,service, notes,user_name , clinic ,curr_date\n"
                                                + "  FROM pb_doctors_request where patient_no='" + patientNO + "' and requisition_no='LAB' and collected=false"
                                                + " and trans_date='" + Qdate + "'\n"
                                                + "   and service in (select distinct request from clerking_request where request_category='" + listOfLabs[k] + "'  ");
                                        java.sql.PreparedStatement pstmtVector = connectDB.prepareStatement(""
                                                + "SELECT request_id, diagnosis,service, notes,user_name , clinic ,curr_date\n"
                                                + "  FROM pb_doctors_request where patient_no='" + patientNO + "' and requisition_no='LAB' and collected=false"
                                                + " and trans_date='" + Qdate + "'\n"
                                                + "   and service in (select distinct request from clerking_requests where request_category='" + listOfLabs[k] + "')  ");

                                        java.sql.ResultSet rsetVector = pstmtVector.executeQuery();

                                        table.getDefaultCell().setColspan(7);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.TOP|Rectangle.BOTTOM);
                                        phrase = new Phrase("" + listOfLabs[k] + "".toUpperCase(), pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("Req NO", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("SPECIMEN", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("LAB TEST", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("TEST NOTES", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("DOCTOR", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("Pat. SOURCE", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase("Date & Time", pFontHeader11);
                                        table.addCell(phrase);
                                    //heads

                                        ///data
                                        while (rsetVector.next()) {

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(1) + "", pFontHeader);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(2) + "", pFontHeader);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(3) + "", pFontHeader);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(4) + "", pFontHeader);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(5) + "", pFontHeader);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(6) + "", pFontHeader);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(rsetVector.getString(7) + "", pFontHeader);
                                            table.addCell(phrase);
                                        }
                                        //heads
                                        //////////////////////////////
                                    }

                                } catch (Exception SqlExec) {
                                    SqlExec.printStackTrace();
                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                                }
                                docPdf.add(table);
                            }

                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {
                        BadElExec.printStackTrace();
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

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM hp_patient_visit where patient_no = '" + patientNO + "'  order by date");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofStaffNos1(Date date) {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();
            System.out.println(
                    "select distinct request_category FROM clerking_requests where\n"
                    + "   request::text in (SELECT distinct service::text FROM pb_doctors_request where"
                    + " requisition_no='LAB' and patient_no='" + patientNO + "' and trans_date='" + date + "' and collected=false)order by 1 asc");
            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payee FROM ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dealer ilike '"+memNo+"%' order by payee");
            java.sql.ResultSet rSet1x = stmt1.executeQuery(
                    "select distinct request_category FROM clerking_requests where\n"
                    + "   request::text in (SELECT distinct service::text FROM pb_doctors_request where"
                    + " requisition_no='LAB' and patient_no='" + patientNO + "' and trans_date='" + date + "' and collected=false)order by 1 asc");

            while (rSet1x.next()) {

                listStaffNoVector.addElement(rSet1x.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}
