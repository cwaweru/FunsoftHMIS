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

public class DailyRevenueRepPdf implements java.lang.Runnable {

    java.util.Date beginDate = null;
    java.util.Date endDate = null;
    String ks;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void DailyRevenueRepPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {

        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;

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



                    double osBalance1 = 0;



                    String compName = null;
                    String date = null;
                    try {


                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name,rep_currency from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();
                            ks = rset2.getString(2);
                        }
                        while (rset4.next()) {
                            date = rset4.getObject(1).toString();
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setRight(5);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }


                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Daily Rev. - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();

                    int noSeq = 0;
                    try {


                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);

                        int headerwidths[] = {7, 20, 40, 15, 20};

                        table.setWidths(headerwidths);

                        table.setWidthPercentage((100));

                        table.setHeaderRows(2);


                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                        table.getDefaultCell().setColspan(5);
                        Phrase phrase = new Phrase("", pFontHeader);


                        try {
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                            java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());
                            java.util.Date endDate11 = dateFormat.parse(beginDate.toLocaleString());//dateInstance.toLocaleString());

                            System.out.println("" + endDate1);
                            //  phrase = new Phrase(bank +" Report: " +dateFormat.format(formattedDate), pFontHeader);

                            //  table.addCell(phrase);
                            table.getDefaultCell().setColspan(3);

                            phrase = new Phrase("Daily Revenue Breakdown:  Period : " + dateFormat.format(endDate11) + " ------ " + dateFormat.format(endDate1), pFontHeader);

                            table.addCell(phrase);
                            table.getDefaultCell().setColspan(2);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase("Printed On  :" + date, pFontHeader);

                            table.addCell(phrase);
                        } catch (java.text.ParseException psExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                        }

                        table.getDefaultCell().setColspan(1);

                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);


                        phrase = new Phrase("#", pFontHeader);

                        table.addCell(phrase);
                        
                        phrase = new Phrase("Revenue GL/Code", pFontHeader);

                        table.addCell(phrase);
                        
                        phrase = new Phrase("Description", pFontHeader);

                        table.addCell(phrase);


                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                        phrase = new Phrase("Amount", pFontHeader);

                        table.addCell(phrase);
                        phrase = new Phrase("Running Amount (" +ks+ ")", pFontHeader);
                        table.addCell(phrase);



                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                        try {



                            java.sql.Statement st = connectDB.createStatement();

                            java.sql.Statement st2 = connectDB.createStatement();

                            java.sql.Statement st3 = connectDB.createStatement();


                            // for (int i = 0; i < listofAct.length; i++) {

                            System.out.println("Statements Created ...");
//                            java.sql.ResultSet rset = st.executeQuery("select pb.code,initcap(pb.activity),sum(ac.credit-ac.debit) from ac_ledger ac,pb_activity pb where pb.code = ac.activity_code and  (pb.activity_category ilike 'i%' or pb.activity_category ilike 'cf%') and ac.date BETWEEN '" + beginDate + "' AND '" + endDate + "' AND ac.activity_code ilike '6%'  group by pb.code,pb.activity order by pb.activity");
                            java.sql.ResultSet rset = st.executeQuery("select pb.code,initcap(pb.activity),sum(ac.credit-ac.debit) from ac_ledger ac,pb_activity pb where pb.code = ac.activity_code and  (pb.activity_category ilike 'i%' or pb.activity_category ilike 'cf%') and ac.date BETWEEN '" + beginDate + "' AND '" + endDate + "' group by pb.code,pb.activity order by pb.activity");
                            //  java.sql.ResultSet rsetTotals = st.executeQuery("select initcap(activity) from pb_activity where code ilike '"+listofAct[i]+"'");// tn,debit_note db WHERE tn.policy_no != '' and tn.policy_no = db.policy_no GROUP BY tn.policy_no,db.policy_class");
                            System.out.println("Select A done ...");
                            //  java.sql.ResultSet rset = st2.executeQuery("SELECT SUM(credit-debit) from ac_ledger where activity_code ilike '"+listofAct[i].toString()+"' AND date BETWEEN '"+beginDate+"' AND '"+endDate+"'");
                            System.out.println("Select B done ...");

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            table.getDefaultCell().setColspan(5);
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.addCell("  ");

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);

                            while (rset.next()) {

                                noSeq = noSeq + 1;
                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(java.lang.String.valueOf(noSeq), pFontNum);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(1).toString(), pFontHeader);

                                table.addCell(phrase);
                                table.getDefaultCell().setColspan(1);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase(rset.getObject(2).toString(), pFontNum);

                                table.addCell(phrase);


                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset.getString(3)), pFontNum);
                                osBalance1 = osBalance1 + rset.getDouble(3);

                                table.addCell(phrase);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader);

                                table.addCell(phrase);

                                //}

                            }

                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.TOP);



                            table.getDefaultCell().setColspan(4);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Gross Total", pFontHeader);

                            table.addCell(phrase);

                            table.getDefaultCell().setColspan(1);

                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                            phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(osBalance1)), pFontHeader);

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

            docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }
    /*  public java.lang.Object[] getListofActivities() {
   
     java.lang.Object[] listofActivities = null;
   
     java.util.Vector listActVector = new java.util.Vector(1,1);
   
   
     /*      try {
   
     //    java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");
   
     java.sql.Statement stmt1 = connectDB.createStatement();
   
     // java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_service FROM revenue_breakdown_view where date BETWEEN '"+beginDate+"' AND '"+endDate+"' order by main_service");
     java.sql.ResultSet rSet1 = stmt1.executeQuery("select DISTINCT code from pb_activity where (activity_category ilike 'I' or activity_category ilike 'cf%') ORDER BY code");
     //SELECT DISTINCT description FROM ac_ledger where date BETWEEN '"+beginDate+"' AND '"+endDate+"' and transaction_type = 'Revenue' order by description");
   
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
}
