///Author @author SaQlever
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.laboratory;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;

public class LabRequestReportPdf implements java.lang.Runnable {

    
    public static java.sql.Connection connectDB = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontHeader11 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    com.lowagie.text.Font pFontHeaderX = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
 
    public void LabRequestReportPdf(java.sql.Connection DbConn) {
        
        connectDB = DbConn;
        threadSample = new java.lang.Thread(this, "SampleThread");
        threadSample.start();
        System.out.println("Thread started....");
    }

    public static void main(java.lang.String[] args) {
      }

    public void run() {
        System.out.println("System is running...");
        while (threadCheck) {
            this.generatePdf();
            try {
                java.lang.Thread.currentThread().sleep(100);
            } catch (java.lang.InterruptedException IntExec) {
                System.out.println(IntExec.getMessage());
            }
            threadCheck = false;
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
            
            year_now_strs = "200"+year_now_abs;
            
        } else {
            
            year_now_strs = "20"+year_now_abs;
            
        }
        
        switch (month_now_str) {
            
            case 0 : month_now_strs = "JAN";
            
            break;
            
            case 1 : month_now_strs = "FEB";
            
            break;
            
            case 2 : month_now_strs = "MAR";
            
            break;
            
            case 3 : month_now_strs = "APR";
            
            break;
            
            case 4 : month_now_strs = "MAY";
            
            break;
            
            case 5 : month_now_strs = "JUN";
            
            break;
            
            case 6 : month_now_strs = "JUL";
            
            break;
            
            case 7 : month_now_strs = "AUG";
            
            break;
            
            case 8 : month_now_strs = "SEP";
            
            break;
            
            case 9 : month_now_strs = "OCT";
            
            break;
            
            case 10 : month_now_strs = "NOV";
            
            break;
            
            case 11 : month_now_strs = "DEC";
            
            break;
            
            default :         if (month_now_str < 10){
                
                month_now_strs = "0"+month_now_str;
                
            } else {
                
                month_now_strs = ""+month_now_str;
                
            }
            
        }
        
        if (date_now_str < 10) {
            
            date_now_strs = "0"+date_now_str;
            
        } else {
            
            date_now_strs = ""+date_now_str;
            
        }
        
        if (minute_now_str < 10) {
            
            minute_now_strs = "0"+minute_now_str;
            
        } else {
            
            minute_now_strs = ""+minute_now_str;
            
        }
        
        if (hour_now_str < 10) {
            
            hour_now_strs = "0"+hour_now_str;
            
        } else {
            
            hour_now_strs = ""+hour_now_str;
            
        }
        
        date_label = date_now_strs+month_now_strs+year_now_strs+"@"+hour_now_strs+minute_now_strs;
        
        return date_label;
        
    }

