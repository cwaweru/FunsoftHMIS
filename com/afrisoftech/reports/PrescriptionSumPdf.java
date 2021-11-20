//Author Charles Waweru

//Made to test Java support for Threads.

//Revision : Ver 1.0a 

//import java.lang.*;
package com.afrisoftech.reports;

import static com.afrisoftech.reports.PrescriptionSumPerSexPdf.connectDB;
import java.awt.Point;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.awt.Desktop;

public class PrescriptionSumPdf implements java.lang.Runnable {

    java.awt.Desktop deskTop = Desktop.getDesktop();
    int numberSeq = 0;
    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    java.lang.String Categ = null;
    java.lang.String ReportType = null;
    com.afrisoftech.lib.DBObject dbObject;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PrescriptionSumPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate, java.lang.String categ, java.lang.String repotype) {

        dbObject = new com.afrisoftech.lib.DBObject();
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;
        Categ = categ;
        ReportType = repotype;
        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //		new GlTransactPdf().GlTransactPdf();
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
        java.sql.ResultSet rsetTotals1 = null;

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
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }


                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Prescription summary - Page: ", pFontHeader1), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();
                    double osBalance1 = 0.00;
                    double ipDiag = 0;
                    double opDiag = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);

                        int headerwidths[] = {6, 70, 7, 7};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(3);


                        // table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                       // if (ReportType.equalsIgnoreCase("Morbidity")) {
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                            table.getDefaultCell().setColspan(2);

                            Phrase phrase = new Phrase("Dispensation summary : " + Categ.toUpperCase(), com.lowagie.text.FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On : " + date, com.lowagie.text.FontFactory.getFont(FontFactory.HELVETICA, 7));

                            table.addCell(phrase);
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);

                                
                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                                System.out.println("" + endDate1);
                                //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                                //  table.addCell(phrase);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                table.getDefaultCell().setColspan(4);

                                phrase = new Phrase("Period : From " + dateFormat.format(endDate11) + " To " + dateFormat.format(endDate1), pFontHeader);

                                table.addCell(phrase);


                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }
                            //phrase = new Phrase("Name", pFontHeader);

                            //table.addCell(phrase);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table.getDefaultCell().setColspan(1);
                            phrase = new Phrase("RANK", pFontHeader);

                            table.addCell(phrase);
                            phrase = new Phrase("DRUG", pFontHeader);

                            table.addCell(phrase);

                            phrase = new Phrase("NO. OF CASES", pFontHeader);

                            table.addCell(phrase);
                            //  table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("% TOTAL", pFontHeader);

                            table.addCell(phrase);



                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            try {

                                java.sql.Statement st212 = connectDB.createStatement();



                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }


                            try {
                                java.lang.Object[] listofAct = this.getListofActivities1();
                                double osBalance = 0;
                                // java.lang.Object[] listofAct = this.getListofActivities();
                                java.lang.Object[] listofAct1 = this.getListofActivities1();

                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st211 = connectDB.createStatement();

                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st212 = connectDB.createStatement();
                                
                                String condition = "";
                                
                                



                                if (Categ == "Op") {
                                    condition = "AND patient_source ilike 'OP'";
                                }
                                if (Categ == "Ip") {
                                    condition = "AND patient_source ilike 'IP'";
                                }
                                    java.sql.ResultSet rset11 = st212.executeQuery("select COUNT(*)   from st_sub_stores where store_name ilike '%pharmacy%' and   (patient_source  ilike 'OP' OR patient_source  ilike 'IP' OR patient_source  ilike 'WI' ) \n" +
                                        "and   trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"'  "+condition);
                                    while (rset11.next()) {


                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                        phrase = new Phrase(dbObject.getDBObject(rset11.getObject(1), "-"), pFontHeader);
                                        osBalance1 = rset11.getDouble(1);
                                    }
                                    // java.sql.ResultSet rset11 = st212.executeQuery("SELECT count(disease) from hp_patient_diagnosis where date_discharged BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                                    java.sql.ResultSet rset1 = st2.executeQuery("select item, COUNT(*)   from st_sub_stores where  store_name ilike '%pharmacy%' and  (patient_source  ilike 'OP' OR patient_source  ilike 'IP' OR patient_source  ilike 'WI' ) \n" +
                                        "and   trans_date::date BETWEEN '"+beginDate+"' AND '"+endDate+"' "+condition+" GROUP BY 1 order by 2 desc");
                                    while (rset1.next()) {
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                        numberSeq = numberSeq + 1;

                                        phrase = new Phrase("" + numberSeq + "   ", pFontHeader);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(1), "-"), pFontHeader);

                                        table.addCell(phrase);
                                        table.getDefaultCell().setColspan(1);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                        phrase = new Phrase(dbObject.getDBObject(rset1.getObject(2), "-"), pFontHeader);
                                        osBalance = rset1.getDouble(2);
                                        // osBalance1 = osBalance + rset1.getDouble(2);
                                        table.addCell(phrase);
                                        //  phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset1.getString(2)),pFontHeader);
                                        //osBalance = osBalance + rset1.getDouble(2);
                                        //table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance * 100 / osBalance1)), pFontHeader);
                                        //   osBalance1 = osBalance1 + rset1.getDouble(5);
                                        table.addCell(phrase);


                                    }
                                    table.getDefaultCell().setColspan(2);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Total", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                    phrase = new Phrase(new com.afrisoftech.sys.Format2IntCurrency().Format2IntCurrency(java.lang.String.valueOf(osBalance1)), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                    phrase = new Phrase("100 ", pFontHeader);

                                    table.addCell(phrase);
                                


                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                        
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
    /*
    public java.lang.Object[] getListofActivities() {
    
    java.lang.Object[] listofActivities = null;
    
    java.util.Vector listActVector = new java.util.Vector(1,1);
    
    
    try {
    
    //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
    
    java.sql.Statement stmt1 = connectDB.createStatement();
    
    java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT sub_cat_code FROM st_stock_item order by sub_cat_code");
    
    while (rSet1.next()) {
    
    listActVector.addElement(rSet1.getObject(1).toString());
    
    }
    
    }catch (java.sql.SQLException sqlExec) {
    
    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());
    
    }
    
    listofActivities = listActVector.toArray();
    System.out.println("Done list of activities ...");
    return listofActivities;
    }*/

    
    
        public java.lang.Object[] getListofActivities1() {

        java.lang.Object[] listofActivities1 = null;

        java.util.Vector listActVector1 = new java.util.Vector(1, 1);


        try {

            java.sql.Statement stmt1 = connectDB.createStatement();
            java.sql.ResultSet rSet1=null;
           
             rSet1 = stmt1.executeQuery("SELECT DISTINCT INITCAP(description) FROM hp_pharmacy where date_prescribed BETWEEN '" + beginDate + "' AND '" + endDate + "'  group by description union SELECT initcap(service) FROM hp_patient_card where date BETWEEN '" + beginDate + "' AND '" + endDate + "' and main_service ilike '%pharmacy%' group by 1 ORDER BY 1 ");
           
              //   rSet1 = stmt1.executeQuery("SELECT DISTINCT service,COUNT(service) FROM hp_patient_card where date BETWEEN '" + beginDate + "' AND '" + endDate + "' and main_service ilike '%pharmacy%' and service not ilike '%Receipt%' GROUP BY 1 ORDER BY 2 DESC");
            
                
            while (rSet1.next()) {
                System.out.println(rSet1.getObject(1).toString());
                listActVector1.addElement(rSet1.getObject(1).toString());

            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }

    
    
    public java.lang.Object[] getListofActivities1e() {

        java.lang.Object[] listofActivities1 = null;

        java.util.Vector listActVector1 = new java.util.Vector(1, 1);


        try {

            //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

        //  java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT distinct disease from hp_pharmacy where (disease !='-' and disease is not null) and date_prescribed BETWEEN '"+beginDate+"' AND '"+endDate+"' order by disease");

        // while (rSet1.next()) {

        ///    listActVector1.addElement(rSet1.getObject(1).toString());

        // }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

        }

        listofActivities1 = listActVector1.toArray();
        System.out.println("Done list of activities ...");
        return listofActivities1;
    }
}





