//Author Charles Waweru and Amimo Benja
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.FileOutputStream;

public class NutritionAdultPatientOralRegimeFormPdf implements java.lang.Runnable {

    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    int numberSeq = 0;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
    com.lowagie.text.Font pFontHeader4 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader5 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    com.lowagie.text.Font pFontHeader6 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader7 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader8 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void NutritionAdultPatientAssessmentFormPdf(java.sql.Connection connDb) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            //    this.generatePdf();

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

    public void generatePdf(String patientNumber, String date, String Comments, String prot, String chos, String fats, String fluids, String kcals) {

        java.lang.String formLabel = null;

        formLabel = "ADULT'S FEEDING REGIME FORM";

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();


        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {

                    PdfWriter writer = com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


//                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(formLabel+" : ", pFontHeader6), true);
//
//                    docPdf.setFooter(footer);


                    docPdf.open();

                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    long dateNow = calendar.getTimeInMillis();

                    java.sql.Date datenowSql = new java.sql.Date(dateNow);

                    System.out.println(datenowSql.toString());


                    try {
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(8);

                        int headerwidths[] = {16, 16, 16, 16, 16, 16, 16, 16};
                        table.setWidths(headerwidths);
                        table.setWidthPercentage((105));

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);
                        int headerwidths2[] = {30, 70};

                        table2.setWidths(headerwidths2);
                        table2.setWidthPercentage((100));
                        table2.getDefaultCell().setFixedHeight(50);
                        table2.getDefaultCell().setColspan(2);
                        table2.getDefaultCell().setBorderColor(Color.WHITE);
                        table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                        img.scalePercent(50);
                        table2.addCell(img);
                        String compName = null;
                        String District = null;
                        String Region = null;
                        String date2 = null;
                        try {
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
                        } catch (java.sql.SQLException ex) {
                            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                            ex.printStackTrace();
                        }
                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase(compName.toUpperCase(), pFontHeader3);
                        table2.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("NUTRITION FEEDING REGIME FORM", pFontHeader2);
                        table2.addCell(phrase);

                        table.addCell(table2);

                        table.setWidths(headerwidths);
                        table.setWidthPercentage((100));
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setBorderColor(Color.WHITE);
                        table.getDefaultCell().setColspan(8);
                        phrase = new Phrase("");
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase(formLabel, pFontHeader4);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("KNH/NUT.ADM/026", pFontHeader7);
                        table.addCell(phrase);

                        String patient_name = "Unspecified", gender = "Not Specified", date_admitted = "Unknown", ward = "Unspecified";
                        String dateFG = "Unspecified", pat_age = "Unspecified";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm = connectDB.createStatement();
                            java.sql.ResultSet rse = stm.executeQuery(
                                    "SELECT patient_name, gender, "
                                    + "date_admitted, ward, pat_age, date_part('day', "
                                    + "now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date) FROM hp_admission "
                                    + "WHERE patient_no='" + patientNumber + "' "
                                    + "AND discharge = false ");

                            while (rse.next()) {

                                //Getting the user's Details;
                                patient_name = rse.getObject(1).toString();
                                gender = rse.getObject(2).toString();
                                date_admitted = rse.getObject(3).toString();
                                ward = rse.getObject(4).toString();
                                pat_age = rse.getObject(5).toString();
                                dateFG = rse.getObject(6).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }

                        String diagnosis = "Unspecified", height = "Unknown", weight = "Unknown";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm1 = connectDB.createStatement();
                            java.sql.ResultSet rse1 = stm1.executeQuery(
                                    "SELECT diagnosis, height, weight FROM nutrition.nutr_patient_antro_measures "
                                    + "WHERE patient_no='" + patientNumber + "' ");

                            while (rse1.next()) {

                                //Getting the user's Details;
                                diagnosis = rse1.getObject(1).toString();
                                height = rse1.getObject(2).toString();
                                weight = rse1.getObject(3).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);


                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("DIAGNOSIS - " + diagnosis, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("OP/IP NO. - " + patientNumber, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("NAME - " + patient_name, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("AGE - " + pat_age, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("WT - " + weight + " Kgs", pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("HT - " + height + " m", pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("WD/CLINIC - " + ward, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(5);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("DATE - " + dateFG, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Patient's Nutrient Requirements ", pFontHeader);
                        table.addCell(phrase);


                        String route_of_administration = "Unknown";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm3 = connectDB.createStatement();
                            java.sql.ResultSet rse3 = stm3.executeQuery(
                                    "SELECT route_of_administration FROM nutrition.nutr_patient_care_plan "
                                    + "WHERE patient_no='" + patientNumber + "' ");

                            while (rse3.next()) {

                                //Getting the user's Details;
                                route_of_administration = rse3.getObject(1).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }


                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Total KCAL - " + kcals, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("FATS - " + fats, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Carbohydrates - " + chos, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Protein - " + prot, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Fluids(m/s) - " + fluids, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Route of Administration - " + route_of_administration, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("MEAL ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("FEED ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("AMNT(M/S) ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("PROTEIN ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("CHO ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("FATS ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("CALORIES ", pFontHeader);
                        table.addCell(phrase);

                        String meal = "Unknown", feed = "Unknown", amount = "Unknown", t_prot = "Unknown", t_cho = "Unknown", t_fats = "Unknown", t_cal = "Unknown";
                        double tprot = 0.00, tcho = 0.00, tfats = 0.00, tcal = 0.00;

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm2 = connectDB.createStatement();
                            java.sql.ResultSet rse2 = stm2.executeQuery(
                                    "SELECT meal, feed, amount, t_prot, t_cho, t_fats, t_cal FROM nutrition.nutr_patient_oral_feeding_regime "
                                    + "WHERE patient_no='" + patientNumber + "' "
                                    + "AND date = '" + date + "'");

                            while (rse2.next()) {

                                //Getting the user's Details;
                                meal = rse2.getObject(1).toString();
                                feed = rse2.getObject(2).toString();
                                amount = rse2.getObject(3).toString();
                                t_prot = rse2.getObject(4).toString();
                                t_cho = rse2.getObject(5).toString();
                                t_fats = rse2.getObject(6).toString();
                                t_cal = rse2.getObject(7).toString();

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(meal, pFontHeader8);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(feed, pFontHeader8);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(amount, pFontHeader8);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(t_prot, pFontHeader8);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(t_cho, pFontHeader8);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(t_fats, pFontHeader8);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(t_cal, pFontHeader8);
                                table.addCell(phrase);

                                tprot += Double.valueOf(t_prot);
                                tcho += Double.valueOf(t_cho);
                                tfats += Double.valueOf(t_fats);
                                tcal += Double.valueOf(t_cal);

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }



                        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);


                        table.getDefaultCell().setBorder(Rectangle.BOX);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        table.getDefaultCell().setColspan(4);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Actual Nutrients Prescribed ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("" + tprot, pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("" + tcho, pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("" + tfats, pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("" + tcal, pFontHeader);
                        table.addCell(phrase);


                        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("Comments/Recommendations - " + Comments, pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(8);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        try {
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access where login_name = current_user order by 1 limit 1), date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                            while (rsetUser.next()) {
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Nutritionist : ".toUpperCase() + rsetUser.getString(1), pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Signature : ", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Date : ".toUpperCase() + rsetUser.getString(2), pFontHeader);
                                table.addCell(phrase);

                            }

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        docPdf.add(table);


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

            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }
    }
}