    public void generatePdf() {

        java.lang.Process wait_for_Pdf2Show;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date dateStampPdf = cal.getTime();
        java.lang.String pdfDateStamp = dateStampPdf.toString();
        com.lowagie.text.pdf.PdfPTable table1 =null;

        try {

            java.io.File tempFile = java.io.File.createTempFile("REPORT" + this.getDateLable() + "_", ".pdf");
            tempFile.deleteOnExit();
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            com.lowagie.text.Document docPdf = new com.lowagie.text.Document();

            try {

                try {
                    com.lowagie.text.pdf.PdfWriter.getInstance(docPdf, new java.io.FileOutputStream(tempFile));

                    String footer1 = null;
                  
                     com.lowagie.text.HeaderFooter footer = new com.lowagie.text.HeaderFooter(new Phrase("Lab Request/Report Form- Page: ",pFontHeader), true);
                    docPdf.setFooter(footer);
                    
                    docPdf.open();

                    try {

                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        long dateNow = calendar.getTimeInMillis();
                        java.sql.Date datenowSql = new java.sql.Date(dateNow);
                        System.out.println(datenowSql.toString());
                        table1 = new com.lowagie.text.pdf.PdfPTable(6);
 
                        int headerwidths[] = {15, 15, 30, 15, 15, 15};

                        table1.setWidths(headerwidths);
                        table1.setWidthPercentage((100));
                        table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        table1.getDefaultCell().setColspan(6);

                        Phrase phrase = new Phrase();
                        Phrase phrase1 = new Phrase();
                        Phrase phrase2 = new Phrase();
                        Phrase phrase3 = new Phrase();
                        Phrase phrase4 = new Phrase();
                        Phrase phrase5 = new Phrase();
                        Phrase phrase6 = new Phrase();
                        Phrase phrase7 = new Phrase();
                        Phrase phrase8 = new Phrase();
                        Phrase phrase9 = new Phrase();
                        Phrase phrase10 = new Phrase();
                        Phrase phrase11 = new Phrase();

                        //table1.getDefaultCell().setColspan(1);
                        //table1.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        //table1.getDefaultCell().setBorderColor(java.awt.Color.WHITE);
                        table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                        try {

                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setColspan(2);
                            table1.getDefaultCell().setFixedHeight(70);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            String curentdate=null;

                            //String logopath=extensiblepos.main.ExtensiblePosLogin.logo;
                            //System.out.println("Logo path...."+logopath);
                            table1.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));

                            //table1.addCell(Image.getInstance(logopath));
                            table1.getDefaultCell().setFixedHeight(16);
                            java.sql.Statement st321 = connectDB.createStatement();
                            java.sql.ResultSet rset3 = st321.executeQuery("SELECT header_name,current_date from pb_header");
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                                table1.getDefaultCell().setColspan(4);
                                while(rset3.next()){
                                curentdate=rset3.getString(2);
                                table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                                phrase = new Phrase(rset3.getObject(1).toString().toUpperCase(), pFontHeader11);
                                phrase = new Phrase(rset3.getString(1).toUpperCase(), pFontHeader11);
                                table1.addCell(phrase);
                                }
                            
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setColspan(6);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_CENTER);
                            table1.getDefaultCell().setFixedHeight(25);
                            phrase = new Phrase("Lab Report Form".toUpperCase(), pFontHeaderX);
                            table1.addCell(phrase);
                            table1.getDefaultCell().setFixedHeight(16);
                            table1.getDefaultCell().setBorder(Rectangle.BOX);
                            table1.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Patient No. : 0000001" .toUpperCase() , pFontHeader1);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Patient Source : " , pFontHeader1);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Patient Name : Test Demo" .toUpperCase() , pFontHeader1);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Specimen of : " , pFontHeader1);
                            table1.addCell(phrase);

                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Sex       : Male".toUpperCase() , pFontHeader1);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Collected on (Date & Time)"  , pFontHeader1);
                            table1.addCell(phrase);
                            
                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Age       : 67" .toUpperCase() , pFontHeader1);
                            table1.addCell(phrase);

                            table1.getDefaultCell().setColspan(3);
                            table1.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            phrase = new Phrase("Printed on : "+curentdate , pFontHeader1);
                            table1.addCell(phrase);

                            table1.getDefaultCell().setColspan(18);
                            phrase = new Phrase(" ", pFontHeader1);
                            table1.addCell(phrase);

                        } catch (java.sql.SQLException SqlExec) {

                            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), SqlExec.getMessage());

                        }
                        
                    } catch (com.lowagie.text.BadElementException BadElExec) {

                        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), BadElExec.getMessage());

                    }

                    try {

                        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(10);

                        int headerwidths[] = {8,20, 15,20,12,15,15,15,15,20};

                        table.setWidths(headerwidths);
                        table.setHeaderRows(1);
                        table.setWidthPercentage((100));

                        table.getDefaultCell().setBorder(Rectangle.BOX);

                        table.getDefaultCell().setColspan(10);

                        Phrase phrase = new Phrase();

                        table.getDefaultCell().setColspan(1);
                        table.getDefaultCell().setBackgroundColor(java.awt.Color.WHITE);
                        table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);

                       
                            table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                            table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                            
                            try{

                            
                            java.sql.Statement st2 = connectDB.createStatement();
                            java.sql.ResultSet rset1 = null;
                            
                            
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(10);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Investigation Required \n\n\n\n", pFontHeader1);
                                table.addCell(phrase);
                                
                               
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(10);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Report to be sent to \n\n\n\n", pFontHeader1);
                                table.addCell(phrase);
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(10);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("Diagnosis: (duration & site of lesion) \n\n\n", pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(10);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("History: (Therapy including antibiotics) \n\n\n\n\n"+"      Doctor___________                                       Signature_________", pFontHeader1);
                                table.addCell(phrase);
                                
                                 table.getDefaultCell().setBorderColor(java.awt.Color.BLACK);
                                table.getDefaultCell().setColspan(10);
                                table.getDefaultCell().setHorizontalAlignment(PdfCell.ALIGN_LEFT);
                                phrase = new Phrase("REPORT"+" \n\n\n\n\n\n", pFontHeader1);
                                table.addCell(phrase);
                                
                                
                                
                                                                                
                                
           
                                table.getDefaultCell().setColspan(30);
                                phrase = new Phrase("   ", pFontHeader);
                                table.addCell(phrase);
                                
                                
                            
                            docPdf.add(table1);
                            docPdf.add(table);
                        
                             }catch (java.sql.SQLException sq) {

            sq.printStackTrace();
            System.out.println(sq.getMessage());
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), sq.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

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
    
    
    
}
