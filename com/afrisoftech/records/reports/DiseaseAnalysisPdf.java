//Author Francis Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class DiseaseAnalysisPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    int numberSeq = 0;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    String diseaseName = null;
    String diseaseCode = null;
    int iterations = 0;
    java.lang.String wardName = null;
    java.lang.String patCategory = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    com.afrisoftech.timeseries.AgeingSeries ageingSeries = null;
    com.afrisoftech.timeseries.DailyAgeing dailySeries = null;
    java.util.Date ageingDates[][] = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void DiseaseAnalysisPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String patcateg, java.lang.String diseasecode) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        diseaseCode = diseasecode;

        patCategory = patcateg;

        iterations = endDate.getDate() - beginDate.getDate();

        dbObject = new com.afrisoftech.lib.DBObject();

        System.out.println("Days Date" + endDate);
        // periodicDates = new com.afrisoftech.lib.PeriodicDates(endDate, 3);
        ageingSeries = new com.afrisoftech.timeseries.AgeingSeries(1, endate);
        dailySeries = new com.afrisoftech.timeseries.DailyAgeing(iterations + 1, endate);

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
                        java.sql.Statement st5 = connectDB.createStatement();
                        // java.sql.Statement st4 = connectDB.createStatement();
                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        java.sql.ResultSet rset5 = st5.executeQuery("SELECT initcap(disease_name) FROM hp_diseases WHERE code = '" + diseaseCode + "'");

                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            District = rset2.getObject(2).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }

                        while (rset5.next()) {
                            diseaseName = rset5.getObject(1).toString();
                        }

                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(compName.toUpperCase()), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(15);

                        int headerwidths[] = {5, 12, 30, 10, 12, 15, 15, 10, 14, 13, 15, 13,13,13,10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(1);

                        int headerwidths1[] = {100};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        //table1.setHeaderRows(1);


                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(6);


                        Phrase phrase = new Phrase("", pFontHeader);
                        Phrase phrase1 = new Phrase("", pFontHeader);

                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(15);


                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("HOSPITAL : " + compName, pFontHeader2);

                            table.addCell(phrase);

                            phrase = new Phrase("PERIOD : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader1);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(9);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("NAMES OF DISEASE : " + diseaseName, pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("ICD CODE : " + diseaseCode, pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("MONTH : " + monthString, pFontHeader);
                            // table.addCell(phrase);

                            phrase = new Phrase("YEAR : " + yearString, pFontHeader);
                            //table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);

                        try {
                            double totalMale = 0.00;
                            double totalFemale = 0.00;
                            double newMaleTotal = 0.00;
                            double newFemaleTotal = 0.00;
                            double oldMaleTotal = 0.00;
                            double oldFemaleTotal = 0.00;
                            java.lang.Object[] listofAct = this.getListofActivities();

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement stw = connectDB.createStatement();
                            java.sql.Statement st1 = connectDB.createStatement();
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.Statement st3 = connectDB.createStatement();
                            java.sql.Statement st4 = connectDB.createStatement();
                            java.sql.Statement st5 = connectDB.createStatement();
                            java.sql.Statement st6 = connectDB.createStatement();
                            java.sql.Statement st7 = connectDB.createStatement();
                            float tAdmissions = 0;
                            float tDischarges = 0;
                            float tDeaths = 0;
                            float tTIn = 0;
                            float tTOut = 0;
                            float tCumTotal = 0;
                            int count = 1;

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("#", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Patient No", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Patient Name", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Age", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Gender", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Date of \n Admission", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Date of \n Discharge", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Inpatient Days", pFontHeader1);
                            table.addCell(phrase);


                            phrase = new Phrase("Outcome of \n Admission", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Marital status", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Occupation", pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase("Unit No ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Tel No ", pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase("Residence", pFontHeader1);
                            table.addCell(phrase);
                            
                            phrase = new Phrase("Code ", pFontHeader1);
                            table.addCell(phrase);

                            // variables declalition

                            String pNo = "-";
                            String pOutcome = "-";
                            String pAge = "-";
                            String pGender = "-";
                            String pStatus = "-";
                            String admDate = null;
                            String disDate = null;
                            String addDiagnosis = "-";
                            float treatmentCost = 0;

                            table.getDefaultCell().setColspan(1);
                            for (int i = 0; i < listofAct.length; i++) {
                                int occu = 0;
                                if (patCategory.equalsIgnoreCase("OP") || patCategory.equalsIgnoreCase("IP")) {
                                    java.sql.ResultSet rset = st.executeQuery("SELECT distinct patient_no,patient_name,pat_age,gender,date_admitted,date_discharged, length_of_stay,admission_outcome,marital_status, (SELECT occupation FROM hp_admission WHERE hp_admission.patient_no = hp_patient_diagnosis.patient_no AND occupation is not null ORDER BY 1 LIMIT 1) as occupation, (SELECT sub_chief FROM hp_admission WHERE hp_admission.patient_no = hp_patient_diagnosis.patient_no AND sub_chief is not null ORDER BY 1 LIMIT 1) as unit_no,main_service,((SELECT tel_no FROM hp_patient_register where hp_patient_register.patient_no = hp_patient_diagnosis.patient_no UNION SELECT tel_no FROM hp_inpatient_register where hp_inpatient_register.patient_no = hp_patient_diagnosis.patient_no)) AS tel,((SELECT residence FROM hp_patient_register where hp_patient_register.patient_no = hp_patient_diagnosis.patient_no UNION SELECT residence FROM hp_inpatient_register where hp_inpatient_register.patient_no = hp_patient_diagnosis.patient_no)) AS residence  FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ILIKE '" + diseaseCode + "%' AND pat_category ilike '" + patCategory + "' AND patient_no = '" + listofAct[i] + "'");
                                    java.sql.ResultSet rsets = st3.executeQuery("SELECT main_service FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND patient_no = '" + listofAct[i] + "' AND pat_category ilike '" + patCategory + "' AND main_service != '" + diseaseCode + "'");
                                    while (rsets.next()) {
                                        // phrase = new Phrase(dbObject.getDBObject(rsets.getObject(1), "-"), pFontHeader1);
                                        //table.addCell(phrase);
                                    }
                                    while (rset.next()) {
                                        phrase = new Phrase(dbObject.getDBObject(count, "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(10), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(11), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(13), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(14), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(12), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        count++;

                                    }
                                } else {
                                    System.err.println("SELECT distinct patient_no,patient_name,pat_age,gender,date_admitted,date_discharged, length_of_stay,admission_outcome,marital_status, (SELECT occupation FROM hp_admission WHERE hp_admission.patient_no = hp_patient_diagnosis.patient_no AND occupation is not null ORDER BY 1 LIMIT 1) as occupation, (SELECT sub_chief FROM hp_admission WHERE hp_admission.patient_no = hp_patient_diagnosis.patient_no AND sub_chief is not null ORDER BY 1 LIMIT 1) as unit_no,main_service FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + diseaseCode + "%'  AND patient_no = '" + listofAct[i] + "'");

                                    String otherDisease = "-";
                                    java.sql.ResultSet rset = st.executeQuery("SELECT distinct patient_no,patient_name,pat_age,gender,date_admitted,date_discharged, length_of_stay,admission_outcome,marital_status, (SELECT occupation FROM hp_admission WHERE hp_admission.patient_no = hp_patient_diagnosis.patient_no AND occupation is not null ORDER BY 1 LIMIT 1) as occupation, (SELECT sub_chief FROM hp_admission WHERE hp_admission.patient_no = hp_patient_diagnosis.patient_no AND sub_chief is not null ORDER BY 1 LIMIT 1) as unit_no,main_service,((SELECT tel_no FROM hp_patient_register where hp_patient_register.patient_no = hp_patient_diagnosis.patient_no UNION SELECT tel_no FROM hp_inpatient_register where hp_inpatient_register.patient_no = hp_patient_diagnosis.patient_no) LIMIT 1) AS tel,((SELECT residence FROM hp_patient_register where hp_patient_register.patient_no = hp_patient_diagnosis.patient_no UNION SELECT residence FROM hp_inpatient_register where hp_inpatient_register.patient_no = hp_patient_diagnosis.patient_no)) AS residence  FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + diseaseCode + "%'  AND patient_no = '" + listofAct[i] + "'");
                                    java.sql.ResultSet rsets = st3.executeQuery("SELECT distinct main_service FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND patient_no = '" + listofAct[i] + "' AND main_service != '" + diseaseCode + "'");
                                    while (rset.next()) {
                                        // while (rsets.next()) {
                                        occu = occu + 1;
                                        // if (occu == 1) { 
                                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT);
                                        // otherDisease = dbObject.getDBObject(rsets.getObject(1), "-");


                                        phrase = new Phrase(dbObject.getDBObject(count, "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(10), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(11), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(13), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(14), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(12), "-"), pFontHeader1);
                                        table.addCell(phrase);


//                                            } else {
//                                                table.getDefaultCell().setColspan(6);
//                                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
//                                               table.getDefaultCell().setBorder(Rectangle.LEFT);
//                                                otherDisease = dbObject.getDBObject(rsets.getObject(1), "-");
//                                              
//                                                
//                                                phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(5), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                         phrase = new Phrase(dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                         phrase = new Phrase(dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                         phrase = new Phrase(dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                         phrase = new Phrase(dbObject.getDBObject(rset.getObject(10), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                         phrase = new Phrase(dbObject.getDBObject(rset.getObject(11), "-"), pFontHeader1);
//                                        table.addCell(phrase);
//                                                
//                                            }
                                        // }
                                        count++;
                                    }
                                }



                            }


                            docPdf.add(table);
                            // docPdf.add(table1);

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
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);


        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    /* public java.lang.Object[] getListofActivities() {
     int interval = 0;
     java.lang.Object[][] rangeDates = dailySeries.getAgeingDateSeries();
     java.lang.Object[][] monthDates = ageingSeries.getAgeingDateSeries();
    
     java.lang.Object[] listofActivities = null;
    
     java.util.Vector listActVector = new java.util.Vector(1,1);
    
    
     //for (int k = 0;  k < monthDates.length; k++){
     for (int k = monthDates.length - 1; k >= 0; k--) {
    
     for (int t = 0; t < rangeDates.length; t++) {
    
     listActVector.addElement(rangeDates[t][k]);
    
     }
     }
    
     listofActivities = listActVector.toArray();
     System.out.println("Done list of activities ...");
     return listofActivities;
    
     }*/
    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            if (patCategory.equalsIgnoreCase("OP") || patCategory.equalsIgnoreCase("IP")) {
                java.sql.Statement stmt1 = connectDB.createStatement();

                System.err.println("SELECT DISTINCT  patient_no FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + diseaseCode + "%' AND pat_category ilike '" + patCategory + "'");
                java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT  patient_no FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + diseaseCode + "%' AND pat_category ilike '" + patCategory + "'");

                while (rSet1.next()) {

                    listActVector.addElement(rSet1.getObject(1).toString());

                }
            } else {
                java.sql.Statement stmt1 = connectDB.createStatement();
                System.err.println("SELECT DISTINCT  patient_no FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + diseaseCode + "%'");
                java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT  patient_no FROM hp_patient_diagnosis WHERE date_discharged::date BETWEEN  '" + beginDate + "' AND '" + endDate + "' AND main_service ilike '" + diseaseCode + "%'");

                while (rSet1.next()) {

                    listActVector.addElement(rSet1.getObject(1).toString());

                }
            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
