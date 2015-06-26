//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class MonthlyLabPdf implements java.lang.Runnable {

    double Credit = 0.00;
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

    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader111 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);

    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();

    java.lang.Process prThread;

    public void MonthlyLabPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        //public void OrderedItemsPdf(java.sql.Connection connDb) {
        // Debtor = combox;
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
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
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

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

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader111), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Monthly Report  Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();

                    double Totals = 0.00;
                    double OS = 0.00;
                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(rangeDates.length + 2);

                        String headerWidths = null;

                        java.util.Vector headerVector = new java.util.Vector(1, 1);

                        int z = rangeDates.length;

                        int headerwidths[] = {31, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12};//,13,13};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(33);
                        Phrase phrase = new Phrase("");
                        table.getDefaultCell().setColspan(25);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate2 = dateFormat.parse(endDate.toLocaleString());

                            phrase = new Phrase("Monthly Laboratory Report as at ".toUpperCase() + dateFormat.format(endDate2), pFontHeader111);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(8);
                        phrase = new Phrase("Printed on : " + date, pFontHeader111);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Description".toUpperCase(), pFontHeader111);
                        table.addCell(phrase);

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

                            for (int i = 0; i < listofAct.length; i++) {
                                double TurnOver = 0.00;
                                double Over120 = 0.00;
                                int TotalCount = 0;

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(rangeDates.length + 2);
                                phrase = new Phrase(dbObject.getDBObject(listofAct[i].toString().toUpperCase(), "-"), pFontHeader);
                                table.addCell(phrase);

                                java.sql.Statement stmt1x = connectDB.createStatement();
                                java.sql.PreparedStatement pSet1x = connectDB.prepareStatement("SELECT distinct service_type  FROM pb_operating_parameters where main_service ilike   '" + listofAct[i] + "'  ");
                                java.sql.ResultSet rSet1x = pSet1x.executeQuery();
                                while (rSet1x.next()) {
                                    TotalCount = 0;

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    java.sql.Statement stmta1 = connectDB.createStatement();
                                //   java.sql.PreparedStatement pSeta1 = connectDB.prepareStatement("SELECT count(activity_code) FROM ac_ledger where  activity_code = '"+listofAct[i]+"'");
//                                java.sql.PreparedStatement pset22 = connectDB.prepareStatement("select service_type from pb_operating_parameters WHERE service_type = ? limit 1");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
//                                
//                                pset22.setString(1,listofAct[i].toString().toUpperCase());
//                                java.sql.ResultSet rset22 = pset22.executeQuery();
//                                
//                                while (rset22.next()){
//                                    
//                                    table.getDefaultCell().setColspan(1);
//                                    phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"),pFontHeader1);
//                                    table.addCell(phrase);
//                                    
//                                }

                                    phrase = new Phrase(dbObject.getDBObject(rSet1x.getString(1).toUpperCase(), "-"), pFontHeader1);
                                    table.addCell(phrase);

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
                                    System.out.println("Dealer Is : [" + listofAct[i] + "]..." + rSet1x.getString(1).toUpperCase());

                                    for (int t = 0; t < rangeDates.length; t++) {

                                        java.sql.Statement st01 = connectDB.createStatement();

                                        java.sql.PreparedStatement psetc = connectDB.prepareStatement("SELECT count(service) from hp_patient_card where service  = ? AND date::date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                        psetc.setString(1, rSet1x.getString(1).toUpperCase());
                                        java.sql.ResultSet rsetc = psetc.executeQuery();

                                        java.sql.PreparedStatement pset = connectDB.prepareStatement("SELECT count(description) from ac_cash_collection where description ilike  ?  AND date between '" + rangeDates[t][0] + "' AND '" + rangeDates[t][1] + "'");//< '"+endDate+"'::date and date > '"+endDate+"'::date - 30 group by dealer");
                                        pset.setString(1, rSet1x.getString(1).toUpperCase());
                                        java.sql.ResultSet rset = pset.executeQuery();

                                        int outPatient = 0;
                                        int inPatient = 0;
                                        int dayTotal = 0;

                                        while (rsetc.next()) {
                                            inPatient = rsetc.getInt(1);
                                            System.out.println("Inpatient Total For  " + rSet1x.getString(1).toUpperCase() + "  " + inPatient);
                                        }

                                        while (rset.next()) {
                                            outPatient = rset.getInt(1);
                                            System.out.println("Outpatient Total For  " + rSet1x.getString(1).toUpperCase() + "  " + outPatient);

                                        }
                                        dayTotal = outPatient + inPatient;
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(dbObject.getDBObject(dayTotal, "-"), pFontHeader1);

                                        System.out.println("Daily Total For  " + rSet1x.getString(1).toUpperCase() + "  " + dayTotal);
                                        table.addCell(phrase);
                                        TotalCount = TotalCount + dayTotal;

                                    }

                                    System.out.println("Monthly Total For  " + rSet1x.getString(1).toUpperCase() + "  " + TotalCount);
                                    phrase = new Phrase("" + TotalCount, pFontHeader);
                                    table.addCell(phrase);

                                }
                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

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

            java.sql.PreparedStatement pSet1 = connectDB.prepareStatement("SELECT distinct main_service  FROM pb_operating_parameters where main_service ilike '%lab%' ");

            java.sql.ResultSet rSet1 = pSet1.executeQuery();

            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector.addElement(rSet1.getObject(1).toString());

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
