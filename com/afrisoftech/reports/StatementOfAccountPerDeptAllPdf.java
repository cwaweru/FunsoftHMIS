//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.afrisoftech.records.reports.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public  class StatementOfAccountPerDeptAllPdf implements java.lang.Runnable {

    double Credit = 0.00;
    double tot = 0.00;
    // double current = 0.00;
    //double over30 = 0.00;
    // double over60 = 0.00;
    //  double over90 = 0.00;
    // double turnOver = 0.00;
    int over = 0;
    int name = 0;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String Debtor = null;
    java.util.Date endDate = null;
    java.util.Date beginDate = null;
    com.afrisoftech.lib.PeriodicDates periodicDates = null;
    com.afrisoftech.timeseries.DailyAgeing ageingSeries = null;
    java.util.Date ageingDates[][] = null;
    String ks;
    double totalForMonth = 0.00;
    double osBalance = 0.00;
    double current = 0.00;
    double over30 = 0.00;
    double over60 = 0.00;
    double over90 = 0.00;
    double turnOver = 0.00;
    int dayInMonth = 0;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader111 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    String sch = "";

    public void StatementOfAccountPerDeptAllPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, String sche, String code) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        // Debtor = combox;
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        sch = sche;
        dbObject = new com.afrisoftech.lib.DBObject();
        System.out.println("Days Date" + endDate);
        // periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.DailyAgeing(31, endate);
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

            this.generatePdf(sch);

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

    public void generatePdf(String scheme) {

        java.lang.Object[][] rangeDates = ageingSeries.getAgeingDateSeries();

        // ageingDates = ageingSeries.getAgeingDateSeries();
        double columnTotals[] = new double[rangeDates.length];

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        int interval = 0;
        int seq = 0;

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

                    String compName = null;
                    String date = null;
                    String district = null;
                    try {

                        //   java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st4s = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency,district_branch from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                            district = rset2.getString(3);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        java.sql.ResultSet rset4s = st4s.executeQuery("SELECT '" + endDate + "'::date - '" + beginDate + "'::date + 1");

                        while (rset4s.next()) {
                            dayInMonth = rset4s.getInt(1);
                        }
                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader111), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("STATEMENT OF ACCOUNT PER DEPARTMENT "), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Scheme statement  Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    //docPdf.setFooter(footer);
                    docPdf.open();

                    double Totals = 0.00;
                    double OS = 0.00;
                    System.out.println(dayInMonth);

//                    if (dayInMonth == 1) {
//                        try {
//                            System.err.println("Lenght " + rangeDates.length);
//                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(1 + 3);
//
//                            String headerWidths = null;
//
//                            java.util.Vector headerVector = new java.util.Vector(1, 1);
//
//                            int z = rangeDates.length;
//
//                            int headerwidths[] = {4, 20, 8, 12};//,13,13};
//
//                            table.setWidths(headerwidths);
//
//                            table.setWidthPercentage((100));
//
//                            table.setHeaderRows(3);
//
//                            //table.getDefaultCell().setColspan(33);
//                            Phrase phrase = new Phrase("");
//                            // for (int x = 0; x < rangeDates.length; x++) {
//                            try {
//                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
//
//                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
//
//                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
//
//                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");
//
//                                java.lang.String monthString = dateFormatter.getDateString();
//
//                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");
//
//                                java.lang.String yearString = dateFormatters.getDateString();
//
//                            } catch (java.text.ParseException psExec) {
//
//                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
//
//                            }
//
//                            table.getDefaultCell().setColspan(2);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Departments", pFontHeader111);
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(1);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//
//                            for (int x = 0; x < 1; x++) {
//
//                                try {
//
//                                    // Date parser
//                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "dd");
//
//                                    java.lang.String monthString = dateFormatter.getDateString();
//
//                                    int days = 1;
//                                    //  if (x < 1) {
//
//                                    //       com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "dd");
//                                    //       java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
//                                    //       phrase = new Phrase(monthStringCurrent ,pFontHeader);
//                                    //  } else {
//                                    phrase = new Phrase(monthString, pFontHeader);
//                                    //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
//                                    interval = x;
//                                    // }
//
//                                    table.addCell(phrase);
//
//                                    // Catch java.text.parse exception.
//                                } catch (java.text.ParseException prs) {
//                                    prs.printStackTrace();
//                                }
//                            }
//
//                            phrase = new Phrase("TOTAL", pFontHeader);
//                            table.addCell(phrase);
//
//                            try {
//
//                                double GrandTotal = 0.00;
//                                double Over120Total = 0.00;
//                                java.lang.Object[] listofAct = this.getListofActivities();
//
//                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
//                                System.out.println(listofAct.length);
//                                int firstVisit = 0;
//                                int newCases = 0;
//                                int reVisit = 0;
//                                int referralIn = 0;
//                                int referralOut = 0;
//                                for (int i = 0; i < listofAct.length; i++) {
//                                    double tot=0.0;
//                                    double TurnOver = 0.00;
//                                    double Over120 = 0.00;
//                                    int TotalCount = 0;
//
//                                    table.getDefaultCell().setColspan(1);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    table.getDefaultCell().setColspan(1);
//                                    java.sql.Statement stmta1 = connectDB.createStatement();
//                                    
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                    seq = seq + 1;
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase("" + seq, pFontHeader1);
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//
//                                    System.err.println("   Processing department " + listofAct[i].toString());
//                                    // }
//                                    for (int t = 0; t < 1; t++) {
//                                        System.err.println("      Processing date " + rangeDates[t][0]);
//                                        java.sql.Statement st01 = connectDB.createStatement();
//                                        java.sql.Statement st1 = connectDB.createStatement();
//                                        Double dateTotal = 0.00;
//                                        int deptTotal = 0;
//
//                                        java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT   count(*) FROM public.hp_patient_card where  main_service ilike '" + listofAct[i].toString() + "'   and service not ilike '%invoice%'  AND date::date BETWEEN '2017-05-11' AND '2017-05-11' ");
//
//                                        java.sql.ResultSet rsetv = psete.executeQuery();
//
//                                        while (rsetv.next()) {
//                                            deptTotal = rsetv.getInt(1);
//
//                                        }
//
////                                        if (deptTotal > 0) {
//
//                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '2017-05-11' AND '2017-05-11'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            System.err.println("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '2017-05-11' AND '2017-05-11'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");
////                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '2017-05-11' AND '2017-05-11' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            while (rsetTotals1.next()) {
//                                                Double debtortotal = 0.00;
//                                                java.sql.PreparedStatement v = connectDB.prepareStatement("  select sum(debit-credit) from ac_debtors WHERE invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p = v.executeQuery();
//                                                while (p.next()) {
//                                                    debtortotal = p.getDouble(1);
//                                                }
//                                                Double billtotal = 0.00;
//                                                java.sql.PreparedStatement v1 = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where   service not ilike '%invoice%' and invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p1 = v1.executeQuery();
//                                                while (p1.next()) {
//                                                    billtotal = p1.getDouble(1);
//                                                }
//                                                if (debtortotal - billtotal == 0) {
//                                                    tot=tot+debtortotal;
//                                                    System.err.println("        Processing invoice number " + rsetTotals1.getString(1)+ " - "+debtortotal +"-"+tot);
//                                                    
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '2017-05-11' AND '2017-05-11' and scheme ilike '"+sch+"' ");
//                                                    java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' -- AND date::date BETWEEN '2017-05-11' AND '2017-05-11' ");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                    java.sql.ResultSet rset = pset.executeQuery();
//
//                                                    while (rset.next()) {
//                                                        if (rset.getDouble(1)>0){ System.err.println("Total  " + rset.getDouble(1));}
//                                                        dateTotal = dateTotal + rset.getDouble(1);
//
//                                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                        TotalCount = TotalCount + rset.getInt(1);
//
//                                                    }
//                                                }
//                                            }
//
////                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '2017-05-11' AND '2017-05-11' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
////
////                                            while (rsetTotals1.next()) {
////                                                System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
////
//////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '2017-05-11' AND '2017-05-11' and scheme ilike '"+sch+"' ");
////                                                java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' --AND date::date BETWEEN '2017-05-11' AND '2017-05-11' ");
////
//////                                        pset.setString(1, listofAct[i].toString());
////                                                java.sql.ResultSet rset = pset.executeQuery();
////
////                                                while (rset.next()) {
////                                                    System.err.println("Total  " + rset.getDouble(1));
////                                                    dateTotal = dateTotal + rset.getDouble(1);
////
////                                                    //columnTotals[t] = columnTotals[t] + rset.get(1);
////                                                    TotalCount = TotalCount + rset.getInt(1);
////
////                                                }
////                                            }
////                                        }
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);
//
//                                        table.addCell(phrase);
//
//                                    }
//
//                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
//                                    phrase = new Phrase("" + TotalCount, pFontHeader);
//                                    tot = tot + TotalCount;
//                                    table.addCell(phrase);
//                                }
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(3);
//                                phrase = new Phrase("Total", pFontHeader);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
//                                table.addCell(phrase);
////                                
//
//                                docPdf.add(table);
//
//                            } catch (java.sql.SQLException SqlExec) {
//
//                                SqlExec.printStackTrace();
//
//                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
//
//                            }
//
//                        } catch (com.lowagie.text.BadElementException BadElExec) {
//
//                            // Bad
//                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
//
//                        }
//                    }

                    if (dayInMonth == 31) {
                        try {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length + 3);

                            String headerWidths = null;

                            java.util.Vector headerVector = new java.util.Vector(1, 1);

                            int z = rangeDates.length;

                            int headerwidths[] = {4, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12};//,13,13};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(3);

                            //table.getDefaultCell().setColspan(33);
                            Phrase phrase = new Phrase("");
                            // for (int x = 0; x < rangeDates.length; x++) {
                            try {
                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("SCHEME: " + scheme, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(14);
                                phrase = new Phrase("FACILITY: " + compName, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("MONTH:" + monthString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("YEAR:" + yearString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("", pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(25);

                                //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                // java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
//.toUpperCase() + dateFormat.format(endDate2)
                                phrase = new Phrase("Days of The Month ", pFontHeader111);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(8);
                                phrase = new Phrase("Printed on : " + date, pFontHeader111);
                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Departments", pFontHeader111);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            for (int x = 0; x < rangeDates.length; x++) {

                                try {

                                    // Date parser
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "dd");

                                    java.lang.String monthString = dateFormatter.getDateString();

                                    int days = 1;
                                    //  if (x < 1) {

                                    //       com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "dd");
                                    //       java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                    //       phrase = new Phrase(monthStringCurrent ,pFontHeader);
                                    //  } else {
                                    phrase = new Phrase(monthString, pFontHeader);
                                    //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
                                    interval = x;
                                    // }

                                    table.addCell(phrase);

                                    // Catch java.text.parse exception.
                                } catch (java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                            }

                            phrase = new Phrase("TOTAL", pFontHeader);
                            table.addCell(phrase);

                            try {

                                double GrandTotal = 0.00;
                                double Over120Total = 0.00;
                                java.lang.Object[] listofAct = this.getListofActivities();

                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                System.out.println(listofAct.length);
                                int firstVisit = 0;
                                int newCases = 0;
                                int reVisit = 0;
                                int referralIn = 0;
                                int referralOut = 0;
                                for (int i = 0; i < listofAct.length; i++) {
                                    double TurnOver = 0.00;
                                    double Over120 = 0.00;
                                    int TotalCount = 0;

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    java.sql.Statement stmta1 = connectDB.createStatement();
                                    //   java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
                                    // java.sql.PreparedStatement pset22 = connectDB.prepareStatement("SELECT scrub_nurse FROM hp_patient_diagnosis WHERE scrub_nurse = ? GROUP BY 1");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");

                                    //333 pset22.setString(1, listofAct[i].toString().toUpperCase());
                                    // java.sql.ResultSet rset22 = pset22.executeQuery();
                                    //table.addCell(phrase);
                                    // while (rset22.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    seq = seq + 1;
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("" + seq, pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    System.err.println("   Processing department " + listofAct[i].toString());
                                    // }
                                    for (int t = 0; t < rangeDates.length; t++) {
                                        System.err.println("      Processing date " + rangeDates[t][0]);
                                        java.sql.Statement st01 = connectDB.createStatement();
                                        java.sql.Statement st1 = connectDB.createStatement();
                                        Double dateTotal = 0.00;
                                        int deptTotal = 0;

                                        //java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT   count(*) FROM public.hp_patient_card where  main_service ilike '" + listofAct[i].toString() + "'   and service not ilike '%invoice%'  AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");

                                        
                                        
                                               
                                                
                                        java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT * FROM public.funsoft_departmental_credit_sales_per_dept('"+ rangeDates[t][0]+"','"+rangeDates[t][1]+"', '"+sch+"', '"+listofAct[i].toString()+"') order by 1");

                                        java.sql.ResultSet rsetv = psete.executeQuery();

                                        while (rsetv.next()) {
                                            dateTotal = rsetv.getDouble(4);
                                            TotalCount += dateTotal;

                                        } 
                                                
//                                        java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT   count(*) FROM public.hp_patient_card where  main_service ilike '" + listofAct[i].toString() + "'   and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND "
//                                                + "invoice_no IN (select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 ) ");
//
//                                        java.sql.ResultSet rsetv = psete.executeQuery();
//
//                                        while (rsetv.next()) {
//                                            deptTotal = rsetv.getInt(1);
//
//                                        }

//                                        if (deptTotal > 0) {
//
//                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
////                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            while (rsetTotals1.next()) {
//                                                Double debtortotal = 0.00;
//                                                java.sql.PreparedStatement v = connectDB.prepareStatement("  select sum(debit-credit) from ac_debtors WHERE invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p = v.executeQuery();
//                                                while (p.next()) {
//                                                    debtortotal = p.getDouble(1);
//                                                }
//                                                Double billtotal = 0.00;
//                                                java.sql.PreparedStatement v1 = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where   (service not ilike '%invoice%' and service not ilike '%receipt%' ) and invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p1 = v1.executeQuery();
//                                                while (p1.next()) {
//                                                    billtotal = p1.getDouble(1);
//                                                }
//                                                if (debtortotal - billtotal == 0) {
//
//                                                    System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                                    java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' -- AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                    java.sql.ResultSet rset = pset.executeQuery();
//
//                                                    while (rset.next()) {
//                                                        System.err.println("Total  " + rset.getDouble(1));
//                                                        dateTotal = dateTotal + rset.getDouble(1);
//
//                                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                        TotalCount = TotalCount + rset.getInt(1);
//
//                                                    }
//                                                }
//                                            }
//                                    }

//                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
//                                            while (rsetTotals1.next()) {
//                                                System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                                java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' --AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                java.sql.ResultSet rset = pset.executeQuery();
//
//                                                while (rset.next()) {
//                                                    System.err.println("Total  " + rset.getDouble(1));
//                                                    dateTotal = dateTotal + rset.getDouble(1);
//
//                                                    //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                    TotalCount = TotalCount + rset.getInt(1);
//
//                                                }
//                                            }
//                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);

                                        table.addCell(phrase);

                                    }

                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
                                    phrase = new Phrase("" + TotalCount, pFontHeader);
                                    tot = tot + TotalCount;
                                    table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                seq = seq + 1;
                                table.getDefaultCell().setColspan(32);
                                phrase = new Phrase("Total", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
                                table.addCell(phrase);
//                                

                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        } catch (com.lowagie.text.BadElementException BadElExec) {

                            // Bad
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                        }
                    }
                    if (dayInMonth == 30) {
                        try {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(33);

                            String headerWidths = null;

                            java.util.Vector headerVector = new java.util.Vector(1, 1);

                            int z = rangeDates.length;

                            int headerwidths[] = {4, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12};//,13,13};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(3);

                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(33);
                            Phrase phrase = new Phrase("");
                            //table.getDefaultCell().setColspan(24);
                            try {
                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("SCHEME: " + scheme, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(13);
                                phrase = new Phrase("FACILITY: " + compName, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("MONTH:" + monthString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("YEAR:" + yearString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("", pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(24);

                                //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                // java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
//.toUpperCase() + dateFormat.format(endDate2)
                                phrase = new Phrase("Days of The Month ", pFontHeader111);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(8);
                                phrase = new Phrase("Printed on : " + date, pFontHeader111);
                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }
                            // }

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Departments", pFontHeader111);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            for (int x = 1; x < 31; x++) {

                                try {

                                    // Date parser
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "dd");

                                    java.lang.String monthString = dateFormatter.getDateString();

                                    int days = 1;
                                    //  if (x < 1) {

                                    //       com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "dd");
                                    //       java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                    //       phrase = new Phrase(monthStringCurrent ,pFontHeader);
                                    //  } else {
                                    phrase = new Phrase(monthString, pFontHeader);
                                    //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
                                    interval = x;
                                    // }

                                    table.addCell(phrase);

                                    // Catch java.text.parse exception.
                                } catch (java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                            }

                            phrase = new Phrase("TOTAL", pFontHeader111);
                            table.addCell(phrase);

                            try {

                                double GrandTotal = 0.00;
                                double Over120Total = 0.00;
                                java.lang.Object[] listofAct = this.getListofActivities();

                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                System.out.println(listofAct.length);
                                int firstVisit = 0;
                                int newCases = 0;
                                int reVisit = 0;
                                int referralIn = 0;
                                int referralOut = 0;

                                for (int i = 0; i < listofAct.length; i++) {
                                    double TurnOver = 0.00;
                                    double Over120 = 0.00;
                                    int TotalCount = 0;

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    java.sql.Statement stmta1 = connectDB.createStatement();
                                    //   java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
                                    // java.sql.PreparedStatement pset22 = connectDB.prepareStatement("SELECT scrub_nurse FROM hp_patient_diagnosis WHERE scrub_nurse = ? GROUP BY 1");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");

                                    //333 pset22.setString(1, listofAct[i].toString().toUpperCase());
                                    // java.sql.ResultSet rset22 = pset22.executeQuery();
                                    //table.addCell(phrase);
                                    // while (rset22.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    seq = seq + 1;
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("" + seq, pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    System.err.println("   Processing department " + listofAct[i].toString());
                                    // }
//                                    for (int t = 0; t < 30; t++) {
                                    for (int t = 1; t < 31; t++) {
                                        System.err.println("      Processing date " + rangeDates[t][0]);
                                        java.sql.Statement st01 = connectDB.createStatement();
                                        java.sql.Statement st1 = connectDB.createStatement();
                                        Double dateTotal = 0.00;
                                        int deptTotal = 0;

//                                        java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT   count(*) FROM public.hp_patient_card where  main_service ilike '" + listofAct[i].toString() + "'   and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND "
//                                                + "invoice_no IN (select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 ) ");
////                                        System.err.println("SELECT   count(*) FROM public.hp_patient_card where  main_service ilike '" + listofAct[i].toString() + "'   and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND "
////                                                + "invoice_no IN (select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 )");
//
//                                        java.sql.ResultSet rsetv = psete.executeQuery();
//
//                                        while (rsetv.next()) {
//                                            deptTotal = rsetv.getInt(1);
//
//                                        }
//
//                                        if (deptTotal > 0) {
//
//                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            System.err.println("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");
////                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            while (rsetTotals1.next()) {
//                                                Double debtortotal = 0.00;
//                                                java.sql.PreparedStatement v = connectDB.prepareStatement("  select sum(debit-credit) from ac_debtors WHERE invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                System.err.println("select sum(debit-credit) from ac_debtors WHERE invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p = v.executeQuery();
//                                                while (p.next()) {
//                                                    debtortotal = p.getDouble(1);
//                                                }
//                                                Double billtotal = 0.00;
//                                                java.sql.PreparedStatement v1 = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where   (service not ilike '%invoice%' and service not ilike '%receipt%' )  and invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                System.err.println("SELECT   sum(debit- credit) FROM public.hp_patient_card where   service not ilike '%invoice%' and invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p1 = v1.executeQuery();
//                                                while (p1.next()) {
//                                                    billtotal = p1.getDouble(1);
//                                                }
//                                                if (debtortotal - billtotal == 0) {
//
//                                                    System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                                    java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' -- AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//                                                  //  System.err.println("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' -- AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                    java.sql.ResultSet rset = pset.executeQuery();
//
//                                                    while (rset.next()) {
//                                                        System.err.println("Total  " + rset.getDouble(1));
//                                                        dateTotal = dateTotal + rset.getDouble(1);
//
//                                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                        TotalCount = TotalCount + rset.getInt(1);
//
//                                                    }
//                                                }
//                                            }
//                                        }
                                        
                                        java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT * FROM public.funsoft_departmental_credit_sales_per_dept('"+ rangeDates[t][0]+"','"+rangeDates[t][1]+"', '"+sch+"', '"+listofAct[i].toString()+"') order by 1");

                                        java.sql.ResultSet rsetv = psete.executeQuery();

                                        while (rsetv.next()) {
                                            dateTotal = rsetv.getDouble(4);
                                            TotalCount += dateTotal;

                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);

                                        table.addCell(phrase);

                                    }

                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
                                    phrase = new Phrase("" + TotalCount, pFontHeader);
                                    tot = tot + TotalCount;
                                    table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                seq = seq + 1;
                                table.getDefaultCell().setColspan(31);
                                phrase = new Phrase("Total", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
                                table.addCell(phrase);

//                                for (int i = 0; i < listofAct.length; i++) {
//                                    double TurnOver = 0.00;
//                                    double Over120 = 0.00;
//                                    int TotalCount = 0;
//
//                                    table.getDefaultCell().setColspan(1);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    table.getDefaultCell().setColspan(1);
//                                    java.sql.Statement stmta1 = connectDB.createStatement();
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                    seq = seq + 1;
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase("" + seq, pFontHeader1);
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//                                    Double dateTotal = 0.00;
//
//                                    // }
//                                    System.out.println("Dealer Is : [" + listofAct[i] + "].");
//
//                                    for (int t = 1; t < 31; t++) {
//                                        dateTotal = 0.00;
//                                        java.sql.Statement st01 = connectDB.createStatement();
//
//                                        java.sql.Statement st1 = connectDB.createStatement();
//
//                                        java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
//                                        while (rsetTotals1.next()) {
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                            java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(hp.debit-hp.credit)  FROM public.hp_patient_card hp,ac_debtors where  service not ilike '%invoice%' and main_service='" + listofAct[i].toString() + "' and hp.date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '" + sch + "' and ac_debtors.account_no is not null and hp.invoice_no=ac_debtors.invoice_no ");
////
////                                        pset.setString(1, listofAct[i].toString());
//                                            java.sql.ResultSet rset = pset.executeQuery();
//
////                                        java.sql.PreparedStatement psetn = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND comments = 'New' AND age < 5");//< '"+endDate+"'::date and date < '"+endDate+"'::date - 30 group by dealer");
////                                        java.sql.ResultSet rsetn = psetn.executeQuery();
////
////                                        java.sql.PreparedStatement pseto = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND comments = 'Old' AND age < 5");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
////                                        java.sql.ResultSet rseto = pseto.executeQuery();
//                                            while (rset.next()) {
//                                                dateTotal = dateTotal + rset.getDouble(1);
//
//                                                //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                TotalCount = TotalCount + rset.getInt(1);
//                                            }
//
//                                        }
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);
//
//                                        table.addCell(phrase);
//                                    }
//
//                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
//                                    phrase = new Phrase("" + TotalCount, pFontHeader);
//                                    tot = tot + TotalCount;
//                                    table.addCell(phrase);
//                                }
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(31);
//                                phrase = new Phrase("Total", pFontHeader);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                table.getDefaultCell().setColspan(2);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
//                                table.addCell(phrase);
//                                table.addCell(phrase);
                                //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        } catch (com.lowagie.text.BadElementException BadElExec) {

                            // Bad
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                        }
                    }

                    if (dayInMonth == 29) {
                        try {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(32);

                            String headerWidths = null;

                            java.util.Vector headerVector = new java.util.Vector(1, 1);

                            int z = rangeDates.length;

                            int headerwidths[] = {4, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12};//,13,13};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(2);

                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(32);
                            Phrase phrase = new Phrase("");
                            table.getDefaultCell().setColspan(24);
                            try {
                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("SCHEME: " + scheme, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(12);
                                phrase = new Phrase("FACILITY: " + compName, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("MONTH:" + monthString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("YEAR:" + yearString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase(" ", pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(23);

                                //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                // java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
//.toUpperCase() + dateFormat.format(endDate2)
                                phrase = new Phrase("Days of The Month ", pFontHeader111);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(8);
                                phrase = new Phrase("Printed on : " + date, pFontHeader111);
                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Department", pFontHeader111);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            Double dateTotal = 0.00;

                            for (int x = 2; x < 31; x++) {

                                try {

                                    // Date parser
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "dd");

                                    java.lang.String monthString = dateFormatter.getDateString();

                                    int days = 1;
                                    //  if (x < 1) {

                                    //       com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "dd");
                                    //       java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                    //       phrase = new Phrase(monthStringCurrent ,pFontHeader);
                                    //  } else {
                                    phrase = new Phrase(monthString, pFontHeader);
                                    //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
                                    interval = x;
                                    // }

                                    table.addCell(phrase);

                                    // Catch java.text.parse exception.
                                } catch (java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                            }

                            phrase = new Phrase("TOTAL", pFontHeader);
                            table.addCell(phrase);

                            try {

                                double GrandTotal = 0.00;
                                double Over120Total = 0.00;
                                java.lang.Object[] listofAct = this.getListofActivities();

                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                System.out.println(listofAct.length);
                                int firstVisit = 0;
                                int newCases = 0;
                                int reVisit = 0;
                                int referralIn = 0;
                                int referralOut = 0;
                                for (int i = 0; i < listofAct.length; i++) {
                                    double TurnOver = 0.00;
                                    double Over120 = 0.00;
                                    int TotalCount = 0;

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    seq = seq + 1;
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("" + seq, pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    java.sql.Statement stmta1 = connectDB.createStatement();

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    // }
                                    System.out.println("Dealer Is : [" + listofAct[i] + "].");

                                    for (int t = 2; t < 31; t++) {
                                        dateTotal = 0.00;
                                        java.sql.Statement st01 = connectDB.createStatement();
                                        java.sql.Statement st1 = connectDB.createStatement();

                                        
                                        
//                                        java.sql.ResultSet rsetTotals1 = st1.executeQuery("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
////                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            while (rsetTotals1.next()) {
//                                                Double debtortotal = 0.00;
//                                                java.sql.PreparedStatement v = connectDB.prepareStatement("  select sum(debit-credit) from ac_debtors WHERE invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p = v.executeQuery();
//                                                while (p.next()) {
//                                                    debtortotal = p.getDouble(1);
//                                                }
//                                                Double billtotal = 0.00;
//                                                java.sql.PreparedStatement v1 = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where   service not ilike '%invoice%' and invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p1 = v1.executeQuery();
//                                                while (p1.next()) {
//                                                    billtotal = p1.getDouble(1);
//                                                }
//                                                if (debtortotal - billtotal == 0) {
//
//                                                    System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                                    java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' -- AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                    java.sql.ResultSet rset = pset.executeQuery();
//
//                                                    while (rset.next()) {
//                                                        System.err.println("Total  " + rset.getDouble(1));
//                                                        dateTotal = dateTotal + rset.getDouble(1);
//
//                                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                        TotalCount = TotalCount + rset.getInt(1);
//
//                                                    }
//                                                }
//                                            }
                                            
                                            java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT * FROM public.funsoft_departmental_credit_sales_per_dept('"+ rangeDates[t][0]+"','"+rangeDates[t][1]+"', '"+sch+"', '"+listofAct[i].toString()+"') order by 1");

                                        java.sql.ResultSet rsetv = psete.executeQuery();

                                        while (rsetv.next()) {
                                            dateTotal = rsetv.getDouble(4);
                                            TotalCount += dateTotal;

                                        }
//                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);

                                        table.addCell(phrase);
                                        
//                                        java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
//                                        while (rsetTotals1.next()) {
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                            java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(hp.debit-hp.credit)  FROM public.hp_patient_card hp,ac_debtors where  service not ilike '%invoice%' and main_service='" + listofAct[i].toString() + "' and hp.date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '" + sch + "' and ac_debtors.account_no is not null and hp.invoice_no=ac_debtors.invoice_no ");
//                                            pset.setString(1, listofAct[i].toString());
//                                            java.sql.ResultSet rset = pset.executeQuery();
//
//                                            while (rset.next()) {
//
//                                                dateTotal = dateTotal + rset.getDouble(1);
//                                                //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                                TotalCount = TotalCount + rset.getInt(1);
//                                            }
//                                        }
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);
//
//                                        table.addCell(phrase);
                                    }

                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
                                    phrase = new Phrase("" + TotalCount, pFontHeader);
                                    tot = tot + TotalCount;
                                    table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                seq = seq + 1;
                                table.getDefaultCell().setColspan(30);
                                phrase = new Phrase("Total", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
                                table.addCell(phrase);

                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        } catch (com.lowagie.text.BadElementException BadElExec) {

                            // Bad
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                        }
                    }
                    if (dayInMonth == 28) {
                        try {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(31);

                            String headerWidths = null;

                            java.util.Vector headerVector = new java.util.Vector(1, 1);

                            int z = rangeDates.length;

                            int headerwidths[] = {4, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12};//,13,13};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.setHeaderRows(2);

                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(31);
                            Phrase phrase = new Phrase("");
                            table.getDefaultCell().setColspan(23);
                            try {
                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("SCHEME: " + scheme, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(12);
                                phrase = new Phrase("FACILITY: " + compName, pFontHeader111);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("MONTH:" + monthString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("YEAR:" + yearString.toUpperCase(), pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(3);
                                phrase = new Phrase(" ", pFontHeader111);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(23);

                                //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                                // java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
//.toUpperCase() + dateFormat.format(endDate2)
                                phrase = new Phrase("Days of The Month ", pFontHeader111);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(7);
                                phrase = new Phrase("Printed on : " + date, pFontHeader111);
                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }

                            table.getDefaultCell().setColspan(2);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Departments", pFontHeader111);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            for (int x = 3; x < 31; x++) {

                                try {

                                    // Date parser
                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "dd");

                                    java.lang.String monthString = dateFormatter.getDateString();

                                    int days = 1;
                                    //  if (x < 1) {

                                    //       com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "dd");
                                    //       java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
                                    //       phrase = new Phrase(monthStringCurrent ,pFontHeader);
                                    //  } else {
                                    phrase = new Phrase(monthString, pFontHeader);
                                    //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
                                    interval = x;
                                    // }

                                    table.addCell(phrase);

                                    // Catch java.text.parse exception.
                                } catch (java.text.ParseException prs) {
                                    prs.printStackTrace();
                                }
                            }

                            phrase = new Phrase("TOTAL", pFontHeader);
                            table.addCell(phrase);

                            try {

                                double GrandTotal = 0.00;
                                double Over120Total = 0.00;
                                java.lang.Object[] listofAct = this.getListofActivities();

                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                                System.out.println(listofAct.length);
                                int firstVisit = 0;
                                int newCases = 0;
                                int reVisit = 0;
                                int referralIn = 0;
                                int referralOut = 0;

                                for (int i = 0; i < listofAct.length; i++) {
                                    double TurnOver = 0.00;
                                    double Over120 = 0.00;
                                    int TotalCount = 0;

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    java.sql.Statement stmta1 = connectDB.createStatement();
                                    //   java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
                                    // java.sql.PreparedStatement pset22 = connectDB.prepareStatement("SELECT scrub_nurse FROM hp_patient_diagnosis WHERE scrub_nurse = ? GROUP BY 1");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");

                                    //333 pset22.setString(1, listofAct[i].toString().toUpperCase());
                                    // java.sql.ResultSet rset22 = pset22.executeQuery();
                                    //table.addCell(phrase);
                                    // while (rset22.next()){
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    seq = seq + 1;
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("" + seq, pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);

                                    System.err.println("   Processing department " + listofAct[i].toString());
                                    // }
//                                    for (int t = 0; t < 28; t++) {
                                    for (int t = 3; t < 31; t++) {
                                        System.err.println("      Processing date " + rangeDates[t][0]);
                                        java.sql.Statement st01 = connectDB.createStatement();
                                        java.sql.Statement st1 = connectDB.createStatement();
                                        Double dateTotal = 0.00;
                                        int deptTotal = 0;

//                                        java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT   count(*) FROM public.hp_patient_card where  main_service ilike '" + listofAct[i].toString() + "'   and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
//                                        java.sql.ResultSet rsetv = psete.executeQuery();
//
//                                        while (rsetv.next()) {
//                                            deptTotal = rsetv.getInt(1);
//
//                                        }
//                                        
//                                        //-------------------------------------
//                                        java.sql.ResultSet rsetTotals1 = st1.executeQuery("select  distinct ac.invoice_no from ac_debtors ac,hp_patient_card hp WHERE ac.date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'  AND ac.payee ilike '" + sch + "' AND ac.debit > 0 and ac.invoice_no=hp.invoice_no and hp.debit>0 order by 1");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
////                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//                                            while (rsetTotals1.next()) {
//                                                Double debtortotal = 0.00;
//                                                java.sql.PreparedStatement v = connectDB.prepareStatement("  select sum(debit-credit) from ac_debtors WHERE invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p = v.executeQuery();
//                                                while (p.next()) {
//                                                    debtortotal = p.getDouble(1);
//                                                }
//                                                Double billtotal = 0.00;
//                                                java.sql.PreparedStatement v1 = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where   service not ilike '%invoice%' and invoice_no='" + rsetTotals1.getString(1) + "'");
//                                                java.sql.ResultSet p1 = v1.executeQuery();
//                                                while (p1.next()) {
//                                                    billtotal = p1.getDouble(1);
//                                                }
//                                                if (debtortotal - billtotal == 0) {
//
//                                                    System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                                    java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' -- AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                    java.sql.ResultSet rset = pset.executeQuery();
//
//                                                    while (rset.next()) {
//                                                        System.err.println("Total  " + rset.getDouble(1));
//                                                        dateTotal = dateTotal + rset.getDouble(1);
//
//                                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                        TotalCount = TotalCount + rset.getInt(1);
//
//                                                    }
//                                                }
//                                            }
                                            
                                            java.sql.PreparedStatement psete = connectDB.prepareStatement("SELECT * FROM public.funsoft_departmental_credit_sales_per_dept('"+ rangeDates[t][0]+"','"+rangeDates[t][1]+"', '"+sch+"', '"+listofAct[i].toString()+"') order by 1");

                                        java.sql.ResultSet rsetv = psete.executeQuery();

                                        while (rsetv.next()) {
                                            dateTotal = rsetv.getDouble(4);
                                            TotalCount += dateTotal;

                                        }
//                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);

                                        table.addCell(phrase);
                                        
                                        //////-----------------------
                                        
                                        
                                        
                                        
                                        

//                                        if (deptTotal > 0) {

//                                            java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
//                                            while (rsetTotals1.next()) {
//                                                System.err.println("        Processing invoice number " + rsetTotals1.getString(1));
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                                java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        pset.setString(1, listofAct[i].toString());
//                                                java.sql.ResultSet rset = pset.executeQuery();
//
//                                                while (rset.next()) {
//                                                    System.err.println("Total  " + rset.getDouble(1));
//                                                    dateTotal = dateTotal + rset.getDouble(1);
//
//                                                    //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                    TotalCount = TotalCount + rset.getInt(1);
//
//                                                }
//                                            }
////                                        }
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);
//
//                                        table.addCell(phrase);

                                    }

                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
                                    phrase = new Phrase("" + TotalCount, pFontHeader);
                                    tot = tot + TotalCount;
                                    table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                seq = seq + 1;
                                table.getDefaultCell().setColspan(29);
                                phrase = new Phrase("Total", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
                                table.addCell(phrase);

//                                for (int i = 0; i < listofAct.length; i++) {
//                                    double TurnOver = 0.00;
//                                    double Over120 = 0.00;
//                                    int TotalCount = 0;
//
//                                    table.getDefaultCell().setColspan(1);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    table.getDefaultCell().setColspan(1);
//                                    java.sql.Statement stmta1 = connectDB.createStatement();
//                                    //   java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                    seq = seq + 1;
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase("" + seq, pFontHeader1);
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//
//                                    // }
//                                    System.out.println("Dealer Is : [" + listofAct[i] + "].");
//                                    Double dateTotal = 0.00;
//
//                                    for (int t = 3; t < 31; t++) {
//                                        dateTotal = 0.00;
//                                        java.sql.Statement st01 = connectDB.createStatement();
//                                        java.sql.Statement st1 = connectDB.createStatement();
//
//                                        java.sql.ResultSet rsetTotals1 = st1.executeQuery("select invoice_no from ac_debtors where payee = '" + sch + "' AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by date");// UNION select pd.date::date,initcap(pd.scheme_staff_no), (sh.first_name||' '||sh.second_name||' '||sh.last_name) as name,pd.reference,sum(pd.credit),pd.patient_no from hp_patient_card pd,hp_inpatient_register sh where pd.patient_no = sh.patient_no and pd.isurer = '"+memNo+"' AND pd.date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' and pd.credit > 0 group by pd.date::date,pd.scheme_staff_no, name,pd.reference,pd.invoice_no,pd.patient_no order by pd.invoice_no");
//
//                                        while (rsetTotals1.next()) {
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '"+sch+"' ");
//                                            java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT   sum(debit- credit) FROM public.hp_patient_card where main_service ilike '" + listofAct[i].toString() + "' and invoice_no='" + rsetTotals1.getString(1) + "'  and service not ilike '%invoice%' AND date::date BETWEEN '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
//
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(debit-credit)  FROM public.hp_patient_card where  service not ilike '%invoice%' and invoice_no in (select invoice_no from ac_debtors) and main_service='" + listofAct[i].toString() + "' and date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' ");
////                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT  sum(hp.debit-hp.credit)  FROM public.hp_patient_card hp,ac_debtors where  service not ilike '%invoice%' and main_service='" + listofAct[i].toString() + "' and hp.date  between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' and scheme ilike '" + sch + "' and ac_debtors.account_no is not null and hp.invoice_no=ac_debtors.invoice_no ");
////                                        pset.setString(1, listofAct[i].toString());
//                                            java.sql.ResultSet rset = pset.executeQuery();
//
//                                            while (rset.next()) {
//
//                                                //columnTotals[t] = columnTotals[t] + rset.get(1);
//                                                dateTotal = dateTotal + rset.getDouble(1);
//                                                TotalCount = TotalCount + rset.getInt(1);
//                                            }
//                                        }
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(dateTotal, "0.00"), pFontHeader1);
//
//                                        table.addCell(phrase);
//
//                                    }
//
//                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
//                                    phrase = new Phrase("" + TotalCount, pFontHeader);
//                                    tot = tot + TotalCount;
//                                    table.addCell(phrase);
//                                }
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(29);
//                                phrase = new Phrase("Total", pFontHeader);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                table.getDefaultCell().setColspan(2);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(tot)), pFontHeader);
//                                table.addCell(phrase);
                                //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        } catch (com.lowagie.text.BadElementException BadElExec) {

                            // Bad
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                        }
                    }
//                    if (dayInMonth == 1) {
//                        try {
//
//                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(31);
//
//                            String headerWidths = null;
//
//                            java.util.Vector headerVector = new java.util.Vector(1, 1);
//
//                            int z = rangeDates.length;
//
//                            int headerwidths[] = {5, 42, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12};//,13,13};
//
//                            table.setWidths(headerwidths);
//
//                            table.setWidthPercentage((100));
//
//                            table.setHeaderRows(2);
//
//                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                            table.getDefaultCell().setColspan(31);
//                            Phrase phrase = new Phrase("");
//                            table.getDefaultCell().setColspan(23);
//                            try {
//                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
//
//                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
//
//                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
//
//                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");
//
//                                java.lang.String monthString = dateFormatter.getDateString();
//
//                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");
//
//                                java.lang.String yearString = dateFormatters.getDateString();
//                                table.getDefaultCell().setColspan(7);
//                                phrase = new Phrase("DISTRICT: " + district, pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(12);
//                                phrase = new Phrase("FACILITY: " + compName, pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(5);
//                                phrase = new Phrase("MONTH:" + monthString.toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(4);
//                                phrase = new Phrase("YEAR:" + yearString.toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(3);
//                                phrase = new Phrase("M.O.H.705A", pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase("");
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(23);
//
//                                //java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
//                                // java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());
////.toUpperCase() + dateFormat.format(endDate2)
//                                phrase = new Phrase("Days of The Month ", pFontHeader111);
//
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                table.getDefaultCell().setColspan(7);
//                                phrase = new Phrase("Printed on : " + date, pFontHeader111);
//                                table.addCell(phrase);
//                            } catch (java.text.ParseException psExec) {
//
//                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
//
//                            }
//
//                            table.getDefaultCell().setColspan(29);
//
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                            phrase = new Phrase("Diseases(New cases only)", pFontHeader111);
//                            table.addCell(phrase);
//
//                            table.getDefaultCell().setColspan(1);
//                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//
//                            for (int x = 30; x < 31; x++) {
//
//                                try {
//
//                                    // Date parser
//                                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//                                    com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(rangeDates[x][0].toString().trim()), "dd");
//
//                                    java.lang.String monthString = dateFormatter.getDateString();
//
//                                    int days = 1;
//                                    //  if (x < 1) {
//
//                                    //       com.afrisoftech.lib.DateFormatter dateFormatterCurrent = new com.afrisoftech.lib.DateFormatter(endDate, "dd");
//                                    //       java.lang.String monthStringCurrent = dateFormatterCurrent.getDateString();
//                                    //       phrase = new Phrase(monthStringCurrent ,pFontHeader);
//                                    //  } else {
//                                    phrase = new Phrase(monthString, pFontHeader);
//                                    //                                phrase = new Phrase("+ "+x*days +" Month",pFontHeader);
//                                    interval = x;
//                                    // }
//
//                                    table.addCell(phrase);
//
//                                    // Catch java.text.parse exception.
//                                } catch (java.text.ParseException prs) {
//                                    prs.printStackTrace();
//                                }
//                            }
//
//                            phrase = new Phrase("TOTAL", pFontHeader);
//                            table.addCell(phrase);
//
//                            try {
//
//                                double GrandTotal = 0.00;
//                                double Over120Total = 0.00;
//                                java.lang.Object[] listofAct = this.getListofActivities();
//
//                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
//                                System.out.println(listofAct.length);
//                                int firstVisit = 0;
//                                int newCases = 0;
//                                int reVisit = 0;
//                                int referralIn = 0;
//                                int referralOut = 0;
//                                for (int i = 0; i < listofAct.length; i++) {
//                                    double TurnOver = 0.00;
//                                    double Over120 = 0.00;
//                                    int TotalCount = 0;
//
//                                    table.getDefaultCell().setColspan(1);
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    table.getDefaultCell().setColspan(1);
//                                    java.sql.Statement stmta1 = connectDB.createStatement();
//                                    //   java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
//
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                    seq = seq + 1;
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase("" + seq, pFontHeader1);
//                                    table.addCell(phrase);
//                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                    table.getDefaultCell().setColspan(28);
//                                    phrase = new Phrase(listofAct[i].toString().toUpperCase(), pFontHeader1);
//                                    table.addCell(phrase);
//
//                                    // }
//                                    System.out.println("Dealer Is : [" + listofAct[i] + "].");
//
//                                    table.getDefaultCell().setColspan(1);
//
//                                    for (int t = 30; t < 31; t++) {
//                                        java.sql.Statement st01 = connectDB.createStatement();
//
//                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT count(scrub_nurse) from hp_patient_diagnosis where scrub_nurse = ?  AND date_discharged between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND pat_age < 5 AND pat_category = 'OP'");//< '"+endDate+"'::date and date < '"+endDate+"'::date - 30 group by dealer");
//
//                                        pset.setString(1, listofAct[i].toString());
//                                        java.sql.ResultSet rset = pset.executeQuery();
//
//                                        java.sql.PreparedStatement psetn = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND comments = 'New' AND age < 5");//< '"+endDate+"'::date and date < '"+endDate+"'::date - 30 group by dealer");
//                                        java.sql.ResultSet rsetn = psetn.executeQuery();
//
//                                        java.sql.PreparedStatement pseto = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "' AND comments = 'Old' AND age < 5");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                        java.sql.ResultSet rseto = pseto.executeQuery();
//
//                                        while (rset.next()) {
//
//                                            table.getDefaultCell().setColspan(1);
//                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                            phrase = new Phrase(dbObject.getDBObject(rset.getString(1), "-"), pFontHeader1);
//
//                                            table.addCell(phrase);
//                                            //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                            TotalCount = TotalCount + rset.getInt(1);
//                                        }
//
//                                    }
//
//                                    //phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(TotalCount)), pFontHeader);
//                                    phrase = new Phrase("" + TotalCount, pFontHeader);
//                                    table.addCell(phrase);
//                                }
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase("" + seq, pFontHeader1);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                table.getDefaultCell().setColspan(28);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                phrase = new Phrase("TOTAL NEW CASES".toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(1);
//                                for (int g = 30; g < 31; g++) {
//
//                                    java.sql.PreparedStatement psetns = connectDB.prepareStatement("SELECT count(scrub_nurse) from hp_patient_diagnosis WHERE date_discharged between '" + rangeDates[g][0] + "' AND '" + rangeDates[g][1] + "'  AND pat_age < 5 AND pat_category = 'OP'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                    java.sql.ResultSet rsetns = psetns.executeQuery();
//
//                                    while (rsetns.next()) {
//
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(rsetns.getString(1), "-"), pFontHeader111);
//
//                                        table.addCell(phrase);
//                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                        newCases = newCases + rsetns.getInt(1);
//                                    }
//
//                                }
//                                phrase = new Phrase("" + newCases, pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase("" + seq, pFontHeader1);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                table.getDefaultCell().setColspan(28);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                phrase = new Phrase("No. of First attendances".toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(1);
//                                for (int n = 30; n < 31; n++) {
//
//                                    java.sql.PreparedStatement psetn = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[n][0] + "' AND '" + rangeDates[n][1] + "' AND comments = 'New' AND age < 5");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                    java.sql.ResultSet rsetn = psetn.executeQuery();
//
//                                    while (rsetn.next()) {
//
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(rsetn.getString(1), "-"), pFontHeader111);
//
//                                        table.addCell(phrase);
//                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                        firstVisit = firstVisit + rsetn.getInt(1);
//                                    }
//
//                                }
//                                phrase = new Phrase("" + firstVisit, pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase("" + seq, pFontHeader1);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                table.getDefaultCell().setColspan(28);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                phrase = new Phrase("Re-Attendances".toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(1);
//                                for (int r = 30; r < 31; r++) {
//
//                                    java.sql.PreparedStatement psetr = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[r][0] + "' AND '" + rangeDates[r][1] + "' AND comments = 'Old' AND age < 5");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                    java.sql.ResultSet rsetr = psetr.executeQuery();
//
//                                    while (rsetr.next()) {
//
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(rsetr.getString(1), "-"), pFontHeader111);
//
//                                        table.addCell(phrase);
//                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                        reVisit = reVisit + rsetr.getInt(1);
//                                    }
//
//                                }
//                                phrase = new Phrase("" + reVisit, pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase("" + seq, pFontHeader1);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                table.getDefaultCell().setColspan(28);
//                                phrase = new Phrase("Referrals in".toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(1);
//                                for (int s = 30; s < 31; s++) {
//
//                                    java.sql.PreparedStatement psetr = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[s][0] + "' AND '" + rangeDates[s][1] + "' AND comments = 'Ref In' AND age < 5");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                    java.sql.ResultSet rsetr = psetr.executeQuery();
//
//                                    while (rsetr.next()) {
//
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(rsetr.getString(1), "-"), pFontHeader111);
//
//                                        table.addCell(phrase);
//                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                        referralIn = referralIn + rsetr.getInt(1);
//                                    }
//
//                                }
//                                phrase = new Phrase("" + referralIn, pFontHeader111);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                seq = seq + 1;
//                                table.getDefaultCell().setColspan(1);
//                                phrase = new Phrase("" + seq, pFontHeader1);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                table.getDefaultCell().setColspan(28);
//                                phrase = new Phrase("Referrals out".toUpperCase(), pFontHeader111);
//                                table.addCell(phrase);
//                                table.getDefaultCell().setColspan(1);
//                                for (int o = 30; o < 31; o++) {
//
//                                    java.sql.PreparedStatement psetr = connectDB.prepareStatement("SELECT count(patient_no) FROM hp_patient_visit WHERE date between '" + rangeDates[o][0] + "' AND '" + rangeDates[o][1] + "' AND comments = 'Ref out' AND age < 5");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                    java.sql.ResultSet rsetr = psetr.executeQuery();
//
//                                    while (rsetr.next()) {
//
//                                        table.getDefaultCell().setColspan(1);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
//                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(1)),pFontHeader1);
//                                        phrase = new Phrase(dbObject.getDBObject(rsetr.getString(1), "-"), pFontHeader111);
//
//                                        table.addCell(phrase);
//                                        //columnTotals[t] = columnTotals[t] + rset.get(1);
//
//                                        referralOut = referralOut + rsetr.getInt(1);
//                                    }
//
//                                }
//                                phrase = new Phrase("" + referralOut, pFontHeader111);
//                                table.addCell(phrase);
//                                //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//
//                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
//                                docPdf.add(table);
//
//                            } catch (java.sql.SQLException SqlExec) {
//
//                                SqlExec.printStackTrace();
//
//                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
//
//                            }
//
//                        } catch (com.lowagie.text.BadElementException BadElExec) {
//
//                            // Bad
//                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());
//
//                        }
//                    }

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

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT  DISTINCT upper(trim(main_service))  FROM public.hp_patient_card WHERE  date between '"+beginDate+"' and '"+endDate+"'  and main_service is not null and main_service not ilike '' and upper(trim(main_service)) IN (SELECT upper(activity) from pb_activity where activity_category ilike '%I%') ORDER BY 1");

            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            while (rSet1.next()) {
                if (rSet1.getObject(1) != null) {
                    System.out.println(rSet1.getObject(1).toString());
                    listActVector.addElement(rSet1.getObject(1).toString());
                }

            }

            //System.out.println(rSet1.getObject(1).toString());
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
