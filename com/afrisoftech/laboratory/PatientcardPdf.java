///Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laboratory;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;
import java.awt.Color;

public class PatientcardPdf implements java.lang.Runnable {

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

    public void PatientcardPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String combox) {
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
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    long dateNow = calendar.getTimeInMillis();
                    java.sql.Date datenowSql = new java.sql.Date(dateNow);
                    System.out.println(datenowSql.toString());
                    try {
                        com.lowagie.text.pdf.PdfPTable headertable = new com.lowagie.text.pdf.PdfPTable(6);
                        
                        int headerwidthstable[] = {15, 7, 25, 7, 8, 18};
                        
                        headertable.setWidths(headerwidthstable);
                        
                        headertable.setWidthPercentage((100));
                        
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        headertable.getDefaultCell().setColspan(6);
                        
                        Phrase headerphrase = new Phrase();
                        
                        headertable.getDefaultCell().setBorder(Rectangle.BOX);
                        headertable.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                        headertable.getDefaultCell().setColspan(2);
                        headertable.getDefaultCell().setFixedHeight(70);
                        headertable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        headertable.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                        java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.ResultSet rset3 = st3.executeQuery("select header_name from pb_header");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT TO_CHAR(current_timestamp(0),'FMDay FMDD/ MM/ YY HH12::MI')");
                        
                        while (rset3.next()) {
                            headertable.getDefaultCell().setColspan(4);
                            
                            headertable.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            headertable.getDefaultCell().setBorderColor(Color.BLACK);
                            headertable.getDefaultCell().setBorder(Rectangle.BOX);
                            headerphrase = new Phrase("\n\n" + rset3.getObject(1).toString(), pFontHeader11);
                            headertable.addCell(headerphrase);
                        }
                        docPdf.add(headertable);
                    } catch (Exception sy) {
                        sy.printStackTrace();
                    }

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {15, 25, 7, 7, 8, 18};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table.getDefaultCell().setColspan(6);

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

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(" PATIENT CARD", pFontHeader11);
                            table.addCell(phrase);
                            java.sql.ResultSet rsetp = stp.executeQuery("select distinct patient_no,initcap(first_name||' '||second_name),Category,residence,tel_no,last_visit,sex,nok,year_of_birth::date,pay_mode from hp_patient_register where patient_no = '" + memNo + "'");
                            java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '" + memNo + "'  order by date desc limit 1");

                            while (rsetp.next()) {

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.  " + rsetp.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);

                                //table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name : " + rsetp.getObject(2).toString(), pFontHeader11);
                                table.addCell(phrase);

                                // table.getDefaultCell().setColspan(2);
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

                                phrase = new Phrase("Date Admitted : " + dbObject.getDBObject(rsetp.getObject(6), "-"), pFontHeader11);
                                table.addCell(phrase);

                                //table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("Sex : " + dbObject.getDBObject(rsetp.getObject(7), "-"), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("Next Of Kin : " + dbObject.getDBObject(rsetp.getObject(8), "-"), pFontHeader11);
                                table.addCell(phrase);

                                // table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("Age : " + dbObject.getDBObject(rsetp.getObject(9), "-"), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("Payment Mode : " + dbObject.getDBObject(rsetp.getObject(10), "-"), pFontHeader11);
                                table.addCell(phrase);

                            }

                            //table.getDefaultCell().setBorderWidth(Rectangle.RIGHT | Rectangle.LEFT);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {

                                java.sql.Time dates = null;
                                String docs = null;

                                System.out.println("SELECT DISTINCT urgency,weight,height,bmi,diastolic,systolic,pulse,pulse_oximetry,pulse_regularity,lung_sounds,pain_scale,type_of_pain,urinary,temp,resp,comments,input_date::time(0),user_name"
                                        + " from hp_signs_record where patient_no = '" + memNo + "' and"
                                        + " input_date::date  = '" + listofStaffNos[j] + "' ORDER BY input_date::time(0) ");
                                java.sql.ResultSet rset1 = st1.executeQuery("select DISTINCT curr_date::time(0) from pb_doctors_request where patient_no = '" + memNo + "' and curr_date::date  = '" + listofStaffNos[j] + "' ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT urgency,weight,height,bmi,diastolic,systolic,pulse,pulse_oximetry,pulse_regularity,lung_sounds,pain_scale,type_of_pain,urinary,temp,resp,comments,input_date::time(0),user_name"
                                        + " from hp_signs_record where patient_no = '" + memNo + "' and"
                                        + " input_date::date  = '" + listofStaffNos[j] + "' ORDER BY input_date::time(0) ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                //java.sql.ResultSet rset13 = st13.executeQuery("select initcap(service),requisition_no,bed_no,time_due from pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%labo%' and trans_date  = '" + listofStaffNos[j] + "' ORDER BY 1");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset14 = st14.executeQuery("select DISTINCT initcap(service),requisition_no,bed_no||'    '||time_due,dosage,curr_date::DATE||' '||curr_date::TIME(0),INITCAP(doctor) FROM pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%Pharm%' and trans_date  = '" + listofStaffNos[j] + "' ORDER BY curr_date::DATE||' '||curr_date::TIME(0) ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");

                                System.out.println(" select Distinct typeof_test, "
                                        + "comments,description,doctor,input_date::DATE,input_date::TIME(0) "
                                        + "FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND "
                                        + "date  = '" + listofStaffNos[j] + "' AND typeof_test != '' GROUP BY "
                                        + "typeof_test, comments,description,doctor,input_date::DATE,input_date::TIME(0)"
                                        + " ORDER BY input_date::DATE,input_date::TIME(0) ");

                                java.sql.ResultSet rset2e = st2e.executeQuery(" select Distinct typeof_test, "
                                        + "comments,description,doctor,input_date::DATE,input_date::TIME(0) "
                                        + "FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND "
                                        + "date  = '" + listofStaffNos[j] + "' AND typeof_test != '' GROUP BY "
                                        + "typeof_test, comments,description,doctor,input_date::DATE,input_date::TIME(0)"
                                        + " ORDER BY input_date::DATE,input_date::TIME(0) ");

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

                                    Hist = dbObject.getDBObject(rseth22.getObject(1), "-");

                                    //       phrase = new Phrase(dbObject.getDBObject(rseth22.getObject(1), "-"), pFontHeader);
                                    //dbObject.getDBObject(rset1.getObject(2), "-")
                                    // hist=rseth.getObject(9).toString();
                                }

                                //     java.sql.ResultSet rseth2 = sth2.executeQuery(" select distinct narration from hp_patients_hist where patient_no = '" + memNo + "'  and date  = '" + listofStaffNos[j] + "' and hist_heading = '" + Hist + "'");
                                System.out.println(" select distinct treatment,input_date::date||' '||input_date::TIME(0),doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND treatment != '' ORDER BY input_date::date||' '||input_date::TIME(0) \n"
                                        + " select DISTINCT description,input_date::date||' '||input_date::TIME(0),doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND description != '' ORDER BY input_date::date||' '||input_date::TIME(0)\n"
                                        + "SELECT DISTINCT comments, treatment,doctor,input_date::date||' '||input_date::TIME(0) FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' GROUP BY comments , treatment,doctor,input_date::date||' '||input_date::TIME(0) ORDER BY 4\n"
                                        + "SELECT DISTINCT result,comm_reason,input_date::date||' '||input_date::TIME(0),doctor FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND result != '' ORDER BY input_date::date||' '||input_date::TIME(0)");

                                java.sql.ResultSet rset2c1 = st2c1.executeQuery(" select distinct treatment,input_date::date||' '||input_date::TIME(0),doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND treatment != '' ORDER BY input_date::date||' '||input_date::TIME(0) ");
                                java.sql.ResultSet rset2c11 = st2c11.executeQuery(" select DISTINCT description,input_date::date||' '||input_date::TIME(0),doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND description != '' ORDER BY input_date::date||' '||input_date::TIME(0)");

                                java.sql.ResultSet rset2c = st2c.executeQuery("SELECT DISTINCT comments, treatment,doctor,input_date::date||' '||input_date::TIME(0) FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' GROUP BY comments , treatment,doctor,input_date::date||' '||input_date::TIME(0) ORDER BY 4");
                                //  java.sql.ResultSet rset2g = st2g.executeQuery("SELECT DISTINCT result,comm_reason,input_date::date||' '||input_date::TIME(0),doctor FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND result != '' ORDER BY input_date::date||' '||input_date::TIME(0)");
                                java.sql.ResultSet rset2g = st2g.executeQuery(
                                        "select  main_service ,disease,date_recorded::date||''||date_of_entry::time(0),doctor_surgeon from hp_patient_diagnosis "
                                        + " where patient_no =  '" + memNo + "' and date_recorded::date  = '" + listofStaffNos[j] + "' ORDER BY date_of_entry::time(0)  ");

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(" ", pFontHeader11);
                                //table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                //phrase = new Phrase(" ", pFontHeader11);
                                //table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                while (rset12.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    phrase = new Phrase(listofStaffNos[j] + " " + dbObject.getDBObject(rset12.getObject(12), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase("Vital Signs :"
                                            + " Patient State : " + dbObject.getDBObject(rset12.getObject("urgency"), "-") + " Temp : " + dbObject.getDBObject(rset12.getObject("temp"), "-")
                                            + "\nWeight : " + dbObject.getDBObject(rset12.getObject("weight"), "-") + " Height : " + dbObject.getDBObject(rset12.getObject("height"), "-")
                                            + " BMI : " + dbObject.getDBObject(rset12.getObject("bmi"), "-") + "\nDiastolic : " + dbObject.getDBObject(rset12.getObject("diastolic"), "-")
                                            + " Systolic : " + dbObject.getDBObject(rset12.getObject("systolic"), "-")
                                            + "\nPulse : " + dbObject.getDBObject(rset12.getObject("pulse"), "-") + " Pulse Oximetry : " + dbObject.getDBObject(rset12.getObject("pulse_oximetry"), "-")
                                            + " Pulse Regularity : " + dbObject.getDBObject(rset12.getObject("pulse_regularity"), "-")
                                            + "\nLung Sounds : " + dbObject.getDBObject(rset12.getObject("lung_sounds"), "-") + " Pain Scale : " + dbObject.getDBObject(rset12.getObject("pain_scale"), "-")
                                            + " Type of Pain : " + dbObject.getDBObject(rset12.getObject("type_of_pain"), "-")
                                            + "\nUrinary : " + dbObject.getDBObject(rset12.getObject("urinary"), "-")
                                            + " Resp: " + dbObject.getDBObject(rset12.getObject("resp"), "-") + "\nComments: " + dbObject.getDBObject(rset12.getObject("comments"), "-")
                                            + "\nInput date: " + dbObject.getDBObject(rset12.getObject("input_date"), "-"));

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(dbObject.getDBObject(rset12.getObject("user_name"), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                }
                                table.getDefaultCell().setColspan(1);

                                while (rset2c.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase("Clinical History/Notes  : " + dbObject.getDBObject(rset2c.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                }

                                while (rset2e.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2e.getObject(5), "-") + " " + dbObject.getDBObject(rset2e.getObject(6), "-"), pFontHeader);

                                    //Exam = dbObject.getDBObject(rset2e.getObject(1), "-");
                                    //Doctor = dbObject.getDBObject(rset2e.getObject(4), "-");
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);

                                    phrase = new Phrase("Clinical Exam : " + dbObject.getDBObject(rset2e.getObject(1), "-"), pFontHeader);

                                    //Exam = dbObject.getDBObject(rset2e.getObject(1), "-");
                                    Doctor = dbObject.getDBObject(rset2e.getObject(4), "-");
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(Doctor, pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(" ", pFontHeader);

                                    //table.addCell(phrase);
                                }

                                while (rset2c1.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase("Treatment Plan : " + dbObject.getDBObject(rset2c1.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    // table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset2c1.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);

                                    // table.addCell(phrase);
                                    // table.addCell(phrase);
                                }

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
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
                                java.lang.Object listofStaffNos1[] = this.getListofStaffNos1();
                                for (int l = 0; l < listofStaffNos1.length; l++) {
                                    // java.sql.ResultSet rset41 = st2.executeQuery("select distinct typeof_test from hp_lab_results where lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' AND spec_time::timestamp < input_date ");

                                    // java.sql.ResultSet rset4111 = st22.executeQuery("select distinct pathologist,doctor from hp_lab_results where lab_no ilike '" + listofStaffNos1[l] + "' AND date  = '" + listofStaffNos[j] + "'");
                                    System.out.println("(SELECT distinct initcap(code),typeof_test,input_date::DATE||' '||input_date::TIME(0),pathologist,doctor "
                                            + "from hp_lab_results where  request_id = '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "') "
                                            + " union "
                                            + "(SELECT  code,typeof_test,input_date,pathologist,doctor from lims_lab_results"
                                            + " where  request_id = '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "') "
                                            + " ORDER BY 3");

                                    java.sql.ResultSet rset121 = st32.executeQuery(
                                            "(SELECT distinct initcap(code),typeof_test,input_date::DATE||' '||input_date::TIME(0),pathologist,doctor "
                                            + "from hp_lab_results where  request_id = '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "') "
                                            + " union "
                                            + "(SELECT  code,typeof_test,input_date::text,pathologist,doctor from lims_lab_results"
                                            + " where  request_id = '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "') "
                                            + " ORDER BY 3");

                                    while (rset121.next()) {
                                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        Test = rset121.getObject(1).toString();
                                        testName = rset121.getObject(2).toString();
                                        TimesD = rset121.getObject(3).toString();;
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
                                            java.sql.ResultSet rset1w = st11.executeQuery(
                                                    "select parameter,out_come||' '||units,lower_limit,upper_limit from hp_lab_results where code ilike '" + Test + "'  and lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' order by OID");
                                            // while (rset41.next()){
                                            java.sql.ResultSet rset411 = st21.executeQuery("select distinct comments from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l] + "' and code ilike '" + Test + "' AND date  = '" + listofStaffNos[j] + "' ");

                                            // while (rset1w.next()) {
                                            if (Status.startsWith("t")) {

                                                System.out.println("No 4");

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase(TimesD, pFontHeader);

                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(4);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("TEST: " + testName.toUpperCase(), pFontHeader1);
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

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("", pFontHeader1);
                                                table.addCell(phrase);
                                                //table.addCell(phrase);
                                                while (rset1w.next()) {

                                                    System.out.println("No 5");
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);

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
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);
                                                    //table.addCell(phrase);
                                                }

                                                System.out.println("No 6");
                                            } else {

                                                table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase(TimesD, pFontHeader1);
                                                table.addCell(phrase);

                                                //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("TEST: " + testName.toUpperCase(), pFontHeader1);
                                                table.addCell(phrase);

                                                // table.getDefaultCell().setColspan(1);
                                                phrase = new Phrase("Result", pFontHeader1);
                                                table.addCell(phrase);
                                                // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                                //  table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                                // phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                                //table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(Tech, pFontHeader1);
                                                table.addCell(phrase);
                                                ////table.addCell(phrase);
                                                while (rset1w.next()) {

                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(2);
                                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    //table.getDefaultCell().setColspan(1);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(" ", pFontHeader1);
                                                    table.addCell(phrase);
                                                    //table.addCell(phrase);
                                                }

                                            }
                                        }
                                        System.out.println("No 8");

                                    }
                                }
                                //}

                                while (rset2c11.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("Provisional Diagnosis : " + dbObject.getDBObject(rset2c11.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    phrase = new Phrase(dbObject.getDBObject(rset2c11.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }

                                while (rset2g.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("Diagnosis : " + dbObject.getDBObject(rset2g.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("ICD10 CODE : " + dbObject.getDBObject(rset2g.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    //table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rset2g.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }

                                while (rset14.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase("Prescriptions : " + dbObject.getDBObject(rset14.getObject(1), "-"), pFontHeader);

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

                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(dbObject.getDBObject(rset14.getObject(6), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                /* table.getDefaultCell().setColspan(1);
                                 phrase = new Phrase(" ", pFontHeader);
                                
                                 //table.addCell(phrase);
                                 //table.addCell(phrase);
                                 table.getDefaultCell().setColspan(4);
                                 phrase = new Phrase("RADIOLOGY: ", pFontHeader11);
                                
                                 table.addCell(phrase);
                                 */

                                System.out.println("SELECT  examination,xray_manager,doctor ,notes,input_date::Date||' '||input_date::TIME(0) FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' AND xray_no ilike  'x%' ORDER BY 5");
                                java.sql.ResultSet rsetT = st2T.executeQuery("SELECT  examination,xray_manager,doctor ,notes,input_date::Date||' '||input_date::TIME(0) FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' AND xray_no ilike  'x%' ORDER BY 5");

                                while (rsetT.next()) {
                                    //table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rsetT.getObject(5), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    //table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("RADIOLOGY: " + dbObject.getDBObject(rsetT.getObject(1), "-") + " Results " + dbObject.getDBObject(rsetT.getObject(4), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Radiologist: " + dbObject.getDBObject(rsetT.getObject(2), "-"), pFontHeader);
                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);

                                System.out.println("SELECT  "
                                        + "examination||' '||notes||' '||ext_ref||' '||xray_manager||' '||"
                                        + "pc||' '||hpc||' '||pmhs||' '||pdhs||' '||exam||' '||exam_notes"
                                        + "||' '||imp_inv||' '||inv||' '||diag||' '||plan,"
                                        + "input_date::Date||' '||input_date::TIME(0),"
                                        + "doctor FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' "
                                        + "AND xray_no ilike  'd%' ORDER BY 2");

                                java.sql.ResultSet rsetd = st2d.executeQuery("SELECT  "
                                        + "examination||' '||notes||' '||ext_ref||' '||xray_manager||' '||"
                                        + "pc||' '||hpc||' '||pmhs||' '||pdhs||' '||exam||' '||exam_notes"
                                        + "||' '||imp_inv||' '||inv||' '||diag||' '||plan,"
                                        + "input_date::Date||' '||input_date::TIME(0),"
                                        + "doctor FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' "
                                        + "AND xray_no ilike  'd%' ORDER BY 2");

                                while (rsetd.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    // table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(2), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("DENTAL: " + dbObject.getDBObject(rsetd.getObject(1), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(dbObject.getDBObject(rsetd.getObject(3), "-"), pFontHeader);
                                    table.addCell(phrase);

                                }

                            }

                            docPdf.add(table);

                        } catch (Exception SqlExec) {
                            SqlExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }

                        // }
                    } catch (com.lowagie.text.BadElementException BadElExec) {
                        BadElExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                } catch (java.io.FileNotFoundException fnfExec) {
                    fnfExec.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                }
            } catch (com.lowagie.text.DocumentException lwDocexec) {
                lwDocexec.printStackTrace();
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
            java.sql.ResultSet rSet1x = stmt1.executeQuery(""
                    + "(SELECT DISTINCT request_id FROM hp_lab_results WHERE patient_no = '" + MNo + "') "
                    + " union "
                    + "(SELECT distinct request_id  FROM lims_lab_results where patient_no ='" + MNo + "' )"
                    + "order by 1 ");

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
