//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
////import //java.awt.Desktop;

public class DailyBedReturnSummaryPdf implements java.lang.Runnable {

//    ////java.awt.Desktop deskTop = Desktop.getDesktop();
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader3 = FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void DailyBedReturnSummaryPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        // wardName = ward;

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

            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

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
                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        //  headerFoter.setRight(5);
                        //  docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(13);
                        //   com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(13);

                        int headerwidths[] = {5, 25, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};//, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        com.lowagie.text.pdf.PdfPTable table6 = new com.lowagie.text.pdf.PdfPTable(7);

                        int headerwidths6[] = {20, 5, 20, 10, 20, 5, 20};

                        table6.setWidths(headerwidths6);

                        table6.setWidthPercentage((100));

                        //table6.setHeaderRows(1);
                        table6.getDefaultCell().setColspan(13);
                        table6.getDefaultCell().setFixedHeight(55);
                        table6.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                        table6.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                        table6.getDefaultCell().setFixedHeight(16);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table6.getDefaultCell().setColspan(13);
                        table6.getDefaultCell().setFixedHeight(55);
                        Phrase phrase = new Phrase(compName, pFontHeader3);

                        table6.addCell(phrase);
                        docPdf.add(table6);
                        table.setHeaderRows(3);



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


                        phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);

                            table.getDefaultCell().setFixedHeight(16);
                            table.getDefaultCell().setColspan(8);

                            phrase = new Phrase("DAILY BED RETURN AS AT : " + endDate.toString(), pFontHeader2);

                            table.addCell(phrase);
                            // phrase = new Phrase(compName, pFontHeader);
                            // table.addCell(phrase);
                            //table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);


                            table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            // table.getDefaultCell().setVerticalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(1);
                            //table.getDefaultCell().setRunDirection();

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(8);
                            //table.getDefaultCell().rotate();
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);



                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Inter Ward", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Refferals", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase(" ", pFontHeader);
                            table.addCell(phrase);

                            //  table.getDefaultCell().setColspan(11);
                            //  phrase = new Phrase("", pFontHeader);
                            // table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);


                            phrase = new Phrase("No", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Ward", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Beds", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Previous Patients", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Admission", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Discharge", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Deaths", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("Abscorders", pFontHeader);
                            table.addCell(phrase);

                            //table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("IN", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("OUT", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("IN", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("OUT", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);


                            phrase = new Phrase("Total Patients Today", pFontHeader);
                            table.addCell(phrase);
                            /*
                             phrase = new Phrase("Bed Sores Dev'd in Ward", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("Care takers", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("Malaria", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("No of Diabetes", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("RVDS Patients", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("Cash FIF", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("NHIF FIF", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("Amount Waived", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("Exemptions", pFontHeader);
                             table.addCell(phrase);

                             phrase = new Phrase("PTS Transfused", pFontHeader);
                             table.addCell(phrase);
                             * */

                            //table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

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
                            java.lang.Object[] listofAct11 = this.getListofStaffNos11();


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
                            int r = 0;
                            int previousTotal = 0;
                            int admissionsTotal = 0;
                            int inPatientsTotal = 0;
                            int dischargesTotal = 0;
                            int deathsTotal = 0;
                            int abscordesTotal = 0;
                            int transferinTotal = 0;
                            int transferoutTotal = 0;
                            int bedCapacityTotal = 0;
//                            int previousTotal = 0; int admissionsTotal = 0; int inPatientsTotal = 0; int dischargesTotal = 0; int deathsTotal = 0; int abscordesTotal = 0;

                            for (int i = 0; i < listofAct11.length; i++) {
                                r = r + 1;

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

                                int deaths = 0;
                                int abscordes = 0;

                                java.sql.ResultSet rset = st.executeQuery("SELECT max_beds FROM hp_wards WHERE ward_name = '" + listofAct11[i].toString() + "' ");

                                while (rset.next()) {
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(java.lang.String.valueOf(r), pFontHeader);
                                    table.addCell(phrase);
                                    pNo = rset.getString(1);
                                    bedCapacityTotal = Integer.parseInt(pNo) + bedCapacityTotal;
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(listofAct11[i].toString().toUpperCase(), pFontHeader1);
                                    table.addCell(phrase);
                                    //pName = rset.getString(2);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase(pNo, pFontHeader1);
                                    table.addCell(phrase);
                                }
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT COUNT(patient_no) FROM hp_ward_to_ward_transfer "
                                        + "WHERE date::date = '" + endDate + "' AND transfered_to = '" + listofAct11[i].toString() + "' ");
                                java.sql.ResultSet rset11 = stw.executeQuery("SELECT COUNT(patient_no) FROM hp_ward_to_ward_transfer "
                                        + "WHERE date::date = '" + endDate + "' AND transfered_from = '" + listofAct11[i].toString() + "' ");

                                //table.getDefaultCell().setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                                while (rset1.next()) {

                                    transferin = rset1.getInt(1);
                                    transferinTotal = transferin + transferinTotal;

                                }
                                while (rset11.next()) {


                                    transferout = rset11.getInt(1);
                                    transferoutTotal = transferout + transferoutTotal;

                                }

                                int vNewCot = 0, inPatients = 0, winPatients = 0, vacBed = 0, inCot = 0, winCot = 0;
                                discharges = 0;
                                admissions = 0;
                                previous = 0;
                                deaths = 0;
                                abscordes = 0;
                                int unDischargedCot = 0, wunDischargedCot = 0, wunDischarged = 0, vCot = 0, vBed = 0;



                                java.sql.Statement st2z = connectDB.createStatement();
                                java.sql.Statement st2s = connectDB.createStatement();
                                java.sql.Statement st3s = connectDB.createStatement();
                                java.sql.Statement st2zs = connectDB.createStatement();
                                java.sql.Statement st3d = connectDB.createStatement();
                                java.sql.Statement st3z = connectDB.createStatement();
                                java.sql.Statement st3zs = connectDB.createStatement();
                                wardName = listofAct11[i].toString();
                                java.sql.ResultSet rset2 = st2.executeQuery("SELECT DISTINCT count(patient_no)"
                                        + " FROM hp_admission WHERE date_admitted::date < '" + beginDate + "' "
                                        + "AND patient_no not in (SELECT patient_no FROM hp_mortuary where date_of_death < '"+beginDate+"' intersect select patient_no from hp_admission) AND (discharge_date::date >= '" + endDate + "' or discharge_date is null) AND ward = '" + wardName + "'");
                                while (rset2.next()) {
                                    previous = rset2.getInt(1);
                                    previousTotal = previous + previousTotal;

                                }

                                java.sql.ResultSet rset2z = st2z.executeQuery("SELECT DISTINCT count(patient_no) "
                                        + " FROM hp_admission WHERE date_admitted::date BETWEEN '" + beginDate + "' "
                                        + " AND '" + endDate + "' AND ward = '" + wardName + "'");
                                while (rset2z.next()) {
                                    admissions = rset2z.getInt(1);
                                    admissionsTotal = admissions + admissionsTotal;
                                }
                                java.sql.ResultSet rset3 = st3d.executeQuery("SELECT DISTINCT count(patient_no) "
                                        + "FROM hp_admission WHERE date_admitted::date <= '" + endDate + "' AND "
                                        + "(discharge_date::date >= '" + endDate + "')"
                                        + " AND ward = '" + wardName + "'");
                                while (rset3.next()) {
                                    inPatients = rset3.getInt(1);
                                    inPatientsTotal = inPatients + inPatientsTotal;
                                }

                                java.sql.ResultSet rset3z = st3z.executeQuery("SELECT DISTINCT count(patient_no) "
                                        + " FROM hp_admission WHERE discharge_date::date "
                                        + " BETWEEN '" + beginDate + "' AND  '" + endDate + "'  "
                                        + " AND ward = '" + wardName + "'");
                                while (rset3z.next()) {
                                    discharges = rset3z.getInt(1);
                                    dischargesTotal = discharges + dischargesTotal;
                                }
                                
