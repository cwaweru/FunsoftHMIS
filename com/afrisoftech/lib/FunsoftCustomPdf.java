///Author @author SaQlever
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.lib;

import com.afrisoftech.nursing.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class FunsoftCustomPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    java.lang.String MName = null;
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
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD);
    com.lowagie.text.Font pFontHeadert = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    java.lang.String visitId;
    java.lang.String pdfheader;
    java.util.HashMap<String, java.util.ArrayList> datapdf = new HashMap();
    static java.util.ArrayList columnVector;

    static java.util.ArrayList dataViewVector;
    static java.util.ArrayList childVector;
    static java.util.ArrayList sizeVector;

    public void FunsoftCustomPdf(java.sql.Connection connDb, String header, java.util.HashMap<String, java.util.ArrayList> data) {
        dbObject = new com.afrisoftech.lib.DBObject();
        datapdf.putAll(data);
        pdfheader = header;
        connectDB = connDb;
       // beginDate = begindate;

        //endDate = endate;
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

            this.generatePdf();

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

            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String date = null;

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase(" - Page: "), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);

                    docPdf.open();
                    try {

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
                                headerphrase = new Phrase("\n\n" + rset3.getObject(1).toString(), pFontHeadert);
                                headertable.addCell(headerphrase);
                            }
                            docPdf.add(headertable);
                        } catch (Exception sy) {
                            sy.printStackTrace();
                        }

                        /**
                         * key 1 header key2 no of columns/columns key 3 column
                         * span key 4 data
                         *
                         */
                        columnVector = new java.util.ArrayList<>();
                        sizeVector = new java.util.ArrayList<>();
                        dataViewVector = new java.util.ArrayList<>();
                        String identifier = "";
                        if (datapdf.containsKey("multi")) {
                            identifier = "query";
                        }
                        System.out.println("\n\n\n\n\n\n the size of the map is " + datapdf.size());

                        for (int t = 0; t < datapdf.size(); t++) {
                            System.out.println("the firsrt key is " + datapdf.keySet());
                        }

                        for (Map.Entry<String, ArrayList> entry : datapdf.entrySet()) {
                            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                        }

                        switch (identifier) {
                            case "query": {
                                Iterator it = datapdf.entrySet().iterator();
                                it.hasNext();

                                for (int d = 0; d < datapdf.size() - 1; d++) {
                                    columnVector = datapdf.get("columns:-" + d);
                                    sizeVector = datapdf.get("columnspan:-" + d);
                                    dataViewVector = datapdf.get("data:-" + d);
                                    for (int i = 0; i < columnVector.size(); i++) {
                                        System.out.print("the column vector is" + columnVector.get(i));
                                    }
                                    System.out.println("\n\n\n\n\n\n");
                                    for (int i = 0; i < sizeVector.size(); i++) {
                                        System.out.print("the size vector is" + sizeVector.get(i));
                                    }
                                    System.out.println("\n\n\n\n\n\n");
                                    for (int i = 0; i < dataViewVector.size(); i++) {
                                        System.out.print("the dataView vector is" + dataViewVector.get(i));
                                    }
                                    System.out.println("\n\n\n\n\n\n");
                                    com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(sizeVector.size());
                                    int totalColspan = 0;
                                    int headerwidths[] = new int[sizeVector.size()];

                                    for (int i = 0; i < sizeVector.size(); i++) {
                                        totalColspan = Integer.valueOf(String.valueOf(sizeVector.get(i)).trim());
                                        headerwidths[i] = Integer.valueOf(String.valueOf(sizeVector.get(i)).trim());
                                    }

                                    table.setWidths(headerwidths);
                                    table.setWidthPercentage((100));
                                    //  table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                                    Phrase phrase = new Phrase();

                                    try {

//                            table.getDefaultCell().setColspan(sizeVector.size());
//                            phrase = new Phrase(" ", pFontHeader11);
//                            table.addCell(phrase);
                                        table.getDefaultCell().setColspan(sizeVector.size());
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase(pdfheader, pFontHeader11);
                                        table.addCell(phrase);
                                        ////writing down the columns 
                                        for (int k = 0; k < columnVector.size(); k++) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_JUSTIFIED);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(String.valueOf(columnVector.get(k)), pFontHeader1);
                                            table.addCell(phrase);
                                        }

                                        /////populating the data
                                        for (int y = 0; y < dataViewVector.size(); y++) {
                                            childVector = (ArrayList) dataViewVector.get(y);
                                            for (int k = 0; k < childVector.size(); k++) {
                                                table.getDefaultCell().setColspan(1);
                                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                                table.getDefaultCell().setBorderColor(Color.BLACK);
                                                table.getDefaultCell().setBorder(Rectangle.BOX);
                                                phrase = new Phrase(String.valueOf(childVector.get(k)), pFontHeader);
                                                table.addCell(phrase);
                                            }
                                        }

                                        table.getDefaultCell().setColspan(sizeVector.size());
                                        phrase = new Phrase(" ", pFontHeader);
                                        table.addCell(phrase);

                                    } catch (Exception SqlExec) {
                                        SqlExec.printStackTrace();
                                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                                    }
                                    docPdf.add(table);
                                    it.remove();

                                }//iteration
                            }
                            ///end of multiple query
                            break;
                            case "": {
                                columnVector = datapdf.get("columns");
                                sizeVector = datapdf.get("columnspan");
                                dataViewVector = datapdf.get("data");
                                com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(sizeVector.size());
                                int totalColspan = 0;
                                int headerwidths[] = new int[sizeVector.size()];

                                for (int i = 0; i < sizeVector.size(); i++) {
                                    totalColspan = Integer.valueOf(String.valueOf(sizeVector.get(i)).trim());
                                    headerwidths[i] = Integer.valueOf(String.valueOf(sizeVector.get(i)).trim());
                                }

                                table.setWidths(headerwidths);
                                table.setWidthPercentage((100));
                                //  table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                                Phrase phrase = new Phrase();

                                try {

//                            table.getDefaultCell().setColspan(sizeVector.size());
//                            phrase = new Phrase(" ", pFontHeader11);
//                            table.addCell(phrase);
                                    table.getDefaultCell().setColspan(sizeVector.size());
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    table.getDefaultCell().setBorderColor(Color.BLACK);
                                    table.getDefaultCell().setBorder(Rectangle.BOX);
                                    phrase = new Phrase(pdfheader, pFontHeader11);
                                    table.addCell(phrase);
                                    ////writing down the columns 
                                    for (int k = 0; k < columnVector.size(); k++) {
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_JUSTIFIED);
                                        table.getDefaultCell().setBorderColor(Color.BLACK);
                                        table.getDefaultCell().setBorder(Rectangle.BOX);
                                        phrase = new Phrase(String.valueOf(columnVector.get(k)), pFontHeader1);
                                        table.addCell(phrase);
                                    }

                                    /////populating the data
                                    for (int y = 0; y < dataViewVector.size(); y++) {
                                        childVector = (ArrayList) dataViewVector.get(y);
                                        for (int k = 0; k < childVector.size(); k++) {
                                            table.getDefaultCell().setColspan(1);
                                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                            table.getDefaultCell().setBorderColor(Color.BLACK);
                                            table.getDefaultCell().setBorder(Rectangle.BOX);
                                            phrase = new Phrase(String.valueOf(childVector.get(k)), pFontHeader);
                                            table.addCell(phrase);
                                        }
                                    }

                                } catch (Exception SqlExec) {
                                    SqlExec.printStackTrace();
                                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                                }
                                docPdf.add(table);
                            }
                            break;
                        }

                    } catch (com.lowagie.text.BadElementException BadElExec) {
                        BadElExec.printStackTrace();
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
