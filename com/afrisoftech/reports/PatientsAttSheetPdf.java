//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import *;
//import pdf.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.pdf.PdfCell;
//import com.sun.xml.internal.ws.api.ResourceLoader;

////import //java.awt.Desktop;

public class PatientsAttSheetPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.lang.String payMode = null;
    // ////java.awt.Desktop deskTop = Desktop.getDesktop();
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    java.lang.String patCat = null;
    java.lang.String servicepnt = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.afrisoftech.lib.DBObject dbObject;
    Font pFontHeader = null; //FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    Font pFontHeader1 = null; //FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String schemesName = null;

    public void PatientsAttSheetPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String patCats, java.lang.String servicePoint, java.lang.String paymentMode, Object schemeName) {

        dbObject = new com.afrisoftech.lib.DBObject();

        connectDB = connDb;

        beginDate = begindate;

        endDate = endate;

        patCat = patCats;

        servicepnt = servicePoint;

        payMode = paymentMode;

        if (payMode.equalsIgnoreCase("Scheme")) {
            payMode = "Scheme";
        } else if (payMode.equalsIgnoreCase("Cash")) {
            payMode = "Cash";
        } else {
            payMode = "--ALL--";
        }

        if (schemeName != null) {
            schemesName = schemeName.toString();
        } else {
            schemesName = null;
        }

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");
//        
//        
//        com.itextpdf.text.pdf.BaseFont pFontHeaderOrig = null;
//        com.itextpdf.text.pdf.BaseFont pFontHeader1Orig = null;
//        com.itextpdf.text.pdf.BaseFont pFontHeader11Orig = null;
//        com.itextpdf.text.pdf.BaseFont pFontHeader12Orig = null;
//
//        com.itextpdf.text.Font pFontHeader = null;
//        com.itextpdf.text.Font pFontHeader1 = null;
//        com.itextpdf.text.Font pFontHeader11 = null;
//        com.itextpdf.text.Font pFontHeader12 = null;
//
//        try {
//            try {
//                pFontHeaderOrig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
//                pFontHeader1Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
//                pFontHeader11Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
//                pFontHeader12Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
//
//            } catch (com.itextpdf.text.DocumentException ex) {
//                ex.printStackTrace();
//            }
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        pFontHeader = new com.itextpdf.text.Font(pFontHeaderOrig, 10f);
//        pFontHeader1 = new com.itextpdf.text.Font(pFontHeader1Orig, 9f);
//        pFontHeader11 = new com.itextpdf.text.Font(pFontHeader11Orig, 10f);
//        pFontHeader12 = new com.itextpdf.text.Font(pFontHeader12Orig, 10f);
//        
//        System.out.println("Font pFontHeader ["+pFontHeader+"]");

    }

    public static void main(java.lang.String[] args) {
        //		new MemberListPdf().MemberListPdf();
    }

    public void run() {

        System.out.println("System has entered running mode");

        while (threadCheck) {

            System.out.println("O.K. see how we execute target program");

            try {
                this.generatePdf();
            } catch (DocumentException ex) {
                Logger.getLogger(PatientsAttSheetPdf.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                System.out.println("Right, let's wait for task to complete of fail");

                java.lang.Thread.currentThread().sleep(50);

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

    public void generatePdf() throws DocumentException {

        java.lang.Process wait_for_Pdf2Show;

        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Date dateStampPdf = cal.getTime();

        java.lang.String pdfDateStamp = dateStampPdf.toString();

        try {

            com.itextpdf.text.pdf.BaseFont pFontHeaderOrig = null;
            com.itextpdf.text.pdf.BaseFont pFontHeader1Orig = null;
            com.itextpdf.text.pdf.BaseFont pFontHeader11Orig = null;
            com.itextpdf.text.pdf.BaseFont pFontHeader12Orig = null;

            com.itextpdf.text.Font pFontHeader = null;
            com.itextpdf.text.Font pFontHeader1 = null;
            com.itextpdf.text.Font pFontHeader11 = null;
            com.itextpdf.text.Font pFontHeader12 = null;

         //  ResourceLoader loader = new ResourceLoader("/fonts/ARIALUNI.ttf");
           
           
            

                        pFontHeaderOrig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
                        pFontHeader1Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
                        pFontHeader11Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
                        pFontHeader12Orig = com.itextpdf.text.pdf.BaseFont.createFont("/fonts/ARIALUNI.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);

    

                pFontHeader = new com.itextpdf.text.Font(pFontHeaderOrig, 10f);
                pFontHeader1 = new com.itextpdf.text.Font(pFontHeader1Orig, 9f);
                pFontHeader11 = new com.itextpdf.text.Font(pFontHeader11Orig, 10f);
                pFontHeader12 = new com.itextpdf.text.Font(pFontHeader12Orig, 10f);

                System.out.println("Font pFontHeader [" + pFontHeader + "]");

                java.io.File tempFile = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

                tempFile.deleteOnExit();

                java.lang.Runtime rt = java.lang.Runtime.getRuntime();

                java.lang.String debitTotal = null;

                java.lang.String creditTotal = null;

                //Document docPdf = new Document();
               Document docPdf = new Document(PageSize.A4.rotate());

                try {

                    try {

                        PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                        String compName = null;
                        String date = null;
                        try {

                            // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
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
//                            HeaderFooter headerFoter = new HeaderFooter(new Phrase("" + compName + ""), false);// FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
//                            headerFoter.setAlignment(HeaderFooter.ALIGN_CENTER);
//
//                            //  HeaderFooter headerFoter = new HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
//                            headerFoter.setRight(5);
//                            docPdf.setHeader(headerFoter);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        Header footer = new com.itextpdf.text.Header("",Header.AUTHOR);
                        if (schemesName == null) {
                            footer = new com.itextpdf.text.Header("Patients List Pay MODE :" + payMode.toUpperCase() + " - Page: ", Header.CREATIONDATE);// FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));
                        } else {
                            footer = new Header("Patients List Pay MODE :" + payMode.toUpperCase() + " " + schemesName.toUpperCase() + " - Page: ", Header.CREATIONDATE);// FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));                      
                        }
                        docPdf.addHeader("PATIENT LISTING",Header.PRODUCER);

                        docPdf.open();

                        PdfPTable table = new PdfPTable(11);
                        int headerwidths[] = {7, 15, 25, 15, 8, 30, 15, 10, 10, 10, 10};
                        table.setWidths(headerwidths);
                        table.setHeaderRows(2);
                        table.setWidthPercentage((100));
                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        // table.getDefaultCell().setColspan(5);
                        Phrase phrase = new Phrase("", pFontHeader);
                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);
                            
                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());
                            
                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);
                            
                            //  table.addCell(phrase);
                            String paymentModes = "";
                            if (schemesName != null) {
                                paymentModes = payMode + " - " + schemesName;
                            }
                            table.getDefaultCell().setColspan(7);
                            if (servicepnt.equalsIgnoreCase("--ALL--")) {
                                if (patCat.equalsIgnoreCase("New")) {
                                    phrase = new Phrase("Patient List [New]: Pay MODE :" + paymentModes.toUpperCase() + "   Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);
                                    
                                    table.addCell(phrase);
                                } else {
                                    if (patCat.equalsIgnoreCase("Old")) {
                                        phrase = new Phrase("Patient List [Revisit]: Pay MODE :" + paymentModes.toUpperCase() + "  Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);
                                        
                                        table.addCell(phrase);
                                    } else {
                                        if (patCat.equalsIgnoreCase("--All--")) {
                                            phrase = new Phrase("Patient List [New & Revisit]: Pay MODE :" + paymentModes.toUpperCase() + "  Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1), pFontHeader);
                                            
                                            table.addCell(phrase);
                                        }
                                    }
                                }
                            } else {
                                
                                if (patCat.equalsIgnoreCase("New")) {
                                    
                                    phrase = new Phrase("Patient List [New]: Pay MODE :" + paymentModes.toUpperCase() + "  Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1) + "  at " + servicepnt, pFontHeader);
                                    
                                    table.addCell(phrase);
                                } else {
                                    if (patCat.equalsIgnoreCase("Old")) {
                                        phrase = new Phrase("Patient List [Revisit]: Pay MODE :" + paymentModes.toUpperCase() + "  Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1) + "  at " + servicepnt, pFontHeader);
                                        
                                        table.addCell(phrase);
                                    } else {
                                        if (patCat.equalsIgnoreCase("--All--")) {
                                            phrase = new Phrase("Patient List [New & Revisit]: Pay MODE :" + paymentModes.toUpperCase() + "  Period : " + dateFormat.format(endDate11) + " - " + dateFormat.format(endDate1) + "  at " + servicepnt, pFontHeader);
                                            
                                            table.addCell(phrase);
                                        }
                                    }
                                }

                            }
                            
                            table.getDefaultCell().setColspan(4);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                            
                            phrase = new Phrase("Printed On  :" + date, pFontHeader);
                            
                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());
                            
                        }
                        // Phrase phrase = new Phrase("Patients List As At:" +endDate, pFontHeader);
                        //table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                        table.getDefaultCell().setColspan(1);
                        //   table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        phrase = new Phrase("No.", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Patient No.", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Name", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Age", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(1);
                        phrase = new Phrase("Gender", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Diagnosis", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Residence", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Date", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Time Registered", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Time Seen", pFontHeader);
                        table.addCell(phrase);
                        phrase = new Phrase("Registered By", pFontHeader);
                        table.addCell(phrase);
                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                        // table.addCell("Amount KShs.");
                  //      table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        try {
                            java.lang.Object[] listofAct = this.getListofActivities();
                            
                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
                            System.out.println(listofAct.length);
                            
                            //  java.sql.Statement pSet1 = connectDB.createStatement();
                            //  java.sql.Statement st = connectDB.createStatement();
                            //java.sql.ResultSet rsetTotals = st.executeQuery("select sum((ph.quantity * st.transfer_price)::numeric(10,2)), sum(amount)::numeric(10,2),sum(amount - ph.quantity * st.transfer_price)::numeric(10,2) from hp_pharmacy ph,st_stock_prices st WHERE (st.department = 'PHA' OR st.department = 'IP Pharmacy') AND ph.date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' AND ph.description = st.product");
                            java.sql.Statement st11 = connectDB.createStatement();
                            int cash = 0;
                            int scheme = 0;
                            int noSeq = 0;
                            
                            java.sql.PreparedStatement st1 = null;
                            java.sql.PreparedStatement st = null;
                            java.sql.PreparedStatement st2 = null;
                            java.sql.ResultSet rset = null;
                            java.sql.ResultSet rset1 = null;
                            java.sql.ResultSet rset2 = null;
                            // if (patCat.equalsIgnoreCase("All")) {
                            // rset1 = st11.executeQuery("select count(distinct(patient_no)) from hp_patient_visit WHERE date BETWEEN '" + beginDate + "' AND '" + endDate + "'");
                            //java.sql.Statement st = connectDB.createStatement();
                            
                            double ages = 0;
                            System.out.println("THIS IS THE LENGTH  " + listofAct.length);
                            System.out.println("Paymode  [" + payMode + "]");
                            for (int i = 0; i < listofAct.length; i++) {
                                String coTime = "-";
                                String diagnosis = "-";
                                System.out.println("item" + listofAct[i]);
                                if (payMode == "--ALL--") {
                                    if (patCat.equalsIgnoreCase("--ALL--")) {
                                        st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE  pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                    } else {
                                        if (patCat.equalsIgnoreCase("New")) {
                                            st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'New' ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                        } else {
                                            if (patCat.equalsIgnoreCase("Old")) {
                                                st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'Old'  ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                            }
                                        }
                                    }
                                } else if (payMode == "Scheme") {
                                    if (schemesName == "--ALL--") {
                                        if (patCat.equalsIgnoreCase("--ALL--")) {
                                            st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.payment = 'Scheme' AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                        } else {
                                            if (patCat.equalsIgnoreCase("New")) {
                                                st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE  pb.payment = 'Scheme' AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'New' ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                            } else {
                                                if (patCat.equalsIgnoreCase("Old")) {
                                                    st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE  pb.payment = 'Scheme' AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'Old'  ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                                }
                                            }
                                            
                                        }
                                    } else {
                                        // Fetch for particular scheme
                                        if (patCat.equalsIgnoreCase("--ALL--")) {
                                            st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date, hp.scheme FROM hp_patient_register pr,hp_patient_visit pb, credit_acc_slip hp WHERE pb.payment = 'Scheme' AND pb.date::date = hp.date::date AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? AND UPPER(hp.scheme) = '" + schemesName.toUpperCase() + "' and pb.patient_no = pr.patient_no AND pb.patient_no = hp.patient_no ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                        } else {
                                            if (patCat.equalsIgnoreCase("New")) {
                                                st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date, hp.scheme FROM hp_patient_register pr,hp_patient_visit pb, credit_acc_slip hp WHERE  pb.payment = 'Scheme' AND pb.date::date = hp.date::date AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? AND UPPER(hp.scheme) = '" + schemesName.toUpperCase() + "'  and pb.patient_no = pr.patient_no AND pb.patient_no = hp.patient_no  AND pb.comments ilike 'New' ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                            } else {
                                                if (patCat.equalsIgnoreCase("Old")) {
                                                    st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date, hp.scheme FROM hp_patient_register pr,hp_patient_visit pb, credit_acc_slip hp WHERE  pb.payment = 'Scheme' AND pb.date::date = hp.date::date AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? AND UPPER(hp.scheme) = '" + schemesName.toUpperCase() + "'  and pb.patient_no = pr.patient_no AND pb.patient_no = hp.patient_no  AND pb.comments ilike 'Old'  ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                                }
                                            }
                                            
                                        }
                                    }
                                } else {
                                    if (patCat.equalsIgnoreCase("--ALL--")) {
                                        st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE pb.payment != 'Scheme' AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                    } else {
                                        if (patCat.equalsIgnoreCase("New")) {
                                            st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE  pb.payment != 'Scheme' AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'New' ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                        } else {
                                            if (patCat.equalsIgnoreCase("Old")) {
                                                st1 = connectDB.prepareStatement("SELECT DISTINCT pb.patient_no,initcap(pb.name),funsoft_patient_age(pr.year_of_birth::date), pb.gender,initcap(pb.services),initcap(pr.residence),pr.date::date,pb.date::date,pb.input_date::TIME(0),INITCAP(pb.user_name), pb.input_date FROM hp_patient_register pr,hp_patient_visit pb WHERE  pb.payment != 'Scheme' AND pb.date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? and pb.patient_no = pr.patient_no AND pb.comments ilike 'Old'  ORDER BY pb.input_date, pb.input_date::TIME(0) limit 1");
                                            }
                                        }
                                    }
                                }
                                
                                st = connectDB.prepareStatement("SELECT INITCAP(disease) AS disease FROM hp_patient_diagnosis pr WHERE pr.date_discharged::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ?");
                                st2 = connectDB.prepareStatement("SELECT curr_date::TIME(0) FROM pb_doctors_request pr WHERE pr.curr_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "' and pr.patient_no = ? ORDER BY 1 ASC LIMIT 1");
                                
                                st1.setString(1, listofAct[i].toString());
                                rset = st1.executeQuery();
                                
                                st.setString(1, listofAct[i].toString());
                                rset1 = st.executeQuery();
                                
                                st2.setString(1, listofAct[i].toString());
                                rset2 = st2.executeQuery();
                                
                                while (rset1.next()) {
                                    
                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    diagnosis = dbObject.getDBObject(rset1.getObject(1), "-");
                                    //      table.addCell(phrase);
                                }
                                
                                while (rset2.next()) {
                                    
                                    // table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    coTime = dbObject.getDBObject(rset2.getObject(1), "-");
                                    //      table.addCell(phrase);
                                }
                                while (rset.next()) {
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    
                                    table.getDefaultCell().setColspan(1);
                                    noSeq = noSeq + 1;
                                    phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontHeader1);
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                    
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//                                    ages = rset.getString(3);
//                                    if (ages < 1) {
//                                        phrase = new Phrase("<1", pFontHeader1);
//
//                                        table.addCell(phrase);
//                                    } else {
//                                        //     phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
//                                        phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(ages)), pFontHeader1);
//
//                                        table.addCell(phrase);
//                                    }
phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
table.addCell(phrase);

table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
table.getDefaultCell().setColspan(1);

table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);

table.addCell(phrase);

table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
//  phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader1);
phrase = new Phrase(diagnosis, pFontHeader1);

table.addCell(phrase);

table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
phrase = new Phrase(dbObject.getDBObject(rset.getObject(6), "-"), pFontHeader1);

table.addCell(phrase);
phrase = new Phrase(dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);

table.addCell(phrase);
phrase = new Phrase(dbObject.getDBObject(rset.getObject(9), "-"), pFontHeader1);

table.addCell(phrase);
phrase = new Phrase(coTime, pFontHeader1);

table.addCell(phrase);

phrase = new Phrase(dbObject.getDBObject(rset.getObject(10), "-"), pFontHeader1);

table.addCell(phrase);

                                }
                            }
                            table.getDefaultCell().setColspan(10);
                            
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase(" ", pFontHeader1);
                            table.addCell(phrase);
                            
                            docPdf.add(table);
                            
                        } catch (java.sql.SQLException SqlExec) {
                            
                            SqlExec.printStackTrace();
                            
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());
                            
                        }
                        //
                        // }

                    } catch (java.io.FileNotFoundException fnfExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                    }
                } catch (DocumentException lwDocexec) {

                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

                }

                docPdf.close();
                docPdf.close();
                com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

                //com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
            } catch (java.io.IOException IOexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

            }

        }
        ////*********THE CHANGE DONE CASTED THE input_date::date
    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);

        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1 = null;
            if (payMode == "--ALL--") {
                if (servicepnt.equalsIgnoreCase("--ALL--")) {
                    if (patCat.equalsIgnoreCase("--ALL--")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  order by 2 ASC");
                    } else {
                        if (patCat.equalsIgnoreCase("New")) {
                            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE  input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'New' order by 2 ASC");
                        } else {
                            if (patCat.equalsIgnoreCase("Old")) {
                                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'Old' order by 2 ASC");
                            }
                        }
                    }
                } else {
                    if (patCat.equalsIgnoreCase("--ALL--")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                    } else {
                        if (patCat.equalsIgnoreCase("New")) {
                            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'New' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                        } else {
                            if (patCat.equalsIgnoreCase("Old")) {
                                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'Old' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                            }
                        }
                    }
                }
            } else if (payMode == "Scheme") {
                if (servicepnt.equalsIgnoreCase("--ALL--")) {
                    if (patCat.equalsIgnoreCase("--ALL--")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment = '" + payMode + "' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  order by 2 ASC");
                    } else {
                        if (patCat.equalsIgnoreCase("New")) {
                            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment = '" + payMode + "' AND  input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'New' order by 2 ASC");
                        } else {
                            if (patCat.equalsIgnoreCase("Old")) {
                                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment = '" + payMode + "' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'Old' order by 2 ASC");
                            }
                        }
                    }
                } else {
                    if (patCat.equalsIgnoreCase("--ALL--")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment = '" + payMode + "' AND  input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                    } else {
                        if (patCat.equalsIgnoreCase("New")) {
                            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment = '" + payMode + "' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'New' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                        } else {
                            if (patCat.equalsIgnoreCase("Old")) {
                                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment = '" + payMode + "' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'Old' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                            }
                        }
                    }
                }
            } else {
                if (servicepnt.equalsIgnoreCase("--ALL--")) {
                    if (patCat.equalsIgnoreCase("--ALL--")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment != 'Scheme' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  order by 2 ASC");
                    } else {
                        if (patCat.equalsIgnoreCase("New")) {
                            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment != 'Scheme' AND  input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'New' order by 2 ASC");
                        } else {
                            if (patCat.equalsIgnoreCase("Old")) {
                                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment != 'Scheme' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'Old' order by 2 ASC");
                            }
                        }
                    }
                } else {
                    if (patCat.equalsIgnoreCase("--ALL--")) {
                        rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit WHERE payment != 'Scheme' AND input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "' and upper(clinic) = '" + servicepnt.toUpperCase() + "' order by 2 ASC");
                    } else {
                        if (patCat.equalsIgnoreCase("New")) {
                            rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit payment != 'Scheme' AND WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'New' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                        } else {
                            if (patCat.equalsIgnoreCase("Old")) {
                                rSet1 = stmt1.executeQuery("SELECT DISTINCT patient_no,input_date FROM hp_patient_visit payment != 'Scheme' AND WHERE input_date::DATE BETWEEN '" + beginDate + "' AND '" + endDate + "'  AND comments = 'Old' and upper(clinic)='" + servicepnt.toUpperCase() + "' order by 2 ASC");
                            }
                        }
                    }
                }
            }
            while (rSet1.next()) {
                //if (rSet1.getFloat(1) > 0){
                listActVector.addElement(rSet1.getObject(1).toString());
                //}

                System.out.println("description" + rSet1.getObject(1).toString());
            }
        } catch (java.sql.SQLException sqlExec) {

            sqlExec.printStackTrace();

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}
