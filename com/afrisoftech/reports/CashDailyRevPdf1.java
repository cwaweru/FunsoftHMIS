//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class CashDailyRevPdf1 implements java.lang.Runnable {

    double Tsales = 0.00;
    double Tipcash = 0.00;
    double Tmatt = 0.00;
    double Txray = 0.00;
    double Tlab = 0.00;
    double Ttheat = 0.00;
    double Tmot = 0.00;
    double Top = 0.00;
    double Tpha = 0.00;
    double Tother = 0.00;
    double Tcash = 0.00;
    double Tnhif = 0.00;
    double TnDebtors = 0.00;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeaderDetail = FontFactory.getFont(FontFactory.TIMES, 8, Font.ITALIC);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void CashDailyRevPdf1(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        // Debtor = combox;
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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            java.util.Calendar calendar = java.util.Calendar.getInstance();

            long dateNow = calendar.getTimeInMillis();

            java.sql.Date datenowSql = new java.sql.Date(dateNow);

            System.out.println(datenowSql.toString());


            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader2), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                    // docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Cash Monthly Collection  Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    // docPdf.setFooter(footer);


                    docPdf.open();


                    double Totals = 0.00;
                    double OS = 0.00;
                    try {


                        //com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length+1);
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);

                        String headerWidths = null;

                        java.util.Vector headerVector = new java.util.Vector(1, 1);

                        int z = rangeDates.length;


                        int headerwidths[] = {10, 20, 20, 20, 20};//,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};


                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                        table.getDefaultCell().setColspan(1);
                        Phrase phrase = new Phrase("");
                        //for (int x = 0; x < rangeDates.length; x++) {
                            
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
                            String otherDebt = null;

                            //    for (int p = 1; p <= 31 ; p++) {

                            // java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'LAB'");
                            // java.sql.ResultSet rset111 = pset111.executeQuery();
                            // while (rset111.next()){
                            //     lab = rset111.getString(1);
                            // }

                            //for ( int w = 0; w < listofAct1.length; w++){

                            for (int i = 0; i < listofAct.length; i++) {

                                try {
                                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(listofAct[i].toString().trim()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(listofAct[i].toString().trim()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatterz = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(listofAct[i].toString()), "dd MMM yyyy");

                                java.lang.String yearStringz = dateFormatterz.getDateString();
                                //  try {
                                //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                //java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("DAILY CASH COLLECTIONS REPORT", pFontHeader2);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("DATE : " + yearStringz, pFontHeader2);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Facility :   " + compName.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Month :   " + monthString.toUpperCase(), pFontHeader2);

                                // table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Year :   " + yearString.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);


                            /*  } catch(java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }*/
                            } catch (java.text.ParseException prs) {
                                prs.printStackTrace();
                            }
                        //}

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT | PdfCell.ALIGN_BOTTOM);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Printed on : " + date, pFontHeader);
                        //  table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        phrase = new Phrase("INDEX", pFontHeader2);
                        table.addCell(phrase);

                        //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT | PdfCell.ALIGN_BOTTOM);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("DEPARTMENT", pFontHeader2);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("AMOUNT", pFontHeader2);
                        table.addCell(phrase);
                        /*
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("RECEIPTS",pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("TOTAL CASH",pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        phrase = new Phrase("REVENUE NOT COLLECTED",pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("BANKING",pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("MONTHLY BREAKDOWN OF OTHER INCOME",pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setColspan(1);
                         *
                        phrase = new Phrase(" ",pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("This figure is based on total services given to all patients, whether Cash, Nhif, Waived, exempt or absconded",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("IP CASH - Beds & Procedures General Paediatric Maternity Amenity",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Maternity Deliveries Abnormal D. C.Section ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("X-Ray ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Lab ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Theatre - Major Surg. Minor Surg Circumcision ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Farewell Body/Day Embalment Post Mortem Disposal ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("OP Treatment Dressings Injections P.O.P Stitching / Removal ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Pharmacy ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Other (Breakdown on right side of this form) ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("CASH Collections from daily Services ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("NHIF Receipts ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("OTHER Debtor Receipts ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Add Cash Collections NHIF and Other Receipts ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("NHIF Patients ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Other Debtors ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Waivers ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Exemptions ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Absconders ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Amount Banked in Facility A/C ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Amount Banked Directly in A/C for       [  ] P.M.O      [  ] M.O.H      [  ] OTHER ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Total Banking ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Category ",pFontHeader);
                        table.addCell(phrase);

                        phrase = new Phrase("Amount ",pFontHeader);
                        table.addCell(phrase);
                         */
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

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


                                java.sql.PreparedStatement pset = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND transaction_type != 'Banking'");
                                java.sql.ResultSet rset = pset.executeQuery();

                                while (rset.next()) {
                                    janu = rset.getDouble(1);
                                    Tsales = Tsales + janu;


                                }

                                java.sql.PreparedStatement psetwav = connectDB.prepareStatement("select sum(credit-debit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND transaction_type ilike 'waiver'");
                                java.sql.ResultSet rsetwav = psetwav.executeQuery();

                                while (rsetwav.next()) {
                                    Twaivers = rsetwav.getDouble(1);
                                    Gwaivers = Gwaivers + Twaivers;
                                }

                                java.sql.PreparedStatement pset11a = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'IPP'");
                                java.sql.ResultSet rset11a = pset11a.executeQuery();
                                double currentIPPTotal = 0.00;
                                while (rset11a.next()) {
                                    ip = rset11a.getString(1);
                                    //    System.out.println("IPP receipt_time::date is : ["+listofAct[i].toString()+"]");
                                    //     System.out.println("Currently doing IPP gl code [ "+ip.toString()+" ]");
                                    //                                 java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '"+listofAct[i]+"' AND activity_code = '"+ip+"' AND transaction_type != 'Banking'");//< '"+endDate+"'::receipt_time::date and receipt_time::date > '"+endDate+"'::receipt_time::date - 30 group by dealer");

                                    java.sql.PreparedStatement pset1 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + ip + "' AND transaction_type != 'Banking'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                    java.sql.ResultSet rset1 = pset1.executeQuery();
                                    while (rset1.next()) {
                                        //     System.out.println("Currently doing currentIPP  : [ "+rset1.getDouble(1)+" ]");
                                        currentIPPTotal = currentIPPTotal + rset1.getDouble(1);
                                    //      System.out.println("Currently doing currentIPP total : [ "+currentIPPTotal+" ]");
                                    }
                                }

                                java.sql.PreparedStatement psetj = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'MAT'");
                                java.sql.ResultSet rsetj = psetj.executeQuery();
                                double currentMATTotal = 0.00;
                                while (rsetj.next()) {
                                    mat = rsetj.getString(1);


                                    java.sql.PreparedStatement psetj1 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + mat + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rsetj1 = psetj1.executeQuery();

                                    while (rsetj1.next()) {
                                        currentMATTotal = currentMATTotal + rsetj1.getDouble(1);
                                    }
                                }
                                java.sql.PreparedStatement pset11 = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'XRY'");
                                java.sql.ResultSet rset11 = pset11.executeQuery();

                                double currentXRYTotal = 0.00;
                                while (rset11.next()) {
                                    xray = rset11.getString(1);


                                    java.sql.PreparedStatement pset12 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + xray + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rset12 = pset12.executeQuery();

                                    while (rset12.next()) {
                                        currentXRYTotal = currentXRYTotal + rset12.getDouble(1);
                                    }
                                }

                                java.sql.PreparedStatement pset111 = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'LAB'");
                                java.sql.ResultSet rset111 = pset111.executeQuery();


                                double currentLABTotal = 0.00;

                                while (rset111.next()) {
                                    lab = rset111.getString(1);
                                    //     System.out.println("LAB receipt_time::date is : ["+listofAct[i].toString()+"]");
                                    //     System.out.println("Currently doing LAB gl code [ "+lab.toString()+" ]");
                                    java.sql.PreparedStatement pset121 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + lab + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rset121 = pset121.executeQuery();

                                    while (rset121.next()) {
                                        //      System.out.println("Currently doing currentLAB  : [ "+rset121.getDouble(1)+" ]");
                                        currentLABTotal = currentLABTotal + rset121.getDouble(1);
                                    //     System.out.println("Currently doing currentLAB total : [ "+currentLABTotal+" ]");
                                    }
                                }
                                java.sql.PreparedStatement psets1 = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'THE'");
                                java.sql.ResultSet rsets1 = psets1.executeQuery();

                                double currentTHETotal = 0.00;

                                while (rsets1.next()) {
                                    the = rsets1.getString(1);
                                    java.sql.PreparedStatement psets1e = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + the + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rsets1e = psets1e.executeQuery();

                                    while (rsets1e.next()) {

                                        currentTHETotal = currentTHETotal + rsets1e.getDouble(1);
                                    }
                                }

                                java.sql.PreparedStatement psetd1 = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'MOT'");
                                java.sql.ResultSet rsetd1 = psetd1.executeQuery();

                                double currentMOTTotal = 0.00;
                                while (rsetd1.next()) {
                                    mot = rsetd1.getString(1);


                                    java.sql.PreparedStatement psetd = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + mot + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rsetd = psetd.executeQuery();

                                    while (rsetd.next()) {
                                        currentMOTTotal = currentMOTTotal + rsetd.getDouble(1);

                                    }
                                }
                                java.sql.PreparedStatement psetp = connectDB.prepareStatement("select gl_account from pb_operating_parameters WHERE category ilike 'OPP'");
                                java.sql.ResultSet rsetp = psetp.executeQuery();

                                double currentOPPTotal = 0.00;
                                while (rsetp.next()) {
                                    opp = rsetp.getString(1);


                                    java.sql.PreparedStatement psetp1 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + opp + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rsetp1 = psetp1.executeQuery();

                                    while (rsetp1.next()) {
                                        currentOPPTotal = currentOPPTotal + rsetp1.getDouble(1);
                                    }
                                }

                                java.sql.PreparedStatement pseth = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'PF' ORDER BY gl_account");
                                java.sql.ResultSet rseth = pseth.executeQuery();


                                double currentPFTotal = 0.00;

                                while (rseth.next()) {
                                    pha = rseth.getString(1);


                                    java.sql.PreparedStatement pseth1 = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + pha + "' AND transaction_type != 'Banking'");
                                    java.sql.ResultSet rseth1 = pseth1.executeQuery();

                                    while (rseth1.next()) {
                                        currentPFTotal = currentPFTotal + rseth1.getDouble(1);
                                    // java.sql.PreparedStatement psetw = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '"+listofAct[i]+"' AND (activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    //java.sql.ResultSet rsetw = psetw.executeQuery();
                                    }
                                }



                                java.sql.PreparedStatement psethb = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'AOT%'");
                                java.sql.ResultSet rsethb = psethb.executeQuery();


                                double currentAOTTotal = 0.00;

                                while (rsethb.next()) {
                                    aot = rsethb.getString(1);


                                    java.sql.PreparedStatement psetw = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + aot + "' AND transaction_type != 'Banking'");//(activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    java.sql.ResultSet rsetw = psetw.executeQuery();

                                    while (rsetw.next()) {
                                        currentAOTTotal = currentAOTTotal + rsetw.getDouble(1);
                                    }
                                }


                                String aotDesc = null;
                                String aotother = null;
                                java.sql.PreparedStatement psethb1 = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'AOT%' ORDER BY gl_account");
                                java.sql.ResultSet rsethb1 = psethb1.executeQuery();

                                Uexemp = 0.00;

                                //  while (rsethb1.next()){
                                //     aotother = rsethb1.getString(1);
                            /*    if (i < listofAct1.length) {
                                java.sql.PreparedStatement psetcw = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE activity_code = '" + listofAct1[i] + "' AND DATE between '" + beginDate + "' AND '" + endDate + "'");
                                java.sql.ResultSet rsetcw = psetcw.executeQuery();

                                java.sql.PreparedStatement psetc1w = connectDB.prepareStatement("select initcap(activity) from pb_activity WHERE code = '" + listofAct1[i] + "'");
                                java.sql.ResultSet rsetc1w = psetc1w.executeQuery();

                                while (rsetc1w.next()) {
                                aotDesc = rsetc1w.getString(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                }
                                while (rsetcw.next()) {
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                Uexemp = rsetcw.getDouble(1);

                                }
                                //  Unhif = Unhif + Uexemp;


                                } else {
                                aotDesc = "-";
                                Uexemp = 0.00;
                                // Unhif = Unhif + Uexemp;
                                }
                                 */
                                Unhif = Unhif + Uexemp;
                                // }




                                java.sql.PreparedStatement psethbF = connectDB.prepareStatement("select distinct gl_code from ac_banks_setup WHERE branch_name ilike 'FACILITY%'");
                                java.sql.ResultSet rsethbF = psethbF.executeQuery();

                                double currentFCTTotal = 0.00;

                                while (rsethbF.next()) {
                                    fct = rsethbF.getString(1);

                                    java.sql.PreparedStatement psetbk = connectDB.prepareStatement("select sum(credit-debit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + fct + "' AND transaction_type ILIKE 'Banking'");
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

                                    java.sql.PreparedStatement psetbp = connectDB.prepareStatement("select sum(credit-debit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + pmo + "' AND transaction_type ILIKE 'Banking'");
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


                                    java.sql.PreparedStatement psethdt = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND activity_code = '" + dbt + "'");
                                    java.sql.ResultSet rsethdt = psethdt.executeQuery();

                                    while (rsethdt.next()) {
                                        currentDRTotal = currentDRTotal + rsethdt.getDouble(1);
                                    // java.sql.PreparedStatement psetw = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE date = '"+listofAct[i]+"' AND (activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    //java.sql.ResultSet rsetw = psetw.executeQuery();
                                    }
                                }



                                java.sql.PreparedStatement psethnh = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'NHIF%'");
                                java.sql.ResultSet rsethnh = psethnh.executeQuery();


                                double currentNHIFTotal = 0.00;

                                while (rsethnh.next()) {
                                    nhifs = rsethnh.getString(1);

                                    java.sql.PreparedStatement psetwnh = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE date = '" + listofAct[i] + "' AND activity_code = '" + nhifs + "'");//(activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    java.sql.ResultSet rsetwnh = psetwnh.executeQuery();

                                    while (rsetwnh.next()) {
                                        currentNHIFTotal = currentNHIFTotal + rsetwnh.getDouble(1);
                                    }

                                }



                                java.sql.PreparedStatement psethnhd = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'DR%'");
                                java.sql.ResultSet rsethnhd = psethnhd.executeQuery();


                                double currentDebtorsTotal = 0.00;

                                while (rsethnhd.next()) {
                                    otherDebt = rsethnhd.getString(1);

                                    java.sql.PreparedStatement psetwnhd = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE date = '" + listofAct[i] + "' AND activity_code = '" + otherDebt + "'");//(activity_code NOT LIKE '"+pha+"' OR activity_code NOT LIKE '"+opp+"' OR activity_code NOT LIKE '"+mot+"' OR activity_code NOT LIKE '"+the+"' OR activity_code NOT LIKE '"+xray+"' OR activity_code NOT LIKE '"+mot+"')");
                                    java.sql.ResultSet rsetwnhd = psetwnhd.executeQuery();

                                    while (rsetwnhd.next()) {
                                        currentDebtorsTotal = currentDebtorsTotal + rsetwnhd.getDouble(1);
                                    }

                                }

                                java.sql.PreparedStatement psetwn = connectDB.prepareStatement("select sum(credit-debit) from ac_cash_collection WHERE receipt_time::date = '" + listofAct[i] + "' AND transaction_type ILIKE 'Waiver%'");
                                java.sql.ResultSet rsetwn = psetwn.executeQuery();

                                java.sql.PreparedStatement psetwn1 = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND payee ILIKE 'NHIF%'");
                                java.sql.ResultSet rsetwn1 = psetwn1.executeQuery();

                                java.sql.PreparedStatement psetwn2 = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND payee ILIKE 'EXE%'");
                                java.sql.ResultSet rsetwn2 = psetwn2.executeQuery();

                                java.sql.PreparedStatement psetwn3 = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND payee ILIKE 'ABS%'");
                                java.sql.ResultSet rsetwn3 = psetwn3.executeQuery();

                                java.sql.PreparedStatement psetwn4 = connectDB.prepareStatement("select sum(debit-credit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND payee ILIKE 'OTH%'");
                                java.sql.ResultSet rsetwn4 = psetwn4.executeQuery();

                                // java.sql.PreparedStatement psetr = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE date = '"+listofAct[i]+"' AND (receipt_no NOT LIKE 'AC%' OR receipt_no NOT LIKE '' OR receipt_no IS NOT NULL) AND transaction_type != 'Banking'");
                                // java.sql.ResultSet rsetr = psetr.executeQuery();

                                java.sql.PreparedStatement psetz = connectDB.prepareStatement("select sum(credit-debit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND transaction_type ILIKE 'Receipts' AND (payee ILIKE 'nhif%' OR payee ILIKE 'n.h.i.f%' OR payee ILIKE 'n h%')");
                                java.sql.ResultSet rsetz = psetz.executeQuery();

                                java.sql.PreparedStatement psetc = connectDB.prepareStatement("select sum(credit-debit) from ac_debtors WHERE date = '" + listofAct[i] + "' AND transaction_type ILIKE 'Receipts' AND (payee NOT ILIKE 'nhif%' OR payee NOT ILIKE 'n.h.i.f%')");
                                java.sql.ResultSet rsetc = psetc.executeQuery();

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setMinimumHeight(15);
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("IN-PATIENT", pFontHeader1);
                                table.addCell(phrase);

                                //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(janu+Twaivers),"0.00")),pFontHeader1);
                                //  table.addCell(phrase);



                                //  while (rset1.next()){
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);

                                //      janus = rset1.getDouble(1);
                                Tipcash = Tipcash + currentIPPTotal;
                                Cashs = Cashs + currentIPPTotal;
                                //                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(janus),"0.00")),pFontHeader1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentIPPTotal), "0.00")), pFontHeader1);

                                table.addCell(phrase);

                                // }

                                // Maternity Cash
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("MATERNITY", pFontHeader1);
                                table.addCell(phrase);

                                //  while (rsetj1.next()){
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);

                                Tmatt = Tmatt + currentMATTotal;
                                Cashs = Cashs + currentMATTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentMATTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("X-RAY", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                Txray = Txray + currentXRYTotal;
                                Cashs = Cashs + currentXRYTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentXRYTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                                //Laboratory
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("LABORATORY", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                Tlab = Tlab + currentLABTotal;
                                Cashs = Cashs + currentLABTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentLABTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                                // Theatre
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("THEATRE", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                //
                                Ttheat = Ttheat + currentTHETotal;
                                Cashs = Cashs + currentTHETotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentTHETotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                                // Farewell
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("MORTUARY", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                Tmot = Tmot + currentMOTTotal;
                                Cashs = Cashs + currentMOTTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentMOTTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);
                                // OUT-Patient
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("OUT PATIENT", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                Top = Top + currentOPPTotal;
                                Cashs = Cashs + currentOPPTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentOPPTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                                // Pharmacy

                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("PHARMACY", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                Tpha = Tpha + currentPFTotal;
                                Cashs = Cashs + currentPFTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentPFTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);



                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);



                                //    Tcash = Tcash + (Cashs-Twaivers);
                                //    phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Cashs-Twaivers),"0.00")),pFontHeader1);
                                //    table.addCell(phrase);

                                // NHIF Receipts
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("NHIF RECEIPTS", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                Tnhif = Tnhif + currentNHIFTotal;
                                Cashs = Cashs + currentNHIFTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentNHIFTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);

                                //Other Debtors

                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("OTHER DEBTORS RECEIPTS", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                TnDebtors = TnDebtors + currentDebtorsTotal;
                                Cashs = Cashs + currentDebtorsTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentDebtorsTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);



                                // Other income
                                numberSeq = numberSeq + 1;
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("OTHER INCOME", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                                Tother = Tother + currentAOTTotal;
                                Cashs = Cashs + currentAOTTotal;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(currentAOTTotal), "0.00")), pFontHeader1);
                                table.addCell(phrase);
                                
                                // Print detailed information for other(Miscellaneous) income departments

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeaderDetail);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Details for other income".toUpperCase(), pFontHeaderDetail);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeaderDetail);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeaderDetail);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Department".toUpperCase().toUpperCase(), pFontHeaderDetail);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Amount(KSH)", pFontHeaderDetail);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("", pFontHeaderDetail);
                                table.addCell(phrase);

                                if (i < listofAct1.length) {
                                    for (int k = 0; k < listofAct1.length; k++) {
                                        java.sql.PreparedStatement psetcw = connectDB.prepareStatement("select sum(debit-credit) from ac_cash_collection WHERE activity_code = '" + listofAct1[k] + "' AND receipt_time::date between '" + beginDate + "' AND '" + endDate + "'");
                                        java.sql.ResultSet rsetcw = psetcw.executeQuery();

                                        java.sql.PreparedStatement psetc1w = connectDB.prepareStatement("select initcap(activity) from pb_activity WHERE code = '" + listofAct1[k] + "'");
                                        java.sql.ResultSet rsetc1w = psetc1w.executeQuery();
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        phrase = new Phrase("", pFontHeaderDetail);
                                        table.addCell(phrase);
                                        while (rsetc1w.next()) {
                                            aotDesc = rsetc1w.getString(1);
                                            table.getDefaultCell().setColspan(2);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(aotDesc, pFontHeaderDetail);
                                            table.addCell(phrase);
                                        }
                                        while (rsetcw.next()) {
                                            //   table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            Uexemp = rsetcw.getDouble(1);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Uexemp), "0.00")), pFontHeaderDetail);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("", pFontHeaderDetail);
                                            table.addCell(phrase);
                                        }
                                    //  Unhif = Unhif + Uexemp;

                                    }
                                } else {
                                    aotDesc = "-";
                                    Uexemp = 0.00;
                                // Unhif = Unhif + Uexemp;
                                }



                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase("  ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                //        table.getDefaultCell().setMinimumHeight(15);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase("Total cash collections for the day", pFontHeader2);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(dbObject.getDBObject(java.lang.String.valueOf(Cashs/*Tsales + nhifdebts1 + othdebts1 + exedebts1 + absdebts1*/), "0.00")), pFontHeader2);
                                table.addCell(phrase);

                            }


                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                    //  }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        // Bad

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

             

            try {

                if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {

                    System.out.println(tempFile);

                    wait_for_Pdf2Show = rt.exec("xpdf " + tempFile + "");

                    wait_for_Pdf2Show.waitFor();

                } else {

                    wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe " + tempFile);

                    wait_for_Pdf2Show.waitFor();

                }

            } catch (java.lang.InterruptedException intrExec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());

            }



        } catch (java.io.IOException IOexec) {

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

            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("select distinct gl_account from pb_operating_parameters WHERE category ilike 'AOT%' GROUP BY gl_account ORDER BY gl_account");


            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector1.addElement(rSet1.getObject(1).toString());

            }

        //System.out.println(rSet1.getObject(1).toString());
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }
}





