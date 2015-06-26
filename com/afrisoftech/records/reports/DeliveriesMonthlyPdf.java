//Author Francis Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a

//Date : 29082008

//import java.lang.*;
package com.afrisoftech.records.reports;

//import //java.awt.Desktop;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import org.jfree.xml.factory.objects.JavaBaseClassFactory;

public class DeliveriesMonthlyPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.util.Date endDate = null;
    String ks;
    int numberSeq = 0;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    ////java.awt.Desktop deskTop = Desktop.getDesktop();
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public DeliveriesMonthlyPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
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

            //  com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A3.rotate());

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));


                    String compName = null;
                    String distName = null;
                    String regiName = null;
                    String date = null;
                    try {

                        // java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency,district_branch,region from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                            distName = rset2.getObject(3).toString();
                            regiName = rset2.getObject(4).toString();
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);

                    //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                    //headerFoter.setRight(5);
                    //docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(17);

                        int headerwidths[] = {7, 10, 20, 7, 12, 7, 7, 12, 12, 10, 7, 7, 7, 7, 20, 12, 10};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(17);


                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            com.afrisoftech.lib.DateFormatter dateFormatter = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "MMMM");

                            java.lang.String monthString = dateFormatter.getDateString();

                            com.afrisoftech.lib.DateFormatter dateFormatters = new com.afrisoftech.lib.DateFormatter(dateFormat.parse(endDate.toLocaleString()), "yyyy");

                            java.lang.String yearString = dateFormatters.getDateString();
                            System.out.println("" + endDate1);

                            table.getDefaultCell().setColspan(17);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                            phrase = new Phrase("MONTHLY RETURNS ON DELIVERIES", pFontHeader2);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            phrase = new Phrase("HOSPITAL : " + compName, pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("DISTRICT : " + distName, pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(5);
                            phrase = new Phrase("REGION : " + regiName, pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("MONTH : " + monthString, pFontHeader);

                            table.addCell(phrase);


                            phrase = new Phrase("YEAR : " + yearString, pFontHeader);

                            table.addCell(phrase);

                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                        table.getDefaultCell().setColspan(10);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        phrase = new Phrase("", pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(4);
                        phrase = new Phrase("++ Outcome of Delivery", pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("", pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(10);
                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);

                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        phrase = new Phrase("", pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(2);
                        phrase = new Phrase("Single Birth", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Multiple Birth", pFontHeader1);
                        table.addCell(phrase);

                        table.getDefaultCell().setColspan(3);
                        phrase = new Phrase("", pFontHeader1);
                        table.addCell(phrase);



                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
                        phrase = new Phrase("No.", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Inpatient Number", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Address * \n (Town/Residence)", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Age", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Occupation", pFontHeader1);
                        table.addCell(phrase);
                        table.getDefaultCell().setRunDirection(0);
                        phrase = new Phrase("Parity", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Duration of Pregnancy (Weeks)", pFontHeader1);
                        table.addCell(phrase);
                        phrase = new Phrase("Date of Admission", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Date of Discharge", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("+ Type of Delivery", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("M", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("F", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("M", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("F", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Complications of Pregnancy", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("**Outcome of Admission", pFontHeader1);
                        table.addCell(phrase);

                        phrase = new Phrase("Cost of Treatment", pFontHeader1);
                        table.addCell(phrase);



                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //
                        double osBalance = 0.00;
                        try {

                            java.lang.Object[] listofAct = this.getListofActivities();

                            //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                            System.out.println(listofAct.length);

                            java.sql.Statement st = connectDB.createStatement();
                            java.sql.Statement st11 = connectDB.createStatement();
                            java.sql.Statement st22 = connectDB.createStatement();
                            java.sql.Statement st33 = connectDB.createStatement();

                            java.sql.Statement st31 = connectDB.createStatement();
                            for (int i = 0; i < listofAct.length; i++) {
                                String outComeSingle = "-";
                                String outComeTwins = "-";
                                String infantState = "-";
                                String babyGender = "-";
                                String infantState1 = "-";
                                String babyGender1 = "-";
                                String parity = "-";

                                int ages = 0;
                                java.sql.ResultSet rset = st11.executeQuery("SELECT patient_no,address||' '||residence,ROUND(pat_age),occupation,'','',date_admitted,discharge_date::date,'','','','','',diagnosis1,transaction_type,balance FROM hp_admission WHERE patient_no = '" + listofAct[i] + "' AND discharge_date::date BETWEEN '" + beginDate + "'::date AND '" + endDate + "'::date AND discharge = true AND diagnosed = true");
                                java.sql.ResultSet rset1 = st22.executeQuery("SELECT DISTINCT duration_of_preg,delivery_method,birth_complications FROM rh.post_natal_services WHERE mother_serial_no = '" + listofAct[i] + "' AND service_date::date BETWEEN '" + beginDate + "'::date AND '" + endDate + "'::date");
                                java.sql.ResultSet rset2 = st.executeQuery("SELECT DISTINCT state_of_infant_at_birth,baby_gender FROM rh.post_natal_services WHERE mother_serial_no = '" + listofAct[i] + "' AND service_date::date BETWEEN '" + beginDate + "'::date AND '" + endDate + "'::date AND type_of_birth ilike 'Single'");
                                java.sql.ResultSet rset3 = st33.executeQuery("SELECT DISTINCT state_of_infant_at_birth,baby_gender FROM rh.post_natal_services WHERE mother_serial_no = '" + listofAct[i] + "' AND service_date::date BETWEEN '" + beginDate + "'::date AND '" + endDate + "'::date AND type_of_birth ilike 'Twins'");
                                java.sql.ResultSet rset31 = st31.executeQuery("SELECT DISTINCT parity FROM rh.post_natal_services WHERE mother_serial_no = '" + listofAct[i] + "' AND service_date::date BETWEEN '" + beginDate + "'::date AND '" + endDate + "'::date");
                                while (rset31.next()) {
                                    parity = dbObject.getDBObject(rset31.getObject(1), "-");
                                }
                                while (rset2.next()) {
                                    infantState = rset2.getString(1);
                                    babyGender = rset2.getString(2);
                                    if (infantState.equalsIgnoreCase("Live Birth")) {
                                        outComeSingle = "A";
                                    } else {
                                        outComeSingle = "D";
                                    }
                                }

                                while (rset3.next()) {
                                    infantState1 = rset3.getString(1);
                                    babyGender1 = rset3.getString(2);
                                    if (infantState1.equalsIgnoreCase("Both Alive")) {
                                        outComeTwins = "AA";
                                    } else {
                                        if (infantState1.equalsIgnoreCase("Both Dead")) {
                                            outComeTwins = "DD";
                                        } else {
                                            if (infantState1.equalsIgnoreCase("One Dead")) {
                                                outComeTwins = "AD";
                                            }
                                        }
                                    }
                                }

                                while (rset.next()) {
                                    while (rset1.next()) {
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setColspan(1);
                                        numberSeq = numberSeq + 1;
                                        phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        ages = rset.getInt(3);
                                        if (ages < 1) {
                                            phrase = new Phrase("<1", pFontHeader1);

                                            table.addCell(phrase);
                                        } else {
                                            //     phrase = new Phrase(dbObject.getDBObject(rset.getObject(3), "-"), pFontHeader1);
                                            phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(ages)), pFontHeader1);

                                            table.addCell(phrase);
                                        }

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(4), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(parity, pFontHeader1);
                                        table.addCell(phrase);

                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(7), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(8), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        if (babyGender.startsWith("M")) {
                                            phrase = new Phrase(outComeSingle, pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase("", pFontHeader1);
                                            table.addCell(phrase);
                                        } else {
                                            phrase = new Phrase("", pFontHeader1);
                                            table.addCell(phrase);

                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            phrase = new Phrase(outComeSingle, pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                        if (babyGender.startsWith("M")) {
                                            phrase = new Phrase(outComeTwins, pFontHeader1);
                                            table.addCell(phrase);

                                            phrase = new Phrase("", pFontHeader1);
                                            table.addCell(phrase);
                                        } else {
                                            phrase = new Phrase("", pFontHeader1);
                                            table.addCell(phrase);

                                            phrase = new Phrase(outComeTwins, pFontHeader1);
                                            table.addCell(phrase);
                                        }
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(3), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(15), "-"), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(dbObject.getDBObject(rset.getObject(16), "-"), pFontHeader1);
                                        table.addCell(phrase);
                                    /*  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase(dbObject.getDBObject(rset.getObject(15), "-"), pFontHeader1);
                                    table.addCell(phrase);
                                     */
                                    }
                                }
                            }

                            table.getDefaultCell().setColspan(17);
                            phrase = new Phrase("   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("*    Address             ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("= This does not mean birth-place,unless the latter is the usual residence. It also excludes temporary residence of less than one month duration", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("+   Type of Delivery             ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("= N-normal; N.E-Normal with Episiotomy; F.E.-Ferceps with Episiotomy; M-Surgical Manipulation; C.S.-Caesarian Section; An-Any Other", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("++    Outcome of Delivery   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("= A-Alive; D-Foetal Death; AA-Both Alive; DD-Both Dead; AD-One Dead", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            phrase = new Phrase("   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(3);
                            phrase = new Phrase("**    Outcome of Admission   ", pFontHeader);
                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(12);
                            phrase = new Phrase("= Discharge, Transferred, Died, Abscond", pFontHeader);
                            table.addCell(phrase);

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

    public java.lang.Object[] getListofActivities() {

        java.lang.Object[] listofActivities = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT mother_serial_no FROM rh.post_natal_services WHERE service_date::date BETWEEN '" + beginDate + "' AND '" + endDate + "'");

            while (rSet1.next()) {
                listActVector.addElement(rSet1.getObject(1).toString());
            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities = listActVector.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities;
    }
}





