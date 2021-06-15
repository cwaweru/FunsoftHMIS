//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MonthlyOPWorkLoadPdf implements java.lang.Runnable {

    boolean specificClinicConsideration = true;
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

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(15);

                        int headerwidths2[] = {6, 20, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};

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
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("District/Sub County : " + District, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Region/County : " + Region, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
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
                            java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT COUNT(patient_no) from hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND age::numeric(10,2) <= 60 AND comments ilike 'new' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OUTPATIENT%' OR clinic ILIKE 'OPD%' OR  clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND age::numeric(10,2) <= 60 AND comments ilike 'old' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
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
                            java.sql.ResultSet rset2 = st21.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND age::numeric(10,2) <= 60 AND comments ilike 'new' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND age::numeric(10,2) <= 60 AND comments ilike 'old' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
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
                            java.sql.ResultSet rset22 = st212.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'new' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset122 = st122.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'old' AND TRIM(gender) ilike 'MALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
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
                            java.sql.ResultSet rset221 = st2121.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'new' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            java.sql.ResultSet rset1221 = st1221.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND comments ilike 'old' AND TRIM(gender) ilike 'FEMALE' AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
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
                            
                            
                            rset221 = st2121.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) > 60 AND comments ilike 'new'  AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            rset1221 = st1221.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >60 AND comments ilike 'old'  AND (clinic ILIKE 'OUTPATIENT%' OR  clinic ILIKE 'OPD%' OR clinic ILIKE 'MCH')");
                            while (rset221.next()) {
                                while (rset1221.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.1.5", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Over 60 years", pFontHeader1);
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
                            phrase = new Phrase("A.1.6", pFontHeader);
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
                            
                            java.sql.ResultSet rsets = null;
                            java.sql.ResultSet rset1s = null;
                            
                            if(specificClinicConsideration){
                                rsets = sts.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%') ");
                                rset1s = st1s.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%')");
                            
                            }else{
                                rsets = sts.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%') ");
                                rset1s = st1s.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'ENT%' OR clinic ILIKE 'E.N.T%')");
                            
                            }
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
                            
                            java.sql.ResultSet rsets1 = null;
                            java.sql.ResultSet rset1s1 = null;

                            java.sql.Statement sts1 = connectDB.createStatement();
                            java.sql.Statement st1s1 = connectDB.createStatement();
                            if(specificClinicConsideration){
                                rsets1 = sts1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Eye%') AND date < '" + beginDate + "'  ) )   AND clinic ILIKE 'Eye%'");
                            rset1s1 = st1s1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Eye%') AND date < '" + beginDate + "'  ) )    AND clinic ILIKE 'Eye%'");
                            
                                
                            }else{
                                rsets1 = sts1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND clinic ILIKE 'Eye%'");
                            rset1s1 = st1s1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND clinic ILIKE 'Eye%'");
                            
                            }
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
                            java.sql.ResultSet rsets11 =null;
                            java.sql.ResultSet rset1s11 = null;
                            if(specificClinicConsideration){
                             rsets11 = sts11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%')");
                             rset1s11 = st1s11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%')");
                            
                            }else{
                                rsets11 = sts11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%')");
                             rset1s11 = st1s11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'TB%' OR clinic ILIKE 'leprosy%' OR clinic ILIKE 'Chest%')");
                            
                            }
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
                            
                            java.sql.ResultSet rsets111 = null;
                            java.sql.ResultSet rset1s111 = null;
                            
                            if(specificClinicConsideration){
                                rsets111 = sts111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%' OR clinic ILIKE 'ccc%' OR clinic ILIKE 'c.c.c%' OR clinic ILIKE 'comprehensive care clinic%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%' OR clinic ILIKE 'ccc%' OR clinic ILIKE 'c.c.c%' OR clinic ILIKE 'comprehensive care clinic%')");
                            rset1s111 = st1s111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%' OR clinic ILIKE 'ccc%' OR clinic ILIKE 'c.c.c%' OR clinic ILIKE 'comprehensive care clinic%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%' OR clinic ILIKE 'ccc%' OR clinic ILIKE 'c.c.c%' OR clinic ILIKE 'comprehensive care clinic%')");
                            
                            }else{
                                rsets111 = sts111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%' OR clinic ILIKE 'ccc%' OR clinic ILIKE 'c.c.c%' OR clinic ILIKE 'comprehensive care clinic%')");
                            rset1s111 = st1s111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'sti%' OR clinic ILIKE 'std%' OR clinic ILIKE 'sex%' OR clinic ILIKE 'ccc%' OR clinic ILIKE 'c.c.c%' OR clinic ILIKE 'comprehensive care clinic%')");
                            
                            }
                             while (rsets111.next()) {
                                while (rset1s111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.4", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Comprehensive Care Clinic", pFontHeader1);
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
                            java.sql.ResultSet rsets1111  = null;
                            java.sql.ResultSet rset1s1111  = null;
                            if(specificClinicConsideration){
                                rsets1111 = sts1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%')");
                            rset1s1111 = st1s1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%')");
                            
                            }else{
                                rsets1111 = sts1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%')");
                            rset1s1111 = st1s1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'psychiatry%' OR clinic ILIKE 'psyc%' OR clinic ILIKE 'mental%')");
                            
                            }
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
                            java.sql.ResultSet rsets11111 = null;
                            java.sql.ResultSet rset1s11111 = null;
                            if(specificClinicConsideration){
                                 rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%')");
                            
                            }else{
                                 rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHO%')");
                            
                            }
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
                             if(specificClinicConsideration){
                                 rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%')");
                            
                             }else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Occu%' OR clinic ILIKE 'Occupational%')");
                             }
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
                             if(specificClinicConsideration){
                                 rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%')");
                            
                             }else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'physio%' OR clinic ILIKE 'Physiotherapy%')");
                             }
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
                             if(specificClinicConsideration){
                                 rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%')");
                            
                             }else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Medical%' OR clinic ILIKE 'mopc%' OR clinic ILIKE 'm.o.p.c%')");
                             }
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
                             if(specificClinicConsideration){
                                 rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%' )");
                            
                             }else{
                                rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Surgical%' OR clinic ILIKE 'sopc%' OR clinic ILIKE 's.o.p.c%' )");
                             
                             }
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

                            if(specificClinicConsideration){
                                rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Paediatric%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%')AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Paediatric%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Paediatric%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Paediatric%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%' )");
                            
                            }else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Paediatric%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Paediatric%' or clinic ILIKE 'Peads%' OR clinic ILIKE 'popc%' OR clinic ILIKE 'p.o.p.c%' )");
                            }
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

                            if(specificClinicConsideration){
                               rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE  (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%' )");
                           
                            } else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Obstetrics%' or clinic ILIKE 'Gynae%' or clinic ILIKE 'obs%' OR clinic ILIKE 'gopc%' OR clinic ILIKE 'g.o.p.c%' )");
                            }
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
                            
                            if(specificClinicConsideration){
                               rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE  (clinic ILIKE 'nutrition%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'nutrition%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'nutrition%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'nutrition%' )");
                           
                            } else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'nutrition%' )");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'nutrition%' )");
                            }
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.13", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Nutrition Clinic ", pFontHeader1);
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
                            
                            if(specificClinicConsideration){
                               rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE  (clinic ILIKE 'Onclogy%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Onclogy%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Onclogy%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'Onclogy%' )");
                           
                            } else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Onclogy%' )");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Onclogy%' )");
                            }
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.14", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Onclogy Clinic ", pFontHeader1);
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
                            
                            if(specificClinicConsideration){
                               rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (patient_no NOT IN (SELECT patient_no FROM hp_patient_visit WHERE  (clinic ILIKE 'Renal%') AND date < '" + beginDate + "'  ) ) AND (clinic ILIKE 'Renal%')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND (patient_no  IN (SELECT patient_no FROM hp_patient_visit WHERE (clinic ILIKE 'Renal%') AND date < '" + beginDate + "'  ) )  AND (clinic ILIKE 'Renal%' )");
                           
                            } else{
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND (clinic ILIKE 'Renal%' )");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND (clinic ILIKE 'Renal%' )");
                            }
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.15", pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Renal Clinic ", pFontHeader1);
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
                            

                            
                            
                            rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments ilike 'new' AND upper(clinic) IN (SELECT DISTINCT upper(clinics)  FROM pb_clinics WHERE clinic_category ILIKE 'other')");
                            rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND comments ilike 'old'  AND upper(clinic) IN (SELECT DISTINCT upper(clinics)  FROM pb_clinics WHERE clinic_category ILIKE 'other')");
                            while (rsets11111.next()) {
                                while (rset1s11111.next()) {
                                    newCount = 0;
                                    oldCount = 0;
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("A.3.16", pFontHeader1);
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
                            phrase = new Phrase("A.3.17", pFontHeader);
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
                            //java.sql.ResultSet rset5 = sts5.executeQuery("SELECT DISTINCT COUNT(hv.patient_no) FROM hp_patient_visit hv, pb_doctors_request pb WHERE hv.date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND hv.comments ilike 'new' AND hv.clinic ILIKE 'DENTAL%' AND hv.patient_no = pb.patient_no AND pb.trans_date = hv.date AND pb.service NOT ILIKE '%filling%' AND  pb.service NOT ILIKE '%extraction%'"); //(SELECT service FROM pb_doctors_request WHERE pb_doctors_request.patient_no = hp_patient_visit.patient_no LIMIT 1) NOT ILIKE '%filling%' AND (SELECT service FROM pb_doctors_request WHERE pb_doctors_request.patient_no = hp_patient_visit.patient_no LIMIT 1) NOT ILIKE '%extraction%'");
                            //java.sql.ResultSet rsets5 = st1s5.executeQuery("SELECT DISTINCT COUNT(hv.patient_no) FROM hp_patient_visit hv, pb_doctors_request pb WHERE hv.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND hv.comments ilike 'old'  AND hv.clinic ILIKE 'DENTAL%'  AND hv.patient_no = pb.patient_no AND pb.trans_date = hv.date AND pb.service NOT ILIKE '%filling%' AND pb.service NOT ILIKE '%extraction%'"); // (SELECT service FROM pb_doctors_request WHERE pb_doctors_request.patient_no = hp_patient_visit.patient_no LIMIT 1) NOT ILIKE '%filling%' AND (SELECT service FROM pb_doctors_request WHERE pb_doctors_request.patient_no = hp_patient_visit.patient_no LIMIT 1) NOT ILIKE '%extraction%'");
                            java.sql.ResultSet rset5 = sts5.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and comments ilike 'new' ) AND description ILIKE 'DENTAL%' AND service_type NOT ILIKE '%filling%' AND service_type NOT ILIKE '%extraction%'");
                            java.sql.ResultSet rsets5 = st1s5.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and comments ilike 'old' )  AND description ILIKE 'DENTAL%' AND service_type NOT ILIKE '%filling%' AND service_type NOT ILIKE '%extraction%'");
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
                            java.sql.ResultSet rset51 = sts51.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and comments ilike 'new' ) AND description ILIKE 'DENTAL%' AND service_type ILIKE '%filling%'");
                            java.sql.ResultSet rsets51 = st1s51.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and comments ilike 'old' )  AND description ILIKE 'DENTAL%' AND service_type ILIKE '%filling%'");
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
                            java.sql.ResultSet rset512 = sts512.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and comments ilike 'new' ) AND description ILIKE 'DENTAL%' AND service_type ILIKE '%extraction%'");
                            java.sql.ResultSet rsets512 = st1s512.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND patient_no IN (SELECT patient_no FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' and comments ilike 'Old' )  AND description ILIKE 'DENTAL%' AND service_type ILIKE '%extraction%'");
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
                            java.sql.ResultSet rsme = stme.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND description ILIKE '%Medical exam%'");
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
                            java.sql.ResultSet rsmr = stmr.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%medical report%' OR description ILIKE '%p3%' OR description ILIKE '%p.3%' OR description ILIKE '%p 3%')");
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
                            
                            java.sql.Statement stre = connectDB.createStatement();
                            java.sql.ResultSet rsre = stre.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%removal of stitch%' OR service_type ILIKE '%stitch removal%' OR service_type ILIKE '%r.o.s%') AND drawer = 'OP'");
                            while (rsre.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.10 REMOVAL OF STITCHES", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsre.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);
                            }

//Inpatient workload starts here....
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
                            
                            float disMedical = 0;
                            float disSurgical = 0;
                            float disObs = 0;
                            float disPaed = 0;
                            float disMat = 0;
                            float disEye = 0;
                            float disNewborn = 0;
                            float disOrtho = 0;
                            float disIsolation = 0;
                            float disAmenity = 0;
                            float disPsyc = 0;
                            float disOther = 0;
                            float disTotal = 0;
                            
                            float dtMedical = 0;
                            float dtSurgical = 0;
                            float dtObs = 0;
                            float dtPaed = 0;
                            float dtMat = 0;
                            float dtEye = 0;
                            float dtNewborn = 0;
                            float dtOrtho = 0;
                            float dtIsolation = 0;
                            float dtAmenity = 0;
                            float dtPsyc = 0;
                            float dtOther = 0;
                            float dtTotal = 0;
                            
                            float absMedical = 0;
                            float absSurgical = 0;
                            float absObs = 0;
                            float absPaed = 0;
                            float absMat = 0;
                            float absEye = 0;
                            float absNewborn = 0;
                            float absOrtho = 0;
                            float absIsolation = 0;
                            float absAmenity = 0;
                            float absPsyc = 0;
                            float absOther = 0;
                            float absTotal = 0;

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorder(Rectangle.TOP);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setFixedHeight(70);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            //table2.addCell(phrase);
                            //table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(15);
                            // table2.getDefaultCell().setFixedHeight(12);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1 INPATIENTS", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase("MEDICAL", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("SURGICAL", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("OBST/GYN", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("PAEDIATRICS", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("MATERNITY", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("EYE", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("NEWBORN", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("ORTHOPAEDIC", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("ISOLATION", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("AMENITY", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("PSYCHIATRY", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("OTHER", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("TOTAL", pFontHeader);
                            table2.addCell(phrase);

                            

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Discharges", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical discharges
                            
                            java.sql.Statement stpdism = connectDB.createStatement();
//                            java.sql.ResultSet rsppx = stppx.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'discharge' AND pat_category ILIKE 'IP'");
                            java.sql.ResultSet rspdism = stpdism.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE 'Medical')"
                                    + "AND discharge = true");
                            while (rspdism.next()) {
                                disMedical = rspdism.getFloat(1);
                            }
                            
                            //surgical discharges
                            
                            java.sql.Statement stpdiss = connectDB.createStatement();
                            java.sql.ResultSet rspdiss = stpdiss.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE 'Surgical')"
                                    + "AND discharge = true");
                            while (rspdiss.next()) {
                                disSurgical = rspdiss.getFloat(1);
                            }
                            
                            //obst/gyn discharges
                            
                            java.sql.Statement stpdiso = connectDB.createStatement();
                            java.sql.ResultSet rspdiso = stpdiso.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND discharge = true");
                            while (rspdiso.next()) {
                                disObs = rspdiso.getFloat(1);
                            }
                            
                            //Paediatric discharges
                            
                            java.sql.Statement stpdisp = connectDB.createStatement();
                            java.sql.ResultSet rspdisp = stpdisp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND discharge = true");
                            while (rspdisp.next()) {
                                disPaed = rspdisp.getFloat(1);
                            }
                            
                            //maternity discharges
                            
                            java.sql.Statement stpdismat = connectDB.createStatement();
                            java.sql.ResultSet rspdismat = stpdismat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND discharge = true");
                            while (rspdismat.next()) {
                                disMat = rspdismat.getFloat(1);
                            }
                            
                            //eye discharges
                            
                            java.sql.Statement stpdise = connectDB.createStatement();
                            java.sql.ResultSet rspdise = stpdise.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND discharge = true");
                            while (rspdise.next()) {
                                disEye = rspdise.getFloat(1);
                            }
                            
                            //newborn discharges
                            
                            java.sql.Statement stpdisn = connectDB.createStatement();
                            java.sql.ResultSet rspdisn = stpdisn.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND discharge = true");
                            while (rspdisn.next()) {
                                disNewborn = rspdisn.getFloat(1);
                            }
                            
                            //orthopaedic discharges
                            
                            java.sql.Statement stpdisort = connectDB.createStatement();
                            java.sql.ResultSet rspdisort = stpdisort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND discharge = true");
                            while (rspdisort.next()) {
                                disOrtho = rspdisort.getFloat(1);
                            }
                            
                            //isolation discharges
                            
                            java.sql.Statement stpdisi = connectDB.createStatement();
                            java.sql.ResultSet rspdisi = stpdisi.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND discharge = true");
                            while (rspdisi.next()) {
                                disIsolation = rspdisi.getFloat(1);
                            }
                            
                            //amenity discharges
                            
                            java.sql.Statement stpdisa = connectDB.createStatement();
                            java.sql.ResultSet rspdisa = stpdisa.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND discharge = true");
                            while (rspdisa.next()) {
                                disAmenity = rspdisa.getFloat(1);
                            }
                            
                            //psychiatry discharges
                            
                            java.sql.Statement stpdispsy = connectDB.createStatement();
                            java.sql.ResultSet rspdispsy = stpdispsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND discharge = true");
                            while (rspdispsy.next()) {
                                disPsyc = rspdispsy.getFloat(1);
                            }
                            
                            //other discharges
                            
                            java.sql.Statement stpdisoth = connectDB.createStatement();
                            java.sql.ResultSet rspdisoth = stpdisoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND discharge = true");
                            while (rspdisoth.next()) {
                                disOther = rspdisoth.getFloat(1);
                            }
                            
                            disTotal = disMedical+disSurgical+disObs+disPaed+disMat+disEye+disNewborn+disOrtho+disIsolation+disAmenity+disPsyc+disOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            //Deaths
                            /*java.sql.Statement stppd = connectDB.createStatement();
//                            java.sql.ResultSet rsppd = stppd.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'Died' AND pat_category ILIKE 'IP'");
                            java.sql.ResultSet rsppd = stppd.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
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

                            }*/

                            /*java.sql.Statement stppd3 = connectDB.createStatement();
                            java.sql.ResultSet rsppd3 = stppd3.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE date_of_death::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "year_of_birth::numeric(5,1) > 5 AND patient_no IN (SELECT patient_no FROM hp_admission WHERE ward ILIKE '%Ameni%' ORDER BY date_admitted::date DESC LIMIT 1) "
                                    + "");
                            while (rsppx3.next()) {

                                amenity = rsppx3.getFloat(1);
                                tamenity = tamenity + amenity;

                            }*/
                            //pTotal = amenity + matMothers + paeds + gAdults;
                            //tpTotal = tpTotal + pTotal;

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Deaths", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical deaths
                            
                            java.sql.Statement stpdtm = connectDB.createStatement();
