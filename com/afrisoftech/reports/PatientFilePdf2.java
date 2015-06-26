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

public class PatientFilePdf2 implements java.lang.Runnable {
   ////java.awt.Desktop deskTop = Desktop.getDesktop();
    java.lang.String patNo = null;
    java.util.Date beginDate = null;
    int slipNo = 0;
    java.lang.String Sex = null;
    java.lang.String Patient = null;
    java.lang.String Marital = null;
    java.lang.String District = null;
    java.lang.String Location = null;
    java.lang.String subLocation = null;
    java.lang.String pAddress = null;
    java.lang.String pPhone = null;
    java.lang.String Nok = null;
    java.lang.String nokRelation = null;
    java.lang.String nokContact = null;
    java.lang.String nokPhone = null;
    java.lang.String nokResidence = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    String ks;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;

    public void PatientFilePdf2(java.sql.Connection connDb, java.lang.String combox, java.lang.String patient, java.util.Date birthDate, java.lang.String sex, java.lang.String marital, java.lang.String district, java.lang.String location, java.lang.String sublocation, java.lang.String paddress, java.lang.String pphone, java.lang.String nok, java.lang.String nokrelation, java.lang.String nokcontact, java.lang.String nokphone, java.lang.String nokresidence) {


        connectDB = connDb;

        patNo = combox;

        Patient = patient;

        beginDate = birthDate;

        Sex = sex;

        Marital = marital;

        District = district;

        Location = location;

        subLocation = sublocation;

        pAddress = paddress;

        pPhone = pphone;

        Nok = nok;

        nokRelation = nokrelation;

        nokContact = nokcontact;

        nokPhone = nokphone;

        nokResidence = nokresidence;

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

         //   this.generatePdf(MNo);

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
                    //   javax.swing.JOptionPane.3showMessageDialog(this,sq.getMessage(), "Error",javax.swing.JOptionPane.ERROR_MESSAGE);

                }
                // com.lowagie.text.Font pFontHeader = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptFontSize")), Font.NORMAL);
                // com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(System.getProperty("font_type_name"), java.lang.Float.parseFloat(System.getProperty("receiptTitleFontSize")), Font.BOLD);
                com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.COURIER, bodyFont, Font.BOLD);
                com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.COURIER, titleFont, Font.BOLD);
                com.lowagie.text.Font pFontHeaderx = FontFactory.getFont(FontFactory.COURIER, bodyFont, Font.BOLD);

                /*
                 * com.lowagie.text.Font pFontHeader =
                 * FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
                 * com.lowagie.text.Font pFontHeader1 =
                 * FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
                 * com.lowagie.text.Font pFontHeaderx =
                 * FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
                 */

                com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))).rotate(), java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")), java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")), java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")), java.lang.Float.parseFloat(System.getProperty("receiptPageMargin")));
                /////  com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((Widths), Heights), Margins, Margins, Margins, Margins);
                //// com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((595), 419),5,5,5,5);
                ////        com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle((200), 419));     //, 0, 0, 0, 0);
                // com.lowagie.text.Document docPdf = new com.lowagie.text.Document(); 

                //            com.lowagie.text.Document docPdf = new com.lowagie.text.Document(new Rectangle(java.lang.Float.parseFloat(System.getProperty("papersize_width")), java.lang.Float.parseFloat(System.getProperty("papersize_legnth"))));

                try {

                    try {

                        com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));



                        try {

                            java.lang.Class.forName("org.postgresql.Driver");

                        } catch (java.lang.ClassNotFoundException cnfExec) {

                            cnfExec.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), cnfExec.getMessage());

                        }

                        String compName = null;
                        String date = null;


                        PdfWriter writer = PdfWriter.getInstance(docPdf, new FileOutputStream(tempFile));
                        docPdf.open();
                        PdfContentByte cb = writer.getDirectContent();

                        try {
                            com.lowagie.text.pdf.PdfPTable table2 = new com.lowagie.text.pdf.PdfPTable(1);

                            int headerwidths2[] = {100};

                            table2.setWidths(headerwidths2);

                            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(1);

                            int headerwidths[] = {100};//, 20, 35};

                            table.setWidths(headerwidths);

                            table.setWidthPercentage((100));

                            table2.getDefaultCell().setFixedHeight(50);
                            Phrase phrase = new Phrase("", pFontHeader);
                            phrase = new Phrase("", pFontHeader1);

                            //table.addCell(phrase);
                            table2.getDefaultCell().setColspan(1);
                            table2.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            //Image img = Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo());
                            //   img.scalePercent(50);
                            // img.sc//aleAbsolute(200, 200);
                            table2.addCell(img);

                            table.addCell(table2); // painting the logo
                            //Phrase phrase = new Phrase("", pFontHeader);



                            table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                            table.getDefaultCell().setBorderColor(java.awt.Color.WHITE);


                            try {

                                java.sql.Statement st = connectDB.createStatement();
                                java.sql.Statement sts2 = connectDB.createStatement();
                                java.sql.Statement st22 = connectDB.createStatement();

                                java.sql.Statement st2 = connectDB.createStatement();
                                java.sql.Statement st21 = connectDB.createStatement();
                                java.sql.Statement st3 = connectDB.createStatement();

                                java.sql.Statement st2x = connectDB.createStatement();

                                java.sql.ResultSet rset2x = st2x.executeQuery("SELECT rep_currency from pb_hospitalprofile");
                                while (rset2x.next()) {
                                    ks = rset2x.getObject(1).toString();
                                }

                                java.sql.ResultSet rset3 = st3.executeQuery("SELECT header,footer FROM ac_receipt_header");


                                while (rset3.next()) {
                                    table.getDefaultCell().setColspan(1);

                                    phrase = new Phrase(rset3.getObject(1).toString(), pFontHeader1);
                                    table.addCell(phrase);

                                    // footers = rset3.getObject(2).toString();


                                }

                                table.getDefaultCell().setColspan(2);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);

                                phrase = new Phrase("Patient No.: " + patNo, pFontHeader1);

                                table.addCell(phrase);
                                //phrase = new Phrase("Sex: " + Sex, pFontHeader1);

                                //table.addCell(phrase);
                                phrase = new Phrase("Patient Name: " + Patient, pFontHeader1);

                                table.addCell(phrase);

                                phrase = new Phrase("PATIENT REGISTRATION CARD", pFontHeader1);

                                table.addCell(phrase);

                    //            phrase = new Phrase("Please rember to carry this card when visiting the hospital.", pFontFooter1);

                    //            table.addCell(phrase);
                    //            phrase = new Phrase("", pFontFooter1);

                                table.addCell(phrase);

                                //phrase = new Phrase("Marital Status : " + Marital, pFontHeader1);

                                //table.addCell(phrase);
                                java.util.Date endDate1 = null;
                                java.text.DateFormat dateFormat = null;

                              //  docPdf.add(table);

                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);

                                // docPdf.add(new Paragraph("Barcode 128"));
                                Barcode128 code128 = new Barcode128();
                                code128.setCode(patNo);
                                code128.setBarHeight(16);
                                //code128.setSize(7);

                                code128.setTextAlignment(Element.ALIGN_CENTER);

                                docPdf.add(table);
                                docPdf.add(code128.createImageWithBarcode(cb, null, null));


                            } catch (java.sql.SQLException SqlExec) {

                                SqlExec.printStackTrace();

                                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                            }

                        } catch (com.lowagie.text.BadElementException BadElExec) {

                            BadElExec.printStackTrace();

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                        }

                    } catch (java.io.FileNotFoundException fnfExec) {

                        fnfExec.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), fnfExec.getMessage());

                    }
                } catch (com.lowagie.text.DocumentException lwDocexec) {

                    lwDocexec.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), lwDocexec.getMessage());

                }


                docPdf.close();
                PdfContentByte under;
                PdfReader reader = new PdfReader(tempFile.getPath());
                int n = reader.getNumberOfPages();
                int i = 0;
                //               tempFile.delete();
                java.io.File tempFile2 = java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");
