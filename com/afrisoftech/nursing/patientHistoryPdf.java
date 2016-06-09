/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.nursing;

/**
 *
 * @author mugisystempartners
 *////Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;


import com.afrisoftech.lib.DBObject;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

/**
 *
 * @author system
 */
public class patientHistoryPdf implements java.lang.Runnable {

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
    String  patient_no=null,pat_name=null;

    public void patientHistoryPdf(java.sql.Connection connDb,String Patient_no,String Pat_Name) {
        //MNo = combox;

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;
       // beginDate = begindate;

        //endDate = endate;
        patient_no=Patient_no;
        pat_name=Pat_Name;

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


                       /********************** //////generating the general history****************************/
                        
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
                            phrase = new Phrase("PATIENT'S HISTORY", pFontHeader11);
                            table.addCell(phrase);
                             java.sql.ResultSet rsetp = stp.executeQuery("SELECT patient_no,  server_date,type_of_accident, \n" +
                                                                            "       mode_of_arrival, status, patient_at_risk, medication_taken, patient_valuables, \n" +
                                                                            "       serious_illness, specify_illness, illness_time, organ_transplant, \n" +
                                                                            "       transplant_desc, transplant_time, drinks_alcohol, alcohol_amount, \n" +
                                                                            "       smokes, smoking_duration, ever_smoked, stopped_smoking, alergic, \n" +
                                                                            "       specify_alergy, social_history, specify_history, difficulty_in, \n" +
                                                                            "       action_user\n" +
                                                                            "  FROM nursing.history_firstpage  where patient_no='"+patient_no+"'");
                            //java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '" + memNo + "'  order by date desc limit 1");

                            while (rsetp.next()) {

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No.  " +   dbObject.getDBObject(rsetp.getObject(1),"-"), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name : " +  dbObject.getDBObject(pat_name,"-") , pFontHeader11);
                                table.addCell(phrase);
                                    ///TIME
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("TIME ", pFontHeader11);
                                table.addCell(phrase);
                                ///GENERAL HIST
                               table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("GENERAL HISTORY ", pFontHeader11);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rsetp.getObject(2),""), pFontHeader11);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Accident Type:   "+rsetp.getObject(3)+",  mode of arrival:   "+rsetp.getObject(4)
                                         +",  status: "+rsetp.getObject(5) +",  patient at risk:   "+rsetp.getObject(6) +",  medication_taken: "+rsetp.getObject(7)
                                        +",  patient_valuables: "+rsetp.getObject(8)+", serious illness: "+rsetp.getObject(9) 
                                        , pFontHeader11);

                                table.addCell(phrase);
                     
                                
                            }/////respiratory/cardiac
                             java.sql.Statement stpj = connectDB.createStatement();
                                java.sql.ResultSet rsetj = stpj.executeQuery("SELECT patient_no, server_date, transaction_date, suffers_stroke, stroke_effect, epileptic, epilepsy_desc, epileptic_when, suffers_depression,  depression_time, high_bp, hbp_time, chest_pain, chestpain_desc, chestpain_time, heart_disease, disease_desc, disease_time, rheumatic_fever,rheumatic_when, swelling_ankle, difficulty_breathing, specify_breathing, home_oxygen, oxygen_explain, chronic, chronic_duration, chronic_desc,  chronic_amout, chronic_color, asmatic, asmatic_when, action_user FROM nursing.respiratory_cardiac_neurohistory where patient_no='"+patient_no+"'");
                                
                                while(rsetj.next()){
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ", pFontHeader11);
                                table.addCell(phrase); 
                                
                                 /////respiratory/cardiac
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("RESPIRATORY/CARDIAC", pFontHeader11);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(  dbObject.getDBObject(rsetj.getObject(2),"-"), pFontHeader11);
                                table.addCell(phrase); 
                                

                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("suffers stroke:   "+  dbObject.getDBObject(rsetj.getObject(4),"-")+",  stroke effect:   "+  dbObject.getDBObject(rsetj.getObject(5),"-")
                                         +",  stroke effect: "+  dbObject.getDBObject(rsetj.getObject(6),"-") +",  epileptic:   "+  dbObject.getDBObject(rsetj.getObject(7),"-") +", epilepsy description: "+  dbObject.getDBObject(rsetj.getObject(8),"-")
                                        +",  when epilepsy started: "+  dbObject.getDBObject(rsetj.getObject(9),"-")+", suffers depression: "+  dbObject.getDBObject(rsetj.getObject(10),"-") 
                                        , pFontHeader11);
                                table.addCell(phrase); 
                                
                                
//                                SELECT patient_no, server_date, transaction_date, suffers_stroke, stroke_effect, epileptic, epilepsy_desc, epileptic_when, suffers_depression, "
//                                 +"depression_time, high_bp, hbp_time, chest_pain, chestpain_desc, chestpain_time, heart_disease, disease_desc, disease_time, rheumatic_fever,rheumatic_when, swelling_ankle, difficulty_breathing, specify_breathing, "
//                                 +"home_oxygen, oxygen_explain, chronic, chronic_duration, chronic_desc,  chronic_amout, chronic_color, asmatic, asmatic_when, action_user ,"
//                                 +"FROM nursing.respiratory_cardiac_neurohistory where patient_no='"+patient_no+"'
                                }
                                
                                //////gastric
                                java.sql.Statement stpg = connectDB.createStatement();
                                java.sql.ResultSet rsetg = stpg.executeQuery("SELECT patient_no,  server_date, action_date, kidney_disorder,"
+"       disorder_type, disorder_time, gastric_reflux, specify_gastric,"
+"       gastric_when, menstrual_period, menstrual_weeks, breast_feeding,"
+"       action_user FROM nursing.gastric_renal_gynaehistory  where patient_no='"+patient_no+"'");
                                
                                while(rsetg.next()){
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ", pFontHeader11);
                                table.addCell(phrase); 
                                
                                 /////respiratory/cardiac
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("GASTRIC RENAL", pFontHeader11);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rsetg.getObject(2),""), pFontHeader11);
                                table.addCell(phrase); 
                                

                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("kidney disorder:   "+  dbObject.getDBObject(rsetg.getObject(4),"-")+",  disorder type:   "+  dbObject.getDBObject(rsetg.getObject(5),"-")
                                         +",  disorder_time: "+  dbObject.getDBObject(rsetg.getObject(6),"-") +",  gastric_reflux:   "+  dbObject.getDBObject(rsetg.getObject(7),"-") +", specify_gastric: "+  dbObject.getDBObject(rsetg.getObject(8),"-")
                                        +",  gastric_when: "+  dbObject.getDBObject(rsetg.getObject(9),"-")+", menstrual_period: "+  dbObject.getDBObject(rsetg.getObject(10),"-") , pFontHeader11);
                                table.addCell(phrase); 
                                