//                            java.sql.ResultSet rsppx = stppx.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'discharge' AND pat_category ILIKE 'IP'");
//                            java.sql.ResultSet rspdtm = stpdtm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%Medical%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            
                            java.sql.ResultSet rspdtm = stpdtm.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%Medical%')");
                            while (rspdtm.next()) {
                                dtMedical = rspdtm.getFloat(1);
                            }
                            
                            //surgical deaths
                            
                            java.sql.Statement stpdts = connectDB.createStatement();
//                            java.sql.ResultSet rspdts = stpdts.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdts = stpdts.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')");
                            while (rspdts.next()) {
                                dtSurgical = rspdts.getFloat(1);
                            }
                            
                            //obst/gyn deaths
                            
                            java.sql.Statement stpdto = connectDB.createStatement();
//                            java.sql.ResultSet rspdto = stpdto.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdto = stpdto.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')");
                            while (rspdto.next()) {
                                dtObs = rspdto.getFloat(1);
                            }
                            
                            //Paediatric deaths
                            
                            java.sql.Statement stpdtp = connectDB.createStatement();
//                            java.sql.ResultSet rspdtp = stpdtp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdtp = stpdtp.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')");
                            while (rspdtp.next()) {
                                dtPaed = rspdtp.getFloat(1);
                            }
                            
                            //maternity deaths
                            
                            java.sql.Statement stpdtmat = connectDB.createStatement();
//                            java.sql.ResultSet rspdtmat = stpdtmat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdtmat = stpdtmat.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')");
                            while (rspdtmat.next()) {
                                dtMat = rspdtmat.getFloat(1);
                            }
                            
                            //eye deaths
                            
                            java.sql.Statement stpdte = connectDB.createStatement();
//                            java.sql.ResultSet rspdte = stpdte.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                                java.sql.ResultSet rspdte = stpdte.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')");
                            while (rspdte.next()) {
                                dtEye = rspdte.getFloat(1);
                            }
                            
                            //newborn deaths
                            
                            java.sql.Statement stpdtn = connectDB.createStatement();
//                            java.sql.ResultSet rspdtn = stpdtn.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdtn = stpdtn.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')");
                            while (rspdtn.next()) {
                                dtNewborn = rspdtn.getFloat(1);
                            }
                            
                            //orthopaedic deaths
                            
                            java.sql.Statement stpdtort = connectDB.createStatement();
//                            java.sql.ResultSet rspdtort = stpdtort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdtort = stpdtort.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')");
                            while (rspdtort.next()) {
                                dtOrtho = rspdtort.getFloat(1);
                            }
                            
                            //isolation deaths
                            
                            java.sql.Statement stpdti = connectDB.createStatement();
//                            java.sql.ResultSet rspdti = stpdti.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdti = stpdti.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')");
                            while (rspdti.next()) {
                                dtIsolation = rspdti.getFloat(1);
                            }
                            
                            //amenity deaths
                            
                            java.sql.Statement stpdta = connectDB.createStatement();
