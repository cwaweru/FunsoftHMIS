//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class UniversalHealthSalesReportPdf implements java.lang.Runnable {

    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    double TdailySales = 0.00;
    double Tmot = 0.00;
    double Tpha = 0.00;
    double Tlab = 0.00;
    double Temr = 0.00;
    double Top = 0.00;
    double Txray = 0.00;
    double Tmopc = 0.00;
    double Tipcash = 0.00;
    double Tden = 0.00;
    double Tphy = 0.00;
    double Tocc = 0.00;
    double Ttheat = 0.00;
    double Tmatt = 0.00;
    double Tnut = 0.00;
    double Tpsy = 0.00;
    double Tref = 0.00;
    double Tsales = 0.00;

    double Tother = 0.00;
    double Tcash = 0.00;
    double Tnhif = 0.00;
    double Tdebtors = 0.00;
    double Treceived = 0.00;
    double Unhif = 0.00;
    double Udebtors = 0.00;
    double Uwaivers = 0.00;
    double Uexemp = 0.00;
    double Uabs = 0.00;
    double Tfacility = 0.00;
    double Tpmo = 0.00;
    double Tbanked = 0.00;
    double Tamt = 0.00;
    double Gwaivers = 0.00;
    // double Ttheat = 0.00;
    String ks;
    int over = 0;
    int name = 0;
    int numberSeq = 0;
    int iterations = 0;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String Debtor = null;
    java.util.Date endDate = null;
    java.util.Date beginDate = null;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    com.afrisoftech.timeseries.DailyAgeing dailySeries = null;
    java.util.Date ageingDates[][] = null;
    double osBalance = 0.00;
    double current = 0.00;
    double over30 = 0.00;
    double over60 = 0.00;
    double over90 = 0.00;
    double turnOver = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String schemeName = null;

    public void UniversalHealthSalesReportPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, String scheme) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        // Debtor = combox;
        schemeName = scheme;
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        iterations = endDate.getDate() - beginDate.getDate();
        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date" + endDate);
        // periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(1, endate);
        dailySeries = new com.afrisoftech.timeseries.DailyAgeing(iterations + 1, endate);

        // long enDate = java.util.Date.parse(endDate);
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new TransactionsListPdf().TransactionsListPdf();
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

        java.lang.Object[][] rangeDates = ageingSeries.getAgeingDateSeries();

        java.lang.Object[][] dailyDates = dailySeries.getAgeingDateSeries();

        // ageingDates = ageingSeries.getAgeingDateSeries();
        double columnTotals[] = new double[rangeDates.length];

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        int interval = 0;

        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            //   com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            java.util.Calendar calendar = java.util.Calendar.getInstance();

            long dateNow = calendar.getTimeInMillis();

            java.sql.Date datenowSql = new java.sql.Date(dateNow);

            System.out.println(datenowSql.toString());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(19);
                    String compName = null;
                    String date = null;
                    String District = null;
                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                    table.getDefaultCell().setColspan(6);

                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                    table.getDefaultCell().setFixedHeight(50);
                    table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                    table.getDefaultCell().setFixedHeight(-1);
                    Phrase phrase = new Phrase("");
                    try {
                        java.sql.Statement stheader = connectDB.createStatement();
                        java.sql.ResultSet rsetHeader = stheader.executeQuery("SELECT header,footer FROM ac_receipt_header");
                        while (rsetHeader.next()) {
                            table.getDefaultCell().setColspan(13);

                            phrase = new Phrase(rsetHeader.getObject(1).toString(), pFontHeader2);
                            table.addCell(phrase);

                    //        footers = rset3.getObject(2).toString();

                        }
                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st4x = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        float mints = 0;
                        java.sql.ResultSet rset4x = st4x.executeQuery("SELECT EXTRACT(MINUTE FROM(current_time::time - report_time::time)) FROM moh_reporting");

                        while (rset4x.next()) {
                            mints = rset4x.getFloat(1);
                        }
                        if (mints > 7 || mints <= 0) {

                            java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("DELETE FROM moh_reporting");
                            pstmt31.executeUpdate();

                            java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO moh_reporting("
                                    + "receipt_source, receipt_time, amount,report_time,activity_code) "
                                    + "SELECT receipt_source, receipt_time, amount,current_timestamp,activity_code  FROM moh_reportings");
                            pstmt.executeUpdate();

                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader2), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        // docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        SqlExec.printStackTrace();

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Universal Healthcare Monthly Sales Summary Report - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    int c = 0;
                    double Totals = 0.00;
                    double OS = 0.00;
                    try {

                        //com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+1);
                        String headerWidths = null;

                        java.util.Vector headerVector = new java.util.Vector(1, 1);

                        int z = rangeDates.length;

                        int headerwidths[] = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                        table.getDefaultCell().setColspan(19);

                        for (int x = 0; x < rangeDates.length; x++) {
                            try {
                                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();

                                table.getDefaultCell().setColspan(8);
                                phrase = new Phrase("UNIVERSAL HEALTHCARE : MONTHLY BILLING - ["+schemeName.toUpperCase()+"]", pFontHeader2);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Facility Name:   " + compName.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Month :   " + monthString.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Year :   " + yearString.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);

                            } catch (java.text.ParseException prs) {
                                prs.printStackTrace();
                            }
                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT | PdfCell.ALIGN_BOTTOM);
                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        phrase = new Phrase("FIS03D", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("DATE", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("FAREWELL HOME/MORTUARY", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("PHARMACY", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("LABORATORY", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("MEDICAL RECORDS", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("OUT-PATIENT", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("X-RAY/RADIOLOGY", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("MOPC", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("IN-PATIENT", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("DENTAL", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("PHYSIO", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("OCCUPATIONAL", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("THEATER", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("GYNAECOLOGY", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("NUTRITION", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("PSYCHIATRIC", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("REFERRAL", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("MCH/FP/OTHERS", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("TOTAL", pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            double GrandTotal = 0.00;
                            double Over120Total = 0.00;
                            java.lang.Object[] listofAct = this.getListofActivities();
                            java.lang.Object[] listofAct1 = this.getListofActivities1();
                            double TurnOver = 0.00;
                            double Over120 = 0.00;
                            double TotalCount = 0.00;

                            double nhifdebts1 = 0.00;
                            double exedebts1 = 0.00;
                            double absdebts1 = 0.00;
                            double othdebts1 = 0.00;
                            double Tgross = 0.00;

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(1);
                            java.sql.Statement stmta1 = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st23 = connectDB.createStatement();
                            java.sql.Statement st211 = connectDB.createStatement();
                            java.sql.Statement st221 = connectDB.createStatement();
                            java.sql.Statement st231 = connectDB.createStatement();
                            java.sql.Statement st2A = connectDB.createStatement();
                            java.sql.Statement st2B = connectDB.createStatement();
                            java.sql.Statement st2C = connectDB.createStatement();
                            java.sql.Statement stc = connectDB.createStatement();

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            int dayTotal = 0;
                            String mot = null;
                            String opp = null;
                            String the = null;
                            String lab = null;
                            String xray = null;
                            String pha = null;
                            String ip = null;
                            String mat = null;
                            String aot = null;
                            String pmo = null;
                            String fct = null;
                            String dbt = null;
                            String nhifs = null;

                            //    for (int p = 1; p <= 31 ; p++) {
                            // java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'LAB'");
                            // java.sql.ResultSet rset111 = pset111.executeQuery();
                            // while (rset111.next()){
                            //     lab = rset111.getString(1);
                            // }
                            //for ( int w = 0; w < listofAct1.length; w++){
                            for (int i = 0; i < listofAct.length; i++) {

                                double janu = 0.00;
                                double janus = 0.00;
                                double januss = 0.00;
                                double jans = 0.00;
                                double labs = 0.00;
                                double debtor = 0.00;
                                double Cashs = 0.00;
                                double nhif = 0.00;
                                double banking = 0.00;
                                double waiver = 0.00;
                                double Twaivers = 0.00;

                                double nhifdebts = 0.00;
                                double exedebts = 0.00;
                                double absdebts = 0.00;
                                double othdebts = 0.00;

                                java.sql.Statement st01 = connectDB.createStatement();

                                // Get the total revenue for the day irrespective of type of service where the transaction type is not "Banking"
                                // This figure becomes the value for the first column in the MOH Cash Analysis Report
                                java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(debit-credit) FROM ac_cash_collection WHERE date = '" + listofAct[i] + "' AND transaction_type != 'Banking'");
                                java.sql.ResultSet rset = pset.executeQuery();

                                while (rset.next()) {
                                    janu = rset.getDouble(1);
                                    Tsales = Tsales + janu;

                                }

                                // Get Farewell Home/ Mortuary sales
                                double currentMOTTotal = 0.00;

                                java.sql.PreparedStatement psetd = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'MOT'");

                                java.sql.ResultSet rsetd = psetd.executeQuery();

                                while (rsetd.next()) {
                                    currentMOTTotal = currentMOTTotal + rsetd.getDouble(1);

                                }

                                // Get pharmacy charges
                                double currentPFTotal = 0.00;

                                java.sql.PreparedStatement pstmtPharmacy = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'PF'");
                                java.sql.ResultSet rsetPharmacy = pstmtPharmacy.executeQuery();

                                while (rsetPharmacy.next()) {
                                    currentPFTotal = currentPFTotal + rsetPharmacy.getDouble(1);
                                }

                                // Get Laboratory charges
                                double currentLABTotal = 0.00;

                                java.sql.PreparedStatement psetLAB = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'LAB'");
                                java.sql.ResultSet rsetLAB = psetLAB.executeQuery();

                                while (rsetLAB.next()) {
                                    currentLABTotal = currentLABTotal + rsetLAB.getDouble(1);
                                }

                                // Get Medical Records sales
                                double currentEMRTotal = 0.00;

                                java.sql.PreparedStatement psetEMR = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'EMR'");
                                java.sql.ResultSet rsetEMR = psetEMR.executeQuery();

                                while (rsetEMR.next()) {
                                    currentEMRTotal = currentEMRTotal + rsetEMR.getDouble(1);
                                }

                                // Get OUT-Patient sales
                                double currentOPPTotal = 0.00;

                                java.sql.PreparedStatement psetOPP = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'OPP'");
                                java.sql.ResultSet rsetOPP = psetOPP.executeQuery();

                                while (rsetOPP.next()) {
                                    currentOPPTotal = currentOPPTotal + rsetOPP.getDouble(1);
                                }

                                //Get Radiology/Xray sales
                                double currentXRYTotal = 0.00;

                                java.sql.PreparedStatement psetXRY = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'XRY'");
                                java.sql.ResultSet rsetXRY = psetXRY.executeQuery();

                                while (rsetXRY.next()) {
                                    currentXRYTotal = currentXRYTotal + rsetXRY.getDouble(1);
                                }

                                //Get MOPC sales
                                double currentMOPCTotal = 0.00;

                                java.sql.PreparedStatement psetMOPC = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'MOPC'");
                                java.sql.ResultSet rsetMOPC = psetMOPC.executeQuery();

                                while (rsetMOPC.next()) {
                                    currentMOPCTotal = currentMOPCTotal + rsetMOPC.getDouble(1);
                                }

                                //Get In_patient sales
                                double currentIPPTotal = 0.00;

                                java.sql.PreparedStatement pset1 = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'IPP'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by payee");

                                java.sql.ResultSet rset1 = pset1.executeQuery();
                                while (rset1.next()) {

                                    currentIPPTotal = currentIPPTotal + rset1.getDouble(1);

                                }

                                //Get Dental sales
                                double currentDENTotal = 0.00;

                                java.sql.PreparedStatement psetDEN = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'DEN'");
                                java.sql.ResultSet rsetDEN = psetDEN.executeQuery();

                                while (rsetDEN.next()) {
                                    currentDENTotal = currentDENTotal + rsetDEN.getDouble(1);
                                }

                                //Get Physio sales
                                double currentPHYTotal = 0.00;

                                java.sql.PreparedStatement psetPHY = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'PHY'");
                                java.sql.ResultSet rsetPHY = psetPHY.executeQuery();

                                while (rsetPHY.next()) {
                                    currentPHYTotal = currentPHYTotal + rsetPHY.getDouble(1);
                                }

                                //Get Occupational Therapy sales
                                double currentOCCTotal = 0.00;

                                java.sql.PreparedStatement psetOCC = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'OCC'");
                                java.sql.ResultSet rsetOCC = psetOCC.executeQuery();

                                while (rsetOCC.next()) {
                                    currentOCCTotal = currentOCCTotal + rsetOCC.getDouble(1);
                                }

                                // Get Theater sales
                                double currentTHETotal = 0.00;

                                java.sql.PreparedStatement psetTHE = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'THE'");
                                java.sql.ResultSet rsetTHE = psetTHE.executeQuery();

                                while (rsetTHE.next()) {

                                    currentTHETotal = currentTHETotal + rsetTHE.getDouble(1);
                                }

                                // Get Maternity and Gyneacology sales
                                double currentMATTotal = 0.00;

                                java.sql.PreparedStatement psetj1 = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'GYN'");
                                java.sql.ResultSet rsetj1 = psetj1.executeQuery();

                                while (rsetj1.next()) {
                                    currentMATTotal = currentMATTotal + rsetj1.getDouble(1);
                                }

                                //Get Nutrition sales
                                double currentNUTTotal = 0.00;

                                java.sql.PreparedStatement psetNUT = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'NUT'");
                                java.sql.ResultSet rsetNUT = psetNUT.executeQuery();

                                while (rsetNUT.next()) {
                                    currentNUTTotal = currentNUTTotal + rsetNUT.getDouble(1);
                                }

                                //Get Psychiatric sales
                                double currentPSYTotal = 0.00;

                                java.sql.PreparedStatement psetPSY = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'PSY'");
                                java.sql.ResultSet rsetPSY = psetPSY.executeQuery();

                                while (rsetPSY.next()) {
                                    currentPSYTotal = currentPSYTotal + rsetPSY.getDouble(1);
                                }

                                //Get Referral sales
                                double currentREFTotal = 0.00;

                                java.sql.PreparedStatement psetREF = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND (SELECT DISTINCT department FROM pb_activity WHERE upper(hp_patient_card.main_service) = upper(pb_activity.activity)) = 'REF'");
                                java.sql.ResultSet rsetREF = psetREF.executeQuery();

                                while (rsetREF.next()) {
                                    currentREFTotal = currentREFTotal + rsetREF.getDouble(1);
                                }

                                // Get other all other charges
                                double currentAOTTotal = 0.00;

                                java.sql.PreparedStatement psetw = connectDB.prepareStatement("SELECT sum(debit-credit) FROM hp_patient_card WHERE invoice_no IN (SELECT invoice_no FROM ac_debtors WHERE date = '" + listofAct[i] + "' AND payee = upper('"+schemeName+"')) AND UPPER(transaction_type) = UPPER('Billing') AND UPPER(main_service) IN (SELECT DISTINCT UPPER(activity) FROM pb_activity WHERE department = 'AOT')");
                                java.sql.ResultSet rsetw = psetw.executeQuery();

                                while (rsetw.next()) {
                                    currentAOTTotal = currentAOTTotal + rsetw.getDouble(1);
                                }

                                String aotDesc = null;
                                String aotother = null;

                                Uexemp = 0.00;

                                if (i < listofAct1.length) {

                                    //java.sql.PreparedStatement psetcw = connectDB.prepareStatement("select sum(amount) FROM moh_reporting WHERE activity_code = '" + listofAct1[i] + "' AND receipt_time between '" + beginDate + "' AND '" + endDate + "'");// AND transaction_type ilike 'receipt%'");
                                    java.sql.PreparedStatement psetcw = connectDB.prepareStatement("select sum(amount) FROM moh_reporting WHERE  activity_code = '" + listofAct1[i] + "' AND receipt_time between '" + beginDate + "' AND '" + endDate + "'");// AND transaction_type ilike 'receipt%'");

                                    java.sql.ResultSet rsetcw = psetcw.executeQuery();

                                    java.sql.PreparedStatement psetc1w = connectDB.prepareStatement("select initcap(activity) from pb_activity WHERE code = '" + listofAct1[i] + "'");
                                    java.sql.ResultSet rsetc1w = psetc1w.executeQuery();

                                    System.out.print("Dealing with this activity code : [" + listofAct1[i] + "] and description : [");

                                    while (rsetc1w.next()) {

                                        aotDesc = rsetc1w.getString(1);
                                        System.out.print(aotDesc + "]");
                                        System.out.println();
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    }
                                    while (rsetcw.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        Uexemp = rsetcw.getDouble(1);

                                    }

                                } else {

                                    java.sql.PreparedStatement psetw2 = connectDB.prepareStatement("select sum(amount) FROM moh_reporting WHERE receipt_time between '" + beginDate + "' AND '" + endDate + "'  AND  receipt_source = 'OTH'");//(activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    java.sql.ResultSet rsetw2 = psetw2.executeQuery();
                                    if (c == 0) {
                                        while (rsetw2.next()) {

                                            Uexemp = rsetw2.getDouble(1);

                                            aotDesc = "Other";

                                        }
                                    } else {
                                        Uexemp = 0;

                                        aotDesc = "";
                                    }
                                    c = c + 1;

                                }

                                Unhif = Unhif + Uexemp;

                                java.sql.PreparedStatement psethbF = connectDB.prepareStatement("select distinct gl_code from ac_banks_setup WHERE branch_name ilike 'FACILITY%'");
                                java.sql.ResultSet rsethbF = psethbF.executeQuery();

                                double currentFCTTotal = 0.00;

                                while (rsethbF.next()) {
                                    fct = rsethbF.getString(1);

                                    java.sql.PreparedStatement psetbk = connectDB.prepareStatement("select sum(credit-debit) FROM ac_cash_collection WHERE date = '" + listofAct[i] + "' AND activity_code = '" + fct + "' AND transaction_type ILIKE 'Banking'");
                                    java.sql.ResultSet rsetbk = psetbk.executeQuery();

                                    while (rsetbk.next()) {
                                        currentFCTTotal = currentFCTTotal + rsetbk.getDouble(1);
                                    }

                                }

                                java.sql.PreparedStatement psethbp = connectDB.prepareStatement("select distinct gl_code from ac_banks_setup WHERE branch_name ilike 'PMO%'");
                                java.sql.ResultSet rsethbp = psethbp.executeQuery();

                                double currentPMOTotal = 0.00;

                                while (rsethbp.next()) {
                                    pmo = rsethbp.getString(1);

                                    java.sql.PreparedStatement psetbp = connectDB.prepareStatement("select sum(credit-debit) FROM ac_cash_collection WHERE date = '" + listofAct[i] + "' AND activity_code = '" + pmo + "' AND transaction_type ILIKE 'Banking'");
                                    java.sql.ResultSet rsetbp = psetbp.executeQuery();

                                    while (rsetbp.next()) {
                                        currentPMOTotal = currentPMOTotal + rsetbp.getDouble(1);
                                    }

                                }

                                java.sql.PreparedStatement psetod = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'DR' ORDER BY gl_account");
                                java.sql.ResultSet rsetod = psetod.executeQuery();

                                double currentDRTotal = 0.00;

                                while (rsetod.next()) {
                                    dbt = rsetod.getString(1);

                                    java.sql.PreparedStatement psethdt = connectDB.prepareStatement("select sum(debit-credit) FROM ac_cash_collection WHERE date = '" + listofAct[i] + "' AND activity_code = '" + dbt + "'");
                                    java.sql.ResultSet rsethdt = psethdt.executeQuery();

                                    while (rsethdt.next()) {
                                        currentDRTotal = currentDRTotal + rsethdt.getDouble(1);

                                    }
                                }

                                java.sql.PreparedStatement psethnh = connectDB.prepareStatement("select distinct gl_code from ac_schemes WHERE category ilike 'NHIF%'");
                                java.sql.ResultSet rsethnh = psethnh.executeQuery();

                                double currentNHIFTotal = 0.00;

                                while (rsethnh.next()) {
                                    nhifs = rsethnh.getString(1);

                                    java.sql.PreparedStatement psetwnh = connectDB.prepareStatement("select sum(debit-credit) FROM ac_cash_collection WHERE date = '" + listofAct[i] + "' AND receipt_source ILIKE 'NHIF'");//(activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    java.sql.ResultSet rsetwnh = psetwnh.executeQuery();

                                    while (rsetwnh.next()) {
                                        currentNHIFTotal = currentNHIFTotal + rsetwnh.getDouble(1);
                                    }

                                    java.sql.PreparedStatement psetwn1d = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE date = '" + listofAct[i] + "'  AND activity_code = '" + nhifs + "'  AND debit > 0");
                                    java.sql.ResultSet rsetwn1d = psetwn1d.executeQuery();
                                    System.out.println("This is nhif code " + nhifs);
                                    while (rsetwn1d.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        nhifdebts = rsetwn1d.getDouble(1);
                                        nhifdebts1 = nhifdebts1 + nhifdebts;

                                    }

                                    java.sql.PreparedStatement psetwn4 = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE date = '" + listofAct[i] + "'  AND activity_code != '" + nhifs + "' AND debit > 0");
                                    java.sql.ResultSet rsetwn4 = psetwn4.executeQuery();

                                    while (rsetwn4.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        othdebts = rsetwn4.getDouble(1);
                                        othdebts1 = othdebts1 + othdebts;

                                    }

                                }

                                java.sql.PreparedStatement psetwn = connectDB.prepareStatement("select sum(amount*-1) FROM moh_reporting WHERE receipt_time = '" + listofAct[i] + "'  AND receipt_source = 'IEDS'");
                                java.sql.ResultSet rsetwn = psetwn.executeQuery();
                                while (rsetwn.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    waiver = rsetwn.getDouble(1);
                                    Uwaivers = Uwaivers + waiver;

                                }

                                java.sql.PreparedStatement psetwn3 = connectDB.prepareStatement("select sum(credit-debit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND payee ILIKE 'ABS%'");
                                java.sql.ResultSet rsetwn3 = psetwn3.executeQuery();

                                java.sql.PreparedStatement psetz = connectDB.prepareStatement("select sum(credit-debit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND transaction_type ILIKE 'Receipts'  AND activity_code = '" + nhifs + "'");
                                java.sql.ResultSet rsetz = psetz.executeQuery();

                                java.sql.PreparedStatement psetc = connectDB.prepareStatement("select sum(credit-debit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND transaction_type ILIKE 'Receipts'  AND activity_code != '" + nhifs + "'");
                                java.sql.ResultSet rsetc = psetc.executeQuery();

                                java.sql.PreparedStatement psetcd = connectDB.prepareStatement("SELECT EXTRACT(DAY FROM DATE '" + listofAct[i] + "')");// AND transaction_type ILIKE 'Receipts' AND (payee NOT ILIKE 'nhif%' OR payee NOT ILIKE 'n.h.i.f%')");
                                java.sql.ResultSet rsetcd = psetcd.executeQuery();

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setMinimumHeight(15);
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                // phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                while (rsetcd.next()) {
                                    phrase = new Phrase(rsetcd.getString(1), pFontHeader);

                                    table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);

                                Tmot = Tmot + currentMOTTotal;
                                Cashs = Cashs + currentMOTTotal;

                                Tpha = Tpha + currentPFTotal;
                                Cashs = Cashs + currentPFTotal;

                                Tlab = Tlab + currentLABTotal;
                                Cashs = Cashs + currentLABTotal;

                                Temr = Temr + currentEMRTotal;
                                Cashs = Cashs + currentEMRTotal;

                                Top = Top + currentOPPTotal;
                                Cashs = Cashs + currentOPPTotal;

                                Txray = Txray + currentXRYTotal;
                                Cashs = Cashs + currentXRYTotal;

                                Tmopc = Tmopc + currentMOPCTotal;
                                Cashs = Cashs + currentMOPCTotal;

                                Tipcash = Tipcash + currentIPPTotal;
                                Cashs = Cashs + currentIPPTotal;

                                Tden = Tden + currentDENTotal;
                                Cashs = Cashs + currentDENTotal;

                                Tphy = Tphy + currentPHYTotal;
                                Cashs = Cashs + currentPHYTotal;

                                Tocc = Tocc + currentOCCTotal;
                                Cashs = Cashs + currentOCCTotal;

                                Ttheat = Ttheat + currentTHETotal;
                                Cashs = Cashs + currentTHETotal;

                                Tmatt = Tmatt + currentMATTotal;
                                Cashs = Cashs + currentMATTotal;

                                Tnut = Tnut + currentNUTTotal;
                                Cashs = Cashs + currentNUTTotal;

                                Tpsy = Tpsy + currentPSYTotal;
                                Cashs = Cashs + currentPSYTotal;

                                Tref = Tref + currentREFTotal;
                                Cashs = Cashs + currentREFTotal;

                                Tother = Tother + currentAOTTotal;
                                Cashs = Cashs + currentAOTTotal;

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                Tcash = Tcash + Cashs;

                                //// Add values of various totals for departmental income
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(janu/*Cashs + waiver + exedebts + nhifdebts + othdebts + absdebts-currentNHIFTotal-currentDRTotal*/), "0.00")), pFontHeader1);
//                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentMOTTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentPFTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentLABTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentEMRTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentOPPTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentXRYTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentMOPCTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentIPPTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentDENTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentPHYTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentOCCTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentTHETotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentMATTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentNUTTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentPSYTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentREFTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentAOTTotal), "0.00")), pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Cashs), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setMinimumHeight(15);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Total", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tmot), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tpha), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tlab), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Temr), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Top), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Txray), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tmopc), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tipcash), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tden), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tphy), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tocc), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Ttheat), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tmatt), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tnut), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tpsy), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tref), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tother), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Tcash), "0.00")), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorder(Rectangle.TOP);

                            table.getDefaultCell().setMinimumHeight(18);
                            table.getDefaultCell().setColspan(19);
                            phrase = new Phrase(" ", pFontHeader2);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            //  java.sql.PreparedStatement pstmtPrint = connectDB.prepareStatement("SELECT FROM secure_menu_access WHERE")
                            table.getDefaultCell().setColspan(10);
                            phrase = new Phrase("Date Printed: [" + com.afrisoftech.lib.ServerTime.serverTimeStamp(connectDB) + "]", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(9);
                            phrase = new Phrase("Printed By : [" + com.afrisoftech.lib.UserName.getUserName(connectDB) + "]", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Prepared By __________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Aprroved By __________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Entered By __________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Signature  ____________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Signature  ____________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Signature  ___________________________", pFontHeader2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(7);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            phrase = new Phrase("Date Prepared ________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Date Approved ________________________", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Date Entered ________________________", pFontHeader2);

                            table.addCell(phrase);

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        //  }
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        // Bad
                        BadElExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {
                    fnfExec.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {
                lwDocexec.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {
            IOexec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

    public java.lang.Object[] getListofActivities() {
        int interval = 0;
        java.lang.Object[][] rangeDates = dailySeries.getAgeingDateSeries();
        java.lang.Object[][] monthDates = ageingSeries.getAgeingDateSeries();

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        //for (int k = 0;  k < monthDates.length; k++){
        for (int k = monthDates.length - 1; k >= 0; k--) {

            for (int t = 0; t < rangeDates.length; t++) {

                listActVector.addElement(rangeDates[t][k]);

            }
        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;

    }

    public java.lang.Object[] getListofActivities1() {

        java.lang.Object[] listofActivities1 = null;

        java.util.Vector listActVector1 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT code FROM pb_activity WHERE department = 'AOT' GROUP BY code ORDER BY code");

            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector1.addElement(rSet1.getObject(1).toString());

            }

            //System.out.println(rSet1.getObject(1).toString());
        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }
    
        public java.lang.Object[] getListofInvoices(java.util.Date invoiceDate) {

        java.lang.Object[] listofActivities1 = null;

        java.util.Vector listActVector1 = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT DISTINCT invoice_no FROM ac_debtors WHERE scheme = ? AND date::date = '"+invoiceDate+"' ORDER BY 1");

            pSet1.setString(1, schemeName);

            
            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector1.addElement(rSet1.getObject(1).toString());

            }

            //System.out.println(rSet1.getObject(1).toString());
        } catch (java.sql.SQLException sqlExec) {
            sqlExec.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }
}
