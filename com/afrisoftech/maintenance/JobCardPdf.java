//Author Charles Waweru
//Made to test Java support for Threads.
//Revision : Ver 1.0a
//import java.lang.*;
package com.afrisoftech.maintenance;

import com.afrisoftech.hospinventory.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;

public class JobCardPdf implements java.lang.Runnable {

    int seq = 0;
    java.lang.String bank = null;
    java.lang.String beginDate = null;
    com.afrisoftech.lib.DBObject dbObject;
    java.lang.String endDate = null;
    public static java.sql.Connection connectDB = null;
    public java.lang.String dbUserName = null;
    org.netbeans.lib.sql.pool.PooledConnectionSource pConnDB = null;
    boolean threadCheck = true;
    java.lang.Thread threadSample;
    com.lowagie.text.Font pFontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    com.lowagie.text.Font pFontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    com.lowagie.text.Font pFontNum = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    //   com.lowagie.text.ParagraphFont pgraph = Paragraph();
    java.lang.Runtime rtThreadSample = java.lang.Runtime.getRuntime();
    java.lang.Process prThread;
    private String jobCardNo=null;

    //public void StockItemsPdf(java.sql.Connection connDb, java.lang.String combox) {
    public void JobCardPdf(java.sql.Connection connDb, String jobNo){
        
        connectDB = connDb;
        jobCardNo=jobNo;
        
        // beginDate = begindate;
        // endDate = endate;
        dbObject = new com.afrisoftech.lib.DBObject();
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
        
        Document doc=new Document();
        PdfPTable table=new com.lowagie.text.pdf.PdfPTable(6);
        PdfPCell cell;
        //Paragraph para;
        Chunk chunk;
        com.lowagie.text.Font reportTitle = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD+Font.UNDERLINE);
        com.lowagie.text.Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
        com.lowagie.text.Font columnHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
        
        
        try{
            java.io.File tempFile=java.io.File.createTempFile("REP" + this.getDateLable() + "_", ".pdf");
            
            tempFile.deleteOnExit();
            
            
            
            com.lowagie.text.pdf.PdfWriter.getInstance(doc, new java.io.FileOutputStream(tempFile));
            
            doc.open();
            
                table.setWidthPercentage(100);
                table.setWidths(new float[]{0.7f,1.3f,1.0f,0.5f,0.5f,1f});
                    chunk=new Chunk("JOB CARD SERIAL NO:  "+jobCardNo,columnHeaders);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setColspan(6);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorderColor(Color.white);
            
                table.addCell(cell);
                    
                table.getDefaultCell().setColspan(6);
                table.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.getDefaultCell().setBorderColor(Color.white);
                table.getDefaultCell().setFixedHeight(50);
                table.addCell(Image.getInstance(com.afrisoftech.lib.CompanyLogo.getPath2Logo()));
                table.getDefaultCell().setFixedHeight(16);
                
                java.sql.Statement hospNameStt=connectDB.createStatement();
                java.sql.ResultSet hospNameRset=hospNameStt.executeQuery("SELECT hospital_name FROM pb_hospitalprofile");
                StringBuffer sBuff=new StringBuffer();
                
                
                while(hospNameRset.next()){
                    cell=new PdfPCell(new Paragraph(new Chunk(hospNameRset.getString(1).toUpperCase(),reportTitle)));
                    cell.setColspan(6);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorderColor(Color.white);
                    
                    table.addCell(cell);
                }
                
                String title="MAINTENANCE WORK INSTRUCTION/ JOB CARD.";
                cell=new PdfPCell(new Paragraph(new Chunk(title,reportTitle)));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                    
                table.addCell(cell);
                
                cell=new PdfPCell(new Paragraph(new Chunk("                     ")));//space, report looks crowded otherwise
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                    
                table.addCell(cell);
                
                java.sql.Statement sectionStt=connectDB.createStatement();
                java.sql.ResultSet sectionRset=sectionStt.executeQuery("SELECT section_assigned,officers_assigned FROM maintenance.job_card_details WHERE job_card_no ='"+jobCardNo+"'");
                sBuff=new StringBuffer();
                String s=null;
                
                while(sectionRset.next()){
                    sBuff.append(sectionRset.getString(1));
                    s=sectionRset.getString(2);
                }
                
                cell=new PdfPCell(new Paragraph(new Chunk(sBuff.toString(),columnHeaders)));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                
                table.addCell(cell);
                
                cell=new PdfPCell(new Paragraph(new Chunk("                     ")));//space, report looks crowded otherwise
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                    
                table.addCell(cell);
                
                
                sBuff=new StringBuffer();
                sBuff.append("OFFICER'S NAME: ").append(s);
                
                cell=new PdfPCell(new Paragraph(new Chunk(sBuff.toString(),columnHeaders)));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                
                table.addCell(cell);
                
                cell=new PdfPCell(new Paragraph(new Chunk("                     ")));//space, report looks crowded otherwise
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                    
                table.addCell(cell);
                
                chunk=new Chunk("INSTRUCTION DATE AND TIME ISSUED ",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                table.addCell(cell);
                
                chunk=new Chunk("E1 FORM NO. AND DESCRIPTION OF WORK TO BE CARRIED OUT",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(2);
                table.addCell(cell);
                
                chunk=new Chunk("EXPECTED DURATION OF WORK HOURS",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                table.addCell(cell);
                
                chunk=new Chunk("ACTUAL TIME TAKEN (HOURS) ",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                table.addCell(cell);
                
                chunk=new Chunk("WORK CERTIFIED COMPLETED BY INCHARGE OF DEPARTMENT, SECTION,DATE & TIME",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                table.addCell(cell);
                
                java.sql.Statement dataStt=connectDB.createStatement();
                java.sql.ResultSet dataRset=dataStt.executeQuery("SELECT time_instructed,jcd.e1_id||' '||ed.description,expected_duration,actual_duration,'Satisfaction Level - '||satisfaction_level||' Remarks - '||sender_completion_remarks||' Date & Time - '||sender_completion_time  FROM maintenance.job_card_details jcd "
                                                                                         + "INNER JOIN maintenance.e1_details ed ON jcd.e1_id=ed.e1_id "
                                                                                         + "WHERE job_card_no='"+jobCardNo+"'");
                
                while(dataRset.next()){
                    chunk=new Chunk(dataRset.getString(1),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataRset.getString(2),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    cell.setColspan(2);
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataRset.getString(3),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataRset.getString(4),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    table.addCell(cell);
                    
                    chunk=new Chunk(dataRset.getString(5),normal);
                    cell=new PdfPCell(new Paragraph(chunk));
                    table.addCell(cell);
                }
                
                cell=new PdfPCell(new Paragraph(new Chunk("                     ")));//space, report looks crowded otherwise
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.TOP);
                    
                table.addCell(cell);
                
                java.sql.Statement signOffStt=connectDB.createStatement();
                java.sql.ResultSet signOffRSet=dataStt.executeQuery("SELECT completion_maintenance_username,reason_work_pending FROM maintenance.job_card_details "
                                                                                         + "WHERE job_card_no='"+jobCardNo+"'");
                String supervisor=null,pending=null;
                
                while(signOffRSet.next()){
                    supervisor=signOffRSet.getString(1);
                    pending=signOffRSet.getString(2);
                }
                
                chunk=new Chunk("SUPERVISOR AUTHORISING FOR HOSPITAL ENGINEER.................."+supervisor,columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
                chunk=new Chunk("WORK PENDING REASON......................."+pending,columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
                chunk=new Chunk("..........................................................................................................................................................................................",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
                chunk=new Chunk("MATERIALS USED................................................................................................................................................................",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
                chunk=new Chunk("TRANSPORT........................................................................................................................................................................",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
                chunk=new Chunk("TOTAL COST........................................................................................................................................................................",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
                chunk=new Chunk("SIGN (COSTING CLERK)........................................................................................................................................................",columnHeaders);
                cell=new PdfPCell(new Paragraph(chunk));
                cell.setColspan(6);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderColor(Color.white);
                table.addCell(cell);
                
            doc.add(table);
            doc.close();
            com.afrisoftech.lib.PDFRenderer.renderPDF(tempFile);
        }
        catch(com.lowagie.text.DocumentException de){
            de.printStackTrace();
        }
        catch(java.io.IOException ioe){
            ioe.printStackTrace();
        }
        catch(java.sql.SQLException sqle){
            sqle.printStackTrace();
        }

    }

}
