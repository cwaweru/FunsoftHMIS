//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import com.lowagie.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import java.awt.Desktop;
import java.io.FileOutputStream;
//import com.lowagie.text.pdf.PdfWriter;

public class GatePassPdf implements java.lang.Runnable {

    java.lang.String MNo = null;
    java.lang.String beginDate = null;
    java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    String patNo;
    boolean threadCheck = true;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    //    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    //    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private Object writer;
    String invoiceNo = null;
    private String patientName;
    private String patientNo;
    private String dischargeNo;

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void GatePassPdf(java.sql.Connection connDb, java.lang.String combox, String invNo, String patientNumber) {

        dischargeNo = combox;

        patientNo = patientNumber;

        invoiceNo = invNo;

        MNo = combox;

        connectDB = connDb;

        // beginDate = begindate;
        // endDate = endate;
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

            this.generatePdf(MNo);

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

    public void generatePdf(java.lang.String memNo) {

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

            int titleFont = 0;
            int bodyFont = 0;
            float Widths = 0;
            float Heights = 0;
            float Margins = 0;

            try {
                connectDB.setAutoCommit(false);

                java.sql.Statement stm1 = connectDB.createStatement();
                java.sql.ResultSet rse1 = stm1.executeQuery("select header_font,body_font,width,height,margins from receipt_pref");
                while (rse1.next()) {
                    titleFont = rse1.getInt(1);
                    bodyFont = rse1.getInt(2);
                    Widths = rse1.getFloat(3);
                    Heights = rse1.getFloat(4);
                    Margins = rse1.getFloat(5);
                }
            } catch (java.sql.SQLException sq) {

                try {
                    connectDB.rollback();
                } catch (java.sql.SQLException sql) {
                    //   javax.swing.JOptionPane.showMessageDialog(this,sql.getMessage(),"Error Message!",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                System.out.println(sq.getMessage());
                //   javax.swing.JOptionPane.showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);

            }
            com.lowagie.text.Font pFontHeader2 = FontFactory.getFont(FontFactory.HELVETICA, bodyFont, Font.BOLD);

            com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, bodyFont, Font.NORMAL);
            com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, titleFont, Font.BOLD);
            com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.TIMES_ITALIC, titleFont, Font.BOLD);

            // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")));
            ///// com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((Widths), Heights), Margins, Margins, Margins, Margins);
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(PageSize.A4.rotate());

