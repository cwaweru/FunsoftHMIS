//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laboratory;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PatientClinicalComprehPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    double balance = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientClinicalComprehPdf(java.sql.Connection connDb, java.lang.String combox) {
        MNo = combox;

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;


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
            //    com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));





                    String compName = null;
                    String date = null;
                    try {


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                        }

                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Patient History - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {10, 10, 10, 10, 10, 10,};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(3);
                        Phrase phrase = new Phrase("");



                        phrase = new Phrase("Patient Card  : ", pFontHeader11);

                        table.addCell(phrase);


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("Printed on : " + date, pFontHeader11);

                        table.addCell(phrase);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st21 = connectDB.createStatement();

                            //  java.sql.ResultSet rset = st.executeQuery("select DISTINCT member_code, member_name,date from shares_transactions order by member_code");
                            java.sql.ResultSet rset = st.executeQuery("select distinct patient_no,initcap(first_name||' '||second_name),Category,residence,tel_no,last_visit from hp_patient_register where patient_no = '" + memNo + "'");
                            //java.sql.ResultSet rset1 = st1.executeQuery(" select distinct date,marital_status,sex_hist , contraceptive ,illness ,drug_allergy,alcohol,smoking  from hp_patients_hist where patient_no = '"+memNo+"'  ");

                            while (rset.next()) {

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Service No.  " + rset.getObject(1).toString(), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(4);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Name : " + rset.getObject(2).toString(), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Rank : " + dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader11);
                                table.addCell(phrase);

                                //  table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Unit  : " + dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader11);
                                table.addCell(phrase);
                                //  table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEl No.  " + dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader11);
                                table.addCell(phrase);

                                phrase = new Phrase("Last Visit : " + dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader11);
                                table.addCell(phrase);

                            }

                            String exam = null;

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            /*
                            phrase = new Phrase("Symptom : ", pFontHeader11);
                            table.addCell(phrase);

                            
                            phrase = new Phrase("Severity : ", pFontHeader11);
                            table.addCell(phrase);
                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Desc : ", pFontHeader11);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Doctor : ", pFontHeader11);
                            table.addCell(phrase);
                             */

                            //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();

                            for (int j = 0; j < listofStaffNos.length; j++) {

                                java.sql.Time dates = null;
                                String docs = null;
                                java.sql.ResultSet rset1xx = st.executeQuery("SELECT DISTINCT input_date::time(0),doctor FROM hp_clinical_results WHERE patient_no = '" + memNo + "' AND input_date::date  = '" + listofStaffNos[j] + "'  ORDER BY 1 ");// union select date::date,initcap(service) as service,dosage,reference,credit from hp_patient_card where patient_no = '"+memNo+"' and credit > 0 order by date");
                                while (rset1xx.next()) {
                                    dates = rset1xx.getTime(1);
                                    docs = rset1xx.getString(2);
                                    table.getDefaultCell().setColspan(6);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                    phrase = new Phrase("ON DATE " + listofStaffNos[j] + " " + dates + "", pFontHeader11);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);
                                    java.sql.ResultSet rsetTotals1 = st21.executeQuery(" select distinct typeof_test,comments, description,treatment,comm_reason,doctor from hp_clinical_results where patient_no = '" + memNo + "' and date  = '" + listofStaffNos[j] + "' AND doctor = '" + docs + "' AND input_date::time(0) = '" + dates + "'");
                                    while (rsetTotals1.next()) {
                                        table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        table.getDefaultCell().setColspan(3);
                                        phrase = new Phrase("Examination : " + dbObject.getDBObject(rsetTotals1.getObject(2), "-"), pFontHeader);
                                        //exam = rsetTotals1.getObject(1).toString();
                                        table.addCell(phrase);
                                        //table.getDefaultCell().setColspan(2);
                                        phrase = new Phrase("Clinical Inpr : " + dbObject.getDBObject(rsetTotals1.getObject(1), "-"), pFontHeader);
                                        // exam = rsetTotals1.getObject(1).toString();
                                        table.addCell(phrase);

                                        phrase = new Phrase("Diagnosis : " + dbObject.getDBObject(rsetTotals1.getObject(3), "-"), pFontHeader);
                                        // exam = rsetTotals1.getObject(1).toString();
                                        table.addCell(phrase);
                                        phrase = new Phrase("Treatment Plan : " + dbObject.getDBObject(rsetTotals1.getObject(4), "-"), pFontHeader);
                                        // exam = rsetTotals1.getObject(1).toString();
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(6);
                                        phrase = new Phrase("Diff Diagnosis : " + dbObject.getDBObject(rsetTotals1.getObject(5), "-"), pFontHeader);
                                        // exam = rsetTotals1.getObject(1).toString();
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(6);
                                        phrase = new Phrase("Doctor : " + dbObject.getDBObject(rsetTotals1.getObject(6), "-"), pFontHeader);
                                        // exam = rsetTotals1.getObject(1).toString();
                                        table.addCell(phrase);
                                        /*    java.sql.ResultSet rsetTotals = st2.executeQuery(" select  parameter,result,description,doctor from hp_clinical_results where patient_no = '"+memNo+"' and date  = '"+listofStaffNos[j]+"' and typeof_test = '"+exam+"'");
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        while (rsetTotals.next()) {
                                        table.getDefaultCell().setBorderColor(java.awt.Color.white);

                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rsetTotals.getObject(1), "-"), pFontHeader);
                                        table.addCell(phrase);

                                        phrase = new Phrase(dbObject.getDBObject(rsetTotals.getObject(2), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(3);
                                        phrase = new Phrase(dbObject.getDBObject(rsetTotals.getObject(3), "-"), pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        phrase = new Phrase(dbObject.getDBObject(rsetTotals.getObject(4), "-"), pFontHeader);
                                        table.addCell(phrase);


                                         */

                                    }
                                }
                            }
                            //}
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



            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT date FROM hp_clinical_results where patient_no = '" + MNo + "'  order by date");

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
}
