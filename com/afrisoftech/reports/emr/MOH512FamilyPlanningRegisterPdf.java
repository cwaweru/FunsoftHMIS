//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports.emr;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MOH512FamilyPlanningRegisterPdf implements java.lang.Runnable {

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

    public void MOH512FamilyPlanningRegisterPdf(java.sql.Connection connDb, java.util.Date startDate, java.util.Date lastDate) {

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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("MINISTRY OF HEALTH : " + compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        headerFoter.setRight(5);

                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("MOH - 512 : Family Planning Register : Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(36);

                        int headerwidths[] = {5, 25, 40, 15, 15, 15, 15, 15, 25, 15, 15, 15, 15, 15, 15, 15, 15, 15, 12, 15, 15, 15, 15, 15, 15, 20, 15, 15, 15, 15, 15, 25, 25, 25, 25, 45};

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
                            table.getDefaultCell().setColspan(36);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(50);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(-1);
                            phrase = new Phrase("MOH 512_Family Planning Register", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("FAMILY PLANNING REGISTER", pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("HEALTH FACILITY : " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase("#", pFontHeader11);

                            table.addCell(phrase);

                            phrase = new Phrase("Date", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Client Full Names", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Client No.", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Client Type (1 = New, 2 = Re-visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Age", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Sex", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Telephone Number", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Village/Estate/Landmark", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Contraceptive Pills - Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Contraceptive Pills - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Progestin only pills - Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Progestin only pills - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Injectables(Vials)- Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Injectables(Vials) - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Implants (Sets) - Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Implants (Sets) - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Emergency Contraceptive pills(Doses) - Given (Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Emergency Contraceptive pills(Doses) - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("IUCD (Sets) - Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("IUCD (Sets) - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Male Condoms - Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Male Condoms - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Female Condoms - Type of visit for method (1 = New, 2 = Re-Visit)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Female Condoms - Qty Dispensed", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Sterilization (Done on day of visit) Male(Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Sterilization (Done on day of visit) Female(Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Counselled for Natural FP (Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Counselling and Testing for HIV - Counselled (Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Counselling and Testing for HIV - Tested (Y/N)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Counselling and Testing for HIV - Status - (1 = Known Positive, 2 = Positive this visit, 3 = Negative, 4 = Unknown)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Cervical Cancer Screening - Method (1 = VIA, 2 = VILI, 3 = HPV VILI, 4 = HPV, 5 = Pap Smear)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Cervical Cancer Screening - Results (1 = Positive, 2 = Negative, 3 = Suspected)", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Referrals 1 = From other HF 2 = To other HF 3 = From CU 4 = to CU", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Remarks/Comments", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("B", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("C", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("E", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("F", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("G", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("H", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("I", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("J", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("K", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("L", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("M", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("N", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("O", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("P", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Q", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("R", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("S", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("T", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("U", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("V", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("W", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("X", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Y", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("Z", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AA", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AB", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AC", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AD", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AE", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AF", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AG", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AH", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("AI", pFontHeader11);
                            table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            psExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        try {

                            int noSeq = 0;

                            java.lang.Object[] listofAct = this.getListofStaffNos();

                            System.out.println("The array has been called...");

                            for (int i = 0; i < listofAct.length; i++) {

                                java.sql.PreparedStatement stw = connectDB.prepareStatement("SELECT DISTINCT "
                                        + " date_part('day', service_date) ||'-'||date_part('month', service_date) ||'-'||date_part('year', service_date), full_name, patient_no, revisit, age::int, initcap(gender),"
                                        + " telephone_no, village, sterilisation_male, sterilisation_female, natural_fp, "
                                        + " immune_counselling, immune_tested, immune_result, cervical_cancer_screening, "
                                        + " cervical_cancer_screening_result, referral_in||' '||referral_out as referrals, remarks  FROM rh.fp_services_register WHERE "
                                        + " service_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' "
                                        + " AND patient_no = ?");

                                stw.setObject(1, listofAct[i]);

                                java.sql.ResultSet rsetw = stw.executeQuery();

                                while (rsetw.next()) {

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    // Index
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);
                                    // Lab procedure date
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(1), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient Number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(2), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Lab Number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(3), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(4), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Age
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(5), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Gender
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(6), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Village
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(7), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Village
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(8), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Telephone number
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "CP", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "CP", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "PP", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "PP", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "IV", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "IV", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "IS", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "IS", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getDosageGiven(connectDB, "EC", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "EC", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "IUD", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "IUD", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "MC", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "MC", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getIndicatorStatus(connectDB, "FC", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(com.afrisoftech.lib.FPServiceIndicators.getQuantityDispensed(connectDB, "FC", listofAct[i].toString(), beginDate, endDate), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Patient names
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(9), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Age
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(10), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Gender
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(11), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Village
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(12), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Telephone number
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(13), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Clinical diagnosis
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(14), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Type of specimen
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(15), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    // Condition of specimen
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(16), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample taken
                                    phrase = new Phrase("", pFontHeader1);
                                    table.addCell(phrase);
                                    //Investigation required
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(17), ""), pFontHeader1);
                                    table.addCell(phrase);
                                    //Date sample received
                                    phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(rsetw.getString(18), ""), pFontHeader1);
                                    table.addCell(phrase);

                                }

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

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            java.sql.PreparedStatement stmt1 = connectDB.prepareStatement("SELECT DISTINCT patient_no, full_name FROM rh.fp_services_register where service_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ORDER BY full_name ASC");

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
