//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MonthlyOPWorkLoadPdf implements java.lang.Runnable {

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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MonthlyOPWorkLoadPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

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
                            compName = rset2.getString(1);
                            District = rset2.getString(2);
                            Region = rset2.getString(3);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

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

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);

                        int headerwidths[] = {6, 40, 10, 15, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths1[] = {6, 30, 10, 10};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        table1.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(8);

                        int headerwidths2[] = {7, 30, 10, 10, 10, 20, 10, 10};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));

                        table2.setHeaderRows(1);
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(5);

                        Phrase phrase = new Phrase("", pFontHeader);

                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MONTHLY WORKLOAD REPORT FOR HEALTH FACILITIES", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            // table.addCell(phrase);
                            // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                            //table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(3);

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            // table.addCell(phrase);
                            phrase = new Phrase("Institution : " + compName, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("MOH 717", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("District/Sub County : " + District, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Region/County : " + Region, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("" + dateFormat.format(endDate1), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A. OUT-PATIENT SERVICES", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A1 GENERAL OUT-PATIENTS(FILTER CLINICS)", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("NEW", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("RE-ATT.", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("TOTAL", pFontHeader);
                            table.addCell(phrase);

                            /*table.getDefaultCell().setColspan(1);
                             phrase = new Phrase("AGE GROUPS",pFontHeader);
                             table.addCell(phrase);
                            
                             phrase = new Phrase("Male",pFontHeader);
                             table.addCell(phrase);
                            
                             phrase = new Phrase("Female",pFontHeader);
                             table.addCell(phrase);
                            
                             phrase = new Phrase("Male",pFontHeader);
                             table.addCell(phrase);
                            
                             phrase = new Phrase("Female",pFontHeader);
                             table.addCell(phrase);
                            
                             phrase = new Phrase("Male",pFontHeader);
                             table.addCell(phrase);
                            
                             phrase = new Phrase("Female",pFontHeader);
                             table.addCell(phrase);*/
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
                            int totalMale = 0;
                            int totalFemale = 0;
                            int newMaleTotal = 0;
                            int newFemaleTotal = 0;
                            int oldMaleTotal = 0;
                            int oldFemaleTotal = 0;
                            int cFemale = 0;
                            int coldFemale = 0;
//                            java.lang.Object[] listofAct = this.getListofStaffNos();

                            java.sql.Statement stw = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();
                            //  for (int i = 0; i < listofAct.length; i++) {
                            // variables declalition
                            int newCount = 0;
                            int oldCount = 0;
                            int newMale = 0;
                            int newFemale = 0;
                            int oldMale = 0;
                            int oldFemale = 0;

                            String Gender = null;
                            double lowerAge = 0;
                            double upperAge = 0;
                            int patNo1 = 0;
                            /*java.sql.ResultSet rsetAge = stw.executeQuery("SELECT min_age,max_age FROM patient_age WHERE description ilike '"+listofAct[i]+"'");
                            
                             while(rsetAge.next()){
                             lowerAge = rsetAge.getDouble(1);
                             upperAge = rsetAge.getDouble(2);
                             }*/
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            //java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT COUNT(patient_no) from hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND comments ilike 'new' AND TRIM(gender) ilike 'MALE' AND clinic ILIKE 'OPD%'");
                            //java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND comments ilike 'old' AND TRIM(gender) ilike 'MALE' AND clinic ILIKE 'OPD%'");
                            java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT COUNT(patient_no) from hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND comments ilike 'new' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND comments ilike 'old' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            while (rset.next()) {
                                while (rset1.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.1.1", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Over 5-Male", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset.getInt(1);
                                    oldCount = rset1.getInt(1);
                                    newMale = newMale + newCount;
                                    oldMale = oldMale + oldCount;
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement st21 = connectDB.createStatement();
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.ResultSet rset2 = st21.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND comments ilike 'new' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND comments ilike 'old' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            while (rset2.next()) {
                                while (rset12.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.1.2", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Over 5-Female", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset2.getInt(1);
                                    oldCount = rset12.getInt(1);
                                    newFemale = newFemale + rset2.getInt(1);
                                    oldFemale = oldFemale + rset12.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement st212 = connectDB.createStatement();
                            java.sql.Statement st122 = connectDB.createStatement();
                            java.sql.ResultSet rset22 = st212.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'new' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset122 = st122.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'old' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            while (rset22.next()) {
                                while (rset122.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.1.3", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Children Under 5-Male", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset22.getInt(1);
                                    oldCount = rset122.getInt(1);
                                    newMale = newMale + rset22.getInt(1);
                                    oldMale = oldMale + rset122.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement st2121 = connectDB.createStatement();
                            java.sql.Statement st1221 = connectDB.createStatement();
                            java.sql.ResultSet rset221 = st2121.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'new' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset1221 = st1221.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'old' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            while (rset221.next()) {
                                while (rset1221.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.1.4", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Children Under 5-Female", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset221.getInt(1);
                                    oldCount = rset1221.getInt(1);
                                    newFemale = newFemale + rset221.getInt(1);
                                    oldFemale = oldFemale + rset1221.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.1.5", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL GENERAL OUT-PATIENTS", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale + newFemale)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMale + oldFemale)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale + newFemale + oldMale + oldFemale)), pFontHeader);
                            table.addCell(phrase);

                            java.sql.Statement stx = connectDB.createStatement();
                            java.sql.Statement st1x = connectDB.createStatement();
                            java.sql.ResultSet rsetx = stx.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND clinic ILIKE 'Casualty' AND comments ilike 'new'");
                            java.sql.ResultSet rset1x = st1x.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND clinic ILIKE 'Casualty' AND comments ilike 'old'");
                            while (rsetx.next()) {
                                while (rset1x.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.2", pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("CASUALTY", pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsetx.getInt(1);
                                    oldCount = rset1x.getInt(1);
                                    cFemale = cFemale + rsetx.getInt(1);
                                    coldFemale = coldFemale + rset1x.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader);
                                    table.addCell(phrase);
                                }
                            }

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("A3. SPECIAL CLINICS(if recorded separately from \n General Filter Clinics)", pFontHeader);
                            table.addCell(phrase);

                            java.sql.Statement sts = connectDB.createStatement();
                            java.sql.Statement st1s = connectDB.createStatement();
                            java.sql.ResultSet rsets = sts.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%')");
                            java.sql.ResultSet rset1s = st1s.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%')");
                            while (rsets.next()) {
                                while (rset1s.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.1", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("E.N.T Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets.getInt(1);
                                    oldCount = rset1s.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts1 = connectDB.createStatement();
                            java.sql.Statement st1s1 = connectDB.createStatement();
                            java.sql.ResultSet rsets1 = sts1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'Eye%'");
                            java.sql.ResultSet rset1s1 = st1s1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'Eye%'");
                            while (rsets1.next()) {
                                while (rset1s1.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.2", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Eye Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets1.getInt(1);
                                    oldCount = rset1s1.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets1.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s1.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts11 = connectDB.createStatement();
                            java.sql.Statement st1s11 = connectDB.createStatement();
                            java.sql.ResultSet rsets11 = sts11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%')");
                            java.sql.ResultSet rset1s11 = st1s11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%')");
                            while (rsets11.next()) {
                                while (rset1s11.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.3", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("TB and Leprosy", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11.getInt(1);
                                    oldCount = rset1s11.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts111 = connectDB.createStatement();
                            java.sql.Statement st1s111 = connectDB.createStatement();
                            java.sql.ResultSet rsets111 = sts111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%')");
                            java.sql.ResultSet rset1s111 = st1s111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%')");
                            while (rsets111.next()) {
                                while (rset1s111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.4", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Sexually Transmitted Infections", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets111.getInt(1);
                                    oldCount = rset1s111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts1111 = connectDB.createStatement();
                            java.sql.Statement st1s1111 = connectDB.createStatement();
                            java.sql.ResultSet rsets1111 = sts1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%')");
                            java.sql.ResultSet rset1s1111 = st1s1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%')");
                            while (rsets1111.next()) {
                                while (rset1s1111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.5", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Psychiatry", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets1111.getInt(1);
                                    oldCount = rset1s1111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets1111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s1111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts11111 = connectDB.createStatement();
                            java.sql.Statement st1s11111 = connectDB.createStatement();
                            java.sql.ResultSet rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%')");
                            java.sql.ResultSet rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%')");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.6", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Othorpaedic Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%')");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.7", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Occupational Therapy Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%')");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.8", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Physiotherapy Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%')");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.9", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Medical Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%' )");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.10", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Surgical Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Paediatrics%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Paediatrics%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%' )");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.11", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Paediatrics Clinic", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%' )");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.12", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Obstetrics/Gynaecology ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Other%' OR clinic ILIKE 'special%' )");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND upper(clinic) IN (SELECT DISTINCT upper(clinics)  FROM pb_clinics WHERE clinic_category ILIKE 'other')");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.13", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("All Other Special Clinics ", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets11111.getInt(1);
                                    oldCount = rset1s11111.getInt(1);
                                    newMaleTotal = newMaleTotal + rsets11111.getInt(1);
                                    oldMaleTotal = oldMaleTotal + rset1s11111.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.8", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL SPECIAL CLINICS", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMaleTotal + oldMaleTotal)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.4 MCH/FP CLIENTS", pFontHeader);
                            table.addCell(phrase);

                            java.sql.Statement sts4 = connectDB.createStatement();
                            java.sql.Statement st1s4 = connectDB.createStatement();
                            java.sql.ResultSet rsets4 = sts4.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'CWC%'");
                            java.sql.ResultSet rset41 = st1s4.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'CWC%'");
                            while (rsets4.next()) {
                                while (rset41.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.4.1", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("CWC Attendances", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets4.getInt(1);
                                    oldCount = rset41.getInt(1);
                                    newFemaleTotal = newFemaleTotal + rsets4.getInt(1);
                                    oldFemaleTotal = oldFemaleTotal + rset41.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts42 = connectDB.createStatement();
                            java.sql.Statement st1s42 = connectDB.createStatement();
                            java.sql.ResultSet rsets42 = sts42.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'ANC%'");
                            java.sql.ResultSet rset412 = st1s42.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'ANC%'");
                            while (rsets42.next()) {
                                while (rset412.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.4.2", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("ANC Attendances", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets42.getInt(1);
                                    oldCount = rset412.getInt(1);
                                    newFemaleTotal = newFemaleTotal + rsets42.getInt(1);
                                    oldFemaleTotal = oldFemaleTotal + rset412.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts43 = connectDB.createStatement();
                            java.sql.Statement st1s43 = connectDB.createStatement();
                            java.sql.ResultSet rsets43 = sts43.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'PNC%'");
                            java.sql.ResultSet rset413 = st1s43.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'PNC%'");
                            while (rsets43.next()) {
                                while (rset413.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.4.3", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("PNC Attendances", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets43.getInt(1);
                                    oldCount = rset413.getInt(1);
                                    newFemaleTotal = newFemaleTotal + rsets43.getInt(1);
                                    oldFemaleTotal = oldFemaleTotal + rset413.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts44 = connectDB.createStatement();
                            java.sql.Statement st1s44 = connectDB.createStatement();
                            java.sql.ResultSet rsets44 = sts44.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'FP%'");
                            java.sql.ResultSet rset414 = st1s44.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'FP%'");
                            while (rsets44.next()) {
                                while (rset414.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.4.4", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("FP Attendances", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rsets44.getInt(1);
                                    oldCount = rset414.getInt(1);
                                    newFemaleTotal = newFemaleTotal + rsets44.getInt(1);
                                    oldFemaleTotal = oldFemaleTotal + rset414.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.4.5", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL MCH/FP", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemaleTotal + oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5 DENTAL CLINIC", pFontHeader);
                            table.addCell(phrase);

                            java.sql.Statement sts5 = connectDB.createStatement();
                            java.sql.Statement st1s5 = connectDB.createStatement();
                            java.sql.ResultSet rset5 = sts5.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'DENTAL%' ");
                            java.sql.ResultSet rsets5 = st1s5.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'DENTAL%' ");
                            while (rset5.next()) {
                                while (rsets5.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.5.1", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Attendances (Excluding fillings and extractions)", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset5.getInt(1);
                                    oldCount = rsets5.getInt(1);
                                    totalMale = totalMale + rset5.getInt(1);
                                    totalFemale = totalFemale + rsets5.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            java.sql.Statement sts51 = connectDB.createStatement();
                            java.sql.Statement st1s51 = connectDB.createStatement();
                            java.sql.ResultSet rset51 = sts51.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'DENTAL%' AND service ILIKE '%filling%'");
                            java.sql.ResultSet rsets51 = st1s51.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'DENTAL%' AND service ILIKE '%filling%'");
                            while (rset51.next()) {
                                while (rsets51.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.5.2", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Fillings", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset51.getInt(1);
                                    oldCount = rsets51.getInt(1);
                                    totalMale = totalMale + rset51.getInt(1);
                                    totalFemale = totalFemale + rsets51.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            System.out.println("Have I passed here");
                            java.sql.Statement sts512 = connectDB.createStatement();
                            java.sql.Statement st1s512 = connectDB.createStatement();
                            java.sql.ResultSet rset512 = sts512.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'DENTAL%' AND service ILIKE '%extraction%'");
                            java.sql.ResultSet rsets512 = st1s512.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'DENTAL%' AND service ILIKE '%extraction%'");
                            while (rset512.next()) {
                                while (rsets512.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.5.3", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Extractions", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    newCount = rset512.getInt(1);
                                    oldCount = rsets512.getInt(1);
                                    totalMale = totalMale + rset512.getInt(1);
                                    totalFemale = totalFemale + rsets512.getInt(1);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5.4", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL DENTAL SERVICES", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalFemale)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + totalFemale)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("A.6 TOTAL OUT-PATIENT SERVICES \n (=A.1.5 + A.2 + A.3.7 + A.4.5 + A.5.4)", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + newMale + newFemale + cFemale + newMaleTotal + newFemaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalFemale + oldMale + oldFemale + coldFemale + oldMaleTotal + oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + newMale + oldMale + cFemale + newMaleTotal + newFemaleTotal + totalFemale + newFemale + oldFemale + coldFemale + oldMaleTotal + oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);

                            java.sql.Statement stme = connectDB.createStatement();
                            java.sql.ResultSet rsme = stme.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND description ILIKE '%Medical examination%'");
                            while (rsme.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.7 MEDICAL EXAMINATION (except p3)", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsme.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

                            java.sql.Statement stin = connectDB.createStatement();
                            java.sql.ResultSet rsin = stin.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%inj%') AND drawer = 'OP'");
                            while (rsin.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.10 INJECTIONS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsin.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

                            java.sql.Statement stmr = connectDB.createStatement();
                            java.sql.ResultSet rsmr = stmr.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%p3%' OR description ILIKE '%p.3%' OR description ILIKE '%p 3%')");
                            while (rsmr.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.8 MEDICAL REPORTS (incl. P3,compensation,insurance,etc)", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsmr.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

                            java.sql.Statement stst = connectDB.createStatement();
                            java.sql.ResultSet rsst = stst.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%stitchi%' OR service_type ILIKE '%stichi%') AND drawer = 'OP'");
                            while (rsst.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.11 STITCHING", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsst.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

                            java.sql.Statement stdr = connectDB.createStatement();
                            java.sql.ResultSet rsdr = stdr.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%dressing%') AND drawer = 'OP'");
                            while (rsdr.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.9 DRESSINGS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsdr.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

                            java.sql.Statement stpp = connectDB.createStatement();
                            java.sql.ResultSet rspp = stpp.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%pop%' OR description ILIKE '%p.o.p.%' OR description ILIKE '%p o p%' OR description ILIKE '%plaster%' )");
                            while (rspp.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.12 P.O.P", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rspp.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

//                              stdr = connectDB.createStatement();
//                            rsdr = stdr.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%r.o.s%' OR description ILIKE '%removal of stitches%') ");
//                            while (rsdr.next()) {
//                                newCount = 0;
//                                oldCount = 0;
//                                table.getDefaultCell().setFixedHeight(50);
//                                table.getDefaultCell().setColspan(2);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                phrase = new Phrase("A.10 Removal OF Stitches", pFontHeader);
//                                table.addCell(phrase);
//
//                                table.getDefaultCell().setColspan(1);
//                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
//                                newCount = rsdr.getInt(1);
//                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
//                                table.addCell(phrase);
//                            }
//Inpatient workload starts here....
//Discharges
                            float gAdults = 0;
                            float paeds = 0;
                            float matMothers = 0;
                            float amenity = 0;
                            float pTotal = 0;
                            float tgAdults = 0;
                            float tpaeds = 0;
                            float tmatMothers = 0;
                            float tamenity = 0;
                            float tpTotal = 0;

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorder(Rectangle.TOP);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setFixedHeight(100);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            //table2.addCell(phrase);
                            //table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(8);
                            // table2.getDefaultCell().setFixedHeight(12);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B. INPATIENT SERVICES", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1 INPATIENT", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase("GENERAL ADULTS", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("PAEDIATRICS", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("MATERNITY MOTHERS ONLY", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("AMENITY", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("TOTAL", pFontHeader);
                            table2.addCell(phrase);

                            //Discharges
                            java.sql.Statement stppx = connectDB.createStatement();
//                            java.sql.ResultSet rsppx = stppx.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'discharge' AND pat_category ILIKE 'IP'");
                            java.sql.ResultSet rsppx = stppx.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND (ward NOT ILIKE '%Mat%' OR ward NOT ILIKE '%Ame%' OR ward NOT ILIKE '%paed%') "
                                    + "AND discharge = true");
                            while (rsppx.next()) {

                                gAdults = rsppx.getFloat(1);
                                tgAdults = tgAdults + gAdults;

                            }

                            java.sql.Statement stppx1 = connectDB.createStatement();
                            java.sql.ResultSet rsppx1 = stppx1.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age <= 5 AND ward ilike 'paed%'"
                                    + "AND discharge = true");
                            while (rsppx1.next()) {

                                paeds = rsppx1.getFloat(1);
                                tpaeds = tpaeds + paeds;

                            }

                            java.sql.Statement stppx2 = connectDB.createStatement();
                            java.sql.ResultSet rsppx2 = stppx2.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Mat%' "
                                    + "AND discharge = true");
                            while (rsppx2.next()) {

                                matMothers = rsppx2.getFloat(1);
                                tmatMothers = tmatMothers + matMothers;

                            }

                            java.sql.Statement stppx3 = connectDB.createStatement();
                            java.sql.ResultSet rsppx3 = stppx3.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%ameni%' "
                                    + "AND discharge = true");
                            while (rsppx3.next()) {

                                amenity = rsppx3.getFloat(1);
                                tamenity = tamenity + amenity;

                            }
                            pTotal = amenity + matMothers + paeds + gAdults;
                            tpTotal = tpTotal + pTotal;

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Discharges", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(pTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            //Deaths
                            java.sql.Statement stppd = connectDB.createStatement();
//                            java.sql.ResultSet rsppd = stppd.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'Died' AND pat_category ILIKE 'IP'");
                            java.sql.ResultSet rsppd = stppd.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE date_of_death BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "year_of_birth::numeric(5,1) > 5 AND patient_no IN (SELECT patient_no FROM hp_admission WHERE ward NOT ILIKE '%Mat%' OR ward NOT ILIKE '%Ame%' OR ward NOT ILIKE '%paed% ORDER BY date_admitted::date DESC LIMIT 1') "
                                    + "");
                            while (rsppd.next()) {

                                gAdults = rsppd.getFloat(1);
                                tgAdults = tgAdults + gAdults;

                            }

                            java.sql.Statement stppd1 = connectDB.createStatement();
                            java.sql.ResultSet rsppd1 = stppd1.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE date_of_death::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "year_of_birth::numeric(5,1) <= 5 "
                                    + "AND patient_no IN (SELECT patient_no FROM hp_admission WHERE ward ILIKE '%paed%' ORDER BY date_admitted::date DESC LIMIT 1)");
                            while (rsppd1.next()) {

                                paeds = rsppd1.getFloat(1);
                                tpaeds = tpaeds + paeds;

                            }

                            java.sql.Statement stppd2 = connectDB.createStatement();
                            java.sql.ResultSet rsppd2 = stppd2.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE date_of_death::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "year_of_birth::numeric(5,1) > 5 AND patient_no IN (SELECT patient_no FROM hp_admission WHERE ward ILIKE '%Mat%' ORDER BY date_admitted::date DESC LIMIT 1) "
                                    + "");
                            while (rsppd2.next()) {

                                matMothers = rsppd2.getFloat(1);
                                tmatMothers = tmatMothers + matMothers;

                            }

                            java.sql.Statement stppd3 = connectDB.createStatement();
                            java.sql.ResultSet rsppd3 = stppd3.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE date_of_death::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "year_of_birth::numeric(5,1) > 5 AND patient_no IN (SELECT patient_no FROM hp_admission WHERE ward ILIKE '%Ameni%' ORDER BY date_admitted::date DESC LIMIT 1) "
                                    + "");
                            while (rsppx3.next()) {

                                amenity = rsppx3.getFloat(1);
                                tamenity = tamenity + amenity;

                            }
                            pTotal = amenity + matMothers + paeds + gAdults;
                            tpTotal = tpTotal + pTotal;

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Deaths", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(pTotal)), pFontHeader1);
                            table2.addCell(phrase);
//Absconders

                            java.sql.Statement stppa = connectDB.createStatement();
                            java.sql.ResultSet rsppa = stppa.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rsppa.next()) {

                                gAdults = rsppa.getFloat(1);
                                tgAdults = tgAdults + gAdults;

                            }

                            java.sql.Statement stppa1 = connectDB.createStatement();
                            java.sql.ResultSet rsppa1 = stppa1.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age <= 5 "
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rsppa1.next()) {

                                paeds = rsppa1.getFloat(1);
                                tpaeds = tpaeds + paeds;

                            }

                            java.sql.Statement stppa2 = connectDB.createStatement();
                            java.sql.ResultSet rsppa2 = stppa2.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward_name ILIKE '%Mat%' "
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rsppa2.next()) {

                                matMothers = rsppa2.getFloat(1);
                                tmatMothers = tmatMothers + matMothers;

                            }

                            java.sql.Statement stppa3 = connectDB.createStatement();
                            java.sql.ResultSet rsppa3 = stppa3.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward_name ILIKE '%ameni%' "
                                    + "AND admission_outcome ILIKE 'discharge' AND pat_category ILIKE 'IP'");
                            while (rsppa3.next()) {

                                amenity = rsppa3.getFloat(1);
                                tamenity = tamenity + amenity;

                            }
                            pTotal = amenity + matMothers + paeds + gAdults;
                            tpTotal = tpTotal + pTotal;

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Absconders", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(pTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.4", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("TOTAL DISCHARGES, DEATHS, etc", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tgAdults)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tpaeds)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmatMothers)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tamenity)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tpTotal)), pFontHeader);
                            table2.addCell(phrase);
//Admissions
                            float gAdmin = 0;
                            float gPaeds = 0;
                            float gMat = 0;
                            float gAmenity = 0;
                            float gTotal = 0;

                            java.sql.Statement sta = connectDB.createStatement();
                            java.sql.ResultSet rsa = sta.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND (ward NOT ILIKE '%Mat%' OR ward NOT ILIKE '%Ame%' OR ward NOT ILIKE '%paed%') AND wing NOT ILIKE 'Prisoner'");
                            while (rsa.next()) {

                                gAdults = rsa.getFloat(1);
                                gAdmin = gAdmin + gAdults;

                            }

                            java.sql.Statement sta1 = connectDB.createStatement();
                            java.sql.ResultSet rsa1 = sta1.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age <= 5 AND wing NOT ILIKE 'Prisoner' ");
                            while (rsa1.next()) {

                                paeds = rsa1.getFloat(1);
                                gPaeds = gPaeds + paeds;

                            }

                            java.sql.Statement sta2 = connectDB.createStatement();
                            java.sql.ResultSet rsa2 = sta2.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Mat%' AND wing NOT ILIKE 'Prisoner'");
                            while (rsa2.next()) {

                                matMothers = rsa2.getFloat(1);
                                gMat = gMat + matMothers;

                            }

                            java.sql.Statement sta3 = connectDB.createStatement();
                            java.sql.ResultSet rsa3 = sta3.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Amen%' AND wing NOT ILIKE 'Prisoner'");
                            while (rsa3.next()) {

                                amenity = rsa3.getFloat(1);
                                gAmenity = gAmenity + amenity;

                            }

                            gTotal = gAdults + paeds + matMothers + amenity;
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.9", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Admissions", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            //Paroles
                            java.sql.Statement stp = connectDB.createStatement();
                            java.sql.ResultSet rsp = stp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND (ward NOT ILIKE '%Mat%' OR ward NOT ILIKE '%Ame%' OR ward NOT ILIKE '%paed%') AND wing ILIKE 'Prisoner'");
                            while (rsp.next()) {

                                gAdults = rsp.getFloat(1);
                                gAdmin = gAdmin + gAdults;

                            }

                            java.sql.Statement stp1 = connectDB.createStatement();
                            java.sql.ResultSet rsp1 = stp1.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age <= 5 AND wing ILIKE 'Prisoner' ");
                            while (rsp1.next()) {

                                paeds = rsp1.getFloat(1);
                                gPaeds = gPaeds + paeds;

                            }

                            java.sql.Statement stp2 = connectDB.createStatement();
                            java.sql.ResultSet rsp2 = stp2.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Mat%' AND wing ILIKE 'Prisoner'");
                            while (rsp2.next()) {

                                matMothers = rsp2.getFloat(1);
                                gMat = gMat + matMothers;

                            }

                            java.sql.Statement stp3 = connectDB.createStatement();
                            java.sql.ResultSet rsp3 = stp3.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Amen%' AND wing ILIKE 'Prisoner'");
                            while (rsp3.next()) {

                                amenity = rsp3.getFloat(1);
                                gAmenity = gAmenity + amenity;

                            }

                            gTotal = gAdults + paeds + matMothers + amenity;
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.10", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Paroles", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gTotal)), pFontHeader1);
                            table2.addCell(phrase);

//Occupied bed days - NHIF MEMBERS
                            java.sql.Statement stp4 = connectDB.createStatement();
                            java.sql.ResultSet rsp4 = stp4.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND (ward NOT ILIKE '%Mat%' OR ward NOT ILIKE '%Ame%' OR ward NOT ILIKE '%paed%') AND wing ILIKE 'nhif'");
                            while (rsp4.next()) {

                                gAdults = rsp4.getFloat(1);
                                gAdmin = gAdmin + gAdults;

                            }

                            java.sql.Statement stp5 = connectDB.createStatement();
                            java.sql.ResultSet rsp5 = stp5.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age <= 5 AND wing ILIKE 'nhif' ");
                            while (rsp5.next()) {

                                paeds = rsp5.getFloat(1);
                                gPaeds = gPaeds + paeds;

                            }

                            java.sql.Statement stp6 = connectDB.createStatement();
                            java.sql.ResultSet rsp6 = stp6.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Mat%' AND wing ILIKE 'NHIF'");
                            while (rsp6.next()) {

                                matMothers = rsp6.getFloat(1);
                                gMat = gMat + matMothers;

                            }

                            java.sql.Statement stp7 = connectDB.createStatement();
                            java.sql.ResultSet rsp7 = stp7.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Amen%' AND wing ILIKE 'nhif'");
                            while (rsp7.next()) {

                                amenity = rsp7.getFloat(1);
                                gAmenity = gAmenity + amenity;

                            }

                            gTotal = gAdults + paeds + matMothers + amenity;
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.11", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Occupied Bed Days - NHIF Members", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            // obd non nhif members
                            java.sql.Statement stp8 = connectDB.createStatement();
                            java.sql.ResultSet rsp8 = stp8.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND (ward NOT ILIKE '%Mat%' OR ward NOT ILIKE '%Ame%' OR ward NOT ILIKE '%paed%') AND wing NOT ILIKE 'nhif'");
                            while (rsp8.next()) {

                                gAdults = rsp8.getFloat(1);
                                gAdmin = gAdmin + gAdults;

                            }

                            java.sql.Statement stp9 = connectDB.createStatement();
                            java.sql.ResultSet rsp9 = stp9.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age <= 5 AND wing NOT ILIKE 'nhif' ");
                            while (rsp9.next()) {

                                paeds = rsp9.getFloat(1);
                                gPaeds = gPaeds + paeds;

                            }

                            java.sql.Statement stp10 = connectDB.createStatement();
                            java.sql.ResultSet rsp10 = stp10.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Mat%' AND wing NOT ILIKE 'NHIF'");
                            while (rsp10.next()) {

                                matMothers = rsp10.getFloat(1);
                                gMat = gMat + matMothers;

                            }

                            java.sql.Statement stp11 = connectDB.createStatement();
                            java.sql.ResultSet rsp11 = stp11.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "pat_age > 5 AND ward ILIKE '%Amen%' AND wing NOT ILIKE 'nhif'");
                            while (rsp11.next()) {

                                amenity = rsp11.getFloat(1);
                                gAmenity = gAmenity + amenity;

                            }

                            gTotal = gAdults + paeds + matMothers + amenity;
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.11a", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Occupied Bed Days - NON NHIF Members", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gAdults)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paeds)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(matMothers)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(amenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            //Well pple
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.12", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Well Persons Days", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.5", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Beds Authorized", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.6", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Beds Actual Physical", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.7", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Cots Authorized", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.8", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Cots- Physical Actual", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
//Maternity Services
                            double patno = 0.00;
                            table2.getDefaultCell().setColspan(8);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //table2.getDefaultCell().setFixedHeight(25);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            //table2.addCell(phrase);
                            // table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            //table2.getDefaultCell().setFixedHeight(12);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("MATERNITY SERVICES", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("B.3 OPERATIONS", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Number", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            java.sql.Statement stp101 = connectDB.createStatement();
                            java.sql.ResultSet rsp101 = stp101.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_card"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service ILIKE '%Delivery%'");
                            while (rsp101.next()) {

                                patno = rsp101.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Normal Deliveries(includes Normal \n & Assisted delivery)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            // table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            // table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            java.sql.Statement stp102 = connectDB.createStatement();
                            java.sql.ResultSet rsp102 = stp102.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE '%minor Surgery%'");
                            while (rsp102.next()) {

                                patno = rsp102.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("B.3.1 Minor Surgeries \n (excluding circumcision)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            // table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

//table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            java.sql.Statement stp103 = connectDB.createStatement();
                            java.sql.ResultSet rsp103 = stp103.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "(service_type ILIKE '%caes%' OR service_type ILIKE '%c/s%' OR service_type ILIKE 'cs'  OR service_type ILIKE 'Ceaserian%')");
                            while (rsp103.next()) {

                                patno = rsp103.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Caesarian Sections", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp104 = connectDB.createStatement();
                            java.sql.ResultSet rsp104 = stp104.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE '%Circumcision%'");
                            while (rsp104.next()) {

                                patno = rsp104.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.3.2 Circumcision", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            // table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            //type_of_birth
                            java.sql.Statement stp105 = connectDB.createStatement();
                            java.sql.ResultSet rsp105 = stp105.executeQuery("SELECT COUNT(mother_serial_no) FROM rh.post_natal_services"
                                    + " WHERE service_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "type_of_birth ILIKE '%Fresh Still%'");
                            while (rsp105.next()) {

                                patno = rsp105.getFloat(1);
                                //gMat = gMat + matMothers;

                            }

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Fresh Still Birth", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp106 = connectDB.createStatement();
                            java.sql.ResultSet rsp106 = stp106.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE 'Major surger%'");
                            while (rsp106.next()) {

                                patno = rsp106.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("B.3.3 Major Surgeries", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp107 = connectDB.createStatement();
                            java.sql.ResultSet rsp107 = stp107.executeQuery("SELECT COUNT(mother_serial_no) FROM rh.post_natal_services"
                                    + " WHERE service_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "type_of_birth ILIKE 'Macerated Still%'");
                            while (rsp107.next()) {

                                patno = rsp107.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.4", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Macerated Still Birth", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
//Pharmacy & Farewell

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("D. MORTUARY", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("NUMBER", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("D.1 Body days", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C PHARMACY - No of prescriptions", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("<5 YRS", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(">5 YRS", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Total", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            //table2.addCell(phrase);

                            java.sql.Statement stp108 = connectDB.createStatement();
                            java.sql.ResultSet rsp108 = stp108.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE 'Embal%'");
                            while (rsp108.next()) {

                                patno = rsp108.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            phrase = new Phrase("D.2 Embalment", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.1", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Common Drugs", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            java.sql.Statement stp109 = connectDB.createStatement();
                            java.sql.ResultSet rsp109 = stp109.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE '%Mortem%'");
                            while (rsp109.next()) {

                                patno = rsp109.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            phrase = new Phrase("D.3 Post-Mortem", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.2 ", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Antibiotics", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("D.4 Unclaimed Body days", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.3", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Special Drugs", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("E. MEDICAL RECORDS ISSUED", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("", pFontHeader1);
                            //table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp110 = connectDB.createStatement();
                            java.sql.ResultSet rsp110 = stp110.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "adm_type ILIKE 'First%'");
                            while (rsp110.next()) {

                                patno = rsp110.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            phrase = new Phrase("E.1 New Files", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp111 = connectDB.createStatement();
                            java.sql.ResultSet rsp111 = stp111.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_visit"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "comments = 'New'");
                            while (rsp111.next()) {

                                patno = rsp111.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            phrase = new Phrase("E.2 Outpatient records", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(8);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            // table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F. SPECIAL SERVICES (includes both inpatients and outpatiens)", pFontHeader);
                            table2.addCell(phrase);

                            java.sql.Statement stp112 = connectDB.createStatement();
                            java.sql.ResultSet rsp112 = stp112.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Laboratory%'");
                            while (rsp112.next()) {
                                patno = rsp112.getFloat(1);
                            }
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.1", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Laboratory Number of Tests", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Routine", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Special", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("Total", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.2", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("X-Ray Number of Examinations", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp113 = connectDB.createStatement();
                            java.sql.ResultSet rsp113 = stp113.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Xray%' AND service_type NOT ILIKE '%Ultra%'");
                            while (rsp113.next()) {
                                patno = rsp113.getFloat(1);
                            }
                            phrase = new Phrase("Plain", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Enhancement", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            java.sql.Statement stp114 = connectDB.createStatement();
                            java.sql.ResultSet rsp114 = stp114.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Xray%' AND service_type ILIKE '%Ultra%'");
                            while (rsp114.next()) {
                                patno = rsp114.getFloat(1);
                            }
                            phrase = new Phrase("Ultrasound", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Special", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(3);
                            java.sql.Statement stp115 = connectDB.createStatement();
                            java.sql.ResultSet rsp115 = stp115.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Xray%'");
                            while (rsp115.next()) {
                                patno = rsp115.getFloat(1);
                            }
                            phrase = new Phrase("TOTAL Radiological exams", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            //table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("", pFontHeader1);
                            //table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.3", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Physiotherapy - Number of Treatments", pFontHeader1);
                            table2.addCell(phrase);
                            java.sql.Statement stp116 = connectDB.createStatement();
                            java.sql.ResultSet rsp116 = stp116.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Physiother%' AND service_type ILIKE '%private%'");
                            while (rsp116.next()) {
                                patno = rsp116.getFloat(1);
                            }
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            java.sql.Statement stp119 = connectDB.createStatement();
                            java.sql.ResultSet rsp119 = stp119.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Physiother%' AND service_type NOT ILIKE '%private%'");
                            while (rsp119.next()) {
                                patno = rsp119.getFloat(1);
                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Non-Private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("Total", pFontHeader);
                            table2.addCell(phrase);
                            java.sql.Statement stp121 = connectDB.createStatement();
                            java.sql.ResultSet rsp121 = stp121.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Physiother%'");
                            while (rsp121.next()) {
                                patno = rsp121.getFloat(1);
                            }

                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.4", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Occupational Therapy - Number of Treatments", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp117 = connectDB.createStatement();
                            java.sql.ResultSet rsp117 = stp117.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%occupational%' AND service_type ILIKE '%private%'");
                            while (rsp117.next()) {
                                patno = rsp117.getFloat(1);
                            }
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Non-Private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            java.sql.Statement stp120 = connectDB.createStatement();
                            java.sql.ResultSet rsp120 = stp120.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%occupati%' AND service_type NOT ILIKE '%private%'");
                            while (rsp120.next()) {
                                patno = rsp120.getFloat(1);
                            }
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Total", pFontHeader);
                            table2.addCell(phrase);
                            java.sql.Statement stp122 = connectDB.createStatement();
                            java.sql.ResultSet rsp122 = stp122.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%occupational%'");
                            while (rsp122.next()) {
                                patno = rsp122.getFloat(1);
                            }
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.5", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("Orthopaedic Technology - No of Items", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp118 = connectDB.createStatement();
                            java.sql.ResultSet rsp118 = stp118.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%ORTHOPAEDIC%' AND service_type ILIKE '%private%'");
                            while (rsp118.next()) {
                                patno = rsp118.getFloat(1);
                            }
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Non-Private", pFontHeader1);
                            table2.addCell(phrase);
                            java.sql.Statement stp123 = connectDB.createStatement();
                            java.sql.ResultSet rsp123 = stp123.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%ORTHOPAEDIC%' AND service_type NOT ILIKE '%private%'");
                            while (rsp123.next()) {
                                patno = rsp123.getFloat(1);
                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Total", pFontHeader);
                            table2.addCell(phrase);
                            java.sql.Statement stp124 = connectDB.createStatement();
                            java.sql.ResultSet rsp124 = stp124.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%ORTHOPAEDIC%'");
                            while (rsp124.next()) {
                                patno = rsp124.getFloat(1);
                            }
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(8);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Name", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Signature", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Date", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Designation", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Prepared By:", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Checked By:", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);

                            phrase = new Phrase("Entered By:", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);

                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            //phrase = new Phrase("", pFontHeader1);
                            //table2.addCell(phrase);
                            docPdf.add(table);
                            docPdf.add(table1);
                            docPdf.add(table2);
                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();
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
            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
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
    /*public java.lang.Object[] getListofStaffNos() {
    
     java.lang.Object[] listofStaffNos = null;
    
     java.util.Vector listStaffNoVector = new java.util.Vector(1,1);
    
    
     try {
    
     //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
     java.sql.Statement stmt1 = connectDB.createStatement();
    
     java.sql.ResultSet rSet1 = stmt1.executeQuery("select distinct initcap(description),min_age from patient_age order by min_age");
     //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_admission WHERE discharge = false ORDER BY patient_no");
    
     while (rSet1.next()) {
    
     listStaffNoVector.addElement(rSet1.getObject(1).toString());
    
     }
    
     }catch (java.sql.SQLException sqlExec) {
    
     javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
     }
    
    
     listofStaffNos = listStaffNoVector.toArray();
     System.out.println("Done list of Staff Nos ...");
     return listofStaffNos;
     }
     */
    /*public java.lang.Object[] getListofStaffNos1() {
    
     java.lang.Object[] listofStaffNos1 = null;
    
     java.util.Vector listStaffNoVector1 = new java.util.Vector(1,1);
    
    
     try {
    
     //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
     java.sql.Statement stmt1 = connectDB.createStatement();
    
     java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ward_code FROM hp_admission WHERE date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' ORDER BY ward_code");
    
     while (rSet1.next()) {
    
     listStaffNoVector1.addElement(rSet1.getObject(1).toString());
    
     }
    
     }catch (java.sql.SQLException sqlExec) {
    
     javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
     }
    
    
     listofStaffNos1 = listStaffNoVector1.toArray();
     System.out.println("Done list of Staff Nos ...");
     return listofStaffNos1;
     }
    
    
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
