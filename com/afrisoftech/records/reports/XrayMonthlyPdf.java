//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class XrayMonthlyPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    int numberSeq = 0;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void XrayMonthlyPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

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

            //com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String District = null;
                    String Region = null;

                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch,region FROM pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                            Region = rset2.getObject(3).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        // com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        // headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //headerFoter.setRight(5);
                        //docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    int dayT = 0;
                    int monT = 0;
                    int cashT = 0;
                    int schemeT = 0;
                    int selfT = 0;
                    int copT = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(9);

                        int headerwidths[] = {28, 15, 10, 10, 10, 10, 10, 10, 12};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths1[] = {6, 30, 10, 10};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        table1.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths2[] = {30, 10, 10, 10, 10, 10, 10};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));

                        table2.setHeaderRows(1);
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(6);

                        table.getDefaultCell().setMinimumHeight(15);

                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            try {
                                java.sql.Statement stF = connectDB.createStatement();
                                java.sql.Statement stxF = connectDB.createStatement();
                                java.sql.Statement st1F = connectDB.createStatement();
                                java.sql.Statement st1xFF = connectDB.createStatement();

                                java.sql.Statement st1xF = connectDB.createStatement();
                                java.sql.Statement stxFF = connectDB.createStatement();

                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                                //java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                //java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                //System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(9);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase("Printed On  :" + date, pFontHeader);

                                //java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString().trim()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString().trim()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();

                                // table.addCell(phrase);

                                // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);

                                //table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setColspan(9);

                                phrase = new Phrase("X-RAY DEPARTMENT MONTHLY/ANNUAL REPORT", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);

                                //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" ", pFontHeader);
                                // table.addCell(phrase);
                                phrase = new Phrase("NAME OF HOSPITAL : " + compName, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("RETURN FOR THE MONTH OF " + monthString + " " + yearString, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("District : " + District, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(5);
                                phrase = new Phrase("Region : " + Region, pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                //phrase = new Phrase(""+dateFormat.format(endDate1),pFontHeader1);
                                //table.addCell(phrase);

                                table.getDefaultCell().setColspan(9);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                // phrase = new Phrase("TOTAL NUMBER OF PATIENTS X-RAYED", pFontHeader1);
                                // table.addCell(phrase);


                                java.sql.ResultSet rsetxF = stxF.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND xray_no ILIKE 'X%' AND age::numeric(10,2) < '5' AND age !=''");
                                java.sql.ResultSet rset1xF = st1xF.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND xray_no ILIKE 'X%' AND age::numeric(10,2) >= '5' AND age !=''");

                                java.sql.ResultSet rsetF = stF.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND xray_no ILIKE 'X%' AND (ext_ref ILIKE 'OP%' OR ext_ref ILIKE 'WI%')  AND age !=''");
                                java.sql.ResultSet rset1F = st1F.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ext_ref ILIKE 'IP%' AND xray_no ILIKE 'X%'  AND age !=''");


                                java.sql.ResultSet rsetxFF = stxFF.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND xray_no ILIKE 'X%' AND gender ILIKE 'Male%'  AND age !=''");
                                java.sql.ResultSet rset1xFF = st1xFF.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND xray_no ILIKE 'X%' AND gender ILIKE 'Female%'  AND age !=''");

                                while (rset1xF.next()) {
                                    while (rsetxF.next()) {

                                        phrase = new Phrase("TOTAL NUMBER OF PATIENTS X-RAYED " + (rset1xF.getInt(1) + rsetxF.getInt(1)), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);

                                        phrase = new Phrase("Adult " + rset1xF.getInt(1), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Children " + rsetxF.getInt(1), pFontHeader1);
                                        table.addCell(phrase);


                                        table.getDefaultCell().setColspan(2);
                                        while (rsetF.next()) {
                                            phrase = new Phrase("Outpatient " + rsetF.getInt(1), pFontHeader1);
                                            table.addCell(phrase);
                                        }

                                        // table.getDefaultCell().setColspan(1);
                                        while (rset1F.next()) {
                                            phrase = new Phrase("Inpatient " + rset1F.getInt(1), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                        table.getDefaultCell().setColspan(1);
                                        while (rsetxFF.next()) {
                                            phrase = new Phrase("Male " + rsetxFF.getInt(1), pFontHeader1);
                                            table.addCell(phrase);
                                            //}
                                            table.getDefaultCell().setColspan(2);
                                            // while (rset1xFF.next()) {
                                            phrase = new Phrase("Female " + ((rset1xF.getInt(1) + rsetxF.getInt(1)) - rsetxFF.getInt(1)), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                    }
                                }
                                table.getDefaultCell().setColspan(9);
                                phrase = new Phrase("EXAMINATIONS CARRIED OUT", pFontHeader1);
                                table.addCell(phrase);
                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                            //phrase = new Phrase("Budget Cumm. Adm. for the month",pFontHeader);
                            //table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            java.lang.Object[] listofAct = this.getListofStaffNos();

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stx = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();

                            java.sql.Statement st1x = connectDB.createStatement();
                            for (int x = 0; x < listofAct.length; x++) {
                                int totalMale = 0;
                                int totalFemale = 0;
                                int newMaleTotal = 0;
                                int newFemaleTotal = 0;
                                int oldMaleTotal = 0;
                                int oldFemaleTotal = 0;
                                table.getDefaultCell().setColspan(9);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(listofAct[x].toString().toUpperCase(), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Type of Examination", pFontHeader);
                                table.addCell(phrase);
                                //table.getDefaultCell().setColspan(2);

                                phrase = new Phrase("Total No Of Examination", pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase("O/P", pFontHeader);
                                table.addCell(phrase);

                                // table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("I/P", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Children", pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase("Adults", pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase("Positive Finding", pFontHeader);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Negative Finding", pFontHeader);
                                table.addCell(phrase);

                                phrase = new Phrase("Comments", pFontHeader);
                                table.addCell(phrase);

                                java.lang.Object[] listofAct1 = this.getListofStaffNos1(listofAct[x]);

                                for (int i = 0; i < listofAct1.length; i++) {

                                    // variables declalition
                                    int op = 0;
                                    int ip = 0;
                                    int child = 0;
                                    int adult = 0;
                                    int total = 0;
                                    /*int oldFemale = 0;

                                    String Gender = null;
                                    double lowerAge = 0;
                                    double upperAge = 0;
                                    int patNo1 = 0;
                                    /*java.sql.ResultSet rsetAge = stw.executeQuery("SELECT min_age,max_age FROM patient_age WHERE description ilike '" + listofAct[i] + "'");

                                    while (rsetAge.next()) {
                                    lowerAge = rsetAge.getDouble(1);
                                    upperAge = rsetAge.getDouble(2);
                                    }*/
                                    java.sql.ResultSet rset = st.executeQuery("SELECT COUNT(exam_notes) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND exam_notes ILIKE '" + listofAct1[i] + "' AND (ext_ref ILIKE 'OP' OR ext_ref ILIKE 'WI')  AND age !=''");
                                    java.sql.ResultSet rset1 = st1.executeQuery("SELECT COUNT(exam_notes) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND exam_notes ILIKE '" + listofAct1[i] + "' AND ext_ref ILIKE 'IP'  AND age !=''");

                                    java.sql.ResultSet rsetx = stx.executeQuery("SELECT COUNT(exam_notes) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND exam_notes ILIKE '" + listofAct1[i] + "' AND age <= '5'  AND age !=''");
                                    java.sql.ResultSet rset1x = st1x.executeQuery("SELECT COUNT(exam_notes) FROM hp_xray_results WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND exam_notes ILIKE '" + listofAct1[i] + "' AND age > '5'  AND age !=''");

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("" + listofAct1[i], pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    while (rset.next()) {
                                        op = rset.getInt(1);
                                    }

                                    while (rset1.next()) {
                                        ip = rset1.getInt(1);
                                    }

                                    while (rsetx.next()) {
                                        child = rsetx.getInt(1);
                                    }

                                    while (rset1x.next()) {
                                        adult = rset1x.getInt(1);
                                    }

                                    total = op + ip;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(total)), pFontHeader1);
                                    newMaleTotal = newMaleTotal + total;
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(op)), pFontHeader1);
                                    newFemaleTotal = newFemaleTotal + op;
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(ip)), pFontHeader1);
                                    oldMaleTotal = oldMaleTotal + ip;
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(child)), pFontHeader1);
                                    oldFemaleTotal = oldFemaleTotal + child;
                                    table.addCell(phrase);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(adult)), pFontHeader1);
                                    totalMale = totalMale + adult;
                                    table.addCell(phrase);

                                    phrase = new Phrase("");
                                    table.addCell(phrase);

                                    phrase = new Phrase("");
                                    table.addCell(phrase);

                                    phrase = new Phrase("");
                                    table.addCell(phrase);

                                }

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
                                phrase = new Phrase("Total", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMaleTotal)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemaleTotal)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMaleTotal)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldFemaleTotal)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("");
                                table.addCell(phrase);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                phrase = new Phrase("");
                                table.addCell(phrase);
                                /* table.getDefaultCell().setColspan(7);
                                phrase = new Phrase(" ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(".................................. \n Medical Officer-in-Charge", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("*To be despatched not later than the Tuesday of the month immediately following to the District Medical \n Officer of Health with copies to; \n          1.        REGIONAL DIRECTOR OF HEALTH SERVICES", pFontHeader1);

                                table.addCell(phrase);
                                 */

                            }


                            docPdf.add(table);
                            //  docPdf.add(table1);
                            //  docPdf.add(table2);
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }

                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

             

            //  try {

            /* if (System.getProperty("os.name").equalsIgnoreCase("Linux"))  {

            System.out.println(tempFile);

            wait_for_Pdf2Show = rt.exec("kghostview "+tempFile+"");

            wait_for_Pdf2Show.waitFor();

            } else {*/
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            // wait_for_Pdf2Show = rt.exec("c:/Program Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe "+tempFile);

            // wait_for_Pdf2Show.waitFor();

            // }

            /*     } catch(java.lang.InterruptedException intrExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), intrExec.getMessage());

            }
             */


        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_category FROM pb_lab_maincategory WHERE department ILIKE 'xray' ORDER BY 1");
            //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_admission WHERE discharge = false ORDER BY patient_no");

            while (rSet1.next()) {

                listStaffNoVector.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }


        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }

    public java.lang.Object[] getListofStaffNos1(java.lang.Object category) {

        java.lang.Object[] listofStaffNos1 = null;

        java.util.Vector listStaffNoVector1 = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT category_description FROM pb_lab_maincategory WHERE department ILIKE 'xray' AND main_category ILIKE '" + category + "' ORDER BY 1");

            while (rSet1.next()) {

                listStaffNoVector1.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }


        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
    }
    /*

    public java.lang.Object[] getListofStaffNos11() {

    java.lang.Object[] listofStaffNos11 = null;

    java.util.Vector listStaffNoVector11 = new java.util.Vector(1,1);


    try {

    //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

    java.sql.Statement stmt1 = connectDB.createStatement();

    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT mode_of_payment FROM hp_admission WHERE discharge_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY mode_of_payment");

    while (rSet1.next()) {

    listStaffNoVector11.addElement(rSet1.getObject(1).toString());

    }

    }catch (java.sql.SQLException sqlExec) {

    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

    }


    listofStaffNos11 = listStaffNoVector11.toArray();
    System.out.println("Done list of Staff Nos ...");
    return listofStaffNos11;
    }*/
}
