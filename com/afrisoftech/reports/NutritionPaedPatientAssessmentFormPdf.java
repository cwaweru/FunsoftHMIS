//Author Charles Waweru and Amimo Benja
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.FileOutputStream;

public class NutritionPaedPatientAssessmentFormPdf implements java.lang.Runnable {

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

    public void generatePdf(String patientNumber, java.util.Date date) {

        java.util.Date date111 = date;
        java.lang.String formLabel = null;

        formLabel = "PAEDIATRICS'S ASSESSEMENT FORM";

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


                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(formLabel + " : ", pFontHeader6), true);

                    docPdf.setFooter(footer);


                    docPdf.open();

                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                    long dateNow = calendar.getTimeInMillis();

                    java.sql.Date datenowSql = new java.sql.Date(dateNow);

                    System.out.println(datenowSql.toString());


                    try {
                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {16, 16, 16, 16, 16, 16};
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
                        String date1 = null;
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
                                date1 = rset4.getObject(1).toString();
                            }
                        } catch (java.sql.SQLException ex) {
                            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), ex.getMessage());
                            ex.printStackTrace();
                        }
                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        Phrase phrase = new Phrase(compName.toUpperCase(), pFontHeader3);
                        table2.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("NUTRITION ASSESSEMENT AND CARE (PAEDIATRICS)", pFontHeader2);
                        table2.addCell(phrase);

                        table.addCell(table2);

                        table.setWidths(headerwidths);
                        table.setWidthPercentage((100));
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setBorderColor(Color.WHITE);
                        table.getDefaultCell().setColspan(6);
                        phrase = new Phrase("");
                        table.addCell(phrase);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(5);
                        phrase = new Phrase(formLabel, pFontHeader4);
                        table.addCell(phrase);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("KNH/NUT.ADM/024", pFontHeader7);
                        table.addCell(phrase);

                        String patient_name = "Unspecified", gender = "Not Specified", date_admitted = "Unknown";
                        String pat_age = "Unknown", ward = "Unspecified";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm = connectDB.createStatement();
                            java.sql.ResultSet rse = stm.executeQuery(
                                    "SELECT patient_name, gender,"
                                    + " date_admitted, ward, pat_age FROM hp_admission "
                                    + "WHERE patient_no='" + patientNumber + "' "
                                    + "AND discharge = false ");

                            while (rse.next()) {

                                //Getting the user's Details;
                                patient_name = rse.getObject(1).toString();
                                gender = rse.getObject(2).toString();
                                date_admitted = rse.getObject(3).toString();
                                ward = rse.getObject(4).toString();
                                pat_age = rse.getObject(5).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }


                        String diagnosis = "Unspecified", child_birth_order = "Unspecified", dateDate = "Unspecified";
                        String parent_mar_status = "Unspecified", total_no_siblings = "Unspecified", mother_age = "Unspecified";
                        String mother_occupation = "Unspecified", father_age = "Unspecified", father_occupation = "Unspecified";
                        String other_info = "Unspecified";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm1 = connectDB.createStatement();
                            java.sql.ResultSet rse1 = stm1.executeQuery(
                                    "SELECT diagnosis, child_birth_order, parent_mar_status, total_no_siblings, mother_age, "
                                    + "mother_occupation, father_age, father_occupation, other_info, "
                                    + "date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date) "
                                    + "FROM nutrition.nutr_paed_family_history WHERE patient_no='" + patientNumber + "' ");

                            while (rse1.next()) {

                                //Getting the user's Details;
                                diagnosis = rse1.getObject(1).toString();
                                child_birth_order = rse1.getObject(2).toString();
                                parent_mar_status = rse1.getObject(3).toString();
                                total_no_siblings = rse1.getObject(4).toString();
                                mother_age = rse1.getObject(5).toString();
                                mother_occupation = rse1.getObject(6).toString();
                                father_age = rse1.getObject(7).toString();
                                father_occupation = rse1.getObject(8).toString();
                                other_info = rse1.getObject(9).toString();
                                dateDate = rse1.getObject(10).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }


                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        phrase = new Phrase("");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Patient Name: " + patient_name, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Diagnosis: " + diagnosis, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("IP NO: " + patientNumber, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Age: " + pat_age, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Sex: " + gender, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Ward: " + ward, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Date: " + dateDate, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Family History ", pFontHeader);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Child birth order: " + child_birth_order, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Parent's marital status: " + parent_mar_status, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Total No. of Sibling in family and their ages: " + total_no_siblings, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Mother's age: " + mother_age, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Occupation: " + mother_occupation, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Father's age: " + father_age, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Occupation: " + father_occupation, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Other psychological information: " + other_info, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Nutrition History ", pFontHeader);
                        table.addCell(phrase);

                        String breast_feeding_history = "Unspecified", weaning_age_food = "Unspecified", dietary_intake = "Unspecified";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm2 = connectDB.createStatement();
                            java.sql.ResultSet rse2 = stm2.executeQuery(
                                    "SELECT breast_feeding_history, weaning_age_food, dietary_intake "
                                    + "FROM nutrition.nutr_paed_nutrition_history WHERE patient_no='" + patientNumber + "' ");

                            while (rse2.next()) {

                                //Getting the user's Details;
                                breast_feeding_history = rse2.getObject(1).toString();
                                weaning_age_food = rse2.getObject(2).toString();
                                dietary_intake = rse2.getObject(3).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Breastfeeding History (if relevant): " + breast_feeding_history, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Weaning age and food used: " + weaning_age_food, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Usual and Current Dietary intake: " + dietary_intake, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Anthropometric Assessement ", pFontHeader);
                        table.addCell(phrase);

                        String birth_wt = "Unspecified", adm_wt = "Unspecified", height = "Unspecified";
                        String wt_for_age = "Unknown", ref_wt_ag = "Unspecified", clin_biochem = "Unspecified", opion_concl = "Unspecified";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm3 = connectDB.createStatement();
                            java.sql.ResultSet rse3 = stm3.executeQuery(
                                    "SELECT birth_wt, adm_wt, height, ref_wt_for_age, wt_for_age, other_clinical_biochem, opion_conclusion "
                                    + "FROM nutrition.nutr_paed_anthro_assessement WHERE patient_no='" + patientNumber + "' ");

                            while (rse3.next()) {

                                //Getting the user's Details;
                                birth_wt = rse3.getObject(1).toString();
                                adm_wt = rse3.getObject(2).toString();
                                height = rse3.getObject(3).toString();
                                ref_wt_ag = rse3.getObject(4).toString();
                                wt_for_age = rse3.getObject(5).toString();
                                clin_biochem = rse3.getObject(6).toString();
                                opion_concl = rse3.getObject(7).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Birth wt.(Kg): " + birth_wt, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Adm Wt.(Kg): " + adm_wt, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Height(cm): " + height, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Ref. Wt. for Age (Kg): " + ref_wt_ag, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Wt. for age %: " + wt_for_age, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Other clinical and biochemical assessement \n - " + clin_biochem, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Opinion/Conclusion (Mention briefly the nutritional "
                                + "status markers which have led you to this opion) \n - " + opion_concl, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("Nutrition Care Plan ", pFontHeader);
                        table.addCell(phrase);

                        String care_plan = "None";

                        try {

                            connectDB.setAutoCommit(false);

                            //About to Get the Patient Details
                            java.sql.Statement stm4 = connectDB.createStatement();
                            java.sql.ResultSet rse4 = stm4.executeQuery(
                                    "SELECT care_plan "
                                    + "FROM nutrition.nutr_paed_care_plan WHERE patient_no='" + patientNumber + "' ");

                            while (rse4.next()) {

                                //Getting the user's Details;
                                care_plan = rse4.getObject(1).toString();

                            }

                            connectDB.commit();
                            connectDB.setAutoCommit(true);
                        } catch (final Exception es) {
                            System.out.println(es);

                        }

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase("\n" + care_plan, pFontHeader8);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(6);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        phrase = new Phrase(" ");
                        table.addCell(phrase);

                        try {
                            java.sql.PreparedStatement pstmtUser = connectDB.prepareStatement("SELECT (SELECT upper(f_name||' '||l_name) FROM secure_menu_access where login_name = current_user order by 1 limit 1), date_part('day', now()::date) ||'-'||date_part('month', now()::date) ||'-'||date_part('year', now()::date)");
                            java.sql.ResultSet rsetUser = pstmtUser.executeQuery();

                            while (rsetUser.next()) {
                                table.getDefaultCell().setColspan(2);
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
