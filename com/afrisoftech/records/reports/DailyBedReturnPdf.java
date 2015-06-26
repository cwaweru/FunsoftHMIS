//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class DailyBedReturnPdf implements java.lang.Runnable {

    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    int numberSeq = 0;
    java.lang.String wardName = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void DailyBedReturnPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String ward) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        wardName = ward;

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
        String nameTo;
        String noTo;
        String wardTo;
        String nameFrom;
        String noFrom;
        String wardFrom;


        try {

            java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

            tempFile.deleteOnExit();

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();

            java.lang.String debitTotal = null;

            java.lang.String creditTotal = null;

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String District = null;
                    String Region = null;

                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/medic","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                       // headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                   // docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {10, 40, 10, 10, 10, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table6 = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths6[] = {20, 5, 20, 10, 20, 5, 20};

                        table6.setWidths(headerwidths6);

                        table6.setWidthPercentage((100));

                        table6.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(2);

                        int headerwidths2[] = {45, 55};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));

                        table2.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table21 = new com.lowagie.text.pdf.PdfPTable(2);

                        int headerwidths21[] = {25, 75};

                        table21.setWidths(headerwidths21);

                        table21.setWidthPercentage((100));

                        table21.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table22 = new com.lowagie.text.pdf.PdfPTable(3);

                        int headerwidths22[] = {20, 60, 20};

                        table22.setWidths(headerwidths22);

                        table22.setWidthPercentage((100));

                        table22.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths1[] = {6, 30, 10, 10};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        table1.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table3 = new com.lowagie.text.pdf.PdfPTable(3);

                        int headerwidths3[] = {30, 20, 50};

                        table3.setWidths(headerwidths3);

                        table3.setWidthPercentage((100));

                        table3.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table5 = new com.lowagie.text.pdf.PdfPTable(3);

                        table5.setWidths(headerwidths3);

                        table5.setWidthPercentage((100));

                        table5.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table4 = new com.lowagie.text.pdf.PdfPTable(8);

                        int headerwidths4[] = {15, 10, 10, 10, 15, 10, 10, 10};

                        table4.setWidths(headerwidths4);

                        table4.setWidthPercentage((100));

                        table4.setHeaderRows(1);
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(6);


                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(6);

                            phrase = new Phrase(compName, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("DAILY BED RETURN", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(3);

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("WARD : " + wardName, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("DATE : " + dateFormat.format(endDate1), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        try {
                            int admissions = 0;
                            int discharges = 0;
                            int previous = 0;
                            int welladmissions = 0;
                            int welldischarges = 0;
                            int wellprevious = 0;
                            int transferin = 0;
                            int transferout = 0;
                            //java.lang.Object[] listofAct = this.getListofStaffNos();
                            //java.lang.Object[] listofAct1 = this.getListofStaffNos1();
                            //java.lang.Object[] listofAct11 = this.getListofStaffNos11();


                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            java.sql.Statement st8 = connectDB.createStatement();

                            // for (int i = 0; i < listofAct.length; i++) {
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("ADMISSIONS", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("DISCHARGES AND DEATHS", pFontHeader);
                            table2.addCell(phrase);

                            table21.getDefaultCell().setColspan(1);
                            phrase = new Phrase("NUMBER", pFontHeader);
                            table21.addCell(phrase);

                            phrase = new Phrase("NAME", pFontHeader);
                            table21.addCell(phrase);

                            phrase = new Phrase("NUMBER", pFontHeader);
                            table22.addCell(phrase);

                            phrase = new Phrase("NAME", pFontHeader);
                            table22.addCell(phrase);

                            phrase = new Phrase("DISCHARGE", pFontHeader);
                            table22.addCell(phrase);


                            String pNo = "-";
                            String pName, disNo, dName, disMethod = "-";


                            int ages = 0;
                            String pGender = "-";
                            String pStatus = "-";

                            int unDischarged = 0;
                            int tAdmissions = 0;
                            int tDischarges = 0;
                            int tDeaths = 0;
                            int bedsAvail = 0;
                            int tIn = 0;
                            int tOut = 0;




                            // java.sql.ResultSet rset1 = st1.executeQuery("SELECT SUM(count_no),gender FROM patient_analysis WHERE input_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' AND age::numeric BETWEEN '"+lowerAge+"' AND '"+upperAge+"' AND comments ilike 'old' GROUP BY 2 ORDER BY gender DESC");

                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            //int i = 0;
                            // int j = 0;
                            java.sql.ResultSet rset = st.executeQuery("SELECT distinct patient_no,patient_name FROM hp_admission WHERE date_admitted::date = '" + endDate + "' AND ward ilike '" + wardName + "' ORDER BY patient_no ASC");

                            table21.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                            while (rset.next()) {
                                pNo = rset.getString(1);
                                pName = rset.getString(2);

                                phrase = new Phrase(pNo, pFontHeader1);
                                table21.addCell(phrase);
                                phrase = new Phrase(pName, pFontHeader1);
                                table21.addCell(phrase);
                                admissions = admissions + 1;


                            }
                            table22.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                            java.sql.ResultSet rset21 = st1.executeQuery("SELECT distinct patient_no,patient_name,"
                                    + "CASE WHEN (transaction_type ILIKE 'ADMITTED') THEN 'Discharged' ELSE transaction_type END AS transaction_type "
                                    + "FROM hp_admission WHERE discharge_date::date = '" + endDate + "' AND "
                                    + "ward ilike '" + wardName + "' ORDER BY patient_no ASC");


                            while (rset21.next()) {
                                // if (!(rset21.getObject(1).equals(null))) {
                                //ages = rset.getInt(3);
                                disNo = rset21.getString(1);
                                dName = rset21.getString(2);
                                disMethod = rset21.getString(3);

                                phrase = new Phrase(disNo, pFontHeader1);
                                table22.addCell(phrase);
                                phrase = new Phrase(dName, pFontHeader1);
                                table22.addCell(phrase);
                                phrase = new Phrase(disMethod, pFontHeader1);
                                table22.addCell(phrase);

                                discharges = discharges + 1;


                            }



                            table2.getDefaultCell().setFixedHeight(280);
                            table2.addCell(table21);
                            table2.addCell(table22);
                            table2.getDefaultCell().setFixedHeight(20);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("INTER-WARD TRANSFER WITHIN THE HOSPITAL", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
                            table2.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("ADMISSIONS", pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("DISCHARGES", pFontHeader1);
                            table2.addCell(phrase);
                            //table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setFixedHeight(120);
                            phrase = new Phrase("FROM WARD", pFontHeader);
                            table3.addCell(phrase);

                            phrase = new Phrase("NUMBER", pFontHeader);
                            table3.addCell(phrase);

                            phrase = new Phrase("NAME", pFontHeader);
                            table3.addCell(phrase);

                            phrase = new Phrase("TO WARD", pFontHeader);
                            table5.addCell(phrase);

                            phrase = new Phrase("NUMBER", pFontHeader);
                            table5.addCell(phrase);

                            phrase = new Phrase("NAME", pFontHeader);
                            table5.addCell(phrase);

                            java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT transfered_from,patient_no,patient_name FROM hp_ward_to_ward_transfer WHERE date::date = '" + endDate + "' AND transfered_to = '" + wardName + "' ORDER BY patient_no ASC");
                            java.sql.ResultSet rset11 = stw.executeQuery("SELECT DISTINCT transfered_to,patient_no,patient_name FROM hp_ward_to_ward_transfer WHERE date::date = '" + endDate + "' AND transfered_from = '" + wardName + "' ORDER BY patient_no ASC");

                            table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                            while (rset1.next()) {

                                wardFrom = rset1.getString(1);
                                noFrom = rset1.getString(2);
                                nameFrom = rset1.getString(3);

                                transferin = transferin + 1;

                                table3.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase(wardFrom, pFontHeader1);
                                table3.addCell(phrase);
                                phrase = new Phrase(noFrom, pFontHeader1);
                                table3.addCell(phrase);
                                phrase = new Phrase(nameFrom, pFontHeader1);
                                table3.addCell(phrase);
                            }
                            while (rset11.next()) {

                                wardTo = rset11.getString(1);
                                noTo = rset11.getString(2);
                                nameTo = rset11.getString(3);
                                phrase = new Phrase(wardTo, pFontHeader1);
                                table5.addCell(phrase);
                                phrase = new Phrase(noTo, pFontHeader1);
                                table5.addCell(phrase);
                                phrase = new Phrase(nameTo, pFontHeader1);
                                table5.addCell(phrase);

                                transferout = transferout + 1;

                            }

                            table2.addCell(table3);
                            table2.addCell(table5);


                            int vNewCot = 0, inPatients = 0, winPatients = 0, vacBed = 0, inCot = 0, winCot = 0;
                            int unDischargedCot = 0, wunDischargedCot = 0, wunDischarged = 0, vCot = 0, vBed = 0;

                            table4.getDefaultCell().setColspan(4);
                            phrase = new Phrase("PREVIOUSLY DAILY RETURN NUMBERS", pFontHeader);
                            table4.addCell(phrase);
                            phrase = new Phrase("TODAYS DAILY RETURN NUMBERS", pFontHeader);
                            table4.addCell(phrase);

                            table4.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
                            table4.getDefaultCell().setColspan(1);
                            table4.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("BEDS", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("COTS", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("TOTAL", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("BEDS", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("COTS", pFontHeader);
                            table4.addCell(phrase);

                            phrase = new Phrase("TOTAL", pFontHeader);
                            table4.addCell(phrase);
                            java.sql.Statement st2z = connectDB.createStatement();
                            java.sql.Statement st2s = connectDB.createStatement();
                            java.sql.Statement st3s = connectDB.createStatement();
                            java.sql.Statement st2zs = connectDB.createStatement();
                            java.sql.Statement st3d = connectDB.createStatement();
                            java.sql.Statement st3z = connectDB.createStatement();
                            java.sql.Statement st3zs = connectDB.createStatement();

                            java.sql.ResultSet rset2 = st2.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date < '" + beginDate + "' AND( discharge_date::date >= '" + beginDate + "' OR discharge_date::date IS NULL) AND ward = '" + wardName + "' AND bed_no  NOT ILIKE 'Cot%'");
                            while (rset2.next()) {
                                unDischarged = rset2.getInt(1);

                            }

                            java.sql.ResultSet rset2z = st2z.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date < '" + beginDate + "' AND (discharge_date::date >= '" + beginDate + "' OR discharge_date::date IS NULL) AND ward = '" + wardName + "' AND bed_no ILIKE 'Cot%'");
                            while (rset2z.next()) {
                                unDischargedCot = rset2z.getInt(1);
                            }
                            java.sql.ResultSet rset3 = st3d.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date <= '" + endDate + "' AND (discharge_date::date >= '" + endDate + "' OR discharge_date::date IS NULL) AND ward = '" + wardName + "' AND bed_no NOT ILIKE 'Cot%'");
                            while (rset3.next()) {
                                inPatients = rset3.getInt(1);
                            }

                            java.sql.ResultSet rset3z = st3z.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date <= '" + endDate + "' AND (discharge_date::date >= '" + endDate + "' OR discharge_date::date IS NULL) AND ward = '" + wardName + "' AND bed_no ILIKE 'Cot%'");
                            while (rset3z.next()) {
                                inCot = rset3z.getInt(1);
                            }


                            java.sql.ResultSet rset2s = st2s.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date < '" + beginDate + "' AND discharge_date::date > '" + beginDate + "' AND ward = '" + wardName + "' AND bed_no  NOT ILIKE 'Cot%' AND transaction_type = 'WELL'");
                            while (rset2s.next()) {
                                wunDischarged = rset2s.getInt(1);
                            }

                            java.sql.ResultSet rset2zs = st2zs.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date < '" + beginDate + "' AND discharge_date::date >= '" + beginDate + "' AND ward = '" + wardName + "' AND bed_no ILIKE 'Cot%' AND transaction_type = 'WELL'");
                            while (rset2zs.next()) {
                                wunDischargedCot = rset2zs.getInt(1);
                            }
                            java.sql.ResultSet rset3s = st3s.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date <= '" + endDate + "' AND (discharge_date::date >= '" + endDate + "' OR discharge_date::date IS NULL) AND ward = '" + wardName + "' AND bed_no NOT ILIKE 'Cot%' AND transaction_type = 'WELL'");
                            while (rset3s.next()) {
                                winPatients = rset3s.getInt(1);
                            }

                            java.sql.ResultSet rset3zs = st3zs.executeQuery("SELECT DISTINCT count(patient_no) FROM hp_admission WHERE date_admitted::date <= '" + endDate + "' AND (discharge_date::date >= '" + endDate + "' OR discharge_date::date IS NULL) AND ward = '" + wardName + "' AND bed_no ILIKE 'Cot%'  AND transaction_type = 'WELL'");
                            while (rset3zs.next()) {
                                winCot = rset3zs.getInt(1);
                            }

                            java.sql.ResultSet rset8 = st8.executeQuery("SELECT distinct count(bed_no) FROM hp_bed_setup WHERE occupied = false AND ward = '" + wardName + "'");
                            while (rset8.next()) {
                                bedsAvail = rset8.getInt(1);
                            }
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("PATIENTS", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischarged)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischargedCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischargedCot + unDischarged)), pFontHeader1);
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("PATIENTS", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(inPatients-discharges)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(inCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(inCot + inPatients-discharges)), pFontHeader1);
                            table4.addCell(phrase);


                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("WELL PEOPLE", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(wunDischarged)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(wunDischargedCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(wunDischargedCot + wunDischarged)), pFontHeader1);
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("WELL PEOPLE", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(winPatients)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(winCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(winCot + winPatients)), pFontHeader1);
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("VACANT", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(vBed)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(vCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(vCot + vBed)), pFontHeader1);
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("VACANT", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(vacBed)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(vNewCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);


                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(vNewCot + vacBed)), pFontHeader1);
                            table4.addCell(phrase);

                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischarged + wunDischarged - vBed)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischargedCot + wunDischargedCot - vCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(unDischargedCot + unDischarged + wunDischargedCot + wunDischarged - (vCot + vBed))), pFontHeader1);
                            table4.addCell(phrase);


                            previous = previous + unDischargedCot + unDischarged;
                            wellprevious = wellprevious + wunDischargedCot + wunDischarged;

                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL", pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);
                            table4.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(inPatients + winPatients + transferin - (discharges+transferout))), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(inCot + winCot - vNewCot)), pFontHeader1);
                            // newMaleTotal = newMaleTotal+newMale;
                            table4.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(inPatients + winPatients - discharges + inCot + winCot)), pFontHeader1);
                            table4.addCell(phrase);


                            table4.getDefaultCell().setColspan(8);
                            phrase = new Phrase(" ", pFontHeader1);

                            table4.addCell(phrase);
                            table4.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table4.addCell(phrase);

                            table6.getDefaultCell().setColspan(3);
                            table6.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("RECORDS OFFICE USE ONLY  ", pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setColspan(4);
                            phrase = new Phrase("SISTER IN CHARGE OF WARD__________________________________  ", pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setColspan(7);
                            phrase = new Phrase(" ", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setColspan(3);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("PATIENTS  ", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setColspan(1);
                            phrase = new Phrase("  ", pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setColspan(3);
                            phrase = new Phrase("WELL PEOPLE  ", pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setColspan(1);
                            table6.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Previously : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("-", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previous)), pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setColspan(1);
                            table6.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Previously : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("-", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(wellprevious)), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Admissions : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("+", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(admissions+transferin)), pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Admissions : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("+", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(welladmissions)), pFontHeader1);
                            table6.addCell(phrase);

                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("=", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(admissions + previous+transferin)), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);
                            phrase = new Phrase("TOTAL : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("=", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(welladmissions + wellprevious)), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Discharge : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("-", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(discharges+transferout)), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("Discharge : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("-", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(welldischarges)), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("=", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(admissions+transferin + previous - (discharges+transferout))), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("TOTAL : ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("=", pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(welladmissions + wellprevious - welldischarges)), pFontHeader1);
                            table6.addCell(phrase);
                            table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Records copy ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("-", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("Checked By....................... ", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("........", pFontHeader1);
                            table6.addCell(phrase);

                            phrase = new Phrase("........................................", pFontHeader1);
                            table6.addCell(phrase);


                            docPdf.add(table);
                            docPdf.add(table2);
                           // docPdf.add(table3);
                            docPdf.add(table4);
                            docPdf.add(table6);
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

    public java.lang.Object[] getListofStaffNos1() {
        java.lang.Object[] listofStaffNos1 = null;

        java.util.Vector listStaffNoVector1 = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rset = stmt1.executeQuery("SELECT distinct patient_no,patient_name FROM hp_admission WHERE date_admitted::date = '" + endDate + "' AND ward ilike '" + wardName + "' ORDER BY patient_no ASC");

            while (rset.next()) {

                listStaffNoVector1.addElement(rset.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos1 = listStaffNoVector1.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos1;
    }

    public java.lang.Object[] getListofStaffNos11() {

        java.lang.Object[] listofStaffNos11 = null;

        java.util.Vector listStaffNoVector11 = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct patient_no,patient_name,transaction_type FROM hp_admission WHERE discharge_date::date = '" + endDate + "' AND ward ilike '" + wardName + "' ORDER BY patient_no ASC");

            while (rSet1.next()) {

                listStaffNoVector11.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofStaffNos11 = listStaffNoVector11.toArray();
        System.out.println("Done list of Staff Nos ...");
        return listofStaffNos11;
    }
}