//                                SELECT patient_no, server_date, action_date, kidney_disorder, 
//       disorder_type, disorder_time, gastric_reflux, specify_gastric, 
//       gastric_when, menstrual_period, menstrual_weeks, breast_feeding, 
//       action_user
//  FROM nursing.gastric_renal_gynaehistory;
                               
                                }
                                
                                ///muscular
        java.sql.Statement stpm = connectDB.createStatement();
        java.sql.ResultSet rsetm= stpg.executeQuery("SELECT patient_no,  server_time, action_date, artificial_joints, \n" +
"       joint_specify, injuries, explain_injury, diabetic, controlled_by, \n" +
"       hepatitis, specify_hepatitis, action_user, hepatitiswhen\n" +
"  FROM nursing.muscularsceletal_endocrinehistory where patient_no='"+patient_no+"'");
                                
                                while(rsetm.next()){
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ", pFontHeader11);
                                table.addCell(phrase); 
                                
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("MUSCULAR SKELETAL", pFontHeader11);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject(rsetm.getObject(2),""), pFontHeader11);
                                table.addCell(phrase); 
                                

                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Artificial joints:   "+  dbObject.getDBObject(rsetm.getObject(4),"-")+",  joint specify:   "+  dbObject.getDBObject(rsetm.getObject(5),"-")
                                         +",  injuries:"+  dbObject.getDBObject(rsetm.getObject(6),"-") +",  explain injury:   "+  dbObject.getDBObject(rsetm.getObject(7),"-") +", diabetic: "+  dbObject.getDBObject(rsetm.getObject(8),"-")
                                        +",  controlled_by: "+  dbObject.getDBObject(rsetm.getObject(9),"-")+", hepatitis: "+  dbObject.getDBObject(rsetm.getObject(10),"-") 
                                        , pFontHeader11);
                                table.addCell(phrase); 
                                