//                            java.sql.ResultSet rspdta = stpdta.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdta = stpdta.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')");
                            while (rspdta.next()) {
                                dtAmenity = rspdta.getFloat(1);
                            }
                            
                            //psychiatry deaths
                            
                            java.sql.Statement stpdtpsy = connectDB.createStatement();
//                            java.sql.ResultSet rspdtpsy = stpdtpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            java.sql.ResultSet rspdtpsy = stpdtpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')");
                            while (rspdtpsy.next()) {
                                disPsyc = rspdtpsy.getFloat(1);
                            }
                            
                            //other deaths
                            
                            java.sql.Statement stpdtoth = connectDB.createStatement();
                            java.sql.ResultSet rspdtoth = stpdtoth.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE date_of_death::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')");
//                            java.sql.ResultSet rspdtoth = stpdtoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            while (rspdtoth.next()) {
                                dtOther = rspdtoth.getFloat(1);
                            }
                            
                            dtTotal = dtMedical+dtSurgical+dtObs+dtPaed+dtMat+dtEye+dtNewborn+dtOrtho+dtIsolation+dtAmenity+dtPsyc+dtOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            
                            //Deaths due to malaria
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Deaths due to confirmed malaria", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical deaths
                            
                             stpdtm = connectDB.createStatement();
//                            java.sql.ResultSet rsppx = stppx.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'discharge' AND pat_category ILIKE 'IP'");
//                            java.sql.ResultSet rspdtm = stpdtm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%Medical%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            
                            rspdtm = stpdtm.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%Medical%')");
                            while (rspdtm.next()) {
                                dtMedical = rspdtm.getFloat(1);
                            }
                            
                            //surgical deaths
                            
                             stpdts = connectDB.createStatement();
//                            java.sql.ResultSet rspdts = stpdts.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdts = stpdts.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')");
                            while (rspdts.next()) {
                                dtSurgical = rspdts.getFloat(1);
                            }
                            
                            //obst/gyn deaths
                            
                             stpdto = connectDB.createStatement();
//                            java.sql.ResultSet rspdto = stpdto.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                             rspdto = stpdto.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')");
                            while (rspdto.next()) {
                                dtObs = rspdto.getFloat(1);
                            }
                            
                            //Paediatric deaths
                            
                             stpdtp = connectDB.createStatement();
//                            java.sql.ResultSet rspdtp = stpdtp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                             rspdtp = stpdtp.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')");
                            while (rspdtp.next()) {
                                dtPaed = rspdtp.getFloat(1);
                            }
                            
                            //maternity deaths
                            
                             stpdtmat = connectDB.createStatement();
//                            java.sql.ResultSet rspdtmat = stpdtmat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdtmat = stpdtmat.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')");
                            while (rspdtmat.next()) {
                                dtMat = rspdtmat.getFloat(1);
                            }
                            
                            //eye deaths
                            
                            stpdte = connectDB.createStatement();
//                            java.sql.ResultSet rspdte = stpdte.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdte = stpdte.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')");
                            while (rspdte.next()) {
                                dtEye = rspdte.getFloat(1);
                            }
                            
                            //newborn deaths
                            
                             stpdtn = connectDB.createStatement();
//                            java.sql.ResultSet rspdtn = stpdtn.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdtn = stpdtn.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')");
                            while (rspdtn.next()) {
                                dtNewborn = rspdtn.getFloat(1);
                            }
                            
                            //orthopaedic deaths
                            
                            stpdtort = connectDB.createStatement();
//                            java.sql.ResultSet rspdtort = stpdtort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdtort = stpdtort.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')");
                            while (rspdtort.next()) {
                                dtOrtho = rspdtort.getFloat(1);
                            }
                            
                            //isolation deaths
                            
                            stpdti = connectDB.createStatement();
//                            java.sql.ResultSet rspdti = stpdti.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdti = stpdti.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')");
                            while (rspdti.next()) {
                                dtIsolation = rspdti.getFloat(1);
                            }
                            
                            //amenity deaths
                            
                             stpdta = connectDB.createStatement();
//                            java.sql.ResultSet rspdta = stpdta.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            rspdta = stpdta.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')");
                            while (rspdta.next()) {
                                dtAmenity = rspdta.getFloat(1);
                            }
                            
                            //psychiatry deaths
                            
                             stpdtpsy = connectDB.createStatement();
//                            java.sql.ResultSet rspdtpsy = stpdtpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                             rspdtpsy = stpdtpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  input_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')");
                            while (rspdtpsy.next()) {
                                disPsyc = rspdtpsy.getFloat(1);
                            }
                            
                            //other deaths
                            
                             stpdtoth = connectDB.createStatement();
                             rspdtoth = stpdtoth.executeQuery("SELECT COUNT(patient_no) FROM hp_mortuary"
                                    + " WHERE death_cause ilike '%malaria%' and  date_of_death::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(place_of_death) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')");
//                            java.sql.ResultSet rspdtoth = stpdtoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
//                                    + " WHERE discharge_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
//                                    + "AND discharge = true "
//                                            + "AND transaction_type ILIKE 'DIED'");
                            while (rspdtoth.next()) {
                                dtOther = rspdtoth.getFloat(1);
                            }
                            
                            dtTotal = dtMedical+dtSurgical+dtObs+dtPaed+dtMat+dtEye+dtNewborn+dtOrtho+dtIsolation+dtAmenity+dtPsyc+dtOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dtTotal)), pFontHeader1);
                            table2.addCell(phrase);
//Absconders

                            /*java.sql.Statement stppa = connectDB.createStatement();
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
                            tpTotal = tpTotal + pTotal;*/

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.4", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Abscondees", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            //medical abscondees
                            
                            java.sql.Statement stpabsm = connectDB.createStatement();
//                            java.sql.ResultSet rsppx = stppx.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
//                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
//                                    + "pat_age > 5 AND (ward_name NOT ILIKE '%Mat%' OR ward_name NOT ILIKE '%Ame%' OR ward_name NOT ILIKE '%paed%') "
//                                    + "AND admission_outcome ILIKE 'discharge' AND pat_category ILIKE 'IP'");
                            java.sql.ResultSet rspabsm = stpabsm.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsm.next()) {
                                absMedical = rspabsm.getFloat(1);
                            }
                            
                            //surgical abscondees
                            
                            java.sql.Statement stpabss = connectDB.createStatement();
                            java.sql.ResultSet rspabss = stpabss.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabss.next()) {
                                absSurgical = rspabss.getFloat(1);
                            }
                            
                            //obst/gyn abscondees
                            
                            java.sql.Statement stpabso = connectDB.createStatement();
                            java.sql.ResultSet rspabso = stpabso.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabso.next()) {
                                absObs = rspabso.getFloat(1);
                            }
                            
                            //Paediatric abscondees
                            
                            java.sql.Statement stpabsp = connectDB.createStatement();
                            java.sql.ResultSet rspabsp = stpabsp.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsp.next()) {
                                absPaed = rspabsp.getFloat(1);
                            }
                            
                            //maternity abscondees
                            
                            java.sql.Statement stpabsmat = connectDB.createStatement();
                            java.sql.ResultSet rspabsmat = stpabsmat.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsmat.next()) {
                                absMat = rspabsmat.getFloat(1);
                            }
                            
                            //eye deaths
                            
                            java.sql.Statement stpabse = connectDB.createStatement();
                            java.sql.ResultSet rspabse = stpabse.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabse.next()) {
                                absEye = rspabse.getFloat(1);
                            }
                            
                            //newborn abscondees
                            
                            java.sql.Statement stpabsn = connectDB.createStatement();
                            java.sql.ResultSet rspabsn = stpabsn.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsn.next()) {
                                absNewborn = rspabsn.getFloat(1);
                            }
                            
                            //orthopaedic abscondees
                            
                            java.sql.Statement stpabsort = connectDB.createStatement();
                            java.sql.ResultSet rspabsort = stpabsort.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsort.next()) {
                                absOrtho = rspabsort.getFloat(1);
                            }
                            
                            //isolation abscondees
                            
                            java.sql.Statement stpabsi = connectDB.createStatement();
                            java.sql.ResultSet rspabsi = stpabsi.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsi.next()) {
                                absIsolation = rspabsi.getFloat(1);
                            }
                            
                            //amenity abscondees
                            
                            java.sql.Statement stpabsa = connectDB.createStatement();
                            java.sql.ResultSet rspabsa = stpabsa.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsa.next()) {
                                absAmenity = rspabsa.getFloat(1);
                            }
                            
                            //psychiatry abscondees
                            
                            java.sql.Statement stpabspsy = connectDB.createStatement();
                            java.sql.ResultSet rspabspsy = stpabspsy.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabspsy.next()) {
                                disPsyc = rspabspsy.getFloat(1);
                            }
                            
                            //other abscondees
                            
                            java.sql.Statement stpabsoth = connectDB.createStatement();
                            java.sql.ResultSet rspabsoth = stpabsoth.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_diagnosis"
                                    + " WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND admission_outcome ILIKE 'Absco' AND pat_category ILIKE 'IP'");
                            while (rspabsoth.next()) {
                                absOther = rspabsoth.getFloat(1);
                            }
                            
                            absTotal = absMedical+absSurgical+absObs+absPaed+absMat+absEye+absNewborn+absOrtho+absIsolation+absAmenity+absPsyc+absOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(absTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.5", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL DISCHARGES, DEATHS, etc", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disMedical+dtMedical+absMedical)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disSurgical+disSurgical+absSurgical)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disObs+dtObs+absObs)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disPaed+dtPaed+absPaed)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disMat+dtMat+absPaed)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disEye+dtEye+absEye)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disNewborn+dtNewborn+absNewborn)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disOrtho+dtOrtho+absOrtho)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disIsolation+dtIsolation+absIsolation)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disAmenity+dtAmenity+absAmenity)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disPsyc+dtPsyc+absPsyc)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disOther+dtOther+absOther)), pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disTotal+dtTotal+absTotal)), pFontHeader);
                            table2.addCell(phrase);
