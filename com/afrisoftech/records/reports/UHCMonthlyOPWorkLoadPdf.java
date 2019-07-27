//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.records.reports;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//import //java.awt.Desktop;

public class UHCMonthlyOPWorkLoadPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    int numberSeq = 0;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String schemeName = null;

    public void UHCMonthlyOPWorkLoadPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, String uhcScheme) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        schemeName = uhcScheme;

        beginDate = begindate;

        endDate = endate;

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

            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3);
            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String District = null;
                    String Region = null;

                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,district_branch,region FROM pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getString(1);
                            District = rset2.getString(2);
                            Region = rset2.getString(3);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
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

                    int dayT = 0;
                    int monT = 0;
                    int cashT = 0;
                    int schemeT = 0;
                    int selfT = 0;
                    int copT = 0;
                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                        int headerwidths[] = {6, 40, 10, 15, 10, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths1[] = {6, 30, 10, 10};

                        table1.setWidths(headerwidths1);

                        table1.setWidthPercentage((100));

                        table1.setHeaderRows(1);

                        com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(12);

                        int headerwidths2[] = {7, 30, 10, 10, 10, 20, 10, 10, 10, 10, 10, 10};

                        table2.setWidths(headerwidths2);

                        table2.setWidthPercentage((100));

                        table2.setHeaderRows(1);
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
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("UNIVERSAL HEALTH CARE : MONTHLY WORKLOAD REPORT FOR HEALTH FACILITIES", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            // table.addCell(phrase);
                            // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                            //table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table.getDefaultCell().setColspan(3);

                            //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader);
                            // table.addCell(phrase);
                            phrase = new Phrase("Institution : " + compName, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase("MOH/UHC 717", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("District/Sub County : " + District, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Region/County : " + Region, pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            phrase = new Phrase(dateFormat.format(endDate1), pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("Scheme Name : " + schemeName.toUpperCase(), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A. OUT-PATIENT SERVICES", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A1 GENERAL OUT-PATIENTS(FILTER CLINICS)", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("NEW", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("RE-ATT.", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("TOTAL PATIENTS", pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase("TOTALCOST (KES)", pFontHeader);
                            table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        // table.addCell("Amount KShs.");
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                        try {
                            int totalMale = 0;
                            int totalFemale = 0;
                            int newMaleTotal = 0;
                            int newFemaleTotal = 0;
                            int oldMaleTotal = 0;
                            int oldFemaleTotal = 0;
                            int cFemale = 0;
                            int coldFemale = 0;
//                            java.lang.Object[] listofAct = this.getListofStaffNos();

                            java.sql.Statement stw = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();
                            //  for (int i = 0; i < listofAct.length; i++) {
                            // variables declalition
                            int newCount = 0;
                            int oldCount = 0;
                            int newMale = 0;
                            int newFemale = 0;
                            int oldMale = 0;
                            int oldFemale = 0;
                            int femaleWorkloadCost = 0;
                            int maleWorkloadCost = 0;
                            int totalNewCount = 0;
                            int totalOldCount = 0;
                            int totalCost = 0;
                            String Gender = null;
                            double lowerAge = 0;
                            double upperAge = 0;
                            int patNo1 = 0;

                            newCount = 0;
                            oldCount = 0;
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.1.1", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("FEMALE", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            newFemale = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByGenderANDAttendanceType(connectDB, schemeName, "Female", "New", beginDate, endDate);
                            oldFemale = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByGenderANDAttendanceType(connectDB, schemeName, "Female", "Old", beginDate, endDate);
                            femaleWorkloadCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByGender(connectDB, schemeName, "Female", beginDate, endDate);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemale)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldFemale)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newFemale + oldFemale)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(femaleWorkloadCost)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.1.2", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("MALE", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            newMale = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByGenderANDAttendanceType(connectDB, schemeName, "Male", "New", beginDate, endDate);
                            oldMale = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByGenderANDAttendanceType(connectDB, schemeName, "Male", "Old", beginDate, endDate);
                            maleWorkloadCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByGender(connectDB, schemeName, "Male", beginDate, endDate);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMale)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale + oldMale)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(maleWorkloadCost)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.1.3", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL GENERAL OUT-PATIENTS", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale + newFemale)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldMale + oldFemale)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newMale + newFemale + oldMale + oldFemale)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(femaleWorkloadCost + maleWorkloadCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            newCount = 0;
                            oldCount = 0;
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.2", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("CASUALTY", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int casualtyNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "CAS", "New", beginDate, endDate);
                            int casualtyOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "CAS", "Old", beginDate, endDate);

                            int casualtyCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "CAS", beginDate, endDate);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(casualtyNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(casualtyOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(casualtyNew + casualtyOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(casualtyCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            //table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("A3. SPECIAL CLINICS(if recorded separately from \n General Filter Clinics)", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.1", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("E.N.T Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            int entNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "ENT", "New", beginDate, endDate);
                            int entOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "ENT", "Old", beginDate, endDate);

                            int entCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "ENT", beginDate, endDate);
                            totalNewCount = totalNewCount + entNew;
                            totalOldCount = totalOldCount + entOld;
                            totalCost = totalCost + entCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(entNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(entOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(entNew + entOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(entCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.2", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Eye Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int eyeNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "EYE", "New", beginDate, endDate);
                            int eyeOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "EYE", "Old", beginDate, endDate);

                            int eyeCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "EYE", beginDate, endDate);
                            totalNewCount = totalNewCount + eyeNew;
                            totalOldCount = totalOldCount + eyeOld;
                            totalCost = totalCost + eyeCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(eyeNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(eyeOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(eyeNew + eyeOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(eyeCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.3", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TB and Leprosy", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int tbNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "ENT", "New", beginDate, endDate);
                            int tbOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "ENT", "Old", beginDate, endDate);

                            int tbCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "ENT", beginDate, endDate);
                            totalNewCount = totalNewCount + tbNew;
                            totalOldCount = totalOldCount + tbOld;
                            totalCost = totalCost + tbCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tbNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tbOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tbNew + tbOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(tbCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.4", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Sexually Transmitted Infections", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int stdNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "STD", "New", beginDate, endDate);
                            int stdOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "STD", "Old", beginDate, endDate);

                            int stdCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "STD", beginDate, endDate);
                            totalNewCount = totalNewCount + stdNew;
                            totalOldCount = totalOldCount + stdOld;
                            totalCost = totalCost + stdCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(stdNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(stdOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(stdNew + stdOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(stdCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.5", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Psychiatry", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int psychNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "PSYC", "New", beginDate, endDate);
                            int psychOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "PSYC", "Old", beginDate, endDate);

                            int psychCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "PSYC", beginDate, endDate);
                            totalNewCount = totalNewCount + psychNew;
                            totalOldCount = totalOldCount + psychOld;
                            totalCost = totalCost + psychCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(psychNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(psychOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(psychNew + psychOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(psychCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.6", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Orthopaedic Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int orthNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "ORTH", "New", beginDate, endDate);
                            int orthOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "ORTH", "Old", beginDate, endDate);

                            int orthCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "ORTH", beginDate, endDate);
                            totalNewCount = totalNewCount + orthNew;
                            totalOldCount = totalOldCount + orthOld;
                            totalCost = totalCost + orthCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(orthNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(orthOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(orthNew + orthOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(orthCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.7", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Occupational Therapy Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int occNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "OCC", "New", beginDate, endDate);
                            int occOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "OCC", "Old", beginDate, endDate);

                            int occCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "OCC", beginDate, endDate);
                            totalNewCount = totalNewCount + occNew;
                            totalOldCount = totalOldCount + occOld;
                            totalCost = totalCost + occCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(occNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(occOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(occNew + occOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(occCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.8", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Physiotherapy Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int physNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "PHYS", "New", beginDate, endDate);
                            int physOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "PHYS", "Old", beginDate, endDate);

                            int physCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "PHYS", beginDate, endDate);
                            totalNewCount = totalNewCount + physNew;
                            totalOldCount = totalOldCount + physOld;
                            totalCost = totalCost + physCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(physNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(physOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(physNew + physOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(physCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.9", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Medical Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int mopcNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "MOPC", "New", beginDate, endDate);
                            int mopcOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "MOPC", "Old", beginDate, endDate);

                            int mopcCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "MOPC", beginDate, endDate);
                            totalNewCount = totalNewCount + mopcNew;
                            totalOldCount = totalOldCount + mopcOld;
                            totalCost = totalCost + mopcCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mopcNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mopcOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mopcNew + entOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(mopcCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.10", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Surgical Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int sopcNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "SOPC", "New", beginDate, endDate);
                            int sopcOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "SOPC", "Old", beginDate, endDate);

                            int sopcCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "SOPC", beginDate, endDate);
                            totalNewCount = totalNewCount + sopcNew;
                            totalOldCount = totalOldCount + sopcOld;
                            totalCost = totalCost + sopcCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(sopcNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(sopcOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(sopcNew + sopcOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(sopcCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.11", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Paediatrics Clinic", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int paedNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "PAED", "New", beginDate, endDate);
                            int paedOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "PAED", "Old", beginDate, endDate);

                            int paedCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "PAED", beginDate, endDate);
                            totalNewCount = totalNewCount + paedNew;
                            totalOldCount = totalOldCount + paedOld;
                            totalCost = totalCost + paedCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paedNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paedOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paedNew + paedOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(paedCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.12", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Obstetrics/Gynaecology ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int gopcNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "GOPC", "New", beginDate, endDate);
                            int gopcOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "GOPC", "Old", beginDate, endDate);

                            int gopcCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "GOPC", beginDate, endDate);
                            totalNewCount = totalNewCount + gopcNew;
                            totalOldCount = totalOldCount + gopcOld;
                            totalCost = totalCost + gopcCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gopcNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gopcOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gopcNew + gopcOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(gopcCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.13", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("All Other Special Clinics ", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int othNew = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "AOT", "New", beginDate, endDate);
                            int othOld = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByClinicAndAttendanceType(connectDB, schemeName, "AOT", "Old", beginDate, endDate);

                            int othCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "AOT", beginDate, endDate);
                            totalNewCount = totalNewCount + othNew;
                            totalOldCount = totalOldCount + othOld;
                            totalCost = totalCost + othCost;
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(othNew)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(othOld)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(othNew + othOld)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(othCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.3.8", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL SPECIAL CLINICS", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int totalClinicsCost = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByClinic(connectDB, schemeName, "ENT", beginDate, endDate);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalNewCount)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalOldCount)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalNewCount + totalOldCount)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalCost)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5 DENTAL CLINIC", pFontHeader);
                            table.addCell(phrase);

                            int newDentalCount = 0;
                            int oldDentalCount = 0;
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5.1", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Attendances (Excluding fillings and extractions)", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int newdentalExcl = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByDepartmentAndServiceAttendanceTypeExl(connectDB, schemeName, "Dental", "New", beginDate, endDate);
                            int olddentalExcl = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByDepartmentAndServiceAttendanceTypeExl(connectDB, schemeName, "Dental", "Old", beginDate, endDate);
                            newDentalCount = newDentalCount + newdentalExcl;
                            oldDentalCount = oldDentalCount + olddentalExcl;

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newdentalExcl)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(olddentalExcl)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newdentalExcl + olddentalExcl)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5.2", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Fillings", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int newdentalFillings = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByDepartmentAndServiceAttendanceType(connectDB, schemeName, "Dental", "filling", "New", beginDate, endDate);
                            int olddentalFillings = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByDepartmentAndServiceAttendanceType(connectDB, schemeName, "Dental", "filling", "Old", beginDate, endDate);
                            newDentalCount = newDentalCount + newdentalFillings;
                            oldDentalCount = oldDentalCount + olddentalFillings;

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newdentalFillings)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(olddentalFillings)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newdentalFillings + olddentalFillings)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5.3", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Extractions", pFontHeader1);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            int newdentalExtractions = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByDepartmentAndServiceAttendanceType(connectDB, schemeName, "Dental", "extraction", "New", beginDate, endDate);
                            int olddentalExtractions = com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByDepartmentAndServiceAttendanceType(connectDB, schemeName, "Dental", "extraction", "Old", beginDate, endDate);
                            newDentalCount = newDentalCount + newdentalExtractions;
                            oldDentalCount = oldDentalCount + olddentalExtractions;

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newdentalExtractions)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(olddentalExtractions)), pFontHeader1);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newdentalExtractions + olddentalExtractions)), pFontHeader1);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount + oldCount)), pFontHeader1);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("A.5.4", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL DENTAL SERVICES", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newDentalCount)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(oldDentalCount)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newDentalCount + oldDentalCount)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + totalFemale)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("A.6 TOTAL OUT-PATIENT SERVICES \n (=A.1.5 + A.2 + A.3.7 + A.4.5 + A.5.4)", pFontHeader);
                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + newMale + newFemale + cFemale + newMaleTotal + newFemaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalFemale + oldMale + oldFemale + coldFemale + oldMaleTotal + oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + newMale + oldMale + cFemale + newMaleTotal + newFemaleTotal + totalFemale + newFemale + oldFemale + coldFemale + oldMaleTotal + oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(totalMale + newMale + oldMale + cFemale + newMaleTotal + newFemaleTotal + totalFemale + newFemale + oldFemale + coldFemale + oldMaleTotal + oldFemaleTotal)), pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);

                            java.sql.Statement stme = connectDB.createStatement();
                            java.sql.ResultSet rsme = stme.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND description ILIKE '%Medical examination%'");
                            while (rsme.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.7 MEDICAL EXAMINATION (except p3)", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsme.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);

                            }

                            java.sql.Statement stin = connectDB.createStatement();
                            java.sql.ResultSet rsin = stin.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%inj%') AND drawer = 'OP'");
                            while (rsin.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.10 INJECTIONS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsin.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);

                            }

                            java.sql.Statement stmr = connectDB.createStatement();
                            java.sql.ResultSet rsmr = stmr.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%p3%' OR description ILIKE '%p.3%' OR description ILIKE '%p 3%')");
                            while (rsmr.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.8 MEDICAL REPORTS (incl. P3,compensation,insurance,etc)", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsmr.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);

                            }

                            java.sql.Statement stst = connectDB.createStatement();
                            java.sql.ResultSet rsst = stst.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%stitchi%' OR service_type ILIKE '%stichi%') AND drawer = 'OP'");
                            while (rsst.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.11 STITCHING", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsst.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);

                            }

                            java.sql.Statement stdr = connectDB.createStatement();
                            java.sql.ResultSet rsdr = stdr.executeQuery("SELECT COUNT(patient_no) FROM ac_ledger WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (service_type ILIKE '%dressing%') AND drawer = 'OP'");
                            while (rsdr.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.9 DRESSINGS", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rsdr.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);

                            }

                            java.sql.Statement stpp = connectDB.createStatement();
                            java.sql.ResultSet rspp = stpp.executeQuery("SELECT sum(quantity) FROM ac_cash_collection WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND (description ILIKE '%pop%' OR description ILIKE '%p.o.p.%' OR description ILIKE '%p o p%' OR description ILIKE '%plaster%' )");
                            while (rspp.next()) {
                                newCount = 0;
                                oldCount = 0;
                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("A.12 P.O.P", pFontHeader);
                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                newCount = rspp.getInt(1);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(newCount)), pFontHeader);
                                table.addCell(phrase);

                            }
