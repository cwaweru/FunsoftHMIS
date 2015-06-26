//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class PatientLabResultsDetPdf implements java.lang.Runnable {

    java.lang.String memNo = null;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    com.afrisoftech.lib.DBObject dbObject;
    //////java.awt.Desktop deskTop = Desktop.getDesktop();
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

    public void PatientLabResultsDetPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date enddate, java.lang.String combox) {
        memNo = combox;
        endDate = enddate;
        beginDate = begindate;

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
                    /*
                    try {


                    java.sql.Statement st3 = connectDB.createStatement();
                    java.sql.Statement st4 = connectDB.createStatement();
                    java.sql.ResultSet rset2 = st3.executeQuery("select name from interim_footer");

                    // java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                    //   java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                    while(rset2.next()){
                    Messg = rset2.getObject(1).toString();
                    }


                    //  while(rset4.next())
                    //    date = rset4.getObject(1).toString();

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(""+Messg+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    //  headerFoter.ALIGN_CENTER;
                    //  headerFoter.setRight(5);
                    docPdf.setFooter(footer);


                    } catch(java.sql.SQLException SqlExec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }*/


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {25, 25, 20, 15, 15, 15};

                        table.setWidths(headerwidths);
                        //table..setWidths(headerwidths);
                        table.setWidthPercentage((100));


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();

                        //  table.addCell(phrase);

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        String Test = null;
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

                            //  java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,postal_code||' '||box_no||' '||town,main_telno||' '||other_telno,initcap(street),main_faxno,email,website,room_no from pb_hospitalprofile");
                            java.sql.ResultSet rset22 = st32.executeQuery("SELECT header_name from pb_header");
                            //  java.sql.ResultSet rseta = sta.executeQuery("select ad.ward,ad.bed_no,ad.doctor ,pr.adm_date,pr.discharge_date from hp_admission ad,hp_inpatient_register pr where pr.patient_no = '"+memNo+"' and pr.patient_no = ad.patient_no");

                            java.sql.ResultSet rset = st1.executeQuery("select patient_no,initcap(second_name||' '||first_name) from hp_patient_register where patient_no = '" + memNo + "'");
                            java.sql.ResultSet rset4 = sta.executeQuery("select distinct age,gender from hp_lab_results where patient_no = '" + memNo + "'");



                            while (rset22.next()) {
                                table.getDefaultCell().setColspan(6);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(dbObject.getDBObject(rset22.getObject(1), "-"), pFontHeader11);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Lab Results", pFontHeader1);
                                table.addCell(phrase);
                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);
                                table.getDefaultCell().setColspan(4);

                                phrase = new Phrase("Period : " + dateFormat.format(endDate11) + " ------ " + dateFormat.format(endDate1), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase("Printed On  :" + date, pFontHeader);

                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            while (rset.next()) {

                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient No    : " + memNo, pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Patient Name : " + dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                table.addCell(phrase);


                            }

                            while (rset4.next()) {



                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Age             : " + dbObject.getDBObject(rset4.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Sex             : " + dbObject.getDBObject(rset4.getObject(2), "-"), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase("", pFontHeader1);
                                // table.addCell(phrase);
                                table.getDefaultCell().setColspan(3);
                               // table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                //table.getDefaultCell().setBorderWidth(Rectangle.TOP);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            }
                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                           // table.getDefaultCell().setBorderWidth(Rectangle.BOTTOM);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);



                            java.lang.Object listofStaffNos[] = this.getListofStaffNos();
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setBorder(Rectangle.TOP);

                            for (int j = 0; j < listofStaffNos.length; j++) {
                                //table.getDefaultCell().setBorderWidth(Rectangle.BOTTOM)
                                String Notice = "-";
                                String Status = null;

                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("TEST: " + listofStaffNos[j], pFontHeader1);
                                table.addCell(phrase);


                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Parameters", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase("Lower", pFontHeader1);
                                table.addCell(phrase);
                                // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                phrase = new Phrase("Upper", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(2);
                                phrase = new Phrase("Result", pFontHeader1);
                                table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                String tests = "-";
                                java.sql.ResultSet rset4111 = st22.executeQuery("select distinct pathologist,lab_manager from hp_lab_results where patient_no = '" + memNo + "' and typeof_test ilike '" + listofStaffNos[j].toString() + "'");

                                //phrase = new Phrase("Results By : " + dbObject.getDBObject(rset4.getObject(4), "-"), pFontHeader1);
                                // table.addCell(phrase);
                                /*while (rset4111.next()) {

                                table.getDefaultCell().setColspan(6);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(dbObject.getDBObject("Test Done By : " + rset4111.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);
                                // phrase = new Phrase(dbObject.getDBObject(rset4111.getObject(2), "-"), pFontHeader1);
                                //table.addCell(phrase);
                                }*/
                                /*java.sql.ResultSet rset41 = st2.executeQuery("select distinct typeof_test,initcap(pathologist) from hp_lab_results where patient_no = '" + memNo + "' and typeof_test ilike '" + listofStaffNos[j].toString() + "'");

                                while (rset41.next()) {
                                tests = rset41.getString(1);
                                }*/

                                /*  java.sql.ResultSet rset42 = sta.executeQuery("select distinct date from hp_lab_results where patient_no = '" + memNo + "' and lab_no ilike '" + listofStaffNos[j].toString() + "'");
                                while (rset42.next()) {

                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Specimen Date :  " + dbObject.getDBObject(rset42.getObject(1), "-"), pFontHeader1);
                                table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Lab No: " + listofStaffNos[j], pFontHeader1);
                                table.addCell(phrase);*/

                                java.sql.ResultSet rset12 = st12.executeQuery("SELECT distinct code from hp_lab_results where  typeof_test ilike '" + listofStaffNos[j].toString() + "'  ");


                                while (rset12.next()) {
                                    table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    Test = rset12.getObject(1).toString();

                                    java.sql.ResultSet rset4c = stc.executeQuery("SELECT DISTINCT status,notes FROM pb_lab_standards where  code ilike '" + Test + "'");
                                    while (rset4c.next()) {
                                        Status = rset4c.getString(1).toLowerCase();
                                        Notice = rset4c.getString(2);
                                        System.out.println(Status);

                                        java.sql.ResultSet rset411 = st21.executeQuery("select distinct comments from hp_lab_results where patient_no = '" + memNo + "'  and code ilike '" + Test + "' and typeof_test ilike '" + listofStaffNos[j] + "'");
                                        // while (rset41.next()){

                                        if (Status.startsWith("t")) {
                                            java.sql.ResultSet rset1 = st.executeQuery("select DISTINCT initcap(parameter),lower_limit,upper_limit,out_come||''||units from hp_lab_results where patient_no = '" + memNo + "'  and code ilike '" + Test + "' and typeof_test ilike '" + listofStaffNos[j] + "' order by 1");

                                            /* table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);


                                            // }
                                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            phrase = new Phrase("Notes: " + Notice, pFontHeader1);
                                            table.addCell(phrase);*/



                                            /*table.getDefaultCell().setColspan(2);
                                            phrase = new Phrase("Parameters", pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(1);
                                            phrase = new Phrase("Lower", pFontHeader1);
                                            table.addCell(phrase);
                                            // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                            phrase = new Phrase("Upper", pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(2);
                                            phrase = new Phrase("Result", pFontHeader1);
                                            table.addCell(phrase);*/
                                            //phrase = new Phrase("Units", pFontHeader1);
                                            //table.addCell(phrase);
                                            while (rset1.next()) {
                                                table.getDefaultCell().setColspan(2);
                                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);

                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader);

                                                table.addCell(phrase);

                                                table.getDefaultCell().setColspan(2);
                                               // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);

                                                table.addCell(phrase);

                                                //table.getDefaultCell().setColspan(1);
                                                //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                //phrase = new Phrase(dbObject.getDBObject(rset1.getObject(5), "-"), pFontHeader);

                                                //table.addCell(phrase);

                                            }

                                        } else {
                                            java.sql.ResultSet rset1 = st.executeQuery("select initcap(parameter),lower_limit,upper_limit,out_come from hp_lab_results where patient_no = '" + memNo + "'  and code ilike '" + Test + "' and lab_no ilike '" + listofStaffNos[j] + "' order by parameter");

                                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                            table.getDefaultCell().setColspan(6);
                                            /*table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("TEST: " + listofStaffNos[j], pFontHeader1);
                                            table.addCell(phrase);*/
                                            // }
                                           /* table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                            phrase = new Phrase("Notes: " + Notice, pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setColspan(3);
                                            phrase = new Phrase("Test", pFontHeader1);
                                            table.addCell(phrase);
                                            table.getDefaultCell().setColspan(3);
                                            phrase = new Phrase("Result", pFontHeader1);
                                            table.addCell(phrase);*/
                                            while (rset1.next()) {
                                                table.getDefaultCell().setColspan(3);
                                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                                table.addCell(phrase);
                                                table.getDefaultCell().setColspan(3);

                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                phrase = new Phrase(dbObject.getDBObject(rset1.getObject(4), "-"), pFontHeader);

                                                table.addCell(phrase);
                                            }
                                        }
                                        table.getDefaultCell().setColspan(6);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("   ", pFontHeader1);
                                        //table.addCell(phrase);
                                        while (rset411.next()) {

                                            table.getDefaultCell().setColspan(6);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("Comments:  " + dbObject.getDBObject(rset411.getObject(1), "-"), pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                    }
                                }
                            }
                            table.getDefaultCell().setColspan(12);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("   ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Performed By:  ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Confirmed By: ", pFontHeader1);
                            table.addCell(phrase);

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            // table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

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

            docPdf.close();
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
            //java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT payee FROM ac_debtors WHERE date BETWEEN '"+beginDate+"' AND '"+endDate+"' and dealer ilike '"+memNo+"%' order by payee");
            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT typeof_test FROM hp_lab_results WHERE  patient_no ilike '" + memNo + "%' and date BETWEEN '" + beginDate + "' AND '" + endDate + "' order by 1");

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
