//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

//import static com.afrisoftech.reports.PatientCardPdf.connectDB;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
//
//import //java.awt.Desktop;

public class EyeDiagnosisSummaryPdf implements java.lang.Runnable {

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
    java.lang.Thread threadSample;
    com.itextpdf.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.itextpdf.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.itextpdf.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.itextpdf.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void EyeDiagnosisSummaryPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        //  Categ = categ;
        //  ReportType = repotype;
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

        //  try {
        java.io.File tempFile = null;
        try {
            tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");
        } catch (IOException ex) {
                        ex.printStackTrace();             //ex.printStackTrace();
        }

        tempFile.deleteOnExit();

        java.lang.Runtime rt = java.lang.Runtime.getRuntime();

        java.lang.String debitTotal = null;

        java.lang.String creditTotal = null;

        com.itextpdf.text.Document docPdf = new com.itextpdf.text.Document(PageSize.A4);

        try {

            com.itextpdf.text.Phrase headerFoter = null;
            com.itextpdf.text.pdf.PdfWriter pdfWriter = null;
            try {
                pdfWriter = com.itextpdf.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
            } catch (DocumentException ex) {
                            ex.printStackTrace();             //ex.printStackTrace();
            }

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
                headerFoter = new com.itextpdf.text.Phrase("MINISTRY OF HEALTH. DIVISION OF OPHTHALMIC SERVICES".toUpperCase());// FontFactorygetFont(com.itextpdf.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                com.afrisoftech.lib.HeaderFooterHelper.header = headerFoter;

            } catch (java.sql.SQLException SqlExec) {
                SqlExec.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

            }

            com.itextpdf.text.Phrase footer = new com.itextpdf.text.Phrase(String.format("Page %d of", pdfWriter.getCurrentPageNumber()));//"Page: ");//getFont(com.itextpdf.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

            com.afrisoftech.lib.HeaderFooterHelper.header = headerFoter;

            com.afrisoftech.lib.HeaderFooterHelper.footer = footer;
            //
            pdfWriter.setPageEvent(new com.afrisoftech.lib.HeaderFooterHelper());

            docPdf.open();