//Inpatient workload starts here....
//Discharges
                            float gAdults = 0;
                            float paeds = 0;
                            float matMothers = 0;
                            float amenity = 0;
                            float pTotal = 0;
                            float tgAdults = 0;
                            float tpaeds = 0;
                            float tmatMothers = 0;
                            float tamenity = 0;
                            float tpTotal = 0;

                            table.getDefaultCell().setColspan(6);
                            table.getDefaultCell().setBorder(Rectangle.TOP);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setFixedHeight(100);
                            phrase = new Phrase("", pFontHeader);
                            table.addCell(phrase);
                            //table2.addCell(phrase);
                            //table2.addCell(phrase);

                            // IN-Patient Section
                            docPdf.newPage();
                            table2.getDefaultCell().setColspan(12);
                            // table2.getDefaultCell().setFixedHeight(12);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B. INPATIENT SERVICES", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1 INPATIENT", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table2.getDefaultCell().setColspan(1);

                            phrase = new Phrase("MEDICAL", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("SURGICAL", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("OBST/GYN", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("EYE", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("ORTHOPAEDIC", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("ISOLATION", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("PSYCHIATRY", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("OTHER", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("TOTAL PATIENTS", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("TOTAL COST (KES)", pFontHeader);
                            table2.addCell(phrase);

                            //Discharges
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Discharges", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "MEDICAL", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "SURGICAL", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "OBS", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "EYE", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "ORTHO", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "ISOLATION", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "PSYCH", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "OTHER", "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByExitType(connectDB, schemeName, beginDate, endDate, "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByExitType(connectDB, schemeName, beginDate, endDate, "DISCHARGE"))), pFontHeader1);
                            table2.addCell(phrase);

                            //Deaths
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Deaths", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "MEDICAL", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "SURGICAL", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "OBS", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "EYE", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "ORTHO", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "ISOLATION", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "PSYCH", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "OTHER", "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByExitType(connectDB, schemeName, beginDate, endDate, "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByExitType(connectDB, schemeName, beginDate, endDate, "DIED"))), pFontHeader1);
                            table2.addCell(phrase);
//Absconders

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Absconders", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "MEDICAL", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "SURGICAL", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "OBS", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "EYE", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "ORTHO", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "ISOLATION", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "PSYCH", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndExitType(connectDB, schemeName, beginDate, endDate, "OTHER", "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByExitType(connectDB, schemeName, beginDate, endDate, "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByExitType(connectDB, schemeName, beginDate, endDate, "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.4", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL DISCHARGES, DEATHS, etc", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "MEDICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "SURGICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "OBS"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "EYE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "ORTHO"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "ISOLATION"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "PSYCH"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardType(connectDB, schemeName, beginDate, endDate, "OTHER"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByExitType(connectDB, schemeName, beginDate, endDate, "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByExitType(connectDB, schemeName, beginDate, endDate, "ABSCOND"))), pFontHeader1);
                            table2.addCell(phrase);
//Admissions

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.9", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Admissions", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "MEDICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "SURGICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "OBS"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "EYE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "ORTHO"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "ISOLATION"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "PSYCH"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissions(connectDB, schemeName, beginDate, endDate, "OTHER"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByAdmissionsALL(connectDB, schemeName, beginDate, endDate))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByExitTypeALL(connectDB, schemeName, beginDate, endDate))), pFontHeader1);
                            table2.addCell(phrase);

//Paroles
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.10", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Paroles", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "MEDICAL", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "SURGICAL", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "OBS", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "EYE", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "ORTHO", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "ISOLATION", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "PSYCH", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByWardTypeAndCategory(connectDB, schemeName, beginDate, endDate, "OTHER", "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByCategory(connectDB, schemeName, beginDate, endDate, "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByCategory(connectDB, schemeName, beginDate, endDate, "PAROLE"))), pFontHeader1);
                            table2.addCell(phrase);

//Occupied bed days - NHIF MEMBERS
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.11", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Occupied Bed Days - NHIF Members", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "MEDICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "SURGICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "OBS"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "EYE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "ORTHO"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "ISOLATION"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "PSYCH"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIF(connectDB, schemeName, beginDate, endDate, "OTHER"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNHIFALL(connectDB, schemeName, beginDate, endDate))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByDedDaysNHIF(connectDB, schemeName, beginDate, endDate))), pFontHeader1);
                            table2.addCell(phrase);

                            // obd non nhif members
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("B.1.11a", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Occupied Bed Days - NON NHIF Members", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "MEDICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "SURGICAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "OBS"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "EYE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "ORTHO"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "ISOLATION"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "PSYCH"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIF(connectDB, schemeName, beginDate, endDate, "OTHER"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByBedDaysNONNHIFALL(connectDB, schemeName, beginDate, endDate))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByDedDaysNONNHIF(connectDB, schemeName, beginDate, endDate))), pFontHeader1);
                            table2.addCell(phrase);

                            double patno = 0.00;
                            table2.getDefaultCell().setColspan(12);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            //Pharmacy
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("PHARMACY", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("Number of prescriptions", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("TOTAL COST (KES)", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("E.2 OPERATIONS", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("No. Booked", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("No. Operated", pFontHeader);
                            table2.addCell(phrase);
                            phrase = new Phrase("TOTAL COST (KES)", pFontHeader);
                            table2.addCell(phrase);
//                  
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Common Drugs", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByPharmacy(connectDB, schemeName, beginDate, endDate, "C"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByPharmacy(connectDB, schemeName, beginDate, endDate, "C"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("E.2.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            phrase = new Phrase("Minor Surgeries \n (excluding circumcision)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategoryBooked(connectDB, schemeName, beginDate, endDate, "MINSURG"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "MINSURG"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "MINSURG"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Antibiotics", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByPharmacy(connectDB, schemeName, beginDate, endDate, "A"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByPharmacy(connectDB, schemeName, beginDate, endDate, "A"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.2.1.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Emergencies", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategoryBooked(connectDB, schemeName, beginDate, endDate, "EMERGENCY"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "EMERGENCY"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "EMERGENCY"))), pFontHeader1);
                            table2.addCell(phrase);

                            //Special drugs
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("C.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Special Drugs", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByPharmacy(connectDB, schemeName, beginDate, endDate, "S"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByPharmacy(connectDB, schemeName, beginDate, endDate, "S"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.2.1.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Cold Cases", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategoryBooked(connectDB, schemeName, beginDate, endDate, "COLDCASE"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "COLDCASE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "COLDCASE"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("E.2.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Major Surgeries", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategoryBooked(connectDB, schemeName, beginDate, endDate, "MAJSURG"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "MAJSURG"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "MAJSURG"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(12);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D SPECIAL SERVICES (Includes both OUT-Patient and IN-Patients)", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(5);
                            phrase = new Phrase("F. FAREWELL HOME/MORTUARY", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Laboratory Services", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("NUMBER", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("TOTAL COST (KES)", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("FAREWELL HOME/MORTUARY SERVICES", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("NUMBER", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL COST (KES)", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Laboratory Routine tests", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "LABROUTINE"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "LABROUTINE"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Body days (Released within the month)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "MORTRELB"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "MORTRELB"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.1.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Laboratory Special tests", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "LABSPECIAL"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "LABSPECIAL"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Embalment", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "MORTEMBAL"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "MORTEMBAL"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.2", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Plain Xray without enhancement", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADPLAIN"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADPLAIN"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Post-mortem", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "MORTPOSTM"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "MORTPOSTM"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.2.1", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Enhancement Xrays with contrast media", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADENHCONT"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADENHCONT"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("F.4", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Unclaimed body days", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "UNCLAIMEDBODY"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "UNCLAIMEDBODY"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.3", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Ultrasound", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADUS"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADUS"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.4", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("MRI, CT-SCAN", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADCTSMRI"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "RADCTSMRI"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("REFERRALS", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.5", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Physiotherapy", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "PHYSIO"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "PHYSIO"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Referral Services", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("No. Using Ambulance Services", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase("Not using Ambulance Services", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Total Referred", pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase("Total Cost (KES)", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.6", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Occupational Therapy", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "OCCUT"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "OCCUT"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("G.1 County Community Units", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINCOMAMBU"))), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINCOMNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINCOMAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINCOMNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINCOMAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.7", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Orthopaedic treatment - private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHOPPVT"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHOPPVT"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("G.2 To this facility except from Community Units", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMAMBU"))), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFINNONCOMNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);


                            table2.getDefaultCell().setColspan(12);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.8", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Orthepaedic treatment - non private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHONPVT"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHONPVT"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("G.3 From this facility to facilities within the county", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTCOUNTYAMBU"))), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTCOUNTYNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTCOUNTYAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTCOUNTYNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTCOUNTYAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTCOUNTYNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);


                            table2.getDefaultCell().setColspan(12);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.9", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Orthepaedic technology items - private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHOPTECPVT"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHOPTECPVT"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("G.4 From this facility to facilities outside the county", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTNONCOUNTYAMBU"))), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTNONCOUNTYNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTNONCOUNTYAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTNONCOUNTYNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTNONCOUNTYAMBU") + com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByReferralsCategory(connectDB, schemeName, beginDate, endDate, "REFOUTNONCOUNTYNOAMBU"))), pFontHeader1);
                            table2.addCell(phrase);


                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("D.10", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Orthepaedic technology items - non private", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHOPTECNPVT"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "ORTHOPTECNPVT"))), pFontHeader1);
                            table2.addCell(phrase);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("H MEDICAL RECORDS ISSUED", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("NUMBER", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("TOTAL COST (KES)", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("H.1 New files/folders (including stationery)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                               phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "RECNFILE"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "RECNFILE"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("H.2 OUT-Patient Cards/Booklets", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "RECOPCARDS"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "RECOPCARDS"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("H.3 OPD Registration", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                                     phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCountByServiceCategory(connectDB, schemeName, beginDate, endDate, "OPREG"))), pFontHeader1);
                            table2.addCell(phrase);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(com.afrisoftech.lib.MonthlyWorkLoads.getWorkloadsCostByServiceCategory(connectDB, schemeName, beginDate, endDate, "OPREG"))), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(12);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("PART A", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase("PART B", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(6);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("GRAND TOTAL (KES)", pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(patno)), pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(12);
                            phrase = new Phrase("", pFontHeader1);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Name", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Signature", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("Date", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("Designation", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Prepared By:", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(4);
                            phrase = new Phrase("Checked By:", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(4);

                            phrase = new Phrase("Entered By:", pFontHeader);
                            table2.addCell(phrase);

                            table2.getDefaultCell().setColspan(3);

                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(2);

                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);
                            table2.getDefaultCell().setColspan(3);
                            phrase = new Phrase("", pFontHeader);
                            table2.addCell(phrase);

                            docPdf.add(table);
                            docPdf.add(table1);
                            docPdf.add(table2);
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

            docPdf.close();

            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }

}