//                                SELECT patient_no, server_time, action_date, artificial_joints, 
////       joint_specify, injuries, explain_injury, diabetic, controlled_by, 
////       hepatitis, specify_hepatitis, action_user, hepatitiswhen
////  FROM nursing.muscularsceletal_endocrinehistory;

                                }
                                
                                ///blood  and surgery
                                java.sql.Statement stpb = connectDB.createStatement();
                                java.sql.ResultSet rsetb= stpg.executeQuery("SELECT patient_no, visit_id, server_date, transaction_date, blood_disorder, \n" +
"       specify_disorder, disorder_time, blood_transfusion, specify_transfussion, \n" +
"       blood_clot, specify_clot, clot_time, bleeding_tendency, specify_tendency, \n" +
"       action_user\n" +
"       FROM nursing.blood_past_surgeryhistory where patient_no='"+patient_no+"'");
                                
                                while(rsetb.next()){
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ", pFontHeader11);
                                table.addCell(phrase); 
                                
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("BLOOD AND PAST SURGERY", pFontHeader11);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(  dbObject.getDBObject(rsetb.getObject(2),"-"), pFontHeader11);
                                table.addCell(phrase); 
                                

                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("blood_disorder:   "+  dbObject.getDBObject(rsetb.getObject(4),"-")+",  specify_disorder:   "+  dbObject.getDBObject(rsetb.getObject(5),"-")
                                         +", disorder_time:"+  dbObject.getDBObject(rsetb.getObject(6),"-") +",  blood_transfusion:   "+  dbObject.getDBObject(rsetb.getObject(7),"-") +", specify_transfussion: "+  dbObject.getDBObject(rsetb.getObject(8),"-")
                                        +",  blood_clot: "+  dbObject.getDBObject(rsetb.getObject(9),"-")+", specify_clot: "+  dbObject.getDBObject(rsetb.getObject(10),"-") 
                                        , pFontHeader11);
                                table.addCell(phrase); 
                                
//                    SELECT patient_no, server_date, transaction_date, blood_disorder, 
//       specify_disorder, disorder_time, blood_transfusion, specify_transfussion, 
//       blood_clot, specify_clot, clot_time, bleeding_tendency, specify_tendency, 
//       action_user
//  FROM nursing.blood_past_surgeryhistory;


                                }
                                
                                
                                                                
                                /////current medication
        java.sql.Statement stpcm = connectDB.createStatement();
        java.sql.ResultSet rsetcm= stpcm.executeQuery("SELECT patient_no, visit_id, server_date, transaction_date, drug_name, " +
            "       strength, tablets_per_day, number_at_each_time, action_user" +
            "  FROM nursing.medication_table_history where patient_no='"+patient_no+"'");
                                
                                while(rsetcm.next()){
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("  ", pFontHeader11);
                                table.addCell(phrase); 
                                
                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("CURRENT MEDICATION", pFontHeader11);
                                table.addCell(phrase); 
                                
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(  dbObject.getDBObject(rsetcm.getObject(2),"-"), pFontHeader11);
                                table.addCell(phrase); 
                                

                                
                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Drug Name:   "+  dbObject.getDBObject(rsetcm.getObject(4),"-")+",  strength:   "+  dbObject.getDBObject(rsetcm.getObject(5),"-")
                                         +", Tablets per day:"+  dbObject.getDBObject(rsetcm.getObject(6),"-") +",  Number at each time:   "+  dbObject.getDBObject(rsetcm.getObject(7),"-") +", History done by: "+  dbObject.getDBObject(rsetcm.getObject(8),"-"), pFontHeader11);
                                table.addCell(phrase); 
 


                                }
                                
                      
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                           
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {

                                java.sql.Time dates = null;
                                String docs = null;
                                /*java.sql.ResultSet rset1xx = st.executeQuery("SELECT DISTINCT input_date::time(0),doctor FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND input_date::date  = '" + listofStaffNos[j] + "' ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                while (rset1xx.next()) {
                                dates = rset1xx.getTime(1);
                                docs = rset1xx.getString(2);
                                 */
                                java.sql.ResultSet rset1 = st1.executeQuery("select DISTINCT curr_date::time(0) from pb_doctors_request where"
                                        + " patient_no = '" + memNo + "' and curr_date::date  = '" + listofStaffNos[j] + "' ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT DISTINCT weight,height,diastolic,"
                                        + "systolic,pulse,temp,resp,"
                                        + "blood_group,rhesus,rbs,comments,input_date::time(0),user_name "
                                        + "from hp_signs_record where patient_no = '" + memNo + "' and"
                                        + " input_date::date  = '" + listofStaffNos[j] + "' ORDER BY input_date::time(0) ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                //java.sql.ResultSet rset13 = st13.executeQuery("select initcap(service),requisition_no,bed_no,time_due from pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%labo%' and trans_date  = '" + listofStaffNos[j] + "' ORDER BY 1");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                java.sql.ResultSet rset14 = st14.executeQuery("select DISTINCT initcap(service),requisition_no,bed_no||'    '||time_due,dosage,curr_date::DATE||' '||curr_date::TIME(0),INITCAP(doctor) FROM pb_doctors_request where patient_no = '" + memNo + "' and revenue_code ilike '%Pharm%' and trans_date  = '" + listofStaffNos[j] + "' ORDER BY curr_date::DATE||' '||curr_date::TIME(0) ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");

                                java.sql.ResultSet rset2e = st2e.executeQuery(" select Distinct typeof_test, "
                                        + "comments,description,doctor,input_date::DATE,input_date::TIME(0) "
                                        + "FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND "
                                        + "date  = '" + listofStaffNos[j] + "' AND typeof_test != '' GROUP BY "
                                        + "typeof_test, comments,description,doctor,input_date::DATE,input_date::TIME(0)"
                                        + " ORDER BY input_date::DATE,input_date::TIME(0) ");
                                // java.sql.ResultSet rset2e1 = st2e1.executeQuery(" select distinct doctor,input_date from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' ORDER BY INPUT_DATE");

                                // java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '"+memNo+"'  ");


                                while (rset1.next()) {
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);



                                    //visit =   dbObject.getDBObject(rset1.getObject(2), "-");
                                    reg =   dbObject.getDBObject(rset1.getObject(1), "-");

                                    //rset1.getObject(2).toString();
                                    // phrase = new Phrase(  dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);



                                }
                                java.sql.ResultSet rseth22 = sth22.executeQuery(" select distinct hist_heading from hp_patients_hist where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "'");
                                // java.sql.ResultSet rseth = sth.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking ,narration,hist_heading from hp_patients_hist where patient_no = '"+memNo+"'  ");

                                while (rseth22.next()) {
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                    Hist =   dbObject.getDBObject(rseth22.getObject(2), "-");


                                    //       phrase = new Phrase(  dbObject.getDBObject(rseth22.getObject(1), "-"), pFontHeader);
                                    //  dbObject.getDBObject(rset1.getObject(2), "-")
                                    // hist=rseth.getObject(9).toString();


                                }

                                //     java.sql.ResultSet rseth2 = sth2.executeQuery(" select distinct narration from hp_patients_hist where patient_no = '" + memNo + "'  and date  = '" + listofStaffNos[j] + "' and hist_heading = '" + Hist + "'");

                                java.sql.ResultSet rset2c1 = st2c1.executeQuery(" select distinct treatment,input_date::date||' '||input_date::TIME(0),doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND treatment != '' ORDER BY input_date::date||' '||input_date::TIME(0) ");
                                java.sql.ResultSet rset2c11 = st2c11.executeQuery(" select DISTINCT description,input_date::date||' '||input_date::TIME(0),doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND description != '' ORDER BY input_date::date||' '||input_date::TIME(0)");

                                java.sql.ResultSet rset2c = st2c.executeQuery("SELECT DISTINCT comments, treatment,doctor,input_date::date||' '||input_date::TIME(0) FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' GROUP BY comments , treatment,doctor,input_date::date||' '||input_date::TIME(0) ORDER BY 4");
                                java.sql.ResultSet rset2g = st2g.executeQuery("SELECT DISTINCT result,comm_reason,input_date::date||' '||input_date::TIME(0),doctor FROM hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND result != '' ORDER BY input_date::date||' '||input_date::TIME(0)");
                                //java.sql.ResultSet rsetTotals = st2.executeQuery("select  symptom,result,duration,description,doctor from hp_patients_hist where patient_no = '"+memNo+"' and date  = '"+listofStaffNos[j]+"' and hist_heading = '"+exam+"'")
                                //  java.sql.ResultSet rset14 = st14.executeQuery("select initcap(description),date_curr::time,date_curr::time from hp_pharmacy where patient_no = '"+memNo+"' and date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                // java.sql.ResultSet rset15 = st15.executeQuery("select initcap(service),date_curr::time,date_curr::time from hp_patient_billing where patient_no = '"+memNo+"' and trans_date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by date_curr::time");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                /*9while (rset2e1.next()) {
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                Doctor =   dbObject.getDBObject(rset2e1.getObject(1), "-");
                                
                                // table.addCell(phrase);
                                }*/
                                phrase = new Phrase(" ", pFontHeader11);
                                //table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                //phrase = new Phrase(" ", pFontHeader11);
                                //table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                /*   while (rset1.next()) {
                                
                                table.getDefaultCell().setColspan(2);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Registration", pFontHeader11);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(" :" + reg, pFontHeader);
                                
                                table.addCell(phrase);
                                
                                }
                                 */

                                /*
                                table.getDefaultCell().setColspan(4);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Vital Signs : ", pFontHeader11);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                //phrase = new Phrase(Doctor, pFontHeader11);
                                phrase = new Phrase(" ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                
                                phrase = new Phrase("", pFontHeader);*/

                                // table.addCell(phrase);
                                while (rset12.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    phrase = new Phrase(listofStaffNos[j] + " " +   dbObject.getDBObject(rset12.getObject(12), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Vital Signs : Weight : " +   dbObject.getDBObject(rset12.getObject(1), "-") + " " + "Height : " +   dbObject.getDBObject(rset12.getObject(2), "-") + " "
                                            + "Diastolic : " +   dbObject.getDBObject(rset12.getObject(3), "-") + " " + "Systolic : " +   dbObject.getDBObject(rset12.getObject(4), "-") + " "
                                            + "Pulse : " +   dbObject.getDBObject(rset12.getObject(5), "-") + " " + "Temp : " +   dbObject.getDBObject(rset12.getObject(6), "-") + " "
                                            + "Resp : " +   dbObject.getDBObject(rset12.getObject(7), "-") + " " + "Rhesus : " +   dbObject.getDBObject(rset12.getObject(9), "-") + " "
                                            + "Blood Group : " +   dbObject.getDBObject(rset12.getObject(8), "-") + " " + "Rbs : " +   dbObject.getDBObject(rset12.getObject(10), "-") + " "
                                            + "Comments : " +   dbObject.getDBObject(rset12.getObject(11), "-"), pFontHeader);

                                    table.addCell(phrase);


                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(  dbObject.getDBObject(rset12.getObject(13), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);



                                }
                                table.getDefaultCell().setColspan(1);
                                /* phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);*/
                                //phrase = new Phrase(Doctor, pFontHeader11);
                                //table.addCell(phrase);

                                //*/
                                //table.getDefaultCell().setColspan(1);

                              /*  table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Clinical History/Notes  : ", pFontHeader11);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);*/
                                
                                while (rset2c.next()) {
                                    
                                    table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(  dbObject.getDBObject(rset2c.getObject(4), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                
                                phrase = new Phrase("Clinical History/Notes  : "+  dbObject.getDBObject(rset2c.getObject(1), "-"), pFontHeader);
                               
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(  dbObject.getDBObject(rset2c.getObject(3), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                }
                                //*/
                               /*
                                 * table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);
                                //table.addCell(phrase);
                                //table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setColspan(4);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Clinical Exam : ", pFontHeader11);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase("", pFontHeader);
                                
                                table.addCell(phrase);*/
                                //table.addCell(phrase);

                                while (rset2e.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(  dbObject.getDBObject(rset2e.getObject(5), "-") + " " +   dbObject.getDBObject(rset2e.getObject(6), "-"), pFontHeader);

                                    //Exam =   dbObject.getDBObject(rset2e.getObject(1), "-");
                                    //Doctor =   dbObject.getDBObject(rset2e.getObject(4), "-");
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);


                                    phrase = new Phrase("Clinical Exam : " +   dbObject.getDBObject(rset2e.getObject(1), "-"), pFontHeader);

                                    //Exam =   dbObject.getDBObject(rset2e.getObject(1), "-");
                                    Doctor =   dbObject.getDBObject(rset2e.getObject(4), "-");
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(Doctor, pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase(" ", pFontHeader);

                                    //table.addCell(phrase);
                                }

                                /* table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                //table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setColspan(4);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Treatment Plan : ", pFontHeader11);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);*/

                                while (rset2c1.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                    phrase = new Phrase(  dbObject.getDBObject(rset2c1.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                    phrase = new Phrase("Treatment Plan :" +   dbObject.getDBObject(rset2c1.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    // table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setColspan(1);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                                    phrase = new Phrase(  dbObject.getDBObject(rset2c1.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);

                                    // table.addCell(phrase);
                                    // table.addCell(phrase);
                                }

                                /* table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                //table.addCell(phrase);
                                //table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(4);
                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Lab Requests & Results : ", pFontHeader11);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);*/

                                /* while (rset13.next()) {
                                
                                table.getDefaultCell().setColspan(2);
                                //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                
                                phrase = new Phrase(  dbObject.getDBObject(rset13.getObject(1), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(  dbObject.getDBObject(rset13.getObject(2), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);
                                phrase = new Phrase(  dbObject.getDBObject(rset13.getObject(3), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(  dbObject.getDBObject(rset13.getObject(4), "-"), pFontHeader);
                                
                                table.addCell(phrase);
                                }*/



                                //  table.addCell(phrase);

                                //table.getDefaultCell().setColspan(1);
                                //phrase = new Phrase(" ", pFontHeader);

                                // table.addCell(phrase);
                                // table.addCell(phrase);
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

                                    java.sql.ResultSet rset121 = st32.executeQuery("SELECT distinct initcap(code),typeof_test,input_date::DATE||' '||input_date::TIME(0),pathologist,doctor from hp_lab_results where  lab_no ilike '" + listofStaffNos1[l].toString() + "' AND date  = '" + listofStaffNos[j] + "' ORDER BY 3");


                                    while (rset121.next()) {
                                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        Test = rset121.getObject(1).toString();
                                        testName = rset121.getObject(2).toString();
                                        TimesD = rset121.getObject(3).toString();;
                                        Tech = "LabTech: " + rset121.getObject(4) + "\n Conf By: " + rset121.getObject(5);;
                                        // Confermer = "";

                                        if (Tech.equalsIgnoreCase("")) {
                                        } else {
                                            java.sql.ResultSet rset4c = stc.executeQuery("SELECT DISTINCT status,notes FROM pb_lab_standards where  code ilike '" + Test + "'");
                                            while (rset4c.next()) {
                                                Status = rset4c.getString(1).toLowerCase();

                                                Notice =   dbObject.getDBObject(rset4c.getString(2), "-");
                                                System.out.println(Status);
                                            }
                                            java.sql.ResultSet rset1w = st11.executeQuery("select parameter,out_come||' '||units,lower_limit,upper_limit from hp_lab_results where code ilike '" + Test + "'  and lab_no ilike '" + listofStaffNos1[l] + "'  AND date  = '" + listofStaffNos[j] + "' order by OID");
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
                                                //phrase = new Phrase(" ", pFontHeader1);
                                                //table.addCell(phrase);
                                                // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                                //table.getDefaultCell().setColspan(18);
                                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                // phrase = new Phrase("   ", pFontHeader1);
                                                //table.addCell(phrase);
                                                //table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                                                // }
                                                //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                                //phrase = new Phrase("Notes: "+Notice, pFontHeader1);
                                                //table.addCell(phrase);
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
                                                    phrase = new Phrase(  dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(1);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(  dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                    phrase = new Phrase(  dbObject.getDBObject(rset1w.getObject(3), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(  dbObject.getDBObject(rset1w.getObject(4), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    table.getDefaultCell().setColspan(1);
                                                    phrase = new Phrase("", pFontHeader1);
                                                    table.addCell(phrase);
                                                    //table.addCell(phrase);
                                                }
                                                // table.addCell(phrase);

                                                //}
                                                                /* while (rset411.next()) {
                                                table.getDefaultCell().setColspan(4);
                                                
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Comments:  " +   dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                }*/

                                                /* table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Performed By:  ", pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase("Confirmed By: ", pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(" ", pFontHeader1);
                                                table.addCell(phrase);
                                                table.addCell(phrase);*/
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
                                                    phrase = new Phrase(  dbObject.getDBObject(rset1w.getObject(1), "-"), pFontHeader);

                                                    table.addCell(phrase);
                                                    //table.getDefaultCell().setColspan(1);

                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(  dbObject.getDBObject(rset1w.getObject(2), "-"), pFontHeader);

                                                    table.addCell(phrase);

                                                    table.getDefaultCell().setColspan(1);
                                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                    phrase = new Phrase(" ", pFontHeader1);
                                                    table.addCell(phrase);
                                                    //table.addCell(phrase);
                                                }
                                                /*
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase("Performed By:  ", pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase("Confirmed By: ", pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(" ", pFontHeader1);
                                                table.addCell(phrase);
                                                table.addCell(phrase);
                                                
                                                while (rset4111.next()) {
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(  dbObject.getDBObject(rset4111.getObject(1), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                phrase = new Phrase(  dbObject.getDBObject(rset4111.getObject(2), "-"), pFontHeader1);
                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(" ", pFontHeader1);
                                                table.addCell(phrase);
                                                table.addCell(phrase);
                                                }*/
                                            }
                                        }
                                        System.out.println("No 8");
                                        /*while (rset411.next()) {
                                        table.getDefaultCell().setColspan(2);
                                        
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("Comments:  " +   dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        }*/
                                        // }

                                        //}

                                        /*table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase("", pFontHeader1);
                                        table.addCell(phrase);
                                        table.addCell(phrase);*/
                                        //table.addCell(phrase);


//while (rset1w.next()) {

                                        /* */
                                    }
                                }
                                //}
                                //}
                                // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                                /*.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);
                                 * /
                                 */

                                while (rset2g.next()) {
                                    /*table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Impression : ", pFontHeader11);
                                    table.addCell(phrase);
                                    //table.getDefaultCell().setColspan(2);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);*/

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rset2g.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("Impression : " +   dbObject.getDBObject(rset2g.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rset2g.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rset2g.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    /*table.getDefaultCell().setColspan(4);
                                    //  table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Results :    ", pFontHeader11);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);*/
                                    table.getDefaultCell().setColspan(4);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Results : " +   dbObject.getDBObject(rset2g.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    //table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rset2g.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                }


                                //}

                                /*table.getDefaultCell().setColspan(4);
                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //table.addCell(phrase);
                                phrase = new Phrase("Diagnosis : ", pFontHeader11);
                                
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);*/

                                while (rset2c11.next()) {
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rset2c11.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase("Diagnosis : " +   dbObject.getDBObject(rset2c11.getObject(1), "-"), pFontHeader);


                                    table.addCell(phrase);
                                    phrase = new Phrase(  dbObject.getDBObject(rset2c11.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    //phrase = new Phrase(  dbObject.getDBObject(rset2c11.getObject(2), "-"), pFontHeader);

                                    //table.addCell(phrase);
                                }



                                /*table.getDefaultCell().setColspan(4);
                                // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Prescriptions : ", pFontHeader11);
                                
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);
                                table.addCell(phrase);*/
                                while (rset14.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(  dbObject.getDBObject(rset14.getObject(5), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase("Prescriptions : " +   dbObject.getDBObject(rset14.getObject(1), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(  dbObject.getDBObject(rset14.getObject(2), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    phrase = new Phrase(  dbObject.getDBObject(rset14.getObject(3), "-"), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(  dbObject.getDBObject(rset14.getObject(4), "-"), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    //   table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(  dbObject.getDBObject(rset14.getObject(6), "-"), pFontHeader);

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

                                java.sql.ResultSet rsetT = st2T.executeQuery("SELECT  examination,xray_manager,doctor ,notes,input_date::Date||' '||input_date::TIME(0) FROM hp_xray_results WHERE patient_no = '" + memNo + "' AND date  = '" + listofStaffNos[j] + "' AND xray_no ilike  'x%' ORDER BY 5");



                                while (rsetT.next()) {
                                    //table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetT.getObject(5), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    //table.getDefaultCell().setColspan(2);
                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("RADIOLOGY: " +   dbObject.getDBObject(rsetT.getObject(1), "-") + " Results " +   dbObject.getDBObject(rsetT.getObject(4), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Radiologist: " +   dbObject.getDBObject(rsetT.getObject(2), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    /* table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetT.getObject(5), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetT.getObject(4), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                     * 
                                     */
                                    ////table.addCell(phrase);

                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);

                                //table.addCell(phrase);
                                //table.addCell(phrase);
                                /*table.getDefaultCell().setColspan(4);
                                phrase = new Phrase("DENTAL: ", pFontHeader11);
                                
                                table.addCell(phrase);*/

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
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(2), "-"), pFontHeader);
                                    table.addCell(phrase);


                                    table.getDefaultCell().setColspan(4);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("DENTAL: " +   dbObject.getDBObject(rsetd.getObject(1), "-"), pFontHeader);
                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(3), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    /* table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(3), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    // table.getDefaultCell().setColspan();
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(4), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(5), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    //table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(7), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(8), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(9), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(10), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(11), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(12), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(13), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(4);
                                    phrase = new Phrase(  dbObject.getDBObject(rsetd.getObject(14), "-"), pFontHeader);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase(" ", pFontHeader);
                                    
                                    table.addCell(phrase);*/
                                }

                                /*  table.getDefaultCell().setColspan(1);
                                phrase = new Phrase(" ", pFontHeader);
                                
                                table.addCell(phrase);*/
                            }
                            // }
                            /*}
                            }
                            }
                            }
                            }
                            }
                            }
                            }
                            }
                            }
                            }*/
                            docPdf.add(table);

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
