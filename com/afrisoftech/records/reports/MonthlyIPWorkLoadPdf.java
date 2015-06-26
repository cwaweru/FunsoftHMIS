//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class MonthlyIPWorkLoadPdf implements java.lang.Runnable {

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
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void MonthlyIPWorkLoadPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

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
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                            Region = rset2.getObject(3).toString();
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

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths2[] = {30, 10, 10, 10, 10, 10, 10};

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
                            phrase = new Phrase("District : " + District, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Region : " + Region, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("" + dateFormat.format(endDate1), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A. OUTPATIENT SERVICES", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A1 GENERAL OUTPATIENTS(FILTER CLINICS)", pFontHeader);
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
                            java.sql.ResultSet rset = st.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND rank ilike 'new' AND TRIM(gender) ilike 'MALE' AND clinic ILIKE 'OPD'");
                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND rank ilike 'old' AND TRIM(gender) ilike 'MALE' AND clinic ILIKE 'OPD'");
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
                            java.sql.ResultSet rset2 = st21.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND rank ilike 'new' AND TRIM(gender) ilike 'FEMALE' AND clinic ILIKE 'OPD'");
                            java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) >= 5 AND rank ilike 'old' AND TRIM(gender) ilike 'FEMALE' AND clinic ILIKE 'OPD'");
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
                            java.sql.ResultSet rset22 = st212.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND rank ilike 'new' AND TRIM(gender) ilike 'MALE' AND clinic ILIKE 'OPD'");
                            java.sql.ResultSet rset122 = st122.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND rank ilike 'old' AND TRIM(gender) ilike 'MALE' AND clinic ILIKE 'OPD'");
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
                            java.sql.ResultSet rset221 = st2121.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND rank ilike 'new' AND TRIM(gender) ilike 'FEMALE' AND clinic ILIKE 'OPD'");
                            java.sql.ResultSet rset1221 = st1221.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date::date  BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric(10,2) < 5 AND rank ilike 'old' AND TRIM(gender) ilike 'FEMALE' AND clinic ILIKE 'OPD'");
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
                            phrase = new Phrase("TOTAL GENERAL OUTPATIENTS", pFontHeader);
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
                            java.sql.ResultSet rsetx = stx.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND clinic ILIKE 'Casualty' AND rank ilike 'new'");
                            java.sql.ResultSet rset1x = st1x.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND clinic ILIKE 'Casualty' AND rank ilike 'old'");
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
                            java.sql.ResultSet rsets = sts.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND (clinic ILIKE 'ENT' OR clinic ILIKE 'E.N.T')");
                            java.sql.ResultSet rset1s = st1s.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND (clinic ILIKE 'ENT' OR clinic ILIKE 'E.N.T')");
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
                            java.sql.ResultSet rsets1 = sts1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'Eye%'");
                            java.sql.ResultSet rset1s1 = st1s1.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'Eye%'");
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
                            java.sql.ResultSet rsets11 = sts11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND (clinic ILIKE 'TB' OR clinic ILIKE 'leprosy' OR clinic ILIKE 'Chest')");
                            java.sql.ResultSet rset1s11 = st1s11.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND (clinic ILIKE 'TB' OR clinic ILIKE 'leprosy' OR clinic ILIKE 'Chest')");
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
                            java.sql.ResultSet rsets111 = sts111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND (clinic ILIKE 'sti' OR clinic ILIKE 'std' OR clinic ILIKE 'sex%')");
                            java.sql.ResultSet rset1s111 = st1s111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND (clinic ILIKE 'sti' OR clinic ILIKE 'std' OR clinic ILIKE 'sex%')");
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
                            java.sql.ResultSet rsets1111 = sts1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'psychia%'");
                            java.sql.ResultSet rset1s1111 = st1s1111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'psychia%'");
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
                            java.sql.ResultSet rsets11111 = sts11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHo%')");
                            java.sql.ResultSet rset1s11111 = st1s11111.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND (clinic ILIKE 'ORTH%' OR clinic ILIKE 'OTHo%')");
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
                            java.sql.ResultSet rsets4 = sts4.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'CWC%'");
                            java.sql.ResultSet rset41 = st1s4.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'CWC%'");
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
                            java.sql.ResultSet rsets42 = sts42.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'ANC%'");
                            java.sql.ResultSet rset412 = st1s42.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'ANC%'");
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
                            java.sql.ResultSet rsets43 = sts43.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'PNC%'");
                            java.sql.ResultSet rset413 = st1s43.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'PNC%'");
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
                            java.sql.ResultSet rsets44 = sts44.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'FP%'");
                            java.sql.ResultSet rset414 = st1s44.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'FP%'");
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
                            java.sql.ResultSet rset5 = sts5.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND rank ilike 'new' AND clinic ILIKE 'DENTAL%' AND (service NOT ILIKE '%extraction%' OR service NOT ILIKE '%filling%')");
                            java.sql.ResultSet rsets5 = st1s5.executeQuery("SELECT DISTINCT COUNT(patient_no) FROM pb_doctors_request WHERE trans_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND rank ilike 'old'  AND clinic ILIKE 'DENTAL%' AND (service NOT ILIKE '%extraction%' OR service NOT ILIKE '%filling%')");
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

                            phrase = new Phrase("A.6 TOTAL OUTPATIENT SERVICES \n (=A.1.5 + A.2 + A.3.7 + A.4.5 + A.5.4)", pFontHeader);
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
                            java.sql.ResultSet rsin = stin.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%injection%' OR description ILIKE '%inj%')");
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
                            java.sql.ResultSet rsst = stst.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%Stiching Of Wound%')");
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
                            java.sql.ResultSet rsdr = stdr.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND description ILIKE '%Dressing Of Wound%'");
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
                            java.sql.ResultSet rspp = stpp.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%pop%' OR description ILIKE '%p.o.p.%' OR description ILIKE '%p o p%')");
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


                            docPdf.add(table);
                            docPdf.add(table1);
                            docPdf.add(table2);
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
