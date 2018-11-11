//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

//import //java.awt.Desktop;
public class PatientLabResultsPdf implements java.lang.Runnable {

    java.lang.String memNo = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String memNo1 = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    //  java.lang.String endDate = null;
    double osBalance = 0.00;
    double osBalance1 = 0.00;
    double current = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    //    endDate = endate;
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientLabResultsPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String combox1) {
        memNo = combox;
        memNo1 = combox1;
        // endDate = enddate;
        // beginDate = begindate;

        connectDB = connDb;

        dbObject = new com.afrisoftech.lib.DBObject();

        // beginDate = begindate;
        // endDate = endate;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberStatementPdf().MemberStatementPdf(args[0]);
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            this.generatePdf(memNo);

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

    public void generatePdf(java.lang.String memNo) {

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

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    String Messg = null;

                    docPdf.open();

                    try {

                        java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                        for (int j = 0; j < listofStaffNos.length; j++) {

                            System.out.println("Request ID or Lab No. [" + listofStaffNos[j] + "]");

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                            int headerwidths[] = {25, 25, 20, 15, 15, 15};

                            table.setWidths(headerwidths);
                            //table..setWidths(headerwidths);
                            table.setWidthPercentage((100));

                            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setColspan(6);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setFixedHeight(50);
                            table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                            table.getDefaultCell().setFixedHeight(-1);
                            Phrase phrase = new Phrase("");
                            phrase = new Phrase();

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            String Test = null;
                            String testName = null;
                            try {
                                java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement stc = connectDB.createStatement();
                                java.sql.Statement stb = connectDB.createStatement();
                                java.sql.Statement sta = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st22 = connectDB.createStatement();
                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st32 = connectDB.createStatement();

                                //  java.sql.ResultSet rset3 = st3.executeQuery("SELECT hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no FROM pb_hospitalprofile");
                                java.sql.ResultSet rset22 = st32.executeQuery("SELECT header_name,current_date FROM pb_header");
                                //  java.sql.ResultSet rseta = sta.executeQuery("SELECT ad.ward,ad.bed_no,ad.doctor ,pr.adm_date,pr.discharge_date FROM hp_admission ad,hp_inpatient_register pr WHERE pr.patient_no = '"+memNo+"' and pr.patient_no = ad.patient_no");

                                java.sql.ResultSet rset = st1.executeQuery("SELECT DISTINCT patient_no,initcap(patient_name),lab_no,age::numeric(5,0),gender,date,spec_time, instrument_name FROM hp_lab_results WHERE lab_no ilike '" + listofStaffNos[j] + "' or request_id ilike '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset4 = sta.executeQuery("SELECT DISTINCT lab_no,age::numeric(5,0),gender,date ,spec_time, lower_limit, upper_limit FROM hp_lab_results WHERE lab_no ilike '" + listofStaffNos[j] + "' or request_id ilike '" + listofStaffNos[j] + "'");
                                java.sql.ResultSet rset41 = st2.executeQuery("SELECT DISTINCT typeof_test FROM hp_lab_results WHERE lab_no ilike '" + listofStaffNos[j] + "' or request_id ilike '" + listofStaffNos[j] + "'");

                                java.sql.ResultSet rset4111 = st22.executeQuery("SELECT DISTINCT user_name,lab_manager, pathologist,doctor, instrument_name FROM hp_lab_results WHERE lab_no ilike '" + listofStaffNos[j] + "' or request_id ilike '" + listofStaffNos[j] + "'");

                                String Date = null;
                                System.out.println("No 1");
                                while (rset22.next()) {
                                    table.getDefaultCell().setColspan(6);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"), pFontHeader11);
                                    table.addCell(phrase);
                                    Date = rset22.getObject(2).toString();

                                    table.getDefaultCell().setColspan(6);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase("Laboratory Results".toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    while (rset.next()) {
                                        // while (rset4.next()) {

                                        table.getDefaultCell().setColspan(3);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Lab No.         :  ".toUpperCase() + dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(3);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Equipment         :  ".toUpperCase() + dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Patient No.", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Patient Name", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        System.out.println("No 2");

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Patient Age", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Gender", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Specimen Time", pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Date", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(3);
                                        phrase = new Phrase(" ", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Print Date", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(Date, pFontHeader1);
                                        table.addCell(phrase);
                                    }
                                }

                                System.out.println("No 3");

                                String Notice = "-";
                                String Status = null;
                                //   java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                                //  for (int j = 0; j < listofStaffNos.length; j++) {

                                System.out.println("From list : [" + listofStaffNos[j].toString() + "]");
                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT initcap(code),typeof_test FROM hp_lab_results WHERE  lab_no ilike '" + listofStaffNos[j].toString() + "' or request_id ilike '" + listofStaffNos[j].toString() + "'");
                                //                              java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT initcap(code),typeof_test FROM hp_lab_results WHERE  lab_no ilike '" + listofStaffNos[j].toString() + "' or request_id ilike '" + listofStaffNos[j].toString() + "'");

                                while (rset12.next()) {
                                    // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    Test = rset12.getObject(1).toString();
                                    System.out.println("From list : [" + Test + "]");
                                    testName = rset12.getObject(2).toString();
                                    java.sql.ResultSet rset4c = stc.executeQuery("SELECT DISTINCT status,notes FROM pb_lab_standards WHERE  code ilike '" + Test + "'");
                                    while (rset4c.next()) {
                                        Status = rset4c.getString(1).toLowerCase();

                                        Notice = dbObject.getDBObject(rset4c.getString(2), "-");
                                        System.out.println(Status);
                                    }
                                    java.sql.ResultSet rset1 = st.executeQuery("SELECT parameter,out_come||' '||units,lower_limit,upper_limit, out_come FROM hp_lab_results WHERE code ilike '" + Test + "'  and (lab_no ilike '" + listofStaffNos[j] + "' or request_id ilike '" + listofStaffNos[j] + "') order by 1");
                                    // while (rset41.next()){
                                    java.sql.ResultSet rset411 = st21.executeQuery("SELECT DISTINCT comments FROM hp_lab_results WHERE  (lab_no ilike '" + listofStaffNos[j] + "' or request_id ilike '" + listofStaffNos[j] + "') and code ilike '" + Test + "' ");

                                    if (Status.startsWith("t")) {

                                        System.out.println("No 4");

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

//                                        table.getDefaultCell().setColspan(6);
//                                        phrase = new Phrase("REFERENCE RANGE", pFontHeader1);
//                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(6);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("PROCEDURE: " + testName.toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase("PARAMETER", pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("RESULT", pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("LOWER LIMIT", pFontHeader1);
                                        table.addCell(phrase);
                                        // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("UPPER LIMIT", pFontHeader1);
                                        table.addCell(phrase);

                                        phrase = new Phrase("STATUS", pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                                        table.getDefaultCell().setColspan(18);
//                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                        phrase = new Phrase("   ", pFontHeader1);
//                                        table.addCell(phrase);
                                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                                        // }
                                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        //phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                        //table.addCell(phrase);
                                        System.out.println("No 5");
                                        while (rset1.next()) {
                                            table.getDefaultCell().setColspan(2);
                                            //       table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                            phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);

                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(1);
                                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);
                                            table.addCell(phrase);

                                            if (rset1.getDouble(5) > rset1.getDouble(3) && rset1.getDouble(5) < rset1.getDouble(4)) {
                                                table.getDefaultCell().setBackgroundColor(Color.GREEN);
                                            } else {
                                                table.getDefaultCell().setBackgroundColor(Color.RED);
                                            }
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(" ", pFontHeader);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setBackgroundColor(Color.WHITE);

                                        }
                                        while (rset411.next()) {
                                            table.getDefaultCell().setColspan(6);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Comments:  " + dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                            table.addCell(phrase);
                                        }

                                        System.out.println("No 6");
                                    } else {

                                        table.getDefaultCell().setColspan(18);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("   ", pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                        //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                        table.getDefaultCell().setColspan(3);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("TEST: " + testName.toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(3);

                                        // table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Result", pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                        //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                        // phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                        //table.addCell(phrase);
                                        System.out.println("No7");
                                        while (rset1.next()) {
                                            table.getDefaultCell().setColspan(3);
                                            //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(3);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);

                                            table.addCell(phrase);

                                        }
                                        System.out.println("No 8");
                                        while (rset411.next()) {
                                            table.getDefaultCell().setColspan(6);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Comments:  " + dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                    }
                                    table.getDefaultCell().setColspan(18);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("   ", pFontHeader1);
                                    table.addCell(phrase);

                                }
                                System.out.println("No 9");

                                table.getDefaultCell().setColspan(18);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("   ", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Performed By:  ", pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase("Confirmed By: ", pFontHeader1);
                                table.addCell(phrase);
                                while (rset4111.next()) {

                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset4111.getObject(1), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset4111.getObject(2), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                            boolean boolNewPage = docPdf.newPage();
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

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payee FROM ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dealer ilike '"+memNo+"%' order by payee");
            /////  java.sql.ResultSet rSet1x = stmt1.executeQuery("SELECT DISTINCT lab_no FROM hp_lab_results WHERE  (lab_no  between '" + memNo + "' and '" + memNo1 + "') UNION SELECT DISTINCT request_id FROM lims_lab_results WHERE (request_id = '" + memNo + "' or request_id = '" + memNo1 + "') order by 1");
            java.sql.ResultSet rSet1x = stmt1.executeQuery("SELECT DISTINCT lab_no FROM hp_lab_results WHERE  (lab_no  between '" + memNo + "' and '" + memNo1 + "') ORDER BY 1"); //UNION SELECT DISTINCT request_id FROM lims_lab_results WHERE (request_id = '" + memNo + "' or request_id = '" + memNo1 + "') order by 1");

            while (rSet1x.next()) {

                listStaffNoVector.addElement(rSet1x.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos = listStaffNoVector.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos;
    }
}
