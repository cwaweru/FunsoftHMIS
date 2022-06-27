//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
/**
 * @author Charles Waweru <cwaweru@systempartners.biz>
 *
 */
package com.afrisoftech.reports.emr;

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
    com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeaderxx = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
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

    public void addWhiteSpace(com.lowagie.text.pdf.PdfPTable table, Phrase phrase,int cols) {
        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
        table.getDefaultCell().setColspan(cols);
        phrase = new Phrase(" ", pFontHeaderx);
        table.addCell(phrase);
        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
    }
    
    public void addLightGrayBackgroundCell(com.lowagie.text.pdf.PdfPTable table, Phrase phrase,int cols){
        table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
        table.getDefaultCell().setColspan(cols);
        phrase = new Phrase(" ", pFontHeader1);
        table.addCell(phrase);
        table.getDefaultCell().setBackgroundColor(Color.WHITE);
    }
    
    public void addTableCell(com.lowagie.text.pdf.PdfPTable table, Phrase phrase,int cols, String text, com.lowagie.text.Font font, int align){
        table.getDefaultCell().setHorizontalAlignment(align);
        table.getDefaultCell().setColspan(cols);
        phrase = new Phrase(text, font);
        table.addCell(phrase);
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

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(22);

                            int headerwidths[] = {12, 6, 6, 6, 2, 12, 6, 6, 6, 2, 10, 6, 6, 6, 2, 10, 4, 4, 2, 10, 5, 5};

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
                                phrase = new Phrase("MOH 706_Laboratory Report", pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("LABORATORY SUMMARY REPORT", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("HEALTH FACILITY : " + compName, pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("County", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("Sub-County : " + compName, pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                java.sql.PreparedStatement pstmt = connectDB.prepareStatement(District);
                                
                                //Space Section
                                addWhiteSpace(table, phrase,22);

                                //Urine Section
                                addTableCell(table, phrase,4, "1. URINE ANALYSIS", pFontHeader, PdfCell.ALIGN_CENTER);
      
                                //Space Section
                                addWhiteSpace(table, phrase,1);

                                //Parasit Section
                                addTableCell(table, phrase,4, "3. PARASITOLOGY", pFontHeader, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);

                                //Bact Section
                                addTableCell(table, phrase,4, "5. BACTERIOLOGY", pFontHeader, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo Section
                                addTableCell(table, phrase,7, "6. HISTOLOGY AND CYTOLOGY", pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);

                                //Parasit Section
                                addTableCell(table, phrase, 1, "Malaria Test", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);

                                //Bact Section
                                addTableCell(table, phrase, 1, "Bacteriological Sample", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Total Cultures", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "No. Culture Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);

                                //Histology Section
                                addTableCell(table, phrase, 3, "Smears", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Malignant", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.1 Urine Chemistry", pFontHeader, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getTestCount(connectDB, "Urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.1 Malaria BS(< 5 yrs)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExtByAge(connectDB, "malaria", "mps", beginDate, endDate,0,5)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExtByAge(connectDB, "malaria", "mps", beginDate, endDate,0,5)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.1. Urine", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "Urine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "Urine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "Urine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.1 PAP Smears", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pap smear", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "pap smear", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.2 Glucose", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "glucose", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);

                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.2 Malaria BS(5 yrs & above)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExtByAge(connectDB, "malaria", "mps", beginDate, endDate, 5, 999)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExtByAge(connectDB, "malaria", "mps", beginDate, endDate, 5, 999)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1); 
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.2. Pus Swabs", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "pus swabs", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "pus swabs", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "pus swabs", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.2 Touch Preparations", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Touch prep", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Touch prep", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.3 Ketones", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "ketone", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);

                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.3 Malaria Rapid Diag Test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "malaria r", "malaria r", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "malaria r", "malaria r", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1); 
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.3. High Vaginal Swabs", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "high vaginal swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "high vaginal swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "high vaginal swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.3 Tissue Impressions", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Tisuue imp", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Tisuue imp", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.4 Proteins", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "protein", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "Stool Examination", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getTestCount(connectDB, "stool", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1); 
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.4. Throat Swabs", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "throat swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "throat swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "throat swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "Fine Needle Aspirates", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Malignant", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.5 Urine Microscopy", pFontHeader, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getTestCount(connectDB, "Urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.4 Taenia spp.", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "taenia", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.5. Rectal Swabs", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "rectal swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "rectal swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "rectal swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.4 Thyroid", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "thyroid", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "thyroid", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.6 Pus cells(>5/hpf)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositivePus(connectDB, "pus", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.5 Hymenolepis nana", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hymenolepis", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.6. Blood", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "blood swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "blood swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "blood swab", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.5 Lymph nodes", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "lymph", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "lymph", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.7 S haematobium", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "haematobium", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.6 Hookworm", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hookworm", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.7. Water", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "water", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "water", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "water", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.6 Liver", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "liver", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "liver", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.8 T Vaginalis", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "vaginalis", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.7 Roundworms", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "roundworm", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.8 food", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "food", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "food", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "food", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.7 Breast", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "breast", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "breast", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.9 Yeast Cells", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "yeast", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.8 S mansoni", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "mansoni", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 1, "5.9 Urethral Swab", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "urethral", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "urethral", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "urethral", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.8 Soft Tissue Masses", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tissue", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "tissue", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase, 1, "1.10 Bacteria", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "bacteria", "urin", beginDate, endDate)), pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.9 Trichuris trichura", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "trichur", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "Bacterial enteric pathogens", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "Fluid Cytology", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Malignant", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 5);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "3.10 Amoeba", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "amoeba", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.10 Stool Cultures", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "stool%cul", "stool%cul", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "stool%cul", "stool%cul", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.9 Ascitic fluid", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Ascitic", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Ascitic", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Urine Section
                                addTableCell(table, phrase,4, "2. BLOOD CHEMISTRY", pFontHeader, PdfCell.ALIGN_CENTER);
      
                                //Space Section
                                addWhiteSpace(table, phrase,6);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "Stool Isolates", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.10 CFS", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "CFS", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "CFS", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "Blood Sugar Test", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Low", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "High", pFontHeaderx, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase,4, "4. HAEMATOLOGY", pFontHeader, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.11 Salmonella Typhi", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "typhi", "typhi", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.11 Pleural Fluid", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pleural", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "pleural", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.1 Blood Sugar", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "rbs--blood sugar", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "rbs--blood sugar", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "rbs--blood sugar", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "Haematology tests", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "HB <5 g/dl", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "HB between 5 & 10 g/dl", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.12 Shigella - dysenteriae type 1", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "Shigella", "dysentry", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.12 Urine", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "urine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "urine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.2 OGTT", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "OGTT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "OGTT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "OGTT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "4.1 Full Blood count", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "full haem", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "hgb", "", 5, beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountOutsideRange(connectDB, "hgb", 5, 10, beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.13 E.coli O 157:H7", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "coli", "coli", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "Tissue Histology", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Malignant", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.3 Renal Function ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "renal profile", "renal function", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Parasit Section
                                addTableCell(table, phrase, 1, "4.2 HB estimation test(other techniques)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "hgb", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "hgb", "", 5, beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountOutsideRange(connectDB, "hgb", 5, 10, beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 2, "5.14 V cholerae O1", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "cholera", "cholera", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.13 Cervix", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "cervix", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "cervix", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.4 Creatinine", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "creatinine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "creatinine", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 2, "", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Number <500", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 2, "5.15 V cholerae O139", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositiveExt(connectDB, "cholera", "cholera", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.14 Prostate", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "prostate", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "prostate", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.5 Sodium", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "sodium", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "sodium", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 2, "4.3 CD4 count", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2,  Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPercentBelow(connectDB, "cd4", "", 500, beginDate, endDate)) , pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 4, "Bacterial meningitis", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "6.15 Breast Tissue", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Breast Tissue", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Breast Tissue", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.5 Potassium", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "potassium", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "potassium", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 4, "", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 1, "Bacterial meningitis", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Number contaminated", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 3, "6.16 Ovary", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "ovary", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "ovary", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.6 Chloride", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "chloride", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "chloride", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "Other Haemalogogy tests", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 1, "5.16 CSF", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCount(connectDB, "csf", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "csf", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getSpecimenCultureCount(connectDB, "csf", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 3, "6.17 Uterus", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Uterus", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Uterus", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.3 Liver Function Test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "liver function", "lfts", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "4.4 Sickling test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "sickling", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "sickling", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "Bacterial meningitis serotypes", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                addTableCell(table, phrase, 3, "6.18 Skin", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Skin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Skin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                
                                 //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.9 Direct Bilirubin", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Direct Bilirubin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Direct Bilirubin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "4.5 Peripheral blood films", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "blood film", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.17 Neissseria meningitidis A", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB,  "Neissseria meningitidis A", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo section
                                addTableCell(table, phrase, 3, "6.19 Head and neck", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Head and neck", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Head and neck", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                
                                 //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.10 Total Bilirubin", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Total Bilirubin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Total Bilirubin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "4.6 BMA", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "bma", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.18 Neissseria meningitidis B", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB,  "Neissseria meningitidis B", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo section
                                addTableCell(table, phrase, 3, "6.20 Dental", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "dental", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "dentals", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.11 ASAT (SGOT)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "SGOT--AST/GOT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "SGOT--AST/GOT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "4.7 coagulation Profile", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "coagulation P", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.19 Neissseria meningitidis C", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB,  "Neissseria meningitidis C", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo section
                                addTableCell(table, phrase, 3, "6.21 GIT", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "git", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "git", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.12 ALAT (SGPT)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "SGPT--ALAT-GPT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "SGPT--ALAT/GPT", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "4.8 Reticulocyte count", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Reticulocyte P", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.20 Neissseria meningitidis W135", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Neissseria meningitidis W135", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo section
                                addTableCell(table, phrase, 3, "6.22 Lymph nodes Tissue", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Lymph nodes Tissue", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Lymph nodes Tissue", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.13 Serum Protein", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Serum Protein--Total Protein", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "serum Protein--Total Protein", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 2, "High", pFontHeaderx, PdfCell.ALIGN_LEFT);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.21 Neissseria meningitidis X", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Neissseria meningitidis X", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histology Section
                                addTableCell(table, phrase, 3, "Bone Marrow Studies ", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "Malignant", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.14 Albumin", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Albumin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "albumin", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 1, "4.9 Erythrocyte Sedimentation rate", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "ESR", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "esr", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.22 Neissseria meningitidis Y", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Neissseria meningitidis Y", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo section
                                addTableCell(table, phrase, 3, "6.23 Bone Marrow Aspirate", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Bone Marrow Aspirate", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Bone Marrow Aspirate", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.15 Alkaline Phosphate", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Alkaline p", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Alkaline p", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 4, " ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.23 N meningitidis (Intermediate)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Neissseria meningitidis Y", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Histo section
                                addTableCell(table, phrase, 3, "6.24 Trephine Biopsy", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Trephine Biopsy", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getCancerNonInfectiveMalignantCount(connectDB, "Trephine Biopsy", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.16 Lipid Profile", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "lipid profile", "lipid profile", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addLightGrayBackgroundCell(table, phrase, 2);
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 2, "Blood grouping", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, "Number", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.24 Streptococcus pneumoniae", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Streptococcus pneumon", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 8);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.17 Total Cholestorol", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Total Cholestor", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Total Cholestor", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 2, "4.10 Total blood group tests", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "blood group", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.25 Haemophilus influenzae (type b)", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Haemophilus influenzae", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 5);
                                
                                //Refferal Section
                                addTableCell(table, phrase,3, "8. SPECIMEN REFERRAL TO HIGHER LEVELS", pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.18 Triglycerides", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Triglyceride", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Triglyceride", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 2, "4.11 Blood units grouped", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "blood%grouped", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.26 Cryptococcal Menengitis", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Cryptococcal Menengitis", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serelogy Section
                                addTableCell(table, phrase,3, "7. SERELOGY", pFontHeader, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "Specimen Referral", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "No. of Specimen", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Results Received", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.19 LDL", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addLightGrayBackgroundCell(table, phrase, 1);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "ldl", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "ldl", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "Blood Safety", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Number", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 4, "Bacterial pathogens from other types of specimen", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                               //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "Serological test", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "No. Positiv", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.1 CD4 ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "CD4", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "CD4", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "Hormonal test", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Low", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "High", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.12 Blood units received from blood transfusion centers", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.27 anthracis ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "anthracis", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.1 VDRL", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "vdrl", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "vdrl", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.2 Viral Load ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Viral Load", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Viral Load", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.20 T3", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "T3", "T3", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "T3", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "T3", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.13 Blood units collected at facility", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.28 Y. Pestis ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 2, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pestis", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.2 TPHA", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tpha", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tpha", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.3 EID ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "EID", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "EID", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.21 T4", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "T4", "T4", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "T4", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "T4", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.14 Blood units transfused", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 4,  "  ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                
                                 //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.3 ASOT", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "asot", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "asot", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.4 Discondant /discripant ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Discondant", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Discondant", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.22 TSH", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "TSH", "TSH", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "TSH", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "TSH", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.15 Transfusion reactions reported and investigated", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 4,  "  ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                
                                 //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.4 HIV", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "HIV", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hiv", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.5 TB Culture", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "tb", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "tb", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.23 PSA", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "PSA", "PSA", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "PSA", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "PSA", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.16 Blood grouping and cross matched", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "SPUTUM", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.5 Brucella", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "brucella", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "brucella", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.6 Virological ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Virological", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Virological", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "Tumor Markers", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Low", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "High", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.17 Blood units discarded", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.29 Total TB Smears ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tb smears", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tb smears", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.6 Rheumatoid factor", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Rheumatoid factor", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Rheumatoid factor", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.7 Clinical Chemistry ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Clinical Chemistry", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Clinical Chemistry", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.24 CEA", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "CEA", "CEA", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "CEA", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "CEA", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "Blood Screening at facility", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Number Positive", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.30 TB new suspects ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tb new suspect", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tb new suspect", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.7 Helicobacter pylori", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "pylori", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "pylori", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.8 Histoloy/cytology  ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Histoloy%cytology", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Histoloy%cytology", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.25 C15-3", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "C15-3", "C15-3", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "C15-3", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "C15-3", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.18 HIV", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "hiv", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.31 TB Follow up ", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "tb follow up", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "tb follow up", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.8 Hepatitis A Test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Hepatitis A", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Hepatitis A", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.9 Haematological", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "Haematolog", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "Haematolog", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "CSF Chemistry", pFontHeaderx, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, "Total Exam", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Low", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "High", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.19 Hepatitis B", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Hepatitis B", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.32 Rifampicin Resistant TB", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Rifampicin Resistant TB", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Rifampicin Resistant TB", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.9 Hepatitis B Test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Hepatitis b", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Hepatitis b", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.10 Parasitological", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "parasit", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "parasit", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.26 Proteins", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "Proteins", "Proteins", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Proteins", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Proteins", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.20 Hepatitis C", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Hepatitis C", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Bact Section
                                addTableCell(table, phrase, 2, "5.33 MDR TB", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "MDR TB", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "MDR TB", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

//                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.10 Hepatitis C Test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "Hepatitis c", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Hepatitis c", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Refferal Section
                                addTableCell(table, phrase, 1, "8.11 Blood Samples for transfusion screening", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralCount(connectDB, "transfusion screening", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getReferralResultsCount(connectDB, "transfusion screening", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                //Blood Chem Section
                                addTableCell(table, phrase, 1, "2.27 Glucose", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCountExt(connectDB, "Glucose", "Glucose", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountLow(connectDB, "Glucose", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountHigh(connectDB, "Glucose", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 1);
                                
                                //Haematology Section
                                addTableCell(table, phrase, 3, "4.21 Syphyllis", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "Syphyllis", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 6);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.11 HCG", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "HCG", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "HCG", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 4);
                                
                                //Space Section
                                addWhiteSpace(table, phrase, 15);
                                
                                //Serology Section
                                addTableCell(table, phrase, 1, "7.12 CRAIG Test", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterCount(connectDB, "CRAIG Test", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, Integer.toString(com.afrisoftech.lib.LabReportCounts.getParameterResultCountPositive(connectDB, "CRAIG Test", beginDate, endDate)), pFontHeader1, PdfCell.ALIGN_CENTER);

                                //Space Section
                                addWhiteSpace(table, phrase, 4);
                                
                                //---------------------------------------------------------------------
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(44);
                                phrase = new Phrase(" ", pFontHeaderx);
                                table.addCell(phrase);
                                table.addCell(phrase);
                                table.addCell(phrase);
                                
                                
                                //---------------------------------
                                
                                //Drug Sensitivity Pattern
                                addTableCell(table, phrase, 22, "Drug Susceptibility Testing", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                addTableCell(table, phrase, 2, "Drug Sensitivity Pattern", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "a. Ampicillin", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "b. Chloramphenicol", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "c. Ceftriaxone", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "d. Penicillin", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "e. Oxacillin", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "f. Ciprofloxacin", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "g. Nalidixic acid", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "h. Trimethoprim sulphamethoxazol", pFontHeaderxx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "i. Tetracycline", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 2, "j. Augmentin", pFontHeaderx, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensit", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resist", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensit", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Sensitive", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, "Resistant", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.1 Haemophilus influanzae", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.2 Neisseria meningitidis", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.3 Streptococcus pnuemoniae", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.4 Salmonella serotype Typhi", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.5 Shigella", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.6 Vibrio Cholerae", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.7 B. anthracis", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                addTableCell(table, phrase, 2, "9.8 Y. Pestis", pFontHeader1, PdfCell.ALIGN_LEFT);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                addTableCell(table, phrase, 1, " ", pFontHeader1, PdfCell.ALIGN_CENTER);
                                
                                
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(44);
                                phrase = new Phrase(" ", pFontHeaderx);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(44);
                                phrase = new Phrase(" ", pFontHeaderx);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Report Compiled By : ______________________________ ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("Designation : ___________________________", pFontHeader);
                                table.addCell(phrase);

                                
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Date : _______________________", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Signature : _______________________", pFontHeader);
                                table.addCell(phrase);

                                

                                table.getDefaultCell().setColspan(22);
                                phrase = new Phrase(" ", pFontHeader);
                                table.addCell(phrase);
                                table.addCell(phrase);
                                table.addCell(phrase);
                                table.addCell(phrase);


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(22);
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
