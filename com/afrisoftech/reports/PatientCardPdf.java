//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;
//package com.chis.laboratory;

import static com.afrisoftech.reports.PatientBillPdf.connectDB;
import static com.afrisoftech.reports.emr.MOHPatientCardPdf.getPartoChart;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import org.jfree.chart.ChartUtilities;

public class PatientCardPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    //com.chis.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    double osBalance = 0.00;
    double current = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.itextpdf.text.Font pFontHeader = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL);
    com.itextpdf.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.COURIER, 9, Font.BOLD);
    //   com.itextpdf.text.ParagraphFont pgraph = Paragraph();
    com.itextpdf.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientCardPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {

        MNo = combox;

        dbObject = new com.afrisoftech.lib.DBObject();
        // dbObject = new com.chis.lib.DBObject();

        connectDB = connDb;
        beginDate = begindate;

        endDate = endate;

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

            this.generatePdf(MNo);

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

            com.itextpdf.text.Document docPdf = new com.itextpdf.text.Document();

            try {

                try {

                    com.itextpdf.text.Phrase headerFoter = null;
                    com.itextpdf.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;

                    com.itextpdf.text.Phrase footer = new com.itextpdf.text.Phrase("Patient card - Page: ");//Factory.getFont(com.itextpdf.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.open();
                    try {
                        com.itextpdf.text.pdf.PdfPTable headertable = new com.itextpdf.text.pdf.PdfPTable(6);

                        int headerwidthstable[] = {15, 7, 25, 7, 8, 18};

                        headertable.setWidths(headerwidthstable);

                        headertable.setWidthPercentage((100));

                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        headertable.getDefaultCell().setColspan(6);

                        Phrase headerphrase = new Phrase();

                        headertable.getDefaultCell().setBorder(Rectangle.BOX);
                        headertable.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                        headertable.getDefaultCell().setColspan(2);
                        headertable.getDefaultCell().setFixedHeight(70);
                        headertable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        headertable.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");

                        while (rset3.next()) {
                            headertable.getDefaultCell().setColspan(4);

                            headertable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                            headertable.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                            headertable.getDefaultCell().setBorder(Rectangle.BOX);
                            headerphrase = new Phrase("\n\n" + rset3.getObject(1).toString(), pFontHeader11);
                            headertable.addCell(headerphrase);
                        }
                        docPdf.add(headertable);
                    } catch (Exception sy) {
                        sy.printStackTrace();
                    }

                    try {

                        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(6);

                        int headerwidths[] = {15, 25, 7, 7, 8, 18};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(com.itextpdf.text.BaseColor.WHITE);
                        table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                        String Exam = null;
                        String Doctor = null;
                        String Hist = null;
                        String visit = null;
                        String reg = null;
                        String treatmentPlan = null;
                        try {
                            java.sql.Statement st2T = connectDB.createStatement();
                            java.sql.Statement st2d = connectDB.createStatement();

                            java.sql.Statement st2g = connectDB.createStatement();

                            java.sql.Statement st12 = connectDB.createStatement();
                            java.sql.Statement st13 = connectDB.createStatement();

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st14 = connectDB.createStatement();
                            java.sql.Statement st15 = connectDB.createStatement();
                            java.sql.Statement sth = connectDB.createStatement();
                            java.sql.Statement stp = connectDB.createStatement();
                            java.sql.Statement st2c = connectDB.createStatement();
                            java.sql.Statement st2e = connectDB.createStatement();
                            java.sql.Statement sth2 = connectDB.createStatement();
                            java.sql.Statement sth22 = connectDB.createStatement();
                            java.sql.Statement st2c1 = connectDB.createStatement();
                            java.sql.Statement st2c11 = connectDB.createStatement();
                            java.sql.Statement st2c111 = connectDB.createStatement();
                            java.sql.Statement st2e1 = connectDB.createStatement();

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                            phrase = new Phrase("PATIENT CARD", pFontHeader11);
                            table.addCell(phrase);
                            java.sql.ResultSet rsetp = stp.executeQuery("select distinct patient_no,initcap(first_name||' '||second_name),Category,residence,tel_no,last_visit,sex,nok,((current_date-year_of_birth::date)/365)::int as age,pay_mode from hp_patient_register where patient_no = '" + memNo + "' UNION "
                                    + "SELECT DISTINCT patient_no, initcap(first_name||' '||second_name), ''as Category,residence, tel_no,  adm_date::date as last_visit, sex, nok, (CASE WHEN year_of_birth = '' THEN 0 ELSE (current_date-year_of_birth::date)/365 END) as age, pay_mode FROM hp_inpatient_register where patient_no = '" + memNo + "'");
                            //  java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '" + memNo + "'  order by date desc limit 1");

                            while (rsetp.next()) {

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase(rsetp.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase("Category", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(3), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase(rsetp.getObject(2).toString(), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No.", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(5), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Gender", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(7), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Next Of Kin", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(8), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Age", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(9), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Date Last Visit", pFontHeader11
                                );
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(6), "-"), pFontHeader11
                                );
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase("Residence", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(4), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Payment Mode", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(10), "-"), pFontHeader11);
                                table.addCell(phrase);

                            }

                            table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {

                                java.sql.Time dates = null;
                                String docs = null;

                                java.sql.ResultSet rset1 = st1.executeQuery("select DISTINCT curr_date::time(0) from pb_doctors_request where patient_no = '" + memNo + "' and curr_date::date  = '" + listofStaffNos[j] + "' ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT weight,height,diastolic,"
                                        + "systolic,pulse,temp,resp,"
                                        + "blood_group,rhesus,rbs,initcap(comments),input_date::time(0),initcap(user_name) "
                                        + "from hp_signs_record where patient_no = '" + memNo + "' and"
                                        + " input_date::date  = '" + listofStaffNos[j] + "' ORDER BY input_date::time(0) ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                //java.sql.ResultSet rset13 = st13.executeQuery("select initcap(service),requisition_no,bed_no,time_due from pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%labo%' and trans_date  = '" + listofStaffNos[j] + "' ORDER BY 1");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset14 = st14.executeQuery("select DISTINCT initcap(service),requisition_no,bed_no||'    '||time_due,dosage,curr_date::TIME(0),INITCAP(doctor) "
                                        + "FROM pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%Pharm%' and trans_date  = '" + listofStaffNos[j] + "' ORDER BY curr_date::TIME(0) ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");

                                java.sql.ResultSet rset2e = st2e.executeQuery("SELECT Distinct initcap(typeof_test), "
                                        + "initcap(comments),initcap(description),initcap(doctor),'',input_date::TIME(0) "
                                        + "FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND "
                                        + "date  = '" + listofStaffNos[j] + "' AND typeof_test != '' AND comments IS NOT NULL GROUP BY "
                                        + "typeof_test, comments,description,doctor,input_date::TIME(0) "
                                        + "UNION ALL "
                                        + " SELECT INITCAP(hpc||' '||pmhs||' '||pdhs||' '||exam||' '||exam_notes"
                                        + " ||' '||imp_inv||' '||inv),initcap(pc),'',initcap(doctor),'',input_date::TIME(0)"
                                        + "  FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' AND xray_no ilike  'd%'"
                                        + " UNION ALL "
                                        + "SELECT indicator_checked, remarks, '', initcap(user_name), '', visit_date::time(0)  FROM hp_screening_data WHERE patient_no = '" + memNo + "' AND visit_date::date  = '" + listofStaffNos[j] + "' "
                                        + "UNION ALL "
                                        + " SELECT requested_procedure, procedure_result, '', initcap(user_name), '', request_time::time(0)  FROM hp_screening_procedures WHERE patient_no = '" + memNo + "' AND request_time::date  = '" + listofStaffNos[j] + "' "
                                        + "ORDER BY 6 ");
                                // java.sql.ResultSet rset2e1 = st2e1.executeQuery(" select distinct doctor,input_date from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' ORDER BY INPUT_DATE");

                                // java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '"+memNo+"'  ");
                                while (rset1.next()) {
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    //visit = dbObject.getDBObject(rset1.getObject(2), "-");
                                    reg = dbObject.getDBObject(rset1.getObject(1), "-");

                                    //rset1.getObject(2).toString();
                                    // phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                }

                                //     java.sql.ResultSet rseth2 = sth2.executeQuery(" select distinct narration from hp_patients_hist where patient_no = '" + memNo + "'  and date  = '" + listofStaffNos[j] + "' and hist_heading = '" + Hist + "'");
                                java.sql.ResultSet rset2c1 = st2c1.executeQuery("SELECT DISTINCT INITCAP(treatment),input_date::TIME(0),INITCAP(doctor), '' as notes "
                                        + " FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND treatment != '' AND comments IS NOT NULL  "
                                        + " UNION ALL SELECT DISTINCT INITCAP(examination),input_date::TIME(0), INITCAP(doctor), notes"
                                        + " FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' "
                                        + " UNION SELECT INITCAP(service), curr_date::TIME(0), initcap(doctor), '' as notes FROM pb_doctors_request WHERE revenue_code not ilike '%pharmacy%' AND patient_no = '" + memNo + "' AND trans_date  = '" + listofStaffNos[j] + "' ORDER BY 2 ");
                                java.sql.ResultSet rset2c11 = st2c11.executeQuery("SELECT DISTINCT INITCAP(description),input_date::TIME(0), initcap(doctor), '' as notes from hp_clinical_results "
                                        + "WHERE patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND description != '' AND DESCRIPTION IS NOT NULL  AND description NOT ILIKE 'null%' "
                                        + " UNION ALL "
                                        + "SELECT INITCAP(diag),input_date::TIME(0),doctor, notes "
                                        + "FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "'  AND diag != '' AND diag IS NOT NULL  AND diag NOT ILIKE 'null%' "
                                        + "ORDER BY 2");
                                java.sql.ResultSet rset2c111 = st2c111.executeQuery("SELECT DISTINCT INITCAP(disease), date_of_entry::TIME(0)::varchar, initcap(user_name) as doctor from hp_patient_diagnosis "
                                        + "WHERE patient_no = '" + memNo + "' and date_recorded  = '" + listofStaffNos[j] + "' AND disease != '' AND disease IS NOT NULL  AND disease NOT ILIKE 'null%' ");
                                java.sql.ResultSet rset2c = st2c.executeQuery("SELECT DISTINCT INITCAP(comments), INITCAP(treatment), INITCAP(doctor),input_date::TIME(0), '' as notes,"
                                        + "extra_oral, intra_oral, pdhx, pmhx, familysocial, review, treatmentplan FROM hp_clinical_results "
                                        + "where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND comments IS NOT NULL  AND comments != '' AND comments NOT ILIKE 'null%' "
                                        + " UNION ALL "
                                        + " SELECT INITCAP(pc),INITCAP(plan),INITCAP(doctor),input_date::TIME(0), notes,"
                                        + " '' as extra_oral, '' as intra_oral, '' as pdhx, '' as pmhx, '' as familysocial, '' as review, '' as treatmentplan  FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' "
                                        + "  AND pc IS NOT NULL  AND pc != '' AND pc NOT ILIKE 'null%'"
                                        + "GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12");
                                java.sql.ResultSet rset2g = st2g.executeQuery("SELECT DISTINCT INITCAP(result),INITCAP(comm_reason),input_date::TIME(0),INITCAP(doctor) FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND result != '' AND comm_reason != '' AND comments IS NOT NULL  ORDER BY input_date");
                                //java.sql.ResultSet rsetTotals = st2.executeQuery("select  symptom,result,duration,description,doctor from hp_patients_hist where patient_no = '"+memNo+"' and date  = '"+listofStaffNos[j]+"' and hist_heading = '"+exam+"'")
                                //  java.sql.ResultSet rset14 = st14.executeQuery("select initcap(description),date_curr::time,date_curr::time from hp_pharmacy where patient_no = '"+memNo+"' and date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                // java.sql.ResultSet rset15 = st15.executeQuery("select initcap(service),date_curr::time,date_curr::time from hp_patient_billing where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                                /*9while (rset2e1.next()) {
                                 //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                 Doctor = dbObject.getDBObject(rset2e1.getObject(1), "-");
                                
                                 // table.addCell(phrase);
                                 }*/
                                phrase = new Phrase(" ", pFontHeader11);
                                //table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                //phrase = new Phrase(" ", pFontHeader11);
                                //table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                phrase = new Phrase("ON DATE : " + listofStaffNos[j], pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("VITAL SIGNS", pFontHeader11);

                                table.addCell(phrase);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                while (rset12.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(rset12.getObject(12), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase("Weight : " + dbObject.getDBObject(rset12.getObject(1), "-") + " " + "Height : " + dbObject.getDBObject(rset12.getObject(2), "-") + " "
                                            + "\n Diastolic : " + dbObject.getDBObject(rset12.getObject(3), "-") + " " + "  Systolic : " + dbObject.getDBObject(rset12.getObject(4), "-") + " "
                                            + "\n Pulse : " + dbObject.getDBObject(rset12.getObject(5), "-") + " " + "  Temp : " + dbObject.getDBObject(rset12.getObject(6), "-") + " "
                                            + "\n Resp : " + dbObject.getDBObject(rset12.getObject(7), "-") + " " + "  Rhesus : " + dbObject.getDBObject(rset12.getObject(9), "-") + " "
                                            + "\n Blood Group : " + dbObject.getDBObject(rset12.getObject(8), "-") + " " + " Rbs : " + dbObject.getDBObject(rset12.getObject(10), "-") + " "
                                            + "\n Comments : " + dbObject.getDBObject(rset12.getObject(11), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(dbObject.getDBObject(rset12.getObject(13), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                }
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                phrase = new Phrase("HISTORY/COMPLAINTS ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                while (rset2c.next()) {
                                    treatmentPlan = rset2c.getString("treatmentplan");
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    // "extra_oral, intra_oral, pdhx, pmhx, familysocial,review, treatmentplan
                                    String extra_oral = rset2c.getString("extra_oral") != null ? rset2c.getString("extra_oral") : ""; 
                                    String intra_oral = rset2c.getString("intra_oral") != null ? rset2c.getString("intra_oral") : ""; 
                                    String pdhx = rset2c.getString("pdhx") != null ? rset2c.getString("pdhx") : ""; 
                                    String pmhx = rset2c.getString("pmhx") != null ? rset2c.getString("pmhx") : ""; 
                                    String familysocial = rset2c.getString("familysocial") != null ? rset2c.getString("familysocial") : "";
                                    String review = rset2c.getString("review") != null ? rset2c.getString("review") : ""; 
                                    String treatmentplan;
                                    phrase = new Phrase(dbObject.getDBObject(rset2c.getObject(1) + "\nEXTRA ORAL: "
                                            + extra_oral
                                            + "\nINTRA ORAL: " + intra_oral
                                            + "\nPDHX: " + pdhx  + "\nPMHX: "
                                            + pmhx + "\nFAMILY SOCIAL: "
                                            + familysocial
                                            + "\nREVIEW: " + review , "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                phrase = new Phrase(" ", pFontHeader);

                                //Exam = dbObject.getDBObject(rset2e.getObject(1), "-");
                                //Doctor = dbObject.getDBObject(rset2e.getObject(4), "-");
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);

                                phrase = new Phrase("GENERAL EXAMINATION", pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);

                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);

                                while (rset2e.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    // table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2e.getObject(5), "-") + " " + dbObject.getDBObject(rset2e.getObject(6), "-"), pFontHeader);

                                    //Exam = dbObject.getDBObject(rset2e.getObject(1), "-");
                                    //Doctor = dbObject.getDBObject(rset2e.getObject(4), "-");
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2e.getObject(1), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    //Exam = dbObject.getDBObject(rset2e.getObject(1), "-");
                                    Doctor = dbObject.getDBObject(rset2e.getObject(4), "-");

                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(Doctor, pFontHeader);

                                    table.addCell(phrase);

                                }

                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);

                                phrase = new Phrase("CLINICAL ILLUSTRATIONS", pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                java.sql.PreparedStatement pstmtImages = connectDB.prepareStatement("SELECT data_capture_time, document_ref_no, data_capture_time::time(0)::varchar, initcap(user_name)::varchar FROM funsoft_image_graphics WHERE data_capture_time::date = '" + listofStaffNos[j] + "' AND document_ref_no = ? AND (document_source = 'EYE_UNIT_ILLUSTRATION' OR document_source = 'PATIENT_DIAGNOSTICS') ORDER BY 1");

                                pstmtImages.setString(1, memNo);

                                java.sql.ResultSet rsetImages = pstmtImages.executeQuery();

                                while (rsetImages.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                    phrase = new Phrase(rsetImages.getString(3), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                                    //  Image image = com.afrisoftech.lib.SaveBytea2DB.getImage(connectDB, memNo, rsetImages.getTimestamp(1));
//                                    table.addCell(image);
                                    ByteArrayOutputStream bout = com.afrisoftech.lib.SaveBytea2DB.getImageBytea(connectDB, memNo, rsetImages.getTimestamp(1));
                                    // ChartUtilities.writeChartAsPNG(bout, getPartoChart(), 750, 380);

                                    table.addCell(Image.getInstance(bout.toByteArray()));
                                    table.getDefaultCell().setColspan(1);

                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase(rsetImages.getString(4), pFontHeader);
                                    table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("WORKING/PROVISIONAL DIAGNOSIS", pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                while (rset2c11.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    //phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(2), "-"), pFontHeader);
                                    //table.addCell(phrase);
                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("INVESTIGATIONS", pFontHeader11);

                                table.addCell(phrase);

                                // table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setColspan(1);
                                //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                phrase = new Phrase(" ", pFontHeader);

                                table.addCell(phrase);
                                while (rset2c1.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1.getObject(1), "-") + " RESULTS : " + dbObject.getDBObject(rset2c1.getObject("notes"), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    // table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(com.itextpdf.text.BaseColor.WHITE);
                                // table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                String Test = "";
                                String testName = "";
                                String TimesD = "";
                                String Tech = " ";
                                String Confermer = "";

                                // java.sql.Statement st12 = connectDB.createStatement();
                                java.sql.Statement stc = connectDB.createStatement();
                                java.sql.Statement stb = connectDB.createStatement();
                                java.sql.Statement sta = connectDB.createStatement();
                                //java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.Statement st11 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st22 = connectDB.createStatement();
                                // java.sql.Statement st = connectDB.createStatement();
                                //  java.sql.Statement st1 = connectDB.createStatement();
                                // java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st32 = connectDB.createStatement();

                                String Date = null;
                                System.out.println("No 1");

                                System.out.println("No 3");

                                String Notice = "-";
                                String Status = "";
                                java.lang.Object listofStaffNos1[] = this.getListofStaffNos1(listofStaffNos[j]);
                                for (int l = 0; l < listofStaffNos1.length; l++) {
                                    // java.sql.ResultSet rset41 = st2.executeQuery("select distinct typeof_test from hp_lab_results where lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date ");

                                    // java.sql.ResultSet rset4111 = st22.executeQuery("select distinct pathologist,doctor from hp_lab_results where lab_no ilike '" + listofStaffNos1[l] + "' AND date  = '" + listofStaffNos[j] + "'");
                                    java.sql.Statement st32t = connectDB.createStatement();
                                    java.sql.ResultSet rset121t = st32t.executeQuery("SELECT input_date::TIME(0)::varchar from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "' ORDER BY 1 DESC LIMIT 1");

                                    String inputTime = null;

                                    while (rset121t.next()) {
                                        inputTime = rset121t.getString(1);
                                    }
                                    java.sql.ResultSet rset121 = st32.executeQuery("SELECT distinct initcap(code),typeof_test,input_date::date,pathologist,doctor from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "' ORDER BY 3");

                                    while (rset121.next()) {
                                        // table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                                        Test = rset121.getObject(1).toString();
                                        testName = rset121.getObject(2).toString();
                                        TimesD = inputTime;
                                        Tech = "LabTech: " + rset121.getObject(4).toString() + "\n Conf By: " + rset121.getObject(5).toString();;
                                        // Confermer = "";

                                        if (Tech.equalsIgnoreCase("")) {
                                        } else {
                                            java.sql.ResultSet rset4c = stc.executeQuery("SELECT DISTINCT status,notes FROM pb_lab_standards where  code ilike '" + Test + "'");
                                            while (rset4c.next()) {
                                                Status = rset4c.getString(1).toLowerCase();

                                                Notice = dbObject.getDBObject(rset4c.getString(2), "-");
                                                System.out.println(Status);
                                            }
                                            java.sql.ResultSet rset1w = st11.executeQuery("select parameter,out_come||' '||units,lower_limit,upper_limit, out_come from hp_lab_results where code ilike '" + Test + "'  and lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' order by OID");
                                            // while (rset41.next()){
                                            java.sql.ResultSet rset411 = st21.executeQuery("select distinct comments from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l] + "' and code ilike '" + Test + "' AND date  = '" + listofStaffNos[j] + "' ");

                                            // while (rset1w.next()) {
                                            if (Status.startsWith("t")) {

                                                System.out.println("No 4");

                                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase(TimesD, pFontHeader);

                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(4);
                                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                phrase = new Phrase("PARAMETER: " + testName.toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase(Tech, pFontHeader);

                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("", pFontHeader1);
                                                table.addCell(phrase);
                                                // table.addCell(phrase);
                                                //table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Parameter", pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Result", pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Lower", pFontHeader1);
                                                table.addCell(phrase);
                                                // table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Upper", pFontHeader1);
                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Status", pFontHeader1);
                                                table.addCell(phrase);
                                                //table.addCell(phrase);
                                                while (rset1w.next()) {

                                                    System.out.println("No 5");
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    //       table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(1);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(3), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    // table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(4), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    if (rset1w.getDouble(5) > rset1w.getDouble(3) && rset1w.getDouble(5) < rset1w.getDouble(4)) {
                                                        table.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
                                                    } else {
                                                        table.getDefaultCell().setBackgroundColor(BaseColor.RED);
                                                    }
                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                                                    //table.addCell(phrase);
                                                }

                                                System.out.println("No 6");
                                            } else {
                                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase(TimesD, pFontHeader);
                                                table.addCell(phrase);

                                                //table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);
                                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                phrase = new Phrase("TEST: " + testName.toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);

                                                // table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Result", pFontHeader1);
                                                table.addCell(phrase);
                                                // table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.BLACK);

                                                //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                                // phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                                //table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                phrase = new Phrase(Tech, pFontHeader1);
                                                table.addCell(phrase);
                                                ////table.addCell(phrase);
                                                while (rset1w.next()) {

                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(2);
                                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    //table.getDefaultCell().setColspan(1);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                                    phrase = new Phrase(" ", pFontHeader1);
                                                    table.addCell(phrase);
                                                    //table.addCell(phrase);
                                                }

                                            }
                                        }
                                        System.out.println("No 8");

                                    }
                                }

                                while (rset2g.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.RIGHT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(4), "-"), pFontHeader);

                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    //table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("CONFIRMED DIAGNOSIS", pFontHeader11);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                while (rset2c111.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c111.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c111.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c111.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("TREATMENT", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("TREATMENT PLAN : \n"+treatmentPlan, pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                table.addCell(phrase);

                                while (rset14.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(6), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    //table.addCell(phrase);
                                    //table.addCell(phrase);
                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("ADMISSION NOTES", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);

                                java.sql.ResultSet rset2c1W = st15.executeQuery("SELECT DISTINCT INITCAP(comments||' '||ward_refered), date_requested::TIME(0),INITCAP(doctor) "
                                        + " FROM  hp_admission_request WHERE patient_no = '" + memNo + "' AND date_requested::DATE  = '" + listofStaffNos[j] + "'");

                                while (rset2c1W.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1W.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1W.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    // table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1W.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    // table.addCell(phrase);
                                    // table.addCell(phrase);
                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader11);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("REFERRAL INFORMATION", pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);

                                table.addCell(phrase);
                                java.sql.Statement stmtReferral = connectDB.createStatement();
                                java.sql.ResultSet rsetReferral = stmtReferral.executeQuery("SELECT DISTINCT transfer_to, reason, notes, data_capture_time::TIME(0),INITCAP(doctor) "
                                        + " FROM  public.hp_refferal_ext WHERE patient_no = '" + memNo + "' AND input_date::DATE  = '" + listofStaffNos[j] + "'");

                                while (rsetReferral.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

                                    phrase = new Phrase(dbObject.getDBObject(rsetReferral.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase("REFERRED TO : " + dbObject.getDBObject(rsetReferral.getObject(1), "-") + ", REASON : " + dbObject.getDBObject(rsetReferral.getObject(2), "-") + " WITH NOTES : " + dbObject.getDBObject(rsetReferral.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    // table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(com.itextpdf.text.BaseColor.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rsetReferral.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    // table.addCell(phrase);
                                    // table.addCell(phrase);
                                }
                            }

                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {
                            SqlExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }
                    } catch (com.itextpdf.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.itextpdf.text.DocumentException lwDocexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

            }

            docPdf.close();
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

            //
            java.sql.Statement stmt1 = connectDB.createStatement();

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM hp_patient_visit where patient_no = '" + MNo + "' UNION SELECT date_admitted::date as date FROM hp_admission WHERE patient_no = '" + MNo + "' AND patient_no IS NOT NULL order by date DESC");
//            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT trans_date FROM pb_doctors_request WHERE patient_no = '" + MNo + "'  ORDER by 1");

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

    public java.lang.Object[] getListofStaffNos1(Object dates) {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);

        try {

            //
            java.sql.Statement stmt1 = connectDB.createStatement();

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payee FROM ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dealer ilike '"+memNo+"%' order by payee");
            java.sql.ResultSet rSet1x = stmt1.executeQuery("SELECT DISTINCT lab_no FROM hp_lab_results WHERE patient_no = '" + MNo + "' and date = '" + dates + "'::date order by lab_no");

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
