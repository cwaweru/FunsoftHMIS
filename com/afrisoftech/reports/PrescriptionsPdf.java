//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.reports;

import java.io.FileOutputStream;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Image;
import java.io.FileOutputStream;
//import //java.awt.Desktop;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class PrescriptionsPdf implements java.lang.Runnable {

    // ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String MNo = null;
    java.lang.String beginDate = null;
    java.lang.String endDate = null;
    String exemptionNumber = "-";
    public com.afrisoftech.lib.DBObject dbObject;
    double WaiverExe = 0.00;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    //  java.lang.String memNo2Use = null;
    java.lang.Thread threadSample;
    //    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
    //    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private double totals = 0.00;
    PdfWriter writer = null;
    PdfContentByte cb = null;
    String drugName = null;

    // public void ReceiptsPdf(java.sql.Connection connDb, java.lang.String begindate, java.lang.String endate, java.lang.String combox) {
    public void PrescriptionsPdf(java.sql.Connection connDb, java.lang.String combox, java.lang.String itemName) {

        dbObject = new com.afrisoftech.lib.DBObject();

        MNo = combox;

        drugName = itemName;

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
            try {
                Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

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
                    java.sql.ResultSet rse1 = stm1.executeQuery("select header_font,body_font,width,height,margins from prescriptions_pref");
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
                    //   javax.swing.JOptionPane.3showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);

                }
                // com.lowagie.text.Font pFontHeader = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptFontSize")), Font.NORMAL);
                // com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptTitleFontSize")), Font.BOLD);
                com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.COURIER, bodyFont, Font.BOLD);
                com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.COURIER, titleFont, Font.BOLD | Font.UNDERLINE);
                com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.COURIER, bodyFont, Font.NORMAL);

                /*
                 * com.lowagie.text.Font pFontHeader =
                 * FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
                 * com.lowagie.text.Font pFontHeader1 =
                 * FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
                 * com.lowagie.text.Font pFontHeaderx =
                 * FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
                 */
                // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))).rotate(),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")),java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")));
                com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((Widths), Heights), Margins, Margins, Margins, Margins);
                //// com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((595), 419),5,5,5,5);
                ////        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((200), 419));     //, 0, 0, 0, 0);
                // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(); 

                //            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));
                try {

                    try {
                        //  //Image imgWaterMark = Image.getInstance(System.getProperty("company.watermark"));

                        com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                        String compName = null;
                        String date = null;

                        writer = PdfWriter.getInstance(docPdf, new FileOutputStream(tempFile));

                        docPdf.open();

                        //PdfContentByte cb = writer.getDirectContent();
                        String footers = null;
                        try {
                            com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(6);

                            int headerwidths2[] = {15, 20, 20, 15, 15, 15};

                            table2.setWidths(headerwidths2);

                            com.lowagie.text.pdf.PdfPTable table1 = new com.lowagie.text.pdf.PdfPTable(2);

                            int headerwidths1[] = {70, 30};

                            table1.setWidths(headerwidths1);

                            table1.setWidthPercentage((100));

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(6);

                            int headerwidths[] = {15, 20, 20, 20, 15, 15};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table2.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                            //  table.getDefaultCell().setColspan(6);
                            Phrase phrase2 = new Phrase("");
                            Phrase phrase = new Phrase("");
                            Phrase phrase1 = new Phrase("");
                            // table.addCell(phrase);
                            // table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setColspan(1);
                            // table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            //table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);

                            try {

                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement st1 = connectDB.createStatement();
                                java.sql.PreparedStatement st5 = connectDB.prepareStatement("SELECT DISTINCT medicine_name, dosage, frequency, no_of_days, comments from prescription_detail where prescription_no = ? AND medicine_name = ? ORDER By 1");
                                st5.setString(1, MNo);
                                st5.setString(2, drugName);
                                java.sql.Statement st3 = connectDB.createStatement();

                                java.sql.ResultSet rset1 = st1.executeQuery("SELECT DISTINCT cb.patient_no, cb.patient_name FROM prescription_detail cb where cb.prescription_no = '" + MNo + "'");
                                java.sql.ResultSet rs = st.executeQuery("SELECT DISTINCT trans_date::timestamp(0) FROM prescription_detail cb where prescription_no = '" + MNo + "'");
                                java.sql.ResultSet rset5 = st5.executeQuery();
                                java.sql.ResultSet rset3 = st3.executeQuery("SELECT header,footer FROM ac_receipt_header");
                                System.out.println(MNo);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                table.getDefaultCell().setColspan(6);

                                table2.getDefaultCell().setFixedHeight(50);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                while (rset3.next()) {

                                    table.getDefaultCell().setColspan(6);

                                    phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader);

                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                                phrase = new Phrase("PRESCRIPTION FORM", pFontHeader1);

                                table.addCell(phrase);
                                //  table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                //  table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);

                                while (rs.next()) {

                                    table.getDefaultCell().setColspan(6);

                                    phrase = new Phrase("Date: " + rs.getObject(1).toString(), pFontHeaderx);

                                    table.addCell(phrase);

                                }

                                while (rset1.next()) {

                                    table.getDefaultCell().setColspan(3);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Client No: " + rset1.getString(1), pFontHeader);

                                    table.addCell(phrase);

                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                    phrase = new Phrase("Name: " + rset1.getString(2), pFontHeader);

                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setColspan(3);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.LEFT);
                                phrase = new Phrase("Medicine", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Dosage", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setColspan(1);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase("Freq", pFontHeader1);

                                table.addCell(phrase);

                                table1.getDefaultCell().setColspan(1);
                                phrase = new Phrase("Days", pFontHeader1);

                                table.addCell(phrase);

                                table.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                                while (rset5.next()) {
                                    table.getDefaultCell().setColspan(3);

                                    //phrase = new Phrase("    ", pFontHeader);
                                    //table.addCell(phrase);
                                    table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                    phrase = new Phrase(rset5.getString(1), pFontHeaderx);

                                    table.addCell(phrase);
                                    table.getDefaultCell().setColspan(1);

                                    String dosage = "";
                                    if(rset5.getString(2).equalsIgnoreCase("i")){
                                        dosage = "1";
                                    }
                                    if(rset5.getString(2).equalsIgnoreCase("ii")){
                                        dosage = "2";
                                    }
                                    if(rset5.getString(2).equalsIgnoreCase("iii")){
                                        dosage = "3";
                                    }
                                    if(rset5.getString(2).equalsIgnoreCase("iv")){
                                        dosage = "4";
                                    }
                                    
                                    
                                    phrase = new Phrase(dosage, pFontHeaderx);

                                    table.addCell(phrase);

                                    String frequency = "";

                                    if (rset5.getString(3) != null) {
                                        frequency = rset5.getString(3);
                                    }

                                    String printFrequency = "";

                                    if (frequency.equalsIgnoreCase("stat")) {
                                        printFrequency = "Once";
                                    } else if (frequency.equalsIgnoreCase("OD")) {
                                        printFrequency = "Once Daily";
                                    } else if (frequency.equalsIgnoreCase("BD")) {
                                        printFrequency = "Every 12 Hours";
                                    } else if (frequency.equalsIgnoreCase("TID")) {
                                        printFrequency = "Every 8 Hours";
                                    } else if (frequency.equalsIgnoreCase("QID")) {
                                        printFrequency = "Every 6 Hours";
                                    } else if (frequency.equalsIgnoreCase("TID")) {
                                        printFrequency = "Every 8 Hours";
                                    } else if (frequency.equalsIgnoreCase("NOCTE")) {
                                        printFrequency = "Every Night";
                                    } else if (frequency.equalsIgnoreCase("PRN")) {
                                        printFrequency = "AS REQUIRED";
                                    } else if (frequency.equalsIgnoreCase("MANE")) {
                                        printFrequency = "IN THE MORNING";
                                    }

                                    phrase = new Phrase(printFrequency, pFontHeaderx);

                                    table.addCell(phrase);

                                    phrase = new Phrase(rset5.getString(4), pFontHeaderx);

                                    table.addCell(phrase);

                                }
                                table.getDefaultCell().setColspan(6);

                                // table.getDefaultCell().setFixedHeight(125);
                                //table.addCell(table1);
                                //table.getDefaultCell().setFixedHeight(15);
                                // while (rsetTotals.next()) {
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setBorder(Rectangle.TOP);
                                table.getDefaultCell().setColspan(1);

                                while (rset3.next()) {

                                    table.getDefaultCell().setColspan(6);

                                    phrase = new Phrase(rset3.getObject(2).toString(), pFontHeader);

                                    table.addCell(phrase);

                                }

                                docPdf.add(table);

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
                PdfContentByte under;
                PdfReader reader = new PdfReader(tempFile.getPath());
                int n = reader.getNumberOfPages();
                int i = 0;
                //               tempFile.delete();
                java.io.File tempFile2 = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");

                //////   com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
                System.out.println("Path of file to be printed : [" + tempFile.getPath() + "]");
//                if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
//                    com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(tempFile.getPath());
//                } else {
                java.awt.Desktop deskTop = java.awt.Desktop.getDesktop();
                deskTop.print(tempFile);
//                }
                //  com.afrisoftech.lib.PrintTextFiles.printReceiptTextFile(tempFile.getPath());

            } catch (java.io.IOException IOexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

            }

        } catch (com.lowagie.text.BadElementException badEl) {

            badEl.printStackTrace();
        }

    }
}
