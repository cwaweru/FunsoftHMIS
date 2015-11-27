//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
//import //java.awt.Desktop;

public class MOH711RHCHReportPdf implements java.lang.Runnable {

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
    com.lowagie.text.Font pFontHeader22 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MOH711RHCHReportPdf(java.sql.Connection connDb, java.util.Date startDate, java.util.Date lastDate) {

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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("MINISTRY OF HEALTH : " + compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        headerFoter.setRight(5);

                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("MOH - 711 : Integrated Reproductive Health & Child Health Programme Report : Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(13);

                        int headerwidths[] = {5, 20, 10, 10, 10, 8, 5, 5, 20, 10, 10, 10, 8};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(4);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase("", pFontHeader);
                        try {
                            try {

                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(13);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setFixedHeight(50);
                                table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                                table.getDefaultCell().setFixedHeight(-1);
                                phrase = new Phrase("MOH - 711_Integrated Programme Summary Report Form : Reproductive & Child Health, Medical and Rehabilitation Services Report".toUpperCase(), pFontHeader22);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(13);

                                phrase = new Phrase(" ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                phrase = new Phrase("Integrated Programme Summary Report Form : Reproductive & Child Health, Medical and Rehabilitation Services Report".toUpperCase(), pFontHeader22);
//                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("FACILITY NAME: " + compName, pFontHeader22);
                                table.addCell(phrase);

                                phrase = new Phrase("COUNTY: " + compName, pFontHeader22);
                                table.addCell(phrase);

                                phrase = new Phrase("SUB-COUNTY: " + compName, pFontHeader22);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader22);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader22);
                                table.addCell(phrase);


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                table.getDefaultCell().setColspan(5);

                                phrase = new Phrase("A.ANC/PMCT", pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("B.Maternity and Delivery", pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement("");
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of ANC Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("New", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getNewANCVisitors(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Normal Deliveries", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryMethodCount(connectDB, beginDate, endDate, "Normal")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of ANC Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Re-Visit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getRevisitingANCVisitors(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Cesarean Sections", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryMethodCount(connectDB, beginDate, endDate, "Cesarean")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. Clients given IPT(1st dose)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getIPTANCVisitors(connectDB, beginDate, endDate, "1")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Breech Delivery", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryMethodCount(connectDB, beginDate, endDate, "Breech")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. Clients given IPT(2nd dose)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getIPTANCVisitors(connectDB, beginDate, endDate, "2")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Assisted Vaginal Deliveries(Vacuum Extraction)", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryMethodCount(connectDB, beginDate, endDate, "Vacuum Extraction")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. Clients with HB < 11g/dl", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getHBANCVisitors(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("TOTAL DELIVERIES", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryMethodCount(connectDB, beginDate, endDate, null)), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("6", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of ANC Clients completed 4 Ante-Natal visits", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getCompletedANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("6", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Live Births", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildBirthStatusCount(connectDB, beginDate, endDate, "Alive")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("7", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of LLITNs distributed to ANC clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getLITNANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("7", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Fresh still Births", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildBirthStatusCount(connectDB, beginDate, endDate, "Still Birth")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of ANC Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Counselled", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getCounselledANCVisitors(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Macerated still Births", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildBirthStatusCount(connectDB, beginDate, endDate, "Macerated Still birth")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("9", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of ANC Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Tested", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getHIVANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("9", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Birth with Deformities", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildBirthDeformitiesCount(connectDB, beginDate, endDate, "Y")), pFontHeader1);
                                table.addCell(phrase);


                                phrase = new Phrase("10", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of ANC Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HIV+", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getHIVPositiveANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("10", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. with low APGAR score", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getLowAPGARCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);


                                phrase = new Phrase("11", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of Mothers issued with Nevirapine", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getNevirapineIssuesANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("11", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Underweight babies (below 2500 grams)", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getUnderWeightInfantsCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("12", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Tested for Syphilis", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getVDRLTestedANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("12", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Pre-term babies", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPreTermCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("13", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of Clients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Positive (+ve)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getVDRLResultPositiveANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("13", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of babies discharged alive", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getNeonatalStatusCount(connectDB, beginDate, endDate, "Alive")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("14", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of clients issued with preventive ARVs", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSDNVPANCVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("14", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of infants initiated on breastfeeding within 1 hour after birth", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getInitiatedBreastFeedingCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("15", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. assessed for ART eligibility - WHO", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getARTAssessedWHOVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("15", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Neonatal deaths", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getNeonatalStatusCount(connectDB, beginDate, endDate, "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("16", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. assessed for ART eligibility - CD4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getARTAssessedCD4Visits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("16", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Maternal Deaths", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getMaternalDeathCount(connectDB, beginDate, endDate,"Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("17", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. started on ART", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getARTStartedVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Maternal Complications", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Alive", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Deaths", pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("18", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of mothers counselled on infant feeding options", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getCounselledFeedingVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("17", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("A.P.H. (Ante Partum Haemorrhage)", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Ante Partum Haemorrhage", "Alive")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Ante Partum Haemorrhage", "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("19", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of partners", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Counselled ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getCounselledPartnersVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("18", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("P.P.H. (Post Partum Haemorrhage)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Post Partum Haemorrhage", "Alive")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Post Partum Haemorrhage", "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("20", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of partners", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Tested", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getTestedPartnersVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("19", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Eclampsia", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Eclampsia", "Alive")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Eclampsia", "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("21", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of partners", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HIV+", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPositivePartnersVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("20", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Ruptured Uterus", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Ruptured Uterus", "Alive")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Ruptured Uterus", "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("22", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HIV+ referred for follow up", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Mothers", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getReferredMothersVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("21", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Obstructed Labour", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Obstructed Labour", "Alive")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Obstructed Labour", "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("23", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HIV+ referred for follow up", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Partners", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getReferredPartnersVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("22", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Sepsis", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Sepsis", "Alive")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getChildLiveryComplicationsCount(connectDB, beginDate, endDate, "Sepsis", "Dead")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("24", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of women who received ARV prophylaxis for the baby", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getBabyARVprophylaxisVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Maternity PMTCT", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("25", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Total women done breast examination", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getBreastExaminationVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("23", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No of Women councelled", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTCounselledCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("26", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Total women given exercise", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getANCExerciseVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("24", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Women tested for HIV", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTTestedHIVCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("27", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No of adolescents 10-17 years presenting with pregnancy", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getAdolescentPregnanciesVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("25", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Women found HIV positive", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTPositiveHIVCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("28", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No of clients issued with Iron", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getIronIssuedVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("26", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of Women given preventive ARVs", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTPreventiveARVGivenCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("29", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No of clients issued with Folic", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFolicIssuedVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("27", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of infant preventive ARVs administered", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTPreventiveARVGivenCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("30", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No of clients issued with Combined Ferrous Folate", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFerrousFolateIssuedVisits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("28", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Total deliveries from HIV +ve women", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTDeliveriesPositiveHIVCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("31", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No of infants tested for HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("At 6 wks - 3 Months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "HVT", 0, 3)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("29", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("No. of women initiated cotrimoxazole", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTInitiatedContrimoxazoleHIVCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("32", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No of infants tested for HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("After 3 Months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "HVT", 3, 60)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("33", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getReferralsFromOtherFacilities(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("30", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From Other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTReferralsINFROMFacilityCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("34", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getReferralsToOtherFacilities(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("31", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To Other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTReferralsOUTTOFacilityCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("35", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getReferralsFromCommunityUnits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("32", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTReferralsINFROMCommunityCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("35", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getReferralsToCommunityUnits(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("33", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPMTCTReferralsOUTTOCommunityCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(13);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("C. Family Planning (Number of clients issued with contraceptives)", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("NEW", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("RE-VISITS", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("E. Child Health and Nutrition Information System(CHANIS)", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Pills", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Progestin only pills", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "PP", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "PP", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setBackgroundColor(Color.yellow);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Weight for Age", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("F", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("M", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("TOTAL", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setBackgroundColor(Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Pills", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Combined Oral Contraceptive pills", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "CP", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "CP", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal Weight for Age", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 0, 6, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Pills", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Emergency Contraceptive pill", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "EC", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "EC", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Underweight", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 0, 6, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Injections", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Injectables", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "IV", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "IV", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severe Underweight", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 0, 6, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("I.U.C.D.", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Insertion", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "IUD", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "IUD", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Overweight", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 0, 6, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Implants", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Insertion (1-Rod)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "IS", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "IS", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Obese", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 0, 6, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 0, 6, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 0, 6, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("7", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Implants", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Insertion (2-Rod)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "ISR2", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "ISR2", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Weighed", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Sterilization", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("BTL", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "STB", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "STB", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("7", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 - 23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal Weight for Age", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 6, 23, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("9", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Sterilization", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Vasectomy", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "STV", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "STV", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Underweight", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 6, 23, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 6, 23, "Female")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("10", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Condoms", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Number of clients received (Male condoms)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "MC", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "MC", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("9", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severe Underweight", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 6, 23, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("11", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Condoms", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Number of clients received (Female condoms)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "FC", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "FC", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("10", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Overweight", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 6, 23, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("12", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Cycle Beads", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Number of clients received (Cycle beads)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "CB", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "CB", "Revisit")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("11", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Obese", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 6, 23, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 6, 23, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("13", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Natural Family Planning", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPNaturalMethodsCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("12", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Weighed", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("14", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Total Number of Clients", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPTotalClientsCount(connectDB, beginDate, endDate, "New")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPTotalClientsCount(connectDB, beginDate, endDate, "Revisit")), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("13", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal Weight for Age", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 24, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NWA", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("15", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Removals", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("I.U.C.D", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "RIUCD", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "RIUCD", "Revisit")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("14", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Underweight for Age", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 24, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "UW", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("16", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Removals", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Implants", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "RIMP", "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPServicesCount(connectDB, beginDate, endDate, "RIMP", "Revisit")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("15", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severe Underweight for Age", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 24, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SA", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("17", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("HIV Clients", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Counselled", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCounselledClientsCount(connectDB, beginDate, endDate, "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCounselledClientsCount(connectDB, beginDate, endDate, "Revisit")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("16", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Overweight for Age", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 24, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OW", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("18", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("HIV Clients", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Tested", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPHIVTestedClientsCount(connectDB, beginDate, endDate, "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPHIVTestedClientsCount(connectDB, beginDate, endDate, "Revisit")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("17", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Obese", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 24, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "OB", 24, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("19", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("HIV Clients", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HIV Positive (+ve)", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPHIVPositiveClientsCount(connectDB, beginDate, endDate, "New")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPHIVPositiveClientsCount(connectDB, beginDate, endDate, "Revisit")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("18", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Weighed", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Cervical cancer screening", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Not Done", pFontHeader11);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. +ve", pFontHeader11);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. -ve", pFontHeader11);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("19", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("MUAC \n 6 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal(Green)", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCG", 6, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCG", 6, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCG", 6, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCG", 6, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("20", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Using VIA/VILI/HPV/ VILI/HPV:", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "-", "-")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "1", "1") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "2", "1") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "3", "1") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "4", "1")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "1", "2") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "2", "2") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "3", "2") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "4", "2")), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("20", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("MUAC \n 6 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Moderate(Yellow)", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCY", 6, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCY", 6, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCY", 6, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCY", 6, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("21", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Suspect Cancer", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "1", "3") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "2", "3") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "3", "3") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "4", "3") + com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "5", "3")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("21", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("MUAC \n 6 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severe(Red)", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCR", 6, 59, "Female")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCR", 6, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCR", 6, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MCR", 6, 59, "Male")), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("22", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Pap Smear", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "5", "1")), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPCervicalCancerResultsCount(connectDB, beginDate, endDate, "5", "2")), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("21", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("MUAC \n 6 -  59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total measured", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setBackgroundColor(Color.yellow);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Height for Age", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("23", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPReferralsINCount(connectDB, beginDate, endDate, "Health Facility")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("22", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 -  <6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal Height for Age", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 0, 6, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 0, 6, "Female")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPReferralsOUTCount(connectDB, beginDate, endDate, "Health Facility")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("23", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 -  <6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Stunted", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 0, 6, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("25", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPReferralsINCount(connectDB, beginDate, endDate, "Community")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 -  <6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severely Stunted", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 0, 6, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("26", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getFPReferralsOUTCount(connectDB, beginDate, endDate, "Community")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("25", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 -  <6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Measured", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("26", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal Height for Age", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("D.PNC : Post Natal Care Services", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("27", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Stunted", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 6, 23, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Breast Exam", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesBreastExamCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("28", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 -  23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severely Stunted", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 6, 23, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("No. of Women counselled", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesCounselledCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("29", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 - 23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Measured", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Women tested for HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesHIVTestedCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("30", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Normal Height for Age", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 24, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 24, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "NHA", 24, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Women found HIV +ve", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesHIVPositiveCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("31", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Stunted", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 24, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 24, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "ST", 24, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("No. of women issued with preventive ARVs", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesMotherPreventiveARVCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("32", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Severely Stunted", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 24, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 24, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 24, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "SST", 24, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("No. of infants issued with preventive ARVs", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesInfantPreventiveARVCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("33", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("24 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total Measured", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("7", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Total Deliveries from HIV +ve women", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesHIVPositiveCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setBackgroundColor(Color.yellow);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Other", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("F", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("M", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("TOTAL", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No initiated contrimoxazole", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Women", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesMothersInitiatedCTXCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("34", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("New Visits", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorNewVisitCount(connectDB, beginDate, endDate, 0, 59,"Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorNewVisitCount(connectDB, beginDate, endDate, 0, 59,"Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorNewVisitCount(connectDB, beginDate, endDate, 0, 59,"Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorNewVisitCount(connectDB, beginDate, endDate, 0, 59,"Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("9", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No initiated contrimoxazole", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Infant", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesInfantsInitiatedCTXCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("35", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Kwashiokor", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("10", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No of cases of Fistula", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesFistulaCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("36", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Marasmus", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "KW", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("11", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("PNC Given Exercise", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesPNCExerciseGivenCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("37", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Faltering Growth", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "FG", 0, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "FG", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "FG", 0, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "FG", 0, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("12", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Cervical cancer screening", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Negative", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesCervicalCancerScreeningResultCount(connectDB, beginDate, endDate, "N")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("39", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("13", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Cervical cancer screening", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Positive", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesCervicalCancerScreeningResultCount(connectDB, beginDate, endDate, "P")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("40", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - < 6 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Exclusive breast feeding", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "EBF", 0, 6, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "EBF", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "EBF", 0, 6, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "EBF", 0, 6, "Male")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("14", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Cervical cancer screening", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Suspected cancer", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesCervicalCancerScreeningResultCount(connectDB, beginDate, endDate, "Suspected")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("41", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("12 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Dewormed", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "DW", 12, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "DW", 12, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "DW", 12, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "DW", 12, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("15", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" Partner Counselling and testing for HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Counselled", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesPartnerHIVCounselledCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("42", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6 - 23 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("MNPs Supplementation", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MNP", 6, 23, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MNP", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MNP", 6, 23, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "MNP", 6, 23, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("16", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Partner Counselling and testing for HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Tested", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesPartnerHIVTestedCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("43", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("0 - 59 months", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Any Disability", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "AD", 0, 59, "Female")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "AD", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "AD", 0, 59, "Female") + com.afrisoftech.lib.CWCIndicators.getCWCIndicatorCount(connectDB, beginDate, endDate, "AD", 0, 59, "Male")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("17", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Counselling and testing for HIV", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("HIV Positive", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesPartnerHIVPositiveCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("18", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesReferralsINCount(connectDB, beginDate, endDate, "health facility")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("F. Rehabilitation Services", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("19", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To Other Health Facility", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesReferralsOUTCount(connectDB, beginDate, endDate, "health facility")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Number assessed", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getRehabilitationAssessmentCount(connectDB, beginDate, endDate, "Assessed")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("20", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("From Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesReferralsINCount(connectDB, beginDate, endDate, "community")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Number Treated", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getRehabilitationAssessmentCount(connectDB, beginDate, endDate, "Treated")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("21", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("To Community Unit", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPNCServicesReferralsOUTCount(connectDB, beginDate, endDate, "community")), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Number Rehabilitated", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getRehabilitationAssessmentCount(connectDB, beginDate, endDate, "Rehabilitated")), pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Number referred for further investigations", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getRehabilitationReferralsCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("G. Medical Social Work", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Total", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Number integrated to communities", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getRehabilitationCommunityIntegrationCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Pysco-Social Counselling", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Psycho-Social Counselling")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Alcohol and Drug Abuse", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Alcohol and Drug Abuse")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("H. Physiotherapy Services ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("< 5yrs", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5-19 yrs", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("20 yrs +", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("3", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Mental Illness", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Mental Illness")), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("1", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of PWDs identified and receiving physiotherapy", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("OPD", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "OUT", "DISABLED", 0, 5)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "OUT", "DISABLED", 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "OUT", "DISABLED", 20, 500)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("4", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Adolescent Issues", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Adolescent Issues")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("2", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of PWDs identified and receiving physiotherapy", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("IN-Patient", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "IN", "DISABLED", 0, 5)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "IN", "DISABLED", 5, 20)), pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "IN", "DISABLED", 20, 500)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Psyco-Social Assessments (psycho, social and economic)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Psyco-Social Assessment (psycho, social and economic)")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("3", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of other clients/patients receiving physiotherapy", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("OPD", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "OUT", "OTHER", 0, 5)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "OUT", "OTHER", 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "OUT", "OTHER", 20, 500)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Social Investigations (Home Visits/Follow ups)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Social Investigations")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("4", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("No. of other clients/patients receiving physiotherapy", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("IN-Patient", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "IN", "OTHER", 0, 5)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "IN", "OTHER", 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDPhysiotherapyServicesCount(connectDB, beginDate, endDate, "IN", "OTHER", 20, 500)), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("7", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Social rehabilitation", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Social Rehabilitation")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("5", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Total Number of treatments", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getALLPhysiotherapyServicesCount(connectDB, beginDate, endDate, 0, 5)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getALLPhysiotherapyServicesCount(connectDB, beginDate, endDate, 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getALLPhysiotherapyServicesCount(connectDB, beginDate, endDate, 20, 500)), pFontHeader11);
                                table.addCell(phrase);



                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("8", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Outreach services/Health talks)", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Outreach Sevices/Health Talks")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("6", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("PWDs assessed for registration", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDServicesCount(connectDB, beginDate, endDate, 0, 5)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDServicesCount(connectDB, beginDate, endDate, 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getPWDServicesCount(connectDB, beginDate, endDate, 20, 500)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("9", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Referrals", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceReferralsCount(connectDB, beginDate, endDate)), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("7", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Number of clients receiving out/in reach services", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getOutInReachServicesCount(connectDB, beginDate, endDate, 0, 5)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getOutInReachServicesCount(connectDB, beginDate, endDate, 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getOutInReachServicesCount(connectDB, beginDate, endDate, 20, 500)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("10", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Number of waived patients", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getSocialWorkServiceCount(connectDB, beginDate, endDate, "Waiver appraisal")), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("8", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Number of pregnant mothers attending ANC receiving counselling", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getANCCounsellingServicesCount(connectDB, beginDate, endDate, 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getANCCounsellingServicesCount(connectDB, beginDate, endDate, 20, 500)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("9", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("WRA receiving pre-natal/post-natal exercices", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getANCPNCExerciseServicesCount(connectDB, beginDate, endDate, 5, 20)), pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(String.valueOf(com.afrisoftech.lib.FPServiceIndicators.getANCPNCExerciseServicesCount(connectDB, beginDate, endDate, 20, 500)), pFontHeader11);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBackgroundColor(Color.yellow);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Report Compiled by:", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("10", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Amount of FIF collected", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.FPServiceIndicators.getFIFAmountCollected(connectDB, beginDate, endDate)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Name:", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("11", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Amount of FIF waived", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.FPServiceIndicators.getFIFWaivedAmount(connectDB, beginDate, endDate)), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Designation", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("12", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Amount of FIF exempted", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(com.afrisoftech.lib.CurrencyFormatter.getFormattedDouble(com.afrisoftech.lib.FPServiceIndicators.getFIFExemptedAmount(connectDB, beginDate, endDate)), pFontHeader11);
                                table.addCell(phrase);

                                
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Date:", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("12", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Number of disability committees held", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                
                                                                
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Signature:", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("", pFontHeader11);
                                table.addCell(phrase);
                  
                                
                            } catch (java.text.ParseException psExec) {

                                psExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

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
}