            try {
                com.itextpdf.text.pdf.PdfPTable headertable = new com.itextpdf.text.pdf.PdfPTable(6);

                int headerwidthstable[] = {15, 7, 25, 7, 8, 18};

                headertable.setWidths(headerwidthstable);

                headertable.setWidthPercentage((100));

                // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                headertable.getDefaultCell().setColspan(6);

                Phrase headerphrase = new Phrase();

                headertable.getDefaultCell().setBorder(Rectangle.BOX);
                headertable.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                headertable.getDefaultCell().setColspan(2);
                headertable.getDefaultCell().setFixedHeight(70);
                headertable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                headertable.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                java.sql.Statement st4 = connectDB.createStatement();
                java.sql.Statement st3 = connectDB.createStatement();
                java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");

                while (rset3.next()) {
                    headertable.getDefaultCell().setColspan(4);

                    headertable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    headertable.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                    headertable.getDefaultCell().setBorder(Rectangle.BOX);
                    headerphrase = new Phrase("\n\n" + rset3.getObject(1).toString(), pFontHeader);
                    headertable.addCell(headerphrase);
                }
                docPdf.add(headertable);
            } catch (Exception sy) {
                sy.printStackTrace();
            }
            try {

                com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(20);

                int headerwidths[] = {10, 50, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

                table.setWidths(headerwidths);

                table.setWidthPercentage((100));

                Phrase phrase = new Phrase("", pFontHeader);

                try {
                    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                    java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                    java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                    java.lang.String monthString = dateFormatter.getDateString();

                    com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                    java.lang.String yearString = dateFormatters.getDateString();

                    table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
//                    table.getDefaultCell().setColspan(20);
//                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//
//                    phrase = new Phrase("MINISTRY OF HEALTH. DIVISION OF OPHTHALMIC SERVICES", pFontHeader2);
//
//                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(7);
                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    phrase = new Phrase("FACILITY : " + compName, pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(8);
                    phrase = new Phrase("Date From : " + java.text.SimpleDateFormat.getDateInstance().format(beginDate) + " To : " + java.text.SimpleDateFormat.getDateInstance().format(endDate1), pFontHeader);
                    table.addCell(phrase);

//                    table.getDefaultCell().setColspan(6);
//
//                    phrase = new Phrase(" To:  : " + java.text.SimpleDateFormat.getDateInstance().format(endDate1), pFontHeader1);
//                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(3);
                    phrase = new Phrase("STATIC  ", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("Mobile: ", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

                    table.getDefaultCell().setColspan(2);

                    table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

                    phrase = new Phrase("New Patients : [" + com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalEyeClinicPatientsNew(connectDB, beginDate, endDate, "Clinic") + "]", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(9);
                    phrase = new Phrase("Revisits : [" + com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalEyeClinicPatientsRevisit(connectDB, beginDate, endDate, "Clinic") + "]", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(9);
                    phrase = new Phrase("Total Patients seen : [" + com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalEyeClinicPatients(connectDB, beginDate, endDate, "Clinic") + "]", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("Rk", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.RIGHT);
                    table.getDefaultCell().setBorderColorBottom(BaseColor.WHITE);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("Visual Acuity (better eye) - ALL)", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setBorderColorBottom(BaseColor.BLACK);
                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("Children", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase("Normal", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase("Low Vision", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(6);
                    phrase = new Phrase("Blind", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("Total", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setBorderColorTop(BaseColor.WHITE);
                    table.getDefaultCell().setBorder(PdfPCell.BOTTOM | PdfPCell.LEFT | PdfPCell.RIGHT);
                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("Patients", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
                    table.getDefaultCell().setBorderColorTop(BaseColor.BLACK);
                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase("6/6 - 6/18", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase(">6/18 - 3/60", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(6);
                    phrase = new Phrase("<3/60 - NPL", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("Age in years", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("< 5 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" 5 - 16 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" Over 16 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" 5 - 16 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" Over 16 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" < 5 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" 5 - 16 yrs", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" Over 16 yrs", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" ", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(" ", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("Gender/Sex", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("CLINICAL DIAGNOSIS", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.getDefaultCell().setColspan(18);
                    phrase = new Phrase(" ", pFontHeader1);
                    table.addCell(phrase);

                    int rank = 1;

                    Object listOfConditionsClinical[] = getListofClinicalEyeConditionsNos();

                    Object listOfConditionsSurgery[] = getListofSurgeryEyeConditionsNos();

                    for (int i = 0; i < listOfConditionsClinical.length; i++) {
                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(i + 1), pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(listOfConditionsClinical[i].toString(), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofChildrenWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, "M", "Clinic", "Normal")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofChildrenWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, "F", "Clinic", "Normal")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 6 / 18, 6 / 6, "M", 5, 16, "Clinic", "Normal")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 6 / 18, 6 / 6, "F", 5, 16, "Clinic", "Normal")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientOver16WithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 6 / 18, 6 / 6, "M", "Clinic", "Normal")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientOver16WithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 6 / 18, 6 / 6, "F", "Clinic", "Normal")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 3 / 60, 6 / 18, "M", 5, 16, "Clinic", "Low Vision")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 3 / 60, 6 / 18, "F", 5, 16, "Clinic", "Low Vision")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientOver16WithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 3 / 60, 6 / 18, "M", "Clinic", "Low Vision")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientOver16WithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 3 / 60, 6 / 18, "F", "Clinic", "Low Vision")), pFontHeader1);
                        table.addCell(phrase);
                        //// No of Blind patients 

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofChildrenWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, "M", "Clinic", "Blind")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofChildrenWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, "F", "Clinic", "Blind")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 1 / 60, 3 / 60, "M", 5, 16, "Clinic", "Blind")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 1 / 60, 3 / 60, "F", 5, 16, "Clinic", "Blind")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientOver16WithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 1 / 60, 3 / 60, "M", "Clinic", "Blind")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientOver16WithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, 1 / 60, 3 / 60, "F", "Clinic", "Blind")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, "M", "Clinic")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientWithCondition(connectDB, listOfConditionsClinical[i].toString(), beginDate, endDate, "F", "Clinic")), pFontHeader1);
                        table.addCell(phrase);
                    }

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("TOTAL DIAGNOSIS", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofChildrenWithVisualAcuity(connectDB, beginDate, endDate, "M", "Clinic")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofChildrenWithVisualAcuity(connectDB, beginDate, endDate, "F", "Clinic")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientsWithVisualAcuity(connectDB, beginDate, endDate, 6 / 18, 6 / 6, "M", 5, 16, "Clinic", "Normal")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientsWithVisualAcuity(connectDB, beginDate, endDate, 6 / 18, 6 / 6, "F", 5, 16, "Clinic", "Normal")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientOver16WithVisualAcuity(connectDB, beginDate, endDate, 6 / 18, 6 / 6, "M", "Clinic", "Normal")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientOver16WithVisualAcuity(connectDB, beginDate, endDate, 6 / 18, 6 / 6, "F", "Clinic", "Normal")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientsWithVisualAcuity(connectDB, beginDate, endDate, 3 / 60, 6 / 18, "M", 5, 16, "Clinic", "Low Vision")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientsWithVisualAcuity(connectDB, beginDate, endDate, 3 / 60, 6 / 18, "F", 5, 16, "Clinic", "Low Vision")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientOver16WithVisualAcuity(connectDB, beginDate, endDate, 3 / 60, 6 / 18, "M", "Clinic", "Low Vision")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientOver16WithVisualAcuity(connectDB, beginDate, endDate, 3 / 60, 6 / 18, "F", "Clinic", "Low Vision")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalChildrenWithVisualAcuity(connectDB, beginDate, endDate, "M", "Clinic", "Blind")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalChildrenWithVisualAcuity(connectDB, beginDate, endDate, "F", "Clinic", "Blind")), pFontHeader);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientsWithVisualAcuity(connectDB, beginDate, endDate, 0 / 60, 3 / 60, "M", 5, 16, "Clinic", "Blind")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getNoofPatientsWithVisualAcuity(connectDB, beginDate, endDate, 0 / 60, 3 / 60, "F", 5, 16, "Clinic", "Blind")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientOver16WithVisualAcuity(connectDB, beginDate, endDate, 0 / 60, 3 / 60, "M", "Clinic", "Blind")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientOver16WithVisualAcuity(connectDB, beginDate, endDate, 0 / 60, 3 / 60, "F", "Clinic", "Blind")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientsPerGender(connectDB, beginDate, endDate, "M", "Clinic")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientsPerGender(connectDB, beginDate, endDate, "F", "Clinic")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(20);
                    phrase = new Phrase(" ", pFontHeader);
                    table.addCell(phrase);

                    //Reporting Surgeries
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(11);
                    phrase = new Phrase("SURGERIES", pFontHeader);
                    table.addCell(phrase);

                    //  table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("< 5 yrs", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("5 - 16 yrs", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase(" Over 16 yrs", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(2);
                    phrase = new Phrase("TOTAL", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(" ", pFontHeader1);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(11);
                    phrase = new Phrase(" ", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("M", pFontHeader1);
                    table.addCell(phrase);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("F", pFontHeader1);
                    table.addCell(phrase);

                    for (int j = 0; j < listOfConditionsSurgery.length; j++) {

                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(j + 1), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(11);
                        phrase = new Phrase(listOfConditionsSurgery[j].toString(), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getnoofSurgeryPatientsUnder5(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "M", "Surgery")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getnoofSurgeryPatientsUnder5(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "F", "Surgery")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getnoofSurgeryPatientsAgesCohorts(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "M", "Surgery", 5, 16)), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getnoofSurgeryPatientsAgesCohorts(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "F", "Surgery", 5, 16)), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getnoofSurgeryPatients16AndAbove(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "M", "Surgery")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getnoofSurgeryPatients16AndAbove(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "F", "Surgery")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientWithCondition(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "M", "Surgery")), pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientWithCondition(connectDB, listOfConditionsSurgery[j].toString(), beginDate, endDate, "F", "Surgery")), pFontHeader1);
                        table.addCell(phrase);

                    }

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(11);
                    phrase = new Phrase("TOTAL SURGERIES", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalSurgeryPatientsUnder5(connectDB, beginDate, endDate, "M", "Surgery")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalSurgeryPatientsUnder5(connectDB, beginDate, endDate, "F", "Surgery")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalSurgeryPatientsAgesCohorts(connectDB, beginDate, endDate, "M", "Surgery", 5, 16)), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalSurgeryPatientsAgesCohorts(connectDB, beginDate, endDate, "F", "Surgery", 5, 16)), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalSurgeryPatients16AndAbove(connectDB, beginDate, endDate, "M", "Surgery")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalSurgeryPatients16AndAbove(connectDB, beginDate, endDate, "F", "Surgery")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientsPerGender(connectDB, beginDate, endDate, "M", "Surgery")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase(String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalPatientsPerGender(connectDB, beginDate, endDate, "F", "Surgery")), pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    table.getDefaultCell().setColspan(1);
                    phrase = new Phrase("", pFontHeader);
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(11);
                    phrase = new Phrase("Eye Ward capacity : [" + String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalEyeWardCapacity(connectDB)) + "]", pFontHeader
                    );
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase("Admissions : [" + String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalEyeWardsAdmissions(connectDB, beginDate, endDate)) + "]", pFontHeader
                    );
                    table.addCell(phrase);

                    table.getDefaultCell().setColspan(4);
                    phrase = new Phrase("Discharges : [" + String.valueOf(com.afrisoftech.lib.EyeUnitReportingIndicators.getTotalEyeWardsDischarges(connectDB, beginDate, endDate)) + "]", pFontHeader
                    );
                    table.addCell(phrase);

                    docPdf.add(table);
                } catch (java.text.ParseException psExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                }

            } catch (com.itextpdf.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getListofClinicalEyeConditionsNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(condition_description), condition_rank FROM public.eye_clinic_conditions WHERE condtion_type ilike 'Clinic' ORDER BY condition_rank");

            while (rSet1.next()) {

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

    public java.lang.Object[] getListofSurgeryEyeConditionsNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(condition_description), condition_rank FROM public.eye_clinic_conditions WHERE condtion_type ilike 'Surgery' ORDER BY condition_rank");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

            while (rSet1.next()) {

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
