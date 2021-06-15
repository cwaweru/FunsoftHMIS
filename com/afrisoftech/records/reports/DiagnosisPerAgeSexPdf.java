//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class DiagnosisPerAgeSexPdf implements java.lang.Runnable {

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

    public void DiagnosisPerAgeSexPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String repotype, java.lang.String categ) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        Gender = repotype;

        ReportType = categ;

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

            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

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

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

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


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(26);

                        int headerwidths[] = {5, 7, 45, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(4);

                        /* com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);
                        
                         int headerwidths1[] = {6,30,10,10};
                        
                         table1.setWidths(headerwidths1);
                        
                         table1.setWidthPercentage((100));
                        
                         table1.setHeaderRows(1);
                        
                         /*    com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(7);
                        
                         int headerwidths2[] = {30,10,10,10,10,10,10};
                        
                         table2.setWidths(headerwidths2);
                        
                         table2.setWidthPercentage((100));
                        
                         table2.setHeaderRows(1);*/
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(6);


                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            //java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(26);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(50);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(18);
                            phrase = new Phrase("IN-PATIENT MORBIDITY & MORTALITY SUMMARY SHEET", pFontHeader2);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("SEX :" + Gender, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(14);
                            phrase = new Phrase("HEALTH FACILITY : " + compName, pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("MONTH : " + monthString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("YEAR : " + yearString.toUpperCase(), pFontHeader22);
                            table.addCell(phrase);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(2);

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("AGE DISTRIBUTION", pFontHeader11);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("<1", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("1-4", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("5-14", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("15-24", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("25-34", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("35-44", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("45-54", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("55-64", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase(">65", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("U/AGE", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("TOTAL", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A.L.S", pFontHeader11);
                            table.addCell(phrase);


                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("STATUS OF DISCHARGE", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("A", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("D", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("RK", pFontHeader11);
                            table.addCell(phrase);
                            phrase = new Phrase("CODE", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("DIAGNOSIS", pFontHeader11);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);



                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            int tmUnderOneA = 0;
                            int tmUnderOneD = 0;
                            int tmOneFourA = 0;
                            int tmOneFourD = 0;
                            int tmFiveFourteenA = 0;
                            int tmFiveFourteenD = 0;
                            int tmFifTwentyA = 0;
                            int tmFifTwentyD = 0;
                            int tmTweThirA = 0;
                            int tmTweThirD = 0;
                            int tmThirFourA = 0;
                            int tmThirFourD = 0;
                            int tmFourFiveA = 0;
                            int tmFourFiveD = 0;
                            int tmFiveSixA = 0;
                            int tmFiveSixD = 0;
                            int tmOverSixA = 0;
                            int tmOverSixD = 0;
                            int tmUnAgeA = 0;
                            int tmUnAgeD = 0;
                            int tmTotalA = 0;
                            int tmTotalD = 0;


                            int tMaleFemale = 0;
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement stf = connectDB.createStatement();
                            // Male statement defination
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st10 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            java.sql.Statement std = connectDB.createStatement();
                            java.sql.Statement st1d = connectDB.createStatement();
                            java.sql.Statement st2d = connectDB.createStatement();
                            java.sql.Statement st3d = connectDB.createStatement();
                            java.sql.Statement st4d = connectDB.createStatement();
                            java.sql.Statement st10d = connectDB.createStatement();
                            java.sql.Statement st11d = connectDB.createStatement();
                            java.sql.Statement st12d = connectDB.createStatement();
                            java.sql.Statement st13d = connectDB.createStatement();
                            java.sql.Statement st15d = connectDB.createStatement();
                            int noSeq = 0;
                            for (int i = 0; i < listofAct.length; i++) {
                                // variables declalition

                                int underOneA = 0;
                                int underOneD = 0;
                                int oneFourA = 0;
                                int oneFourD = 0;
                                int fiveFourteenA = 0;
                                int fiveFourteenD = 0;
                                int fifTwentyA = 0;
                                int fifTwentyD = 0;
                                int tweThirA = 0;
                                int tweThirD = 0;
                                int thirFourA = 0;
                                int thirFourD = 0;
                                int fourFiveA = 0;
                                int fourFiveD = 0;
                                int fiveSixA = 0;
                                int fiveSixD = 0;
                                int overSixA = 0;
                                int overSixD = 0;
                                int unAgeA = 0;
                                int unAgeD = 0;
                                int totalA = 0;
                                int totalD = 0;
                                int avDays = 0;
                                int totalMaleFemale = 0;


                                int patNo1 = 0;

                                if (this.ReportType.equalsIgnoreCase("Both")) {
                                    java.sql.ResultSet rsetf = stf.executeQuery("SELECT DISTINCT disease_name FROM hp_diseases WHERE code ilike '" + listofAct[i] + "'");

                                    java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age BETWEEN 0.1 AND 1 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1.1 AND 4.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset5 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset6 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset7 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset8 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 65 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");
                                    java.sql.ResultSet rset9 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age <= 0 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome NOT ILIKE 'DIED'");


                                    java.sql.ResultSet rsetd = std.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age BETWEEN 0.1 AND 1 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset1d = st1d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1.1 AND 4.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset2d = st2d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset3d = st3d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset4d = st4d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset5d = st11d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset6d = st12d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset7d = st13d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset8d = st10d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 65 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");
                                    java.sql.ResultSet rset9d = st15d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age <= 0 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND admission_outcome ILIKE 'DIED'");

                                    // Average stay

                                    java.sql.ResultSet rsetw = stw.executeQuery("SELECT sum(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "'");


                                    while (rsetw.next()) {
                                        avDays = rsetw.getInt(1);
                                    }
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(listofAct[i].toString(), pFontHeader1);
                                    table.addCell(phrase);
                                    while (rsetf.next()) {
                                        phrase = new Phrase(dbObject.getDBObject(rsetf.getObject(1), "-"), pFontHeader1);

                                        table.addCell(phrase);
                                    }

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);




                                    while (rset.next()) {
                                        underOneA = rset.getInt(1);
                                    }

                                    while (rsetd.next()) {
                                        underOneD = rsetd.getInt(1);
                                    }

                                    while (rset1.next()) {
                                        oneFourA = rset1.getInt(1);
                                    }

                                    while (rset1d.next()) {
                                        oneFourD = rset1d.getInt(1);
                                    }

                                    while (rset2.next()) {
                                        fiveFourteenA = rset2.getInt(1);
                                    }

                                    while (rset2d.next()) {
                                        fiveFourteenD = rset2d.getInt(1);
                                    }

                                    while (rset3.next()) {
                                        fifTwentyA = rset3.getInt(1);
                                    }

                                    while (rset3d.next()) {
                                        fifTwentyD = rset3d.getInt(1);
                                    }
                                    while (rset4.next()) {
                                        tweThirA = rset4.getInt(1);
                                    }

                                    while (rset4d.next()) {
                                        tweThirD = rset4d.getInt(1);
                                    }

                                    while (rset5.next()) {
                                        thirFourA = rset5.getInt(1);
                                    }

                                    while (rset5d.next()) {
                                        thirFourD = rset5d.getInt(1);
                                    }

                                    while (rset6.next()) {
                                        fourFiveA = rset6.getInt(1);
                                    }

                                    while (rset6d.next()) {
                                        fourFiveD = rset6d.getInt(1);
                                    }

                                    while (rset7.next()) {
                                        fiveSixA = rset7.getInt(1);
                                    }

                                    while (rset7d.next()) {
                                        fiveSixD = rset7d.getInt(1);
                                    }

                                    while (rset8.next()) {
                                        overSixA = rset8.getInt(1);
                                    }

                                    while (rset8d.next()) {
                                        overSixD = rset8d.getInt(1);
                                    }


                                    while (rset9.next()) {
                                        unAgeA = rset9.getInt(1);
                                    }

                                    while (rset9d.next()) {
                                        unAgeD = rset9d.getInt(1);
                                    }
                                } else {
                                    if (this.ReportType.equalsIgnoreCase("IP")) {
                                        java.sql.ResultSet rsetf = stf.executeQuery("SELECT DISTINCT disease_name FROM hp_diseases WHERE code ilike '" + listofAct[i] + "'");

                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age BETWEEN 0.1 AND 1 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1.1 AND 4.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset6 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset7 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset8 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 65 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset9 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age <= 0 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome NOT ILIKE 'DIED'");


                                        java.sql.ResultSet rsetd = std.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age BETWEEN 0.1 AND 1 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1d = st1d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1.1 AND 4.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2d = st2d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3d = st3d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4d = st4d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5d = st11d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset6d = st12d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset7d = st13d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset8d = st10d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 65 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset9d = st15d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age <= 0 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay

                                        java.sql.ResultSet rsetw = stw.executeQuery("SELECT sum(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'IP'");


                                        while (rsetw.next()) {
                                            avDays = rsetw.getInt(1);
                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(listofAct[i].toString(), pFontHeader1);
                                        table.addCell(phrase);
                                        while (rsetf.next()) {
                                            phrase = new Phrase(dbObject.getDBObject(rsetf.getObject(1), "-"), pFontHeader1);

                                            table.addCell(phrase);
                                        }

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);




                                        while (rset.next()) {
                                            underOneA = rset.getInt(1);
                                        }

                                        while (rsetd.next()) {
                                            underOneD = rsetd.getInt(1);
                                        }

                                        while (rset1.next()) {
                                            oneFourA = rset1.getInt(1);
                                        }

                                        while (rset1d.next()) {
                                            oneFourD = rset1d.getInt(1);
                                        }

                                        while (rset2.next()) {
                                            fiveFourteenA = rset2.getInt(1);
                                        }

                                        while (rset2d.next()) {
                                            fiveFourteenD = rset2d.getInt(1);
                                        }

                                        while (rset3.next()) {
                                            fifTwentyA = rset3.getInt(1);
                                        }

                                        while (rset3d.next()) {
                                            fifTwentyD = rset3d.getInt(1);
                                        }
                                        while (rset4.next()) {
                                            tweThirA = rset4.getInt(1);
                                        }

                                        while (rset4d.next()) {
                                            tweThirD = rset4d.getInt(1);
                                        }

                                        while (rset5.next()) {
                                            thirFourA = rset5.getInt(1);
                                        }

                                        while (rset5d.next()) {
                                            thirFourD = rset5d.getInt(1);
                                        }

                                        while (rset6.next()) {
                                            fourFiveA = rset6.getInt(1);
                                        }

                                        while (rset6d.next()) {
                                            fourFiveD = rset6d.getInt(1);
                                        }

                                        while (rset7.next()) {
                                            fiveSixA = rset7.getInt(1);
                                        }

                                        while (rset7d.next()) {
                                            fiveSixD = rset7d.getInt(1);
                                        }

                                        while (rset8.next()) {
                                            overSixA = rset8.getInt(1);
                                        }

                                        while (rset8d.next()) {
                                            overSixD = rset8d.getInt(1);
                                        }


                                        while (rset9.next()) {
                                            unAgeA = rset9.getInt(1);
                                        }

                                        while (rset9d.next()) {
                                            unAgeD = rset9d.getInt(1);
                                        }


                                    } else {
                                        java.sql.ResultSet rsetf = stf.executeQuery("SELECT DISTINCT disease_name FROM hp_diseases WHERE code ilike '" + listofAct[i] + "'");

                                        java.sql.ResultSet rset = st.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age BETWEEN 0.1 AND 1 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset1 = st1.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1.1 AND 4.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset2 = st2.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset3 = st3.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset5 = st11.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset6 = st12.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset7 = st13.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset8 = st10.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 65 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");
                                        java.sql.ResultSet rset9 = st15.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age <= 0 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome NOT ILIKE 'DIED'");


                                        java.sql.ResultSet rsetd = std.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age BETWEEN 0.1 AND 1 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset1d = st1d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 1.1 AND 4.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset2d = st2d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 5 AND 14.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset3d = st3d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 15 AND 24.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset4d = st4d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 25 AND 34.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset5d = st11d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 35 AND 44.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset6d = st12d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 45 AND 54.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset7d = st13d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age between 55 AND 64.9 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset8d = st10d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age > 65 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");
                                        java.sql.ResultSet rset9d = st15d.executeQuery("SELECT count(patient_no) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND pat_age <= 0 AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP' AND admission_outcome ILIKE 'DIED'");

                                        // Average stay

                                        java.sql.ResultSet rsetw = stw.executeQuery("SELECT sum(length_of_stay) FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + listofAct[i] + "' AND gender ilike '" + Gender + "' AND pat_category ILIKE 'OP'");


                                        while (rsetw.next()) {
                                            avDays = rsetw.getInt(1);
                                        }
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        noSeq = noSeq + 1;
                                        phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(listofAct[i].toString(), pFontHeader1);
                                        table.addCell(phrase);
                                        while (rsetf.next()) {
                                            phrase = new Phrase(dbObject.getDBObject(rsetf.getObject(1), "-"), pFontHeader1);

                                            table.addCell(phrase);
                                        }

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);




                                        while (rset.next()) {
                                            underOneA = rset.getInt(1);
                                        }

                                        while (rsetd.next()) {
                                            underOneD = rsetd.getInt(1);
                                        }

                                        while (rset1.next()) {
                                            oneFourA = rset1.getInt(1);
                                        }

                                        while (rset1d.next()) {
                                            oneFourD = rset1d.getInt(1);
                                        }

                                        while (rset2.next()) {
                                            fiveFourteenA = rset2.getInt(1);
                                        }

                                        while (rset2d.next()) {
                                            fiveFourteenD = rset2d.getInt(1);
                                        }

                                        while (rset3.next()) {
                                            fifTwentyA = rset3.getInt(1);
                                        }

                                        while (rset3d.next()) {
                                            fifTwentyD = rset3d.getInt(1);
                                        }
                                        while (rset4.next()) {
                                            tweThirA = rset4.getInt(1);
                                        }

                                        while (rset4d.next()) {
                                            tweThirD = rset4d.getInt(1);
                                        }

                                        while (rset5.next()) {
                                            thirFourA = rset5.getInt(1);
                                        }

                                        while (rset5d.next()) {
                                            thirFourD = rset5d.getInt(1);
                                        }

                                        while (rset6.next()) {
                                            fourFiveA = rset6.getInt(1);
                                        }

                                        while (rset6d.next()) {
                                            fourFiveD = rset6d.getInt(1);
                                        }

                                        while (rset7.next()) {
                                            fiveSixA = rset7.getInt(1);
                                        }

                                        while (rset7d.next()) {
                                            fiveSixD = rset7d.getInt(1);
                                        }

                                        while (rset8.next()) {
                                            overSixA = rset8.getInt(1);
                                        }

                                        while (rset8d.next()) {
                                            overSixD = rset8d.getInt(1);
                                        }


                                        while (rset9.next()) {
                                            unAgeA = rset9.getInt(1);
                                        }

                                        while (rset9d.next()) {
                                            unAgeD = rset9d.getInt(1);
                                        }
                                    }

                                }
                                //int totalA = 0;
                                //int totalD = 0;


                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOneA)), pFontHeader1);
                                tmUnderOneA = tmUnderOneA + underOneA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(underOneD)), pFontHeader1);
                                tmUnderOneD = tmUnderOneD + underOneD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oneFourA)), pFontHeader1);
                                tmOneFourA = tmOneFourA + oneFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oneFourD)), pFontHeader1);
                                tmOneFourD = tmOneFourD + oneFourD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fiveFourteenA)), pFontHeader1);
                                tmFiveFourteenA = tmFiveFourteenA + fiveFourteenA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fiveFourteenD)), pFontHeader1);
                                tmFiveFourteenD = tmFiveFourteenD + fiveFourteenD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fifTwentyA)), pFontHeader1);
                                tmFifTwentyA = tmFifTwentyA + fifTwentyA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fifTwentyD)), pFontHeader1);
                                tmFifTwentyD = tmFifTwentyD + fifTwentyD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tweThirA)), pFontHeader1);
                                tmTweThirA = tmTweThirA + tweThirA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tweThirD)), pFontHeader1);
                                tmTweThirD = tmTweThirD + tweThirD;
                                table.addCell(phrase);


                                // female
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(thirFourA)), pFontHeader1);
                                tmThirFourA = tmThirFourA + thirFourA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(thirFourD)), pFontHeader1);
                                tmThirFourD = tmThirFourD + thirFourD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fourFiveA)), pFontHeader1);
                                tmFourFiveA = tmFourFiveA + fourFiveA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fourFiveD)), pFontHeader1);
                                tmFourFiveD = tmFourFiveD + fourFiveD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fiveSixA)), pFontHeader1);
                                tmFiveSixA = tmFiveSixA + fiveSixA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fiveSixD)), pFontHeader1);
                                tmFiveSixD = tmFiveSixD + fiveSixD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overSixA)), pFontHeader1);
                                tmOverSixA = tmOverSixA + overSixA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(overSixD)), pFontHeader1);
                                tmOverSixD = tmOverSixD + overSixD;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unAgeA)), pFontHeader1);
                                tmUnAgeA = tmUnAgeA + unAgeA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unAgeD)), pFontHeader1);
                                tmUnAgeD = tmUnAgeD + unAgeD;
                                table.addCell(phrase);
                                totalA = underOneA + oneFourA + fiveFourteenA + fifTwentyA + tweThirA + thirFourA + fourFiveA + fiveSixA + overSixA + unAgeA;
                                totalD = underOneD + oneFourD + fiveFourteenD + fifTwentyD + tweThirD + thirFourD + fourFiveD + fiveSixD + overSixD + unAgeD;

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalA)), pFontHeader1);
                                tmTotalA = tmTotalA + totalA;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalD)), pFontHeader1);
                                tmTotalD = tmTotalD + totalD;
                                table.addCell(phrase);
                                if (totalA + totalD < 1) {
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(avDays / (1))), pFontHeader1);
                                    table.addCell(phrase);
                                } else {
                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(avDays / (totalA + totalD))), pFontHeader1);
                                    table.addCell(phrase);
                                }
                            }
                            /* table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                             table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
                             table.getDefaultCell().setMinimumHeight(20);
                             phrase = new Phrase("All Ages", pFontHeader1);
                             table.addCell(phrase);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                             table.getDefaultCell().setColspan(1);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnderOne)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOneFour)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiveFourteen)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFifteenFortyFour)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFortyFiveSixty)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOverSixtyOne)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfUnderOne)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOneFour)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFiveFourteen)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFifteenFortyFour)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFortyFiveSixty)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOverSixtyOne)), pFontHeader1);
                             table.addCell(phrase);

                             phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tMaleFemale)), pFontHeader1);
                             table.addCell(phrase);
                             * */

                            docPdf.add(table);
                            //docPdf.add(table1);
                            //docPdf.add(table2);
                        } catch (java.sql.SQLException SqlExec) {

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

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    public java.lang.Object[] getListofStaffNos() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {


            if (this.ReportType.equalsIgnoreCase("Both")) {
                java.sql.Statement stmt1 = connectDB.createStatement();

                java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_service FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender = '" + Gender + "' AND main_service IS NOT NULL AND main_service != '' ORDER BY 1 ASC");

                while (rSet1.next()) {

                    listStaffNoVector.addElement(rSet1.getObject(1).toString());

                    // }

                }
            } else {
                if (this.ReportType.equalsIgnoreCase("IP")) {
                    java.sql.Statement stmt1 = connectDB.createStatement();

                    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_service FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender = '" + Gender + "' AND main_service IS NOT NULL AND main_service != '' AND pat_category = 'IP' ORDER BY 1 ASC");

                    while (rSet1.next()) {

                        listStaffNoVector.addElement(rSet1.getObject(1).toString());

                        // }

                    }
                } else {
                    java.sql.Statement stmt1 = connectDB.createStatement();

                    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_service FROM hp_patient_diagnosis where date_discharged BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND gender = '" + Gender + "' AND main_service IS NOT NULL AND main_service != '' AND pat_category = 'OP' ORDER BY 1 ASC");

                    while (rSet1.next()) {

                        listStaffNoVector.addElement(rSet1.getObject(1).toString());

                        // }

                    }
                }
            }
        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}