//                tempFile = tempFile.createNewFile();
               /*
                 * try { PdfStamper stamp = new PdfStamper(reader, new
                 * FileOutputStream(tempFile2));
                 * imgWaterMark.scaleToFit(docPdf.getPageSize().width(),
                 * docPdf.getPageSize().height()); // img.scalePercent(50);
                 *
                 * imgWaterMark.setAbsolutePosition(0, 0); while (i < n) { i++;
                 * // watermark under the existing page under =
                 * stamp.getUnderContent(i); //
                 * under.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230,
                 * 430, 45); //under.addImage(img);
                 * under.addImage(imgWaterMark); } stamp.close(); } catch
                 * (com.lowagie.text.DocumentException docEx) {
                 * docEx.printStackTrace(); }
                 */
                com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
                /*
                 * try {
                 *
                 * if (System.getProperty("os.name").equalsIgnoreCase("Linux"))
                 * {
                 *
                 * System.out.println(tempFile);
                 *
                 * // wait_for_Pdf2Show = rt.exec("kghostview " + tempFile2 +
                 * ""); wait_for_Pdf2Show = rt.exec("cat kghostview " + tempFile
                 * + "");
                 *
                 * wait_for_Pdf2Show.waitFor();
                 *
                 * } else { //wait_for_Pdf2Show = rt.exec("c:/Program
                 * Files/Adobe/Acrobat 5.0/Reader/AcroRd32.exe " + tempFile);
                 *
                 * //wait_for_Pdf2Show.waitFor(); wait_for_Pdf2Show =
                 * rt.exec("cmd.exe /C AcroRd32 /p /h " + tempFile2);
                 *
                 * wait_for_Pdf2Show.waitFor();
                 *
                 * }
                 *
                 * } catch (java.lang.InterruptedException intrExec) {
                 *
                 * javax.swing.JOptionPane.showMessageDialog(new
                 * javax.swing.JFrame(), intrExec.getMessage());
                 *
                 * }
                 */



            } catch (java.io.IOException IOexec) {

                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), IOexec.getMessage());

            }

        } catch (com.lowagie.text.BadElementException badEl) {

            badEl.printStackTrace();
        }

    }
}