//Admissions
                            float gAdmin = 0;
                            float gPaeds = 0;
                            float gMat = 0;
                            float gAmenity = 0;
                            float gTotal = 0;
                            
                            float underMedical = 0;
                            float underSurgical = 0;
                            float underObs = 0;
                            float underPaed = 0;
                            float underMat = 0;
                            float underEye = 0;
                            float underNewborn = 0;
                            float underOrtho = 0;
                            float underIsolation = 0;
                            float underAmenity = 0;
                            float underPsyc = 0;
                            float underOther = 0;
                            float underTotal = 0;
                            
                            float overMedical = 0;
                            float overSurgical = 0;
                            float overObs = 0;
                            float overPaed = 0;
                            float overMat = 0;
                            float overEye = 0;
                            float overNewborn = 0;
                            float overOrtho = 0;
                            float overIsolation = 0;
                            float overAmenity = 0;
                            float overPsyc = 0;
                            float overOther = 0;
                            float overTotal = 0;
                            
                            

                            /*java.sql.Statement sta = connectDB.createStatement();
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
                            */
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.6", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(14);
                            phrase = new Phrase("Admissions", pFontHeader);
                            table2.addCell(phrase);
                            
                            
                            
                            
                            //Admissions  0-28 days
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.6a", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Admission 0-28 days", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical admissions (0-28)
                            
                            java.sql.Statement stpunderm = connectDB.createStatement();
                           java.sql.ResultSet rspunderm = stpunderm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28 ");
                            while (rspunderm.next()) {
                                underMedical = rspunderm.getFloat(1);
                            }
                            
                            //surgical admissions (0-28)
                            
                            java.sql.Statement stpunders = connectDB.createStatement();
                            java.sql.ResultSet rspunders = stpunders.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND  (discharge_date::date-date_admitted) <= 28 ");
                            while (rspunders.next()) {
                                underSurgical = rspunders.getFloat(1);
                            }
                            
                            //obst/gyn admissions (0-28)
                            
                            java.sql.Statement stpundero = connectDB.createStatement();
                            java.sql.ResultSet rspundero = stpundero.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND  (discharge_date::date-date_admitted) <= 28 ");
                            while (rspundero.next()) {
                                underObs = rspundero.getFloat(1);
                            }
                            
                            //Paediatric admissions (0-28)
                            
                            java.sql.Statement stpunderp = connectDB.createStatement();
                            java.sql.ResultSet rspunderp = stpunderp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND  (discharge_date::date-date_admitted) <= 28 ");
                            while (rspunderp.next()) {
                                underPaed = rspunderp.getFloat(1);
                            }
                            
                            //maternity admissions (0-28)
                            
                            java.sql.Statement stpundermat = connectDB.createStatement();
                            java.sql.ResultSet rspundermat = stpundermat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND  (discharge_date::date-date_admitted) <= 28");
                            while (rspundermat.next()) {
                                underMat = rspundermat.getFloat(1);
                            }
                            
                            //eye admissions (under 5)
                            
                            java.sql.Statement stpundere = connectDB.createStatement();
                            java.sql.ResultSet rspundere = stpundere.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND  (discharge_date::date-date_admitted) <= 28 ");
                            while (rspundere.next()) {
                                underEye = rspundere.getFloat(1);
                            }
                            
                            //newborn admissions (0-28 days)
                            
                            java.sql.Statement stpundern = connectDB.createStatement();
                            java.sql.ResultSet rspundern = stpundern.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28");
                            while (rspundern.next()) {
                                underNewborn = rspundern.getFloat(1);
                            }
                            
                            //orthopaedic admissions (under 5)
                            
                            java.sql.Statement stpunderort = connectDB.createStatement();
                            java.sql.ResultSet rspunderort = stpunderort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28");
                            while (rspunderort.next()) {
                                underOrtho = rspunderort.getFloat(1);
                            }
                            
                            //isolation admissions (under 5)
                            
                            java.sql.Statement stpunderi = connectDB.createStatement();
                            java.sql.ResultSet rspunderi = stpunderi.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28");
                            while (rspunderi.next()) {
                                underIsolation = rspunderi.getFloat(1);
                            }
                            
                            //amenity admissions (under 5)
                            
                            java.sql.Statement stpundera = connectDB.createStatement();
                            java.sql.ResultSet rspundera = stpundera.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28");
                            while (rspundera.next()) {
                                underAmenity = rspundera.getFloat(1);
                            }
                            
                            //psychiatry admissions (under 5)
                            
                            java.sql.Statement stpunderpsy = connectDB.createStatement();
                            java.sql.ResultSet rspunderpsy = stpunderpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28");
                            while (rspunderpsy.next()) {
                                underPsyc = rspunderpsy.getFloat(1);
                            }
                            
                            //other admissions (under 5)
                            
                            java.sql.Statement stpunderoth = connectDB.createStatement();
                            java.sql.ResultSet rspunderoth = stpunderoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND (discharge_date::date-date_admitted) <= 28");
                            while (rspunderoth.next()) {
                                underOther = rspunderoth.getFloat(1);
                            }
                            
                            underTotal = underMedical+underSurgical+underObs+underPaed+underMat+underEye+underNewborn+underOrtho+underIsolation+underAmenity+underPsyc+underOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //------------------------------------------------------------------
                            
                            //admission Under Five
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.6b", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Admission Under Five", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical admissions (under 5)
                            
                            stpunderm = connectDB.createStatement();
                           rspunderm = stpunderm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND pat_age <= 5");
                            while (rspunderm.next()) {
                                underMedical = rspunderm.getFloat(1);
                            }
                            
                            //surgical admissions (under 5)
                            
                            stpunders = connectDB.createStatement();
                            rspunders = stpunders.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND pat_age <= 5");
                            while (rspunders.next()) {
                                underSurgical = rspunders.getFloat(1);
                            }
                            
                            //obst/gyn admissions (under 5)
                            
                            stpundero = connectDB.createStatement();
                            rspundero = stpundero.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND pat_age <= 5");
                            while (rspundero.next()) {
                                underObs = rspundero.getFloat(1);
                            }
                            
                            //Paediatric admissions (under 5)
                            
                            stpunderp = connectDB.createStatement();
                            rspunderp = stpunderp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND pat_age <= 5");
                            while (rspunderp.next()) {
                                underPaed = rspunderp.getFloat(1);
                            }
                            
                            //maternity admissions (under 5)
                            
                            stpundermat = connectDB.createStatement();
                            rspundermat = stpundermat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND pat_age <= 5");
                            while (rspundermat.next()) {
                                underMat = rspundermat.getFloat(1);
                            }
                            
                            //eye admissions (under 5)
                            
                            stpundere = connectDB.createStatement();
                            rspundere = stpundere.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND pat_age <= 5");
                            while (rspundere.next()) {
                                underEye = rspundere.getFloat(1);
                            }
                            
                            //newborn admissions (under 5)
                            
                            stpundern = connectDB.createStatement();
                            rspundern = stpundern.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND pat_age <= 5");
                            while (rspundern.next()) {
                                underNewborn = rspundern.getFloat(1);
                            }
                            
                            //orthopaedic admissions (under 5)
                            
                            stpunderort = connectDB.createStatement();
                            rspunderort = stpunderort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND pat_age <= 5");
                            while (rspunderort.next()) {
                                underOrtho = rspunderort.getFloat(1);
                            }
                            
                            //isolation admissions (under 5)
                            
                            stpunderi = connectDB.createStatement();
                            rspunderi = stpunderi.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND pat_age <= 5");
                            while (rspunderi.next()) {
                                underIsolation = rspunderi.getFloat(1);
                            }
                            
                            //amenity admissions (under 5)
                            
                            stpundera = connectDB.createStatement();
                            rspundera = stpundera.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND pat_age <= 5");
                            while (rspundera.next()) {
                                underAmenity = rspundera.getFloat(1);
                            }
                            
                            //psychiatry admissions (under 5)
                            
                            stpunderpsy = connectDB.createStatement();
                            rspunderpsy = stpunderpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND pat_age <= 5");
                            while (rspunderpsy.next()) {
                                underPsyc = rspunderpsy.getFloat(1);
                            }
                            
                            //other admissions (under 5)
                            
                            stpunderoth = connectDB.createStatement();
                            rspunderoth = stpunderoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND pat_age <= 5");
                            while (rspunderoth.next()) {
                                underOther = rspunderoth.getFloat(1);
                            }
                            
                            underTotal = underMedical+underSurgical+underObs+underPaed+underMat+underEye+underNewborn+underOrtho+underIsolation+underAmenity+underPsyc+underOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //Admission Over Five
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.9c", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Admission Over Five", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical admissions (over 5)
                            
                            java.sql.Statement stpoverm = connectDB.createStatement();
                           java.sql.ResultSet rspoverm = stpoverm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND pat_age > 5");
                            while (rspoverm.next()) {
                                overMedical = rspoverm.getFloat(1);
                            }
                            
                            //surgical admissions (over 5)
                            
                            java.sql.Statement stpovers = connectDB.createStatement();
                            java.sql.ResultSet rspovers = stpovers.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND pat_age > 5");
                            while (rspovers.next()) {
                                overSurgical = rspovers.getFloat(1);
                            }
                            
                            //obst/gyn admissions (over 5)
                            
                            java.sql.Statement stpovero = connectDB.createStatement();
                            java.sql.ResultSet rspovero = stpovero.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND pat_age > 5");
                            while (rspovero.next()) {
                                overObs = rspovero.getFloat(1);
                            }
                            
                            //Paediatric admissions (over 5)
                            
                            java.sql.Statement stpoverp = connectDB.createStatement();
                            java.sql.ResultSet rspoverp = stpoverp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND pat_age > 5");
                            while (rspoverp.next()) {
                                overPaed = rspoverp.getFloat(1);
                            }
                            
                            //maternity admissions (over 5)
                            
                            java.sql.Statement stpovermat = connectDB.createStatement();
                            java.sql.ResultSet rspovermat = stpovermat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND pat_age > 5");
                            while (rspovermat.next()) {
                                overMat = rspovermat.getFloat(1);
                            }
                            
                            //eye admissions (over 5)
                            
                            java.sql.Statement stpovere = connectDB.createStatement();
                            java.sql.ResultSet rspovere = stpovere.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND pat_age > 5");
                            while (rspovere.next()) {
                                overEye = rspovere.getFloat(1);
                            }
                            
                            //newborn admissions (over 5)
                            
                            java.sql.Statement stpovern = connectDB.createStatement();
                            java.sql.ResultSet rspovern = stpovern.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND pat_age > 5");
                            while (rspovern.next()) {
                                overNewborn = rspovern.getFloat(1);
                            }
                            
                            //orthopaedic admissions (over 5)
                            
                            java.sql.Statement stpoverort = connectDB.createStatement();
                            java.sql.ResultSet rspoverort = stpoverort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND pat_age > 5");
                            while (rspoverort.next()) {
                                overOrtho = rspoverort.getFloat(1);
                            }
                            
                            //isolation admissions (over 5)
                            
                            java.sql.Statement stpoveri = connectDB.createStatement();
                            java.sql.ResultSet rspoveri = stpoveri.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND pat_age > 5");
                            while (rspoveri.next()) {
                                overIsolation = rspoveri.getFloat(1);
                            }
                            
                            //amenity admissions (over 5)
                            
                            java.sql.Statement stpovera = connectDB.createStatement();
                            java.sql.ResultSet rspovera = stpovera.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND pat_age > 5");
                            while (rspovera.next()) {
                                overAmenity = rspovera.getFloat(1);
                            }
                            
                            //psychiatry admissions (over 5)
                            
                            java.sql.Statement stpoverpsy = connectDB.createStatement();
                            java.sql.ResultSet rspoverpsy = stpoverpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND pat_age > 5");
                            while (rspoverpsy.next()) {
                                overPsyc = rspoverpsy.getFloat(1);
                            }
                            
                            //other admissions (over 5)
                            
                            java.sql.Statement stpoveroth = connectDB.createStatement();
                            java.sql.ResultSet rspoveroth = stpoveroth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND pat_age > 5");
                            while (rspoveroth.next()) {
                                overOther = rspoveroth.getFloat(1);
                            }
                            
                            overTotal = overMedical+overSurgical+overObs+overPaed+overMat+overEye+overNewborn+overOrtho+overIsolation+overAmenity+overPsyc+overOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            
                            //--------------------------------------------------------------------------
                            
                            //------------------------------------------------------------------
                            
                            //admission Under Five with sever malaria
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.6d", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Admission Under Five", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical admissions (under 5)
                            
                            stpunderm = connectDB.createStatement();
                           rspunderm = stpunderm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%' ");
                            while (rspunderm.next()) {
                                underMedical = rspunderm.getFloat(1);
                            }
                            
                            //surgical admissions (under 5)
                            
                            stpunders = connectDB.createStatement();
                            rspunders = stpunders.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspunders.next()) {
                                underSurgical = rspunders.getFloat(1);
                            }
                            
                            //obst/gyn admissions (under 5)
                            
                            stpundero = connectDB.createStatement();
                            rspundero = stpundero.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspundero.next()) {
                                underObs = rspundero.getFloat(1);
                            }
                            
                            //Paediatric admissions (under 5)
                            
                            stpunderp = connectDB.createStatement();
                            rspunderp = stpunderp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspunderp.next()) {
                                underPaed = rspunderp.getFloat(1);
                            }
                            
                            //maternity admissions (under 5)
                            
                            stpundermat = connectDB.createStatement();
                            rspundermat = stpundermat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspundermat.next()) {
                                underMat = rspundermat.getFloat(1);
                            }
                            
                            //eye admissions (under 5)
                            
                            stpundere = connectDB.createStatement();
                            rspundere = stpundere.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspundere.next()) {
                                underEye = rspundere.getFloat(1);
                            }
                            
                            //newborn admissions (under 5)
                            
                            stpundern = connectDB.createStatement();
                            rspundern = stpundern.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspundern.next()) {
                                underNewborn = rspundern.getFloat(1);
                            }
                            
                            //orthopaedic admissions (under 5)
                            
                            stpunderort = connectDB.createStatement();
                            rspunderort = stpunderort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND pat_age <= 5");
                            while (rspunderort.next()) {
                                underOrtho = rspunderort.getFloat(1);
                            }
                            
                            //isolation admissions (under 5)
                            
                            stpunderi = connectDB.createStatement();
                            rspunderi = stpunderi.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspunderi.next()) {
                                underIsolation = rspunderi.getFloat(1);
                            }
                            
                            //amenity admissions (under 5)
                            
                            stpundera = connectDB.createStatement();
                            rspundera = stpundera.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspundera.next()) {
                                underAmenity = rspundera.getFloat(1);
                            }
                            
                            //psychiatry admissions (under 5)
                            
                            stpunderpsy = connectDB.createStatement();
                            rspunderpsy = stpunderpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspunderpsy.next()) {
                                underPsyc = rspunderpsy.getFloat(1);
                            }
                            
                            //other admissions (under 5)
                            
                            stpunderoth = connectDB.createStatement();
                            rspunderoth = stpunderoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND pat_age <= 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspunderoth.next()) {
                                underOther = rspunderoth.getFloat(1);
                            }
                            
                            underTotal = underMedical+underSurgical+underObs+underPaed+underMat+underEye+underNewborn+underOrtho+underIsolation+underAmenity+underPsyc+underOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //Admission Over Five
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.9e", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Admission Over Five with sever malaria", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical admissions (over 5)
                            
                            stpoverm = connectDB.createStatement();
                           rspoverm = stpoverm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspoverm.next()) {
                                overMedical = rspoverm.getFloat(1);
                            }
                            
                            //surgical admissions (over 5)
                            
                            stpovers = connectDB.createStatement();
                             rspovers = stpovers.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspovers.next()) {
                                overSurgical = rspovers.getFloat(1);
                            }
                            
                            //obst/gyn admissions (over 5)
                            
                            stpovero = connectDB.createStatement();
                            rspovero = stpovero.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspovero.next()) {
                                overObs = rspovero.getFloat(1);
                            }
                            
                            //Paediatric admissions (over 5)
                            
                             stpoverp = connectDB.createStatement();
                             rspoverp = stpoverp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspoverp.next()) {
                                overPaed = rspoverp.getFloat(1);
                            }
                            
                            //maternity admissions (over 5)
                            
                            stpovermat = connectDB.createStatement();
                             rspovermat = stpovermat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspovermat.next()) {
                                overMat = rspovermat.getFloat(1);
                            }
                            
                            //eye admissions (over 5)
                            
                            stpovere = connectDB.createStatement();
                            rspovere = stpovere.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspovere.next()) {
                                overEye = rspovere.getFloat(1);
                            }
                            
                            //newborn admissions (over 5)
                            
                            stpovern = connectDB.createStatement();
                            rspovern = stpovern.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspovern.next()) {
                                overNewborn = rspovern.getFloat(1);
                            }
                            
                            //orthopaedic admissions (over 5)
                            
                            stpoverort = connectDB.createStatement();
                            rspoverort = stpoverort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspoverort.next()) {
                                overOrtho = rspoverort.getFloat(1);
                            }
                            
                            //isolation admissions (over 5)
                            
                            stpoveri = connectDB.createStatement();
                            rspoveri = stpoveri.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspoveri.next()) {
                                overIsolation = rspoveri.getFloat(1);
                            }
                            
                            //amenity admissions (over 5)
                            
                            stpovera = connectDB.createStatement();
                            rspovera = stpovera.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspovera.next()) {
                                overAmenity = rspovera.getFloat(1);
                            }
                            
                            //psychiatry admissions (over 5)
                            
                            stpoverpsy = connectDB.createStatement();
                            rspoverpsy = stpoverpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspoverpsy.next()) {
                                overPsyc = rspoverpsy.getFloat(1);
                            }
                            
                            //other admissions (over 5)
                            
                            stpoveroth = connectDB.createStatement();
                            rspoveroth = stpoveroth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND pat_age > 5 AND diagnosis1 ilike '%severe malaria%'");
                            while (rspoveroth.next()) {
                                overOther = rspoveroth.getFloat(1);
                            }
                            
                            overTotal = overMedical+overSurgical+overObs+overPaed+overMat+overEye+overNewborn+overOrtho+overIsolation+overAmenity+overPsyc+overOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //============================================================================

                            //Paroles
                            
                            float parMedical = 0;
                            float parSurgical = 0;
                            float parObs = 0;
                            float parPaed = 0;
                            float parMat = 0;
                            float parEye = 0;
                            float parNewborn = 0;
                            float parOrtho = 0;
                            float parIsolation = 0;
                            float parAmenity = 0;
                            float parPsyc = 0;
                            float parOther = 0;
                            float parTotal = 0;

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.7", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Paroles", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical paroles
                            
                            java.sql.Statement stpparm = connectDB.createStatement();
                           java.sql.ResultSet rspparm = stpparm.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparm.next()) {
                                parMedical = rspparm.getFloat(1);
                            }
                            
                            //surgical paroles
                            
                            java.sql.Statement stppars = connectDB.createStatement();
                            java.sql.ResultSet rsppars = stppars.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rsppars.next()) {
                                parSurgical = rsppars.getFloat(1);
                            }
                            
                            //obst/gyn paroles
                            
                            java.sql.Statement stpparo = connectDB.createStatement();
                            java.sql.ResultSet rspparo = stpparo.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparo.next()) {
                                parObs = rspparo.getFloat(1);
                            }
                            
                            //Paediatric paroles
                            
                            java.sql.Statement stpparp = connectDB.createStatement();
                            java.sql.ResultSet rspparp = stpparp.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparp.next()) {
                                parPaed = rspparp.getFloat(1);
                            }
                            
                            //maternity paroles
                            
                            java.sql.Statement stpparmat = connectDB.createStatement();
                            java.sql.ResultSet rspparmat = stpparmat.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparmat.next()) {
                                parMat = rspparmat.getFloat(1);
                            }
                            
                            //eye paroles
                            
                            java.sql.Statement stppare = connectDB.createStatement();
                            java.sql.ResultSet rsppare = stppare.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rsppare.next()) {
                                parEye = rsppare.getFloat(1);
                            }
                            
                            //newborn paroles
                            
                            java.sql.Statement stpparn = connectDB.createStatement();
                            java.sql.ResultSet rspparn = stpparn.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparn.next()) {
                                parNewborn = rspparn.getFloat(1);
                            }
                            
                            //orthopaedic paroles
                            
                            java.sql.Statement stpparort = connectDB.createStatement();
                            java.sql.ResultSet rspparort = stpparort.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparort.next()) {
                                parOrtho = rspparort.getFloat(1);
                            }
                            
                            //isolation paroles
                            
                            java.sql.Statement stppari = connectDB.createStatement();
                            java.sql.ResultSet rsppari = stppari.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rsppari.next()) {
                                parIsolation = rsppari.getFloat(1);
                            }
                            
                            //amenity paroles
                            
                            java.sql.Statement stppara = connectDB.createStatement();
                            java.sql.ResultSet rsppara = stppara.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rsppara.next()) {
                                parAmenity = rsppara.getFloat(1);
                            }
                            
                            //psychiatry paroles
                            
                            java.sql.Statement stpparpsy = connectDB.createStatement();
                            java.sql.ResultSet rspparpsy = stpparpsy.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparpsy.next()) {
                                parPsyc = rspparpsy.getFloat(1);
                            }
                            
                            //other paroles
                            
                            java.sql.Statement stpparoth = connectDB.createStatement();
                            java.sql.ResultSet rspparoth = stpparoth.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND wing ILIKE 'Prisoner'");
                            while (rspparoth.next()) {
                                parOther = rspparoth.getFloat(1);
                            }
                            
                            parTotal = parMedical+parSurgical+parObs+parPaed+parMat+parEye+parNewborn+parOrtho+parIsolation+parAmenity+parPsyc+parOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(parTotal)), pFontHeader1);
                            table2.addCell(phrase);

