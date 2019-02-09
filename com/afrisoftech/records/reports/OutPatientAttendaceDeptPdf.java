//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.sql.SQLException;
//import org.openide.util.Exceptions;
//import //java.awt.Desktop;

public class OutPatientAttendaceDeptPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date endDate = null;
    int numberSeq = 0;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    float mints = 0;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void OutPatientAttendaceDeptPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

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

            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));
                    String compName = null;
                    String District = null;
                    String Region = null;
                    String date = null;
                    try {
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
                            date = rset4.getDate(1).toString();
                        }
                    } catch (SQLException ex) {
                        javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                        ex.printStackTrace();             //Exceptions.printStackTrace(ex);
                    }
                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setRight(5);
                    docPdf.setHeader(headerFoter);

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

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(27);

                        int headerwidths[] = {40, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

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
                            try {

                                java.sql.Statement st3Y = connectDB.createStatement();
                                java.sql.Statement st4Y = connectDB.createStatement();

                                java.sql.ResultSet rset2 = st3Y.executeQuery("SELECT hospital_name,district_branch from pb_hospitalprofile");
                                java.sql.ResultSet rset4 = st4Y.executeQuery("SELECT date('now') as Date");
                                while (rset2.next()) {
                                    compName = rset2.getObject(1).toString();
                                    District = rset2.getObject(2).toString();
                                }
                                while (rset4.next()) {
                                    date = rset4.getObject(1).toString();
                                }

//                                java.sql.Statement st4x = connectDB.createStatement();
//                                java.sql.ResultSet rset4x = st4x.executeQuery("SELECT EXTRACT(MINUTE FROM(current_time::time - report_time::time)) FROM patient_treated");
//
//                                while (rset4x.next()) {
//                                    mints = rset4x.getFloat(1);
//                                    System.out.println("Mins " + mints);
//                                }
//                                if (mints > 7 || mints <= 0) {
//
//                                    java.sql.PreparedStatement pstmt31 = connectDB.prepareStatement("DELETE FROM patient_treated");
//                                    pstmt31.executeUpdate();
//
//                                    java.sql.PreparedStatement pstmt = connectDB.prepareStatement("INSERT INTO patient_treated("
//                                            + " patient_no, name, curr_date, doctor, diagnosis, rank, age, gender, clinic) "
//                                            + " SELECT patient_no, name, curr_date, doctor, diagnosis, rank, age,"
//                                            + " gender, clinic FROM patient_seen");
//
//                                    //pstmt.executeUpdate();
//                                }
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                //java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                                java.lang.String monthString = dateFormatter.getDateString();

                                com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                                java.lang.String yearString = dateFormatters.getDateString();

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(27);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("DEPARTMENTAL OUT-PATIENTS ATTENDANCE", pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(21);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("FACILITY : " + compName.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("DATE  : " + dateFormat.format(endDate1), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(21);
                                phrase = new Phrase("", pFontHeader2);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("MONTH  : " + monthString.toUpperCase(), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(21);
                                phrase = new Phrase(" ", pFontHeader2);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("YEAR  : " + yearString.toUpperCase(), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                table.getDefaultCell().setColspan(13);

                                //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                phrase = new Phrase("MALE CASES", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(14);

                                phrase = new Phrase("FEMALE CASES", pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase("DEPARTMENT", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("UNDER 1 YR.", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("1-4", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("5-14", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("15-44", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("45-60", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("61+", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("UNDER 1 YR.", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("1-4", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("5-14", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("15-44", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("45-60", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("61+", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("TOTAL", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase("", pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("N", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("R", pFontHeader1);
                                table.addCell(phrase);

                            } catch (java.sql.SQLException sqlExec) {

                                sqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

                            }

                        } catch (java.text.ParseException psExec) {

                            psExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            int tmUnderOne = 0;
                            int tmOneFour = 0;
                            int tmFiveFourteen = 0;
                            int tmFifteenFortyFour = 0;
                            int tmFortyFiveSixty = 0;
                            int tmOverSixtyOne = 0;

                            int tmUnderOner = 0;
                            int tmOneFourr = 0;
                            int tmFiveFourteenr = 0;
                            int tmFifteenFortyFourr = 0;
                            int tmFortyFiveSixtyr = 0;
                            int tmOverSixtyOner = 0;

                            // female variables
                            int tfUnderOne = 0;
                            int tfOneFour = 0;
                            int tfFiveFourteen = 0;
                            int tfFifteenFortyFour = 0;
                            int tfFortyFiveSixty = 0;
                            int tfOverSixtyOne = 0;

                            int tfUnderOner = 0;
                            int tfOneFourr = 0;
                            int tfFiveFourteenr = 0;
                            int tfFifteenFortyFourr = 0;
                            int tfFortyFiveSixtyr = 0;
                            int tfOverSixtyOner = 0;

                            int tMaleFemale = 0;
                            int tMaleFemaler = 0;
                            java.lang.Object[] listofAct = this.getListofStaffNos();
                            java.sql.Statement stw = connectDB.createStatement();

                            // Male statement defination
                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();

                            java.sql.Statement stm = connectDB.createStatement();
                            java.sql.Statement st1m = connectDB.createStatement();
                            java.sql.Statement st2m = connectDB.createStatement();
                            java.sql.Statement st3m = connectDB.createStatement();
                            java.sql.Statement st4m = connectDB.createStatement();
                            java.sql.Statement st5m = connectDB.createStatement();

                            // Female statement definition
                            java.sql.Statement st10 = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();

                            java.sql.Statement st10f = connectDB.createStatement();
                            java.sql.Statement st11f = connectDB.createStatement();
                            java.sql.Statement st12f = connectDB.createStatement();
                            java.sql.Statement st13f = connectDB.createStatement();
                            java.sql.Statement st14f = connectDB.createStatement();
                            java.sql.Statement st15f = connectDB.createStatement();
                            for (int i = 0; i < listofAct.length; i++) {
                                // variables declalition
                                // male variables
                                int mUnderOne = 0;
                                int mOneFour = 0;
                                int mFiveFourteen = 0;
                                int mFifteenFortyFour = 0;
                                int mFortyFiveSixty = 0;
                                int mOverSixtyOne = 0;

                                int mUnderOner = 0;
                                int mOneFourr = 0;
                                int mFiveFourteenr = 0;
                                int mFifteenFortyFourr = 0;
                                int mFortyFiveSixtyr = 0;
                                int mOverSixtyOner = 0;

                                // female variables
                                int fUnderOne = 0;
                                int fOneFour = 0;
                                int fFiveFourteen = 0;
                                int fFifteenFortyFour = 0;
                                int fFortyFiveSixty = 0;
                                int fOverSixtyOne = 0;
                                int fUnderOner = 0;
                                int fOneFourr = 0;
                                int fFiveFourteenr = 0;
                                int fFifteenFortyFourr = 0;
                                int fFortyFiveSixtyr = 0;
                                int fOverSixtyOner = 0;

                                int totalMaleFemale = 0;
                                int totalMaleFemaler = 0;
                                String Gender = null;

                                int patNo1 = 0;
                                /*java.sql.ResultSet rsetAge = stw.executeQuery("SELECT min_age,max_age FROM patient_age WHERE description ilike '"+listofAct[i]+"'");

                                 while(rsetAge.next()){
                                 lowerAge = rsetAge.getInt(1);
                                 upperAge = rsetAge.getInt(2);
                                 }*/

                                // Created a view hp_patient_visit to analyse the data before select.
                                // Male result-sets.
//                                java.sql.ResultSet rset = st.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset2 = st2.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset3 = st3.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset5 = st5.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'New'");
//
//                                java.sql.ResultSet rsetm = stm.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset1m = st1m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset2m = st2m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset3m = st3m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset4m = st4m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset5m = st5m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Male' AND rank ILIKE 'Old'");
//
//                                // Female result sets.
//                                java.sql.ResultSet rset10 = st10.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset13 = st13.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset14 = st14.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'New'");
//                                java.sql.ResultSet rset15 = st15.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'New'");
//
//                                java.sql.ResultSet rset10f = st10f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset11f = st11f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset12f = st12f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset13f = st13f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset14f = st14f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'Old'");
//                                java.sql.ResultSet rset15f = st15f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM patient_treated WHERE curr_date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND clinic ilike '" + listofAct[i] + "' AND gender ilike 'Female' AND rank ILIKE 'Old'");
//                                
                                java.sql.ResultSet rset = st.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset2 = st2.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset3 = st3.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset4 = st4.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset5 = st5.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'NEW'");

                                java.sql.ResultSet rsetm = stm.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset1m = st1m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset2m = st2m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset3m = st3m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset4m = st4m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset5m = st5m.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'MALE' AND upper(comments) = 'OLD'");

                                // Female result sets.
                                java.sql.ResultSet rset10 = st10.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset13 = st13.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset14 = st14.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'NEW'");
                                java.sql.ResultSet rset15 = st15.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'NEW'");

                                java.sql.ResultSet rset10f = st10f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric <= 1 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset11f = st11f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 1.01 AND 4 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset12f = st12f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 5 AND 14 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset13f = st13f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 15 AND 44 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset14f = st14f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric between 45 AND 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'OLD'");
                                java.sql.ResultSet rset15f = st15f.executeQuery("SELECT COUNT(DISTINCT patient_no) FROM hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND age::numeric > 60 AND upper(clinic) = '" + listofAct[i].toString().toUpperCase() + "' AND upper(gender) = 'FEMALE' AND upper(comments) = 'OLD'");

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("" + listofAct[i], pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                while (rset.next()) {
                                    mUnderOne = rset.getInt(1);
                                }

                                while (rset1.next()) {
                                    mOneFour = rset1.getInt(1);
                                }

                                while (rset2.next()) {
                                    mFiveFourteen = rset2.getInt(1);
                                }

                                while (rset3.next()) {
                                    mFifteenFortyFour = rset3.getInt(1);
                                }

                                while (rset4.next()) {
                                    mFortyFiveSixty = rset4.getInt(1);
                                }

                                while (rset5.next()) {
                                    mOverSixtyOne = rset5.getInt(1);
                                }
//

                                while (rsetm.next()) {
                                    mUnderOner = rsetm.getInt(1);
                                }

                                while (rset1m.next()) {
                                    mOneFourr = rset1m.getInt(1);
                                }

                                while (rset2m.next()) {
                                    mFiveFourteenr = rset2m.getInt(1);
                                }

                                while (rset3m.next()) {
                                    mFifteenFortyFourr = rset3m.getInt(1);
                                }

                                while (rset4m.next()) {
                                    mFortyFiveSixtyr = rset4m.getInt(1);
                                }

                                while (rset5m.next()) {
                                    mOverSixtyOner = rset5m.getInt(1);
                                }

                                // female results.
                                while (rset10.next()) {
                                    fUnderOne = rset10.getInt(1);
                                }

                                while (rset11.next()) {
                                    fOneFour = rset11.getInt(1);
                                }

                                while (rset12.next()) {
                                    fFiveFourteen = rset12.getInt(1);
                                }

                                while (rset13.next()) {
                                    fFifteenFortyFour = rset13.getInt(1);
                                }

                                while (rset14.next()) {
                                    fFortyFiveSixty = rset14.getInt(1);
                                }

                                while (rset15.next()) {
                                    fOverSixtyOne = rset15.getInt(1);
                                }
//

                                while (rset10f.next()) {
                                    fUnderOner = rset10f.getInt(1);
                                }

                                while (rset11f.next()) {
                                    fOneFourr = rset11f.getInt(1);
                                }

                                while (rset12f.next()) {
                                    fFiveFourteenr = rset12f.getInt(1);
                                }

                                while (rset13f.next()) {
                                    fFifteenFortyFourr = rset13f.getInt(1);
                                }

                                while (rset14f.next()) {
                                    fFortyFiveSixtyr = rset14f.getInt(1);
                                }

                                while (rset15f.next()) {
                                    fOverSixtyOner = rset15f.getInt(1);
                                }

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnderOne)), pFontHeader1);
                                tmUnderOne = tmUnderOne + mUnderOne;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mUnderOner)), pFontHeader1);
                                tmUnderOner = tmUnderOner + mUnderOner;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOneFour)), pFontHeader1);
                                tmOneFour = tmOneFour + mOneFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOneFourr)), pFontHeader1);
                                tmOneFourr = tmOneFourr + mOneFourr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiveFourteen)), pFontHeader1);
                                tmFiveFourteen = tmFiveFourteen + mFiveFourteen;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFiveFourteenr)), pFontHeader1);
                                tmFiveFourteenr = tmFiveFourteenr + mFiveFourteenr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFifteenFortyFour)), pFontHeader1);
                                tmFifteenFortyFour = tmFifteenFortyFour + mFifteenFortyFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFifteenFortyFourr)), pFontHeader1);
                                tmFifteenFortyFourr = tmFifteenFortyFourr + mFifteenFortyFourr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFortyFiveSixty)), pFontHeader1);
                                tmFortyFiveSixty = tmFortyFiveSixty + mFortyFiveSixty;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mFortyFiveSixtyr)), pFontHeader1);
                                tmFortyFiveSixtyr = tmFortyFiveSixtyr + mFortyFiveSixtyr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOverSixtyOne)), pFontHeader1);
                                tmOverSixtyOne = tmOverSixtyOne + mOverSixtyOne;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mOverSixtyOner)), pFontHeader1);
                                tmOverSixtyOner = tmOverSixtyOner + mOverSixtyOner;
                                table.addCell(phrase);

                                // female
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fUnderOne)), pFontHeader1);
                                tfUnderOne = tfUnderOne + fUnderOne;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fUnderOner)), pFontHeader1);
                                tfUnderOner = tfUnderOner + fUnderOner;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fOneFour)), pFontHeader1);
                                tfOneFour = tfOneFour + fOneFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fOneFourr)), pFontHeader1);
                                tfOneFourr = tfOneFourr + fOneFourr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFiveFourteen)), pFontHeader1);
                                tfFiveFourteen = tfFiveFourteen + fFiveFourteen;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFiveFourteenr)), pFontHeader1);
                                tfFiveFourteenr = tfFiveFourteenr + fFiveFourteenr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFifteenFortyFour)), pFontHeader1);
                                tfFifteenFortyFour = tfFifteenFortyFour + fFifteenFortyFour;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFifteenFortyFourr)), pFontHeader1);
                                tfFifteenFortyFourr = tfFifteenFortyFourr + fFifteenFortyFourr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFortyFiveSixty)), pFontHeader1);
                                tfFortyFiveSixty = tfFortyFiveSixty + fFortyFiveSixty;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fFortyFiveSixtyr)), pFontHeader1);
                                tfFortyFiveSixtyr = tfFortyFiveSixtyr + fFortyFiveSixtyr;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fOverSixtyOne)), pFontHeader1);
                                tfOverSixtyOne = tfOverSixtyOne + fOverSixtyOne;
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(fOverSixtyOner)), pFontHeader1);
                                tfOverSixtyOner = tfOverSixtyOner + fOverSixtyOner;
                                table.addCell(phrase);

                                totalMaleFemale = (mUnderOne + mOneFour + mFiveFourteen + mFifteenFortyFour + mFortyFiveSixty + mOverSixtyOne) + (fUnderOne + fOneFour + fFiveFourteen + fFifteenFortyFour + fFortyFiveSixty + fOverSixtyOne);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMaleFemale)), pFontHeader1);
                                tMaleFemale = tMaleFemale + totalMaleFemale;
                                table.addCell(phrase);

                                totalMaleFemaler = (mUnderOner + mOneFourr + mFiveFourteenr + mFifteenFortyFourr + mFortyFiveSixtyr + mOverSixtyOner) + (fUnderOner + fOneFourr + fFiveFourteenr + fFifteenFortyFourr + fFortyFiveSixtyr + fOverSixtyOner);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMaleFemaler)), pFontHeader1);
                                tMaleFemaler = tMaleFemaler + totalMaleFemaler;
                                table.addCell(phrase);
                            }
                            // }
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
                            table.getDefaultCell().setMinimumHeight(20);
                            phrase = new Phrase("Totals for All Ages", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(1);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnderOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmUnderOner)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOneFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOneFourr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiveFourteen)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFiveFourteenr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFifteenFortyFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFifteenFortyFourr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFortyFiveSixty)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmFortyFiveSixtyr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOverSixtyOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tmOverSixtyOner)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfUnderOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfUnderOner)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOneFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOneFourr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFiveFourteen)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFiveFourteenr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFifteenFortyFour)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFifteenFortyFourr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFortyFiveSixty)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfFortyFiveSixtyr)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOverSixtyOne)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tfOverSixtyOner)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tMaleFemale)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tMaleFemaler)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(25);
                            phrase = new Phrase("Total Patients Diagnosed", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tMaleFemaler + tMaleFemale)), pFontHeader1);
                            table.addCell(phrase);

                            docPdf.add(table);
                            //docPdf.add(table1);
                            //docPdf.add(table2);
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

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct initcap(clinics) FROM pb_clinics WHERE clinics IS NOT NULL ORDER BY 1");

//           java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct upper(clinic) FROM hp_patient_visit WHERE clinic IS NOT NULL ORDER BY 1");
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
