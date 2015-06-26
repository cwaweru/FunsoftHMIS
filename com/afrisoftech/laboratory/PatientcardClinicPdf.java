///Author Farncis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laboratory;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PatientcardClinicPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientcardClinicPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
        MNo = combox;

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();


            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String date = null;
                    /*   try {


                    java.sql.Statement st3 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();

                    java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                    java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                    while(rset2.next())
                    compName = rset2.getObject(1).toString();

                    while(rset4.next())
                    date = rset4.getObject(1).toString();

                    com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+"                                                        Printed On: "+date+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    headerFoter.setRight(5);
                    docPdf.setHeader(headerFoter);

                    } catch(java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }
                     */
                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient card - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();
                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();

                        long dateNow = calendar.getTimeInMillis();

                        java.sql.Date datenowSql = new java.sql.Date(dateNow);

                        System.out.println(datenowSql.toString());

                        // java.lang.Object listofStaffNos[] = this.getListofStaffNos();


                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(7);
                        //  com.lowagie.text.Table table = new com.lowagie.text.Table(7);

                        // table.endHeaders();

                        int headerwidths[] = {15, 15, 30, 15, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        //  if (docPdf.getPageNumber() > 1) {
                        //  table1.setHeaderRows(4);
                        //  }
                        table1.setWidthPercentage((100));


                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table1.getDefaultCell().setColspan(7);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table1.getDefaultCell().setColspan(1);
                        table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            java.sql.Statement st4 = connectDB.createStatement();

                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                            java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");

                            while (rset3.next()) {
                                table1.getDefaultCell().setColspan(7);

                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader11);
                                table1.addCell(phrase);
                            }
                            while (rset4.next()) {
                                table1.getDefaultCell().setColspan(3);
                                date = rset4.getObject(1).toString();
                                //  phrase = new Phrase("Printed On  :" +date , pFontHeader);

                                table1.addCell(phrase);
                            }
                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        docPdf.add(table1);
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths[] = {15, 15, 15, 25};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(4);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.black);
                        String Exam = null;
                        String Doctor = null;
                        String Hist = null;
                        String visit = null;
                        String reg = null;
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
                            java.sql.Statement st2e1 = connectDB.createStatement();

                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Patient Card", pFontHeader11);
                            table.addCell(phrase);
                            java.sql.ResultSet rsetp = stp.executeQuery("select distinct patient_no,initcap(first_name||' '||second_name),Category,residence,tel_no,last_visit,sex,nok,year_of_birth::date,pay_mode from hp_patient_register where patient_no = '" + memNo + "'");
                            java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '" + memNo + "'  order by date desc limit 1");

                            while (rsetp.next()) {

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.  " + rsetp.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name : " + rsetp.getObject(2).toString(), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" Category: " + dbObject.getDBObject(rsetp.getObject(3), "-"), pFontHeader11);
                                table.addCell(phrase);

                                //  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Residence : " + dbObject.getDBObject(rsetp.getObject(4), "-"), pFontHeader11);
                                table.addCell(phrase);
                                //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No. " + dbObject.getDBObject(rsetp.getObject(5), "-"), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("Last Visit : " + dbObject.getDBObject(rsetp.getObject(6), "-"), pFontHeader11);
                                table.addCell(phrase);



                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("Sex : " + dbObject.getDBObject(rsetp.getObject(7), "-"), pFontHeader11);
                                table.addCell(phrase);


                                phrase = new Phrase("Next Of Kin : " + dbObject.getDBObject(rsetp.getObject(8), "-"), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("Year of Birth : " + dbObject.getDBObject(rsetp.getObject(9), "-"), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("Payment Mode : " + dbObject.getDBObject(rsetp.getObject(10), "-"), pFontHeader11);
                                table.addCell(phrase);

                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                            while (rseth.next()) {

                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                phrase = new Phrase("Date : " + dbObject.getDBObject(rseth.getObject(1), "-"), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("Marital :  " + dbObject.getDBObject(rseth.getObject(2), "-"), pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Sex Hist :  " + dbObject.getDBObject(rseth.getObject(3), "-"), pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Contr :  " + dbObject.getDBObject(rseth.getObject(4), "-"), pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Illnes :  " + dbObject.getDBObject(rseth.getObject(5), "-"), pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Allergy :  " + dbObject.getDBObject(rseth.getObject(6), "-"), pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Alcohol :  " + dbObject.getDBObject(rseth.getObject(7), "-"), pFontHeader11);
                                table.addCell(phrase);
                                phrase = new Phrase("Smoking :  " + dbObject.getDBObject(rseth.getObject(8), "-"), pFontHeader11);
                                table.addCell(phrase);

                                //    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);


                            }


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);

                            /* table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Dep",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Start",pFontHeader1);
                            table.addCell(phrase);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("End",pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Duration",pFontHeader1);
                            table.addCell(phrase);
                             */
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {

                                java.sql.Date dates = null;
                                String docs = null;
                                java.sql.ResultSet rset1xx = st.executeQuery("SELECT DISTINCT input_date::DATE,doctor FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND input_date::date  = '" + listofStaffNos[j] + "' ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                while (rset1xx.next()) {
                                    dates = rset1xx.getDate(1);
                                    docs = rset1xx.getString(2);

                                    java.sql.ResultSet rset1 = st1.executeQuery("select DISTINCT curr_date::time(0) from pb_doctors_request where patient_no = '" + memNo + "' and curr_date::date  = '" + listofStaffNos[j] + "' ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                    java.sql.ResultSet rset12 = st12.executeQuery("select DISTINCT weight,height,diastolic,systolic,pulse,temp,resp,blood_group,rhesus,rbs,comments from hp_signs_record where patient_no = '" + memNo + "' and input_date::date  = '" + listofStaffNos[j] + "' ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                    java.sql.ResultSet rset13 = st13.executeQuery("select initcap(service),requisition_no,bed_no,time_due from pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%labo%' and trans_date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' order by curr_date::DATE");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                    java.sql.ResultSet rset14 = st14.executeQuery("select initcap(service),requisition_no,bed_no,time_due||'          '||dosage from pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%Pharm%' and trans_date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' order by curr_date::DATE");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");

                                    java.sql.ResultSet rset2e = st2e.executeQuery(" select Distinct typeof_test, comments,description,doctor,input_date from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' GROUP BY typeof_test, comments,description,doctor,input_date ORDER BY input_date");
                                    java.sql.ResultSet rset2e1 = st2e1.executeQuery(" select distinct doctor,input_date from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' ORDER BY INPUT_DATE");

                                    // java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '"+memNo+"'  ");


                                    while (rset1.next()) {
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);



                                        //visit = dbObject.getDBObject(rset1.getObject(2), "-");
                                        reg = dbObject.getDBObject(rset1.getObject(1), "-");

                                        //rset1.getObject(2).toString();
                                        // phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);



                                    }
                                    java.sql.ResultSet rseth22 = sth22.executeQuery(" select distinct hist_heading from hp_patients_hist where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "'");
                                    // java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '"+memNo+"'  ");

                                    while (rseth22.next()) {
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                        Hist = dbObject.getDBObject(rseth22.getObject(2), "-");


                                        //       phrase = new Phrase(dbObject.getDBObject(rseth22.getObject(1), "-"), pFontHeader);
                                        //dbObject.getDBObject(rset1.getObject(2), "-")
                                        // hist=rseth.getObject(9).toString();


                                    }

                                    //     java.sql.ResultSet rseth2 = sth2.executeQuery(" select distinct narration from hp_patients_hist where patient_no = '" + memNo + "'  and date  = '" + listofStaffNos[j] + "' and hist_heading = '" + Hist + "'");

                                    java.sql.ResultSet rset2c1 = st2c1.executeQuery(" select distinct treatment from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "'");
                                    java.sql.ResultSet rset2c11 = st2c11.executeQuery(" select description from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "'");

                                    java.sql.ResultSet rset2c = st2c.executeQuery(" select distinct comments , treatment,doctor,input_date from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' GROUP BY comments , treatment,doctor,input_date ORDER BY input_date");
                                    java.sql.ResultSet rset2g = st2g.executeQuery(" select result,comm_reason from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' ");
                                    //java.sql.ResultSet rsetTotals = st2.executeQuery("select  symptom,result,duration,description,doctor from hp_patients_hist where patient_no = '"+memNo+"' and date  = '"+listofStaffNos[j]+"' and hist_heading = '"+exam+"'")
                                    //  java.sql.ResultSet rset14 = st14.executeQuery("select initcap(description),date_curr::time,date_curr::time from hp_pharmacy where patient_no = '"+memNo+"' and date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                    // java.sql.ResultSet rset15 = st15.executeQuery("select initcap(service),date_curr::time,date_curr::time from hp_patient_billing where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    while (rset2e1.next()) {
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        Doctor = dbObject.getDBObject(rset2e1.getObject(1), "-");

                                        // table.addCell(phrase);
                                    }
                                    phrase = new Phrase("ON DATE " + listofStaffNos[j] + "               DOCTOR:  " + Doctor, pFontHeader11);
                                    table.addCell(phrase);
                                    phrase = new Phrase(" ", pFontHeader11);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    while (rset1.next()) {

                                        table.getDefaultCell().setColspan(2);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Registration", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(" :" + reg, pFontHeader);

                                        table.addCell(phrase);

                                    }


                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Vital Signs : ", pFontHeader11);

                                    table.addCell(phrase);

                                    while (rset12.next()) {
                                        table.getDefaultCell().setColspan(1);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Weight : " + dbObject.getDBObject(rset12.getObject(1), "-"), pFontHeader);

                                        table.addCell(phrase);

                                        phrase = new Phrase("Height : " + dbObject.getDBObject(rset12.getObject(2), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Diastolic : " + dbObject.getDBObject(rset12.getObject(3), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Systolic : " + dbObject.getDBObject(rset12.getObject(4), "-"), pFontHeader);

                                        table.addCell(phrase);

                                        phrase = new Phrase("Pulse : " + dbObject.getDBObject(rset12.getObject(5), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Temp : " + dbObject.getDBObject(rset12.getObject(6), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Resp : " + dbObject.getDBObject(rset12.getObject(7), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        phrase = new Phrase("Rhesus : " + dbObject.getDBObject(rset12.getObject(9), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("Blood Group : " + dbObject.getDBObject(rset12.getObject(8), "-"), pFontHeader);

                                        table.addCell(phrase);

                                        phrase = new Phrase("Rbs : " + dbObject.getDBObject(rset12.getObject(10), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase("Comments : " + dbObject.getDBObject(rset12.getObject(11), "-"), pFontHeader);

                                        table.addCell(phrase);


                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        //  phrase = new Phrase("Rhesus : "dbObject.getDBObject(rset12.getObject(9), "-"), pFontHeader);

                                        //table.addCell(phrase);





                                    }



                                    //*/
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Clinical History/Notes  : ", pFontHeader11);

                                    table.addCell(phrase);

                                    while (rset2c.next()) {
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                        phrase = new Phrase(dbObject.getDBObject(rset2c.getObject(1), "-"), pFontHeader);
                                        //phrase = new Phrase("");

                                        table.addCell(phrase);


                                    }

                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Clinical Exam :    ", pFontHeader11);
                                    table.addCell(phrase);
                                    while (rset2e.next()) {
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                        phrase = new Phrase(dbObject.getDBObject(rset2e.getObject(1), "-"), pFontHeader);

                                        //Exam = dbObject.getDBObject(rset2e.getObject(1), "-");
                                        Doctor = dbObject.getDBObject(rset2e.getObject(4), "-");
                                        table.addCell(phrase);
                                    }





                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Treatment Plan : ", pFontHeader11);

                                    table.addCell(phrase);

                                    while (rset2c1.next()) {
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                        phrase = new Phrase(dbObject.getDBObject(rset2c1.getObject(1), "-"), pFontHeader);

                                        table.addCell(phrase);

                                        // table.getDefaultCell().setColspan(1);


                                    }


                                    table.getDefaultCell().setColspan(4);
                                    // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Lab Requests & Results : ", pFontHeader11);

                                    table.addCell(phrase);

                                    while (rset13.next()) {

                                        table.getDefaultCell().setColspan(1);
                                        //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                        phrase = new Phrase(dbObject.getDBObject(rset13.getObject(1), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset13.getObject(2), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset13.getObject(3), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset13.getObject(4), "-"), pFontHeader);

                                        table.addCell(phrase);
                                    }



                                    //  table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                    // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    String Test = null;
                                    String testName = null;

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
                                    String Status = null;
                                    java.lang.Object listofStaffNos1[] = this.getListofStaffNos1();
                                    for (int l = 0; l < listofStaffNos1.length; l++) {
                                        // java.sql.ResultSet rset41 = st2.executeQuery("select distinct typeof_test from hp_lab_results where lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date ");

                                        java.sql.ResultSet rset4111 = st22.executeQuery("select distinct pathologist,doctor from hp_lab_results where lab_no ilike '" + listofStaffNos1[l] + "' AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date  ");

                                        java.sql.ResultSet rset121 = st32.executeQuery("SELECT distinct initcap(code),typeof_test from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date  ");


                                        while (rset121.next()) {
                                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                            Test = rset121.getObject(1).toString();
                                            testName = rset121.getObject(2).toString();
                                            java.sql.ResultSet rset4c = stc.executeQuery("SELECT DISTINCT status,notes FROM pb_lab_standards where  code ilike '" + Test + "'");
                                            while (rset4c.next()) {
                                                Status = rset4c.getString(1).toLowerCase();

                                                Notice = dbObject.getDBObject(rset4c.getString(2), "-");
                                                System.out.println(Status);
                                            }
                                            java.sql.ResultSet rset1w = st11.executeQuery("select parameter,out_come||' '||units,lower_limit,upper_limit from hp_lab_results where code ilike '" + Test + "'  and lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date  order by OID");
                                            // while (rset41.next()){
                                            java.sql.ResultSet rset411 = st21.executeQuery("select distinct comments from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l] + "' and code ilike '" + Test + "' AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date  ");

                                            if (Status.startsWith("t")) {


                                                System.out.println("No 4");

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                                table.getDefaultCell().setColspan(4);
                                                table.getDefaultCell().setColspan(4);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("TEST: " + testName.toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Test", pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Result", pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Lower", pFontHeader1);
                                                table.addCell(phrase);
                                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Upper", pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(" ", pFontHeader1);
                                                //table.addCell(phrase);
                                                // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                                table.getDefaultCell().setColspan(18);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("   ", pFontHeader1);
                                                //table.addCell(phrase);
                                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                                                // }
                                                //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                                //phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                                //table.addCell(phrase);
                                                System.out.println("No 5");
                                                while (rset1w.next()) {
                                                    table.getDefaultCell().setColspan(1);
                                                    //       table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(1);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(3), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(4), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(" ", pFontHeader);

                                                    // table.addCell(phrase);

                                                }
                                                /* while (rset411.next()) {
                                                table.getDefaultCell().setColspan(4);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Comments:  " + dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                }*/

                                                System.out.println("No 6");
                                            } else {


                                                table.getDefaultCell().setColspan(18);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("   ", pFontHeader1);
                                                table.addCell(phrase);
                                                //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("TEST: " + testName.toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(2);

                                                // table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Result", pFontHeader1);
                                                table.addCell(phrase);
                                                // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                                //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                                // phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                                //table.addCell(phrase);
                                                System.out.println("No7");
                                                while (rset1w.next()) {
                                                    table.getDefaultCell().setColspan(2);
                                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(2);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);


                                                }
                                                System.out.println("No 8");
                                                /*while (rset411.next()) {
                                                table.getDefaultCell().setColspan(2);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Comments:  " + dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                }*/
                                                // }

                                            }

                                            System.out.println("No 9");


                                            table.getDefaultCell().setColspan(18);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("   ", pFontHeader1);
                                            //table.addCell(phrase);
                                            table.getDefaultCell().setColspan(2);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Performed By:  ", pFontHeader1);
                                            table.addCell(phrase);
                                            phrase = new Phrase("Confirmed By: ", pFontHeader1);
                                            table.addCell(phrase);
                                            while (rset4111.next()) {

                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(dbObject.getDBObject(rset4111.getObject(1), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(dbObject.getDBObject(rset4111.getObject(2), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                            }
                                        }
                                    }
                                    // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                    // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);


                                    while (rset2g.next()) {
                                        table.getDefaultCell().setColspan(4);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Impression :    ", pFontHeader11);
                                        table.addCell(phrase);
                                        //table.getDefaultCell().setColspan(2);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                        phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(2), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(4);
                                        //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Results :    ", pFontHeader11);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(1), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        //table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                    }


                                    //}
                                    table.getDefaultCell().setColspan(4);
                                    // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    //table.addCell(phrase);
                                    phrase = new Phrase("Diagnosis : ", pFontHeader11);

                                    table.addCell(phrase);
                                    while (rset2c11.next()) {

                                        phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(1), "-"), pFontHeader);


                                        table.addCell(phrase);


                                        //phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(2), "-"), pFontHeader);

                                        //table.addCell(phrase);
                                    }


                                    table.getDefaultCell().setColspan(4);
                                    // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Prescriptions : ", pFontHeader11);

                                    table.addCell(phrase);

                                    while (rset14.next()) {

                                        table.getDefaultCell().setColspan(1);
                                        //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                        phrase = new Phrase(dbObject.getDBObject(rset14.getObject(1), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset14.getObject(2), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset14.getObject(3), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset14.getObject(4), "-"), pFontHeader);

                                        table.addCell(phrase);




                                    }
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("RADIOLOGY: ", pFontHeader11);

                                    table.addCell(phrase);


                                    java.sql.ResultSet rsetT = st2T.executeQuery(" select  examination,xray_manager,doctor ,notes from hp_xray_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' and xray_no ilike  'x%'  AND doctor = '" + docs + "' ");


                                    while (rsetT.next()) {
                                        table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                        table.getDefaultCell().setColspan(4);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rsetT.getObject(1), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase("Radiologist: " + dbObject.getDBObject(rsetT.getObject(2), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase("Requested By: " + dbObject.getDBObject(rsetT.getObject(3), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(8);
                                        phrase = new Phrase(dbObject.getDBObject(rsetT.getObject(4), "-"), pFontHeader);
                                        table.addCell(phrase);

                                    }
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("DENTAL: ", pFontHeader11);

                                    table.addCell(phrase);

                                    java.sql.ResultSet rsetd = st2d.executeQuery(" select  EXAMINATION,notes,ext_ref,xray_manager,pc,hpc,pmhs,pdhs,exam,exam_notes,imp_inv,inv,diag,plan from hp_xray_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' and xray_no ilike  'd%'");


                                    while (rsetd.next()) {
                                        table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(1), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(2), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(3), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(4), "-"), pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(5), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(6), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(7), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(8), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(9), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(10), "-"), pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(11), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(12), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(13), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(14), "-"), pFontHeader);
                                        table.addCell(phrase);
                                    }


                                }

                            }
                            docPdf.add(table);

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

             
docPdf.close();  com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



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



            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM hp_patient_visit where patient_no = '" + MNo + "'  order by date");

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

    public java.lang.Object[] getListofStaffNos1() {

        java.lang.Object[] listofStaffNos = null;

        java.util.Vector listStaffNoVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no FROM hp_patient_card WHERE date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND payment_mode = 'Scheme' AND isurer = '"+memNo+"' order by patient_no");
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payee FROM ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dealer ilike '"+memNo+"%' order by payee");
            java.sql.ResultSet rSet1x = stmt1.executeQuery("SELECT DISTINCT lab_no FROM hp_lab_results WHERE patient_no = '" + MNo + "' order by lab_no");

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
