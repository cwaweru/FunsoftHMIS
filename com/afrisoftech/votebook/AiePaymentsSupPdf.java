//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.votebook;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class AiePaymentsSupPdf implements java.lang.Runnable {

    public static java.sql.Connection connectDB = null;
    double assets = 0.00;
    double liabilities = 0.00;
    double equity = 0.00;
    double profitloss = 0.00;
    double totalLiabEquity = 0.00;
    double subTotal = 0.00;
    java.util.Date beginDate = null;
    java.lang.String dateLastyear = null;
    java.util.Date endDate = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void AiePaymentsSupPdf(java.sql.Connection connDb, java.util.Date begindate, java.util.Date endate) {
        connectDB = connDb;
        beginDate = begindate;
        endDate = endate;

        threadSample = new java.lang.Thread(this, "SampleThread");

        System.out.println("threadSample created");

        threadSample.start();

        System.out.println("threadSample fired");

    }

    public static void main(java.lang.String[] args) {
        //	new BalanceSheetPdf().BalanceSheetPdf();
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

        java.util.Calendar calLast = java.util.Calendar.getInstance();

        calLast.roll(java.util.Calendar.YEAR, -1);

        dateLastyear = calLast.getTime().toString();

        System.out.println(this.dateLastyear);

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

            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());
            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));




                    String compName = null;
                    String date = null;
                    try {

                        //    java.sql.Connection conDb = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                        java.sql.Statement st3 = connectDB.createStatement();
                        java.sql.Statement st4 = connectDB.createStatement();

                        java.sql.ResultSet rset2 = st3.executeQuery("SELECT hospital_name from pb_hospitalprofile");
                        java.sql.ResultSet rset4 = st4.executeQuery("SELECT date('now') as Date");
                        while (rset2.next()) {
                            compName = rset2.getObject(1).toString();

                            while (rset4.next()) {
                                date = rset4.getObject(1).toString();
                            }
                        }
                        com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase("" + compName, pFontHeader), false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));

                        //  com.lowagie.text.HeaderFooter headerFoter = new com.lowagie.text.HeaderFooter(new Phrase(""+compName+""),false);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 14, Font.BOLDITALIC,java.awt.Color.blue)));
                        headerFoter.setAlignment(com.lowagie.text.HeaderFooter.ALIGN_CENTER);
                        docPdf.setHeader(headerFoter);

                    } catch (java.sql.SQLException SqlExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                    }

                    com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Allocations - Page: ", pFontHeader), true);// FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12, Font.BOLDITALIC,java.awt.Color.blue));

                    docPdf.setFooter(footer);


                    docPdf.open();


                    try {



                        java.lang.Object[] listofAct2 = this.getListofActivities2();
                        for (int i = 0; i < listofAct2.length; i++) {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);

                            int headerwidths[] = {10, 30, 50, 30, 15,};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));
                            Phrase phrase = new Phrase("");


                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                            table.getDefaultCell().setColspan(3);
                            try {
                                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);//MEDIUM);


                                java.util.Date endDate1 = dateFormat.parse(endDate.toLocaleString());//dateInstance.toLocaleString());

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("PAYMENTS", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(2);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Printed on : " + dateFormat.format(endDate1), pFontHeader1);

                                table.addCell(phrase);
                            } catch (java.text.ParseException psExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), psExec.getMessage());

                            }



                            //    table.getDefaultCell().setBackgroundColor(java.awt.Color.LIGHT_GRAY);

                            // table.addCell("");

                            /*  table.addCell("");
                             table.getDefaultCell().setColspan(2);
                             table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                             table.addCell("Amount KShs.");
                             */
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            try {

                                //java.lang.Object[] listofAct = this.getListofActivities();

                                //java.lang.Object[] listofAct1 = this.getListofActivities1();

                                //    java.sql.Connection conDb1 = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/sako","postgres","pilsiner");

                                // System.out.println(listofAct.length);


                                totalLiabEquity = 0;

                                java.sql.Statement st10 = connectDB.createStatement();

                                java.sql.Statement st8 = connectDB.createStatement();

                                java.sql.Statement st11 = connectDB.createStatement();

                                //   java.sql.ResultSet rset10 = st10.executeQuery("select * from equity_view");


                                java.sql.ResultSet rset11 = st11.executeQuery("SELECT DISTINCT ai.vote,v.classname FROM ac_aie_commitment ai, "
                                        + "vb_class v  WHERE ai.v_class = '" + listofAct2[i].toString() + "' AND ai.v_class = v.class AND ai.voucher_no IS NOT NULL");

                                java.sql.Statement st15 = connectDB.createStatement();
                                java.sql.ResultSet rset15 = st15.executeQuery("SELECT at.v_class||'-'||at.vote||'-'||at.subvote"
                                        + "||'-'||at.subhead||'-'||at.subitem||'-'||at.donorcode||'-'||at.donoritem,at.subitem,SUM(at.paid_amount) AS amt,"
                                        + "INITCAP(sb.subitemname) as sub,at.voucher_no,INITCAP(at.supplier) FROM ac_aie_commitment at, subitems sb WHERE "
                                        + "at.v_class = '" + listofAct2[i].toString() + "' AND at.subitem = sb.subitem GROUP BY 1,2,4,5,6  HAVING SUM(at.paid_amount) > 0  ORDER BY 1");

                                System.out.println("This stage has been passed 3");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                //table.addCell(listofAct2[i].toString());
                                //  table.addCell(phrase);

                                while (rset11.next()) {
                                    java.sql.ResultSet rset11x = st8.executeQuery("SELECT DISTINCT votename FROM votes"
                                            + " WHERE vote = '" + rset11.getObject(1).toString() + "'");

                                    while (rset11x.next()) {
                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setColspan(5);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("VOTE : " + rset11.getObject(1).toString() + "   " + rset11x.getObject(1).toString(), pFontHeader);

                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase("CLASS : " + listofAct2[i].toString() + " " + rset11.getObject(2).toString().toUpperCase(), pFontHeader);

                                        table.addCell(phrase);
                                    }


                                    table.getDefaultCell().setColspan(1);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("VOUCHER", pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase("ACCOUNT DETAILS", pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase("SUB ITEM DESCRIPTION", pFontHeader);

                                    table.addCell(phrase);

                                    phrase = new Phrase("PAYEE/VENDOR", pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);

                                    phrase = new Phrase("AMOUNT", pFontHeader);

                                    table.addCell(phrase);

                                    //  }

                                    while (rset15.next()) {
                                        table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                        table.getDefaultCell().setColspan(1);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset15.getObject(5).toString().toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        phrase = new Phrase(rset15.getObject(1).toString().toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);
                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                        phrase = new Phrase(rset15.getObject(4).toString(), pFontHeader1);
                                        table.addCell(phrase);

                                        phrase = new Phrase(rset15.getObject(6).toString().toUpperCase(), pFontHeader1);
                                        table.addCell(phrase);

                                        table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                        phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(rset15.getString(3)), pFontHeader1);

                                        table.addCell(phrase);

                                        totalLiabEquity = totalLiabEquity + rset15.getDouble(3);
                                        //}

                                    }

                                }


                                table.getDefaultCell().setColspan(5);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                table.addCell("  ");
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                                //    while (rset16.next()) {

                                table.getDefaultCell().setColspan(4);

                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                // table.getDefaultCell().
                                phrase = new Phrase("Total ", pFontHeader);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_RIGHT);
                                //   totalLiabEquity = equity + liabilities;
                                phrase = new Phrase(new com.afrisoftech.sys.Format2Currency().Format2Currency(java.lang.String.valueOf(totalLiabEquity)), pFontHeader);

                                table.addCell(phrase);

                                docPdf.add(table);

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }
                            boolean boolNewPage = docPdf.newPage();
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

       //     docPdf.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);



        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }



    }

    /* public java.lang.Object[] getListofActivities() {

     java.lang.Object[] listofActivities = null;

     java.util.Vector listActVector = new java.util.Vector(1,1);



     try {


     java.sql.Statement stmt1 = connectDB.createStatement();

     java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'ba' order by main_code");

     while (rSet1.next()) {

     listActVector.addElement(rSet1.getObject(1).toString());

     }

     }catch (java.sql.SQLException sqlExec) {

     javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

     }

     listofActivities = listActVector.toArray();

     return listofActivities;
     }
    
    
     public java.lang.Object[] getListofActivities1() {

     java.lang.Object[] listofActivities1 = null;

     java.util.Vector listActVector = new java.util.Vector(1,1);



     try {


     java.sql.Statement stmt1 = connectDB.createStatement();

     java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT main_code FROM pb_accounts_setup where class = 'bl' order by main_code");

     while (rSet1.next()) {

     listActVector.addElement(rSet1.getObject(1).toString());

     }

     }catch (java.sql.SQLException sqlExec) {

     javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());

     }

     listofActivities1 = listActVector.toArray();

     return listofActivities1;
     }
     */
    public java.lang.Object[] getListofActivities2() {

        java.lang.Object[] listofActivities2 = null;

        java.util.Vector listActVector = new java.util.Vector(1, 1);





        try {

            //  java.sql.Connection connDB = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/insurance","postgres","pilsiner");

            java.sql.Statement stmt1 = connectDB.createStatement();

            java.sql.ResultSet rSet1 = stmt1.executeQuery("SELECT DISTINCT v_class FROM ac_aie_commitment ORDER BY 1");



            while (rSet1.next()) {

                listActVector.addElement(rSet1.getObject(1).toString());



            }

        } catch (java.sql.SQLException sqlExec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sqlExec.getMessage());



        }

        listofActivities2 = listActVector.toArray();

        System.out.println("Total list of activities = " + listofActivities2.length);



        return listofActivities2;

    }
}
