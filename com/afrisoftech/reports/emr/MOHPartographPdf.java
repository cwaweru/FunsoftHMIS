//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.openide.util.Exceptions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.XYSeries;
import org.jfree.data.XYSeriesCollection;
//import //java.awt.Desktop;

public class MOHPartographPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date endDate = null;
    int numberSeq = 0;
    java.lang.String Categ = null;
    java.lang.String ReportType = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String Gender = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    com.lowagie.text.Font pFontHeader22 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MOHPartographPdf(java.sql.Connection connDb, java.util.Date startDate, java.util.Date lastDate) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = startDate;

        endDate = lastDate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberListPdf().MemberListPdf();
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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3);

            try {
                Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());

                //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

                PdfWriter writer = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                String compName = null;

                String District = null;

                String Region = null;

                String date = null;

                try {
                    java.sql.Statement st3 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();

                    java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch from pb_hospitalprofile");
                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                    while (rset2.next()) {
                        compName = rset2.getObject(1).toString();
                        District = rset2.getObject(2).toString();
                    }
                    while (rset4.next()) {
                        date = rset4.getObject(1).toString();
                    }
                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("FACILITY NAME : " + compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                    headerFoter.setRight(5);

                    docPdf.setHeader(headerFoter);

                } catch (java.sql.SQLException SqlExec) {

                    SqlExec.printStackTrace();

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                }

                com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("MOH - 329 : Partograph : Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                docPdf.setFooter(footer);

                docPdf.open();
                try {
                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(54);

                    int headerwidths[] = {20, 10, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10};

                    table.setWidths(headerwidths);

                    table.setWidthPercentage((100));

                    table.setHeaderRows(4);

                    table.getDefaultCell().setColspan(6);

                    Phrase phrase = new Phrase("Foetal Heart Rx", pFontHeader);

                    try {
                        try {
                            com.afrisoftech.lib.DateFormatter dateFormatters = null;
                            java.lang.String monthString = null;
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            try {
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                monthString = dateFormatter.getDateString();

                                dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");
                            } catch (ParseException ex) {
                                Exceptions.printStackTrace(ex);
                            }

                            java.lang.String yearString = dateFormatters.getDateString();

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(54);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(50);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(-1);
                            table.getDefaultCell().setColspan(30);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("DIVISION OF REPRODUCTIVE HEALTH", pFontHeader22);
                            table.addCell(phrase);
                            phrase = new Phrase("MOH 329_DELIVERY PROCESS - PARTOGRAPH", pFontHeader22);

                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("HEALTH FACILITY : " + compName, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("AGENCY : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("CONSTITUENCY: ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("SUB-COUNTY : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("COUNTY : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Client Name: ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("Client No. : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("Age : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("Admission Date : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("Admission Time : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase("Visit ID : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(8);
                            phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(14);
                            phrase = new Phrase("Gravida : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(14);
                            phrase = new Phrase("Para: ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Raptured Membranes : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Hours on admission : ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);



                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("");

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Foetal Heart Rate", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("180", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("170", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("160", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("150", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("140", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("130", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("120", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("110", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("100", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("90", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("80", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(54);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Liquor", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("C/B/M/F", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("MOULDING", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(54);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(50);
                            ByteArrayOutputStream bout = new ByteArrayOutputStream();
                            ChartUtilities.writeChartAsPNG(bout, getPartoChart(), 750, 380);
                            table.addCell(Image.getInstance(bout.toByteArray()));
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(54);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("5", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("4", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("3", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("2", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("1", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(54);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Oxytocin UI", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("drops Min", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(54);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("DRUGS GIVEN \n AND \n IV FLUIDS", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 24; i++) {
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Pulse", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("180", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("AND", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("170", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("BP", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("160", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("150", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("140", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("130", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("120", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("110", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("100", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("90", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("80", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("70", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("60", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 48; i++) {
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(54);
                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("TEMP", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 24; i++) {
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("RESP", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 24; i++) {
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("PROT", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 24; i++) {
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("ACET", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 24; i++) {
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("VOL", pFontHeader11);
                            table.addCell(phrase);
                            for (int i = 0; i < 24; i++) {
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            // Summary of labour

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(48);
                            phrase = new Phrase("SUMMARY OF LABOUR", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("1st Stage", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Induction Labour", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Duration", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("No of VE", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("2nd Stage", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Mode of delivery", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Duration", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(16);
                            phrase = new Phrase("Oxytocin/Egometrine", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("3rd Stage", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Baby Alive", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("APGAR Score 1 Min   5 Min", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Resuscitation", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Duration", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Placenta ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Mebranes", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Cord", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("placenta Wt", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Blood Loss (M/s) ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("Perineal", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("Repair", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("Mother BP", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("Pulse", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("Temp", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(7);
                            phrase = new Phrase("Resp", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Baby Length ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Weight", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("HC", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("Drugs Given", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(24);
                            phrase = new Phrase("Delivered By:", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(24);
                            phrase = new Phrase("Date and Time of Delivery", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(24);
                            phrase = new Phrase("Person managing partograph:", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(24);
                            phrase = new Phrase("Printed by:", pFontHeader11);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader11);
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

    /**
     *
     * @return @throws SQLException
     * @throws IOException
     */
    public static JFreeChart getPartoChart() throws SQLException, IOException {
        java.sql.Statement stm = connectDB.createStatement();
        java.sql.ResultSet rs = stm.executeQuery("SELECT date_part('hour', input_date), pulse, temp, pulse_oximetry, blood_glucose from hp_signs_record WHERE patient_no = '0356346' ORDER BY 1");
        org.jfree.data.DefaultTableXYDataset dataset = new org.jfree.data.DefaultTableXYDataset();
        // final org.jfree.data.JDBCXYDataset data = new org.jfree.data.JDBCXYDataset(connectDB, "SELECT date_part('hour', input_date), pulse, temp, blood_glucose as glucose, pulse_oximetry as oximetry from hp_signs_record WHERE patient_no = '0356346' ORDER BY 1");
        XYSeries series1 = new XYSeries("Pulse");
        XYSeries series2 = new XYSeries("Temp");
        XYSeries series3 = new XYSeries("Pulse_Oximetry");
        XYSeries series4 = new XYSeries("Blood Glucose");
        int i = 0;
        while (rs.next()) {
            series1.add(i, rs.getDouble("pulse"));
            series2.add(i, rs.getDouble("temp"));
            series3.add(i, rs.getDouble("pulse_oximetry"));
            series4.add(i, rs.getDouble("blood_glucose"));
            i++;
        }
        for (int j = i; j < 12; j++) {
            series1.add(j, null);
            series2.add(j, null);
            series3.add(j, null);
            series4.add(j, null);
        }
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(series1);
        seriesCollection.addSeries(series2);
        seriesCollection.addSeries(series3);
        seriesCollection.addSeries(series4);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Partograph Chart",
                "Hours",
                "CERVICAL DILATION (CM) - PLOT X",
                seriesCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart; //ChartFactory.createXYLineChart(director, director, director, dataset, director, director, director, director); //createPieChart("Movies / directors", dataset, true, true, false);
    }
}