//Occupied bed days - NHIF MEMBERS
                            /*java.sql.Statement stp4 = connectDB.createStatement();
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

                            }*/

                            float nhifMedical = 0;
                            float nhifSurgical = 0;
                            float nhifObs = 0;
                            float nhifPaed = 0;
                            float nhifMat = 0;
                            float nhifEye = 0;
                            float nhifNewborn = 0;
                            float nhifOrtho = 0;
                            float nhifIsolation = 0;
                            float nhifAmenity = 0;
                            float nhifPsyc = 0;
                            float nhifOther = 0;
                            float nhifTotal = 0;
                            
                            float nonhifMedical = 0;
                            float nonhifSurgical = 0;
                            float nonhifObs = 0;
                            float nonhifPaed = 0;
                            float nonhifMat = 0;
                            float nonhifEye = 0;
                            float nonhifNewborn = 0;
                            float nonhifOrtho = 0;
                            float nonhifIsolation = 0;
                            float nonhifAmenity = 0;
                            float nonhifPsyc = 0;
                            float nonhifOther = 0;
                            float nonhifTotal = 0;
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.8", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Occupied Bed Days - NHIF Members", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            //medical obd nhif
                            
                            java.sql.Statement stpnhifm = connectDB.createStatement();
                           java.sql.ResultSet rspnhifm = stpnhifm.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifm.next()) {
                                nhifMedical = nhifMedical + rspnhifm.getFloat(5);
                            }
                            
                            //surgical obd nhif
                            
                            java.sql.Statement stpnhifs = connectDB.createStatement();
                           java.sql.ResultSet rspnhifs = stpnhifs.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifs.next()) {
                                nhifSurgical = nhifSurgical + rspnhifs.getFloat(5);
                            }
                            
                            // obst/gyn obd nhif
                            
                            java.sql.Statement stpnhifo = connectDB.createStatement();
                           java.sql.ResultSet rspnhifo = stpnhifo.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifo.next()) {
                                nhifObs = nhifObs + rspnhifo.getFloat(5);
                            }
                            
                            // paediatric obd nhif
                            
                            java.sql.Statement stpnhifp = connectDB.createStatement();
                           java.sql.ResultSet rspnhifp = stpnhifp.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifo.next()) {
                                nhifPaed = nhifPaed + rspnhifp.getFloat(5);
                            }
                            
                            // materity obd nhif
                            
                            java.sql.Statement stpnhifmat = connectDB.createStatement();
                           java.sql.ResultSet rspnhifmat = stpnhifmat.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifmat.next()) {
                                nhifMat = nhifMat + rspnhifmat.getFloat(5);
                            }
                            
                            // eye obd nhif
                            
                            java.sql.Statement stpnhife = connectDB.createStatement();
                           java.sql.ResultSet rspnhife = stpnhife.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhife.next()) {
                                nhifEye = nhifEye + rspnhife.getFloat(5);
                            }
                            
                            // newborn obd nhif
                            
                            java.sql.Statement stpnhifn = connectDB.createStatement();
                           java.sql.ResultSet rspnhifn = stpnhifn.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifn.next()) {
                                nhifNewborn = nhifNewborn + rspnhifn.getFloat(5);
                            }
                            
                            // orthopaedic obd nhif
                            
                            java.sql.Statement stpnhifort = connectDB.createStatement();
                           java.sql.ResultSet rspnhifort = stpnhifort.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifort.next()) {
                                nhifOrtho = nhifOrtho + rspnhifort.getFloat(5);
                            }
                            
                            // isolation obd nhif
                            
                            java.sql.Statement stpnhifi = connectDB.createStatement();
                           java.sql.ResultSet rspnhifi = stpnhifi.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifi.next()) {
                                nhifIsolation = nhifIsolation + rspnhifi.getFloat(5);
                            }
                            
                            // amenity obd nhif
                            
                            java.sql.Statement stpnhifa = connectDB.createStatement();
                           java.sql.ResultSet rspnhifa = stpnhifa.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifa.next()) {
                                nhifAmenity = nhifAmenity + rspnhifa.getFloat(5);
                            }
                            
                            // psychiatry obd nhif
                            
                            java.sql.Statement stpnhifpsy = connectDB.createStatement();
                           java.sql.ResultSet rspnhifpsy = stpnhifpsy.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifpsy.next()) {
                                nhifPsyc = nhifPsyc + rspnhifpsy.getFloat(5);
                            }
                            
                            // other obd nhif
                            
                            java.sql.Statement stpnhifot = connectDB.createStatement();
                           java.sql.ResultSet rspnhifot = stpnhifot.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%') "
                                    + "AND ad.ward_code ILIKE '%NHIF%'");
                            while (rspnhifot.next()) {
                                nhifOther = nhifOther + rspnhifot.getFloat(5);
                            }
                            
                            nhifTotal = nhifMedical+nhifSurgical+nhifObs+nhifPaed+nhifMat+nhifEye+nhifNewborn+nhifOrtho+nhifIsolation+nhifAmenity+nhifPsyc+nhifOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nhifTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            // obd non nhif members
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.9", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Occupied Bed Days - NON NHIF Members", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            //medical obd nonhif
                            
                            java.sql.Statement stpnonhifm = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifm = stpnonhifm.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifm.next()) {
                                nonhifMedical = nonhifMedical + rspnonhifm.getFloat(5);
                            }
                            
                            //surgical obd nonhif
                            
                            java.sql.Statement stpnonhifs = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifs = stpnonhifs.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifs.next()) {
                                nonhifSurgical = nonhifSurgical + rspnonhifs.getFloat(5);
                            }
                            
                            // obst/gyn obd nonhif
                            
                            java.sql.Statement stpnonhifo = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifo = stpnonhifo.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifo.next()) {
                                nonhifObs = nonhifObs + rspnonhifo.getFloat(5);
                            }
                            
                            // paediatric obd nonhif
                            
                            java.sql.Statement stpnonhifp = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifp = stpnonhifp.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifo.next()) {
                                nonhifPaed = nonhifPaed + rspnonhifp.getFloat(5);
                            }
                            
                            // materNity obd nonhif
                            
                            java.sql.Statement stpnonhifmat = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifmat = stpnonhifmat.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifmat.next()) {
                                nonhifMat = nonhifMat + rspnonhifmat.getFloat(5);
                            }
                            
                            // eye obd nonhif
                            
                            java.sql.Statement stpnonhife = connectDB.createStatement();
                           java.sql.ResultSet rspnonhife = stpnonhife.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhife.next()) {
                                nonhifEye = nonhifEye + rspnonhife.getFloat(5);
                            }
                            
                            // newborn obd nonhif
                            
                            java.sql.Statement stpnonhifn = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifn = stpnonhifn.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifn.next()) {
                                nonhifNewborn = nonhifNewborn + rspnonhifn.getFloat(5);
                            }
                            
                            // orthopaedic obd nonhif
                            
                            java.sql.Statement stpnonhifort = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifort = stpnonhifort.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifort.next()) {
                                nonhifOrtho = nonhifOrtho + rspnonhifort.getFloat(5);
                            }
                            
                            // isolation obd nonhif
                            
                            java.sql.Statement stpnonhifi = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifi = stpnonhifi.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifi.next()) {
                                nonhifIsolation = nonhifIsolation + rspnonhifi.getFloat(5);
                            }
                            
                            // amenity obd nonhif
                            
                            java.sql.Statement stpnonhifa = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifa = stpnonhifa.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifa.next()) {
                                nonhifAmenity = nonhifAmenity + rspnonhifa.getFloat(5);
                            }
                            
                            // psychiatry obd nonhif
                            
                            java.sql.Statement stpnonhifpsy = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifpsy = stpnonhifpsy.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%') "
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifpsy.next()) {
                                nonhifPsyc = nonhifPsyc + rspnonhifpsy.getFloat(5);
                            }
                            
                            // other obd nonhif
                            
                            java.sql.Statement stpnonhifot = connectDB.createStatement();
                           java.sql.ResultSet rspnonhifot = stpnonhifot.executeQuery("select DISTINCT ad.date_admitted,(ad.patient_no),initcap(ad.patient_name),dc.discharge_date ,dc.discharge_date-ad.date_admitted from hp_admission ad ,hp_patient_discharge dc WHERE ad.date_admitted BETWEEN '"+beginDate+"' AND '"+endDate+"' and dc.discharge_date-ad.date_admitted >0 and dc.patient_no = ad.patient_no AND "
                                    + " upper(ad.ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%')"
                                    + "AND ad.ward_code NOT ILIKE '%NHIF%'");
                            while (rspnonhifot.next()) {
                                nonhifOther = nonhifOther + rspnonhifot.getFloat(5);
                            }
                            
                            nonhifTotal = nonhifMedical+nonhifSurgical+nonhifObs+nonhifPaed+nonhifMat+nonhifEye+nonhifNewborn+nonhifOrtho+nonhifIsolation+nonhifAmenity+nonhifPsyc+nonhifOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(nonhifTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            //Well pple
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.10", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
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
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //BEDS
                            
                            float bauthMedical = 0;
                            float bauthSurgical = 0;
                            float bauthObs = 0;
                            float bauthPaed = 0;
                            float bauthMat = 0;
                            float bauthEye = 0;
                            float bauthNewborn = 0;
                            float bauthOrtho = 0;
                            float bauthIsolation = 0;
                            float bauthAmenity = 0;
                            float bauthPsyc = 0;
                            float bauthOther = 0;
                            float bauthTotal = 0;
                            
                            float bactMedical = 0;
                            float bactSurgical = 0;
                            float bactObs = 0;
                            float bactPaed = 0;
                            float bactMat = 0;
                            float bactEye = 0;
                            float bactNewborn = 0;
                            float bactOrtho = 0;
                            float bactIsolation = 0;
                            float bactAmenity = 0;
                            float bactPsyc = 0;
                            float bactOther = 0;
                            float bactTotal = 0;

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.11", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Beds- Authorized", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            
                            //medical beds authorized
                            
                            java.sql.Statement stpbauthm = connectDB.createStatement();
                           java.sql.ResultSet rspbauthm = stpbauthm.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%') ");
                            while (rspbauthm.next()) {
                                bauthMedical = rspbauthm.getFloat(1);
                            }
                            
                            
                            //surgical beds authorized
                            
                            java.sql.Statement stpbauths = connectDB.createStatement();
                           java.sql.ResultSet rspbauths = stpbauths.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%') ");
                            while (rspbauths.next()) {
                                bauthSurgical = rspbauths.getFloat(1);
                            }
                           
                            //obs/gyn beds authorized
                            
                            java.sql.Statement stpbautho = connectDB.createStatement();
                           java.sql.ResultSet rspbautho = stpbautho.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%') ");
                            while (rspbautho.next()) {
                                bauthObs = rspbautho.getFloat(1);
                            }
                            
                            //Paediatric beds authorized
                            
                            java.sql.Statement stpbauthp = connectDB.createStatement();
                           java.sql.ResultSet rspbauthp = stpbauthp.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%') ");
                            while (rspbauthp.next()) {
                                bauthPaed = rspbauthp.getFloat(1);
                            }
                            
                            //Maternity beds authorized
                            
                            java.sql.Statement stpbauthmat = connectDB.createStatement();
                           java.sql.ResultSet rspbauthmat = stpbauthmat.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%') ");
                            while (rspbauthmat.next()) {
                                bauthMat = rspbauthmat.getFloat(1);
                            }
                            
                            //Eye beds authorized
                            
                            java.sql.Statement stpbauthe = connectDB.createStatement();
                           java.sql.ResultSet rspbauthe = stpbauthe.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%') ");
                            while (rspbauthe.next()) {
                                bauthEye = rspbauthe.getFloat(1);
                            }
                            
                            //Newborn beds authorized
                            
                            java.sql.Statement stpbauthn = connectDB.createStatement();
                           java.sql.ResultSet rspbauthn = stpbauthn.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%') ");
                            while (rspbauthn.next()) {
                                bauthNewborn = rspbauthn.getFloat(1);
                            }
                            
                            //Orthopaedic beds authorized
                            
                            java.sql.Statement stpbauthort = connectDB.createStatement();
                           java.sql.ResultSet rspbauthort = stpbauthort.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%') ");
                            while (rspbauthort.next()) {
                                bauthOrtho = rspbauthort.getFloat(1);
                            }
                            
                            //Isolation beds authorized
                            
                            java.sql.Statement stpbauthi = connectDB.createStatement();
                           java.sql.ResultSet rspbauthi = stpbauthi.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%') ");
                            while (rspbauthi.next()) {
                                bauthIsolation = rspbauthi.getFloat(1);
                            }
                            
                            //Amenity beds authorized
                            
                            java.sql.Statement stpbautha = connectDB.createStatement();
                           java.sql.ResultSet rspbautha = stpbautha.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%') ");
                            while (rspbautha.next()) {
                                bauthAmenity = rspbautha.getFloat(1);
                            }
                            
                            //Psychiatry beds authorized
                            
                            java.sql.Statement stpbauthpsy = connectDB.createStatement();
                           java.sql.ResultSet rspbauthpsy = stpbauthpsy.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%') ");
                            while (rspbauthpsy.next()) {
                                bauthPsyc = rspbauthpsy.getFloat(1);
                            }
                            
                            //Other beds authorized
                            
                            java.sql.Statement stpbauthoth = connectDB.createStatement();
                           java.sql.ResultSet rspbauthoth = stpbauthoth.executeQuery("SELECT SUM(max_beds) FROM hp_wards WHERE"
                                    + " upper(ward_name) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%') ");
                            while (rspbauthoth.next()) {
                                bauthOther = rspbauthoth.getFloat(1);
                            }
                        
                            bauthTotal = bauthMedical+bauthSurgical+bauthObs+bauthPaed+bauthMat+bauthEye+bauthNewborn+bauthOrtho+bauthIsolation+bauthAmenity+bauthPsyc+bauthOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bauthTotal)), pFontHeader1);
                            table2.addCell(phrase);
                            

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.12", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Beds- Actual Physical", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            
                            
                            
                            //medical beds actual
                            
                            java.sql.Statement stpbactm = connectDB.createStatement();
                           java.sql.ResultSet rspbactm = stpbactm.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MEDICAL%') ");
                            while (rspbactm.next()) {
                                bactMedical = rspbactm.getFloat(1);
                            }
                            
                            
                            //surgical beds actual
                            
                            java.sql.Statement stpbacts = connectDB.createStatement();
                           java.sql.ResultSet rspbacts = stpbacts.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%SURGICAL%') ");
                            while (rspbacts.next()) {
                                bactSurgical = rspbacts.getFloat(1);
                            }
                           
                            //obs/gyn beds actual
                            
                            java.sql.Statement stpbacto = connectDB.createStatement();
                           java.sql.ResultSet rspbacto = stpbacto.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OBS%') ");
                            while (rspbacto.next()) {
                                bactObs = rspbacto.getFloat(1);
                            }
                            
                            //Paediatric beds actual
                            
                            java.sql.Statement stpbactp = connectDB.createStatement();
                           java.sql.ResultSet rspbactp = stpbactp.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PAED%') ");
                            while (rspbactp.next()) {
                                bactPaed = rspbactp.getFloat(1);
                            }
                            
                            //Maternity beds atual
                            
                            java.sql.Statement stpbactmat = connectDB.createStatement();
                           java.sql.ResultSet rspbactmat = stpbactmat.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%MATERNITY%') ");
                            while (rspbactmat.next()) {
                                bactMat = rspbactmat.getFloat(1);
                            }
                            
                            //Eye beds actual
                            
                            java.sql.Statement stpbacte = connectDB.createStatement();
                           java.sql.ResultSet rspbacte = stpbacte.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%EYE%') ");
                            while (rspbacte.next()) {
                                bactEye = rspbacte.getFloat(1);
                            }
                            
                            //Newborn beds actual
                            
                            java.sql.Statement stpbactn = connectDB.createStatement();
                           java.sql.ResultSet rspbactn = stpbactn.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%NBU%') ");
                            while (rspbactn.next()) {
                                bactNewborn = rspbactn.getFloat(1);
                            }
                            
                            //Orthopaedic beds actual
                            
                            java.sql.Statement stpbactort = connectDB.createStatement();
                           java.sql.ResultSet rspbactort = stpbactort.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ORTHO%') ");
                            while (rspbactort.next()) {
                                bactOrtho = rspbactort.getFloat(1);
                            }
                            
                            //Isolation beds actual
                            
                            java.sql.Statement stpbacti = connectDB.createStatement();
                           java.sql.ResultSet rspbacti = stpbacti.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%ISOLATION%') ");
                            while (rspbacti.next()) {
                                bactIsolation = rspbacti.getFloat(1);
                            }
                            
                            //Amenity beds actual
                            
                            java.sql.Statement stpbacta = connectDB.createStatement();
                           java.sql.ResultSet rspbacta = stpbacta.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%AMENITY%') ");
                            while (rspbacta.next()) {
                                bactAmenity = rspbacta.getFloat(1);
                            }
                            
                            //Psychiatry beds actual
                            
                            java.sql.Statement stpbactpsy = connectDB.createStatement();
                           java.sql.ResultSet rspbactpsy = stpbactpsy.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%PSYC%') ");
                            while (rspbactpsy.next()) {
                                bactPsyc = rspbactpsy.getFloat(1);
                            }
                            
                            //Other beds actual
                            
                            java.sql.Statement stpbactoth = connectDB.createStatement();
                           java.sql.ResultSet rspbactoth = stpbactoth.executeQuery("SELECT COUNT(DISTINCT bed_no) FROM hp_bed_setup WHERE"
                                    + " upper(ward) IN (SELECT DISTINCT upper(ward_name) FROM hp_wards WHERE ward_type ILIKE '%OTHER%') ");
                            while (rspbactoth.next()) {
                                bactOther = rspbactoth.getFloat(1);
                            }
                        
                            bactTotal = bactMedical+bactSurgical+bactObs+bactPaed+bactMat+bactEye+bactNewborn+bactOrtho+bactIsolation+bactAmenity+bactPsyc+bactOther;
                            
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactMedical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactSurgical)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactObs)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactPaed)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactMat)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactEye)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactNewborn)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactOrtho)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactIsolation)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactAmenity)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactPsyc)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactOther)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bactTotal)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.13", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Cots- Authorized", pFontHeader1);
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
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.14", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Cots- Actual Physical", pFontHeader1);
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
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.15", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Incubator- Authorized", pFontHeader1);
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
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.16", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Cots- Incubator Physical", pFontHeader1);
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
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                            table2.addCell(phrase);
                            
