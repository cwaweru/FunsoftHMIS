//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MOHTBHIVReportPdf implements java.lang.Runnable {

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

    public void MOHTBHIVReportPdf(java.sql.Connection connDb, java.util.Date startDate, java.util.Date lastDate) {

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A2.rotate());

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

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("MOH - TBHIVCF : Leprosy, Tuberculosis and Lung Disease Case-Finding Summary : Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(22);

                        int headerwidths[] = {15, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 15};

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
                            table.getDefaultCell().setColspan(22);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(50);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(-1);
                            phrase = new Phrase("MOH TBVIVCF_LEPROSY, TUBERCULOSIS AND LUNG DISEASE CASE-FINDING SUMMARY", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(15);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            //  phrase = new Phrase("LEPROSY, TUBERCULOSIS AND LUNG DISEASE CASE-FINDING SUMMARY", pFontHeader22);
                            //  table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("County Name : " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("Health Facility Name: " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("Sub County Name : " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("Name DTLC: " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("YEAR : " + yearString.toUpperCase() + " Signature _______________________________", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase("ALL CASES REGISTERED DURING THE QUARTER", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Table 1", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(9);
                            phrase = new Phrase("Retreatment", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(11);
                            phrase = new Phrase("New", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Total TB cases", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Relapses PTB+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Relapses PTB-/EPTB", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Failures", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Return after default", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("PTB+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("PTB<15yrs smear not done", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("PTB>15yrs smear not done", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("PTB-<15 years", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("PTB->15 years", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("EPTB<15 years", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("EPTB>15 years", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("All registered TB cases", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested for HIV", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number HIV+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase("REPORT ON AGE/SEX DISTRIBUTION (NEW SMEAR POSITIVE CASES ONLY)", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Table 2", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(18);
                            phrase = new Phrase("Age group (in years)", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Total", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0-4", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("5-9", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("10-14", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("15-24", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("25-34", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("35-44", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("45-54", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("55-64", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("65+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Sex", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Total", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("New PTB+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested for HIV", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested HIV+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
//New smear Negative only
                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase("REPORT ON AGE/SEX DISTRIBUTION (NEW SMEAR NEGATIVE CASES ONLY)", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Table 3", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(18);
                            phrase = new Phrase("Age group (in years)", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Total", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0-4", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("5-9", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("10-14", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("15-24", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("25-34", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("35-44", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("45-54", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("55-64", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("65+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Sex", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Total", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("New PTB+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested for HIV", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested HIV+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

// New EXTRA PULMONARY CASES only
                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(22);
                            phrase = new Phrase("REPORT ON AGE/SEX DISTRIBUTION (NEW EXTRA PULMONARY CASES ONLY)", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Table 4", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(18);
                            phrase = new Phrase("Age group (in years)", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Total", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0-4", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("5-9", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("10-14", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("15-24", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("25-34", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("35-44", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("45-54", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("55-64", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("65+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Sex", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("M", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Total", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("New PTB+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested for HIV", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number tested HIV+", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader22);
                            table.addCell(phrase);



                            docPdf.add(table);

                        } catch (java.text.ParseException psExec) {

                            psExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);


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