            //            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));
            try {

                try {

                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String compName = null;
                    String date = null;
                    PdfWriter writer = PdfWriter.getInstance(docPdf, new FileOutputStream(tempFile));
                    docPdf.open();
                    PdfContentByte cb = writer.getDirectContent();
                    String footers = null;
                    try {

                        if (dischargeNo.length() > 0 && patientNo.length() > 0) {

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                            int headerwidths[] = {15, 1, 10, 30, 15, 10};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            Phrase phrase = new Phrase("");

                            // table.addCell(phrase);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(1);
                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            try {

                                String patAge = "-";

                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st5 = connectDB.createStatement();
                                java.sql.Statement st6 = connectDB.createStatement();
                                java.sql.Statement st51 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();
                                java.sql.ResultSet rset3 = st3.executeQuery("select hospital_name,box_no,main_telno,main_faxno from pb_hospitalprofile");

                                java.sql.ResultSet rs = st.executeQuery("select patient_no,patient_name,discharge_no,discharge_date,doctor,discharge_time::time(0),admission_date,user_name,ward from hp_patient_discharge cb where inv_no = '" + invoiceNo + "' AND patient_no = '" + patientNo + "' AND discharge_no = '" + dischargeNo + "'");

                                System.out.println(MNo);

                                while (rset3.next()) {
                                    table.getDefaultCell().setColspan(6);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                    phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);

                                    phrase = new Phrase("P.O. BOX : " + rset3.getObject(2).toString(), pFontHeader2);
                                    table.addCell(phrase);

                                    phrase = new Phrase("TEL : " + rset3.getObject(3).toString() + "  FAX : " + rset3.getObject(4).toString(), pFontHeader2);
                                    table.addCell(phrase);

                                }
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                //  table.getDefaultCell().setBorderWidth(Rectangle.TOP);

                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("PATIENT RELEASE PAPER/GATE PASS", pFontHeader1);

                                table.addCell(phrase);

                                while (rs.next()) {

                                    table.getDefaultCell().setColspan(1);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Paper No : " + rs.getString(3), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(3);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Date : " + rs.getObject(4).toString(), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase("TIME : " + rs.getString(6), pFontHeader);

                                    table.addCell(phrase);

                                    patNo = rs.getString(1);

                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Patient No : " + patNo, pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(3);
                                    phrase = new Phrase("Patient Name : " + rs.getObject(2).toString(), pFontHeader);
                                    patientName = rs.getObject(2).toString();
                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    java.sql.ResultSet rsw = st1.executeQuery("SELECT ('now'::date-year_of_birth::date)/365 FROM hp_inpatient_register WHERE patient_no =  '" + rs.getString(1) + "'");
                                    while (rsw.next()) {
                                        phrase = new Phrase("Age: " + rsw.getObject(1).toString(), pFontHeader);

                                        table.addCell(phrase);
                                    }
                                    table.getDefaultCell().setColspan(1);
                                    phrase = new Phrase("Admission Date : " + rs.getString(7), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setColspan(3);
                                    phrase = new Phrase("Discharge Date : " + rs.getObject(4).toString(), pFontHeader);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(2);
                                    phrase = new Phrase("Ward : " + rs.getObject(9).toString(), pFontHeader);

                                    table.addCell(phrase);

                                    // phrase = new Phrase("Discharged By : " + rs.getString(8), pFontHeader);
                                    // table.addCell(phrase);
                                }
                                table.getDefaultCell().setColspan(6);
                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("Method of Payment(Tick where approprite)", pFontHeader11);

                                table.addCell(phrase);

                                phrase = new Phrase("Cash [   ] / Cheque [   ] / N.H.I.F [   ] / CO-OP [   ] / M.R.M [   ] / OTHERS.......................... Final Invoice No : " + invoiceNo.toUpperCase(), pFontHeader2);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(6);

                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("Accountant Incharge : .....................................Sign/Stamp:................................Check-Out Date..............................", pFontHeader);

                                table.addCell(phrase);

                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);

                                phrase = new Phrase("Nurse Manager : .........................................Sign/Stamp:...................................Check-Out Date...............................", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("Records Officer : .........................................Sign/Stamp:...................................Check-Out Date...............................", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("Security Guard I/C : ........................................Sign/Stamp:..................................Check-Out Date................................", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("\nAccompanied By : ........................................Sign:..........................................Check-Out Date................................", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("  ", pFontHeader);

                                table.addCell(phrase);
                                phrase = new Phrase("NB:\nThis form should be filled in duplicate, one copy to be retained in the ward and the other one be left at the Main Gate.\nPLEASE NOTE THAT CHECK-OUT DATE MUST BE THE DATE THE PATIENT LEAVES THE HOSPITAL", pFontHeader11);

                                table.addCell(phrase);
                                phrase = new Phrase(" ");
                                table.addCell(phrase);
                                Barcode128 code128 = new Barcode128();
                                code128.setCode(patNo + " " + patientName + " " + invoiceNo);
                                code128.setBarHeight(20);

                                code128.setTextAlignment(Element.ALIGN_CENTER);

                                docPdf.add(table);

                                System.out.println(cb);

                                docPdf.add(code128.createImageWithBarcode(cb, null, null));

                            } catch (java.sql.SQLException SqlExec) {

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

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

            // deskTop.open(tempFile);
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);

        } catch (java.io.IOException IOexec) {

            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

        }

    }
}
