//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
/**
 * @author Charles Waweru <cwaweru@systempartners.biz>
 * 
*/
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
//import //java.awt.Desktop;

public class MOH706LaboratorySummaryPdf implements java.lang.Runnable {

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
    com.lowagie.text.Font pFontHeaderItallic = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC);
    com.lowagie.text.Font pFontHeaderItallicB = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLDITALIC);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MOH706LaboratorySummaryPdf(java.sql.Connection connDb, java.util.Date startDate, java.util.Date lastDate) {

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A2);

            try {

                try {
                    Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());

                    //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

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

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("MOH - 706 : Laboratory Summary Report : Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {

                        try {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(15);

                            int headerwidths[] = {10, 5, 8, 3, 5, 5, 5, 10, 10, 10, 3, 5, 8, 8, 8};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(4);

                            table.getDefaultCell().setColspan(6);

                            Phrase phrase = new Phrase("", pFontHeader);

                            try {

                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(15);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setFixedHeight(50);
                                table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                                table.getDefaultCell().setFixedHeight(-1);
                                phrase = new Phrase("MOH 706_Laboratory Report", pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("LABORATORY SUMMARY REPORT", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("HEALTH FACILITY : " + compName, pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("County", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Sub-County : " + compName, pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement(District);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("1. URINE ANALYSIS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("3.HAEMATOLOGY", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("7.PARASITOLOGY", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Number Positive", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Type of examination", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("HB < 5g/dl", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5 < HB <10g/dl", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Blood smears", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.1 Urine Chemistry", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.1 Full blood count(automated)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "fbc", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "fbc", "", 5, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountRange(connectDB, "fbc", 5, 10, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Positive", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.2 Glucose", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "glucose", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "glucose", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.2 HB tests (by a HB meter)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hbc", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "hbc", "", 5, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountRange(connectDB, "hbc", 5, 10, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.1 Malaria", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "malaria", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "malaria", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.3 Ketones", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "ketone", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "ketones", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.3 CD4/CD8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. tests", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("<200", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("200-300", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Stool examinations", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.4 Proteins", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "protein", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "protein", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.3 CD4/CD8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "cd4/cd8", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "cd4/cd8", "", 200, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountRange(connectDB, "cd4/cd8", 200, 300, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.2 Total Exams", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenPositiveCount(connectDB, "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.5 HCG", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hcg", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hcg", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.4 CD4%", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Tests", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("<25%", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(">25%", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Microscopic findings", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.6 Urine microscopy", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.lightGray);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.4 CD4%", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "cd4", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "cd4", "", 25, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentOver(connectDB, "cd4", "", 25, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.3 Taenia spp.", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "taenia", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "taenia", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.7 Pus cells (>5hpf)", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pus", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "pus", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Type of examination", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Positive", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.4 Hymenoiepis nana.", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hymenoiepis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hymenoiepis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.8 S. haematobium", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "haematobium", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "haematobium", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.5 Sickling test", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "sickling", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "sickling", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.5 Hookworm", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hookworm", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hookworm", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.9 T. vaginalis", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "vaginalis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "vaginalis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.6 Manual WBC counts", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "wbc", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "wbc", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.6 Roundworms", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "roundworms", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "roundworms", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.10 Yeast cells", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "yeast", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.lightGray);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "yeast", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.7 Peripheral blood films", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "blood films", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.7 S. Mansoni", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "mansoni", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "mansoni", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.11 Red blood cells", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "red blood cells", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "rbc", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("3.8 Erythrocyte Sedimentation rate", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "erythrocyte", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "erythrocyte", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.8 Trichuris trichura", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "trichura", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "trichura", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.12 Bacteria", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "bacteria", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "bacteria", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.9 E. histolytica", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "histolytica", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "histolytica", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1.13 Spermatozoa", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "spermatozoa", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "spermatozoa", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("4. BLOOD GROUPING AND CROSSMATCH", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("7.10 Giardia lambila", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "lambila", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "lamblia", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Measure", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Number", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. outside normal range", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("4.1 Total groupings done", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "grouping", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("8. SEROLOGY", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.1 Blood sugar", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "sugar", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "sugar", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("4.1 Total groupings done", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "grouping", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Serological test", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total exam", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Positive", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.2 OGTT", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "ogtt", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "ogtt", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("4.2 Blood units grouped", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "grouping", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.1 Rapid Plasma Region", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "plasma", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "plasma", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No outside normal range", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("4.3 Transfusion reactions", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "transfusion", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.2 TPHA", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tpha", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tpha", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.3 Renal function tests", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "renal function", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "renal function", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("4.4 Blood cross matches", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "cross match", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.3 ASOT", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "asot", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "asot", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.4 Creatinine", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "creatinine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "creatinine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.4 HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hiv", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hiv", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.5 Urea", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "urea", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "urea", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("5. BLOOD SAFETY ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.5 Widal", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "widal", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "widal", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.6 Sodium", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "sodium", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "sodium", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Measure", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Number", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.6 Brucella", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "brucella", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "brucella", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.7 Potasium", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "potasium", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "potasium", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.1 Blood units collected from regional blood transfusion centres", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.7 Rheumatoid factor", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "rheumatoid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "rheumatoid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.8 Chlorides", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "chlorides", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "chlorides", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.2 Blood units collected from other centres and screened at facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.8 Helicobacter pylori", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pylori", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "pyroli", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No outside normal range", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.3 Blood units screened at facility that are HIV positive", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hiv", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.9 Hepatitis A test", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hepatitis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hepatitis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.9 Liver function tests", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "liver function", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "liver function", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.4 Blood units screened at facility that are Hepatitis B positive", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.10 Hepatitis B test", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hepatitis b", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hepatitis b", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.10 Direct Bilirubin", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "bilirubin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "birirubin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.5 Blood units screened at facility that are Hepatitis C positive", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.11 Hepatitis C test", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hepatitis c", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hepatitis c", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.11 Total Bilirubin", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "bilirubin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "bilirubin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.6 Blood units positive for other infections", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("8.12 Viral load", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "viral load", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "viral load", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.12 ASAT(SGOT)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "sgot", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "sgot", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("5.7 Blood units transfused", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.13 ALAT(SGPT)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "spgt", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "spgt", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("6. HISTOLOGY AND CYTOLOGY", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("9 BACTERIOLOGY TESTS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.14 Serum Protein", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "serum", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "serum", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("SMEARS", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Infective", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Non Infective", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("9.1 Total bacteriological tests", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.15 Albumin", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "albumin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "albumin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Benign", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Malignant", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Sample", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total exam", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Cultures done", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.16 Alkaline Phosphatase", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "alkaline", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "alkaline", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.1 PAP smear", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pap smear", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "pap smear", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "pap smear", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "pap smear", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.2 Urine", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "Urine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "Urine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.17 Gamma GT", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "gamma", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                //to decorate
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "gamma", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.2 Touch prep", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Touch prep", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "Touch prep", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "Touch prep", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Touch prep", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.3 Pus swabs", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "pus swabs", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "pus swabs", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total exam", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No outside normal range", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.3 Tissue impressions", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tissue impressions", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "tissue impressions", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "tissue impressions", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "tissue impressions", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.4 High Vaginal Swab", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "vaginal swab", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "vaginal swab", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.18 Lipid profile", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "lipid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("FINE NEEDLE ASPIRATES (FNA)", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.6 Stool", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "Stool", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "Stool", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.19 Total cholesterol", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "cholesterol", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "cholesterol", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.4 Thyroid", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "thyroid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "thyroid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "thyroid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "thyroid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.8 Blood", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "blood", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "blood", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.20 Triglycerides", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "triglycerides", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "triglycerides", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.5 Lymph nodes", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.9 CSF", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.22 LDL", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "ldl", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "ldl", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.7 Breast", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Breast", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "Breast", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "Breast", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Breast", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.10 Water", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "water", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "water", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exams", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. outside normal range", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.8 Soft tissue masses", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Soft tissue masses", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "Soft tissue masses", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "Soft tissue masses", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Soft tissue masses", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("9.11 Food", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "food", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "food", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.23 CSF Chemistry", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "csf chemistry", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.9 Others", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "Others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "Others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.24 Proteins", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "proteins", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "proteins", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("FLUID CYTOLOGY", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("10.SPUTUM", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.25 Glucose", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "glucose", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "glucose", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.11 CSF", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "csf", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total exam", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Positive", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No outside normal margin", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.12 Pleural fluid", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pleural fluid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "pleural fluid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "pleural fluid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "pleural fluid", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("10.1 TB new suspects", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tb new suspect", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tb new suspect", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.26 Body fuids", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "body fluids", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "body fluids", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.13 Urine", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "urine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "urine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "urine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "urine", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("10.2 Followup", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tb followup", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tb followup", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.27 Proteins", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "proteins", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "proteins", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("6.14 Others", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "others", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("10.3 TB smears", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tb smears", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tb smears", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.28 Glucose", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "glucose", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "glucode", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("TISSUE HISTOLOGY", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("10.4 MDR TB", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "mdr tb", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "mdr tb", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Hormones", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. outside mormal range", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.15 Cervix", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "cervix", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "cervix", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "cervix", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.29 T3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "t3", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "t3", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.16 Prostrate", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "prostrate", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "prostrate", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "prostrate", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("11. SPECIMEN REFERRAL TO HIGHER LEVELS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.30 T4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "t4", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "t4", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.18 Ovary", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "ovary", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "ovary", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "ovary", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Specimen", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Specimen", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Results received", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.31 TSH", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tsh", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tsh", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.19 Uterus", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "uterus", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "uterus", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "uterus", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("11.1 Referred to County Hospital", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Referred to County Hospital", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Referred to County Hospital", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2.32 PSA", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "psa", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "psa", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.20 Skin", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "skin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "skin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "skin", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("11.2 Referred to National Hospitals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Referred to National Hospitals", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Referred to National Hospitals", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.21 Head and Neck", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "head and neck", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "head and neck", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "head and neck", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("11.3 Referred to National Reference Lab", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Referred to National Reference Lab", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Referred to National Reference Lab", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.22 Dental", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "dental", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "dental", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "dental", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("11.4 Referred to KEMRI", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Referred to KEMRI", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Referred to KEMRI", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.23 GIT", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "git", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "git", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "git", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("11.5 Referred for Quality Analysis", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Referred for Quality Analysis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Referred for Quality Analysis", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.24 Lymph nodes", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveBenignCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "lymph nodes", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("BONE MARROW STUDIES", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Malignant", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.25 Bone marrow apirate", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "bone marrow aspirate", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "bone marrow aspirate", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("6.26 Trephine biopsy", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerInfectiveCount(connectDB, "Trephine biopsy", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Trephine biopsy", beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(15);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(15);
                                phrase = new Phrase("12.BACTERIOLOGY ISOLATES", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("12.1 Total Sensitivity tests done", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(10);
                                phrase = new Phrase("12.2 Total antibiotic resistant cases detected", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(15);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(15);
                                //table.getDefaultCell().setHorizontalAlignment(PDFcell.);
                                phrase = new Phrase("ISOLATE", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Urine", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Pus", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HVS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Throat", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Stool", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Blood", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("CSF", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Water", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Food", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.3 Neissseria gonorrhoeae", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria gonorrhoeae", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

//    String specimentType[] = {"Urine","Pus","HVS","Throat","Stool", "Blood", "CSF", "Water","Food"};
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.4 Neissseria meningitidis", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.5 Klebsiella", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Klebsiella", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.6 Staphyloccoci", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Staphyloccoci", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.7 Streptococcus", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Streptococcus", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.8 Proteus", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Proteus", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.9 Shigella flex", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Shigella flex", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.10 Salmonella", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Salmonella", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.11 V cholera", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "V cholera", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.12 E. coli", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "E. coli", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.13 C. neoformans", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "C. neoformans", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.14 Cardinella vaginalis", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Cardinella vaginalis", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.15 Haemophilus", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Haemophilus", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Neissseria meningitidis", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.16 Bordotella pertusis", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Bordotella pertusis", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.17 Psuedomonas", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Psuedomonas", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.18 Coliforms", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Coliforms", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.19 Faecal coliforms", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Faecal coliforms", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("12.20 Enterococcus faecalis", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Urine", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Pus", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "HVS", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Throat", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Stool", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Blood", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "CSF", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Water", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterIsolate(connectDB, "Enterococcus faecalis", "Food", beginDate, endDate)), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Report Compiled By:", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Date:", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Designation:", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Signature:", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(15);
                                phrase = new Phrase(" \n\n\n", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(11);
                                phrase = new Phrase("This form should be completed at facility in duplicate; Original copy sent to the DMLT to reach by 5th of every month for entry into DHIS and duplicate copy remains as the facility record.", pFontHeaderItallic);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Courtesy of Funsoft I-HMIS", pFontHeaderItallicB);
                                table.addCell(phrase);

                            } catch (java.text.ParseException psExec) {

                                psExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

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

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            java.sql.PreparedStatement stmt1 = connectDB.prepareStatement("SELECT DISTINCT patient_no, patient_name FROM hp_lab_results where date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ORDER BY patient_name ASC");

            java.sql.ResultSet rSet1 = stmt1.executeQuery();

            while (rSet1.next()) {

                System.out.println("Patient names : [" + rSet1.getString(2) + "]");

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();

        System.out.println("Done list of Staff Nos ...");

        return listofStaffNos;

    }
}