//                                java.sql.ResultSet rset3z = st3z.executeQuery("SELECT DISTINCT count(patient_no) "
//                                        + " FROM hp_patient_discharge WHERE discharge_date::date "
//                                        + " BETWEEN '" + beginDate + "' AND  '" + endDate + "'  "
//                                        + " AND ward = '" + wardName + "'");
//                                while (rset3z.next()) {
//                                    discharges = rset3z.getInt(1);
//                                    dischargesTotal = discharges + dischargesTotal;
//                                }
                                // Deaths
                                java.sql.ResultSet rset2s = st2s.executeQuery("SELECT DISTINCT count(patient_no) FROM "
                                        + " (SELECT patient_no FROM hp_mortuary where date_of_death = '"+beginDate+"' intersect select patient_no from hp_admission where  ward = '" + wardName + "') as foo"); //admission_outcome ILIKE 'Died'");
                                while (rset2s.next()) {
                                    deaths = rset2s.getInt(1);
                                    deathsTotal = deaths + deathsTotal;
                                }

                                java.sql.ResultSet rset2zs = st2zs.executeQuery("SELECT DISTINCT count(patient_no) FROM "
                                        + " hp_patient_diagnosis WHERE date_discharged::date BETWEEN '" + beginDate + "' AND "
                                        + " '" + endDate + "' AND ward_name = '" + wardName + "' "
                                        + " AND admission_outcome ILIKE 'absc%'");
                                while (rset2zs.next()) {
                                    abscordes = rset2zs.getInt(1);
                                    abscordesTotal = abscordes + abscordesTotal;
                                }
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previous)), "0"), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(admissions)), "0"), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(discharges)), "0"), pFontHeader1);
                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(discharges)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(deaths)), "0"), pFontHeader1);
                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(deaths)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(abscordes)), "0"), pFontHeader1);
                                //  phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(abscordes)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferin)), "0"), pFontHeader1);
                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferin)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferout)), "0"), pFontHeader1);
                                //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferout)), pFontHeader1);
                                table.addCell(phrase);

                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                table.addCell(phrase);
                                phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previous + admissions + transferin - abscordes - deaths - transferout - discharges)), "0"), pFontHeader1);
                                //  phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previous + admissions)), pFontHeader1);
                                table.addCell(phrase);

                                /*
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader1);
                                 table.addCell(phrase);
                                 */

                            }
                            table.getDefaultCell().setColspan(1);
                            System.out.println("This is the total DBR : [" + (previousTotal + admissionsTotal) + "]");
                            phrase = new Phrase("", pFontHeader2);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Total", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(bedCapacityTotal)), "0"), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previousTotal)), "0"), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(admissionsTotal)), "0"), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(dischargesTotal)), "0"), pFontHeader2);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(discharges)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(deathsTotal)), "0"), pFontHeader2);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(deaths)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(abscordesTotal)), "0"), pFontHeader2);
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(abscordes)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferinTotal)), "0"), pFontHeader2);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferin)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferoutTotal)), "0"), pFontHeader2);
                            //   phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(transferout)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            System.out.println("Printing the total for DBR : [" + previousTotal + admissionsTotal + "]");
                            phrase = new Phrase(new com.afrisoftech.lib.DBObject().getDBObject(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previousTotal + admissionsTotal + transferinTotal - -deathsTotal - transferoutTotal - dischargesTotal - abscordesTotal)), "0"), pFontHeader2);
                            //  phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(previous + admissions)), pFontHeader1);
                            table.addCell(phrase);
                            ;

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(0)), pFontHeader2);
                            table.addCell(phrase);

                            System.out.println("Printing total DBR ...");
                            docPdf.add(table);

                        } catch (java.sql.SQLException SqlExec) {

                            SqlExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }


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

            IOexec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    public java.lang.Object[] getListofStaffNos1() {
        java.lang.Object[] listofStaffNos1 = null;

        java.util.Vector listStaffNoVector1 = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rset = stmt1.executeQuery("SELECT distinct patient_no,patient_name FROM hp_admission WHERE date_admitted::date = '" + endDate + "' AND ward_name ilike '" + wardName + "' ORDER BY patient_no ASC");

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

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT ward_name FROM hp_wards WHERE ward_name != '-' ORDER BY 1 ASC");

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