//Maternity Services
                            double patno = 0.00;
                            table2.getDefaultCell().setColspan(15);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //table2.getDefaultCell().setFixedHeight(25);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("B.2 MATERNITY SERVICES", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Number", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(5);
                            phrase = new Phrase("B.3 OPERATIONS", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("No. Booked", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("No. Operated", pFontHeader);
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
                            phrase = new Phrase("Normal Deliveries)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            // table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            // table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            java.sql.Statement stp102 = connectDB.createStatement();
                            java.sql.ResultSet rsp102 = stp102.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "  UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'MINOR SURGERY' ) ");
                            while (rsp102.next()) {

                                patno = rsp102.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("B.3.1 Minor Surgeries (excluding circumcision)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
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
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.3.1.1 Emergencies", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Breech Deliveries", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.3.1.2 Cold Cases", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.4", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Assisted Vaginal Delivery", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
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
                            table2.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.3.2 Circumcision", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
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
                            phrase = new Phrase("B.2.5a", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("BBA(Born Before Arrival)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            java.sql.Statement stp106 = connectDB.createStatement();
                            java.sql.ResultSet rsp106 = stp106.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "  UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'MAJOR SURGERY' ) ");
                            while (rsp106.next()) {

                                patno = rsp106.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            table2.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.3.3 Major Surgeries", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
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
                            phrase = new Phrase("B.2.5b", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Maternal Deaths", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.6", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Maternal Deaths Audited", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.7", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Live Births", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.8", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Still Births", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.9", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Neonatal Deaths", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.10", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Low Birth Weight Babies (Under 2500g)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("MOH 711", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.2.11", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Total Discharges: new born", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(disNewborn)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(10);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //blank line
                            
                            table2.getDefaultCell().setColspan(15);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //Special Services
                            
                            table2.getDefaultCell().setColspan(5);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C. SPECIAL SERVICES (includes both inpatients and outpatients)", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Number", pFontHeader);
                            table2.addCell(phrase);
//Pharmacy & Farewell

                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Number", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Total", pFontHeader);
                            table2.addCell(phrase);
                            
                            //C.1
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.1", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Laboratory - Number of Tests", pFontHeader1);
                            table2.addCell(phrase);
                            
                            patno = 0;
                            java.sql.Statement stp112 = connectDB.createStatement();
                            java.sql.ResultSet rsp112 = stp112.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Laboratory%' AND UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'ROUTINE' )");
                            while (rsp112.next()) {
                                patno = rsp112.getFloat(1);
                            }
                            
                            double patno1 = 0.00;
                            double patno2 = 0.00;
                            double patno3 = 0.00;
                            rsp112 = stp112.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Laboratory%' AND UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'SPECIAL' )");
                            while (rsp112.next()) {
                                patno1 = rsp112.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Routine", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Special", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno1)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno+patno1)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //C.2
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.2", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("X-Ray - Number of Examinations", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp113 = connectDB.createStatement();
                            java.sql.ResultSet rsp113 = stp113.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "(description ILIKE '%Xray%' OR description  ILIKE '%Ultra%' OR description  ILIKE '%x-ray%' OR description  ILIKE '%radiology%' OR description  ILIKE '%imaging%' ) AND UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'PLAIN' ) ");
                            while (rsp113.next()) {
                                patno = rsp113.getFloat(1);
                            }
                            
                            rsp113 = stp113.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "(description ILIKE '%Xray%' OR description  ILIKE '%Ultra%' OR description  ILIKE '%x-ray%' OR description  ILIKE '%radiology%' OR description  ILIKE '%imaging%' ) AND UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'ENHANCEMENT' ) ");
                            while (rsp113.next()) {
                                patno1 = rsp113.getFloat(1);
                            }
                            
                            rsp113 = stp113.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "(description ILIKE '%Xray%' OR description  ILIKE '%Ultra%' OR description  ILIKE '%x-ray%' OR description  ILIKE '%radiology%' OR description  ILIKE '%imaging%' OR description  ILIKE '%u/s%' ) AND UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'ULTRASOUND' ) ");
                            while (rsp113.next()) {
                                patno2 = rsp113.getFloat(1);
                            }
                            
                            rsp113 = stp113.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "(description ILIKE '%Xray%' OR description  ILIKE '%Ultra%' OR description  ILIKE '%x-ray%' OR description  ILIKE '%radiology%' OR description  ILIKE '%imaging%' ) AND UPPER(service_type) IN  (SELECT UPPER(service_type) FROM pb_operating_parameters  WHERE UPPER(service_classification) = 'SPECIAL' ) ");
                            while (rsp113.next()) {
                                patno3 = rsp113.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Plain without enhancement", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Enhancement with contrast media", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno1)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno1)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //new row
                            
                            table2.getDefaultCell().setColspan(7);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Special with magnetic process (Ultra Sound)", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno2)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno2)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //new row
                            
                            table2.getDefaultCell().setColspan(7);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Special with magnetic process (MRI, CT Scan)", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno3)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno3)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //new row
                            
                            table2.getDefaultCell().setColspan(7);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Total Radiological Examinations", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno+patno1+patno2+patno3)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno+patno1+patno2+patno3)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //C.3
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.3", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Physiotherapy - Number of Treatments", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp116 = connectDB.createStatement();
                            java.sql.ResultSet rsp116 = stp116.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Physiother%' AND service_type ILIKE '%private%'");
                            while (rsp116.next()) {
                                patno = rsp116.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Non- private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp119 = connectDB.createStatement();
                            java.sql.ResultSet rsp119 = stp119.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Physiother%' AND service_type NOT ILIKE '%private%'");
                            while (rsp119.next()) {
                                patno = rsp119.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp121 = connectDB.createStatement();
                            java.sql.ResultSet rsp121 = stp121.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%Physiother%'");
                            while (rsp121.next()) {
                                patno = rsp121.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //C.4
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.4", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Occupational Therapy - Number of Treatments", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp117 = connectDB.createStatement();
                            java.sql.ResultSet rsp117 = stp117.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%occupational%' AND service_type ILIKE '%private%'");
                            while (rsp117.next()) {
                                patno = rsp117.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Non- private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp120 = connectDB.createStatement();
                            java.sql.ResultSet rsp120 = stp120.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%occupati%' AND service_type NOT ILIKE '%private%'");
                            while (rsp120.next()) {
                                patno = rsp120.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp122 = connectDB.createStatement();
                            java.sql.ResultSet rsp122 = stp122.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%occupational%'");
                            while (rsp122.next()) {
                                patno = rsp122.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //C.5
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.5", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Orthopaedic Technology - Number of ITEMS Prepared and Issued", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Non- private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //C.5
                            
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Orthopaedic Technology - Number of ITEMS Issued", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp118 = connectDB.createStatement();
                            java.sql.ResultSet rsp118 = stp118.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%ORTHOPAEDIC TECH%' AND service_type ILIKE '%private%'");
                            while (rsp118.next()) {
                                patno = rsp118.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Non- private", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp123 = connectDB.createStatement();
                            java.sql.ResultSet rsp123 = stp123.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%ORTHOPAEDIC TECH%' AND service_type NOT ILIKE '%private%'");
                            while (rsp123.next()) {
                                patno = rsp123.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp124 = connectDB.createStatement();
                            java.sql.ResultSet rsp124 = stp124.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%ORTHOPAEDIC TECH%'");
                            while (rsp124.next()) {
                                patno = rsp124.getFloat(1);
                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //blank line
                            
                            table2.getDefaultCell().setColspan(15);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //Pharmacy, Mortuary, Medical Records Title Row
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D. PHARMACY", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("No. of Prescriptions", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("E. MORTUARY", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Number", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("F. MEDICAL RECORDS ISSUED", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Number", pFontHeader);
                            table2.addCell(phrase);
                            
                            //New Row
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.1 Common Drugs", pFontHeader1);
                            table2.addCell(phrase);
                            
                            int under5 = 0;
                             int over5 = 0;
                             
                             int dChildren = 0;     //varaible to hold number of under 5 cases
                            
                             java.sql.Statement stu1 = connectDB.createStatement();
                            java.sql.ResultSet rsetu1 = stu1.executeQuery("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 <= 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%')  UNION " 
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 <= 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%') ");
                            System.err.println("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 <= 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%')  UNION " 
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 <= 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%') ");
                                
                            while (rsetu1.next()) {
                                        under5 += rsetu1.getInt(1);

                                    }
                            
                            dChildren += under5;
                            
                            stu1 = connectDB.createStatement();
                            rsetu1 = stu1.executeQuery("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%')  UNION "
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%') ");
                            System.err.println("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%')  UNION "
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%common%') ");
                                
                            while (rsetu1.next()) {
                                        over5 += rsetu1.getInt(1);

                                    }

                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(String.valueOf(under5+over5), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.1 Body Days", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpDays = connectDB.createStatement();
                            java.sql.ResultSet rspDays = stpDays.executeQuery("SELECT SUM(quantity) FROM ac_cash_collection"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "description ILIKE '%body day%'");
                            while (rspDays.next()) {

                                patno = rspDays.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.1 New Files/Folders", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpFiles = connectDB.createStatement();
                            java.sql.ResultSet rspFiles = stpFiles.executeQuery("SELECT COUNT(patient_no) FROM hp_admission"
                                    + " WHERE date_admitted::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "adm_type ILIKE 'First%'");
                            while (rspFiles.next()) {

                                patno = rspFiles.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //New Row
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.2 Antibiotics", pFontHeader1);
                            table2.addCell(phrase);
                            
                            under5 = 0;
                            over5 = 0;
                            
                            stu1 = connectDB.createStatement();
                            rsetu1 = stu1.executeQuery("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 <= 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%')  UNION " 
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 <= 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%') ");
                            System.out.println("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 <= 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%')  UNION " 
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 <= 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%') ");
                                
                            while (rsetu1.next()) {
                                        under5 += rsetu1.getInt(1);

                                    }
                            dChildren += under5;
                            
                            stu1 = connectDB.createStatement();
                            System.err.println("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%')   UNION "
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%') ");
                            rsetu1 = stu1.executeQuery("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%')   UNION "
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%antibio%')  ");
                                
                            while (rsetu1.next()) {
                                        over5 += rsetu1.getInt(1);

                                    }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(String.valueOf(under5+over5), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.2 Embalmment", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stp108 = connectDB.createStatement();
                            java.sql.ResultSet rsp108 = stp108.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE 'Embal%'");
                            while (rsp108.next()) {

                                patno = rsp108.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("F.2 Outpatient Cards/Booklets", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpCards = connectDB.createStatement();
                            java.sql.ResultSet rspCards = stpCards.executeQuery("SELECT COUNT(patient_no) FROM hp_patient_visit"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "comments = 'New'");
                            while (rspCards.next()) {

                                patno = rspCards.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //New Row
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.3 Special Drugs", pFontHeader1);
                            table2.addCell(phrase);
                            
                            under5 = 0;
                            over5 = 0;
                            
                            stu1 = connectDB.createStatement();
                            System.err.println("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 <= 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%')  UNION " 
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 <= 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%')");
                            rsetu1 = stu1.executeQuery("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 <= 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%')  UNION " 
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 <= 5 and  h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%') ");
                                
                            while (rsetu1.next()) {
                                        under5 += rsetu1.getInt(1);

                                    }
                            dChildren += under5;
                            
                            stu1 = connectDB.createStatement();
                            System.err.println("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%')  UNION "
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%') ");
                            rsetu1 = stu1.executeQuery("SELECT  count(h.patient_no) FROM st_sub_stores h,hp_patient_register p where (patient_source  ilike 'OP%'   ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%')  UNION "
                                        + "SELECT  count(h.patient_no) FROM st_sub_stores h,hp_inpatient_register p where (patient_source  ilike 'IP%'  ) AND trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and h.patient_no=p.patient_no and  (trans_date::date-year_of_birth::date)/365 > 5 and h.item_code IN (SELECT item_code from st_stock_item WHERE item_classification ilike '%Special%') ");
                                
                            while (rsetu1.next()) {
                                        over5 += rsetu1.getInt(1);

                                    }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(String.valueOf(under5+over5), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.3 Post-mortem", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpMortem = connectDB.createStatement();
                            java.sql.ResultSet rspMortem = stpMortem.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger"
                                    + " WHERE date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND "
                                    + "service_type ILIKE '%Mortem%'");
                            while (rspMortem.next()) {

                                patno = rspMortem.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //New Row
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.4 Drugs for Children", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(String.valueOf(dChildren), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.4 Unclaimed Body Days", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("0", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //blank line
                            
                            table2.getDefaultCell().setColspan(15);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            
                            //Finance
                            
                            table2.getDefaultCell().setColspan(4);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("G. FINANCE", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("G.2 Clients Waived", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpWaivers = connectDB.createStatement();
                            java.sql.ResultSet rspWaivers = stpWaivers.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_card WHERE main_service ilike '%waiver%'"
                                    + " AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ");
                            while (rspWaivers.next()) {

                                patno = rspWaivers.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("G.4 Clients Exempted", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpExemptions = connectDB.createStatement();
                            java.sql.ResultSet rspExemptions = stpExemptions.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM ac_cash_collection WHERE transaction_type ilike '%exemption%'"
                                    + " AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ");
                            while (rspExemptions.next()) {

                                patno = rspExemptions.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //New Row
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("G.1 Available Financing (Ksh)", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpFinance = connectDB.createStatement();
                            java.sql.ResultSet rspFinance = stpFinance.executeQuery("SELECT SUM(debit-credit) FROM ac_cash_collection WHERE "
                                    + " date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ");
                            while (rspFinance.next()) {

                                patno = rspFinance.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("G.3 Total Waived (Ksh)", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpWaiversT = connectDB.createStatement();
                            java.sql.ResultSet rspWaiversT = stpWaiversT.executeQuery("SELECT SUM(credit-debit) FROM hp_patient_card WHERE main_service ilike '%waiver%'"
                                    + " AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ");
                            while (rspWaiversT.next()) {

                                patno = rspWaiversT.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("G.5 Total Exempted (Ksh)", pFontHeader1);
                            table2.addCell(phrase);
                            
                            java.sql.Statement stpExemptionsT = connectDB.createStatement();
                            java.sql.ResultSet rspExemptionsT = stpExemptionsT.executeQuery("SELECT SUM(credit-debit) FROM ac_cash_collection WHERE transaction_type ilike '%exemption%'"
                                    + " AND date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' ");
                            while (rspExemptionsT.next()) {

                                patno = rspExemptionsT.getFloat(1);
                                //gMat = gMat + matMothers;

                            }
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            
                            //blank line
                            
                            table2.getDefaultCell().setColspan(15);
                            phrase = new Phrase(" ", pFontHeader1);
                            table2.addCell(phrase);
                            
                            //Signatures table
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Name", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(6);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Designation", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Date", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Signature", pFontHeader);
                            table2.addCell(phrase);
                            
                            //Prepared by
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Prepared by:", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(6);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            //Checked by
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Checked by:", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(6);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            //Received by
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Received by:", pFontHeader1);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(6);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
                            table2.addCell(phrase);
                            
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" ", pFontHeader);
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